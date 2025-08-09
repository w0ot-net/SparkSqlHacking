package org.apache.curator.framework.recipes.locks;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.ThreadUtils;

public class InterProcessMultiLock implements InterProcessLock {
   private final List locks;

   public InterProcessMultiLock(CuratorFramework client, List paths) {
      this(makeLocks(client, paths));
   }

   public InterProcessMultiLock(List locks) {
      this.locks = ImmutableList.copyOf(locks);
   }

   private static List makeLocks(CuratorFramework client, List paths) {
      ImmutableList.Builder<InterProcessLock> builder = ImmutableList.builder();

      for(String path : paths) {
         InterProcessLock lock = new InterProcessMutex(client, path);
         builder.add(lock);
      }

      return builder.build();
   }

   public void acquire() throws Exception {
      this.acquire(-1L, (TimeUnit)null);
   }

   public boolean acquire(long time, TimeUnit unit) throws Exception {
      Exception exception = null;
      List<InterProcessLock> acquired = Lists.newArrayList();
      boolean success = true;

      for(InterProcessLock lock : this.locks) {
         try {
            if (unit == null) {
               lock.acquire();
               acquired.add(lock);
            } else {
               if (!lock.acquire(time, unit)) {
                  success = false;
                  break;
               }

               acquired.add(lock);
            }
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            success = false;
            exception = e;
         }
      }

      if (!success) {
         for(InterProcessLock lock : Lists.reverse(acquired)) {
            try {
               lock.release();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
            }
         }
      }

      if (exception != null) {
         throw exception;
      } else {
         return success;
      }
   }

   public synchronized void release() throws Exception {
      Exception baseException = null;

      for(InterProcessLock lock : Lists.reverse(this.locks)) {
         try {
            lock.release();
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            if (baseException == null) {
               baseException = e;
            } else {
               baseException = new Exception(baseException);
            }
         }
      }

      if (baseException != null) {
         throw baseException;
      }
   }

   public synchronized boolean isAcquiredInThisProcess() {
      for(InterProcessLock lock : this.locks) {
         if (!lock.isAcquiredInThisProcess()) {
            return false;
         }
      }

      return true;
   }
}
