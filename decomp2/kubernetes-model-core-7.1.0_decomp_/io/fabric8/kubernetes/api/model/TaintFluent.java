package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TaintFluent extends BaseFluent {
   private String effect;
   private String key;
   private String timeAdded;
   private String value;
   private Map additionalProperties;

   public TaintFluent() {
   }

   public TaintFluent(Taint instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Taint instance) {
      instance = instance != null ? instance : new Taint();
      if (instance != null) {
         this.withEffect(instance.getEffect());
         this.withKey(instance.getKey());
         this.withTimeAdded(instance.getTimeAdded());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getEffect() {
      return this.effect;
   }

   public TaintFluent withEffect(String effect) {
      this.effect = effect;
      return this;
   }

   public boolean hasEffect() {
      return this.effect != null;
   }

   public String getKey() {
      return this.key;
   }

   public TaintFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public String getTimeAdded() {
      return this.timeAdded;
   }

   public TaintFluent withTimeAdded(String timeAdded) {
      this.timeAdded = timeAdded;
      return this;
   }

   public boolean hasTimeAdded() {
      return this.timeAdded != null;
   }

   public String getValue() {
      return this.value;
   }

   public TaintFluent withValue(String value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public TaintFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TaintFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TaintFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TaintFluent removeFromAdditionalProperties(Map map) {
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

   public TaintFluent withAdditionalProperties(Map additionalProperties) {
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
            TaintFluent that = (TaintFluent)o;
            if (!Objects.equals(this.effect, that.effect)) {
               return false;
            } else if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.timeAdded, that.timeAdded)) {
               return false;
            } else if (!Objects.equals(this.value, that.value)) {
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
      return Objects.hash(new Object[]{this.effect, this.key, this.timeAdded, this.value, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.effect != null) {
         sb.append("effect:");
         sb.append(this.effect + ",");
      }

      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.timeAdded != null) {
         sb.append("timeAdded:");
         sb.append(this.timeAdded + ",");
      }

      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
