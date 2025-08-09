package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

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

public class PriorityLevelConfigurationStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private Map additionalProperties;

   public PriorityLevelConfigurationStatusFluent() {
   }

   public PriorityLevelConfigurationStatusFluent(PriorityLevelConfigurationStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PriorityLevelConfigurationStatus instance) {
      instance = instance != null ? instance : new PriorityLevelConfigurationStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PriorityLevelConfigurationStatusFluent addToConditions(int index, PriorityLevelConfigurationCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent setToConditions(int index, PriorityLevelConfigurationCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent addToConditions(PriorityLevelConfigurationCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PriorityLevelConfigurationCondition item : items) {
         PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PriorityLevelConfigurationCondition item : items) {
         PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent removeFromConditions(PriorityLevelConfigurationCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PriorityLevelConfigurationCondition item : items) {
            PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PriorityLevelConfigurationStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PriorityLevelConfigurationCondition item : items) {
            PriorityLevelConfigurationConditionBuilder builder = new PriorityLevelConfigurationConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PriorityLevelConfigurationStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<PriorityLevelConfigurationConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            PriorityLevelConfigurationConditionBuilder builder = (PriorityLevelConfigurationConditionBuilder)each.next();
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

   public PriorityLevelConfigurationCondition buildCondition(int index) {
      return ((PriorityLevelConfigurationConditionBuilder)this.conditions.get(index)).build();
   }

   public PriorityLevelConfigurationCondition buildFirstCondition() {
      return ((PriorityLevelConfigurationConditionBuilder)this.conditions.get(0)).build();
   }

   public PriorityLevelConfigurationCondition buildLastCondition() {
      return ((PriorityLevelConfigurationConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public PriorityLevelConfigurationCondition buildMatchingCondition(Predicate predicate) {
      for(PriorityLevelConfigurationConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(PriorityLevelConfigurationConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PriorityLevelConfigurationStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(PriorityLevelConfigurationCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent withConditions(PriorityLevelConfigurationCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(PriorityLevelConfigurationCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public PriorityLevelConfigurationStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new PriorityLevelConfigurationCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (PriorityLevelConfigurationCondition)null);
   }

   public ConditionsNested addNewConditionLike(PriorityLevelConfigurationCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, PriorityLevelConfigurationCondition item) {
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
         if (predicate.test((PriorityLevelConfigurationConditionBuilder)this.conditions.get(i))) {
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

   public PriorityLevelConfigurationStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PriorityLevelConfigurationStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PriorityLevelConfigurationStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PriorityLevelConfigurationStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PriorityLevelConfigurationStatusFluent that = (PriorityLevelConfigurationStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
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
      return Objects.hash(new Object[]{this.conditions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends PriorityLevelConfigurationConditionFluent implements Nested {
      PriorityLevelConfigurationConditionBuilder builder;
      int index;

      ConditionsNested(int index, PriorityLevelConfigurationCondition item) {
         this.index = index;
         this.builder = new PriorityLevelConfigurationConditionBuilder(this, item);
      }

      public Object and() {
         return PriorityLevelConfigurationStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
