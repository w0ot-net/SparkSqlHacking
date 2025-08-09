package jodd.util.cl;

import java.lang.reflect.Array;
import java.util.Arrays;
import jodd.util.ReflectUtil;
import jodd.util.StringUtil;

public class DefaultClassLoaderStrategy implements ClassLoaderStrategy {
   public static final String[] PRIMITIVE_TYPE_NAMES = new String[]{"boolean", "byte", "char", "double", "float", "int", "long", "short"};
   public static final Class[] PRIMITIVE_TYPES;
   public static final char[] PRIMITIVE_BYTECODE_NAME;
   protected boolean loadArrayClassByComponentTypes = false;

   public boolean isLoadArrayClassByComponentTypes() {
      return this.loadArrayClassByComponentTypes;
   }

   public void setLoadArrayClassByComponentTypes(boolean loadArrayClassByComponentTypes) {
      this.loadArrayClassByComponentTypes = loadArrayClassByComponentTypes;
   }

   public static String prepareArrayClassnameForLoading(String className) {
      int bracketCount = StringUtil.count(className, '[');
      if (bracketCount == 0) {
         return null;
      } else {
         String brackets = StringUtil.repeat('[', bracketCount);
         int bracketIndex = className.indexOf(91);
         className = className.substring(0, bracketIndex);
         int primitiveNdx = getPrimitiveClassNameIndex(className);
         if (primitiveNdx >= 0) {
            className = String.valueOf(PRIMITIVE_BYTECODE_NAME[primitiveNdx]);
            return brackets + className;
         } else {
            return brackets + 'L' + className + ';';
         }
      }
   }

   private static int getPrimitiveClassNameIndex(String className) {
      int dotIndex = className.indexOf(46);
      return dotIndex != -1 ? -1 : Arrays.binarySearch(PRIMITIVE_TYPE_NAMES, className);
   }

   public Class loadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
      String arrayClassName = prepareArrayClassnameForLoading(className);
      if (className.indexOf(46) == -1 && arrayClassName == null) {
         int primitiveNdx = getPrimitiveClassNameIndex(className);
         if (primitiveNdx >= 0) {
            return PRIMITIVE_TYPES[primitiveNdx];
         }
      }

      if (classLoader != null) {
         Class klass = this.loadClass(className, arrayClassName, classLoader);
         if (klass != null) {
            return klass;
         }
      }

      ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
      if (currentThreadClassLoader != null && currentThreadClassLoader != classLoader) {
         Class klass = this.loadClass(className, arrayClassName, currentThreadClassLoader);
         if (klass != null) {
            return klass;
         }
      }

      Class callerClass = ReflectUtil.getCallerClass();
      ClassLoader callerClassLoader = callerClass.getClassLoader();
      if (callerClassLoader != classLoader && callerClassLoader != currentThreadClassLoader) {
         Class klass = this.loadClass(className, arrayClassName, callerClassLoader);
         if (klass != null) {
            return klass;
         }
      }

      if (arrayClassName != null) {
         try {
            return this.loadArrayClassByComponentType(className, classLoader);
         } catch (ClassNotFoundException var8) {
         }
      }

      throw new ClassNotFoundException("Class not found: " + className);
   }

   protected Class loadClass(String className, String arrayClassName, ClassLoader classLoader) {
      if (arrayClassName != null) {
         try {
            if (this.loadArrayClassByComponentTypes) {
               return this.loadArrayClassByComponentType(className, classLoader);
            }

            return Class.forName(arrayClassName, true, classLoader);
         } catch (ClassNotFoundException var6) {
         }
      }

      try {
         return classLoader.loadClass(className);
      } catch (ClassNotFoundException var5) {
         return null;
      }
   }

   protected Class loadArrayClassByComponentType(String className, ClassLoader classLoader) throws ClassNotFoundException {
      int ndx = className.indexOf(91);
      int multi = StringUtil.count(className, '[');
      String componentTypeName = className.substring(0, ndx);
      Class componentType = this.loadClass(componentTypeName, classLoader);
      if (multi == 1) {
         return Array.newInstance(componentType, 0).getClass();
      } else {
         int[] multiSizes;
         if (multi == 2) {
            multiSizes = new int[]{0, 0};
         } else if (multi == 3) {
            multiSizes = new int[]{0, 0, 0};
         } else {
            multiSizes = (int[])Array.newInstance(Integer.TYPE, multi);
         }

         return Array.newInstance(componentType, multiSizes).getClass();
      }
   }

   static {
      PRIMITIVE_TYPES = new Class[]{Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE};
      PRIMITIVE_BYTECODE_NAME = new char[]{'Z', 'B', 'C', 'D', 'F', 'I', 'J', 'S'};
   }
}
