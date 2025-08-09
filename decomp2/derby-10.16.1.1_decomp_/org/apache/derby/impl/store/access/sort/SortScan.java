package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public abstract class SortScan extends Scan {
   protected MergeSort sort = null;
   protected TransactionManager tran = null;
   protected DataValueDescriptor[] current;
   protected boolean hold;

   SortScan(MergeSort var1, TransactionManager var2, boolean var3) {
      this.sort = var1;
      this.tran = var2;
      this.hold = var3;
   }

   public final boolean fetchNext(DataValueDescriptor[] var1) throws StandardException {
      boolean var2 = this.next();
      if (var2) {
         this.fetch(var1);
      }

      return var2;
   }

   public final void fetch(DataValueDescriptor[] var1) throws StandardException {
      if (this.current == null) {
         throw StandardException.newException("XSAS1.S", new Object[0]);
      } else {
         this.sort.checkColumnTypes(var1);
         System.arraycopy(this.current, 0, var1, 0, var1.length);
      }
   }

   public final void fetchWithoutQualify(DataValueDescriptor[] var1) throws StandardException {
      throw StandardException.newException("XSAS0.S", new Object[0]);
   }

   public void close() {
      this.sort = null;
      this.current = null;
      this.tran.closeMe((ScanManager)this);
   }
}
