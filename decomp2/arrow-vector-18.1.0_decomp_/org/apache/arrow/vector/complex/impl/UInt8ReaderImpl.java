package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.UInt8Vector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.UInt8Writer;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UInt8ReaderImpl extends AbstractFieldReader {
   private final UInt8Vector vector;

   public UInt8ReaderImpl(UInt8Vector vector) {
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

   public void copyAsValue(UInt8Writer writer) {
      UInt8WriterImpl impl = (UInt8WriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      UInt8WriterImpl impl = (UInt8WriterImpl)writer.uInt8(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableUInt8Holder h) {
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
