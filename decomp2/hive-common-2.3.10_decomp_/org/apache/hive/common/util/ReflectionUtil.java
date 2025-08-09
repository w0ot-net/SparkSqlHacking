package org.apache.hive.common.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;

public class ReflectionUtil {
   private static final Cache CONSTRUCTOR_CACHE;
   private static final Class[] EMPTY_ARRAY;
   private static final Class jobConfClass;
   private static final Class jobConfigurableClass;
   private static final Method configureMethod;

   public static Object newInstance(Class theClass, Configuration conf) {
      T result;
      try {
         Constructor<?> ctor = (Constructor)CONSTRUCTOR_CACHE.getIfPresent(theClass);
         if (ctor == null) {
            ctor = theClass.getDeclaredConstructor(EMPTY_ARRAY);
            ctor.setAccessible(true);
            CONSTRUCTOR_CACHE.put(theClass, ctor);
         }

         result = (T)ctor.newInstance();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

      setConf(result, conf);
      return result;
   }

   public static void setConf(Object theObject, Configuration conf) {
      if (conf != null) {
         if (theObject instanceof Configurable) {
            ((Configurable)theObject).setConf(conf);
         }

         setJobConf(theObject, conf);
      }

   }

   private static void setJobConf(Object theObject, Configuration conf) {
      if (configureMethod != null) {
         try {
            if (jobConfClass.isAssignableFrom(conf.getClass()) && jobConfigurableClass.isAssignableFrom(theObject.getClass())) {
               configureMethod.invoke(theObject, conf);
            }

         } catch (Exception e) {
            throw new RuntimeException("Error in configuring object", e);
         }
      }
   }

   static {
      CONSTRUCTOR_CACHE = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.MINUTES).concurrencyLevel(64).weakKeys().weakValues().build();
      EMPTY_ARRAY = new Class[0];

      Class<?> jobConfClassLocal;
      Class<?> jobConfigurableClassLocal;
      Method configureMethodLocal;
      try {
         jobConfClassLocal = Class.forName("org.apache.hadoop.mapred.JobConf");
         jobConfigurableClassLocal = Class.forName("org.apache.hadoop.mapred.JobConfigurable");
         configureMethodLocal = jobConfigurableClassLocal.getMethod("configure", jobConfClassLocal);
      } catch (Throwable var4) {
         jobConfigurableClassLocal = null;
         jobConfClassLocal = null;
         configureMethodLocal = null;
      }

      jobConfClass = jobConfClassLocal;
      jobConfigurableClass = jobConfigurableClassLocal;
      configureMethod = configureMethodLocal;
   }
}
