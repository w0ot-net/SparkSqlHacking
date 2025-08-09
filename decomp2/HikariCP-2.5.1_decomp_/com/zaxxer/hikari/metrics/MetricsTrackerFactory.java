package com.zaxxer.hikari.metrics;

public interface MetricsTrackerFactory {
   MetricsTracker create(String var1, PoolStats var2);
}
