package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.IntervalMonthDayNanoVector;
import org.apache.arrow.vector.PeriodDuration;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.IntervalMonthDayNanoWriter;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class IntervalMonthDayNanoReaderImpl extends AbstractFieldReader {
   private final IntervalMonthDayNanoVector vector;

   public IntervalMonthDayNanoReaderImpl(IntervalMonthDayNanoVector vector) {
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

   public void copyAsValue(IntervalMonthDayNanoWriter writer) {
      IntervalMonthDayNanoWriterImpl impl = (IntervalMonthDayNanoWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      IntervalMonthDayNanoWriterImpl impl = (IntervalMonthDayNanoWriterImpl)writer.intervalMonthDayNano(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableIntervalMonthDayNanoHolder h) {
      this.vector.get(this.idx(), h);
   }

   public PeriodDuration readPeriodDuration() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
