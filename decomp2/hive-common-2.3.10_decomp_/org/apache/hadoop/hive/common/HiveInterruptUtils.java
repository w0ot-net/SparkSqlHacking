package org.apache.hadoop.hive.common;

import java.util.ArrayList;
import java.util.List;

public class HiveInterruptUtils {
   private static List interruptCallbacks = new ArrayList();

   public static HiveInterruptCallback add(HiveInterruptCallback command) {
      synchronized(interruptCallbacks) {
         interruptCallbacks.add(command);
         return command;
      }
   }

   public static HiveInterruptCallback remove(HiveInterruptCallback command) {
      synchronized(interruptCallbacks) {
         interruptCallbacks.remove(command);
         return command;
      }
   }

   public static void interrupt() {
      synchronized(interruptCallbacks) {
         for(HiveInterruptCallback resource : new ArrayList(interruptCallbacks)) {
            resource.interrupt();
         }

      }
   }

   public static void checkInterrupted() {
      if (Thread.currentThread().isInterrupted()) {
         InterruptedException interrupt = null;

         try {
            Thread.sleep(0L);
         } catch (InterruptedException e) {
            interrupt = e;
         }

         throw new RuntimeException("Interuppted", interrupt);
      }
   }
}
