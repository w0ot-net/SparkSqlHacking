package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ResourceSliceSpecFluent extends BaseFluent {
   private Boolean allNodes;
   private ArrayList devices = new ArrayList();
   private String driver;
   private String nodeName;
   private NodeSelector nodeSelector;
   private ResourcePoolBuilder pool;
   private Map additionalProperties;

   public ResourceSliceSpecFluent() {
   }

   public ResourceSliceSpecFluent(ResourceSliceSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceSliceSpec instance) {
      instance = instance != null ? instance : new ResourceSliceSpec();
      if (instance != null) {
         this.withAllNodes(instance.getAllNodes());
         this.withDevices(instance.getDevices());
         this.withDriver(instance.getDriver());
         this.withNodeName(instance.getNodeName());
         this.withNodeSelector(instance.getNodeSelector());
         this.withPool(instance.getPool());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllNodes() {
      return this.allNodes;
   }

   public ResourceSliceSpecFluent withAllNodes(Boolean allNodes) {
      this.allNodes = allNodes;
      return this;
   }

   public boolean hasAllNodes() {
      return this.allNodes != null;
   }

   public ResourceSliceSpecFluent addToDevices(int index, Device item) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      DeviceBuilder builder = new DeviceBuilder(item);
      if (index >= 0 && index < this.devices.size()) {
         this._visitables.get("devices").add(index, builder);
         this.devices.add(index, builder);
      } else {
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceSliceSpecFluent setToDevices(int index, Device item) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      DeviceBuilder builder = new DeviceBuilder(item);
      if (index >= 0 && index < this.devices.size()) {
         this._visitables.get("devices").set(index, builder);
         this.devices.set(index, builder);
      } else {
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceSliceSpecFluent addToDevices(Device... items) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      for(Device item : items) {
         DeviceBuilder builder = new DeviceBuilder(item);
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceSliceSpecFluent addAllToDevices(Collection items) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      for(Device item : items) {
         DeviceBuilder builder = new DeviceBuilder(item);
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceSliceSpecFluent removeFromDevices(Device... items) {
      if (this.devices == null) {
         return this;
      } else {
         for(Device item : items) {
            DeviceBuilder builder = new DeviceBuilder(item);
            this._visitables.get("devices").remove(builder);
            this.devices.remove(builder);
         }

         return this;
      }
   }

   public ResourceSliceSpecFluent removeAllFromDevices(Collection items) {
      if (this.devices == null) {
         return this;
      } else {
         for(Device item : items) {
            DeviceBuilder builder = new DeviceBuilder(item);
            this._visitables.get("devices").remove(builder);
            this.devices.remove(builder);
         }

         return this;
      }
   }

   public ResourceSliceSpecFluent removeMatchingFromDevices(Predicate predicate) {
      if (this.devices == null) {
         return this;
      } else {
         Iterator<DeviceBuilder> each = this.devices.iterator();
         List visitables = this._visitables.get("devices");

         while(each.hasNext()) {
            DeviceBuilder builder = (DeviceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildDevices() {
      return this.devices != null ? build(this.devices) : null;
   }

   public Device buildDevice(int index) {
      return ((DeviceBuilder)this.devices.get(index)).build();
   }

   public Device buildFirstDevice() {
      return ((DeviceBuilder)this.devices.get(0)).build();
   }

   public Device buildLastDevice() {
      return ((DeviceBuilder)this.devices.get(this.devices.size() - 1)).build();
   }

   public Device buildMatchingDevice(Predicate predicate) {
      for(DeviceBuilder item : this.devices) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingDevice(Predicate predicate) {
      for(DeviceBuilder item : this.devices) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceSliceSpecFluent withDevices(List devices) {
      if (this.devices != null) {
         this._visitables.get("devices").clear();
      }

      if (devices != null) {
         this.devices = new ArrayList();

         for(Device item : devices) {
            this.addToDevices(item);
         }
      } else {
         this.devices = null;
      }

      return this;
   }

   public ResourceSliceSpecFluent withDevices(Device... devices) {
      if (this.devices != null) {
         this.devices.clear();
         this._visitables.remove("devices");
      }

      if (devices != null) {
         for(Device item : devices) {
            this.addToDevices(item);
         }
      }

      return this;
   }

   public boolean hasDevices() {
      return this.devices != null && !this.devices.isEmpty();
   }

   public DevicesNested addNewDevice() {
      return new DevicesNested(-1, (Device)null);
   }

   public DevicesNested addNewDeviceLike(Device item) {
      return new DevicesNested(-1, item);
   }

   public DevicesNested setNewDeviceLike(int index, Device item) {
      return new DevicesNested(index, item);
   }

   public DevicesNested editDevice(int index) {
      if (this.devices.size() <= index) {
         throw new RuntimeException("Can't edit devices. Index exceeds size.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public DevicesNested editFirstDevice() {
      if (this.devices.size() == 0) {
         throw new RuntimeException("Can't edit first devices. The list is empty.");
      } else {
         return this.setNewDeviceLike(0, this.buildDevice(0));
      }
   }

   public DevicesNested editLastDevice() {
      int index = this.devices.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last devices. The list is empty.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public DevicesNested editMatchingDevice(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.devices.size(); ++i) {
         if (predicate.test((DeviceBuilder)this.devices.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching devices. No match found.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public String getDriver() {
      return this.driver;
   }

   public ResourceSliceSpecFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public String getNodeName() {
      return this.nodeName;
   }

   public ResourceSliceSpecFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public NodeSelector getNodeSelector() {
      return this.nodeSelector;
   }

   public ResourceSliceSpecFluent withNodeSelector(NodeSelector nodeSelector) {
      this.nodeSelector = nodeSelector;
      return this;
   }

   public boolean hasNodeSelector() {
      return this.nodeSelector != null;
   }

   public ResourcePool buildPool() {
      return this.pool != null ? this.pool.build() : null;
   }

   public ResourceSliceSpecFluent withPool(ResourcePool pool) {
      this._visitables.remove("pool");
      if (pool != null) {
         this.pool = new ResourcePoolBuilder(pool);
         this._visitables.get("pool").add(this.pool);
      } else {
         this.pool = null;
         this._visitables.get("pool").remove(this.pool);
      }

      return this;
   }

   public boolean hasPool() {
      return this.pool != null;
   }

   public ResourceSliceSpecFluent withNewPool(Long generation, String name, Long resourceSliceCount) {
      return this.withPool(new ResourcePool(generation, name, resourceSliceCount));
   }

   public PoolNested withNewPool() {
      return new PoolNested((ResourcePool)null);
   }

   public PoolNested withNewPoolLike(ResourcePool item) {
      return new PoolNested(item);
   }

   public PoolNested editPool() {
      return this.withNewPoolLike((ResourcePool)Optional.ofNullable(this.buildPool()).orElse((Object)null));
   }

   public PoolNested editOrNewPool() {
      return this.withNewPoolLike((ResourcePool)Optional.ofNullable(this.buildPool()).orElse((new ResourcePoolBuilder()).build()));
   }

   public PoolNested editOrNewPoolLike(ResourcePool item) {
      return this.withNewPoolLike((ResourcePool)Optional.ofNullable(this.buildPool()).orElse(item));
   }

   public ResourceSliceSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceSliceSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceSliceSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceSliceSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceSliceSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceSliceSpecFluent that = (ResourceSliceSpecFluent)o;
            if (!Objects.equals(this.allNodes, that.allNodes)) {
               return false;
            } else if (!Objects.equals(this.devices, that.devices)) {
               return false;
            } else if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.nodeSelector, that.nodeSelector)) {
               return false;
            } else if (!Objects.equals(this.pool, that.pool)) {
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
      return Objects.hash(new Object[]{this.allNodes, this.devices, this.driver, this.nodeName, this.nodeSelector, this.pool, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allNodes != null) {
         sb.append("allNodes:");
         sb.append(this.allNodes + ",");
      }

      if (this.devices != null && !this.devices.isEmpty()) {
         sb.append("devices:");
         sb.append(this.devices + ",");
      }

      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.nodeSelector != null) {
         sb.append("nodeSelector:");
         sb.append(this.nodeSelector + ",");
      }

      if (this.pool != null) {
         sb.append("pool:");
         sb.append(this.pool + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ResourceSliceSpecFluent withAllNodes() {
      return this.withAllNodes(true);
   }

   public class DevicesNested extends DeviceFluent implements Nested {
      DeviceBuilder builder;
      int index;

      DevicesNested(int index, Device item) {
         this.index = index;
         this.builder = new DeviceBuilder(this, item);
      }

      public Object and() {
         return ResourceSliceSpecFluent.this.setToDevices(this.index, this.builder.build());
      }

      public Object endDevice() {
         return this.and();
      }
   }

   public class PoolNested extends ResourcePoolFluent implements Nested {
      ResourcePoolBuilder builder;

      PoolNested(ResourcePool item) {
         this.builder = new ResourcePoolBuilder(this, item);
      }

      public Object and() {
         return ResourceSliceSpecFluent.this.withPool(this.builder.build());
      }

      public Object endPool() {
         return this.and();
      }
   }
}
