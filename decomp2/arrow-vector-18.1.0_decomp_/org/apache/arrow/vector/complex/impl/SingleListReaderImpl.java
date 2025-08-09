package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.AbstractContainerVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.types.Types;

public class SingleListReaderImpl extends AbstractFieldReader {
   private final String name;
   private final AbstractContainerVector container;
   private FieldReader reader;

   public SingleListReaderImpl(String name, AbstractContainerVector container) {
      this.name = name;
      this.container = container;
   }

   public void setPosition(int index) {
      super.setPosition(index);
      if (this.reader != null) {
         this.reader.setPosition(index);
      }

   }

   public Object readObject() {
      return this.reader.readObject();
   }

   public FieldReader reader() {
      if (this.reader == null) {
         this.reader = this.container.getChild(this.name).getReader();
         this.setPosition(this.idx());
      }

      return this.reader;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LIST;
   }

   public boolean isSet() {
      return false;
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
      throw new UnsupportedOperationException("Generic list copying not yet supported.  Please resolve to typed list.");
   }

   public void copyAsField(String name, BaseWriter.StructWriter writer) {
      throw new UnsupportedOperationException("Generic list copying not yet supported.  Please resolve to typed list.");
   }
}
