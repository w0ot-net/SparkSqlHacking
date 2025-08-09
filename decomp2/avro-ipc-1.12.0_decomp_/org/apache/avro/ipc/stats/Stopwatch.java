package org.apache.avro.ipc.stats;

class Stopwatch {
   public static final Ticks SYSTEM_TICKS = new SystemTicks();
   private Ticks ticks;
   private long start;
   private long elapsed = -1L;
   private boolean running;

   public Stopwatch(Ticks ticks) {
      this.ticks = ticks;
   }

   public long elapsedNanos() {
      if (this.running) {
         return this.ticks.ticks() - this.start;
      } else if (this.elapsed == -1L) {
         throw new IllegalStateException();
      } else {
         return this.elapsed;
      }
   }

   public void start() {
      if (this.running) {
         throw new IllegalStateException();
      } else {
         this.start = this.ticks.ticks();
         this.running = true;
      }
   }

   public void stop() {
      if (!this.running) {
         throw new IllegalStateException();
      } else {
         this.elapsed = this.ticks.ticks() - this.start;
         this.running = false;
      }
   }

   private static class SystemTicks implements Ticks {
      public long ticks() {
         return System.nanoTime();
      }
   }

   interface Ticks {
      long ticks();
   }
}
