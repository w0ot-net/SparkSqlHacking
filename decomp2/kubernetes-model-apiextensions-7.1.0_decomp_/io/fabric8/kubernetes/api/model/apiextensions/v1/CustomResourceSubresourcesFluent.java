package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomResourceSubresourcesFluent extends BaseFluent {
   private CustomResourceSubresourceScaleBuilder scale;
   private CustomResourceSubresourceStatusBuilder status;
   private Map additionalProperties;

   public CustomResourceSubresourcesFluent() {
   }

   public CustomResourceSubresourcesFluent(CustomResourceSubresources instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceSubresources instance) {
      instance = instance != null ? instance : new CustomResourceSubresources();
      if (instance != null) {
         this.withScale(instance.getScale());
         this.withStatus(instance.getStatus());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceSubresourceScale buildScale() {
      return this.scale != null ? this.scale.build() : null;
   }

   public CustomResourceSubresourcesFluent withScale(CustomResourceSubresourceScale scale) {
      this._visitables.remove("scale");
      if (scale != null) {
         this.scale = new CustomResourceSubresourceScaleBuilder(scale);
         this._visitables.get("scale").add(this.scale);
      } else {
         this.scale = null;
         this._visitables.get("scale").remove(this.scale);
      }

      return this;
   }

   public boolean hasScale() {
      return this.scale != null;
   }

   public CustomResourceSubresourcesFluent withNewScale(String labelSelectorPath, String specReplicasPath, String statusReplicasPath) {
      return this.withScale(new CustomResourceSubresourceScale(labelSelectorPath, specReplicasPath, statusReplicasPath));
   }

   public ScaleNested withNewScale() {
      return new ScaleNested((CustomResourceSubresourceScale)null);
   }

   public ScaleNested withNewScaleLike(CustomResourceSubresourceScale item) {
      return new ScaleNested(item);
   }

   public ScaleNested editScale() {
      return this.withNewScaleLike((CustomResourceSubresourceScale)Optional.ofNullable(this.buildScale()).orElse((Object)null));
   }

   public ScaleNested editOrNewScale() {
      return this.withNewScaleLike((CustomResourceSubresourceScale)Optional.ofNullable(this.buildScale()).orElse((new CustomResourceSubresourceScaleBuilder()).build()));
   }

   public ScaleNested editOrNewScaleLike(CustomResourceSubresourceScale item) {
      return this.withNewScaleLike((CustomResourceSubresourceScale)Optional.ofNullable(this.buildScale()).orElse(item));
   }

   public CustomResourceSubresourceStatus buildStatus() {
      return this.status != null ? this.status.build() : null;
   }

   public CustomResourceSubresourcesFluent withStatus(CustomResourceSubresourceStatus status) {
      this._visitables.remove("status");
      if (status != null) {
         this.status = new CustomResourceSubresourceStatusBuilder(status);
         this._visitables.get("status").add(this.status);
      } else {
         this.status = null;
         this._visitables.get("status").remove(this.status);
      }

      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public StatusNested withNewStatus() {
      return new StatusNested((CustomResourceSubresourceStatus)null);
   }

   public StatusNested withNewStatusLike(CustomResourceSubresourceStatus item) {
      return new StatusNested(item);
   }

   public StatusNested editStatus() {
      return this.withNewStatusLike((CustomResourceSubresourceStatus)Optional.ofNullable(this.buildStatus()).orElse((Object)null));
   }

   public StatusNested editOrNewStatus() {
      return this.withNewStatusLike((CustomResourceSubresourceStatus)Optional.ofNullable(this.buildStatus()).orElse((new CustomResourceSubresourceStatusBuilder()).build()));
   }

   public StatusNested editOrNewStatusLike(CustomResourceSubresourceStatus item) {
      return this.withNewStatusLike((CustomResourceSubresourceStatus)Optional.ofNullable(this.buildStatus()).orElse(item));
   }

   public CustomResourceSubresourcesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceSubresourcesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceSubresourcesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceSubresourcesFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceSubresourcesFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceSubresourcesFluent that = (CustomResourceSubresourcesFluent)o;
            if (!Objects.equals(this.scale, that.scale)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
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
      return Objects.hash(new Object[]{this.scale, this.status, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.scale != null) {
         sb.append("scale:");
         sb.append(this.scale + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ScaleNested extends CustomResourceSubresourceScaleFluent implements Nested {
      CustomResourceSubresourceScaleBuilder builder;

      ScaleNested(CustomResourceSubresourceScale item) {
         this.builder = new CustomResourceSubresourceScaleBuilder(this, item);
      }

      public Object and() {
         return CustomResourceSubresourcesFluent.this.withScale(this.builder.build());
      }

      public Object endScale() {
         return this.and();
      }
   }

   public class StatusNested extends CustomResourceSubresourceStatusFluent implements Nested {
      CustomResourceSubresourceStatusBuilder builder;

      StatusNested(CustomResourceSubresourceStatus item) {
         this.builder = new CustomResourceSubresourceStatusBuilder(this, item);
      }

      public Object and() {
         return CustomResourceSubresourcesFluent.this.withStatus(this.builder.build());
      }

      public Object endStatus() {
         return this.and();
      }
   }
}
