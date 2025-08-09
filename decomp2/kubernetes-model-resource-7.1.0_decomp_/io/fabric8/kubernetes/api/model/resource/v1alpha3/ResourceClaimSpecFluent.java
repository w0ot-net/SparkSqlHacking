package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceClaimSpecFluent extends BaseFluent {
   private DeviceClaimBuilder devices;
   private Map additionalProperties;

   public ResourceClaimSpecFluent() {
   }

   public ResourceClaimSpecFluent(ResourceClaimSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimSpec instance) {
      instance = instance != null ? instance : new ResourceClaimSpec();
      if (instance != null) {
         this.withDevices(instance.getDevices());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DeviceClaim buildDevices() {
      return this.devices != null ? this.devices.build() : null;
   }

   public ResourceClaimSpecFluent withDevices(DeviceClaim devices) {
      this._visitables.remove("devices");
      if (devices != null) {
         this.devices = new DeviceClaimBuilder(devices);
         this._visitables.get("devices").add(this.devices);
      } else {
         this.devices = null;
         this._visitables.get("devices").remove(this.devices);
      }

      return this;
   }

   public boolean hasDevices() {
      return this.devices != null;
   }

   public DevicesNested withNewDevices() {
      return new DevicesNested((DeviceClaim)null);
   }

   public DevicesNested withNewDevicesLike(DeviceClaim item) {
      return new DevicesNested(item);
   }

   public DevicesNested editDevices() {
      return this.withNewDevicesLike((DeviceClaim)Optional.ofNullable(this.buildDevices()).orElse((Object)null));
   }

   public DevicesNested editOrNewDevices() {
      return this.withNewDevicesLike((DeviceClaim)Optional.ofNullable(this.buildDevices()).orElse((new DeviceClaimBuilder()).build()));
   }

   public DevicesNested editOrNewDevicesLike(DeviceClaim item) {
      return this.withNewDevicesLike((DeviceClaim)Optional.ofNullable(this.buildDevices()).orElse(item));
   }

   public ResourceClaimSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClaimSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClaimSpecFluent that = (ResourceClaimSpecFluent)o;
            if (!Objects.equals(this.devices, that.devices)) {
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
      return Objects.hash(new Object[]{this.devices, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.devices != null) {
         sb.append("devices:");
         sb.append(this.devices + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DevicesNested extends DeviceClaimFluent implements Nested {
      DeviceClaimBuilder builder;

      DevicesNested(DeviceClaim item) {
         this.builder = new DeviceClaimBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimSpecFluent.this.withDevices(this.builder.build());
      }

      public Object endDevices() {
         return this.and();
      }
   }
}
