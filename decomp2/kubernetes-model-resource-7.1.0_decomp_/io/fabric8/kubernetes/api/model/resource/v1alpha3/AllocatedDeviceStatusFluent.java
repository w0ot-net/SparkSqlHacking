package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class AllocatedDeviceStatusFluent extends BaseFluent {
   private List conditions = new ArrayList();
   private Object data;
   private String device;
   private String driver;
   private NetworkDeviceDataBuilder networkData;
   private String pool;
   private Map additionalProperties;

   public AllocatedDeviceStatusFluent() {
   }

   public AllocatedDeviceStatusFluent(AllocatedDeviceStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AllocatedDeviceStatus instance) {
      instance = instance != null ? instance : new AllocatedDeviceStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withData(instance.getData());
         this.withDevice(instance.getDevice());
         this.withDriver(instance.getDriver());
         this.withNetworkData(instance.getNetworkData());
         this.withPool(instance.getPool());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AllocatedDeviceStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public AllocatedDeviceStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public AllocatedDeviceStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public AllocatedDeviceStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public AllocatedDeviceStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public AllocatedDeviceStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public List getConditions() {
      return this.conditions;
   }

   public Condition getCondition(int index) {
      return (Condition)this.conditions.get(index);
   }

   public Condition getFirstCondition() {
      return (Condition)this.conditions.get(0);
   }

   public Condition getLastCondition() {
      return (Condition)this.conditions.get(this.conditions.size() - 1);
   }

   public Condition getMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AllocatedDeviceStatusFluent withConditions(List conditions) {
      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public AllocatedDeviceStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public Object getData() {
      return this.data;
   }

   public AllocatedDeviceStatusFluent withData(Object data) {
      this.data = data;
      return this;
   }

   public boolean hasData() {
      return this.data != null;
   }

   public String getDevice() {
      return this.device;
   }

   public AllocatedDeviceStatusFluent withDevice(String device) {
      this.device = device;
      return this;
   }

   public boolean hasDevice() {
      return this.device != null;
   }

   public String getDriver() {
      return this.driver;
   }

   public AllocatedDeviceStatusFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public NetworkDeviceData buildNetworkData() {
      return this.networkData != null ? this.networkData.build() : null;
   }

   public AllocatedDeviceStatusFluent withNetworkData(NetworkDeviceData networkData) {
      this._visitables.remove("networkData");
      if (networkData != null) {
         this.networkData = new NetworkDeviceDataBuilder(networkData);
         this._visitables.get("networkData").add(this.networkData);
      } else {
         this.networkData = null;
         this._visitables.get("networkData").remove(this.networkData);
      }

      return this;
   }

   public boolean hasNetworkData() {
      return this.networkData != null;
   }

   public NetworkDataNested withNewNetworkData() {
      return new NetworkDataNested((NetworkDeviceData)null);
   }

   public NetworkDataNested withNewNetworkDataLike(NetworkDeviceData item) {
      return new NetworkDataNested(item);
   }

   public NetworkDataNested editNetworkData() {
      return this.withNewNetworkDataLike((NetworkDeviceData)Optional.ofNullable(this.buildNetworkData()).orElse((Object)null));
   }

   public NetworkDataNested editOrNewNetworkData() {
      return this.withNewNetworkDataLike((NetworkDeviceData)Optional.ofNullable(this.buildNetworkData()).orElse((new NetworkDeviceDataBuilder()).build()));
   }

   public NetworkDataNested editOrNewNetworkDataLike(NetworkDeviceData item) {
      return this.withNewNetworkDataLike((NetworkDeviceData)Optional.ofNullable(this.buildNetworkData()).orElse(item));
   }

   public String getPool() {
      return this.pool;
   }

   public AllocatedDeviceStatusFluent withPool(String pool) {
      this.pool = pool;
      return this;
   }

   public boolean hasPool() {
      return this.pool != null;
   }

   public AllocatedDeviceStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AllocatedDeviceStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AllocatedDeviceStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AllocatedDeviceStatusFluent removeFromAdditionalProperties(Map map) {
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

   public AllocatedDeviceStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            AllocatedDeviceStatusFluent that = (AllocatedDeviceStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.data, that.data)) {
               return false;
            } else if (!Objects.equals(this.device, that.device)) {
               return false;
            } else if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.networkData, that.networkData)) {
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
      return Objects.hash(new Object[]{this.conditions, this.data, this.device, this.driver, this.networkData, this.pool, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.data != null) {
         sb.append("data:");
         sb.append(this.data + ",");
      }

      if (this.device != null) {
         sb.append("device:");
         sb.append(this.device + ",");
      }

      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.networkData != null) {
         sb.append("networkData:");
         sb.append(this.networkData + ",");
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

   public class NetworkDataNested extends NetworkDeviceDataFluent implements Nested {
      NetworkDeviceDataBuilder builder;

      NetworkDataNested(NetworkDeviceData item) {
         this.builder = new NetworkDeviceDataBuilder(this, item);
      }

      public Object and() {
         return AllocatedDeviceStatusFluent.this.withNetworkData(this.builder.build());
      }

      public Object endNetworkData() {
         return this.and();
      }
   }
}
