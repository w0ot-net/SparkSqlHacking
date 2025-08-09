package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;

class DefaultLayoutComponentBuilder extends DefaultComponentAndConfigurationBuilder implements LayoutComponentBuilder {
   public DefaultLayoutComponentBuilder(final DefaultConfigurationBuilder builder, final String type) {
      super(builder, type);
   }
}
