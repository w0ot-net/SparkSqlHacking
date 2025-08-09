package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeviceRequestAllocationResultFluent extends BaseFluent {
   private Boolean adminAccess;
   private String device;
   private String driver;
   private String pool;
   private String request;
   private Map additionalProperties;

   public DeviceRequestAllocationResultFluent() {
   }

   public DeviceRequestAllocationResultFluent(DeviceRequestAllocationResult instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceRequestAllocationResult instance) {
      instance = instance != null ? instance : new DeviceRequestAllocationResult();
      if (instance != null) {
         this.withAdminAccess(instance.getAdminAccess());
         this.withDevice(instance.getDevice());
         this.withDriver(instance.getDriver());
         this.withPool(instance.getPool());
         this.withRequest(instance.getRequest());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAdminAccess() {
      return this.adminAccess;
   }

   public DeviceRequestAllocationResultFluent withAdminAccess(Boolean adminAccess) {
      this.adminAccess = adminAccess;
      return this;
   }

   public boolean hasAdminAccess() {
      return this.adminAccess != null;
   }

   public String getDevice() {
      return this.device;
   }

   public DeviceRequestAllocationResultFluent withDevice(String device) {
      this.device = device;
      return this;
   }

   public boolean hasDevice() {
      return this.device != null;
   }

   public String getDriver() {
      return this.driver;
   }

   public DeviceRequestAllocationResultFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public String getPool() {
      return this.pool;
   }

   public DeviceRequestAllocationResultFluent withPool(String pool) {
      this.pool = pool;
      return this;
   }

   public boolean hasPool() {
      return this.pool != null;
   }

   public String getRequest() {
      return this.request;
   }

   public DeviceRequestAllocationResultFluent withRequest(String request) {
      this.request = request;
      return this;
   }

   public boolean hasRequest() {
      return this.request != null;
   }

   public DeviceRequestAllocationResultFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceRequestAllocationResultFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceRequestAllocationResultFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceRequestAllocationResultFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceRequestAllocationResultFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceRequestAllocationResultFluent that = (DeviceRequestAllocationResultFluent)o;
            if (!Objects.equals(this.adminAccess, that.adminAccess)) {
               return false;
            } else if (!Objects.equals(this.device, that.device)) {
               return false;
            } else if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.pool, that.pool)) {
               return false;
            } else if (!Objects.equals(this.request, that.request)) {
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
      return Objects.hash(new Object[]{this.adminAccess, this.device, this.driver, this.pool, this.request, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.adminAccess != null) {
         sb.append("adminAccess:");
         sb.append(this.adminAccess + ",");
      }

      if (this.device != null) {
         sb.append("device:");
         sb.append(this.device + ",");
      }

      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.pool != null) {
         sb.append("pool:");
         sb.append(this.pool + ",");
      }

      if (this.request != null) {
         sb.append("request:");
         sb.append(this.request + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public DeviceRequestAllocationResultFluent withAdminAccess() {
      return this.withAdminAccess(true);
   }
}
