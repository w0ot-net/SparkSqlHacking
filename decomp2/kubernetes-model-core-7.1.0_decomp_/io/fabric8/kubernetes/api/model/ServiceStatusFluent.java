package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ServiceStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private LoadBalancerStatusBuilder loadBalancer;
   private Map additionalProperties;

   public ServiceStatusFluent() {
   }

   public ServiceStatusFluent(ServiceStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServiceStatus instance) {
      instance = instance != null ? instance : new ServiceStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withLoadBalancer(instance.getLoadBalancer());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ServiceStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ConditionBuilder builder = new ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ServiceStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ConditionBuilder builder = new ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ServiceStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         ConditionBuilder builder = new ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ServiceStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         ConditionBuilder builder = new ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ServiceStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            ConditionBuilder builder = new ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public ServiceStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            ConditionBuilder builder = new ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public ServiceStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<ConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            ConditionBuilder builder = (ConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public Condition buildCondition(int index) {
      return ((ConditionBuilder)this.conditions.get(index)).build();
   }

   public Condition buildFirstCondition() {
      return ((ConditionBuilder)this.conditions.get(0)).build();
   }

   public Condition buildLastCondition() {
      return ((ConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public Condition buildMatchingCondition(Predicate predicate) {
      for(ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public ServiceStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (Condition)null);
   }

   public ConditionsNested addNewConditionLike(Condition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, Condition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((ConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public LoadBalancerStatus buildLoadBalancer() {
      return this.loadBalancer != null ? this.loadBalancer.build() : null;
   }

   public ServiceStatusFluent withLoadBalancer(LoadBalancerStatus loadBalancer) {
      this._visitables.remove("loadBalancer");
      if (loadBalancer != null) {
         this.loadBalancer = new LoadBalancerStatusBuilder(loadBalancer);
         this._visitables.get("loadBalancer").add(this.loadBalancer);
      } else {
         this.loadBalancer = null;
         this._visitables.get("loadBalancer").remove(this.loadBalancer);
      }

      return this;
   }

   public boolean hasLoadBalancer() {
      return this.loadBalancer != null;
   }

   public LoadBalancerNested withNewLoadBalancer() {
      return new LoadBalancerNested((LoadBalancerStatus)null);
   }

   public LoadBalancerNested withNewLoadBalancerLike(LoadBalancerStatus item) {
      return new LoadBalancerNested(item);
   }

   public LoadBalancerNested editLoadBalancer() {
      return this.withNewLoadBalancerLike((LoadBalancerStatus)Optional.ofNullable(this.buildLoadBalancer()).orElse((Object)null));
   }

   public LoadBalancerNested editOrNewLoadBalancer() {
      return this.withNewLoadBalancerLike((LoadBalancerStatus)Optional.ofNullable(this.buildLoadBalancer()).orElse((new LoadBalancerStatusBuilder()).build()));
   }

   public LoadBalancerNested editOrNewLoadBalancerLike(LoadBalancerStatus item) {
      return this.withNewLoadBalancerLike((LoadBalancerStatus)Optional.ofNullable(this.buildLoadBalancer()).orElse(item));
   }

   public ServiceStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServiceStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServiceStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServiceStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ServiceStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ServiceStatusFluent that = (ServiceStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.loadBalancer, that.loadBalancer)) {
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
      return Objects.hash(new Object[]{this.conditions, this.loadBalancer, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.loadBalancer != null) {
         sb.append("loadBalancer:");
         sb.append(this.loadBalancer + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends ConditionFluent implements Nested {
      ConditionBuilder builder;
      int index;

      ConditionsNested(int index, Condition item) {
         this.index = index;
         this.builder = new ConditionBuilder(this, item);
      }

      public Object and() {
         return ServiceStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class LoadBalancerNested extends LoadBalancerStatusFluent implements Nested {
      LoadBalancerStatusBuilder builder;

      LoadBalancerNested(LoadBalancerStatus item) {
         this.builder = new LoadBalancerStatusBuilder(this, item);
      }

      public Object and() {
         return ServiceStatusFluent.this.withLoadBalancer(this.builder.build());
      }

      public Object endLoadBalancer() {
         return this.and();
      }
   }
}
