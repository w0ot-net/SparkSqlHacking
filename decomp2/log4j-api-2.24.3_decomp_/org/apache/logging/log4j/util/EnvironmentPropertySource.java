package org.apache.logging.log4j.util;

import aQute.bnd.annotation.spi.ServiceProvider;
import java.util.Collection;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

@ServiceProvider(
   value = PropertySource.class,
   resolution = "optional"
)
public class EnvironmentPropertySource implements PropertySource {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String PREFIX = "LOG4J_";
   private static final int DEFAULT_PRIORITY = 100;
   private static final PropertySource INSTANCE = new EnvironmentPropertySource();

   public static PropertySource provider() {
      return INSTANCE;
   }

   public int getPriority() {
      return 100;
   }

   private static void logException(final SecurityException error) {
      LOGGER.error((String)"The environment variables are not available to Log4j due to security restrictions.", (Throwable)error);
   }

   private static void logException(final SecurityException error, final String key) {
      LOGGER.error((String)"The environment variable {} is not available to Log4j due to security restrictions.", (Object)key, (Object)error);
   }

   public void forEach(final BiConsumer action) {
      Map<String, String> getenv;
      try {
         getenv = System.getenv();
      } catch (SecurityException e) {
         logException(e);
         return;
      }

      for(Map.Entry entry : getenv.entrySet()) {
         String key = (String)entry.getKey();
         if (key.startsWith("LOG4J_")) {
            action.accept(key.substring("LOG4J_".length()), (String)entry.getValue());
         }
      }

   }

   public CharSequence getNormalForm(final Iterable tokens) {
      StringBuilder sb = new StringBuilder("LOG4J");
      boolean empty = true;

      for(CharSequence token : tokens) {
         empty = false;
         sb.append('_');

         for(int i = 0; i < token.length(); ++i) {
            sb.append(Character.toUpperCase(token.charAt(i)));
         }
      }

      return empty ? null : sb.toString();
   }

   public Collection getPropertyNames() {
      try {
         return System.getenv().keySet();
      } catch (SecurityException e) {
         logException(e);
         return PropertySource.super.getPropertyNames();
      }
   }

   public String getProperty(final String key) {
      try {
         return System.getenv(key);
      } catch (SecurityException e) {
         logException(e, key);
         return PropertySource.super.getProperty(key);
      }
   }

   public boolean containsProperty(final String key) {
      try {
         return System.getenv().containsKey(key);
      } catch (SecurityException e) {
         logException(e, key);
         return PropertySource.super.containsProperty(key);
      }
   }
}
