package com.codahale.metrics.jmx;

import java.util.Hashtable;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultObjectNameFactory implements ObjectNameFactory {
   private static final char[] QUOTABLE_CHARS = new char[]{',', '=', ':', '"'};
   private static final Logger LOGGER = LoggerFactory.getLogger(JmxReporter.class);

   public ObjectName createName(String type, String domain, String name) {
      try {
         Hashtable<String, String> properties = new Hashtable();
         properties.put("name", name);
         properties.put("type", type);
         ObjectName objectName = new ObjectName(domain, properties);
         if (objectName.isDomainPattern()) {
            domain = ObjectName.quote(domain);
         }

         if (objectName.isPropertyValuePattern("name") || this.shouldQuote(objectName.getKeyProperty("name"))) {
            properties.put("name", ObjectName.quote(name));
         }

         if (objectName.isPropertyValuePattern("type") || this.shouldQuote(objectName.getKeyProperty("type"))) {
            properties.put("type", ObjectName.quote(type));
         }

         objectName = new ObjectName(domain, properties);
         return objectName;
      } catch (MalformedObjectNameException var7) {
         try {
            return new ObjectName(domain, "name", ObjectName.quote(name));
         } catch (MalformedObjectNameException e1) {
            LOGGER.warn("Unable to register {} {}", new Object[]{type, name, e1});
            throw new RuntimeException(e1);
         }
      }
   }

   private boolean shouldQuote(final String value) {
      for(char quotableChar : QUOTABLE_CHARS) {
         if (value.indexOf(quotableChar) != -1) {
            return true;
         }
      }

      return false;
   }
}
