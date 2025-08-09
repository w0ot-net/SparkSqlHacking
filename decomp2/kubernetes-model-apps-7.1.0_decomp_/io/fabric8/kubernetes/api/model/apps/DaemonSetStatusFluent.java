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

public class DaemonSetStatusFluent extends BaseFluent {
   private Integer collisionCount;
   private ArrayList conditions = new ArrayList();
   private Integer currentNumberScheduled;
   private Integer desiredNumberScheduled;
   private Integer numberAvailable;
   private Integer numberMisscheduled;
   private Integer numberReady;
   private Integer numberUnavailable;
   private Long observedGeneration;
   private Integer updatedNumberScheduled;
   private Map additionalProperties;

   public DaemonSetStatusFluent() {
   }

   public DaemonSetStatusFluent(DaemonSetStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DaemonSetStatus instance) {
      instance = instance != null ? instance : new DaemonSetStatus();
      if (instance != null) {
         this.withCollisionCount(instance.getCollisionCount());
         this.withConditions(instance.getConditions());
         this.withCurrentNumberScheduled(instance.getCurrentNumberScheduled());
         this.withDesiredNumberScheduled(instance.getDesiredNumberScheduled());
         this.withNumberAvailable(instance.getNumberAvailable());
         this.withNumberMisscheduled(instance.getNumberMisscheduled());
         this.withNumberReady(instance.getNumberReady());
         this.withNumberUnavailable(instance.getNumberUnavailable());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withUpdatedNumberScheduled(instance.getUpdatedNumberScheduled());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getCollisionCount() {
      return this.collisionCount;
   }

   public DaemonSetStatusFluent withCollisionCount(Integer collisionCount) {
      this.collisionCount = collisionCount;
      return this;
   }

   public boolean hasCollisionCount() {
      return this.collisionCount != null;
   }

   public DaemonSetStatusFluent addToConditions(int index, DaemonSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DaemonSetStatusFluent setToConditions(int index, DaemonSetCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DaemonSetStatusFluent addToConditions(DaemonSetCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(DaemonSetCondition item : items) {
         DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DaemonSetStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(DaemonSetCondition item : items) {
         DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public DaemonSetStatusFluent removeFromConditions(DaemonSetCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(DaemonSetCondition item : items) {
            DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public DaemonSetStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(DaemonSetCondition item : items) {
            DaemonSetConditionBuilder builder = new DaemonSetConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public DaemonSetStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<DaemonSetConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            DaemonSetConditionBuilder builder = (DaemonSetConditionBuilder)each.next();
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

   public DaemonSetCondition buildCondition(int index) {
      return ((DaemonSetConditionBuilder)this.conditions.get(index)).build();
   }

   public DaemonSetCondition buildFirstCondition() {
      return ((DaemonSetConditionBuilder)this.conditions.get(0)).build();
   }

   public DaemonSetCondition buildLastCondition() {
      return ((DaemonSetConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public DaemonSetCondition buildMatchingCondition(Predicate predicate) {
      for(DaemonSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(DaemonSetConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DaemonSetStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(DaemonSetCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public DaemonSetStatusFluent withConditions(DaemonSetCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(DaemonSetCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public DaemonSetStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new DaemonSetCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (DaemonSetCondition)null);
   }

   public ConditionsNested addNewConditionLike(DaemonSetCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, DaemonSetCondition item) {
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
         if (predicate.test((DaemonSetConditionBuilder)this.conditions.get(i))) {
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

   public Integer getCurrentNumberScheduled() {
      return this.currentNumberScheduled;
   }

   public DaemonSetStatusFluent withCurrentNumberScheduled(Integer currentNumberScheduled) {
      this.currentNumberScheduled = currentNumberScheduled;
      return this;
   }

   public boolean hasCurrentNumberScheduled() {
      return this.currentNumberScheduled != null;
   }

   public Integer getDesiredNumberScheduled() {
      return this.desiredNumberScheduled;
   }

   public DaemonSetStatusFluent withDesiredNumberScheduled(Integer desiredNumberScheduled) {
      this.desiredNumberScheduled = desiredNumberScheduled;
      return this;
   }

   public boolean hasDesiredNumberScheduled() {
      return this.desiredNumberScheduled != null;
   }

   public Integer getNumberAvailable() {
      return this.numberAvailable;
   }

   public DaemonSetStatusFluent withNumberAvailable(Integer numberAvailable) {
      this.numberAvailable = numberAvailable;
      return this;
   }

   public boolean hasNumberAvailable() {
      return this.numberAvailable != null;
   }

   public Integer getNumberMisscheduled() {
      return this.numberMisscheduled;
   }

   public DaemonSetStatusFluent withNumberMisscheduled(Integer numberMisscheduled) {
      this.numberMisscheduled = numberMisscheduled;
      return this;
   }

   public boolean hasNumberMisscheduled() {
      return this.numberMisscheduled != null;
   }

   public Integer getNumberReady() {
      return this.numberReady;
   }

   public DaemonSetStatusFluent withNumberReady(Integer numberReady) {
      this.numberReady = numberReady;
      return this;
   }

   public boolean hasNumberReady() {
      return this.numberReady != null;
   }

   public Integer getNumberUnavailable() {
      return this.numberUnavailable;
   }

   public DaemonSetStatusFluent withNumberUnavailable(Integer numberUnavailable) {
      this.numberUnavailable = numberUnavailable;
      return this;
   }

   public boolean hasNumberUnavailable() {
      return this.numberUnavailable != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public DaemonSetStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public Integer getUpdatedNumberScheduled() {
      return this.updatedNumberScheduled;
   }

   public DaemonSetStatusFluent withUpdatedNumberScheduled(Integer updatedNumberScheduled) {
      this.updatedNumberScheduled = updatedNumberScheduled;
      return this;
   }

   public boolean hasUpdatedNumberScheduled() {
      return this.updatedNumberScheduled != null;
   }

   public DaemonSetStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DaemonSetStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DaemonSetStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DaemonSetStatusFluent removeFromAdditionalProperties(Map map) {
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

   public DaemonSetStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            DaemonSetStatusFluent that = (DaemonSetStatusFluent)o;
            if (!Objects.equals(this.collisionCount, that.collisionCount)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.currentNumberScheduled, that.currentNumberScheduled)) {
               return false;
            } else if (!Objects.equals(this.desiredNumberScheduled, that.desiredNumberScheduled)) {
               return false;
            } else if (!Objects.equals(this.numberAvailable, that.numberAvailable)) {
               return false;
            } else if (!Objects.equals(this.numberMisscheduled, that.numberMisscheduled)) {
               return false;
            } else if (!Objects.equals(this.numberReady, that.numberReady)) {
               return false;
            } else if (!Objects.equals(this.numberUnavailable, that.numberUnavailable)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.updatedNumberScheduled, that.updatedNumberScheduled)) {
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
      return Objects.hash(new Object[]{this.collisionCount, this.conditions, this.currentNumberScheduled, this.desiredNumberScheduled, this.numberAvailable, this.numberMisscheduled, this.numberReady, this.numberUnavailable, this.observedGeneration, this.updatedNumberScheduled, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.collisionCount != null) {
         sb.append("collisionCount:");
         sb.append(this.collisionCount + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.currentNumberScheduled != null) {
         sb.append("currentNumberScheduled:");
         sb.append(this.currentNumberScheduled + ",");
      }

      if (this.desiredNumberScheduled != null) {
         sb.append("desiredNumberScheduled:");
         sb.append(this.desiredNumberScheduled + ",");
      }

      if (this.numberAvailable != null) {
         sb.append("numberAvailable:");
         sb.append(this.numberAvailable + ",");
      }

      if (this.numberMisscheduled != null) {
         sb.append("numberMisscheduled:");
         sb.append(this.numberMisscheduled + ",");
      }

      if (this.numberReady != null) {
         sb.append("numberReady:");
         sb.append(this.numberReady + ",");
      }

      if (this.numberUnavailable != null) {
         sb.append("numberUnavailable:");
         sb.append(this.numberUnavailable + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.updatedNumberScheduled != null) {
         sb.append("updatedNumberScheduled:");
         sb.append(this.updatedNumberScheduled + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends DaemonSetConditionFluent implements Nested {
      DaemonSetConditionBuilder builder;
      int index;

      ConditionsNested(int index, DaemonSetCondition item) {
         this.index = index;
         this.builder = new DaemonSetConditionBuilder(this, item);
      }

      public Object and() {
         return DaemonSetStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
