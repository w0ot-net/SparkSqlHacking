package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class LockTableConstantAction implements ConstantAction {
   private final String fullTableName;
   private final long conglomerateNumber;
   private final boolean exclusiveMode;

   LockTableConstantAction(String var1, long var2, boolean var4) {
      this.fullTableName = var1;
      this.conglomerateNumber = var2;
      this.exclusiveMode = var4;
   }

   public String toString() {
      return "LOCK TABLE " + this.fullTableName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      TransactionController var3 = var1.getTransactionController();

      try {
         ConglomerateController var2 = var3.openConglomerate(this.conglomerateNumber, false, this.exclusiveMode ? 68 : 64, 7, 5);
         var2.close();
      } catch (StandardException var7) {
         StandardException var4 = var7;
         String var5 = var7.getMessageId();
         if (var7.isLockTimeoutOrDeadlock()) {
            String var6 = this.exclusiveMode ? "EXCLUSIVE" : "SHARE";
            var4 = StandardException.newException("X0X02.S", var7, new Object[]{this.fullTableName, var6});
         }

         throw var4;
      }
   }
}
