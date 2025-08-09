package org.apache.logging.log4j.core.config.builder.api;

import org.apache.logging.log4j.core.config.builder.impl.DefaultConfigurationBuilder;

public abstract class ConfigurationBuilderFactory {
   public static ConfigurationBuilder newConfigurationBuilder() {
      return new DefaultConfigurationBuilder();
   }

   public static ConfigurationBuilder newConfigurationBuilder(final Class clazz) {
      return new DefaultConfigurationBuilder(clazz);
   }
}
