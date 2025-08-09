package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PriorityLevelConfigurationSpecFluent extends BaseFluent {
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
         this.withLimited(instance.getLimited());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

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
            if (!Objects.equals(this.limited, that.limited)) {
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
      return Objects.hash(new Object[]{this.limited, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
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
