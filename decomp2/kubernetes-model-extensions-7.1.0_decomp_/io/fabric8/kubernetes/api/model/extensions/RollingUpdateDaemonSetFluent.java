package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.IntOrStringBuilder;
import io.fabric8.kubernetes.api.model.IntOrStringFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RollingUpdateDaemonSetFluent extends BaseFluent {
   private IntOrStringBuilder maxUnavailable;
   private Map additionalProperties;

   public RollingUpdateDaemonSetFluent() {
   }

   public RollingUpdateDaemonSetFluent(RollingUpdateDaemonSet instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RollingUpdateDaemonSet instance) {
      instance = instance != null ? instance : new RollingUpdateDaemonSet();
      if (instance != null) {
         this.withMaxUnavailable(instance.getMaxUnavailable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IntOrString buildMaxUnavailable() {
      return this.maxUnavailable != null ? this.maxUnavailable.build() : null;
   }

   public RollingUpdateDaemonSetFluent withMaxUnavailable(IntOrString maxUnavailable) {
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

   public RollingUpdateDaemonSetFluent withNewMaxUnavailable(Object value) {
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

   public RollingUpdateDaemonSetFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RollingUpdateDaemonSetFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RollingUpdateDaemonSetFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RollingUpdateDaemonSetFluent removeFromAdditionalProperties(Map map) {
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

   public RollingUpdateDaemonSetFluent withAdditionalProperties(Map additionalProperties) {
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
            RollingUpdateDaemonSetFluent that = (RollingUpdateDaemonSetFluent)o;
            if (!Objects.equals(this.maxUnavailable, that.maxUnavailable)) {
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
      return Objects.hash(new Object[]{this.maxUnavailable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.maxUnavailable != null) {
         sb.append("maxUnavailable:");
         sb.append(this.maxUnavailable + ",");
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
         return RollingUpdateDaemonSetFluent.this.withMaxUnavailable(this.builder.build());
      }

      public Object endMaxUnavailable() {
         return this.and();
      }
   }
}
