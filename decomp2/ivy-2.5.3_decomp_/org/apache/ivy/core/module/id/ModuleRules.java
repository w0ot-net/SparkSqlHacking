package org.apache.ivy.core.module.id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.plugins.matcher.MapMatcher;
import org.apache.ivy.util.Checks;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.filter.Filter;
import org.apache.ivy.util.filter.NoFilter;

public class ModuleRules {
   private Map rules = new LinkedHashMap();
   private MatcherLookup matcherLookup = new MatcherLookup();

   public ModuleRules() {
   }

   private ModuleRules(Map rules) {
      this.rules = new LinkedHashMap(rules);

      for(MapMatcher matcher : rules.keySet()) {
         this.matcherLookup.add(matcher);
      }

   }

   public void defineRule(MapMatcher condition, Object rule) {
      Checks.checkNotNull(condition, "condition");
      Checks.checkNotNull(rule, "rule");
      this.rules.put(condition, rule);
      this.matcherLookup.add(condition);
   }

   public Object getRule(ModuleId mid) {
      return this.getRule(mid, NoFilter.instance());
   }

   public List getRules(ModuleId mid) {
      return this.getRules(mid.getAttributes(), NoFilter.instance());
   }

   public Object getRule(ModuleRevisionId mrid) {
      return this.getRule(mrid, NoFilter.instance());
   }

   public Object getRule(ModuleId mid, Filter filter) {
      Checks.checkNotNull(mid, "mid");
      return this.getRule(mid.getAttributes(), filter);
   }

   public Object getRule(ModuleRevisionId mrid, Filter filter) {
      Checks.checkNotNull(mrid, "mrid");
      Checks.checkNotNull(filter, "filter");
      Map<String, String> moduleAttributes = mrid.getAttributes();
      return this.getRule(moduleAttributes, filter);
   }

   private Object getRule(Map moduleAttributes, Filter filter) {
      for(MapMatcher midm : this.matcherLookup.get(moduleAttributes)) {
         T rule = (T)this.rules.get(midm);
         if (filter.accept(rule)) {
            return rule;
         }
      }

      return null;
   }

   public List getRules(ModuleRevisionId mrid, Filter filter) {
      Checks.checkNotNull(mrid, "mrid");
      Checks.checkNotNull(filter, "filter");
      Map<String, String> moduleAttributes = mrid.getAttributes();
      return this.getRules(moduleAttributes, filter);
   }

   private List getRules(Map moduleAttributes, Filter filter) {
      List<T> matchingRules = new ArrayList();

      for(MapMatcher midm : this.matcherLookup.get(moduleAttributes)) {
         T rule = (T)this.rules.get(midm);
         if (filter.accept(rule)) {
            matchingRules.add(rule);
         }
      }

      return matchingRules;
   }

   public void dump(String prefix) {
      if (this.rules.isEmpty()) {
         Message.debug(prefix + "NONE");
      } else {
         for(Map.Entry entry : this.rules.entrySet()) {
            MapMatcher midm = (MapMatcher)entry.getKey();
            T rule = (T)entry.getValue();
            Message.debug(prefix + midm + " -> " + rule);
         }

      }
   }

   public Map getAllRules() {
      return Collections.unmodifiableMap(this.rules);
   }

   public ModuleRules clone() {
      return new ModuleRules(this.rules);
   }
}
