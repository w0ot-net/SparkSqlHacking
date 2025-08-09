package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ContainerResourceMetricSourceFluent extends BaseFluent {
   private String container;
   private String name;
   private MetricTargetBuilder target;
   private Map additionalProperties;

   public ContainerResourceMetricSourceFluent() {
   }

   public ContainerResourceMetricSourceFluent(ContainerResourceMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerResourceMetricSource instance) {
      instance = instance != null ? instance : new ContainerResourceMetricSource();
      if (instance != null) {
         this.withContainer(instance.getContainer());
         this.withName(instance.getName());
         this.withTarget(instance.getTarget());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContainer() {
      return this.container;
   }

   public ContainerResourceMetricSourceFluent withContainer(String container) {
      this.container = container;
      return this;
   }

   public boolean hasContainer() {
      return this.container != null;
   }

   public String getName() {
      return this.name;
   }

   public ContainerResourceMetricSourceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public MetricTarget buildTarget() {
      return this.target != null ? this.target.build() : null;
   }

   public ContainerResourceMetricSourceFluent withTarget(MetricTarget target) {
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

   public ContainerResourceMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerResourceMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerResourceMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerResourceMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerResourceMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerResourceMetricSourceFluent that = (ContainerResourceMetricSourceFluent)o;
            if (!Objects.equals(this.container, that.container)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.container, this.name, this.target, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.container != null) {
         sb.append("container:");
         sb.append(this.container + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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

   public class TargetNested extends MetricTargetFluent implements Nested {
      MetricTargetBuilder builder;

      TargetNested(MetricTarget item) {
         this.builder = new MetricTargetBuilder(this, item);
      }

      public Object and() {
         return ContainerResourceMetricSourceFluent.this.withTarget(this.builder.build());
      }

      public Object endTarget() {
         return this.and();
      }
   }
}
