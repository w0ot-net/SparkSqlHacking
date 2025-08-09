package org.apache.arrow.vector.complex.impl;

import java.time.Period;
import org.apache.arrow.vector.IntervalYearVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.IntervalYearWriter;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class IntervalYearReaderImpl extends AbstractFieldReader {
   private final IntervalYearVector vector;

   public IntervalYearReaderImpl(IntervalYearVector vector) {
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

   public void copyAsValue(IntervalYearWriter writer) {
      IntervalYearWriterImpl impl = (IntervalYearWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      IntervalYearWriterImpl impl = (IntervalYearWriterImpl)writer.intervalYear(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableIntervalYearHolder h) {
      this.vector.get(this.idx(), h);
   }

   public Period readPeriod() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
