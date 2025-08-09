package io.fabric8.kubernetes.api.model.certificates.v1beta1;

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
@JsonPropertyOrder({"certificate", "conditions"})
public class CertificateSigningRequestStatus implements Editable, KubernetesResource {
   @JsonProperty("certificate")
   private String certificate;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CertificateSigningRequestStatus() {
   }

   public CertificateSigningRequestStatus(String certificate, List conditions) {
      this.certificate = certificate;
      this.conditions = conditions;
   }

   @JsonProperty("certificate")
   public String getCertificate() {
      return this.certificate;
   }

   @JsonProperty("certificate")
   public void setCertificate(String certificate) {
      this.certificate = certificate;
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

   @JsonIgnore
   public CertificateSigningRequestStatusBuilder edit() {
      return new CertificateSigningRequestStatusBuilder(this);
   }

   @JsonIgnore
   public CertificateSigningRequestStatusBuilder toBuilder() {
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
      String var10000 = this.getCertificate();
      return "CertificateSigningRequestStatus(certificate=" + var10000 + ", conditions=" + this.getConditions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CertificateSigningRequestStatus)) {
         return false;
      } else {
         CertificateSigningRequestStatus other = (CertificateSigningRequestStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$certificate = this.getCertificate();
            Object other$certificate = other.getCertificate();
            if (this$certificate == null) {
               if (other$certificate != null) {
                  return false;
               }
            } else if (!this$certificate.equals(other$certificate)) {
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
      return other instanceof CertificateSigningRequestStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $certificate = this.getCertificate();
      result = result * 59 + ($certificate == null ? 43 : $certificate.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
