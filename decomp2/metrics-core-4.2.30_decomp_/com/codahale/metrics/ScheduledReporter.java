package com.codahale.metrics;

import java.io.Closeable;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ScheduledReporter implements Closeable, Reporter {
   private static final Logger LOG = LoggerFactory.getLogger(ScheduledReporter.class);
   private static final AtomicInteger FACTORY_ID = new AtomicInteger();
   private final MetricRegistry registry;
   private final ScheduledExecutorService executor;
   private final boolean shutdownExecutorOnStop;
   private final Set disabledMetricAttributes;
   private ScheduledFuture scheduledFuture;
   private final MetricFilter filter;
   private final long durationFactor;
   private final String durationUnit;
   private final long rateFactor;
   private final String rateUnit;

   protected ScheduledReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit, TimeUnit durationUnit) {
      this(registry, name, filter, rateUnit, durationUnit, createDefaultExecutor(name));
   }

   protected ScheduledReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit, TimeUnit durationUnit, ScheduledExecutorService executor) {
      this(registry, name, filter, rateUnit, durationUnit, executor, true);
   }

   protected ScheduledReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit, TimeUnit durationUnit, ScheduledExecutorService executor, boolean shutdownExecutorOnStop) {
      this(registry, name, filter, rateUnit, durationUnit, executor, shutdownExecutorOnStop, Collections.emptySet());
   }

   protected ScheduledReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit, TimeUnit durationUnit, ScheduledExecutorService executor, boolean shutdownExecutorOnStop, Set disabledMetricAttributes) {
      if (registry == null) {
         throw new NullPointerException("registry == null");
      } else {
         this.registry = registry;
         this.filter = filter;
         this.executor = executor == null ? createDefaultExecutor(name) : executor;
         this.shutdownExecutorOnStop = shutdownExecutorOnStop;
         this.rateFactor = rateUnit.toSeconds(1L);
         this.rateUnit = this.calculateRateUnit(rateUnit);
         this.durationFactor = durationUnit.toNanos(1L);
         this.durationUnit = durationUnit.toString().toLowerCase(Locale.US);
         this.disabledMetricAttributes = disabledMetricAttributes != null ? disabledMetricAttributes : Collections.emptySet();
      }
   }

   public void start(long period, TimeUnit unit) {
      this.start(period, period, unit);
   }

   synchronized void start(long initialDelay, long period, TimeUnit unit, Runnable runnable) {
      if (this.scheduledFuture != null) {
         throw new IllegalArgumentException("Reporter already started");
      } else {
         this.scheduledFuture = this.getScheduledFuture(initialDelay, period, unit, runnable);
      }
   }

   /** @deprecated */
   @Deprecated
   protected ScheduledFuture getScheduledFuture(long initialDelay, long period, TimeUnit unit, Runnable runnable) {
      return this.getScheduledFuture(initialDelay, period, unit, runnable, this.executor);
   }

   protected ScheduledFuture getScheduledFuture(long initialDelay, long period, TimeUnit unit, Runnable runnable, ScheduledExecutorService executor) {
      return executor.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
   }

   public synchronized void start(long initialDelay, long period, TimeUnit unit) {
      this.start(initialDelay, period, unit, () -> {
         try {
            this.report();
         } catch (Throwable ex) {
            LOG.error("Exception thrown from {}#report. Exception was suppressed.", this.getClass().getSimpleName(), ex);
         }

      });
   }

   public void stop() {
      if (this.shutdownExecutorOnStop) {
         this.executor.shutdown();
      }

      if (this.scheduledFuture != null) {
         try {
            this.report();
         } catch (Exception e) {
            LOG.warn("Final reporting of metrics failed.", e);
         }
      }

      if (this.shutdownExecutorOnStop) {
         try {
            if (!this.executor.awaitTermination(1L, TimeUnit.SECONDS)) {
               this.executor.shutdownNow();
               if (!this.executor.awaitTermination(1L, TimeUnit.SECONDS)) {
                  LOG.warn("ScheduledExecutorService did not terminate.");
               }
            }
         } catch (InterruptedException var2) {
            this.executor.shutdownNow();
            Thread.currentThread().interrupt();
         }
      } else {
         this.cancelScheduledFuture();
      }

   }

   private synchronized void cancelScheduledFuture() {
      if (this.scheduledFuture != null) {
         if (!this.scheduledFuture.isCancelled()) {
            this.scheduledFuture.cancel(false);
         }
      }
   }

   public void close() {
      this.stop();
   }

   public void report() {
      synchronized(this) {
         this.report(this.registry.getGauges(this.filter), this.registry.getCounters(this.filter), this.registry.getHistograms(this.filter), this.registry.getMeters(this.filter), this.registry.getTimers(this.filter));
      }
   }

   public abstract void report(SortedMap gauges, SortedMap counters, SortedMap histograms, SortedMap meters, SortedMap timers);

   protected String getRateUnit() {
      return this.rateUnit;
   }

   protected String getDurationUnit() {
      return this.durationUnit;
   }

   protected double convertDuration(double duration) {
      return duration / (double)this.durationFactor;
   }

   protected double convertRate(double rate) {
      return rate * (double)this.rateFactor;
   }

   protected boolean isShutdownExecutorOnStop() {
      return this.shutdownExecutorOnStop;
   }

   protected Set getDisabledMetricAttributes() {
      return this.disabledMetricAttributes;
   }

   private String calculateRateUnit(TimeUnit unit) {
      String s = unit.toString().toLowerCase(Locale.US);
      return s.substring(0, s.length() - 1);
   }

   private static ScheduledExecutorService createDefaultExecutor(String name) {
      return Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(name + '-' + FACTORY_ID.incrementAndGet()));
   }

   private static class NamedThreadFactory implements ThreadFactory {
      private final ThreadGroup group;
      private final AtomicInteger threadNumber;
      private final String namePrefix;

      private NamedThreadFactory(String name) {
         this.threadNumber = new AtomicInteger(1);
         SecurityManager s = System.getSecurityManager();
         this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
         this.namePrefix = "metrics-" + name + "-thread-";
      }

      public Thread newThread(Runnable r) {
         Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
         t.setDaemon(true);
         if (t.getPriority() != 5) {
            t.setPriority(5);
         }

         return t;
      }
   }
}
