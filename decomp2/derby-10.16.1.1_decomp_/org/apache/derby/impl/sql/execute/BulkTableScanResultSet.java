package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class BulkTableScanResultSet extends TableScanResultSet implements CursorResultSet {
   private DataValueDescriptor[][] rowArray;
   private RowLocation[] rowLocations;
   private int curRowPosition;
   private int numRowsInArray;
   private int baseColumnCount;
   private int resultColumnCount;
   private static int OUT_OF_ROWS = 0;

   BulkTableScanResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, int var23, boolean var24, boolean var25, double var26, double var28) throws StandardException {
      super(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, adjustBulkFetchSize(var4, var23, var24), var25, var26, var28);
      this.setRowLocationsState();
      if (this.fetchRowLocations) {
         this.resultColumnCount = this.accessedCols == null ? this.candidate.nColumns() : this.accessedCols.getNumBitsSet();
         this.baseColumnCount = this.candidate.nColumns() - 1;
         this.candidate.setRowArray(this.lopOffRowLocation());
         if (this.accessedCols == null) {
            this.accessedCols = new FormatableBitSet(this.baseColumnCount);

            for(int var30 = 0; var30 < this.baseColumnCount; ++var30) {
               this.accessedCols.set(var30);
            }
         } else {
            FormatableBitSet var32 = new FormatableBitSet(this.baseColumnCount);

            for(int var31 = 0; var31 < this.baseColumnCount; ++var31) {
               if (this.accessedCols.isSet(var31)) {
                  var32.set(var31);
               }
            }

            this.accessedCols = var32;
         }
      }

   }

   private static int adjustBulkFetchSize(Activation var0, int var1, boolean var2) {
      return var2 && var0.getResultSetHoldability() ? 1 : var1;
   }

   protected void openScanController(TransactionController var1) throws StandardException {
      DataValueDescriptor[] var2 = this.startPosition == null ? null : this.startPosition.getRowArray();
      DataValueDescriptor[] var3 = this.stopPosition == null ? null : this.stopPosition.getRowArray();
      if (this.qualifiers != null) {
         this.clearOrderableCache(this.qualifiers);
      }

      if (var1 == null) {
         var1 = this.activation.getTransactionController();
      }

      this.scanController = var1.openCompiledScan(this.activation.getResultSetHoldability(), this.forUpdate ? 4 : 0, this.lockMode, this.isolationLevel, this.accessedCols, var2, this.startSearchOperator, this.qualifiers, var3, this.stopSearchOperator, this.scoci, this.dcoci);
      this.scanControllerOpened = true;
      this.rowsThisScan = 0L;
      this.activation.informOfRowCount(this, this.scanController.getEstimatedRowCount());
   }

   public void openCore() throws StandardException {
      super.openCore();
      this.beginTime = this.getCurrentTimeMillis();
      this.rowArray = new DataValueDescriptor[this.rowsPerRead][];
      if (this.fetchRowLocations) {
         this.rowLocations = new RowLocation[this.rowsPerRead];
      }

      this.rowArray[0] = this.candidate.getRowArrayClone();
      this.numRowsInArray = 0;
      this.curRowPosition = -1;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private DataValueDescriptor[] lopOffRowLocation() throws StandardException {
      DataValueDescriptor[] var1 = this.candidate.getRowArrayClone();
      int var2 = var1.length - 1;
      DataValueDescriptor[] var3 = new DataValueDescriptor[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = var1[var4];
      }

      return var3;
   }

   public void reopenCore() throws StandardException {
      super.reopenCore();
      this.numRowsInArray = 0;
      this.curRowPosition = -1;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         Object var1 = null;
         this.checkCancellationFlag();
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen && this.scanControllerOpened) {
            if (this.currentRow == null) {
               this.currentRow = this.getCompactRow(this.candidate, this.accessedCols, this.isKeyed);
            }

            label41:
            while(true) {
               if (this.curRowPosition >= this.numRowsInArray - 1 && this.reloadArray() == OUT_OF_ROWS) {
                  this.clearCurrentRow();
                  this.setRowCountIfPossible(this.rowsThisScan);
                  return null;
               }

               while(++this.curRowPosition < this.numRowsInArray) {
                  this.candidate.setRowArray(this.rowArray[this.curRowPosition]);
                  this.currentRow = this.setCompactRow(this.candidate, this.currentRow);
                  ++this.rowsSeen;
                  ++this.rowsThisScan;
                  if (!this.skipRow(this.candidate)) {
                     var1 = this.currentRow;
                     if (this.fetchRowLocations) {
                        var1 = new ValueRow(this.resultColumnCount);

                        int var2;
                        for(var2 = 1; var2 < this.resultColumnCount; ++var2) {
                           ((ExecRow)var1).setColumn(var2, this.currentRow.getColumn(var2));
                        }

                        ((ExecRow)var1).setColumn(var2, this.rowLocations[this.curRowPosition]);
                     }
                     break label41;
                  }

                  ++this.rowsFiltered;
               }
            }
         }

         this.setCurrentRow((ExecRow)var1);
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return (ExecRow)var1;
      }
   }

   private int reloadArray() throws StandardException {
      this.curRowPosition = -1;
      this.numRowsInArray = ((GroupFetchScanController)this.scanController).fetchNextGroup(this.rowArray, this.rowLocations);
      return this.numRowsInArray;
   }

   public void close() throws StandardException {
      super.close();
      this.numRowsInArray = -1;
      this.curRowPosition = -1;
      this.rowArray = null;
      this.rowLocations = null;
   }

   protected boolean canGetInstantaneousLocks() {
      return !this.forUpdate;
   }

   public boolean requiresRelocking() {
      return this.isolationLevel == 2 || this.isolationLevel == 3 || this.isolationLevel == 1;
   }
}
