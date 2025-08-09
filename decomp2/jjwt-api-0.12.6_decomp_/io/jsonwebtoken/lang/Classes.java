package io.jsonwebtoken.lang;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public final class Classes {
   private static final ClassLoaderAccessor THREAD_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
      protected ClassLoader doGetClassLoader() {
         return Thread.currentThread().getContextClassLoader();
      }
   };
   private static final ClassLoaderAccessor CLASS_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
      protected ClassLoader doGetClassLoader() {
         return Classes.class.getClassLoader();
      }
   };
   private static final ClassLoaderAccessor SYSTEM_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
      protected ClassLoader doGetClassLoader() {
         return ClassLoader.getSystemClassLoader();
      }
   };

   private Classes() {
   }

   public static Class forName(String fqcn) throws UnknownClassException {
      Class<?> clazz = THREAD_CL_ACCESSOR.loadClass(fqcn);
      if (clazz == null) {
         clazz = CLASS_CL_ACCESSOR.loadClass(fqcn);
      }

      if (clazz == null) {
         clazz = SYSTEM_CL_ACCESSOR.loadClass(fqcn);
      }

      if (clazz == null) {
         String msg = "Unable to load class named [" + fqcn + "] from the thread context, current, or " + "system/application ClassLoaders.  All heuristics have been exhausted.  Class could not be found.";
         if (fqcn != null && fqcn.startsWith("io.jsonwebtoken.impl")) {
            msg = msg + "  Have you remembered to include the jjwt-impl.jar in your runtime classpath?";
         }

         throw new UnknownClassException(msg);
      } else {
         return clazz;
      }
   }

   public static InputStream getResourceAsStream(String name) {
      InputStream is = THREAD_CL_ACCESSOR.getResourceStream(name);
      if (is == null) {
         is = CLASS_CL_ACCESSOR.getResourceStream(name);
      }

      if (is == null) {
         is = SYSTEM_CL_ACCESSOR.getResourceStream(name);
      }

      return is;
   }

   private static URL getResource(String name) {
      URL url = THREAD_CL_ACCESSOR.getResource(name);
      if (url == null) {
         url = CLASS_CL_ACCESSOR.getResource(name);
      }

      return url == null ? SYSTEM_CL_ACCESSOR.getResource(name) : url;
   }

   public static boolean isAvailable(String fullyQualifiedClassName) {
      try {
         forName(fullyQualifiedClassName);
         return true;
      } catch (UnknownClassException var2) {
         return false;
      }
   }

   public static Object newInstance(String fqcn) {
      return newInstance(forName(fqcn));
   }

   public static Object newInstance(String fqcn, Class[] ctorArgTypes, Object... args) {
      Class<T> clazz = forName(fqcn);
      Constructor<T> ctor = getConstructor(clazz, ctorArgTypes);
      return instantiate(ctor, args);
   }

   public static Object newInstance(String fqcn, Object... args) {
      return newInstance(forName(fqcn), args);
   }

   public static Object newInstance(Class clazz) {
      if (clazz == null) {
         String msg = "Class method parameter cannot be null.";
         throw new IllegalArgumentException(msg);
      } else {
         try {
            return clazz.newInstance();
         } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate class [" + clazz.getName() + "]", e);
         }
      }
   }

   public static Object newInstance(Class clazz, Object... args) {
      Class<?>[] argTypes = new Class[args.length];

      for(int i = 0; i < args.length; ++i) {
         argTypes[i] = args[i].getClass();
      }

      Constructor<T> ctor = getConstructor(clazz, argTypes);
      return instantiate(ctor, args);
   }

   public static Constructor getConstructor(Class clazz, Class... argTypes) throws IllegalStateException {
      try {
         return clazz.getConstructor(argTypes);
      } catch (NoSuchMethodException e) {
         throw new IllegalStateException(e);
      }
   }

   public static Object instantiate(Constructor ctor, Object... args) {
      try {
         return ctor.newInstance(args);
      } catch (Exception e) {
         String msg = "Unable to instantiate instance with constructor [" + ctor + "]";
         throw new InstantiationException(msg, e);
      }
   }

   public static Object invokeStatic(String fqcn, String methodName, Class[] argTypes, Object... args) {
      try {
         Class<?> clazz = forName(fqcn);
         return invokeStatic(clazz, methodName, argTypes, args);
      } catch (Exception e) {
         String msg = "Unable to invoke class method " + fqcn + "#" + methodName + ".  Ensure the necessary " + "implementation is in the runtime classpath.";
         throw new IllegalStateException(msg, e);
      }
   }

   public static Object invokeStatic(Class clazz, String methodName, Class[] argTypes, Object... args) {
      try {
         Method method = clazz.getDeclaredMethod(methodName, argTypes);
         method.setAccessible(true);
         return method.invoke((Object)null, args);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
         Throwable cause = ((ReflectiveOperationException)e).getCause();
         if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
         } else {
            String msg = "Unable to invoke class method " + clazz.getName() + "#" + methodName + ". Ensure the necessary implementation is in the runtime classpath.";
            throw new IllegalStateException(msg, e);
         }
      }
   }

   public static Object getFieldValue(Object instance, String fieldName, Class fieldType) {
      if (instance == null) {
         return null;
      } else {
         try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object o = field.get(instance);
            return fieldType.cast(o);
         } catch (Throwable t) {
            String msg = "Unable to read field " + instance.getClass().getName() + "#" + fieldName + ": " + t.getMessage();
            throw new IllegalStateException(msg, t);
         }
      }
   }

   private abstract static class ExceptionIgnoringAccessor implements ClassLoaderAccessor {
      private ExceptionIgnoringAccessor() {
      }

      public Class loadClass(String fqcn) {
         Class<?> clazz = null;
         ClassLoader cl = this.getClassLoader();
         if (cl != null) {
            try {
               clazz = cl.loadClass(fqcn);
            } catch (ClassNotFoundException var5) {
            }
         }

         return clazz;
      }

      public URL getResource(String name) {
         URL url = null;
         ClassLoader cl = this.getClassLoader();
         if (cl != null) {
            url = cl.getResource(name);
         }

         return url;
      }

      public InputStream getResourceStream(String name) {
         InputStream is = null;
         ClassLoader cl = this.getClassLoader();
         if (cl != null) {
            is = cl.getResourceAsStream(name);
         }

         return is;
      }

      protected final ClassLoader getClassLoader() {
         try {
            return this.doGetClassLoader();
         } catch (Throwable var2) {
            return null;
         }
      }

      protected abstract ClassLoader doGetClassLoader() throws Throwable;
   }

   private interface ClassLoaderAccessor {
      Class loadClass(String var1);

      URL getResource(String var1);

      InputStream getResourceStream(String var1);
   }
}
