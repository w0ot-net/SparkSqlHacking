package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ExternalMetricSourceFluent extends BaseFluent {
   private MetricIdentifierBuilder metric;
   private MetricTargetBuilder target;
   private Map additionalProperties;

   public ExternalMetricSourceFluent() {
   }

   public ExternalMetricSourceFluent(ExternalMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExternalMetricSource instance) {
      instance = instance != null ? instance : new ExternalMetricSource();
      if (instance != null) {
         this.withMetric(instance.getMetric());
         this.withTarget(instance.getTarget());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MetricIdentifier buildMetric() {
      return this.metric != null ? this.metric.build() : null;
   }

   public ExternalMetricSourceFluent withMetric(MetricIdentifier metric) {
      this._visitables.remove("metric");
      if (metric != null) {
         this.metric = new MetricIdentifierBuilder(metric);
         this._visitables.get("metric").add(this.metric);
      } else {
         this.metric = null;
         this._visitables.get("metric").remove(this.metric);
      }

      return this;
   }

   public boolean hasMetric() {
      return this.metric != null;
   }

   public MetricNested withNewMetric() {
      return new MetricNested((MetricIdentifier)null);
   }

   public MetricNested withNewMetricLike(MetricIdentifier item) {
      return new MetricNested(item);
   }

   public MetricNested editMetric() {
      return this.withNewMetricLike((MetricIdentifier)Optional.ofNullable(this.buildMetric()).orElse((Object)null));
   }

   public MetricNested editOrNewMetric() {
      return this.withNewMetricLike((MetricIdentifier)Optional.ofNullable(this.buildMetric()).orElse((new MetricIdentifierBuilder()).build()));
   }

   public MetricNested editOrNewMetricLike(MetricIdentifier item) {
      return this.withNewMetricLike((MetricIdentifier)Optional.ofNullable(this.buildMetric()).orElse(item));
   }

   public MetricTarget buildTarget() {
      return this.target != null ? this.target.build() : null;
   }

   public ExternalMetricSourceFluent withTarget(MetricTarget target) {
      this._visitables.remove("target");
      if (target != null) {
         this.target = new MetricTargetBuilder(target);
         this._visitables.get("target").add(this.target);
      } else {
         this.target = null;
         this._visitables.get("target").remove(this.target);
      }

      return this;
   }

   public boolean hasTarget() {
      return this.target != null;
   }

   public TargetNested withNewTarget() {
      return new TargetNested((MetricTarget)null);
   }

   public TargetNested withNewTargetLike(MetricTarget item) {
      return new TargetNested(item);
   }

   public TargetNested editTarget() {
      return this.withNewTargetLike((MetricTarget)Optional.ofNullable(this.buildTarget()).orElse((Object)null));
   }

   public TargetNested editOrNewTarget() {
      return this.withNewTargetLike((MetricTarget)Optional.ofNullable(this.buildTarget()).orElse((new MetricTargetBuilder()).build()));
   }

   public TargetNested editOrNewTargetLike(MetricTarget item) {
      return this.withNewTargetLike((MetricTarget)Optional.ofNullable(this.buildTarget()).orElse(item));
   }

   public ExternalMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExternalMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExternalMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExternalMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ExternalMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ExternalMetricSourceFluent that = (ExternalMetricSourceFluent)o;
            if (!Objects.equals(this.metric, that.metric)) {
               return false;
            } else if (!Objects.equals(this.target, that.target)) {
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
      return Objects.hash(new Object[]{this.metric, this.target, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.metric != null) {
         sb.append("metric:");
         sb.append(this.metric + ",");
      }

      if (this.target != null) {
         sb.append("target:");
         sb.append(this.target + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetricNested extends MetricIdentifierFluent implements Nested {
      MetricIdentifierBuilder builder;

      MetricNested(MetricIdentifier item) {
         this.builder = new MetricIdentifierBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricSourceFluent.this.withMetric(this.builder.build());
      }

      public Object endMetric() {
         return this.and();
      }
   }

   public class TargetNested extends MetricTargetFluent implements Nested {
      MetricTargetBuilder builder;

      TargetNested(MetricTarget item) {
         this.builder = new MetricTargetBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricSourceFluent.this.withTarget(this.builder.build());
      }

      public Object endTarget() {
         return this.and();
      }
   }
}
