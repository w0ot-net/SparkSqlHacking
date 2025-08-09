package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.types.Types;

public class Decimal256HolderReaderImpl extends AbstractFieldReader {
   private Decimal256Holder holder;

   public Decimal256HolderReaderImpl(Decimal256Holder holder) {
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
      return Types.MinorType.DECIMAL256;
   }

   public boolean isSet() {
      return true;
   }

   public void read(Decimal256Holder h) {
      h.start = this.holder.start;
      h.buffer = this.holder.buffer;
      h.scale = this.holder.scale;
      h.precision = this.holder.precision;
   }

   public void read(NullableDecimal256Holder h) {
      h.start = this.holder.start;
      h.buffer = this.holder.buffer;
      h.scale = this.holder.scale;
      h.precision = this.holder.precision;
      h.isSet = this.isSet() ? 1 : 0;
   }

   public BigDecimal readBigDecimal() {
      byte[] bytes = new byte[32];
      this.holder.buffer.getBytes(this.holder.start, bytes, 0, 32);
      BigDecimal value = new BigDecimal(new BigInteger(bytes), this.holder.scale);
      return value;
   }

   public Object readObject() {
      return this.readBigDecimal();
   }

   public void copyAsValue(Decimal256Writer writer) {
      writer.write(this.holder);
   }
}
