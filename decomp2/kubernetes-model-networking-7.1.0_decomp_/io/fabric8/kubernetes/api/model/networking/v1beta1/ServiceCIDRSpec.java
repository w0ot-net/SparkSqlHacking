package io.fabric8.kubernetes.api.model.networking.v1beta1;

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
@JsonPropertyOrder({"cidrs"})
public class ServiceCIDRSpec implements Editable, KubernetesResource {
   @JsonProperty("cidrs")
   @JsonInclude(Include.NON_EMPTY)
   private List cidrs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ServiceCIDRSpec() {
   }

   public ServiceCIDRSpec(List cidrs) {
      this.cidrs = cidrs;
   }

   @JsonProperty("cidrs")
   @JsonInclude(Include.NON_EMPTY)
   public List getCidrs() {
      return this.cidrs;
   }

   @JsonProperty("cidrs")
   public void setCidrs(List cidrs) {
      this.cidrs = cidrs;
   }

   @JsonIgnore
   public ServiceCIDRSpecBuilder edit() {
      return new ServiceCIDRSpecBuilder(this);
   }

   @JsonIgnore
   public ServiceCIDRSpecBuilder toBuilder() {
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
      List var10000 = this.getCidrs();
      return "ServiceCIDRSpec(cidrs=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServiceCIDRSpec)) {
         return false;
      } else {
         ServiceCIDRSpec other = (ServiceCIDRSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$cidrs = this.getCidrs();
            Object other$cidrs = other.getCidrs();
            if (this$cidrs == null) {
               if (other$cidrs != null) {
                  return false;
               }
            } else if (!this$cidrs.equals(other$cidrs)) {
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
      return other instanceof ServiceCIDRSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $cidrs = this.getCidrs();
      result = result * 59 + ($cidrs == null ? 43 : $cidrs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
