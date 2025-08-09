package org.datanucleus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.util.NucleusLogger;

public class ExecutionContextPool {
   private PersistenceNucleusContext nucCtx;
   private long maxIdle = 20L;
   private long expirationTime;
   private Map recyclableECs;
   private CleanUpThread cleaner;

   public ExecutionContextPool(PersistenceNucleusContext nucCtx) {
      this.maxIdle = (long)nucCtx.getConfiguration().getIntProperty("datanucleus.executionContext.maxIdle");
      this.nucCtx = nucCtx;
      this.expirationTime = 30000L;
      this.recyclableECs = new ConcurrentHashMap();
      if (nucCtx.getConfiguration().getBooleanProperty("datanucleus.executionContext.reaperThread")) {
         this.cleaner = new CleanUpThread(this, this.expirationTime * 2L);
         this.cleaner.start();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug("Started pool of ExecutionContext (maxPool=" + this.maxIdle + ", reaperThread=" + (this.cleaner != null) + ")");
      }

   }

   protected ExecutionContext create(Object owner, Map options) {
      return (ExecutionContext)(this.nucCtx.getConfiguration().getBooleanProperty("datanucleus.Multithreaded") ? new ExecutionContextThreadedImpl(this.nucCtx, owner, options) : new ExecutionContextImpl(this.nucCtx, owner, options));
   }

   public boolean validate(ExecutionContext ec) {
      return true;
   }

   public void expire(ExecutionContext ec) {
   }

   public synchronized ExecutionContext checkOut(Object owner, Map options) {
      long now = System.currentTimeMillis();
      if (this.recyclableECs.size() > 0) {
         for(ExecutionContext ec : this.recyclableECs.keySet()) {
            if (now - (Long)this.recyclableECs.get(ec) > this.expirationTime) {
               this.recyclableECs.remove(ec);
               this.expire(ec);
               ExecutionContext ec = null;
            } else {
               if (this.validate(ec)) {
                  this.recyclableECs.remove(ec);
                  ec.initialise(owner, options);
                  return ec;
               }

               this.recyclableECs.remove(ec);
               this.expire(ec);
               ExecutionContext var9 = null;
            }
         }
      }

      ExecutionContext ec = this.create(owner, options);
      return ec;
   }

   public synchronized void cleanUp() {
      long now = System.currentTimeMillis();

      for(ExecutionContext ec : this.recyclableECs.keySet()) {
         if (now - (Long)this.recyclableECs.get(ec) > this.expirationTime) {
            this.recyclableECs.remove(ec);
            this.expire(ec);
            ec = null;
         }
      }

      System.gc();
   }

   public synchronized void checkIn(ExecutionContext ec) {
      if ((long)this.recyclableECs.size() < this.maxIdle) {
         this.recyclableECs.put(ec, System.currentTimeMillis());
      }

   }

   class CleanUpThread extends Thread {
      private ExecutionContextPool pool;
      private long sleepTime;

      CleanUpThread(ExecutionContextPool pool, long sleepTime) {
         this.pool = pool;
         this.sleepTime = sleepTime;
      }

      public void run() {
         while(true) {
            try {
               sleep(this.sleepTime);
            } catch (InterruptedException var2) {
            }

            this.pool.cleanUp();
         }
      }
   }
}
