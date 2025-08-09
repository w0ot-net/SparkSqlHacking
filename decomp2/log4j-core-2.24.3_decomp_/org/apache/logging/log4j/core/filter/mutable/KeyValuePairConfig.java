package org.apache.logging.log4j.core.filter.mutable;

import java.util.Map;

public class KeyValuePairConfig {
   private Map configs;

   public Map getConfigs() {
      return this.configs;
   }

   public void setConfig(final Map configs) {
      this.configs = configs;
   }
}
