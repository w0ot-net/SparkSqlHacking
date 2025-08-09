package org.apache.commons.text.lookup;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

final class InterpolatorStringLookup extends AbstractStringLookup {
   static final AbstractStringLookup INSTANCE = new InterpolatorStringLookup();
   private static final char PREFIX_SEPARATOR = ':';
   private final StringLookup defaultStringLookup;
   private final Map stringLookupMap;

   InterpolatorStringLookup() {
      this((Map)null);
   }

   InterpolatorStringLookup(Map stringLookupMap, StringLookup defaultStringLookup, boolean addDefaultLookups) {
      this.defaultStringLookup = defaultStringLookup;
      this.stringLookupMap = (Map)stringLookupMap.entrySet().stream().collect(Collectors.toMap((e) -> StringLookupFactory.toKey((String)e.getKey()), Map.Entry::getValue));
      if (addDefaultLookups) {
         StringLookupFactory.INSTANCE.addDefaultStringLookups(this.stringLookupMap);
      }

   }

   InterpolatorStringLookup(Map defaultMap) {
      this(StringLookupFactory.INSTANCE.mapStringLookup(defaultMap));
   }

   InterpolatorStringLookup(StringLookup defaultStringLookup) {
      this(Collections.emptyMap(), defaultStringLookup, true);
   }

   public Map getStringLookupMap() {
      return this.stringLookupMap;
   }

   public String lookup(String key) {
      if (key == null) {
         return null;
      } else {
         int prefixPos = key.indexOf(58);
         if (prefixPos >= 0) {
            String prefix = StringLookupFactory.toKey(key.substring(0, prefixPos));
            String name = key.substring(prefixPos + 1);
            StringLookup lookup = (StringLookup)this.stringLookupMap.get(prefix);
            String value = null;
            if (lookup != null) {
               value = lookup.lookup(name);
            }

            if (value != null) {
               return value;
            }

            key = key.substring(prefixPos + 1);
         }

         return this.defaultStringLookup != null ? this.defaultStringLookup.lookup(key) : null;
      }
   }

   public String toString() {
      return super.toString() + " [stringLookupMap=" + this.stringLookupMap + ", defaultStringLookup=" + this.defaultStringLookup + "]";
   }
}
