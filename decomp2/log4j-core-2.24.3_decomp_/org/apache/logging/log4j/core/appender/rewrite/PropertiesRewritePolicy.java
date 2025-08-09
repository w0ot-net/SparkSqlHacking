package org.apache.logging.log4j.core.appender.rewrite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringMap;

@Plugin(
   name = "PropertiesRewritePolicy",
   category = "Core",
   elementType = "rewritePolicy",
   printObject = true
)
public final class PropertiesRewritePolicy implements RewritePolicy {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   private final Map properties;
   private final Configuration config;

   private PropertiesRewritePolicy(final Configuration config, final List props) {
      this.config = config;
      this.properties = new HashMap(props.size());

      for(Property property : props) {
         Boolean interpolate = property.getValue().contains("${");
         this.properties.put(property, interpolate);
      }

   }

   public LogEvent rewrite(final LogEvent source) {
      StringMap newContextData = ContextDataFactory.createContextData(source.getContextData());

      for(Map.Entry entry : this.properties.entrySet()) {
         Property prop = (Property)entry.getKey();
         newContextData.putValue(prop.getName(), (Boolean)entry.getValue() ? this.config.getStrSubstitutor().replace(prop.getValue()) : prop.getValue());
      }

      return (new Log4jLogEvent.Builder(source)).setContextData(newContextData).build();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(" {");
      boolean first = true;

      for(Map.Entry entry : this.properties.entrySet()) {
         if (!first) {
            sb.append(", ");
         }

         Property prop = (Property)entry.getKey();
         sb.append(prop.getName()).append('=').append(prop.getValue());
         first = false;
      }

      sb.append('}');
      return sb.toString();
   }

   @PluginFactory
   public static PropertiesRewritePolicy createPolicy(@PluginConfiguration final Configuration config, @PluginElement("Properties") final Property[] props) {
      if (props != null && props.length != 0) {
         List<Property> properties = Arrays.asList(props);
         return new PropertiesRewritePolicy(config, properties);
      } else {
         LOGGER.error("Properties must be specified for the PropertiesRewritePolicy");
         return null;
      }
   }
}
