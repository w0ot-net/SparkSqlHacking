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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "spec", "status"})
@Version("v1")
@Group("")
public class PersistentVolumeClaim implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("kind")
   private String kind = "PersistentVolumeClaim";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("spec")
   private PersistentVolumeClaimSpec spec;
   @JsonProperty("status")
   private PersistentVolumeClaimStatus status;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeClaim() {
   }

   public PersistentVolumeClaim(String apiVersion, String kind, ObjectMeta metadata, PersistentVolumeClaimSpec spec, PersistentVolumeClaimStatus status) {
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.metadata = metadata;
      this.spec = spec;
      this.status = status;
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

   @JsonProperty("spec")
   public PersistentVolumeClaimSpec getSpec() {
      return this.spec;
   }

   @JsonProperty("spec")
   public void setSpec(PersistentVolumeClaimSpec spec) {
      this.spec = spec;
   }

   @JsonProperty("status")
   public PersistentVolumeClaimStatus getStatus() {
      return this.status;
   }

   @JsonProperty("status")
   public void setStatus(PersistentVolumeClaimStatus status) {
      this.status = status;
   }

   @JsonIgnore
   public PersistentVolumeClaimBuilder edit() {
      return new PersistentVolumeClaimBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeClaimBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "PersistentVolumeClaim(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", spec=" + this.getSpec() + ", status=" + this.getStatus() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeClaim)) {
         return false;
      } else {
         PersistentVolumeClaim other = (PersistentVolumeClaim)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
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

            Object this$spec = this.getSpec();
            Object other$spec = other.getSpec();
            if (this$spec == null) {
               if (other$spec != null) {
                  return false;
               }
            } else if (!this$spec.equals(other$spec)) {
               return false;
            }

            Object this$status = this.getStatus();
            Object other$status = other.getStatus();
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
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
      return other instanceof PersistentVolumeClaim;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $spec = this.getSpec();
      result = result * 59 + ($spec == null ? 43 : $spec.hashCode());
      Object $status = this.getStatus();
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
