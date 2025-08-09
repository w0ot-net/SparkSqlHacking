package io.fabric8.kubernetes.api.model.autoscaling.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HorizontalPodAutoscalerSpecFluent extends BaseFluent {
   private Integer maxReplicas;
   private Integer minReplicas;
   private CrossVersionObjectReferenceBuilder scaleTargetRef;
   private Integer targetCPUUtilizationPercentage;
   private Map additionalProperties;

   public HorizontalPodAutoscalerSpecFluent() {
   }

   public HorizontalPodAutoscalerSpecFluent(HorizontalPodAutoscalerSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HorizontalPodAutoscalerSpec instance) {
      instance = instance != null ? instance : new HorizontalPodAutoscalerSpec();
      if (instance != null) {
         this.withMaxReplicas(instance.getMaxReplicas());
         this.withMinReplicas(instance.getMinReplicas());
         this.withScaleTargetRef(instance.getScaleTargetRef());
         this.withTargetCPUUtilizationPercentage(instance.getTargetCPUUtilizationPercentage());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMaxReplicas() {
      return this.maxReplicas;
   }

   public HorizontalPodAutoscalerSpecFluent withMaxReplicas(Integer maxReplicas) {
      this.maxReplicas = maxReplicas;
      return this;
   }

   public boolean hasMaxReplicas() {
      return this.maxReplicas != null;
   }

   public Integer getMinReplicas() {
      return this.minReplicas;
   }

   public HorizontalPodAutoscalerSpecFluent withMinReplicas(Integer minReplicas) {
      this.minReplicas = minReplicas;
      return this;
   }

   public boolean hasMinReplicas() {
      return this.minReplicas != null;
   }

   public CrossVersionObjectReference buildScaleTargetRef() {
      return this.scaleTargetRef != null ? this.scaleTargetRef.build() : null;
   }

   public HorizontalPodAutoscalerSpecFluent withScaleTargetRef(CrossVersionObjectReference scaleTargetRef) {
      this._visitables.remove("scaleTargetRef");
      if (scaleTargetRef != null) {
         this.scaleTargetRef = new CrossVersionObjectReferenceBuilder(scaleTargetRef);
         this._visitables.get("scaleTargetRef").add(this.scaleTargetRef);
      } else {
         this.scaleTargetRef = null;
         this._visitables.get("scaleTargetRef").remove(this.scaleTargetRef);
      }

      return this;
   }

   public boolean hasScaleTargetRef() {
      return this.scaleTargetRef != null;
   }

   public HorizontalPodAutoscalerSpecFluent withNewScaleTargetRef(String apiVersion, String kind, String name) {
      return this.withScaleTargetRef(new CrossVersionObjectReference(apiVersion, kind, name));
   }

   public ScaleTargetRefNested withNewScaleTargetRef() {
      return new ScaleTargetRefNested((CrossVersionObjectReference)null);
   }

   public ScaleTargetRefNested withNewScaleTargetRefLike(CrossVersionObjectReference item) {
      return new ScaleTargetRefNested(item);
   }

   public ScaleTargetRefNested editScaleTargetRef() {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse((Object)null));
   }

   public ScaleTargetRefNested editOrNewScaleTargetRef() {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse((new CrossVersionObjectReferenceBuilder()).build()));
   }

   public ScaleTargetRefNested editOrNewScaleTargetRefLike(CrossVersionObjectReference item) {
      return this.withNewScaleTargetRefLike((CrossVersionObjectReference)Optional.ofNullable(this.buildScaleTargetRef()).orElse(item));
   }

   public Integer getTargetCPUUtilizationPercentage() {
      return this.targetCPUUtilizationPercentage;
   }

   public HorizontalPodAutoscalerSpecFluent withTargetCPUUtilizationPercentage(Integer targetCPUUtilizationPercentage) {
      this.targetCPUUtilizationPercentage = targetCPUUtilizationPercentage;
      return this;
   }

   public boolean hasTargetCPUUtilizationPercentage() {
      return this.targetCPUUtilizationPercentage != null;
   }

   public HorizontalPodAutoscalerSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HorizontalPodAutoscalerSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerSpecFluent removeFromAdditionalProperties(Map map) {
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

   public HorizontalPodAutoscalerSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            HorizontalPodAutoscalerSpecFluent that = (HorizontalPodAutoscalerSpecFluent)o;
            if (!Objects.equals(this.maxReplicas, that.maxReplicas)) {
               return false;
            } else if (!Objects.equals(this.minReplicas, that.minReplicas)) {
               return false;
            } else if (!Objects.equals(this.scaleTargetRef, that.scaleTargetRef)) {
               return false;
            } else if (!Objects.equals(this.targetCPUUtilizationPercentage, that.targetCPUUtilizationPercentage)) {
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
      return Objects.hash(new Object[]{this.maxReplicas, this.minReplicas, this.scaleTargetRef, this.targetCPUUtilizationPercentage, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.maxReplicas != null) {
         sb.append("maxReplicas:");
         sb.append(this.maxReplicas + ",");
      }

      if (this.minReplicas != null) {
         sb.append("minReplicas:");
         sb.append(this.minReplicas + ",");
      }

      if (this.scaleTargetRef != null) {
         sb.append("scaleTargetRef:");
         sb.append(this.scaleTargetRef + ",");
      }

      if (this.targetCPUUtilizationPercentage != null) {
         sb.append("targetCPUUtilizationPercentage:");
         sb.append(this.targetCPUUtilizationPercentage + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ScaleTargetRefNested extends CrossVersionObjectReferenceFluent implements Nested {
      CrossVersionObjectReferenceBuilder builder;

      ScaleTargetRefNested(CrossVersionObjectReference item) {
         this.builder = new CrossVersionObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerSpecFluent.this.withScaleTargetRef(this.builder.build());
      }

      public Object endScaleTargetRef() {
         return this.and();
      }
   }
}
