package org.datanucleus.state;

import java.util.HashMap;
import java.util.Map;

public class LockManagerImpl implements LockManager {
   Map requiredLockModesById = null;

   public void close() {
      this.clear();
      this.requiredLockModesById = null;
   }

   public void lock(Object id, short lockMode) {
      if (this.requiredLockModesById == null) {
         this.requiredLockModesById = new HashMap();
      }

      this.requiredLockModesById.put(id, lockMode);
   }

   public short getLockMode(Object id) {
      if (this.requiredLockModesById != null) {
         Short lockMode = (Short)this.requiredLockModesById.get(id);
         if (lockMode != null) {
            return lockMode;
         }
      }

      return 0;
   }

   public void clear() {
      if (this.requiredLockModesById != null) {
         this.requiredLockModesById.clear();
         this.requiredLockModesById = null;
      }

   }

   public void lock(ObjectProvider sm, short lockMode) {
      sm.lock(lockMode);
      if (lockMode == 3 || lockMode == 4) {
         sm.locate();
      }

   }

   public void unlock(ObjectProvider sm) {
      sm.unlock();
   }

   public short getLockMode(ObjectProvider sm) {
      return sm.getLockMode();
   }
}
