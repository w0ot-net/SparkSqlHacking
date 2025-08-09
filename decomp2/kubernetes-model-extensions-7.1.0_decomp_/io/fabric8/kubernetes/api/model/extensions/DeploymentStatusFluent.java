package io.fabric8.kubernetes.api.model.extensions;

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

public class DeploymentStatusFluent extends BaseFluent {
   private Integer availableReplicas;
   private Integer collisionCount;
   private ArrayList conditions = new ArrayList();
   private Long observedGeneration;
   private Integer readyReplicas;
   private Integer replicas;
   private Integer unavailableReplicas;
   private Integer updatedReplicas;
   private Map additionalProperties;

   public DeploymentStatusFluent() {
   }

   public DeploymentStatusFluent(DeploymentStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeploymentStatus instance) {
      instance = instance != null ? instance : new DeploymentStatus();
      if (instance != null) {
         this.withAvailableReplicas(instance.getAvailableReplicas());
         this.withCollisionCount(instance.getCollisionCount());
         this.withConditions(instance.getConditions());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withReadyReplicas(instance.getReadyReplicas());
         this.withReplicas(instance.getReplicas());
         this.withUnavailableReplicas(instance.getUnavailableReplicas());
         this.withUpdatedReplicas(instance.getUpdatedReplicas());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAvailableReplicas() {
      return this.availableReplicas;
   }

   public DeploymentStatusFluent withAvailableReplicas(Integer availableReplicas) {
      this.availableReplicas = availableReplicas;
      return this;
   }

   public boolean hasAvailableReplicas() {
      return this.availableReplicas != null;
   }

   public Integer getCollisionCount() {
      return this.collisionCount;
   }

   public DeploymentStatusFluent withCollisionCount(Integer collisionCount) {
      this.collisionCount = collisionCount;
      return this;
   }

   public boolean hasCollisionCount() {
      return this.collisionCount != null;
   }

   public DeploymentStatusFluent addToConditions(int index, DeploymentCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DeploymentStatusFluent setToConditions(int index, DeploymentCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DeploymentStatusFluent addToConditions(DeploymentCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(DeploymentCondition item : items) {
         DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DeploymentStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(DeploymentCondition item : items) {
         DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DeploymentStatusFluent removeFromConditions(DeploymentCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(DeploymentCondition item : items) {
            DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public DeploymentStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(DeploymentCondition item : items) {
            DeploymentConditionBuilder builder = new DeploymentConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public DeploymentStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<DeploymentConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            DeploymentConditionBuilder builder = (DeploymentConditionBuilder)each.next();
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

   public DeploymentCondition buildCondition(int index) {
      return ((DeploymentConditionBuilder)this.conditions.get(index)).build();
   }

   public DeploymentCondition buildFirstCondition() {
      return ((DeploymentConditionBuilder)this.conditions.get(0)).build();
   }

   public DeploymentCondition buildLastCondition() {
      return ((DeploymentConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public DeploymentCondition buildMatchingCondition(Predicate predicate) {
      for(DeploymentConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(DeploymentConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeploymentStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(DeploymentCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public DeploymentStatusFluent withConditions(DeploymentCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(DeploymentCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (DeploymentCondition)null);
   }

   public ConditionsNested addNewConditionLike(DeploymentCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, DeploymentCondition item) {
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
         if (predicate.test((DeploymentConditionBuilder)this.conditions.get(i))) {
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

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public DeploymentStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public Integer getReadyReplicas() {
      return this.readyReplicas;
   }

   public DeploymentStatusFluent withReadyReplicas(Integer readyReplicas) {
      this.readyReplicas = readyReplicas;
      return this;
   }

   public boolean hasReadyReplicas() {
      return this.readyReplicas != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public DeploymentStatusFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public Integer getUnavailableReplicas() {
      return this.unavailableReplicas;
   }

   public DeploymentStatusFluent withUnavailableReplicas(Integer unavailableReplicas) {
      this.unavailableReplicas = unavailableReplicas;
      return this;
   }

   public boolean hasUnavailableReplicas() {
      return this.unavailableReplicas != null;
   }

   public Integer getUpdatedReplicas() {
      return this.updatedReplicas;
   }

   public DeploymentStatusFluent withUpdatedReplicas(Integer updatedReplicas) {
      this.updatedReplicas = updatedReplicas;
      return this;
   }

   public boolean hasUpdatedReplicas() {
      return this.updatedReplicas != null;
   }

   public DeploymentStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeploymentStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeploymentStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeploymentStatusFluent removeFromAdditionalProperties(Map map) {
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

   public DeploymentStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            DeploymentStatusFluent that = (DeploymentStatusFluent)o;
            if (!Objects.equals(this.availableReplicas, that.availableReplicas)) {
               return false;
            } else if (!Objects.equals(this.collisionCount, that.collisionCount)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.readyReplicas, that.readyReplicas)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.unavailableReplicas, that.unavailableReplicas)) {
               return false;
            } else if (!Objects.equals(this.updatedReplicas, that.updatedReplicas)) {
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
      return Objects.hash(new Object[]{this.availableReplicas, this.collisionCount, this.conditions, this.observedGeneration, this.readyReplicas, this.replicas, this.unavailableReplicas, this.updatedReplicas, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.availableReplicas != null) {
         sb.append("availableReplicas:");
         sb.append(this.availableReplicas + ",");
      }

      if (this.collisionCount != null) {
         sb.append("collisionCount:");
         sb.append(this.collisionCount + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
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

      if (this.unavailableReplicas != null) {
         sb.append("unavailableReplicas:");
         sb.append(this.unavailableReplicas + ",");
      }

      if (this.updatedReplicas != null) {
         sb.append("updatedReplicas:");
         sb.append(this.updatedReplicas + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends DeploymentConditionFluent implements Nested {
      DeploymentConditionBuilder builder;
      int index;

      ConditionsNested(int index, DeploymentCondition item) {
         this.index = index;
         this.builder = new DeploymentConditionBuilder(this, item);
      }

      public Object and() {
         return DeploymentStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
