package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ObjectMetricSourceFluent extends BaseFluent {
   private CrossVersionObjectReferenceBuilder describedObject;
   private MetricIdentifierBuilder metric;
   private MetricTargetBuilder target;
   private Map additionalProperties;

   public ObjectMetricSourceFluent() {
   }

   public ObjectMetricSourceFluent(ObjectMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ObjectMetricSource instance) {
      instance = instance != null ? instance : new ObjectMetricSource();
      if (instance != null) {
         this.withDescribedObject(instance.getDescribedObject());
         this.withMetric(instance.getMetric());
         this.withTarget(instance.getTarget());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CrossVersionObjectReference buildDescribedObject() {
      return this.describedObject != null ? this.describedObject.build() : null;
   }

   public ObjectMetricSourceFluent withDescribedObject(CrossVersionObjectReference describedObject) {
      this._visitables.remove("describedObject");
      if (describedObject != null) {
         this.describedObject = new CrossVersionObjectReferenceBuilder(describedObject);
         this._visitables.get("describedObject").add(this.describedObject);
      } else {
         this.describedObject = null;
         this._visitables.get("describedObject").remove(this.describedObject);
      }

      return this;
   }

   public boolean hasDescribedObject() {
      return this.describedObject != null;
   }

   public ObjectMetricSourceFluent withNewDescribedObject(String apiVersion, String kind, String name) {
      return this.withDescribedObject(new CrossVersionObjectReference(apiVersion, kind, name));
   }

   public DescribedObjectNested withNewDescribedObject() {
      return new DescribedObjectNested((CrossVersionObjectReference)null);
   }

   public DescribedObjectNested withNewDescribedObjectLike(CrossVersionObjectReference item) {
      return new DescribedObjectNested(item);
   }

   public DescribedObjectNested editDescribedObject() {
      return this.withNewDescribedObjectLike((CrossVersionObjectReference)Optional.ofNullable(this.buildDescribedObject()).orElse((Object)null));
   }

   public DescribedObjectNested editOrNewDescribedObject() {
      return this.withNewDescribedObjectLike((CrossVersionObjectReference)Optional.ofNullable(this.buildDescribedObject()).orElse((new CrossVersionObjectReferenceBuilder()).build()));
   }

   public DescribedObjectNested editOrNewDescribedObjectLike(CrossVersionObjectReference item) {
      return this.withNewDescribedObjectLike((CrossVersionObjectReference)Optional.ofNullable(this.buildDescribedObject()).orElse(item));
   }

   public MetricIdentifier buildMetric() {
      return this.metric != null ? this.metric.build() : null;
   }

   public ObjectMetricSourceFluent withMetric(MetricIdentifier metric) {
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

   public ObjectMetricSourceFluent withTarget(MetricTarget target) {
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

   public ObjectMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ObjectMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ObjectMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ObjectMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ObjectMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ObjectMetricSourceFluent that = (ObjectMetricSourceFluent)o;
            if (!Objects.equals(this.describedObject, that.describedObject)) {
               return false;
            } else if (!Objects.equals(this.metric, that.metric)) {
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
      return Objects.hash(new Object[]{this.describedObject, this.metric, this.target, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.describedObject != null) {
         sb.append("describedObject:");
         sb.append(this.describedObject + ",");
      }

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

   public class DescribedObjectNested extends CrossVersionObjectReferenceFluent implements Nested {
      CrossVersionObjectReferenceBuilder builder;

      DescribedObjectNested(CrossVersionObjectReference item) {
         this.builder = new CrossVersionObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return ObjectMetricSourceFluent.this.withDescribedObject(this.builder.build());
      }

      public Object endDescribedObject() {
         return this.and();
      }
   }

   public class MetricNested extends MetricIdentifierFluent implements Nested {
      MetricIdentifierBuilder builder;

      MetricNested(MetricIdentifier item) {
         this.builder = new MetricIdentifierBuilder(this, item);
      }

      public Object and() {
         return ObjectMetricSourceFluent.this.withMetric(this.builder.build());
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
         return ObjectMetricSourceFluent.this.withTarget(this.builder.build());
      }

      public Object endTarget() {
         return this.and();
      }
   }
}
