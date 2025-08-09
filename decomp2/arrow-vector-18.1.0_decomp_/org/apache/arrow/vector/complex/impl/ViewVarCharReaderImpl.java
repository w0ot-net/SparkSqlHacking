package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.ViewVarCharVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.ViewVarCharWriter;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.Text;

public class ViewVarCharReaderImpl extends AbstractFieldReader {
   private final ViewVarCharVector vector;

   public ViewVarCharReaderImpl(ViewVarCharVector vector) {
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

   public void copyAsValue(ViewVarCharWriter writer) {
      ViewVarCharWriterImpl impl = (ViewVarCharWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      ViewVarCharWriterImpl impl = (ViewVarCharWriterImpl)writer.viewVarChar(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableViewVarCharHolder h) {
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
