package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class BigIntReaderImpl extends AbstractFieldReader {
   private final BigIntVector vector;

   public BigIntReaderImpl(BigIntVector vector) {
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

   public void copyAsValue(BigIntWriter writer) {
      BigIntWriterImpl impl = (BigIntWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      BigIntWriterImpl impl = (BigIntWriterImpl)writer.bigInt(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableBigIntHolder h) {
      this.vector.get(this.idx(), h);
   }

   public Long readLong() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
