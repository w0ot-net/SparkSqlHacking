package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.SmallIntWriter;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class SmallIntReaderImpl extends AbstractFieldReader {
   private final SmallIntVector vector;

   public SmallIntReaderImpl(SmallIntVector vector) {
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

   public void copyAsValue(SmallIntWriter writer) {
      SmallIntWriterImpl impl = (SmallIntWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      SmallIntWriterImpl impl = (SmallIntWriterImpl)writer.smallInt(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableSmallIntHolder h) {
      this.vector.get(this.idx(), h);
   }

   public Short readShort() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
