package io.fabric8.kubernetes.api.model.networking.v1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"egress", "ingress", "podSelector", "policyTypes"})
public class NetworkPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("egress")
   @JsonInclude(Include.NON_EMPTY)
   private List egress = new ArrayList();
   @JsonProperty("ingress")
   @JsonInclude(Include.NON_EMPTY)
   private List ingress = new ArrayList();
   @JsonProperty("podSelector")
   private LabelSelector podSelector;
   @JsonProperty("policyTypes")
   @JsonInclude(Include.NON_EMPTY)
   private List policyTypes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NetworkPolicySpec() {
   }

   public NetworkPolicySpec(List egress, List ingress, LabelSelector podSelector, List policyTypes) {
      this.egress = egress;
      this.ingress = ingress;
      this.podSelector = podSelector;
      this.policyTypes = policyTypes;
   }

   @JsonProperty("egress")
   @JsonInclude(Include.NON_EMPTY)
   public List getEgress() {
      return this.egress;
   }

   @JsonProperty("egress")
   public void setEgress(List egress) {
      this.egress = egress;
   }

   @JsonProperty("ingress")
   @JsonInclude(Include.NON_EMPTY)
   public List getIngress() {
      return this.ingress;
   }

   @JsonProperty("ingress")
   public void setIngress(List ingress) {
      this.ingress = ingress;
   }

   @JsonProperty("podSelector")
   public LabelSelector getPodSelector() {
      return this.podSelector;
   }

   @JsonProperty("podSelector")
   public void setPodSelector(LabelSelector podSelector) {
      this.podSelector = podSelector;
   }

   @JsonProperty("policyTypes")
   @JsonInclude(Include.NON_EMPTY)
   public List getPolicyTypes() {
      return this.policyTypes;
   }

   @JsonProperty("policyTypes")
   public void setPolicyTypes(List policyTypes) {
      this.policyTypes = policyTypes;
   }

   @JsonIgnore
   public NetworkPolicySpecBuilder edit() {
      return new NetworkPolicySpecBuilder(this);
   }

   @JsonIgnore
   public NetworkPolicySpecBuilder toBuilder() {
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
      List var10000 = this.getEgress();
      return "NetworkPolicySpec(egress=" + var10000 + ", ingress=" + this.getIngress() + ", podSelector=" + this.getPodSelector() + ", policyTypes=" + this.getPolicyTypes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkPolicySpec)) {
         return false;
      } else {
         NetworkPolicySpec other = (NetworkPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$egress = this.getEgress();
            Object other$egress = other.getEgress();
            if (this$egress == null) {
               if (other$egress != null) {
                  return false;
               }
            } else if (!this$egress.equals(other$egress)) {
               return false;
            }

            Object this$ingress = this.getIngress();
            Object other$ingress = other.getIngress();
            if (this$ingress == null) {
               if (other$ingress != null) {
                  return false;
               }
            } else if (!this$ingress.equals(other$ingress)) {
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

            Object this$policyTypes = this.getPolicyTypes();
            Object other$policyTypes = other.getPolicyTypes();
            if (this$policyTypes == null) {
               if (other$policyTypes != null) {
                  return false;
               }
            } else if (!this$policyTypes.equals(other$policyTypes)) {
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
      return other instanceof NetworkPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $egress = this.getEgress();
      result = result * 59 + ($egress == null ? 43 : $egress.hashCode());
      Object $ingress = this.getIngress();
      result = result * 59 + ($ingress == null ? 43 : $ingress.hashCode());
      Object $podSelector = this.getPodSelector();
      result = result * 59 + ($podSelector == null ? 43 : $podSelector.hashCode());
      Object $policyTypes = this.getPolicyTypes();
      result = result * 59 + ($policyTypes == null ? 43 : $policyTypes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
