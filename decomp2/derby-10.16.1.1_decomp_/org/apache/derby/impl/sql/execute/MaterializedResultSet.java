package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class MaterializedResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public NoPutResultSet source;
   private ExecRow materializedRowBuffer;
   protected long materializedCID;
   public boolean materializedCreated;
   private boolean fromSource = true;
   protected ConglomerateController materializedCC;
   protected ScanController materializedScan;
   private TransactionController tc;
   private boolean sourceDrained;
   public long createTCTime;
   public long fetchTCTime;

   public MaterializedResultSet(NoPutResultSet var1, Activation var2, int var3, double var4, double var6) throws StandardException {
      super(var2, var3, var4, var6);
      this.source = var1;
      this.tc = var2.getTransactionController();
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();

      while(!this.sourceDrained) {
         this.getNextRowFromSource();
      }

      this.fromSource = false;
      if (this.materializedScan != null) {
         this.materializedScan.close();
      }

      if (this.materializedCID != 0L) {
         this.materializedScan = this.tc.openScan(this.materializedCID, false, 0, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         this.isOpen = true;
      }

      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            if (this.fromSource) {
               var1 = this.getNextRowFromSource();
            } else {
               var1 = this.getNextRowFromTempTable();
            }

            if (var1 != null) {
               ++this.rowsSeen;
            }

            this.setCurrentRow(var1);
            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var1;
         }
      }
   }

   private ExecRow getNextRowFromSource() throws StandardException {
      if (this.sourceDrained) {
         return null;
      } else {
         ExecRow var1 = null;
         Object var2 = null;
         var1 = this.source.getNextRowCore();
         if (var1 != null) {
            long var3 = this.getCurrentTimeMillis();
            if (this.materializedRowBuffer == null) {
               this.materializedRowBuffer = var1.getClone();
               this.tc = this.activation.getTransactionController();
               this.materializedCID = this.tc.createConglomerate("heap", this.materializedRowBuffer.getRowArray(), (ColumnOrdering[])null, (int[])null, (Properties)null, 3);
               this.materializedCreated = true;
               this.materializedCC = this.tc.openConglomerate(this.materializedCID, false, 4, 7, 5);
            }

            this.materializedCC.insert(var1.getRowArray());
            this.createTCTime += this.getElapsedMillis(var3);
         } else {
            this.sourceDrained = true;
         }

         return var1;
      }
   }

   private ExecRow getNextRowFromTempTable() throws StandardException {
      long var1 = this.getCurrentTimeMillis();
      if (this.materializedScan != null && this.materializedScan.fetchNext(this.materializedRowBuffer.getRowArray())) {
         this.fetchTCTime += this.getElapsedMillis(var1);
         return this.materializedRowBuffer;
      } else {
         return null;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.currentRow = null;
         this.source.close();
         if (this.materializedScan != null) {
            this.materializedScan.close();
         }

         this.materializedScan = null;
         if (this.materializedCC != null) {
            this.materializedCC.close();
         }

         this.materializedCC = null;
         if (this.materializedCreated) {
            this.tc.dropConglomerate(this.materializedCID);
         }

         this.materializedCreated = false;
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() {
      return this.currentRow;
   }
}
