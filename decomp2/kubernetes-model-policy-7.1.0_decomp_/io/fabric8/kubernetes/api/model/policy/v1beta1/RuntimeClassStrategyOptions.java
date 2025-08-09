package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "allowedRuntimeClassNames", "defaultRuntimeClassName"})
public class RuntimeClassStrategyOptions implements Editable, KubernetesResource {
   @JsonProperty("allowedRuntimeClassNames")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedRuntimeClassNames = new ArrayList();
   @JsonProperty("defaultRuntimeClassName")
   private String defaultRuntimeClassName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RuntimeClassStrategyOptions() {
   }

   public RuntimeClassStrategyOptions(List allowedRuntimeClassNames, String defaultRuntimeClassName) {
      this.allowedRuntimeClassNames = allowedRuntimeClassNames;
      this.defaultRuntimeClassName = defaultRuntimeClassName;
   }

   @JsonProperty("allowedRuntimeClassNames")
   public List getAllowedRuntimeClassNames() {
      return this.allowedRuntimeClassNames;
   }

   @JsonProperty("allowedRuntimeClassNames")
   public void setAllowedRuntimeClassNames(List allowedRuntimeClassNames) {
      this.allowedRuntimeClassNames = allowedRuntimeClassNames;
   }

   @JsonProperty("defaultRuntimeClassName")
   public String getDefaultRuntimeClassName() {
      return this.defaultRuntimeClassName;
   }

   @JsonProperty("defaultRuntimeClassName")
   public void setDefaultRuntimeClassName(String defaultRuntimeClassName) {
      this.defaultRuntimeClassName = defaultRuntimeClassName;
   }

   @JsonIgnore
   public RuntimeClassStrategyOptionsBuilder edit() {
      return new RuntimeClassStrategyOptionsBuilder(this);
   }

   @JsonIgnore
   public RuntimeClassStrategyOptionsBuilder toBuilder() {
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

   @Generated
   public String toString() {
      List var10000 = this.getAllowedRuntimeClassNames();
      return "RuntimeClassStrategyOptions(allowedRuntimeClassNames=" + var10000 + ", defaultRuntimeClassName=" + this.getDefaultRuntimeClassName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RuntimeClassStrategyOptions)) {
         return false;
      } else {
         RuntimeClassStrategyOptions other = (RuntimeClassStrategyOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allowedRuntimeClassNames = this.getAllowedRuntimeClassNames();
            Object other$allowedRuntimeClassNames = other.getAllowedRuntimeClassNames();
            if (this$allowedRuntimeClassNames == null) {
               if (other$allowedRuntimeClassNames != null) {
                  return false;
               }
            } else if (!this$allowedRuntimeClassNames.equals(other$allowedRuntimeClassNames)) {
               return false;
            }

            Object this$defaultRuntimeClassName = this.getDefaultRuntimeClassName();
            Object other$defaultRuntimeClassName = other.getDefaultRuntimeClassName();
            if (this$defaultRuntimeClassName == null) {
               if (other$defaultRuntimeClassName != null) {
                  return false;
               }
            } else if (!this$defaultRuntimeClassName.equals(other$defaultRuntimeClassName)) {
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
      return other instanceof RuntimeClassStrategyOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowedRuntimeClassNames = this.getAllowedRuntimeClassNames();
      result = result * 59 + ($allowedRuntimeClassNames == null ? 43 : $allowedRuntimeClassNames.hashCode());
      Object $defaultRuntimeClassName = this.getDefaultRuntimeClassName();
      result = result * 59 + ($defaultRuntimeClassName == null ? 43 : $defaultRuntimeClassName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }
}
