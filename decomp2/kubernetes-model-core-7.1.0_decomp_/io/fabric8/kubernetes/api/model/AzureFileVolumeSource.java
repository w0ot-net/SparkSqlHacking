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
@JsonPropertyOrder({"readOnly", "secretName", "shareName"})
public class AzureFileVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretName")
   private String secretName;
   @JsonProperty("shareName")
   private String shareName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AzureFileVolumeSource() {
   }

   public AzureFileVolumeSource(Boolean readOnly, String secretName, String shareName) {
      this.readOnly = readOnly;
      this.secretName = secretName;
      this.shareName = shareName;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("secretName")
   public String getSecretName() {
      return this.secretName;
   }

   @JsonProperty("secretName")
   public void setSecretName(String secretName) {
      this.secretName = secretName;
   }

   @JsonProperty("shareName")
   public String getShareName() {
      return this.shareName;
   }

   @JsonProperty("shareName")
   public void setShareName(String shareName) {
      this.shareName = shareName;
   }

   @JsonIgnore
   public AzureFileVolumeSourceBuilder edit() {
      return new AzureFileVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public AzureFileVolumeSourceBuilder toBuilder() {
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
      Boolean var10000 = this.getReadOnly();
      return "AzureFileVolumeSource(readOnly=" + var10000 + ", secretName=" + this.getSecretName() + ", shareName=" + this.getShareName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AzureFileVolumeSource)) {
         return false;
      } else {
         AzureFileVolumeSource other = (AzureFileVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$readOnly = this.getReadOnly();
            Object other$readOnly = other.getReadOnly();
            if (this$readOnly == null) {
               if (other$readOnly != null) {
                  return false;
               }
            } else if (!this$readOnly.equals(other$readOnly)) {
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

            Object this$shareName = this.getShareName();
            Object other$shareName = other.getShareName();
            if (this$shareName == null) {
               if (other$shareName != null) {
                  return false;
               }
            } else if (!this$shareName.equals(other$shareName)) {
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
      return other instanceof AzureFileVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $secretName = this.getSecretName();
      result = result * 59 + ($secretName == null ? 43 : $secretName.hashCode());
      Object $shareName = this.getShareName();
      result = result * 59 + ($shareName == null ? 43 : $shareName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
