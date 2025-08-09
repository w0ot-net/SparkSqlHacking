package com.codahale.metrics.jvm;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class JvmAttributeGaugeSet implements MetricSet {
   private final RuntimeMXBean runtime;

   public JvmAttributeGaugeSet() {
      this(ManagementFactory.getRuntimeMXBean());
   }

   public JvmAttributeGaugeSet(RuntimeMXBean runtime) {
      this.runtime = runtime;
   }

   public Map getMetrics() {
      Map<String, Metric> gauges = new HashMap();
      RuntimeMXBean var10002 = this.runtime;
      Objects.requireNonNull(var10002);
      gauges.put("name", var10002::getName);
      gauges.put("vendor", (Gauge)() -> String.format(Locale.US, "%s %s %s (%s)", this.runtime.getVmVendor(), this.runtime.getVmName(), this.runtime.getVmVersion(), this.runtime.getSpecVersion()));
      var10002 = this.runtime;
      Objects.requireNonNull(var10002);
      gauges.put("uptime", var10002::getUptime);
      return Collections.unmodifiableMap(gauges);
   }
}
