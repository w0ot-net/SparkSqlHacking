package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.Float2Writer;
import org.apache.arrow.vector.holders.Float2Holder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;
import org.apache.arrow.vector.types.Types;

public class Float2HolderReaderImpl extends AbstractFieldReader {
   private Float2Holder holder;

   public Float2HolderReaderImpl(Float2Holder holder) {
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
      return Types.MinorType.FLOAT2;
   }

   public boolean isSet() {
      return true;
   }

   public void read(Float2Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableFloat2Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Short readShort() {
      Short value = new Short(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readShort();
   }

   public void copyAsValue(Float2Writer writer) {
      writer.write(this.holder);
   }
}
