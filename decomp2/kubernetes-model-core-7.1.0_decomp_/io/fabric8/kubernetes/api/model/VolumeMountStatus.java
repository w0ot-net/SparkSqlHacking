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
@JsonPropertyOrder({"mountPath", "name", "readOnly", "recursiveReadOnly"})
public class VolumeMountStatus implements Editable, KubernetesResource {
   @JsonProperty("mountPath")
   private String mountPath;
   @JsonProperty("name")
   private String name;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("recursiveReadOnly")
   private String recursiveReadOnly;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeMountStatus() {
   }

   public VolumeMountStatus(String mountPath, String name, Boolean readOnly, String recursiveReadOnly) {
      this.mountPath = mountPath;
      this.name = name;
      this.readOnly = readOnly;
      this.recursiveReadOnly = recursiveReadOnly;
   }

   @JsonProperty("mountPath")
   public String getMountPath() {
      return this.mountPath;
   }

   @JsonProperty("mountPath")
   public void setMountPath(String mountPath) {
      this.mountPath = mountPath;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("recursiveReadOnly")
   public String getRecursiveReadOnly() {
      return this.recursiveReadOnly;
   }

   @JsonProperty("recursiveReadOnly")
   public void setRecursiveReadOnly(String recursiveReadOnly) {
      this.recursiveReadOnly = recursiveReadOnly;
   }

   @JsonIgnore
   public VolumeMountStatusBuilder edit() {
      return new VolumeMountStatusBuilder(this);
   }

   @JsonIgnore
   public VolumeMountStatusBuilder toBuilder() {
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
      String var10000 = this.getMountPath();
      return "VolumeMountStatus(mountPath=" + var10000 + ", name=" + this.getName() + ", readOnly=" + this.getReadOnly() + ", recursiveReadOnly=" + this.getRecursiveReadOnly() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeMountStatus)) {
         return false;
      } else {
         VolumeMountStatus other = (VolumeMountStatus)o;
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

            Object this$mountPath = this.getMountPath();
            Object other$mountPath = other.getMountPath();
            if (this$mountPath == null) {
               if (other$mountPath != null) {
                  return false;
               }
            } else if (!this$mountPath.equals(other$mountPath)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$recursiveReadOnly = this.getRecursiveReadOnly();
            Object other$recursiveReadOnly = other.getRecursiveReadOnly();
            if (this$recursiveReadOnly == null) {
               if (other$recursiveReadOnly != null) {
                  return false;
               }
            } else if (!this$recursiveReadOnly.equals(other$recursiveReadOnly)) {
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
      return other instanceof VolumeMountStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $mountPath = this.getMountPath();
      result = result * 59 + ($mountPath == null ? 43 : $mountPath.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $recursiveReadOnly = this.getRecursiveReadOnly();
      result = result * 59 + ($recursiveReadOnly == null ? 43 : $recursiveReadOnly.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
