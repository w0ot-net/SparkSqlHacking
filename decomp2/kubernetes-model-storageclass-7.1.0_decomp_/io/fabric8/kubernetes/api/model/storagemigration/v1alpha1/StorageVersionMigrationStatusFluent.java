package io.fabric8.kubernetes.api.model.storagemigration.v1alpha1;

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

public class StorageVersionMigrationStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private String resourceVersion;
   private Map additionalProperties;

   public StorageVersionMigrationStatusFluent() {
   }

   public StorageVersionMigrationStatusFluent(StorageVersionMigrationStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StorageVersionMigrationStatus instance) {
      instance = instance != null ? instance : new StorageVersionMigrationStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withResourceVersion(instance.getResourceVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public StorageVersionMigrationStatusFluent addToConditions(int index, MigrationCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent setToConditions(int index, MigrationCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent addToConditions(MigrationCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(MigrationCondition item : items) {
         MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(MigrationCondition item : items) {
         MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent removeFromConditions(MigrationCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(MigrationCondition item : items) {
            MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public StorageVersionMigrationStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(MigrationCondition item : items) {
            MigrationConditionBuilder builder = new MigrationConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public StorageVersionMigrationStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<MigrationConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            MigrationConditionBuilder builder = (MigrationConditionBuilder)each.next();
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

   public MigrationCondition buildCondition(int index) {
      return ((MigrationConditionBuilder)this.conditions.get(index)).build();
   }

   public MigrationCondition buildFirstCondition() {
      return ((MigrationConditionBuilder)this.conditions.get(0)).build();
   }

   public MigrationCondition buildLastCondition() {
      return ((MigrationConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public MigrationCondition buildMatchingCondition(Predicate predicate) {
      for(MigrationConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(MigrationConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StorageVersionMigrationStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(MigrationCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent withConditions(MigrationCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(MigrationCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public StorageVersionMigrationStatusFluent addNewCondition(String lastUpdateTime, String message, String reason, String status, String type) {
      return this.addToConditions(new MigrationCondition(lastUpdateTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (MigrationCondition)null);
   }

   public ConditionsNested addNewConditionLike(MigrationCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, MigrationCondition item) {
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
         if (predicate.test((MigrationConditionBuilder)this.conditions.get(i))) {
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

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public StorageVersionMigrationStatusFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public StorageVersionMigrationStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StorageVersionMigrationStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StorageVersionMigrationStatusFluent removeFromAdditionalProperties(Map map) {
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

   public StorageVersionMigrationStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            StorageVersionMigrationStatusFluent that = (StorageVersionMigrationStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
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
      return Objects.hash(new Object[]{this.conditions, this.resourceVersion, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends MigrationConditionFluent implements Nested {
      MigrationConditionBuilder builder;
      int index;

      ConditionsNested(int index, MigrationCondition item) {
         this.index = index;
         this.builder = new MigrationConditionBuilder(this, item);
      }

      public Object and() {
         return StorageVersionMigrationStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
