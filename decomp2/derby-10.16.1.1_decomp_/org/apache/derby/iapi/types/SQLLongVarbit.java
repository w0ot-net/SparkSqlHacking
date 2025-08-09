package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public class SQLLongVarbit extends SQLVarbit {
   public String getTypeName() {
      return "LONG VARCHAR FOR BIT DATA";
   }

   int getMaxMemoryUsage() {
      return 32700;
   }

   public DataValueDescriptor getNewNull() {
      return new SQLLongVarbit();
   }

   public int getTypeFormatId() {
      return 234;
   }

   public SQLLongVarbit() {
   }

   public SQLLongVarbit(byte[] var1) {
      super(var1);
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      if (var2 instanceof SQLLongVarbit var3) {
         this.stream = var3.stream;
         this.dataValue = var3.dataValue;
      } else {
         this.setValue(var2.getBytes());
      }

   }

   public int typePrecedence() {
      return 160;
   }
}
