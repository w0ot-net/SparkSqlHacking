package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeviceClassConfigurationFluent extends BaseFluent {
   private OpaqueDeviceConfigurationBuilder opaque;
   private Map additionalProperties;

   public DeviceClassConfigurationFluent() {
   }

   public DeviceClassConfigurationFluent(DeviceClassConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceClassConfiguration instance) {
      instance = instance != null ? instance : new DeviceClassConfiguration();
      if (instance != null) {
         this.withOpaque(instance.getOpaque());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public OpaqueDeviceConfiguration buildOpaque() {
      return this.opaque != null ? this.opaque.build() : null;
   }

   public DeviceClassConfigurationFluent withOpaque(OpaqueDeviceConfiguration opaque) {
      this._visitables.remove("opaque");
      if (opaque != null) {
         this.opaque = new OpaqueDeviceConfigurationBuilder(opaque);
         this._visitables.get("opaque").add(this.opaque);
      } else {
         this.opaque = null;
         this._visitables.get("opaque").remove(this.opaque);
      }

      return this;
   }

   public boolean hasOpaque() {
      return this.opaque != null;
   }

   public DeviceClassConfigurationFluent withNewOpaque(String driver, Object parameters) {
      return this.withOpaque(new OpaqueDeviceConfiguration(driver, parameters));
   }

   public OpaqueNested withNewOpaque() {
      return new OpaqueNested((OpaqueDeviceConfiguration)null);
   }

   public OpaqueNested withNewOpaqueLike(OpaqueDeviceConfiguration item) {
      return new OpaqueNested(item);
   }

   public OpaqueNested editOpaque() {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse((Object)null));
   }

   public OpaqueNested editOrNewOpaque() {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse((new OpaqueDeviceConfigurationBuilder()).build()));
   }

   public OpaqueNested editOrNewOpaqueLike(OpaqueDeviceConfiguration item) {
      return this.withNewOpaqueLike((OpaqueDeviceConfiguration)Optional.ofNullable(this.buildOpaque()).orElse(item));
   }

   public DeviceClassConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceClassConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceClassConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceClassConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceClassConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceClassConfigurationFluent that = (DeviceClassConfigurationFluent)o;
            if (!Objects.equals(this.opaque, that.opaque)) {
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
      return Objects.hash(new Object[]{this.opaque, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.opaque != null) {
         sb.append("opaque:");
         sb.append(this.opaque + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OpaqueNested extends OpaqueDeviceConfigurationFluent implements Nested {
      OpaqueDeviceConfigurationBuilder builder;

      OpaqueNested(OpaqueDeviceConfiguration item) {
         this.builder = new OpaqueDeviceConfigurationBuilder(this, item);
      }

      public Object and() {
         return DeviceClassConfigurationFluent.this.withOpaque(this.builder.build());
      }

      public Object endOpaque() {
         return this.and();
      }
   }
}
