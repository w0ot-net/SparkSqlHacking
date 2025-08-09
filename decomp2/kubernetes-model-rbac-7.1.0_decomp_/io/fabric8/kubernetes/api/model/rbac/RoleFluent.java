package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class RoleFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList rules = new ArrayList();
   private Map additionalProperties;

   public RoleFluent() {
   }

   public RoleFluent(Role instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Role instance) {
      instance = instance != null ? instance : new Role();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withRules(instance.getRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public RoleFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public RoleFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public RoleFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public RoleFluent addToRules(int index, PolicyRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public RoleFluent setToRules(int index, PolicyRule item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public RoleFluent addToRules(PolicyRule... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(PolicyRule item : items) {
         PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public RoleFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(PolicyRule item : items) {
         PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public RoleFluent removeFromRules(PolicyRule... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(PolicyRule item : items) {
            PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public RoleFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(PolicyRule item : items) {
            PolicyRuleBuilder builder = new PolicyRuleBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public RoleFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<PolicyRuleBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            PolicyRuleBuilder builder = (PolicyRuleBuilder)each.next();
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

   public PolicyRule buildRule(int index) {
      return ((PolicyRuleBuilder)this.rules.get(index)).build();
   }

   public PolicyRule buildFirstRule() {
      return ((PolicyRuleBuilder)this.rules.get(0)).build();
   }

   public PolicyRule buildLastRule() {
      return ((PolicyRuleBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public PolicyRule buildMatchingRule(Predicate predicate) {
      for(PolicyRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(PolicyRuleBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RoleFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(PolicyRule item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public RoleFluent withRules(PolicyRule... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(PolicyRule item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (PolicyRule)null);
   }

   public RulesNested addNewRuleLike(PolicyRule item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, PolicyRule item) {
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
         if (predicate.test((PolicyRuleBuilder)this.rules.get(i))) {
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

   public RoleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RoleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RoleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RoleFluent removeFromAdditionalProperties(Map map) {
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

   public RoleFluent withAdditionalProperties(Map additionalProperties) {
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
            RoleFluent that = (RoleFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.rules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
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

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return RoleFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class RulesNested extends PolicyRuleFluent implements Nested {
      PolicyRuleBuilder builder;
      int index;

      RulesNested(int index, PolicyRule item) {
         this.index = index;
         this.builder = new PolicyRuleBuilder(this, item);
      }

      public Object and() {
         return RoleFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }
}
