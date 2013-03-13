package am.ik.moneyger4u.common.bean;

import org.springframework.util.StringUtils;

/**
 * Ignore options for {@link BeanConverter}
 * <p>
 * This enum provides some options to ignore to copying properties in using
 * {@link BeanConverter#convert(Object, Object, IgnoreOption)} and so on. <br>
 * In this context, "ignore" means "DO NOT CONVERT".
 * </p>
 */
public enum IgnoreOption {
    /**
     * ignores when <code>source</code>'s property is null.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>source</code>'s property is not null. <br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName(<font color=red>null</font>);
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName("Foo");
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.EMPTY_NULL);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Foo"</code> and <code>target.getAge()</code> returns
     * <code>100</code>.
     * </p>
     */
    NULL_SOURCE(true, false) {
        @Override
        public boolean isIgnoreValue(Object value) {
            return value == null;
        }
    },
    /**
     * ignores when <code>source</code>'s property is null or empty.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>source</code>'s property is not null and
     * empty. <br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName(<font color=red>""</font>);
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName("Foo");
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.EMPTY_SOURCE);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Foo"</code> and <code>target.getAge()</code> returns
     * <code>100</code>.
     * </p>
     */
    EMPTY_SOURCE(true, false) {
        @Override
        public boolean isIgnoreValue(Object value) {
            return value == null || "".equals(value);
        }
    },
    /**
     * ignores when <code>source</code>'s property is null or whitespace.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>source</code>'s property is not null and
     * whitespace. <br>
     * if object is whitespace, {@link StringUtils#hasText(CharSequence)} returns false<br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName(<font color=red>"     "</font>);
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName("Foo");
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.WHITESPACE_SOURCE);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Foo"</code> and <code>target.getAge()</code> returns
     * <code>100</code>.
     * </p>
     */
    WHITESPACE_SOURCE(true, false) {
        @Override
        public boolean isIgnoreValue(Object value) {
            if (value == null) {
                return true;
            }
            if (value instanceof CharSequence) {
                return !StringUtils.hasText(((CharSequence) value));
            }
            return false;
        }
    },
    /**
     * ignores when <code>target</code>'s property is not null.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>target</code>'s property is null. <br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName(<font color=red>null</font>);
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.NOT_NULL_TARGET);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Bar"</code> and <code>target.getAge()</code> returns
     * <code>20</code>.
     * </p>
     */
    NOT_NULL_TARGET(false, true) {
        @Override
        public boolean isIgnoreValue(Object value) {
            return value != null;
        }
    },
    /**
     * ignores when <code>target</code>'s property is not null and empty.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>target</code>'s property is null or empty. <br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName(<font color=red>""</font>);
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.NOT_EMPTY_TARGET);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Bar"</code> and <code>target.getAge()</code> returns
     * <code>20</code>.
     * </p>
     */
    NOT_EMPTY_TARGET(false, true) {
        @Override
        public boolean isIgnoreValue(Object value) {
            return value != null && !"".equals(value);
        }
    },
    /**
     * ignores when <code>target</code>'s property is not null and whitespace.<br>
     * This means copy <code>source</code> to <code>target</code> only if the <code>target</code>'s property is null or empty. <br>
     * if object is whitespace, {@link StringUtils#hasText(CharSequence)} returns false<br>
     * For example.
     * 
     * <pre>
     * SourceBean source = new SourceBean();
     * source.setName("Bar");
     * source.setAge(100);
     * 
     * TargetBean target = new TargetBean();
     * target.setName(<font color=red>"     "</font>);
     * target.setAge(20);
     * 
     * new BeanConverter().convert(source, target, IgnoreOption.NOT_EMPTY_TARGET);
     * </pre>
     * <p>
     * In this case, <code>target.getName()</code> returns <code>"Bar"</code> and <code>target.getAge()</code> returns
     * <code>20</code>.
     * </p>
     */
    NOT_WHITESPACE_TARGET(false, true) {
        @Override
        public boolean isIgnoreValue(Object value) {
            if (value == null) {
                return false;
            }
            if (value instanceof CharSequence) {
                return StringUtils.hasText(((CharSequence) value));
            }
            return true;
        }
    };

    /**
     * whether check for source's property.
     */
    private final boolean forSource;

    /**
     * whether check for target's property.
     */
    private final boolean forTarget;

    /**
     * Constructor<br>
     * @param forSource whether check for source's property.
     * @param forTarget whether check for target's property.
     */
    private IgnoreOption(boolean forSource, boolean forTarget) {
        this.forSource = forSource;
        this.forTarget = forTarget;
    }

    /**
     * Returns whether ignore the given property.<br>
     * @param value value which is checked to ignore
     * @return whether ignore the given property(<code>true</code>=ignore, <code>false</code>=not ignore).
     */
    public abstract boolean isIgnoreValue(Object value);

    /**
     * Returns whether check for source's property.
     * @return whether check for source's property.
     */
    public boolean isForTarget() {
        return this.forTarget;
    }

    /**
     * Returns whether check for target's property.
     * @return whether check for target's property.
     */
    public boolean isForSource() {
        return this.forSource;
    }

}
