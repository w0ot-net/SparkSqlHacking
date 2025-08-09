package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ModifyVolumeStatusFluent extends BaseFluent {
   private String status;
   private String targetVolumeAttributesClassName;
   private Map additionalProperties;

   public ModifyVolumeStatusFluent() {
   }

   public ModifyVolumeStatusFluent(ModifyVolumeStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ModifyVolumeStatus instance) {
      instance = instance != null ? instance : new ModifyVolumeStatus();
      if (instance != null) {
         this.withStatus(instance.getStatus());
         this.withTargetVolumeAttributesClassName(instance.getTargetVolumeAttributesClassName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getStatus() {
      return this.status;
   }

   public ModifyVolumeStatusFluent withStatus(String status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public String getTargetVolumeAttributesClassName() {
      return this.targetVolumeAttributesClassName;
   }

   public ModifyVolumeStatusFluent withTargetVolumeAttributesClassName(String targetVolumeAttributesClassName) {
      this.targetVolumeAttributesClassName = targetVolumeAttributesClassName;
      return this;
   }

   public boolean hasTargetVolumeAttributesClassName() {
      return this.targetVolumeAttributesClassName != null;
   }

   public ModifyVolumeStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ModifyVolumeStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ModifyVolumeStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ModifyVolumeStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ModifyVolumeStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ModifyVolumeStatusFluent that = (ModifyVolumeStatusFluent)o;
            if (!Objects.equals(this.status, that.status)) {
               return false;
            } else if (!Objects.equals(this.targetVolumeAttributesClassName, that.targetVolumeAttributesClassName)) {
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
      return Objects.hash(new Object[]{this.status, this.targetVolumeAttributesClassName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.targetVolumeAttributesClassName != null) {
         sb.append("targetVolumeAttributesClassName:");
         sb.append(this.targetVolumeAttributesClassName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
