package org.apache.logging.log4j.core.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "Appenders",
   category = "Core"
)
public final class AppendersPlugin {
   private AppendersPlugin() {
   }

   @PluginFactory
   public static ConcurrentMap createAppenders(@PluginElement("Appenders") final Appender[] appenders) {
      ConcurrentMap<String, Appender> map = new ConcurrentHashMap(appenders.length);

      for(Appender appender : appenders) {
         map.put(appender.getName(), appender);
      }

      return map;
   }
}
