package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReference;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReferenceBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class UDPRouteSpecFluent extends BaseFluent {
   private ArrayList parentRefs = new ArrayList();
   private ArrayList rules = new ArrayList();
   private Map additionalProperties;

   public UDPRouteSpecFluent() {
   }

   public UDPRouteSpecFluent(UDPRouteSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(UDPRouteSpec instance) {
      instance = instance != null ? instance : new UDPRouteSpec();
      if (instance != null) {
         this.withParentRefs(instance.getParentRefs());
         this.withRules(instance.getRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public UDPRouteSpecFluent addToParentRefs(int index, ParentReference item) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
      if (index >= 0 && index < this.parentRefs.size()) {
         this._visitables.get("parentRefs").add(index, builder);
         this.parentRefs.add(index, builder);
      } else {
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent setToParentRefs(int index, ParentReference item) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
      if (index >= 0 && index < this.parentRefs.size()) {
         this._visitables.get("parentRefs").set(index, builder);
         this.parentRefs.set(index, builder);
      } else {
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent addToParentRefs(ParentReference... items) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      for(ParentReference item : items) {
         ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent addAllToParentRefs(Collection items) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      for(ParentReference item : items) {
         ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent removeFromParentRefs(ParentReference... items) {
      if (this.parentRefs == null) {
         return this;
      } else {
         for(ParentReference item : items) {
            ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
            this._visitables.get("parentRefs").remove(builder);
            this.parentRefs.remove(builder);
         }

         return this;
      }
   }

   public UDPRouteSpecFluent removeAllFromParentRefs(Collection items) {
      if (this.parentRefs == null) {
         return this;
      } else {
         for(ParentReference item : items) {
            ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
            this._visitables.get("parentRefs").remove(builder);
            this.parentRefs.remove(builder);
         }

         return this;
      }
   }

   public UDPRouteSpecFluent removeMatchingFromParentRefs(Predicate predicate) {
      if (this.parentRefs == null) {
         return this;
      } else {
         Iterator<ParentReferenceBuilder> each = this.parentRefs.iterator();
         List visitables = this._visitables.get("parentRefs");

         while(each.hasNext()) {
            ParentReferenceBuilder builder = (ParentReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildParentRefs() {
      return this.parentRefs != null ? build(this.parentRefs) : null;
   }

   public ParentReference buildParentRef(int index) {
      return ((ParentReferenceBuilder)this.parentRefs.get(index)).build();
   }

   public ParentReference buildFirstParentRef() {
      return ((ParentReferenceBuilder)this.parentRefs.get(0)).build();
   }

   public ParentReference buildLastParentRef() {
      return ((ParentReferenceBuilder)this.parentRefs.get(this.parentRefs.size() - 1)).build();
   }

   public ParentReference buildMatchingParentRef(Predicate predicate) {
      for(ParentReferenceBuilder item : this.parentRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingParentRef(Predicate predicate) {
      for(ParentReferenceBuilder item : this.parentRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public UDPRouteSpecFluent withParentRefs(List parentRefs) {
      if (this.parentRefs != null) {
         this._visitables.get("parentRefs").clear();
      }

      if (parentRefs != null) {
         this.parentRefs = new ArrayList();

         for(ParentReference item : parentRefs) {
            this.addToParentRefs(item);
         }
      } else {
         this.parentRefs = null;
      }

      return this;
   }

   public UDPRouteSpecFluent withParentRefs(ParentReference... parentRefs) {
      if (this.parentRefs != null) {
         this.parentRefs.clear();
         this._visitables.remove("parentRefs");
      }

      if (parentRefs != null) {
         for(ParentReference item : parentRefs) {
            this.addToParentRefs(item);
         }
      }

      return this;
   }

   public boolean hasParentRefs() {
      return this.parentRefs != null && !this.parentRefs.isEmpty();
   }

   public ParentRefsNested addNewParentRef() {
      return new ParentRefsNested(-1, (ParentReference)null);
   }

   public ParentRefsNested addNewParentRefLike(ParentReference item) {
      return new ParentRefsNested(-1, item);
   }

   public ParentRefsNested setNewParentRefLike(int index, ParentReference item) {
      return new ParentRefsNested(index, item);
   }

   public ParentRefsNested editParentRef(int index) {
      if (this.parentRefs.size() <= index) {
         throw new RuntimeException("Can't edit parentRefs. Index exceeds size.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public ParentRefsNested editFirstParentRef() {
      if (this.parentRefs.size() == 0) {
         throw new RuntimeException("Can't edit first parentRefs. The list is empty.");
      } else {
         return this.setNewParentRefLike(0, this.buildParentRef(0));
      }
   }

   public ParentRefsNested editLastParentRef() {
      int index = this.parentRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last parentRefs. The list is empty.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public ParentRefsNested editMatchingParentRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.parentRefs.size(); ++i) {
         if (predicate.test((ParentReferenceBuilder)this.parentRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching parentRefs. No match found.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public UDPRouteSpecFluent addToRules(int index, UDPRouteRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent setToRules(int index, UDPRouteRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent addToRules(UDPRouteRule... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(UDPRouteRule item : items) {
         UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(UDPRouteRule item : items) {
         UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public UDPRouteSpecFluent removeFromRules(UDPRouteRule... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(UDPRouteRule item : items) {
            UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public UDPRouteSpecFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(UDPRouteRule item : items) {
            UDPRouteRuleBuilder builder = new UDPRouteRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public UDPRouteSpecFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<UDPRouteRuleBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            UDPRouteRuleBuilder builder = (UDPRouteRuleBuilder)each.next();
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

   public UDPRouteRule buildRule(int index) {
      return ((UDPRouteRuleBuilder)this.rules.get(index)).build();
   }

   public UDPRouteRule buildFirstRule() {
      return ((UDPRouteRuleBuilder)this.rules.get(0)).build();
   }

   public UDPRouteRule buildLastRule() {
      return ((UDPRouteRuleBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public UDPRouteRule buildMatchingRule(Predicate predicate) {
      for(UDPRouteRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(UDPRouteRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public UDPRouteSpecFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(UDPRouteRule item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public UDPRouteSpecFluent withRules(UDPRouteRule... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(UDPRouteRule item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (UDPRouteRule)null);
   }

   public RulesNested addNewRuleLike(UDPRouteRule item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, UDPRouteRule item) {
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
         if (predicate.test((UDPRouteRuleBuilder)this.rules.get(i))) {
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

   public UDPRouteSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public UDPRouteSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public UDPRouteSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public UDPRouteSpecFluent removeFromAdditionalProperties(Map map) {
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

   public UDPRouteSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            UDPRouteSpecFluent that = (UDPRouteSpecFluent)o;
            if (!Objects.equals(this.parentRefs, that.parentRefs)) {
               return false;
            } else if (!Objects.equals(this.rules, that.rules)) {
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
      return Objects.hash(new Object[]{this.parentRefs, this.rules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.parentRefs != null && !this.parentRefs.isEmpty()) {
         sb.append("parentRefs:");
         sb.append(this.parentRefs + ",");
      }

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

   public class ParentRefsNested extends ParentReferenceFluent implements Nested {
      ParentReferenceBuilder builder;
      int index;

      ParentRefsNested(int index, ParentReference item) {
         this.index = index;
         this.builder = new ParentReferenceBuilder(this, item);
      }

      public Object and() {
         return UDPRouteSpecFluent.this.setToParentRefs(this.index, this.builder.build());
      }

      public Object endParentRef() {
         return this.and();
      }
   }

   public class RulesNested extends UDPRouteRuleFluent implements Nested {
      UDPRouteRuleBuilder builder;
      int index;

      RulesNested(int index, UDPRouteRule item) {
         this.index = index;
         this.builder = new UDPRouteRuleBuilder(this, item);
      }

      public Object and() {
         return UDPRouteSpecFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }
}
