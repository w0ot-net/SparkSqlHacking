package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class OpaqueDeviceConfigurationFluent extends BaseFluent {
   private String driver;
   private Object parameters;
   private Map additionalProperties;

   public OpaqueDeviceConfigurationFluent() {
   }

   public OpaqueDeviceConfigurationFluent(OpaqueDeviceConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(OpaqueDeviceConfiguration instance) {
      instance = instance != null ? instance : new OpaqueDeviceConfiguration();
      if (instance != null) {
         this.withDriver(instance.getDriver());
         this.withParameters(instance.getParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriver() {
      return this.driver;
   }

   public OpaqueDeviceConfigurationFluent withDriver(String driver) {
      this.driver = driver;
      return this;
   }

   public boolean hasDriver() {
      return this.driver != null;
   }

   public Object getParameters() {
      return this.parameters;
   }

   public OpaqueDeviceConfigurationFluent withParameters(Object parameters) {
      this.parameters = parameters;
      return this;
   }

   public boolean hasParameters() {
      return this.parameters != null;
   }

   public OpaqueDeviceConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public OpaqueDeviceConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public OpaqueDeviceConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public OpaqueDeviceConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public OpaqueDeviceConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            OpaqueDeviceConfigurationFluent that = (OpaqueDeviceConfigurationFluent)o;
            if (!Objects.equals(this.driver, that.driver)) {
               return false;
            } else if (!Objects.equals(this.parameters, that.parameters)) {
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
      return Objects.hash(new Object[]{this.driver, this.parameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.driver != null) {
         sb.append("driver:");
         sb.append(this.driver + ",");
      }

      if (this.parameters != null) {
         sb.append("parameters:");
         sb.append(this.parameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
