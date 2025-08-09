package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.StatisticsImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.StatementUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.BulkInsertCounter;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.TargetResultSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class InsertResultSet extends DMLWriteGeneratedColumnsResultSet implements TargetResultSet {
   NoPutResultSet savedSource;
   InsertConstantAction constants;
   private GeneratedMethod generationClauses;
   private GeneratedMethod checkGM;
   private long heapConglom;
   private RowChanger rowChanger;
   private TransactionController tc;
   private ExecRow row;
   boolean userSpecifiedBulkInsert;
   boolean bulkInsertPerformed;
   protected boolean bulkInsert;
   private boolean bulkInsertReplace;
   private boolean firstRow = true;
   private boolean[] needToDropSort;
   private Hashtable indexConversionTable;
   private FormatableBitSet indexedCols;
   private ConglomerateController bulkHeapCC;
   protected DataDictionary dd;
   protected TableDescriptor td;
   private ExecIndexRow[] indexRows;
   private final int fullTemplateId;
   private final String schemaName;
   private final String tableName;
   private long[] sortIds;
   private RowLocationRetRowSource[] rowSources;
   private ScanController bulkHeapSC;
   private ColumnOrdering[][] ordering;
   private int[][] collation;
   private SortController[] sorters;
   private TemporaryRowHolderImpl rowHolder;
   private RowLocation rl;
   private boolean hasBeforeRowTrigger;
   private BulkTableScanResultSet tableScan;
   private int numOpens;
   private boolean firstExecute;
   private FKInfo[] fkInfoArray;
   private TriggerInfo triggerInfo;
   private RISetChecker fkChecker;
   private TriggerEventActivator triggerActivator;
   private BulkInsertCounter[] bulkInsertCounters;
   private BackingStoreHashtable deferredChecks;
   private List violatingCheckConstraints;

   public void changedRow(ExecRow var1, RowLocation var2) throws StandardException {
      if (this.constants.irgs.length > 0) {
         RowLocation var3 = (RowLocation)var2.cloneValue(false);

         for(int var4 = 0; var4 < var1.getRowArray().length; ++var4) {
            if (this.constants.indexedCols[var4] && var1.getRowArray()[var4] instanceof StreamStorable) {
               var1.getRowArray()[var4].getObject();
            }
         }

         if (this.firstRow) {
            this.firstRow = false;
            this.indexRows = new ExecIndexRow[this.constants.irgs.length];
            this.setUpAllSorts(var1.getNewNullRow(), var3);
         }

         for(int var5 = 0; var5 < this.constants.irgs.length; ++var5) {
            this.indexRows[var5].getNewObjectArray();
            this.constants.irgs[var5].getIndexRow(var1, var3, this.indexRows[var5], (FormatableBitSet)null);
            this.sorters[var5].insert(this.indexRows[var5].getRowArray());
         }
      }

   }

   public ExecRow preprocessSourceRow(ExecRow var1) throws StandardException {
      if (this.triggerInfo != null) {
      }

      if (this.generationClauses != null) {
         this.evaluateGenerationClauses(this.generationClauses, this.activation, this.sourceResultSet, var1, false);
      }

      if (this.checkGM != null) {
         boolean var2 = this.evaluateCheckConstraints();
         if (!var2) {
         }
      }

      return this.constants.irgs.length > 0 ? var1.getClone(this.indexedCols) : var1;
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
      if (this.violatingCheckConstraints != null) {
         this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(this.lcc, this.constants.targetUUID, this.schemaName, this.tableName, this.deferredChecks, this.violatingCheckConstraints, var1, new DeferredConstraintsMemory.CheckInfo[1]);
         this.violatingCheckConstraints.clear();
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

   InsertResultSet(NoPutResultSet var1, GeneratedMethod var2, GeneratedMethod var3, int var4, String var5, String var6, Activation var7) throws StandardException {
      super(var7);
      this.sourceResultSet = var1;
      this.constants = (InsertConstantAction)this.constantAction;
      this.generationClauses = var2;
      this.checkGM = var3;
      this.fullTemplateId = var4;
      this.schemaName = var5;
      this.tableName = var6;
      this.heapConglom = this.constants.conglomId;
      this.identitySequenceUUIDString = this.constants.identitySequenceUUIDString;
      this.tc = var7.getTransactionController();
      this.fkInfoArray = this.constants.getFKInfo();
      this.triggerInfo = this.constants.getTriggerInfo();
      this.hasBeforeRowTrigger = this.triggerInfo != null ? this.triggerInfo.hasTrigger(true, true) : false;
      this.resultDescription = this.sourceResultSet.getResultDescription();
      String var8 = this.constants.getProperty("insertMode");
      this.initializeAIcache(this.constants.getAutoincRowLocation());
      if (var8 != null) {
         if (StringUtil.SQLEqualsIgnoreCase(var8, "BULKINSERT")) {
            this.userSpecifiedBulkInsert = true;
         } else if (StringUtil.SQLEqualsIgnoreCase(var8, "REPLACE")) {
            this.userSpecifiedBulkInsert = true;
            this.bulkInsertReplace = true;
            this.bulkInsert = true;
            if (this.triggerInfo != null) {
               TriggerDescriptor var9 = this.triggerInfo.getTriggerArray()[0];
               throw StandardException.newException("X0Y72.S", new Object[]{this.constants.getTableName(), var9.getName()});
            }
         }
      }

   }

   public void open() throws StandardException {
      this.setup();
      this.firstExecute = this.rowChanger == null;
      this.autoincrementGenerated = false;
      this.dd = this.lcc.getDataDictionary();
      this.verifyAutoGeneratedRScolumnsList(this.constants.targetUUID);
      this.rowCount = 0L;
      if (this.numOpens++ == 0) {
         this.sourceResultSet.openCore();
      } else {
         this.sourceResultSet.reopenCore();
      }

      if (this.userSpecifiedBulkInsert) {
         if (!this.bulkInsertReplace) {
            this.bulkInsert = this.verifyBulkInsert();
         } else {
            this.getExclusiveTableLock();
         }
      }

      if (this.bulkInsert) {
         this.sourceResultSet.setTargetResultSet(this);
         ExecRow var1 = ((ExecRowBuilder)this.activation.getPreparedStatement().getSavedObject(this.fullTemplateId)).build(this.activation.getExecutionFactory());
         this.bulkInsertCore(this.lcc, var1, this.heapConglom);
         if (this.triggerInfo != null) {
         }

         this.bulkValidateForeignKeys(this.tc, this.lcc.getContextManager(), var1);
         this.bulkInsertPerformed = true;
      } else {
         this.row = this.getNextRowCore(this.sourceResultSet);
         this.normalInsertCore(this.lcc, this.firstExecute);
      }

      if (this.lcc.getRunTimeStatisticsMode()) {
         this.savedSource = this.sourceResultSet;
      }

      this.cleanUp();
      this.saveAIcacheInformation(this.constants.getSchemaName(), this.constants.getTableName(), this.constants.getColumnNames());
      this.endTime = this.getCurrentTimeMillis();
   }

   public void close() throws StandardException {
      this.close(this.constants.underMerge());
      if (this.autoGeneratedKeysRowsHolder != null) {
         this.autoGeneratedKeysRowsHolder.close();
      }

   }

   private int[] generatedColumnPositionsArray() throws StandardException {
      TableDescriptor var1 = this.dd.getTableDescriptor(this.constants.targetUUID);
      int var3 = var1.getMaxColumnID();
      int[] var4 = new int[var3];
      Arrays.fill(var4, -1);
      int var5 = 0;

      for(int var6 = 0; var6 < var3; ++var6) {
         ColumnDescriptor var2 = var1.getColumnDescriptor(var6 + 1);
         if (var2.isAutoincrement()) {
            ++var5;
            var4[var6] = var6 + 1;
         } else if (var2.getDefaultValue() != null || var2.getDefaultInfo() != null) {
            ++var5;
            var4[var6] = var6 + 1;
         }
      }

      int[] var9 = new int[var5];
      int var7 = 0;

      for(int var8 = 0; var7 < var3; ++var7) {
         if (var4[var7] != -1) {
            var9[var8++] = var4[var7];
         }
      }

      return var9;
   }

   public NumberDataValue getSetAutoincrementValue(int var1, long var2) throws StandardException {
      int var4 = var1 - 1;
      this.setIdentity = !this.autoincrementGenerated && this.isSourceRowResultSet();
      this.autoincrementGenerated = true;
      if (this.bulkInsert) {
         if (this.identitySequenceUUIDString == null) {
            this.getOldStyleBulkInsertValue(var4, var2);
         } else {
            if (this.bulkInsertCounters[var4] == null) {
               this.bulkInsertCounters[var4] = this.dd.getBulkInsertCounter(this.identitySequenceUUIDString, this.bulkInsertReplace);
            }

            this.bulkInsertCounters[var4].getCurrentValueAndAdvance((NumberDataValue)this.aiCache[var4]);
         }
      } else {
         NumberDataValue var5;
         if (this.identitySequenceUUIDString == null) {
            var5 = this.getOldStyleIdentityValue(var4);
         } else {
            var5 = this.activation.getCurrentValueAndAdvance(this.identitySequenceUUIDString, this.aiCache[var4].getTypeFormatId());
         }

         this.aiCache[var4] = var5;
         if (this.setIdentity) {
            this.identityVal = var5.getLong();
         }
      }

      return (NumberDataValue)this.aiCache[var4];
   }

   private void getOldStyleBulkInsertValue(int var1, long var2) throws StandardException {
      int var5 = var1 + 1;
      ColumnDescriptor var6 = this.td.getColumnDescriptor(var5);
      if (this.aiCache[var1].isNull()) {
         long var9;
         if (this.bulkInsertReplace) {
            var9 = var6.getAutoincStart();
         } else {
            NumberDataValue var4 = this.dd.getSetAutoincrementValue(this.constants.autoincRowLocation[var1], this.tc, false, (NumberDataValue)this.aiCache[var1], true);
            var9 = var4.getLong();
         }

         this.lcc.autoincrementCreateCounter(this.td.getSchemaName(), this.td.getName(), var6.getColumnName(), var9, var2, var5);
      }

      long var7 = this.lcc.nextAutoincrementValue(this.td.getSchemaName(), this.td.getName(), var6.getColumnName());
      this.aiCache[var5 - 1].setValue(var7);
   }

   private NumberDataValue getOldStyleIdentityValue(int var1) throws StandardException {
      TransactionController var3 = null;

      TransactionController var4;
      try {
         var3 = this.tc.startNestedUserTransaction(false, false);
         var4 = var3;
      } catch (StandardException var10) {
         var4 = this.tc;
      }

      NumberDataValue var2;
      try {
         var2 = this.dd.getSetAutoincrementValue(this.constants.autoincRowLocation[var1], var4, true, (NumberDataValue)this.aiCache[var1], var4 == this.tc);
      } catch (StandardException var11) {
         if (var4 == this.tc) {
            throw var11;
         }

         if (!var11.getMessageId().equals("40XL1") && !var11.isSelfDeadlock()) {
            if (var11.getMessageId().equals("22003")) {
               throw StandardException.newException("42Z24", var11, new Object[]{this.constants.getTableName(), this.constants.getColumnName(var1)});
            }

            throw var11;
         }

         var2 = this.dd.getSetAutoincrementValue(this.constants.autoincRowLocation[var1], this.tc, true, (NumberDataValue)this.aiCache[var1], true);
      } finally {
         if (var3 != null) {
            var3.commitNoSync(1);
            var3.destroy();
         }

      }

      return var2;
   }

   private boolean isSourceRowResultSet() {
      boolean var1 = false;
      if (this.sourceResultSet instanceof NormalizeResultSet) {
         var1 = ((NormalizeResultSet)this.sourceResultSet).source instanceof RowResultSet;
      }

      return var1;
   }

   private boolean isSingleRowResultSet() {
      boolean var1 = false;
      if (this.sourceResultSet instanceof RowResultSet) {
         var1 = true;
      } else if (this.sourceResultSet instanceof NormalizeResultSet) {
         var1 = ((NormalizeResultSet)this.sourceResultSet).source instanceof RowResultSet;
      }

      return var1;
   }

   private void normalInsertCore(LanguageConnectionContext var1, boolean var2) throws StandardException {
      boolean var3 = this.constants.hasAutoincrement() && this.isSingleRowResultSet();
      long var5 = 0L;
      if (var2) {
         this.rowChanger = var1.getLanguageConnectionFactory().getExecutionFactory().getRowChanger(this.heapConglom, this.constants.heapSCOCI, this.heapDCOCI, this.constants.irgs, this.constants.indexCIDS, this.constants.indexSCOCIs, this.indexDCOCIs, 0, this.tc, (int[])null, this.constants.getStreamStorableHeapColIds(), this.activation);
         this.rowChanger.setIndexNames(this.constants.indexNames);
      }

      int var7 = this.decodeLockMode(this.constants.lockMode);
      this.rowChanger.open(var7);
      if (this.constants.deferred) {
         this.activation.clearIndexScanInfo();
      }

      if (this.fkInfoArray != null) {
         if (this.fkChecker == null) {
            this.fkChecker = new RISetChecker(var1, this.tc, this.fkInfoArray);
         } else {
            this.fkChecker.reopen();
         }
      }

      if (var2 && this.constants.deferred) {
         Properties var8 = new Properties();
         this.rowChanger.getHeapConglomerateController().getInternalTablePropertySet(var8);
         this.rowHolder = new TemporaryRowHolderImpl(this.activation, var8, this.resultDescription);
         this.rowChanger.setRowHolder(this.rowHolder);
      }

      this.firstExecuteSpecialHandlingAutoGen(var2, this.rowChanger, this.constants.targetUUID);

      while(this.row != null) {
         if (this.activation.getAutoGeneratedKeysResultsetMode() && this.autoGeneratedKeysColumnIndexes.length > 0) {
            this.autoGeneratedKeysRowsHolder.insert(this.getCompactRow(this.row, this.autoGeneratedKeysColumnIndexes));
         }

         this.evaluateGenerationClauses(this.generationClauses, this.activation, this.sourceResultSet, this.row, false);
         if (this.constants.deferred) {
            this.rowHolder.insert(this.row);
         } else {
            boolean var24 = this.evaluateCheckConstraints();
            if (this.fkChecker != null) {
               this.fkChecker.doFKCheck(this.activation, this.row);
            }

            if (this.constants.irgs.length > 0) {
               DataValueDescriptor[] var9 = this.row.getRowArray();

               for(int var10 = 0; var10 < var9.length; ++var10) {
                  if (this.constants.indexedCols[var10] && var9[var10] instanceof StreamStorable) {
                     var9[var10].getObject();
                  }
               }
            }

            if (var24) {
               this.rowChanger.insertRow(this.row, false);
            } else {
               RowLocation var29 = this.rowChanger.insertRow(this.row, true);
               this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(var1, this.constants.targetUUID, this.schemaName, this.tableName, this.deferredChecks, this.violatingCheckConstraints, var29, new DeferredConstraintsMemory.CheckInfo[1]);
            }
         }

         ++this.rowCount;
         if (var3) {
            this.dd = var1.getDataDictionary();
            this.td = this.dd.getTableDescriptor(this.constants.targetUUID);
            int var25 = this.td.getMaxColumnID();

            int var30;
            for(var30 = 1; var30 <= var25; ++var30) {
               ColumnDescriptor var33 = this.td.getColumnDescriptor(var30);
               if (var33.isAutoincrement()) {
                  break;
               }
            }

            if (var30 <= var25) {
               DataValueDescriptor var34 = this.row.cloneColumn(var30);
               var5 = var34.getLong();
            }
         }

         if (this.constants.singleRowSource) {
            this.row = null;
         } else {
            this.row = this.getNextRowCore(this.sourceResultSet);
         }
      }

      if (this.constants.deferred) {
         if (this.triggerInfo != null) {
            Vector var26 = null;
            if (this.aiCache != null) {
               var26 = new Vector();

               for(int var31 = 0; var31 < this.aiCache.length; ++var31) {
                  if (this.aiCache[var31] != null) {
                     String var11;
                     String var12;
                     String var35;
                     Long var13 = var1.lastAutoincrementValue(var35 = this.constants.getSchemaName(), var11 = this.constants.getTableName(), var12 = this.constants.getColumnName(var31));
                     AutoincrementCounter var14 = new AutoincrementCounter(var13, this.constants.getAutoincIncrement(var31), this.aiCache[var31].getLong(), var35, var11, var12, var31 + 1);
                     var26.addElement(var14);
                  }
               }
            }

            if (this.triggerActivator == null) {
               this.triggerActivator = new TriggerEventActivator(var1, this.constants.targetUUID, this.triggerInfo, 3, this.activation, var26);
            } else {
               this.triggerActivator.reopen();
            }

            this.triggerActivator.notifyEvent(TriggerEvents.BEFORE_INSERT, (CursorResultSet)null, this.rowHolder.getResultSet(), (int[])null);
         }

         CursorResultSet var27 = this.rowHolder.getResultSet();

         try {
            var27.open();

            ExecRow var4;
            while((var4 = var27.getNextRow()) != null) {
               this.sourceResultSet.setCurrentRow(var4);
               boolean var32 = this.evaluateCheckConstraints();
               if (var32) {
                  this.rowChanger.insertRow(var4, false);
               } else {
                  RowLocation var36 = this.rowChanger.insertRow(var4, true);
                  this.deferredChecks = DeferredConstraintsMemory.rememberCheckViolations(var1, this.constants.targetUUID, this.schemaName, this.tableName, this.deferredChecks, this.violatingCheckConstraints, var36, new DeferredConstraintsMemory.CheckInfo[1]);
               }
            }
         } finally {
            this.sourceResultSet.clearCurrentRow();
            var27.close();
         }

         if (this.fkChecker != null) {
            var27 = this.rowHolder.getResultSet();

            try {
               var27.open();

               ExecRow var23;
               while((var23 = var27.getNextRow()) != null) {
                  this.fkChecker.doFKCheck(this.activation, var23);
               }
            } finally {
               var27.close();
            }
         }

         if (this.triggerActivator != null) {
            this.triggerActivator.notifyEvent(TriggerEvents.AFTER_INSERT, (CursorResultSet)null, this.rowHolder.getResultSet(), (int[])null);
         }
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

      if (this.fkChecker != null) {
         this.fkChecker.close();
         this.fkChecker = null;
      }

      if (this.setIdentity) {
         var1.setIdentityValue(this.identityVal);
      } else if (var3) {
         var1.setIdentityValue(var5);
      }

   }

   protected ExecRow getNextRowCore(NoPutResultSet var1) throws StandardException {
      ExecRow var2 = super.getNextRowCore(var1);
      if (var2 != null && this.constants.underMerge()) {
         var2 = this.processMergeRow(var1, var2);
      }

      return var2;
   }

   private ExecRow processMergeRow(NoPutResultSet var1, ExecRow var2) throws StandardException {
      if (this.constants.hasAutoincrement()) {
         int var3 = this.constants.getAutoGenColumn();
         long var4 = this.constants.getAutoincIncrement(var3);
         DataValueDescriptor var6 = var2.getColumn(var3 + 1);
         boolean var7 = var6 == null;
         if (var7) {
            NumberDataValue var8 = this.getSetAutoincrementValue(var3 + 1, var4);
            var2.setColumn(var3 + 1, var8);
         }
      }

      return this.normalizeRow(var1, var2);
   }

   private void bulkInsertCore(LanguageConnectionContext var1, ExecRow var2, long var3) throws StandardException {
      this.bulkHeapCC = this.tc.openCompiledConglomerate(false, 4, 7, 5, this.constants.heapSCOCI, this.heapDCOCI);
      Properties var7 = new Properties();
      this.bulkHeapCC.getInternalTablePropertySet(var7);
      if (this.triggerInfo != null) {
      }

      if (this.hasBeforeRowTrigger && this.rowHolder != null) {
         this.rowHolder = new TemporaryRowHolderImpl(this.activation, var7, this.resultDescription);
      }

      Properties var8 = this.constants.getTargetProperties();
      Enumeration var9 = var8.keys();

      while(var9.hasMoreElements()) {
         String var10 = (String)var9.nextElement();
         var7.put(var10, var8.getProperty(var10));
      }

      if (this.constants.irgs.length > 0) {
         this.sourceResultSet.setNeedsRowLocation(true);
      }

      if (this.constants.hasDeferrableChecks) {
         this.sourceResultSet.setHasDeferrableChecks();
      }

      this.dd = var1.getDataDictionary();
      this.td = this.dd.getTableDescriptor(this.constants.targetUUID);
      long[] var15 = new long[1];
      long var5;
      if (this.bulkInsertReplace) {
         var5 = this.tc.createAndLoadConglomerate("heap", var2.getRowArray(), (ColumnOrdering[])null, this.td.getColumnCollationIds(), var7, 0, this.sourceResultSet, var15);
      } else {
         var5 = this.tc.recreateAndLoadConglomerate("heap", false, var2.getRowArray(), (ColumnOrdering[])null, this.td.getColumnCollationIds(), var7, 0, var3, this.sourceResultSet, var15);
      }

      if (var5 != var3) {
         this.rowCount = var15[0];
         this.setEstimatedRowCount(var5);
         this.dd.startWriting(var1);
         if (this.identitySequenceUUIDString == null) {
            var1.autoincrementFlushCache(this.constants.targetUUID);
         } else {
            for(BulkInsertCounter var14 : this.bulkInsertCounters) {
               if (var14 != null) {
                  this.dd.flushBulkInsertCounter(this.identitySequenceUUIDString, var14);
               }
            }
         }

         DependencyManager var16 = this.dd.getDependencyManager();
         var16.invalidateFor(this.td, 15, var1);
         if (this.constants.irgs.length > 0) {
            this.updateAllIndexes(var5, this.constants, this.td, this.dd, var2);
         }

         this.bulkHeapCC.close();
         this.bulkHeapCC = null;
         ConglomerateDescriptor var17 = this.td.getConglomerateDescriptor(var3);
         this.dd.updateConglomerateDescriptor(var17, var5, this.tc);
         this.tc.dropConglomerate(var3);
      }
   }

   private void bulkValidateForeignKeys(TransactionController var1, ContextManager var2, ExecRow var3) throws StandardException {
      if ((this.indexRows != null || this.bulkInsertReplace) && this.fkInfoArray != null) {
         for(FKInfo var7 : this.fkInfoArray) {
            if (!this.bulkInsertReplace) {
               Long var15 = (Long)this.indexConversionTable.get(var7.fkConglomNumbers[0]);
               this.bulkValidateForeignKeysCore(var1, var2, var7, var15, var7.refConglomNumber, var7.fkConstraintNames[0], var3);
            } else {
               for(int var8 = 0; var8 < var7.fkConglomNumbers.length; ++var8) {
                  if (!var7.fkIsSelfReferencing[var8] || this.indexRows != null) {
                     long var9;
                     long var11;
                     if (var7.fkIsSelfReferencing[var8]) {
                        var9 = (Long)this.indexConversionTable.get(var7.refConglomNumber);
                        var11 = (Long)this.indexConversionTable.get(var7.fkConglomNumbers[var8]);
                     } else {
                        Long var13 = (Long)this.indexConversionTable.get(var7.refConglomNumber);
                        Long var14 = (Long)this.indexConversionTable.get(var7.fkConglomNumbers[var8]);
                        if (var13 == null) {
                           var9 = var7.refConglomNumber;
                        } else {
                           var9 = var13;
                        }

                        if (var14 == null) {
                           var11 = var7.fkConglomNumbers[var8];
                        } else {
                           var11 = var14;
                        }
                     }

                     this.bulkValidateForeignKeysCore(var1, var2, var7, var11, var9, var7.fkConstraintNames[var8], var3);
                  }
               }
            }
         }

      }
   }

   private void bulkValidateForeignKeysCore(TransactionController var1, ContextManager var2, FKInfo var3, long var4, long var6, String var8, ExecRow var9) throws StandardException {
      GroupFetchScanController var11 = null;
      GroupFetchScanController var12 = null;

      try {
         ExecRow var10 = this.makeIndexTemplate(var3, var9, var2);
         var12 = var1.openGroupFetchScan(var4, false, 0, 7, 2, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
         var11 = var1.openGroupFetchScan(var6, false, 0, var4 == var6 ? 7 : 6, 2, (FormatableBitSet)null, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
         ExecRow var13 = var10.getClone();
         RIBulkChecker var14 = new RIBulkChecker(this.activation, var11, var12, var10, true, (ConglomerateController)null, var13, var3.schemaName, var3.tableName, var3.fkIds[0], var3.deferrable[0], var4, var6);
         int var15 = var14.doCheck();
         if (var15 > 0) {
            StandardException var16 = StandardException.newException("23503", new Object[]{var8, var3.tableName, StatementUtil.typeName(var3.stmtType), RowUtil.toString((ExecRow)var13, 0, var3.colArray.length - 1)});
            throw var16;
         }
      } finally {
         if (var12 != null) {
            var12.close();
         }

         if (var11 != null) {
            var11.close();
         }

      }

   }

   private ExecRow makeIndexTemplate(FKInfo var1, ExecRow var2, ContextManager var3) throws StandardException {
      ExecIndexRow var4 = RowUtil.getEmptyIndexRow(var1.colArray.length + 1, this.lcc);
      DataValueDescriptor[] var5 = var2.getRowArray();
      DataValueDescriptor[] var6 = var4.getRowArray();

      int var7;
      for(var7 = 0; var7 < var1.colArray.length; ++var7) {
         var6[var7] = var5[var1.colArray[var7] - 1].cloneValue(false);
      }

      var6[var7] = var1.rowLocation.cloneValue(false);
      return var4;
   }

   private void setUpAllSorts(ExecRow var1, RowLocation var2) throws StandardException {
      int var3 = this.constants.irgs.length;
      int var4 = this.td.getNumberOfColumns();
      this.ordering = new ColumnOrdering[var3][];
      this.collation = new int[var3][];
      this.needToDropSort = new boolean[var3];
      this.sortIds = new long[var3];
      this.rowSources = new RowLocationRetRowSource[var3];
      this.indexedCols = new FormatableBitSet(var4 + 1);

      for(int var5 = 0; var5 < var3; ++var5) {
         int[] var6 = this.constants.irgs[var5].baseColumnPositions();

         for(int var7 = 0; var7 < var6.length; ++var7) {
            this.indexedCols.set(var6[var7]);
         }

         this.indexRows[var5] = this.constants.irgs[var5].getIndexRowTemplate();
         this.constants.irgs[var5].getIndexRow(var1, var2, this.indexRows[var5], (FormatableBitSet)null);
         ConglomerateDescriptor var21 = this.td.getConglomerateDescriptor(this.constants.indexCIDS[var5]);
         int[] var8 = this.constants.irgs[var5].baseColumnPositions();
         boolean[] var9 = this.constants.irgs[var5].isAscending();
         boolean var12 = var3 == 1;
         IndexRowGenerator var13 = var21.getIndexDescriptor();
         Properties var14 = null;
         String var15 = var21.getConglomerateName();
         boolean var16 = false;
         boolean var17 = false;
         UUID var18 = null;
         if (var21.isConstraint()) {
            ConstraintDescriptor var19 = this.dd.getConstraintDescriptor(this.td, var21.getUUID());
            var15 = var19.getConstraintName();
            var16 = this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(this.activation), var19.getUUID());
            var17 = var19.deferrable();
            var18 = var19.getUUID();
         }

         int var10;
         Object var11;
         if (!var13.isUnique() && !var13.isUniqueDeferrable()) {
            if (var13.isUniqueWithDuplicateNulls()) {
               var10 = var8.length + 1;
               var14 = new Properties();
               var14.put("implType", "sort almost unique external");
               var11 = new UniqueWithDuplicateNullsIndexSortObserver(this.lcc, var18, true, var17, var16, var15, this.indexRows[var5], true, this.td.getName());
            } else {
               var10 = var8.length + 1;
               var11 = new BasicSortObserver(false, false, this.indexRows[var5], var12);
            }
         } else {
            var10 = var13.isUnique() ? var8.length : var8.length + 1;
            var11 = new UniqueIndexSortObserver(this.lcc, var18, false, var17, var16, var15, this.indexRows[var5], var12, this.td.getName());
         }

         this.ordering[var5] = new ColumnOrdering[var10];

         for(int var22 = 0; var22 < var9.length; ++var22) {
            this.ordering[var5][var22] = new IndexColumnOrder(var22, var9[var22]);
         }

         if (var10 > var9.length) {
            this.ordering[var5][var9.length] = new IndexColumnOrder(var9.length);
         }

         this.collation[var5] = this.constants.irgs[var5].getColumnCollationIds(this.td.getColumnDescriptorList());
         this.sortIds[var5] = this.tc.createSort(var14, this.indexRows[var5].getRowArrayClone(), this.ordering[var5], (SortObserver)var11, false, (long)((int)this.sourceResultSet.getEstimatedRowCount()), -1);
         this.needToDropSort[var5] = true;
      }

      this.sorters = new SortController[var3];

      for(int var20 = 0; var20 < var3; ++var20) {
         this.sorters[var20] = this.tc.openSort(this.sortIds[var20]);
         this.needToDropSort[var20] = true;
      }

   }

   private void updateAllIndexes(long var1, InsertConstantAction var3, TableDescriptor var4, DataDictionary var5, ExecRow var6) throws StandardException {
      int var7 = var3.irgs.length;
      if (this.indexRows == null) {
         if (this.bulkInsertReplace) {
            this.emptyIndexes(var1, var3, var4, var5, var6);
         }

      } else {
         var5.dropStatisticsDescriptors(var4.getUUID(), (UUID)null, this.tc);
         long[] var8 = new long[var7];
         this.indexConversionTable = new Hashtable(var7);

         for(int var9 = 0; var9 < var7; ++var9) {
            Properties var11 = new Properties();
            ConglomerateDescriptor var12 = var4.getConglomerateDescriptor(var3.indexCIDS[var9]);
            ConglomerateController var10 = this.tc.openCompiledConglomerate(false, 4, 7, 5, var3.indexSCOCIs[var9], this.indexDCOCIs[var9]);
            var10.getInternalTablePropertySet(var11);
            int var13 = this.indexRows[var9].nColumns();
            var11.put("baseConglomerateId", Long.toString(var1));
            if (var12.getIndexDescriptor().isUnique()) {
               var11.put("nUniqueColumns", Integer.toString(var13 - 1));
            } else {
               var11.put("nUniqueColumns", Integer.toString(var13));
            }

            if (var12.getIndexDescriptor().isUniqueWithDuplicateNulls() && !var12.getIndexDescriptor().hasDeferrableChecking()) {
               var11.put("uniqueWithDuplicateNulls", Boolean.toString(true));
            }

            var11.put("rowLocationColumn", Integer.toString(var13 - 1));
            var11.put("nKeyFields", Integer.toString(var13));
            var10.close();
            this.sorters[var9].completedInserts();
            this.sorters[var9] = null;
            this.rowSources[var9] = new CardinalityCounter(this.tc.openSortRowSource(this.sortIds[var9]));
            var8[var9] = this.tc.createAndLoadConglomerate("BTREE", this.indexRows[var9].getRowArray(), this.ordering[var9], this.collation[var9], var11, 0, this.rowSources[var9], (long[])null);
            CardinalityCounter var14 = (CardinalityCounter)this.rowSources[var9];
            long var15;
            if ((var15 = var14.getRowCount()) > 0L) {
               long[] var17 = var14.getCardinality();

               for(int var18 = 0; var18 < var17.length; ++var18) {
                  StatisticsDescriptor var19 = new StatisticsDescriptor(var5, var5.getUUIDFactory().createUUID(), var12.getUUID(), var4.getUUID(), "I", new StatisticsImpl(var15, var17[var18]), var18 + 1);
                  var5.addDescriptor(var19, (TupleDescriptor)null, 14, true, this.tc);
               }
            }

            var5.updateConglomerateDescriptor(var4.getConglomerateDescriptors(var3.indexCIDS[var9]), var8[var9], this.tc);
            this.tc.dropConglomerate(var3.indexCIDS[var9]);
            this.indexConversionTable.put(var3.indexCIDS[var9], var8[var9]);
         }

      }
   }

   public void cleanUp() throws StandardException {
      if (this.tableScan != null) {
         this.tableScan.close();
         this.tableScan = null;
      }

      if (this.triggerActivator != null) {
         this.triggerActivator.cleanup();
      }

      if (this.sourceResultSet != null) {
         this.sourceResultSet.close();
      }

      this.numOpens = 0;
      if (this.rowChanger != null) {
         this.rowChanger.close();
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

      if (this.fkChecker != null) {
         this.fkChecker.close();
      }

      if (this.bulkHeapCC != null) {
         this.bulkHeapCC.close();
         this.bulkHeapCC = null;
      }

      if (this.bulkHeapSC != null) {
         this.bulkHeapSC.close();
         this.bulkHeapSC = null;
      }

      if (this.sorters != null) {
         for(int var1 = 0; var1 < this.constants.irgs.length; ++var1) {
            if (this.sorters[var1] != null) {
               this.sorters[var1].completedInserts();
            }

            this.sorters[var1] = null;
         }
      }

      if (this.needToDropSort != null) {
         for(int var2 = 0; var2 < this.needToDropSort.length; ++var2) {
            if (this.needToDropSort[var2]) {
               this.tc.dropSort(this.sortIds[var2]);
               this.needToDropSort[var2] = false;
            }
         }
      }

      if (this.rowSources != null) {
         for(int var3 = 0; var3 < this.rowSources.length; ++var3) {
            if (this.rowSources[var3] != null) {
               this.rowSources[var3].closeRowSource();
               this.rowSources[var3] = null;
            }
         }
      }

      this.close(this.constants.underMerge());
   }

   protected boolean verifyBulkInsert() throws StandardException {
      return this.constants.deferred ? false : this.getExclusiveTableLock();
   }

   private boolean getExclusiveTableLock() throws StandardException {
      boolean var1 = false;
      this.bulkHeapSC = this.tc.openCompiledScan(false, 4, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0, this.constants.heapSCOCI, this.heapDCOCI);
      if (!this.bulkInsertReplace) {
         var1 = this.bulkHeapSC.next();
      } else {
         this.rl = this.bulkHeapSC.newRowLocationTemplate();
      }

      this.bulkHeapSC.close();
      this.bulkHeapSC = null;
      return !var1;
   }

   private void setEstimatedRowCount(long var1) throws StandardException {
      this.bulkHeapSC = this.tc.openCompiledScan(false, 4, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0, this.constants.heapSCOCI, this.heapDCOCI);
      this.bulkHeapSC.setEstimatedRowCount(this.rowCount);
      this.bulkHeapSC.close();
      this.bulkHeapSC = null;
   }

   private void emptyIndexes(long var1, InsertConstantAction var3, TableDescriptor var4, DataDictionary var5, ExecRow var6) throws StandardException {
      int var7 = var3.irgs.length;
      ExecIndexRow[] var8 = new ExecIndexRow[var7];
      ColumnOrdering[][] var10 = new ColumnOrdering[var7][];
      int var11 = var4.getNumberOfColumns();
      this.collation = new int[var7][];
      FormatableBitSet var12 = new FormatableBitSet(var11 + 1);
      int var13 = 0;

      for(int var14 = 0; var14 < var7; ++var14) {
         int[] var15 = var3.irgs[var14].baseColumnPositions();

         for(int var16 = 0; var16 < var15.length; ++var16) {
            if (!var12.get(var15[var16])) {
               var12.set(var15[var16]);
               ++var13;
            }
         }
      }

      ExecRow var9 = this.activation.getExecutionFactory().getValueRow(var13);
      int var27 = 0;

      for(int var28 = 0; var28 < var11; ++var28) {
         if (var12.get(var28 + 1)) {
            ++var27;
            var9.setColumn(var27, var6.getColumn(var28 + 1).cloneValue(false));
         }
      }

      this.needToDropSort = new boolean[var7];
      this.sortIds = new long[var7];

      for(int var29 = 0; var29 < var7; ++var29) {
         var8[var29] = var3.irgs[var29].getIndexRowTemplate();
         var3.irgs[var29].getIndexRow(var9, this.rl, var8[var29], var12);
         ConglomerateDescriptor var31 = var4.getConglomerateDescriptor(var3.indexCIDS[var29]);
         int[] var17 = var3.irgs[var29].baseColumnPositions();
         boolean[] var18 = var3.irgs[var29].isAscending();
         IndexRowGenerator var21 = var31.getIndexDescriptor();
         int var19;
         Object var20;
         if (!var21.isUnique() && !var21.isUniqueDeferrable()) {
            var19 = var17.length + 1;
            var20 = new BasicSortObserver(false, false, var8[var29], true);
         } else {
            var19 = var21.isUnique() ? var17.length : var17.length + 1;
            String var22 = var31.getConglomerateName();
            boolean var23 = false;
            boolean var24 = false;
            UUID var25 = null;
            if (var31.isConstraint()) {
               ConstraintDescriptor var26 = var5.getConstraintDescriptor(var4, var31.getUUID());
               var22 = var26.getConstraintName();
               var23 = this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(this.activation), var26.getUUID());
               var24 = var26.deferrable();
               var25 = var26.getUUID();
            }

            var20 = new UniqueIndexSortObserver(this.lcc, var25, false, var24, var23, var22, var8[var29], true, var4.getName());
         }

         var10[var29] = new ColumnOrdering[var19];

         for(int var39 = 0; var39 < var18.length; ++var39) {
            var10[var29][var39] = new IndexColumnOrder(var39, var18[var39]);
         }

         if (var19 > var18.length) {
            var10[var29][var18.length] = new IndexColumnOrder(var18.length);
         }

         this.sortIds[var29] = this.tc.createSort((Properties)null, var8[var29].getRowArrayClone(), var10[var29], (SortObserver)var20, false, this.rowCount, -1);
         this.needToDropSort[var29] = true;
      }

      this.rowSources = new RowLocationRetRowSource[var7];
      SortController[] var30 = new SortController[var7];

      for(int var32 = 0; var32 < var7; ++var32) {
         var30[var32] = this.tc.openSort(this.sortIds[var32]);
         var30[var32].completedInserts();
         this.rowSources[var32] = this.tc.openSortRowSource(this.sortIds[var32]);
      }

      long[] var33 = new long[var7];

      for(int var34 = 0; var34 < var7; ++var34) {
         Properties var36 = new Properties();
         ConglomerateDescriptor var37 = var4.getConglomerateDescriptor(var3.indexCIDS[var34]);
         ConglomerateController var35 = this.tc.openCompiledConglomerate(false, 4, 7, 5, var3.indexSCOCIs[var34], this.indexDCOCIs[var34]);
         var35.getInternalTablePropertySet(var36);
         int var38 = var8[var34].nColumns();
         var36.put("baseConglomerateId", Long.toString(var1));
         if (var37.getIndexDescriptor().isUnique()) {
            var36.put("nUniqueColumns", Integer.toString(var38 - 1));
         } else {
            var36.put("nUniqueColumns", Integer.toString(var38));
         }

         if (var37.getIndexDescriptor().isUniqueWithDuplicateNulls() && !var37.getIndexDescriptor().hasDeferrableChecking()) {
            var36.put("uniqueWithDuplicateNulls", Boolean.toString(true));
         }

         var36.put("rowLocationColumn", Integer.toString(var38 - 1));
         var36.put("nKeyFields", Integer.toString(var38));
         var35.close();
         this.collation[var34] = var3.irgs[var34].getColumnCollationIds(var4.getColumnDescriptorList());
         var33[var34] = this.tc.createAndLoadConglomerate("BTREE", var8[var34].getRowArray(), (ColumnOrdering[])null, this.collation[var34], var36, 0, this.rowSources[var34], (long[])null);
         var5.updateConglomerateDescriptor(var4.getConglomerateDescriptors(var3.indexCIDS[var34]), var33[var34], this.tc);
         this.tc.dropConglomerate(var3.indexCIDS[var34]);
      }

   }

   private BulkTableScanResultSet getTableScanResultSet(long var1) throws StandardException {
      if (this.tableScan == null) {
         this.tableScan = new BulkTableScanResultSet(var1, this.tc.getStaticCompiledConglomInfo(var1), this.activation, this.fullTemplateId, 0, (GeneratedMethod)null, 0, (GeneratedMethod)null, 0, false, (Qualifier[][])null, "tableName", (String)null, (String)null, false, false, -1, -1, 7, true, 2, 16, false, false, (double)0.0F, (double)0.0F);
         this.tableScan.openCore();
      } else {
         this.tableScan.reopenCore();
      }

      return this.tableScan;
   }

   private String[] getColumnNames(int[] var1) {
      int var2 = var1.length;
      String[] var3 = new String[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = this.constants.getColumnName(var4);
      }

      return var3;
   }

   public void finish() throws StandardException {
      this.sourceResultSet.finish();
      super.finish();
   }

   public void rememberConstraint(UUID var1) throws StandardException {
      if (this.violatingCheckConstraints == null) {
         this.violatingCheckConstraints = new ArrayList();
      }

      if (!this.violatingCheckConstraints.contains(var1)) {
         this.violatingCheckConstraints.add(var1);
      }

   }

   protected void initializeAIcache(RowLocation[] var1) throws StandardException {
      if ((var1 = this.constants.getAutoincRowLocation()) != null) {
         this.aiCache = new DataValueDescriptor[var1.length];
         this.bulkInsertCounters = new BulkInsertCounter[var1.length];

         for(int var2 = 0; var2 < this.resultDescription.getColumnCount(); ++var2) {
            if (var1[var2] != null) {
               ResultColumnDescriptor var3 = this.resultDescription.getColumnDescriptor(var2 + 1);
               this.aiCache[var2] = var3.getType().getNull();
            }
         }
      }

   }
}
