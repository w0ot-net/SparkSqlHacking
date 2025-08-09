package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class SetSchemaConstantAction implements ConstantAction {
   private final String schemaName;
   private final int type;

   SetSchemaConstantAction(String var1, int var2) {
      this.schemaName = var1;
      this.type = var2;
   }

   public String toString() {
      String var10000 = this.type == 1 ? "USER" : (this.type == 2 && this.schemaName == null ? "?" : this.schemaName);
      return "SET SCHEMA " + var10000;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      String var4 = this.schemaName;
      if (this.type == 2) {
         ParameterValueSet var5 = var1.getParameterValueSet();
         DataValueDescriptor var6 = var5.getParameter(0);
         var4 = var6.getString();
         if (var4 == null || var4.length() > 128) {
            throw StandardException.newException("42815.S.713", new Object[]{"CURRENT SCHEMA"});
         }
      } else if (this.type == 1) {
         var4 = var2.getCurrentUserId(var1);
      }

      SchemaDescriptor var7 = var3.getSchemaDescriptor(var4, var2.getTransactionExecute(), true);
      var2.setDefaultSchema(var1, var7);
   }
}
