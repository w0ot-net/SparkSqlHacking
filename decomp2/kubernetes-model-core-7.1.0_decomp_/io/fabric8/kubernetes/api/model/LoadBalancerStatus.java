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
@JsonPropertyOrder({"ingress"})
public class LoadBalancerStatus implements Editable, KubernetesResource {
   @JsonProperty("ingress")
   @JsonInclude(Include.NON_EMPTY)
   private List ingress = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LoadBalancerStatus() {
   }

   public LoadBalancerStatus(List ingress) {
      this.ingress = ingress;
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

   @JsonIgnore
   public LoadBalancerStatusBuilder edit() {
      return new LoadBalancerStatusBuilder(this);
   }

   @JsonIgnore
   public LoadBalancerStatusBuilder toBuilder() {
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
      List var10000 = this.getIngress();
      return "LoadBalancerStatus(ingress=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LoadBalancerStatus)) {
         return false;
      } else {
         LoadBalancerStatus other = (LoadBalancerStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ingress = this.getIngress();
            Object other$ingress = other.getIngress();
            if (this$ingress == null) {
               if (other$ingress != null) {
                  return false;
               }
            } else if (!this$ingress.equals(other$ingress)) {
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
      return other instanceof LoadBalancerStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ingress = this.getIngress();
      result = result * 59 + ($ingress == null ? 43 : $ingress.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
