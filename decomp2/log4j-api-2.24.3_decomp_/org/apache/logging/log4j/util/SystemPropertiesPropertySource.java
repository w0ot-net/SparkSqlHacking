package org.apache.logging.log4j.util;

import aQute.bnd.annotation.spi.ServiceProvider;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

@ServiceProvider(
   value = PropertySource.class,
   resolution = "optional"
)
public class SystemPropertiesPropertySource implements PropertySource {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final int DEFAULT_PRIORITY = 0;
   private static final String PREFIX = "log4j2.";
   private static final PropertySource INSTANCE = new SystemPropertiesPropertySource();

   public static PropertySource provider() {
      return INSTANCE;
   }

   public static String getSystemProperty(final String key, final String defaultValue) {
      String value = INSTANCE.getProperty(key);
      return value != null ? value : defaultValue;
   }

   private static void logException(final SecurityException error) {
      LOGGER.error((String)"The Java system properties are not available to Log4j due to security restrictions.", (Throwable)error);
   }

   private static void logException(final SecurityException error, final String key) {
      LOGGER.error((String)"The Java system property {} is not available to Log4j due to security restrictions.", (Object)key, (Object)error);
   }

   public int getPriority() {
      return 0;
   }

   public void forEach(final BiConsumer action) {
      Properties properties;
      try {
         properties = System.getProperties();
      } catch (SecurityException e) {
         logException(e);
         return;
      }

      Object[] keySet;
      synchronized(properties) {
         keySet = properties.keySet().toArray();
      }

      for(Object key : keySet) {
         String keyStr = Objects.toString(key, (String)null);
         action.accept(keyStr, properties.getProperty(keyStr));
      }

   }

   public CharSequence getNormalForm(final Iterable tokens) {
      return "log4j2." + PropertySource.Util.joinAsCamelCase(tokens);
   }

   public Collection getPropertyNames() {
      try {
         return System.getProperties().stringPropertyNames();
      } catch (SecurityException e) {
         logException(e);
         return PropertySource.super.getPropertyNames();
      }
   }

   public String getProperty(final String key) {
      try {
         return System.getProperty(key);
      } catch (SecurityException e) {
         logException(e, key);
         return PropertySource.super.getProperty(key);
      }
   }

   public boolean containsProperty(final String key) {
      return this.getProperty(key) != null;
   }
}
