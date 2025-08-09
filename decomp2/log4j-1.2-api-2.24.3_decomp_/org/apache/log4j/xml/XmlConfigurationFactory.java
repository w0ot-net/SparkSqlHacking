package org.apache.log4j.xml;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.PropertiesUtil;

@Plugin(
   name = "Log4j1XmlConfigurationFactory",
   category = "ConfigurationFactory"
)
@Order(2)
public class XmlConfigurationFactory extends ConfigurationFactory {
   public static final String FILE_EXTENSION = ".xml";
   private static final Logger LOGGER = StatusLogger.getLogger();
   protected static final String TEST_PREFIX = "log4j-test";
   protected static final String DEFAULT_PREFIX = "log4j";

   protected String[] getSupportedTypes() {
      return !PropertiesUtil.getProperties().getBooleanProperty("log4j1.compatibility", Boolean.FALSE) ? null : new String[]{".xml"};
   }

   public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
      int interval = PropertiesUtil.getProperties().getIntegerProperty("log4j1.monitorInterval", 0);
      return new XmlConfiguration(loggerContext, source, interval);
   }

   protected String getTestPrefix() {
      return "log4j-test";
   }

   protected String getDefaultPrefix() {
      return "log4j";
   }

   protected String getVersion() {
      return "1";
   }
}
