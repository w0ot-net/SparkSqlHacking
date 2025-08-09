package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"addresses", "backendTLS", "gatewayClassName", "infrastructure", "listeners"})
public class GatewaySpec implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("backendTLS")
   private GatewayBackendTLS backendTLS;
   @JsonProperty("gatewayClassName")
   private String gatewayClassName;
   @JsonProperty("infrastructure")
   private GatewayInfrastructure infrastructure;
   @JsonProperty("listeners")
   @JsonInclude(Include.NON_EMPTY)
   private List listeners = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewaySpec() {
   }

   public GatewaySpec(List addresses, GatewayBackendTLS backendTLS, String gatewayClassName, GatewayInfrastructure infrastructure, List listeners) {
      this.addresses = addresses;
      this.backendTLS = backendTLS;
      this.gatewayClassName = gatewayClassName;
      this.infrastructure = infrastructure;
      this.listeners = listeners;
   }

   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   public List getAddresses() {
      return this.addresses;
   }

   @JsonProperty("addresses")
   public void setAddresses(List addresses) {
      this.addresses = addresses;
   }

   @JsonProperty("backendTLS")
   public GatewayBackendTLS getBackendTLS() {
      return this.backendTLS;
   }

   @JsonProperty("backendTLS")
   public void setBackendTLS(GatewayBackendTLS backendTLS) {
      this.backendTLS = backendTLS;
   }

   @JsonProperty("gatewayClassName")
   public String getGatewayClassName() {
      return this.gatewayClassName;
   }

   @JsonProperty("gatewayClassName")
   public void setGatewayClassName(String gatewayClassName) {
      this.gatewayClassName = gatewayClassName;
   }

   @JsonProperty("infrastructure")
   public GatewayInfrastructure getInfrastructure() {
      return this.infrastructure;
   }

   @JsonProperty("infrastructure")
   public void setInfrastructure(GatewayInfrastructure infrastructure) {
      this.infrastructure = infrastructure;
   }

   @JsonProperty("listeners")
   @JsonInclude(Include.NON_EMPTY)
   public List getListeners() {
      return this.listeners;
   }

   @JsonProperty("listeners")
   public void setListeners(List listeners) {
      this.listeners = listeners;
   }

   @JsonIgnore
   public GatewaySpecBuilder edit() {
      return new GatewaySpecBuilder(this);
   }

   @JsonIgnore
   public GatewaySpecBuilder toBuilder() {
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
      List var10000 = this.getAddresses();
      return "GatewaySpec(addresses=" + var10000 + ", backendTLS=" + this.getBackendTLS() + ", gatewayClassName=" + this.getGatewayClassName() + ", infrastructure=" + this.getInfrastructure() + ", listeners=" + this.getListeners() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewaySpec)) {
         return false;
      } else {
         GatewaySpec other = (GatewaySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$addresses = this.getAddresses();
            Object other$addresses = other.getAddresses();
            if (this$addresses == null) {
               if (other$addresses != null) {
                  return false;
               }
            } else if (!this$addresses.equals(other$addresses)) {
               return false;
            }

            Object this$backendTLS = this.getBackendTLS();
            Object other$backendTLS = other.getBackendTLS();
            if (this$backendTLS == null) {
               if (other$backendTLS != null) {
                  return false;
               }
            } else if (!this$backendTLS.equals(other$backendTLS)) {
               return false;
            }

            Object this$gatewayClassName = this.getGatewayClassName();
            Object other$gatewayClassName = other.getGatewayClassName();
            if (this$gatewayClassName == null) {
               if (other$gatewayClassName != null) {
                  return false;
               }
            } else if (!this$gatewayClassName.equals(other$gatewayClassName)) {
               return false;
            }

            Object this$infrastructure = this.getInfrastructure();
            Object other$infrastructure = other.getInfrastructure();
            if (this$infrastructure == null) {
               if (other$infrastructure != null) {
                  return false;
               }
            } else if (!this$infrastructure.equals(other$infrastructure)) {
               return false;
            }

            Object this$listeners = this.getListeners();
            Object other$listeners = other.getListeners();
            if (this$listeners == null) {
               if (other$listeners != null) {
                  return false;
               }
            } else if (!this$listeners.equals(other$listeners)) {
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
      return other instanceof GatewaySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $backendTLS = this.getBackendTLS();
      result = result * 59 + ($backendTLS == null ? 43 : $backendTLS.hashCode());
      Object $gatewayClassName = this.getGatewayClassName();
      result = result * 59 + ($gatewayClassName == null ? 43 : $gatewayClassName.hashCode());
      Object $infrastructure = this.getInfrastructure();
      result = result * 59 + ($infrastructure == null ? 43 : $infrastructure.hashCode());
      Object $listeners = this.getListeners();
      result = result * 59 + ($listeners == null ? 43 : $listeners.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
