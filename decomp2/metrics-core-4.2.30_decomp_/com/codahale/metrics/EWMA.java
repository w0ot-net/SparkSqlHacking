package com.codahale.metrics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class EWMA {
   private static final int INTERVAL = 5;
   private static final double SECONDS_PER_MINUTE = (double)60.0F;
   private static final int ONE_MINUTE = 1;
   private static final int FIVE_MINUTES = 5;
   private static final int FIFTEEN_MINUTES = 15;
   private static final double M1_ALPHA = (double)1.0F - Math.exp(-0.08333333333333333);
   private static final double M5_ALPHA = (double)1.0F - Math.exp(-0.016666666666666666);
   private static final double M15_ALPHA = (double)1.0F - Math.exp(-0.005555555555555555);
   private volatile boolean initialized = false;
   private volatile double rate = (double)0.0F;
   private final LongAdder uncounted = new LongAdder();
   private final double alpha;
   private final double interval;

   public static EWMA oneMinuteEWMA() {
      return new EWMA(M1_ALPHA, 5L, TimeUnit.SECONDS);
   }

   public static EWMA fiveMinuteEWMA() {
      return new EWMA(M5_ALPHA, 5L, TimeUnit.SECONDS);
   }

   public static EWMA fifteenMinuteEWMA() {
      return new EWMA(M15_ALPHA, 5L, TimeUnit.SECONDS);
   }

   public EWMA(double alpha, long interval, TimeUnit intervalUnit) {
      this.interval = (double)intervalUnit.toNanos(interval);
      this.alpha = alpha;
   }

   public void update(long n) {
      this.uncounted.add(n);
   }

   public void reset() {
      this.uncounted.reset();
      this.rate = Double.MIN_NORMAL;
   }

   public void tick() {
      long count = this.uncounted.sumThenReset();
      double instantRate = (double)count / this.interval;
      if (this.initialized) {
         double oldRate = this.rate;
         this.rate = oldRate + this.alpha * (instantRate - oldRate);
      } else {
         this.rate = instantRate;
         this.initialized = true;
      }

   }

   public double getRate(TimeUnit rateUnit) {
      return this.rate * (double)rateUnit.toNanos(1L);
   }
}
