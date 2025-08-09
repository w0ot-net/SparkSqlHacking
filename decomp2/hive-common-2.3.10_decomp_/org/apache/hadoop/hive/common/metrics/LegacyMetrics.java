package org.apache.hadoop.hive.common.metrics;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.common.metrics.common.MetricsScope;
import org.apache.hadoop.hive.common.metrics.common.MetricsVariable;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyMetrics implements Metrics {
   private static final Logger LOG = LoggerFactory.getLogger(LegacyMetrics.class);
   private static final MetricsMBean metrics = new MetricsMBeanImpl();
   private static final ObjectName oname;
   private static final ThreadLocal threadLocalScopes;

   private LegacyMetrics() {
   }

   public LegacyMetrics(HiveConf conf) throws Exception {
      MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
      mbs.registerMBean(metrics, oname);
   }

   public Long incrementCounter(String name) {
      return this.incrementCounter(name, Long.valueOf(1L));
   }

   public Long incrementCounter(String name, long increment) {
      Long value = null;
      synchronized(metrics) {
         if (!metrics.hasKey(name)) {
            value = increment;
            this.set(name, value);
         } else {
            try {
               value = (Long)this.get(name) + increment;
               this.set(name, value);
            } catch (JMException e) {
               LOG.warn("Could not find counter value for " + name + ", increment operation skipped.", e);
            }
         }

         return value;
      }
   }

   public Long decrementCounter(String name) {
      return this.decrementCounter(name, Long.valueOf(1L));
   }

   public Long decrementCounter(String name, long decrement) {
      Long value = null;
      synchronized(metrics) {
         if (!metrics.hasKey(name)) {
            value = decrement;
            this.set(name, -value);
         } else {
            try {
               value = (Long)this.get(name) - decrement;
               this.set(name, value);
            } catch (JMException e) {
               LOG.warn("Could not find counter value for " + name + ", decrement operation skipped.", e);
            }
         }

         return value;
      }
   }

   public void addGauge(String name, MetricsVariable variable) {
   }

   public void addRatio(String name, MetricsVariable numerator, MetricsVariable denominator) {
   }

   public void markMeter(String name) {
   }

   public void set(String name, Object value) {
      metrics.put(name, value);
   }

   public Object get(String name) throws JMException {
      return metrics.get(name);
   }

   public void startStoredScope(String name) {
      if (((HashMap)threadLocalScopes.get()).containsKey(name)) {
         ((LegacyMetricsScope)((HashMap)threadLocalScopes.get()).get(name)).open();
      } else {
         ((HashMap)threadLocalScopes.get()).put(name, new LegacyMetricsScope(name, this));
      }

   }

   public MetricsScope getStoredScope(String name) throws IllegalStateException {
      if (((HashMap)threadLocalScopes.get()).containsKey(name)) {
         return (MetricsScope)((HashMap)threadLocalScopes.get()).get(name);
      } else {
         throw new IllegalStateException("No metrics scope named " + name);
      }
   }

   public void endStoredScope(String name) {
      if (((HashMap)threadLocalScopes.get()).containsKey(name)) {
         ((LegacyMetricsScope)((HashMap)threadLocalScopes.get()).get(name)).close();
      }

   }

   public MetricsScope createScope(String name) {
      return new LegacyMetricsScope(name, this);
   }

   public void endScope(MetricsScope scope) {
      ((LegacyMetricsScope)scope).close();
   }

   public void close() throws Exception {
      synchronized(metrics) {
         MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
         if (mbs.isRegistered(oname)) {
            mbs.unregisterMBean(oname);
         }

         metrics.clear();
         threadLocalScopes.remove();
      }
   }

   static {
      try {
         oname = new ObjectName("org.apache.hadoop.hive.common.metrics:type=MetricsMBean");
      } catch (MalformedObjectNameException mone) {
         throw new RuntimeException(mone);
      }

      threadLocalScopes = new ThreadLocal() {
         protected HashMap initialValue() {
            return new HashMap();
         }
      };
   }

   public static class LegacyMetricsScope implements MetricsScope {
      private final LegacyMetrics metrics;
      private final String name;
      private final String numCounter;
      private final String timeCounter;
      private final String avgTimeCounter;
      private boolean isOpen;
      private Long startTime;

      private LegacyMetricsScope(String name, LegacyMetrics metrics) {
         this.isOpen = false;
         this.startTime = null;
         this.metrics = metrics;
         this.name = name;
         this.numCounter = name + ".n";
         this.timeCounter = name + ".t";
         this.avgTimeCounter = name + ".avg_t";
         this.open();
      }

      public Long getNumCounter() {
         try {
            return (Long)this.metrics.get(this.numCounter);
         } catch (JMException e) {
            LegacyMetrics.LOG.warn("Could not find counter value for " + this.numCounter + ", returning null instead. ", e);
            return null;
         }
      }

      public Long getTimeCounter() {
         try {
            return (Long)this.metrics.get(this.timeCounter);
         } catch (JMException e) {
            LegacyMetrics.LOG.warn("Could not find timer value for " + this.timeCounter + ", returning null instead. ", e);
            return null;
         }
      }

      public void open() {
         if (!this.isOpen) {
            this.isOpen = true;
            this.startTime = System.currentTimeMillis();
         } else {
            LegacyMetrics.LOG.warn("Scope named " + this.name + " is not closed, cannot be opened.");
         }

      }

      public void close() {
         if (this.isOpen) {
            Long endTime = System.currentTimeMillis();
            synchronized(this.metrics) {
               Long num = this.metrics.incrementCounter(this.numCounter);
               Long time = this.metrics.incrementCounter(this.timeCounter, endTime - this.startTime);
               if (num != null && time != null) {
                  this.metrics.set(this.avgTimeCounter, time.doubleValue() / num.doubleValue());
               }
            }
         } else {
            LegacyMetrics.LOG.warn("Scope named " + this.name + " is not open, cannot be closed.");
         }

         this.isOpen = false;
      }

      public void reopen() {
         if (this.isOpen) {
            this.close();
         }

         this.open();
      }
   }
}
