package org.apache.derby.iapi.types;

import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class SQLVarbit extends SQLBit {
   public String getTypeName() {
      return "VARCHAR () FOR BIT DATA";
   }

   int getMaxMemoryUsage() {
      return 32672;
   }

   public DataValueDescriptor getNewNull() {
      return new SQLVarbit();
   }

   public int getTypeFormatId() {
      return 88;
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      int var3 = var1.getMaximumWidth();
      byte[] var4 = var2.getBytes();
      this.setValue(var4);
      if (var4.length > var3) {
         this.setWidth(var3, 0, true);
      }

   }

   public void setWidth(int var1, int var2, boolean var3) throws StandardException {
      if (this.getValue() != null) {
         int var4 = this.dataValue.length;
         if (var4 > var1) {
            if (var3) {
               for(int var5 = var1; var5 < this.dataValue.length; ++var5) {
                  if (this.dataValue[var5] != 32) {
                     throw StandardException.newException("22001", new Object[]{this.getTypeName(), StringUtil.formatForPrint(this.toString()), String.valueOf(var1)});
                  }
               }
            }

            this.truncate(var4, var1, !var3);
         }

      }
   }

   public SQLVarbit() {
   }

   public SQLVarbit(byte[] var1) {
      super(var1);
   }

   public int typePrecedence() {
      return 150;
   }
}
