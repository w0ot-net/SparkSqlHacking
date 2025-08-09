package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.shared.common.error.StandardException;

public class SortBufferScan extends SortScan {
   protected SortBuffer sortBuffer;

   SortBufferScan(MergeSort var1, TransactionManager var2, SortBuffer var3, boolean var4) {
      super(var1, var2, var4);
      this.sortBuffer = var3;
   }

   public boolean next() throws StandardException {
      this.current = this.sortBuffer.removeFirst();
      return this.current != null;
   }

   public boolean closeForEndTransaction(boolean var1) {
      if (!var1 && this.hold) {
         return false;
      } else {
         this.close();
         return true;
      }
   }

   public void close() {
      if (super.sort != null) {
         this.sort.doneScanning(this, this.sortBuffer);
         this.sortBuffer = null;
      }

      super.close();
   }
}
