package com.backbone.common;


import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import com.mysql.jdbc.Field;

public class Common {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		
	}
	
	 public static final String escapeHTMLTags(String input) {
	        //Check if the string is null or zero length -- if so, return
	        //what was sent in.
	        if (input == null || input.length() == 0) {
	            return input;
	        }
	        //Use a StringBuffer in lieu of String concatenation -- it is
	        //much more efficient this way.
	        StringBuffer buf = new StringBuffer(input.length());
	        char ch = ' ';
	        for (int i = 0; i < input.length(); i++) {
	            ch = input.charAt(i);
	            if (ch == '<') {
	                buf.append("&lt;");
	            }
	            else if (ch == '>') {
	                buf.append("&gt;");
	            }else if(ch == '"'){
	             buf.append("&quot;");
	            }else if(ch == '&'){
	             buf.append("&amp;");
	            }
	            else {
	                buf.append(ch);
	            }
	        }
	        return buf.toString();
	 }
	
	 public static Class getFieldType(Object obj,String propertyName)
	 {
		 Class fieldType = null;
			Class objclass = obj.getClass();
			while(fieldType == null )
			{
				try{
					fieldType = objclass.getDeclaredField(propertyName).getType();
				}
				catch(NoSuchFieldException e)
				{
					fieldType = null;
					objclass = objclass.getSuperclass();
					if(objclass == Object.class)
						break;
				}
			}
			return fieldType;
	 }
	
	public static void setObjectValue(Object obj, String propertyName, Object value) {
		try {
			Method[] ms = obj.getClass().getMethods();
			String str = toMehtodName(MethodType.set, propertyName);
			for (Method m : ms) {
				if (m.getName().equals(str)) {
					Class type = getFieldType(obj,propertyName);
					if(type != null)
						m.invoke(obj,Convert(type, value));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getObjectValue(Object obj, String propertyNamee) {
		try {
			Method[] ms = obj.getClass().getMethods();
			String str = toMehtodName(MethodType.get, propertyNamee);
			for (Method m : ms) {
				if (m.getName().equals(str)) {
					return m.invoke(obj);
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	public static Map<String, Object> getProperties() {
		return null;
	}

	private static String toMehtodName(MethodType type, String propertyName) {
		String str = type.name() + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1).toLowerCase();
		return type.name() + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1).toLowerCase();
	}

	public static enum MethodType {
		set, get
	}

	public static Object Convert(Class<?> clazz, Object obj) {
		try {
			if (clazz == Integer.class) {
				return Integer.valueOf( obj.toString());
			} else if (clazz == String.class) {
				return obj;
			} else if (clazz == Date.class) {
				return new Date( obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
