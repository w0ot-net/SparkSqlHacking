package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Objects;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.ErrorHandler;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.filter.AbstractFilterable;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.util.Integers;

public abstract class AbstractAppender extends AbstractFilterable implements Appender, LocationAware {
   private final String name;
   private final boolean ignoreExceptions;
   private final Layout layout;
   private ErrorHandler handler;

   public static int parseInt(final String s, final int defaultValue) {
      try {
         return Integers.parseInt(s, defaultValue);
      } catch (NumberFormatException e) {
         LOGGER.error("Could not parse \"{}\" as an integer,  using default value {}: {}", s, defaultValue, e);
         return defaultValue;
      }
   }

   public boolean requiresLocation() {
      return this.layout instanceof LocationAware && ((LocationAware)this.layout).requiresLocation();
   }

   /** @deprecated */
   @Deprecated
   protected AbstractAppender(final String name, final Filter filter, final Layout layout) {
      this(name, filter, layout, true, Property.EMPTY_ARRAY);
   }

   /** @deprecated */
   @Deprecated
   protected AbstractAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions) {
      this(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
   }

   protected AbstractAppender(final String name, final Filter filter, final Layout layout, final boolean ignoreExceptions, final Property[] properties) {
      super(filter, properties);
      this.handler = new DefaultErrorHandler(this);
      this.name = (String)Objects.requireNonNull(name, "name");
      this.layout = layout;
      this.ignoreExceptions = ignoreExceptions;
   }

   public void error(final String msg) {
      this.handler.error(msg);
   }

   public void error(final String msg, final LogEvent event, final Throwable t) {
      this.handler.error(msg, event, t);
   }

   public void error(final String msg, final Throwable t) {
      this.handler.error(msg, t);
   }

   public ErrorHandler getHandler() {
      return this.handler;
   }

   public Layout getLayout() {
      return this.layout;
   }

   public String getName() {
      return this.name;
   }

   public boolean ignoreExceptions() {
      return this.ignoreExceptions;
   }

   public void setHandler(final ErrorHandler handler) {
      if (handler == null) {
         LOGGER.error("The handler cannot be set to null");
      } else if (this.isStarted()) {
         LOGGER.error("The handler cannot be changed once the appender is started");
      } else {
         this.handler = handler;
      }
   }

   protected Serializable toSerializable(final LogEvent event) {
      return this.layout != null ? this.layout.toSerializable(event) : null;
   }

   public String toString() {
      return this.name;
   }

   public abstract static class Builder extends AbstractFilterable.Builder {
      @PluginBuilderAttribute
      private boolean ignoreExceptions = true;
      @PluginElement("Layout")
      private Layout layout;
      @PluginBuilderAttribute
      @Required(
         message = "No appender name provided"
      )
      private String name;
      @PluginConfiguration
      private Configuration configuration;

      public Configuration getConfiguration() {
         return this.configuration;
      }

      public Layout getLayout() {
         return this.layout;
      }

      public String getName() {
         return this.name;
      }

      public Layout getOrCreateLayout() {
         return (Layout)(this.layout == null ? PatternLayout.createDefaultLayout(this.configuration) : this.layout);
      }

      public Layout getOrCreateLayout(final Charset charset) {
         return (Layout)(this.layout == null ? PatternLayout.newBuilder().withCharset(charset).withConfiguration(this.configuration).build() : this.layout);
      }

      public boolean isIgnoreExceptions() {
         return this.ignoreExceptions;
      }

      public Builder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return (Builder)this.asBuilder();
      }

      public Builder setIgnoreExceptions(final boolean ignoreExceptions) {
         this.ignoreExceptions = ignoreExceptions;
         return (Builder)this.asBuilder();
      }

      public Builder setLayout(final Layout layout) {
         this.layout = layout;
         return (Builder)this.asBuilder();
      }

      public Builder setName(final String name) {
         this.name = name;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withIgnoreExceptions(final boolean ignoreExceptions) {
         return this.setIgnoreExceptions(ignoreExceptions);
      }

      /** @deprecated */
      @Deprecated
      public Builder withLayout(final Layout layout) {
         return this.setLayout(layout);
      }

      /** @deprecated */
      @Deprecated
      public Builder withName(final String name) {
         return this.setName(name);
      }

      public String getErrorPrefix() {
         Class<?> appenderClass = this.getClass().getEnclosingClass();
         String name = this.getName();
         StringBuilder sb = new StringBuilder(appenderClass != null ? appenderClass.getSimpleName() : "Appender");
         if (name != null) {
            sb.append(" '").append(name).append("'");
         }

         return sb.toString();
      }
   }
}
