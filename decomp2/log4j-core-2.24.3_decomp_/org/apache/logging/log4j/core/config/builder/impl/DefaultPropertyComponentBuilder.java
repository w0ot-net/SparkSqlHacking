package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.PropertyComponentBuilder;

class DefaultPropertyComponentBuilder extends DefaultComponentAndConfigurationBuilder implements PropertyComponentBuilder {
   public DefaultPropertyComponentBuilder(final DefaultConfigurationBuilder builder, final String name, final String value) {
      super(builder, name, "Property", value);
   }
}
