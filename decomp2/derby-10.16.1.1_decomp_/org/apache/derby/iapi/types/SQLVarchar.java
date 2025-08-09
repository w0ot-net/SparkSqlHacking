package org.apache.derby.iapi.types;

import java.sql.Clob;
import java.text.RuleBasedCollator;
import org.apache.derby.shared.common.error.StandardException;

public class SQLVarchar extends SQLChar {
   public String getTypeName() {
      return "VARCHAR";
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         return new SQLVarchar(this.getString());
      } catch (StandardException var3) {
         return null;
      }
   }

   public DataValueDescriptor getNewNull() {
      return new SQLVarchar();
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 == null) {
         return this;
      } else {
         CollatorSQLVarchar var2 = new CollatorSQLVarchar(var1);
         var2.copyState(this);
         return var2;
      }
   }

   public int getTypeFormatId() {
      return 85;
   }

   public SQLVarchar() {
   }

   public SQLVarchar(String var1) {
      super(var1);
   }

   public SQLVarchar(Clob var1) {
      super(var1);
   }

   public SQLVarchar(char[] var1) {
      super(var1);
   }

   public void normalize(DataTypeDescriptor var1, DataValueDescriptor var2) throws StandardException {
      this.normalize(var1, var2.getString());
   }

   protected void normalize(DataTypeDescriptor var1, String var2) throws StandardException {
      int var3 = var1.getMaximumWidth();
      int var4 = var2.length();
      if (var4 > var3) {
         this.hasNonBlankChars(var2, var3, var4);
         var2 = var2.substring(0, var3);
      }

      this.setValue(var2);
   }

   public int typePrecedence() {
      return 10;
   }

   protected final int growBy() {
      return 4096;
   }
}
