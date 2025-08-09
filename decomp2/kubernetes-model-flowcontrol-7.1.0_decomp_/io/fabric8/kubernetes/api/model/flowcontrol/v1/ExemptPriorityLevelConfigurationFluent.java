package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ExemptPriorityLevelConfigurationFluent extends BaseFluent {
   private Integer lendablePercent;
   private Integer nominalConcurrencyShares;
   private Map additionalProperties;

   public ExemptPriorityLevelConfigurationFluent() {
   }

   public ExemptPriorityLevelConfigurationFluent(ExemptPriorityLevelConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExemptPriorityLevelConfiguration instance) {
      instance = instance != null ? instance : new ExemptPriorityLevelConfiguration();
      if (instance != null) {
         this.withLendablePercent(instance.getLendablePercent());
         this.withNominalConcurrencyShares(instance.getNominalConcurrencyShares());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getLendablePercent() {
      return this.lendablePercent;
   }

   public ExemptPriorityLevelConfigurationFluent withLendablePercent(Integer lendablePercent) {
      this.lendablePercent = lendablePercent;
      return this;
   }

   public boolean hasLendablePercent() {
      return this.lendablePercent != null;
   }

   public Integer getNominalConcurrencyShares() {
      return this.nominalConcurrencyShares;
   }

   public ExemptPriorityLevelConfigurationFluent withNominalConcurrencyShares(Integer nominalConcurrencyShares) {
      this.nominalConcurrencyShares = nominalConcurrencyShares;
      return this;
   }

   public boolean hasNominalConcurrencyShares() {
      return this.nominalConcurrencyShares != null;
   }

   public ExemptPriorityLevelConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExemptPriorityLevelConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExemptPriorityLevelConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExemptPriorityLevelConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public ExemptPriorityLevelConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            ExemptPriorityLevelConfigurationFluent that = (ExemptPriorityLevelConfigurationFluent)o;
            if (!Objects.equals(this.lendablePercent, that.lendablePercent)) {
               return false;
            } else if (!Objects.equals(this.nominalConcurrencyShares, that.nominalConcurrencyShares)) {
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
      return Objects.hash(new Object[]{this.lendablePercent, this.nominalConcurrencyShares, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.lendablePercent != null) {
         sb.append("lendablePercent:");
         sb.append(this.lendablePercent + ",");
      }

      if (this.nominalConcurrencyShares != null) {
         sb.append("nominalConcurrencyShares:");
         sb.append(this.nominalConcurrencyShares + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
