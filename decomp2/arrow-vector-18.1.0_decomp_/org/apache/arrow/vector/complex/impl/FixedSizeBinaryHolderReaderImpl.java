package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.FixedSizeBinaryWriter;
import org.apache.arrow.vector.holders.FixedSizeBinaryHolder;
import org.apache.arrow.vector.holders.NullableFixedSizeBinaryHolder;
import org.apache.arrow.vector.types.Types;

public class FixedSizeBinaryHolderReaderImpl extends AbstractFieldReader {
   private FixedSizeBinaryHolder holder;

   public FixedSizeBinaryHolderReaderImpl(FixedSizeBinaryHolder holder) {
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
      return Types.MinorType.FIXEDSIZEBINARY;
   }

   public boolean isSet() {
      return true;
   }

   public void read(FixedSizeBinaryHolder h) {
      h.buffer = this.holder.buffer;
      h.byteWidth = this.holder.byteWidth;
   }

   public void read(NullableFixedSizeBinaryHolder h) {
      h.buffer = this.holder.buffer;
      h.byteWidth = this.holder.byteWidth;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public byte[] readByteArray() {
      byte[] value = new byte[this.holder.byteWidth];
      this.holder.buffer.getBytes(0L, value, 0, this.holder.byteWidth);
      return value;
   }

   public Object readObject() {
      return this.readByteArray();
   }

   public void copyAsValue(FixedSizeBinaryWriter writer) {
      writer.write(this.holder);
   }
}
