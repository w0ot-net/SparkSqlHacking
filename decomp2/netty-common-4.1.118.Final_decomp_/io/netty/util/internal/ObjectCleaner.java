package io.netty.util.internal;

import io.netty.util.concurrent.FastThreadLocalThread;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObjectCleaner {
   private static final int REFERENCE_QUEUE_POLL_TIMEOUT_MS = Math.max(500, SystemPropertyUtil.getInt("io.netty.util.internal.ObjectCleaner.refQueuePollTimeout", 10000));
   static final String CLEANER_THREAD_NAME = ObjectCleaner.class.getSimpleName() + "Thread";
   private static final Set LIVE_SET = new ConcurrentSet();
   private static final ReferenceQueue REFERENCE_QUEUE = new ReferenceQueue();
   private static final AtomicBoolean CLEANER_RUNNING = new AtomicBoolean(false);
   private static final Runnable CLEANER_TASK = new Runnable() {
      public void run() {
         boolean interrupted = false;

         do {
            while(!ObjectCleaner.LIVE_SET.isEmpty()) {
               AutomaticCleanerReference reference;
               try {
                  reference = (AutomaticCleanerReference)ObjectCleaner.REFERENCE_QUEUE.remove((long)ObjectCleaner.REFERENCE_QUEUE_POLL_TIMEOUT_MS);
               } catch (InterruptedException var5) {
                  interrupted = true;
                  continue;
               }

               if (reference != null) {
                  try {
                     reference.cleanup();
                  } catch (Throwable var4) {
                  }

                  ObjectCleaner.LIVE_SET.remove(reference);
               }
            }

            ObjectCleaner.CLEANER_RUNNING.set(false);
         } while(!ObjectCleaner.LIVE_SET.isEmpty() && ObjectCleaner.CLEANER_RUNNING.compareAndSet(false, true));

         if (interrupted) {
            Thread.currentThread().interrupt();
         }

      }
   };

   public static void register(Object object, Runnable cleanupTask) {
      AutomaticCleanerReference reference = new AutomaticCleanerReference(object, (Runnable)ObjectUtil.checkNotNull(cleanupTask, "cleanupTask"));
      LIVE_SET.add(reference);
      if (CLEANER_RUNNING.compareAndSet(false, true)) {
         final Thread cleanupThread = new FastThreadLocalThread(CLEANER_TASK);
         cleanupThread.setPriority(1);
         AccessController.doPrivileged(new PrivilegedAction() {
            public Void run() {
               cleanupThread.setContextClassLoader((ClassLoader)null);
               return null;
            }
         });
         cleanupThread.setName(CLEANER_THREAD_NAME);
         cleanupThread.setDaemon(true);
         cleanupThread.start();
      }

   }

   public static int getLiveSetCount() {
      return LIVE_SET.size();
   }

   private ObjectCleaner() {
   }

   private static final class AutomaticCleanerReference extends WeakReference {
      private final Runnable cleanupTask;

      AutomaticCleanerReference(Object referent, Runnable cleanupTask) {
         super(referent, ObjectCleaner.REFERENCE_QUEUE);
         this.cleanupTask = cleanupTask;
      }

      void cleanup() {
         this.cleanupTask.run();
      }

      public Thread get() {
         return null;
      }

      public void clear() {
         ObjectCleaner.LIVE_SET.remove(this);
         super.clear();
      }
   }
}
