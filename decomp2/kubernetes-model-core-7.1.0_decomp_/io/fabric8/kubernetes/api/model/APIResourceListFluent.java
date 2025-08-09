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

public class APIResourceListFluent extends BaseFluent {
   private String apiVersion;
   private String groupVersion;
   private String kind;
   private ArrayList resources = new ArrayList();
   private Map additionalProperties;

   public APIResourceListFluent() {
   }

   public APIResourceListFluent(APIResourceList instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIResourceList instance) {
      instance = instance != null ? instance : new APIResourceList();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withGroupVersion(instance.getGroupVersion());
         this.withKind(instance.getKind());
         this.withResources(instance.getResources());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public APIResourceListFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getGroupVersion() {
      return this.groupVersion;
   }

   public APIResourceListFluent withGroupVersion(String groupVersion) {
      this.groupVersion = groupVersion;
      return this;
   }

   public boolean hasGroupVersion() {
      return this.groupVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public APIResourceListFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public APIResourceListFluent addToResources(int index, APIResource item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      APIResourceBuilder builder = new APIResourceBuilder(item);
      if (index >= 0 && index < this.resources.size()) {
         this._visitables.get("resources").add(index, builder);
         this.resources.add(index, builder);
      } else {
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public APIResourceListFluent setToResources(int index, APIResource item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      APIResourceBuilder builder = new APIResourceBuilder(item);
      if (index >= 0 && index < this.resources.size()) {
         this._visitables.get("resources").set(index, builder);
         this.resources.set(index, builder);
      } else {
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public APIResourceListFluent addToResources(APIResource... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(APIResource item : items) {
         APIResourceBuilder builder = new APIResourceBuilder(item);
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public APIResourceListFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(APIResource item : items) {
         APIResourceBuilder builder = new APIResourceBuilder(item);
         this._visitables.get("resources").add(builder);
         this.resources.add(builder);
      }

      return this;
   }

   public APIResourceListFluent removeFromResources(APIResource... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(APIResource item : items) {
            APIResourceBuilder builder = new APIResourceBuilder(item);
            this._visitables.get("resources").remove(builder);
            this.resources.remove(builder);
         }

         return this;
      }
   }

   public APIResourceListFluent removeAllFromResources(Collection items) {
      if (this.resources == null) {
         return this;
      } else {
         for(APIResource item : items) {
            APIResourceBuilder builder = new APIResourceBuilder(item);
            this._visitables.get("resources").remove(builder);
            this.resources.remove(builder);
         }

         return this;
      }
   }

   public APIResourceListFluent removeMatchingFromResources(Predicate predicate) {
      if (this.resources == null) {
         return this;
      } else {
         Iterator<APIResourceBuilder> each = this.resources.iterator();
         List visitables = this._visitables.get("resources");

         while(each.hasNext()) {
            APIResourceBuilder builder = (APIResourceBuilder)each.next();
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

   public APIResource buildResource(int index) {
      return ((APIResourceBuilder)this.resources.get(index)).build();
   }

   public APIResource buildFirstResource() {
      return ((APIResourceBuilder)this.resources.get(0)).build();
   }

   public APIResource buildLastResource() {
      return ((APIResourceBuilder)this.resources.get(this.resources.size() - 1)).build();
   }

   public APIResource buildMatchingResource(Predicate predicate) {
      for(APIResourceBuilder item : this.resources) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResource(Predicate predicate) {
      for(APIResourceBuilder item : this.resources) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public APIResourceListFluent withResources(List resources) {
      if (this.resources != null) {
         this._visitables.get("resources").clear();
      }

      if (resources != null) {
         this.resources = new ArrayList();

         for(APIResource item : resources) {
            this.addToResources(item);
         }
      } else {
         this.resources = null;
      }

      return this;
   }

   public APIResourceListFluent withResources(APIResource... resources) {
      if (this.resources != null) {
         this.resources.clear();
         this._visitables.remove("resources");
      }

      if (resources != null) {
         for(APIResource item : resources) {
            this.addToResources(item);
         }
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null && !this.resources.isEmpty();
   }

   public ResourcesNested addNewResource() {
      return new ResourcesNested(-1, (APIResource)null);
   }

   public ResourcesNested addNewResourceLike(APIResource item) {
      return new ResourcesNested(-1, item);
   }

   public ResourcesNested setNewResourceLike(int index, APIResource item) {
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
         if (predicate.test((APIResourceBuilder)this.resources.get(i))) {
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

   public APIResourceListFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIResourceListFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIResourceListFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIResourceListFluent removeFromAdditionalProperties(Map map) {
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

   public APIResourceListFluent withAdditionalProperties(Map additionalProperties) {
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
            APIResourceListFluent that = (APIResourceListFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.groupVersion, that.groupVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.groupVersion, this.kind, this.resources, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.groupVersion != null) {
         sb.append("groupVersion:");
         sb.append(this.groupVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
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

   public class ResourcesNested extends APIResourceFluent implements Nested {
      APIResourceBuilder builder;
      int index;

      ResourcesNested(int index, APIResource item) {
         this.index = index;
         this.builder = new APIResourceBuilder(this, item);
      }

      public Object and() {
         return APIResourceListFluent.this.setToResources(this.index, this.builder.build());
      }

      public Object endResource() {
         return this.and();
      }
   }
}
