package org.apache.logging.log4j.core.async;

import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

@Plugin(
   name = "AsyncWaitStrategyFactory",
   category = "Core",
   printObject = true
)
public class AsyncWaitStrategyFactoryConfig {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   private final String factoryClassName;

   public AsyncWaitStrategyFactoryConfig(final String factoryClassName) {
      this.factoryClassName = (String)Objects.requireNonNull(factoryClassName, "factoryClassName");
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (new Builder()).asBuilder();
   }

   public AsyncWaitStrategyFactory createWaitStrategyFactory() {
      try {
         return (AsyncWaitStrategyFactory)LoaderUtil.newCheckedInstanceOf(this.factoryClassName, AsyncWaitStrategyFactory.class);
      } catch (ClassCastException var2) {
         LOGGER.error("Ignoring factory '{}': it is not assignable to AsyncWaitStrategyFactory", this.factoryClassName);
         return null;
      } catch (ReflectiveOperationException e) {
         LOGGER.info("Invalid implementation class name value: error creating AsyncWaitStrategyFactory {}: {}", this.factoryClassName, e.getMessage(), e);
         return null;
      }
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute("class")
      @Required(
         message = "AsyncWaitStrategyFactory cannot be configured without a factory class name"
      )
      private String factoryClassName;

      public String getFactoryClassName() {
         return this.factoryClassName;
      }

      public Builder withFactoryClassName(final String className) {
         this.factoryClassName = className;
         return this.asBuilder();
      }

      public AsyncWaitStrategyFactoryConfig build() {
         return new AsyncWaitStrategyFactoryConfig(this.factoryClassName);
      }

      public Builder asBuilder() {
         return this;
      }
   }
}
