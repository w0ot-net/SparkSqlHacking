package org.apache.curator;

import java.util.concurrent.TimeUnit;
import org.apache.curator.drivers.TracerDriver;

public class TimeTrace {
   private final String name;
   private final TracerDriver driver;
   private final long startTimeNanos = System.nanoTime();

   public TimeTrace(String name, TracerDriver driver) {
      this.name = name;
      this.driver = driver;
   }

   public void commit() {
      long elapsed = System.nanoTime() - this.startTimeNanos;
      this.driver.addTrace(this.name, elapsed, TimeUnit.NANOSECONDS);
   }
}
