package io.fabric8.kubernetes.api.model;

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

public class ResourceStatusFluent extends BaseFluent {
   private String name;
   private ArrayList resources = new ArrayList();
   private Map additionalProperties;

   public ResourceStatusFluent() {
   }

   public ResourceStatusFluent(ResourceStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceStatus instance) {
      instance = instance != null ? instance : new ResourceStatus();
      if (instance != null) {
         this.withName(instance.getName());
         this.withResources(instance.getResources());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public ResourceStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ResourceStatusFluent addToResources(int index, ResourceHealth item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
      if (index >= 0 && index < this.resources.size()) {
         this._visitables.get("resources").add(index, builder);
         this.resources.add(index, builder);
      } else {
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public ResourceStatusFluent setToResources(int index, ResourceHealth item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
      if (index >= 0 && index < this.resources.size()) {
         this._visitables.get("resources").set(index, builder);
         this.resources.set(index, builder);
      } else {
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public ResourceStatusFluent addToResources(ResourceHealth... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(ResourceHealth item : items) {
         ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public ResourceStatusFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(ResourceHealth item : items) {
         ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public ResourceStatusFluent removeFromResources(ResourceHealth... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(ResourceHealth item : items) {
            ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
            this._visitables.get("resources").remove(builder);
            this.resources.remove(builder);
         }

         return this;
      }
   }

   public ResourceStatusFluent removeAllFromResources(Collection items) {
      if (this.resources == null) {
         return this;
      } else {
         for(ResourceHealth item : items) {
            ResourceHealthBuilder builder = new ResourceHealthBuilder(item);
            this._visitables.get("resources").remove(builder);
            this.resources.remove(builder);
         }

         return this;
      }
   }

   public ResourceStatusFluent removeMatchingFromResources(Predicate predicate) {
      if (this.resources == null) {
         return this;
      } else {
         Iterator<ResourceHealthBuilder> each = this.resources.iterator();
         List visitables = this._visitables.get("resources");

         while(each.hasNext()) {
            ResourceHealthBuilder builder = (ResourceHealthBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResources() {
      return this.resources != null ? build(this.resources) : null;
   }

   public ResourceHealth buildResource(int index) {
      return ((ResourceHealthBuilder)this.resources.get(index)).build();
   }

   public ResourceHealth buildFirstResource() {
      return ((ResourceHealthBuilder)this.resources.get(0)).build();
   }

   public ResourceHealth buildLastResource() {
      return ((ResourceHealthBuilder)this.resources.get(this.resources.size() - 1)).build();
   }

   public ResourceHealth buildMatchingResource(Predicate predicate) {
      for(ResourceHealthBuilder item : this.resources) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResource(Predicate predicate) {
      for(ResourceHealthBuilder item : this.resources) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceStatusFluent withResources(List resources) {
      if (this.resources != null) {
         this._visitables.get("resources").clear();
      }

      if (resources != null) {
         this.resources = new ArrayList();

         for(ResourceHealth item : resources) {
            this.addToResources(item);
         }
      } else {
         this.resources = null;
      }

      return this;
   }

   public ResourceStatusFluent withResources(ResourceHealth... resources) {
      if (this.resources != null) {
         this.resources.clear();
         this._visitables.remove("resources");
      }

      if (resources != null) {
         for(ResourceHealth item : resources) {
            this.addToResources(item);
         }
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null && !this.resources.isEmpty();
   }

   public ResourceStatusFluent addNewResource(String health, String resourceID) {
      return this.addToResources(new ResourceHealth(health, resourceID));
   }

   public ResourcesNested addNewResource() {
      return new ResourcesNested(-1, (ResourceHealth)null);
   }

   public ResourcesNested addNewResourceLike(ResourceHealth item) {
      return new ResourcesNested(-1, item);
   }

   public ResourcesNested setNewResourceLike(int index, ResourceHealth item) {
      return new ResourcesNested(index, item);
   }

   public ResourcesNested editResource(int index) {
      if (this.resources.size() <= index) {
         throw new RuntimeException("Can't edit resources. Index exceeds size.");
      } else {
         return this.setNewResourceLike(index, this.buildResource(index));
      }
   }

   public ResourcesNested editFirstResource() {
      if (this.resources.size() == 0) {
         throw new RuntimeException("Can't edit first resources. The list is empty.");
      } else {
         return this.setNewResourceLike(0, this.buildResource(0));
      }
   }

   public ResourcesNested editLastResource() {
      int index = this.resources.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resources. The list is empty.");
      } else {
         return this.setNewResourceLike(index, this.buildResource(index));
      }
   }

   public ResourcesNested editMatchingResource(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resources.size(); ++i) {
         if (predicate.test((ResourceHealthBuilder)this.resources.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resources. No match found.");
      } else {
         return this.setNewResourceLike(index, this.buildResource(index));
      }
   }

   public ResourceStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceStatusFluent that = (ResourceStatusFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
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
      return Objects.hash(new Object[]{this.name, this.resources, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.resources != null && !this.resources.isEmpty()) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ResourcesNested extends ResourceHealthFluent implements Nested {
      ResourceHealthBuilder builder;
      int index;

      ResourcesNested(int index, ResourceHealth item) {
         this.index = index;
         this.builder = new ResourceHealthBuilder(this, item);
      }

      public Object and() {
         return ResourceStatusFluent.this.setToResources(this.index, this.builder.build());
      }

      public Object endResource() {
         return this.and();
      }
   }
}
