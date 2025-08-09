package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Assert;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Services {
   private static final ConcurrentMap SERVICES = new ConcurrentHashMap();
   private static final List CLASS_LOADER_ACCESSORS = Arrays.asList(new ClassLoaderAccessor[]{new ClassLoaderAccessor() {
      public ClassLoader getClassLoader() {
         return Thread.currentThread().getContextClassLoader();
      }
   }, new ClassLoaderAccessor() {
      public ClassLoader getClassLoader() {
         return Services.class.getClassLoader();
      }
   }, new ClassLoaderAccessor() {
      public ClassLoader getClassLoader() {
         return ClassLoader.getSystemClassLoader();
      }
   }});

   private Services() {
   }

   public static Object get(Class spi) {
      T instance = (T)findCached(spi);
      if (instance == null) {
         instance = (T)loadFirst(spi);
         SERVICES.putIfAbsent(spi, instance);
      }

      return instance;
   }

   private static Object findCached(Class spi) {
      Assert.notNull(spi, "Service interface cannot be null.");
      Object obj = SERVICES.get(spi);
      return obj != null ? Assert.isInstanceOf(spi, obj, "Unexpected cached service implementation type.") : null;
   }

   private static Object loadFirst(Class spi) {
      for(ClassLoaderAccessor accessor : CLASS_LOADER_ACCESSORS) {
         ServiceLoader<T> loader = ServiceLoader.load(spi, accessor.getClassLoader());
         Assert.stateNotNull(loader, "JDK ServiceLoader#load should never return null.");
         Iterator<T> i = loader.iterator();
         Assert.stateNotNull(i, "JDK ServiceLoader#iterator() should never return null.");
         if (i.hasNext()) {
            return i.next();
         }
      }

      throw new UnavailableImplementationException(spi);
   }

   public static void reload() {
      SERVICES.clear();
   }

   private interface ClassLoaderAccessor {
      ClassLoader getClassLoader();
   }
}
