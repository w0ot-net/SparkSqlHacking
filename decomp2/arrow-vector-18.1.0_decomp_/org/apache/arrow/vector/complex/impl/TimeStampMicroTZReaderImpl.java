package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeStampMicroTZVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroTZWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMicroTZHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampMicroTZReaderImpl extends AbstractFieldReader {
   private final TimeStampMicroTZVector vector;

   public TimeStampMicroTZReaderImpl(TimeStampMicroTZVector vector) {
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

   public void copyAsValue(TimeStampMicroTZWriter writer) {
      TimeStampMicroTZWriterImpl impl = (TimeStampMicroTZWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeStampMicroTZWriterImpl impl = (TimeStampMicroTZWriterImpl)writer.timeStampMicroTZ(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeStampMicroTZHolder h) {
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
