package org.apache.derby.iapi.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class SQLBit extends SQLBinary {
   public Object getObject() throws StandardException {
      return this.getBytes();
   }

   public String getTypeName() {
      return "CHAR () FOR BIT DATA";
   }

   int getMaxMemoryUsage() {
      return 254;
   }

   public int getTypeFormatId() {
      return 87;
   }

   public DataValueDescriptor getNewNull() {
      return new SQLBit();
   }

   public final void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException {
      this.setValue(var1.getBytes(var2));
   }

   public int typePrecedence() {
      return 140;
   }

   final void setObject(Object var1) throws StandardException {
      this.setValue((byte[])var1);
   }

   public SQLBit() {
   }

   public SQLBit(byte[] var1) {
      this.dataValue = var1;
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      int var3 = var1.getMaximumWidth();
      ((SQLBinary)this).setValue(var2.getBytes());
      this.setWidth(var3, 0, true);
   }

   public void setWidth(int var1, int var2, boolean var3) throws StandardException {
      if (this.getValue() != null) {
         int var4 = this.dataValue.length;
         if (var4 < var1) {
            byte[] var5 = new byte[var1];
            System.arraycopy(this.dataValue, 0, var5, 0, this.dataValue.length);
            Arrays.fill(var5, this.dataValue.length, var5.length, (byte)32);
            this.dataValue = var5;
         } else if (var4 > var1) {
            if (var3) {
               for(int var6 = var1; var6 < this.dataValue.length; ++var6) {
                  if (this.dataValue[var6] != 32) {
                     throw StandardException.newException("22001", new Object[]{this.getTypeName(), StringUtil.formatForPrint(this.toString()), String.valueOf(var1)});
                  }
               }
            }

            this.truncate(var4, var1, !var3);
         }

      }
   }
}
