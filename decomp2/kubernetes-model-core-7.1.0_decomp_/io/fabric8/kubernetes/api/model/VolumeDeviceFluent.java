package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VolumeDeviceFluent extends BaseFluent {
   private String devicePath;
   private String name;
   private Map additionalProperties;

   public VolumeDeviceFluent() {
   }

   public VolumeDeviceFluent(VolumeDevice instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeDevice instance) {
      instance = instance != null ? instance : new VolumeDevice();
      if (instance != null) {
         this.withDevicePath(instance.getDevicePath());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDevicePath() {
      return this.devicePath;
   }

   public VolumeDeviceFluent withDevicePath(String devicePath) {
      this.devicePath = devicePath;
      return this;
   }

   public boolean hasDevicePath() {
      return this.devicePath != null;
   }

   public String getName() {
      return this.name;
   }

   public VolumeDeviceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public VolumeDeviceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeDeviceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeDeviceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeDeviceFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeDeviceFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeDeviceFluent that = (VolumeDeviceFluent)o;
            if (!Objects.equals(this.devicePath, that.devicePath)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.devicePath, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.devicePath != null) {
         sb.append("devicePath:");
         sb.append(this.devicePath + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
