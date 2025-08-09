package org.apache.logging.log4j.core.config.properties;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationException;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.FilterableComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggableComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ScriptComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ScriptFileComponentBuilder;
import org.apache.logging.log4j.core.util.Builder;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;

public class PropertiesConfigurationBuilder extends ConfigurationBuilderFactory implements Builder {
   private static final String ADVERTISER_KEY = "advertiser";
   private static final String STATUS_KEY = "status";
   private static final String SHUTDOWN_HOOK = "shutdownHook";
   private static final String SHUTDOWN_TIMEOUT = "shutdownTimeout";
   private static final String DEST = "dest";
   private static final String PACKAGES = "packages";
   private static final String CONFIG_NAME = "name";
   private static final String MONITOR_INTERVAL = "monitorInterval";
   private static final String CONFIG_TYPE = "type";
   private final ConfigurationBuilder builder = newConfigurationBuilder(PropertiesConfiguration.class);
   private LoggerContext loggerContext;
   private Properties rootProperties;

   public PropertiesConfigurationBuilder setRootProperties(final Properties rootProperties) {
      this.rootProperties = rootProperties;
      return this;
   }

   public PropertiesConfigurationBuilder setConfigurationSource(final ConfigurationSource source) {
      this.builder.setConfigurationSource(source);
      return this;
   }

   public PropertiesConfiguration build() {
      for(String key : this.rootProperties.stringPropertyNames()) {
         if (!key.contains(".")) {
            this.builder.addRootProperty(key, this.rootProperties.getProperty(key));
         }
      }

      this.builder.setStatusLevel(Level.toLevel(this.rootProperties.getProperty("status"), Level.ERROR)).setShutdownHook(this.rootProperties.getProperty("shutdownHook")).setShutdownTimeout(Long.parseLong(this.rootProperties.getProperty("shutdownTimeout", "0")), TimeUnit.MILLISECONDS).setDestination(this.rootProperties.getProperty("dest")).setPackages(this.rootProperties.getProperty("packages")).setConfigurationName(this.rootProperties.getProperty("name")).setMonitorInterval(this.rootProperties.getProperty("monitorInterval", "0")).setAdvertiser(this.rootProperties.getProperty("advertiser"));
      Properties propertyPlaceholders = PropertiesUtil.extractSubset(this.rootProperties, "property");

      for(String key : propertyPlaceholders.stringPropertyNames()) {
         this.builder.addProperty(key, propertyPlaceholders.getProperty(key));
      }

      Map<String, Properties> scripts = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(this.rootProperties, "script"));

      for(Map.Entry entry : scripts.entrySet()) {
         Properties scriptProps = (Properties)entry.getValue();
         String type = (String)scriptProps.remove("type");
         if (type == null) {
            throw new ConfigurationException("No type provided for script - must be Script or ScriptFile");
         }

         if (type.equalsIgnoreCase("script")) {
            this.builder.add(this.createScript(scriptProps));
         } else {
            this.builder.add(this.createScriptFile(scriptProps));
         }
      }

      Properties levelProps = PropertiesUtil.extractSubset(this.rootProperties, "customLevel");
      if (levelProps.size() > 0) {
         for(String key : levelProps.stringPropertyNames()) {
            this.builder.add(this.builder.newCustomLevel(key, Integers.parseInt(levelProps.getProperty(key))));
         }
      }

      String filterProp = this.rootProperties.getProperty("filters");
      if (filterProp != null) {
         String[] filterNames = filterProp.split(",");

         for(String filterName : filterNames) {
            String name = filterName.trim();
            this.builder.add(this.createFilter(name, PropertiesUtil.extractSubset(this.rootProperties, "filter." + name)));
         }
      } else {
         Map<String, Properties> filters = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(this.rootProperties, "filter"));

         for(Map.Entry entry : filters.entrySet()) {
            this.builder.add(this.createFilter(((String)entry.getKey()).trim(), (Properties)entry.getValue()));
         }
      }

      String appenderProp = this.rootProperties.getProperty("appenders");
      if (appenderProp != null) {
         String[] appenderNames = appenderProp.split(",");

         for(String appenderName : appenderNames) {
            String name = appenderName.trim();
            this.builder.add(this.createAppender(appenderName.trim(), PropertiesUtil.extractSubset(this.rootProperties, "appender." + name)));
         }
      } else {
         Map<String, Properties> appenders = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(this.rootProperties, "appender"));

         for(Map.Entry entry : appenders.entrySet()) {
            this.builder.add(this.createAppender(((String)entry.getKey()).trim(), (Properties)entry.getValue()));
         }
      }

      String loggerProp = this.rootProperties.getProperty("loggers");
      if (loggerProp != null) {
         String[] loggerNames = loggerProp.split(",");

         for(String loggerName : loggerNames) {
            String name = loggerName.trim();
            if (!name.equals("root")) {
               this.builder.add(this.createLogger(name, PropertiesUtil.extractSubset(this.rootProperties, "logger." + name)));
            }
         }
      } else {
         Map<String, Properties> loggers = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(this.rootProperties, "logger"), true);

         for(Map.Entry entry : loggers.entrySet()) {
            String name = ((String)entry.getKey()).trim();
            if (!name.equals("root")) {
               this.builder.add(this.createLogger(name, (Properties)entry.getValue()));
            }
         }
      }

      String rootProp = this.rootProperties.getProperty("rootLogger");
      Properties props = PropertiesUtil.extractSubset(this.rootProperties, "rootLogger");
      if (rootProp != null) {
         props.setProperty("", rootProp);
         this.rootProperties.remove("rootLogger");
      }

      if (props.size() > 0) {
         this.builder.add(this.createRootLogger(props));
      }

      this.builder.setLoggerContext(this.loggerContext);
      return (PropertiesConfiguration)this.builder.build(false);
   }

   private ScriptComponentBuilder createScript(final Properties properties) {
      String name = (String)properties.remove("name");
      String language = (String)properties.remove("language");
      String text = (String)properties.remove("text");
      ScriptComponentBuilder scriptBuilder = this.builder.newScript(name, language, text);
      return (ScriptComponentBuilder)processRemainingProperties(scriptBuilder, properties);
   }

   private ScriptFileComponentBuilder createScriptFile(final Properties properties) {
      String name = (String)properties.remove("name");
      String path = (String)properties.remove("path");
      ScriptFileComponentBuilder scriptFileBuilder = this.builder.newScriptFile(name, path);
      return (ScriptFileComponentBuilder)processRemainingProperties(scriptFileBuilder, properties);
   }

   private AppenderComponentBuilder createAppender(final String key, final Properties properties) {
      String name = (String)properties.remove("name");
      if (Strings.isEmpty(name)) {
         throw new ConfigurationException("No name attribute provided for Appender " + key);
      } else {
         String type = (String)properties.remove("type");
         if (Strings.isEmpty(type)) {
            throw new ConfigurationException("No type attribute provided for Appender " + key);
         } else {
            AppenderComponentBuilder appenderBuilder = this.builder.newAppender(name, type);
            this.addFiltersToComponent(appenderBuilder, properties);
            Properties layoutProps = PropertiesUtil.extractSubset(properties, "layout");
            if (layoutProps.size() > 0) {
               appenderBuilder.add(this.createLayout(name, layoutProps));
            }

            return (AppenderComponentBuilder)processRemainingProperties(appenderBuilder, properties);
         }
      }
   }

   private FilterComponentBuilder createFilter(final String key, final Properties properties) {
      String type = (String)properties.remove("type");
      if (Strings.isEmpty(type)) {
         throw new ConfigurationException("No type attribute provided for Filter " + key);
      } else {
         String onMatch = (String)properties.remove("onMatch");
         String onMismatch = (String)properties.remove("onMismatch");
         FilterComponentBuilder filterBuilder = this.builder.newFilter(type, onMatch, onMismatch);
         return (FilterComponentBuilder)processRemainingProperties(filterBuilder, properties);
      }
   }

   private AppenderRefComponentBuilder createAppenderRef(final String key, final Properties properties) {
      String ref = (String)properties.remove("ref");
      if (Strings.isEmpty(ref)) {
         throw new ConfigurationException("No ref attribute provided for AppenderRef " + key);
      } else {
         AppenderRefComponentBuilder appenderRefBuilder = this.builder.newAppenderRef(ref);
         String level = Strings.trimToNull((String)properties.remove("level"));
         if (!Strings.isEmpty(level)) {
            appenderRefBuilder.addAttribute("level", level);
         }

         return (AppenderRefComponentBuilder)this.addFiltersToComponent(appenderRefBuilder, properties);
      }
   }

   private LoggerComponentBuilder createLogger(final String key, final Properties properties) {
      String levelAndRefs = properties.getProperty("");
      String name = (String)properties.remove("name");
      String location = (String)properties.remove("includeLocation");
      if (Strings.isEmpty(name)) {
         throw new ConfigurationException("No name attribute provided for Logger " + key);
      } else {
         String level = Strings.trimToNull((String)properties.remove("level"));
         String type = (String)properties.remove("type");
         LoggerComponentBuilder loggerBuilder;
         if (type != null) {
            if (!type.equalsIgnoreCase("asyncLogger")) {
               throw new ConfigurationException("Unknown Logger type " + type + " for Logger " + name);
            }

            if (location != null) {
               boolean includeLocation = Boolean.parseBoolean(location);
               loggerBuilder = this.builder.newAsyncLogger(name, level, includeLocation);
            } else {
               loggerBuilder = this.builder.newAsyncLogger(name, level);
            }
         } else if (location != null) {
            boolean includeLocation = Boolean.parseBoolean(location);
            loggerBuilder = this.builder.newLogger(name, level, includeLocation);
         } else {
            loggerBuilder = this.builder.newLogger(name, level);
         }

         this.addLoggersToComponent(loggerBuilder, properties);
         this.addFiltersToComponent(loggerBuilder, properties);
         String additivity = (String)properties.remove("additivity");
         if (!Strings.isEmpty(additivity)) {
            loggerBuilder.addAttribute("additivity", additivity);
         }

         if (levelAndRefs != null) {
            loggerBuilder.addAttribute("levelAndRefs", levelAndRefs);
         }

         return loggerBuilder;
      }
   }

   private RootLoggerComponentBuilder createRootLogger(final Properties properties) {
      String levelAndRefs = properties.getProperty("");
      String level = Strings.trimToNull((String)properties.remove("level"));
      String type = (String)properties.remove("type");
      String location = (String)properties.remove("includeLocation");
      RootLoggerComponentBuilder loggerBuilder;
      if (type != null) {
         if (!type.equalsIgnoreCase("asyncRoot")) {
            throw new ConfigurationException("Unknown Logger type for root logger" + type);
         }

         if (location != null) {
            boolean includeLocation = Boolean.parseBoolean(location);
            loggerBuilder = this.builder.newAsyncRootLogger(level, includeLocation);
         } else {
            loggerBuilder = this.builder.newAsyncRootLogger(level);
         }
      } else if (location != null) {
         boolean includeLocation = Boolean.parseBoolean(location);
         loggerBuilder = this.builder.newRootLogger(level, includeLocation);
      } else {
         loggerBuilder = this.builder.newRootLogger(level);
      }

      this.addLoggersToComponent(loggerBuilder, properties);
      if (levelAndRefs != null) {
         loggerBuilder.addAttribute("levelAndRefs", levelAndRefs);
      }

      return (RootLoggerComponentBuilder)this.addFiltersToComponent(loggerBuilder, properties);
   }

   private LayoutComponentBuilder createLayout(final String appenderName, final Properties properties) {
      String type = (String)properties.remove("type");
      if (Strings.isEmpty(type)) {
         throw new ConfigurationException("No type attribute provided for Layout on Appender " + appenderName);
      } else {
         LayoutComponentBuilder layoutBuilder = this.builder.newLayout(type);
         return (LayoutComponentBuilder)processRemainingProperties(layoutBuilder, properties);
      }
   }

   private static ComponentBuilder createComponent(final ComponentBuilder parent, final String key, final Properties properties) {
      String name = (String)properties.remove("name");
      String type = (String)properties.remove("type");
      if (Strings.isEmpty(type)) {
         throw new ConfigurationException("No type attribute provided for component " + key);
      } else {
         ComponentBuilder<B> componentBuilder = parent.getBuilder().newComponent(name, type);
         return processRemainingProperties(componentBuilder, properties);
      }
   }

   private static ComponentBuilder processRemainingProperties(final ComponentBuilder builder, final Properties properties) {
      while(properties.size() > 0) {
         String propertyName = (String)properties.stringPropertyNames().iterator().next();
         int index = propertyName.indexOf(46);
         if (index > 0) {
            String prefix = propertyName.substring(0, index);
            Properties componentProperties = PropertiesUtil.extractSubset(properties, prefix);
            builder.addComponent(createComponent(builder, prefix, componentProperties));
         } else {
            builder.addAttribute(propertyName, properties.getProperty(propertyName));
            properties.remove(propertyName);
         }
      }

      return builder;
   }

   private FilterableComponentBuilder addFiltersToComponent(final FilterableComponentBuilder componentBuilder, final Properties properties) {
      Map<String, Properties> filters = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(properties, "filter"));

      for(Map.Entry entry : filters.entrySet()) {
         componentBuilder.add(this.createFilter(((String)entry.getKey()).trim(), (Properties)entry.getValue()));
      }

      return componentBuilder;
   }

   private LoggableComponentBuilder addLoggersToComponent(final LoggableComponentBuilder loggerBuilder, final Properties properties) {
      Map<String, Properties> appenderRefs = PropertiesUtil.partitionOnCommonPrefixes(PropertiesUtil.extractSubset(properties, "appenderRef"));

      for(Map.Entry entry : appenderRefs.entrySet()) {
         loggerBuilder.add(this.createAppenderRef(((String)entry.getKey()).trim(), (Properties)entry.getValue()));
      }

      return loggerBuilder;
   }

   public PropertiesConfigurationBuilder setLoggerContext(final LoggerContext loggerContext) {
      this.loggerContext = loggerContext;
      return this;
   }

   public LoggerContext getLoggerContext() {
      return this.loggerContext;
   }
}
