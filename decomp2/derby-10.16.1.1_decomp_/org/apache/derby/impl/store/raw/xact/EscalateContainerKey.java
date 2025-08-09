package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.util.Matchable;

public final class EscalateContainerKey implements Matchable {
   private ContainerKey container_key;

   public EscalateContainerKey(ContainerKey var1) {
      this.container_key = var1;
   }

   public boolean match(Object var1) {
      return var1 instanceof RecordHandle ? this.container_key.equals(((RecordHandle)var1).getContainerId()) : false;
   }
}
