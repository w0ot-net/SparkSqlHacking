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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"configSource", "externalID", "podCIDR", "podCIDRs", "providerID", "taints", "unschedulable"})
public class NodeSpec implements Editable, KubernetesResource {
   @JsonProperty("configSource")
   private NodeConfigSource configSource;
   @JsonProperty("externalID")
   private String externalID;
   @JsonProperty("podCIDR")
   private String podCIDR;
   @JsonProperty("podCIDRs")
   @JsonInclude(Include.NON_EMPTY)
   private List podCIDRs = new ArrayList();
   @JsonProperty("providerID")
   private String providerID;
   @JsonProperty("taints")
   @JsonInclude(Include.NON_EMPTY)
   private List taints = new ArrayList();
   @JsonProperty("unschedulable")
   private Boolean unschedulable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeSpec() {
   }

   public NodeSpec(NodeConfigSource configSource, String externalID, String podCIDR, List podCIDRs, String providerID, List taints, Boolean unschedulable) {
      this.configSource = configSource;
      this.externalID = externalID;
      this.podCIDR = podCIDR;
      this.podCIDRs = podCIDRs;
      this.providerID = providerID;
      this.taints = taints;
      this.unschedulable = unschedulable;
   }

   @JsonProperty("configSource")
   public NodeConfigSource getConfigSource() {
      return this.configSource;
   }

   @JsonProperty("configSource")
   public void setConfigSource(NodeConfigSource configSource) {
      this.configSource = configSource;
   }

   @JsonProperty("externalID")
   public String getExternalID() {
      return this.externalID;
   }

   @JsonProperty("externalID")
   public void setExternalID(String externalID) {
      this.externalID = externalID;
   }

   @JsonProperty("podCIDR")
   public String getPodCIDR() {
      return this.podCIDR;
   }

   @JsonProperty("podCIDR")
   public void setPodCIDR(String podCIDR) {
      this.podCIDR = podCIDR;
   }

   @JsonProperty("podCIDRs")
   @JsonInclude(Include.NON_EMPTY)
   public List getPodCIDRs() {
      return this.podCIDRs;
   }

   @JsonProperty("podCIDRs")
   public void setPodCIDRs(List podCIDRs) {
      this.podCIDRs = podCIDRs;
   }

   @JsonProperty("providerID")
   public String getProviderID() {
      return this.providerID;
   }

   @JsonProperty("providerID")
   public void setProviderID(String providerID) {
      this.providerID = providerID;
   }

   @JsonProperty("taints")
   @JsonInclude(Include.NON_EMPTY)
   public List getTaints() {
      return this.taints;
   }

   @JsonProperty("taints")
   public void setTaints(List taints) {
      this.taints = taints;
   }

   @JsonProperty("unschedulable")
   public Boolean getUnschedulable() {
      return this.unschedulable;
   }

   @JsonProperty("unschedulable")
   public void setUnschedulable(Boolean unschedulable) {
      this.unschedulable = unschedulable;
   }

   @JsonIgnore
   public NodeSpecBuilder edit() {
      return new NodeSpecBuilder(this);
   }

   @JsonIgnore
   public NodeSpecBuilder toBuilder() {
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
      NodeConfigSource var10000 = this.getConfigSource();
      return "NodeSpec(configSource=" + var10000 + ", externalID=" + this.getExternalID() + ", podCIDR=" + this.getPodCIDR() + ", podCIDRs=" + this.getPodCIDRs() + ", providerID=" + this.getProviderID() + ", taints=" + this.getTaints() + ", unschedulable=" + this.getUnschedulable() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeSpec)) {
         return false;
      } else {
         NodeSpec other = (NodeSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$unschedulable = this.getUnschedulable();
            Object other$unschedulable = other.getUnschedulable();
            if (this$unschedulable == null) {
               if (other$unschedulable != null) {
                  return false;
               }
            } else if (!this$unschedulable.equals(other$unschedulable)) {
               return false;
            }

            Object this$configSource = this.getConfigSource();
            Object other$configSource = other.getConfigSource();
            if (this$configSource == null) {
               if (other$configSource != null) {
                  return false;
               }
            } else if (!this$configSource.equals(other$configSource)) {
               return false;
            }

            Object this$externalID = this.getExternalID();
            Object other$externalID = other.getExternalID();
            if (this$externalID == null) {
               if (other$externalID != null) {
                  return false;
               }
            } else if (!this$externalID.equals(other$externalID)) {
               return false;
            }

            Object this$podCIDR = this.getPodCIDR();
            Object other$podCIDR = other.getPodCIDR();
            if (this$podCIDR == null) {
               if (other$podCIDR != null) {
                  return false;
               }
            } else if (!this$podCIDR.equals(other$podCIDR)) {
               return false;
            }

            Object this$podCIDRs = this.getPodCIDRs();
            Object other$podCIDRs = other.getPodCIDRs();
            if (this$podCIDRs == null) {
               if (other$podCIDRs != null) {
                  return false;
               }
            } else if (!this$podCIDRs.equals(other$podCIDRs)) {
               return false;
            }

            Object this$providerID = this.getProviderID();
            Object other$providerID = other.getProviderID();
            if (this$providerID == null) {
               if (other$providerID != null) {
                  return false;
               }
            } else if (!this$providerID.equals(other$providerID)) {
               return false;
            }

            Object this$taints = this.getTaints();
            Object other$taints = other.getTaints();
            if (this$taints == null) {
               if (other$taints != null) {
                  return false;
               }
            } else if (!this$taints.equals(other$taints)) {
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
      return other instanceof NodeSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $unschedulable = this.getUnschedulable();
      result = result * 59 + ($unschedulable == null ? 43 : $unschedulable.hashCode());
      Object $configSource = this.getConfigSource();
      result = result * 59 + ($configSource == null ? 43 : $configSource.hashCode());
      Object $externalID = this.getExternalID();
      result = result * 59 + ($externalID == null ? 43 : $externalID.hashCode());
      Object $podCIDR = this.getPodCIDR();
      result = result * 59 + ($podCIDR == null ? 43 : $podCIDR.hashCode());
      Object $podCIDRs = this.getPodCIDRs();
      result = result * 59 + ($podCIDRs == null ? 43 : $podCIDRs.hashCode());
      Object $providerID = this.getProviderID();
      result = result * 59 + ($providerID == null ? 43 : $providerID.hashCode());
      Object $taints = this.getTaints();
      result = result * 59 + ($taints == null ? 43 : $taints.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
