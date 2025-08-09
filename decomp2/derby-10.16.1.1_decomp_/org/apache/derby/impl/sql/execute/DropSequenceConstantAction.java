package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropSequenceConstantAction extends DDLConstantAction {
   private final String sequenceName;
   private final SchemaDescriptor schemaDescriptor;

   DropSequenceConstantAction(SchemaDescriptor var1, String var2) {
      this.sequenceName = var2;
      this.schemaDescriptor = var1;
   }

   public String toString() {
      return "DROP SEQUENCE " + this.sequenceName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      var3.startWriting(var2);
      var3.clearSequenceCaches();
      SequenceDescriptor var5 = var3.getSequenceDescriptor(this.schemaDescriptor, this.sequenceName);
      if (var5 == null) {
         Object[] var10001 = new Object[]{"SEQUENCE", null};
         String var10004 = this.schemaDescriptor.getObjectName();
         var10001[1] = var10004 + "." + this.sequenceName;
         throw StandardException.newException("X0X81.S", var10001);
      } else {
         var5.drop(var2);
      }
   }
}
