package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class FromStringBasedConverter implements Converter {
   public static final String FROM_STRING = "fromString";
   private final Method method;
   private final Class clazz;

   private FromStringBasedConverter(Class clazz, Method method) {
      this.clazz = clazz;
      this.method = method;
   }

   public static FromStringBasedConverter getIfEligible(Class clazz) {
      try {
         Method method = clazz.getMethod("fromString", String.class);
         if (Modifier.isStatic(method.getModifiers())) {
            if (!method.isAccessible()) {
               method.setAccessible(true);
            }

            return new FromStringBasedConverter(clazz, method);
         } else {
            return null;
         }
      } catch (NoSuchMethodException var2) {
         return null;
      }
   }

   public Object fromString(String input) throws IllegalArgumentException {
      try {
         return this.clazz.cast(this.method.invoke((Object)null, input));
      } catch (InvocationTargetException | IllegalAccessException e) {
         if (((ReflectiveOperationException)e).getCause() != null) {
            throw new IllegalArgumentException(((ReflectiveOperationException)e).getCause());
         } else {
            throw new IllegalArgumentException(e);
         }
      }
   }
}
