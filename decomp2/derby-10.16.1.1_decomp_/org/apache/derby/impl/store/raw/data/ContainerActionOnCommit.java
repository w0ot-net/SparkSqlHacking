package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.monitor.DerbyObserver;
import org.apache.derby.iapi.store.raw.ContainerKey;

abstract class ContainerActionOnCommit implements DerbyObserver {
   protected ContainerKey identity;

   protected ContainerActionOnCommit(ContainerKey var1) {
      this.identity = var1;
   }

   public int hashCode() {
      return this.identity.hashCode();
   }

   public boolean equals(Object var1) {
      if (var1 instanceof ContainerActionOnCommit) {
         return !this.identity.equals(((ContainerActionOnCommit)var1).identity) ? false : this.getClass().equals(var1.getClass());
      } else {
         return false;
      }
   }
}
