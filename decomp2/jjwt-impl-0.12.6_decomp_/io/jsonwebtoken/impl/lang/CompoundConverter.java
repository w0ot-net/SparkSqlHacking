package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class CompoundConverter implements Converter {
   private final Converter first;
   private final Converter second;

   public CompoundConverter(Converter first, Converter second) {
      this.first = (Converter)Assert.notNull(first, "First converter cannot be null.");
      this.second = (Converter)Assert.notNull(second, "Second converter cannot be null.");
   }

   public Object applyTo(Object a) {
      B b = (B)this.first.applyTo(a);
      return this.second.applyTo(b);
   }

   public Object applyFrom(Object c) {
      B b = (B)this.second.applyFrom(c);
      return this.first.applyFrom(b);
   }
}
