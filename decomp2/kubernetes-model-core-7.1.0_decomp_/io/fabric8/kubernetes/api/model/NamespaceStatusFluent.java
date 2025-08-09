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
import java.util.function.Predicate;

public class NamespaceStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private String phase;
   private Map additionalProperties;

   public NamespaceStatusFluent() {
   }

   public NamespaceStatusFluent(NamespaceStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamespaceStatus instance) {
      instance = instance != null ? instance : new NamespaceStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withPhase(instance.getPhase());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamespaceStatusFluent addToConditions(int index, NamespaceCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NamespaceStatusFluent setToConditions(int index, NamespaceCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NamespaceStatusFluent addToConditions(NamespaceCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(NamespaceCondition item : items) {
         NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NamespaceStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(NamespaceCondition item : items) {
         NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NamespaceStatusFluent removeFromConditions(NamespaceCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(NamespaceCondition item : items) {
            NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public NamespaceStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(NamespaceCondition item : items) {
            NamespaceConditionBuilder builder = new NamespaceConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public NamespaceStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<NamespaceConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            NamespaceConditionBuilder builder = (NamespaceConditionBuilder)each.next();
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

   public NamespaceCondition buildCondition(int index) {
      return ((NamespaceConditionBuilder)this.conditions.get(index)).build();
   }

   public NamespaceCondition buildFirstCondition() {
      return ((NamespaceConditionBuilder)this.conditions.get(0)).build();
   }

   public NamespaceCondition buildLastCondition() {
      return ((NamespaceConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public NamespaceCondition buildMatchingCondition(Predicate predicate) {
      for(NamespaceConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(NamespaceConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamespaceStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(NamespaceCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public NamespaceStatusFluent withConditions(NamespaceCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(NamespaceCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public NamespaceStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new NamespaceCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (NamespaceCondition)null);
   }

   public ConditionsNested addNewConditionLike(NamespaceCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, NamespaceCondition item) {
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
         if (predicate.test((NamespaceConditionBuilder)this.conditions.get(i))) {
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

   public String getPhase() {
      return this.phase;
   }

   public NamespaceStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public NamespaceStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamespaceStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamespaceStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamespaceStatusFluent removeFromAdditionalProperties(Map map) {
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

   public NamespaceStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            NamespaceStatusFluent that = (NamespaceStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.phase, that.phase)) {
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
      return Objects.hash(new Object[]{this.conditions, this.phase, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.phase != null) {
         sb.append("phase:");
         sb.append(this.phase + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends NamespaceConditionFluent implements Nested {
      NamespaceConditionBuilder builder;
      int index;

      ConditionsNested(int index, NamespaceCondition item) {
         this.index = index;
         this.builder = new NamespaceConditionBuilder(this, item);
      }

      public Object and() {
         return NamespaceStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
