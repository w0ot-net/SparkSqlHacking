package org.apache.derby.impl.services.locks;

public final class ConcurrentPool extends AbstractPool {
   protected LockTable createLockTable() {
      return new ConcurrentLockSet(this);
   }
}
