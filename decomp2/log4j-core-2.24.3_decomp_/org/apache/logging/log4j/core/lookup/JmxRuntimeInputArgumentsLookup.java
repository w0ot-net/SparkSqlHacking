package org.apache.logging.log4j.core.lookup;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;

@Plugin(
   name = "jvmrunargs",
   category = "Lookup"
)
public class JmxRuntimeInputArgumentsLookup extends MapLookup {
   public static final JmxRuntimeInputArgumentsLookup JMX_SINGLETON;

   public JmxRuntimeInputArgumentsLookup() {
   }

   public JmxRuntimeInputArgumentsLookup(final Map map) {
      super(map);
   }

   public String lookup(final LogEvent ignored, final String key) {
      if (key == null) {
         return null;
      } else {
         Map<String, String> map = this.getMap();
         return map == null ? null : (String)map.get(key);
      }
   }

   static {
      List<String> argsList = ManagementFactory.getRuntimeMXBean().getInputArguments();
      JMX_SINGLETON = new JmxRuntimeInputArgumentsLookup(MapLookup.toMap(argsList));
   }
}
