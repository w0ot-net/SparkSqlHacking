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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"configMapRef", "prefix", "secretRef"})
public class EnvFromSource implements Editable, KubernetesResource {
   @JsonProperty("configMapRef")
   private ConfigMapEnvSource configMapRef;
   @JsonProperty("prefix")
   private String prefix;
   @JsonProperty("secretRef")
   private SecretEnvSource secretRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EnvFromSource() {
   }

   public EnvFromSource(ConfigMapEnvSource configMapRef, String prefix, SecretEnvSource secretRef) {
      this.configMapRef = configMapRef;
      this.prefix = prefix;
      this.secretRef = secretRef;
   }

   @JsonProperty("configMapRef")
   public ConfigMapEnvSource getConfigMapRef() {
      return this.configMapRef;
   }

   @JsonProperty("configMapRef")
   public void setConfigMapRef(ConfigMapEnvSource configMapRef) {
      this.configMapRef = configMapRef;
   }

   @JsonProperty("prefix")
   public String getPrefix() {
      return this.prefix;
   }

   @JsonProperty("prefix")
   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   @JsonProperty("secretRef")
   public SecretEnvSource getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(SecretEnvSource secretRef) {
      this.secretRef = secretRef;
   }

   @JsonIgnore
   public EnvFromSourceBuilder edit() {
      return new EnvFromSourceBuilder(this);
   }

   @JsonIgnore
   public EnvFromSourceBuilder toBuilder() {
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
      ConfigMapEnvSource var10000 = this.getConfigMapRef();
      return "EnvFromSource(configMapRef=" + var10000 + ", prefix=" + this.getPrefix() + ", secretRef=" + this.getSecretRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EnvFromSource)) {
         return false;
      } else {
         EnvFromSource other = (EnvFromSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$configMapRef = this.getConfigMapRef();
            Object other$configMapRef = other.getConfigMapRef();
            if (this$configMapRef == null) {
               if (other$configMapRef != null) {
                  return false;
               }
            } else if (!this$configMapRef.equals(other$configMapRef)) {
               return false;
            }

            Object this$prefix = this.getPrefix();
            Object other$prefix = other.getPrefix();
            if (this$prefix == null) {
               if (other$prefix != null) {
                  return false;
               }
            } else if (!this$prefix.equals(other$prefix)) {
               return false;
            }

            Object this$secretRef = this.getSecretRef();
            Object other$secretRef = other.getSecretRef();
            if (this$secretRef == null) {
               if (other$secretRef != null) {
                  return false;
               }
            } else if (!this$secretRef.equals(other$secretRef)) {
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
      return other instanceof EnvFromSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $configMapRef = this.getConfigMapRef();
      result = result * 59 + ($configMapRef == null ? 43 : $configMapRef.hashCode());
      Object $prefix = this.getPrefix();
      result = result * 59 + ($prefix == null ? 43 : $prefix.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
