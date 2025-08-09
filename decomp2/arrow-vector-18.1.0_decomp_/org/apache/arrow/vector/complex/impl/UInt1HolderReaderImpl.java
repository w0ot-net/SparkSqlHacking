package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.UInt1Writer;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.UInt1Holder;
import org.apache.arrow.vector.types.Types;

public class UInt1HolderReaderImpl extends AbstractFieldReader {
   private UInt1Holder holder;

   public UInt1HolderReaderImpl(UInt1Holder holder) {
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
      return Types.MinorType.UINT1;
   }

   public boolean isSet() {
      return true;
   }

   public void read(UInt1Holder h) {
      h.value = this.holder.value;
   }

   public void read(NullableUInt1Holder h) {
      h.value = this.holder.value;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Byte readByte() {
      Byte value = new Byte(this.holder.value);
      return value;
   }

   public Object readObject() {
      return this.readByte();
   }

   public void copyAsValue(UInt1Writer writer) {
      writer.write(this.holder);
   }
}
