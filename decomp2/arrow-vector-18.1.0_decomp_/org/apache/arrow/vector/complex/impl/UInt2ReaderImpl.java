package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.UInt2Vector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.UInt2Writer;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UInt2ReaderImpl extends AbstractFieldReader {
   private final UInt2Vector vector;

   public UInt2ReaderImpl(UInt2Vector vector) {
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

   public void copyAsValue(UInt2Writer writer) {
      UInt2WriterImpl impl = (UInt2WriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      UInt2WriterImpl impl = (UInt2WriterImpl)writer.uInt2(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableUInt2Holder h) {
      this.vector.get(this.idx(), h);
   }

   public Character readCharacter() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
