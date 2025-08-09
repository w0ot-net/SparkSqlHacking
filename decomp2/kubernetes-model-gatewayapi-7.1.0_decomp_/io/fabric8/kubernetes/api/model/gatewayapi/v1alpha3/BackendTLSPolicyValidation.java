package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

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
import io.fabric8.kubernetes.api.model.gatewayapi.v1.LocalObjectReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"caCertificateRefs", "hostname", "subjectAltNames", "wellKnownCACertificates"})
public class BackendTLSPolicyValidation implements Editable, KubernetesResource {
   @JsonProperty("caCertificateRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List caCertificateRefs = new ArrayList();
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("subjectAltNames")
   @JsonInclude(Include.NON_EMPTY)
   private List subjectAltNames = new ArrayList();
   @JsonProperty("wellKnownCACertificates")
   private String wellKnownCACertificates;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public BackendTLSPolicyValidation() {
   }

   public BackendTLSPolicyValidation(List caCertificateRefs, String hostname, List subjectAltNames, String wellKnownCACertificates) {
      this.caCertificateRefs = caCertificateRefs;
      this.hostname = hostname;
      this.subjectAltNames = subjectAltNames;
      this.wellKnownCACertificates = wellKnownCACertificates;
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

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("subjectAltNames")
   @JsonInclude(Include.NON_EMPTY)
   public List getSubjectAltNames() {
      return this.subjectAltNames;
   }

   @JsonProperty("subjectAltNames")
   public void setSubjectAltNames(List subjectAltNames) {
      this.subjectAltNames = subjectAltNames;
   }

   @JsonProperty("wellKnownCACertificates")
   public String getWellKnownCACertificates() {
      return this.wellKnownCACertificates;
   }

   @JsonProperty("wellKnownCACertificates")
   public void setWellKnownCACertificates(String wellKnownCACertificates) {
      this.wellKnownCACertificates = wellKnownCACertificates;
   }

   @JsonIgnore
   public BackendTLSPolicyValidationBuilder edit() {
      return new BackendTLSPolicyValidationBuilder(this);
   }

   @JsonIgnore
   public BackendTLSPolicyValidationBuilder toBuilder() {
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
      return "BackendTLSPolicyValidation(caCertificateRefs=" + var10000 + ", hostname=" + this.getHostname() + ", subjectAltNames=" + this.getSubjectAltNames() + ", wellKnownCACertificates=" + this.getWellKnownCACertificates() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BackendTLSPolicyValidation)) {
         return false;
      } else {
         BackendTLSPolicyValidation other = (BackendTLSPolicyValidation)o;
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

            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
               return false;
            }

            Object this$subjectAltNames = this.getSubjectAltNames();
            Object other$subjectAltNames = other.getSubjectAltNames();
            if (this$subjectAltNames == null) {
               if (other$subjectAltNames != null) {
                  return false;
               }
            } else if (!this$subjectAltNames.equals(other$subjectAltNames)) {
               return false;
            }

            Object this$wellKnownCACertificates = this.getWellKnownCACertificates();
            Object other$wellKnownCACertificates = other.getWellKnownCACertificates();
            if (this$wellKnownCACertificates == null) {
               if (other$wellKnownCACertificates != null) {
                  return false;
               }
            } else if (!this$wellKnownCACertificates.equals(other$wellKnownCACertificates)) {
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
      return other instanceof BackendTLSPolicyValidation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $caCertificateRefs = this.getCaCertificateRefs();
      result = result * 59 + ($caCertificateRefs == null ? 43 : $caCertificateRefs.hashCode());
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $subjectAltNames = this.getSubjectAltNames();
      result = result * 59 + ($subjectAltNames == null ? 43 : $subjectAltNames.hashCode());
      Object $wellKnownCACertificates = this.getWellKnownCACertificates();
      result = result * 59 + ($wellKnownCACertificates == null ? 43 : $wellKnownCACertificates.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
