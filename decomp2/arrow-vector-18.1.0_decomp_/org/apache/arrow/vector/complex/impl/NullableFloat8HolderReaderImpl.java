package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.Float8Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.types.Types;

public class NullableFloat8HolderReaderImpl extends AbstractFieldReader {
   private NullableFloat8Holder holder;

   public NullableFloat8HolderReaderImpl(NullableFloat8Holder holder) {
      this.holder = holder;
   }

   public int size() {
      throw new UnsupportedOperationException("You can't call size on a Holder value reader.");
   }

   public boolean next() {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public void setPosition(int index) {
      throw new UnsupportedOperationException("You can't call next on a single value reader.");
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.FLOAT8;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(Float8Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableFloat8Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Double readDouble() {
      if (!this.isSet()) {
         return null;
      } else {
         Double value = new Double(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readDouble();
   }
}
