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
@JsonPropertyOrder({"caCertificateRefs"})
public class FrontendTLSValidation implements Editable, KubernetesResource {
   @JsonProperty("caCertificateRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List caCertificateRefs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FrontendTLSValidation() {
   }

   public FrontendTLSValidation(List caCertificateRefs) {
      this.caCertificateRefs = caCertificateRefs;
   }

   @JsonProperty("caCertificateRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getCaCertificateRefs() {
      return this.caCertificateRefs;
   }

   @JsonProperty("caCertificateRefs")
   public void setCaCertificateRefs(List caCertificateRefs) {
      this.caCertificateRefs = caCertificateRefs;
   }

   @JsonIgnore
   public FrontendTLSValidationBuilder edit() {
      return new FrontendTLSValidationBuilder(this);
   }

   @JsonIgnore
   public FrontendTLSValidationBuilder toBuilder() {
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
      List var10000 = this.getCaCertificateRefs();
      return "FrontendTLSValidation(caCertificateRefs=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FrontendTLSValidation)) {
         return false;
      } else {
         FrontendTLSValidation other = (FrontendTLSValidation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$caCertificateRefs = this.getCaCertificateRefs();
            Object other$caCertificateRefs = other.getCaCertificateRefs();
            if (this$caCertificateRefs == null) {
               if (other$caCertificateRefs != null) {
                  return false;
               }
            } else if (!this$caCertificateRefs.equals(other$caCertificateRefs)) {
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
      return other instanceof FrontendTLSValidation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $caCertificateRefs = this.getCaCertificateRefs();
      result = result * 59 + ($caCertificateRefs == null ? 43 : $caCertificateRefs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
