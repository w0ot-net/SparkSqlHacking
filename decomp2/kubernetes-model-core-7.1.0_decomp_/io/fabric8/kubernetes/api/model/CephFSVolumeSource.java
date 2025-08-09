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
@JsonPropertyOrder({"monitors", "path", "readOnly", "secretFile", "secretRef", "user"})
public class CephFSVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("monitors")
   @JsonInclude(Include.NON_EMPTY)
   private List monitors = new ArrayList();
   @JsonProperty("path")
   private String path;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretFile")
   private String secretFile;
   @JsonProperty("secretRef")
   private LocalObjectReference secretRef;
   @JsonProperty("user")
   private String user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CephFSVolumeSource() {
   }

   public CephFSVolumeSource(List monitors, String path, Boolean readOnly, String secretFile, LocalObjectReference secretRef, String user) {
      this.monitors = monitors;
      this.path = path;
      this.readOnly = readOnly;
      this.secretFile = secretFile;
      this.secretRef = secretRef;
      this.user = user;
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

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("secretFile")
   public String getSecretFile() {
      return this.secretFile;
   }

   @JsonProperty("secretFile")
   public void setSecretFile(String secretFile) {
      this.secretFile = secretFile;
   }

   @JsonProperty("secretRef")
   public LocalObjectReference getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(LocalObjectReference secretRef) {
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
   public CephFSVolumeSourceBuilder edit() {
      return new CephFSVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public CephFSVolumeSourceBuilder toBuilder() {
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
      List var10000 = this.getMonitors();
      return "CephFSVolumeSource(monitors=" + var10000 + ", path=" + this.getPath() + ", readOnly=" + this.getReadOnly() + ", secretFile=" + this.getSecretFile() + ", secretRef=" + this.getSecretRef() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CephFSVolumeSource)) {
         return false;
      } else {
         CephFSVolumeSource other = (CephFSVolumeSource)o;
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

            Object this$monitors = this.getMonitors();
            Object other$monitors = other.getMonitors();
            if (this$monitors == null) {
               if (other$monitors != null) {
                  return false;
               }
            } else if (!this$monitors.equals(other$monitors)) {
               return false;
            }

            Object this$path = this.getPath();
            Object other$path = other.getPath();
            if (this$path == null) {
               if (other$path != null) {
                  return false;
               }
            } else if (!this$path.equals(other$path)) {
               return false;
            }

            Object this$secretFile = this.getSecretFile();
            Object other$secretFile = other.getSecretFile();
            if (this$secretFile == null) {
               if (other$secretFile != null) {
                  return false;
               }
            } else if (!this$secretFile.equals(other$secretFile)) {
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
      return other instanceof CephFSVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $monitors = this.getMonitors();
      result = result * 59 + ($monitors == null ? 43 : $monitors.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $secretFile = this.getSecretFile();
      result = result * 59 + ($secretFile == null ? 43 : $secretFile.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
