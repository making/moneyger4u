package am.ik.moneyger4u.common.bean;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.Assert;

/**
 * Convenience utility for JavaBeans to copy properties and populate.<br>
 */
public class BeanConverter {
    /**
     * default conversion service.
     */
    private static final ConversionService DEFAULT_SERVICE = new DefaultFormattingConversionService();

    /**
     * conversion service to use in converting beans.
     */
    private final ConversionService conversionService;

    /**
     * default constructor which uses {@link DefaultFormattingConversionService} to convert beans.<br>
     */
    public BeanConverter() {
        this.conversionService = DEFAULT_SERVICE;
    }

    /**
     * constructor which uses the given conversion service to convert beans.<br>
     * @param conversionService conversion service to convert beans
     */
    public BeanConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * constructor which uses {@link DefaultFormattingConversionService} adding the given {@link Formatter}'s to convert beans.<br>
     * <br>
     * @param formatters formatters to convert beans
     */
    public BeanConverter(Formatter<?>... formatters) {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (Formatter<?> formatter : formatters) {
            conversionService.addFormatter(formatter);
        }
        this.conversionService = conversionService;
    }

    /**
     * constructor which uses {@link DefaultFormattingConversionService} adding the given {@link Converter}'s to convert beans.<br>
     * <br>
     * @param converters converters to convert beans
     */
    public BeanConverter(Converter<?, ?>... converters) {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (Converter<?, ?> converter : converters) {
            conversionService.addConverter(converter);
        }
        this.conversionService = conversionService;
    }

    /**
     * Copy the property values of the given source bean into the target bean.<br>
     * <p>
     * <strong>property name of source bean and target bean must be same.</strong> <br>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new TargetBean();
     * // target.setName(null);
     * // target.setBloodType(null);
     * target.setAge(20);
     * target.setPassword(null);
     * 
     * new BeanConverter().convert(source, target,
     *         IgnoreOption.NOT_NULL_TARGET, new String[] { "password" });
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>20</code><br>
     * <code>target.getPassword()</code> returns <code>null</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param target must not be null
     * @param option must not be (not ignore if option is null)
     * @param ignoreProperties property names not to copy
     * @see IgnoreOption#NULL_SOURCE
     * @see IgnoreOption#EMPTY_SOURCE
     * @see IgnoreOption#WHITESPACE_SOURCE
     * @see IgnoreOption#NOT_NULL_TARGET
     * @see IgnoreOption#NOT_EMPTY_TARGET
     * @see IgnoreOption#NOT_WHITESPACE_TARGET
     * @throws ConversionException when conversion failed
     */
    public void convert(Object source, Object target, IgnoreOption option,
            String[] ignoreProperties) {
        Assert.notNull(source, "source must not be null!");
        Assert.notNull(target, "target must not be null!");

        BeanWrapper sourceWrapper = PropertyAccessorFactory
                .forBeanPropertyAccess(source);
        BeanWrapper targetWrapper = PropertyAccessorFactory
                .forBeanPropertyAccess(target);

        Set<String> ignorePropertiesSet;
        if (ignoreProperties == null) {
            ignorePropertiesSet = null;
        } else {
            ignorePropertiesSet = new HashSet<String>();
            for (String proprty : ignoreProperties) {
                ignorePropertiesSet.add(proprty);
            }
        }

        PropertyDescriptor[] sourceDescriptors = sourceWrapper
                .getPropertyDescriptors();
        for (PropertyDescriptor pd : sourceDescriptors) {
            String propertyName = pd.getName();
            TypeDescriptor targetType = targetWrapper
                    .getPropertyTypeDescriptor(propertyName);

            if (targetType == null
                    || !targetWrapper.isWritableProperty(propertyName)) {
                // skip if target is not writable
                continue;
            }
            if (!sourceWrapper.isReadableProperty(propertyName)
                    || !targetWrapper.isReadableProperty(propertyName)) {
                // skip if property is not readable
                continue;
            }
            if (ignorePropertiesSet != null
                    && ignorePropertiesSet.contains(propertyName)) {
                // skip if property name should be ignored
                continue;
            }

            Object sourceValue = sourceWrapper.getPropertyValue(propertyName);
            if (option != null && option.isForSource()
                    && option.isIgnoreValue(sourceValue)) {
                // skip if source value should be ignored
                continue;
            }
            Object targetValue = targetWrapper.getPropertyValue(propertyName);
            if (option != null && option.isForTarget()
                    && option.isIgnoreValue(targetValue)) {
                // skip if target value should be ignored
                continue;
            }

            Object value = null;
            if (sourceValue == null) {
                if (targetType.isPrimitive()) {
                    continue;
                }
                value = null;
            } else if (conversionService == null) {
                value = sourceValue;
            } else {
                TypeDescriptor sourceType = sourceWrapper
                        .getPropertyTypeDescriptor(propertyName);
                if (conversionService.canConvert(sourceType, targetType)) {
                    value = conversionService.convert(sourceValue, sourceType,
                            targetType);
                } else if (sourceValue != null) {
                    // recurse if sourceValue can not convert to targetValue
                    if (targetValue == null) {
                        targetValue = BeanUtils.instantiate(targetType
                                .getType());
                    }
                    // TODO what about ignoreProperties?
                    convert(sourceValue, targetValue, option);
                    value = targetValue;
                }

            }
            targetWrapper.setPropertyValue(propertyName, value);
        }
    }

    /**
     * Copy the property values of the given source bean into the target bean.<br>
     * <p>
     * <strong>property name of source bean and target bean must be same.</strong> <br>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new TargetBean();
     * // target.setName(null);
     * // target.setBloodType(null);
     * target.setAge(20);
     * target.setPassword(null);
     * 
     * new BeanConverter().convert(source, target,
     *         IgnoreOption.NOT_NULL_TARGET);
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>20</code><br>
     * <code>target.getPassword()</code> returns <code>"aaaa"</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param target must not be null
     * @param option (not ignore if option is null)
     * @see IgnoreOption#NULL_SOURCE
     * @see IgnoreOption#EMPTY_SOURCE
     * @see IgnoreOption#WHITESPACE_SOURCE
     * @see IgnoreOption#NOT_NULL_TARGET
     * @see IgnoreOption#NOT_EMPTY_TARGET
     * @see IgnoreOption#NOT_WHITESPACE_TARGET
     */
    public void convert(Object source, Object target, IgnoreOption option) {
        convert(source, target, option, null);
    }

    /**
     * Copy the property values of the given source bean into the target bean.<br>
     * <p>
     * <strong>property name of source bean and target bean must be same.</strong> <br>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new TargetBean();
     * // target.setName(null);
     * // target.setBloodType(null);
     * target.setAge(20);
     * target.setPassword(null);
     * 
     * new BeanConverter().convert(source, target,
     *         new String[] { "password" });
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>100</code><br>
     * <code>target.getPassword()</code> returns <code>null</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param target must not be null
     * @param ignoreProperties property names not to copy
     */
    public void convert(Object source, Object target, String[] ignoreProperties) {
        convert(source, target, null, ignoreProperties);
    }

    /**
     * Copy the property values of the given source bean into the target bean.<br>
     * <p>
     * <strong>property name of source bean and target bean must be same.</strong> <br>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new TargetBean();
     * // target.setName(null);
     * // target.setBloodType(null);
     * target.setAge(20);
     * target.setPassword(null);
     * 
     * new BeanConverter().convert(source, target);
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>10</code><br>
     * <code>target.getPassword()</code> returns <code>"aaaa"</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param target must not be null
     */
    public void convert(Object source, Object target) {
        convert(source, target, null, null);
    }

    /**
     * Instantiate a target class using its no-arg constructor and copy the property values of the given source bean.<br>
     * <p>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new BeanConverter().populate(source, TargetBean.class,
     *         IgnoreOption.NOT_NULL_TARGET, new String[] { "password" });
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>100</code><br>
     * <code>target.getPassword()</code> returns <code>null</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param targetClass must not be null
     * @param option must not be (not ignore if option is null)
     * @param ignoreProperties property names not to copy
     * @see #convert(Object, Object, IgnoreOption, String[])
     * @see IgnoreOption#NULL_SOURCE
     * @see IgnoreOption#EMPTY_SOURCE
     * @see IgnoreOption#WHITESPACE_SOURCE
     * @see IgnoreOption#NOT_NULL_TARGET
     * @see IgnoreOption#NOT_EMPTY_TARGET
     * @see IgnoreOption#NOT_WHITESPACE_TARGET
     * @return target object
     * @throws BeanInstantiationException if the bean cannot be instantiated
     */
    public <T> T populate(Object source, Class<T> targetClass,
            IgnoreOption option, String[] ignoreProperties) {
        T target = BeanUtils.instantiateClass(targetClass);
        convert(source, target, option, ignoreProperties);
        return target;
    }

    /**
     * Instantiate a target class using its no-arg constructor and copy the property values of the given source bean.<br>
     * <p>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new BeanConverter().populate(source, TargetBean.class,
     *         IgnoreOption.NOT_NULL_TARGET);
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>100</code><br>
     * <code>target.getPassword()</code> returns <code>"aaaa"</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param targetClass must not be null
     * @param option must not be (not ignore if option is null)
     * @see #convert(Object, Object, IgnoreOption)
     * @see IgnoreOption#NULL_SOURCE
     * @see IgnoreOption#EMPTY_SOURCE
     * @see IgnoreOption#WHITESPACE_SOURCE
     * @see IgnoreOption#NOT_NULL_TARGET
     * @see IgnoreOption#NOT_EMPTY_TARGET
     * @see IgnoreOption#NOT_WHITESPACE_TARGET
     * @return target object
     * @throws BeanInstantiationException if the bean cannot be instantiated
     */
    public <T> T populate(Object source, Class<T> targetClass,
            IgnoreOption option) {
        T target = BeanUtils.instantiateClass(targetClass);
        convert(source, target, option);
        return target;
    }

    /**
     * Instantiate a target class using its no-arg constructor and copy the property values of the given source bean.<br>
     * <p>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new BeanConverter().populate(source, TargetBean.class,
     *         new String[] { "password" });
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>100</code><br>
     * <code>target.getPassword()</code> returns <code>null</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param targetClass must not be null
     * @param ignoreProperties property names not to copy
     * @see #convert(Object, Object, String[])
     * @return target object
     * @throws BeanInstantiationException if the bean cannot be instantiated
     */
    public <T> T populate(Object source, Class<T> targetClass,
            String[] ignoreProperties) {
        T target = BeanUtils.instantiateClass(targetClass);
        convert(source, target, ignoreProperties);
        return target;
    }

    /**
     * Instantiate a target class using its no-arg constructor and copy the property values of the given source bean.<br>
     * <p>
     * For example.
     * </p>
     * 
     * <pre>
     * <code>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setBloodType("A");
     * source.setAge(100);
     * source.setPassword("aaaa");
     * 
     * TargetBean target = new BeanConverter().populate(source, TargetBean.class);
     * 
     * </code>
     * </pre>
     * <p>
     * In this case,<br>
     * <code>target.getName()</code> returns <code>"Bar"</code><br>
     * <code>target.getBloodType()</code> returns <code>"A"</code><br>
     * <code>target.getAge()</code> returns <code>100</code><br>
     * <code>target.getPassword()</code> returns <code>"aaaa"</code><br>
     * </p>
     * @param source source bean (must not be null)
     * @param targetClass must not be null
     * @see #convert(Object, Object)
     * @return target object
     * @throws BeanInstantiationException if the bean cannot be instantiated
     */
    public <T> T populate(Object source, Class<T> targetClass) {
        T target = BeanUtils.instantiateClass(targetClass);
        convert(source, target);
        return target;
    }
}
