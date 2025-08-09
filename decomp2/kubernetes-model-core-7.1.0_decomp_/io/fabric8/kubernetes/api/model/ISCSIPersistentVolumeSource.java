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
@JsonPropertyOrder({"chapAuthDiscovery", "chapAuthSession", "fsType", "initiatorName", "iqn", "iscsiInterface", "lun", "portals", "readOnly", "secretRef", "targetPortal"})
public class ISCSIPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("chapAuthDiscovery")
   private Boolean chapAuthDiscovery;
   @JsonProperty("chapAuthSession")
   private Boolean chapAuthSession;
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("initiatorName")
   private String initiatorName;
   @JsonProperty("iqn")
   private String iqn;
   @JsonProperty("iscsiInterface")
   private String iscsiInterface;
   @JsonProperty("lun")
   private Integer lun;
   @JsonProperty("portals")
   @JsonInclude(Include.NON_EMPTY)
   private List portals = new ArrayList();
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private SecretReference secretRef;
   @JsonProperty("targetPortal")
   private String targetPortal;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ISCSIPersistentVolumeSource() {
   }

   public ISCSIPersistentVolumeSource(Boolean chapAuthDiscovery, Boolean chapAuthSession, String fsType, String initiatorName, String iqn, String iscsiInterface, Integer lun, List portals, Boolean readOnly, SecretReference secretRef, String targetPortal) {
      this.chapAuthDiscovery = chapAuthDiscovery;
      this.chapAuthSession = chapAuthSession;
      this.fsType = fsType;
      this.initiatorName = initiatorName;
      this.iqn = iqn;
      this.iscsiInterface = iscsiInterface;
      this.lun = lun;
      this.portals = portals;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
      this.targetPortal = targetPortal;
   }

   @JsonProperty("chapAuthDiscovery")
   public Boolean getChapAuthDiscovery() {
      return this.chapAuthDiscovery;
   }

   @JsonProperty("chapAuthDiscovery")
   public void setChapAuthDiscovery(Boolean chapAuthDiscovery) {
      this.chapAuthDiscovery = chapAuthDiscovery;
   }

   @JsonProperty("chapAuthSession")
   public Boolean getChapAuthSession() {
      return this.chapAuthSession;
   }

   @JsonProperty("chapAuthSession")
   public void setChapAuthSession(Boolean chapAuthSession) {
      this.chapAuthSession = chapAuthSession;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("initiatorName")
   public String getInitiatorName() {
      return this.initiatorName;
   }

   @JsonProperty("initiatorName")
   public void setInitiatorName(String initiatorName) {
      this.initiatorName = initiatorName;
   }

   @JsonProperty("iqn")
   public String getIqn() {
      return this.iqn;
   }

   @JsonProperty("iqn")
   public void setIqn(String iqn) {
      this.iqn = iqn;
   }

   @JsonProperty("iscsiInterface")
   public String getIscsiInterface() {
      return this.iscsiInterface;
   }

   @JsonProperty("iscsiInterface")
   public void setIscsiInterface(String iscsiInterface) {
      this.iscsiInterface = iscsiInterface;
   }

   @JsonProperty("lun")
   public Integer getLun() {
      return this.lun;
   }

   @JsonProperty("lun")
   public void setLun(Integer lun) {
      this.lun = lun;
   }

   @JsonProperty("portals")
   @JsonInclude(Include.NON_EMPTY)
   public List getPortals() {
      return this.portals;
   }

   @JsonProperty("portals")
   public void setPortals(List portals) {
      this.portals = portals;
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
   public SecretReference getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(SecretReference secretRef) {
      this.secretRef = secretRef;
   }

   @JsonProperty("targetPortal")
   public String getTargetPortal() {
      return this.targetPortal;
   }

   @JsonProperty("targetPortal")
   public void setTargetPortal(String targetPortal) {
      this.targetPortal = targetPortal;
   }

   @JsonIgnore
   public ISCSIPersistentVolumeSourceBuilder edit() {
      return new ISCSIPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public ISCSIPersistentVolumeSourceBuilder toBuilder() {
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
      Boolean var10000 = this.getChapAuthDiscovery();
      return "ISCSIPersistentVolumeSource(chapAuthDiscovery=" + var10000 + ", chapAuthSession=" + this.getChapAuthSession() + ", fsType=" + this.getFsType() + ", initiatorName=" + this.getInitiatorName() + ", iqn=" + this.getIqn() + ", iscsiInterface=" + this.getIscsiInterface() + ", lun=" + this.getLun() + ", portals=" + this.getPortals() + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", targetPortal=" + this.getTargetPortal() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ISCSIPersistentVolumeSource)) {
         return false;
      } else {
         ISCSIPersistentVolumeSource other = (ISCSIPersistentVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$chapAuthDiscovery = this.getChapAuthDiscovery();
            Object other$chapAuthDiscovery = other.getChapAuthDiscovery();
            if (this$chapAuthDiscovery == null) {
               if (other$chapAuthDiscovery != null) {
                  return false;
               }
            } else if (!this$chapAuthDiscovery.equals(other$chapAuthDiscovery)) {
               return false;
            }

            Object this$chapAuthSession = this.getChapAuthSession();
            Object other$chapAuthSession = other.getChapAuthSession();
            if (this$chapAuthSession == null) {
               if (other$chapAuthSession != null) {
                  return false;
               }
            } else if (!this$chapAuthSession.equals(other$chapAuthSession)) {
               return false;
            }

            Object this$lun = this.getLun();
            Object other$lun = other.getLun();
            if (this$lun == null) {
               if (other$lun != null) {
                  return false;
               }
            } else if (!this$lun.equals(other$lun)) {
               return false;
            }

            Object this$readOnly = this.getReadOnly();
            Object other$readOnly = other.getReadOnly();
            if (this$readOnly == null) {
               if (other$readOnly != null) {
                  return false;
               }
            } else if (!this$readOnly.equals(other$readOnly)) {
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

            Object this$initiatorName = this.getInitiatorName();
            Object other$initiatorName = other.getInitiatorName();
            if (this$initiatorName == null) {
               if (other$initiatorName != null) {
                  return false;
               }
            } else if (!this$initiatorName.equals(other$initiatorName)) {
               return false;
            }

            Object this$iqn = this.getIqn();
            Object other$iqn = other.getIqn();
            if (this$iqn == null) {
               if (other$iqn != null) {
                  return false;
               }
            } else if (!this$iqn.equals(other$iqn)) {
               return false;
            }

            Object this$iscsiInterface = this.getIscsiInterface();
            Object other$iscsiInterface = other.getIscsiInterface();
            if (this$iscsiInterface == null) {
               if (other$iscsiInterface != null) {
                  return false;
               }
            } else if (!this$iscsiInterface.equals(other$iscsiInterface)) {
               return false;
            }

            Object this$portals = this.getPortals();
            Object other$portals = other.getPortals();
            if (this$portals == null) {
               if (other$portals != null) {
                  return false;
               }
            } else if (!this$portals.equals(other$portals)) {
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

            Object this$targetPortal = this.getTargetPortal();
            Object other$targetPortal = other.getTargetPortal();
            if (this$targetPortal == null) {
               if (other$targetPortal != null) {
                  return false;
               }
            } else if (!this$targetPortal.equals(other$targetPortal)) {
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
      return other instanceof ISCSIPersistentVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $chapAuthDiscovery = this.getChapAuthDiscovery();
      result = result * 59 + ($chapAuthDiscovery == null ? 43 : $chapAuthDiscovery.hashCode());
      Object $chapAuthSession = this.getChapAuthSession();
      result = result * 59 + ($chapAuthSession == null ? 43 : $chapAuthSession.hashCode());
      Object $lun = this.getLun();
      result = result * 59 + ($lun == null ? 43 : $lun.hashCode());
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $initiatorName = this.getInitiatorName();
      result = result * 59 + ($initiatorName == null ? 43 : $initiatorName.hashCode());
      Object $iqn = this.getIqn();
      result = result * 59 + ($iqn == null ? 43 : $iqn.hashCode());
      Object $iscsiInterface = this.getIscsiInterface();
      result = result * 59 + ($iscsiInterface == null ? 43 : $iscsiInterface.hashCode());
      Object $portals = this.getPortals();
      result = result * 59 + ($portals == null ? 43 : $portals.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $targetPortal = this.getTargetPortal();
      result = result * 59 + ($targetPortal == null ? 43 : $targetPortal.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
