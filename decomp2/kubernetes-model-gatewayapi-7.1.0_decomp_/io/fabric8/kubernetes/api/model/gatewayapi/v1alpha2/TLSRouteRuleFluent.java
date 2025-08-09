package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.BackendRef;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.BackendRefBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.BackendRefFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class TLSRouteRuleFluent extends BaseFluent {
   private ArrayList backendRefs = new ArrayList();
   private String name;
   private Map additionalProperties;

   public TLSRouteRuleFluent() {
   }

   public TLSRouteRuleFluent(TLSRouteRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TLSRouteRule instance) {
      instance = instance != null ? instance : new TLSRouteRule();
      if (instance != null) {
         this.withBackendRefs(instance.getBackendRefs());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TLSRouteRuleFluent addToBackendRefs(int index, BackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      BackendRefBuilder builder = new BackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").add(index, builder);
         this.backendRefs.add(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public TLSRouteRuleFluent setToBackendRefs(int index, BackendRef item) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      BackendRefBuilder builder = new BackendRefBuilder(item);
      if (index >= 0 && index < this.backendRefs.size()) {
         this._visitables.get("backendRefs").set(index, builder);
         this.backendRefs.set(index, builder);
      } else {
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public TLSRouteRuleFluent addToBackendRefs(BackendRef... items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(BackendRef item : items) {
         BackendRefBuilder builder = new BackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public TLSRouteRuleFluent addAllToBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         this.backendRefs = new ArrayList();
      }

      for(BackendRef item : items) {
         BackendRefBuilder builder = new BackendRefBuilder(item);
         this._visitables.get("backendRefs").add(builder);
         this.backendRefs.add(builder);
      }

      return this;
   }

   public TLSRouteRuleFluent removeFromBackendRefs(BackendRef... items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(BackendRef item : items) {
            BackendRefBuilder builder = new BackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public TLSRouteRuleFluent removeAllFromBackendRefs(Collection items) {
      if (this.backendRefs == null) {
         return this;
      } else {
         for(BackendRef item : items) {
            BackendRefBuilder builder = new BackendRefBuilder(item);
            this._visitables.get("backendRefs").remove(builder);
            this.backendRefs.remove(builder);
         }

         return this;
      }
   }

   public TLSRouteRuleFluent removeMatchingFromBackendRefs(Predicate predicate) {
      if (this.backendRefs == null) {
         return this;
      } else {
         Iterator<BackendRefBuilder> each = this.backendRefs.iterator();
         List visitables = this._visitables.get("backendRefs");

         while(each.hasNext()) {
            BackendRefBuilder builder = (BackendRefBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildBackendRefs() {
      return this.backendRefs != null ? build(this.backendRefs) : null;
   }

   public BackendRef buildBackendRef(int index) {
      return ((BackendRefBuilder)this.backendRefs.get(index)).build();
   }

   public BackendRef buildFirstBackendRef() {
      return ((BackendRefBuilder)this.backendRefs.get(0)).build();
   }

   public BackendRef buildLastBackendRef() {
      return ((BackendRefBuilder)this.backendRefs.get(this.backendRefs.size() - 1)).build();
   }

   public BackendRef buildMatchingBackendRef(Predicate predicate) {
      for(BackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingBackendRef(Predicate predicate) {
      for(BackendRefBuilder item : this.backendRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TLSRouteRuleFluent withBackendRefs(List backendRefs) {
      if (this.backendRefs != null) {
         this._visitables.get("backendRefs").clear();
      }

      if (backendRefs != null) {
         this.backendRefs = new ArrayList();

         for(BackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      } else {
         this.backendRefs = null;
      }

      return this;
   }

   public TLSRouteRuleFluent withBackendRefs(BackendRef... backendRefs) {
      if (this.backendRefs != null) {
         this.backendRefs.clear();
         this._visitables.remove("backendRefs");
      }

      if (backendRefs != null) {
         for(BackendRef item : backendRefs) {
            this.addToBackendRefs(item);
         }
      }

      return this;
   }

   public boolean hasBackendRefs() {
      return this.backendRefs != null && !this.backendRefs.isEmpty();
   }

   public BackendRefsNested addNewBackendRef() {
      return new BackendRefsNested(-1, (BackendRef)null);
   }

   public BackendRefsNested addNewBackendRefLike(BackendRef item) {
      return new BackendRefsNested(-1, item);
   }

   public BackendRefsNested setNewBackendRefLike(int index, BackendRef item) {
      return new BackendRefsNested(index, item);
   }

   public BackendRefsNested editBackendRef(int index) {
      if (this.backendRefs.size() <= index) {
         throw new RuntimeException("Can't edit backendRefs. Index exceeds size.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public BackendRefsNested editFirstBackendRef() {
      if (this.backendRefs.size() == 0) {
         throw new RuntimeException("Can't edit first backendRefs. The list is empty.");
      } else {
         return this.setNewBackendRefLike(0, this.buildBackendRef(0));
      }
   }

   public BackendRefsNested editLastBackendRef() {
      int index = this.backendRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last backendRefs. The list is empty.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public BackendRefsNested editMatchingBackendRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.backendRefs.size(); ++i) {
         if (predicate.test((BackendRefBuilder)this.backendRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching backendRefs. No match found.");
      } else {
         return this.setNewBackendRefLike(index, this.buildBackendRef(index));
      }
   }

   public String getName() {
      return this.name;
   }

   public TLSRouteRuleFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public TLSRouteRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TLSRouteRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TLSRouteRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TLSRouteRuleFluent removeFromAdditionalProperties(Map map) {
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

   public TLSRouteRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            TLSRouteRuleFluent that = (TLSRouteRuleFluent)o;
            if (!Objects.equals(this.backendRefs, that.backendRefs)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.backendRefs, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backendRefs != null && !this.backendRefs.isEmpty()) {
         sb.append("backendRefs:");
         sb.append(this.backendRefs + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendRefsNested extends BackendRefFluent implements Nested {
      BackendRefBuilder builder;
      int index;

      BackendRefsNested(int index, BackendRef item) {
         this.index = index;
         this.builder = new BackendRefBuilder(this, item);
      }

      public Object and() {
         return TLSRouteRuleFluent.this.setToBackendRefs(this.index, this.builder.build());
      }

      public Object endBackendRef() {
         return this.and();
      }
   }
}
