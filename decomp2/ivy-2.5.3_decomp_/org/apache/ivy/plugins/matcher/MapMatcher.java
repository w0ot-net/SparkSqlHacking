package org.apache.ivy.plugins.matcher;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapMatcher {
   private Map matchers = new HashMap();
   private PatternMatcher pm;
   private Map attributes;

   public MapMatcher(Map attributes, PatternMatcher pm) {
      this.attributes = attributes;
      this.pm = pm;

      for(Map.Entry entry : attributes.entrySet()) {
         String value = (String)entry.getValue();
         if (value != null) {
            this.matchers.put(entry.getKey(), pm.getMatcher(value));
         }
      }

   }

   public boolean matches(Map m) {
      for(Map.Entry entry : this.matchers.entrySet()) {
         Matcher matcher = (Matcher)entry.getValue();
         String value = (String)m.get(entry.getKey());
         if (value == null || !matcher.matches(value)) {
            return false;
         }
      }

      return true;
   }

   public String toString() {
      return this.attributes + " (" + this.pm.getName() + ")";
   }

   public Map getAttributes() {
      return Collections.unmodifiableMap(this.attributes);
   }

   public PatternMatcher getPatternMatcher() {
      return this.pm;
   }
}
