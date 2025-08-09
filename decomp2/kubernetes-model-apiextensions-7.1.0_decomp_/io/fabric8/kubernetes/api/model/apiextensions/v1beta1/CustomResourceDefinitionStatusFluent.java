package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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

public class CustomResourceDefinitionStatusFluent extends BaseFluent {
   private CustomResourceDefinitionNamesBuilder acceptedNames;
   private ArrayList conditions = new ArrayList();
   private List storedVersions = new ArrayList();
   private Map additionalProperties;

   public CustomResourceDefinitionStatusFluent() {
   }

   public CustomResourceDefinitionStatusFluent(CustomResourceDefinitionStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceDefinitionStatus instance) {
      instance = instance != null ? instance : new CustomResourceDefinitionStatus();
      if (instance != null) {
         this.withAcceptedNames(instance.getAcceptedNames());
         this.withConditions(instance.getConditions());
         this.withStoredVersions(instance.getStoredVersions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceDefinitionNames buildAcceptedNames() {
      return this.acceptedNames != null ? this.acceptedNames.build() : null;
   }

   public CustomResourceDefinitionStatusFluent withAcceptedNames(CustomResourceDefinitionNames acceptedNames) {
      this._visitables.remove("acceptedNames");
      if (acceptedNames != null) {
         this.acceptedNames = new CustomResourceDefinitionNamesBuilder(acceptedNames);
         this._visitables.get("acceptedNames").add(this.acceptedNames);
      } else {
         this.acceptedNames = null;
         this._visitables.get("acceptedNames").remove(this.acceptedNames);
      }

      return this;
   }

   public boolean hasAcceptedNames() {
      return this.acceptedNames != null;
   }

   public AcceptedNamesNested withNewAcceptedNames() {
      return new AcceptedNamesNested((CustomResourceDefinitionNames)null);
   }

   public AcceptedNamesNested withNewAcceptedNamesLike(CustomResourceDefinitionNames item) {
      return new AcceptedNamesNested(item);
   }

   public AcceptedNamesNested editAcceptedNames() {
      return this.withNewAcceptedNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildAcceptedNames()).orElse((Object)null));
   }

   public AcceptedNamesNested editOrNewAcceptedNames() {
      return this.withNewAcceptedNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildAcceptedNames()).orElse((new CustomResourceDefinitionNamesBuilder()).build()));
   }

   public AcceptedNamesNested editOrNewAcceptedNamesLike(CustomResourceDefinitionNames item) {
      return this.withNewAcceptedNamesLike((CustomResourceDefinitionNames)Optional.ofNullable(this.buildAcceptedNames()).orElse(item));
   }

   public CustomResourceDefinitionStatusFluent addToConditions(int index, CustomResourceDefinitionCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent setToConditions(int index, CustomResourceDefinitionCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent addToConditions(CustomResourceDefinitionCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(CustomResourceDefinitionCondition item : items) {
         CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(CustomResourceDefinitionCondition item : items) {
         CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent removeFromConditions(CustomResourceDefinitionCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(CustomResourceDefinitionCondition item : items) {
            CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(CustomResourceDefinitionCondition item : items) {
            CustomResourceDefinitionConditionBuilder builder = new CustomResourceDefinitionConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public CustomResourceDefinitionStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<CustomResourceDefinitionConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            CustomResourceDefinitionConditionBuilder builder = (CustomResourceDefinitionConditionBuilder)each.next();
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

   public CustomResourceDefinitionCondition buildCondition(int index) {
      return ((CustomResourceDefinitionConditionBuilder)this.conditions.get(index)).build();
   }

   public CustomResourceDefinitionCondition buildFirstCondition() {
      return ((CustomResourceDefinitionConditionBuilder)this.conditions.get(0)).build();
   }

   public CustomResourceDefinitionCondition buildLastCondition() {
      return ((CustomResourceDefinitionConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public CustomResourceDefinitionCondition buildMatchingCondition(Predicate predicate) {
      for(CustomResourceDefinitionConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(CustomResourceDefinitionConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(CustomResourceDefinitionCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent withConditions(CustomResourceDefinitionCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(CustomResourceDefinitionCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public CustomResourceDefinitionStatusFluent addNewCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      return this.addToConditions(new CustomResourceDefinitionCondition(lastTransitionTime, message, reason, status, type));
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (CustomResourceDefinitionCondition)null);
   }

   public ConditionsNested addNewConditionLike(CustomResourceDefinitionCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, CustomResourceDefinitionCondition item) {
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
         if (predicate.test((CustomResourceDefinitionConditionBuilder)this.conditions.get(i))) {
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

   public CustomResourceDefinitionStatusFluent addToStoredVersions(int index, String item) {
      if (this.storedVersions == null) {
         this.storedVersions = new ArrayList();
      }

      this.storedVersions.add(index, item);
      return this;
   }

   public CustomResourceDefinitionStatusFluent setToStoredVersions(int index, String item) {
      if (this.storedVersions == null) {
         this.storedVersions = new ArrayList();
      }

      this.storedVersions.set(index, item);
      return this;
   }

   public CustomResourceDefinitionStatusFluent addToStoredVersions(String... items) {
      if (this.storedVersions == null) {
         this.storedVersions = new ArrayList();
      }

      for(String item : items) {
         this.storedVersions.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent addAllToStoredVersions(Collection items) {
      if (this.storedVersions == null) {
         this.storedVersions = new ArrayList();
      }

      for(String item : items) {
         this.storedVersions.add(item);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent removeFromStoredVersions(String... items) {
      if (this.storedVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.storedVersions.remove(item);
         }

         return this;
      }
   }

   public CustomResourceDefinitionStatusFluent removeAllFromStoredVersions(Collection items) {
      if (this.storedVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.storedVersions.remove(item);
         }

         return this;
      }
   }

   public List getStoredVersions() {
      return this.storedVersions;
   }

   public String getStoredVersion(int index) {
      return (String)this.storedVersions.get(index);
   }

   public String getFirstStoredVersion() {
      return (String)this.storedVersions.get(0);
   }

   public String getLastStoredVersion() {
      return (String)this.storedVersions.get(this.storedVersions.size() - 1);
   }

   public String getMatchingStoredVersion(Predicate predicate) {
      for(String item : this.storedVersions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingStoredVersion(Predicate predicate) {
      for(String item : this.storedVersions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CustomResourceDefinitionStatusFluent withStoredVersions(List storedVersions) {
      if (storedVersions != null) {
         this.storedVersions = new ArrayList();

         for(String item : storedVersions) {
            this.addToStoredVersions(item);
         }
      } else {
         this.storedVersions = null;
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent withStoredVersions(String... storedVersions) {
      if (this.storedVersions != null) {
         this.storedVersions.clear();
         this._visitables.remove("storedVersions");
      }

      if (storedVersions != null) {
         for(String item : storedVersions) {
            this.addToStoredVersions(item);
         }
      }

      return this;
   }

   public boolean hasStoredVersions() {
      return this.storedVersions != null && !this.storedVersions.isEmpty();
   }

   public CustomResourceDefinitionStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceDefinitionStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceDefinitionStatusFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceDefinitionStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceDefinitionStatusFluent that = (CustomResourceDefinitionStatusFluent)o;
            if (!Objects.equals(this.acceptedNames, that.acceptedNames)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.storedVersions, that.storedVersions)) {
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
      return Objects.hash(new Object[]{this.acceptedNames, this.conditions, this.storedVersions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.acceptedNames != null) {
         sb.append("acceptedNames:");
         sb.append(this.acceptedNames + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.storedVersions != null && !this.storedVersions.isEmpty()) {
         sb.append("storedVersions:");
         sb.append(this.storedVersions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AcceptedNamesNested extends CustomResourceDefinitionNamesFluent implements Nested {
      CustomResourceDefinitionNamesBuilder builder;

      AcceptedNamesNested(CustomResourceDefinitionNames item) {
         this.builder = new CustomResourceDefinitionNamesBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionStatusFluent.this.withAcceptedNames(this.builder.build());
      }

      public Object endAcceptedNames() {
         return this.and();
      }
   }

   public class ConditionsNested extends CustomResourceDefinitionConditionFluent implements Nested {
      CustomResourceDefinitionConditionBuilder builder;
      int index;

      ConditionsNested(int index, CustomResourceDefinitionCondition item) {
         this.index = index;
         this.builder = new CustomResourceDefinitionConditionBuilder(this, item);
      }

      public Object and() {
         return CustomResourceDefinitionStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
