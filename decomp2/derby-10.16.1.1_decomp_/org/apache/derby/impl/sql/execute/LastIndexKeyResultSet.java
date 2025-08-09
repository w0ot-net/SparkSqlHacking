package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class LastIndexKeyResultSet extends ScanResultSet {
   protected long conglomId;
   protected int startSearchOperator;
   protected int stopSearchOperator;
   protected Qualifier[][] qualifiers;
   public String userSuppliedOptimizerOverrides;
   protected boolean runTimeStatisticsOn;
   public String stopPositionString;
   public boolean coarserLock;
   public boolean returnedRow;

   public LastIndexKeyResultSet(Activation var1, int var2, int var3, long var4, String var6, String var7, String var8, int var9, int var10, boolean var11, int var12, double var13, double var15) throws StandardException {
      super(var1, var2, var3, var10, var11, var12, var9, var13, var15);
      this.conglomId = var4;
      this.tableName = var6;
      this.userSuppliedOptimizerOverrides = var7;
      this.indexName = var8;
      this.runTimeStatisticsOn = this.getLanguageConnectionContext().getRunTimeStatisticsMode();
      var1.informOfRowCount(this, 1L);
      this.recordConstructorTime();
   }

   boolean canGetInstantaneousLocks() {
      return true;
   }

   public void openCore() throws StandardException {
      ExecRow var1 = this.candidate.getClone();
      this.beginTime = this.getCurrentTimeMillis();
      this.isOpen = true;
      TransactionController var2 = this.activation.getTransactionController();
      this.initIsolationLevel();
      if (var2.fetchMaxOnBtree(this.conglomId, 0, this.lockMode, this.isolationLevel, this.accessedCols, var1.getRowArray())) {
         this.setCurrentRow(this.getCompactRow(var1, this.accessedCols, true));
      } else {
         this.clearCurrentRow();
      }

      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         if (!this.returnedRow && this.isOpen) {
            this.returnedRow = true;
         } else {
            this.clearCurrentRow();
         }

         return this.currentRow;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.isOpen = false;
         this.returnedRow = false;
         this.clearCurrentRow();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 : var2;
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.currentRow;
   }
}
