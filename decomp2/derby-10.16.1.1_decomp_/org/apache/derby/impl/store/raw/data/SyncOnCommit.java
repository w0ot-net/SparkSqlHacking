package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class SyncOnCommit extends ContainerHandleActionOnCommit {
   public SyncOnCommit(ContainerKey var1) {
      super(var1);
   }

   public void update(DerbyObservable var1, Object var2) {
      if (var2.equals(RawTransaction.COMMIT)) {
         this.openContainerAndDoIt((RawTransaction)var1);
      }

      if (var2.equals(RawTransaction.COMMIT) || var2.equals(RawTransaction.ABORT) || var2.equals(this.identity)) {
         var1.deleteObserver(this);
      }

   }

   protected void doIt(BaseContainerHandle var1) throws StandardException {
      var1.container.flushAll();
   }
}
