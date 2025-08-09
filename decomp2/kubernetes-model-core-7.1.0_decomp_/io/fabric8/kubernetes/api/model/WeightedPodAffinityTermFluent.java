package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class WeightedPodAffinityTermFluent extends BaseFluent {
   private PodAffinityTermBuilder podAffinityTerm;
   private Integer weight;
   private Map additionalProperties;

   public WeightedPodAffinityTermFluent() {
   }

   public WeightedPodAffinityTermFluent(WeightedPodAffinityTerm instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(WeightedPodAffinityTerm instance) {
      instance = instance != null ? instance : new WeightedPodAffinityTerm();
      if (instance != null) {
         this.withPodAffinityTerm(instance.getPodAffinityTerm());
         this.withWeight(instance.getWeight());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodAffinityTerm buildPodAffinityTerm() {
      return this.podAffinityTerm != null ? this.podAffinityTerm.build() : null;
   }

   public WeightedPodAffinityTermFluent withPodAffinityTerm(PodAffinityTerm podAffinityTerm) {
      this._visitables.remove("podAffinityTerm");
      if (podAffinityTerm != null) {
         this.podAffinityTerm = new PodAffinityTermBuilder(podAffinityTerm);
         this._visitables.get("podAffinityTerm").add(this.podAffinityTerm);
      } else {
         this.podAffinityTerm = null;
         this._visitables.get("podAffinityTerm").remove(this.podAffinityTerm);
      }

      return this;
   }

   public boolean hasPodAffinityTerm() {
      return this.podAffinityTerm != null;
   }

   public PodAffinityTermNested withNewPodAffinityTerm() {
      return new PodAffinityTermNested((PodAffinityTerm)null);
   }

   public PodAffinityTermNested withNewPodAffinityTermLike(PodAffinityTerm item) {
      return new PodAffinityTermNested(item);
   }

   public PodAffinityTermNested editPodAffinityTerm() {
      return this.withNewPodAffinityTermLike((PodAffinityTerm)Optional.ofNullable(this.buildPodAffinityTerm()).orElse((Object)null));
   }

   public PodAffinityTermNested editOrNewPodAffinityTerm() {
      return this.withNewPodAffinityTermLike((PodAffinityTerm)Optional.ofNullable(this.buildPodAffinityTerm()).orElse((new PodAffinityTermBuilder()).build()));
   }

   public PodAffinityTermNested editOrNewPodAffinityTermLike(PodAffinityTerm item) {
      return this.withNewPodAffinityTermLike((PodAffinityTerm)Optional.ofNullable(this.buildPodAffinityTerm()).orElse(item));
   }

   public Integer getWeight() {
      return this.weight;
   }

   public WeightedPodAffinityTermFluent withWeight(Integer weight) {
      this.weight = weight;
      return this;
   }

   public boolean hasWeight() {
      return this.weight != null;
   }

   public WeightedPodAffinityTermFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public WeightedPodAffinityTermFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public WeightedPodAffinityTermFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public WeightedPodAffinityTermFluent removeFromAdditionalProperties(Map map) {
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

   public WeightedPodAffinityTermFluent withAdditionalProperties(Map additionalProperties) {
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
            WeightedPodAffinityTermFluent that = (WeightedPodAffinityTermFluent)o;
            if (!Objects.equals(this.podAffinityTerm, that.podAffinityTerm)) {
               return false;
            } else if (!Objects.equals(this.weight, that.weight)) {
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
      return Objects.hash(new Object[]{this.podAffinityTerm, this.weight, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.podAffinityTerm != null) {
         sb.append("podAffinityTerm:");
         sb.append(this.podAffinityTerm + ",");
      }

      if (this.weight != null) {
         sb.append("weight:");
         sb.append(this.weight + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PodAffinityTermNested extends PodAffinityTermFluent implements Nested {
      PodAffinityTermBuilder builder;

      PodAffinityTermNested(PodAffinityTerm item) {
         this.builder = new PodAffinityTermBuilder(this, item);
      }

      public Object and() {
         return WeightedPodAffinityTermFluent.this.withPodAffinityTerm(this.builder.build());
      }

      public Object endPodAffinityTerm() {
         return this.and();
      }
   }
}
