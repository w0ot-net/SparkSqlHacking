package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class TokenRequestSpecFluent extends BaseFluent {
   private List audiences = new ArrayList();
   private BoundObjectReferenceBuilder boundObjectRef;
   private Long expirationSeconds;
   private Map additionalProperties;

   public TokenRequestSpecFluent() {
   }

   public TokenRequestSpecFluent(TokenRequestSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TokenRequestSpec instance) {
      instance = instance != null ? instance : new TokenRequestSpec();
      if (instance != null) {
         this.withAudiences(instance.getAudiences());
         this.withBoundObjectRef(instance.getBoundObjectRef());
         this.withExpirationSeconds(instance.getExpirationSeconds());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TokenRequestSpecFluent addToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.add(index, item);
      return this;
   }

   public TokenRequestSpecFluent setToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.set(index, item);
      return this;
   }

   public TokenRequestSpecFluent addToAudiences(String... items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenRequestSpecFluent addAllToAudiences(Collection items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenRequestSpecFluent removeFromAudiences(String... items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public TokenRequestSpecFluent removeAllFromAudiences(Collection items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public List getAudiences() {
      return this.audiences;
   }

   public String getAudience(int index) {
      return (String)this.audiences.get(index);
   }

   public String getFirstAudience() {
      return (String)this.audiences.get(0);
   }

   public String getLastAudience() {
      return (String)this.audiences.get(this.audiences.size() - 1);
   }

   public String getMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TokenRequestSpecFluent withAudiences(List audiences) {
      if (audiences != null) {
         this.audiences = new ArrayList();

         for(String item : audiences) {
            this.addToAudiences(item);
         }
      } else {
         this.audiences = null;
      }

      return this;
   }

   public TokenRequestSpecFluent withAudiences(String... audiences) {
      if (this.audiences != null) {
         this.audiences.clear();
         this._visitables.remove("audiences");
      }

      if (audiences != null) {
         for(String item : audiences) {
            this.addToAudiences(item);
         }
      }

      return this;
   }

   public boolean hasAudiences() {
      return this.audiences != null && !this.audiences.isEmpty();
   }

   public BoundObjectReference buildBoundObjectRef() {
      return this.boundObjectRef != null ? this.boundObjectRef.build() : null;
   }

   public TokenRequestSpecFluent withBoundObjectRef(BoundObjectReference boundObjectRef) {
      this._visitables.remove("boundObjectRef");
      if (boundObjectRef != null) {
         this.boundObjectRef = new BoundObjectReferenceBuilder(boundObjectRef);
         this._visitables.get("boundObjectRef").add(this.boundObjectRef);
      } else {
         this.boundObjectRef = null;
         this._visitables.get("boundObjectRef").remove(this.boundObjectRef);
      }

      return this;
   }

   public boolean hasBoundObjectRef() {
      return this.boundObjectRef != null;
   }

   public TokenRequestSpecFluent withNewBoundObjectRef(String apiVersion, String kind, String name, String uid) {
      return this.withBoundObjectRef(new BoundObjectReference(apiVersion, kind, name, uid));
   }

   public BoundObjectRefNested withNewBoundObjectRef() {
      return new BoundObjectRefNested((BoundObjectReference)null);
   }

   public BoundObjectRefNested withNewBoundObjectRefLike(BoundObjectReference item) {
      return new BoundObjectRefNested(item);
   }

   public BoundObjectRefNested editBoundObjectRef() {
      return this.withNewBoundObjectRefLike((BoundObjectReference)Optional.ofNullable(this.buildBoundObjectRef()).orElse((Object)null));
   }

   public BoundObjectRefNested editOrNewBoundObjectRef() {
      return this.withNewBoundObjectRefLike((BoundObjectReference)Optional.ofNullable(this.buildBoundObjectRef()).orElse((new BoundObjectReferenceBuilder()).build()));
   }

   public BoundObjectRefNested editOrNewBoundObjectRefLike(BoundObjectReference item) {
      return this.withNewBoundObjectRefLike((BoundObjectReference)Optional.ofNullable(this.buildBoundObjectRef()).orElse(item));
   }

   public Long getExpirationSeconds() {
      return this.expirationSeconds;
   }

   public TokenRequestSpecFluent withExpirationSeconds(Long expirationSeconds) {
      this.expirationSeconds = expirationSeconds;
      return this;
   }

   public boolean hasExpirationSeconds() {
      return this.expirationSeconds != null;
   }

   public TokenRequestSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TokenRequestSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TokenRequestSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TokenRequestSpecFluent removeFromAdditionalProperties(Map map) {
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

   public TokenRequestSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            TokenRequestSpecFluent that = (TokenRequestSpecFluent)o;
            if (!Objects.equals(this.audiences, that.audiences)) {
               return false;
            } else if (!Objects.equals(this.boundObjectRef, that.boundObjectRef)) {
               return false;
            } else if (!Objects.equals(this.expirationSeconds, that.expirationSeconds)) {
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
      return Objects.hash(new Object[]{this.audiences, this.boundObjectRef, this.expirationSeconds, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.audiences != null && !this.audiences.isEmpty()) {
         sb.append("audiences:");
         sb.append(this.audiences + ",");
      }

      if (this.boundObjectRef != null) {
         sb.append("boundObjectRef:");
         sb.append(this.boundObjectRef + ",");
      }

      if (this.expirationSeconds != null) {
         sb.append("expirationSeconds:");
         sb.append(this.expirationSeconds + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BoundObjectRefNested extends BoundObjectReferenceFluent implements Nested {
      BoundObjectReferenceBuilder builder;

      BoundObjectRefNested(BoundObjectReference item) {
         this.builder = new BoundObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return TokenRequestSpecFluent.this.withBoundObjectRef(this.builder.build());
      }

      public Object endBoundObjectRef() {
         return this.and();
      }
   }
}
