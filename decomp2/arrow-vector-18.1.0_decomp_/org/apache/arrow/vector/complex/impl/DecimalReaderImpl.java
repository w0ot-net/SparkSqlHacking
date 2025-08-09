package org.apache.arrow.vector.complex.impl;

import java.math.BigDecimal;
import org.apache.arrow.vector.DecimalVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class DecimalReaderImpl extends AbstractFieldReader {
   private final DecimalVector vector;

   public DecimalReaderImpl(DecimalVector vector) {
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

   public void copyAsValue(DecimalWriter writer) {
      DecimalWriterImpl impl = (DecimalWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      DecimalWriterImpl impl = (DecimalWriterImpl)writer.decimal(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableDecimalHolder h) {
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
