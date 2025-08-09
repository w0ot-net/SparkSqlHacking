package io.fabric8.kubernetes.api.model.apps;

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
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "data", "revision"})
@Version("v1")
@Group("apps")
public class ControllerRevision implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "apps/v1";
   @JsonProperty("data")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object data;
   @JsonProperty("kind")
   private String kind = "ControllerRevision";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("revision")
   private Long revision;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ControllerRevision() {
   }

   public ControllerRevision(String apiVersion, Object data, String kind, ObjectMeta metadata, Long revision) {
      this.apiVersion = apiVersion;
      this.data = data;
      this.kind = kind;
      this.metadata = metadata;
      this.revision = revision;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("data")
   public Object getData() {
      return this.data;
   }

   @JsonProperty("data")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setData(Object data) {
      this.data = data;
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

   @JsonProperty("revision")
   public Long getRevision() {
      return this.revision;
   }

   @JsonProperty("revision")
   public void setRevision(Long revision) {
      this.revision = revision;
   }

   @JsonIgnore
   public ControllerRevisionBuilder edit() {
      return new ControllerRevisionBuilder(this);
   }

   @JsonIgnore
   public ControllerRevisionBuilder toBuilder() {
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
      return "ControllerRevision(apiVersion=" + var10000 + ", data=" + this.getData() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", revision=" + this.getRevision() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ControllerRevision)) {
         return false;
      } else {
         ControllerRevision other = (ControllerRevision)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$revision = this.getRevision();
            Object other$revision = other.getRevision();
            if (this$revision == null) {
               if (other$revision != null) {
                  return false;
               }
            } else if (!this$revision.equals(other$revision)) {
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

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
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
      return other instanceof ControllerRevision;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $revision = this.getRevision();
      result = result * 59 + ($revision == null ? 43 : $revision.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
