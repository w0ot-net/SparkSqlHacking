package org.apache.derby.impl.store.access.sort;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class MergeScan extends SortScan {
   protected SortBuffer sortBuffer;
   protected Vector mergeRuns;
   protected StreamContainerHandle[] openScans;
   private SortObserver sortObserver;

   MergeScan(MergeSort var1, TransactionManager var2, SortBuffer var3, Vector var4, SortObserver var5, boolean var6) {
      super(var1, var2, var6);
      this.sortBuffer = var3;
      this.mergeRuns = var4;
      this.tran = var2;
      this.sortObserver = var5;
   }

   public boolean next() throws StandardException {
      this.current = this.sortBuffer.removeFirst();
      if (this.current != null) {
         this.mergeARow(this.sortBuffer.getLastAux());
      }

      return this.current != null;
   }

   public void close() {
      if (this.openScans != null) {
         for(int var1 = 0; var1 < this.openScans.length; ++var1) {
            if (this.openScans[var1] != null) {
               this.openScans[var1].close();
            }

            this.openScans[var1] = null;
         }

         this.openScans = null;
      }

      if (super.sort != null) {
         this.sort.doneScanning(this, this.sortBuffer, this.mergeRuns);
         this.sortBuffer = null;
         this.mergeRuns = null;
      }

      super.close();
   }

   public boolean closeForEndTransaction(boolean var1) {
      if (this.hold && !var1) {
         return false;
      } else {
         this.close();
         return true;
      }
   }

   public boolean init(TransactionManager var1) throws StandardException {
      this.sortBuffer.reset();
      this.openScans = new StreamContainerHandle[this.mergeRuns.size()];
      if (this.openScans == null) {
         return false;
      } else {
         int var2 = 0;

         long var4;
         Transaction var6;
         byte var7;
         for(Enumeration var3 = this.mergeRuns.elements(); var3.hasMoreElements(); this.openScans[var2++] = var6.openStreamContainer((long)var7, var4, this.hold)) {
            var4 = (Long)var3.nextElement();
            var6 = var1.getRawStoreXact();
            var7 = -1;
         }

         for(int var8 = 0; var8 < this.openScans.length; ++var8) {
            this.mergeARow(var8);
         }

         return true;
      }
   }

   void mergeARow(int var1) throws StandardException {
      DataValueDescriptor[] var2;
      do {
         var2 = this.sortObserver.getArrayClone();
         if (!this.openScans[var1].fetchNext(var2)) {
            this.openScans[var1].close();
            this.openScans[var1] = null;
            return;
         }

         this.sortBuffer.setNextAux(var1);
      } while(this.sortBuffer.insert(var2) == 1);

   }
}
