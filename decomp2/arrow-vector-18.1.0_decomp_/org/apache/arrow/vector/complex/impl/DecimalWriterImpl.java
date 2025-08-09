package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.DecimalUtility;

public class DecimalWriterImpl extends AbstractFieldWriter {
   final DecimalVector vector;

   public DecimalWriterImpl(DecimalVector vector) {
      this.vector = vector;
   }

   public Field getField() {
      return this.vector.getField();
   }

   public int getValueCapacity() {
      return this.vector.getValueCapacity();
   }

   public void allocate() {
      this.vector.allocateNew();
   }

   public void close() {
      this.vector.close();
   }

   public void clear() {
      this.vector.clear();
   }

   protected int idx() {
      return super.idx();
   }

   public void write(DecimalHolder h) {
      DecimalUtility.checkPrecisionAndScale(h.precision, h.scale, this.vector.getPrecision(), this.vector.getScale());
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableDecimalHolder h) {
      if (h.isSet == 1) {
         DecimalUtility.checkPrecisionAndScale(h.precision, h.scale, this.vector.getPrecision(), this.vector.getScale());
      }

      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeDecimal(long start, ArrowBuf buffer) {
      this.vector.setSafe(this.idx(), 1, start, buffer);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeDecimal(long start, ArrowBuf buffer, ArrowType arrowType) {
      DecimalUtility.checkPrecisionAndScale(((ArrowType.Decimal)arrowType).getPrecision(), ((ArrowType.Decimal)arrowType).getScale(), this.vector.getPrecision(), this.vector.getScale());
      this.vector.setSafe(this.idx(), 1, start, buffer);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeDecimal(BigDecimal value) {
      this.vector.setSafe(this.idx(), value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeBigEndianBytesToDecimal(byte[] value, ArrowType arrowType) {
      DecimalUtility.checkPrecisionAndScale(((ArrowType.Decimal)arrowType).getPrecision(), ((ArrowType.Decimal)arrowType).getScale(), this.vector.getPrecision(), this.vector.getScale());
      this.vector.setBigEndianSafe(this.idx(), value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeBigEndianBytesToDecimal(byte[] value) {
      this.vector.setBigEndianSafe(this.idx(), value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }
}
