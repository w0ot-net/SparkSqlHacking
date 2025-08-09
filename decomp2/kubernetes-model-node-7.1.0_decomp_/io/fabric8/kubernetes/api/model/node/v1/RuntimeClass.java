package io.fabric8.kubernetes.api.model.node.v1;

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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "handler", "overhead", "scheduling"})
@Version("v1")
@Group("node.k8s.io")
public class RuntimeClass implements Editable, HasMetadata {
   @JsonProperty("apiVersion")
   private String apiVersion = "node.k8s.io/v1";
   @JsonProperty("handler")
   private String handler;
   @JsonProperty("kind")
   private String kind = "RuntimeClass";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("overhead")
   private Overhead overhead;
   @JsonProperty("scheduling")
   private Scheduling scheduling;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RuntimeClass() {
   }

   public RuntimeClass(String apiVersion, String handler, String kind, ObjectMeta metadata, Overhead overhead, Scheduling scheduling) {
      this.apiVersion = apiVersion;
      this.handler = handler;
      this.kind = kind;
      this.metadata = metadata;
      this.overhead = overhead;
      this.scheduling = scheduling;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("handler")
   public String getHandler() {
      return this.handler;
   }

   @JsonProperty("handler")
   public void setHandler(String handler) {
      this.handler = handler;
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

   @JsonProperty("overhead")
   public Overhead getOverhead() {
      return this.overhead;
   }

   @JsonProperty("overhead")
   public void setOverhead(Overhead overhead) {
      this.overhead = overhead;
   }

   @JsonProperty("scheduling")
   public Scheduling getScheduling() {
      return this.scheduling;
   }

   @JsonProperty("scheduling")
   public void setScheduling(Scheduling scheduling) {
      this.scheduling = scheduling;
   }

   @JsonIgnore
   public RuntimeClassBuilder edit() {
      return new RuntimeClassBuilder(this);
   }

   @JsonIgnore
   public RuntimeClassBuilder toBuilder() {
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
      return "RuntimeClass(apiVersion=" + var10000 + ", handler=" + this.getHandler() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", overhead=" + this.getOverhead() + ", scheduling=" + this.getScheduling() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RuntimeClass)) {
         return false;
      } else {
         RuntimeClass other = (RuntimeClass)o;
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

            Object this$handler = this.getHandler();
            Object other$handler = other.getHandler();
            if (this$handler == null) {
               if (other$handler != null) {
                  return false;
               }
            } else if (!this$handler.equals(other$handler)) {
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

            Object this$overhead = this.getOverhead();
            Object other$overhead = other.getOverhead();
            if (this$overhead == null) {
               if (other$overhead != null) {
                  return false;
               }
            } else if (!this$overhead.equals(other$overhead)) {
               return false;
            }

            Object this$scheduling = this.getScheduling();
            Object other$scheduling = other.getScheduling();
            if (this$scheduling == null) {
               if (other$scheduling != null) {
                  return false;
               }
            } else if (!this$scheduling.equals(other$scheduling)) {
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
      return other instanceof RuntimeClass;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $handler = this.getHandler();
      result = result * 59 + ($handler == null ? 43 : $handler.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $overhead = this.getOverhead();
      result = result * 59 + ($overhead == null ? 43 : $overhead.hashCode());
      Object $scheduling = this.getScheduling();
      result = result * 59 + ($scheduling == null ? 43 : $scheduling.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
