package org.apache.logging.log4j.core.util;

import org.apache.logging.log4j.core.config.plugins.util.PluginBuilder;

public interface Builder {
   Object build();

   default boolean isValid() {
      return PluginBuilder.validateFields(this, this.getErrorPrefix());
   }

   default String getErrorPrefix() {
      return "Component";
   }
}
