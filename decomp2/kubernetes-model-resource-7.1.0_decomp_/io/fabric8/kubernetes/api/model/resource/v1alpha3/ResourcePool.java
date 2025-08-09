package io.fabric8.kubernetes.api.model.resource.v1alpha3;

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
@JsonPropertyOrder({"generation", "name", "resourceSliceCount"})
public class ResourcePool implements Editable, KubernetesResource {
   @JsonProperty("generation")
   private Long generation;
   @JsonProperty("name")
   private String name;
   @JsonProperty("resourceSliceCount")
   private Long resourceSliceCount;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourcePool() {
   }

   public ResourcePool(Long generation, String name, Long resourceSliceCount) {
      this.generation = generation;
      this.name = name;
      this.resourceSliceCount = resourceSliceCount;
   }

   @JsonProperty("generation")
   public Long getGeneration() {
      return this.generation;
   }

   @JsonProperty("generation")
   public void setGeneration(Long generation) {
      this.generation = generation;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("resourceSliceCount")
   public Long getResourceSliceCount() {
      return this.resourceSliceCount;
   }

   @JsonProperty("resourceSliceCount")
   public void setResourceSliceCount(Long resourceSliceCount) {
      this.resourceSliceCount = resourceSliceCount;
   }

   @JsonIgnore
   public ResourcePoolBuilder edit() {
      return new ResourcePoolBuilder(this);
   }

   @JsonIgnore
   public ResourcePoolBuilder toBuilder() {
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
      Long var10000 = this.getGeneration();
      return "ResourcePool(generation=" + var10000 + ", name=" + this.getName() + ", resourceSliceCount=" + this.getResourceSliceCount() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePool)) {
         return false;
      } else {
         ResourcePool other = (ResourcePool)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$generation = this.getGeneration();
            Object other$generation = other.getGeneration();
            if (this$generation == null) {
               if (other$generation != null) {
                  return false;
               }
            } else if (!this$generation.equals(other$generation)) {
               return false;
            }

            Object this$resourceSliceCount = this.getResourceSliceCount();
            Object other$resourceSliceCount = other.getResourceSliceCount();
            if (this$resourceSliceCount == null) {
               if (other$resourceSliceCount != null) {
                  return false;
               }
            } else if (!this$resourceSliceCount.equals(other$resourceSliceCount)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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
      return other instanceof ResourcePool;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $generation = this.getGeneration();
      result = result * 59 + ($generation == null ? 43 : $generation.hashCode());
      Object $resourceSliceCount = this.getResourceSliceCount();
      result = result * 59 + ($resourceSliceCount == null ? 43 : $resourceSliceCount.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
