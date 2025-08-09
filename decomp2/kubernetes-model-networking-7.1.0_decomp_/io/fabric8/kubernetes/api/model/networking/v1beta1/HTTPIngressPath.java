package io.fabric8.kubernetes.api.model.networking.v1beta1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"backend", "path", "pathType"})
public class HTTPIngressPath implements Editable, KubernetesResource {
   @JsonProperty("backend")
   private IngressBackend backend;
   @JsonProperty("path")
   private String path;
   @JsonProperty("pathType")
   private String pathType;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPIngressPath() {
   }

   public HTTPIngressPath(IngressBackend backend, String path, String pathType) {
      this.backend = backend;
      this.path = path;
      this.pathType = pathType;
   }

   @JsonProperty("backend")
   public IngressBackend getBackend() {
      return this.backend;
   }

   @JsonProperty("backend")
   public void setBackend(IngressBackend backend) {
      this.backend = backend;
   }

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("pathType")
   public String getPathType() {
      return this.pathType;
   }

   @JsonProperty("pathType")
   public void setPathType(String pathType) {
      this.pathType = pathType;
   }

   @JsonIgnore
   public HTTPIngressPathBuilder edit() {
      return new HTTPIngressPathBuilder(this);
   }

   @JsonIgnore
   public HTTPIngressPathBuilder toBuilder() {
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
      IngressBackend var10000 = this.getBackend();
      return "HTTPIngressPath(backend=" + var10000 + ", path=" + this.getPath() + ", pathType=" + this.getPathType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPIngressPath)) {
         return false;
      } else {
         HTTPIngressPath other = (HTTPIngressPath)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$backend = this.getBackend();
            Object other$backend = other.getBackend();
            if (this$backend == null) {
               if (other$backend != null) {
                  return false;
               }
            } else if (!this$backend.equals(other$backend)) {
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

            Object this$pathType = this.getPathType();
            Object other$pathType = other.getPathType();
            if (this$pathType == null) {
               if (other$pathType != null) {
                  return false;
               }
            } else if (!this$pathType.equals(other$pathType)) {
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
      return other instanceof HTTPIngressPath;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $backend = this.getBackend();
      result = result * 59 + ($backend == null ? 43 : $backend.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $pathType = this.getPathType();
      result = result * 59 + ($pathType == null ? 43 : $pathType.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
