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
@JsonPropertyOrder({"addresses", "notReadyAddresses", "ports"})
public class EndpointSubset implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("notReadyAddresses")
   @JsonInclude(Include.NON_EMPTY)
   private List notReadyAddresses = new ArrayList();
   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   private List ports = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EndpointSubset() {
   }

   public EndpointSubset(List addresses, List notReadyAddresses, List ports) {
      this.addresses = addresses;
      this.notReadyAddresses = notReadyAddresses;
      this.ports = ports;
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

   @JsonProperty("notReadyAddresses")
   @JsonInclude(Include.NON_EMPTY)
   public List getNotReadyAddresses() {
      return this.notReadyAddresses;
   }

   @JsonProperty("notReadyAddresses")
   public void setNotReadyAddresses(List notReadyAddresses) {
      this.notReadyAddresses = notReadyAddresses;
   }

   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   public List getPorts() {
      return this.ports;
   }

   @JsonProperty("ports")
   public void setPorts(List ports) {
      this.ports = ports;
   }

   @JsonIgnore
   public EndpointSubsetBuilder edit() {
      return new EndpointSubsetBuilder(this);
   }

   @JsonIgnore
   public EndpointSubsetBuilder toBuilder() {
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
      return "EndpointSubset(addresses=" + var10000 + ", notReadyAddresses=" + this.getNotReadyAddresses() + ", ports=" + this.getPorts() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EndpointSubset)) {
         return false;
      } else {
         EndpointSubset other = (EndpointSubset)o;
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

            Object this$notReadyAddresses = this.getNotReadyAddresses();
            Object other$notReadyAddresses = other.getNotReadyAddresses();
            if (this$notReadyAddresses == null) {
               if (other$notReadyAddresses != null) {
                  return false;
               }
            } else if (!this$notReadyAddresses.equals(other$notReadyAddresses)) {
               return false;
            }

            Object this$ports = this.getPorts();
            Object other$ports = other.getPorts();
            if (this$ports == null) {
               if (other$ports != null) {
                  return false;
               }
            } else if (!this$ports.equals(other$ports)) {
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
      return other instanceof EndpointSubset;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $notReadyAddresses = this.getNotReadyAddresses();
      result = result * 59 + ($notReadyAddresses == null ? 43 : $notReadyAddresses.hashCode());
      Object $ports = this.getPorts();
      result = result * 59 + ($ports == null ? 43 : $ports.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
