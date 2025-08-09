package io.fabric8.kubernetes.api.model.autoscaling.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HorizontalPodAutoscalerStatusFluent extends BaseFluent {
   private Integer currentCPUUtilizationPercentage;
   private Integer currentReplicas;
   private Integer desiredReplicas;
   private String lastScaleTime;
   private Long observedGeneration;
   private Map additionalProperties;

   public HorizontalPodAutoscalerStatusFluent() {
   }

   public HorizontalPodAutoscalerStatusFluent(HorizontalPodAutoscalerStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HorizontalPodAutoscalerStatus instance) {
      instance = instance != null ? instance : new HorizontalPodAutoscalerStatus();
      if (instance != null) {
         this.withCurrentCPUUtilizationPercentage(instance.getCurrentCPUUtilizationPercentage());
         this.withCurrentReplicas(instance.getCurrentReplicas());
         this.withDesiredReplicas(instance.getDesiredReplicas());
         this.withLastScaleTime(instance.getLastScaleTime());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getCurrentCPUUtilizationPercentage() {
      return this.currentCPUUtilizationPercentage;
   }

   public HorizontalPodAutoscalerStatusFluent withCurrentCPUUtilizationPercentage(Integer currentCPUUtilizationPercentage) {
      this.currentCPUUtilizationPercentage = currentCPUUtilizationPercentage;
      return this;
   }

   public boolean hasCurrentCPUUtilizationPercentage() {
      return this.currentCPUUtilizationPercentage != null;
   }

   public Integer getCurrentReplicas() {
      return this.currentReplicas;
   }

   public HorizontalPodAutoscalerStatusFluent withCurrentReplicas(Integer currentReplicas) {
      this.currentReplicas = currentReplicas;
      return this;
   }

   public boolean hasCurrentReplicas() {
      return this.currentReplicas != null;
   }

   public Integer getDesiredReplicas() {
      return this.desiredReplicas;
   }

   public HorizontalPodAutoscalerStatusFluent withDesiredReplicas(Integer desiredReplicas) {
      this.desiredReplicas = desiredReplicas;
      return this;
   }

   public boolean hasDesiredReplicas() {
      return this.desiredReplicas != null;
   }

   public String getLastScaleTime() {
      return this.lastScaleTime;
   }

   public HorizontalPodAutoscalerStatusFluent withLastScaleTime(String lastScaleTime) {
      this.lastScaleTime = lastScaleTime;
      return this;
   }

   public boolean hasLastScaleTime() {
      return this.lastScaleTime != null;
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public HorizontalPodAutoscalerStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public HorizontalPodAutoscalerStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HorizontalPodAutoscalerStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerStatusFluent removeFromAdditionalProperties(Map map) {
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

   public HorizontalPodAutoscalerStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            HorizontalPodAutoscalerStatusFluent that = (HorizontalPodAutoscalerStatusFluent)o;
            if (!Objects.equals(this.currentCPUUtilizationPercentage, that.currentCPUUtilizationPercentage)) {
               return false;
            } else if (!Objects.equals(this.currentReplicas, that.currentReplicas)) {
               return false;
            } else if (!Objects.equals(this.desiredReplicas, that.desiredReplicas)) {
               return false;
            } else if (!Objects.equals(this.lastScaleTime, that.lastScaleTime)) {
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
      return Objects.hash(new Object[]{this.currentCPUUtilizationPercentage, this.currentReplicas, this.desiredReplicas, this.lastScaleTime, this.observedGeneration, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.currentCPUUtilizationPercentage != null) {
         sb.append("currentCPUUtilizationPercentage:");
         sb.append(this.currentCPUUtilizationPercentage + ",");
      }

      if (this.currentReplicas != null) {
         sb.append("currentReplicas:");
         sb.append(this.currentReplicas + ",");
      }

      if (this.desiredReplicas != null) {
         sb.append("desiredReplicas:");
         sb.append(this.desiredReplicas + ",");
      }

      if (this.lastScaleTime != null) {
         sb.append("lastScaleTime:");
         sb.append(this.lastScaleTime + ",");
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
