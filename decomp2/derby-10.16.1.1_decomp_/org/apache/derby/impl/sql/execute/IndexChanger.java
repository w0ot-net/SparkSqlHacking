package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
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

class IndexChanger {
   private final IndexRowGenerator irg;
   private final long indexCID;
   private final DynamicCompiledOpenConglomInfo indexDCOCI;
   private final StaticCompiledOpenConglomInfo indexSCOCI;
   private final String indexName;
   private ConglomerateController baseCC;
   private final TransactionController tc;
   private final int lockMode;
   private final FormatableBitSet baseRowReadMap;
   private ConglomerateController indexCC = null;
   private ScanController indexSC = null;
   private ExecIndexRow ourIndexRow = null;
   private ExecIndexRow ourUpdatedIndexRow = null;
   private TemporaryRowHolderImpl rowHolder = null;
   private boolean rowHolderPassedIn;
   private int isolationLevel;
   private final Activation activation;
   private boolean ownIndexSC = true;
   private final boolean deferrable;
   private final LanguageConnectionContext lcc;
   private BackingStoreHashtable deferredDuplicates;
   private UUID uniqueConstraintId;

   IndexChanger(IndexRowGenerator var1, long var2, StaticCompiledOpenConglomInfo var4, DynamicCompiledOpenConglomInfo var5, String var6, ConglomerateController var7, TransactionController var8, int var9, FormatableBitSet var10, int var11, Activation var12) throws StandardException {
      this.irg = var1;
      this.deferrable = var1.hasDeferrableChecking();
      this.indexCID = var2;
      this.indexSCOCI = var4;
      this.indexDCOCI = var5;
      this.baseCC = var7;
      this.tc = var8;
      this.lockMode = var9;
      this.baseRowReadMap = var10;
      this.rowHolderPassedIn = false;
      this.isolationLevel = var11;
      this.activation = var12;
      this.indexName = var6;
      this.lcc = var12 != null ? var12.getLanguageConnectionContext() : null;
      if (var12 != null && var12.getIndexConglomerateNumber() == var2) {
         this.ownIndexSC = false;
      }

   }

   void setRowHolder(TemporaryRowHolderImpl var1) {
      this.rowHolder = var1;
      this.rowHolderPassedIn = var1 != null;
   }

   void setBaseCC(ConglomerateController var1) {
      this.baseCC = var1;
   }

   private void setOurIndexRow(ExecRow var1, RowLocation var2) throws StandardException {
      if (this.ourIndexRow == null) {
         this.ourIndexRow = this.irg.getIndexRowTemplate();
      }

      this.irg.getIndexRow(var1, var2, this.ourIndexRow, this.baseRowReadMap);
   }

   private void setOurUpdatedIndexRow(ExecRow var1, RowLocation var2) throws StandardException {
      if (this.ourUpdatedIndexRow == null) {
         this.ourUpdatedIndexRow = this.irg.getIndexRowTemplate();
      }

      this.irg.getIndexRow(var1, var2, this.ourUpdatedIndexRow, this.baseRowReadMap);
   }

   private boolean indexRowChanged() throws StandardException {
      int var1 = this.ourIndexRow.nColumns();

      for(int var2 = 1; var2 <= var1; ++var2) {
         DataValueDescriptor var3 = this.ourIndexRow.getColumn(var2);
         DataValueDescriptor var4 = this.ourUpdatedIndexRow.getColumn(var2);
         if (!var3.compare(2, var4, true, true)) {
            return true;
         }
      }

      return false;
   }

   private void setScan() throws StandardException {
      if (!this.ownIndexSC) {
         this.indexSC = this.activation.getIndexScanController();
      } else if (this.indexSC == null) {
         RowLocation var1 = this.baseCC.newRowLocationTemplate();
         if (this.indexSCOCI == null) {
            this.indexSC = this.tc.openScan(this.indexCID, false, 4, this.lockMode, this.isolationLevel, (FormatableBitSet)null, this.ourIndexRow.getRowArray(), 1, (Qualifier[][])null, this.ourIndexRow.getRowArray(), -1);
         } else {
            this.indexSC = this.tc.openCompiledScan(false, 4, this.lockMode, this.isolationLevel, (FormatableBitSet)null, this.ourIndexRow.getRowArray(), 1, (Qualifier[][])null, this.ourIndexRow.getRowArray(), -1, this.indexSCOCI, this.indexDCOCI);
         }
      } else {
         this.indexSC.reopenScan(this.ourIndexRow.getRowArray(), 1, (Qualifier[][])null, this.ourIndexRow.getRowArray(), -1);
      }

   }

   private void closeIndexCC() throws StandardException {
      if (this.indexCC != null) {
         this.indexCC.close();
      }

      this.indexCC = null;
   }

   private void closeIndexSC() throws StandardException {
      if (this.ownIndexSC && this.indexSC != null) {
         this.indexSC.close();
         this.indexSC = null;
      }

   }

   private void doDelete() throws StandardException {
      if (this.ownIndexSC && !this.indexSC.next()) {
         Object[] var1 = new Object[]{this.ourIndexRow.getRowArray()[this.ourIndexRow.getRowArray().length - 1], this.indexCID};
         Monitor.getStream().println(MessageService.getTextMessage("X0Y83.S", var1));
      } else {
         this.indexSC.delete();
      }
   }

   private void doInsert() throws StandardException {
      this.insertAndCheckDups(this.ourIndexRow);
   }

   private void doDeferredInsert() throws StandardException {
      if (this.rowHolder == null) {
         Properties var1 = new Properties();
         this.openIndexCC().getInternalTablePropertySet(var1);
         this.rowHolder = new TemporaryRowHolderImpl(this.activation, var1, (ResultDescription)null);
      }

      if (!this.rowHolderPassedIn) {
         this.rowHolder.insert(this.ourIndexRow);
      }

   }

   private UUID getUniqueConstraintId() throws StandardException {
      if (this.uniqueConstraintId == null) {
         DataDictionary var1 = this.lcc.getDataDictionary();
         ConglomerateDescriptor var2 = var1.getConglomerateDescriptor(this.indexCID);
         this.uniqueConstraintId = var1.getConstraintDescriptor(var1.getTableDescriptor(var2.getTableID()), var2.getUUID()).getUUID();
      }

      return this.uniqueConstraintId;
   }

   private void insertAndCheckDups(ExecIndexRow var1) throws StandardException {
      this.openIndexCC();
      DataValueDescriptor[] var3 = var1.getRowArray();
      int var2;
      if (this.deferrable) {
         var2 = this.indexCC.insert(var1.getRowArray());
         DataValueDescriptor[] var4 = new DataValueDescriptor[var3.length - 1];
         System.arraycopy(var3, 0, var4, 0, var4.length);
         boolean var5 = this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(this.activation), this.getUniqueConstraintId());
         ScanController var6 = this.tc.openScan(this.indexCID, false, var5 ? '耀' : 0, 6, 3, (FormatableBitSet)null, var4, 1, (Qualifier[][])null, var4, -1);
         boolean var7 = false;

         try {
            boolean var8 = var6.next();
            var7 = var8 && var6.next();
         } catch (StandardException var12) {
            if (!var12.getSQLState().equals("40XL1") && !var12.getSQLState().equals("40001") || !var5) {
               throw var12;
            }

            var7 = true;
         }

         if (var7 && this.irg.isUniqueWithDuplicateNulls()) {
            int var18 = var3.length - 1;

            for(int var9 = 0; var9 < var18; ++var9) {
               if (var3[var9].isNull()) {
                  var7 = false;
                  break;
               }
            }
         }

         if (var7) {
            if (var5) {
               this.deferredDuplicates = DeferredConstraintsMemory.rememberDuplicate(this.lcc, this.deferredDuplicates, this.getUniqueConstraintId(), var1.getRowArray());
            } else {
               var2 = 1;
            }
         }
      } else {
         var2 = this.indexCC.insert(var1.getRowArray());
      }

      if (var2 == 1) {
         String var13 = this.indexName;
         LanguageConnectionContext var14 = this.activation.getLanguageConnectionContext();
         DataDictionary var15 = var14.getDataDictionary();
         ConglomerateDescriptor var17 = var15.getConglomerateDescriptor(this.indexCID);
         UUID var19 = var17.getTableID();
         TableDescriptor var20 = var15.getTableDescriptor(var19);
         String var10 = var20.getName();
         if (var13 == null) {
            ConstraintDescriptor var11 = var15.getConstraintDescriptor(var20, var17.getUUID());
            var13 = var11.getConstraintName();
         }

         StandardException var21 = StandardException.newException("23505", new Object[]{var13, var10});
         throw var21;
      }
   }

   private ConglomerateController openIndexCC() throws StandardException {
      if (this.indexCC == null) {
         if (this.indexSCOCI == null) {
            this.indexCC = this.tc.openConglomerate(this.indexCID, false, 16388, this.lockMode, this.isolationLevel);
         } else {
            this.indexCC = this.tc.openCompiledConglomerate(false, 16388, this.lockMode, this.isolationLevel, this.indexSCOCI, this.indexDCOCI);
         }
      }

      return this.indexCC;
   }

   void open() throws StandardException {
   }

   void delete(ExecRow var1, RowLocation var2) throws StandardException {
      this.setOurIndexRow(var1, var2);
      this.setScan();
      this.doDelete();
   }

   void update(ExecRow var1, ExecRow var2, RowLocation var3) throws StandardException {
      this.setOurIndexRow(var1, var3);
      this.setOurUpdatedIndexRow(var2, var3);
      if (this.indexRowChanged()) {
         this.setScan();
         this.doDelete();
         this.insertForUpdate(var2, var3);
      }

   }

   void insert(ExecRow var1, RowLocation var2) throws StandardException {
      this.setOurIndexRow(var1, var2);
      this.doInsert();
   }

   void insertForUpdate(ExecRow var1, RowLocation var2) throws StandardException {
      this.setOurIndexRow(var1, var2);
      if (!this.irg.isUnique() && !this.irg.isUniqueWithDuplicateNulls() && !this.irg.hasDeferrableChecking()) {
         this.doInsert();
      } else {
         this.doDeferredInsert();
      }

   }

   void finish() throws StandardException {
      if (this.rowHolder != null) {
         CursorResultSet var2 = this.rowHolder.getResultSet();

         try {
            var2.open();

            ExecRow var1;
            while((var1 = var2.getNextRow()) != null) {
               this.insertAndCheckDups((ExecIndexRow)var1);
            }
         } finally {
            var2.close();
            if (!this.rowHolderPassedIn) {
               this.rowHolder.close();
            }

         }
      }

   }

   void close() throws StandardException {
      this.closeIndexCC();
      this.closeIndexSC();
      if (this.rowHolder != null && !this.rowHolderPassedIn) {
         this.rowHolder.close();
      }

      this.baseCC = null;
   }
}
