package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.CustomLevelComponentBuilder;

class DefaultCustomLevelComponentBuilder extends DefaultComponentAndConfigurationBuilder implements CustomLevelComponentBuilder {
   public DefaultCustomLevelComponentBuilder(final DefaultConfigurationBuilder builder, final String name, final int level) {
      super(builder, name, "CustomLevel");
      this.addAttribute("intLevel", level);
   }
}
