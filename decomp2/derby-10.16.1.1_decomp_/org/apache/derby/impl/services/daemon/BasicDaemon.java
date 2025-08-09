package org.apache.derby.impl.services.daemon;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

public class BasicDaemon implements DaemonService, Runnable {
   private int numClients;
   private static final int OPTIMAL_QUEUE_SIZE = 100;
   private final Vector subscription;
   protected final ContextService contextService;
   protected final ContextManager contextMgr;
   private final List highPQ;
   private final List normPQ;
   private int nextService;
   private boolean awakened;
   private boolean waiting;
   private boolean inPause;
   private boolean running;
   private boolean stopRequested;
   private boolean stopped;
   private long lastServiceTime;
   private int earlyWakeupCount;

   public BasicDaemon(ContextService var1) {
      this.contextService = var1;
      this.contextMgr = var1.newContextManager();
      this.subscription = new Vector(1, 1);
      this.highPQ = new LinkedList();
      this.normPQ = new LinkedList();
      this.lastServiceTime = System.currentTimeMillis();
   }

   public int subscribe(Serviceable var1, boolean var2) {
      synchronized(this) {
         int var3 = this.numClients++;
         ServiceRecord var4 = new ServiceRecord(var1, var2, true);
         this.subscription.add(var3, var4);
         return var3;
      }
   }

   public void unsubscribe(int var1) {
      if (var1 >= 0 && var1 <= this.subscription.size()) {
         this.subscription.set(var1, (Object)null);
      }
   }

   public void serviceNow(int var1) {
      if (var1 >= 0 && var1 <= this.subscription.size()) {
         ServiceRecord var2 = (ServiceRecord)this.subscription.get(var1);
         if (var2 != null) {
            var2.called();
            this.wakeUp();
         }
      }
   }

   public boolean enqueue(Serviceable var1, boolean var2) {
      ServiceRecord var3 = new ServiceRecord(var1, false, false);
      List var4 = var2 ? this.highPQ : this.normPQ;
      int var5;
      synchronized(this) {
         var4.add(var3);
         var5 = this.highPQ.size();
      }

      if (var2 && !this.awakened) {
         this.wakeUp();
      }

      if (var2) {
         return var5 > 100;
      } else {
         return false;
      }
   }

   public synchronized void clear() {
      this.normPQ.clear();
      this.highPQ.clear();
   }

   protected ServiceRecord nextAssignment(boolean var1) {
      while(true) {
         if (this.nextService < this.subscription.size()) {
            ServiceRecord var9 = (ServiceRecord)this.subscription.get(this.nextService++);
            if (var9 == null || !var9.needImmediateService() && (var1 || !var9.needService())) {
               continue;
            }

            return var9;
         }

         ServiceRecord var2 = null;
         synchronized(this) {
            if (!this.highPQ.isEmpty()) {
               var2 = (ServiceRecord)this.highPQ.remove(0);
            }
         }

         if (!var1 && var2 == null) {
            var2 = null;
            synchronized(this) {
               if (!this.normPQ.isEmpty()) {
                  var2 = (ServiceRecord)this.normPQ.remove(0);
               }

               return var2;
            }
         }

         return var2;
      }
   }

   protected void serviceClient(ServiceRecord var1) {
      var1.serviced();
      Serviceable var2 = var1.client;
      if (var2 != null) {
         ContextManager var3 = this.contextMgr;

         try {
            int var4 = var2.performWork(var3);
            if (!var1.subscriber) {
               if (var4 == 2) {
                  List var5 = var2.serviceASAP() ? this.highPQ : this.normPQ;
                  synchronized(this) {
                     var5.add(var1);
                  }
               }

            }
         } catch (Throwable var9) {
            var3.cleanupOnError(var9, false);
         }
      }
   }

   public void run() {
      this.contextService.setCurrentContextManager(this.contextMgr);

      while(!this.stopRequested()) {
         boolean var1 = this.rest();
         if (this.stopRequested()) {
            break;
         }

         if (!this.inPause()) {
            this.work(var1);
         }
      }

      synchronized(this) {
         this.running = false;
         this.stopped = true;
      }

      this.contextMgr.cleanupOnError(StandardException.normalClose(), false);
      this.contextService.resetCurrentContextManager(this.contextMgr);
   }

   public void pause() {
      synchronized(this) {
         this.inPause = true;

         while(this.running) {
            try {
               this.wait();
            } catch (InterruptedException var4) {
               InterruptStatus.setInterrupted();
            }
         }

      }
   }

   public void resume() {
      synchronized(this) {
         this.inPause = false;
      }
   }

   public void stop() {
      if (!this.stopped) {
         synchronized(this) {
            this.stopRequested = true;
            this.notifyAll();
         }

         this.pause();
      }
   }

   public void waitUntilQueueIsEmpty() {
      while(true) {
         synchronized(this) {
            boolean var2 = true;

            for(int var3 = 0; var3 < this.subscription.size(); ++var3) {
               ServiceRecord var4 = (ServiceRecord)this.subscription.get(var3);
               if (var4 != null && var4.needService()) {
                  var2 = false;
                  break;
               }
            }

            if (this.highPQ.isEmpty() && var2 && !this.running) {
               return;
            }

            this.notifyAll();

            try {
               this.wait();
            } catch (InterruptedException var6) {
               InterruptStatus.setInterrupted();
            }
         }
      }
   }

   private synchronized boolean stopRequested() {
      return this.stopRequested;
   }

   private synchronized boolean inPause() {
      return this.inPause;
   }

   protected synchronized void wakeUp() {
      if (!this.awakened) {
         this.awakened = true;
         if (this.waiting) {
            this.notifyAll();
         }
      }

   }

   private boolean rest() {
      boolean var2 = false;
      boolean var1;
      synchronized(this) {
         try {
            if (!this.awakened) {
               this.waiting = true;
               this.wait(10000L);
               this.waiting = false;
            }
         } catch (InterruptedException var6) {
         }

         this.nextService = 0;
         var1 = this.awakened;
         if (var1 && this.earlyWakeupCount++ > 20) {
            this.earlyWakeupCount = 0;
            var2 = true;
         }

         this.awakened = false;
      }

      if (var2) {
         long var3 = System.currentTimeMillis();
         if (var3 - this.lastServiceTime > 10000L) {
            this.lastServiceTime = var3;
            var1 = false;
         }
      }

      return var1;
   }

   private void work(boolean var1) {
      int var3 = 0;
      byte var4 = 10;
      if (var1 && this.highPQ.size() > 100) {
         var4 = 2;
      }

      int var5 = 100 / var4;

      for(ServiceRecord var2 = this.nextAssignment(var1); var2 != null; var2 = this.nextAssignment(var1)) {
         synchronized(this) {
            if (this.inPause || this.stopRequested) {
               break;
            }

            this.running = true;
         }

         try {
            this.serviceClient(var2);
            ++var3;
         } finally {
            synchronized(this) {
               this.running = false;
               this.notifyAll();
               if (!this.inPause && !this.stopRequested) {
                  ;
               } else {
                  break;
               }
            }
         }

         if (var3 % 50 == 0) {
            this.nextService = 0;
         }

         if (var3 % var5 == 0) {
            this.yieldNow();
         }
      }

   }

   private void yieldNow() {
      Thread var1 = Thread.currentThread();
      int var2 = var1.getPriority();
      if (var2 <= 1) {
         Thread.yield();
      } else {
         ModuleFactory var3 = getMonitor();
         setThreadPriority(var3, 1);
         Thread.yield();
         setThreadPriority(var3, var2);
      }

   }

   private static void setThreadPriority(ModuleFactory var0, int var1) {
      Thread var2 = Thread.currentThread();
      if (var0 != null && var0.isDaemonThread(var2)) {
         var2.setPriority(var1);
      }

   }

   static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
