package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.Float8Writer;
import org.apache.arrow.vector.holders.Float8Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.types.Types;

public class Float8HolderReaderImpl extends AbstractFieldReader {
   private Float8Holder holder;

   public Float8HolderReaderImpl(Float8Holder holder) {
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
      return true;
   }

   public void read(Float8Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableFloat8Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Double readDouble() {
      Double value = new Double(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readDouble();
   }

   public void copyAsValue(Float8Writer writer) {
      writer.write(this.holder);
   }
}
