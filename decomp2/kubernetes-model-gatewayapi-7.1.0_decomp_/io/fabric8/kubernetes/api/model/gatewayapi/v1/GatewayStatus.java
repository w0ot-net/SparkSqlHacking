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
import io.fabric8.kubernetes.api.model.Condition;
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
@JsonPropertyOrder({"addresses", "conditions", "listeners"})
public class GatewayStatus implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("listeners")
   @JsonInclude(Include.NON_EMPTY)
   private List listeners = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayStatus() {
   }

   public GatewayStatus(List addresses, List conditions, List listeners) {
      this.addresses = addresses;
      this.conditions = conditions;
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

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
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
   public GatewayStatusBuilder edit() {
      return new GatewayStatusBuilder(this);
   }

   @JsonIgnore
   public GatewayStatusBuilder toBuilder() {
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
      return "GatewayStatus(addresses=" + var10000 + ", conditions=" + this.getConditions() + ", listeners=" + this.getListeners() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayStatus)) {
         return false;
      } else {
         GatewayStatus other = (GatewayStatus)o;
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

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
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
      return other instanceof GatewayStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $listeners = this.getListeners();
      result = result * 59 + ($listeners == null ? 43 : $listeners.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
