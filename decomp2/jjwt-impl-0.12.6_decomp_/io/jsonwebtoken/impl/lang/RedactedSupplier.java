package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Supplier;

public class RedactedSupplier implements Supplier {
   public static final String REDACTED_VALUE = "<redacted>";
   private final Object value;

   public RedactedSupplier(Object value) {
      this.value = Assert.notNull(value, "value cannot be null.");
   }

   public Object get() {
      return this.value;
   }

   public int hashCode() {
      return Objects.nullSafeHashCode(this.value);
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         if (obj instanceof RedactedSupplier) {
            obj = ((RedactedSupplier)obj).value;
         }

         return Objects.nullSafeEquals(this.value, obj);
      }
   }

   public String toString() {
      return "<redacted>";
   }
}
