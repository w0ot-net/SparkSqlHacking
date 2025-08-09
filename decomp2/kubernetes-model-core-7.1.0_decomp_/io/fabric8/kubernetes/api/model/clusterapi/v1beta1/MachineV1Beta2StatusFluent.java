package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

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

public class MachineV1Beta2StatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private Map additionalProperties;

   public MachineV1Beta2StatusFluent() {
   }

   public MachineV1Beta2StatusFluent(MachineV1Beta2Status instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MachineV1Beta2Status instance) {
      instance = instance != null ? instance : new MachineV1Beta2Status();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MachineV1Beta2StatusFluent addToConditions(int index, io.fabric8.kubernetes.api.model.Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent setToConditions(int index, io.fabric8.kubernetes.api.model.Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent addToConditions(io.fabric8.kubernetes.api.model.Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(io.fabric8.kubernetes.api.model.Condition item : items) {
         io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(io.fabric8.kubernetes.api.model.Condition item : items) {
         io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent removeFromConditions(io.fabric8.kubernetes.api.model.Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(io.fabric8.kubernetes.api.model.Condition item : items) {
            io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public MachineV1Beta2StatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(io.fabric8.kubernetes.api.model.Condition item : items) {
            io.fabric8.kubernetes.api.model.ConditionBuilder builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public MachineV1Beta2StatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<io.fabric8.kubernetes.api.model.ConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            io.fabric8.kubernetes.api.model.ConditionBuilder builder = (io.fabric8.kubernetes.api.model.ConditionBuilder)each.next();
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

   public io.fabric8.kubernetes.api.model.Condition buildCondition(int index) {
      return ((io.fabric8.kubernetes.api.model.ConditionBuilder)this.conditions.get(index)).build();
   }

   public io.fabric8.kubernetes.api.model.Condition buildFirstCondition() {
      return ((io.fabric8.kubernetes.api.model.ConditionBuilder)this.conditions.get(0)).build();
   }

   public io.fabric8.kubernetes.api.model.Condition buildLastCondition() {
      return ((io.fabric8.kubernetes.api.model.ConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public io.fabric8.kubernetes.api.model.Condition buildMatchingCondition(Predicate predicate) {
      for(io.fabric8.kubernetes.api.model.ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(io.fabric8.kubernetes.api.model.ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MachineV1Beta2StatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(io.fabric8.kubernetes.api.model.Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public MachineV1Beta2StatusFluent withConditions(io.fabric8.kubernetes.api.model.Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(io.fabric8.kubernetes.api.model.Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (io.fabric8.kubernetes.api.model.Condition)null);
   }

   public ConditionsNested addNewConditionLike(io.fabric8.kubernetes.api.model.Condition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, io.fabric8.kubernetes.api.model.Condition item) {
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
         if (predicate.test((io.fabric8.kubernetes.api.model.ConditionBuilder)this.conditions.get(i))) {
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

   public MachineV1Beta2StatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MachineV1Beta2StatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MachineV1Beta2StatusFluent removeFromAdditionalProperties(Map map) {
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

   public MachineV1Beta2StatusFluent withAdditionalProperties(Map additionalProperties) {
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
            MachineV1Beta2StatusFluent that = (MachineV1Beta2StatusFluent)o;
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

   public class ConditionsNested extends io.fabric8.kubernetes.api.model.ConditionFluent implements Nested {
      io.fabric8.kubernetes.api.model.ConditionBuilder builder;
      int index;

      ConditionsNested(int index, io.fabric8.kubernetes.api.model.Condition item) {
         this.index = index;
         this.builder = new io.fabric8.kubernetes.api.model.ConditionBuilder(this, item);
      }

      public Object and() {
         return MachineV1Beta2StatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
