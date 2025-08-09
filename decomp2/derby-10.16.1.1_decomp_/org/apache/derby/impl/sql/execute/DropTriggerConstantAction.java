package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class DropTriggerConstantAction extends DDLSingleTableConstantAction {
   private final String triggerName;
   private final SchemaDescriptor sd;

   DropTriggerConstantAction(SchemaDescriptor var1, String var2, UUID var3) {
      super(var3);
      this.sd = var1;
      this.triggerName = var2;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var3 = var1.getLanguageConnectionContext();
      DataDictionary var4 = var3.getDataDictionary();
      var4.startWriting(var3);
      TableDescriptor var5 = var4.getTableDescriptor(this.tableId);
      if (var5 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.tableId.toString()});
      } else {
         TransactionController var6 = var3.getTransactionExecute();
         this.lockTableForDDL(var6, var5.getHeapConglomerateId(), true);
         var5 = var4.getTableDescriptor(this.tableId);
         if (var5 == null) {
            throw StandardException.newException("X0X05.S", new Object[]{this.tableId.toString()});
         } else {
            TriggerDescriptor var2 = var4.getTriggerDescriptor(this.triggerName, this.sd);
            if (var2 == null) {
               Object[] var10001 = new Object[]{"TRIGGER", null};
               String var10004 = this.sd.getSchemaName();
               var10001[1] = var10004 + "." + this.triggerName;
               throw StandardException.newException("X0X81.S", var10001);
            } else {
               var2.drop(var3);
            }
         }
      }
   }

   public String toString() {
      return "DROP TRIGGER " + this.triggerName;
   }
}
