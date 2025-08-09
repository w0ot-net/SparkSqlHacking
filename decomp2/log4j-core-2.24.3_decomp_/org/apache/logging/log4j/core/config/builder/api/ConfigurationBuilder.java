package org.apache.logging.log4j.core.config.builder.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.util.Builder;

public interface ConfigurationBuilder extends Builder {
   ConfigurationBuilder add(ScriptComponentBuilder builder);

   ConfigurationBuilder add(ScriptFileComponentBuilder builder);

   ConfigurationBuilder add(AppenderComponentBuilder builder);

   ConfigurationBuilder add(CustomLevelComponentBuilder builder);

   ConfigurationBuilder add(FilterComponentBuilder builder);

   ConfigurationBuilder add(LoggerComponentBuilder builder);

   ConfigurationBuilder add(RootLoggerComponentBuilder builder);

   ConfigurationBuilder addProperty(String key, String value);

   ScriptComponentBuilder newScript(String name, String language, String text);

   ScriptFileComponentBuilder newScriptFile(String path);

   ScriptFileComponentBuilder newScriptFile(String name, String path);

   AppenderComponentBuilder newAppender(String name, String pluginName);

   AppenderRefComponentBuilder newAppenderRef(String ref);

   LoggerComponentBuilder newAsyncLogger(String name);

   LoggerComponentBuilder newAsyncLogger(String name, boolean includeLocation);

   LoggerComponentBuilder newAsyncLogger(String name, Level level);

   LoggerComponentBuilder newAsyncLogger(String name, Level level, boolean includeLocation);

   LoggerComponentBuilder newAsyncLogger(String name, String level);

   LoggerComponentBuilder newAsyncLogger(String name, String level, boolean includeLocation);

   RootLoggerComponentBuilder newAsyncRootLogger();

   RootLoggerComponentBuilder newAsyncRootLogger(boolean includeLocation);

   RootLoggerComponentBuilder newAsyncRootLogger(Level level);

   RootLoggerComponentBuilder newAsyncRootLogger(Level level, boolean includeLocation);

   RootLoggerComponentBuilder newAsyncRootLogger(String level);

   RootLoggerComponentBuilder newAsyncRootLogger(String level, boolean includeLocation);

   ComponentBuilder newComponent(String pluginName);

   ComponentBuilder newComponent(String name, String pluginName);

   ComponentBuilder newComponent(String name, String pluginName, String value);

   PropertyComponentBuilder newProperty(String name, String value);

   KeyValuePairComponentBuilder newKeyValuePair(String key, String value);

   CustomLevelComponentBuilder newCustomLevel(String name, int level);

   FilterComponentBuilder newFilter(String pluginName, Filter.Result onMatch, Filter.Result onMismatch);

   FilterComponentBuilder newFilter(String pluginName, String onMatch, String onMismatch);

   LayoutComponentBuilder newLayout(String pluginName);

   LoggerComponentBuilder newLogger(String name);

   LoggerComponentBuilder newLogger(String name, boolean includeLocation);

   LoggerComponentBuilder newLogger(String name, Level level);

   LoggerComponentBuilder newLogger(String name, Level level, boolean includeLocation);

   LoggerComponentBuilder newLogger(String name, String level);

   LoggerComponentBuilder newLogger(String name, String level, boolean includeLocation);

   RootLoggerComponentBuilder newRootLogger();

   RootLoggerComponentBuilder newRootLogger(boolean includeLocation);

   RootLoggerComponentBuilder newRootLogger(Level level);

   RootLoggerComponentBuilder newRootLogger(Level level, boolean includeLocation);

   RootLoggerComponentBuilder newRootLogger(String level);

   RootLoggerComponentBuilder newRootLogger(String level, boolean includeLocation);

   ConfigurationBuilder setAdvertiser(String advertiser);

   ConfigurationBuilder setConfigurationName(String name);

   ConfigurationBuilder setConfigurationSource(ConfigurationSource configurationSource);

   ConfigurationBuilder setMonitorInterval(String intervalSeconds);

   ConfigurationBuilder setPackages(String packages);

   ConfigurationBuilder setShutdownHook(String flag);

   ConfigurationBuilder setShutdownTimeout(long timeout, TimeUnit timeUnit);

   ConfigurationBuilder setStatusLevel(Level level);

   ConfigurationBuilder setVerbosity(String verbosity);

   ConfigurationBuilder setDestination(String destination);

   void setLoggerContext(LoggerContext loggerContext);

   ConfigurationBuilder addRootProperty(String key, String value);

   Configuration build(boolean initialize);

   void writeXmlConfiguration(OutputStream output) throws IOException;

   String toXmlConfiguration();
}
