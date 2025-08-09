package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class DropOnCommit extends ContainerActionOnCommit {
   protected boolean isStreamContainer = false;

   public DropOnCommit(ContainerKey var1) {
      super(var1);
   }

   public DropOnCommit(ContainerKey var1, boolean var2) {
      super(var1);
      this.isStreamContainer = var2;
   }

   public void update(DerbyObservable var1, Object var2) {
      if (var2.equals(RawTransaction.COMMIT) || var2.equals(RawTransaction.ABORT)) {
         RawTransaction var3 = (RawTransaction)var1;

         try {
            if (this.isStreamContainer) {
               var3.dropStreamContainer(this.identity.getSegmentId(), this.identity.getContainerId());
            } else {
               var3.dropContainer(this.identity);
            }
         } catch (StandardException var5) {
            var3.setObserverException(var5);
         }

         var1.deleteObserver(this);
      }

   }
}
