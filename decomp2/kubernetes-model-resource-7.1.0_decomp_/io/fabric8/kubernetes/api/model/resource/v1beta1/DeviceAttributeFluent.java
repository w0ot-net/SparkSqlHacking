package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeviceAttributeFluent extends BaseFluent {
   private Boolean bool;
   private Long _int;
   private String string;
   private String version;
   private Map additionalProperties;

   public DeviceAttributeFluent() {
   }

   public DeviceAttributeFluent(DeviceAttribute instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceAttribute instance) {
      instance = instance != null ? instance : new DeviceAttribute();
      if (instance != null) {
         this.withBool(instance.getBool());
         this.withInt(instance.getInt());
         this.withString(instance.getString());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getBool() {
      return this.bool;
   }

   public DeviceAttributeFluent withBool(Boolean bool) {
      this.bool = bool;
      return this;
   }

   public boolean hasBool() {
      return this.bool != null;
   }

   public Long getInt() {
      return this._int;
   }

   public DeviceAttributeFluent withInt(Long _int) {
      this._int = _int;
      return this;
   }

   public boolean hasInt() {
      return this._int != null;
   }

   public String getString() {
      return this.string;
   }

   public DeviceAttributeFluent withString(String string) {
      this.string = string;
      return this;
   }

   public boolean hasString() {
      return this.string != null;
   }

   public String getVersion() {
      return this.version;
   }

   public DeviceAttributeFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public DeviceAttributeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceAttributeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceAttributeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceAttributeFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceAttributeFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceAttributeFluent that = (DeviceAttributeFluent)o;
            if (!Objects.equals(this.bool, that.bool)) {
               return false;
            } else if (!Objects.equals(this._int, that._int)) {
               return false;
            } else if (!Objects.equals(this.string, that.string)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.bool, this._int, this.string, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.bool != null) {
         sb.append("bool:");
         sb.append(this.bool + ",");
      }

      if (this._int != null) {
         sb.append("_int:");
         sb.append(this._int + ",");
      }

      if (this.string != null) {
         sb.append("string:");
         sb.append(this.string + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public DeviceAttributeFluent withBool() {
      return this.withBool(true);
   }
}
