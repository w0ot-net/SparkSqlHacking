package org.apache.derby.iapi.services.locks;

public interface LockOwner {
   boolean noWait();

   boolean isNestedOwner();

   boolean nestsUnder(LockOwner var1);
}
