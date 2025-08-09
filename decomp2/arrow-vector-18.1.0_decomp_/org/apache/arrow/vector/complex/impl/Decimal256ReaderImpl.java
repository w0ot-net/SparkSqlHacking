package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import org.apache.arrow.vector.Decimal256Vector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class Decimal256ReaderImpl extends AbstractFieldReader {
   private final Decimal256Vector vector;

   public Decimal256ReaderImpl(Decimal256Vector vector) {
      this.vector = vector;
   }

   public Types.MinorType getMinorType() {
      return this.vector.getMinorType();
   }

   public Field getField() {
      return this.vector.getField();
   }

   public boolean isSet() {
      return !this.vector.isNull(this.idx());
   }

   public void copyAsValue(Decimal256Writer writer) {
      Decimal256WriterImpl impl = (Decimal256WriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      Decimal256WriterImpl impl = (Decimal256WriterImpl)writer.decimal256(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableDecimal256Holder h) {
      this.vector.get(this.idx(), h);
   }

   public BigDecimal readBigDecimal() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
