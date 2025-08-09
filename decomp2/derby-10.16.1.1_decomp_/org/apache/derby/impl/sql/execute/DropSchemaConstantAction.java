package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropSchemaConstantAction extends DDLConstantAction {
   private final String schemaName;

   DropSchemaConstantAction(String var1) {
      this.schemaName = var1;
   }

   public String toString() {
      return "DROP SCHEMA " + this.schemaName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      var3.startWriting(var2);
      SchemaDescriptor var5 = var3.getSchemaDescriptor(this.schemaName, var4, true);
      var5.drop(var2, var1);
   }
}
