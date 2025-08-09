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
@JsonPropertyOrder({"configMapKeyRef", "fieldRef", "resourceFieldRef", "secretKeyRef"})
public class EnvVarSource implements Editable, KubernetesResource {
   @JsonProperty("configMapKeyRef")
   private ConfigMapKeySelector configMapKeyRef;
   @JsonProperty("fieldRef")
   private ObjectFieldSelector fieldRef;
   @JsonProperty("resourceFieldRef")
   private ResourceFieldSelector resourceFieldRef;
   @JsonProperty("secretKeyRef")
   private SecretKeySelector secretKeyRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EnvVarSource() {
   }

   public EnvVarSource(ConfigMapKeySelector configMapKeyRef, ObjectFieldSelector fieldRef, ResourceFieldSelector resourceFieldRef, SecretKeySelector secretKeyRef) {
      this.configMapKeyRef = configMapKeyRef;
      this.fieldRef = fieldRef;
      this.resourceFieldRef = resourceFieldRef;
      this.secretKeyRef = secretKeyRef;
   }

   @JsonProperty("configMapKeyRef")
   public ConfigMapKeySelector getConfigMapKeyRef() {
      return this.configMapKeyRef;
   }

   @JsonProperty("configMapKeyRef")
   public void setConfigMapKeyRef(ConfigMapKeySelector configMapKeyRef) {
      this.configMapKeyRef = configMapKeyRef;
   }

   @JsonProperty("fieldRef")
   public ObjectFieldSelector getFieldRef() {
      return this.fieldRef;
   }

   @JsonProperty("fieldRef")
   public void setFieldRef(ObjectFieldSelector fieldRef) {
      this.fieldRef = fieldRef;
   }

   @JsonProperty("resourceFieldRef")
   public ResourceFieldSelector getResourceFieldRef() {
      return this.resourceFieldRef;
   }

   @JsonProperty("resourceFieldRef")
   public void setResourceFieldRef(ResourceFieldSelector resourceFieldRef) {
      this.resourceFieldRef = resourceFieldRef;
   }

   @JsonProperty("secretKeyRef")
   public SecretKeySelector getSecretKeyRef() {
      return this.secretKeyRef;
   }

   @JsonProperty("secretKeyRef")
   public void setSecretKeyRef(SecretKeySelector secretKeyRef) {
      this.secretKeyRef = secretKeyRef;
   }

   @JsonIgnore
   public EnvVarSourceBuilder edit() {
      return new EnvVarSourceBuilder(this);
   }

   @JsonIgnore
   public EnvVarSourceBuilder toBuilder() {
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
      ConfigMapKeySelector var10000 = this.getConfigMapKeyRef();
      return "EnvVarSource(configMapKeyRef=" + var10000 + ", fieldRef=" + this.getFieldRef() + ", resourceFieldRef=" + this.getResourceFieldRef() + ", secretKeyRef=" + this.getSecretKeyRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EnvVarSource)) {
         return false;
      } else {
         EnvVarSource other = (EnvVarSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$configMapKeyRef = this.getConfigMapKeyRef();
            Object other$configMapKeyRef = other.getConfigMapKeyRef();
            if (this$configMapKeyRef == null) {
               if (other$configMapKeyRef != null) {
                  return false;
               }
            } else if (!this$configMapKeyRef.equals(other$configMapKeyRef)) {
               return false;
            }

            Object this$fieldRef = this.getFieldRef();
            Object other$fieldRef = other.getFieldRef();
            if (this$fieldRef == null) {
               if (other$fieldRef != null) {
                  return false;
               }
            } else if (!this$fieldRef.equals(other$fieldRef)) {
               return false;
            }

            Object this$resourceFieldRef = this.getResourceFieldRef();
            Object other$resourceFieldRef = other.getResourceFieldRef();
            if (this$resourceFieldRef == null) {
               if (other$resourceFieldRef != null) {
                  return false;
               }
            } else if (!this$resourceFieldRef.equals(other$resourceFieldRef)) {
               return false;
            }

            Object this$secretKeyRef = this.getSecretKeyRef();
            Object other$secretKeyRef = other.getSecretKeyRef();
            if (this$secretKeyRef == null) {
               if (other$secretKeyRef != null) {
                  return false;
               }
            } else if (!this$secretKeyRef.equals(other$secretKeyRef)) {
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
      return other instanceof EnvVarSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $configMapKeyRef = this.getConfigMapKeyRef();
      result = result * 59 + ($configMapKeyRef == null ? 43 : $configMapKeyRef.hashCode());
      Object $fieldRef = this.getFieldRef();
      result = result * 59 + ($fieldRef == null ? 43 : $fieldRef.hashCode());
      Object $resourceFieldRef = this.getResourceFieldRef();
      result = result * 59 + ($resourceFieldRef == null ? 43 : $resourceFieldRef.hashCode());
      Object $secretKeyRef = this.getSecretKeyRef();
      result = result * 59 + ($secretKeyRef == null ? 43 : $secretKeyRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
