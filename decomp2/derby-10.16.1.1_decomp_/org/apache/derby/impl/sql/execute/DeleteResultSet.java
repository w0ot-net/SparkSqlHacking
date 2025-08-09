package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class DeleteResultSet extends DMLWriteResultSet {
   private TransactionController tc;
   DeleteConstantAction constants;
   protected NoPutResultSet source;
   NoPutResultSet savedSource;
   int numIndexes;
   protected RowChanger rc;
   private ExecRow row;
   protected ConglomerateController deferredBaseCC;
   protected TemporaryRowHolderImpl rowHolder;
   private int numOpens;
   private boolean firstExecute;
   private FormatableBitSet baseRowReadList;
   private int rlColumnNumber;
   protected FKInfo[] fkInfoArray;
   private TriggerInfo triggerInfo;
   private RISetChecker fkChecker;
   private TriggerEventActivator triggerActivator;
   private boolean noTriggersOrFks;
   ExecRow deferredSparseRow;
   ExecRow deferredBaseRow;
   int lockMode;
   protected boolean cascadeDelete;
   ExecRow deferredRLRow;
   int numberOfBaseColumns;

   DeleteResultSet(NoPutResultSet var1, Activation var2) throws StandardException {
      this(var1, var2.getConstantAction(), var2);
   }

   DeleteResultSet(NoPutResultSet var1, ConstantAction var2, Activation var3) throws StandardException {
      super(var3, var2);
      this.deferredRLRow = null;
      this.numberOfBaseColumns = 0;
      this.source = var1;
      this.tc = var3.getTransactionController();
      this.constants = (DeleteConstantAction)this.constantAction;
      this.fkInfoArray = this.constants.getFKInfo();
      this.triggerInfo = this.constants.getTriggerInfo();
      this.noTriggersOrFks = this.fkInfoArray == null && this.triggerInfo == null;
      this.baseRowReadList = this.constants.getBaseRowReadList();
      if (var1 != null) {
         this.resultDescription = var1.getResultDescription();
      } else {
         this.resultDescription = this.constants.resultDescription;
      }

   }

   public void open() throws StandardException {
      this.setup();
      boolean var1 = this.collectAffectedRows();
      if (!var1) {
         this.activation.addWarning(StandardException.newWarning("02000", new Object[0]));
      }

      if (this.constants.deferred) {
         this.runFkChecker(true);
         this.fireBeforeTriggers();
         this.deleteDeferredRows();
         this.runFkChecker(false);
         this.rc.finish();
         this.fireAfterTriggers();
      }

      if (this.lcc.getRunTimeStatisticsMode()) {
         this.savedSource = this.source;
      }

      this.cleanUp();
      this.endTime = this.getCurrentTimeMillis();
   }

   void setup() throws StandardException {
      super.setup();
      this.firstExecute = this.rc == null;

      try {
         if (this.numOpens++ == 0) {
            this.source.openCore();
         } else {
            this.source.reopenCore();
         }
      } catch (StandardException var2) {
         this.activation.checkStatementValidity();
         throw var2;
      }

      this.activation.checkStatementValidity();
      if (this.firstExecute) {
         this.rc = this.lcc.getLanguageConnectionFactory().getExecutionFactory().getRowChanger(this.constants.conglomId, this.constants.heapSCOCI, this.heapDCOCI, this.constants.irgs, this.constants.indexCIDS, this.constants.indexSCOCIs, this.indexDCOCIs, this.constants.numColumns, this.tc, (int[])null, this.baseRowReadList, this.constants.getBaseRowReadMap(), this.constants.getStreamStorableHeapColIds(), this.activation);
      }

      this.lockMode = this.decodeLockMode(this.constants.lockMode);
      this.rc.open(this.lockMode);
      if (this.constants.deferred || this.cascadeDelete) {
         this.activation.clearIndexScanInfo();
      }

      this.rowCount = 0L;
      if (!this.cascadeDelete) {
         this.row = this.getNextRowCore(this.source);
      }

      if (this.resultDescription == null) {
         this.numberOfBaseColumns = this.row == null ? 0 : this.row.nColumns();
      } else {
         this.numberOfBaseColumns = this.resultDescription.getColumnCount();
      }

      this.numIndexes = this.constants.irgs.length;
      if (this.constants.deferred || this.cascadeDelete) {
         Properties var1 = new Properties();
         this.rc.getHeapConglomerateController().getInternalTablePropertySet(var1);
         this.deferredRLRow = RowUtil.getEmptyValueRow(1, this.lcc);
         this.rlColumnNumber = this.noTriggersOrFks ? 1 : this.numberOfBaseColumns;
         if (this.cascadeDelete) {
            this.rowHolder = new TemporaryRowHolderImpl(this.activation, var1, this.resultDescription != null ? this.resultDescription.truncateColumns(this.rlColumnNumber) : null, false);
         } else {
            this.rowHolder = new TemporaryRowHolderImpl(this.activation, var1, this.resultDescription != null ? this.resultDescription.truncateColumns(this.rlColumnNumber) : null);
         }

         this.rc.setRowHolder(this.rowHolder);
      }

      if (this.fkInfoArray != null) {
         if (this.fkChecker == null) {
            this.fkChecker = new RISetChecker(this.lcc, this.tc, this.fkInfoArray);
         } else {
            this.fkChecker.reopen();
         }
      }

   }

   boolean collectAffectedRows() throws StandardException {
      boolean var3 = false;
      if (this.cascadeDelete) {
         this.row = this.getNextRowCore(this.source);
      }

      while(this.row != null) {
         var3 = true;
         DataValueDescriptor var1 = this.row.getColumn(this.row.nColumns());
         if (!this.constants.deferred && !this.cascadeDelete) {
            if (this.fkChecker != null) {
               this.fkChecker.doPKCheck(this.activation, this.row, false, 2);
            }

            RowLocation var2 = (RowLocation)var1.getObject();
            this.rc.deleteRow(this.row, var2);
            this.source.markRowAsDeleted();
         } else {
            if (this.noTriggersOrFks) {
               this.deferredRLRow.setColumn(1, var1);
               this.rowHolder.insert(this.deferredRLRow);
            } else {
               this.rowHolder.insert(this.row);
            }

            if (this.deferredBaseRow == null) {
               this.deferredBaseRow = RowUtil.getEmptyValueRow(this.numberOfBaseColumns - 1, this.lcc);
               RowUtil.copyCloneColumns(this.deferredBaseRow, this.row, this.numberOfBaseColumns - 1);
               this.deferredSparseRow = this.makeDeferredSparseRow(this.deferredBaseRow, this.baseRowReadList, this.lcc);
            }
         }

         ++this.rowCount;
         if (this.constants.singleRowSource) {
            this.row = null;
         } else {
            this.row = this.getNextRowCore(this.source);
         }
      }

      return var3;
   }

   void fireBeforeTriggers() throws StandardException {
      if (this.triggerInfo != null) {
         if (this.triggerActivator == null) {
            this.triggerActivator = new TriggerEventActivator(this.lcc, this.constants.targetUUID, this.triggerInfo, 2, this.activation, (Vector)null);
         } else {
            this.triggerActivator.reopen();
         }

         this.triggerActivator.notifyEvent(TriggerEvents.BEFORE_DELETE, this.rowHolder.getResultSet(), (CursorResultSet)null, this.constants.getBaseRowReadMap());
         this.triggerActivator.cleanup();
      }

   }

   void fireAfterTriggers() throws StandardException {
      if (this.triggerActivator != null) {
         this.triggerActivator.reopen();
         this.triggerActivator.notifyEvent(TriggerEvents.AFTER_DELETE, this.rowHolder.getResultSet(), (CursorResultSet)null, this.constants.getBaseRowReadMap());
         this.triggerActivator.cleanup();
      }

   }

   void deleteDeferredRows() throws StandardException {
      this.deferredBaseCC = this.tc.openCompiledConglomerate(false, 8196, this.lockMode, 5, this.constants.heapSCOCI, this.heapDCOCI);
      CursorResultSet var4 = this.rowHolder.getResultSet();

      try {
         FormatableBitSet var5 = RowUtil.shift(this.baseRowReadList, 1);
         var4.open();

         ExecRow var3;
         while((var3 = var4.getNextRow()) != null) {
            DataValueDescriptor var1 = var3.getColumn(this.rlColumnNumber);
            RowLocation var2 = (RowLocation)var1.getObject();
            boolean var6 = this.deferredBaseCC.fetch(var2, this.deferredSparseRow.getRowArray(), var5);
            if (!this.cascadeDelete || var6) {
               this.rc.deleteRow(this.deferredBaseRow, var2);
               this.source.markRowAsDeleted();
            }
         }
      } finally {
         var4.close();
      }

   }

   void runFkChecker(boolean var1) throws StandardException {
      if (this.fkChecker != null) {
         CursorResultSet var2 = this.rowHolder.getResultSet();

         try {
            var2.open();

            ExecRow var3;
            while((var3 = var2.getNextRow()) != null) {
               this.fkChecker.doPKCheck(this.activation, var3, var1, 1);
            }

            if (var1) {
               this.fkChecker.postCheck();
            }
         } finally {
            var2.close();
         }
      }

   }

   NoPutResultSet createDependentSource(RowChanger var1) throws StandardException {
      return null;
   }

   public void cleanUp() throws StandardException {
      this.numOpens = 0;
      if (this.source != null) {
         this.source.close();
      }

      if (this.rc != null) {
         this.rc.close();
      }

      if (this.rowHolder != null) {
         this.rowHolder.close();
      }

      if (this.fkChecker != null) {
         this.fkChecker.close();
      }

      if (this.deferredBaseCC != null) {
         this.deferredBaseCC.close();
      }

      this.deferredBaseCC = null;
      if (this.rc != null) {
         this.rc.close();
      }

      this.close();
   }

   public void close() throws StandardException {
      super.close(this.constants.underMerge());
   }

   public void finish() throws StandardException {
      if (this.source != null) {
         this.source.finish();
      }

      super.finish();
   }
}
