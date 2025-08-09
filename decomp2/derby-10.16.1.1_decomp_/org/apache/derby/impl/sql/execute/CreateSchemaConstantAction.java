package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class CreateSchemaConstantAction extends DDLConstantAction {
   private final String aid;
   private final String schemaName;

   CreateSchemaConstantAction(String var1, String var2) {
      this.schemaName = var1;
      this.aid = var2;
   }

   public String toString() {
      return "CREATE SCHEMA " + this.schemaName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      TransactionController var2 = var1.getLanguageConnectionContext().getTransactionExecute();
      this.executeConstantActionMinion(var1, var2);
   }

   public void executeConstantAction(Activation var1, TransactionController var2) throws StandardException {
      this.executeConstantActionMinion(var1, var2);
   }

   private void executeConstantActionMinion(Activation var1, TransactionController var2) throws StandardException {
      LanguageConnectionContext var3 = var1.getLanguageConnectionContext();
      DataDictionary var4 = var3.getDataDictionary();
      DataDescriptorGenerator var5 = var4.getDataDescriptorGenerator();
      SchemaDescriptor var6 = var4.getSchemaDescriptor(this.schemaName, var3.getTransactionExecute(), false);
      if (var6 != null && var6.getUUID() != null) {
         throw StandardException.newException("X0Y68.S", new Object[]{"Schema", this.schemaName});
      } else {
         UUID var7 = var4.getUUIDFactory().createUUID();
         String var8 = this.aid;
         if (var8 == null) {
            var8 = var3.getCurrentUserId(var1);
         }

         var4.startWriting(var3);
         var6 = var5.newSchemaDescriptor(this.schemaName, var8, var7);
         var4.addDescriptor(var6, (TupleDescriptor)null, 3, false, var2);
      }
   }
}
