package org.apache.arrow.vector.complex.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;

public class SingleStructReaderImpl extends AbstractFieldReader {
   private final NonNullableStructVector vector;
   private final Map fields = new HashMap();

   public SingleStructReaderImpl(NonNullableStructVector vector) {
      this.vector = vector;
   }

   private void setChildrenPosition(int index) {
      for(FieldReader r : this.fields.values()) {
         r.setPosition(index);
      }

   }

   public Field getField() {
      return this.vector.getField();
   }

   public FieldReader reader(String name) {
      FieldReader reader = (FieldReader)this.fields.get(name);
      if (reader == null) {
         ValueVector child = this.vector.getChild(name);
         if (child == null) {
            reader = NullReader.INSTANCE;
         } else {
            reader = child.getReader();
         }

         this.fields.put(name, reader);
         reader.setPosition(this.idx());
      }

      return reader;
   }

   public void setPosition(int index) {
      super.setPosition(index);

      for(FieldReader r : this.fields.values()) {
         r.setPosition(index);
      }

   }

   public Object readObject() {
      return this.vector.getObject(this.idx());
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.STRUCT;
   }

   public boolean isSet() {
      return true;
   }

   public Iterator iterator() {
      return this.vector.fieldNameIterator();
   }

   public void copyAsValue(BaseWriter.StructWriter writer) {
      SingleStructWriter impl = (SingleStructWriter)writer;
      impl.container.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      SingleStructWriter impl = (SingleStructWriter)writer.struct(name);
      impl.container.copyFromSafe(this.idx(), impl.idx(), this.vector);
   }
}
