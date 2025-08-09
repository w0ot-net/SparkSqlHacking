package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;

class DefaultAppenderComponentBuilder extends DefaultComponentAndConfigurationBuilder implements AppenderComponentBuilder {
   public DefaultAppenderComponentBuilder(final DefaultConfigurationBuilder builder, final String name, final String type) {
      super(builder, name, type);
   }

   public AppenderComponentBuilder add(final LayoutComponentBuilder builder) {
      return (AppenderComponentBuilder)this.addComponent(builder);
   }

   public AppenderComponentBuilder add(final FilterComponentBuilder builder) {
      return (AppenderComponentBuilder)this.addComponent(builder);
   }
}
