package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TolerationFluent extends BaseFluent {
   private String effect;
   private String key;
   private String operator;
   private Long tolerationSeconds;
   private String value;
   private Map additionalProperties;

   public TolerationFluent() {
   }

   public TolerationFluent(Toleration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Toleration instance) {
      instance = instance != null ? instance : new Toleration();
      if (instance != null) {
         this.withEffect(instance.getEffect());
         this.withKey(instance.getKey());
         this.withOperator(instance.getOperator());
         this.withTolerationSeconds(instance.getTolerationSeconds());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getEffect() {
      return this.effect;
   }

   public TolerationFluent withEffect(String effect) {
      this.effect = effect;
      return this;
   }

   public boolean hasEffect() {
      return this.effect != null;
   }

   public String getKey() {
      return this.key;
   }

   public TolerationFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public String getOperator() {
      return this.operator;
   }

   public TolerationFluent withOperator(String operator) {
      this.operator = operator;
      return this;
   }

   public boolean hasOperator() {
      return this.operator != null;
   }

   public Long getTolerationSeconds() {
      return this.tolerationSeconds;
   }

   public TolerationFluent withTolerationSeconds(Long tolerationSeconds) {
      this.tolerationSeconds = tolerationSeconds;
      return this;
   }

   public boolean hasTolerationSeconds() {
      return this.tolerationSeconds != null;
   }

   public String getValue() {
      return this.value;
   }

   public TolerationFluent withValue(String value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public TolerationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TolerationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TolerationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TolerationFluent removeFromAdditionalProperties(Map map) {
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

   public TolerationFluent withAdditionalProperties(Map additionalProperties) {
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
            TolerationFluent that = (TolerationFluent)o;
            if (!Objects.equals(this.effect, that.effect)) {
               return false;
            } else if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.operator, that.operator)) {
               return false;
            } else if (!Objects.equals(this.tolerationSeconds, that.tolerationSeconds)) {
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
      return Objects.hash(new Object[]{this.effect, this.key, this.operator, this.tolerationSeconds, this.value, this.additionalProperties, super.hashCode()});
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

      if (this.operator != null) {
         sb.append("operator:");
         sb.append(this.operator + ",");
      }

      if (this.tolerationSeconds != null) {
         sb.append("tolerationSeconds:");
         sb.append(this.tolerationSeconds + ",");
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
