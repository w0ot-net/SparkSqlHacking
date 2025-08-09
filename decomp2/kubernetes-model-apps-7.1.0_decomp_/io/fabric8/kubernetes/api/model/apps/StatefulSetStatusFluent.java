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

public class StatefulSetStatusFluent extends BaseFluent {
   private Integer availableReplicas;
   private Integer collisionCount;
   private ArrayList conditions = new ArrayList();
   private Integer currentReplicas;
   private String currentRevision;
   private Long observedGeneration;
   private Integer readyReplicas;
   private Integer replicas;
   private String updateRevision;
   private Integer updatedReplicas;
   private Map additionalProperties;

   public StatefulSetStatusFluent() {
   }

   public StatefulSetStatusFluent(StatefulSetStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatefulSetStatus instance) {
      instance = instance != null ? instance : new StatefulSetStatus();
      if (instance != null) {
         this.withAvailableReplicas(instance.getAvailableReplicas());
         this.withCollisionCount(instance.getCollisionCount());
         this.withConditions(instance.getConditions());
         this.withCurrentReplicas(instance.getCurrentReplicas());
         this.withCurrentRevision(instance.getCurrentRevision());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withReadyReplicas(instance.getReadyReplicas());
         this.withReplicas(instance.getReplicas());
         this.withUpdateRevision(instance.getUpdateRevision());
         this.withUpdatedReplicas(instance.getUpdatedReplicas());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAvailableReplicas() {
      return this.availableReplicas;
   }

   public StatefulSetStatusFluent withAvailableReplicas(Integer availableReplicas) {
      this.availableReplicas = availableReplicas;
      return this;
   }

   public boolean hasAvailableReplicas() {
      return this.availableReplicas != null;
   }

   public Integer getCollisionCount() {
      return this.collisionCount;
   }

   public StatefulSetStatusFluent withCollisionCount(Integer collisionCount) {
      this.collisionCount = collisionCount;
      return this;
   }

   public boolean hasCollisionCount() {
      return this.collisionCount != null;
   }

   public StatefulSetStatusFluent addToConditions(int index, StatefulSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StatefulSetStatusFluent setToConditions(int index, StatefulSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StatefulSetStatusFluent addToConditions(StatefulSetCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(StatefulSetCondition item : items) {
         StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StatefulSetStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(StatefulSetCondition item : items) {
         StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StatefulSetStatusFluent removeFromConditions(StatefulSetCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(StatefulSetCondition item : items) {
            StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public StatefulSetStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(StatefulSetCondition item : items) {
            StatefulSetConditionBuilder builder = new StatefulSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public StatefulSetStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<StatefulSetConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            StatefulSetConditionBuilder builder = (StatefulSetConditionBuilder)each.next();
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

   public StatefulSetCondition buildCondition(int index) {
      return ((StatefulSetConditionBuilder)this.conditions.get(index)).build();
   }

   public StatefulSetCondition buildFirstCondition() {
      return ((StatefulSetConditionBuilder)this.conditions.get(0)).build();
   }

   public StatefulSetCondition buildLastCondition() {
      return ((StatefulSetConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public StatefulSetCondition buildMatchingCondition(Predicate predicate) {
      for(StatefulSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(StatefulSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StatefulSetStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(StatefulSetCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public StatefulSetStatusFluent withConditions(StatefulSetCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(StatefulSetCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public StatefulSetStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new StatefulSetCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (StatefulSetCondition)null);
   }

   public ConditionsNested addNewConditionLike(StatefulSetCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, StatefulSetCondition item) {
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
         if (predicate.test((StatefulSetConditionBuilder)this.conditions.get(i))) {
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

   public Integer getCurrentReplicas() {
      return this.currentReplicas;
   }

   public StatefulSetStatusFluent withCurrentReplicas(Integer currentReplicas) {
      this.currentReplicas = currentReplicas;
      return this;
   }

   public boolean hasCurrentReplicas() {
      return this.currentReplicas != null;
   }

   public String getCurrentRevision() {
      return this.currentRevision;
   }

   public StatefulSetStatusFluent withCurrentRevision(String currentRevision) {
      this.currentRevision = currentRevision;
      return this;
   }

   public boolean hasCurrentRevision() {
      return this.currentRevision != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public StatefulSetStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public Integer getReadyReplicas() {
      return this.readyReplicas;
   }

   public StatefulSetStatusFluent withReadyReplicas(Integer readyReplicas) {
      this.readyReplicas = readyReplicas;
      return this;
   }

   public boolean hasReadyReplicas() {
      return this.readyReplicas != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public StatefulSetStatusFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public String getUpdateRevision() {
      return this.updateRevision;
   }

   public StatefulSetStatusFluent withUpdateRevision(String updateRevision) {
      this.updateRevision = updateRevision;
      return this;
   }

   public boolean hasUpdateRevision() {
      return this.updateRevision != null;
   }

   public Integer getUpdatedReplicas() {
      return this.updatedReplicas;
   }

   public StatefulSetStatusFluent withUpdatedReplicas(Integer updatedReplicas) {
      this.updatedReplicas = updatedReplicas;
      return this;
   }

   public boolean hasUpdatedReplicas() {
      return this.updatedReplicas != null;
   }

   public StatefulSetStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatefulSetStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatefulSetStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatefulSetStatusFluent removeFromAdditionalProperties(Map map) {
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

   public StatefulSetStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            StatefulSetStatusFluent that = (StatefulSetStatusFluent)o;
            if (!Objects.equals(this.availableReplicas, that.availableReplicas)) {
               return false;
            } else if (!Objects.equals(this.collisionCount, that.collisionCount)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.currentReplicas, that.currentReplicas)) {
               return false;
            } else if (!Objects.equals(this.currentRevision, that.currentRevision)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.readyReplicas, that.readyReplicas)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.updateRevision, that.updateRevision)) {
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
      return Objects.hash(new Object[]{this.availableReplicas, this.collisionCount, this.conditions, this.currentReplicas, this.currentRevision, this.observedGeneration, this.readyReplicas, this.replicas, this.updateRevision, this.updatedReplicas, this.additionalProperties, super.hashCode()});
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

      if (this.currentReplicas != null) {
         sb.append("currentReplicas:");
         sb.append(this.currentReplicas + ",");
      }

      if (this.currentRevision != null) {
         sb.append("currentRevision:");
         sb.append(this.currentRevision + ",");
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

      if (this.updateRevision != null) {
         sb.append("updateRevision:");
         sb.append(this.updateRevision + ",");
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

   public class ConditionsNested extends StatefulSetConditionFluent implements Nested {
      StatefulSetConditionBuilder builder;
      int index;

      ConditionsNested(int index, StatefulSetCondition item) {
         this.index = index;
         this.builder = new StatefulSetConditionBuilder(this, item);
      }

      public Object and() {
         return StatefulSetStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
