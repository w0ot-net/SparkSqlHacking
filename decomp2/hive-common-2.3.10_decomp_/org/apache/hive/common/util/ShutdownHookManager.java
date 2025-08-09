package org.apache.hive.common.util;

import com.google.common.annotations.VisibleForTesting;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ShutdownHookManager {
   private static final org.apache.hadoop.util.ShutdownHookManager MGR = org.apache.hadoop.util.ShutdownHookManager.get();
   private static final DeleteOnExitHook DELETE_ON_EXIT_HOOK = new DeleteOnExitHook();

   public static void addShutdownHook(Runnable shutdownHook) {
      addShutdownHook(shutdownHook, 10);
   }

   public static void addShutdownHook(Runnable shutdownHook, int priority) {
      if (priority < 0) {
         throw new IllegalArgumentException("Priority should be greater than or equal to zero");
      } else {
         MGR.addShutdownHook(shutdownHook, priority);
      }
   }

   public static boolean isShutdownInProgress() {
      return MGR.isShutdownInProgress();
   }

   public static boolean removeShutdownHook(Runnable shutdownHook) {
      return shutdownHook == null ? false : MGR.removeShutdownHook(shutdownHook);
   }

   public static void deleteOnExit(File file) {
      if (MGR.isShutdownInProgress()) {
         throw new IllegalStateException("Shutdown in progress, cannot add a deleteOnExit");
      } else {
         DELETE_ON_EXIT_HOOK.deleteTargets.add(file);
      }
   }

   public static void cancelDeleteOnExit(File file) {
      if (MGR.isShutdownInProgress()) {
         throw new IllegalStateException("Shutdown in progress, cannot cancel a deleteOnExit");
      } else {
         DELETE_ON_EXIT_HOOK.deleteTargets.remove(file);
      }
   }

   @VisibleForTesting
   static boolean isRegisteredToDeleteOnExit(File file) {
      return DELETE_ON_EXIT_HOOK.deleteTargets.contains(file);
   }

   static {
      MGR.addShutdownHook(DELETE_ON_EXIT_HOOK, -1);
   }

   private static class DeleteOnExitHook implements Runnable {
      private final Set deleteTargets;

      private DeleteOnExitHook() {
         this.deleteTargets = Collections.synchronizedSet(new HashSet());
      }

      public void run() {
         for(File deleteTarget : this.deleteTargets) {
            deleteTarget.delete();
         }

         this.deleteTargets.clear();
      }
   }
}
