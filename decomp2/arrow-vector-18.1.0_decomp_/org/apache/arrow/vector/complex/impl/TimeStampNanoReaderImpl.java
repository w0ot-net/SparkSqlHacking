package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.TimeStampNanoVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeStampNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampNanoReaderImpl extends AbstractFieldReader {
   private final TimeStampNanoVector vector;

   public TimeStampNanoReaderImpl(TimeStampNanoVector vector) {
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

   public void copyAsValue(TimeStampNanoWriter writer) {
      TimeStampNanoWriterImpl impl = (TimeStampNanoWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeStampNanoWriterImpl impl = (TimeStampNanoWriterImpl)writer.timeStampNano(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeStampNanoHolder h) {
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
