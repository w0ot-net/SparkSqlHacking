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
@JsonPropertyOrder({"resourceName", "restartPolicy"})
public class ContainerResizePolicy implements Editable, KubernetesResource {
   @JsonProperty("resourceName")
   private String resourceName;
   @JsonProperty("restartPolicy")
   private String restartPolicy;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerResizePolicy() {
   }

   public ContainerResizePolicy(String resourceName, String restartPolicy) {
      this.resourceName = resourceName;
      this.restartPolicy = restartPolicy;
   }

   @JsonProperty("resourceName")
   public String getResourceName() {
      return this.resourceName;
   }

   @JsonProperty("resourceName")
   public void setResourceName(String resourceName) {
      this.resourceName = resourceName;
   }

   @JsonProperty("restartPolicy")
   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   @JsonProperty("restartPolicy")
   public void setRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
   }

   @JsonIgnore
   public ContainerResizePolicyBuilder edit() {
      return new ContainerResizePolicyBuilder(this);
   }

   @JsonIgnore
   public ContainerResizePolicyBuilder toBuilder() {
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
      String var10000 = this.getResourceName();
      return "ContainerResizePolicy(resourceName=" + var10000 + ", restartPolicy=" + this.getRestartPolicy() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerResizePolicy)) {
         return false;
      } else {
         ContainerResizePolicy other = (ContainerResizePolicy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$resourceName = this.getResourceName();
            Object other$resourceName = other.getResourceName();
            if (this$resourceName == null) {
               if (other$resourceName != null) {
                  return false;
               }
            } else if (!this$resourceName.equals(other$resourceName)) {
               return false;
            }

            Object this$restartPolicy = this.getRestartPolicy();
            Object other$restartPolicy = other.getRestartPolicy();
            if (this$restartPolicy == null) {
               if (other$restartPolicy != null) {
                  return false;
               }
            } else if (!this$restartPolicy.equals(other$restartPolicy)) {
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
      return other instanceof ContainerResizePolicy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $resourceName = this.getResourceName();
      result = result * 59 + ($resourceName == null ? 43 : $resourceName.hashCode());
      Object $restartPolicy = this.getRestartPolicy();
      result = result * 59 + ($restartPolicy == null ? 43 : $restartPolicy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
