package org.apache.hadoop.hive.common.metrics.metrics2;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.Timer;
import com.codahale.metrics.json.MetricsModule;
import com.codahale.metrics.jvm.BufferPoolMetricSet;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joshelser.dropwizard.metrics.hadoop.HadoopMetrics2Reporter;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.common.metrics.common.MetricsScope;
import org.apache.hadoop.hive.common.metrics.common.MetricsVariable;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.metrics2.lib.DefaultMetricsSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodahaleMetrics implements Metrics {
   public static final Logger LOGGER = LoggerFactory.getLogger(CodahaleMetrics.class);
   public final MetricRegistry metricRegistry = new MetricRegistry();
   private final Lock timersLock = new ReentrantLock();
   private final Lock countersLock = new ReentrantLock();
   private final Lock gaugesLock = new ReentrantLock();
   private final Lock metersLock = new ReentrantLock();
   private LoadingCache timers;
   private LoadingCache counters;
   private LoadingCache meters;
   private ConcurrentHashMap gauges;
   private HiveConf conf;
   private final Set reporters = new HashSet();
   private final ThreadLocal threadLocalScopes = new ThreadLocal() {
      protected HashMap initialValue() {
         return new HashMap();
      }
   };

   public CodahaleMetrics(HiveConf conf) {
      this.conf = conf;
      this.timers = CacheBuilder.newBuilder().build(new CacheLoader() {
         public Timer load(String key) {
            Timer timer = new Timer(new ExponentiallyDecayingReservoir());
            CodahaleMetrics.this.metricRegistry.register(key, timer);
            return timer;
         }
      });
      this.counters = CacheBuilder.newBuilder().build(new CacheLoader() {
         public Counter load(String key) {
            Counter counter = new Counter();
            CodahaleMetrics.this.metricRegistry.register(key, counter);
            return counter;
         }
      });
      this.meters = CacheBuilder.newBuilder().build(new CacheLoader() {
         public Meter load(String key) {
            Meter meter = new Meter();
            CodahaleMetrics.this.metricRegistry.register(key, meter);
            return meter;
         }
      });
      this.gauges = new ConcurrentHashMap();
      this.registerAll("gc", new GarbageCollectorMetricSet());
      this.registerAll("buffers", new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));
      this.registerAll("memory", new MemoryUsageGaugeSet());
      this.registerAll("threads", new ThreadStatesGaugeSet());
      this.registerAll("classLoading", new ClassLoadingGaugeSet());
      Set<MetricsReporting> finalReporterList = new HashSet();
      List<String> metricsReporterNames = Lists.newArrayList(Splitter.on(",").trimResults().omitEmptyStrings().split(conf.getVar(HiveConf.ConfVars.HIVE_METRICS_REPORTER)));
      if (metricsReporterNames != null) {
         for(String metricsReportingName : metricsReporterNames) {
            try {
               MetricsReporting reporter = MetricsReporting.valueOf(metricsReportingName.trim().toUpperCase());
               finalReporterList.add(reporter);
            } catch (IllegalArgumentException var7) {
               LOGGER.warn("Metrics reporter skipped due to invalid configured reporter: " + metricsReportingName);
            }
         }
      }

      this.initReporting(finalReporterList);
   }

   public void close() throws Exception {
      if (this.reporters != null) {
         for(Closeable reporter : this.reporters) {
            reporter.close();
         }
      }

      for(Map.Entry metric : this.metricRegistry.getMetrics().entrySet()) {
         this.metricRegistry.remove((String)metric.getKey());
      }

      this.timers.invalidateAll();
      this.counters.invalidateAll();
      this.meters.invalidateAll();
   }

   public void startStoredScope(String name) {
      if (((HashMap)this.threadLocalScopes.get()).containsKey(name)) {
         ((CodahaleMetricsScope)((HashMap)this.threadLocalScopes.get()).get(name)).open();
      } else {
         ((HashMap)this.threadLocalScopes.get()).put(name, new CodahaleMetricsScope(name));
      }

   }

   public void endStoredScope(String name) {
      if (((HashMap)this.threadLocalScopes.get()).containsKey(name)) {
         ((CodahaleMetricsScope)((HashMap)this.threadLocalScopes.get()).get(name)).close();
         ((HashMap)this.threadLocalScopes.get()).remove(name);
      }

   }

   public MetricsScope getStoredScope(String name) throws IllegalArgumentException {
      if (((HashMap)this.threadLocalScopes.get()).containsKey(name)) {
         return (MetricsScope)((HashMap)this.threadLocalScopes.get()).get(name);
      } else {
         throw new IllegalArgumentException("No metrics scope named " + name);
      }
   }

   public MetricsScope createScope(String name) {
      return new CodahaleMetricsScope(name);
   }

   public void endScope(MetricsScope scope) {
      ((CodahaleMetricsScope)scope).close();
   }

   public Long incrementCounter(String name) {
      return this.incrementCounter(name, 1L);
   }

   public Long incrementCounter(String name, long increment) {
      String key = name;

      Long var5;
      try {
         this.countersLock.lock();
         ((Counter)this.counters.get(key)).inc(increment);
         var5 = ((Counter)this.counters.get(key)).getCount();
      } catch (ExecutionException ee) {
         throw new IllegalStateException("Error retrieving counter from the metric registry ", ee);
      } finally {
         this.countersLock.unlock();
      }

      return var5;
   }

   public Long decrementCounter(String name) {
      return this.decrementCounter(name, 1L);
   }

   public Long decrementCounter(String name, long decrement) {
      String key = name;

      Long var5;
      try {
         this.countersLock.lock();
         ((Counter)this.counters.get(key)).dec(decrement);
         var5 = ((Counter)this.counters.get(key)).getCount();
      } catch (ExecutionException ee) {
         throw new IllegalStateException("Error retrieving counter from the metric registry ", ee);
      } finally {
         this.countersLock.unlock();
      }

      return var5;
   }

   public void addGauge(String name, final MetricsVariable variable) {
      Gauge gauge = new Gauge() {
         public Object getValue() {
            return variable.getValue();
         }
      };
      this.addGaugeInternal(name, gauge);
   }

   public void addRatio(String name, MetricsVariable numerator, MetricsVariable denominator) {
      Preconditions.checkArgument(numerator != null, "Numerator must not be null");
      Preconditions.checkArgument(denominator != null, "Denominator must not be null");
      MetricVariableRatioGauge gauge = new MetricVariableRatioGauge(numerator, denominator);
      this.addGaugeInternal(name, gauge);
   }

   private void addGaugeInternal(String name, Gauge gauge) {
      try {
         this.gaugesLock.lock();
         this.gauges.put(name, gauge);
         if (this.metricRegistry.getGauges().containsKey(name)) {
            LOGGER.warn("A Gauge with name [" + name + "] already exists.  The old gauge will be overwritten, but this is not recommended");
            this.metricRegistry.remove(name);
         }

         this.metricRegistry.register(name, gauge);
      } finally {
         this.gaugesLock.unlock();
      }

   }

   public void markMeter(String name) {
      try {
         this.metersLock.lock();
         Meter meter = (Meter)this.meters.get(name);
         meter.mark();
      } catch (ExecutionException e) {
         throw new IllegalStateException("Error retrieving meter " + name + " from the metric registry ", e);
      } finally {
         this.metersLock.unlock();
      }

   }

   private Timer getTimer(String name) {
      String key = name;

      Timer var4;
      try {
         this.timersLock.lock();
         Timer timer = (Timer)this.timers.get(key);
         var4 = timer;
      } catch (ExecutionException e) {
         throw new IllegalStateException("Error retrieving timer " + name + " from the metric registry ", e);
      } finally {
         this.timersLock.unlock();
      }

      return var4;
   }

   private void registerAll(String prefix, MetricSet metricSet) {
      for(Map.Entry entry : metricSet.getMetrics().entrySet()) {
         if (entry.getValue() instanceof MetricSet) {
            this.registerAll(prefix + "." + (String)entry.getKey(), (MetricSet)entry.getValue());
         } else {
            this.metricRegistry.register(prefix + "." + (String)entry.getKey(), (Metric)entry.getValue());
         }
      }

   }

   @VisibleForTesting
   public MetricRegistry getMetricRegistry() {
      return this.metricRegistry;
   }

   @VisibleForTesting
   public String dumpJson() throws Exception {
      ObjectMapper jsonMapper = (new ObjectMapper()).registerModule(new MetricsModule(TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS, false));
      return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.metricRegistry);
   }

   private void initReporting(Set reportingSet) {
      for(MetricsReporting reporting : reportingSet) {
         switch (reporting) {
            case CONSOLE:
               ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(this.metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
               consoleReporter.start(1L, TimeUnit.SECONDS);
               this.reporters.add(consoleReporter);
               break;
            case JMX:
               JmxReporter jmxReporter = JmxReporter.forRegistry(this.metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
               jmxReporter.start();
               this.reporters.add(jmxReporter);
               break;
            case JSON_FILE:
               JsonFileReporter jsonFileReporter = new JsonFileReporter();
               jsonFileReporter.start();
               this.reporters.add(jsonFileReporter);
               break;
            case HADOOP2:
               String applicationName = this.conf.get(HiveConf.ConfVars.HIVE_METRICS_HADOOP2_COMPONENT_NAME.varname);
               long reportingInterval = HiveConf.toTime(this.conf.get(HiveConf.ConfVars.HIVE_METRICS_HADOOP2_INTERVAL.varname), TimeUnit.SECONDS, TimeUnit.SECONDS);
               HadoopMetrics2Reporter metrics2Reporter = HadoopMetrics2Reporter.forRegistry(this.metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build(DefaultMetricsSystem.initialize(applicationName), applicationName, applicationName, "General");
               metrics2Reporter.start(reportingInterval, TimeUnit.SECONDS);
         }
      }

   }

   public class CodahaleMetricsScope implements MetricsScope {
      private final String name;
      private final Timer timer;
      private Timer.Context timerContext;
      private boolean isOpen;

      private CodahaleMetricsScope(String name) {
         this.isOpen = false;
         this.name = name;
         this.timer = CodahaleMetrics.this.getTimer(name);
         this.open();
      }

      public void open() {
         if (!this.isOpen) {
            this.isOpen = true;
            this.timerContext = this.timer.time();
            CodahaleMetrics.this.incrementCounter("active_calls_" + this.name);
         } else {
            CodahaleMetrics.LOGGER.warn("Scope named " + this.name + " is not closed, cannot be opened.");
         }

      }

      public void close() {
         if (this.isOpen) {
            this.timerContext.close();
            CodahaleMetrics.this.decrementCounter("active_calls_" + this.name);
         } else {
            CodahaleMetrics.LOGGER.warn("Scope named " + this.name + " is not open, cannot be closed.");
         }

         this.isOpen = false;
      }
   }

   class JsonFileReporter implements Closeable {
      private ObjectMapper jsonMapper = null;
      private java.util.Timer timer = null;

      public void start() {
         this.jsonMapper = (new ObjectMapper()).registerModule(new MetricsModule(TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS, false));
         this.timer = new java.util.Timer(true);
         long time = CodahaleMetrics.this.conf.getTimeVar(HiveConf.ConfVars.HIVE_METRICS_JSON_FILE_INTERVAL, TimeUnit.MILLISECONDS);
         final String pathString = CodahaleMetrics.this.conf.getVar(HiveConf.ConfVars.HIVE_METRICS_JSON_FILE_LOCATION);
         this.timer.schedule(new TimerTask() {
            public void run() {
               BufferedWriter bw = null;

               try {
                  String json = JsonFileReporter.this.jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(CodahaleMetrics.this.metricRegistry);
                  Path tmpPath = new Path(pathString + ".tmp");
                  URI tmpPathURI = tmpPath.toUri();
                  FileSystem fs = null;
                  if (tmpPathURI.getScheme() == null && tmpPathURI.getAuthority() == null) {
                     fs = FileSystem.getLocal(CodahaleMetrics.this.conf);
                  } else {
                     fs = FileSystem.get(tmpPathURI, CodahaleMetrics.this.conf);
                  }

                  fs.delete(tmpPath, true);
                  bw = new BufferedWriter(new OutputStreamWriter(fs.create(tmpPath, true)));
                  bw.write(json);
                  bw.close();
                  fs.setPermission(tmpPath, FsPermission.createImmutable((short)420));
                  Path path = new Path(pathString);
                  fs.rename(tmpPath, path);
                  fs.setPermission(path, FsPermission.createImmutable((short)420));
               } catch (Exception e) {
                  CodahaleMetrics.LOGGER.warn("Error writing JSON Metrics to file", e);
               } finally {
                  try {
                     if (bw != null) {
                        bw.close();
                     }
                  } catch (IOException var14) {
                  }

               }

            }
         }, 0L, time);
      }

      public void close() {
         if (this.timer != null) {
            this.timer.cancel();
         }

      }
   }
}
