package org.apache.ivy.plugins.lock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.ivy.util.Message;

public abstract class FileBasedLockStrategy extends AbstractLockStrategy {
   private static final int SLEEP_TIME = 100;
   private static final long DEFAULT_TIMEOUT = 120000L;
   private FileLocker locker;
   private long timeout;
   private static final ConcurrentMap currentLockHolders = new ConcurrentHashMap();

   protected FileBasedLockStrategy() {
      this(new CreateFileLocker(false), false);
   }

   protected FileBasedLockStrategy(boolean debugLocking) {
      this(new CreateFileLocker(debugLocking), debugLocking);
   }

   protected FileBasedLockStrategy(FileLocker locker, boolean debugLocking) {
      super(debugLocking);
      this.timeout = 120000L;
      this.locker = locker;
   }

   protected boolean acquireLock(File file) throws InterruptedException {
      Thread currentThread = Thread.currentThread();
      if (this.isDebugLocking()) {
         debugLocking("acquiring lock on " + file);
      }

      long start = System.currentTimeMillis();

      do {
         synchronized(currentLockHolders) {
            if (this.isDebugLocking()) {
               debugLocking("entered synchronized area (locking)");
            }

            int lockCount = this.hasLock(file, currentThread);
            if (this.isDebugLocking()) {
               debugLocking("current status for " + file + " is " + lockCount + " held locks: " + this.getCurrentLockHolderNames(file));
            }

            if (lockCount < 0) {
               if (this.isDebugLocking()) {
                  debugLocking("waiting for another thread to release the lock: " + this.getCurrentLockHolderNames(file));
               }
            } else {
               if (lockCount > 0) {
                  int holdLocks = this.incrementLock(file, currentThread);
                  if (this.isDebugLocking()) {
                     debugLocking("reentrant lock acquired on " + file + " in " + (System.currentTimeMillis() - start) + "ms - hold locks = " + holdLocks);
                  }

                  return true;
               }

               if (this.locker.tryLock(file)) {
                  if (this.isDebugLocking()) {
                     debugLocking("lock acquired on " + file + " in " + (System.currentTimeMillis() - start) + "ms");
                  }

                  this.incrementLock(file, currentThread);
                  return true;
               }
            }
         }

         if (this.isDebugLocking()) {
            debugLocking("failed to acquire lock; sleeping for retry...");
         }

         Thread.sleep(100L);
      } while(System.currentTimeMillis() - start < this.timeout);

      return false;
   }

   protected void releaseLock(File file) {
      Thread currentThread = Thread.currentThread();
      if (this.isDebugLocking()) {
         debugLocking("releasing lock on " + file);
      }

      synchronized(currentLockHolders) {
         if (this.isDebugLocking()) {
            debugLocking("entered synchronized area (unlocking)");
         }

         int holdLocks = this.decrementLock(file, currentThread);
         if (holdLocks == 0) {
            this.locker.unlock(file);
            if (this.isDebugLocking()) {
               debugLocking("lock released on " + file);
            }
         } else if (this.isDebugLocking()) {
            debugLocking("reentrant lock released on " + file + " - hold locks = " + holdLocks);
         }

      }
   }

   private static void debugLocking(String msg) {
      Message.info(Thread.currentThread() + " " + System.currentTimeMillis() + " " + msg);
   }

   private int hasLock(File file, Thread forThread) {
      ConcurrentMap<Thread, Integer> locksPerThread = (ConcurrentMap)currentLockHolders.get(file);
      if (locksPerThread == null) {
         return 0;
      } else if (locksPerThread.isEmpty()) {
         return 0;
      } else {
         Integer counterObj = (Integer)locksPerThread.get(forThread);
         int counter = counterObj == null ? 0 : counterObj;
         return counter > 0 ? counter : -1;
      }
   }

   private int incrementLock(File file, Thread forThread) {
      ConcurrentMap<Thread, Integer> locksPerThread = (ConcurrentMap)currentLockHolders.get(file);
      if (locksPerThread == null) {
         locksPerThread = new ConcurrentHashMap();
         currentLockHolders.put(file, locksPerThread);
      }

      Integer c = (Integer)locksPerThread.get(forThread);
      int holdLocks = c == null ? 1 : c + 1;
      locksPerThread.put(forThread, holdLocks);
      return holdLocks;
   }

   private int decrementLock(File file, Thread forThread) {
      ConcurrentMap<Thread, Integer> locksPerThread = (ConcurrentMap)currentLockHolders.get(file);
      if (locksPerThread == null) {
         throw new RuntimeException("Calling decrementLock on a thread which holds no locks");
      } else {
         Integer c = (Integer)locksPerThread.get(forThread);
         int oldHeldLocks = c == null ? 0 : c;
         if (oldHeldLocks <= 0) {
            throw new RuntimeException("Calling decrementLock on a thread which holds no locks");
         } else {
            int newHeldLocks = oldHeldLocks - 1;
            if (newHeldLocks > 0) {
               locksPerThread.put(forThread, newHeldLocks);
            } else {
               locksPerThread.remove(forThread);
            }

            return newHeldLocks;
         }
      }
   }

   protected String getCurrentLockHolderNames(File file) {
      StringBuilder sb = new StringBuilder();
      ConcurrentMap<Thread, Integer> m = (ConcurrentMap)currentLockHolders.get(file);
      if (m == null) {
         return "(NULL)";
      } else {
         for(Thread t : m.keySet()) {
            if (sb.length() > 0) {
               sb.append(", ");
            }

            sb.append(t.toString());
         }

         return sb.toString();
      }
   }

   public static class CreateFileLocker implements FileLocker {
      private boolean debugLocking;

      public CreateFileLocker(boolean debugLocking) {
         this.debugLocking = debugLocking;
      }

      public boolean tryLock(File file) {
         try {
            if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
               if (file.createNewFile()) {
                  DeleteOnExitHook.add(file);
                  return true;
               }

               if (this.debugLocking) {
                  FileBasedLockStrategy.debugLocking("file creation failed " + file);
               }
            }
         } catch (IOException e) {
            Message.verbose("file creation failed due to an exception: " + e.getMessage() + " (" + file + ")");
         }

         return false;
      }

      public void unlock(File file) {
         file.delete();
         DeleteOnExitHook.remove(file);
      }
   }

   public static class NIOFileLocker implements FileLocker {
      private ConcurrentMap locks = new ConcurrentHashMap();
      private boolean debugLocking;

      public NIOFileLocker(boolean debugLocking) {
         this.debugLocking = debugLocking;
      }

      public boolean tryLock(File file) {
         try {
            if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
               RandomAccessFile raf = new RandomAccessFile(file, "rw");
               FileLock l = raf.getChannel().tryLock();
               if (l != null) {
                  synchronized(this) {
                     this.locks.put(file, new LockData(raf, l));
                  }

                  return true;
               }

               if (this.debugLocking) {
                  FileBasedLockStrategy.debugLocking("failed to acquire lock on " + file);
               }
            }
         } catch (IOException e) {
            Message.verbose("file lock failed due to an exception: " + e.getMessage() + " (" + file + ")");
         }

         return false;
      }

      public void unlock(File file) {
         synchronized(this) {
            LockData data = (LockData)this.locks.get(file);
            if (data == null) {
               throw new IllegalArgumentException("file not previously locked: " + file);
            } else {
               try {
                  this.locks.remove(file);
                  data.l.release();
                  data.raf.close();
               } catch (IOException e) {
                  Message.error("problem while releasing lock on " + file + ": " + e.getMessage());
               }

            }
         }
      }

      private static class LockData {
         private RandomAccessFile raf;
         private FileLock l;

         LockData(RandomAccessFile raf, FileLock l) {
            this.raf = raf;
            this.l = l;
         }
      }
   }

   public interface FileLocker {
      boolean tryLock(File var1);

      void unlock(File var1);
   }
}
