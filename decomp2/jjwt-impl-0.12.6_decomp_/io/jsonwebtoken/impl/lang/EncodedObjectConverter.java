package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class EncodedObjectConverter implements Converter {
   private final Class type;
   private final Converter converter;

   public EncodedObjectConverter(Class type, Converter converter) {
      this.type = (Class)Assert.notNull(type, "Value type cannot be null.");
      this.converter = (Converter)Assert.notNull(converter, "Value converter cannot be null.");
   }

   public Object applyTo(Object t) {
      Assert.notNull(t, "Value argument cannot be null.");
      return this.converter.applyTo(t);
   }

   public Object applyFrom(Object value) {
      Assert.notNull(value, "Value argument cannot be null.");
      if (this.type.isInstance(value)) {
         return this.type.cast(value);
      } else if (value instanceof CharSequence) {
         return this.converter.applyFrom((CharSequence)value);
      } else {
         String msg = "Values must be either String or " + this.type.getName() + " instances. Value type found: " + value.getClass().getName() + ".";
         throw new IllegalArgumentException(msg);
      }
   }
}
