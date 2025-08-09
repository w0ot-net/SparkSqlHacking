package org.apache.derby.impl.sql.execute;

import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.TargetResultSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;
import org.w3c.dom.Element;

class TemporaryRowHolderResultSet implements CursorResultSet, NoPutResultSet, Cloneable {
   private ExecRow[] rowArray;
   private int numRowsOut;
   private ScanController scan;
   private TransactionController tc;
   private boolean isOpen;
   private boolean finished;
   private ExecRow currentRow;
   private ResultDescription resultDescription;
   private boolean isAppendable;
   private long positionIndexConglomId;
   private boolean isVirtualMemHeap;
   private boolean currRowFromMem;
   private TemporaryRowHolderImpl holder;
   ConglomerateController heapCC;
   private RowLocation baseRowLocation;
   DataValueDescriptor[] indexRow;
   ScanController indexsc;

   public TemporaryRowHolderResultSet(TransactionController var1, ExecRow[] var2, ResultDescription var3, boolean var4, TemporaryRowHolderImpl var5) {
      this(var1, var2, var3, var4, false, 0L, var5);
   }

   public TemporaryRowHolderResultSet(TransactionController var1, ExecRow[] var2, ResultDescription var3, boolean var4, boolean var5, long var6, TemporaryRowHolderImpl var8) {
      this.isAppendable = false;
      this.tc = var1;
      this.rowArray = var2;
      this.resultDescription = var3;
      this.numRowsOut = 0;
      this.isOpen = false;
      this.finished = false;
      this.isVirtualMemHeap = var4;
      this.isAppendable = var5;
      this.positionIndexConglomId = var6;
      this.holder = var8;
   }

   public void reset(ExecRow[] var1) {
      this.rowArray = var1;
      this.numRowsOut = 0;
      this.isOpen = false;
      this.finished = false;
   }

   public void reStartScan(long var1, long var3) throws StandardException {
      if (this.isAppendable) {
         this.positionIndexConglomId = var3;
         this.setupPositionBasedScan((long)this.numRowsOut);
      } else {
         --this.numRowsOut;
      }

   }

   private static int[] supersetofAllColumns(int[] var0, int[] var1) {
      int var2 = var0.length + var1.length;
      int[] var3 = new int[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = -1;
      }

      for(int var9 = 0; var9 < var0.length; ++var9) {
         var3[var9] = var0[var9];
      }

      int var10 = var0.length;

      for(int var5 = 0; var5 < var1.length; ++var5) {
         boolean var6 = false;

         for(int var7 = 0; var7 < var10; ++var7) {
            if (var3[var7] == var1[var5]) {
               var6 = true;
               break;
            }
         }

         if (!var6) {
            var3[var10] = var1[var5];
            ++var10;
         }
      }

      var3 = shrinkArray(var3);
      Arrays.sort(var3);
      return var3;
   }

   private static int[] shrinkArray(int[] var0) {
      int var1 = 0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var0[var3] != -1) {
            ++var1;
         }
      }

      if (var1 > 0) {
         int[] var6 = new int[var1];
         int var4 = 0;

         for(int var5 = 0; var5 < var2; ++var5) {
            if (var0[var5] != -1) {
               var6[var4++] = var0[var5];
            }
         }

         return var6;
      } else {
         return null;
      }
   }

   private static int[] justTheRequiredColumnsPositions(int[] var0) {
      int var1 = 0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var0[var3] != -1) {
            ++var1;
         }
      }

      if (var1 > 0) {
         int[] var6 = new int[var1];
         int var4 = 0;

         for(int var5 = 0; var5 < var2; ++var5) {
            if (var0[var5] != -1) {
               var6[var4++] = var5 + 1;
            }
         }

         return var6;
      } else {
         return null;
      }
   }

   public static TemporaryRowHolderResultSet getNewRSOnCurrentRow(TriggerDescriptor var0, Activation var1, CursorResultSet var2, int[] var3) throws StandardException {
      DataDictionary var5 = var1.getLanguageConnectionContext().getDataDictionary();
      if (!var5.checkVersion(210, (String)null)) {
         TemporaryRowHolderImpl var15 = new TemporaryRowHolderImpl(var1, (Properties)null, var2.getResultDescription());
         var15.insert(var2.getCurrentRow());
         return (TemporaryRowHolderResultSet)var15.getResultSet();
      } else {
         int[] var6 = var0.getReferencedColsInTriggerAction();
         int[] var7 = var0.getReferencedCols();
         TemporaryRowHolderImpl var4;
         if (var7 != null && var0.isRowTrigger() && var6 != null && var6.length != 0) {
            int[] var8 = supersetofAllColumns(var7, var6);
            int var9 = var8.length;
            int[] var10 = new int[var9];
            int[] var11;
            if (var3 != null) {
               var11 = justTheRequiredColumnsPositions(var3);
            } else {
               int var12 = var0.getTableDescriptor().getNumberOfColumns();
               var11 = new int[var12];

               for(int var13 = 1; var13 <= var12; var11[var13 - 1] = var13++) {
               }
            }

            int var16 = 0;

            for(int var17 = 0; var17 < var9; ++var17) {
               while(var16 < var11.length) {
                  if (var11[var16] == var8[var17]) {
                     var10[var17] = var16 + 1;
                     break;
                  }

                  ++var16;
               }
            }

            var4 = new TemporaryRowHolderImpl(var1, (Properties)null, var1.getLanguageConnectionContext().getLanguageFactory().getResultDescription(var2.getResultDescription(), var10));
            ExecRow var18 = var1.getExecutionFactory().getValueRow(var9);

            for(int var14 = 0; var14 < var9; ++var14) {
               var18.setColumn(var14 + 1, var2.getCurrentRow().getColumn(var10[var14]));
            }

            var4.insert(var18);
         } else {
            var4 = new TemporaryRowHolderImpl(var1, (Properties)null, var2.getResultDescription());
            var4.insert(var2.getCurrentRow());
         }

         return (TemporaryRowHolderResultSet)var4.getResultSet();
      }
   }

   public void markAsTopResultSet() {
   }

   public void openCore() throws StandardException {
      this.numRowsOut = 0;
      this.isOpen = true;
      this.currentRow = null;
      if (this.isAppendable) {
         this.setupPositionBasedScan((long)this.numRowsOut);
      }

   }

   public void reopenCore() throws StandardException {
      this.numRowsOut = 0;
      this.isOpen = true;
      this.currentRow = null;
      if (this.isAppendable) {
         this.setupPositionBasedScan((long)this.numRowsOut);
      } else {
         if (this.scan != null) {
            this.scan.reopenScan((DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         }

      }
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (!this.isOpen) {
         return (ExecRow)null;
      } else if (this.isAppendable) {
         return this.getNextAppendedRow();
      } else if (this.isVirtualMemHeap && this.holder.lastArraySlot >= 0) {
         ++this.numRowsOut;
         this.currentRow = this.rowArray[this.holder.lastArraySlot];
         this.currRowFromMem = true;
         return this.currentRow;
      } else if (this.numRowsOut++ <= this.holder.lastArraySlot) {
         this.currentRow = this.rowArray[this.numRowsOut - 1];
         return this.currentRow;
      } else if (this.holder.getTemporaryConglomId() == 0L) {
         return (ExecRow)null;
      } else {
         if (this.scan == null) {
            this.scan = this.tc.openScan(this.holder.getTemporaryConglomId(), false, 0, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         } else if (this.isVirtualMemHeap && this.holder.state == 1) {
            this.holder.state = 2;
            this.scan.reopenScan((DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         }

         if (this.scan.next()) {
            this.currentRow = this.rowArray[0].getNewNullRow();
            this.scan.fetch(this.currentRow.getRowArray());
            this.currRowFromMem = false;
            return this.currentRow;
         } else {
            return (ExecRow)null;
         }
      }
   }

   public void deleteCurrentRow() throws StandardException {
      if (this.currRowFromMem) {
         if (this.holder.lastArraySlot > 0) {
            this.rowArray[this.holder.lastArraySlot] = null;
         }

         --this.holder.lastArraySlot;
      } else {
         if (this.baseRowLocation == null) {
            this.baseRowLocation = this.scan.newRowLocationTemplate();
         }

         this.scan.fetchLocation(this.baseRowLocation);
         if (this.heapCC == null) {
            this.heapCC = this.tc.openConglomerate(this.holder.getTemporaryConglomId(), false, 4, 7, 5);
         }

         this.heapCC.delete(this.baseRowLocation);
      }

   }

   private void setupPositionBasedScan(long var1) throws StandardException {
      if (this.holder.getTemporaryConglomId() != 0L) {
         if (this.heapCC == null) {
            this.heapCC = this.tc.openConglomerate(this.holder.getTemporaryConglomId(), false, 0, 7, 5);
         }

         this.currentRow = this.rowArray[0].getNewNullRow();
         this.indexRow = new DataValueDescriptor[2];
         this.indexRow[0] = new SQLLongint(var1);
         this.indexRow[1] = this.heapCC.newRowLocationTemplate();
         DataValueDescriptor[] var3 = new DataValueDescriptor[]{new SQLLongint(var1)};
         if (this.indexsc == null) {
            this.indexsc = this.tc.openScan(this.positionIndexConglomId, false, 0, 7, 5, (FormatableBitSet)null, var3, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
         } else {
            this.indexsc.reopenScan(var3, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);
         }

      }
   }

   private ExecRow getNextAppendedRow() throws StandardException {
      if (this.indexsc == null) {
         return null;
      } else if (!this.indexsc.fetchNext(this.indexRow)) {
         return null;
      } else {
         RowLocation var1 = (RowLocation)this.indexRow[1];
         this.heapCC.fetch(var1, this.currentRow.getRowArray(), (FormatableBitSet)null);
         ++this.numRowsOut;
         return this.currentRow;
      }
   }

   public int getPointOfAttachment() {
      return -1;
   }

   public int getScanIsolationLevel() {
      return 5;
   }

   public void setTargetResultSet(TargetResultSet var1) {
   }

   public void setNeedsRowLocation(boolean var1) {
   }

   public double getEstimatedRowCount() {
      return (double)0.0F;
   }

   public int resultSetNumber() {
      return 0;
   }

   public void setCurrentRow(ExecRow var1) {
      this.currentRow = var1;
   }

   public void clearCurrentRow() {
      this.currentRow = null;
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.currentRow;
   }

   public RowLocation getRowLocation() {
      return (RowLocation)null;
   }

   public void close() throws StandardException {
      this.isOpen = false;
      this.numRowsOut = 0;
      this.currentRow = null;
      if (this.scan != null) {
         this.scan.close();
         this.scan = null;
      }

   }

   public boolean returnsRows() {
      return true;
   }

   public long modifiedRowCount() {
      return 0L;
   }

   public ResultDescription getResultDescription() {
      return this.resultDescription;
   }

   public void open() throws StandardException {
      this.openCore();
   }

   public ExecRow getAbsoluteRow(int var1) throws StandardException {
      return null;
   }

   public ExecRow getRelativeRow(int var1) throws StandardException {
      return null;
   }

   public ExecRow setBeforeFirstRow() throws StandardException {
      return null;
   }

   public ExecRow getFirstRow() throws StandardException {
      return null;
   }

   public ExecRow getNextRow() throws StandardException {
      return this.getNextRowCore();
   }

   public ExecRow getPreviousRow() throws StandardException {
      return null;
   }

   public ExecRow getLastRow() throws StandardException {
      return null;
   }

   public ExecRow setAfterLastRow() throws StandardException {
      return null;
   }

   public boolean checkRowPosition(int var1) {
      return false;
   }

   public int getRowNumber() {
      return 0;
   }

   public void cleanUp() throws StandardException {
      this.close();
   }

   public boolean isClosed() {
      return !this.isOpen;
   }

   public void finish() throws StandardException {
      this.finished = true;
      this.close();
   }

   public long getExecuteTime() {
      return 0L;
   }

   public ResultSet getAutoGeneratedKeysResultset() {
      return (ResultSet)null;
   }

   public Timestamp getBeginExecutionTimestamp() {
      return (Timestamp)null;
   }

   public Timestamp getEndExecutionTimestamp() {
      return (Timestamp)null;
   }

   public long getTimeSpent(int var1) {
      return 0L;
   }

   public NoPutResultSet[] getSubqueryTrackingArray(int var1) {
      return (NoPutResultSet[])null;
   }

   public String getCursorName() {
      return (String)null;
   }

   public boolean requiresRelocking() {
      return false;
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      return null;
   }

   public boolean needsToClone() {
      return false;
   }

   public FormatableBitSet getValidColumns() {
      return null;
   }

   public void closeRowSource() {
   }

   public boolean needsRowLocation() {
      return false;
   }

   public void setHasDeferrableChecks() {
   }

   public boolean needsRowLocationForDeferredCheckConstraints() {
      return false;
   }

   public void rowLocation(RowLocation var1) throws StandardException {
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
   }

   public void positionScanAtRowLocation(RowLocation var1) throws StandardException {
   }

   public boolean isForUpdate() {
      return false;
   }

   public Object clone() {
      Object var1 = null;

      try {
         var1 = super.clone();
      } catch (CloneNotSupportedException var3) {
      }

      return var1;
   }

   public void addWarning(SQLWarning var1) {
      this.getActivation().addWarning(var1);
   }

   public SQLWarning getWarnings() {
      return null;
   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
   }

   public void markRowAsDeleted() throws StandardException {
   }

   public final Activation getActivation() {
      return this.holder.activation;
   }

   public Element toXML(Element var1, String var2) throws Exception {
      return BasicNoPutResultSetImpl.childrenToXML(BasicNoPutResultSetImpl.toXML(var1, var2, this), this);
   }
}
