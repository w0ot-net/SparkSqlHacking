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
import io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2.LocalPolicyTargetReferenceWithSectionName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"options", "targetRefs", "validation"})
public class BackendTLSPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   private Map options = new LinkedHashMap();
   @JsonProperty("targetRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List targetRefs = new ArrayList();
   @JsonProperty("validation")
   private BackendTLSPolicyValidation validation;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public BackendTLSPolicySpec() {
   }

   public BackendTLSPolicySpec(Map options, List targetRefs, BackendTLSPolicyValidation validation) {
      this.options = options;
      this.targetRefs = targetRefs;
      this.validation = validation;
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

   @JsonProperty("targetRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getTargetRefs() {
      return this.targetRefs;
   }

   @JsonProperty("targetRefs")
   public void setTargetRefs(List targetRefs) {
      this.targetRefs = targetRefs;
   }

   @JsonProperty("validation")
   public BackendTLSPolicyValidation getValidation() {
      return this.validation;
   }

   @JsonProperty("validation")
   public void setValidation(BackendTLSPolicyValidation validation) {
      this.validation = validation;
   }

   @JsonIgnore
   public BackendTLSPolicySpecBuilder edit() {
      return new BackendTLSPolicySpecBuilder(this);
   }

   @JsonIgnore
   public BackendTLSPolicySpecBuilder toBuilder() {
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
      Map var10000 = this.getOptions();
      return "BackendTLSPolicySpec(options=" + var10000 + ", targetRefs=" + this.getTargetRefs() + ", validation=" + this.getValidation() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BackendTLSPolicySpec)) {
         return false;
      } else {
         BackendTLSPolicySpec other = (BackendTLSPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$options = this.getOptions();
            Object other$options = other.getOptions();
            if (this$options == null) {
               if (other$options != null) {
                  return false;
               }
            } else if (!this$options.equals(other$options)) {
               return false;
            }

            Object this$targetRefs = this.getTargetRefs();
            Object other$targetRefs = other.getTargetRefs();
            if (this$targetRefs == null) {
               if (other$targetRefs != null) {
                  return false;
               }
            } else if (!this$targetRefs.equals(other$targetRefs)) {
               return false;
            }

            Object this$validation = this.getValidation();
            Object other$validation = other.getValidation();
            if (this$validation == null) {
               if (other$validation != null) {
                  return false;
               }
            } else if (!this$validation.equals(other$validation)) {
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
      return other instanceof BackendTLSPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      Object $targetRefs = this.getTargetRefs();
      result = result * 59 + ($targetRefs == null ? 43 : $targetRefs.hashCode());
      Object $validation = this.getValidation();
      result = result * 59 + ($validation == null ? 43 : $validation.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
