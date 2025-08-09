package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropIndexConstantAction extends IndexConstantAction {
   private String fullIndexName;
   private long tableConglomerateId;

   DropIndexConstantAction(String var1, String var2, String var3, String var4, UUID var5, long var6) {
      super(var5, var2, var3, var4);
      this.fullIndexName = var1;
      this.tableConglomerateId = var6;
   }

   public String toString() {
      return "DROP INDEX " + this.fullIndexName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      TransactionController var6 = var4.getTransactionExecute();
      var5.startWriting(var4);
      if (this.tableConglomerateId == 0L) {
         TableDescriptor var2 = var5.getTableDescriptor(this.tableId);
         if (var2 == null) {
            throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
         }

         this.tableConglomerateId = var2.getHeapConglomerateId();
      }

      this.lockTableForDDL(var6, this.tableConglomerateId, true);
      TableDescriptor var8 = var5.getTableDescriptor(this.tableId);
      if (var8 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
      } else {
         SchemaDescriptor var7 = var5.getSchemaDescriptor(this.schemaName, var6, true);
         ConglomerateDescriptor var3 = var5.getConglomerateDescriptor(this.indexName, var7, true);
         if (var3 == null) {
            throw StandardException.newException("X0X99.S", new Object[]{this.fullIndexName});
         } else {
            this.dropConglomerate(var3, var8, var1, var4);
         }
      }
   }
}
