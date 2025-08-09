package org.apache.derby.iapi.services.locks;

import java.util.Hashtable;

public class ShExLockable implements Lockable {
   public boolean lockerAlwaysCompatible() {
      return true;
   }

   public boolean requestCompatible(Object var1, Object var2) {
      ShExQual var3 = (ShExQual)var1;
      ShExQual var4 = (ShExQual)var2;
      return var3.getLockState() == 0 && var4.getLockState() == 0;
   }

   public void lockEvent(Latch var1) {
   }

   public void unlockEvent(Latch var1) {
   }

   public boolean lockAttributes(int var1, Hashtable var2) {
      if ((var1 & 4) == 0) {
         return false;
      } else {
         var2.put("CONTAINERID", -1);
         var2.put("LOCKNAME", this.toString());
         var2.put("TYPE", "ShExLockable");
         return true;
      }
   }
}
