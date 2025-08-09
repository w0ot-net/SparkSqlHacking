package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DecimalTypeIdImpl extends BaseTypeIdImpl {
   public DecimalTypeIdImpl() {
   }

   public DecimalTypeIdImpl(boolean var1) {
      super(198);
      if (var1) {
         this.setNumericType();
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      boolean var2 = var1.readBoolean();
      super.readExternal(var1);
      if (var2) {
         this.setNumericType();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeBoolean(this.getJDBCTypeId() == 2);
      super.writeExternal(var1);
   }

   private void setNumericType() {
      this.unqualifiedName = "NUMERIC";
      this.JDBCTypeId = 2;
   }
}
