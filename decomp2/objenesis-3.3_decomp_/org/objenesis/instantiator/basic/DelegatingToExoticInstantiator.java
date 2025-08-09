package org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

public abstract class DelegatingToExoticInstantiator implements ObjectInstantiator {
   private final ObjectInstantiator wrapped;

   protected DelegatingToExoticInstantiator(String className, Class type) {
      Class<ObjectInstantiator<T>> clazz = this.instantiatorClass(className);
      Constructor<ObjectInstantiator<T>> constructor = this.instantiatorConstructor(className, clazz);
      this.wrapped = this.instantiator(className, type, constructor);
   }

   private ObjectInstantiator instantiator(String className, Class type, Constructor constructor) {
      try {
         return (ObjectInstantiator)constructor.newInstance(type);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         throw new RuntimeException("Failed to call constructor of " + className, e);
      }
   }

   private Class instantiatorClass(String className) {
      try {
         Class<ObjectInstantiator<T>> clazz = Class.forName(className);
         return clazz;
      } catch (ClassNotFoundException e) {
         throw new ObjenesisException(this.getClass().getSimpleName() + " now requires objenesis-exotic to be in the classpath", e);
      }
   }

   private Constructor instantiatorConstructor(String className, Class clazz) {
      try {
         return clazz.getConstructor(Class.class);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException("Try to find constructor taking a Class<T> in parameter on " + className + " but can't find it", e);
      }
   }

   public Object newInstance() {
      return this.wrapped.newInstance();
   }
}
