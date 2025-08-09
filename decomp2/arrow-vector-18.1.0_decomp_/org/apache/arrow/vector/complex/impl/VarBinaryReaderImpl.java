package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.VarBinaryVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.VarBinaryWriter;
import org.apache.arrow.vector.holders.NullableVarBinaryHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class VarBinaryReaderImpl extends AbstractFieldReader {
   private final VarBinaryVector vector;

   public VarBinaryReaderImpl(VarBinaryVector vector) {
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

   public void copyAsValue(VarBinaryWriter writer) {
      VarBinaryWriterImpl impl = (VarBinaryWriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      VarBinaryWriterImpl impl = (VarBinaryWriterImpl)writer.varBinary(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableVarBinaryHolder h) {
      this.vector.get(this.idx(), h);
   }

   public byte[] readByteArray() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
