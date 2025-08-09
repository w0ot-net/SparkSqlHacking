package com.clearspring.analytics.stream.quantile;

public interface IQuantileEstimator {
   void offer(long var1);

   long getQuantile(double var1);
}
