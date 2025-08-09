package org.apache.commons.lang.text;

import java.util.Map;

public abstract class StrLookup {
   private static final StrLookup NONE_LOOKUP = new MapStrLookup((Map)null);
   private static final StrLookup SYSTEM_PROPERTIES_LOOKUP;

   public static StrLookup noneLookup() {
      return NONE_LOOKUP;
   }

   public static StrLookup systemPropertiesLookup() {
      return SYSTEM_PROPERTIES_LOOKUP;
   }

   public static StrLookup mapLookup(Map map) {
      return new MapStrLookup(map);
   }

   protected StrLookup() {
   }

   public abstract String lookup(String var1);

   static {
      StrLookup lookup = null;

      try {
         lookup = new MapStrLookup(System.getProperties());
      } catch (SecurityException var2) {
         lookup = NONE_LOOKUP;
      }

      SYSTEM_PROPERTIES_LOOKUP = lookup;
   }

   static class MapStrLookup extends StrLookup {
      private final Map map;

      MapStrLookup(Map map) {
         this.map = map;
      }

      public String lookup(String key) {
         if (this.map == null) {
            return null;
         } else {
            Object obj = this.map.get(key);
            return obj == null ? null : obj.toString();
         }
      }
   }
}
