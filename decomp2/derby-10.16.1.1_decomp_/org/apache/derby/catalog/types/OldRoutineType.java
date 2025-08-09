package org.apache.derby.catalog.types;

import java.io.DataInput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.Formatable;

final class OldRoutineType implements Formatable {
   private TypeDescriptor catalogType;

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatIdUtil.readFormatIdInteger((DataInput)var1);
      var1.readObject();
      this.catalogType = (TypeDescriptor)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
   }

   public int getTypeFormatId() {
      return 14;
   }

   TypeDescriptor getCatalogType() {
      return this.catalogType;
   }
}
