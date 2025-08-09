package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Callables {
   private Callables() {
   }

   public static Callable returning(@ParametricNullness Object value) {
      return () -> value;
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static AsyncCallable asAsyncCallable(Callable callable, ListeningExecutorService listeningExecutorService) {
      Preconditions.checkNotNull(callable);
      Preconditions.checkNotNull(listeningExecutorService);
      return () -> listeningExecutorService.submit(callable);
   }

   @J2ktIncompatible
   @GwtIncompatible
   static Callable threadRenaming(Callable callable, Supplier nameSupplier) {
      Preconditions.checkNotNull(nameSupplier);
      Preconditions.checkNotNull(callable);
      return () -> {
         Thread currentThread = Thread.currentThread();
         String oldName = currentThread.getName();
         boolean restoreName = trySetName((String)nameSupplier.get(), currentThread);
         boolean var10 = false;

         Object var5;
         try {
            var10 = true;
            var5 = callable.call();
            var10 = false;
         } finally {
            if (var10) {
               if (restoreName) {
                  trySetName(oldName, currentThread);
               }

            }
         }

         if (restoreName) {
            trySetName(oldName, currentThread);
         }

         return var5;
      };
   }

   @J2ktIncompatible
   @GwtIncompatible
   static Runnable threadRenaming(Runnable task, Supplier nameSupplier) {
      Preconditions.checkNotNull(nameSupplier);
      Preconditions.checkNotNull(task);
      return () -> {
         Thread currentThread = Thread.currentThread();
         String oldName = currentThread.getName();
         boolean restoreName = trySetName((String)nameSupplier.get(), currentThread);
         boolean var9 = false;

         try {
            var9 = true;
            task.run();
            var9 = false;
         } finally {
            if (var9) {
               if (restoreName) {
                  trySetName(oldName, currentThread);
               }

            }
         }

         if (restoreName) {
            trySetName(oldName, currentThread);
         }

      };
   }

   @J2ktIncompatible
   @GwtIncompatible
   private static boolean trySetName(String threadName, Thread currentThread) {
      try {
         currentThread.setName(threadName);
         return true;
      } catch (SecurityException var3) {
         return false;
      }
   }
}
