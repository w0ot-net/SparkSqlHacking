package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MetricStatusFluent extends BaseFluent {
   private ContainerResourceMetricStatusBuilder containerResource;
   private ExternalMetricStatusBuilder external;
   private ObjectMetricStatusBuilder object;
   private PodsMetricStatusBuilder pods;
   private ResourceMetricStatusBuilder resource;
   private String type;
   private Map additionalProperties;

   public MetricStatusFluent() {
   }

   public MetricStatusFluent(MetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MetricStatus instance) {
      instance = instance != null ? instance : new MetricStatus();
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

   public ContainerResourceMetricStatus buildContainerResource() {
      return this.containerResource != null ? this.containerResource.build() : null;
   }

   public MetricStatusFluent withContainerResource(ContainerResourceMetricStatus containerResource) {
      this._visitables.remove("containerResource");
      if (containerResource != null) {
         this.containerResource = new ContainerResourceMetricStatusBuilder(containerResource);
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
      return new ContainerResourceNested((ContainerResourceMetricStatus)null);
   }

   public ContainerResourceNested withNewContainerResourceLike(ContainerResourceMetricStatus item) {
      return new ContainerResourceNested(item);
   }

   public ContainerResourceNested editContainerResource() {
      return this.withNewContainerResourceLike((ContainerResourceMetricStatus)Optional.ofNullable(this.buildContainerResource()).orElse((Object)null));
   }

   public ContainerResourceNested editOrNewContainerResource() {
      return this.withNewContainerResourceLike((ContainerResourceMetricStatus)Optional.ofNullable(this.buildContainerResource()).orElse((new ContainerResourceMetricStatusBuilder()).build()));
   }

   public ContainerResourceNested editOrNewContainerResourceLike(ContainerResourceMetricStatus item) {
      return this.withNewContainerResourceLike((ContainerResourceMetricStatus)Optional.ofNullable(this.buildContainerResource()).orElse(item));
   }

   public ExternalMetricStatus buildExternal() {
      return this.external != null ? this.external.build() : null;
   }

   public MetricStatusFluent withExternal(ExternalMetricStatus external) {
      this._visitables.remove("external");
      if (external != null) {
         this.external = new ExternalMetricStatusBuilder(external);
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
      return new ExternalNested((ExternalMetricStatus)null);
   }

   public ExternalNested withNewExternalLike(ExternalMetricStatus item) {
      return new ExternalNested(item);
   }

   public ExternalNested editExternal() {
      return this.withNewExternalLike((ExternalMetricStatus)Optional.ofNullable(this.buildExternal()).orElse((Object)null));
   }

   public ExternalNested editOrNewExternal() {
      return this.withNewExternalLike((ExternalMetricStatus)Optional.ofNullable(this.buildExternal()).orElse((new ExternalMetricStatusBuilder()).build()));
   }

   public ExternalNested editOrNewExternalLike(ExternalMetricStatus item) {
      return this.withNewExternalLike((ExternalMetricStatus)Optional.ofNullable(this.buildExternal()).orElse(item));
   }

   public ObjectMetricStatus buildObject() {
      return this.object != null ? this.object.build() : null;
   }

   public MetricStatusFluent withObject(ObjectMetricStatus object) {
      this._visitables.remove("object");
      if (object != null) {
         this.object = new ObjectMetricStatusBuilder(object);
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
      return new ObjectNested((ObjectMetricStatus)null);
   }

   public ObjectNested withNewObjectLike(ObjectMetricStatus item) {
      return new ObjectNested(item);
   }

   public ObjectNested editObject() {
      return this.withNewObjectLike((ObjectMetricStatus)Optional.ofNullable(this.buildObject()).orElse((Object)null));
   }

   public ObjectNested editOrNewObject() {
      return this.withNewObjectLike((ObjectMetricStatus)Optional.ofNullable(this.buildObject()).orElse((new ObjectMetricStatusBuilder()).build()));
   }

   public ObjectNested editOrNewObjectLike(ObjectMetricStatus item) {
      return this.withNewObjectLike((ObjectMetricStatus)Optional.ofNullable(this.buildObject()).orElse(item));
   }

   public PodsMetricStatus buildPods() {
      return this.pods != null ? this.pods.build() : null;
   }

   public MetricStatusFluent withPods(PodsMetricStatus pods) {
      this._visitables.remove("pods");
      if (pods != null) {
         this.pods = new PodsMetricStatusBuilder(pods);
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
      return new PodsNested((PodsMetricStatus)null);
   }

   public PodsNested withNewPodsLike(PodsMetricStatus item) {
      return new PodsNested(item);
   }

   public PodsNested editPods() {
      return this.withNewPodsLike((PodsMetricStatus)Optional.ofNullable(this.buildPods()).orElse((Object)null));
   }

   public PodsNested editOrNewPods() {
      return this.withNewPodsLike((PodsMetricStatus)Optional.ofNullable(this.buildPods()).orElse((new PodsMetricStatusBuilder()).build()));
   }

   public PodsNested editOrNewPodsLike(PodsMetricStatus item) {
      return this.withNewPodsLike((PodsMetricStatus)Optional.ofNullable(this.buildPods()).orElse(item));
   }

   public ResourceMetricStatus buildResource() {
      return this.resource != null ? this.resource.build() : null;
   }

   public MetricStatusFluent withResource(ResourceMetricStatus resource) {
      this._visitables.remove("resource");
      if (resource != null) {
         this.resource = new ResourceMetricStatusBuilder(resource);
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
      return new ResourceNested((ResourceMetricStatus)null);
   }

   public ResourceNested withNewResourceLike(ResourceMetricStatus item) {
      return new ResourceNested(item);
   }

   public ResourceNested editResource() {
      return this.withNewResourceLike((ResourceMetricStatus)Optional.ofNullable(this.buildResource()).orElse((Object)null));
   }

   public ResourceNested editOrNewResource() {
      return this.withNewResourceLike((ResourceMetricStatus)Optional.ofNullable(this.buildResource()).orElse((new ResourceMetricStatusBuilder()).build()));
   }

   public ResourceNested editOrNewResourceLike(ResourceMetricStatus item) {
      return this.withNewResourceLike((ResourceMetricStatus)Optional.ofNullable(this.buildResource()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public MetricStatusFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public MetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public MetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            MetricStatusFluent that = (MetricStatusFluent)o;
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

   public class ContainerResourceNested extends ContainerResourceMetricStatusFluent implements Nested {
      ContainerResourceMetricStatusBuilder builder;

      ContainerResourceNested(ContainerResourceMetricStatus item) {
         this.builder = new ContainerResourceMetricStatusBuilder(this, item);
      }

      public Object and() {
         return MetricStatusFluent.this.withContainerResource(this.builder.build());
      }

      public Object endContainerResource() {
         return this.and();
      }
   }

   public class ExternalNested extends ExternalMetricStatusFluent implements Nested {
      ExternalMetricStatusBuilder builder;

      ExternalNested(ExternalMetricStatus item) {
         this.builder = new ExternalMetricStatusBuilder(this, item);
      }

      public Object and() {
         return MetricStatusFluent.this.withExternal(this.builder.build());
      }

      public Object endExternal() {
         return this.and();
      }
   }

   public class ObjectNested extends ObjectMetricStatusFluent implements Nested {
      ObjectMetricStatusBuilder builder;

      ObjectNested(ObjectMetricStatus item) {
         this.builder = new ObjectMetricStatusBuilder(this, item);
      }

      public Object and() {
         return MetricStatusFluent.this.withObject(this.builder.build());
      }

      public Object endObject() {
         return this.and();
      }
   }

   public class PodsNested extends PodsMetricStatusFluent implements Nested {
      PodsMetricStatusBuilder builder;

      PodsNested(PodsMetricStatus item) {
         this.builder = new PodsMetricStatusBuilder(this, item);
      }

      public Object and() {
         return MetricStatusFluent.this.withPods(this.builder.build());
      }

      public Object endPods() {
         return this.and();
      }
   }

   public class ResourceNested extends ResourceMetricStatusFluent implements Nested {
      ResourceMetricStatusBuilder builder;

      ResourceNested(ResourceMetricStatus item) {
         this.builder = new ResourceMetricStatusBuilder(this, item);
      }

      public Object and() {
         return MetricStatusFluent.this.withResource(this.builder.build());
      }

      public Object endResource() {
         return this.and();
      }
   }
}
