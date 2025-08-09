package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

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

public class ValidatingAdmissionPolicyStatusFluent extends BaseFluent {
   private List conditions = new ArrayList();
   private Long observedGeneration;
   private TypeCheckingBuilder typeChecking;
   private Map additionalProperties;

   public ValidatingAdmissionPolicyStatusFluent() {
   }

   public ValidatingAdmissionPolicyStatusFluent(ValidatingAdmissionPolicyStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ValidatingAdmissionPolicyStatus instance) {
      instance = instance != null ? instance : new ValidatingAdmissionPolicyStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withTypeChecking(instance.getTypeChecking());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ValidatingAdmissionPolicyStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicyStatusFluent removeAllFromConditions(Collection items) {
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

   public ValidatingAdmissionPolicyStatusFluent withConditions(List conditions) {
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

   public ValidatingAdmissionPolicyStatusFluent withConditions(Condition... conditions) {
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

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public ValidatingAdmissionPolicyStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public TypeChecking buildTypeChecking() {
      return this.typeChecking != null ? this.typeChecking.build() : null;
   }

   public ValidatingAdmissionPolicyStatusFluent withTypeChecking(TypeChecking typeChecking) {
      this._visitables.remove("typeChecking");
      if (typeChecking != null) {
         this.typeChecking = new TypeCheckingBuilder(typeChecking);
         this._visitables.get("typeChecking").add(this.typeChecking);
      } else {
         this.typeChecking = null;
         this._visitables.get("typeChecking").remove(this.typeChecking);
      }

      return this;
   }

   public boolean hasTypeChecking() {
      return this.typeChecking != null;
   }

   public TypeCheckingNested withNewTypeChecking() {
      return new TypeCheckingNested((TypeChecking)null);
   }

   public TypeCheckingNested withNewTypeCheckingLike(TypeChecking item) {
      return new TypeCheckingNested(item);
   }

   public TypeCheckingNested editTypeChecking() {
      return this.withNewTypeCheckingLike((TypeChecking)Optional.ofNullable(this.buildTypeChecking()).orElse((Object)null));
   }

   public TypeCheckingNested editOrNewTypeChecking() {
      return this.withNewTypeCheckingLike((TypeChecking)Optional.ofNullable(this.buildTypeChecking()).orElse((new TypeCheckingBuilder()).build()));
   }

   public TypeCheckingNested editOrNewTypeCheckingLike(TypeChecking item) {
      return this.withNewTypeCheckingLike((TypeChecking)Optional.ofNullable(this.buildTypeChecking()).orElse(item));
   }

   public ValidatingAdmissionPolicyStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidatingAdmissionPolicyStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicyStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ValidatingAdmissionPolicyStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidatingAdmissionPolicyStatusFluent that = (ValidatingAdmissionPolicyStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.typeChecking, that.typeChecking)) {
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
      return Objects.hash(new Object[]{this.conditions, this.observedGeneration, this.typeChecking, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.typeChecking != null) {
         sb.append("typeChecking:");
         sb.append(this.typeChecking + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class TypeCheckingNested extends TypeCheckingFluent implements Nested {
      TypeCheckingBuilder builder;

      TypeCheckingNested(TypeChecking item) {
         this.builder = new TypeCheckingBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicyStatusFluent.this.withTypeChecking(this.builder.build());
      }

      public Object endTypeChecking() {
         return this.and();
      }
   }
}
