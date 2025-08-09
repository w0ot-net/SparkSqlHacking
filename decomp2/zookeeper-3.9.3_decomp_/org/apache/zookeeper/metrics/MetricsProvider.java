package org.apache.zookeeper.metrics;

import java.util.Properties;
import java.util.function.BiConsumer;

public interface MetricsProvider {
   void configure(Properties var1) throws MetricsProviderLifeCycleException;

   void start() throws MetricsProviderLifeCycleException;

   MetricsContext getRootContext();

   void stop();

   void dump(BiConsumer var1);

   void resetAllValues();
}
