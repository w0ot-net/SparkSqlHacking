package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.TemporaryRowHolder;
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

class DependentResultSet extends ScanResultSet implements CursorResultSet {
   ConglomerateController heapCC;
   RowLocation baseRowLocation;
   ExecRow indexRow;
   IndexRow indexQualifierRow;
   ScanController indexSC;
   StaticCompiledOpenConglomInfo indexScoci;
   DynamicCompiledOpenConglomInfo indexDcoci;
   int numFkColumns;
   boolean isOpen;
   TemporaryRowHolderResultSet source;
   TransactionController tc;
   String parentResultSetId;
   int[] fkColArray;
   RowLocation rowLocation;
   TemporaryRowHolder[] sourceRowHolders;
   TemporaryRowHolderResultSet[] sourceResultSets;
   int[] sourceOpened;
   int sArrayIndex;
   Vector sVector;
   protected ScanController scanController;
   protected boolean scanControllerOpened;
   protected boolean isKeyed;
   protected boolean firstScan = true;
   protected ExecIndexRow startPosition;
   protected ExecIndexRow stopPosition;
   protected long conglomId;
   protected DynamicCompiledOpenConglomInfo heapDcoci;
   protected StaticCompiledOpenConglomInfo heapScoci;
   protected int startSearchOperator;
   protected int stopSearchOperator;
   protected Qualifier[][] qualifiers;
   public String userSuppliedOptimizerOverrides;
   protected boolean runTimeStatisticsOn;
   public int rowsPerRead;
   public boolean forUpdate;
   private Properties scanProperties;
   public String startPositionString;
   public String stopPositionString;
   public boolean isConstraint;
   public boolean coarserLock;
   public boolean oneRowScan;
   protected long rowsThisScan;
   ExecRow searchRow = null;

   DependentResultSet(long var1, StaticCompiledOpenConglomInfo var3, Activation var4, int var5, int var6, GeneratedMethod var7, int var8, GeneratedMethod var9, int var10, boolean var11, Qualifier[][] var12, String var13, String var14, String var15, boolean var16, boolean var17, int var18, int var19, boolean var20, int var21, int var22, boolean var23, double var24, double var26, String var28, long var29, int var31, int var32) throws StandardException {
      super(var4, var6, var5, var19, var20, 4, var18, var24, var26);
      this.conglomId = var1;
      this.heapScoci = var3;
      this.heapDcoci = var4.getTransactionController().getDynamicCompiledConglomInfo(var1);
      this.startSearchOperator = var8;
      this.stopSearchOperator = var10;
      this.qualifiers = var12;
      this.tableName = var13;
      this.userSuppliedOptimizerOverrides = var14;
      this.indexName = "On Foreign Key";
      this.isConstraint = var16;
      this.forUpdate = var17;
      this.rowsPerRead = var22;
      this.oneRowScan = var23;
      this.runTimeStatisticsOn = var4.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.tc = var4.getTransactionController();
      this.indexDcoci = this.tc.getDynamicCompiledConglomInfo(var29);
      this.indexScoci = this.tc.getStaticCompiledConglomInfo(var29);
      this.parentResultSetId = var28;
      this.fkColArray = (int[])var4.getPreparedStatement().getSavedObject(var31);
      this.rowLocation = (RowLocation)var4.getPreparedStatement().getSavedObject(var32);
      this.numFkColumns = this.fkColArray.length;
      this.indexQualifierRow = new IndexRow(this.numFkColumns);
      this.recordConstructorTime();
   }

   private ScanController openIndexScanController(ExecRow var1) throws StandardException {
      this.setupQualifierRow(var1);
      this.indexSC = this.tc.openCompiledScan(false, 4, this.lockMode, this.isolationLevel, (FormatableBitSet)null, this.indexQualifierRow.getRowArray(), 1, (Qualifier[][])null, this.indexQualifierRow.getRowArray(), -1, this.indexScoci, this.indexDcoci);
      return this.indexSC;
   }

   private void reopenIndexScanController(ExecRow var1) throws StandardException {
      this.setupQualifierRow(var1);
      this.indexSC.reopenScan(this.indexQualifierRow.getRowArray(), 1, (Qualifier[][])null, this.indexQualifierRow.getRowArray(), -1);
   }

   private void setupQualifierRow(ExecRow var1) {
      DataValueDescriptor[] var2 = this.indexQualifierRow.getRowArray();
      DataValueDescriptor[] var3 = var1.getRowArray();

      for(int var4 = 0; var4 < this.numFkColumns; ++var4) {
         var2[var4] = var3[this.fkColArray[var4] - 1];
      }

   }

   private void openIndexScan(ExecRow var1) throws StandardException {
      if (this.indexSC == null) {
         this.indexSC = this.openIndexScanController(var1);
         this.indexRow = this.indexQualifierRow.getClone();
         this.indexRow.setColumn(this.numFkColumns + 1, this.rowLocation.cloneValue(false));
      } else {
         this.reopenIndexScanController(var1);
      }

   }

   private ExecRow fetchIndexRow() throws StandardException {
      return !this.indexSC.fetchNext(this.indexRow.getRowArray()) ? null : this.indexRow;
   }

   private ExecRow fetchBaseRow() throws StandardException {
      if (this.currentRow == null) {
         this.currentRow = this.getCompactRow(this.candidate, this.accessedCols, this.isKeyed);
      }

      this.baseRowLocation = (RowLocation)this.indexRow.getColumn(this.indexRow.getRowArray().length);
      boolean var1 = this.heapCC.fetch(this.baseRowLocation, this.candidate.getRowArray(), this.accessedCols);
      return this.currentRow;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (this.searchRow == null && (this.searchRow = this.getNextParentRow()) != null) {
            this.openIndexScan(this.searchRow);
         }

         ExecRow var1 = null;

         while(this.searchRow != null) {
            var1 = this.fetchIndexRow();
            if (var1 != null) {
               break;
            }

            if ((this.searchRow = this.getNextParentRow()) != null) {
               this.openIndexScan(this.searchRow);
            }
         }

         this.nextTime += this.getElapsedMillis(this.beginTime);
         if (var1 != null) {
            ++this.rowsSeen;
            return this.fetchBaseRow();
         } else {
            return var1;
         }
      }
   }

   private ExecRow getNextParentRow() throws StandardException {
      if (this.sourceOpened[this.sArrayIndex] == 0) {
         TemporaryRowHolder var2 = this.sourceRowHolders[this.sArrayIndex];
         this.source = (TemporaryRowHolderResultSet)var2.getResultSet();
         this.source.open();
         this.sourceOpened[this.sArrayIndex] = -1;
         this.sourceResultSets[this.sArrayIndex] = this.source;
      }

      if (this.sourceOpened[this.sArrayIndex] == 1) {
         this.source = this.sourceResultSets[this.sArrayIndex];
         this.source.reStartScan(this.sourceRowHolders[this.sArrayIndex].getTemporaryConglomId(), this.sourceRowHolders[this.sArrayIndex].getPositionIndexConglomId());
         this.sourceOpened[this.sArrayIndex] = -1;
      }

      if (this.sVector.size() > this.sourceRowHolders.length) {
         this.addNewSources();
      }

      ExecRow var1;
      for(var1 = this.source.getNextRow(); var1 == null && this.sArrayIndex + 1 < this.sourceRowHolders.length; var1 = this.source.getNextRow()) {
         ++this.sArrayIndex;
         if (this.sourceOpened[this.sArrayIndex] == 0) {
            TemporaryRowHolder var4 = this.sourceRowHolders[this.sArrayIndex];
            this.source = (TemporaryRowHolderResultSet)var4.getResultSet();
            this.source.open();
            this.sourceOpened[this.sArrayIndex] = -1;
            this.sourceResultSets[this.sArrayIndex] = this.source;
         }

         if (this.sourceOpened[this.sArrayIndex] == 1) {
            this.source = this.sourceResultSets[this.sArrayIndex];
            this.source.reStartScan(this.sourceRowHolders[this.sArrayIndex].getTemporaryConglomId(), this.sourceRowHolders[this.sArrayIndex].getPositionIndexConglomId());
            this.sourceOpened[this.sArrayIndex] = -1;
         }
      }

      if (var1 == null) {
         this.sArrayIndex = 0;

         for(int var3 = 0; var3 < this.sourceOpened.length; ++var3) {
            this.sourceOpened[var3] = 1;
         }
      }

      return var1;
   }

   public ConglomerateController openHeapConglomerateController() throws StandardException {
      return this.tc.openCompiledConglomerate(false, 4, this.lockMode, this.isolationLevel, this.heapScoci, this.heapDcoci);
   }

   public void close() throws StandardException {
      if (this.runTimeStatisticsOn) {
         this.startPositionString = this.printStartPosition();
         this.stopPositionString = this.printStopPosition();
         this.scanProperties = this.getScanProperties();
      }

      if (this.indexSC != null) {
         this.indexSC.close();
         this.indexSC = null;
      }

      if (this.heapCC != null) {
         this.heapCC.close();
         this.heapCC = null;
      }

      if (this.isOpen) {
         this.source.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      if (this.source != null) {
         this.source.finish();
      }

      this.finishAndRTS();
   }

   public void openCore() throws StandardException {
      this.initIsolationLevel();
      this.sVector = this.activation.getParentResultSet(this.parentResultSetId);
      int var1 = this.sVector.size();
      this.sourceRowHolders = new TemporaryRowHolder[var1];
      this.sourceOpened = new int[var1];
      this.sourceResultSets = new TemporaryRowHolderResultSet[var1];

      for(int var2 = 0; var2 < var1; ++var2) {
         this.sourceRowHolders[var2] = (TemporaryRowHolder)this.sVector.elementAt(var2);
         this.sourceOpened[var2] = 0;
      }

      this.heapCC = this.openHeapConglomerateController();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private void addNewSources() {
      int var1 = this.sVector.size();
      TemporaryRowHolder[] var2 = new TemporaryRowHolder[var1];
      int[] var3 = new int[var1];
      TemporaryRowHolderResultSet[] var4 = new TemporaryRowHolderResultSet[var1];
      System.arraycopy(this.sourceRowHolders, 0, var2, 0, this.sourceRowHolders.length);
      System.arraycopy(this.sourceOpened, 0, var3, 0, this.sourceOpened.length);
      System.arraycopy(this.sourceResultSets, 0, var4, 0, this.sourceResultSets.length);

      for(int var5 = this.sourceRowHolders.length; var5 < var1; ++var5) {
         var2[var5] = (TemporaryRowHolder)this.sVector.elementAt(var5);
         var3[var5] = 0;
      }

      this.sourceRowHolders = var2;
      this.sourceOpened = var3;
      this.sourceResultSets = var4;
   }

   boolean canGetInstantaneousLocks() {
      return false;
   }

   public long getTimeSpent(int var1) {
      return this.constructorTime + this.openTime + this.nextTime + this.closeTime;
   }

   public RowLocation getRowLocation() throws StandardException {
      return this.baseRowLocation;
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.currentRow;
   }

   public Properties getScanProperties() {
      if (this.scanProperties == null) {
         this.scanProperties = new Properties();
      }

      try {
         if (this.indexSC != null) {
            this.indexSC.getScanInfo().getAllScanInfo(this.scanProperties);
            this.coarserLock = this.indexSC.isTableLocked() && this.lockMode == 6;
         }
      } catch (StandardException var2) {
      }

      return this.scanProperties;
   }

   public String printStartPosition() {
      return this.printPosition(1, this.indexQualifierRow);
   }

   public String printStopPosition() {
      return this.printPosition(-1, this.indexQualifierRow);
   }

   private String printPosition(int var1, ExecIndexRow var2) {
      String var3 = "";
      String var4;
      switch (var1) {
         case -1 -> var4 = ">";
         case 1 -> var4 = ">=";
         default -> var4 = "unknown value (" + var1 + ")";
      }

      if (var2 != null) {
         var3 = var3 + "\t" + MessageService.getTextMessage("42Z40.U", new Object[]{var4, String.valueOf(var2.nColumns())}) + "\n";
         var3 = var3 + "\t" + MessageService.getTextMessage("42Z41.U", new Object[0]) + "\n";
         boolean var5 = false;

         for(int var6 = 0; var6 < var2.nColumns(); ++var6) {
            if (var2.areNullsOrdered(var6)) {
               var3 = var3 + var6 + " ";
               var5 = true;
            }

            if (var5 && var6 == var2.nColumns() - 1) {
               var3 = var3 + "\n";
            }
         }
      }

      return var3;
   }

   public String printQualifiers() {
      String var1 = "";
      return var1 + MessageService.getTextMessage("42Z37.U", new Object[0]);
   }
}
