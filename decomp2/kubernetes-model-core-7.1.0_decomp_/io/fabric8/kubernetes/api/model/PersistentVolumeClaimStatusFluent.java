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

public class PersistentVolumeClaimStatusFluent extends BaseFluent {
   private List accessModes = new ArrayList();
   private Map allocatedResourceStatuses;
   private Map allocatedResources;
   private Map capacity;
   private ArrayList conditions = new ArrayList();
   private String currentVolumeAttributesClassName;
   private ModifyVolumeStatusBuilder modifyVolumeStatus;
   private String phase;
   private Map additionalProperties;

   public PersistentVolumeClaimStatusFluent() {
   }

   public PersistentVolumeClaimStatusFluent(PersistentVolumeClaimStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeClaimStatus instance) {
      instance = instance != null ? instance : new PersistentVolumeClaimStatus();
      if (instance != null) {
         this.withAccessModes(instance.getAccessModes());
         this.withAllocatedResourceStatuses(instance.getAllocatedResourceStatuses());
         this.withAllocatedResources(instance.getAllocatedResources());
         this.withCapacity(instance.getCapacity());
         this.withConditions(instance.getConditions());
         this.withCurrentVolumeAttributesClassName(instance.getCurrentVolumeAttributesClassName());
         this.withModifyVolumeStatus(instance.getModifyVolumeStatus());
         this.withPhase(instance.getPhase());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PersistentVolumeClaimStatusFluent addToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.add(index, item);
      return this;
   }

   public PersistentVolumeClaimStatusFluent setToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.set(index, item);
      return this;
   }

   public PersistentVolumeClaimStatusFluent addToAccessModes(String... items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addAllToAccessModes(Collection items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromAccessModes(String... items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeAllFromAccessModes(Collection items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public List getAccessModes() {
      return this.accessModes;
   }

   public String getAccessMode(int index) {
      return (String)this.accessModes.get(index);
   }

   public String getFirstAccessMode() {
      return (String)this.accessModes.get(0);
   }

   public String getLastAccessMode() {
      return (String)this.accessModes.get(this.accessModes.size() - 1);
   }

   public String getMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PersistentVolumeClaimStatusFluent withAccessModes(List accessModes) {
      if (accessModes != null) {
         this.accessModes = new ArrayList();

         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      } else {
         this.accessModes = null;
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent withAccessModes(String... accessModes) {
      if (this.accessModes != null) {
         this.accessModes.clear();
         this._visitables.remove("accessModes");
      }

      if (accessModes != null) {
         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      }

      return this;
   }

   public boolean hasAccessModes() {
      return this.accessModes != null && !this.accessModes.isEmpty();
   }

   public PersistentVolumeClaimStatusFluent addToAllocatedResourceStatuses(String key, String value) {
      if (this.allocatedResourceStatuses == null && key != null && value != null) {
         this.allocatedResourceStatuses = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.allocatedResourceStatuses.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addToAllocatedResourceStatuses(Map map) {
      if (this.allocatedResourceStatuses == null && map != null) {
         this.allocatedResourceStatuses = new LinkedHashMap();
      }

      if (map != null) {
         this.allocatedResourceStatuses.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromAllocatedResourceStatuses(String key) {
      if (this.allocatedResourceStatuses == null) {
         return this;
      } else {
         if (key != null && this.allocatedResourceStatuses != null) {
            this.allocatedResourceStatuses.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeFromAllocatedResourceStatuses(Map map) {
      if (this.allocatedResourceStatuses == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.allocatedResourceStatuses != null) {
                  this.allocatedResourceStatuses.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAllocatedResourceStatuses() {
      return this.allocatedResourceStatuses;
   }

   public PersistentVolumeClaimStatusFluent withAllocatedResourceStatuses(Map allocatedResourceStatuses) {
      if (allocatedResourceStatuses == null) {
         this.allocatedResourceStatuses = null;
      } else {
         this.allocatedResourceStatuses = new LinkedHashMap(allocatedResourceStatuses);
      }

      return this;
   }

   public boolean hasAllocatedResourceStatuses() {
      return this.allocatedResourceStatuses != null;
   }

   public PersistentVolumeClaimStatusFluent addToAllocatedResources(String key, Quantity value) {
      if (this.allocatedResources == null && key != null && value != null) {
         this.allocatedResources = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.allocatedResources.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addToAllocatedResources(Map map) {
      if (this.allocatedResources == null && map != null) {
         this.allocatedResources = new LinkedHashMap();
      }

      if (map != null) {
         this.allocatedResources.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromAllocatedResources(String key) {
      if (this.allocatedResources == null) {
         return this;
      } else {
         if (key != null && this.allocatedResources != null) {
            this.allocatedResources.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeFromAllocatedResources(Map map) {
      if (this.allocatedResources == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.allocatedResources != null) {
                  this.allocatedResources.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAllocatedResources() {
      return this.allocatedResources;
   }

   public PersistentVolumeClaimStatusFluent withAllocatedResources(Map allocatedResources) {
      if (allocatedResources == null) {
         this.allocatedResources = null;
      } else {
         this.allocatedResources = new LinkedHashMap(allocatedResources);
      }

      return this;
   }

   public boolean hasAllocatedResources() {
      return this.allocatedResources != null;
   }

   public PersistentVolumeClaimStatusFluent addToCapacity(String key, Quantity value) {
      if (this.capacity == null && key != null && value != null) {
         this.capacity = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.capacity.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addToCapacity(Map map) {
      if (this.capacity == null && map != null) {
         this.capacity = new LinkedHashMap();
      }

      if (map != null) {
         this.capacity.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromCapacity(String key) {
      if (this.capacity == null) {
         return this;
      } else {
         if (key != null && this.capacity != null) {
            this.capacity.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeFromCapacity(Map map) {
      if (this.capacity == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.capacity != null) {
                  this.capacity.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getCapacity() {
      return this.capacity;
   }

   public PersistentVolumeClaimStatusFluent withCapacity(Map capacity) {
      if (capacity == null) {
         this.capacity = null;
      } else {
         this.capacity = new LinkedHashMap(capacity);
      }

      return this;
   }

   public boolean hasCapacity() {
      return this.capacity != null;
   }

   public PersistentVolumeClaimStatusFluent addToConditions(int index, PersistentVolumeClaimCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent setToConditions(int index, PersistentVolumeClaimCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addToConditions(PersistentVolumeClaimCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PersistentVolumeClaimCondition item : items) {
         PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PersistentVolumeClaimCondition item : items) {
         PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromConditions(PersistentVolumeClaimCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PersistentVolumeClaimCondition item : items) {
            PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PersistentVolumeClaimCondition item : items) {
            PersistentVolumeClaimConditionBuilder builder = new PersistentVolumeClaimConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<PersistentVolumeClaimConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            PersistentVolumeClaimConditionBuilder builder = (PersistentVolumeClaimConditionBuilder)each.next();
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

   public PersistentVolumeClaimCondition buildCondition(int index) {
      return ((PersistentVolumeClaimConditionBuilder)this.conditions.get(index)).build();
   }

   public PersistentVolumeClaimCondition buildFirstCondition() {
      return ((PersistentVolumeClaimConditionBuilder)this.conditions.get(0)).build();
   }

   public PersistentVolumeClaimCondition buildLastCondition() {
      return ((PersistentVolumeClaimConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public PersistentVolumeClaimCondition buildMatchingCondition(Predicate predicate) {
      for(PersistentVolumeClaimConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(PersistentVolumeClaimConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PersistentVolumeClaimStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(PersistentVolumeClaimCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent withConditions(PersistentVolumeClaimCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(PersistentVolumeClaimCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (PersistentVolumeClaimCondition)null);
   }

   public ConditionsNested addNewConditionLike(PersistentVolumeClaimCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, PersistentVolumeClaimCondition item) {
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
         if (predicate.test((PersistentVolumeClaimConditionBuilder)this.conditions.get(i))) {
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

   public String getCurrentVolumeAttributesClassName() {
      return this.currentVolumeAttributesClassName;
   }

   public PersistentVolumeClaimStatusFluent withCurrentVolumeAttributesClassName(String currentVolumeAttributesClassName) {
      this.currentVolumeAttributesClassName = currentVolumeAttributesClassName;
      return this;
   }

   public boolean hasCurrentVolumeAttributesClassName() {
      return this.currentVolumeAttributesClassName != null;
   }

   public ModifyVolumeStatus buildModifyVolumeStatus() {
      return this.modifyVolumeStatus != null ? this.modifyVolumeStatus.build() : null;
   }

   public PersistentVolumeClaimStatusFluent withModifyVolumeStatus(ModifyVolumeStatus modifyVolumeStatus) {
      this._visitables.remove("modifyVolumeStatus");
      if (modifyVolumeStatus != null) {
         this.modifyVolumeStatus = new ModifyVolumeStatusBuilder(modifyVolumeStatus);
         this._visitables.get("modifyVolumeStatus").add(this.modifyVolumeStatus);
      } else {
         this.modifyVolumeStatus = null;
         this._visitables.get("modifyVolumeStatus").remove(this.modifyVolumeStatus);
      }

      return this;
   }

   public boolean hasModifyVolumeStatus() {
      return this.modifyVolumeStatus != null;
   }

   public PersistentVolumeClaimStatusFluent withNewModifyVolumeStatus(String status, String targetVolumeAttributesClassName) {
      return this.withModifyVolumeStatus(new ModifyVolumeStatus(status, targetVolumeAttributesClassName));
   }

   public ModifyVolumeStatusNested withNewModifyVolumeStatus() {
      return new ModifyVolumeStatusNested((ModifyVolumeStatus)null);
   }

   public ModifyVolumeStatusNested withNewModifyVolumeStatusLike(ModifyVolumeStatus item) {
      return new ModifyVolumeStatusNested(item);
   }

   public ModifyVolumeStatusNested editModifyVolumeStatus() {
      return this.withNewModifyVolumeStatusLike((ModifyVolumeStatus)Optional.ofNullable(this.buildModifyVolumeStatus()).orElse((Object)null));
   }

   public ModifyVolumeStatusNested editOrNewModifyVolumeStatus() {
      return this.withNewModifyVolumeStatusLike((ModifyVolumeStatus)Optional.ofNullable(this.buildModifyVolumeStatus()).orElse((new ModifyVolumeStatusBuilder()).build()));
   }

   public ModifyVolumeStatusNested editOrNewModifyVolumeStatusLike(ModifyVolumeStatus item) {
      return this.withNewModifyVolumeStatusLike((ModifyVolumeStatus)Optional.ofNullable(this.buildModifyVolumeStatus()).orElse(item));
   }

   public String getPhase() {
      return this.phase;
   }

   public PersistentVolumeClaimStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public PersistentVolumeClaimStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeClaimStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeClaimStatusFluent that = (PersistentVolumeClaimStatusFluent)o;
            if (!Objects.equals(this.accessModes, that.accessModes)) {
               return false;
            } else if (!Objects.equals(this.allocatedResourceStatuses, that.allocatedResourceStatuses)) {
               return false;
            } else if (!Objects.equals(this.allocatedResources, that.allocatedResources)) {
               return false;
            } else if (!Objects.equals(this.capacity, that.capacity)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.currentVolumeAttributesClassName, that.currentVolumeAttributesClassName)) {
               return false;
            } else if (!Objects.equals(this.modifyVolumeStatus, that.modifyVolumeStatus)) {
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
      return Objects.hash(new Object[]{this.accessModes, this.allocatedResourceStatuses, this.allocatedResources, this.capacity, this.conditions, this.currentVolumeAttributesClassName, this.modifyVolumeStatus, this.phase, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.accessModes != null && !this.accessModes.isEmpty()) {
         sb.append("accessModes:");
         sb.append(this.accessModes + ",");
      }

      if (this.allocatedResourceStatuses != null && !this.allocatedResourceStatuses.isEmpty()) {
         sb.append("allocatedResourceStatuses:");
         sb.append(this.allocatedResourceStatuses + ",");
      }

      if (this.allocatedResources != null && !this.allocatedResources.isEmpty()) {
         sb.append("allocatedResources:");
         sb.append(this.allocatedResources + ",");
      }

      if (this.capacity != null && !this.capacity.isEmpty()) {
         sb.append("capacity:");
         sb.append(this.capacity + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.currentVolumeAttributesClassName != null) {
         sb.append("currentVolumeAttributesClassName:");
         sb.append(this.currentVolumeAttributesClassName + ",");
      }

      if (this.modifyVolumeStatus != null) {
         sb.append("modifyVolumeStatus:");
         sb.append(this.modifyVolumeStatus + ",");
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

   public class ConditionsNested extends PersistentVolumeClaimConditionFluent implements Nested {
      PersistentVolumeClaimConditionBuilder builder;
      int index;

      ConditionsNested(int index, PersistentVolumeClaimCondition item) {
         this.index = index;
         this.builder = new PersistentVolumeClaimConditionBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class ModifyVolumeStatusNested extends ModifyVolumeStatusFluent implements Nested {
      ModifyVolumeStatusBuilder builder;

      ModifyVolumeStatusNested(ModifyVolumeStatus item) {
         this.builder = new ModifyVolumeStatusBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimStatusFluent.this.withModifyVolumeStatus(this.builder.build());
      }

      public Object endModifyVolumeStatus() {
         return this.and();
      }
   }
}
