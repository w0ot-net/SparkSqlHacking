package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;

class DefaultFilterComponentBuilder extends DefaultComponentAndConfigurationBuilder implements FilterComponentBuilder {
   public DefaultFilterComponentBuilder(final DefaultConfigurationBuilder builder, final String type, final String onMatch, final String onMismatch) {
      super(builder, type);
      this.addAttribute("onMatch", onMatch);
      this.addAttribute("onMismatch", onMismatch);
   }
}
