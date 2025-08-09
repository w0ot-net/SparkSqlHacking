package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.LargeVarCharWriter;
import org.apache.arrow.vector.holders.LargeVarCharHolder;
import org.apache.arrow.vector.holders.NullableLargeVarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.Text;

public class LargeVarCharHolderReaderImpl extends AbstractFieldReader {
   private LargeVarCharHolder holder;

   public LargeVarCharHolderReaderImpl(LargeVarCharHolder holder) {
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
      return Types.MinorType.LARGEVARCHAR;
   }

   public boolean isSet() {
      return true;
   }

   public void read(LargeVarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
   }

   public void read(NullableLargeVarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Text readText() {
      int length = (int)(this.holder.end - this.holder.start);
      byte[] value = new byte[length];
      this.holder.buffer.getBytes(this.holder.start, value, 0, length);
      Text text = new Text();
      text.set(value);
      return text;
   }

   public Object readObject() {
      return this.readText();
   }

   public void copyAsValue(LargeVarCharWriter writer) {
      writer.write(this.holder);
   }
}
