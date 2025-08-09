package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.KeyValuePairComponentBuilder;

class DefaultKeyValuePairComponentBuilder extends DefaultComponentAndConfigurationBuilder implements KeyValuePairComponentBuilder {
   public DefaultKeyValuePairComponentBuilder(final DefaultConfigurationBuilder builder, final String key, final String value) {
      super(builder, "KeyValuePair");
      this.addAttribute("key", key);
      this.addAttribute("value", value);
   }
}
