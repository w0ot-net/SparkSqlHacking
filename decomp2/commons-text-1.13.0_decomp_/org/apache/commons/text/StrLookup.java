package org.apache.commons.text;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import org.apache.commons.text.lookup.StringLookup;

/** @deprecated */
@Deprecated
public abstract class StrLookup implements StringLookup {
   private static final StrLookup NONE_LOOKUP = new MapStrLookup((Map)null);
   private static final StrLookup SYSTEM_PROPERTIES_LOOKUP = new SystemPropertiesStrLookup();

   public static StrLookup mapLookup(Map map) {
      return new MapStrLookup(map);
   }

   public static StrLookup noneLookup() {
      return NONE_LOOKUP;
   }

   public static StrLookup resourceBundleLookup(ResourceBundle resourceBundle) {
      return new ResourceBundleLookup(resourceBundle);
   }

   public static StrLookup systemPropertiesLookup() {
      return SYSTEM_PROPERTIES_LOOKUP;
   }

   protected StrLookup() {
   }

   private static final class MapStrLookup extends StrLookup {
      private final Map map;

      private MapStrLookup(Map map) {
         this.map = map != null ? map : Collections.emptyMap();
      }

      public String lookup(String key) {
         return Objects.toString(this.map.get(key), (String)null);
      }

      public String toString() {
         return super.toString() + " [map=" + this.map + "]";
      }
   }

   private static final class ResourceBundleLookup extends StrLookup {
      private final ResourceBundle resourceBundle;

      private ResourceBundleLookup(ResourceBundle resourceBundle) {
         this.resourceBundle = resourceBundle;
      }

      public String lookup(String key) {
         return this.resourceBundle != null && key != null && this.resourceBundle.containsKey(key) ? this.resourceBundle.getString(key) : null;
      }

      public String toString() {
         return super.toString() + " [resourceBundle=" + this.resourceBundle + "]";
      }
   }

   private static final class SystemPropertiesStrLookup extends StrLookup {
      private SystemPropertiesStrLookup() {
      }

      public String lookup(String key) {
         if (!key.isEmpty()) {
            try {
               return System.getProperty(key);
            } catch (SecurityException var3) {
            }
         }

         return null;
      }
   }
}
