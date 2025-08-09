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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"defaultMode", "items", "optional", "secretName"})
public class SecretVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("defaultMode")
   private Integer defaultMode;
   @JsonProperty("items")
   @JsonInclude(Include.NON_EMPTY)
   private List items = new ArrayList();
   @JsonProperty("optional")
   private Boolean optional;
   @JsonProperty("secretName")
   private String secretName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SecretVolumeSource() {
   }

   public SecretVolumeSource(Integer defaultMode, List items, Boolean optional, String secretName) {
      this.defaultMode = defaultMode;
      this.items = items;
      this.optional = optional;
      this.secretName = secretName;
   }

   @JsonProperty("defaultMode")
   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   @JsonProperty("defaultMode")
   public void setDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
   }

   @JsonProperty("items")
   @JsonInclude(Include.NON_EMPTY)
   public List getItems() {
      return this.items;
   }

   @JsonProperty("items")
   public void setItems(List items) {
      this.items = items;
   }

   @JsonProperty("optional")
   public Boolean getOptional() {
      return this.optional;
   }

   @JsonProperty("optional")
   public void setOptional(Boolean optional) {
      this.optional = optional;
   }

   @JsonProperty("secretName")
   public String getSecretName() {
      return this.secretName;
   }

   @JsonProperty("secretName")
   public void setSecretName(String secretName) {
      this.secretName = secretName;
   }

   @JsonIgnore
   public SecretVolumeSourceBuilder edit() {
      return new SecretVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public SecretVolumeSourceBuilder toBuilder() {
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
      Integer var10000 = this.getDefaultMode();
      return "SecretVolumeSource(defaultMode=" + var10000 + ", items=" + this.getItems() + ", optional=" + this.getOptional() + ", secretName=" + this.getSecretName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SecretVolumeSource)) {
         return false;
      } else {
         SecretVolumeSource other = (SecretVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$defaultMode = this.getDefaultMode();
            Object other$defaultMode = other.getDefaultMode();
            if (this$defaultMode == null) {
               if (other$defaultMode != null) {
                  return false;
               }
            } else if (!this$defaultMode.equals(other$defaultMode)) {
               return false;
            }

            Object this$optional = this.getOptional();
            Object other$optional = other.getOptional();
            if (this$optional == null) {
               if (other$optional != null) {
                  return false;
               }
            } else if (!this$optional.equals(other$optional)) {
               return false;
            }

            Object this$items = this.getItems();
            Object other$items = other.getItems();
            if (this$items == null) {
               if (other$items != null) {
                  return false;
               }
            } else if (!this$items.equals(other$items)) {
               return false;
            }

            Object this$secretName = this.getSecretName();
            Object other$secretName = other.getSecretName();
            if (this$secretName == null) {
               if (other$secretName != null) {
                  return false;
               }
            } else if (!this$secretName.equals(other$secretName)) {
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
      return other instanceof SecretVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $defaultMode = this.getDefaultMode();
      result = result * 59 + ($defaultMode == null ? 43 : $defaultMode.hashCode());
      Object $optional = this.getOptional();
      result = result * 59 + ($optional == null ? 43 : $optional.hashCode());
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      Object $secretName = this.getSecretName();
      result = result * 59 + ($secretName == null ? 43 : $secretName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
