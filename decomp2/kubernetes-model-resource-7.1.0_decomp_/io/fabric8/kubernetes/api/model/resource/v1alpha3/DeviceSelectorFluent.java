package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeviceSelectorFluent extends BaseFluent {
   private CELDeviceSelectorBuilder cel;
   private Map additionalProperties;

   public DeviceSelectorFluent() {
   }

   public DeviceSelectorFluent(DeviceSelector instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceSelector instance) {
      instance = instance != null ? instance : new DeviceSelector();
      if (instance != null) {
         this.withCel(instance.getCel());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CELDeviceSelector buildCel() {
      return this.cel != null ? this.cel.build() : null;
   }

   public DeviceSelectorFluent withCel(CELDeviceSelector cel) {
      this._visitables.remove("cel");
      if (cel != null) {
         this.cel = new CELDeviceSelectorBuilder(cel);
         this._visitables.get("cel").add(this.cel);
      } else {
         this.cel = null;
         this._visitables.get("cel").remove(this.cel);
      }

      return this;
   }

   public boolean hasCel() {
      return this.cel != null;
   }

   public DeviceSelectorFluent withNewCel(String expression) {
      return this.withCel(new CELDeviceSelector(expression));
   }

   public CelNested withNewCel() {
      return new CelNested((CELDeviceSelector)null);
   }

   public CelNested withNewCelLike(CELDeviceSelector item) {
      return new CelNested(item);
   }

   public CelNested editCel() {
      return this.withNewCelLike((CELDeviceSelector)Optional.ofNullable(this.buildCel()).orElse((Object)null));
   }

   public CelNested editOrNewCel() {
      return this.withNewCelLike((CELDeviceSelector)Optional.ofNullable(this.buildCel()).orElse((new CELDeviceSelectorBuilder()).build()));
   }

   public CelNested editOrNewCelLike(CELDeviceSelector item) {
      return this.withNewCelLike((CELDeviceSelector)Optional.ofNullable(this.buildCel()).orElse(item));
   }

   public DeviceSelectorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceSelectorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceSelectorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceSelectorFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceSelectorFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceSelectorFluent that = (DeviceSelectorFluent)o;
            if (!Objects.equals(this.cel, that.cel)) {
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
      return Objects.hash(new Object[]{this.cel, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.cel != null) {
         sb.append("cel:");
         sb.append(this.cel + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CelNested extends CELDeviceSelectorFluent implements Nested {
      CELDeviceSelectorBuilder builder;

      CelNested(CELDeviceSelector item) {
         this.builder = new CELDeviceSelectorBuilder(this, item);
      }

      public Object and() {
         return DeviceSelectorFluent.this.withCel(this.builder.build());
      }

      public Object endCel() {
         return this.and();
      }
   }
}
