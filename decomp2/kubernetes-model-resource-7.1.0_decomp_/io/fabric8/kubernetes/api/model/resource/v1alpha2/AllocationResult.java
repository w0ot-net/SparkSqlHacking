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
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"availableOnNodes", "resourceHandles", "shareable"})
public class AllocationResult implements Editable, KubernetesResource {
   @JsonProperty("availableOnNodes")
   private NodeSelector availableOnNodes;
   @JsonProperty("resourceHandles")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceHandles = new ArrayList();
   @JsonProperty("shareable")
   private Boolean shareable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AllocationResult() {
   }

   public AllocationResult(NodeSelector availableOnNodes, List resourceHandles, Boolean shareable) {
      this.availableOnNodes = availableOnNodes;
      this.resourceHandles = resourceHandles;
      this.shareable = shareable;
   }

   @JsonProperty("availableOnNodes")
   public NodeSelector getAvailableOnNodes() {
      return this.availableOnNodes;
   }

   @JsonProperty("availableOnNodes")
   public void setAvailableOnNodes(NodeSelector availableOnNodes) {
      this.availableOnNodes = availableOnNodes;
   }

   @JsonProperty("resourceHandles")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceHandles() {
      return this.resourceHandles;
   }

   @JsonProperty("resourceHandles")
   public void setResourceHandles(List resourceHandles) {
      this.resourceHandles = resourceHandles;
   }

   @JsonProperty("shareable")
   public Boolean getShareable() {
      return this.shareable;
   }

   @JsonProperty("shareable")
   public void setShareable(Boolean shareable) {
      this.shareable = shareable;
   }

   @JsonIgnore
   public AllocationResultBuilder edit() {
      return new AllocationResultBuilder(this);
   }

   @JsonIgnore
   public AllocationResultBuilder toBuilder() {
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
      NodeSelector var10000 = this.getAvailableOnNodes();
      return "AllocationResult(availableOnNodes=" + var10000 + ", resourceHandles=" + this.getResourceHandles() + ", shareable=" + this.getShareable() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AllocationResult)) {
         return false;
      } else {
         AllocationResult other = (AllocationResult)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$shareable = this.getShareable();
            Object other$shareable = other.getShareable();
            if (this$shareable == null) {
               if (other$shareable != null) {
                  return false;
               }
            } else if (!this$shareable.equals(other$shareable)) {
               return false;
            }

            Object this$availableOnNodes = this.getAvailableOnNodes();
            Object other$availableOnNodes = other.getAvailableOnNodes();
            if (this$availableOnNodes == null) {
               if (other$availableOnNodes != null) {
                  return false;
               }
            } else if (!this$availableOnNodes.equals(other$availableOnNodes)) {
               return false;
            }

            Object this$resourceHandles = this.getResourceHandles();
            Object other$resourceHandles = other.getResourceHandles();
            if (this$resourceHandles == null) {
               if (other$resourceHandles != null) {
                  return false;
               }
            } else if (!this$resourceHandles.equals(other$resourceHandles)) {
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
      return other instanceof AllocationResult;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $shareable = this.getShareable();
      result = result * 59 + ($shareable == null ? 43 : $shareable.hashCode());
      Object $availableOnNodes = this.getAvailableOnNodes();
      result = result * 59 + ($availableOnNodes == null ? 43 : $availableOnNodes.hashCode());
      Object $resourceHandles = this.getResourceHandles();
      result = result * 59 + ($resourceHandles == null ? 43 : $resourceHandles.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
