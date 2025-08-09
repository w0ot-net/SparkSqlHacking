package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.types.pojo.Field;

public class NullableStructReaderImpl extends SingleStructReaderImpl {
   private StructVector nullableStructVector;

   public NullableStructReaderImpl(NonNullableStructVector vector) {
      super(vector);
      this.nullableStructVector = (StructVector)vector;
   }

   public Field getField() {
      return this.nullableStructVector.getField();
   }

   public void copyAsValue(BaseWriter.StructWriter writer) {
      NullableStructWriter impl = (NullableStructWriter)writer;
      impl.container.copyFromSafe(this.idx(), impl.idx(), this.nullableStructVector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      NullableStructWriter impl = (NullableStructWriter)writer.struct(name);
      impl.container.copyFromSafe(this.idx(), impl.idx(), this.nullableStructVector);
   }

   public boolean isSet() {
      return !this.nullableStructVector.isNull(this.idx());
   }
}
