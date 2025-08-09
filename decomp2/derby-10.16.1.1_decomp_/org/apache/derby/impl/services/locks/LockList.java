package org.apache.derby.impl.services.locks;

import java.util.Enumeration;
import org.apache.derby.iapi.services.locks.Lockable;

class LockList implements Enumeration {
   private Enumeration lockGroup;

   LockList(Enumeration var1) {
      this.lockGroup = var1;
   }

   public boolean hasMoreElements() {
      return this.lockGroup.hasMoreElements();
   }

   public Lockable nextElement() {
      return ((Lock)this.lockGroup.nextElement()).getLockable();
   }
}
