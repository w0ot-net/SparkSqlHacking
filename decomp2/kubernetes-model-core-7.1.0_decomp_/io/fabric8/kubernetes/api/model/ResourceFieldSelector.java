package io.fabric8.kubernetes.api.model;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"containerName", "divisor", "resource"})
public class ResourceFieldSelector implements Editable, KubernetesResource {
   @JsonProperty("containerName")
   private String containerName;
   @JsonProperty("divisor")
   private Quantity divisor;
   @JsonProperty("resource")
   private String resource;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceFieldSelector() {
   }

   public ResourceFieldSelector(String containerName, Quantity divisor, String resource) {
      this.containerName = containerName;
      this.divisor = divisor;
      this.resource = resource;
   }

   @JsonProperty("containerName")
   public String getContainerName() {
      return this.containerName;
   }

   @JsonProperty("containerName")
   public void setContainerName(String containerName) {
      this.containerName = containerName;
   }

   @JsonProperty("divisor")
   public Quantity getDivisor() {
      return this.divisor;
   }

   @JsonProperty("divisor")
   public void setDivisor(Quantity divisor) {
      this.divisor = divisor;
   }

   @JsonProperty("resource")
   public String getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(String resource) {
      this.resource = resource;
   }

   @JsonIgnore
   public ResourceFieldSelectorBuilder edit() {
      return new ResourceFieldSelectorBuilder(this);
   }

   @JsonIgnore
   public ResourceFieldSelectorBuilder toBuilder() {
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
      String var10000 = this.getContainerName();
      return "ResourceFieldSelector(containerName=" + var10000 + ", divisor=" + this.getDivisor() + ", resource=" + this.getResource() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceFieldSelector)) {
         return false;
      } else {
         ResourceFieldSelector other = (ResourceFieldSelector)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$containerName = this.getContainerName();
            Object other$containerName = other.getContainerName();
            if (this$containerName == null) {
               if (other$containerName != null) {
                  return false;
               }
            } else if (!this$containerName.equals(other$containerName)) {
               return false;
            }

            Object this$divisor = this.getDivisor();
            Object other$divisor = other.getDivisor();
            if (this$divisor == null) {
               if (other$divisor != null) {
                  return false;
               }
            } else if (!this$divisor.equals(other$divisor)) {
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
      return other instanceof ResourceFieldSelector;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $containerName = this.getContainerName();
      result = result * 59 + ($containerName == null ? 43 : $containerName.hashCode());
      Object $divisor = this.getDivisor();
      result = result * 59 + ($divisor == null ? 43 : $divisor.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
