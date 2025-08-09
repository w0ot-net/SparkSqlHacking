package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FractionFluent extends BaseFluent {
   private Integer denominator;
   private Integer numerator;
   private Map additionalProperties;

   public FractionFluent() {
   }

   public FractionFluent(Fraction instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Fraction instance) {
      instance = instance != null ? instance : new Fraction();
      if (instance != null) {
         this.withDenominator(instance.getDenominator());
         this.withNumerator(instance.getNumerator());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getDenominator() {
      return this.denominator;
   }

   public FractionFluent withDenominator(Integer denominator) {
      this.denominator = denominator;
      return this;
   }

   public boolean hasDenominator() {
      return this.denominator != null;
   }

   public Integer getNumerator() {
      return this.numerator;
   }

   public FractionFluent withNumerator(Integer numerator) {
      this.numerator = numerator;
      return this;
   }

   public boolean hasNumerator() {
      return this.numerator != null;
   }

   public FractionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FractionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FractionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FractionFluent removeFromAdditionalProperties(Map map) {
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

   public FractionFluent withAdditionalProperties(Map additionalProperties) {
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
            FractionFluent that = (FractionFluent)o;
            if (!Objects.equals(this.denominator, that.denominator)) {
               return false;
            } else if (!Objects.equals(this.numerator, that.numerator)) {
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
      return Objects.hash(new Object[]{this.denominator, this.numerator, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.denominator != null) {
         sb.append("denominator:");
         sb.append(this.denominator + ",");
      }

      if (this.numerator != null) {
         sb.append("numerator:");
         sb.append(this.numerator + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
