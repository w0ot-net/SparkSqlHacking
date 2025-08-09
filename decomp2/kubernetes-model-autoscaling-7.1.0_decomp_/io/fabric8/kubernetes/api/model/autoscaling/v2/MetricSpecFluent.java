package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MetricSpecFluent extends BaseFluent {
   private ContainerResourceMetricSourceBuilder containerResource;
   private ExternalMetricSourceBuilder external;
   private ObjectMetricSourceBuilder object;
   private PodsMetricSourceBuilder pods;
   private ResourceMetricSourceBuilder resource;
   private String type;
   private Map additionalProperties;

   public MetricSpecFluent() {
   }

   public MetricSpecFluent(MetricSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MetricSpec instance) {
      instance = instance != null ? instance : new MetricSpec();
      if (instance != null) {
         this.withContainerResource(instance.getContainerResource());
         this.withExternal(instance.getExternal());
         this.withObject(instance.getObject());
         this.withPods(instance.getPods());
         this.withResource(instance.getResource());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ContainerResourceMetricSource buildContainerResource() {
      return this.containerResource != null ? this.containerResource.build() : null;
   }

   public MetricSpecFluent withContainerResource(ContainerResourceMetricSource containerResource) {
      this._visitables.remove("containerResource");
      if (containerResource != null) {
         this.containerResource = new ContainerResourceMetricSourceBuilder(containerResource);
         this._visitables.get("containerResource").add(this.containerResource);
      } else {
         this.containerResource = null;
         this._visitables.get("containerResource").remove(this.containerResource);
      }

      return this;
   }

   public boolean hasContainerResource() {
      return this.containerResource != null;
   }

   public ContainerResourceNested withNewContainerResource() {
      return new ContainerResourceNested((ContainerResourceMetricSource)null);
   }

   public ContainerResourceNested withNewContainerResourceLike(ContainerResourceMetricSource item) {
      return new ContainerResourceNested(item);
   }

   public ContainerResourceNested editContainerResource() {
      return this.withNewContainerResourceLike((ContainerResourceMetricSource)Optional.ofNullable(this.buildContainerResource()).orElse((Object)null));
   }

   public ContainerResourceNested editOrNewContainerResource() {
      return this.withNewContainerResourceLike((ContainerResourceMetricSource)Optional.ofNullable(this.buildContainerResource()).orElse((new ContainerResourceMetricSourceBuilder()).build()));
   }

   public ContainerResourceNested editOrNewContainerResourceLike(ContainerResourceMetricSource item) {
      return this.withNewContainerResourceLike((ContainerResourceMetricSource)Optional.ofNullable(this.buildContainerResource()).orElse(item));
   }

   public ExternalMetricSource buildExternal() {
      return this.external != null ? this.external.build() : null;
   }

   public MetricSpecFluent withExternal(ExternalMetricSource external) {
      this._visitables.remove("external");
      if (external != null) {
         this.external = new ExternalMetricSourceBuilder(external);
         this._visitables.get("external").add(this.external);
      } else {
         this.external = null;
         this._visitables.get("external").remove(this.external);
      }

      return this;
   }

   public boolean hasExternal() {
      return this.external != null;
   }

   public ExternalNested withNewExternal() {
      return new ExternalNested((ExternalMetricSource)null);
   }

   public ExternalNested withNewExternalLike(ExternalMetricSource item) {
      return new ExternalNested(item);
   }

   public ExternalNested editExternal() {
      return this.withNewExternalLike((ExternalMetricSource)Optional.ofNullable(this.buildExternal()).orElse((Object)null));
   }

   public ExternalNested editOrNewExternal() {
      return this.withNewExternalLike((ExternalMetricSource)Optional.ofNullable(this.buildExternal()).orElse((new ExternalMetricSourceBuilder()).build()));
   }

   public ExternalNested editOrNewExternalLike(ExternalMetricSource item) {
      return this.withNewExternalLike((ExternalMetricSource)Optional.ofNullable(this.buildExternal()).orElse(item));
   }

   public ObjectMetricSource buildObject() {
      return this.object != null ? this.object.build() : null;
   }

   public MetricSpecFluent withObject(ObjectMetricSource object) {
      this._visitables.remove("object");
      if (object != null) {
         this.object = new ObjectMetricSourceBuilder(object);
         this._visitables.get("object").add(this.object);
      } else {
         this.object = null;
         this._visitables.get("object").remove(this.object);
      }

      return this;
   }

   public boolean hasObject() {
      return this.object != null;
   }

   public ObjectNested withNewObject() {
      return new ObjectNested((ObjectMetricSource)null);
   }

   public ObjectNested withNewObjectLike(ObjectMetricSource item) {
      return new ObjectNested(item);
   }

   public ObjectNested editObject() {
      return this.withNewObjectLike((ObjectMetricSource)Optional.ofNullable(this.buildObject()).orElse((Object)null));
   }

   public ObjectNested editOrNewObject() {
      return this.withNewObjectLike((ObjectMetricSource)Optional.ofNullable(this.buildObject()).orElse((new ObjectMetricSourceBuilder()).build()));
   }

   public ObjectNested editOrNewObjectLike(ObjectMetricSource item) {
      return this.withNewObjectLike((ObjectMetricSource)Optional.ofNullable(this.buildObject()).orElse(item));
   }

   public PodsMetricSource buildPods() {
      return this.pods != null ? this.pods.build() : null;
   }

   public MetricSpecFluent withPods(PodsMetricSource pods) {
      this._visitables.remove("pods");
      if (pods != null) {
         this.pods = new PodsMetricSourceBuilder(pods);
         this._visitables.get("pods").add(this.pods);
      } else {
         this.pods = null;
         this._visitables.get("pods").remove(this.pods);
      }

      return this;
   }

   public boolean hasPods() {
      return this.pods != null;
   }

   public PodsNested withNewPods() {
      return new PodsNested((PodsMetricSource)null);
   }

   public PodsNested withNewPodsLike(PodsMetricSource item) {
      return new PodsNested(item);
   }

   public PodsNested editPods() {
      return this.withNewPodsLike((PodsMetricSource)Optional.ofNullable(this.buildPods()).orElse((Object)null));
   }

   public PodsNested editOrNewPods() {
      return this.withNewPodsLike((PodsMetricSource)Optional.ofNullable(this.buildPods()).orElse((new PodsMetricSourceBuilder()).build()));
   }

   public PodsNested editOrNewPodsLike(PodsMetricSource item) {
      return this.withNewPodsLike((PodsMetricSource)Optional.ofNullable(this.buildPods()).orElse(item));
   }

   public ResourceMetricSource buildResource() {
      return this.resource != null ? this.resource.build() : null;
   }

   public MetricSpecFluent withResource(ResourceMetricSource resource) {
      this._visitables.remove("resource");
      if (resource != null) {
         this.resource = new ResourceMetricSourceBuilder(resource);
         this._visitables.get("resource").add(this.resource);
      } else {
         this.resource = null;
         this._visitables.get("resource").remove(this.resource);
      }

      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public ResourceNested withNewResource() {
      return new ResourceNested((ResourceMetricSource)null);
   }

   public ResourceNested withNewResourceLike(ResourceMetricSource item) {
      return new ResourceNested(item);
   }

   public ResourceNested editResource() {
      return this.withNewResourceLike((ResourceMetricSource)Optional.ofNullable(this.buildResource()).orElse((Object)null));
   }

   public ResourceNested editOrNewResource() {
      return this.withNewResourceLike((ResourceMetricSource)Optional.ofNullable(this.buildResource()).orElse((new ResourceMetricSourceBuilder()).build()));
   }

   public ResourceNested editOrNewResourceLike(ResourceMetricSource item) {
      return this.withNewResourceLike((ResourceMetricSource)Optional.ofNullable(this.buildResource()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public MetricSpecFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public MetricSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MetricSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MetricSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MetricSpecFluent removeFromAdditionalProperties(Map map) {
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

   public MetricSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            MetricSpecFluent that = (MetricSpecFluent)o;
            if (!Objects.equals(this.containerResource, that.containerResource)) {
               return false;
            } else if (!Objects.equals(this.external, that.external)) {
               return false;
            } else if (!Objects.equals(this.object, that.object)) {
               return false;
            } else if (!Objects.equals(this.pods, that.pods)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.containerResource, this.external, this.object, this.pods, this.resource, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.containerResource != null) {
         sb.append("containerResource:");
         sb.append(this.containerResource + ",");
      }

      if (this.external != null) {
         sb.append("external:");
         sb.append(this.external + ",");
      }

      if (this.object != null) {
         sb.append("object:");
         sb.append(this.object + ",");
      }

      if (this.pods != null) {
         sb.append("pods:");
         sb.append(this.pods + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ContainerResourceNested extends ContainerResourceMetricSourceFluent implements Nested {
      ContainerResourceMetricSourceBuilder builder;

      ContainerResourceNested(ContainerResourceMetricSource item) {
         this.builder = new ContainerResourceMetricSourceBuilder(this, item);
      }

      public Object and() {
         return MetricSpecFluent.this.withContainerResource(this.builder.build());
      }

      public Object endContainerResource() {
         return this.and();
      }
   }

   public class ExternalNested extends ExternalMetricSourceFluent implements Nested {
      ExternalMetricSourceBuilder builder;

      ExternalNested(ExternalMetricSource item) {
         this.builder = new ExternalMetricSourceBuilder(this, item);
      }

      public Object and() {
         return MetricSpecFluent.this.withExternal(this.builder.build());
      }

      public Object endExternal() {
         return this.and();
      }
   }

   public class ObjectNested extends ObjectMetricSourceFluent implements Nested {
      ObjectMetricSourceBuilder builder;

      ObjectNested(ObjectMetricSource item) {
         this.builder = new ObjectMetricSourceBuilder(this, item);
      }

      public Object and() {
         return MetricSpecFluent.this.withObject(this.builder.build());
      }

      public Object endObject() {
         return this.and();
      }
   }

   public class PodsNested extends PodsMetricSourceFluent implements Nested {
      PodsMetricSourceBuilder builder;

      PodsNested(PodsMetricSource item) {
         this.builder = new PodsMetricSourceBuilder(this, item);
      }

      public Object and() {
         return MetricSpecFluent.this.withPods(this.builder.build());
      }

      public Object endPods() {
         return this.and();
      }
   }

   public class ResourceNested extends ResourceMetricSourceFluent implements Nested {
      ResourceMetricSourceBuilder builder;

      ResourceNested(ResourceMetricSource item) {
         this.builder = new ResourceMetricSourceBuilder(this, item);
      }

      public Object and() {
         return MetricSpecFluent.this.withResource(this.builder.build());
      }

      public Object endResource() {
         return this.and();
      }
   }
}
