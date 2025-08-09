package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VendorParametersFluent extends BaseFluent {
   private String driverName;
   private Object parameters;
   private Map additionalProperties;

   public VendorParametersFluent() {
   }

   public VendorParametersFluent(VendorParameters instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VendorParameters instance) {
      instance = instance != null ? instance : new VendorParameters();
      if (instance != null) {
         this.withDriverName(instance.getDriverName());
         this.withParameters(instance.getParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriverName() {
      return this.driverName;
   }

   public VendorParametersFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public Object getParameters() {
      return this.parameters;
   }

   public VendorParametersFluent withParameters(Object parameters) {
      this.parameters = parameters;
      return this;
   }

   public boolean hasParameters() {
      return this.parameters != null;
   }

   public VendorParametersFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VendorParametersFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VendorParametersFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VendorParametersFluent removeFromAdditionalProperties(Map map) {
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

   public VendorParametersFluent withAdditionalProperties(Map additionalProperties) {
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
            VendorParametersFluent that = (VendorParametersFluent)o;
            if (!Objects.equals(this.driverName, that.driverName)) {
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
      return Objects.hash(new Object[]{this.driverName, this.parameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.driverName != null) {
         sb.append("driverName:");
         sb.append(this.driverName + ",");
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
