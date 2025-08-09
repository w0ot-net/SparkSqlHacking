package org.apache.arrow.vector.complex.impl;

import java.time.Duration;
import org.apache.arrow.vector.DurationVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.DurationWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class DurationReaderImpl extends AbstractFieldReader {
   private final DurationVector vector;

   public DurationReaderImpl(DurationVector vector) {
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

   public void copyAsValue(DurationWriter writer) {
      DurationWriterImpl impl = (DurationWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      DurationWriterImpl impl = (DurationWriterImpl)writer.duration(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableDurationHolder h) {
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
