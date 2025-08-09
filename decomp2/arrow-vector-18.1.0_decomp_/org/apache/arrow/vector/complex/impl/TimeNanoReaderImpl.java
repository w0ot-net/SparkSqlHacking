package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.TimeNanoVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.TimeNanoWriter;
import org.apache.arrow.vector.holders.NullableTimeNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class TimeNanoReaderImpl extends AbstractFieldReader {
   private final TimeNanoVector vector;

   public TimeNanoReaderImpl(TimeNanoVector vector) {
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

   public void copyAsValue(TimeNanoWriter writer) {
      TimeNanoWriterImpl impl = (TimeNanoWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      TimeNanoWriterImpl impl = (TimeNanoWriterImpl)writer.timeNano(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableTimeNanoHolder h) {
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
