package org.apache.arrow.vector.complex.impl;

import java.time.LocalDateTime;
import org.apache.arrow.vector.TimeStampSecVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeStampSecWriter;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeStampSecReaderImpl extends AbstractFieldReader {
   private final TimeStampSecVector vector;

   public TimeStampSecReaderImpl(TimeStampSecVector vector) {
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

   public void copyAsValue(TimeStampSecWriter writer) {
      TimeStampSecWriterImpl impl = (TimeStampSecWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeStampSecWriterImpl impl = (TimeStampSecWriterImpl)writer.timeStampSec(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeStampSecHolder h) {
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
