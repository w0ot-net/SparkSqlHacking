package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CommonRouteSpecFluent extends BaseFluent {
   private ArrayList parentRefs = new ArrayList();
   private Map additionalProperties;

   public CommonRouteSpecFluent() {
   }

   public CommonRouteSpecFluent(CommonRouteSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CommonRouteSpec instance) {
      instance = instance != null ? instance : new CommonRouteSpec();
      if (instance != null) {
         this.withParentRefs(instance.getParentRefs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CommonRouteSpecFluent addToParentRefs(int index, ParentReference item) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
      if (index >= 0 && index < this.parentRefs.size()) {
         this._visitables.get("parentRefs").add(index, builder);
         this.parentRefs.add(index, builder);
      } else {
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public CommonRouteSpecFluent setToParentRefs(int index, ParentReference item) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
      if (index >= 0 && index < this.parentRefs.size()) {
         this._visitables.get("parentRefs").set(index, builder);
         this.parentRefs.set(index, builder);
      } else {
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public CommonRouteSpecFluent addToParentRefs(ParentReference... items) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      for(ParentReference item : items) {
         ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public CommonRouteSpecFluent addAllToParentRefs(Collection items) {
      if (this.parentRefs == null) {
         this.parentRefs = new ArrayList();
      }

      for(ParentReference item : items) {
         ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
         this._visitables.get("parentRefs").add(builder);
         this.parentRefs.add(builder);
      }

      return this;
   }

   public CommonRouteSpecFluent removeFromParentRefs(ParentReference... items) {
      if (this.parentRefs == null) {
         return this;
      } else {
         for(ParentReference item : items) {
            ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
            this._visitables.get("parentRefs").remove(builder);
            this.parentRefs.remove(builder);
         }

         return this;
      }
   }

   public CommonRouteSpecFluent removeAllFromParentRefs(Collection items) {
      if (this.parentRefs == null) {
         return this;
      } else {
         for(ParentReference item : items) {
            ParentReferenceBuilder builder = new ParentReferenceBuilder(item);
            this._visitables.get("parentRefs").remove(builder);
            this.parentRefs.remove(builder);
         }

         return this;
      }
   }

   public CommonRouteSpecFluent removeMatchingFromParentRefs(Predicate predicate) {
      if (this.parentRefs == null) {
         return this;
      } else {
         Iterator<ParentReferenceBuilder> each = this.parentRefs.iterator();
         List visitables = this._visitables.get("parentRefs");

         while(each.hasNext()) {
            ParentReferenceBuilder builder = (ParentReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildParentRefs() {
      return this.parentRefs != null ? build(this.parentRefs) : null;
   }

   public ParentReference buildParentRef(int index) {
      return ((ParentReferenceBuilder)this.parentRefs.get(index)).build();
   }

   public ParentReference buildFirstParentRef() {
      return ((ParentReferenceBuilder)this.parentRefs.get(0)).build();
   }

   public ParentReference buildLastParentRef() {
      return ((ParentReferenceBuilder)this.parentRefs.get(this.parentRefs.size() - 1)).build();
   }

   public ParentReference buildMatchingParentRef(Predicate predicate) {
      for(ParentReferenceBuilder item : this.parentRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingParentRef(Predicate predicate) {
      for(ParentReferenceBuilder item : this.parentRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CommonRouteSpecFluent withParentRefs(List parentRefs) {
      if (this.parentRefs != null) {
         this._visitables.get("parentRefs").clear();
      }

      if (parentRefs != null) {
         this.parentRefs = new ArrayList();

         for(ParentReference item : parentRefs) {
            this.addToParentRefs(item);
         }
      } else {
         this.parentRefs = null;
      }

      return this;
   }

   public CommonRouteSpecFluent withParentRefs(ParentReference... parentRefs) {
      if (this.parentRefs != null) {
         this.parentRefs.clear();
         this._visitables.remove("parentRefs");
      }

      if (parentRefs != null) {
         for(ParentReference item : parentRefs) {
            this.addToParentRefs(item);
         }
      }

      return this;
   }

   public boolean hasParentRefs() {
      return this.parentRefs != null && !this.parentRefs.isEmpty();
   }

   public ParentRefsNested addNewParentRef() {
      return new ParentRefsNested(-1, (ParentReference)null);
   }

   public ParentRefsNested addNewParentRefLike(ParentReference item) {
      return new ParentRefsNested(-1, item);
   }

   public ParentRefsNested setNewParentRefLike(int index, ParentReference item) {
      return new ParentRefsNested(index, item);
   }

   public ParentRefsNested editParentRef(int index) {
      if (this.parentRefs.size() <= index) {
         throw new RuntimeException("Can't edit parentRefs. Index exceeds size.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public ParentRefsNested editFirstParentRef() {
      if (this.parentRefs.size() == 0) {
         throw new RuntimeException("Can't edit first parentRefs. The list is empty.");
      } else {
         return this.setNewParentRefLike(0, this.buildParentRef(0));
      }
   }

   public ParentRefsNested editLastParentRef() {
      int index = this.parentRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last parentRefs. The list is empty.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public ParentRefsNested editMatchingParentRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.parentRefs.size(); ++i) {
         if (predicate.test((ParentReferenceBuilder)this.parentRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching parentRefs. No match found.");
      } else {
         return this.setNewParentRefLike(index, this.buildParentRef(index));
      }
   }

   public CommonRouteSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CommonRouteSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CommonRouteSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CommonRouteSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CommonRouteSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CommonRouteSpecFluent that = (CommonRouteSpecFluent)o;
            if (!Objects.equals(this.parentRefs, that.parentRefs)) {
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
      return Objects.hash(new Object[]{this.parentRefs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.parentRefs != null && !this.parentRefs.isEmpty()) {
         sb.append("parentRefs:");
         sb.append(this.parentRefs + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParentRefsNested extends ParentReferenceFluent implements Nested {
      ParentReferenceBuilder builder;
      int index;

      ParentRefsNested(int index, ParentReference item) {
         this.index = index;
         this.builder = new ParentReferenceBuilder(this, item);
      }

      public Object and() {
         return CommonRouteSpecFluent.this.setToParentRefs(this.index, this.builder.build());
      }

      public Object endParentRef() {
         return this.and();
      }
   }
}
