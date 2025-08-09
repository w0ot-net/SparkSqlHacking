package com.codahale.metrics.jmx;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metered;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricRegistryListener;
import com.codahale.metrics.Reporter;
import com.codahale.metrics.Timer;
import java.io.Closeable;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.JMException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmxReporter implements Reporter, Closeable {
   private static final Logger LOGGER = LoggerFactory.getLogger(JmxReporter.class);
   private final MetricRegistry registry;
   private final JmxListener listener;

   public static Builder forRegistry(MetricRegistry registry) {
      return new Builder(registry);
   }

   private JmxReporter(MBeanServer mBeanServer, String domain, MetricRegistry registry, MetricFilter filter, MetricTimeUnits timeUnits, ObjectNameFactory objectNameFactory) {
      this.registry = registry;
      this.listener = new JmxListener(mBeanServer, domain, filter, timeUnits, objectNameFactory);
   }

   public void start() {
      this.registry.addListener(this.listener);
   }

   public void stop() {
      this.registry.removeListener(this.listener);
      this.listener.unregisterAll();
   }

   public void close() {
      this.stop();
   }

   ObjectNameFactory getObjectNameFactory() {
      return this.listener.objectNameFactory;
   }

   public static class Builder {
      private final MetricRegistry registry;
      private MBeanServer mBeanServer;
      private TimeUnit rateUnit;
      private TimeUnit durationUnit;
      private ObjectNameFactory objectNameFactory;
      private MetricFilter filter;
      private String domain;
      private Map specificDurationUnits;
      private Map specificRateUnits;

      private Builder(MetricRegistry registry) {
         this.filter = MetricFilter.ALL;
         this.registry = registry;
         this.rateUnit = TimeUnit.SECONDS;
         this.durationUnit = TimeUnit.MILLISECONDS;
         this.domain = "metrics";
         this.objectNameFactory = new DefaultObjectNameFactory();
         this.specificDurationUnits = Collections.emptyMap();
         this.specificRateUnits = Collections.emptyMap();
      }

      public Builder registerWith(MBeanServer mBeanServer) {
         this.mBeanServer = mBeanServer;
         return this;
      }

      public Builder convertRatesTo(TimeUnit rateUnit) {
         this.rateUnit = rateUnit;
         return this;
      }

      public Builder createsObjectNamesWith(ObjectNameFactory onFactory) {
         if (onFactory == null) {
            throw new IllegalArgumentException("null objectNameFactory");
         } else {
            this.objectNameFactory = onFactory;
            return this;
         }
      }

      public Builder convertDurationsTo(TimeUnit durationUnit) {
         this.durationUnit = durationUnit;
         return this;
      }

      public Builder filter(MetricFilter filter) {
         this.filter = filter;
         return this;
      }

      public Builder inDomain(String domain) {
         this.domain = domain;
         return this;
      }

      public Builder specificDurationUnits(Map specificDurationUnits) {
         this.specificDurationUnits = Collections.unmodifiableMap(specificDurationUnits);
         return this;
      }

      public Builder specificRateUnits(Map specificRateUnits) {
         this.specificRateUnits = Collections.unmodifiableMap(specificRateUnits);
         return this;
      }

      public JmxReporter build() {
         MetricTimeUnits timeUnits = new MetricTimeUnits(this.rateUnit, this.durationUnit, this.specificRateUnits, this.specificDurationUnits);
         if (this.mBeanServer == null) {
            this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
         }

         return new JmxReporter(this.mBeanServer, this.domain, this.registry, this.filter, timeUnits, this.objectNameFactory);
      }
   }

   private abstract static class AbstractBean implements MetricMBean {
      private final ObjectName objectName;

      AbstractBean(ObjectName objectName) {
         this.objectName = objectName;
      }

      public ObjectName objectName() {
         return this.objectName;
      }
   }

   private static class JmxGauge extends AbstractBean implements JmxGaugeMBean {
      private final Gauge metric;

      private JmxGauge(Gauge metric, ObjectName objectName) {
         super(objectName);
         this.metric = metric;
      }

      public Object getValue() {
         return this.metric.getValue();
      }

      public Number getNumber() {
         Object value = this.metric.getValue();
         return (Number)(value instanceof Number ? (Number)value : 0);
      }
   }

   private static class JmxCounter extends AbstractBean implements JmxCounterMBean {
      private final Counter metric;

      private JmxCounter(Counter metric, ObjectName objectName) {
         super(objectName);
         this.metric = metric;
      }

      public long getCount() {
         return this.metric.getCount();
      }
   }

   private static class JmxHistogram implements JmxHistogramMBean {
      private final ObjectName objectName;
      private final Histogram metric;

      private JmxHistogram(Histogram metric, ObjectName objectName) {
         this.metric = metric;
         this.objectName = objectName;
      }

      public ObjectName objectName() {
         return this.objectName;
      }

      public double get50thPercentile() {
         return this.metric.getSnapshot().getMedian();
      }

      public long getCount() {
         return this.metric.getCount();
      }

      public long getMin() {
         return this.metric.getSnapshot().getMin();
      }

      public long getMax() {
         return this.metric.getSnapshot().getMax();
      }

      public double getMean() {
         return this.metric.getSnapshot().getMean();
      }

      public double getStdDev() {
         return this.metric.getSnapshot().getStdDev();
      }

      public double get75thPercentile() {
         return this.metric.getSnapshot().get75thPercentile();
      }

      public double get95thPercentile() {
         return this.metric.getSnapshot().get95thPercentile();
      }

      public double get98thPercentile() {
         return this.metric.getSnapshot().get98thPercentile();
      }

      public double get99thPercentile() {
         return this.metric.getSnapshot().get99thPercentile();
      }

      public double get999thPercentile() {
         return this.metric.getSnapshot().get999thPercentile();
      }

      public long[] values() {
         return this.metric.getSnapshot().getValues();
      }

      public long getSnapshotSize() {
         return (long)this.metric.getSnapshot().size();
      }
   }

   private static class JmxMeter extends AbstractBean implements JmxMeterMBean {
      private final Metered metric;
      private final double rateFactor;
      private final String rateUnit;

      private JmxMeter(Metered metric, ObjectName objectName, TimeUnit rateUnit) {
         super(objectName);
         this.metric = metric;
         this.rateFactor = (double)rateUnit.toSeconds(1L);
         this.rateUnit = ("events/" + this.calculateRateUnit(rateUnit)).intern();
      }

      public long getCount() {
         return this.metric.getCount();
      }

      public double getMeanRate() {
         return this.metric.getMeanRate() * this.rateFactor;
      }

      public double getOneMinuteRate() {
         return this.metric.getOneMinuteRate() * this.rateFactor;
      }

      public double getFiveMinuteRate() {
         return this.metric.getFiveMinuteRate() * this.rateFactor;
      }

      public double getFifteenMinuteRate() {
         return this.metric.getFifteenMinuteRate() * this.rateFactor;
      }

      public String getRateUnit() {
         return this.rateUnit;
      }

      private String calculateRateUnit(TimeUnit unit) {
         String s = unit.toString().toLowerCase(Locale.US);
         return s.substring(0, s.length() - 1);
      }
   }

   static class JmxTimer extends JmxMeter implements JmxTimerMBean {
      private final Timer metric;
      private final double durationFactor;
      private final String durationUnit;

      private JmxTimer(Timer metric, ObjectName objectName, TimeUnit rateUnit, TimeUnit durationUnit) {
         super(metric, objectName, rateUnit, null);
         this.metric = metric;
         this.durationFactor = (double)1.0F / (double)durationUnit.toNanos(1L);
         this.durationUnit = durationUnit.toString().toLowerCase(Locale.US);
      }

      public double get50thPercentile() {
         return this.metric.getSnapshot().getMedian() * this.durationFactor;
      }

      public double getMin() {
         return (double)this.metric.getSnapshot().getMin() * this.durationFactor;
      }

      public double getMax() {
         return (double)this.metric.getSnapshot().getMax() * this.durationFactor;
      }

      public double getMean() {
         return this.metric.getSnapshot().getMean() * this.durationFactor;
      }

      public double getStdDev() {
         return this.metric.getSnapshot().getStdDev() * this.durationFactor;
      }

      public double get75thPercentile() {
         return this.metric.getSnapshot().get75thPercentile() * this.durationFactor;
      }

      public double get95thPercentile() {
         return this.metric.getSnapshot().get95thPercentile() * this.durationFactor;
      }

      public double get98thPercentile() {
         return this.metric.getSnapshot().get98thPercentile() * this.durationFactor;
      }

      public double get99thPercentile() {
         return this.metric.getSnapshot().get99thPercentile() * this.durationFactor;
      }

      public double get999thPercentile() {
         return this.metric.getSnapshot().get999thPercentile() * this.durationFactor;
      }

      public long[] values() {
         return this.metric.getSnapshot().getValues();
      }

      public String getDurationUnit() {
         return this.durationUnit;
      }
   }

   private static class JmxListener implements MetricRegistryListener {
      private final String name;
      private final MBeanServer mBeanServer;
      private final MetricFilter filter;
      private final MetricTimeUnits timeUnits;
      private final Map registered;
      private final ObjectNameFactory objectNameFactory;

      private JmxListener(MBeanServer mBeanServer, String name, MetricFilter filter, MetricTimeUnits timeUnits, ObjectNameFactory objectNameFactory) {
         this.mBeanServer = mBeanServer;
         this.name = name;
         this.filter = filter;
         this.timeUnits = timeUnits;
         this.registered = new ConcurrentHashMap();
         this.objectNameFactory = objectNameFactory;
      }

      private void registerMBean(Object mBean, ObjectName objectName) throws InstanceAlreadyExistsException, JMException {
         ObjectInstance objectInstance = this.mBeanServer.registerMBean(mBean, objectName);
         if (objectInstance != null) {
            this.registered.put(objectName, objectInstance.getObjectName());
         } else {
            this.registered.put(objectName, objectName);
         }

      }

      private void unregisterMBean(ObjectName originalObjectName) throws InstanceNotFoundException, MBeanRegistrationException {
         ObjectName storedObjectName = (ObjectName)this.registered.remove(originalObjectName);
         if (storedObjectName != null) {
            this.mBeanServer.unregisterMBean(storedObjectName);
         } else {
            this.mBeanServer.unregisterMBean(originalObjectName);
         }

      }

      public void onGaugeAdded(String name, Gauge gauge) {
         try {
            if (this.filter.matches(name, gauge)) {
               ObjectName objectName = this.createName("gauges", name);
               this.registerMBean(new JmxGauge(gauge, objectName), objectName);
            }
         } catch (InstanceAlreadyExistsException e) {
            JmxReporter.LOGGER.debug("Unable to register gauge", e);
         } catch (JMException e) {
            JmxReporter.LOGGER.warn("Unable to register gauge", e);
         }

      }

      public void onGaugeRemoved(String name) {
         try {
            ObjectName objectName = this.createName("gauges", name);
            this.unregisterMBean(objectName);
         } catch (InstanceNotFoundException e) {
            JmxReporter.LOGGER.debug("Unable to unregister gauge", e);
         } catch (MBeanRegistrationException e) {
            JmxReporter.LOGGER.warn("Unable to unregister gauge", e);
         }

      }

      public void onCounterAdded(String name, Counter counter) {
         try {
            if (this.filter.matches(name, counter)) {
               ObjectName objectName = this.createName("counters", name);
               this.registerMBean(new JmxCounter(counter, objectName), objectName);
            }
         } catch (InstanceAlreadyExistsException e) {
            JmxReporter.LOGGER.debug("Unable to register counter", e);
         } catch (JMException e) {
            JmxReporter.LOGGER.warn("Unable to register counter", e);
         }

      }

      public void onCounterRemoved(String name) {
         try {
            ObjectName objectName = this.createName("counters", name);
            this.unregisterMBean(objectName);
         } catch (InstanceNotFoundException e) {
            JmxReporter.LOGGER.debug("Unable to unregister counter", e);
         } catch (MBeanRegistrationException e) {
            JmxReporter.LOGGER.warn("Unable to unregister counter", e);
         }

      }

      public void onHistogramAdded(String name, Histogram histogram) {
         try {
            if (this.filter.matches(name, histogram)) {
               ObjectName objectName = this.createName("histograms", name);
               this.registerMBean(new JmxHistogram(histogram, objectName), objectName);
            }
         } catch (InstanceAlreadyExistsException e) {
            JmxReporter.LOGGER.debug("Unable to register histogram", e);
         } catch (JMException e) {
            JmxReporter.LOGGER.warn("Unable to register histogram", e);
         }

      }

      public void onHistogramRemoved(String name) {
         try {
            ObjectName objectName = this.createName("histograms", name);
            this.unregisterMBean(objectName);
         } catch (InstanceNotFoundException e) {
            JmxReporter.LOGGER.debug("Unable to unregister histogram", e);
         } catch (MBeanRegistrationException e) {
            JmxReporter.LOGGER.warn("Unable to unregister histogram", e);
         }

      }

      public void onMeterAdded(String name, Meter meter) {
         try {
            if (this.filter.matches(name, meter)) {
               ObjectName objectName = this.createName("meters", name);
               this.registerMBean(new JmxMeter(meter, objectName, this.timeUnits.rateFor(name)), objectName);
            }
         } catch (InstanceAlreadyExistsException e) {
            JmxReporter.LOGGER.debug("Unable to register meter", e);
         } catch (JMException e) {
            JmxReporter.LOGGER.warn("Unable to register meter", e);
         }

      }

      public void onMeterRemoved(String name) {
         try {
            ObjectName objectName = this.createName("meters", name);
            this.unregisterMBean(objectName);
         } catch (InstanceNotFoundException e) {
            JmxReporter.LOGGER.debug("Unable to unregister meter", e);
         } catch (MBeanRegistrationException e) {
            JmxReporter.LOGGER.warn("Unable to unregister meter", e);
         }

      }

      public void onTimerAdded(String name, Timer timer) {
         try {
            if (this.filter.matches(name, timer)) {
               ObjectName objectName = this.createName("timers", name);
               this.registerMBean(new JmxTimer(timer, objectName, this.timeUnits.rateFor(name), this.timeUnits.durationFor(name)), objectName);
            }
         } catch (InstanceAlreadyExistsException e) {
            JmxReporter.LOGGER.debug("Unable to register timer", e);
         } catch (JMException e) {
            JmxReporter.LOGGER.warn("Unable to register timer", e);
         }

      }

      public void onTimerRemoved(String name) {
         try {
            ObjectName objectName = this.createName("timers", name);
            this.unregisterMBean(objectName);
         } catch (InstanceNotFoundException e) {
            JmxReporter.LOGGER.debug("Unable to unregister timer", e);
         } catch (MBeanRegistrationException e) {
            JmxReporter.LOGGER.warn("Unable to unregister timer", e);
         }

      }

      private ObjectName createName(String type, String name) {
         return this.objectNameFactory.createName(type, this.name, name);
      }

      void unregisterAll() {
         for(ObjectName name : this.registered.keySet()) {
            try {
               this.unregisterMBean(name);
            } catch (InstanceNotFoundException e) {
               JmxReporter.LOGGER.debug("Unable to unregister metric", e);
            } catch (MBeanRegistrationException e) {
               JmxReporter.LOGGER.warn("Unable to unregister metric", e);
            }
         }

         this.registered.clear();
      }
   }

   private static class MetricTimeUnits {
      private final TimeUnit defaultRate;
      private final TimeUnit defaultDuration;
      private final Map rateOverrides;
      private final Map durationOverrides;

      MetricTimeUnits(TimeUnit defaultRate, TimeUnit defaultDuration, Map rateOverrides, Map durationOverrides) {
         this.defaultRate = defaultRate;
         this.defaultDuration = defaultDuration;
         this.rateOverrides = rateOverrides;
         this.durationOverrides = durationOverrides;
      }

      public TimeUnit durationFor(String name) {
         return (TimeUnit)this.durationOverrides.getOrDefault(name, this.defaultDuration);
      }

      public TimeUnit rateFor(String name) {
         return (TimeUnit)this.rateOverrides.getOrDefault(name, this.defaultRate);
      }
   }

   public interface JmxCounterMBean extends MetricMBean {
      long getCount();
   }

   public interface JmxGaugeMBean extends MetricMBean {
      Object getValue();

      Number getNumber();
   }

   public interface JmxHistogramMBean extends MetricMBean {
      long getCount();

      long getMin();

      long getMax();

      double getMean();

      double getStdDev();

      double get50thPercentile();

      double get75thPercentile();

      double get95thPercentile();

      double get98thPercentile();

      double get99thPercentile();

      double get999thPercentile();

      long[] values();

      long getSnapshotSize();
   }

   public interface JmxMeterMBean extends MetricMBean {
      long getCount();

      double getMeanRate();

      double getOneMinuteRate();

      double getFiveMinuteRate();

      double getFifteenMinuteRate();

      String getRateUnit();
   }

   public interface JmxTimerMBean extends JmxMeterMBean {
      double getMin();

      double getMax();

      double getMean();

      double getStdDev();

      double get50thPercentile();

      double get75thPercentile();

      double get95thPercentile();

      double get98thPercentile();

      double get99thPercentile();

      double get999thPercentile();

      long[] values();

      String getDurationUnit();
   }

   public interface MetricMBean {
      ObjectName objectName();
   }
}
