package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.IntOrStringBuilder;
import io.fabric8.kubernetes.api.model.IntOrStringFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RollingUpdateStatefulSetStrategyFluent extends BaseFluent {
   private IntOrStringBuilder maxUnavailable;
   private Integer partition;
   private Map additionalProperties;

   public RollingUpdateStatefulSetStrategyFluent() {
   }

   public RollingUpdateStatefulSetStrategyFluent(RollingUpdateStatefulSetStrategy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RollingUpdateStatefulSetStrategy instance) {
      instance = instance != null ? instance : new RollingUpdateStatefulSetStrategy();
      if (instance != null) {
         this.withMaxUnavailable(instance.getMaxUnavailable());
         this.withPartition(instance.getPartition());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IntOrString buildMaxUnavailable() {
      return this.maxUnavailable != null ? this.maxUnavailable.build() : null;
   }

   public RollingUpdateStatefulSetStrategyFluent withMaxUnavailable(IntOrString maxUnavailable) {
      this._visitables.remove("maxUnavailable");
      if (maxUnavailable != null) {
         this.maxUnavailable = new IntOrStringBuilder(maxUnavailable);
         this._visitables.get("maxUnavailable").add(this.maxUnavailable);
      } else {
         this.maxUnavailable = null;
         this._visitables.get("maxUnavailable").remove(this.maxUnavailable);
      }

      return this;
   }

   public boolean hasMaxUnavailable() {
      return this.maxUnavailable != null;
   }

   public RollingUpdateStatefulSetStrategyFluent withNewMaxUnavailable(Object value) {
      return this.withMaxUnavailable(new IntOrString(value));
   }

   public MaxUnavailableNested withNewMaxUnavailable() {
      return new MaxUnavailableNested((IntOrString)null);
   }

   public MaxUnavailableNested withNewMaxUnavailableLike(IntOrString item) {
      return new MaxUnavailableNested(item);
   }

   public MaxUnavailableNested editMaxUnavailable() {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse((Object)null));
   }

   public MaxUnavailableNested editOrNewMaxUnavailable() {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse((new IntOrStringBuilder()).build()));
   }

   public MaxUnavailableNested editOrNewMaxUnavailableLike(IntOrString item) {
      return this.withNewMaxUnavailableLike((IntOrString)Optional.ofNullable(this.buildMaxUnavailable()).orElse(item));
   }

   public Integer getPartition() {
      return this.partition;
   }

   public RollingUpdateStatefulSetStrategyFluent withPartition(Integer partition) {
      this.partition = partition;
      return this;
   }

   public boolean hasPartition() {
      return this.partition != null;
   }

   public RollingUpdateStatefulSetStrategyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RollingUpdateStatefulSetStrategyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RollingUpdateStatefulSetStrategyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RollingUpdateStatefulSetStrategyFluent removeFromAdditionalProperties(Map map) {
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

   public RollingUpdateStatefulSetStrategyFluent withAdditionalProperties(Map additionalProperties) {
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
            RollingUpdateStatefulSetStrategyFluent that = (RollingUpdateStatefulSetStrategyFluent)o;
            if (!Objects.equals(this.maxUnavailable, that.maxUnavailable)) {
               return false;
            } else if (!Objects.equals(this.partition, that.partition)) {
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
      return Objects.hash(new Object[]{this.maxUnavailable, this.partition, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.maxUnavailable != null) {
         sb.append("maxUnavailable:");
         sb.append(this.maxUnavailable + ",");
      }

      if (this.partition != null) {
         sb.append("partition:");
         sb.append(this.partition + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MaxUnavailableNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      MaxUnavailableNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return RollingUpdateStatefulSetStrategyFluent.this.withMaxUnavailable(this.builder.build());
      }

      public Object endMaxUnavailable() {
         return this.and();
      }
   }
}
