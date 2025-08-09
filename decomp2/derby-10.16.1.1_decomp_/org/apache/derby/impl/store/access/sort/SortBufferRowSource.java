package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.conglomerate.ScanControllerRowSource;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class SortBufferRowSource extends Scan implements ScanControllerRowSource {
   SortBuffer sortBuffer = null;
   protected TransactionManager tran = null;
   private int maxFreeListSize;
   private boolean writingToDisk;
   private SortObserver sortObserver;

   SortBufferRowSource(SortBuffer var1, TransactionManager var2, SortObserver var3, boolean var4, int var5) {
      this.sortBuffer = var1;
      this.tran = var2;
      this.sortObserver = var3;
      this.writingToDisk = var4;
      this.maxFreeListSize = var5;
   }

   public DataValueDescriptor[] getNextRowFromRowSource() {
      if (this.sortBuffer == null) {
         return null;
      } else {
         DataValueDescriptor[] var1 = this.sortBuffer.removeFirst();
         if (var1 != null && this.writingToDisk) {
            this.sortObserver.addToFreeList(var1, this.maxFreeListSize);
         }

         return var1;
      }
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

   public void close() {
      if (this.sortBuffer != null) {
         this.sortBuffer.close();
         this.sortBuffer = null;
      }

      this.tran.closeMe((ScanManager)this);
   }

   public boolean closeForEndTransaction(boolean var1) {
      this.close();
      return true;
   }

   public void closeRowSource() {
      this.close();
   }

   public boolean next() throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void fetchWithoutQualify(DataValueDescriptor[] var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void fetch(DataValueDescriptor[] var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public final boolean fetchNext(DataValueDescriptor[] var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }
}
