package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.LargeVarCharVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.LargeVarCharWriter;
import org.apache.arrow.vector.holders.NullableLargeVarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class LargeVarCharReaderImpl extends AbstractFieldReader {
   private final LargeVarCharVector vector;

   public LargeVarCharReaderImpl(LargeVarCharVector vector) {
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

   public void copyAsValue(LargeVarCharWriter writer) {
      LargeVarCharWriterImpl impl = (LargeVarCharWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      LargeVarCharWriterImpl impl = (LargeVarCharWriterImpl)writer.largeVarChar(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableLargeVarCharHolder h) {
      this.vector.get(this.idx(), h);
   }

   public Text readText() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
