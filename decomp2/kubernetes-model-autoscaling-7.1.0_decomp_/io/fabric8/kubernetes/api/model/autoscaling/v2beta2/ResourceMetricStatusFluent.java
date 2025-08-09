package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceMetricStatusFluent extends BaseFluent {
   private MetricValueStatusBuilder current;
   private String name;
   private Map additionalProperties;

   public ResourceMetricStatusFluent() {
   }

   public ResourceMetricStatusFluent(ResourceMetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceMetricStatus instance) {
      instance = instance != null ? instance : new ResourceMetricStatus();
      if (instance != null) {
         this.withCurrent(instance.getCurrent());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MetricValueStatus buildCurrent() {
      return this.current != null ? this.current.build() : null;
   }

   public ResourceMetricStatusFluent withCurrent(MetricValueStatus current) {
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

   public String getName() {
      return this.name;
   }

   public ResourceMetricStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ResourceMetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceMetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceMetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceMetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceMetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceMetricStatusFluent that = (ResourceMetricStatusFluent)o;
            if (!Objects.equals(this.current, that.current)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.current, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.current != null) {
         sb.append("current:");
         sb.append(this.current + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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
         return ResourceMetricStatusFluent.this.withCurrent(this.builder.build());
      }

      public Object endCurrent() {
         return this.and();
      }
   }
}
