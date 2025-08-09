package com.zaxxer.hikari.metrics.dropwizard;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.health.HealthCheck.Result;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public final class CodahaleHealthChecker {
   public static void registerHealthChecks(HikariPool pool, final HikariConfig hikariConfig, HealthCheckRegistry registry) {
      Properties healthCheckProperties = hikariConfig.getHealthCheckProperties();
      MetricRegistry metricRegistry = (MetricRegistry)hikariConfig.getMetricRegistry();
      long checkTimeoutMs = Long.parseLong(healthCheckProperties.getProperty("connectivityCheckTimeoutMs", String.valueOf(hikariConfig.getConnectionTimeout())));
      registry.register(MetricRegistry.name(hikariConfig.getPoolName(), new String[]{"pool", "ConnectivityCheck"}), new ConnectivityHealthCheck(pool, checkTimeoutMs));
      long expected99thPercentile = Long.parseLong(healthCheckProperties.getProperty("expected99thPercentileMs", "0"));
      if (metricRegistry != null && expected99thPercentile > 0L) {
         SortedMap<String, Timer> timers = metricRegistry.getTimers(new MetricFilter() {
            public boolean matches(String name, Metric metric) {
               return name.equals(MetricRegistry.name(hikariConfig.getPoolName(), new String[]{"pool", "Wait"}));
            }
         });
         if (!timers.isEmpty()) {
            Timer timer = (Timer)((Map.Entry)timers.entrySet().iterator().next()).getValue();
            registry.register(MetricRegistry.name(hikariConfig.getPoolName(), new String[]{"pool", "Connection99Percent"}), new Connection99Percent(timer, expected99thPercentile));
         }
      }

   }

   private CodahaleHealthChecker() {
   }

   private static class ConnectivityHealthCheck extends HealthCheck {
      private final HikariPool pool;
      private final long checkTimeoutMs;

      ConnectivityHealthCheck(HikariPool pool, long checkTimeoutMs) {
         this.pool = pool;
         this.checkTimeoutMs = checkTimeoutMs > 0L && checkTimeoutMs != 2147483647L ? checkTimeoutMs : TimeUnit.SECONDS.toMillis(10L);
      }

      protected HealthCheck.Result check() throws Exception {
         try {
            Connection connection = this.pool.getConnection(this.checkTimeoutMs);
            Throwable var2 = null;

            HealthCheck.Result var3;
            try {
               var3 = Result.healthy();
            } catch (Throwable var13) {
               var2 = var13;
               throw var13;
            } finally {
               if (connection != null) {
                  if (var2 != null) {
                     try {
                        connection.close();
                     } catch (Throwable var12) {
                        var2.addSuppressed(var12);
                     }
                  } else {
                     connection.close();
                  }
               }

            }

            return var3;
         } catch (SQLException e) {
            return Result.unhealthy(e);
         }
      }
   }

   private static class Connection99Percent extends HealthCheck {
      private final Timer waitTimer;
      private final long expected99thPercentile;

      Connection99Percent(Timer waitTimer, long expected99thPercentile) {
         this.waitTimer = waitTimer;
         this.expected99thPercentile = expected99thPercentile;
      }

      protected HealthCheck.Result check() throws Exception {
         long the99thPercentile = TimeUnit.NANOSECONDS.toMillis(Math.round(this.waitTimer.getSnapshot().get99thPercentile()));
         return the99thPercentile <= this.expected99thPercentile ? Result.healthy() : Result.unhealthy("99th percentile connection wait time of %dms exceeds the threshold %dms", new Object[]{the99thPercentile, this.expected99thPercentile});
      }
   }
}
