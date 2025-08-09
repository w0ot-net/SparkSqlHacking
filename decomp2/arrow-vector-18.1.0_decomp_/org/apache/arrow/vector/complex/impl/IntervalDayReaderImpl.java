package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import org.apache.arrow.vector.IntervalDayVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.IntervalDayWriter;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class IntervalDayReaderImpl extends AbstractFieldReader {
   private final IntervalDayVector vector;

   public IntervalDayReaderImpl(IntervalDayVector vector) {
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

   public void copyAsValue(IntervalDayWriter writer) {
      IntervalDayWriterImpl impl = (IntervalDayWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      IntervalDayWriterImpl impl = (IntervalDayWriterImpl)writer.intervalDay(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableIntervalDayHolder h) {
      this.vector.get(this.idx(), h);
   }

   public Duration readDuration() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
