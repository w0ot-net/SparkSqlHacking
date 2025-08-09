package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VolumeErrorFluent extends BaseFluent {
   private String message;
   private String time;
   private Map additionalProperties;

   public VolumeErrorFluent() {
   }

   public VolumeErrorFluent(VolumeError instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeError instance) {
      instance = instance != null ? instance : new VolumeError();
      if (instance != null) {
         this.withMessage(instance.getMessage());
         this.withTime(instance.getTime());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMessage() {
      return this.message;
   }

   public VolumeErrorFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getTime() {
      return this.time;
   }

   public VolumeErrorFluent withTime(String time) {
      this.time = time;
      return this;
   }

   public boolean hasTime() {
      return this.time != null;
   }

   public VolumeErrorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeErrorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeErrorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeErrorFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeErrorFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeErrorFluent that = (VolumeErrorFluent)o;
            if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.time, that.time)) {
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
      return Objects.hash(new Object[]{this.message, this.time, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.time != null) {
         sb.append("time:");
         sb.append(this.time + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
