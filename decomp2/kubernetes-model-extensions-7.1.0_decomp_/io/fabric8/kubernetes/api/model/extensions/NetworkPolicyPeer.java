package io.fabric8.kubernetes.api.model.extensions;

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
import io.fabric8.kubernetes.api.model.LabelSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"ipBlock", "namespaceSelector", "podSelector"})
public class NetworkPolicyPeer implements Editable, KubernetesResource {
   @JsonProperty("ipBlock")
   private IPBlock ipBlock;
   @JsonProperty("namespaceSelector")
   private LabelSelector namespaceSelector;
   @JsonProperty("podSelector")
   private LabelSelector podSelector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NetworkPolicyPeer() {
   }

   public NetworkPolicyPeer(IPBlock ipBlock, LabelSelector namespaceSelector, LabelSelector podSelector) {
      this.ipBlock = ipBlock;
      this.namespaceSelector = namespaceSelector;
      this.podSelector = podSelector;
   }

   @JsonProperty("ipBlock")
   public IPBlock getIpBlock() {
      return this.ipBlock;
   }

   @JsonProperty("ipBlock")
   public void setIpBlock(IPBlock ipBlock) {
      this.ipBlock = ipBlock;
   }

   @JsonProperty("namespaceSelector")
   public LabelSelector getNamespaceSelector() {
      return this.namespaceSelector;
   }

   @JsonProperty("namespaceSelector")
   public void setNamespaceSelector(LabelSelector namespaceSelector) {
      this.namespaceSelector = namespaceSelector;
   }

   @JsonProperty("podSelector")
   public LabelSelector getPodSelector() {
      return this.podSelector;
   }

   @JsonProperty("podSelector")
   public void setPodSelector(LabelSelector podSelector) {
      this.podSelector = podSelector;
   }

   @JsonIgnore
   public NetworkPolicyPeerBuilder edit() {
      return new NetworkPolicyPeerBuilder(this);
   }

   @JsonIgnore
   public NetworkPolicyPeerBuilder toBuilder() {
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
      IPBlock var10000 = this.getIpBlock();
      return "NetworkPolicyPeer(ipBlock=" + var10000 + ", namespaceSelector=" + this.getNamespaceSelector() + ", podSelector=" + this.getPodSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkPolicyPeer)) {
         return false;
      } else {
         NetworkPolicyPeer other = (NetworkPolicyPeer)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ipBlock = this.getIpBlock();
            Object other$ipBlock = other.getIpBlock();
            if (this$ipBlock == null) {
               if (other$ipBlock != null) {
                  return false;
               }
            } else if (!this$ipBlock.equals(other$ipBlock)) {
               return false;
            }

            Object this$namespaceSelector = this.getNamespaceSelector();
            Object other$namespaceSelector = other.getNamespaceSelector();
            if (this$namespaceSelector == null) {
               if (other$namespaceSelector != null) {
                  return false;
               }
            } else if (!this$namespaceSelector.equals(other$namespaceSelector)) {
               return false;
            }

            Object this$podSelector = this.getPodSelector();
            Object other$podSelector = other.getPodSelector();
            if (this$podSelector == null) {
               if (other$podSelector != null) {
                  return false;
               }
            } else if (!this$podSelector.equals(other$podSelector)) {
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
      return other instanceof NetworkPolicyPeer;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ipBlock = this.getIpBlock();
      result = result * 59 + ($ipBlock == null ? 43 : $ipBlock.hashCode());
      Object $namespaceSelector = this.getNamespaceSelector();
      result = result * 59 + ($namespaceSelector == null ? 43 : $namespaceSelector.hashCode());
      Object $podSelector = this.getPodSelector();
      result = result * 59 + ($podSelector == null ? 43 : $podSelector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
