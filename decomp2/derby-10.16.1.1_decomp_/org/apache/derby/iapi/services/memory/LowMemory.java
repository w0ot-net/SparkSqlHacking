package org.apache.derby.iapi.services.memory;

public class LowMemory {
   private long lowMemory;
   private long whenLowMemorySet;

   public void setLowMemory() {
      if (this.lowMemory == 0L) {
         boolean var1 = false;

         for(int var2 = 0; var2 < 5; ++var2) {
            System.gc();
            System.runFinalization();

            try {
               Thread.sleep(50L);
            } catch (InterruptedException var6) {
               var1 = true;
            }
         }

         if (var1) {
            Thread.currentThread().interrupt();
         }
      }

      synchronized(this) {
         if (this.lowMemory == 0L) {
            this.lowMemory = Runtime.getRuntime().freeMemory();
            this.whenLowMemorySet = System.currentTimeMillis();
         }

      }
   }

   public boolean isLowMemory() {
      synchronized(this) {
         long var2 = this.lowMemory;
         if (var2 == 0L) {
            return false;
         } else if (Runtime.getRuntime().freeMemory() > var2) {
            return false;
         } else {
            long var4 = System.currentTimeMillis();
            if (var4 - this.whenLowMemorySet > 5000L) {
               this.lowMemory = 0L;
               this.whenLowMemorySet = 0L;
               return false;
            } else {
               return true;
            }
         }
      }
   }
}
