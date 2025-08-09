package io.vertx.core.cli.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ConstructorBasedConverter implements Converter {
   private final Constructor constructor;

   private ConstructorBasedConverter(Constructor constructor) {
      this.constructor = constructor;
   }

   public static ConstructorBasedConverter getIfEligible(Class clazz) {
      try {
         Constructor<T> constructor = clazz.getConstructor(String.class);
         if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
         }

         return new ConstructorBasedConverter(constructor);
      } catch (NoSuchMethodException var2) {
         return null;
      }
   }

   public Object fromString(String input) throws IllegalArgumentException {
      try {
         return this.constructor.newInstance(input);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         if (((ReflectiveOperationException)e).getCause() != null) {
            throw new IllegalArgumentException(((ReflectiveOperationException)e).getCause());
         } else {
            throw new IllegalArgumentException(e);
         }
      }
   }
}
