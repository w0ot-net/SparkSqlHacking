package org.sparkproject.jetty.util.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

public class TimerScheduler extends AbstractLifeCycle implements Scheduler, Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(TimerScheduler.class);
   private final String _name;
   private final boolean _daemon;
   private Timer _timer;

   public TimerScheduler() {
      this((String)null, false);
   }

   public TimerScheduler(String name, boolean daemon) {
      this._name = name;
      this._daemon = daemon;
   }

   protected void doStart() throws Exception {
      this._timer = this._name == null ? new Timer() : new Timer(this._name, this._daemon);
      this.run();
      super.doStart();
   }

   protected void doStop() throws Exception {
      this._timer.cancel();
      super.doStop();
      this._timer = null;
   }

   public Scheduler.Task schedule(Runnable task, long delay, TimeUnit units) {
      Timer timer = this._timer;
      if (timer == null) {
         throw new RejectedExecutionException("STOPPED: " + String.valueOf(this));
      } else {
         SimpleTask t = new SimpleTask(task);
         timer.schedule(t, units.toMillis(delay));
         return t;
      }
   }

   public void run() {
      Timer timer = this._timer;
      if (timer != null) {
         timer.purge();
         this.schedule(this, 1L, TimeUnit.SECONDS);
      }

   }

   private static class SimpleTask extends TimerTask implements Scheduler.Task {
      private final Runnable _task;

      private SimpleTask(Runnable runnable) {
         this._task = runnable;
      }

      public void run() {
         try {
            this._task.run();
         } catch (Throwable x) {
            TimerScheduler.LOG.warn("Exception while executing task {}", this._task, x);
         }

      }

      public String toString() {
         return String.format("%s.%s@%x", TimerScheduler.class.getSimpleName(), SimpleTask.class.getSimpleName(), this.hashCode());
      }
   }
}
