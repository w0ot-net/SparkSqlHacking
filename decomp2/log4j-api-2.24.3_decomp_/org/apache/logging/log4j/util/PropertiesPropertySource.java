package org.apache.logging.log4j.util;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

public class PropertiesPropertySource implements PropertySource {
   private static final int DEFAULT_PRIORITY = 200;
   private static final String PREFIX = "log4j2.";
   private final Properties properties;
   private final int priority;

   public PropertiesPropertySource(final Properties properties) {
      this(properties, 200);
   }

   public PropertiesPropertySource(final Properties properties, final int priority) {
      this.properties = properties;
      this.priority = priority;
   }

   public int getPriority() {
      return this.priority;
   }

   public void forEach(final BiConsumer action) {
      for(Map.Entry entry : this.properties.entrySet()) {
         action.accept((String)entry.getKey(), (String)entry.getValue());
      }

   }

   public CharSequence getNormalForm(final Iterable tokens) {
      CharSequence camelCase = PropertySource.Util.joinAsCamelCase(tokens);
      return camelCase.length() > 0 ? "log4j2." + camelCase : null;
   }

   public Collection getPropertyNames() {
      return this.properties.stringPropertyNames();
   }

   public String getProperty(final String key) {
      return this.properties.getProperty(key);
   }

   public boolean containsProperty(final String key) {
      return this.getProperty(key) != null;
   }
}
