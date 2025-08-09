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
@JsonPropertyOrder({"driver", "fsType", "nodePublishSecretRef", "readOnly", "volumeAttributes"})
public class CSIVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("nodePublishSecretRef")
   private LocalObjectReference nodePublishSecretRef;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("volumeAttributes")
   @JsonInclude(Include.NON_EMPTY)
   private Map volumeAttributes = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSIVolumeSource() {
   }

   public CSIVolumeSource(String driver, String fsType, LocalObjectReference nodePublishSecretRef, Boolean readOnly, Map volumeAttributes) {
      this.driver = driver;
      this.fsType = fsType;
      this.nodePublishSecretRef = nodePublishSecretRef;
      this.readOnly = readOnly;
      this.volumeAttributes = volumeAttributes;
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

   @JsonProperty("nodePublishSecretRef")
   public LocalObjectReference getNodePublishSecretRef() {
      return this.nodePublishSecretRef;
   }

   @JsonProperty("nodePublishSecretRef")
   public void setNodePublishSecretRef(LocalObjectReference nodePublishSecretRef) {
      this.nodePublishSecretRef = nodePublishSecretRef;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("volumeAttributes")
   @JsonInclude(Include.NON_EMPTY)
   public Map getVolumeAttributes() {
      return this.volumeAttributes;
   }

   @JsonProperty("volumeAttributes")
   public void setVolumeAttributes(Map volumeAttributes) {
      this.volumeAttributes = volumeAttributes;
   }

   @JsonIgnore
   public CSIVolumeSourceBuilder edit() {
      return new CSIVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public CSIVolumeSourceBuilder toBuilder() {
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
      return "CSIVolumeSource(driver=" + var10000 + ", fsType=" + this.getFsType() + ", nodePublishSecretRef=" + this.getNodePublishSecretRef() + ", readOnly=" + this.getReadOnly() + ", volumeAttributes=" + this.getVolumeAttributes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSIVolumeSource)) {
         return false;
      } else {
         CSIVolumeSource other = (CSIVolumeSource)o;
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

            Object this$nodePublishSecretRef = this.getNodePublishSecretRef();
            Object other$nodePublishSecretRef = other.getNodePublishSecretRef();
            if (this$nodePublishSecretRef == null) {
               if (other$nodePublishSecretRef != null) {
                  return false;
               }
            } else if (!this$nodePublishSecretRef.equals(other$nodePublishSecretRef)) {
               return false;
            }

            Object this$volumeAttributes = this.getVolumeAttributes();
            Object other$volumeAttributes = other.getVolumeAttributes();
            if (this$volumeAttributes == null) {
               if (other$volumeAttributes != null) {
                  return false;
               }
            } else if (!this$volumeAttributes.equals(other$volumeAttributes)) {
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
      return other instanceof CSIVolumeSource;
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
      Object $nodePublishSecretRef = this.getNodePublishSecretRef();
      result = result * 59 + ($nodePublishSecretRef == null ? 43 : $nodePublishSecretRef.hashCode());
      Object $volumeAttributes = this.getVolumeAttributes();
      result = result * 59 + ($volumeAttributes == null ? 43 : $volumeAttributes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
