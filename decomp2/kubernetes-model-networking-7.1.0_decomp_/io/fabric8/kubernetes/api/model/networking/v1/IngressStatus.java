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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"loadBalancer"})
public class IngressStatus implements Editable, KubernetesResource {
   @JsonProperty("loadBalancer")
   private IngressLoadBalancerStatus loadBalancer;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressStatus() {
   }

   public IngressStatus(IngressLoadBalancerStatus loadBalancer) {
      this.loadBalancer = loadBalancer;
   }

   @JsonProperty("loadBalancer")
   public IngressLoadBalancerStatus getLoadBalancer() {
      return this.loadBalancer;
   }

   @JsonProperty("loadBalancer")
   public void setLoadBalancer(IngressLoadBalancerStatus loadBalancer) {
      this.loadBalancer = loadBalancer;
   }

   @JsonIgnore
   public IngressStatusBuilder edit() {
      return new IngressStatusBuilder(this);
   }

   @JsonIgnore
   public IngressStatusBuilder toBuilder() {
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
      IngressLoadBalancerStatus var10000 = this.getLoadBalancer();
      return "IngressStatus(loadBalancer=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressStatus)) {
         return false;
      } else {
         IngressStatus other = (IngressStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$loadBalancer = this.getLoadBalancer();
            Object other$loadBalancer = other.getLoadBalancer();
            if (this$loadBalancer == null) {
               if (other$loadBalancer != null) {
                  return false;
               }
            } else if (!this$loadBalancer.equals(other$loadBalancer)) {
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
      return other instanceof IngressStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $loadBalancer = this.getLoadBalancer();
      result = result * 59 + ($loadBalancer == null ? 43 : $loadBalancer.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
