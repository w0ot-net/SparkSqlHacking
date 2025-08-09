package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SelfSubjectAccessReviewSpecFluent extends BaseFluent {
   private NonResourceAttributesBuilder nonResourceAttributes;
   private ResourceAttributesBuilder resourceAttributes;
   private Map additionalProperties;

   public SelfSubjectAccessReviewSpecFluent() {
   }

   public SelfSubjectAccessReviewSpecFluent(SelfSubjectAccessReviewSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SelfSubjectAccessReviewSpec instance) {
      instance = instance != null ? instance : new SelfSubjectAccessReviewSpec();
      if (instance != null) {
         this.withNonResourceAttributes(instance.getNonResourceAttributes());
         this.withResourceAttributes(instance.getResourceAttributes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NonResourceAttributes buildNonResourceAttributes() {
      return this.nonResourceAttributes != null ? this.nonResourceAttributes.build() : null;
   }

   public SelfSubjectAccessReviewSpecFluent withNonResourceAttributes(NonResourceAttributes nonResourceAttributes) {
      this._visitables.remove("nonResourceAttributes");
      if (nonResourceAttributes != null) {
         this.nonResourceAttributes = new NonResourceAttributesBuilder(nonResourceAttributes);
         this._visitables.get("nonResourceAttributes").add(this.nonResourceAttributes);
      } else {
         this.nonResourceAttributes = null;
         this._visitables.get("nonResourceAttributes").remove(this.nonResourceAttributes);
      }

      return this;
   }

   public boolean hasNonResourceAttributes() {
      return this.nonResourceAttributes != null;
   }

   public SelfSubjectAccessReviewSpecFluent withNewNonResourceAttributes(String path, String verb) {
      return this.withNonResourceAttributes(new NonResourceAttributes(path, verb));
   }

   public NonResourceAttributesNested withNewNonResourceAttributes() {
      return new NonResourceAttributesNested((NonResourceAttributes)null);
   }

   public NonResourceAttributesNested withNewNonResourceAttributesLike(NonResourceAttributes item) {
      return new NonResourceAttributesNested(item);
   }

   public NonResourceAttributesNested editNonResourceAttributes() {
      return this.withNewNonResourceAttributesLike((NonResourceAttributes)Optional.ofNullable(this.buildNonResourceAttributes()).orElse((Object)null));
   }

   public NonResourceAttributesNested editOrNewNonResourceAttributes() {
      return this.withNewNonResourceAttributesLike((NonResourceAttributes)Optional.ofNullable(this.buildNonResourceAttributes()).orElse((new NonResourceAttributesBuilder()).build()));
   }

   public NonResourceAttributesNested editOrNewNonResourceAttributesLike(NonResourceAttributes item) {
      return this.withNewNonResourceAttributesLike((NonResourceAttributes)Optional.ofNullable(this.buildNonResourceAttributes()).orElse(item));
   }

   public ResourceAttributes buildResourceAttributes() {
      return this.resourceAttributes != null ? this.resourceAttributes.build() : null;
   }

   public SelfSubjectAccessReviewSpecFluent withResourceAttributes(ResourceAttributes resourceAttributes) {
      this._visitables.remove("resourceAttributes");
      if (resourceAttributes != null) {
         this.resourceAttributes = new ResourceAttributesBuilder(resourceAttributes);
         this._visitables.get("resourceAttributes").add(this.resourceAttributes);
      } else {
         this.resourceAttributes = null;
         this._visitables.get("resourceAttributes").remove(this.resourceAttributes);
      }

      return this;
   }

   public boolean hasResourceAttributes() {
      return this.resourceAttributes != null;
   }

   public ResourceAttributesNested withNewResourceAttributes() {
      return new ResourceAttributesNested((ResourceAttributes)null);
   }

   public ResourceAttributesNested withNewResourceAttributesLike(ResourceAttributes item) {
      return new ResourceAttributesNested(item);
   }

   public ResourceAttributesNested editResourceAttributes() {
      return this.withNewResourceAttributesLike((ResourceAttributes)Optional.ofNullable(this.buildResourceAttributes()).orElse((Object)null));
   }

   public ResourceAttributesNested editOrNewResourceAttributes() {
      return this.withNewResourceAttributesLike((ResourceAttributes)Optional.ofNullable(this.buildResourceAttributes()).orElse((new ResourceAttributesBuilder()).build()));
   }

   public ResourceAttributesNested editOrNewResourceAttributesLike(ResourceAttributes item) {
      return this.withNewResourceAttributesLike((ResourceAttributes)Optional.ofNullable(this.buildResourceAttributes()).orElse(item));
   }

   public SelfSubjectAccessReviewSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SelfSubjectAccessReviewSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SelfSubjectAccessReviewSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SelfSubjectAccessReviewSpecFluent removeFromAdditionalProperties(Map map) {
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

   public SelfSubjectAccessReviewSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            SelfSubjectAccessReviewSpecFluent that = (SelfSubjectAccessReviewSpecFluent)o;
            if (!Objects.equals(this.nonResourceAttributes, that.nonResourceAttributes)) {
               return false;
            } else if (!Objects.equals(this.resourceAttributes, that.resourceAttributes)) {
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
      return Objects.hash(new Object[]{this.nonResourceAttributes, this.resourceAttributes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nonResourceAttributes != null) {
         sb.append("nonResourceAttributes:");
         sb.append(this.nonResourceAttributes + ",");
      }

      if (this.resourceAttributes != null) {
         sb.append("resourceAttributes:");
         sb.append(this.resourceAttributes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NonResourceAttributesNested extends NonResourceAttributesFluent implements Nested {
      NonResourceAttributesBuilder builder;

      NonResourceAttributesNested(NonResourceAttributes item) {
         this.builder = new NonResourceAttributesBuilder(this, item);
      }

      public Object and() {
         return SelfSubjectAccessReviewSpecFluent.this.withNonResourceAttributes(this.builder.build());
      }

      public Object endNonResourceAttributes() {
         return this.and();
      }
   }

   public class ResourceAttributesNested extends ResourceAttributesFluent implements Nested {
      ResourceAttributesBuilder builder;

      ResourceAttributesNested(ResourceAttributes item) {
         this.builder = new ResourceAttributesBuilder(this, item);
      }

      public Object and() {
         return SelfSubjectAccessReviewSpecFluent.this.withResourceAttributes(this.builder.build());
      }

      public Object endResourceAttributes() {
         return this.and();
      }
   }
}
