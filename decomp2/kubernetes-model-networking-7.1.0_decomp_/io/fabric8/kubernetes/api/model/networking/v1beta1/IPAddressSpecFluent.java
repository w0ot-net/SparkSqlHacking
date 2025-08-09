package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IPAddressSpecFluent extends BaseFluent {
   private ParentReferenceBuilder parentRef;
   private Map additionalProperties;

   public IPAddressSpecFluent() {
   }

   public IPAddressSpecFluent(IPAddressSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IPAddressSpec instance) {
      instance = instance != null ? instance : new IPAddressSpec();
      if (instance != null) {
         this.withParentRef(instance.getParentRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ParentReference buildParentRef() {
      return this.parentRef != null ? this.parentRef.build() : null;
   }

   public IPAddressSpecFluent withParentRef(ParentReference parentRef) {
      this._visitables.remove("parentRef");
      if (parentRef != null) {
         this.parentRef = new ParentReferenceBuilder(parentRef);
         this._visitables.get("parentRef").add(this.parentRef);
      } else {
         this.parentRef = null;
         this._visitables.get("parentRef").remove(this.parentRef);
      }

      return this;
   }

   public boolean hasParentRef() {
      return this.parentRef != null;
   }

   public IPAddressSpecFluent withNewParentRef(String group, String name, String namespace, String resource) {
      return this.withParentRef(new ParentReference(group, name, namespace, resource));
   }

   public ParentRefNested withNewParentRef() {
      return new ParentRefNested((ParentReference)null);
   }

   public ParentRefNested withNewParentRefLike(ParentReference item) {
      return new ParentRefNested(item);
   }

   public ParentRefNested editParentRef() {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse((Object)null));
   }

   public ParentRefNested editOrNewParentRef() {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse((new ParentReferenceBuilder()).build()));
   }

   public ParentRefNested editOrNewParentRefLike(ParentReference item) {
      return this.withNewParentRefLike((ParentReference)Optional.ofNullable(this.buildParentRef()).orElse(item));
   }

   public IPAddressSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IPAddressSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IPAddressSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IPAddressSpecFluent removeFromAdditionalProperties(Map map) {
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

   public IPAddressSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            IPAddressSpecFluent that = (IPAddressSpecFluent)o;
            if (!Objects.equals(this.parentRef, that.parentRef)) {
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
      return Objects.hash(new Object[]{this.parentRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.parentRef != null) {
         sb.append("parentRef:");
         sb.append(this.parentRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParentRefNested extends ParentReferenceFluent implements Nested {
      ParentReferenceBuilder builder;

      ParentRefNested(ParentReference item) {
         this.builder = new ParentReferenceBuilder(this, item);
      }

      public Object and() {
         return IPAddressSpecFluent.this.withParentRef(this.builder.build());
      }

      public Object endParentRef() {
         return this.and();
      }
   }
}
