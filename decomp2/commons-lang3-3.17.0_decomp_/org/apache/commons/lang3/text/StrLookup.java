package org.apache.commons.lang3.text;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.SystemProperties;

/** @deprecated */
@Deprecated
public abstract class StrLookup {
   private static final StrLookup NONE_LOOKUP = new MapStrLookup((Map)null);
   private static final StrLookup SYSTEM_PROPERTIES_LOOKUP = new SystemPropertiesStrLookup();

   public static StrLookup mapLookup(Map map) {
      return new MapStrLookup(map);
   }

   public static StrLookup noneLookup() {
      return NONE_LOOKUP;
   }

   public static StrLookup systemPropertiesLookup() {
      return SYSTEM_PROPERTIES_LOOKUP;
   }

   protected StrLookup() {
   }

   public abstract String lookup(String var1);

   static class MapStrLookup extends StrLookup {
      private final Map map;

      MapStrLookup(Map map) {
         this.map = map;
      }

      public String lookup(String key) {
         return this.map == null ? null : Objects.toString(this.map.get(key), (String)null);
      }
   }

   private static final class SystemPropertiesStrLookup extends StrLookup {
      private SystemPropertiesStrLookup() {
      }

      public String lookup(String key) {
         return SystemProperties.getProperty(key);
      }
   }
}
