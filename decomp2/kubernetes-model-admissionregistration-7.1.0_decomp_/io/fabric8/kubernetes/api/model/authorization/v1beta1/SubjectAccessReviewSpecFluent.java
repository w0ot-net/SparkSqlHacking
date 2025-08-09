package io.fabric8.kubernetes.api.model.authorization.v1beta1;

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

public class SubjectAccessReviewSpecFluent extends BaseFluent {
   private Map extra;
   private List group = new ArrayList();
   private NonResourceAttributesBuilder nonResourceAttributes;
   private ResourceAttributesBuilder resourceAttributes;
   private String uid;
   private String user;
   private Map additionalProperties;

   public SubjectAccessReviewSpecFluent() {
   }

   public SubjectAccessReviewSpecFluent(SubjectAccessReviewSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SubjectAccessReviewSpec instance) {
      instance = instance != null ? instance : new SubjectAccessReviewSpec();
      if (instance != null) {
         this.withExtra(instance.getExtra());
         this.withGroup(instance.getGroup());
         this.withNonResourceAttributes(instance.getNonResourceAttributes());
         this.withResourceAttributes(instance.getResourceAttributes());
         this.withUid(instance.getUid());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SubjectAccessReviewSpecFluent addToExtra(String key, List value) {
      if (this.extra == null && key != null && value != null) {
         this.extra = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.extra.put(key, value);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent addToExtra(Map map) {
      if (this.extra == null && map != null) {
         this.extra = new LinkedHashMap();
      }

      if (map != null) {
         this.extra.putAll(map);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent removeFromExtra(String key) {
      if (this.extra == null) {
         return this;
      } else {
         if (key != null && this.extra != null) {
            this.extra.remove(key);
         }

         return this;
      }
   }

   public SubjectAccessReviewSpecFluent removeFromExtra(Map map) {
      if (this.extra == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.extra != null) {
                  this.extra.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getExtra() {
      return this.extra;
   }

   public SubjectAccessReviewSpecFluent withExtra(Map extra) {
      if (extra == null) {
         this.extra = null;
      } else {
         this.extra = new LinkedHashMap(extra);
      }

      return this;
   }

   public boolean hasExtra() {
      return this.extra != null;
   }

   public SubjectAccessReviewSpecFluent addToGroup(int index, String item) {
      if (this.group == null) {
         this.group = new ArrayList();
      }

      this.group.add(index, item);
      return this;
   }

   public SubjectAccessReviewSpecFluent setToGroup(int index, String item) {
      if (this.group == null) {
         this.group = new ArrayList();
      }

      this.group.set(index, item);
      return this;
   }

   public SubjectAccessReviewSpecFluent addToGroup(String... items) {
      if (this.group == null) {
         this.group = new ArrayList();
      }

      for(String item : items) {
         this.group.add(item);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent addAllToGroup(Collection items) {
      if (this.group == null) {
         this.group = new ArrayList();
      }

      for(String item : items) {
         this.group.add(item);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent removeFromGroup(String... items) {
      if (this.group == null) {
         return this;
      } else {
         for(String item : items) {
            this.group.remove(item);
         }

         return this;
      }
   }

   public SubjectAccessReviewSpecFluent removeAllFromGroup(Collection items) {
      if (this.group == null) {
         return this;
      } else {
         for(String item : items) {
            this.group.remove(item);
         }

         return this;
      }
   }

   public List getGroup() {
      return this.group;
   }

   public String getGroup(int index) {
      return (String)this.group.get(index);
   }

   public String getFirstGroup() {
      return (String)this.group.get(0);
   }

   public String getLastGroup() {
      return (String)this.group.get(this.group.size() - 1);
   }

   public String getMatchingGroup(Predicate predicate) {
      for(String item : this.group) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingGroup(Predicate predicate) {
      for(String item : this.group) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SubjectAccessReviewSpecFluent withGroup(List group) {
      if (group != null) {
         this.group = new ArrayList();

         for(String item : group) {
            this.addToGroup(item);
         }
      } else {
         this.group = null;
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent withGroup(String... group) {
      if (this.group != null) {
         this.group.clear();
         this._visitables.remove("group");
      }

      if (group != null) {
         for(String item : group) {
            this.addToGroup(item);
         }
      }

      return this;
   }

   public boolean hasGroup() {
      return this.group != null && !this.group.isEmpty();
   }

   public NonResourceAttributes buildNonResourceAttributes() {
      return this.nonResourceAttributes != null ? this.nonResourceAttributes.build() : null;
   }

   public SubjectAccessReviewSpecFluent withNonResourceAttributes(NonResourceAttributes nonResourceAttributes) {
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

   public SubjectAccessReviewSpecFluent withNewNonResourceAttributes(String path, String verb) {
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

   public SubjectAccessReviewSpecFluent withResourceAttributes(ResourceAttributes resourceAttributes) {
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

   public String getUid() {
      return this.uid;
   }

   public SubjectAccessReviewSpecFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public String getUser() {
      return this.user;
   }

   public SubjectAccessReviewSpecFluent withUser(String user) {
      this.user = user;
      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public SubjectAccessReviewSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SubjectAccessReviewSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SubjectAccessReviewSpecFluent removeFromAdditionalProperties(Map map) {
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

   public SubjectAccessReviewSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            SubjectAccessReviewSpecFluent that = (SubjectAccessReviewSpecFluent)o;
            if (!Objects.equals(this.extra, that.extra)) {
               return false;
            } else if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.nonResourceAttributes, that.nonResourceAttributes)) {
               return false;
            } else if (!Objects.equals(this.resourceAttributes, that.resourceAttributes)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
               return false;
            } else if (!Objects.equals(this.user, that.user)) {
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
      return Objects.hash(new Object[]{this.extra, this.group, this.nonResourceAttributes, this.resourceAttributes, this.uid, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.extra != null && !this.extra.isEmpty()) {
         sb.append("extra:");
         sb.append(this.extra + ",");
      }

      if (this.group != null && !this.group.isEmpty()) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.nonResourceAttributes != null) {
         sb.append("nonResourceAttributes:");
         sb.append(this.nonResourceAttributes + ",");
      }

      if (this.resourceAttributes != null) {
         sb.append("resourceAttributes:");
         sb.append(this.resourceAttributes + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.user != null) {
         sb.append("user:");
         sb.append(this.user + ",");
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
         return SubjectAccessReviewSpecFluent.this.withNonResourceAttributes(this.builder.build());
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
         return SubjectAccessReviewSpecFluent.this.withResourceAttributes(this.builder.build());
      }

      public Object endResourceAttributes() {
         return this.and();
      }
   }
}
