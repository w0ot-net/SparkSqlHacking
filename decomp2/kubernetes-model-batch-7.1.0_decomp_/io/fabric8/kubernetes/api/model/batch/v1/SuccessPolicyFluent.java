package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class SuccessPolicyFluent extends BaseFluent {
   private ArrayList rules = new ArrayList();
   private Map additionalProperties;

   public SuccessPolicyFluent() {
   }

   public SuccessPolicyFluent(SuccessPolicy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SuccessPolicy instance) {
      instance = instance != null ? instance : new SuccessPolicy();
      if (instance != null) {
         this.withRules(instance.getRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SuccessPolicyFluent addToRules(int index, SuccessPolicyRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public SuccessPolicyFluent setToRules(int index, SuccessPolicyRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public SuccessPolicyFluent addToRules(SuccessPolicyRule... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(SuccessPolicyRule item : items) {
         SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public SuccessPolicyFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(SuccessPolicyRule item : items) {
         SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public SuccessPolicyFluent removeFromRules(SuccessPolicyRule... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(SuccessPolicyRule item : items) {
            SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public SuccessPolicyFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(SuccessPolicyRule item : items) {
            SuccessPolicyRuleBuilder builder = new SuccessPolicyRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public SuccessPolicyFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<SuccessPolicyRuleBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            SuccessPolicyRuleBuilder builder = (SuccessPolicyRuleBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRules() {
      return this.rules != null ? build(this.rules) : null;
   }

   public SuccessPolicyRule buildRule(int index) {
      return ((SuccessPolicyRuleBuilder)this.rules.get(index)).build();
   }

   public SuccessPolicyRule buildFirstRule() {
      return ((SuccessPolicyRuleBuilder)this.rules.get(0)).build();
   }

   public SuccessPolicyRule buildLastRule() {
      return ((SuccessPolicyRuleBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public SuccessPolicyRule buildMatchingRule(Predicate predicate) {
      for(SuccessPolicyRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(SuccessPolicyRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SuccessPolicyFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(SuccessPolicyRule item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public SuccessPolicyFluent withRules(SuccessPolicyRule... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(SuccessPolicyRule item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public SuccessPolicyFluent addNewRule(Integer succeededCount, String succeededIndexes) {
      return this.addToRules(new SuccessPolicyRule(succeededCount, succeededIndexes));
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (SuccessPolicyRule)null);
   }

   public RulesNested addNewRuleLike(SuccessPolicyRule item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, SuccessPolicyRule item) {
      return new RulesNested(index, item);
   }

   public RulesNested editRule(int index) {
      if (this.rules.size() <= index) {
         throw new RuntimeException("Can't edit rules. Index exceeds size.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editFirstRule() {
      if (this.rules.size() == 0) {
         throw new RuntimeException("Can't edit first rules. The list is empty.");
      } else {
         return this.setNewRuleLike(0, this.buildRule(0));
      }
   }

   public RulesNested editLastRule() {
      int index = this.rules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last rules. The list is empty.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editMatchingRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.rules.size(); ++i) {
         if (predicate.test((SuccessPolicyRuleBuilder)this.rules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching rules. No match found.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public SuccessPolicyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SuccessPolicyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SuccessPolicyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SuccessPolicyFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public SuccessPolicyFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            SuccessPolicyFluent that = (SuccessPolicyFluent)o;
            if (!Objects.equals(this.rules, that.rules)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.rules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.rules != null && !this.rules.isEmpty()) {
         sb.append("rules:");
         sb.append(this.rules + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RulesNested extends SuccessPolicyRuleFluent implements Nested {
      SuccessPolicyRuleBuilder builder;
      int index;

      RulesNested(int index, SuccessPolicyRule item) {
         this.index = index;
         this.builder = new SuccessPolicyRuleBuilder(this, item);
      }

      public Object and() {
         return SuccessPolicyFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }
}
