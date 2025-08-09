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

public class RouteStatusFluent extends BaseFluent {
   private ArrayList parents = new ArrayList();
   private Map additionalProperties;

   public RouteStatusFluent() {
   }

   public RouteStatusFluent(RouteStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RouteStatus instance) {
      instance = instance != null ? instance : new RouteStatus();
      if (instance != null) {
         this.withParents(instance.getParents());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RouteStatusFluent addToParents(int index, RouteParentStatus item) {
      if (this.parents == null) {
         this.parents = new ArrayList();
      }

      RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
      if (index >= 0 && index < this.parents.size()) {
         this._visitables.get("parents").add(index, builder);
         this.parents.add(index, builder);
      } else {
         this._visitables.get("parents").add(builder);
         this.parents.add(builder);
      }

      return this;
   }

   public RouteStatusFluent setToParents(int index, RouteParentStatus item) {
      if (this.parents == null) {
         this.parents = new ArrayList();
      }

      RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
      if (index >= 0 && index < this.parents.size()) {
         this._visitables.get("parents").set(index, builder);
         this.parents.set(index, builder);
      } else {
         this._visitables.get("parents").add(builder);
         this.parents.add(builder);
      }

      return this;
   }

   public RouteStatusFluent addToParents(RouteParentStatus... items) {
      if (this.parents == null) {
         this.parents = new ArrayList();
      }

      for(RouteParentStatus item : items) {
         RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
         this._visitables.get("parents").add(builder);
         this.parents.add(builder);
      }

      return this;
   }

   public RouteStatusFluent addAllToParents(Collection items) {
      if (this.parents == null) {
         this.parents = new ArrayList();
      }

      for(RouteParentStatus item : items) {
         RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
         this._visitables.get("parents").add(builder);
         this.parents.add(builder);
      }

      return this;
   }

   public RouteStatusFluent removeFromParents(RouteParentStatus... items) {
      if (this.parents == null) {
         return this;
      } else {
         for(RouteParentStatus item : items) {
            RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
            this._visitables.get("parents").remove(builder);
            this.parents.remove(builder);
         }

         return this;
      }
   }

   public RouteStatusFluent removeAllFromParents(Collection items) {
      if (this.parents == null) {
         return this;
      } else {
         for(RouteParentStatus item : items) {
            RouteParentStatusBuilder builder = new RouteParentStatusBuilder(item);
            this._visitables.get("parents").remove(builder);
            this.parents.remove(builder);
         }

         return this;
      }
   }

   public RouteStatusFluent removeMatchingFromParents(Predicate predicate) {
      if (this.parents == null) {
         return this;
      } else {
         Iterator<RouteParentStatusBuilder> each = this.parents.iterator();
         List visitables = this._visitables.get("parents");

         while(each.hasNext()) {
            RouteParentStatusBuilder builder = (RouteParentStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildParents() {
      return this.parents != null ? build(this.parents) : null;
   }

   public RouteParentStatus buildParent(int index) {
      return ((RouteParentStatusBuilder)this.parents.get(index)).build();
   }

   public RouteParentStatus buildFirstParent() {
      return ((RouteParentStatusBuilder)this.parents.get(0)).build();
   }

   public RouteParentStatus buildLastParent() {
      return ((RouteParentStatusBuilder)this.parents.get(this.parents.size() - 1)).build();
   }

   public RouteParentStatus buildMatchingParent(Predicate predicate) {
      for(RouteParentStatusBuilder item : this.parents) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingParent(Predicate predicate) {
      for(RouteParentStatusBuilder item : this.parents) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RouteStatusFluent withParents(List parents) {
      if (this.parents != null) {
         this._visitables.get("parents").clear();
      }

      if (parents != null) {
         this.parents = new ArrayList();

         for(RouteParentStatus item : parents) {
            this.addToParents(item);
         }
      } else {
         this.parents = null;
      }

      return this;
   }

   public RouteStatusFluent withParents(RouteParentStatus... parents) {
      if (this.parents != null) {
         this.parents.clear();
         this._visitables.remove("parents");
      }

      if (parents != null) {
         for(RouteParentStatus item : parents) {
            this.addToParents(item);
         }
      }

      return this;
   }

   public boolean hasParents() {
      return this.parents != null && !this.parents.isEmpty();
   }

   public ParentsNested addNewParent() {
      return new ParentsNested(-1, (RouteParentStatus)null);
   }

   public ParentsNested addNewParentLike(RouteParentStatus item) {
      return new ParentsNested(-1, item);
   }

   public ParentsNested setNewParentLike(int index, RouteParentStatus item) {
      return new ParentsNested(index, item);
   }

   public ParentsNested editParent(int index) {
      if (this.parents.size() <= index) {
         throw new RuntimeException("Can't edit parents. Index exceeds size.");
      } else {
         return this.setNewParentLike(index, this.buildParent(index));
      }
   }

   public ParentsNested editFirstParent() {
      if (this.parents.size() == 0) {
         throw new RuntimeException("Can't edit first parents. The list is empty.");
      } else {
         return this.setNewParentLike(0, this.buildParent(0));
      }
   }

   public ParentsNested editLastParent() {
      int index = this.parents.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last parents. The list is empty.");
      } else {
         return this.setNewParentLike(index, this.buildParent(index));
      }
   }

   public ParentsNested editMatchingParent(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.parents.size(); ++i) {
         if (predicate.test((RouteParentStatusBuilder)this.parents.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching parents. No match found.");
      } else {
         return this.setNewParentLike(index, this.buildParent(index));
      }
   }

   public RouteStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RouteStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RouteStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RouteStatusFluent removeFromAdditionalProperties(Map map) {
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

   public RouteStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            RouteStatusFluent that = (RouteStatusFluent)o;
            if (!Objects.equals(this.parents, that.parents)) {
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
      return Objects.hash(new Object[]{this.parents, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.parents != null && !this.parents.isEmpty()) {
         sb.append("parents:");
         sb.append(this.parents + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParentsNested extends RouteParentStatusFluent implements Nested {
      RouteParentStatusBuilder builder;
      int index;

      ParentsNested(int index, RouteParentStatus item) {
         this.index = index;
         this.builder = new RouteParentStatusBuilder(this, item);
      }

      public Object and() {
         return RouteStatusFluent.this.setToParents(this.index, this.builder.build());
      }

      public Object endParent() {
         return this.and();
      }
   }
}
