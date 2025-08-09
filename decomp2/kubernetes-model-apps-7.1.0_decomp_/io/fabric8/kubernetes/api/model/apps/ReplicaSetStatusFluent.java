package io.fabric8.kubernetes.api.model.apps;

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

public class ReplicaSetStatusFluent extends BaseFluent {
   private Integer availableReplicas;
   private ArrayList conditions = new ArrayList();
   private Integer fullyLabeledReplicas;
   private Long observedGeneration;
   private Integer readyReplicas;
   private Integer replicas;
   private Map additionalProperties;

   public ReplicaSetStatusFluent() {
   }

   public ReplicaSetStatusFluent(ReplicaSetStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ReplicaSetStatus instance) {
      instance = instance != null ? instance : new ReplicaSetStatus();
      if (instance != null) {
         this.withAvailableReplicas(instance.getAvailableReplicas());
         this.withConditions(instance.getConditions());
         this.withFullyLabeledReplicas(instance.getFullyLabeledReplicas());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withReadyReplicas(instance.getReadyReplicas());
         this.withReplicas(instance.getReplicas());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAvailableReplicas() {
      return this.availableReplicas;
   }

   public ReplicaSetStatusFluent withAvailableReplicas(Integer availableReplicas) {
      this.availableReplicas = availableReplicas;
      return this;
   }

   public boolean hasAvailableReplicas() {
      return this.availableReplicas != null;
   }

   public ReplicaSetStatusFluent addToConditions(int index, ReplicaSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ReplicaSetStatusFluent setToConditions(int index, ReplicaSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ReplicaSetStatusFluent addToConditions(ReplicaSetCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(ReplicaSetCondition item : items) {
         ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ReplicaSetStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(ReplicaSetCondition item : items) {
         ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public ReplicaSetStatusFluent removeFromConditions(ReplicaSetCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(ReplicaSetCondition item : items) {
            ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public ReplicaSetStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(ReplicaSetCondition item : items) {
            ReplicaSetConditionBuilder builder = new ReplicaSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public ReplicaSetStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<ReplicaSetConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            ReplicaSetConditionBuilder builder = (ReplicaSetConditionBuilder)each.next();
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

   public ReplicaSetCondition buildCondition(int index) {
      return ((ReplicaSetConditionBuilder)this.conditions.get(index)).build();
   }

   public ReplicaSetCondition buildFirstCondition() {
      return ((ReplicaSetConditionBuilder)this.conditions.get(0)).build();
   }

   public ReplicaSetCondition buildLastCondition() {
      return ((ReplicaSetConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public ReplicaSetCondition buildMatchingCondition(Predicate predicate) {
      for(ReplicaSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(ReplicaSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ReplicaSetStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(ReplicaSetCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public ReplicaSetStatusFluent withConditions(ReplicaSetCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(ReplicaSetCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ReplicaSetStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new ReplicaSetCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (ReplicaSetCondition)null);
   }

   public ConditionsNested addNewConditionLike(ReplicaSetCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, ReplicaSetCondition item) {
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
         if (predicate.test((ReplicaSetConditionBuilder)this.conditions.get(i))) {
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

   public Integer getFullyLabeledReplicas() {
      return this.fullyLabeledReplicas;
   }

   public ReplicaSetStatusFluent withFullyLabeledReplicas(Integer fullyLabeledReplicas) {
      this.fullyLabeledReplicas = fullyLabeledReplicas;
      return this;
   }

   public boolean hasFullyLabeledReplicas() {
      return this.fullyLabeledReplicas != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public ReplicaSetStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public Integer getReadyReplicas() {
      return this.readyReplicas;
   }

   public ReplicaSetStatusFluent withReadyReplicas(Integer readyReplicas) {
      this.readyReplicas = readyReplicas;
      return this;
   }

   public boolean hasReadyReplicas() {
      return this.readyReplicas != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public ReplicaSetStatusFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public ReplicaSetStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ReplicaSetStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ReplicaSetStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ReplicaSetStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ReplicaSetStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ReplicaSetStatusFluent that = (ReplicaSetStatusFluent)o;
            if (!Objects.equals(this.availableReplicas, that.availableReplicas)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.fullyLabeledReplicas, that.fullyLabeledReplicas)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.readyReplicas, that.readyReplicas)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
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
      return Objects.hash(new Object[]{this.availableReplicas, this.conditions, this.fullyLabeledReplicas, this.observedGeneration, this.readyReplicas, this.replicas, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.availableReplicas != null) {
         sb.append("availableReplicas:");
         sb.append(this.availableReplicas + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.fullyLabeledReplicas != null) {
         sb.append("fullyLabeledReplicas:");
         sb.append(this.fullyLabeledReplicas + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.readyReplicas != null) {
         sb.append("readyReplicas:");
         sb.append(this.readyReplicas + ",");
      }

      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends ReplicaSetConditionFluent implements Nested {
      ReplicaSetConditionBuilder builder;
      int index;

      ConditionsNested(int index, ReplicaSetCondition item) {
         this.index = index;
         this.builder = new ReplicaSetConditionBuilder(this, item);
      }

      public Object and() {
         return ReplicaSetStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
