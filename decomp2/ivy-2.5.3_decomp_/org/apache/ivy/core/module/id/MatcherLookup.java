package org.apache.ivy.core.module.id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.plugins.matcher.ExactPatternMatcher;
import org.apache.ivy.plugins.matcher.MapMatcher;

public class MatcherLookup {
   private static final String DEFAULT = "{org:default, module:default}";
   private Map lookup = new HashMap();
   private List nonExactMatchers = new ArrayList();

   public void add(MapMatcher matcher) {
      if (!(matcher.getPatternMatcher() instanceof ExactPatternMatcher)) {
         this.nonExactMatchers.add(matcher);
      } else {
         String key = this.key(matcher.getAttributes());
         List<MapMatcher> exactMatchers = (List)this.lookup.get(key);
         if (exactMatchers == null) {
            exactMatchers = new ArrayList();
            this.lookup.put(key, exactMatchers);
         }

         exactMatchers.add(matcher);
      }
   }

   public List get(Map attrs) {
      List<MapMatcher> matchers = new ArrayList();
      if (!this.nonExactMatchers.isEmpty()) {
         for(MapMatcher matcher : this.nonExactMatchers) {
            if (matcher.matches(attrs)) {
               matchers.add(matcher);
            }
         }
      }

      String key = this.key(attrs);
      List<MapMatcher> exactMatchers = (List)this.lookup.get(key);
      if (exactMatchers != null) {
         for(MapMatcher matcher : exactMatchers) {
            if (matcher.matches(attrs)) {
               matchers.add(matcher);
            }
         }
      }

      if (!"{org:default, module:default}".equals(key)) {
         List<MapMatcher> defaultExactMatchers = (List)this.lookup.get("{org:default, module:default}");
         if (defaultExactMatchers != null) {
            for(MapMatcher matcher : defaultExactMatchers) {
               if (matcher.matches(attrs)) {
                  matchers.add(matcher);
               }
            }
         }
      }

      return matchers;
   }

   private String key(Map attrs) {
      String org = (String)attrs.get("organisation");
      String module = (String)attrs.get("module");
      return org != null && !"*".equals(org) && module != null && !"*".equals(module) ? "{org:" + org + ", module:" + module + "}" : "{org:default, module:default}";
   }
}
