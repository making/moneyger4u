package am.ik.moneyger4u.app.common.el;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Functions {
	private static final Pattern URL_PATTERN = Pattern
			.compile("(http|https)://[A-Za-z0-9\\._~/:\\-?&=%;]+");

	private static final String BR = "<br />";

	private static final FormattingConversionService CONVERSION_SERVICE = new DefaultFormattingConversionService();

	private static final TypeDescriptor STRING_DESC = TypeDescriptor
			.valueOf(String.class);

	/**
	 * escape html tags in the given string. *
	 * <p>
	 * target characters to escape are following <br>
	 * &lt; ====&gt; &amp;lt;<br>
	 * &gt; ====&gt; &amp;gt;<br>
	 * &amp; ====&gt; &amp;amp;<br>
	 * " ====&gt; &amp;quot;<br>
	 * ' ====&gt; &amp;#39;<br>
	 * </p>
	 * 
	 * @param input
	 *            string to escape
	 * @return escaped string. returns empty string if <code>value</code> is
	 *         <code>null</code>.
	 * @see HtmlUtils#htmlEscape(String)
	 */
	public static String h(Object input) {
		if (input == null) {
			return "";
		}
		String str = "";
		if (input.getClass().isArray()) {
			Class<?> clazz = input.getClass().getComponentType();
			if (clazz == String.class) {
				str = Arrays.toString((Object[]) input);
			} else if (clazz == boolean.class) {
				str = Arrays.toString((boolean[]) input);
			} else if (clazz == char.class) {
				str = Arrays.toString((char[]) input);
			} else if (clazz == int.class) {
				str = Arrays.toString((int[]) input);
			} else if (clazz == long.class) {
				str = Arrays.toString((long[]) input);
			} else if (clazz == byte.class) {
				str = Arrays.toString((byte[]) input);
			} else if (clazz == short.class) {
				str = Arrays.toString((short[]) input);
			} else if (clazz == float.class) {
				str = Arrays.toString((float[]) input);
			} else if (clazz == double.class) {
				str = Arrays.toString((double[]) input);
			} else {
				str = Arrays.toString((Object[]) input);
			}
		} else {
			str = input.toString();
		}

		return HtmlUtils.htmlEscape(str);
	}

	/**
	 * url encode the given string.
	 * 
	 * @param value
	 *            string to encode
	 * @return encoded string. returns empty string if <code>value</code> is
	 *         <code>null</code>.
	 * @see UriComponents#encode()
	 */
	public static String u(String value) {
		if (value == null) {
			return "";
		}
		UriComponents components = UriComponentsBuilder.fromPath(value).build()
				.encode();
		return components.toString();
	}

	/**
	 * convert <code>&quot;\r\n&quot;</code>,<code>&quot;\r&quot;</code>,
	 * <code>&quot;\n&quot;</code> to <code>&lt;br&gt;</code>
	 * 
	 * @param value
	 *            string to convert
	 * @return converted string. returns empty string if <code>value</code> is
	 *         <code>null</code>.
	 */
	public static String br(String value) {
		if (value == null) {
			return "";
		}
		value = value.replace("\r\n", BR);
		value = value.replace("\r", BR);
		value = value.replace("\n", BR);
		return value;
	}

	/**
	 * cut the given string from head to the given length.
	 * 
	 * @param value
	 *            string to be cut
	 * @param length
	 *            length of cut string
	 * @return cut string. returns empty string if <code>value</code> is
	 *         <code>null</code>.
	 */
	public static String cut(String value, int length) {
		if (value == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0, l = value.length(); i < l; i++) {
			if (i >= length) {
				break;
			}
			sb.append(value.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * convert URL to anchor in the given string.
	 * 
	 * @param value
	 *            string to convert
	 * @return converted string. returns empty string if <code>value</code> is
	 *         <code>null</code>.
	 */
	public static String link(String value) {
		if (value == null) {
			return "";
		}
		return URL_PATTERN.matcher(value).replaceAll("<a href=\"$0\">$0</a>");
	}

	/**
	 * build query string from map.
	 * 
	 * @param map
	 *            map
	 * @return query string
	 */
	public static String mapToQuery(Map<String, Object> map) {
		return mapToQuery(map, null);
	}

	/**
	 * build query string from map with the specified {@link BeanWrapper}.
	 * 
	 * @param map
	 *            map
	 * @param beanWrapper
	 *            beanWrapper which has the definition of each field.
	 * @return query string
	 */
	public static String mapToQuery(Map<String, Object> map,
			BeanWrapper beanWrapper) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		Map<String, Object> attr = new HashMap<String, Object>();
		for (Map.Entry<String, Object> e : map.entrySet()) {
			String name = e.getKey();
			Object value = e.getValue();
			builder.path("&" + name + "={" + name + "}");
			TypeDescriptor sourceType;
			if (beanWrapper != null) {
				sourceType = beanWrapper.getPropertyTypeDescriptor(name);
			} else {
				sourceType = TypeDescriptor.forObject(value);
			}
			attr.put(name,
					CONVERSION_SERVICE.convert(value, sourceType, STRING_DESC));
		}
		return builder.buildAndExpand(attr).encode().toString();
	}

	/**
	 * build query string from map or bean.
	 * 
	 * @param params
	 *            map or bean
	 * @return query string. returns empty string if <code>params</code> is
	 *         <code>null</code> or {@link Iterable} or
	 *         {@link BeanUtils#isSimpleValueType(Class)}.
	 */
	@SuppressWarnings("unchecked")
	public static String query(Object params) {
		if (params == null) {
			return "";
		}
		Class<?> clazz = params.getClass();
		if (clazz.isArray() || params instanceof Iterable
				|| BeanUtils.isSimpleValueType(clazz)) {
			return "";
		}

		String query;
		if (params instanceof Map) {
			query = mapToQuery((Map<String, Object>) params);
		} else {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			BeanWrapper beanWrapper = PropertyAccessorFactory
					.forBeanPropertyAccess(params);
			PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();
				if (!"class".equals(name)) {
					Object value = beanWrapper.getPropertyValue(name);
					map.put(name, value);
				}
			}
			query = mapToQuery(map, beanWrapper);
		}
		if (query.startsWith("&")) {
			return query.substring(1);
		} else {
			return query;
		}
	}

	/**
	 * escape javascript in the given string supposed to be surrounded by
	 * single-quote.<br>
	 * <p>
	 * example
	 * </p>
	 * 
	 * <pre>
	 * &lt;script type="text/javascript"&gt;
	 *   var message = '${f:js(message)}';
	 *   ...
	 * &lt;/script&gt;
	 * </pre>
	 * 
	 * target characters to escape are following <br>
	 * ' ====&gt; \'<br>
	 * " ====&gt; \"<br>
	 * \ ====&gt; \\<br>
	 * / ====&gt; \/<br>
	 * < ====&gt; \x3c<br>
	 * > ====&gt; \x3e<br>
	 * 0x0D ====&gt; \r<br>
	 * 0x0A ====&gt; \n<br>
	 * </p>
	 * 
	 * @param value
	 *            string to escape
	 * @return escaped string
	 */
	public static String js(String value) {
		if (value == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			switch (ch) {
			case '\'':
				result.append("\\'");
				break;
			case '"':
				result.append("\\\"");
				break;
			case '\\':
				result.append("\\\\");
				break;
			case '/':
				result.append("\\/");
				break;
			case '<':
				result.append("\\x3c");
				break;
			case '>':
				result.append("\\x3e");
				break;
			case '\r':
				result.append("\\r");
				break;
			case '\n':
				result.append("\\n");
				break;
			default:
				result.append(ch);
				break;
			}
		}
		return result.toString();
	}

	/**
	 * escape html (by {@link h}) after escape js (by {@link js})<br>
	 * <p>
	 * This is used to escape evnet handler (ex. onclick="${f:hjs(xxxx)}").
	 * 
	 * This funcation equals to ${f:h(f:js(xxx))}.
	 * </p>
	 * 
	 * @param input
	 * @return
	 */
	public static String hjs(String input) {
		return h(js(input));
	}
}