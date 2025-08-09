package org.codehaus.commons.compiler.samples;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DemoBase {
   protected DemoBase() {
   }

   public static Object createObject(Class type, String value) throws NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
      if (type.isPrimitive()) {
         type = type == Boolean.TYPE ? Boolean.class : (type == Character.TYPE ? Character.class : (type == Byte.TYPE ? Byte.class : (type == Short.TYPE ? Short.class : (type == Integer.TYPE ? Integer.class : (type == Long.TYPE ? Long.class : (type == Float.TYPE ? Float.class : (type == Double.TYPE ? Double.class : Void.TYPE)))))));
      }

      return "".equals(value) ? type.getConstructor().newInstance() : type.getConstructor(String.class).newInstance(value);
   }

   public static String[] explode(String s) {
      StringTokenizer st = new StringTokenizer(s, ",");
      List<String> l = new ArrayList();

      while(st.hasMoreTokens()) {
         l.add(st.nextToken());
      }

      return (String[])l.toArray(new String[l.size()]);
   }

   public static Class stringToType(String s) {
      int brackets;
      for(brackets = 0; s.endsWith("[]"); s = s.substring(0, s.length() - 2)) {
         ++brackets;
      }

      if (brackets == 0) {
         if ("void".equals(s)) {
            return Void.TYPE;
         }

         if ("boolean".equals(s)) {
            return Boolean.TYPE;
         }

         if ("char".equals(s)) {
            return Character.TYPE;
         }

         if ("byte".equals(s)) {
            return Byte.TYPE;
         }

         if ("short".equals(s)) {
            return Short.TYPE;
         }

         if ("int".equals(s)) {
            return Integer.TYPE;
         }

         if ("long".equals(s)) {
            return Long.TYPE;
         }

         if ("float".equals(s)) {
            return Float.TYPE;
         }

         if ("double".equals(s)) {
            return Double.TYPE;
         }
      }

      if ("void".equals(s)) {
         s = "V";
      } else if ("boolean".equals(s)) {
         s = "Z";
      } else if ("char".equals(s)) {
         s = "C";
      } else if ("byte".equals(s)) {
         s = "B";
      } else if ("short".equals(s)) {
         s = "S";
      } else if ("int".equals(s)) {
         s = "I";
      } else if ("long".equals(s)) {
         s = "J";
      } else if ("float".equals(s)) {
         s = "F";
      } else if ("double".equals(s)) {
         s = "D";
      }

      while(true) {
         --brackets;
         if (brackets < 0) {
            try {
               return Class.forName(s);
            } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
               System.exit(1);
               throw new RuntimeException();
            }
         }

         s = '[' + s;
      }
   }

   public static Class[] stringToTypes(String s) {
      StringTokenizer st = new StringTokenizer(s, ",");
      List<Class<?>> l = new ArrayList();

      while(st.hasMoreTokens()) {
         l.add(stringToType(st.nextToken()));
      }

      Class<?>[] res = new Class[l.size()];
      l.toArray(res);
      return res;
   }
}
