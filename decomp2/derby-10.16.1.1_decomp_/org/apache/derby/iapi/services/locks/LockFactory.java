package org.apache.derby.iapi.services.locks;

import java.util.Enumeration;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.shared.common.error.StandardException;

public interface LockFactory extends PropertySetCallback {
   CompatibilitySpace createCompatibilitySpace(LockOwner var1);

   boolean lockObject(CompatibilitySpace var1, Object var2, Lockable var3, Object var4, int var5) throws StandardException;

   int unlock(CompatibilitySpace var1, Object var2, Lockable var3, Object var4);

   void unlockGroup(CompatibilitySpace var1, Object var2);

   void unlockGroup(CompatibilitySpace var1, Object var2, Matchable var3);

   void transfer(CompatibilitySpace var1, Object var2, Object var3);

   boolean anyoneBlocked();

   boolean areLocksHeld(CompatibilitySpace var1, Object var2);

   boolean areLocksHeld(CompatibilitySpace var1);

   boolean zeroDurationlockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException;

   boolean isLockHeld(CompatibilitySpace var1, Object var2, Lockable var3, Object var4);

   int getWaitTimeout();

   void setLimit(CompatibilitySpace var1, Object var2, int var3, Limit var4);

   void clearLimit(CompatibilitySpace var1, Object var2);

   Enumeration makeVirtualLockTable();
}
