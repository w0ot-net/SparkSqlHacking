package org.apache.logging.log4j.core.lookup;

import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;

@Plugin(
   name = "main",
   category = "Lookup"
)
public class MainMapLookup extends MapLookup {
   static final MapLookup MAIN_SINGLETON = new MapLookup(MapLookup.newMap(0));

   public MainMapLookup() {
   }

   public MainMapLookup(final Map map) {
      super(map);
   }

   public static void setMainArguments(final String... args) {
      if (args != null) {
         initMap(args, MAIN_SINGLETON.getMap());
      }
   }

   public String lookup(final LogEvent ignored, final String key) {
      return (String)MAIN_SINGLETON.getMap().get(key);
   }

   public String lookup(final String key) {
      return (String)MAIN_SINGLETON.getMap().get(key);
   }
}
