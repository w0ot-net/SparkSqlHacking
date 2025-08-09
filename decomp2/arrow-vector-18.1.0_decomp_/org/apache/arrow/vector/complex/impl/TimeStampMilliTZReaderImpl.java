package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeStampMilliTZVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMilliTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMilliTZHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampMilliTZReaderImpl extends AbstractFieldReader {
   private final TimeStampMilliTZVector vector;

   public TimeStampMilliTZReaderImpl(TimeStampMilliTZVector vector) {
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

   public void copyAsValue(TimeStampMilliTZWriter writer) {
      TimeStampMilliTZWriterImpl impl = (TimeStampMilliTZWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeStampMilliTZWriterImpl impl = (TimeStampMilliTZWriterImpl)writer.timeStampMilliTZ(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeStampMilliTZHolder h) {
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
