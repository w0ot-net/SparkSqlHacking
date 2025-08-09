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
import java.util.Optional;
import java.util.function.Predicate;

public class AllowedRoutesFluent extends BaseFluent {
   private ArrayList kinds = new ArrayList();
   private RouteNamespacesBuilder namespaces;
   private Map additionalProperties;

   public AllowedRoutesFluent() {
   }

   public AllowedRoutesFluent(AllowedRoutes instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AllowedRoutes instance) {
      instance = instance != null ? instance : new AllowedRoutes();
      if (instance != null) {
         this.withKinds(instance.getKinds());
         this.withNamespaces(instance.getNamespaces());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AllowedRoutesFluent addToKinds(int index, RouteGroupKind item) {
      if (this.kinds == null) {
         this.kinds = new ArrayList();
      }

      RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
      if (index >= 0 && index < this.kinds.size()) {
         this._visitables.get("kinds").add(index, builder);
         this.kinds.add(index, builder);
      } else {
         this._visitables.get("kinds").add(builder);
         this.kinds.add(builder);
      }

      return this;
   }

   public AllowedRoutesFluent setToKinds(int index, RouteGroupKind item) {
      if (this.kinds == null) {
         this.kinds = new ArrayList();
      }

      RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
      if (index >= 0 && index < this.kinds.size()) {
         this._visitables.get("kinds").set(index, builder);
         this.kinds.set(index, builder);
      } else {
         this._visitables.get("kinds").add(builder);
         this.kinds.add(builder);
      }

      return this;
   }

   public AllowedRoutesFluent addToKinds(RouteGroupKind... items) {
      if (this.kinds == null) {
         this.kinds = new ArrayList();
      }

      for(RouteGroupKind item : items) {
         RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
         this._visitables.get("kinds").add(builder);
         this.kinds.add(builder);
      }

      return this;
   }

   public AllowedRoutesFluent addAllToKinds(Collection items) {
      if (this.kinds == null) {
         this.kinds = new ArrayList();
      }

      for(RouteGroupKind item : items) {
         RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
         this._visitables.get("kinds").add(builder);
         this.kinds.add(builder);
      }

      return this;
   }

   public AllowedRoutesFluent removeFromKinds(RouteGroupKind... items) {
      if (this.kinds == null) {
         return this;
      } else {
         for(RouteGroupKind item : items) {
            RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
            this._visitables.get("kinds").remove(builder);
            this.kinds.remove(builder);
         }

         return this;
      }
   }

   public AllowedRoutesFluent removeAllFromKinds(Collection items) {
      if (this.kinds == null) {
         return this;
      } else {
         for(RouteGroupKind item : items) {
            RouteGroupKindBuilder builder = new RouteGroupKindBuilder(item);
            this._visitables.get("kinds").remove(builder);
            this.kinds.remove(builder);
         }

         return this;
      }
   }

   public AllowedRoutesFluent removeMatchingFromKinds(Predicate predicate) {
      if (this.kinds == null) {
         return this;
      } else {
         Iterator<RouteGroupKindBuilder> each = this.kinds.iterator();
         List visitables = this._visitables.get("kinds");

         while(each.hasNext()) {
            RouteGroupKindBuilder builder = (RouteGroupKindBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildKinds() {
      return this.kinds != null ? build(this.kinds) : null;
   }

   public RouteGroupKind buildKind(int index) {
      return ((RouteGroupKindBuilder)this.kinds.get(index)).build();
   }

   public RouteGroupKind buildFirstKind() {
      return ((RouteGroupKindBuilder)this.kinds.get(0)).build();
   }

   public RouteGroupKind buildLastKind() {
      return ((RouteGroupKindBuilder)this.kinds.get(this.kinds.size() - 1)).build();
   }

   public RouteGroupKind buildMatchingKind(Predicate predicate) {
      for(RouteGroupKindBuilder item : this.kinds) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingKind(Predicate predicate) {
      for(RouteGroupKindBuilder item : this.kinds) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AllowedRoutesFluent withKinds(List kinds) {
      if (this.kinds != null) {
         this._visitables.get("kinds").clear();
      }

      if (kinds != null) {
         this.kinds = new ArrayList();

         for(RouteGroupKind item : kinds) {
            this.addToKinds(item);
         }
      } else {
         this.kinds = null;
      }

      return this;
   }

   public AllowedRoutesFluent withKinds(RouteGroupKind... kinds) {
      if (this.kinds != null) {
         this.kinds.clear();
         this._visitables.remove("kinds");
      }

      if (kinds != null) {
         for(RouteGroupKind item : kinds) {
            this.addToKinds(item);
         }
      }

      return this;
   }

   public boolean hasKinds() {
      return this.kinds != null && !this.kinds.isEmpty();
   }

   public AllowedRoutesFluent addNewKind(String group, String kind) {
      return this.addToKinds(new RouteGroupKind(group, kind));
   }

   public KindsNested addNewKind() {
      return new KindsNested(-1, (RouteGroupKind)null);
   }

   public KindsNested addNewKindLike(RouteGroupKind item) {
      return new KindsNested(-1, item);
   }

   public KindsNested setNewKindLike(int index, RouteGroupKind item) {
      return new KindsNested(index, item);
   }

   public KindsNested editKind(int index) {
      if (this.kinds.size() <= index) {
         throw new RuntimeException("Can't edit kinds. Index exceeds size.");
      } else {
         return this.setNewKindLike(index, this.buildKind(index));
      }
   }

   public KindsNested editFirstKind() {
      if (this.kinds.size() == 0) {
         throw new RuntimeException("Can't edit first kinds. The list is empty.");
      } else {
         return this.setNewKindLike(0, this.buildKind(0));
      }
   }

   public KindsNested editLastKind() {
      int index = this.kinds.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last kinds. The list is empty.");
      } else {
         return this.setNewKindLike(index, this.buildKind(index));
      }
   }

   public KindsNested editMatchingKind(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.kinds.size(); ++i) {
         if (predicate.test((RouteGroupKindBuilder)this.kinds.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching kinds. No match found.");
      } else {
         return this.setNewKindLike(index, this.buildKind(index));
      }
   }

   public RouteNamespaces buildNamespaces() {
      return this.namespaces != null ? this.namespaces.build() : null;
   }

   public AllowedRoutesFluent withNamespaces(RouteNamespaces namespaces) {
      this._visitables.remove("namespaces");
      if (namespaces != null) {
         this.namespaces = new RouteNamespacesBuilder(namespaces);
         this._visitables.get("namespaces").add(this.namespaces);
      } else {
         this.namespaces = null;
         this._visitables.get("namespaces").remove(this.namespaces);
      }

      return this;
   }

   public boolean hasNamespaces() {
      return this.namespaces != null;
   }

   public NamespacesNested withNewNamespaces() {
      return new NamespacesNested((RouteNamespaces)null);
   }

   public NamespacesNested withNewNamespacesLike(RouteNamespaces item) {
      return new NamespacesNested(item);
   }

   public NamespacesNested editNamespaces() {
      return this.withNewNamespacesLike((RouteNamespaces)Optional.ofNullable(this.buildNamespaces()).orElse((Object)null));
   }

   public NamespacesNested editOrNewNamespaces() {
      return this.withNewNamespacesLike((RouteNamespaces)Optional.ofNullable(this.buildNamespaces()).orElse((new RouteNamespacesBuilder()).build()));
   }

   public NamespacesNested editOrNewNamespacesLike(RouteNamespaces item) {
      return this.withNewNamespacesLike((RouteNamespaces)Optional.ofNullable(this.buildNamespaces()).orElse(item));
   }

   public AllowedRoutesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AllowedRoutesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AllowedRoutesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AllowedRoutesFluent removeFromAdditionalProperties(Map map) {
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

   public AllowedRoutesFluent withAdditionalProperties(Map additionalProperties) {
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
            AllowedRoutesFluent that = (AllowedRoutesFluent)o;
            if (!Objects.equals(this.kinds, that.kinds)) {
               return false;
            } else if (!Objects.equals(this.namespaces, that.namespaces)) {
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
      return Objects.hash(new Object[]{this.kinds, this.namespaces, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.kinds != null && !this.kinds.isEmpty()) {
         sb.append("kinds:");
         sb.append(this.kinds + ",");
      }

      if (this.namespaces != null) {
         sb.append("namespaces:");
         sb.append(this.namespaces + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class KindsNested extends RouteGroupKindFluent implements Nested {
      RouteGroupKindBuilder builder;
      int index;

      KindsNested(int index, RouteGroupKind item) {
         this.index = index;
         this.builder = new RouteGroupKindBuilder(this, item);
      }

      public Object and() {
         return AllowedRoutesFluent.this.setToKinds(this.index, this.builder.build());
      }

      public Object endKind() {
         return this.and();
      }
   }

   public class NamespacesNested extends RouteNamespacesFluent implements Nested {
      RouteNamespacesBuilder builder;

      NamespacesNested(RouteNamespaces item) {
         this.builder = new RouteNamespacesBuilder(this, item);
      }

      public Object and() {
         return AllowedRoutesFluent.this.withNamespaces(this.builder.build());
      }

      public Object endNamespaces() {
         return this.and();
      }
   }
}
