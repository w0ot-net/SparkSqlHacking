package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EmptyDirVolumeSourceFluent extends BaseFluent {
   private String medium;
   private QuantityBuilder sizeLimit;
   private Map additionalProperties;

   public EmptyDirVolumeSourceFluent() {
   }

   public EmptyDirVolumeSourceFluent(EmptyDirVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EmptyDirVolumeSource instance) {
      instance = instance != null ? instance : new EmptyDirVolumeSource();
      if (instance != null) {
         this.withMedium(instance.getMedium());
         this.withSizeLimit(instance.getSizeLimit());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMedium() {
      return this.medium;
   }

   public EmptyDirVolumeSourceFluent withMedium(String medium) {
      this.medium = medium;
      return this;
   }

   public boolean hasMedium() {
      return this.medium != null;
   }

   public Quantity buildSizeLimit() {
      return this.sizeLimit != null ? this.sizeLimit.build() : null;
   }

   public EmptyDirVolumeSourceFluent withSizeLimit(Quantity sizeLimit) {
      this._visitables.remove("sizeLimit");
      if (sizeLimit != null) {
         this.sizeLimit = new QuantityBuilder(sizeLimit);
         this._visitables.get("sizeLimit").add(this.sizeLimit);
      } else {
         this.sizeLimit = null;
         this._visitables.get("sizeLimit").remove(this.sizeLimit);
      }

      return this;
   }

   public boolean hasSizeLimit() {
      return this.sizeLimit != null;
   }

   public EmptyDirVolumeSourceFluent withNewSizeLimit(String amount, String format) {
      return this.withSizeLimit(new Quantity(amount, format));
   }

   public EmptyDirVolumeSourceFluent withNewSizeLimit(String amount) {
      return this.withSizeLimit(new Quantity(amount));
   }

   public SizeLimitNested withNewSizeLimit() {
      return new SizeLimitNested((Quantity)null);
   }

   public SizeLimitNested withNewSizeLimitLike(Quantity item) {
      return new SizeLimitNested(item);
   }

   public SizeLimitNested editSizeLimit() {
      return this.withNewSizeLimitLike((Quantity)Optional.ofNullable(this.buildSizeLimit()).orElse((Object)null));
   }

   public SizeLimitNested editOrNewSizeLimit() {
      return this.withNewSizeLimitLike((Quantity)Optional.ofNullable(this.buildSizeLimit()).orElse((new QuantityBuilder()).build()));
   }

   public SizeLimitNested editOrNewSizeLimitLike(Quantity item) {
      return this.withNewSizeLimitLike((Quantity)Optional.ofNullable(this.buildSizeLimit()).orElse(item));
   }

   public EmptyDirVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EmptyDirVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EmptyDirVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EmptyDirVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public EmptyDirVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            EmptyDirVolumeSourceFluent that = (EmptyDirVolumeSourceFluent)o;
            if (!Objects.equals(this.medium, that.medium)) {
               return false;
            } else if (!Objects.equals(this.sizeLimit, that.sizeLimit)) {
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
      return Objects.hash(new Object[]{this.medium, this.sizeLimit, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.medium != null) {
         sb.append("medium:");
         sb.append(this.medium + ",");
      }

      if (this.sizeLimit != null) {
         sb.append("sizeLimit:");
         sb.append(this.sizeLimit + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SizeLimitNested extends QuantityFluent implements Nested {
      QuantityBuilder builder;

      SizeLimitNested(Quantity item) {
         this.builder = new QuantityBuilder(this, item);
      }

      public Object and() {
         return EmptyDirVolumeSourceFluent.this.withSizeLimit(this.builder.build());
      }

      public Object endSizeLimit() {
         return this.and();
      }
   }
}
