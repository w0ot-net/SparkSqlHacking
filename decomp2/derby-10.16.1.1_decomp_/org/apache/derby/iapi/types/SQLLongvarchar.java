package org.apache.derby.iapi.types;

import java.text.RuleBasedCollator;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class SQLLongvarchar extends SQLVarchar {
   public String getTypeName() {
      return "LONG VARCHAR";
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      try {
         return new SQLLongvarchar(this.getString());
      } catch (StandardException var3) {
         return null;
      }
   }

   public DataValueDescriptor getNewNull() {
      return new SQLLongvarchar();
   }

   public StringDataValue getValue(RuleBasedCollator var1) {
      if (var1 == null) {
         return this;
      } else {
         CollatorSQLLongvarchar var2 = new CollatorSQLLongvarchar(var1);
         var2.copyState(this);
         return var2;
      }
   }

   public int getTypeFormatId() {
      return 235;
   }

   public SQLLongvarchar() {
   }

   public SQLLongvarchar(String var1) {
      super(var1);
   }

   protected void normalize(DataTypeDescriptor var1, String var2) throws StandardException {
      if (var2.length() > var1.getMaximumWidth()) {
         throw StandardException.newException("22001", new Object[]{this.getTypeName(), StringUtil.formatForPrint(var2), String.valueOf(var1.getMaximumWidth())});
      } else {
         this.setValue(var2);
      }
   }

   public StringDataValue concatenate(StringDataValue var1, StringDataValue var2, StringDataValue var3) throws StandardException {
      var3 = super.concatenate(var1, var2, var3);
      if (var3.getString() != null && var3.getString().length() > 32700) {
         throw StandardException.newException("54006", new Object[]{"CONCAT", String.valueOf(32700)});
      } else {
         return var3;
      }
   }

   public int typePrecedence() {
      return 12;
   }
}
