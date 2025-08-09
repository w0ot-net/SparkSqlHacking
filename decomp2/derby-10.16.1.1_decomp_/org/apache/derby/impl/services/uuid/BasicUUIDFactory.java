package org.apache.derby.impl.services.uuid;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;

public final class BasicUUIDFactory implements UUIDFactory {
   private long majorId;
   private long timemillis;
   private static final long MODULUS = 4294967296L;
   private static final long MULTIPLIER = 16385L;
   private static final long STEP = 134217729L;
   private static final long INITIAL_VALUE = 2551218188L;
   private long currentValue;

   public BasicUUIDFactory() {
      Object var1 = getMonitor().getEnvironment();
      if (var1 != null) {
         String var2 = var1.toString();
         if (var2 != null) {
            var1 = var2;
         }

         this.majorId = (long)var1.hashCode();
      } else {
         this.majorId = Runtime.getRuntime().freeMemory();
      }

      this.majorId &= 281474976710655L;
      this.resetCounters();
   }

   public synchronized UUID createUUID() {
      long var1 = this.currentValue = (16385L * this.currentValue + 134217729L) % 4294967296L;
      if (var1 == 2551218188L) {
         this.bumpMajor();
      }

      int var3 = (int)var1;
      return new BasicUUID(this.majorId, this.timemillis, var3);
   }

   public UUID recreateUUID(String var1) {
      return new BasicUUID(var1);
   }

   private void bumpMajor() {
      this.majorId = this.majorId + 1L & 281474976710655L;
      if (this.majorId == 0L) {
         this.resetCounters();
      }

   }

   private void resetCounters() {
      this.timemillis = System.currentTimeMillis();
      this.currentValue = 2551218188L;
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
