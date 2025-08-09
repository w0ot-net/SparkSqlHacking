package org.apache.derby.impl.services.locks;

import java.util.List;
import java.util.Map;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;

public interface Control {
   Lockable getLockable();

   LockControl getLockControl();

   Lock getLock(CompatibilitySpace var1, Object var2);

   Control shallowClone();

   ActiveLock firstWaiter();

   boolean isEmpty();

   boolean unlock(Latch var1, int var2);

   void addWaiters(Map var1);

   Lock getFirstGrant();

   List getGranted();

   List getWaiting();

   boolean isGrantable(boolean var1, CompatibilitySpace var2, Object var3);
}
