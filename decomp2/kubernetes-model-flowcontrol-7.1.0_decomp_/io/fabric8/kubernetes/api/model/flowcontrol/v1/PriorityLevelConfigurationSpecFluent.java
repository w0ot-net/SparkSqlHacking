package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PriorityLevelConfigurationSpecFluent extends BaseFluent {
   private ExemptPriorityLevelConfigurationBuilder exempt;
   private LimitedPriorityLevelConfigurationBuilder limited;
   private String type;
   private Map additionalProperties;

   public PriorityLevelConfigurationSpecFluent() {
   }

   public PriorityLevelConfigurationSpecFluent(PriorityLevelConfigurationSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PriorityLevelConfigurationSpec instance) {
      instance = instance != null ? instance : new PriorityLevelConfigurationSpec();
      if (instance != null) {
         this.withExempt(instance.getExempt());
         this.withLimited(instance.getLimited());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ExemptPriorityLevelConfiguration buildExempt() {
      return this.exempt != null ? this.exempt.build() : null;
   }

   public PriorityLevelConfigurationSpecFluent withExempt(ExemptPriorityLevelConfiguration exempt) {
      this._visitables.remove("exempt");
      if (exempt != null) {
         this.exempt = new ExemptPriorityLevelConfigurationBuilder(exempt);
         this._visitables.get("exempt").add(this.exempt);
      } else {
         this.exempt = null;
         this._visitables.get("exempt").remove(this.exempt);
      }

      return this;
   }

   public boolean hasExempt() {
      return this.exempt != null;
   }

   public PriorityLevelConfigurationSpecFluent withNewExempt(Integer lendablePercent, Integer nominalConcurrencyShares) {
      return this.withExempt(new ExemptPriorityLevelConfiguration(lendablePercent, nominalConcurrencyShares));
   }

   public ExemptNested withNewExempt() {
      return new ExemptNested((ExemptPriorityLevelConfiguration)null);
   }

   public ExemptNested withNewExemptLike(ExemptPriorityLevelConfiguration item) {
      return new ExemptNested(item);
   }

   public ExemptNested editExempt() {
      return this.withNewExemptLike((ExemptPriorityLevelConfiguration)Optional.ofNullable(this.buildExempt()).orElse((Object)null));
   }

   public ExemptNested editOrNewExempt() {
      return this.withNewExemptLike((ExemptPriorityLevelConfiguration)Optional.ofNullable(this.buildExempt()).orElse((new ExemptPriorityLevelConfigurationBuilder()).build()));
   }

   public ExemptNested editOrNewExemptLike(ExemptPriorityLevelConfiguration item) {
      return this.withNewExemptLike((ExemptPriorityLevelConfiguration)Optional.ofNullable(this.buildExempt()).orElse(item));
   }

   public LimitedPriorityLevelConfiguration buildLimited() {
      return this.limited != null ? this.limited.build() : null;
   }

   public PriorityLevelConfigurationSpecFluent withLimited(LimitedPriorityLevelConfiguration limited) {
      this._visitables.remove("limited");
      if (limited != null) {
         this.limited = new LimitedPriorityLevelConfigurationBuilder(limited);
         this._visitables.get("limited").add(this.limited);
      } else {
         this.limited = null;
         this._visitables.get("limited").remove(this.limited);
      }

      return this;
   }

   public boolean hasLimited() {
      return this.limited != null;
   }

   public LimitedNested withNewLimited() {
      return new LimitedNested((LimitedPriorityLevelConfiguration)null);
   }

   public LimitedNested withNewLimitedLike(LimitedPriorityLevelConfiguration item) {
      return new LimitedNested(item);
   }

   public LimitedNested editLimited() {
      return this.withNewLimitedLike((LimitedPriorityLevelConfiguration)Optional.ofNullable(this.buildLimited()).orElse((Object)null));
   }

   public LimitedNested editOrNewLimited() {
      return this.withNewLimitedLike((LimitedPriorityLevelConfiguration)Optional.ofNullable(this.buildLimited()).orElse((new LimitedPriorityLevelConfigurationBuilder()).build()));
   }

   public LimitedNested editOrNewLimitedLike(LimitedPriorityLevelConfiguration item) {
      return this.withNewLimitedLike((LimitedPriorityLevelConfiguration)Optional.ofNullable(this.buildLimited()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public PriorityLevelConfigurationSpecFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public PriorityLevelConfigurationSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PriorityLevelConfigurationSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PriorityLevelConfigurationSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PriorityLevelConfigurationSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PriorityLevelConfigurationSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PriorityLevelConfigurationSpecFluent that = (PriorityLevelConfigurationSpecFluent)o;
            if (!Objects.equals(this.exempt, that.exempt)) {
               return false;
            } else if (!Objects.equals(this.limited, that.limited)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.exempt, this.limited, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.exempt != null) {
         sb.append("exempt:");
         sb.append(this.exempt + ",");
      }

      if (this.limited != null) {
         sb.append("limited:");
         sb.append(this.limited + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ExemptNested extends ExemptPriorityLevelConfigurationFluent implements Nested {
      ExemptPriorityLevelConfigurationBuilder builder;

      ExemptNested(ExemptPriorityLevelConfiguration item) {
         this.builder = new ExemptPriorityLevelConfigurationBuilder(this, item);
      }

      public Object and() {
         return PriorityLevelConfigurationSpecFluent.this.withExempt(this.builder.build());
      }

      public Object endExempt() {
         return this.and();
      }
   }

   public class LimitedNested extends LimitedPriorityLevelConfigurationFluent implements Nested {
      LimitedPriorityLevelConfigurationBuilder builder;

      LimitedNested(LimitedPriorityLevelConfiguration item) {
         this.builder = new LimitedPriorityLevelConfigurationBuilder(this, item);
      }

      public Object and() {
         return PriorityLevelConfigurationSpecFluent.this.withLimited(this.builder.build());
      }

      public Object endLimited() {
         return this.and();
      }
   }
}
