package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.VarCharWriter;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.Text;

public class VarCharHolderReaderImpl extends AbstractFieldReader {
   private VarCharHolder holder;

   public VarCharHolderReaderImpl(VarCharHolder holder) {
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
      return Types.MinorType.VARCHAR;
   }

   public boolean isSet() {
      return true;
   }

   public void read(VarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
   }

   public void read(NullableVarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Text readText() {
      int length = this.holder.end - this.holder.start;
      byte[] value = new byte[length];
      this.holder.buffer.getBytes((long)this.holder.start, value, 0, length);
      Text text = new Text();
      text.set(value);
      return text;
   }

   public Object readObject() {
      return this.readText();
   }

   public void copyAsValue(VarCharWriter writer) {
      writer.write(this.holder);
   }
}
