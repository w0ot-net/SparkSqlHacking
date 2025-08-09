package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.TimeStampMicroVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeStampMicroWriter;
import org.apache.arrow.vector.holders.NullableTimeStampMicroHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampMicroReaderImpl extends AbstractFieldReader {
   private final TimeStampMicroVector vector;

   public TimeStampMicroReaderImpl(TimeStampMicroVector vector) {
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

   public void copyAsValue(TimeStampMicroWriter writer) {
      TimeStampMicroWriterImpl impl = (TimeStampMicroWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeStampMicroWriterImpl impl = (TimeStampMicroWriterImpl)writer.timeStampMicro(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeStampMicroHolder h) {
      this.vector.get(this.idx(), h);
   }

   public LocalDateTime readLocalDateTime() {
      return this.vector.getObject(this.idx());
   }

   public Long readLong() {
      return this.vector.get(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
