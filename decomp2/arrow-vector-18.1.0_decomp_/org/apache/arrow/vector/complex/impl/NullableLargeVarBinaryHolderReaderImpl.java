package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.LargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;
import org.apache.arrow.vector.types.Types;

public class NullableLargeVarBinaryHolderReaderImpl extends AbstractFieldReader {
   private NullableLargeVarBinaryHolder holder;

   public NullableLargeVarBinaryHolderReaderImpl(NullableLargeVarBinaryHolder holder) {
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
      return Types.MinorType.LARGEVARBINARY;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(LargeVarBinaryHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
   }

   public void read(NullableLargeVarBinaryHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public byte[] readByteArray() {
      if (!this.isSet()) {
         return null;
      } else {
         int length = (int)(this.holder.end - this.holder.start);
         byte[] value = new byte[length];
         this.holder.buffer.getBytes(this.holder.start, value, 0, length);
         return value;
      }
   }

   public Object readObject() {
      return this.readByteArray();
   }
}
