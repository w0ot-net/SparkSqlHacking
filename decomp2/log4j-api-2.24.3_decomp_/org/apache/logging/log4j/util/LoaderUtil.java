package org.apache.logging.log4j.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

@InternalApi
public final class LoaderUtil {
   private static final Logger LOGGER = StatusLogger.getLogger();
   public static final String IGNORE_TCCL_PROPERTY = "log4j.ignoreTCL";
   private static Boolean ignoreTCCL;
   static final RuntimePermission GET_CLASS_LOADER = new RuntimePermission("getClassLoader");
   static final LazyBoolean GET_CLASS_LOADER_DISABLED = new LazyBoolean(() -> {
      if (System.getSecurityManager() == null) {
         return false;
      } else {
         try {
            AccessController.checkPermission(GET_CLASS_LOADER);
            return false;
         } catch (SecurityException var3) {
            try {
               AccessController.doPrivileged(() -> {
                  AccessController.checkPermission(GET_CLASS_LOADER);
                  return null;
               });
               return false;
            } catch (SecurityException var2) {
               return true;
            }
         }
      }
   });
   private static final PrivilegedAction TCCL_GETTER = new ThreadContextClassLoaderGetter();

   private LoaderUtil() {
   }

   public static ClassLoader getClassLoader() {
      return getClassLoader(LoaderUtil.class, (Class)null);
   }

   public static ClassLoader getClassLoader(final Class class1, final Class class2) {
      PrivilegedAction<ClassLoader> action = () -> {
         ClassLoader loader1 = class1 == null ? null : class1.getClassLoader();
         ClassLoader loader2 = class2 == null ? null : class2.getClassLoader();
         ClassLoader referenceLoader = GET_CLASS_LOADER_DISABLED.getAsBoolean() ? getThisClassLoader() : Thread.currentThread().getContextClassLoader();
         if (isChild(referenceLoader, loader1)) {
            return isChild(referenceLoader, loader2) ? referenceLoader : loader2;
         } else {
            return isChild(loader1, loader2) ? loader1 : loader2;
         }
      };
      return (ClassLoader)runPrivileged(action);
   }

   private static boolean isChild(final ClassLoader loader1, final ClassLoader loader2) {
      if (loader1 != null && loader2 != null) {
         ClassLoader parent;
         for(parent = loader1.getParent(); parent != null && parent != loader2; parent = parent.getParent()) {
         }

         return parent != null;
      } else {
         return loader1 != null;
      }
   }

   public static ClassLoader getThreadContextClassLoader() {
      try {
         return GET_CLASS_LOADER_DISABLED.getAsBoolean() ? getThisClassLoader() : (ClassLoader)runPrivileged(TCCL_GETTER);
      } catch (SecurityException var1) {
         return null;
      }
   }

   private static ClassLoader getThisClassLoader() {
      return LoaderUtil.class.getClassLoader();
   }

   private static Object runPrivileged(final PrivilegedAction action) {
      return System.getSecurityManager() != null ? AccessController.doPrivileged(action) : action.run();
   }

   public static boolean isClassAvailable(final String className) {
      try {
         loadClass(className);
         return true;
      } catch (LinkageError | ClassNotFoundException var2) {
         return false;
      } catch (Throwable error) {
         LOGGER.error((String)"Unknown error while checking existence of class `{}`", (Object)className, (Object)error);
         return false;
      }
   }

   public static Class loadClass(final String className) throws ClassNotFoundException {
      ClassLoader classLoader = isIgnoreTccl() ? getThisClassLoader() : getThreadContextClassLoader();
      if (classLoader == null) {
         classLoader = getThisClassLoader();
      }

      return Class.forName(className, true, classLoader);
   }

   public static Class loadClassUnchecked(final String className) {
      try {
         return loadClass(className);
      } catch (ClassNotFoundException e) {
         NoClassDefFoundError error = new NoClassDefFoundError(e.getMessage());
         error.initCause(e);
         throw error;
      }
   }

   public static Object newInstanceOf(final Class clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      Constructor<T> constructor = clazz.getDeclaredConstructor();
      return constructor.newInstance();
   }

   public static Object newInstanceOfUnchecked(final Class clazz) {
      try {
         return newInstanceOf(clazz);
      } catch (NoSuchMethodException e) {
         NoSuchMethodError error = new NoSuchMethodError(e.getMessage());
         error.initCause(e);
         throw error;
      } catch (InvocationTargetException e) {
         Throwable cause = e.getCause();
         throw new InternalException(cause);
      } catch (InstantiationException e) {
         InstantiationError error = new InstantiationError(e.getMessage());
         error.initCause(e);
         throw error;
      } catch (IllegalAccessException e) {
         IllegalAccessError error = new IllegalAccessError(e.getMessage());
         error.initCause(e);
         throw error;
      }
   }

   public static Object newInstanceOf(final String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
      Class<T> clazz = (Class)Cast.cast(loadClass(className));
      return newInstanceOf(clazz);
   }

   public static Object newCheckedInstanceOfProperty(final String propertyName, final Class clazz) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
      return newCheckedInstanceOfProperty(propertyName, clazz, () -> null);
   }

   public static Object newCheckedInstanceOfProperty(final String propertyName, final Class clazz, final java.util.function.Supplier defaultSupplier) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
      String className = PropertiesUtil.getProperties().getStringProperty(propertyName);
      return className == null ? defaultSupplier.get() : newCheckedInstanceOf(className, clazz);
   }

   public static Object newInstanceOfUnchecked(final String className) {
      Class<T> clazz = (Class)Cast.cast(loadClassUnchecked(className));
      return newInstanceOfUnchecked(clazz);
   }

   public static Object newCheckedInstanceOf(final String className, final Class clazz) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
      return newInstanceOf(loadClass(className).asSubclass(clazz));
   }

   public static Object newInstanceOfUnchecked(final String className, final Class supertype) {
      Class<? extends T> clazz = loadClassUnchecked(className).asSubclass(supertype);
      return newInstanceOfUnchecked(clazz);
   }

   private static boolean isIgnoreTccl() {
      if (ignoreTCCL == null) {
         String ignoreTccl = PropertiesUtil.getProperties().getStringProperty("log4j.ignoreTCL", (String)null);
         ignoreTCCL = ignoreTccl != null && !"false".equalsIgnoreCase(ignoreTccl.trim());
      }

      return ignoreTCCL;
   }

   public static Collection findResources(final String resource) {
      return findResources(resource, true);
   }

   static Collection findResources(final String resource, final boolean useTccl) {
      Collection<UrlResource> urlResources = findUrlResources(resource, useTccl);
      Collection<URL> resources = new LinkedHashSet(urlResources.size());

      for(UrlResource urlResource : urlResources) {
         resources.add(urlResource.getUrl());
      }

      return resources;
   }

   static Collection findUrlResources(final String resource, final boolean useTccl) {
      ClassLoader[] candidates = new ClassLoader[]{useTccl ? getThreadContextClassLoader() : null, LoaderUtil.class.getClassLoader(), GET_CLASS_LOADER_DISABLED.getAsBoolean() ? null : ClassLoader.getSystemClassLoader()};
      Collection<UrlResource> resources = new LinkedHashSet();

      for(ClassLoader cl : candidates) {
         if (cl != null) {
            try {
               Enumeration<URL> resourceEnum = cl.getResources(resource);

               while(resourceEnum.hasMoreElements()) {
                  resources.add(new UrlResource(cl, (URL)resourceEnum.nextElement()));
               }
            } catch (IOException error) {
               LOGGER.error((String)"failed to collect resources of name `{}`", (Object)resource, (Object)error);
            }
         }
      }

      return resources;
   }

   private static class ThreadContextClassLoaderGetter implements PrivilegedAction {
      private ThreadContextClassLoaderGetter() {
      }

      public ClassLoader run() {
         ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
         if (contextClassLoader != null) {
            return contextClassLoader;
         } else {
            ClassLoader thisClassLoader = LoaderUtil.getThisClassLoader();
            return thisClassLoader == null && !LoaderUtil.GET_CLASS_LOADER_DISABLED.getAsBoolean() ? ClassLoader.getSystemClassLoader() : thisClassLoader;
         }
      }
   }

   static class UrlResource {
      private final ClassLoader classLoader;
      private final URL url;

      UrlResource(final ClassLoader classLoader, final URL url) {
         this.classLoader = classLoader;
         this.url = url;
      }

      public ClassLoader getClassLoader() {
         return this.classLoader;
      }

      public URL getUrl() {
         return this.url;
      }

      public boolean equals(final Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof UrlResource)) {
            return false;
         } else {
            UrlResource that = (UrlResource)o;
            return Objects.equals(this.classLoader, that.classLoader) && Objects.equals(this.url, that.url);
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.classLoader) + Objects.hashCode(this.url);
      }
   }
}
