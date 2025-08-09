package com.codahale.metrics.jvm;

import com.codahale.metrics.RatioGauge;
import com.codahale.metrics.RatioGauge.Ratio;
import com.sun.management.UnixOperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class FileDescriptorRatioGauge extends RatioGauge {
   private static boolean unixOperatingSystemMXBeanExists = false;
   private final OperatingSystemMXBean os;

   public FileDescriptorRatioGauge() {
      this(ManagementFactory.getOperatingSystemMXBean());
   }

   public FileDescriptorRatioGauge(OperatingSystemMXBean os) {
      this.os = os;
   }

   protected RatioGauge.Ratio getRatio() {
      if (unixOperatingSystemMXBeanExists && this.os instanceof UnixOperatingSystemMXBean) {
         UnixOperatingSystemMXBean unixOs = (UnixOperatingSystemMXBean)this.os;
         return Ratio.of((double)unixOs.getOpenFileDescriptorCount(), (double)unixOs.getMaxFileDescriptorCount());
      } else {
         return Ratio.of(Double.NaN, Double.NaN);
      }
   }

   static {
      try {
         Class.forName("com.sun.management.UnixOperatingSystemMXBean");
         unixOperatingSystemMXBeanExists = true;
      } catch (ClassNotFoundException var1) {
      }

   }
}
