package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class QuantityFluent extends BaseFluent {
   private String amount;
   private String format;
   private Map additionalProperties;

   public QuantityFluent() {
   }

   public QuantityFluent(Quantity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Quantity instance) {
      instance = instance != null ? instance : new Quantity();
      if (instance != null) {
         this.withAmount(instance.getAmount());
         this.withFormat(instance.getFormat());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAmount() {
      return this.amount;
   }

   public QuantityFluent withAmount(String amount) {
      this.amount = amount;
      return this;
   }

   public boolean hasAmount() {
      return this.amount != null;
   }

   public String getFormat() {
      return this.format;
   }

   public QuantityFluent withFormat(String format) {
      this.format = format;
      return this;
   }

   public boolean hasFormat() {
      return this.format != null;
   }

   public QuantityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public QuantityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public QuantityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public QuantityFluent removeFromAdditionalProperties(Map map) {
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

   public QuantityFluent withAdditionalProperties(Map additionalProperties) {
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
            QuantityFluent that = (QuantityFluent)o;
            if (!Objects.equals(this.amount, that.amount)) {
               return false;
            } else if (!Objects.equals(this.format, that.format)) {
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
      return Objects.hash(new Object[]{this.amount, this.format, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.amount != null) {
         sb.append("amount:");
         sb.append(this.amount + ",");
      }

      if (this.format != null) {
         sb.append("format:");
         sb.append(this.format + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
