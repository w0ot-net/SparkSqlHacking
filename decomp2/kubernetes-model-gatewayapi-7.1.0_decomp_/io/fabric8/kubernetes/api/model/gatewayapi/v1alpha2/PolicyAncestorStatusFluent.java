package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Condition;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReference;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReferenceBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.ParentReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PolicyAncestorStatusFluent extends BaseFluent {
   private ParentReferenceBuilder ancestorRef;
   private List conditions = new ArrayList();
   private String controllerName;
   private Map additionalProperties;

   public PolicyAncestorStatusFluent() {
   }

   public PolicyAncestorStatusFluent(PolicyAncestorStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PolicyAncestorStatus instance) {
      instance = instance != null ? instance : new PolicyAncestorStatus();
      if (instance != null) {
         this.withAncestorRef(instance.getAncestorRef());
         this.withConditions(instance.getConditions());
         this.withControllerName(instance.getControllerName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ParentReference buildAncestorRef() {
      return this.ancestorRef != null ? this.ancestorRef.build() : null;
   }

   public PolicyAncestorStatusFluent withAncestorRef(ParentReference ancestorRef) {
      this._visitables.remove("ancestorRef");
      if (ancestorRef != null) {
         this.ancestorRef = new ParentReferenceBuilder(ancestorRef);
         this._visitables.get("ancestorRef").add(this.ancestorRef);
      } else {
         this.ancestorRef = null;
         this._visitables.get("ancestorRef").remove(this.ancestorRef);
      }

      return this;
   }

   public boolean hasAncestorRef() {
      return this.ancestorRef != null;
   }

   public AncestorRefNested withNewAncestorRef() {
      return new AncestorRefNested((ParentReference)null);
   }

   public AncestorRefNested withNewAncestorRefLike(ParentReference item) {
      return new AncestorRefNested(item);
   }

   public AncestorRefNested editAncestorRef() {
      return this.withNewAncestorRefLike((ParentReference)Optional.ofNullable(this.buildAncestorRef()).orElse((Object)null));
   }

   public AncestorRefNested editOrNewAncestorRef() {
      return this.withNewAncestorRefLike((ParentReference)Optional.ofNullable(this.buildAncestorRef()).orElse((new ParentReferenceBuilder()).build()));
   }

   public AncestorRefNested editOrNewAncestorRefLike(ParentReference item) {
      return this.withNewAncestorRefLike((ParentReference)Optional.ofNullable(this.buildAncestorRef()).orElse(item));
   }

   public PolicyAncestorStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public PolicyAncestorStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public PolicyAncestorStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public PolicyAncestorStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public PolicyAncestorStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public PolicyAncestorStatusFluent removeAllFromConditions(Collection items) {
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

   public PolicyAncestorStatusFluent withConditions(List conditions) {
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

   public PolicyAncestorStatusFluent withConditions(Condition... conditions) {
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

   public PolicyAncestorStatusFluent withControllerName(String controllerName) {
      this.controllerName = controllerName;
      return this;
   }

   public boolean hasControllerName() {
      return this.controllerName != null;
   }

   public PolicyAncestorStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PolicyAncestorStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PolicyAncestorStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PolicyAncestorStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PolicyAncestorStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PolicyAncestorStatusFluent that = (PolicyAncestorStatusFluent)o;
            if (!Objects.equals(this.ancestorRef, that.ancestorRef)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.controllerName, that.controllerName)) {
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
      return Objects.hash(new Object[]{this.ancestorRef, this.conditions, this.controllerName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ancestorRef != null) {
         sb.append("ancestorRef:");
         sb.append(this.ancestorRef + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.controllerName != null) {
         sb.append("controllerName:");
         sb.append(this.controllerName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AncestorRefNested extends ParentReferenceFluent implements Nested {
      ParentReferenceBuilder builder;

      AncestorRefNested(ParentReference item) {
         this.builder = new ParentReferenceBuilder(this, item);
      }

      public Object and() {
         return PolicyAncestorStatusFluent.this.withAncestorRef(this.builder.build());
      }

      public Object endAncestorRef() {
         return this.and();
      }
   }
}
