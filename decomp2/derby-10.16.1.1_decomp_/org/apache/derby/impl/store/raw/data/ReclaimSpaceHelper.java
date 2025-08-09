package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class ReclaimSpaceHelper {
   public static int reclaimSpace(BaseDataFileFactory var0, RawTransaction var1, ReclaimSpace var2) throws StandardException {
      if (var2.reclaimWhat() == 1) {
         return reclaimContainer(var0, var1, var2);
      } else {
         LockingPolicy var3 = var1.newLockingPolicy(1, 5, true);
         ContainerHandle var4 = openContainerNW(var1, var3, var2.getContainerId());
         if (var4 == null) {
            var1.abort();
            return var2.incrAttempts() < 3 ? 2 : 1;
         } else if (var2.reclaimWhat() == 2) {
            Page var12 = var4.getPageNoWait(var2.getPageId().getPageNumber());
            if (var12 != null) {
               var4.removePage(var12);
            }

            var1.commit();
            return 1;
         } else {
            RecordHandle var5 = var2.getHeadRowHandle();
            if (!var3.lockRecordForWrite(var1, var5, false, false)) {
               var1.abort();
               return var2.incrAttempts() < 3 ? 2 : 1;
            } else if (var2.reclaimWhat() == 3) {
               var4.compactRecord(var5);
               var1.commitNoSync(1);
               return 1;
            } else {
               long var6 = ((PageKey)var5.getPageId()).getPageNumber();
               StoredPage var8 = (StoredPage)var4.getPage(var6);
               if (var8 == null) {
                  var1.abort();
                  return 1;
               } else {
                  try {
                     var8.removeOrphanedColumnChain(var2, var4);
                  } finally {
                     var8.unlatch();
                  }

                  var1.commitNoSync(1);
                  return 1;
               }
            }
         }
      }
   }

   private static int reclaimContainer(BaseDataFileFactory var0, RawTransaction var1, ReclaimSpace var2) throws StandardException {
      LockingPolicy var3 = var1.newLockingPolicy(2, 5, true);
      RawContainerHandle var4 = var1.openDroppedContainer(var2.getContainerId(), var3);
      if (var4 != null && var4.getContainerStatus() != 1 && var4.getContainerStatus() != 4) {
         ContainerOperation var5 = new ContainerOperation(var4, (byte)4);
         var4.preDirty(true);

         try {
            var1.logAndDo(var5);
         } finally {
            var4.preDirty(false);
         }

         var4.close();
         var1.commit();
      } else {
         if (var4 != null) {
            var4.close();
         }

         var1.abort();
      }

      return 1;
   }

   private static ContainerHandle openContainerNW(Transaction var0, LockingPolicy var1, ContainerKey var2) throws StandardException {
      ContainerHandle var3 = null;

      try {
         var3 = var0.openContainer(var2, var1, 132);
      } catch (StandardException var5) {
         if (!var5.isLockTimeout()) {
            throw var5;
         }
      }

      return var3;
   }
}
