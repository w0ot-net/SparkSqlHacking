package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VolumeMountFluent extends BaseFluent {
   private String mountPath;
   private String mountPropagation;
   private String name;
   private Boolean readOnly;
   private String recursiveReadOnly;
   private String subPath;
   private String subPathExpr;
   private Map additionalProperties;

   public VolumeMountFluent() {
   }

   public VolumeMountFluent(VolumeMount instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeMount instance) {
      instance = instance != null ? instance : new VolumeMount();
      if (instance != null) {
         this.withMountPath(instance.getMountPath());
         this.withMountPropagation(instance.getMountPropagation());
         this.withName(instance.getName());
         this.withReadOnly(instance.getReadOnly());
         this.withRecursiveReadOnly(instance.getRecursiveReadOnly());
         this.withSubPath(instance.getSubPath());
         this.withSubPathExpr(instance.getSubPathExpr());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMountPath() {
      return this.mountPath;
   }

   public VolumeMountFluent withMountPath(String mountPath) {
      this.mountPath = mountPath;
      return this;
   }

   public boolean hasMountPath() {
      return this.mountPath != null;
   }

   public String getMountPropagation() {
      return this.mountPropagation;
   }

   public VolumeMountFluent withMountPropagation(String mountPropagation) {
      this.mountPropagation = mountPropagation;
      return this;
   }

   public boolean hasMountPropagation() {
      return this.mountPropagation != null;
   }

   public String getName() {
      return this.name;
   }

   public VolumeMountFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public VolumeMountFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getRecursiveReadOnly() {
      return this.recursiveReadOnly;
   }

   public VolumeMountFluent withRecursiveReadOnly(String recursiveReadOnly) {
      this.recursiveReadOnly = recursiveReadOnly;
      return this;
   }

   public boolean hasRecursiveReadOnly() {
      return this.recursiveReadOnly != null;
   }

   public String getSubPath() {
      return this.subPath;
   }

   public VolumeMountFluent withSubPath(String subPath) {
      this.subPath = subPath;
      return this;
   }

   public boolean hasSubPath() {
      return this.subPath != null;
   }

   public String getSubPathExpr() {
      return this.subPathExpr;
   }

   public VolumeMountFluent withSubPathExpr(String subPathExpr) {
      this.subPathExpr = subPathExpr;
      return this;
   }

   public boolean hasSubPathExpr() {
      return this.subPathExpr != null;
   }

   public VolumeMountFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeMountFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeMountFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeMountFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public VolumeMountFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            VolumeMountFluent that = (VolumeMountFluent)o;
            if (!Objects.equals(this.mountPath, that.mountPath)) {
               return false;
            } else if (!Objects.equals(this.mountPropagation, that.mountPropagation)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.recursiveReadOnly, that.recursiveReadOnly)) {
               return false;
            } else if (!Objects.equals(this.subPath, that.subPath)) {
               return false;
            } else if (!Objects.equals(this.subPathExpr, that.subPathExpr)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.mountPath, this.mountPropagation, this.name, this.readOnly, this.recursiveReadOnly, this.subPath, this.subPathExpr, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.mountPath != null) {
         sb.append("mountPath:");
         sb.append(this.mountPath + ",");
      }

      if (this.mountPropagation != null) {
         sb.append("mountPropagation:");
         sb.append(this.mountPropagation + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.recursiveReadOnly != null) {
         sb.append("recursiveReadOnly:");
         sb.append(this.recursiveReadOnly + ",");
      }

      if (this.subPath != null) {
         sb.append("subPath:");
         sb.append(this.subPath + ",");
      }

      if (this.subPathExpr != null) {
         sb.append("subPathExpr:");
         sb.append(this.subPathExpr + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public VolumeMountFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
