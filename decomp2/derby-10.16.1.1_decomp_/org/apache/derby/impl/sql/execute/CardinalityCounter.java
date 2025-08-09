package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class CardinalityCounter implements RowLocationRetRowSource {
   private RowLocationRetRowSource rowSource;
   private DataValueDescriptor[] prevKey;
   private long[] cardinality;
   private long numRows;

   public CardinalityCounter(RowLocationRetRowSource var1) {
      this.rowSource = var1;
   }

   public boolean needsRowLocation() {
      return this.rowSource.needsRowLocation();
   }

   public boolean needsRowLocationForDeferredCheckConstraints() {
      return this.rowSource.needsRowLocationForDeferredCheckConstraints();
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
      this.rowSource.offendingRowLocation(var1, var2);
   }

   public void rowLocation(RowLocation var1) throws StandardException {
      this.rowSource.rowLocation(var1);
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      DataValueDescriptor[] var1 = this.rowSource.getNextRowFromRowSource();
      if (var1 != null) {
         this.keepCount(var1);
      }

      return var1;
   }

   public boolean needsToClone() {
      return this.rowSource.needsToClone();
   }

   public FormatableBitSet getValidColumns() {
      return this.rowSource.getValidColumns();
   }

   public void closeRowSource() {
      this.rowSource.closeRowSource();
   }

   private DataValueDescriptor[] clone(DataValueDescriptor[] var1) {
      DataValueDescriptor[] var2 = new DataValueDescriptor[var1.length];

      for(int var3 = 0; var3 < var1.length - 1; ++var3) {
         var2[var3] = var1[var3].cloneValue(false);
      }

      return var2;
   }

   public void keepCount(DataValueDescriptor[] var1) throws StandardException {
      int var2 = var1.length - 1;
      ++this.numRows;
      if (this.prevKey == null) {
         this.prevKey = this.clone(var1);
         this.cardinality = new long[var1.length - 1];

         for(int var5 = 0; var5 < var2; ++var5) {
            this.cardinality[var5] = 1L;
         }

      } else {
         int var3;
         for(var3 = 0; var3 < var2 && !this.prevKey[var3].isNull(); ++var3) {
            if (this.prevKey[var3].compare(var1[var3]) != 0) {
               this.prevKey = null;
               this.prevKey = this.clone(var1);
               break;
            }
         }

         for(int var4 = var3; var4 < var2; ++var4) {
            int var10002 = this.cardinality[var4]++;
         }

      }
   }

   public long[] getCardinality() {
      return this.cardinality;
   }

   public long getRowCount() {
      return this.numRows;
   }
}
