package org.apache.curator.utils;

import java.util.concurrent.TimeUnit;
import org.apache.curator.drivers.TracerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTracerDriver implements TracerDriver {
   private final Logger log = LoggerFactory.getLogger(this.getClass());

   public void addTrace(String name, long time, TimeUnit unit) {
      if (this.log.isTraceEnabled()) {
         this.log.trace("Trace: " + name + " - " + TimeUnit.MILLISECONDS.convert(time, unit) + " ms");
      }

   }

   public void addCount(String name, int increment) {
      if (this.log.isTraceEnabled()) {
         this.log.trace("Counter " + name + ": " + increment);
      }

   }
}
