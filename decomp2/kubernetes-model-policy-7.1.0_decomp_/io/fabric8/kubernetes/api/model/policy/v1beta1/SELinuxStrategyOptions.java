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
import io.fabric8.kubernetes.api.model.SELinuxOptions;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "rule", "seLinuxOptions"})
public class SELinuxStrategyOptions implements Editable, KubernetesResource {
   @JsonProperty("rule")
   private String rule;
   @JsonProperty("seLinuxOptions")
   private SELinuxOptions seLinuxOptions;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SELinuxStrategyOptions() {
   }

   public SELinuxStrategyOptions(String rule, SELinuxOptions seLinuxOptions) {
      this.rule = rule;
      this.seLinuxOptions = seLinuxOptions;
   }

   @JsonProperty("rule")
   public String getRule() {
      return this.rule;
   }

   @JsonProperty("rule")
   public void setRule(String rule) {
      this.rule = rule;
   }

   @JsonProperty("seLinuxOptions")
   public SELinuxOptions getSeLinuxOptions() {
      return this.seLinuxOptions;
   }

   @JsonProperty("seLinuxOptions")
   public void setSeLinuxOptions(SELinuxOptions seLinuxOptions) {
      this.seLinuxOptions = seLinuxOptions;
   }

   @JsonIgnore
   public SELinuxStrategyOptionsBuilder edit() {
      return new SELinuxStrategyOptionsBuilder(this);
   }

   @JsonIgnore
   public SELinuxStrategyOptionsBuilder toBuilder() {
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
      String var10000 = this.getRule();
      return "SELinuxStrategyOptions(rule=" + var10000 + ", seLinuxOptions=" + this.getSeLinuxOptions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SELinuxStrategyOptions)) {
         return false;
      } else {
         SELinuxStrategyOptions other = (SELinuxStrategyOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$rule = this.getRule();
            Object other$rule = other.getRule();
            if (this$rule == null) {
               if (other$rule != null) {
                  return false;
               }
            } else if (!this$rule.equals(other$rule)) {
               return false;
            }

            Object this$seLinuxOptions = this.getSeLinuxOptions();
            Object other$seLinuxOptions = other.getSeLinuxOptions();
            if (this$seLinuxOptions == null) {
               if (other$seLinuxOptions != null) {
                  return false;
               }
            } else if (!this$seLinuxOptions.equals(other$seLinuxOptions)) {
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
      return other instanceof SELinuxStrategyOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $rule = this.getRule();
      result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
      Object $seLinuxOptions = this.getSeLinuxOptions();
      result = result * 59 + ($seLinuxOptions == null ? 43 : $seLinuxOptions.hashCode());
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
