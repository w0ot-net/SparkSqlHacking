package org.sparkproject.jetty.util.thread;

import java.io.Closeable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class ThreadPoolBudget {
   private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolBudget.class);
   private static final Lease NOOP_LEASE = new Lease() {
      public void close() {
      }

      public int getThreads() {
         return 0;
      }
   };
   private final Set leases = new CopyOnWriteArraySet();
   private final AtomicBoolean warned = new AtomicBoolean();
   private final ThreadPool.SizedThreadPool pool;
   private final int warnAt;

   public ThreadPoolBudget(ThreadPool.SizedThreadPool pool) {
      this.pool = pool;
      this.warnAt = -1;
   }

   public ThreadPool.SizedThreadPool getSizedThreadPool() {
      return this.pool;
   }

   @ManagedAttribute("the number of threads leased to components")
   public int getLeasedThreads() {
      return this.leases.stream().mapToInt(Lease::getThreads).sum();
   }

   public void reset() {
      this.leases.clear();
      this.warned.set(false);
   }

   public Lease leaseTo(Object leasee, int threads) {
      Leased lease = new Leased(leasee, threads);
      this.leases.add(lease);

      try {
         this.check(this.pool.getMaxThreads());
         return lease;
      } catch (IllegalStateException e) {
         lease.close();
         throw e;
      }
   }

   public boolean check(int maxThreads) throws IllegalStateException {
      int required = this.getLeasedThreads();
      int left = maxThreads - required;
      if (left <= 0) {
         this.printInfoOnLeases();
         throw new IllegalStateException(String.format("Insufficient configured threads: required=%d < max=%d for %s", required, maxThreads, this.pool));
      } else if (left < this.warnAt) {
         if (this.warned.compareAndSet(false, true)) {
            this.printInfoOnLeases();
            LOG.info("Low configured threads: (max={} - required={})={} < warnAt={} for {}", new Object[]{maxThreads, required, left, this.warnAt, this.pool});
         }

         return false;
      } else {
         return true;
      }
   }

   private void printInfoOnLeases() {
      this.leases.forEach((lease) -> LOG.info("{} requires {} threads from {}", new Object[]{lease.leasee, lease.getThreads(), this.pool}));
   }

   public static Lease leaseFrom(Executor executor, Object leasee, int threads) {
      if (executor instanceof ThreadPool.SizedThreadPool) {
         ThreadPoolBudget budget = ((ThreadPool.SizedThreadPool)executor).getThreadPoolBudget();
         if (budget != null) {
            return budget.leaseTo(leasee, threads);
         }
      }

      return NOOP_LEASE;
   }

   public class Leased implements Lease {
      private final Object leasee;
      private final int threads;

      private Leased(Object leasee, int threads) {
         this.leasee = leasee;
         this.threads = threads;
      }

      public int getThreads() {
         return this.threads;
      }

      public void close() {
         ThreadPoolBudget.this.leases.remove(this);
         ThreadPoolBudget.this.warned.set(false);
      }
   }

   public interface Lease extends Closeable {
      int getThreads();
   }
}
