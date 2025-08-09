package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class TruncateOnCommit extends ContainerHandleActionOnCommit {
   private boolean commitAsWell;

   public TruncateOnCommit(ContainerKey var1, boolean var2) {
      super(var1);
      this.commitAsWell = var2;
   }

   public void update(DerbyObservable var1, Object var2) {
      if (var2.equals(RawTransaction.ABORT) || var2.equals(RawTransaction.SAVEPOINT_ROLLBACK) || this.commitAsWell && var2.equals(RawTransaction.COMMIT)) {
         this.openContainerAndDoIt((RawTransaction)var1);
      }

      if (var2.equals(RawTransaction.COMMIT) || var2.equals(RawTransaction.ABORT) || var2.equals(this.identity)) {
         var1.deleteObserver(this);
      }

   }

   protected void doIt(BaseContainerHandle var1) throws StandardException {
      var1.container.truncate(var1);
   }

   public boolean equals(Object var1) {
      if (var1 instanceof TruncateOnCommit) {
         return ((TruncateOnCommit)var1).commitAsWell != this.commitAsWell ? false : super.equals(var1);
      } else {
         return false;
      }
   }
}
