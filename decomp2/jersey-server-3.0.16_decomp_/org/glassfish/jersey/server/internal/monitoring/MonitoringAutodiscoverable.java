package org.glassfish.jersey.server.internal.monitoring;

import jakarta.annotation.Priority;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.jersey.internal.spi.ForcedAutoDiscoverable;
import org.glassfish.jersey.server.ServerProperties;

@ConstrainedTo(RuntimeType.SERVER)
@Priority(2000)
public final class MonitoringAutodiscoverable implements ForcedAutoDiscoverable {
   public void configure(FeatureContext context) {
      if (!context.getConfiguration().isRegistered(MonitoringFeature.class)) {
         Boolean monitoringEnabled = (Boolean)ServerProperties.getValue(context.getConfiguration().getProperties(), "jersey.config.server.monitoring.enabled", (Object)Boolean.FALSE);
         Boolean statisticsEnabled = (Boolean)ServerProperties.getValue(context.getConfiguration().getProperties(), "jersey.config.server.monitoring.statistics.enabled", (Object)Boolean.FALSE);
         Boolean mbeansEnabled = (Boolean)ServerProperties.getValue(context.getConfiguration().getProperties(), "jersey.config.server.monitoring.statistics.mbeans.enabled", (Object)Boolean.FALSE);
         if (monitoringEnabled || statisticsEnabled || mbeansEnabled) {
            context.register(MonitoringFeature.class);
         }
      }

   }
}
