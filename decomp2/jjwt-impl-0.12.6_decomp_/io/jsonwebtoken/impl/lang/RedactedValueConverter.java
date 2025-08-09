package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Supplier;

public class RedactedValueConverter implements Converter {
   private final Converter delegate;

   public RedactedValueConverter(Converter delegate) {
      this.delegate = (Converter)Assert.notNull(delegate, "Delegate cannot be null.");
   }

   public Object applyTo(Object t) {
      Object value = this.delegate.applyTo(t);
      if (value != null && !(value instanceof RedactedSupplier)) {
         value = new RedactedSupplier(value);
      }

      return value;
   }

   public Object applyFrom(Object o) {
      if (o instanceof RedactedSupplier) {
         o = ((Supplier)o).get();
      }

      return this.delegate.applyFrom(o);
   }
}
