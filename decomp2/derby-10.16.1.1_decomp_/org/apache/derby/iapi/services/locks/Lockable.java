package org.apache.derby.iapi.services.locks;

import java.util.Hashtable;

public interface Lockable {
   void lockEvent(Latch var1);

   boolean requestCompatible(Object var1, Object var2);

   boolean lockerAlwaysCompatible();

   void unlockEvent(Latch var1);

   boolean lockAttributes(int var1, Hashtable var2);
}
