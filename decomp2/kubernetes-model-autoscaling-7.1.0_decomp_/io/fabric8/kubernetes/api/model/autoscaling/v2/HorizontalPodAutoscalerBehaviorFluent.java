package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HorizontalPodAutoscalerBehaviorFluent extends BaseFluent {
   private HPAScalingRulesBuilder scaleDown;
   private HPAScalingRulesBuilder scaleUp;
   private Map additionalProperties;

   public HorizontalPodAutoscalerBehaviorFluent() {
   }

   public HorizontalPodAutoscalerBehaviorFluent(HorizontalPodAutoscalerBehavior instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HorizontalPodAutoscalerBehavior instance) {
      instance = instance != null ? instance : new HorizontalPodAutoscalerBehavior();
      if (instance != null) {
         this.withScaleDown(instance.getScaleDown());
         this.withScaleUp(instance.getScaleUp());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HPAScalingRules buildScaleDown() {
      return this.scaleDown != null ? this.scaleDown.build() : null;
   }

   public HorizontalPodAutoscalerBehaviorFluent withScaleDown(HPAScalingRules scaleDown) {
      this._visitables.remove("scaleDown");
      if (scaleDown != null) {
         this.scaleDown = new HPAScalingRulesBuilder(scaleDown);
         this._visitables.get("scaleDown").add(this.scaleDown);
      } else {
         this.scaleDown = null;
         this._visitables.get("scaleDown").remove(this.scaleDown);
      }

      return this;
   }

   public boolean hasScaleDown() {
      return this.scaleDown != null;
   }

   public ScaleDownNested withNewScaleDown() {
      return new ScaleDownNested((HPAScalingRules)null);
   }

   public ScaleDownNested withNewScaleDownLike(HPAScalingRules item) {
      return new ScaleDownNested(item);
   }

   public ScaleDownNested editScaleDown() {
      return this.withNewScaleDownLike((HPAScalingRules)Optional.ofNullable(this.buildScaleDown()).orElse((Object)null));
   }

   public ScaleDownNested editOrNewScaleDown() {
      return this.withNewScaleDownLike((HPAScalingRules)Optional.ofNullable(this.buildScaleDown()).orElse((new HPAScalingRulesBuilder()).build()));
   }

   public ScaleDownNested editOrNewScaleDownLike(HPAScalingRules item) {
      return this.withNewScaleDownLike((HPAScalingRules)Optional.ofNullable(this.buildScaleDown()).orElse(item));
   }

   public HPAScalingRules buildScaleUp() {
      return this.scaleUp != null ? this.scaleUp.build() : null;
   }

   public HorizontalPodAutoscalerBehaviorFluent withScaleUp(HPAScalingRules scaleUp) {
      this._visitables.remove("scaleUp");
      if (scaleUp != null) {
         this.scaleUp = new HPAScalingRulesBuilder(scaleUp);
         this._visitables.get("scaleUp").add(this.scaleUp);
      } else {
         this.scaleUp = null;
         this._visitables.get("scaleUp").remove(this.scaleUp);
      }

      return this;
   }

   public boolean hasScaleUp() {
      return this.scaleUp != null;
   }

   public ScaleUpNested withNewScaleUp() {
      return new ScaleUpNested((HPAScalingRules)null);
   }

   public ScaleUpNested withNewScaleUpLike(HPAScalingRules item) {
      return new ScaleUpNested(item);
   }

   public ScaleUpNested editScaleUp() {
      return this.withNewScaleUpLike((HPAScalingRules)Optional.ofNullable(this.buildScaleUp()).orElse((Object)null));
   }

   public ScaleUpNested editOrNewScaleUp() {
      return this.withNewScaleUpLike((HPAScalingRules)Optional.ofNullable(this.buildScaleUp()).orElse((new HPAScalingRulesBuilder()).build()));
   }

   public ScaleUpNested editOrNewScaleUpLike(HPAScalingRules item) {
      return this.withNewScaleUpLike((HPAScalingRules)Optional.ofNullable(this.buildScaleUp()).orElse(item));
   }

   public HorizontalPodAutoscalerBehaviorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HorizontalPodAutoscalerBehaviorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HorizontalPodAutoscalerBehaviorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HorizontalPodAutoscalerBehaviorFluent removeFromAdditionalProperties(Map map) {
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

   public HorizontalPodAutoscalerBehaviorFluent withAdditionalProperties(Map additionalProperties) {
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
            HorizontalPodAutoscalerBehaviorFluent that = (HorizontalPodAutoscalerBehaviorFluent)o;
            if (!Objects.equals(this.scaleDown, that.scaleDown)) {
               return false;
            } else if (!Objects.equals(this.scaleUp, that.scaleUp)) {
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
      return Objects.hash(new Object[]{this.scaleDown, this.scaleUp, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.scaleDown != null) {
         sb.append("scaleDown:");
         sb.append(this.scaleDown + ",");
      }

      if (this.scaleUp != null) {
         sb.append("scaleUp:");
         sb.append(this.scaleUp + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ScaleDownNested extends HPAScalingRulesFluent implements Nested {
      HPAScalingRulesBuilder builder;

      ScaleDownNested(HPAScalingRules item) {
         this.builder = new HPAScalingRulesBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerBehaviorFluent.this.withScaleDown(this.builder.build());
      }

      public Object endScaleDown() {
         return this.and();
      }
   }

   public class ScaleUpNested extends HPAScalingRulesFluent implements Nested {
      HPAScalingRulesBuilder builder;

      ScaleUpNested(HPAScalingRules item) {
         this.builder = new HPAScalingRulesBuilder(this, item);
      }

      public Object and() {
         return HorizontalPodAutoscalerBehaviorFluent.this.withScaleUp(this.builder.build());
      }

      public Object endScaleUp() {
         return this.and();
      }
   }
}
