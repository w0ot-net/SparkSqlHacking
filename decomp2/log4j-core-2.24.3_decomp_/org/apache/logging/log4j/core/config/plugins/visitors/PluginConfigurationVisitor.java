package org.apache.logging.log4j.core.config.plugins.visitors;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;

public class PluginConfigurationVisitor extends AbstractPluginVisitor {
   public PluginConfigurationVisitor() {
      super(PluginConfiguration.class);
   }

   public Object visit(final Configuration configuration, final Node node, final LogEvent event, final StringBuilder log) {
      if (this.conversionType.isInstance(configuration)) {
         log.append("Configuration");
         if (configuration.getName() != null) {
            log.append('(').append(configuration.getName()).append(')');
         }

         return configuration;
      } else {
         LOGGER.warn("Variable annotated with @PluginConfiguration is not compatible with type {}.", configuration.getClass());
         return null;
      }
   }
}
