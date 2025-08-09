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
@JsonPropertyOrder({"driver", "fsType", "options", "readOnly", "secretRef"})
public class FlexVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   private Map options = new LinkedHashMap();
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private LocalObjectReference secretRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FlexVolumeSource() {
   }

   public FlexVolumeSource(String driver, String fsType, Map options, Boolean readOnly, LocalObjectReference secretRef) {
      this.driver = driver;
      this.fsType = fsType;
      this.options = options;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
   }

   @JsonProperty("driver")
   public String getDriver() {
      return this.driver;
   }

   @JsonProperty("driver")
   public void setDriver(String driver) {
      this.driver = driver;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
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

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("secretRef")
   public LocalObjectReference getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(LocalObjectReference secretRef) {
      this.secretRef = secretRef;
   }

   @JsonIgnore
   public FlexVolumeSourceBuilder edit() {
      return new FlexVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public FlexVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getDriver();
      return "FlexVolumeSource(driver=" + var10000 + ", fsType=" + this.getFsType() + ", options=" + this.getOptions() + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FlexVolumeSource)) {
         return false;
      } else {
         FlexVolumeSource other = (FlexVolumeSource)o;
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

            Object this$driver = this.getDriver();
            Object other$driver = other.getDriver();
            if (this$driver == null) {
               if (other$driver != null) {
                  return false;
               }
            } else if (!this$driver.equals(other$driver)) {
               return false;
            }

            Object this$fsType = this.getFsType();
            Object other$fsType = other.getFsType();
            if (this$fsType == null) {
               if (other$fsType != null) {
                  return false;
               }
            } else if (!this$fsType.equals(other$fsType)) {
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
      return other instanceof FlexVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
