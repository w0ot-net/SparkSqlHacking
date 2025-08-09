package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.BooleanDataValue;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLRef;
import org.apache.derby.shared.common.error.StandardException;

class UpdateResultSet extends DMLWriteGeneratedColumnsResultSet {
   private TransactionController tc;
   private ExecRow newBaseRow;
   private ExecRow row;
   private ExecRow deferredSparseRow;
   UpdateConstantAction constants;
   NoPutResultSet savedSource;
   private RowChanger rowChanger;
   protected ConglomerateController deferredBaseCC;
   protected long[] deferredUniqueCIDs;
   protected boolean[] deferredUniqueCreated;
   protected ConglomerateController[] deferredUniqueCC;
   protected ScanController[] deferredUniqueScans;
   private TemporaryRowHolderImpl deletedRowHolder;
   private TemporaryRowHolderImpl insertedRowHolder;
   private RISetChecker riChecker;
   private TriggerInfo triggerInfo;
   private TriggerEventActivator triggerActivator;
   private boolean updatingReferencedKey;
   private boolean updatingForeignKey;
   private int numOpens;
   private long heapConglom;
   private FKInfo[] fkInfoArray;
   private FormatableBitSet baseRowReadList;
   private GeneratedMethod generationClauses;
   private GeneratedMethod checkGM;
   private int resultWidth;
   private int numberOfBaseColumns;
   private ExecRow deferredTempRow;
   private ExecRow deferredBaseRow;
   private ExecRow oldDeletedRow;
   private ResultDescription triggerResultDescription;
   int lockMode;
   boolean deferred;
   boolean beforeUpdateCopyRequired;
   private List violatingCheckConstraints;
   private BackingStoreHashtable deferredChecks;

   UpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, Activation var4) throws StandardException {
      this(var1, var2, var3, var4, var4.getConstantAction(), (ResultDescription)null);
   }

   UpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, Activation var4, int var5, int var6) throws StandardException {
      this(var1, var2, var3, var4, (ConstantAction)var4.getPreparedStatement().getSavedObject(var5), (ResultDescription)var4.getPreparedStatement().getSavedObject(var6));
      this.deferred = true;
   }

   UpdateResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, Activation var4, ConstantAction var5, ResultDescription var6) throws StandardException {
      super(var4, var5);
      this.beforeUpdateCopyRequired = false;
      this.tc = var4.getTransactionController();
      this.sourceResultSet = var1;
      this.generationClauses = var2;
      this.checkGM = var3;
      this.constants = (UpdateConstantAction)this.constantAction;
      this.fkInfoArray = this.constants.getFKInfo();
      this.triggerInfo = this.constants.getTriggerInfo();
      this.heapConglom = this.constants.conglomId;
      this.baseRowReadList = this.constants.getBaseRowReadList();
      if (var6 == null) {
         this.resultDescription = var1.getResultDescription();
      } else {
         this.resultDescription = var6;
      }

      if (this.fkInfoArray != null) {
         for(FKInfo var10 : this.fkInfoArray) {
            if (var10.type == 2) {
               this.updatingReferencedKey = true;
            } else {
               this.updatingForeignKey = true;
            }
         }
      }

      this.resultWidth = this.resultDescription.getColumnCount();
      this.numberOfBaseColumns = (this.resultWidth - 1) / 2;
      this.newBaseRow = RowUtil.getEmptyValueRow(this.numberOfBaseColumns, this.lcc);
      this.deferred = this.constants.deferred;
      if (this.triggerInfo != null || this.fkInfoArray != null) {
         this.beforeUpdateCopyRequired = true;
      }

      this.identitySequenceUUIDString = this.constants.identitySequenceUUIDString;
      this.initializeAIcache(this.constants.getAutoincRowLocation());
   }

   public void open() throws StandardException {
      this.setup();
      this.autoincrementGenerated = false;
      this.collectAffectedRows();
      if (this.deferred) {
         this.runChecker(true);
         this.fireBeforeTriggers();
         this.updateDeferredRows();
         this.rowChanger.finish();
         this.runChecker(false);
         this.fireAfterTriggers();
      } else {
         this.rowChanger.finish();
      }

      this.saveAIcacheInformation(this.constants.getSchemaName(), this.constants.getTableName(), this.constants.getColumnNames());
      this.cleanUp();
   }

   void setup() throws StandardException {
      super.setup();
      this.lockMode = this.decodeLockMode(this.constants.lockMode);
      boolean var1 = this.rowChanger == null;
      this.rowCount = 0L;
      if (this.lcc.getRunTimeStatisticsMode()) {
         this.savedSource = this.sourceResultSet;
      }

      if (var1) {
         this.rowChanger = this.lcc.getLanguageConnectionFactory().getExecutionFactory().getRowChanger(this.heapConglom, this.constants.heapSCOCI, this.heapDCOCI, this.constants.irgs, this.constants.indexCIDS, this.constants.indexSCOCIs, this.indexDCOCIs, this.constants.numColumns, this.tc, this.constants.changedColumnIds, this.constants.getBaseRowReadList(), this.constants.getBaseRowReadMap(), this.constants.getStreamStorableHeapColIds(), this.activation);
         this.rowChanger.setIndexNames(this.constants.indexNames);
      }

      this.verifyAutoGeneratedRScolumnsList(this.constants.targetUUID);
      this.rowChanger.open(this.lockMode);
      if (this.numOpens++ == 0) {
         this.sourceResultSet.openCore();
      } else {
         this.sourceResultSet.reopenCore();
      }

      if (this.deferred) {
         this.activation.clearIndexScanInfo();
      }

      if (this.fkInfoArray != null) {
         if (this.riChecker == null) {
            this.riChecker = new RISetChecker(this.lcc, this.tc, this.fkInfoArray);
         } else {
            this.riChecker.reopen();
         }
      }

      if (this.deferred) {
         if (var1) {
            this.deferredTempRow = RowUtil.getEmptyValueRow(this.numberOfBaseColumns + 1, this.lcc);
            this.oldDeletedRow = RowUtil.getEmptyValueRow(this.numberOfBaseColumns, this.lcc);
            this.triggerResultDescription = this.resultDescription != null ? this.resultDescription.truncateColumns(this.numberOfBaseColumns + 1) : null;
         }

         Properties var2 = new Properties();
         this.rowChanger.getHeapConglomerateController().getInternalTablePropertySet(var2);
         if (this.beforeUpdateCopyRequired) {
            this.deletedRowHolder = new TemporaryRowHolderImpl(this.activation, var2, this.triggerResultDescription);
         }

         this.insertedRowHolder = new TemporaryRowHolderImpl(this.activation, var2, this.triggerResultDescription);
         this.rowChanger.setRowHolder(this.insertedRowHolder);
      }

      this.firstExecuteSpecialHandlingAutoGen(var1, this.rowChanger, this.constants.targetUUID);
   }

   private FormatableBitSet checkStreamCols() {
      DataValueDescriptor[] var1 = this.row.getRowArray();
      FormatableBitSet var2 = null;

      for(int var3 = 0; var3 < this.numberOfBaseColumns; ++var3) {
         if (var1[var3 + this.numberOfBaseColumns] instanceof StreamStorable) {
            if (var2 == null) {
               var2 = new FormatableBitSet(this.numberOfBaseColumns);
            }

            var2.set(var3);
         }
      }

      return var2;
   }

   private void objectifyStream(ExecRow var1, FormatableBitSet var2) throws StandardException {
      DataValueDescriptor[] var3 = var1.getRowArray();

      for(int var4 = 0; var4 < this.numberOfBaseColumns; ++var4) {
         if (var3[var4] != null && var2.get(var4)) {
            ((StreamStorable)var3[var4]).loadStream();
         }
      }

   }

   private boolean evaluateCheckConstraints() throws StandardException {
      boolean var1 = true;
      if (this.checkGM != null) {
         SQLBoolean var2 = (SQLBoolean)this.checkGM.invoke(this.activation);
         var1 = var2.isNull() || var2.getBoolean();
      }

      return var1;
   }

   public boolean collectAffectedRows() throws StandardException {
      boolean var1 = false;
      this.row = this.getNextRowCore(this.sourceResultSet);
      if (this.row != null) {
         var1 = true;
      } else {
         this.activation.addWarning(StandardException.newWarning("02000", new Object[0]));
      }

      TableScanResultSet var2 = (TableScanResultSet)this.activation.getForUpdateIndexScan();
      boolean var3 = var2 != null;
      boolean var4 = this.deferred && var1 && !this.constants.singleRowSource;
      FormatableBitSet var5 = var4 ? this.checkStreamCols() : null;
      var4 = var5 != null;

      while(this.row != null) {
         this.evaluateGenerationClauses(this.generationClauses, this.activation, this.sourceResultSet, this.row, true);
         if (this.deferred) {
            if (this.triggerInfo == null) {
               boolean var6 = this.evaluateCheckConstraints();
               if (!var6) {
                  DataValueDescriptor[] var7 = this.row.getRowArray();
                  SQLRef var8 = (SQLRef)var7[var7.length - 1];
                  RowLocation var9 = (RowLocation)var8.getObject();
                  this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(this.lcc, this.constants.targetUUID, this.constants.getSchemaName(), this.constants.getTableName(), this.deferredChecks, this.violatingCheckConstraints, var9, new DeferredConstraintsMemory.CheckInfo[1]);
               }
            }

            RowUtil.copyRefColumns(this.deferredTempRow, this.row, this.numberOfBaseColumns, this.numberOfBaseColumns + 1);
            if (var4) {
               this.objectifyStream(this.deferredTempRow, var5);
            }

            this.insertedRowHolder.insert(this.deferredTempRow);
            if (this.beforeUpdateCopyRequired) {
               RowUtil.copyRefColumns(this.oldDeletedRow, this.row, this.numberOfBaseColumns);
               this.deletedRowHolder.insert(this.oldDeletedRow);
            }

            if (this.deferredBaseRow == null) {
               this.deferredBaseRow = RowUtil.getEmptyValueRow(this.numberOfBaseColumns, this.lcc);
               RowUtil.copyCloneColumns(this.deferredBaseRow, this.row, this.numberOfBaseColumns);
               this.deferredSparseRow = this.makeDeferredSparseRow(this.deferredBaseRow, this.baseRowReadList, this.lcc);
            }
         } else {
            boolean var11 = this.evaluateCheckConstraints();
            RowLocation var12 = (RowLocation)this.row.getColumn(this.resultWidth).getObject();
            if (!var11) {
               this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(this.lcc, this.constants.targetUUID, this.constants.getSchemaName(), this.constants.getTableName(), this.deferredChecks, this.violatingCheckConstraints, var12, new DeferredConstraintsMemory.CheckInfo[1]);
            }

            RowUtil.copyRefColumns(this.newBaseRow, this.row, this.numberOfBaseColumns, this.numberOfBaseColumns);
            if (this.riChecker != null) {
               this.riChecker.doFKCheck(this.activation, this.newBaseRow);
            }

            this.sourceResultSet.updateRow(this.newBaseRow, this.rowChanger);
            this.rowChanger.updateRow(this.row, this.newBaseRow, var12);
            if (var3) {
               this.notifyForUpdateCursor(this.row.getRowArray(), this.newBaseRow.getRowArray(), var12, var2);
            }
         }

         ++this.rowCount;
         if (this.constants.singleRowSource) {
            this.row = null;
         } else {
            this.row = this.getNextRowCore(this.sourceResultSet);
         }
      }

      if (this.rowCount == 1L && this.constants.hasAutoincrement()) {
         this.lcc.setIdentityValue(this.identityVal);
      }

      return var1;
   }

   protected ExecRow getNextRowCore(NoPutResultSet var1) throws StandardException {
      ExecRow var2 = super.getNextRowCore(var1);
      if (var2 != null && this.constants.underMerge()) {
         var2 = this.processMergeRow(var1, var2);
      }

      return var2;
   }

   private ExecRow processMergeRow(NoPutResultSet var1, ExecRow var2) throws StandardException {
      return this.normalizeRow(var1, var2);
   }

   private void notifyForUpdateCursor(DataValueDescriptor[] var1, DataValueDescriptor[] var2, RowLocation var3, TableScanResultSet var4) throws StandardException {
      int[] var5 = var4.indexCols;
      int[] var6 = this.constants.changedColumnIds;
      boolean var7 = false;
      boolean var9 = false;
      boolean var10 = false;

      for(int var13 = 0; var13 < var5.length; ++var13) {
         int var11 = var5[var13];
         boolean var8;
         if (var11 > 0) {
            var8 = true;
         } else {
            var8 = false;
            var11 = -var11;
         }

         for(int var14 = 0; var14 < var6.length; ++var14) {
            if (var11 == var6[var14]) {
               var9 = true;
               int[] var15 = this.constants.getBaseRowReadMap();
               int var12;
               if (var15 == null) {
                  var12 = var11 - 1;
               } else {
                  var12 = var15[var11 - 1];
               }

               DataValueDescriptor var16 = var1[var12];
               if ((!var8 || !var16.greaterThan(var2[var12], var16).equals(true)) && (var8 || !var16.lessThan(var2[var12], var16).equals(true))) {
                  if (var16.equals(var2[var12], var16).equals(true)) {
                     var9 = false;
                     var10 = true;
                  }
               } else {
                  var7 = true;
               }
               break;
            }
         }

         if (var9) {
            break;
         }
      }

      if (var10 && !var9) {
         var7 = true;
      }

      if (var7) {
         int var17 = this.lcc.getOptimizerFactory().getMaxMemoryPerTable() / 16;
         if (var17 < 100) {
            var17 = 100;
         }

         if (var4.past2FutureTbl == null) {
            double var18 = var4.getEstimatedRowCount();
            int var20 = 32768;
            if (var18 > (double)0.0F) {
               var18 = var18 / (double)0.75F + (double)1.0F;
               if (var18 < (double)var20) {
                  var20 = (int)var18;
               }
            }

            if (var17 < var20) {
               var20 = var17;
            }

            var4.past2FutureTbl = new BackingStoreHashtable(this.tc, (RowSource)null, new int[]{0}, false, -1L, (long)var17, var20, -1.0F, false, var4.getActivation().getResultSetHoldability());
         }

         var4.past2FutureTbl.putRow(false, new DataValueDescriptor[]{var3.cloneValue(false)}, (RowLocation)null);
      }

   }

   void fireBeforeTriggers() throws StandardException {
      if (this.deferred && this.triggerInfo != null) {
         Vector var1 = null;
         if (this.aiCache != null) {
            var1 = new Vector();

            for(int var2 = 0; var2 < this.aiCache.length; ++var2) {
               if (this.aiCache[var2] != null) {
                  String var3;
                  String var4;
                  String var5;
                  Long var6 = this.lcc.lastAutoincrementValue(var3 = this.constants.getSchemaName(), var4 = this.constants.getTableName(), var5 = this.constants.getColumnName(var2));
                  AutoincrementCounter var7 = new AutoincrementCounter(var6, this.constants.getAutoincIncrement(var2), this.aiCache[var2].getLong(), var3, var4, var5, var2 + 1);
                  var1.addElement(var7);
               }
            }
         }

         if (this.triggerActivator == null) {
            this.triggerActivator = new TriggerEventActivator(this.lcc, this.constants.targetUUID, this.triggerInfo, 1, this.activation, var1);
         } else {
            this.triggerActivator.reopen();
         }

         this.triggerActivator.notifyEvent(TriggerEvents.BEFORE_UPDATE, this.deletedRowHolder.getResultSet(), this.insertedRowHolder.getResultSet(), this.constants.getBaseRowReadMap());
      }

   }

   void fireAfterTriggers() throws StandardException {
      if (this.deferred && this.triggerActivator != null) {
         this.triggerActivator.notifyEvent(TriggerEvents.AFTER_UPDATE, this.deletedRowHolder.getResultSet(), this.insertedRowHolder.getResultSet(), this.constants.getBaseRowReadMap());
      }

   }

   void updateDeferredRows() throws StandardException {
      if (this.deferred) {
         this.deferredBaseCC = this.tc.openCompiledConglomerate(false, 8196, this.lockMode, 5, this.constants.heapSCOCI, this.heapDCOCI);
         CursorResultSet var1 = this.insertedRowHolder.getResultSet();

         try {
            FormatableBitSet var2 = RowUtil.shift(this.baseRowReadList, 1);
            var1.open();

            ExecRow var3;
            while((var3 = var1.getNextRow()) != null) {
               boolean var4 = true;
               if (this.triggerInfo != null) {
                  this.sourceResultSet.setCurrentRow(this.deferredTempRow);
                  var4 = this.evaluateCheckConstraints();
               }

               DataValueDescriptor var5 = var3.getColumn(this.numberOfBaseColumns + 1);
               RowLocation var6 = (RowLocation)var5.getObject();
               if (!var4) {
                  this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(this.lcc, this.constants.targetUUID, this.constants.getSchemaName(), this.constants.getTableName(), this.deferredChecks, this.violatingCheckConstraints, var6, new DeferredConstraintsMemory.CheckInfo[1]);
               }

               this.deferredBaseCC.fetch(var6, this.deferredSparseRow.getRowArray(), var2);
               RowUtil.copyRefColumns(this.newBaseRow, var3, this.numberOfBaseColumns);
               this.rowChanger.updateRow(this.deferredBaseRow, this.newBaseRow, var6);
            }
         } finally {
            this.sourceResultSet.clearCurrentRow();
            var1.close();
         }
      }

   }

   void runChecker(boolean var1) throws StandardException {
      if (this.deferred && this.updatingReferencedKey) {
         for(int var4 = 0; var4 < this.fkInfoArray.length; ++var4) {
            if (this.fkInfoArray[var4].type != 1) {
               CursorResultSet var3 = this.deletedRowHolder.getResultSet();

               try {
                  var3.open();

                  ExecRow var2;
                  while((var2 = var3.getNextRow()) != null) {
                     if (!foundRow(var2, this.fkInfoArray[var4].colArray, this.insertedRowHolder)) {
                        this.riChecker.doRICheck(this.activation, var4, var2, var1, 1);
                     }
                  }

                  if (var1) {
                     this.riChecker.postCheck(var4);
                  }
               } finally {
                  var3.close();
               }
            }
         }
      }

      if (this.deferred && this.updatingForeignKey) {
         for(int var15 = 0; var15 < this.fkInfoArray.length; ++var15) {
            if (this.fkInfoArray[var15].type != 2) {
               CursorResultSet var14 = this.insertedRowHolder.getResultSet();

               try {
                  var14.open();

                  ExecRow var13;
                  while((var13 = var14.getNextRow()) != null) {
                     if (!foundRow(var13, this.fkInfoArray[var15].colArray, this.deletedRowHolder)) {
                        this.riChecker.doRICheck(this.activation, var15, var13, var1, 0);
                     }
                  }
               } finally {
                  var14.close();
               }
            }
         }
      }

   }

   public static boolean foundRow(ExecRow var0, int[] var1, TemporaryRowHolderImpl var2) throws StandardException {
      boolean var4 = false;
      DataValueDescriptor[] var5 = var0.getRowArray();
      CursorResultSet var8 = var2.getResultSet();

      try {
         var8.open();

         ExecRow var3;
         while((var3 = var8.getNextRow()) != null) {
            DataValueDescriptor[] var9 = var3.getRowArray();

            int var10;
            for(var10 = 0; var10 < var1.length; ++var10) {
               DataValueDescriptor var6 = var5[var1[var10] - 1];
               DataValueDescriptor var7 = var9[var1[var10] - 1];
               BooleanDataValue var11 = var6.equals(var7, var6);
               if (!var11.getBoolean()) {
                  break;
               }
            }

            if (var10 == var1.length) {
               var4 = true;
               break;
            }
         }
      } finally {
         var8.close();
      }

      return var4;
   }

   public void cleanUp() throws StandardException {
      this.numOpens = 0;
      if (this.sourceResultSet != null) {
         this.sourceResultSet.close();
      }

      if (this.triggerActivator != null) {
         this.triggerActivator.cleanup();
      }

      if (this.rowChanger != null) {
         this.rowChanger.close();
      }

      if (this.deferredBaseCC != null) {
         this.deferredBaseCC.close();
      }

      this.deferredBaseCC = null;
      if (this.insertedRowHolder != null) {
         this.insertedRowHolder.close();
      }

      if (this.deletedRowHolder != null) {
         this.deletedRowHolder.close();
      }

      if (this.riChecker != null) {
         this.riChecker.close();
      }

      this.close();
      this.endTime = this.getCurrentTimeMillis();
   }

   public void close() throws StandardException {
      super.close(this.constants.underMerge());
   }

   void rowChangerFinish() throws StandardException {
      this.rowChanger.finish();
   }

   public void rememberConstraint(UUID var1) throws StandardException {
      if (this.violatingCheckConstraints == null) {
         this.violatingCheckConstraints = new ArrayList();
      }

      this.violatingCheckConstraints.add(var1);
   }

   public NumberDataValue getSetAutoincrementValue(int var1, long var2) throws StandardException {
      this.autoincrementGenerated = true;
      int var4 = var1 - 1;
      NumberDataValue var5 = this.activation.getCurrentValueAndAdvance(this.identitySequenceUUIDString, this.aiCache[var4].getTypeFormatId());
      this.aiCache[var4] = var5;
      this.identityVal = var5.getLong();
      return (NumberDataValue)this.aiCache[var4];
   }

   protected void initializeAIcache(RowLocation[] var1) throws StandardException {
      if (var1 != null) {
         this.aiCache = new DataValueDescriptor[var1.length];
         ColumnDescriptorList var2 = this.lcc.getDataDictionary().getTableDescriptor(this.constants.targetUUID).getColumnDescriptorList();

         for(int var3 = 0; var3 < var2.size(); ++var3) {
            if (var1[var3] != null) {
               this.aiCache[var3] = var2.elementAt(var3).getType().getNull();
            }
         }
      }

   }
}
