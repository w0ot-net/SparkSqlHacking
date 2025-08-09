package org.apache.logging.log4j.core.layout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.SimpleBindings;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.pattern.PatternFormatter;
import org.apache.logging.log4j.core.pattern.PatternParser;
import org.apache.logging.log4j.core.script.AbstractScript;
import org.apache.logging.log4j.core.script.ScriptRef;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(
   name = "ScriptPatternSelector",
   category = "Core",
   elementType = "patternSelector",
   printObject = true
)
public class ScriptPatternSelector implements PatternSelector, LocationAware {
   private final Map formatterMap;
   private final Map patternMap;
   private final PatternFormatter[] defaultFormatters;
   private final String defaultPattern;
   private static Logger LOGGER = StatusLogger.getLogger();
   private final AbstractScript script;
   private final Configuration configuration;
   private final boolean requiresLocation;

   private ScriptPatternSelector(final Configuration config, final AbstractScript script, final PatternMatch[] properties, final String defaultPattern, final boolean alwaysWriteExceptions, final boolean disableAnsi, final boolean noConsoleNoAnsi) {
      this.formatterMap = new HashMap();
      this.patternMap = new HashMap();
      this.script = script;
      this.configuration = config;
      PatternParser parser = PatternLayout.createPatternParser(config);
      boolean needsLocation = false;

      for(PatternMatch property : properties) {
         try {
            List<PatternFormatter> list = parser.parse(property.getPattern(), alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi);
            PatternFormatter[] formatters = (PatternFormatter[])list.toArray(PatternFormatter.EMPTY_ARRAY);
            this.formatterMap.put(property.getKey(), formatters);
            this.patternMap.put(property.getKey(), property.getPattern());

            for(int i = 0; !needsLocation && i < formatters.length; ++i) {
               needsLocation = formatters[i].requiresLocation();
            }
         } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Cannot parse pattern '" + property.getPattern() + "'", ex);
         }
      }

      try {
         List<PatternFormatter> list = parser.parse(defaultPattern, alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi);
         this.defaultFormatters = (PatternFormatter[])list.toArray(PatternFormatter.EMPTY_ARRAY);
         this.defaultPattern = defaultPattern;

         for(int i = 0; !needsLocation && i < this.defaultFormatters.length; ++i) {
            needsLocation = this.defaultFormatters[i].requiresLocation();
         }
      } catch (RuntimeException ex) {
         throw new IllegalArgumentException("Cannot parse pattern '" + defaultPattern + "'", ex);
      }

      this.requiresLocation = needsLocation;
   }

   /** @deprecated */
   @Deprecated
   public ScriptPatternSelector(final AbstractScript script, final PatternMatch[] properties, final String defaultPattern, final boolean alwaysWriteExceptions, final boolean disableAnsi, final boolean noConsoleNoAnsi, final Configuration config) {
      this.formatterMap = new HashMap();
      this.patternMap = new HashMap();
      this.script = script;
      this.configuration = config;
      if (!(script instanceof ScriptRef)) {
         config.getScriptManager().addScript(script);
      }

      PatternParser parser = PatternLayout.createPatternParser(config);
      boolean needsLocation = false;

      for(PatternMatch property : properties) {
         try {
            List<PatternFormatter> list = parser.parse(property.getPattern(), alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi);
            PatternFormatter[] formatters = (PatternFormatter[])list.toArray(PatternFormatter.EMPTY_ARRAY);
            this.formatterMap.put(property.getKey(), formatters);
            this.patternMap.put(property.getKey(), property.getPattern());

            for(int i = 0; !needsLocation && i < formatters.length; ++i) {
               needsLocation = formatters[i].requiresLocation();
            }
         } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Cannot parse pattern '" + property.getPattern() + "'", ex);
         }
      }

      try {
         List<PatternFormatter> list = parser.parse(defaultPattern, alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi);
         this.defaultFormatters = (PatternFormatter[])list.toArray(PatternFormatter.EMPTY_ARRAY);
         this.defaultPattern = defaultPattern;

         for(int i = 0; !needsLocation && i < this.defaultFormatters.length; ++i) {
            needsLocation = this.defaultFormatters[i].requiresLocation();
         }
      } catch (RuntimeException ex) {
         throw new IllegalArgumentException("Cannot parse pattern '" + defaultPattern + "'", ex);
      }

      this.requiresLocation = needsLocation;
   }

   public boolean requiresLocation() {
      return this.requiresLocation;
   }

   public PatternFormatter[] getFormatters(final LogEvent event) {
      SimpleBindings bindings = new SimpleBindings();
      bindings.putAll(this.configuration.getProperties());
      bindings.put("substitutor", this.configuration.getStrSubstitutor());
      bindings.put("logEvent", event);
      Object object = this.configuration.getScriptManager().execute(this.script.getName(), bindings);
      if (object == null) {
         return this.defaultFormatters;
      } else {
         PatternFormatter[] patternFormatter = (PatternFormatter[])this.formatterMap.get(object.toString());
         return patternFormatter == null ? this.defaultFormatters : patternFormatter;
      }
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public static ScriptPatternSelector createSelector(final AbstractScript script, final PatternMatch[] properties, final String defaultPattern, final boolean alwaysWriteExceptions, final boolean noConsoleNoAnsi, final Configuration configuration) {
      Builder builder = newBuilder();
      builder.setScript(script);
      builder.setProperties(properties);
      builder.setDefaultPattern(defaultPattern);
      builder.setAlwaysWriteExceptions(alwaysWriteExceptions);
      builder.setNoConsoleNoAnsi(noConsoleNoAnsi);
      builder.setConfiguration(configuration);
      return builder.build();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      boolean first = true;

      for(Map.Entry entry : this.patternMap.entrySet()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("key=\"").append((String)entry.getKey()).append("\", pattern=\"").append((String)entry.getValue()).append("\"");
         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("default=\"").append(this.defaultPattern).append("\"");
      return sb.toString();
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginElement("Script")
      private AbstractScript script;
      @PluginElement("PatternMatch")
      private PatternMatch[] properties;
      @PluginBuilderAttribute("defaultPattern")
      private String defaultPattern;
      @PluginBuilderAttribute("alwaysWriteExceptions")
      private boolean alwaysWriteExceptions;
      @PluginBuilderAttribute("disableAnsi")
      private boolean disableAnsi;
      @PluginBuilderAttribute("noConsoleNoAnsi")
      private boolean noConsoleNoAnsi;
      @PluginConfiguration
      private Configuration configuration;

      private Builder() {
         this.alwaysWriteExceptions = true;
      }

      public ScriptPatternSelector build() {
         if (this.script == null) {
            ScriptPatternSelector.LOGGER.error("A Script, ScriptFile or ScriptRef element must be provided for this ScriptFilter");
            return null;
         } else if (this.configuration.getScriptManager() == null) {
            ScriptPatternSelector.LOGGER.error("Script support is not enabled");
            return null;
         } else {
            if (this.script instanceof ScriptRef) {
               if (this.configuration.getScriptManager().getScript(this.script.getName()) == null) {
                  ScriptPatternSelector.LOGGER.error("No script with name {} has been declared.", this.script.getName());
                  return null;
               }
            } else if (!this.configuration.getScriptManager().addScript(this.script)) {
               return null;
            }

            if (this.defaultPattern == null) {
               this.defaultPattern = "%m%n";
            }

            if (this.properties != null && this.properties.length != 0) {
               return new ScriptPatternSelector(this.configuration, this.script, this.properties, this.defaultPattern, this.alwaysWriteExceptions, this.disableAnsi, this.noConsoleNoAnsi);
            } else {
               ScriptPatternSelector.LOGGER.warn("No marker patterns were provided");
               return null;
            }
         }
      }

      public Builder setScript(final AbstractScript script) {
         this.script = script;
         return this;
      }

      public Builder setProperties(final PatternMatch[] properties) {
         this.properties = properties;
         return this;
      }

      public Builder setDefaultPattern(final String defaultPattern) {
         this.defaultPattern = defaultPattern;
         return this;
      }

      public Builder setAlwaysWriteExceptions(final boolean alwaysWriteExceptions) {
         this.alwaysWriteExceptions = alwaysWriteExceptions;
         return this;
      }

      public Builder setDisableAnsi(final boolean disableAnsi) {
         this.disableAnsi = disableAnsi;
         return this;
      }

      public Builder setNoConsoleNoAnsi(final boolean noConsoleNoAnsi) {
         this.noConsoleNoAnsi = noConsoleNoAnsi;
         return this;
      }

      public Builder setConfiguration(final Configuration config) {
         this.configuration = config;
         return this;
      }
   }
}
