package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class NullSafeConverter implements Converter {
   private final Converter converter;

   public NullSafeConverter(Converter converter) {
      this.converter = (Converter)Assert.notNull(converter, "Delegate converter cannot be null.");
   }

   public Object applyTo(Object a) {
      return a == null ? null : this.converter.applyTo(a);
   }

   public Object applyFrom(Object b) {
      return b == null ? null : this.converter.applyFrom(b);
   }
}
