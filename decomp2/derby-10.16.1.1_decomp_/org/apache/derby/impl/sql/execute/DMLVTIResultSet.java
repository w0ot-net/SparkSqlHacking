package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

abstract class DMLVTIResultSet extends DMLWriteResultSet {
   NoPutResultSet sourceResultSet;
   NoPutResultSet savedSource;
   UpdatableVTIConstantAction constants;
   TransactionController tc;
   private int numOpens;
   boolean firstExecute;

   DMLVTIResultSet(NoPutResultSet var1, Activation var2) throws StandardException {
      super(var2);
      this.sourceResultSet = var1;
      this.constants = (UpdatableVTIConstantAction)this.constantAction;
      this.tc = var2.getTransactionController();
      this.resultDescription = this.sourceResultSet.getResultDescription();
   }

   public void open() throws StandardException {
      this.setup();
      this.firstExecute = this.numOpens == 0;
      this.rowCount = 0L;
      if (this.numOpens++ == 0) {
         this.sourceResultSet.openCore();
      } else {
         this.sourceResultSet.reopenCore();
      }

      this.openCore();
      if (this.lcc.getRunTimeStatisticsMode()) {
         this.savedSource = this.sourceResultSet;
      }

      this.cleanUp();
      this.endTime = this.getCurrentTimeMillis();
   }

   protected abstract void openCore() throws StandardException;

   public void cleanUp() throws StandardException {
      if (null != this.sourceResultSet) {
         this.sourceResultSet.close();
      }

      this.numOpens = 0;
      this.close(false);
   }

   public void finish() throws StandardException {
      this.sourceResultSet.finish();
      super.finish();
   }
}
