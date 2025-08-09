package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class TableScanResultSet extends ScanResultSet implements CursorResultSet, Cloneable {
   protected ScanController scanController;
   protected boolean scanControllerOpened;
   protected boolean isKeyed;
   protected boolean firstScan = true;
   protected ExecIndexRow startPosition;
   protected ExecIndexRow stopPosition;
   protected long conglomId;
   protected DynamicCompiledOpenConglomInfo dcoci;
   protected StaticCompiledOpenConglomInfo scoci;
   protected GeneratedMethod startKeyGetter;
   protected int startSearchOperator;
   protected GeneratedMethod stopKeyGetter;
   protected int stopSearchOperator;
   public Qualifier[][] qualifiers;
   public String userSuppliedOptimizerOverrides;
   protected boolean runTimeStatisticsOn;
   protected int[] indexCols;
   public int rowsPerRead;
   public boolean forUpdate;
   final boolean sameStartStopPosition;
   protected boolean nextDone;
   private RowLocation rlTemplate;
   private Properties scanProperties;
   public String startPositionString;
   public String stopPositionString;
   public boolean isConstraint;
   public boolean coarserLock;
   public boolean oneRowScan;
   protected long rowsThisScan;
   private long estimatedRowCount;
   protected BackingStoreHashtable past2FutureTbl;
   protected boolean qualify;
   protected boolean currentRowIsValid;
   protected boolean scanRepositioned;

   TableScanResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, int var20, boolean var21, int var22, int var23, boolean var24, double var25, double var27) throws StandardException {
      super(var4, var6, var5, var20, var21, var22, var18, var25, var27);
      this.conglomId = var1;
      this.scoci = var3;
      this.startKeyGetter = var7;
      this.startSearchOperator = var8;
      this.stopKeyGetter = var9;
      this.stopSearchOperator = var10;
      this.sameStartStopPosition = var11;
      this.qualifiers = var12;
      this.tableName = var13;
      this.userSuppliedOptimizerOverrides = var14;
      this.indexName = var15;
      this.isConstraint = var16;
      this.forUpdate = var17;
      this.rowsPerRead = var23;
      this.oneRowScan = var24;
      if (var19 != -1) {
         this.indexCols = (int[])var4.getPreparedStatement().getSavedObject(var19);
      }

      if (this.indexCols != null) {
         var4.setForUpdateIndexScan(this);
      }

      this.runTimeStatisticsOn = var4 != null && var4.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.qualify = true;
      this.currentRowIsValid = false;
      this.scanRepositioned = false;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      TransactionController var1 = this.activation.getTransactionController();
      this.initIsolationLevel();
      if (this.dcoci == null) {
         this.dcoci = var1.getDynamicCompiledConglomInfo(this.conglomId);
      }

      this.initStartAndStopKey();
      if (this.firstScan) {
         this.openScanController(var1);
         this.isKeyed = this.scanController.isKeyed();
      }

      if (this.skipScan(this.startPosition, this.stopPosition)) {
         this.scanControllerOpened = false;
      } else if (!this.firstScan) {
         this.openScanController(var1);
      }

      if (this.forUpdate && this.isKeyed) {
         this.activation.setIndexScanController(this.scanController);
         this.activation.setIndexConglomerateNumber(this.conglomId);
      }

      this.firstScan = false;
      this.isOpen = true;
      ++this.numOpens;
      this.nextDone = false;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   void initStartAndStopKey() throws StandardException {
      if (this.startKeyGetter != null) {
         this.startPosition = (ExecIndexRow)this.startKeyGetter.invoke(this.activation);
         if (this.sameStartStopPosition) {
            this.stopPosition = this.startPosition;
         }
      }

      if (this.stopKeyGetter != null) {
         this.stopPosition = (ExecIndexRow)this.stopKeyGetter.invoke(this.activation);
      }

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

      int var4 = 0;
      if (this.forUpdate) {
         var4 = 4;
         if (this.activation.isCursorActivation()) {
            var4 |= 4096;
         }
      }

      this.scanController = var1.openCompiledScan(this.activation.getResultSetHoldability(), var4, this.lockMode, this.isolationLevel, this.accessedCols, var2, this.startSearchOperator, this.qualifiers, var3, this.stopSearchOperator, this.scoci, this.dcoci);
      this.scanControllerOpened = true;
      this.rowsThisScan = 0L;
      this.estimatedRowCount = this.scanController.getEstimatedRowCount();
      this.activation.informOfRowCount(this, this.scanController.getEstimatedRowCount());
   }

   protected void reopenScanController() throws StandardException {
      DataValueDescriptor[] var1 = this.startPosition == null ? null : this.startPosition.getRowArray();
      DataValueDescriptor[] var2 = this.stopPosition == null ? null : this.stopPosition.getRowArray();
      this.rowsThisScan = 0L;
      if (this.qualifiers != null) {
         this.clearOrderableCache(this.qualifiers);
      }

      this.scanController.reopenScan(var1, this.startSearchOperator, this.qualifiers, var2, this.stopSearchOperator);
      this.scanControllerOpened = true;
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.initStartAndStopKey();
      if (this.skipScan(this.startPosition, this.stopPosition)) {
         this.scanControllerOpened = false;
      } else if (this.scanController == null) {
         this.openScanController((TransactionController)null);
      } else {
         this.reopenScanController();
      }

      ++this.numOpens;
      this.nextDone = false;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   boolean loopControl(boolean var1) throws StandardException {
      return this.scanController.fetchNext(this.candidate.getRowArray());
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.checkCancellationFlag();
         if (this.currentRow == null || this.scanRepositioned) {
            this.currentRow = this.getCompactRow(this.candidate, this.accessedCols, this.isKeyed);
         }

         this.beginTime = this.getCurrentTimeMillis();
         ExecRow var1 = null;
         if (this.isOpen && !this.nextDone) {
            this.nextDone = this.oneRowScan;
            if (this.scanControllerOpened) {
               boolean var2 = true;

               label42: {
                  while(true) {
                     if (!(var2 = this.loopControl(var2))) {
                        break label42;
                     }

                     ++this.rowsSeen;
                     ++this.rowsThisScan;
                     if (this.sameStartStopPosition || !this.skipRow(this.candidate)) {
                        if (this.past2FutureTbl == null) {
                           break;
                        }

                        RowLocation var3 = (RowLocation)this.currentRow.getColumn(this.currentRow.nColumns());
                        if (this.past2FutureTbl.remove(var3) == null) {
                           break;
                        }
                     } else {
                        ++this.rowsFiltered;
                     }
                  }

                  var1 = this.currentRow;
               }

               if (!var2) {
                  this.setRowCountIfPossible(this.rowsThisScan);
                  this.currentRow = null;
               }
            }
         }

         this.setCurrentRow(var1);
         this.currentRowIsValid = true;
         this.scanRepositioned = false;
         this.qualify = true;
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var1;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         if (this.scanController != null) {
            if (this.runTimeStatisticsOn) {
               this.scanProperties = this.getScanProperties();
               this.startPositionString = this.printStartPosition();
               this.stopPositionString = this.printStopPosition();
            }

            this.scanController.close();
            this.scanController = null;
            if (this.forUpdate && this.isKeyed) {
               this.activation.clearIndexScanInfo();
            }
         }

         this.scanControllerOpened = false;
         this.startPosition = null;
         this.stopPosition = null;
         super.close();
         if (this.indexCols != null) {
            ConglomerateController var1 = this.activation.getHeapConglomerateController();
            if (var1 != null) {
               var1.close();
               this.activation.clearHeapConglomerateController();
            }
         }

         if (this.past2FutureTbl != null) {
            this.past2FutureTbl.close();
            this.past2FutureTbl = null;
         }
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      if (!this.isOpen) {
         return null;
      } else if (!this.scanControllerOpened) {
         return null;
      } else {
         RowLocation var1;
         if (this.isKeyed) {
            var1 = (RowLocation)this.currentRow.getColumn(this.currentRow.nColumns());
         } else if (this.currentRowIsValid) {
            if (this.rlTemplate == null) {
               this.rlTemplate = this.scanController.newRowLocationTemplate();
            }

            var1 = this.rlTemplate;

            try {
               this.scanController.fetchLocation(var1);
            } catch (StandardException var3) {
               if (var3.getMessageId().equals("XSCH7.S")) {
                  throw StandardException.newException("24000", new Object[0]);
               }

               throw var3;
            }
         } else {
            var1 = null;
         }

         return var1;
      }
   }

   public ExecRow getCurrentRow() throws StandardException {
      try {
         if (this.currentRow == null || !this.currentRowIsValid || !this.scanControllerOpened || this.qualify && this.scanController.isCurrentPositionDeleted() || this.qualify && !this.scanController.doesCurrentPositionQualify()) {
            return null;
         }
      } catch (StandardException var3) {
         if (var3.getMessageId().equals("XSAM5.S")) {
            StandardException var1 = StandardException.newException("24000", new Object[0]);
            throw var1;
         }
      }

      this.resultRowBuilder.reset(this.candidate);
      this.currentRow = this.getCompactRow(this.candidate, this.accessedCols, this.isKeyed);

      try {
         this.scanController.fetchWithoutQualify(this.candidate.getRowArray());
      } catch (StandardException var2) {
         if (var2.getMessageId().equals("XSAM6.S")) {
            return null;
         }

         throw var2;
      }

      this.setCurrentRow(this.candidate);
      return this.currentRow;
   }

   public void positionScanAtRowLocation(RowLocation var1) throws StandardException {
      if (!this.isKeyed) {
         this.currentRowIsValid = this.scanController.positionAtRowLocation(var1);
      }

      this.qualify = false;
      this.scanRepositioned = true;
   }

   public String printStartPosition() {
      return this.printPosition(this.startSearchOperator, this.startKeyGetter, this.startPosition);
   }

   public String printStopPosition() {
      return this.sameStartStopPosition ? this.printPosition(this.stopSearchOperator, this.startKeyGetter, this.startPosition) : this.printPosition(this.stopSearchOperator, this.stopKeyGetter, this.stopPosition);
   }

   private String printPosition(int var1, GeneratedMethod var2, ExecIndexRow var3) {
      String var4 = "";
      if (var2 == null) {
         return "\t" + MessageService.getTextMessage("42Z37.U", new Object[0]) + "\n";
      } else {
         if (var3 == null) {
            if (this.numOpens == 0) {
               return "\t" + MessageService.getTextMessage("42Z38.U", new Object[0]) + "\n";
            }

            try {
               var3 = (ExecIndexRow)var2.invoke(this.activation);
            } catch (StandardException var8) {
               Object[] var10001 = new Object[]{var8.toString()};
               return "\t" + MessageService.getTextMessage("42Z39.U", var10001);
            }
         }

         if (var3 == null) {
            return "\t" + MessageService.getTextMessage("42Z37.U", new Object[0]) + "\n";
         } else {
            Object var5 = null;
            String var11;
            switch (var1) {
               case -1 -> var11 = ">";
               case 1 -> var11 = ">=";
               default -> var11 = "unknown value (" + var1 + ")";
            }

            var4 = var4 + "\t" + MessageService.getTextMessage("42Z40.U", new Object[]{var11, String.valueOf(var3.nColumns())}) + "\n";
            var4 = var4 + "\t" + MessageService.getTextMessage("42Z41.U", new Object[0]) + "\n";
            boolean var6 = false;

            for(int var7 = 0; var7 < var3.nColumns(); ++var7) {
               if (var3.areNullsOrdered(var7)) {
                  var4 = var4 + var7 + " ";
                  var6 = true;
               }

               if (var6 && var7 == var3.nColumns() - 1) {
                  var4 = var4 + "\n";
               }
            }

            return var4;
         }
      }
   }

   public Properties getScanProperties() {
      if (this.scanProperties == null) {
         this.scanProperties = new Properties();
      }

      try {
         if (this.scanController != null) {
            this.scanController.getScanInfo().getAllScanInfo(this.scanProperties);
            this.coarserLock = this.scanController.isTableLocked() && this.lockMode == 6;
         }
      } catch (StandardException var2) {
      }

      return this.scanProperties;
   }

   public boolean requiresRelocking() {
      return this.isolationLevel == 3;
   }

   protected final void setRowCountIfPossible(long var1) throws StandardException {
      if (!this.scanController.isKeyed() && (this.qualifiers == null || this.qualifiers.length == 0) && !this.forUpdate) {
         long var3 = var1 - this.estimatedRowCount;
         long var5 = this.estimatedRowCount / 10L;
         if (var3 < 0L) {
            var3 = -var3;
         }

         if (var3 > var5) {
            this.scanController.setEstimatedRowCount(var1);
         }
      }

   }

   protected boolean canGetInstantaneousLocks() {
      return false;
   }

   public boolean isForUpdate() {
      return this.forUpdate;
   }

   public Object clone() {
      Object var1 = null;

      try {
         var1 = super.clone();
      } catch (CloneNotSupportedException var3) {
      }

      return var1;
   }
}
