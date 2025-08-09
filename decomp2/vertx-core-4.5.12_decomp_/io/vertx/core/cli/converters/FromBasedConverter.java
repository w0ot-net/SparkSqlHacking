package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class FromBasedConverter implements Converter {
   public static final String FROM = "from";
   private final Method method;
   private final Class clazz;

   private FromBasedConverter(Class clazz, Method method) {
      this.clazz = clazz;
      this.method = method;
   }

   public static FromBasedConverter getIfEligible(Class clazz) {
      try {
         Method method = clazz.getMethod("from", String.class);
         if (Modifier.isStatic(method.getModifiers())) {
            if (!method.isAccessible()) {
               method.setAccessible(true);
            }

            return new FromBasedConverter(clazz, method);
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
