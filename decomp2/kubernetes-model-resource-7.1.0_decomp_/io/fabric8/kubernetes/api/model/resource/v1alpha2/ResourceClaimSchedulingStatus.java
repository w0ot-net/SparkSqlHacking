package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"name", "unsuitableNodes"})
public class ResourceClaimSchedulingStatus implements Editable, KubernetesResource {
   @JsonProperty("name")
   private String name;
   @JsonProperty("unsuitableNodes")
   @JsonInclude(Include.NON_EMPTY)
   private List unsuitableNodes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimSchedulingStatus() {
   }

   public ResourceClaimSchedulingStatus(String name, List unsuitableNodes) {
      this.name = name;
      this.unsuitableNodes = unsuitableNodes;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("unsuitableNodes")
   @JsonInclude(Include.NON_EMPTY)
   public List getUnsuitableNodes() {
      return this.unsuitableNodes;
   }

   @JsonProperty("unsuitableNodes")
   public void setUnsuitableNodes(List unsuitableNodes) {
      this.unsuitableNodes = unsuitableNodes;
   }

   @JsonIgnore
   public ResourceClaimSchedulingStatusBuilder edit() {
      return new ResourceClaimSchedulingStatusBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimSchedulingStatusBuilder toBuilder() {
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
      String var10000 = this.getName();
      return "ResourceClaimSchedulingStatus(name=" + var10000 + ", unsuitableNodes=" + this.getUnsuitableNodes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimSchedulingStatus)) {
         return false;
      } else {
         ResourceClaimSchedulingStatus other = (ResourceClaimSchedulingStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$unsuitableNodes = this.getUnsuitableNodes();
            Object other$unsuitableNodes = other.getUnsuitableNodes();
            if (this$unsuitableNodes == null) {
               if (other$unsuitableNodes != null) {
                  return false;
               }
            } else if (!this$unsuitableNodes.equals(other$unsuitableNodes)) {
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
      return other instanceof ResourceClaimSchedulingStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $unsuitableNodes = this.getUnsuitableNodes();
      result = result * 59 + ($unsuitableNodes == null ? 43 : $unsuitableNodes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
