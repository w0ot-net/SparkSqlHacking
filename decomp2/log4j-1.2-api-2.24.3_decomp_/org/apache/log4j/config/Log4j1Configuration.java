package org.apache.log4j.config;

import org.apache.log4j.Level;
import org.apache.log4j.builders.BuilderManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.LifeCycle.State;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Reconfigurable;

public class Log4j1Configuration extends AbstractConfiguration implements Reconfigurable {
   public static final String MONITOR_INTERVAL = "log4j1.monitorInterval";
   public static final String APPENDER_REF_TAG = "appender-ref";
   public static final String THRESHOLD_PARAM = "Threshold";
   public static final String INHERITED = "inherited";
   public static final String NULL = "null";
   public static final Level DEFAULT_LEVEL;
   protected final BuilderManager manager = new BuilderManager();

   public Log4j1Configuration(final LoggerContext loggerContext, final ConfigurationSource configurationSource, final int monitorIntervalSeconds) {
      super(loggerContext, configurationSource);
      this.initializeWatchers(this, configurationSource, monitorIntervalSeconds);
   }

   public BuilderManager getBuilderManager() {
      return this.manager;
   }

   public void initialize() {
      this.getStrSubstitutor().setConfiguration(this);
      this.getConfigurationStrSubstitutor().setConfiguration(this);
      super.getScheduler().start();
      this.doConfigure();
      this.setState(State.INITIALIZED);
      LOGGER.debug("Configuration {} initialized", this);
   }

   public Configuration reconfigure() {
      return null;
   }

   static {
      DEFAULT_LEVEL = Level.DEBUG;
   }
}
