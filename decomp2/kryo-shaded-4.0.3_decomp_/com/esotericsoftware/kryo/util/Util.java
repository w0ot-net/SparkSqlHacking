package com.esotericsoftware.kryo.util;

import com.esotericsoftware.minlog.Log;
import java.util.concurrent.ConcurrentHashMap;

public class Util {
   public static final boolean IS_ANDROID = "Dalvik".equals(System.getProperty("java.vm.name"));
   /** @deprecated */
   @Deprecated
   public static boolean isAndroid;
   private static final ConcurrentHashMap classAvailabilities;

   public static boolean isClassAvailable(String className) {
      Boolean result = (Boolean)classAvailabilities.get(className);
      if (result == null) {
         try {
            Class.forName(className);
            result = true;
         } catch (Exception var3) {
            Log.debug("kryo", "Class not available: " + className);
            result = false;
         }

         classAvailabilities.put(className, result);
      }

      return result;
   }

   public static Class getWrapperClass(Class type) {
      if (type == Integer.TYPE) {
         return Integer.class;
      } else if (type == Float.TYPE) {
         return Float.class;
      } else if (type == Boolean.TYPE) {
         return Boolean.class;
      } else if (type == Long.TYPE) {
         return Long.class;
      } else if (type == Byte.TYPE) {
         return Byte.class;
      } else if (type == Character.TYPE) {
         return Character.class;
      } else if (type == Short.TYPE) {
         return Short.class;
      } else {
         return type == Double.TYPE ? Double.class : Void.class;
      }
   }

   public static Class getPrimitiveClass(Class type) {
      if (type == Integer.class) {
         return Integer.TYPE;
      } else if (type == Float.class) {
         return Float.TYPE;
      } else if (type == Boolean.class) {
         return Boolean.TYPE;
      } else if (type == Long.class) {
         return Long.TYPE;
      } else if (type == Byte.class) {
         return Byte.TYPE;
      } else if (type == Character.class) {
         return Character.TYPE;
      } else if (type == Short.class) {
         return Short.TYPE;
      } else if (type == Double.class) {
         return Double.TYPE;
      } else {
         return type == Void.class ? Void.TYPE : type;
      }
   }

   public static boolean isWrapperClass(Class type) {
      return type == Integer.class || type == Float.class || type == Boolean.class || type == Long.class || type == Byte.class || type == Character.class || type == Short.class || type == Double.class;
   }

   public static void log(String message, Object object) {
      if (object == null) {
         if (Log.TRACE) {
            Log.trace("kryo", message + ": null");
         }

      } else {
         Class type = object.getClass();
         if (!type.isPrimitive() && type != Boolean.class && type != Byte.class && type != Character.class && type != Short.class && type != Integer.class && type != Long.class && type != Float.class && type != Double.class && type != String.class) {
            Log.debug("kryo", message + ": " + string(object));
         } else if (Log.TRACE) {
            Log.trace("kryo", message + ": " + string(object));
         }

      }
   }

   public static String string(Object object) {
      if (object == null) {
         return "null";
      } else {
         Class type = object.getClass();
         if (type.isArray()) {
            return className(type);
         } else {
            try {
               if (type.getMethod("toString").getDeclaringClass() == Object.class) {
                  return Log.TRACE ? className(type) : type.getSimpleName();
               }
            } catch (Exception var3) {
            }

            try {
               return String.valueOf(object);
            } catch (Throwable e) {
               return (Log.TRACE ? className(type) : type.getSimpleName()) + "(Exception " + e + " in toString)";
            }
         }
      }
   }

   public static String className(Class type) {
      if (!type.isArray()) {
         return !type.isPrimitive() && type != Object.class && type != Boolean.class && type != Byte.class && type != Character.class && type != Short.class && type != Integer.class && type != Long.class && type != Float.class && type != Double.class && type != String.class ? type.getName() : type.getSimpleName();
      } else {
         Class elementClass = getElementClass(type);
         StringBuilder buffer = new StringBuilder(16);
         int i = 0;

         for(int n = getDimensionCount(type); i < n; ++i) {
            buffer.append("[]");
         }

         return className(elementClass) + buffer;
      }
   }

   public static int getDimensionCount(Class arrayClass) {
      int depth = 0;

      for(Class nextClass = arrayClass.getComponentType(); nextClass != null; nextClass = nextClass.getComponentType()) {
         ++depth;
      }

      return depth;
   }

   public static Class getElementClass(Class arrayClass) {
      Class elementClass;
      for(elementClass = arrayClass; elementClass.getComponentType() != null; elementClass = elementClass.getComponentType()) {
      }

      return elementClass;
   }

   public static int swapInt(int i) {
      return (i & 255) << 24 | (i & '\uff00') << 8 | (i & 16711680) >> 8 | i >> 24 & 255;
   }

   public static long swapLong(long value) {
      return (value >> 0 & 255L) << 56 | (value >> 8 & 255L) << 48 | (value >> 16 & 255L) << 40 | (value >> 24 & 255L) << 32 | (value >> 32 & 255L) << 24 | (value >> 40 & 255L) << 16 | (value >> 48 & 255L) << 8 | (value >> 56 & 255L) << 0;
   }

   static {
      isAndroid = IS_ANDROID;
      classAvailabilities = new ConcurrentHashMap();
   }
}
