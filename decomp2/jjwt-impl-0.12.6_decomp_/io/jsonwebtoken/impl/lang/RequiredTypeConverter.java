package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class RequiredTypeConverter implements Converter {
   private final Class type;

   public RequiredTypeConverter(Class type) {
      this.type = (Class)Assert.notNull(type, "type argument cannot be null.");
   }

   public Object applyTo(Object t) {
      return t;
   }

   public Object applyFrom(Object o) {
      if (o == null) {
         return null;
      } else {
         Class<?> clazz = o.getClass();
         if (!this.type.isAssignableFrom(clazz)) {
            String msg = "Unsupported value type. Expected: " + this.type.getName() + ", found: " + clazz.getName();
            throw new IllegalArgumentException(msg);
         } else {
            return this.type.cast(o);
         }
      }
   }
}
