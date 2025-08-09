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
@JsonPropertyOrder({"endpoints", "endpointsNamespace", "path", "readOnly"})
public class GlusterfsPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("endpoints")
   private String endpoints;
   @JsonProperty("endpointsNamespace")
   private String endpointsNamespace;
   @JsonProperty("path")
   private String path;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GlusterfsPersistentVolumeSource() {
   }

   public GlusterfsPersistentVolumeSource(String endpoints, String endpointsNamespace, String path, Boolean readOnly) {
      this.endpoints = endpoints;
      this.endpointsNamespace = endpointsNamespace;
      this.path = path;
      this.readOnly = readOnly;
   }

   @JsonProperty("endpoints")
   public String getEndpoints() {
      return this.endpoints;
   }

   @JsonProperty("endpoints")
   public void setEndpoints(String endpoints) {
      this.endpoints = endpoints;
   }

   @JsonProperty("endpointsNamespace")
   public String getEndpointsNamespace() {
      return this.endpointsNamespace;
   }

   @JsonProperty("endpointsNamespace")
   public void setEndpointsNamespace(String endpointsNamespace) {
      this.endpointsNamespace = endpointsNamespace;
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

   @JsonIgnore
   public GlusterfsPersistentVolumeSourceBuilder edit() {
      return new GlusterfsPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public GlusterfsPersistentVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getEndpoints();
      return "GlusterfsPersistentVolumeSource(endpoints=" + var10000 + ", endpointsNamespace=" + this.getEndpointsNamespace() + ", path=" + this.getPath() + ", readOnly=" + this.getReadOnly() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GlusterfsPersistentVolumeSource)) {
         return false;
      } else {
         GlusterfsPersistentVolumeSource other = (GlusterfsPersistentVolumeSource)o;
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

            Object this$endpoints = this.getEndpoints();
            Object other$endpoints = other.getEndpoints();
            if (this$endpoints == null) {
               if (other$endpoints != null) {
                  return false;
               }
            } else if (!this$endpoints.equals(other$endpoints)) {
               return false;
            }

            Object this$endpointsNamespace = this.getEndpointsNamespace();
            Object other$endpointsNamespace = other.getEndpointsNamespace();
            if (this$endpointsNamespace == null) {
               if (other$endpointsNamespace != null) {
                  return false;
               }
            } else if (!this$endpointsNamespace.equals(other$endpointsNamespace)) {
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
      return other instanceof GlusterfsPersistentVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $endpoints = this.getEndpoints();
      result = result * 59 + ($endpoints == null ? 43 : $endpoints.hashCode());
      Object $endpointsNamespace = this.getEndpointsNamespace();
      result = result * 59 + ($endpointsNamespace == null ? 43 : $endpointsNamespace.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
