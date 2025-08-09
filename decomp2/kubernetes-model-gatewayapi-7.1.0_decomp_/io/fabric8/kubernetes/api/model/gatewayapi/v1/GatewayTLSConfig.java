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
@JsonPropertyOrder({"certificateRefs", "frontendValidation", "mode", "options"})
public class GatewayTLSConfig implements Editable, KubernetesResource {
   @JsonProperty("certificateRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List certificateRefs = new ArrayList();
   @JsonProperty("frontendValidation")
   private FrontendTLSValidation frontendValidation;
   @JsonProperty("mode")
   private String mode;
   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   private Map options = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayTLSConfig() {
   }

   public GatewayTLSConfig(List certificateRefs, FrontendTLSValidation frontendValidation, String mode, Map options) {
      this.certificateRefs = certificateRefs;
      this.frontendValidation = frontendValidation;
      this.mode = mode;
      this.options = options;
   }

   @JsonProperty("certificateRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getCertificateRefs() {
      return this.certificateRefs;
   }

   @JsonProperty("certificateRefs")
   public void setCertificateRefs(List certificateRefs) {
      this.certificateRefs = certificateRefs;
   }

   @JsonProperty("frontendValidation")
   public FrontendTLSValidation getFrontendValidation() {
      return this.frontendValidation;
   }

   @JsonProperty("frontendValidation")
   public void setFrontendValidation(FrontendTLSValidation frontendValidation) {
      this.frontendValidation = frontendValidation;
   }

   @JsonProperty("mode")
   public String getMode() {
      return this.mode;
   }

   @JsonProperty("mode")
   public void setMode(String mode) {
      this.mode = mode;
   }

   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   public Map getOptions() {
      return this.options;
   }

   @JsonProperty("options")
   public void setOptions(Map options) {
      this.options = options;
   }

   @JsonIgnore
   public GatewayTLSConfigBuilder edit() {
      return new GatewayTLSConfigBuilder(this);
   }

   @JsonIgnore
   public GatewayTLSConfigBuilder toBuilder() {
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
      List var10000 = this.getCertificateRefs();
      return "GatewayTLSConfig(certificateRefs=" + var10000 + ", frontendValidation=" + this.getFrontendValidation() + ", mode=" + this.getMode() + ", options=" + this.getOptions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayTLSConfig)) {
         return false;
      } else {
         GatewayTLSConfig other = (GatewayTLSConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$certificateRefs = this.getCertificateRefs();
            Object other$certificateRefs = other.getCertificateRefs();
            if (this$certificateRefs == null) {
               if (other$certificateRefs != null) {
                  return false;
               }
            } else if (!this$certificateRefs.equals(other$certificateRefs)) {
               return false;
            }

            Object this$frontendValidation = this.getFrontendValidation();
            Object other$frontendValidation = other.getFrontendValidation();
            if (this$frontendValidation == null) {
               if (other$frontendValidation != null) {
                  return false;
               }
            } else if (!this$frontendValidation.equals(other$frontendValidation)) {
               return false;
            }

            Object this$mode = this.getMode();
            Object other$mode = other.getMode();
            if (this$mode == null) {
               if (other$mode != null) {
                  return false;
               }
            } else if (!this$mode.equals(other$mode)) {
               return false;
            }

            Object this$options = this.getOptions();
            Object other$options = other.getOptions();
            if (this$options == null) {
               if (other$options != null) {
                  return false;
               }
            } else if (!this$options.equals(other$options)) {
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
      return other instanceof GatewayTLSConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $certificateRefs = this.getCertificateRefs();
      result = result * 59 + ($certificateRefs == null ? 43 : $certificateRefs.hashCode());
      Object $frontendValidation = this.getFrontendValidation();
      result = result * 59 + ($frontendValidation == null ? 43 : $frontendValidation.hashCode());
      Object $mode = this.getMode();
      result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
