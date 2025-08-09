package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.UInt1Vector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.complex.writer.UInt1Writer;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class UInt1ReaderImpl extends AbstractFieldReader {
   private final UInt1Vector vector;

   public UInt1ReaderImpl(UInt1Vector vector) {
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

   public void copyAsValue(UInt1Writer writer) {
      UInt1WriterImpl impl = (UInt1WriterImpl)writer;
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      UInt1WriterImpl impl = (UInt1WriterImpl)writer.uInt1(name);
      impl.vector.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void read(NullableUInt1Holder h) {
      this.vector.get(this.idx(), h);
   }

   public Byte readByte() {
      return this.vector.getObject(this.idx());
   }

   public void copyValue(FieldWriter w) {
   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }
}
