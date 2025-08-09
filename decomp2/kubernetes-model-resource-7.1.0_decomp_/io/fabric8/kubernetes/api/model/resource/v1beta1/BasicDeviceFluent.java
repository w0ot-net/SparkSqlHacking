package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BasicDeviceFluent extends BaseFluent {
   private Map attributes;
   private Map capacity;
   private Map additionalProperties;

   public BasicDeviceFluent() {
   }

   public BasicDeviceFluent(BasicDevice instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(BasicDevice instance) {
      instance = instance != null ? instance : new BasicDevice();
      if (instance != null) {
         this.withAttributes(instance.getAttributes());
         this.withCapacity(instance.getCapacity());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public BasicDeviceFluent addToAttributes(String key, DeviceAttribute value) {
      if (this.attributes == null && key != null && value != null) {
         this.attributes = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.attributes.put(key, value);
      }

      return this;
   }

   public BasicDeviceFluent addToAttributes(Map map) {
      if (this.attributes == null && map != null) {
         this.attributes = new LinkedHashMap();
      }

      if (map != null) {
         this.attributes.putAll(map);
      }

      return this;
   }

   public BasicDeviceFluent removeFromAttributes(String key) {
      if (this.attributes == null) {
         return this;
      } else {
         if (key != null && this.attributes != null) {
            this.attributes.remove(key);
         }

         return this;
      }
   }

   public BasicDeviceFluent removeFromAttributes(Map map) {
      if (this.attributes == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.attributes != null) {
                  this.attributes.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public BasicDeviceFluent withAttributes(Map attributes) {
      if (attributes == null) {
         this.attributes = null;
      } else {
         this.attributes = new LinkedHashMap(attributes);
      }

      return this;
   }

   public boolean hasAttributes() {
      return this.attributes != null;
   }

   public BasicDeviceFluent addToCapacity(String key, DeviceCapacity value) {
      if (this.capacity == null && key != null && value != null) {
         this.capacity = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.capacity.put(key, value);
      }

      return this;
   }

   public BasicDeviceFluent addToCapacity(Map map) {
      if (this.capacity == null && map != null) {
         this.capacity = new LinkedHashMap();
      }

      if (map != null) {
         this.capacity.putAll(map);
      }

      return this;
   }

   public BasicDeviceFluent removeFromCapacity(String key) {
      if (this.capacity == null) {
         return this;
      } else {
         if (key != null && this.capacity != null) {
            this.capacity.remove(key);
         }

         return this;
      }
   }

   public BasicDeviceFluent removeFromCapacity(Map map) {
      if (this.capacity == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.capacity != null) {
                  this.capacity.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getCapacity() {
      return this.capacity;
   }

   public BasicDeviceFluent withCapacity(Map capacity) {
      if (capacity == null) {
         this.capacity = null;
      } else {
         this.capacity = new LinkedHashMap(capacity);
      }

      return this;
   }

   public boolean hasCapacity() {
      return this.capacity != null;
   }

   public BasicDeviceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public BasicDeviceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public BasicDeviceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public BasicDeviceFluent removeFromAdditionalProperties(Map map) {
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

   public BasicDeviceFluent withAdditionalProperties(Map additionalProperties) {
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
            BasicDeviceFluent that = (BasicDeviceFluent)o;
            if (!Objects.equals(this.attributes, that.attributes)) {
               return false;
            } else if (!Objects.equals(this.capacity, that.capacity)) {
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
      return Objects.hash(new Object[]{this.attributes, this.capacity, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attributes != null && !this.attributes.isEmpty()) {
         sb.append("attributes:");
         sb.append(this.attributes + ",");
      }

      if (this.capacity != null && !this.capacity.isEmpty()) {
         sb.append("capacity:");
         sb.append(this.capacity + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
