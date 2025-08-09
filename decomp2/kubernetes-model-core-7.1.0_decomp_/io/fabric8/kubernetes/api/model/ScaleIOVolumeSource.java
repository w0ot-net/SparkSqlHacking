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
@JsonPropertyOrder({"fsType", "gateway", "protectionDomain", "readOnly", "secretRef", "sslEnabled", "storageMode", "storagePool", "system", "volumeName"})
public class ScaleIOVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("gateway")
   private String gateway;
   @JsonProperty("protectionDomain")
   private String protectionDomain;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private LocalObjectReference secretRef;
   @JsonProperty("sslEnabled")
   private Boolean sslEnabled;
   @JsonProperty("storageMode")
   private String storageMode;
   @JsonProperty("storagePool")
   private String storagePool;
   @JsonProperty("system")
   private String system;
   @JsonProperty("volumeName")
   private String volumeName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ScaleIOVolumeSource() {
   }

   public ScaleIOVolumeSource(String fsType, String gateway, String protectionDomain, Boolean readOnly, LocalObjectReference secretRef, Boolean sslEnabled, String storageMode, String storagePool, String system, String volumeName) {
      this.fsType = fsType;
      this.gateway = gateway;
      this.protectionDomain = protectionDomain;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
      this.sslEnabled = sslEnabled;
      this.storageMode = storageMode;
      this.storagePool = storagePool;
      this.system = system;
      this.volumeName = volumeName;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("gateway")
   public String getGateway() {
      return this.gateway;
   }

   @JsonProperty("gateway")
   public void setGateway(String gateway) {
      this.gateway = gateway;
   }

   @JsonProperty("protectionDomain")
   public String getProtectionDomain() {
      return this.protectionDomain;
   }

   @JsonProperty("protectionDomain")
   public void setProtectionDomain(String protectionDomain) {
      this.protectionDomain = protectionDomain;
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

   @JsonProperty("sslEnabled")
   public Boolean getSslEnabled() {
      return this.sslEnabled;
   }

   @JsonProperty("sslEnabled")
   public void setSslEnabled(Boolean sslEnabled) {
      this.sslEnabled = sslEnabled;
   }

   @JsonProperty("storageMode")
   public String getStorageMode() {
      return this.storageMode;
   }

   @JsonProperty("storageMode")
   public void setStorageMode(String storageMode) {
      this.storageMode = storageMode;
   }

   @JsonProperty("storagePool")
   public String getStoragePool() {
      return this.storagePool;
   }

   @JsonProperty("storagePool")
   public void setStoragePool(String storagePool) {
      this.storagePool = storagePool;
   }

   @JsonProperty("system")
   public String getSystem() {
      return this.system;
   }

   @JsonProperty("system")
   public void setSystem(String system) {
      this.system = system;
   }

   @JsonProperty("volumeName")
   public String getVolumeName() {
      return this.volumeName;
   }

   @JsonProperty("volumeName")
   public void setVolumeName(String volumeName) {
      this.volumeName = volumeName;
   }

   @JsonIgnore
   public ScaleIOVolumeSourceBuilder edit() {
      return new ScaleIOVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public ScaleIOVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getFsType();
      return "ScaleIOVolumeSource(fsType=" + var10000 + ", gateway=" + this.getGateway() + ", protectionDomain=" + this.getProtectionDomain() + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", sslEnabled=" + this.getSslEnabled() + ", storageMode=" + this.getStorageMode() + ", storagePool=" + this.getStoragePool() + ", system=" + this.getSystem() + ", volumeName=" + this.getVolumeName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScaleIOVolumeSource)) {
         return false;
      } else {
         ScaleIOVolumeSource other = (ScaleIOVolumeSource)o;
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

            Object this$sslEnabled = this.getSslEnabled();
            Object other$sslEnabled = other.getSslEnabled();
            if (this$sslEnabled == null) {
               if (other$sslEnabled != null) {
                  return false;
               }
            } else if (!this$sslEnabled.equals(other$sslEnabled)) {
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

            Object this$gateway = this.getGateway();
            Object other$gateway = other.getGateway();
            if (this$gateway == null) {
               if (other$gateway != null) {
                  return false;
               }
            } else if (!this$gateway.equals(other$gateway)) {
               return false;
            }

            Object this$protectionDomain = this.getProtectionDomain();
            Object other$protectionDomain = other.getProtectionDomain();
            if (this$protectionDomain == null) {
               if (other$protectionDomain != null) {
                  return false;
               }
            } else if (!this$protectionDomain.equals(other$protectionDomain)) {
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

            Object this$storageMode = this.getStorageMode();
            Object other$storageMode = other.getStorageMode();
            if (this$storageMode == null) {
               if (other$storageMode != null) {
                  return false;
               }
            } else if (!this$storageMode.equals(other$storageMode)) {
               return false;
            }

            Object this$storagePool = this.getStoragePool();
            Object other$storagePool = other.getStoragePool();
            if (this$storagePool == null) {
               if (other$storagePool != null) {
                  return false;
               }
            } else if (!this$storagePool.equals(other$storagePool)) {
               return false;
            }

            Object this$system = this.getSystem();
            Object other$system = other.getSystem();
            if (this$system == null) {
               if (other$system != null) {
                  return false;
               }
            } else if (!this$system.equals(other$system)) {
               return false;
            }

            Object this$volumeName = this.getVolumeName();
            Object other$volumeName = other.getVolumeName();
            if (this$volumeName == null) {
               if (other$volumeName != null) {
                  return false;
               }
            } else if (!this$volumeName.equals(other$volumeName)) {
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
      return other instanceof ScaleIOVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $sslEnabled = this.getSslEnabled();
      result = result * 59 + ($sslEnabled == null ? 43 : $sslEnabled.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $gateway = this.getGateway();
      result = result * 59 + ($gateway == null ? 43 : $gateway.hashCode());
      Object $protectionDomain = this.getProtectionDomain();
      result = result * 59 + ($protectionDomain == null ? 43 : $protectionDomain.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $storageMode = this.getStorageMode();
      result = result * 59 + ($storageMode == null ? 43 : $storageMode.hashCode());
      Object $storagePool = this.getStoragePool();
      result = result * 59 + ($storagePool == null ? 43 : $storagePool.hashCode());
      Object $system = this.getSystem();
      result = result * 59 + ($system == null ? 43 : $system.hashCode());
      Object $volumeName = this.getVolumeName();
      result = result * 59 + ($volumeName == null ? 43 : $volumeName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
