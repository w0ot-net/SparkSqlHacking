package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class DropViewConstantAction extends DDLConstantAction {
   private String fullTableName;
   private String tableName;
   private SchemaDescriptor sd;

   DropViewConstantAction(String var1, String var2, SchemaDescriptor var3) {
      this.fullTableName = var1;
      this.tableName = var2;
      this.sd = var3;
   }

   public String toString() {
      return "DROP VIEW " + this.fullTableName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      var5.startWriting(var4);
      TableDescriptor var2 = var5.getTableDescriptor(this.tableName, this.sd, var4.getTransactionExecute());
      if (var2 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.fullTableName});
      } else if (var2.getTableType() != 2) {
         throw StandardException.newException("X0Y16.S", new Object[]{this.fullTableName});
      } else {
         ViewDescriptor var3 = var5.getViewDescriptor(var2);
         var3.drop(var4, this.sd, var2);
      }
   }
}
