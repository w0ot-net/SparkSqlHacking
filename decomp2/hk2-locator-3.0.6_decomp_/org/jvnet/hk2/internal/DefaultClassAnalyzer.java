package org.jvnet.hk2.internal;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import org.glassfish.hk2.api.ClassAnalyzer;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.Visibility;

@Singleton
@Named("default")
@Visibility(DescriptorVisibility.LOCAL)
public class DefaultClassAnalyzer implements ClassAnalyzer {
   private final ServiceLocatorImpl locator;

   public DefaultClassAnalyzer(ServiceLocatorImpl locator) {
      this.locator = locator;
   }

   public Constructor getConstructor(Class clazz) throws MultiException, NoSuchMethodException {
      Collector collector = new Collector();
      Constructor<T> retVal = Utilities.findProducerConstructor(clazz, this.locator, collector);

      try {
         collector.throwIfErrors();
         return retVal;
      } catch (MultiException me) {
         for(Throwable th : me.getErrors()) {
            if (th instanceof NoSuchMethodException) {
               throw (NoSuchMethodException)th;
            }
         }

         throw me;
      }
   }

   public Set getInitializerMethods(Class clazz) throws MultiException {
      Collector collector = new Collector();
      Set<Method> retVal = Utilities.findInitializerMethods(clazz, this.locator, collector);
      collector.throwIfErrors();
      return retVal;
   }

   public Set getFields(Class clazz) throws MultiException {
      Collector collector = new Collector();
      Set<Field> retVal = Utilities.findInitializerFields(clazz, this.locator, collector);
      collector.throwIfErrors();
      return retVal;
   }

   public Method getPostConstructMethod(Class clazz) throws MultiException {
      Collector collector = new Collector();
      Method retVal = Utilities.findPostConstruct(clazz, this.locator, collector);
      collector.throwIfErrors();
      return retVal;
   }

   public Method getPreDestroyMethod(Class clazz) throws MultiException {
      Collector collector = new Collector();
      Method retVal = Utilities.findPreDestroy(clazz, this.locator, collector);
      collector.throwIfErrors();
      return retVal;
   }
}
