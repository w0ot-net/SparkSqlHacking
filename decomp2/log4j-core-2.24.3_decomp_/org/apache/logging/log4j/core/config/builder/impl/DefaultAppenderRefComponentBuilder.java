package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;

class DefaultAppenderRefComponentBuilder extends DefaultComponentAndConfigurationBuilder implements AppenderRefComponentBuilder {
   public DefaultAppenderRefComponentBuilder(final DefaultConfigurationBuilder builder, final String ref) {
      super(builder, "AppenderRef");
      this.addAttribute("ref", ref);
   }

   public AppenderRefComponentBuilder add(final FilterComponentBuilder builder) {
      return (AppenderRefComponentBuilder)this.addComponent(builder);
   }
}
