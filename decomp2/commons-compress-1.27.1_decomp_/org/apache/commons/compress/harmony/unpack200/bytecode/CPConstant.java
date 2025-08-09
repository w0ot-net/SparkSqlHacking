package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.Objects;

public abstract class CPConstant extends ConstantPoolEntry {
   private final Object value;

   public CPConstant(byte tag, Object value, int globalIndex) {
      super(tag, globalIndex);
      this.value = Objects.requireNonNull(value, "value");
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         CPConstant other = (CPConstant)obj;
         return Objects.equals(this.value, other.value);
      }
   }

   protected Object getValue() {
      return this.value;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.value});
   }
}
