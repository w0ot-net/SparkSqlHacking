package io.fabric8.kubernetes.api.model.extensions;

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

public class HTTPIngressRuleValueFluent extends BaseFluent {
   private ArrayList paths = new ArrayList();
   private Map additionalProperties;

   public HTTPIngressRuleValueFluent() {
   }

   public HTTPIngressRuleValueFluent(HTTPIngressRuleValue instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPIngressRuleValue instance) {
      instance = instance != null ? instance : new HTTPIngressRuleValue();
      if (instance != null) {
         this.withPaths(instance.getPaths());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HTTPIngressRuleValueFluent addToPaths(int index, HTTPIngressPath item) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
      if (index >= 0 && index < this.paths.size()) {
         this._visitables.get("paths").add(index, builder);
         this.paths.add(index, builder);
      } else {
         this._visitables.get("paths").add(builder);
         this.paths.add(builder);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent setToPaths(int index, HTTPIngressPath item) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
      if (index >= 0 && index < this.paths.size()) {
         this._visitables.get("paths").set(index, builder);
         this.paths.set(index, builder);
      } else {
         this._visitables.get("paths").add(builder);
         this.paths.add(builder);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent addToPaths(HTTPIngressPath... items) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      for(HTTPIngressPath item : items) {
         HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
         this._visitables.get("paths").add(builder);
         this.paths.add(builder);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent addAllToPaths(Collection items) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      for(HTTPIngressPath item : items) {
         HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
         this._visitables.get("paths").add(builder);
         this.paths.add(builder);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent removeFromPaths(HTTPIngressPath... items) {
      if (this.paths == null) {
         return this;
      } else {
         for(HTTPIngressPath item : items) {
            HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
            this._visitables.get("paths").remove(builder);
            this.paths.remove(builder);
         }

         return this;
      }
   }

   public HTTPIngressRuleValueFluent removeAllFromPaths(Collection items) {
      if (this.paths == null) {
         return this;
      } else {
         for(HTTPIngressPath item : items) {
            HTTPIngressPathBuilder builder = new HTTPIngressPathBuilder(item);
            this._visitables.get("paths").remove(builder);
            this.paths.remove(builder);
         }

         return this;
      }
   }

   public HTTPIngressRuleValueFluent removeMatchingFromPaths(Predicate predicate) {
      if (this.paths == null) {
         return this;
      } else {
         Iterator<HTTPIngressPathBuilder> each = this.paths.iterator();
         List visitables = this._visitables.get("paths");

         while(each.hasNext()) {
            HTTPIngressPathBuilder builder = (HTTPIngressPathBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPaths() {
      return this.paths != null ? build(this.paths) : null;
   }

   public HTTPIngressPath buildPath(int index) {
      return ((HTTPIngressPathBuilder)this.paths.get(index)).build();
   }

   public HTTPIngressPath buildFirstPath() {
      return ((HTTPIngressPathBuilder)this.paths.get(0)).build();
   }

   public HTTPIngressPath buildLastPath() {
      return ((HTTPIngressPathBuilder)this.paths.get(this.paths.size() - 1)).build();
   }

   public HTTPIngressPath buildMatchingPath(Predicate predicate) {
      for(HTTPIngressPathBuilder item : this.paths) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPath(Predicate predicate) {
      for(HTTPIngressPathBuilder item : this.paths) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPIngressRuleValueFluent withPaths(List paths) {
      if (this.paths != null) {
         this._visitables.get("paths").clear();
      }

      if (paths != null) {
         this.paths = new ArrayList();

         for(HTTPIngressPath item : paths) {
            this.addToPaths(item);
         }
      } else {
         this.paths = null;
      }

      return this;
   }

   public HTTPIngressRuleValueFluent withPaths(HTTPIngressPath... paths) {
      if (this.paths != null) {
         this.paths.clear();
         this._visitables.remove("paths");
      }

      if (paths != null) {
         for(HTTPIngressPath item : paths) {
            this.addToPaths(item);
         }
      }

      return this;
   }

   public boolean hasPaths() {
      return this.paths != null && !this.paths.isEmpty();
   }

   public PathsNested addNewPath() {
      return new PathsNested(-1, (HTTPIngressPath)null);
   }

   public PathsNested addNewPathLike(HTTPIngressPath item) {
      return new PathsNested(-1, item);
   }

   public PathsNested setNewPathLike(int index, HTTPIngressPath item) {
      return new PathsNested(index, item);
   }

   public PathsNested editPath(int index) {
      if (this.paths.size() <= index) {
         throw new RuntimeException("Can't edit paths. Index exceeds size.");
      } else {
         return this.setNewPathLike(index, this.buildPath(index));
      }
   }

   public PathsNested editFirstPath() {
      if (this.paths.size() == 0) {
         throw new RuntimeException("Can't edit first paths. The list is empty.");
      } else {
         return this.setNewPathLike(0, this.buildPath(0));
      }
   }

   public PathsNested editLastPath() {
      int index = this.paths.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last paths. The list is empty.");
      } else {
         return this.setNewPathLike(index, this.buildPath(index));
      }
   }

   public PathsNested editMatchingPath(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.paths.size(); ++i) {
         if (predicate.test((HTTPIngressPathBuilder)this.paths.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching paths. No match found.");
      } else {
         return this.setNewPathLike(index, this.buildPath(index));
      }
   }

   public HTTPIngressRuleValueFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPIngressRuleValueFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPIngressRuleValueFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPIngressRuleValueFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPIngressRuleValueFluent that = (HTTPIngressRuleValueFluent)o;
            if (!Objects.equals(this.paths, that.paths)) {
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
      return Objects.hash(new Object[]{this.paths, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.paths != null && !this.paths.isEmpty()) {
         sb.append("paths:");
         sb.append(this.paths + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PathsNested extends HTTPIngressPathFluent implements Nested {
      HTTPIngressPathBuilder builder;
      int index;

      PathsNested(int index, HTTPIngressPath item) {
         this.index = index;
         this.builder = new HTTPIngressPathBuilder(this, item);
      }

      public Object and() {
         return HTTPIngressRuleValueFluent.this.setToPaths(this.index, this.builder.build());
      }

      public Object endPath() {
         return this.and();
      }
   }
}
