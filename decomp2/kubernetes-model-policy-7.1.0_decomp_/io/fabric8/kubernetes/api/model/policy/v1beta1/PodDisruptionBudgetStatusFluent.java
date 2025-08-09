package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class PodDisruptionBudgetStatusFluent extends BaseFluent {
   private List conditions = new ArrayList();
   private Integer currentHealthy;
   private Integer desiredHealthy;
   private Map disruptedPods;
   private Integer disruptionsAllowed;
   private Integer expectedPods;
   private Long observedGeneration;
   private Map additionalProperties;

   public PodDisruptionBudgetStatusFluent() {
   }

   public PodDisruptionBudgetStatusFluent(PodDisruptionBudgetStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodDisruptionBudgetStatus instance) {
      instance = instance != null ? instance : new PodDisruptionBudgetStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withCurrentHealthy(instance.getCurrentHealthy());
         this.withDesiredHealthy(instance.getDesiredHealthy());
         this.withDisruptedPods(instance.getDisruptedPods());
         this.withDisruptionsAllowed(instance.getDisruptionsAllowed());
         this.withExpectedPods(instance.getExpectedPods());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodDisruptionBudgetStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public PodDisruptionBudgetStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public PodDisruptionBudgetStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public PodDisruptionBudgetStatusFluent removeAllFromConditions(Collection items) {
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

   public PodDisruptionBudgetStatusFluent withConditions(List conditions) {
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

   public PodDisruptionBudgetStatusFluent withConditions(Condition... conditions) {
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

   public Integer getCurrentHealthy() {
      return this.currentHealthy;
   }

   public PodDisruptionBudgetStatusFluent withCurrentHealthy(Integer currentHealthy) {
      this.currentHealthy = currentHealthy;
      return this;
   }

   public boolean hasCurrentHealthy() {
      return this.currentHealthy != null;
   }

   public Integer getDesiredHealthy() {
      return this.desiredHealthy;
   }

   public PodDisruptionBudgetStatusFluent withDesiredHealthy(Integer desiredHealthy) {
      this.desiredHealthy = desiredHealthy;
      return this;
   }

   public boolean hasDesiredHealthy() {
      return this.desiredHealthy != null;
   }

   public PodDisruptionBudgetStatusFluent addToDisruptedPods(String key, String value) {
      if (this.disruptedPods == null && key != null && value != null) {
         this.disruptedPods = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.disruptedPods.put(key, value);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent addToDisruptedPods(Map map) {
      if (this.disruptedPods == null && map != null) {
         this.disruptedPods = new LinkedHashMap();
      }

      if (map != null) {
         this.disruptedPods.putAll(map);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent removeFromDisruptedPods(String key) {
      if (this.disruptedPods == null) {
         return this;
      } else {
         if (key != null && this.disruptedPods != null) {
            this.disruptedPods.remove(key);
         }

         return this;
      }
   }

   public PodDisruptionBudgetStatusFluent removeFromDisruptedPods(Map map) {
      if (this.disruptedPods == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.disruptedPods != null) {
                  this.disruptedPods.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getDisruptedPods() {
      return this.disruptedPods;
   }

   public PodDisruptionBudgetStatusFluent withDisruptedPods(Map disruptedPods) {
      if (disruptedPods == null) {
         this.disruptedPods = null;
      } else {
         this.disruptedPods = new LinkedHashMap(disruptedPods);
      }

      return this;
   }

   public boolean hasDisruptedPods() {
      return this.disruptedPods != null;
   }

   public Integer getDisruptionsAllowed() {
      return this.disruptionsAllowed;
   }

   public PodDisruptionBudgetStatusFluent withDisruptionsAllowed(Integer disruptionsAllowed) {
      this.disruptionsAllowed = disruptionsAllowed;
      return this;
   }

   public boolean hasDisruptionsAllowed() {
      return this.disruptionsAllowed != null;
   }

   public Integer getExpectedPods() {
      return this.expectedPods;
   }

   public PodDisruptionBudgetStatusFluent withExpectedPods(Integer expectedPods) {
      this.expectedPods = expectedPods;
      return this;
   }

   public boolean hasExpectedPods() {
      return this.expectedPods != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public PodDisruptionBudgetStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public PodDisruptionBudgetStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodDisruptionBudgetStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodDisruptionBudgetStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PodDisruptionBudgetStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PodDisruptionBudgetStatusFluent that = (PodDisruptionBudgetStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.currentHealthy, that.currentHealthy)) {
               return false;
            } else if (!Objects.equals(this.desiredHealthy, that.desiredHealthy)) {
               return false;
            } else if (!Objects.equals(this.disruptedPods, that.disruptedPods)) {
               return false;
            } else if (!Objects.equals(this.disruptionsAllowed, that.disruptionsAllowed)) {
               return false;
            } else if (!Objects.equals(this.expectedPods, that.expectedPods)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
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
      return Objects.hash(new Object[]{this.conditions, this.currentHealthy, this.desiredHealthy, this.disruptedPods, this.disruptionsAllowed, this.expectedPods, this.observedGeneration, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.currentHealthy != null) {
         sb.append("currentHealthy:");
         sb.append(this.currentHealthy + ",");
      }

      if (this.desiredHealthy != null) {
         sb.append("desiredHealthy:");
         sb.append(this.desiredHealthy + ",");
      }

      if (this.disruptedPods != null && !this.disruptedPods.isEmpty()) {
         sb.append("disruptedPods:");
         sb.append(this.disruptedPods + ",");
      }

      if (this.disruptionsAllowed != null) {
         sb.append("disruptionsAllowed:");
         sb.append(this.disruptionsAllowed + ",");
      }

      if (this.expectedPods != null) {
         sb.append("expectedPods:");
         sb.append(this.expectedPods + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
