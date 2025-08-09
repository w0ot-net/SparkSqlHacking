package org.apache.derby.impl.store.access.sort;

import java.util.Vector;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.conglomerate.ScanControllerRowSource;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class MergeScanRowSource extends MergeScan implements ScanControllerRowSource {
   MergeScanRowSource(MergeSort var1, TransactionManager var2, SortBuffer var3, Vector var4, SortObserver var5, boolean var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public boolean next() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      DataValueDescriptor[] var1 = this.sortBuffer.removeFirst();
      if (var1 != null) {
         this.mergeARow(this.sortBuffer.getLastAux());
      }

      return var1;
   }

   public boolean needsRowLocation() {
      return false;
   }

   public boolean needsRowLocationForDeferredCheckConstraints() {
      return false;
   }

   public boolean needsToClone() {
      return false;
   }

   public void rowLocation(RowLocation var1) {
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
   }

   public FormatableBitSet getValidColumns() {
      return null;
   }

   public void closeRowSource() {
      this.close();
   }
}
