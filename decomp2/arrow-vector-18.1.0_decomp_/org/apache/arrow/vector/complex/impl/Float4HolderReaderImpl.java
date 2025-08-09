package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.Float4Writer;
import org.apache.arrow.vector.holders.Float4Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.types.Types;

public class Float4HolderReaderImpl extends AbstractFieldReader {
   private Float4Holder holder;

   public Float4HolderReaderImpl(Float4Holder holder) {
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
      return true;
   }

   public void read(Float4Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableFloat4Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Float readFloat() {
      Float value = new Float(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readFloat();
   }

   public void copyAsValue(Float4Writer writer) {
      writer.write(this.holder);
   }
}
