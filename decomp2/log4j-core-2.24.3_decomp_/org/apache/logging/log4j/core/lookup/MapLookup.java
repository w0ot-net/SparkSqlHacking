package org.apache.logging.log4j.core.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "map",
   category = "Lookup"
)
public class MapLookup implements StrLookup {
   private final Map map;

   public MapLookup() {
      this.map = null;
   }

   public MapLookup(final Map map) {
      this.map = map;
   }

   static Map initMap(final String[] srcArgs, final Map destMap) {
      for(int i = 0; i < srcArgs.length; ++i) {
         int next = i + 1;
         String value = srcArgs[i];
         destMap.put(Integer.toString(i), value);
         destMap.put(value, next < srcArgs.length ? srcArgs[next] : null);
      }

      return destMap;
   }

   static HashMap newMap(final int initialCapacity) {
      return new HashMap(initialCapacity);
   }

   /** @deprecated */
   @Deprecated
   public static void setMainArguments(final String... args) {
      MainMapLookup.setMainArguments(args);
   }

   static Map toMap(final List args) {
      if (args == null) {
         return null;
      } else {
         int size = args.size();
         return initMap((String[])args.toArray(Strings.EMPTY_ARRAY), newMap(size));
      }
   }

   static Map toMap(final String[] args) {
      return args == null ? null : initMap(args, newMap(args.length));
   }

   protected Map getMap() {
      return this.map;
   }

   public String lookup(final LogEvent event, final String key) {
      boolean isMapMessage = event != null && event.getMessage() instanceof MapMessage;
      if (isMapMessage) {
         String obj = ((MapMessage)event.getMessage()).get(key);
         if (obj != null) {
            return obj;
         }
      }

      return this.map != null ? (String)this.map.get(key) : null;
   }

   public String lookup(final String key) {
      return key != null && this.map != null ? (String)this.map.get(key) : null;
   }
}
