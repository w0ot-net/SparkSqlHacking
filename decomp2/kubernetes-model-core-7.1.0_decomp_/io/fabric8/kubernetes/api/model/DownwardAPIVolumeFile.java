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
@JsonPropertyOrder({"fieldRef", "mode", "path", "resourceFieldRef"})
public class DownwardAPIVolumeFile implements Editable, KubernetesResource {
   @JsonProperty("fieldRef")
   private ObjectFieldSelector fieldRef;
   @JsonProperty("mode")
   private Integer mode;
   @JsonProperty("path")
   private String path;
   @JsonProperty("resourceFieldRef")
   private ResourceFieldSelector resourceFieldRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DownwardAPIVolumeFile() {
   }

   public DownwardAPIVolumeFile(ObjectFieldSelector fieldRef, Integer mode, String path, ResourceFieldSelector resourceFieldRef) {
      this.fieldRef = fieldRef;
      this.mode = mode;
      this.path = path;
      this.resourceFieldRef = resourceFieldRef;
   }

   @JsonProperty("fieldRef")
   public ObjectFieldSelector getFieldRef() {
      return this.fieldRef;
   }

   @JsonProperty("fieldRef")
   public void setFieldRef(ObjectFieldSelector fieldRef) {
      this.fieldRef = fieldRef;
   }

   @JsonProperty("mode")
   public Integer getMode() {
      return this.mode;
   }

   @JsonProperty("mode")
   public void setMode(Integer mode) {
      this.mode = mode;
   }

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("resourceFieldRef")
   public ResourceFieldSelector getResourceFieldRef() {
      return this.resourceFieldRef;
   }

   @JsonProperty("resourceFieldRef")
   public void setResourceFieldRef(ResourceFieldSelector resourceFieldRef) {
      this.resourceFieldRef = resourceFieldRef;
   }

   @JsonIgnore
   public DownwardAPIVolumeFileBuilder edit() {
      return new DownwardAPIVolumeFileBuilder(this);
   }

   @JsonIgnore
   public DownwardAPIVolumeFileBuilder toBuilder() {
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
      ObjectFieldSelector var10000 = this.getFieldRef();
      return "DownwardAPIVolumeFile(fieldRef=" + var10000 + ", mode=" + this.getMode() + ", path=" + this.getPath() + ", resourceFieldRef=" + this.getResourceFieldRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DownwardAPIVolumeFile)) {
         return false;
      } else {
         DownwardAPIVolumeFile other = (DownwardAPIVolumeFile)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$mode = this.getMode();
            Object other$mode = other.getMode();
            if (this$mode == null) {
               if (other$mode != null) {
                  return false;
               }
            } else if (!this$mode.equals(other$mode)) {
               return false;
            }

            Object this$fieldRef = this.getFieldRef();
            Object other$fieldRef = other.getFieldRef();
            if (this$fieldRef == null) {
               if (other$fieldRef != null) {
                  return false;
               }
            } else if (!this$fieldRef.equals(other$fieldRef)) {
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

            Object this$resourceFieldRef = this.getResourceFieldRef();
            Object other$resourceFieldRef = other.getResourceFieldRef();
            if (this$resourceFieldRef == null) {
               if (other$resourceFieldRef != null) {
                  return false;
               }
            } else if (!this$resourceFieldRef.equals(other$resourceFieldRef)) {
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
      return other instanceof DownwardAPIVolumeFile;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $mode = this.getMode();
      result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
      Object $fieldRef = this.getFieldRef();
      result = result * 59 + ($fieldRef == null ? 43 : $fieldRef.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $resourceFieldRef = this.getResourceFieldRef();
      result = result * 59 + ($resourceFieldRef == null ? 43 : $resourceFieldRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
