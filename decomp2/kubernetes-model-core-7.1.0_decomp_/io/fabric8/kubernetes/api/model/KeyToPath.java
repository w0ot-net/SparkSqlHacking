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
@JsonPropertyOrder({"key", "mode", "path"})
public class KeyToPath implements Editable, KubernetesResource {
   @JsonProperty("key")
   private String key;
   @JsonProperty("mode")
   private Integer mode;
   @JsonProperty("path")
   private String path;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public KeyToPath() {
   }

   public KeyToPath(String key, Integer mode, String path) {
      this.key = key;
      this.mode = mode;
      this.path = path;
   }

   @JsonProperty("key")
   public String getKey() {
      return this.key;
   }

   @JsonProperty("key")
   public void setKey(String key) {
      this.key = key;
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

   @JsonIgnore
   public KeyToPathBuilder edit() {
      return new KeyToPathBuilder(this);
   }

   @JsonIgnore
   public KeyToPathBuilder toBuilder() {
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
      String var10000 = this.getKey();
      return "KeyToPath(key=" + var10000 + ", mode=" + this.getMode() + ", path=" + this.getPath() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof KeyToPath)) {
         return false;
      } else {
         KeyToPath other = (KeyToPath)o;
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

            Object this$key = this.getKey();
            Object other$key = other.getKey();
            if (this$key == null) {
               if (other$key != null) {
                  return false;
               }
            } else if (!this$key.equals(other$key)) {
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
      return other instanceof KeyToPath;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $mode = this.getMode();
      result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
      Object $key = this.getKey();
      result = result * 59 + ($key == null ? 43 : $key.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
