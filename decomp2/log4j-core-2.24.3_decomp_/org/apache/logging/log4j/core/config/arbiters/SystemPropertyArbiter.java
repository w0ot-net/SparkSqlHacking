package org.apache.logging.log4j.core.config.arbiters;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;

@Plugin(
   name = "SystemPropertyArbiter",
   category = "Core",
   elementType = "Arbiter",
   deferChildren = true,
   printObject = true
)
public class SystemPropertyArbiter implements Arbiter {
   private final String propertyName;
   private final String propertyValue;

   private SystemPropertyArbiter(final String propertyName, final String propertyValue) {
      this.propertyName = propertyName;
      this.propertyValue = propertyValue;
   }

   public boolean isCondition() {
      String value = System.getProperty(this.propertyName);
      return value != null && (this.propertyValue == null || value.equals(this.propertyValue));
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      public static final String ATTR_PROPERTY_NAME = "propertyName";
      public static final String ATTR_PROPERTY_VALUE = "propertyValue";
      @PluginBuilderAttribute("propertyName")
      private String propertyName;
      @PluginBuilderAttribute("propertyValue")
      private String propertyValue;

      public Builder setPropertyName(final String propertyName) {
         this.propertyName = propertyName;
         return this.asBuilder();
      }

      public Builder setPropertyValue(final String propertyValue) {
         this.propertyValue = propertyValue;
         return this.asBuilder();
      }

      public Builder asBuilder() {
         return this;
      }

      public SystemPropertyArbiter build() {
         return new SystemPropertyArbiter(this.propertyName, this.propertyValue);
      }
   }
}
