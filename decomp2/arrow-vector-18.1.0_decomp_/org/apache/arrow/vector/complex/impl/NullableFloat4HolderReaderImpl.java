package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.Float4Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.types.Types;

public class NullableFloat4HolderReaderImpl extends AbstractFieldReader {
   private NullableFloat4Holder holder;

   public NullableFloat4HolderReaderImpl(NullableFloat4Holder holder) {
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
      return Types.MinorType.FLOAT4;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(Float4Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableFloat4Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Float readFloat() {
      if (!this.isSet()) {
         return null;
      } else {
         Float value = new Float(this.holder.value);
         return value;
      }
   }

   public Object readObject() {
      return this.readFloat();
   }
}
