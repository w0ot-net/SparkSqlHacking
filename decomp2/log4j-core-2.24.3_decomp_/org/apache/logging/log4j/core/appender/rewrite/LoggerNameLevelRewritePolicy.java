package org.apache.logging.log4j.core.appender.rewrite;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "LoggerNameLevelRewritePolicy",
   category = "Core",
   elementType = "rewritePolicy",
   printObject = true
)
public class LoggerNameLevelRewritePolicy implements RewritePolicy {
   private final String loggerName;
   private final Map map;

   @PluginFactory
   public static LoggerNameLevelRewritePolicy createPolicy(@PluginAttribute("logger") final String loggerNamePrefix, @PluginElement("KeyValuePair") final KeyValuePair[] levelPairs) {
      Map<Level, Level> newMap = new HashMap(levelPairs.length);

      for(KeyValuePair keyValuePair : levelPairs) {
         newMap.put(getLevel(keyValuePair.getKey()), getLevel(keyValuePair.getValue()));
      }

      return new LoggerNameLevelRewritePolicy(loggerNamePrefix, newMap);
   }

   private static Level getLevel(final String name) {
      return Level.getLevel(Strings.toRootUpperCase(name));
   }

   private LoggerNameLevelRewritePolicy(final String loggerName, final Map map) {
      this.loggerName = loggerName;
      this.map = map;
   }

   public LogEvent rewrite(final LogEvent event) {
      if (event.getLoggerName() != null && event.getLoggerName().startsWith(this.loggerName)) {
         Level sourceLevel = event.getLevel();
         Level newLevel = (Level)this.map.get(sourceLevel);
         if (newLevel != null && newLevel != sourceLevel) {
            LogEvent result = (new Log4jLogEvent.Builder(event)).setLevel(newLevel).build();
            return result;
         } else {
            return event;
         }
      } else {
         return event;
      }
   }
}
