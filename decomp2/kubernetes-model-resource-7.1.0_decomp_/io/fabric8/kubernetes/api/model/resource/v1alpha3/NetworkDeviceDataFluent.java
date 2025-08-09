package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NetworkDeviceDataFluent extends BaseFluent {
   private String hardwareAddress;
   private String interfaceName;
   private List ips = new ArrayList();
   private Map additionalProperties;

   public NetworkDeviceDataFluent() {
   }

   public NetworkDeviceDataFluent(NetworkDeviceData instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkDeviceData instance) {
      instance = instance != null ? instance : new NetworkDeviceData();
      if (instance != null) {
         this.withHardwareAddress(instance.getHardwareAddress());
         this.withInterfaceName(instance.getInterfaceName());
         this.withIps(instance.getIps());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHardwareAddress() {
      return this.hardwareAddress;
   }

   public NetworkDeviceDataFluent withHardwareAddress(String hardwareAddress) {
      this.hardwareAddress = hardwareAddress;
      return this;
   }

   public boolean hasHardwareAddress() {
      return this.hardwareAddress != null;
   }

   public String getInterfaceName() {
      return this.interfaceName;
   }

   public NetworkDeviceDataFluent withInterfaceName(String interfaceName) {
      this.interfaceName = interfaceName;
      return this;
   }

   public boolean hasInterfaceName() {
      return this.interfaceName != null;
   }

   public NetworkDeviceDataFluent addToIps(int index, String item) {
      if (this.ips == null) {
         this.ips = new ArrayList();
      }

      this.ips.add(index, item);
      return this;
   }

   public NetworkDeviceDataFluent setToIps(int index, String item) {
      if (this.ips == null) {
         this.ips = new ArrayList();
      }

      this.ips.set(index, item);
      return this;
   }

   public NetworkDeviceDataFluent addToIps(String... items) {
      if (this.ips == null) {
         this.ips = new ArrayList();
      }

      for(String item : items) {
         this.ips.add(item);
      }

      return this;
   }

   public NetworkDeviceDataFluent addAllToIps(Collection items) {
      if (this.ips == null) {
         this.ips = new ArrayList();
      }

      for(String item : items) {
         this.ips.add(item);
      }

      return this;
   }

   public NetworkDeviceDataFluent removeFromIps(String... items) {
      if (this.ips == null) {
         return this;
      } else {
         for(String item : items) {
            this.ips.remove(item);
         }

         return this;
      }
   }

   public NetworkDeviceDataFluent removeAllFromIps(Collection items) {
      if (this.ips == null) {
         return this;
      } else {
         for(String item : items) {
            this.ips.remove(item);
         }

         return this;
      }
   }

   public List getIps() {
      return this.ips;
   }

   public String getIp(int index) {
      return (String)this.ips.get(index);
   }

   public String getFirstIp() {
      return (String)this.ips.get(0);
   }

   public String getLastIp() {
      return (String)this.ips.get(this.ips.size() - 1);
   }

   public String getMatchingIp(Predicate predicate) {
      for(String item : this.ips) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingIp(Predicate predicate) {
      for(String item : this.ips) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkDeviceDataFluent withIps(List ips) {
      if (ips != null) {
         this.ips = new ArrayList();

         for(String item : ips) {
            this.addToIps(item);
         }
      } else {
         this.ips = null;
      }

      return this;
   }

   public NetworkDeviceDataFluent withIps(String... ips) {
      if (this.ips != null) {
         this.ips.clear();
         this._visitables.remove("ips");
      }

      if (ips != null) {
         for(String item : ips) {
            this.addToIps(item);
         }
      }

      return this;
   }

   public boolean hasIps() {
      return this.ips != null && !this.ips.isEmpty();
   }

   public NetworkDeviceDataFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkDeviceDataFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkDeviceDataFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkDeviceDataFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkDeviceDataFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkDeviceDataFluent that = (NetworkDeviceDataFluent)o;
            if (!Objects.equals(this.hardwareAddress, that.hardwareAddress)) {
               return false;
            } else if (!Objects.equals(this.interfaceName, that.interfaceName)) {
               return false;
            } else if (!Objects.equals(this.ips, that.ips)) {
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
      return Objects.hash(new Object[]{this.hardwareAddress, this.interfaceName, this.ips, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hardwareAddress != null) {
         sb.append("hardwareAddress:");
         sb.append(this.hardwareAddress + ",");
      }

      if (this.interfaceName != null) {
         sb.append("interfaceName:");
         sb.append(this.interfaceName + ",");
      }

      if (this.ips != null && !this.ips.isEmpty()) {
         sb.append("ips:");
         sb.append(this.ips + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
