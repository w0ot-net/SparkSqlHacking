package io.fabric8.kubernetes.api.model.certificates.v1alpha1;

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
@JsonPropertyOrder({"signerName", "trustBundle"})
public class ClusterTrustBundleSpec implements Editable, KubernetesResource {
   @JsonProperty("signerName")
   private String signerName;
   @JsonProperty("trustBundle")
   private String trustBundle;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ClusterTrustBundleSpec() {
   }

   public ClusterTrustBundleSpec(String signerName, String trustBundle) {
      this.signerName = signerName;
      this.trustBundle = trustBundle;
   }

   @JsonProperty("signerName")
   public String getSignerName() {
      return this.signerName;
   }

   @JsonProperty("signerName")
   public void setSignerName(String signerName) {
      this.signerName = signerName;
   }

   @JsonProperty("trustBundle")
   public String getTrustBundle() {
      return this.trustBundle;
   }

   @JsonProperty("trustBundle")
   public void setTrustBundle(String trustBundle) {
      this.trustBundle = trustBundle;
   }

   @JsonIgnore
   public ClusterTrustBundleSpecBuilder edit() {
      return new ClusterTrustBundleSpecBuilder(this);
   }

   @JsonIgnore
   public ClusterTrustBundleSpecBuilder toBuilder() {
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
      String var10000 = this.getSignerName();
      return "ClusterTrustBundleSpec(signerName=" + var10000 + ", trustBundle=" + this.getTrustBundle() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClusterTrustBundleSpec)) {
         return false;
      } else {
         ClusterTrustBundleSpec other = (ClusterTrustBundleSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$signerName = this.getSignerName();
            Object other$signerName = other.getSignerName();
            if (this$signerName == null) {
               if (other$signerName != null) {
                  return false;
               }
            } else if (!this$signerName.equals(other$signerName)) {
               return false;
            }

            Object this$trustBundle = this.getTrustBundle();
            Object other$trustBundle = other.getTrustBundle();
            if (this$trustBundle == null) {
               if (other$trustBundle != null) {
                  return false;
               }
            } else if (!this$trustBundle.equals(other$trustBundle)) {
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
      return other instanceof ClusterTrustBundleSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $signerName = this.getSignerName();
      result = result * 59 + ($signerName == null ? 43 : $signerName.hashCode());
      Object $trustBundle = this.getTrustBundle();
      result = result * 59 + ($trustBundle == null ? 43 : $trustBundle.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
