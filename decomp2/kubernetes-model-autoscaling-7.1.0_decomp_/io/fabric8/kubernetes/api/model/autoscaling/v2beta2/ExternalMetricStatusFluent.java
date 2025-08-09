package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ExternalMetricStatusFluent extends BaseFluent {
   private MetricValueStatusBuilder current;
   private MetricIdentifierBuilder metric;
   private Map additionalProperties;

   public ExternalMetricStatusFluent() {
   }

   public ExternalMetricStatusFluent(ExternalMetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExternalMetricStatus instance) {
      instance = instance != null ? instance : new ExternalMetricStatus();
      if (instance != null) {
         this.withCurrent(instance.getCurrent());
         this.withMetric(instance.getMetric());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MetricValueStatus buildCurrent() {
      return this.current != null ? this.current.build() : null;
   }

   public ExternalMetricStatusFluent withCurrent(MetricValueStatus current) {
      this._visitables.remove("current");
      if (current != null) {
         this.current = new MetricValueStatusBuilder(current);
         this._visitables.get("current").add(this.current);
      } else {
         this.current = null;
         this._visitables.get("current").remove(this.current);
      }

      return this;
   }

   public boolean hasCurrent() {
      return this.current != null;
   }

   public CurrentNested withNewCurrent() {
      return new CurrentNested((MetricValueStatus)null);
   }

   public CurrentNested withNewCurrentLike(MetricValueStatus item) {
      return new CurrentNested(item);
   }

   public CurrentNested editCurrent() {
      return this.withNewCurrentLike((MetricValueStatus)Optional.ofNullable(this.buildCurrent()).orElse((Object)null));
   }

   public CurrentNested editOrNewCurrent() {
      return this.withNewCurrentLike((MetricValueStatus)Optional.ofNullable(this.buildCurrent()).orElse((new MetricValueStatusBuilder()).build()));
   }

   public CurrentNested editOrNewCurrentLike(MetricValueStatus item) {
      return this.withNewCurrentLike((MetricValueStatus)Optional.ofNullable(this.buildCurrent()).orElse(item));
   }

   public MetricIdentifier buildMetric() {
      return this.metric != null ? this.metric.build() : null;
   }

   public ExternalMetricStatusFluent withMetric(MetricIdentifier metric) {
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

   public ExternalMetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExternalMetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExternalMetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExternalMetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ExternalMetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ExternalMetricStatusFluent that = (ExternalMetricStatusFluent)o;
            if (!Objects.equals(this.current, that.current)) {
               return false;
            } else if (!Objects.equals(this.metric, that.metric)) {
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
      return Objects.hash(new Object[]{this.current, this.metric, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.current != null) {
         sb.append("current:");
         sb.append(this.current + ",");
      }

      if (this.metric != null) {
         sb.append("metric:");
         sb.append(this.metric + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CurrentNested extends MetricValueStatusFluent implements Nested {
      MetricValueStatusBuilder builder;

      CurrentNested(MetricValueStatus item) {
         this.builder = new MetricValueStatusBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricStatusFluent.this.withCurrent(this.builder.build());
      }

      public Object endCurrent() {
         return this.and();
      }
   }

   public class MetricNested extends MetricIdentifierFluent implements Nested {
      MetricIdentifierBuilder builder;

      MetricNested(MetricIdentifier item) {
         this.builder = new MetricIdentifierBuilder(this, item);
      }

      public Object and() {
         return ExternalMetricStatusFluent.this.withMetric(this.builder.build());
      }

      public Object endMetric() {
         return this.and();
      }
   }
}
