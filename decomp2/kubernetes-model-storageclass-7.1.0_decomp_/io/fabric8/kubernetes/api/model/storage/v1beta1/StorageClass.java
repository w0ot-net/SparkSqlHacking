package io.fabric8.kubernetes.api.model.storage.v1beta1;

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
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.TopologySelectorTerm;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "allowVolumeExpansion", "allowedTopologies", "mountOptions", "parameters", "provisioner", "reclaimPolicy", "volumeBindingMode"})
@Version("v1beta1")
@Group("storage.k8s.io")
public class StorageClass implements Editable, HasMetadata {
   @JsonProperty("allowVolumeExpansion")
   private Boolean allowVolumeExpansion;
   @JsonProperty("allowedTopologies")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedTopologies = new ArrayList();
   @JsonProperty("apiVersion")
   private String apiVersion = "storage.k8s.io/v1beta1";
   @JsonProperty("kind")
   private String kind = "StorageClass";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("mountOptions")
   @JsonInclude(Include.NON_EMPTY)
   private List mountOptions = new ArrayList();
   @JsonProperty("parameters")
   @JsonInclude(Include.NON_EMPTY)
   private Map parameters = new LinkedHashMap();
   @JsonProperty("provisioner")
   private String provisioner;
   @JsonProperty("reclaimPolicy")
   private String reclaimPolicy;
   @JsonProperty("volumeBindingMode")
   private String volumeBindingMode;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StorageClass() {
   }

   public StorageClass(Boolean allowVolumeExpansion, List allowedTopologies, String apiVersion, String kind, ObjectMeta metadata, List mountOptions, Map parameters, String provisioner, String reclaimPolicy, String volumeBindingMode) {
      this.allowVolumeExpansion = allowVolumeExpansion;
      this.allowedTopologies = allowedTopologies;
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.metadata = metadata;
      this.mountOptions = mountOptions;
      this.parameters = parameters;
      this.provisioner = provisioner;
      this.reclaimPolicy = reclaimPolicy;
      this.volumeBindingMode = volumeBindingMode;
   }

   @JsonProperty("allowVolumeExpansion")
   public Boolean getAllowVolumeExpansion() {
      return this.allowVolumeExpansion;
   }

   @JsonProperty("allowVolumeExpansion")
   public void setAllowVolumeExpansion(Boolean allowVolumeExpansion) {
      this.allowVolumeExpansion = allowVolumeExpansion;
   }

   @JsonProperty("allowedTopologies")
   @JsonInclude(Include.NON_EMPTY)
   public List getAllowedTopologies() {
      return this.allowedTopologies;
   }

   @JsonProperty("allowedTopologies")
   public void setAllowedTopologies(List allowedTopologies) {
      this.allowedTopologies = allowedTopologies;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("mountOptions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMountOptions() {
      return this.mountOptions;
   }

   @JsonProperty("mountOptions")
   public void setMountOptions(List mountOptions) {
      this.mountOptions = mountOptions;
   }

   @JsonProperty("parameters")
   @JsonInclude(Include.NON_EMPTY)
   public Map getParameters() {
      return this.parameters;
   }

   @JsonProperty("parameters")
   public void setParameters(Map parameters) {
      this.parameters = parameters;
   }

   @JsonProperty("provisioner")
   public String getProvisioner() {
      return this.provisioner;
   }

   @JsonProperty("provisioner")
   public void setProvisioner(String provisioner) {
      this.provisioner = provisioner;
   }

   @JsonProperty("reclaimPolicy")
   public String getReclaimPolicy() {
      return this.reclaimPolicy;
   }

   @JsonProperty("reclaimPolicy")
   public void setReclaimPolicy(String reclaimPolicy) {
      this.reclaimPolicy = reclaimPolicy;
   }

   @JsonProperty("volumeBindingMode")
   public String getVolumeBindingMode() {
      return this.volumeBindingMode;
   }

   @JsonProperty("volumeBindingMode")
   public void setVolumeBindingMode(String volumeBindingMode) {
      this.volumeBindingMode = volumeBindingMode;
   }

   @JsonIgnore
   public StorageClassBuilder edit() {
      return new StorageClassBuilder(this);
   }

   @JsonIgnore
   public StorageClassBuilder toBuilder() {
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
      Boolean var10000 = this.getAllowVolumeExpansion();
      return "StorageClass(allowVolumeExpansion=" + var10000 + ", allowedTopologies=" + this.getAllowedTopologies() + ", apiVersion=" + this.getApiVersion() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", mountOptions=" + this.getMountOptions() + ", parameters=" + this.getParameters() + ", provisioner=" + this.getProvisioner() + ", reclaimPolicy=" + this.getReclaimPolicy() + ", volumeBindingMode=" + this.getVolumeBindingMode() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StorageClass)) {
         return false;
      } else {
         StorageClass other = (StorageClass)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allowVolumeExpansion = this.getAllowVolumeExpansion();
            Object other$allowVolumeExpansion = other.getAllowVolumeExpansion();
            if (this$allowVolumeExpansion == null) {
               if (other$allowVolumeExpansion != null) {
                  return false;
               }
            } else if (!this$allowVolumeExpansion.equals(other$allowVolumeExpansion)) {
               return false;
            }

            Object this$allowedTopologies = this.getAllowedTopologies();
            Object other$allowedTopologies = other.getAllowedTopologies();
            if (this$allowedTopologies == null) {
               if (other$allowedTopologies != null) {
                  return false;
               }
            } else if (!this$allowedTopologies.equals(other$allowedTopologies)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$mountOptions = this.getMountOptions();
            Object other$mountOptions = other.getMountOptions();
            if (this$mountOptions == null) {
               if (other$mountOptions != null) {
                  return false;
               }
            } else if (!this$mountOptions.equals(other$mountOptions)) {
               return false;
            }

            Object this$parameters = this.getParameters();
            Object other$parameters = other.getParameters();
            if (this$parameters == null) {
               if (other$parameters != null) {
                  return false;
               }
            } else if (!this$parameters.equals(other$parameters)) {
               return false;
            }

            Object this$provisioner = this.getProvisioner();
            Object other$provisioner = other.getProvisioner();
            if (this$provisioner == null) {
               if (other$provisioner != null) {
                  return false;
               }
            } else if (!this$provisioner.equals(other$provisioner)) {
               return false;
            }

            Object this$reclaimPolicy = this.getReclaimPolicy();
            Object other$reclaimPolicy = other.getReclaimPolicy();
            if (this$reclaimPolicy == null) {
               if (other$reclaimPolicy != null) {
                  return false;
               }
            } else if (!this$reclaimPolicy.equals(other$reclaimPolicy)) {
               return false;
            }

            Object this$volumeBindingMode = this.getVolumeBindingMode();
            Object other$volumeBindingMode = other.getVolumeBindingMode();
            if (this$volumeBindingMode == null) {
               if (other$volumeBindingMode != null) {
                  return false;
               }
            } else if (!this$volumeBindingMode.equals(other$volumeBindingMode)) {
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
      return other instanceof StorageClass;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowVolumeExpansion = this.getAllowVolumeExpansion();
      result = result * 59 + ($allowVolumeExpansion == null ? 43 : $allowVolumeExpansion.hashCode());
      Object $allowedTopologies = this.getAllowedTopologies();
      result = result * 59 + ($allowedTopologies == null ? 43 : $allowedTopologies.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $mountOptions = this.getMountOptions();
      result = result * 59 + ($mountOptions == null ? 43 : $mountOptions.hashCode());
      Object $parameters = this.getParameters();
      result = result * 59 + ($parameters == null ? 43 : $parameters.hashCode());
      Object $provisioner = this.getProvisioner();
      result = result * 59 + ($provisioner == null ? 43 : $provisioner.hashCode());
      Object $reclaimPolicy = this.getReclaimPolicy();
      result = result * 59 + ($reclaimPolicy == null ? 43 : $reclaimPolicy.hashCode());
      Object $volumeBindingMode = this.getVolumeBindingMode();
      result = result * 59 + ($volumeBindingMode == null ? 43 : $volumeBindingMode.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
