package io.vertx.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/** @deprecated */
@Deprecated
public class ServiceHelper {
   public static Object loadFactory(Class clazz) {
      T factory = (T)loadFactoryOrNull(clazz);
      if (factory == null) {
         throw new IllegalStateException("Cannot find META-INF/services/" + clazz.getName() + " on classpath");
      } else {
         return factory;
      }
   }

   public static Object loadFactoryOrNull(Class clazz) {
      Collection<T> collection = loadFactories(clazz);
      return !collection.isEmpty() ? collection.iterator().next() : null;
   }

   public static Collection loadFactories(Class clazz) {
      return loadFactories(clazz, (ClassLoader)null);
   }

   public static Collection loadFactories(Class clazz, ClassLoader classLoader) {
      List<T> list = new ArrayList();
      ServiceLoader<T> factories;
      if (classLoader != null) {
         factories = ServiceLoader.load(clazz, classLoader);
      } else {
         factories = ServiceLoader.load(clazz);
      }

      if (factories.iterator().hasNext()) {
         factories.iterator().forEachRemaining(list::add);
         return list;
      } else {
         factories = ServiceLoader.load(clazz, ServiceHelper.class.getClassLoader());
         if (factories.iterator().hasNext()) {
            factories.iterator().forEachRemaining(list::add);
            return list;
         } else {
            return Collections.emptyList();
         }
      }
   }
}
