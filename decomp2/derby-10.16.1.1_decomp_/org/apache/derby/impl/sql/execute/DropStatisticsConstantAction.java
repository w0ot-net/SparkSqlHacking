package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropStatisticsConstantAction extends DDLConstantAction {
   private final String objectName;
   private final boolean forTable;
   private final SchemaDescriptor sd;
   private final String fullTableName;

   DropStatisticsConstantAction(SchemaDescriptor var1, String var2, String var3, boolean var4) {
      this.objectName = var3;
      this.sd = var1;
      this.forTable = var4;
      this.fullTableName = var2;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      ConglomerateDescriptor var3 = null;
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      DependencyManager var6 = var5.getDependencyManager();
      TransactionController var7 = var4.getTransactionExecute();
      var5.startWriting(var4);
      TableDescriptor var2;
      if (this.forTable) {
         var2 = var5.getTableDescriptor(this.objectName, this.sd, var7);
      } else {
         var3 = var5.getConglomerateDescriptor(this.objectName, this.sd, false);
         var2 = var5.getTableDescriptor(var3.getTableID());
      }

      var6.invalidateFor(var2, 39, var4);
      var5.dropStatisticsDescriptors(var2.getUUID(), var3 != null ? var3.getUUID() : null, var7);
   }

   public String toString() {
      String var10000 = this.forTable ? "table " : "index ";
      return "DROP STATISTICS FOR " + var10000 + this.fullTableName;
   }
}
