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
@JsonPropertyOrder({"potentialNodes", "selectedNode"})
public class PodSchedulingContextSpec implements Editable, KubernetesResource {
   @JsonProperty("potentialNodes")
   @JsonInclude(Include.NON_EMPTY)
   private List potentialNodes = new ArrayList();
   @JsonProperty("selectedNode")
   private String selectedNode;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodSchedulingContextSpec() {
   }

   public PodSchedulingContextSpec(List potentialNodes, String selectedNode) {
      this.potentialNodes = potentialNodes;
      this.selectedNode = selectedNode;
   }

   @JsonProperty("potentialNodes")
   @JsonInclude(Include.NON_EMPTY)
   public List getPotentialNodes() {
      return this.potentialNodes;
   }

   @JsonProperty("potentialNodes")
   public void setPotentialNodes(List potentialNodes) {
      this.potentialNodes = potentialNodes;
   }

   @JsonProperty("selectedNode")
   public String getSelectedNode() {
      return this.selectedNode;
   }

   @JsonProperty("selectedNode")
   public void setSelectedNode(String selectedNode) {
      this.selectedNode = selectedNode;
   }

   @JsonIgnore
   public PodSchedulingContextSpecBuilder edit() {
      return new PodSchedulingContextSpecBuilder(this);
   }

   @JsonIgnore
   public PodSchedulingContextSpecBuilder toBuilder() {
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
      List var10000 = this.getPotentialNodes();
      return "PodSchedulingContextSpec(potentialNodes=" + var10000 + ", selectedNode=" + this.getSelectedNode() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodSchedulingContextSpec)) {
         return false;
      } else {
         PodSchedulingContextSpec other = (PodSchedulingContextSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$potentialNodes = this.getPotentialNodes();
            Object other$potentialNodes = other.getPotentialNodes();
            if (this$potentialNodes == null) {
               if (other$potentialNodes != null) {
                  return false;
               }
            } else if (!this$potentialNodes.equals(other$potentialNodes)) {
               return false;
            }

            Object this$selectedNode = this.getSelectedNode();
            Object other$selectedNode = other.getSelectedNode();
            if (this$selectedNode == null) {
               if (other$selectedNode != null) {
                  return false;
               }
            } else if (!this$selectedNode.equals(other$selectedNode)) {
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
      return other instanceof PodSchedulingContextSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $potentialNodes = this.getPotentialNodes();
      result = result * 59 + ($potentialNodes == null ? 43 : $potentialNodes.hashCode());
      Object $selectedNode = this.getSelectedNode();
      result = result * 59 + ($selectedNode == null ? 43 : $selectedNode.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
