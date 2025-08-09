package io.fabric8.kubernetes.api.model.resource.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeviceFluent extends BaseFluent {
   private BasicDeviceBuilder basic;
   private String name;
   private Map additionalProperties;

   public DeviceFluent() {
   }

   public DeviceFluent(Device instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Device instance) {
      instance = instance != null ? instance : new Device();
      if (instance != null) {
         this.withBasic(instance.getBasic());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public BasicDevice buildBasic() {
      return this.basic != null ? this.basic.build() : null;
   }

   public DeviceFluent withBasic(BasicDevice basic) {
      this._visitables.remove("basic");
      if (basic != null) {
         this.basic = new BasicDeviceBuilder(basic);
         this._visitables.get("basic").add(this.basic);
      } else {
         this.basic = null;
         this._visitables.get("basic").remove(this.basic);
      }

      return this;
   }

   public boolean hasBasic() {
      return this.basic != null;
   }

   public BasicNested withNewBasic() {
      return new BasicNested((BasicDevice)null);
   }

   public BasicNested withNewBasicLike(BasicDevice item) {
      return new BasicNested(item);
   }

   public BasicNested editBasic() {
      return this.withNewBasicLike((BasicDevice)Optional.ofNullable(this.buildBasic()).orElse((Object)null));
   }

   public BasicNested editOrNewBasic() {
      return this.withNewBasicLike((BasicDevice)Optional.ofNullable(this.buildBasic()).orElse((new BasicDeviceBuilder()).build()));
   }

   public BasicNested editOrNewBasicLike(BasicDevice item) {
      return this.withNewBasicLike((BasicDevice)Optional.ofNullable(this.buildBasic()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public DeviceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public DeviceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceFluent that = (DeviceFluent)o;
            if (!Objects.equals(this.basic, that.basic)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.basic, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.basic != null) {
         sb.append("basic:");
         sb.append(this.basic + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BasicNested extends BasicDeviceFluent implements Nested {
      BasicDeviceBuilder builder;

      BasicNested(BasicDevice item) {
         this.builder = new BasicDeviceBuilder(this, item);
      }

      public Object and() {
         return DeviceFluent.this.withBasic(this.builder.build());
      }

      public Object endBasic() {
         return this.and();
      }
   }
}
