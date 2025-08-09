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

public class RollingUpdateDeploymentFluent extends BaseFluent {
   private IntOrStringBuilder maxSurge;
   private IntOrStringBuilder maxUnavailable;
   private Map additionalProperties;

   public RollingUpdateDeploymentFluent() {
   }

   public RollingUpdateDeploymentFluent(RollingUpdateDeployment instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RollingUpdateDeployment instance) {
      instance = instance != null ? instance : new RollingUpdateDeployment();
      if (instance != null) {
         this.withMaxSurge(instance.getMaxSurge());
         this.withMaxUnavailable(instance.getMaxUnavailable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IntOrString buildMaxSurge() {
      return this.maxSurge != null ? this.maxSurge.build() : null;
   }

   public RollingUpdateDeploymentFluent withMaxSurge(IntOrString maxSurge) {
      this._visitables.remove("maxSurge");
      if (maxSurge != null) {
         this.maxSurge = new IntOrStringBuilder(maxSurge);
         this._visitables.get("maxSurge").add(this.maxSurge);
      } else {
         this.maxSurge = null;
         this._visitables.get("maxSurge").remove(this.maxSurge);
      }

      return this;
   }

   public boolean hasMaxSurge() {
      return this.maxSurge != null;
   }

   public RollingUpdateDeploymentFluent withNewMaxSurge(Object value) {
      return this.withMaxSurge(new IntOrString(value));
   }

   public MaxSurgeNested withNewMaxSurge() {
      return new MaxSurgeNested((IntOrString)null);
   }

   public MaxSurgeNested withNewMaxSurgeLike(IntOrString item) {
      return new MaxSurgeNested(item);
   }

   public MaxSurgeNested editMaxSurge() {
      return this.withNewMaxSurgeLike((IntOrString)Optional.ofNullable(this.buildMaxSurge()).orElse((Object)null));
   }

   public MaxSurgeNested editOrNewMaxSurge() {
      return this.withNewMaxSurgeLike((IntOrString)Optional.ofNullable(this.buildMaxSurge()).orElse((new IntOrStringBuilder()).build()));
   }

   public MaxSurgeNested editOrNewMaxSurgeLike(IntOrString item) {
      return this.withNewMaxSurgeLike((IntOrString)Optional.ofNullable(this.buildMaxSurge()).orElse(item));
   }

   public IntOrString buildMaxUnavailable() {
      return this.maxUnavailable != null ? this.maxUnavailable.build() : null;
   }

   public RollingUpdateDeploymentFluent withMaxUnavailable(IntOrString maxUnavailable) {
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

   public RollingUpdateDeploymentFluent withNewMaxUnavailable(Object value) {
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

   public RollingUpdateDeploymentFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RollingUpdateDeploymentFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RollingUpdateDeploymentFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RollingUpdateDeploymentFluent removeFromAdditionalProperties(Map map) {
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

   public RollingUpdateDeploymentFluent withAdditionalProperties(Map additionalProperties) {
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
            RollingUpdateDeploymentFluent that = (RollingUpdateDeploymentFluent)o;
            if (!Objects.equals(this.maxSurge, that.maxSurge)) {
               return false;
            } else if (!Objects.equals(this.maxUnavailable, that.maxUnavailable)) {
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
      return Objects.hash(new Object[]{this.maxSurge, this.maxUnavailable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.maxSurge != null) {
         sb.append("maxSurge:");
         sb.append(this.maxSurge + ",");
      }

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

   public class MaxSurgeNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      MaxSurgeNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return RollingUpdateDeploymentFluent.this.withMaxSurge(this.builder.build());
      }

      public Object endMaxSurge() {
         return this.and();
      }
   }

   public class MaxUnavailableNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      MaxUnavailableNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return RollingUpdateDeploymentFluent.this.withMaxUnavailable(this.builder.build());
      }

      public Object endMaxUnavailable() {
         return this.and();
      }
   }
}
