package org.sparkproject.jetty.server.session;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.SessionIdManager;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject
public class HouseKeeper extends AbstractLifeCycle {
   private static final Logger LOG = LoggerFactory.getLogger(HouseKeeper.class);
   public static final long DEFAULT_PERIOD_MS = 600000L;
   private final AutoLock _lock = new AutoLock();
   protected SessionIdManager _sessionIdManager;
   protected Scheduler _scheduler;
   protected Scheduler.Task _task;
   protected Runner _runner;
   protected boolean _ownScheduler = false;
   private long _intervalMs = 600000L;

   public void setSessionIdManager(SessionIdManager sessionIdManager) {
      if (this.isStarted()) {
         throw new IllegalStateException("HouseKeeper started");
      } else {
         this._sessionIdManager = sessionIdManager;
      }
   }

   protected void doStart() throws Exception {
      if (this._sessionIdManager == null) {
         throw new IllegalStateException("No SessionIdManager for Housekeeper");
      } else {
         this.setIntervalSec(this.getIntervalSec());
         super.doStart();
      }
   }

   protected void startScavenging() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this._scheduler == null) {
            if (this._sessionIdManager instanceof DefaultSessionIdManager) {
               this._scheduler = (Scheduler)((DefaultSessionIdManager)this._sessionIdManager).getServer().getBean(Scheduler.class);
            }

            if (this._scheduler == null) {
               this._scheduler = new ScheduledExecutorScheduler(String.format("Session-HouseKeeper-%x", this.hashCode()), false);
               this._ownScheduler = true;
               this._scheduler.start();
               if (LOG.isDebugEnabled()) {
                  LOG.debug("{} using own scheduler for scavenging", this._sessionIdManager.getWorkerName());
               }
            } else if (!this._scheduler.isStarted()) {
               throw new IllegalStateException("Shared scheduler not started");
            }
         }

         if (this._task != null) {
            this._task.cancel();
         }

         if (this._runner == null) {
            this._runner = new Runner();
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("{} scavenging every {}ms", this._sessionIdManager.getWorkerName(), this._intervalMs);
         }

         this._task = this._scheduler.schedule(this._runner, this._intervalMs, TimeUnit.MILLISECONDS);
      }

   }

   protected void stopScavenging() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this._task != null) {
            this._task.cancel();
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} stopped scavenging", this._sessionIdManager.getWorkerName());
            }
         }

         this._task = null;
         if (this._ownScheduler && this._scheduler != null) {
            this._ownScheduler = false;
            this._scheduler.stop();
            this._scheduler = null;
         }

         this._runner = null;
      }

   }

   protected void doStop() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         this.stopScavenging();
         this._scheduler = null;
      }

      super.doStop();
   }

   public void setIntervalSec(long sec) throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (!this.isStarted() && !this.isStarting()) {
            this._intervalMs = sec * 1000L;
         } else if (sec <= 0L) {
            this._intervalMs = 0L;
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} scavenging disabled", this._sessionIdManager.getWorkerName());
            }

            this.stopScavenging();
         } else {
            if (sec < 10L) {
               LOG.warn("{} short interval of {}sec for session scavenging.", this._sessionIdManager.getWorkerName(), sec);
            }

            this._intervalMs = sec * 1000L;
            long tenPercent = this._intervalMs / 10L;
            if (System.currentTimeMillis() % 2L == 0L) {
               this._intervalMs += tenPercent;
            }

            if (this.isStarting() || this.isStarted()) {
               this.startScavenging();
            }
         }
      }

   }

   @ManagedAttribute(
      value = "secs between scavenge cycles",
      readonly = true
   )
   public long getIntervalSec() {
      try (AutoLock l = this._lock.lock()) {
         return this._intervalMs / 1000L;
      }
   }

   public void scavenge() {
      if (!this.isStopping() && !this.isStopped()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} scavenging sessions", this._sessionIdManager.getWorkerName());
         }

         for(SessionHandler manager : this._sessionIdManager.getSessionHandlers()) {
            if (manager != null) {
               try {
                  manager.scavenge();
               } catch (Exception e) {
                  LOG.warn("Unable to scavenge", e);
               }
            }
         }

      }
   }

   public String toString() {
      try (AutoLock l = this._lock.lock()) {
         String var10000 = super.toString();
         return var10000 + "[interval=" + this._intervalMs + ", ownscheduler=" + this._ownScheduler + "]";
      }
   }

   protected class Runner implements Runnable {
      public void run() {
         try {
            HouseKeeper.this.scavenge();
         } finally {
            try (AutoLock var5 = HouseKeeper.this._lock.lock()) {
               if (HouseKeeper.this._scheduler != null && HouseKeeper.this._scheduler.isRunning()) {
                  HouseKeeper.this._task = HouseKeeper.this._scheduler.schedule(this, HouseKeeper.this._intervalMs, TimeUnit.MILLISECONDS);
               }
            }

         }

      }
   }
}
