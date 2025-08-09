package org.objenesis.instantiator.sun;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;

class SunReflectionFactoryHelper {
   public static Constructor newConstructorForSerialization(Class type, Constructor constructor) {
      Class<?> reflectionFactoryClass = getReflectionFactoryClass();
      Object reflectionFactory = createReflectionFactory(reflectionFactoryClass);
      Method newConstructorForSerializationMethod = getNewConstructorForSerializationMethod(reflectionFactoryClass);

      try {
         return (Constructor)newConstructorForSerializationMethod.invoke(reflectionFactory, type, constructor);
      } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
         throw new ObjenesisException(e);
      }
   }

   private static Class getReflectionFactoryClass() {
      try {
         return Class.forName("sun.reflect.ReflectionFactory");
      } catch (ClassNotFoundException e) {
         throw new ObjenesisException(e);
      }
   }

   private static Object createReflectionFactory(Class reflectionFactoryClass) {
      try {
         Method method = reflectionFactoryClass.getDeclaredMethod("getReflectionFactory");
         return method.invoke((Object)null);
      } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException | NoSuchMethodException e) {
         throw new ObjenesisException(e);
      }
   }

   private static Method getNewConstructorForSerializationMethod(Class reflectionFactoryClass) {
      try {
         return reflectionFactoryClass.getDeclaredMethod("newConstructorForSerialization", Class.class, Constructor.class);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException(e);
      }
   }
}
