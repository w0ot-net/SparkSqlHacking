package org.apache.logging.log4j.core.lookup;

import java.util.Map;
import java.util.Properties;

public final class ConfigurationStrSubstitutor extends StrSubstitutor {
   public ConfigurationStrSubstitutor() {
   }

   public ConfigurationStrSubstitutor(final Map valueMap) {
      super(valueMap);
   }

   public ConfigurationStrSubstitutor(final Properties properties) {
      super(properties);
   }

   public ConfigurationStrSubstitutor(final StrLookup lookup) {
      super(lookup);
   }

   public ConfigurationStrSubstitutor(final StrSubstitutor other) {
      super(other);
   }

   public String toString() {
      return "ConfigurationStrSubstitutor{" + super.toString() + "}";
   }
}
