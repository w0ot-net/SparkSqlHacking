package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;

class DefaultComponentAndConfigurationBuilder extends DefaultComponentBuilder {
   DefaultComponentAndConfigurationBuilder(final DefaultConfigurationBuilder builder, final String name, final String type, final String value) {
      super(builder, name, type, value);
   }

   DefaultComponentAndConfigurationBuilder(final DefaultConfigurationBuilder builder, final String name, final String type) {
      super(builder, name, type);
   }

   public DefaultComponentAndConfigurationBuilder(final DefaultConfigurationBuilder builder, final String type) {
      super(builder, type);
   }
}
