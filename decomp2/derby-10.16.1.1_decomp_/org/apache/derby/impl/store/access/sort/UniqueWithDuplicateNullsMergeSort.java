package org.apache.derby.impl.store.access.sort;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

final class UniqueWithDuplicateNullsMergeSort extends MergeSort {
   protected int compare(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      int var3 = this.columnOrdering.length;
      boolean var5 = true;

      for(int var6 = 0; var6 < var3; ++var6) {
         if (var6 == var3 - 1 && var5) {
            if (!this.sortObserver.deferred()) {
               return 0;
            }

            this.sortObserver.rememberDuplicate(var1);
         }

         int var7 = this.columnOrderingMap[var6];
         boolean var8 = this.columnOrderingNullsLowMap[var6];
         int var4;
         if ((var4 = var1[var7].compare(var2[var7], var8)) != 0) {
            if (this.columnOrderingAscendingMap[var6]) {
               return var4;
            }

            return -var4;
         }

         if (var1[var7].isNull()) {
            var5 = false;
         }
      }

      return 0;
   }
}
