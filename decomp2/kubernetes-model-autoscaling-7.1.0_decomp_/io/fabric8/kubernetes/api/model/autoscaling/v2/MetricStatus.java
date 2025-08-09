package io.fabric8.kubernetes.api.model.autoscaling.v2;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"containerResource", "external", "object", "pods", "resource", "type"})
public class MetricStatus implements Editable, KubernetesResource {
   @JsonProperty("containerResource")
   private ContainerResourceMetricStatus containerResource;
   @JsonProperty("external")
   private ExternalMetricStatus external;
   @JsonProperty("object")
   private ObjectMetricStatus object;
   @JsonProperty("pods")
   private PodsMetricStatus pods;
   @JsonProperty("resource")
   private ResourceMetricStatus resource;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MetricStatus() {
   }

   public MetricStatus(ContainerResourceMetricStatus containerResource, ExternalMetricStatus external, ObjectMetricStatus object, PodsMetricStatus pods, ResourceMetricStatus resource, String type) {
      this.containerResource = containerResource;
      this.external = external;
      this.object = object;
      this.pods = pods;
      this.resource = resource;
      this.type = type;
   }

   @JsonProperty("containerResource")
   public ContainerResourceMetricStatus getContainerResource() {
      return this.containerResource;
   }

   @JsonProperty("containerResource")
   public void setContainerResource(ContainerResourceMetricStatus containerResource) {
      this.containerResource = containerResource;
   }

   @JsonProperty("external")
   public ExternalMetricStatus getExternal() {
      return this.external;
   }

   @JsonProperty("external")
   public void setExternal(ExternalMetricStatus external) {
      this.external = external;
   }

   @JsonProperty("object")
   public ObjectMetricStatus getObject() {
      return this.object;
   }

   @JsonProperty("object")
   public void setObject(ObjectMetricStatus object) {
      this.object = object;
   }

   @JsonProperty("pods")
   public PodsMetricStatus getPods() {
      return this.pods;
   }

   @JsonProperty("pods")
   public void setPods(PodsMetricStatus pods) {
      this.pods = pods;
   }

   @JsonProperty("resource")
   public ResourceMetricStatus getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(ResourceMetricStatus resource) {
      this.resource = resource;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public MetricStatusBuilder edit() {
      return new MetricStatusBuilder(this);
   }

   @JsonIgnore
   public MetricStatusBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      ContainerResourceMetricStatus var10000 = this.getContainerResource();
      return "MetricStatus(containerResource=" + var10000 + ", external=" + this.getExternal() + ", object=" + this.getObject() + ", pods=" + this.getPods() + ", resource=" + this.getResource() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MetricStatus)) {
         return false;
      } else {
         MetricStatus other = (MetricStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$containerResource = this.getContainerResource();
            Object other$containerResource = other.getContainerResource();
            if (this$containerResource == null) {
               if (other$containerResource != null) {
                  return false;
               }
            } else if (!this$containerResource.equals(other$containerResource)) {
               return false;
            }

            Object this$external = this.getExternal();
            Object other$external = other.getExternal();
            if (this$external == null) {
               if (other$external != null) {
                  return false;
               }
            } else if (!this$external.equals(other$external)) {
               return false;
            }

            Object this$object = this.getObject();
            Object other$object = other.getObject();
            if (this$object == null) {
               if (other$object != null) {
                  return false;
               }
            } else if (!this$object.equals(other$object)) {
               return false;
            }

            Object this$pods = this.getPods();
            Object other$pods = other.getPods();
            if (this$pods == null) {
               if (other$pods != null) {
                  return false;
               }
            } else if (!this$pods.equals(other$pods)) {
               return false;
            }

            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof MetricStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $containerResource = this.getContainerResource();
      result = result * 59 + ($containerResource == null ? 43 : $containerResource.hashCode());
      Object $external = this.getExternal();
      result = result * 59 + ($external == null ? 43 : $external.hashCode());
      Object $object = this.getObject();
      result = result * 59 + ($object == null ? 43 : $object.hashCode());
      Object $pods = this.getPods();
      result = result * 59 + ($pods == null ? 43 : $pods.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
