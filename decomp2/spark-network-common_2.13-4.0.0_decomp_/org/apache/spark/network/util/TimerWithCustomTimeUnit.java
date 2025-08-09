package org.apache.spark.network.util;

import com.codahale.metrics.Clock;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class TimerWithCustomTimeUnit extends Timer {
   private final TimeUnit timeUnit;
   private final double nanosPerUnit;

   public TimerWithCustomTimeUnit(TimeUnit timeUnit) {
      this(timeUnit, Clock.defaultClock());
   }

   TimerWithCustomTimeUnit(TimeUnit timeUnit, Clock clock) {
      super(new ExponentiallyDecayingReservoir(), clock);
      this.timeUnit = timeUnit;
      this.nanosPerUnit = (double)timeUnit.toNanos(1L);
   }

   public Snapshot getSnapshot() {
      return new SnapshotWithCustomTimeUnit(super.getSnapshot());
   }

   private double toUnit(double nanos) {
      return nanos / this.nanosPerUnit;
   }

   private long toUnit(long nanos) {
      return this.timeUnit.convert(nanos, TimeUnit.NANOSECONDS);
   }

   private class SnapshotWithCustomTimeUnit extends Snapshot {
      private final Snapshot wrappedSnapshot;

      SnapshotWithCustomTimeUnit(Snapshot wrappedSnapshot) {
         this.wrappedSnapshot = wrappedSnapshot;
      }

      public double getValue(double v) {
         return TimerWithCustomTimeUnit.this.toUnit(this.wrappedSnapshot.getValue(v));
      }

      public long[] getValues() {
         long[] nanoValues = this.wrappedSnapshot.getValues();
         long[] customUnitValues = new long[nanoValues.length];

         for(int i = 0; i < nanoValues.length; ++i) {
            customUnitValues[i] = TimerWithCustomTimeUnit.this.toUnit(nanoValues[i]);
         }

         return customUnitValues;
      }

      public int size() {
         return this.wrappedSnapshot.size();
      }

      public long getMax() {
         return TimerWithCustomTimeUnit.this.toUnit(this.wrappedSnapshot.getMax());
      }

      public double getMean() {
         return TimerWithCustomTimeUnit.this.toUnit(this.wrappedSnapshot.getMean());
      }

      public long getMin() {
         return TimerWithCustomTimeUnit.this.toUnit(this.wrappedSnapshot.getMin());
      }

      public double getStdDev() {
         return TimerWithCustomTimeUnit.this.toUnit(this.wrappedSnapshot.getStdDev());
      }

      public void dump(OutputStream outputStream) {
         PrintWriter writer = new PrintWriter(outputStream);

         try {
            for(long value : this.getValues()) {
               writer.println(value);
            }
         } catch (Throwable var9) {
            try {
               writer.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         writer.close();
      }
   }
}
