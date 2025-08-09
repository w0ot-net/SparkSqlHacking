package org.apache.derby.impl.services.locks;

import java.util.Map;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.shared.common.error.StandardException;

interface LockTable {
   Lock lockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException;

   void unlock(Latch var1, int var2);

   Lock unlockReference(CompatibilitySpace var1, Lockable var2, Object var3, Map var4);

   void oneMoreWaiter();

   void oneLessWaiter();

   boolean anyoneBlocked();

   boolean zeroDurationLockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException;

   Map shallowClone();

   void setDeadlockTimeout(int var1);

   void setWaitTimeout(int var1);

   int getWaitTimeout();

   void setDeadlockTrace(boolean var1);

   void addWaiters(Map var1);
}
