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
@JsonPropertyOrder({"controllerExpandSecretRef", "controllerPublishSecretRef", "driver", "fsType", "nodeExpandSecretRef", "nodePublishSecretRef", "nodeStageSecretRef", "readOnly", "volumeAttributes", "volumeHandle"})
public class CSIPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("controllerExpandSecretRef")
   private SecretReference controllerExpandSecretRef;
   @JsonProperty("controllerPublishSecretRef")
   private SecretReference controllerPublishSecretRef;
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("nodeExpandSecretRef")
   private SecretReference nodeExpandSecretRef;
   @JsonProperty("nodePublishSecretRef")
   private SecretReference nodePublishSecretRef;
   @JsonProperty("nodeStageSecretRef")
   private SecretReference nodeStageSecretRef;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("volumeAttributes")
   @JsonInclude(Include.NON_EMPTY)
   private Map volumeAttributes = new LinkedHashMap();
   @JsonProperty("volumeHandle")
   private String volumeHandle;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSIPersistentVolumeSource() {
   }

   public CSIPersistentVolumeSource(SecretReference controllerExpandSecretRef, SecretReference controllerPublishSecretRef, String driver, String fsType, SecretReference nodeExpandSecretRef, SecretReference nodePublishSecretRef, SecretReference nodeStageSecretRef, Boolean readOnly, Map volumeAttributes, String volumeHandle) {
      this.controllerExpandSecretRef = controllerExpandSecretRef;
      this.controllerPublishSecretRef = controllerPublishSecretRef;
      this.driver = driver;
      this.fsType = fsType;
      this.nodeExpandSecretRef = nodeExpandSecretRef;
      this.nodePublishSecretRef = nodePublishSecretRef;
      this.nodeStageSecretRef = nodeStageSecretRef;
      this.readOnly = readOnly;
      this.volumeAttributes = volumeAttributes;
      this.volumeHandle = volumeHandle;
   }

   @JsonProperty("controllerExpandSecretRef")
   public SecretReference getControllerExpandSecretRef() {
      return this.controllerExpandSecretRef;
   }

   @JsonProperty("controllerExpandSecretRef")
   public void setControllerExpandSecretRef(SecretReference controllerExpandSecretRef) {
      this.controllerExpandSecretRef = controllerExpandSecretRef;
   }

   @JsonProperty("controllerPublishSecretRef")
   public SecretReference getControllerPublishSecretRef() {
      return this.controllerPublishSecretRef;
   }

   @JsonProperty("controllerPublishSecretRef")
   public void setControllerPublishSecretRef(SecretReference controllerPublishSecretRef) {
      this.controllerPublishSecretRef = controllerPublishSecretRef;
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

   @JsonProperty("nodeExpandSecretRef")
   public SecretReference getNodeExpandSecretRef() {
      return this.nodeExpandSecretRef;
   }

   @JsonProperty("nodeExpandSecretRef")
   public void setNodeExpandSecretRef(SecretReference nodeExpandSecretRef) {
      this.nodeExpandSecretRef = nodeExpandSecretRef;
   }

   @JsonProperty("nodePublishSecretRef")
   public SecretReference getNodePublishSecretRef() {
      return this.nodePublishSecretRef;
   }

   @JsonProperty("nodePublishSecretRef")
   public void setNodePublishSecretRef(SecretReference nodePublishSecretRef) {
      this.nodePublishSecretRef = nodePublishSecretRef;
   }

   @JsonProperty("nodeStageSecretRef")
   public SecretReference getNodeStageSecretRef() {
      return this.nodeStageSecretRef;
   }

   @JsonProperty("nodeStageSecretRef")
   public void setNodeStageSecretRef(SecretReference nodeStageSecretRef) {
      this.nodeStageSecretRef = nodeStageSecretRef;
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

   @JsonProperty("volumeHandle")
   public String getVolumeHandle() {
      return this.volumeHandle;
   }

   @JsonProperty("volumeHandle")
   public void setVolumeHandle(String volumeHandle) {
      this.volumeHandle = volumeHandle;
   }

   @JsonIgnore
   public CSIPersistentVolumeSourceBuilder edit() {
      return new CSIPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public CSIPersistentVolumeSourceBuilder toBuilder() {
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
      SecretReference var10000 = this.getControllerExpandSecretRef();
      return "CSIPersistentVolumeSource(controllerExpandSecretRef=" + var10000 + ", controllerPublishSecretRef=" + this.getControllerPublishSecretRef() + ", driver=" + this.getDriver() + ", fsType=" + this.getFsType() + ", nodeExpandSecretRef=" + this.getNodeExpandSecretRef() + ", nodePublishSecretRef=" + this.getNodePublishSecretRef() + ", nodeStageSecretRef=" + this.getNodeStageSecretRef() + ", readOnly=" + this.getReadOnly() + ", volumeAttributes=" + this.getVolumeAttributes() + ", volumeHandle=" + this.getVolumeHandle() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSIPersistentVolumeSource)) {
         return false;
      } else {
         CSIPersistentVolumeSource other = (CSIPersistentVolumeSource)o;
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

            Object this$controllerExpandSecretRef = this.getControllerExpandSecretRef();
            Object other$controllerExpandSecretRef = other.getControllerExpandSecretRef();
            if (this$controllerExpandSecretRef == null) {
               if (other$controllerExpandSecretRef != null) {
                  return false;
               }
            } else if (!this$controllerExpandSecretRef.equals(other$controllerExpandSecretRef)) {
               return false;
            }

            Object this$controllerPublishSecretRef = this.getControllerPublishSecretRef();
            Object other$controllerPublishSecretRef = other.getControllerPublishSecretRef();
            if (this$controllerPublishSecretRef == null) {
               if (other$controllerPublishSecretRef != null) {
                  return false;
               }
            } else if (!this$controllerPublishSecretRef.equals(other$controllerPublishSecretRef)) {
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

            Object this$nodeExpandSecretRef = this.getNodeExpandSecretRef();
            Object other$nodeExpandSecretRef = other.getNodeExpandSecretRef();
            if (this$nodeExpandSecretRef == null) {
               if (other$nodeExpandSecretRef != null) {
                  return false;
               }
            } else if (!this$nodeExpandSecretRef.equals(other$nodeExpandSecretRef)) {
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

            Object this$nodeStageSecretRef = this.getNodeStageSecretRef();
            Object other$nodeStageSecretRef = other.getNodeStageSecretRef();
            if (this$nodeStageSecretRef == null) {
               if (other$nodeStageSecretRef != null) {
                  return false;
               }
            } else if (!this$nodeStageSecretRef.equals(other$nodeStageSecretRef)) {
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

            Object this$volumeHandle = this.getVolumeHandle();
            Object other$volumeHandle = other.getVolumeHandle();
            if (this$volumeHandle == null) {
               if (other$volumeHandle != null) {
                  return false;
               }
            } else if (!this$volumeHandle.equals(other$volumeHandle)) {
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
      return other instanceof CSIPersistentVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $controllerExpandSecretRef = this.getControllerExpandSecretRef();
      result = result * 59 + ($controllerExpandSecretRef == null ? 43 : $controllerExpandSecretRef.hashCode());
      Object $controllerPublishSecretRef = this.getControllerPublishSecretRef();
      result = result * 59 + ($controllerPublishSecretRef == null ? 43 : $controllerPublishSecretRef.hashCode());
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $nodeExpandSecretRef = this.getNodeExpandSecretRef();
      result = result * 59 + ($nodeExpandSecretRef == null ? 43 : $nodeExpandSecretRef.hashCode());
      Object $nodePublishSecretRef = this.getNodePublishSecretRef();
      result = result * 59 + ($nodePublishSecretRef == null ? 43 : $nodePublishSecretRef.hashCode());
      Object $nodeStageSecretRef = this.getNodeStageSecretRef();
      result = result * 59 + ($nodeStageSecretRef == null ? 43 : $nodeStageSecretRef.hashCode());
      Object $volumeAttributes = this.getVolumeAttributes();
      result = result * 59 + ($volumeAttributes == null ? 43 : $volumeAttributes.hashCode());
      Object $volumeHandle = this.getVolumeHandle();
      result = result * 59 + ($volumeHandle == null ? 43 : $volumeHandle.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
