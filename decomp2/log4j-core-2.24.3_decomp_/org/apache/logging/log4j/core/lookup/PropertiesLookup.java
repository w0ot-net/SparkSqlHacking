package org.apache.logging.log4j.core.lookup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;

public final class PropertiesLookup implements StrLookup {
   private final Map contextProperties;
   private final Map configurationProperties;

   public PropertiesLookup(final Property[] configProperties, final Map contextProperties) {
      this.contextProperties = contextProperties == null ? Collections.emptyMap() : contextProperties;
      this.configurationProperties = configProperties == null ? Collections.emptyMap() : createConfigurationPropertyMap(configProperties);
   }

   public PropertiesLookup(final Map properties) {
      this(Property.EMPTY_ARRAY, properties);
   }

   public String lookup(final LogEvent event, final String key) {
      return this.lookup(key);
   }

   public String lookup(final String key) {
      LookupResult result = this.evaluate(key);
      return result == null ? null : result.value();
   }

   public LookupResult evaluate(final String key) {
      if (key == null) {
         return null;
      } else {
         LookupResult configResult = (LookupResult)this.configurationProperties.get(key);
         if (configResult != null) {
            return configResult;
         } else {
            String contextResult = (String)this.contextProperties.get(key);
            return contextResult == null ? null : new ContextPropertyResult(contextResult);
         }
      }
   }

   public LookupResult evaluate(final LogEvent event, final String key) {
      return this.evaluate(key);
   }

   public String toString() {
      return "PropertiesLookup{contextProperties=" + this.contextProperties + ", configurationProperties=" + this.configurationProperties + '}';
   }

   private static Map createConfigurationPropertyMap(final Property[] props) {
      Map<String, ConfigurationPropertyResult> result = new HashMap(props.length);

      for(Property property : props) {
         result.put(property.getName(), new ConfigurationPropertyResult(property.getRawValue()));
      }

      return result;
   }

   private static final class ConfigurationPropertyResult implements LookupResult {
      private final String value;

      ConfigurationPropertyResult(final String value) {
         this.value = (String)Objects.requireNonNull(value, "value is required");
      }

      public String value() {
         return this.value;
      }

      public boolean isLookupEvaluationAllowedInValue() {
         return true;
      }

      public String toString() {
         return "ConfigurationPropertyResult{'" + this.value + "'}";
      }
   }

   private static final class ContextPropertyResult implements LookupResult {
      private final String value;

      ContextPropertyResult(final String value) {
         this.value = (String)Objects.requireNonNull(value, "value is required");
      }

      public String value() {
         return this.value;
      }

      public boolean isLookupEvaluationAllowedInValue() {
         return false;
      }

      public String toString() {
         return "ContextPropertyResult{'" + this.value + "'}";
      }
   }
}
