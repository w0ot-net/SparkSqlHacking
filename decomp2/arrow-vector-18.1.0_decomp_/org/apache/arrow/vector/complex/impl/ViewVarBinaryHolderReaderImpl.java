package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.ViewVarBinaryWriter;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.ViewVarBinaryHolder;
import org.apache.arrow.vector.types.Types;

public class ViewVarBinaryHolderReaderImpl extends AbstractFieldReader {
   private ViewVarBinaryHolder holder;

   public ViewVarBinaryHolderReaderImpl(ViewVarBinaryHolder holder) {
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
      return Types.MinorType.VIEWVARBINARY;
   }

   public boolean isSet() {
      return true;
   }

   public void read(ViewVarBinaryHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
   }

   public void read(NullableViewVarBinaryHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public byte[] readByteArray() {
      int length = this.holder.end - this.holder.start;
      byte[] value = new byte[length];
      this.holder.buffer.getBytes((long)this.holder.start, value, 0, length);
      return value;
   }

   public Object readObject() {
      return this.readByteArray();
   }

   public void copyAsValue(ViewVarBinaryWriter writer) {
      writer.write(this.holder);
   }
}
