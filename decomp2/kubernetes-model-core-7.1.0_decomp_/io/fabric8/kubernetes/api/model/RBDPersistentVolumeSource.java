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
@JsonPropertyOrder({"fsType", "image", "keyring", "monitors", "pool", "readOnly", "secretRef", "user"})
public class RBDPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("image")
   private String image;
   @JsonProperty("keyring")
   private String keyring;
   @JsonProperty("monitors")
   @JsonInclude(Include.NON_EMPTY)
   private List monitors = new ArrayList();
   @JsonProperty("pool")
   private String pool;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private SecretReference secretRef;
   @JsonProperty("user")
   private String user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RBDPersistentVolumeSource() {
   }

   public RBDPersistentVolumeSource(String fsType, String image, String keyring, List monitors, String pool, Boolean readOnly, SecretReference secretRef, String user) {
      this.fsType = fsType;
      this.image = image;
      this.keyring = keyring;
      this.monitors = monitors;
      this.pool = pool;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
      this.user = user;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("image")
   public String getImage() {
      return this.image;
   }

   @JsonProperty("image")
   public void setImage(String image) {
      this.image = image;
   }

   @JsonProperty("keyring")
   public String getKeyring() {
      return this.keyring;
   }

   @JsonProperty("keyring")
   public void setKeyring(String keyring) {
      this.keyring = keyring;
   }

   @JsonProperty("monitors")
   @JsonInclude(Include.NON_EMPTY)
   public List getMonitors() {
      return this.monitors;
   }

   @JsonProperty("monitors")
   public void setMonitors(List monitors) {
      this.monitors = monitors;
   }

   @JsonProperty("pool")
   public String getPool() {
      return this.pool;
   }

   @JsonProperty("pool")
   public void setPool(String pool) {
      this.pool = pool;
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

   @JsonProperty("user")
   public String getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(String user) {
      this.user = user;
   }

   @JsonIgnore
   public RBDPersistentVolumeSourceBuilder edit() {
      return new RBDPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public RBDPersistentVolumeSourceBuilder toBuilder() {
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
      return "RBDPersistentVolumeSource(fsType=" + var10000 + ", image=" + this.getImage() + ", keyring=" + this.getKeyring() + ", monitors=" + this.getMonitors() + ", pool=" + this.getPool() + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RBDPersistentVolumeSource)) {
         return false;
      } else {
         RBDPersistentVolumeSource other = (RBDPersistentVolumeSource)o;
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

            Object this$fsType = this.getFsType();
            Object other$fsType = other.getFsType();
            if (this$fsType == null) {
               if (other$fsType != null) {
                  return false;
               }
            } else if (!this$fsType.equals(other$fsType)) {
               return false;
            }

            Object this$image = this.getImage();
            Object other$image = other.getImage();
            if (this$image == null) {
               if (other$image != null) {
                  return false;
               }
            } else if (!this$image.equals(other$image)) {
               return false;
            }

            Object this$keyring = this.getKeyring();
            Object other$keyring = other.getKeyring();
            if (this$keyring == null) {
               if (other$keyring != null) {
                  return false;
               }
            } else if (!this$keyring.equals(other$keyring)) {
               return false;
            }

            Object this$monitors = this.getMonitors();
            Object other$monitors = other.getMonitors();
            if (this$monitors == null) {
               if (other$monitors != null) {
                  return false;
               }
            } else if (!this$monitors.equals(other$monitors)) {
               return false;
            }

            Object this$pool = this.getPool();
            Object other$pool = other.getPool();
            if (this$pool == null) {
               if (other$pool != null) {
                  return false;
               }
            } else if (!this$pool.equals(other$pool)) {
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

            Object this$user = this.getUser();
            Object other$user = other.getUser();
            if (this$user == null) {
               if (other$user != null) {
                  return false;
               }
            } else if (!this$user.equals(other$user)) {
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
      return other instanceof RBDPersistentVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $image = this.getImage();
      result = result * 59 + ($image == null ? 43 : $image.hashCode());
      Object $keyring = this.getKeyring();
      result = result * 59 + ($keyring == null ? 43 : $keyring.hashCode());
      Object $monitors = this.getMonitors();
      result = result * 59 + ($monitors == null ? 43 : $monitors.hashCode());
      Object $pool = this.getPool();
      result = result * 59 + ($pool == null ? 43 : $pool.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
