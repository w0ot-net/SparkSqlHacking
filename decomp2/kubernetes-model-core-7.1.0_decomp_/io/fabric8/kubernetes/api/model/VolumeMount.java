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
@JsonPropertyOrder({"mountPath", "mountPropagation", "name", "readOnly", "recursiveReadOnly", "subPath", "subPathExpr"})
public class VolumeMount implements Editable, KubernetesResource {
   @JsonProperty("mountPath")
   private String mountPath;
   @JsonProperty("mountPropagation")
   private String mountPropagation;
   @JsonProperty("name")
   private String name;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("recursiveReadOnly")
   private String recursiveReadOnly;
   @JsonProperty("subPath")
   private String subPath;
   @JsonProperty("subPathExpr")
   private String subPathExpr;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeMount() {
   }

   public VolumeMount(String mountPath, String mountPropagation, String name, Boolean readOnly, String recursiveReadOnly, String subPath, String subPathExpr) {
      this.mountPath = mountPath;
      this.mountPropagation = mountPropagation;
      this.name = name;
      this.readOnly = readOnly;
      this.recursiveReadOnly = recursiveReadOnly;
      this.subPath = subPath;
      this.subPathExpr = subPathExpr;
   }

   @JsonProperty("mountPath")
   public String getMountPath() {
      return this.mountPath;
   }

   @JsonProperty("mountPath")
   public void setMountPath(String mountPath) {
      this.mountPath = mountPath;
   }

   @JsonProperty("mountPropagation")
   public String getMountPropagation() {
      return this.mountPropagation;
   }

   @JsonProperty("mountPropagation")
   public void setMountPropagation(String mountPropagation) {
      this.mountPropagation = mountPropagation;
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

   @JsonProperty("subPath")
   public String getSubPath() {
      return this.subPath;
   }

   @JsonProperty("subPath")
   public void setSubPath(String subPath) {
      this.subPath = subPath;
   }

   @JsonProperty("subPathExpr")
   public String getSubPathExpr() {
      return this.subPathExpr;
   }

   @JsonProperty("subPathExpr")
   public void setSubPathExpr(String subPathExpr) {
      this.subPathExpr = subPathExpr;
   }

   @JsonIgnore
   public VolumeMountBuilder edit() {
      return new VolumeMountBuilder(this);
   }

   @JsonIgnore
   public VolumeMountBuilder toBuilder() {
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
      return "VolumeMount(mountPath=" + var10000 + ", mountPropagation=" + this.getMountPropagation() + ", name=" + this.getName() + ", readOnly=" + this.getReadOnly() + ", recursiveReadOnly=" + this.getRecursiveReadOnly() + ", subPath=" + this.getSubPath() + ", subPathExpr=" + this.getSubPathExpr() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeMount)) {
         return false;
      } else {
         VolumeMount other = (VolumeMount)o;
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

            Object this$mountPropagation = this.getMountPropagation();
            Object other$mountPropagation = other.getMountPropagation();
            if (this$mountPropagation == null) {
               if (other$mountPropagation != null) {
                  return false;
               }
            } else if (!this$mountPropagation.equals(other$mountPropagation)) {
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

            Object this$subPath = this.getSubPath();
            Object other$subPath = other.getSubPath();
            if (this$subPath == null) {
               if (other$subPath != null) {
                  return false;
               }
            } else if (!this$subPath.equals(other$subPath)) {
               return false;
            }

            Object this$subPathExpr = this.getSubPathExpr();
            Object other$subPathExpr = other.getSubPathExpr();
            if (this$subPathExpr == null) {
               if (other$subPathExpr != null) {
                  return false;
               }
            } else if (!this$subPathExpr.equals(other$subPathExpr)) {
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
      return other instanceof VolumeMount;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $mountPath = this.getMountPath();
      result = result * 59 + ($mountPath == null ? 43 : $mountPath.hashCode());
      Object $mountPropagation = this.getMountPropagation();
      result = result * 59 + ($mountPropagation == null ? 43 : $mountPropagation.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $recursiveReadOnly = this.getRecursiveReadOnly();
      result = result * 59 + ($recursiveReadOnly == null ? 43 : $recursiveReadOnly.hashCode());
      Object $subPath = this.getSubPath();
      result = result * 59 + ($subPath == null ? 43 : $subPath.hashCode());
      Object $subPathExpr = this.getSubPathExpr();
      result = result * 59 + ($subPathExpr == null ? 43 : $subPathExpr.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
