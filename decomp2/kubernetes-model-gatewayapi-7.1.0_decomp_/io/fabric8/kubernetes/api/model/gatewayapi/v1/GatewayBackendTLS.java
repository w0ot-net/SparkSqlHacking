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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"clientCertificateRef"})
public class GatewayBackendTLS implements Editable, KubernetesResource {
   @JsonProperty("clientCertificateRef")
   private SecretObjectReference clientCertificateRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayBackendTLS() {
   }

   public GatewayBackendTLS(SecretObjectReference clientCertificateRef) {
      this.clientCertificateRef = clientCertificateRef;
   }

   @JsonProperty("clientCertificateRef")
   public SecretObjectReference getClientCertificateRef() {
      return this.clientCertificateRef;
   }

   @JsonProperty("clientCertificateRef")
   public void setClientCertificateRef(SecretObjectReference clientCertificateRef) {
      this.clientCertificateRef = clientCertificateRef;
   }

   @JsonIgnore
   public GatewayBackendTLSBuilder edit() {
      return new GatewayBackendTLSBuilder(this);
   }

   @JsonIgnore
   public GatewayBackendTLSBuilder toBuilder() {
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
      SecretObjectReference var10000 = this.getClientCertificateRef();
      return "GatewayBackendTLS(clientCertificateRef=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayBackendTLS)) {
         return false;
      } else {
         GatewayBackendTLS other = (GatewayBackendTLS)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clientCertificateRef = this.getClientCertificateRef();
            Object other$clientCertificateRef = other.getClientCertificateRef();
            if (this$clientCertificateRef == null) {
               if (other$clientCertificateRef != null) {
                  return false;
               }
            } else if (!this$clientCertificateRef.equals(other$clientCertificateRef)) {
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
      return other instanceof GatewayBackendTLS;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clientCertificateRef = this.getClientCertificateRef();
      result = result * 59 + ($clientCertificateRef == null ? 43 : $clientCertificateRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
