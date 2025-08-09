package io.fabric8.kubernetes.api.model.metrics.v1beta1;

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
import io.fabric8.kubernetes.api.model.Duration;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "containers", "timestamp", "window"})
@Version("v1beta1")
@Group("metrics.k8s.io")
public class PodMetrics implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "metrics.k8s.io/v1beta1";
   @JsonProperty("containers")
   @JsonInclude(Include.NON_EMPTY)
   private List containers = new ArrayList();
   @JsonProperty("kind")
   private String kind = "PodMetrics";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("timestamp")
   private String timestamp;
   @JsonProperty("window")
   private Duration window;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodMetrics() {
   }

   public PodMetrics(String apiVersion, List containers, String kind, ObjectMeta metadata, String timestamp, Duration window) {
      this.apiVersion = apiVersion;
      this.containers = containers;
      this.kind = kind;
      this.metadata = metadata;
      this.timestamp = timestamp;
      this.window = window;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("containers")
   @JsonInclude(Include.NON_EMPTY)
   public List getContainers() {
      return this.containers;
   }

   @JsonProperty("containers")
   public void setContainers(List containers) {
      this.containers = containers;
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

   @JsonProperty("timestamp")
   public String getTimestamp() {
      return this.timestamp;
   }

   @JsonProperty("timestamp")
   public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
   }

   @JsonProperty("window")
   public Duration getWindow() {
      return this.window;
   }

   @JsonProperty("window")
   public void setWindow(Duration window) {
      this.window = window;
   }

   @JsonIgnore
   public PodMetricsBuilder edit() {
      return new PodMetricsBuilder(this);
   }

   @JsonIgnore
   public PodMetricsBuilder toBuilder() {
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
      return "PodMetrics(apiVersion=" + var10000 + ", containers=" + this.getContainers() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", timestamp=" + this.getTimestamp() + ", window=" + this.getWindow() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodMetrics)) {
         return false;
      } else {
         PodMetrics other = (PodMetrics)o;
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

            Object this$containers = this.getContainers();
            Object other$containers = other.getContainers();
            if (this$containers == null) {
               if (other$containers != null) {
                  return false;
               }
            } else if (!this$containers.equals(other$containers)) {
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

            Object this$timestamp = this.getTimestamp();
            Object other$timestamp = other.getTimestamp();
            if (this$timestamp == null) {
               if (other$timestamp != null) {
                  return false;
               }
            } else if (!this$timestamp.equals(other$timestamp)) {
               return false;
            }

            Object this$window = this.getWindow();
            Object other$window = other.getWindow();
            if (this$window == null) {
               if (other$window != null) {
                  return false;
               }
            } else if (!this$window.equals(other$window)) {
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
      return other instanceof PodMetrics;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $containers = this.getContainers();
      result = result * 59 + ($containers == null ? 43 : $containers.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $timestamp = this.getTimestamp();
      result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
      Object $window = this.getWindow();
      result = result * 59 + ($window == null ? 43 : $window.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
