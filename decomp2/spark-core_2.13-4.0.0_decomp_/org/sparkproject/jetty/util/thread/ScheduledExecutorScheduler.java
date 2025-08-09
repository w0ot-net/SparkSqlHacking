package org.sparkproject.jetty.util.thread;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;

@ManagedObject
public class ScheduledExecutorScheduler extends AbstractLifeCycle implements Scheduler, Dumpable {
   private final String name;
   private final boolean daemon;
   private final ClassLoader classloader;
   private final ThreadGroup threadGroup;
   private final int threads;
   private final AtomicInteger count;
   private volatile ScheduledExecutorService scheduler;
   private volatile Thread thread;

   public ScheduledExecutorScheduler() {
      this((String)null, false);
   }

   public ScheduledExecutorScheduler(String name, boolean daemon) {
      this(name, daemon, (ClassLoader)null);
   }

   public ScheduledExecutorScheduler(@Name("name") String name, @Name("daemon") boolean daemon, @Name("threads") int threads) {
      this(name, daemon, (ClassLoader)null, (ThreadGroup)null, threads);
   }

   public ScheduledExecutorScheduler(String name, boolean daemon, ClassLoader classLoader) {
      this(name, daemon, classLoader, (ThreadGroup)null);
   }

   public ScheduledExecutorScheduler(String name, boolean daemon, ClassLoader classLoader, ThreadGroup threadGroup) {
      this(name, daemon, classLoader, threadGroup, -1);
   }

   public ScheduledExecutorScheduler(@Name("name") String name, @Name("daemon") boolean daemon, @Name("classLoader") ClassLoader classLoader, @Name("threadGroup") ThreadGroup threadGroup, @Name("threads") int threads) {
      this.count = new AtomicInteger();
      this.name = StringUtil.isBlank(name) ? "Scheduler-" + this.hashCode() : name;
      this.daemon = daemon;
      this.classloader = classLoader == null ? Thread.currentThread().getContextClassLoader() : classLoader;
      this.threadGroup = threadGroup;
      this.threads = threads;
   }

   public ScheduledExecutorScheduler(ScheduledExecutorService scheduledExecutorService) {
      this.count = new AtomicInteger();
      this.name = null;
      this.daemon = false;
      this.classloader = null;
      this.threadGroup = null;
      this.threads = 0;
      this.scheduler = scheduledExecutorService;
   }

   protected void doStart() throws Exception {
      if (this.scheduler == null) {
         int size = this.threads > 0 ? this.threads : 1;
         ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(size, (r) -> {
            String var10005 = this.name;
            Thread thread = this.thread = new Thread(this.threadGroup, r, var10005 + "-" + this.count.incrementAndGet());
            thread.setDaemon(this.daemon);
            thread.setContextClassLoader(this.classloader);
            return thread;
         });
         scheduler.setRemoveOnCancelPolicy(true);
         this.scheduler = scheduler;
      }

      super.doStart();
   }

   protected void doStop() throws Exception {
      if (this.name != null) {
         this.scheduler.shutdownNow();
         this.scheduler = null;
      }

      super.doStop();
   }

   public Scheduler.Task schedule(Runnable task, long delay, TimeUnit unit) {
      ScheduledExecutorService s = this.scheduler;
      if (s == null) {
         return () -> false;
      } else {
         ScheduledFuture<?> result = s.schedule(task, delay, unit);
         return new ScheduledFutureTask(result);
      }
   }

   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Thread thread = this.thread;
      if (thread == null) {
         Dumpable.dumpObject(out, this);
      } else {
         Dumpable.dumpObjects(out, indent, this, (Object[])thread.getStackTrace());
      }

   }

   @ManagedAttribute("The name of the scheduler")
   public String getName() {
      return this.name;
   }

   @ManagedAttribute("Whether the scheduler uses daemon threads")
   public boolean isDaemon() {
      return this.daemon;
   }

   @ManagedAttribute("The number of scheduler threads")
   public int getThreads() {
      return this.threads;
   }

   private static class ScheduledFutureTask implements Scheduler.Task {
      private final ScheduledFuture scheduledFuture;

      ScheduledFutureTask(ScheduledFuture scheduledFuture) {
         this.scheduledFuture = scheduledFuture;
      }

      public boolean cancel() {
         return this.scheduledFuture.cancel(false);
      }
   }
}
