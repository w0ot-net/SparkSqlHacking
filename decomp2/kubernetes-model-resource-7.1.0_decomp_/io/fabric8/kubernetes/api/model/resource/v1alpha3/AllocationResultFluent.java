package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AllocationResultFluent extends BaseFluent {
   private DeviceAllocationResultBuilder devices;
   private NodeSelector nodeSelector;
   private Map additionalProperties;

   public AllocationResultFluent() {
   }

   public AllocationResultFluent(AllocationResult instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AllocationResult instance) {
      instance = instance != null ? instance : new AllocationResult();
      if (instance != null) {
         this.withDevices(instance.getDevices());
         this.withNodeSelector(instance.getNodeSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DeviceAllocationResult buildDevices() {
      return this.devices != null ? this.devices.build() : null;
   }

   public AllocationResultFluent withDevices(DeviceAllocationResult devices) {
      this._visitables.remove("devices");
      if (devices != null) {
         this.devices = new DeviceAllocationResultBuilder(devices);
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
      return new DevicesNested((DeviceAllocationResult)null);
   }

   public DevicesNested withNewDevicesLike(DeviceAllocationResult item) {
      return new DevicesNested(item);
   }

   public DevicesNested editDevices() {
      return this.withNewDevicesLike((DeviceAllocationResult)Optional.ofNullable(this.buildDevices()).orElse((Object)null));
   }

   public DevicesNested editOrNewDevices() {
      return this.withNewDevicesLike((DeviceAllocationResult)Optional.ofNullable(this.buildDevices()).orElse((new DeviceAllocationResultBuilder()).build()));
   }

   public DevicesNested editOrNewDevicesLike(DeviceAllocationResult item) {
      return this.withNewDevicesLike((DeviceAllocationResult)Optional.ofNullable(this.buildDevices()).orElse(item));
   }

   public NodeSelector getNodeSelector() {
      return this.nodeSelector;
   }

   public AllocationResultFluent withNodeSelector(NodeSelector nodeSelector) {
      this.nodeSelector = nodeSelector;
      return this;
   }

   public boolean hasNodeSelector() {
      return this.nodeSelector != null;
   }

   public AllocationResultFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AllocationResultFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AllocationResultFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AllocationResultFluent removeFromAdditionalProperties(Map map) {
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

   public AllocationResultFluent withAdditionalProperties(Map additionalProperties) {
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
            AllocationResultFluent that = (AllocationResultFluent)o;
            if (!Objects.equals(this.devices, that.devices)) {
               return false;
            } else if (!Objects.equals(this.nodeSelector, that.nodeSelector)) {
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
      return Objects.hash(new Object[]{this.devices, this.nodeSelector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.devices != null) {
         sb.append("devices:");
         sb.append(this.devices + ",");
      }

      if (this.nodeSelector != null) {
         sb.append("nodeSelector:");
         sb.append(this.nodeSelector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DevicesNested extends DeviceAllocationResultFluent implements Nested {
      DeviceAllocationResultBuilder builder;

      DevicesNested(DeviceAllocationResult item) {
         this.builder = new DeviceAllocationResultBuilder(this, item);
      }

      public Object and() {
         return AllocationResultFluent.this.withDevices(this.builder.build());
      }

      public Object endDevices() {
         return this.and();
      }
   }
}
