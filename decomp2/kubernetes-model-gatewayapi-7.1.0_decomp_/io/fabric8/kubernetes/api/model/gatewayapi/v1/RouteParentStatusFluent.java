package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class RouteParentStatusFluent extends BaseFluent {
   private List conditions = new ArrayList();
   private String controllerName;
   private ParentReferenceBuilder parentRef;
   private Map additionalProperties;

   public RouteParentStatusFluent() {
   }

   public RouteParentStatusFluent(RouteParentStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RouteParentStatus instance) {
      instance = instance != null ? instance : new RouteParentStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withControllerName(instance.getControllerName());
         this.withParentRef(instance.getParentRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RouteParentStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public RouteParentStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public RouteParentStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public RouteParentStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public RouteParentStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public RouteParentStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public List getConditions() {
      return this.conditions;
   }

   public Condition getCondition(int index) {
      return (Condition)this.conditions.get(index);
   }

   public Condition getFirstCondition() {
      return (Condition)this.conditions.get(0);
   }

   public Condition getLastCondition() {
      return (Condition)this.conditions.get(this.conditions.size() - 1);
   }

   public Condition getMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RouteParentStatusFluent withConditions(List conditions) {
      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public RouteParentStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public String getControllerName() {
      return this.controllerName;
   }

   public RouteParentStatusFluent withControllerName(String controllerName) {
      this.controllerName = controllerName;
      return this;
   }

   public boolean hasControllerName() {
      return this.controllerName != null;
   }

   public ParentReference buildParentRef() {
      return this.parentRef != null ? this.parentRef.build() : null;
   }

   public RouteParentStatusFluent withParentRef(ParentReference parentRef) {
      this._visitables.remove("parentRef");
      if (parentRef != null) {
         this.parentRef = new ParentReferenceBuilder(parentRef);
         this._visitables.get("parentRef").add(this.parentRef);
      } else {
         this.parentRef = null;
         this._visitables.get("parentRef").remove(this.parentRef);
      }

      return this;
   }

   public boolean hasParentRef() {
      return this.parentRef != null;
   }

   public ParentRefNested withNewParentRef() {
      return new ParentRefNested((ParentReference)null);
   }

   public ParentRefNested withNewParentRefLike(ParentReference item) {
      return new ParentRefNested(item);
   }

   public ParentRefNested editParentRef() {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse((Object)null));
   }

   public ParentRefNested editOrNewParentRef() {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse((new ParentReferenceBuilder()).build()));
   }

   public ParentRefNested editOrNewParentRefLike(ParentReference item) {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse(item));
   }

   public RouteParentStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RouteParentStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RouteParentStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RouteParentStatusFluent removeFromAdditionalProperties(Map map) {
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

   public RouteParentStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            RouteParentStatusFluent that = (RouteParentStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.controllerName, that.controllerName)) {
               return false;
            } else if (!Objects.equals(this.parentRef, that.parentRef)) {
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
      return Objects.hash(new Object[]{this.conditions, this.controllerName, this.parentRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.controllerName != null) {
         sb.append("controllerName:");
         sb.append(this.controllerName + ",");
      }

      if (this.parentRef != null) {
         sb.append("parentRef:");
         sb.append(this.parentRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParentRefNested extends ParentReferenceFluent implements Nested {
      ParentReferenceBuilder builder;

      ParentRefNested(ParentReference item) {
         this.builder = new ParentReferenceBuilder(this, item);
      }

      public Object and() {
         return RouteParentStatusFluent.this.withParentRef(this.builder.build());
      }

      public Object endParentRef() {
         return this.and();
      }
   }
}
