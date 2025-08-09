package io.fabric8.kubernetes.api.model.autoscaling.v2;

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

public class HPAScalingRulesFluent extends BaseFluent {
   private ArrayList policies = new ArrayList();
   private String selectPolicy;
   private Integer stabilizationWindowSeconds;
   private Map additionalProperties;

   public HPAScalingRulesFluent() {
   }

   public HPAScalingRulesFluent(HPAScalingRules instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HPAScalingRules instance) {
      instance = instance != null ? instance : new HPAScalingRules();
      if (instance != null) {
         this.withPolicies(instance.getPolicies());
         this.withSelectPolicy(instance.getSelectPolicy());
         this.withStabilizationWindowSeconds(instance.getStabilizationWindowSeconds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HPAScalingRulesFluent addToPolicies(int index, HPAScalingPolicy item) {
      if (this.policies == null) {
         this.policies = new ArrayList();
      }

      HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
      if (index >= 0 && index < this.policies.size()) {
         this._visitables.get("policies").add(index, builder);
         this.policies.add(index, builder);
      } else {
         this._visitables.get("policies").add(builder);
         this.policies.add(builder);
      }

      return this;
   }

   public HPAScalingRulesFluent setToPolicies(int index, HPAScalingPolicy item) {
      if (this.policies == null) {
         this.policies = new ArrayList();
      }

      HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
      if (index >= 0 && index < this.policies.size()) {
         this._visitables.get("policies").set(index, builder);
         this.policies.set(index, builder);
      } else {
         this._visitables.get("policies").add(builder);
         this.policies.add(builder);
      }

      return this;
   }

   public HPAScalingRulesFluent addToPolicies(HPAScalingPolicy... items) {
      if (this.policies == null) {
         this.policies = new ArrayList();
      }

      for(HPAScalingPolicy item : items) {
         HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
         this._visitables.get("policies").add(builder);
         this.policies.add(builder);
      }

      return this;
   }

   public HPAScalingRulesFluent addAllToPolicies(Collection items) {
      if (this.policies == null) {
         this.policies = new ArrayList();
      }

      for(HPAScalingPolicy item : items) {
         HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
         this._visitables.get("policies").add(builder);
         this.policies.add(builder);
      }

      return this;
   }

   public HPAScalingRulesFluent removeFromPolicies(HPAScalingPolicy... items) {
      if (this.policies == null) {
         return this;
      } else {
         for(HPAScalingPolicy item : items) {
            HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
            this._visitables.get("policies").remove(builder);
            this.policies.remove(builder);
         }

         return this;
      }
   }

   public HPAScalingRulesFluent removeAllFromPolicies(Collection items) {
      if (this.policies == null) {
         return this;
      } else {
         for(HPAScalingPolicy item : items) {
            HPAScalingPolicyBuilder builder = new HPAScalingPolicyBuilder(item);
            this._visitables.get("policies").remove(builder);
            this.policies.remove(builder);
         }

         return this;
      }
   }

   public HPAScalingRulesFluent removeMatchingFromPolicies(Predicate predicate) {
      if (this.policies == null) {
         return this;
      } else {
         Iterator<HPAScalingPolicyBuilder> each = this.policies.iterator();
         List visitables = this._visitables.get("policies");

         while(each.hasNext()) {
            HPAScalingPolicyBuilder builder = (HPAScalingPolicyBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPolicies() {
      return this.policies != null ? build(this.policies) : null;
   }

   public HPAScalingPolicy buildPolicy(int index) {
      return ((HPAScalingPolicyBuilder)this.policies.get(index)).build();
   }

   public HPAScalingPolicy buildFirstPolicy() {
      return ((HPAScalingPolicyBuilder)this.policies.get(0)).build();
   }

   public HPAScalingPolicy buildLastPolicy() {
      return ((HPAScalingPolicyBuilder)this.policies.get(this.policies.size() - 1)).build();
   }

   public HPAScalingPolicy buildMatchingPolicy(Predicate predicate) {
      for(HPAScalingPolicyBuilder item : this.policies) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPolicy(Predicate predicate) {
      for(HPAScalingPolicyBuilder item : this.policies) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HPAScalingRulesFluent withPolicies(List policies) {
      if (this.policies != null) {
         this._visitables.get("policies").clear();
      }

      if (policies != null) {
         this.policies = new ArrayList();

         for(HPAScalingPolicy item : policies) {
            this.addToPolicies(item);
         }
      } else {
         this.policies = null;
      }

      return this;
   }

   public HPAScalingRulesFluent withPolicies(HPAScalingPolicy... policies) {
      if (this.policies != null) {
         this.policies.clear();
         this._visitables.remove("policies");
      }

      if (policies != null) {
         for(HPAScalingPolicy item : policies) {
            this.addToPolicies(item);
         }
      }

      return this;
   }

   public boolean hasPolicies() {
      return this.policies != null && !this.policies.isEmpty();
   }

   public HPAScalingRulesFluent addNewPolicy(Integer periodSeconds, String type, Integer value) {
      return this.addToPolicies(new HPAScalingPolicy(periodSeconds, type, value));
   }

   public PoliciesNested addNewPolicy() {
      return new PoliciesNested(-1, (HPAScalingPolicy)null);
   }

   public PoliciesNested addNewPolicyLike(HPAScalingPolicy item) {
      return new PoliciesNested(-1, item);
   }

   public PoliciesNested setNewPolicyLike(int index, HPAScalingPolicy item) {
      return new PoliciesNested(index, item);
   }

   public PoliciesNested editPolicy(int index) {
      if (this.policies.size() <= index) {
         throw new RuntimeException("Can't edit policies. Index exceeds size.");
      } else {
         return this.setNewPolicyLike(index, this.buildPolicy(index));
      }
   }

   public PoliciesNested editFirstPolicy() {
      if (this.policies.size() == 0) {
         throw new RuntimeException("Can't edit first policies. The list is empty.");
      } else {
         return this.setNewPolicyLike(0, this.buildPolicy(0));
      }
   }

   public PoliciesNested editLastPolicy() {
      int index = this.policies.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last policies. The list is empty.");
      } else {
         return this.setNewPolicyLike(index, this.buildPolicy(index));
      }
   }

   public PoliciesNested editMatchingPolicy(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.policies.size(); ++i) {
         if (predicate.test((HPAScalingPolicyBuilder)this.policies.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching policies. No match found.");
      } else {
         return this.setNewPolicyLike(index, this.buildPolicy(index));
      }
   }

   public String getSelectPolicy() {
      return this.selectPolicy;
   }

   public HPAScalingRulesFluent withSelectPolicy(String selectPolicy) {
      this.selectPolicy = selectPolicy;
      return this;
   }

   public boolean hasSelectPolicy() {
      return this.selectPolicy != null;
   }

   public Integer getStabilizationWindowSeconds() {
      return this.stabilizationWindowSeconds;
   }

   public HPAScalingRulesFluent withStabilizationWindowSeconds(Integer stabilizationWindowSeconds) {
      this.stabilizationWindowSeconds = stabilizationWindowSeconds;
      return this;
   }

   public boolean hasStabilizationWindowSeconds() {
      return this.stabilizationWindowSeconds != null;
   }

   public HPAScalingRulesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HPAScalingRulesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HPAScalingRulesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HPAScalingRulesFluent removeFromAdditionalProperties(Map map) {
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

   public HPAScalingRulesFluent withAdditionalProperties(Map additionalProperties) {
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
            HPAScalingRulesFluent that = (HPAScalingRulesFluent)o;
            if (!Objects.equals(this.policies, that.policies)) {
               return false;
            } else if (!Objects.equals(this.selectPolicy, that.selectPolicy)) {
               return false;
            } else if (!Objects.equals(this.stabilizationWindowSeconds, that.stabilizationWindowSeconds)) {
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
      return Objects.hash(new Object[]{this.policies, this.selectPolicy, this.stabilizationWindowSeconds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.policies != null && !this.policies.isEmpty()) {
         sb.append("policies:");
         sb.append(this.policies + ",");
      }

      if (this.selectPolicy != null) {
         sb.append("selectPolicy:");
         sb.append(this.selectPolicy + ",");
      }

      if (this.stabilizationWindowSeconds != null) {
         sb.append("stabilizationWindowSeconds:");
         sb.append(this.stabilizationWindowSeconds + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PoliciesNested extends HPAScalingPolicyFluent implements Nested {
      HPAScalingPolicyBuilder builder;
      int index;

      PoliciesNested(int index, HPAScalingPolicy item) {
         this.index = index;
         this.builder = new HPAScalingPolicyBuilder(this, item);
      }

      public Object and() {
         return HPAScalingRulesFluent.this.setToPolicies(this.index, this.builder.build());
      }

      public Object endPolicy() {
         return this.and();
      }
   }
}
