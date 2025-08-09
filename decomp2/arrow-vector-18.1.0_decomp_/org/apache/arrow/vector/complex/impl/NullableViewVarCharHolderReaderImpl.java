package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.ViewVarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.util.Text;

public class NullableViewVarCharHolderReaderImpl extends AbstractFieldReader {
   private NullableViewVarCharHolder holder;

   public NullableViewVarCharHolderReaderImpl(NullableViewVarCharHolder holder) {
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
      return Types.MinorType.VIEWVARCHAR;
   }

   public boolean isSet() {
      return this.holder.isSet == 1;
   }

   public void read(ViewVarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
   }

   public void read(NullableViewVarCharHolder h) {
      h.start = this.holder.start;
      h.end = this.holder.end;
      h.buffer = this.holder.buffer;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public Text readText() {
      if (!this.isSet()) {
         return null;
      } else {
         int length = this.holder.end - this.holder.start;
         byte[] value = new byte[length];
         this.holder.buffer.getBytes((long)this.holder.start, value, 0, length);
         Text text = new Text();
         text.set(value);
         return text;
      }
   }

   public Object readObject() {
      return this.readText();
   }
}
