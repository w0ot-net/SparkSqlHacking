package org.apache.logging.log4j.core.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.async.AsyncLoggerConfig;
import org.apache.logging.log4j.core.async.AsyncLoggerContext;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;
import org.apache.logging.log4j.core.filter.AbstractFilterable;
import org.apache.logging.log4j.core.impl.DefaultLogEventFactory;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.apache.logging.log4j.core.impl.ReusableLogEventFactory;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "Logger",
   category = "Core",
   printObject = true
)
public class LoggerConfig extends AbstractFilterable implements LocationAware {
   public static final String ROOT = "root";
   private static LogEventFactory LOG_EVENT_FACTORY = null;
   private List appenderRefs = new ArrayList();
   private final AppenderControlArraySet appenders = new AppenderControlArraySet();
   private final String name;
   private LogEventFactory logEventFactory;
   private Level level;
   private boolean additive = true;
   private boolean includeLocation = true;
   private LoggerConfig parent;
   private Map propertiesMap;
   private final List properties;
   private final boolean propertiesRequireLookup;
   private final Configuration config;
   private final ReliabilityStrategy reliabilityStrategy;

   private static LogEventFactory createDefaultLogEventFactory() {
      return (LogEventFactory)(Constants.ENABLE_THREADLOCALS ? new ReusableLogEventFactory() : new DefaultLogEventFactory());
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (new Builder()).asBuilder();
   }

   public LoggerConfig() {
      this.logEventFactory = LOG_EVENT_FACTORY;
      this.level = Level.ERROR;
      this.name = "";
      this.properties = null;
      this.propertiesRequireLookup = false;
      this.config = null;
      this.reliabilityStrategy = new DefaultReliabilityStrategy(this);
   }

   public LoggerConfig(final String name, final Level level, final boolean additive) {
      this.logEventFactory = LOG_EVENT_FACTORY;
      this.name = name;
      this.level = level;
      this.additive = additive;
      this.properties = null;
      this.propertiesRequireLookup = false;
      this.config = null;
      this.reliabilityStrategy = new DefaultReliabilityStrategy(this);
   }

   protected LoggerConfig(final String name, final List appenders, final Filter filter, final Level level, final boolean additive, final Property[] properties, final Configuration config, final boolean includeLocation) {
      super(filter);
      this.logEventFactory = LOG_EVENT_FACTORY;
      this.name = name;
      this.appenderRefs = appenders;
      this.level = level;
      this.additive = additive;
      this.includeLocation = includeLocation;
      this.config = config;
      if (properties != null && properties.length > 0) {
         this.properties = Collections.unmodifiableList(Arrays.asList((Property[])Arrays.copyOf(properties, properties.length)));
      } else {
         this.properties = null;
      }

      this.propertiesRequireLookup = containsPropertyRequiringLookup(properties);
      this.reliabilityStrategy = config.getReliabilityStrategy(this);
   }

   private static boolean containsPropertyRequiringLookup(final Property[] properties) {
      if (properties == null) {
         return false;
      } else {
         for(int i = 0; i < properties.length; ++i) {
            if (properties[i].isValueNeedsLookup()) {
               return true;
            }
         }

         return false;
      }
   }

   public Filter getFilter() {
      return super.getFilter();
   }

   public String getName() {
      return this.name;
   }

   public void setParent(final LoggerConfig parent) {
      this.parent = parent;
   }

   public LoggerConfig getParent() {
      return this.parent;
   }

   public void addAppender(final Appender appender, final Level level, final Filter filter) {
      this.appenders.add(new AppenderControl(appender, level, filter));
   }

   public void removeAppender(final String name) {
      AppenderControl removed = null;

      while((removed = this.appenders.remove(name)) != null) {
         this.cleanupFilter(removed);
      }

   }

   public Map getAppenders() {
      return this.appenders.asMap();
   }

   protected void clearAppenders() {
      do {
         AppenderControl[] original = this.appenders.clear();

         for(AppenderControl ctl : original) {
            this.cleanupFilter(ctl);
         }
      } while(!this.appenders.isEmpty());

   }

   private void cleanupFilter(final AppenderControl ctl) {
      Filter filter = ctl.getFilter();
      if (filter != null) {
         ctl.removeFilter(filter);
         filter.stop();
      }

   }

   public List getAppenderRefs() {
      return this.appenderRefs;
   }

   public void setLevel(final Level level) {
      this.level = level;
   }

   public Level getLevel() {
      return this.level == null ? (this.parent == null ? Level.ERROR : this.parent.getLevel()) : this.level;
   }

   public Level getExplicitLevel() {
      return this.level;
   }

   public LogEventFactory getLogEventFactory() {
      return this.logEventFactory;
   }

   public void setLogEventFactory(final LogEventFactory logEventFactory) {
      this.logEventFactory = logEventFactory;
   }

   public boolean isAdditive() {
      return this.additive;
   }

   public void setAdditive(final boolean additive) {
      this.additive = additive;
   }

   public boolean isIncludeLocation() {
      return this.includeLocation;
   }

   /** @deprecated */
   @Deprecated
   public Map getProperties() {
      if (this.properties == null) {
         return null;
      } else {
         if (this.propertiesMap == null) {
            Map<Property, Boolean> result = new HashMap(this.properties.size() * 2);

            for(int i = 0; i < this.properties.size(); ++i) {
               result.put((Property)this.properties.get(i), ((Property)this.properties.get(i)).isValueNeedsLookup());
            }

            this.propertiesMap = Collections.unmodifiableMap(result);
         }

         return this.propertiesMap;
      }
   }

   public List getPropertyList() {
      return this.properties;
   }

   public boolean isPropertiesRequireLookup() {
      return this.propertiesRequireLookup;
   }

   @PerformanceSensitive({"allocation"})
   public void log(final String loggerName, final String fqcn, final Marker marker, final Level level, final Message data, final Throwable t) {
      List<Property> props = this.getProperties(loggerName, fqcn, marker, level, data, t);
      LogEvent logEvent = this.logEventFactory.createEvent(loggerName, marker, fqcn, this.location(fqcn), level, data, props, t);

      try {
         this.log(logEvent, LoggerConfig.LoggerConfigPredicate.ALL);
      } finally {
         ReusableLogEventFactory.release(logEvent);
      }

   }

   private StackTraceElement location(final String fqcn) {
      return this.requiresLocation() ? StackLocatorUtil.calcLocation(fqcn) : null;
   }

   @PerformanceSensitive({"allocation"})
   public void log(final String loggerName, final String fqcn, final StackTraceElement location, final Marker marker, final Level level, final Message data, final Throwable t) {
      List<Property> props = this.getProperties(loggerName, fqcn, marker, level, data, t);
      LogEvent logEvent = this.logEventFactory.createEvent(loggerName, marker, fqcn, location, level, data, props, t);

      try {
         this.log(logEvent, LoggerConfig.LoggerConfigPredicate.ALL);
      } finally {
         ReusableLogEventFactory.release(logEvent);
      }

   }

   private List getProperties(final String loggerName, final String fqcn, final Marker marker, final Level level, final Message data, final Throwable t) {
      List<Property> snapshot = this.properties;
      return snapshot != null && this.propertiesRequireLookup ? this.getPropertiesWithLookups(loggerName, fqcn, marker, level, data, t, snapshot) : snapshot;
   }

   private List getPropertiesWithLookups(final String loggerName, final String fqcn, final Marker marker, final Level level, final Message data, final Throwable t, final List props) {
      List<Property> results = new ArrayList(props.size());
      Log4jLogEvent.newBuilder().setMessage(data).setMarker(marker).setLevel(level).setLoggerName(loggerName).setLoggerFqcn(fqcn).setThrown(t).build();

      for(int i = 0; i < props.size(); ++i) {
         Property prop = (Property)props.get(i);
         String value = prop.evaluate(this.config.getStrSubstitutor());
         results.add(Property.createProperty(prop.getName(), prop.getRawValue(), value));
      }

      return results;
   }

   public void log(final LogEvent event) {
      this.log(event, LoggerConfig.LoggerConfigPredicate.ALL);
   }

   protected void log(final LogEvent event, final LoggerConfigPredicate predicate) {
      if (!this.isFiltered(event)) {
         this.processLogEvent(event, predicate);
      }

   }

   public ReliabilityStrategy getReliabilityStrategy() {
      return this.reliabilityStrategy;
   }

   protected void processLogEvent(final LogEvent event, final LoggerConfigPredicate predicate) {
      event.setIncludeLocation(this.isIncludeLocation());
      if (predicate == null || predicate.allow(this)) {
         this.callAppenders(event);
      }

      this.logParent(event, predicate);
   }

   public boolean requiresLocation() {
      if (!this.includeLocation) {
         return false;
      } else {
         AppenderControl[] controls = this.appenders.get();
         LoggerConfig loggerConfig = this;

         while(loggerConfig != null) {
            for(AppenderControl control : controls) {
               Appender appender = control.getAppender();
               if (appender instanceof LocationAware && ((LocationAware)appender).requiresLocation()) {
                  return true;
               }
            }

            if (!loggerConfig.additive) {
               break;
            }

            loggerConfig = loggerConfig.parent;
            if (loggerConfig != null) {
               controls = loggerConfig.appenders.get();
            }
         }

         return false;
      }
   }

   private void logParent(final LogEvent event, final LoggerConfigPredicate predicate) {
      if (this.additive && this.parent != null) {
         this.parent.log(event, predicate);
      }

   }

   @PerformanceSensitive({"allocation"})
   protected void callAppenders(final LogEvent event) {
      AppenderControl[] controls = this.appenders.get();

      for(int i = 0; i < controls.length; ++i) {
         controls[i].callAppender(event);
      }

   }

   public String toString() {
      return Strings.isEmpty(this.name) ? "root" : this.name;
   }

   /** @deprecated */
   @Deprecated
   public static LoggerConfig createLogger(final String additivity, final Level level, @PluginAttribute("name") final String loggerName, final String includeLocation, final AppenderRef[] refs, final Property[] properties, @PluginConfiguration final Configuration config, final Filter filter) {
      if (loggerName == null) {
         LOGGER.error("Loggers cannot be configured without a name");
         return null;
      } else {
         List<AppenderRef> appenderRefs = Arrays.asList(refs);
         String name = loggerName.equals("root") ? "" : loggerName;
         boolean additive = Booleans.parseBoolean(additivity, true);
         return new LoggerConfig(name, appenderRefs, filter, level, additive, properties, config, includeLocation(includeLocation, config));
      }
   }

   /** @deprecated */
   @Deprecated
   public static LoggerConfig createLogger(@PluginAttribute(value = "additivity",defaultBoolean = true) final boolean additivity, @PluginAttribute("level") final Level level, @Required(message = "Loggers cannot be configured without a name") @PluginAttribute("name") final String loggerName, @PluginAttribute("includeLocation") final String includeLocation, @PluginElement("AppenderRef") final AppenderRef[] refs, @PluginElement("Properties") final Property[] properties, @PluginConfiguration final Configuration config, @PluginElement("Filter") final Filter filter) {
      String name = loggerName.equals("root") ? "" : loggerName;
      return new LoggerConfig(name, Arrays.asList(refs), filter, level, additivity, properties, config, includeLocation(includeLocation, config));
   }

   protected static boolean includeLocation(final String includeLocationConfigValue) {
      return includeLocation(includeLocationConfigValue, (Configuration)null);
   }

   protected static boolean includeLocation(final String includeLocationConfigValue, final Configuration configuration) {
      if (includeLocationConfigValue == null) {
         LoggerContext context = null;
         if (configuration != null) {
            context = configuration.getLoggerContext();
         }

         if (context != null) {
            return !(context instanceof AsyncLoggerContext);
         } else {
            return !AsyncLoggerContextSelector.isSelected();
         }
      } else {
         return Boolean.parseBoolean(includeLocationConfigValue);
      }
   }

   protected final boolean hasAppenders() {
      return !this.appenders.isEmpty();
   }

   protected static LevelAndRefs getLevelAndRefs(final Level level, final AppenderRef[] refs, final String levelAndRefs, final Configuration config) {
      LevelAndRefs result = new LevelAndRefs();
      if (levelAndRefs != null) {
         if (config instanceof PropertiesConfiguration) {
            if (level != null) {
               LOGGER.warn("Level is ignored when levelAndRefs syntax is used.");
            }

            if (refs != null && refs.length > 0) {
               LOGGER.warn("Appender references are ignored when levelAndRefs syntax is used");
            }

            String[] parts = Strings.splitList(levelAndRefs);
            result.level = Level.getLevel(parts[0]);
            if (parts.length > 1) {
               List<AppenderRef> refList = new ArrayList();
               Arrays.stream(parts).skip(1L).forEach((ref) -> refList.add(AppenderRef.createAppenderRef(ref, (Level)null, (Filter)null)));
               result.refs = refList;
            }
         } else {
            LOGGER.warn("levelAndRefs are only allowed in a properties configuration. The value is ignored.");
            result.level = level;
            result.refs = (List)(refs != null ? Arrays.asList(refs) : new ArrayList());
         }
      } else {
         result.level = level;
         result.refs = (List)(refs != null ? Arrays.asList(refs) : new ArrayList());
      }

      return result;
   }

   static {
      try {
         LOG_EVENT_FACTORY = (LogEventFactory)LoaderUtil.newCheckedInstanceOfProperty("Log4jLogEventFactory", LogEventFactory.class, LoggerConfig::createDefaultLogEventFactory);
      } catch (Exception ex) {
         LOGGER.error("Unable to create LogEventFactory: {}", ex.getMessage(), ex);
         LOG_EVENT_FACTORY = createDefaultLogEventFactory();
      }

   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private Boolean additivity;
      @PluginBuilderAttribute
      private Level level;
      @PluginBuilderAttribute
      private String levelAndRefs;
      @PluginBuilderAttribute("name")
      @Required(
         message = "Loggers cannot be configured without a name"
      )
      private String loggerName;
      @PluginBuilderAttribute
      private String includeLocation;
      @PluginElement("AppenderRef")
      private AppenderRef[] refs;
      @PluginElement("Properties")
      private Property[] properties;
      @PluginConfiguration
      private Configuration config;
      @PluginElement("Filter")
      private Filter filter;

      public boolean isAdditivity() {
         return this.additivity == null || this.additivity;
      }

      public Builder withAdditivity(final boolean additivity) {
         this.additivity = additivity;
         return this.asBuilder();
      }

      public Level getLevel() {
         return this.level;
      }

      public Builder withLevel(final Level level) {
         this.level = level;
         return this.asBuilder();
      }

      public String getLevelAndRefs() {
         return this.levelAndRefs;
      }

      public Builder withLevelAndRefs(final String levelAndRefs) {
         this.levelAndRefs = levelAndRefs;
         return this.asBuilder();
      }

      public String getLoggerName() {
         return this.loggerName;
      }

      public Builder withLoggerName(final String loggerName) {
         this.loggerName = loggerName;
         return this.asBuilder();
      }

      public String getIncludeLocation() {
         return this.includeLocation;
      }

      public Builder withIncludeLocation(final String includeLocation) {
         this.includeLocation = includeLocation;
         return this.asBuilder();
      }

      public AppenderRef[] getRefs() {
         return this.refs;
      }

      public Builder withRefs(final AppenderRef[] refs) {
         this.refs = refs;
         return this.asBuilder();
      }

      public Property[] getProperties() {
         return this.properties;
      }

      public Builder withProperties(final Property[] properties) {
         this.properties = properties;
         return this.asBuilder();
      }

      public Configuration getConfig() {
         return this.config;
      }

      public Builder withConfig(final Configuration config) {
         this.config = config;
         return this.asBuilder();
      }

      public Filter getFilter() {
         return this.filter;
      }

      /** @deprecated */
      @Deprecated
      public Builder withtFilter(final Filter filter) {
         this.filter = filter;
         return this.asBuilder();
      }

      public Builder withFilter(final Filter filter) {
         this.filter = filter;
         return this.asBuilder();
      }

      public LoggerConfig build() {
         String name = this.loggerName.equals("root") ? "" : this.loggerName;
         LevelAndRefs container = LoggerConfig.getLevelAndRefs(this.level, this.refs, this.levelAndRefs, this.config);
         boolean useLocation = LoggerConfig.includeLocation(this.includeLocation, this.config);
         return new LoggerConfig(name, container.refs, this.filter, container.level, this.isAdditivity(), this.properties, this.config, useLocation);
      }

      public Builder asBuilder() {
         return this;
      }
   }

   @Plugin(
      name = "Root",
      category = "Core",
      printObject = true
   )
   public static class RootLogger extends LoggerConfig {
      @PluginBuilderFactory
      public static Builder newRootBuilder() {
         return (new Builder()).asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public static LoggerConfig createLogger(@PluginAttribute("additivity") final String additivity, @PluginAttribute("level") final Level level, @PluginAttribute("includeLocation") final String includeLocation, @PluginElement("AppenderRef") final AppenderRef[] refs, @PluginElement("Properties") final Property[] properties, @PluginConfiguration final Configuration config, @PluginElement("Filter") final Filter filter) {
         List<AppenderRef> appenderRefs = Arrays.asList(refs);
         Level actualLevel = level == null ? Level.ERROR : level;
         boolean additive = Booleans.parseBoolean(additivity, true);
         return new LoggerConfig("", appenderRefs, filter, actualLevel, additive, properties, config, includeLocation(includeLocation, config));
      }

      public static class Builder implements org.apache.logging.log4j.core.util.Builder {
         @PluginBuilderAttribute
         private boolean additivity;
         @PluginBuilderAttribute
         private Level level;
         @PluginBuilderAttribute
         private String levelAndRefs;
         @PluginBuilderAttribute
         private String includeLocation;
         @PluginElement("AppenderRef")
         private AppenderRef[] refs;
         @PluginElement("Properties")
         private Property[] properties;
         @PluginConfiguration
         private Configuration config;
         @PluginElement("Filter")
         private Filter filter;

         public boolean isAdditivity() {
            return this.additivity;
         }

         public Builder withAdditivity(final boolean additivity) {
            this.additivity = additivity;
            return this.asBuilder();
         }

         public Level getLevel() {
            return this.level;
         }

         public Builder withLevel(final Level level) {
            this.level = level;
            return this.asBuilder();
         }

         public String getLevelAndRefs() {
            return this.levelAndRefs;
         }

         public Builder withLevelAndRefs(final String levelAndRefs) {
            this.levelAndRefs = levelAndRefs;
            return this.asBuilder();
         }

         public String getIncludeLocation() {
            return this.includeLocation;
         }

         public Builder withIncludeLocation(final String includeLocation) {
            this.includeLocation = includeLocation;
            return this.asBuilder();
         }

         public AppenderRef[] getRefs() {
            return this.refs;
         }

         public Builder withRefs(final AppenderRef[] refs) {
            this.refs = refs;
            return this.asBuilder();
         }

         public Property[] getProperties() {
            return this.properties;
         }

         public Builder withProperties(final Property[] properties) {
            this.properties = properties;
            return this.asBuilder();
         }

         public Configuration getConfig() {
            return this.config;
         }

         public Builder withConfig(final Configuration config) {
            this.config = config;
            return this.asBuilder();
         }

         public Filter getFilter() {
            return this.filter;
         }

         public Builder withtFilter(final Filter filter) {
            this.filter = filter;
            return this.asBuilder();
         }

         public LoggerConfig build() {
            LevelAndRefs container = LoggerConfig.getLevelAndRefs(this.level, this.refs, this.levelAndRefs, this.config);
            return new LoggerConfig("", container.refs, this.filter, container.level, this.additivity, this.properties, this.config, LoggerConfig.includeLocation(this.includeLocation, this.config));
         }

         public Builder asBuilder() {
            return this;
         }
      }
   }

   protected static class LevelAndRefs {
      public Level level;
      public List refs;
   }

   protected static enum LoggerConfigPredicate {
      ALL {
         boolean allow(final LoggerConfig config) {
            return true;
         }
      },
      ASYNCHRONOUS_ONLY {
         boolean allow(final LoggerConfig config) {
            return config instanceof AsyncLoggerConfig;
         }
      },
      SYNCHRONOUS_ONLY {
         boolean allow(final LoggerConfig config) {
            return !ASYNCHRONOUS_ONLY.allow(config);
         }
      };

      private LoggerConfigPredicate() {
      }

      abstract boolean allow(LoggerConfig config);

      // $FF: synthetic method
      private static LoggerConfigPredicate[] $values() {
         return new LoggerConfigPredicate[]{ALL, ASYNCHRONOUS_ONLY, SYNCHRONOUS_ONLY};
      }
   }
}
