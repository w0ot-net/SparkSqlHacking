package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ContainerHandleActionOnCommit extends ContainerActionOnCommit {
   public ContainerHandleActionOnCommit(ContainerKey var1) {
      super(var1);
   }

   public void openContainerAndDoIt(RawTransaction var1) {
      BaseContainerHandle var2 = null;

      try {
         var2 = (BaseContainerHandle)var1.openContainer(this.identity, (LockingPolicy)null, 1028);
         if (var2 != null) {
            try {
               this.doIt(var2);
            } catch (StandardException var8) {
               var1.setObserverException(var8);
            }
         }
      } catch (StandardException var9) {
         if (this.identity.getSegmentId() != -1L) {
            var1.setObserverException(var9);
         }
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

   }

   protected abstract void doIt(BaseContainerHandle var1) throws StandardException;
}
