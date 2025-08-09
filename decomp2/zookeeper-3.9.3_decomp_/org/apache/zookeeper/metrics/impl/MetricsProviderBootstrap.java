package org.apache.zookeeper.metrics.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.apache.zookeeper.metrics.MetricsProvider;
import org.apache.zookeeper.metrics.MetricsProviderLifeCycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MetricsProviderBootstrap {
   private static final Logger LOG = LoggerFactory.getLogger(MetricsProviderBootstrap.class);

   public static MetricsProvider startMetricsProvider(String metricsProviderClassName, Properties configuration) throws MetricsProviderLifeCycleException {
      try {
         Class<?> clazz = Class.forName(metricsProviderClassName, true, Thread.currentThread().getContextClassLoader());
         MetricsProvider metricsProvider = (MetricsProvider)clazz.getConstructor().newInstance();
         metricsProvider.configure(configuration);
         metricsProvider.start();
         return metricsProvider;
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | ClassNotFoundException error) {
         LOG.error("Cannot boot MetricsProvider {}", metricsProviderClassName, error);
         throw new MetricsProviderLifeCycleException("Cannot boot MetricsProvider " + metricsProviderClassName, error);
      } catch (MetricsProviderLifeCycleException error) {
         LOG.error("Cannot boot MetricsProvider {}", metricsProviderClassName, error);
         throw error;
      }
   }
}
