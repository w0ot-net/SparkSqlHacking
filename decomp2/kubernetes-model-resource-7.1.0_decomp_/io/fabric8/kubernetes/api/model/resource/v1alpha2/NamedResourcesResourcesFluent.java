package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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

public class NamedResourcesResourcesFluent extends BaseFluent {
   private ArrayList instances = new ArrayList();
   private Map additionalProperties;

   public NamedResourcesResourcesFluent() {
   }

   public NamedResourcesResourcesFluent(NamedResourcesResources instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesResources instance) {
      instance = instance != null ? instance : new NamedResourcesResources();
      if (instance != null) {
         this.withInstances(instance.getInstances());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesResourcesFluent addToInstances(int index, NamedResourcesInstance item) {
      if (this.instances == null) {
         this.instances = new ArrayList();
      }

      NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
      if (index >= 0 && index < this.instances.size()) {
         this._visitables.get("instances").add(index, builder);
         this.instances.add(index, builder);
      } else {
         this._visitables.get("instances").add(builder);
         this.instances.add(builder);
      }

      return this;
   }

   public NamedResourcesResourcesFluent setToInstances(int index, NamedResourcesInstance item) {
      if (this.instances == null) {
         this.instances = new ArrayList();
      }

      NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
      if (index >= 0 && index < this.instances.size()) {
         this._visitables.get("instances").set(index, builder);
         this.instances.set(index, builder);
      } else {
         this._visitables.get("instances").add(builder);
         this.instances.add(builder);
      }

      return this;
   }

   public NamedResourcesResourcesFluent addToInstances(NamedResourcesInstance... items) {
      if (this.instances == null) {
         this.instances = new ArrayList();
      }

      for(NamedResourcesInstance item : items) {
         NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
         this._visitables.get("instances").add(builder);
         this.instances.add(builder);
      }

      return this;
   }

   public NamedResourcesResourcesFluent addAllToInstances(Collection items) {
      if (this.instances == null) {
         this.instances = new ArrayList();
      }

      for(NamedResourcesInstance item : items) {
         NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
         this._visitables.get("instances").add(builder);
         this.instances.add(builder);
      }

      return this;
   }

   public NamedResourcesResourcesFluent removeFromInstances(NamedResourcesInstance... items) {
      if (this.instances == null) {
         return this;
      } else {
         for(NamedResourcesInstance item : items) {
            NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
            this._visitables.get("instances").remove(builder);
            this.instances.remove(builder);
         }

         return this;
      }
   }

   public NamedResourcesResourcesFluent removeAllFromInstances(Collection items) {
      if (this.instances == null) {
         return this;
      } else {
         for(NamedResourcesInstance item : items) {
            NamedResourcesInstanceBuilder builder = new NamedResourcesInstanceBuilder(item);
            this._visitables.get("instances").remove(builder);
            this.instances.remove(builder);
         }

         return this;
      }
   }

   public NamedResourcesResourcesFluent removeMatchingFromInstances(Predicate predicate) {
      if (this.instances == null) {
         return this;
      } else {
         Iterator<NamedResourcesInstanceBuilder> each = this.instances.iterator();
         List visitables = this._visitables.get("instances");

         while(each.hasNext()) {
            NamedResourcesInstanceBuilder builder = (NamedResourcesInstanceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildInstances() {
      return this.instances != null ? build(this.instances) : null;
   }

   public NamedResourcesInstance buildInstance(int index) {
      return ((NamedResourcesInstanceBuilder)this.instances.get(index)).build();
   }

   public NamedResourcesInstance buildFirstInstance() {
      return ((NamedResourcesInstanceBuilder)this.instances.get(0)).build();
   }

   public NamedResourcesInstance buildLastInstance() {
      return ((NamedResourcesInstanceBuilder)this.instances.get(this.instances.size() - 1)).build();
   }

   public NamedResourcesInstance buildMatchingInstance(Predicate predicate) {
      for(NamedResourcesInstanceBuilder item : this.instances) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingInstance(Predicate predicate) {
      for(NamedResourcesInstanceBuilder item : this.instances) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamedResourcesResourcesFluent withInstances(List instances) {
      if (this.instances != null) {
         this._visitables.get("instances").clear();
      }

      if (instances != null) {
         this.instances = new ArrayList();

         for(NamedResourcesInstance item : instances) {
            this.addToInstances(item);
         }
      } else {
         this.instances = null;
      }

      return this;
   }

   public NamedResourcesResourcesFluent withInstances(NamedResourcesInstance... instances) {
      if (this.instances != null) {
         this.instances.clear();
         this._visitables.remove("instances");
      }

      if (instances != null) {
         for(NamedResourcesInstance item : instances) {
            this.addToInstances(item);
         }
      }

      return this;
   }

   public boolean hasInstances() {
      return this.instances != null && !this.instances.isEmpty();
   }

   public InstancesNested addNewInstance() {
      return new InstancesNested(-1, (NamedResourcesInstance)null);
   }

   public InstancesNested addNewInstanceLike(NamedResourcesInstance item) {
      return new InstancesNested(-1, item);
   }

   public InstancesNested setNewInstanceLike(int index, NamedResourcesInstance item) {
      return new InstancesNested(index, item);
   }

   public InstancesNested editInstance(int index) {
      if (this.instances.size() <= index) {
         throw new RuntimeException("Can't edit instances. Index exceeds size.");
      } else {
         return this.setNewInstanceLike(index, this.buildInstance(index));
      }
   }

   public InstancesNested editFirstInstance() {
      if (this.instances.size() == 0) {
         throw new RuntimeException("Can't edit first instances. The list is empty.");
      } else {
         return this.setNewInstanceLike(0, this.buildInstance(0));
      }
   }

   public InstancesNested editLastInstance() {
      int index = this.instances.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last instances. The list is empty.");
      } else {
         return this.setNewInstanceLike(index, this.buildInstance(index));
      }
   }

   public InstancesNested editMatchingInstance(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.instances.size(); ++i) {
         if (predicate.test((NamedResourcesInstanceBuilder)this.instances.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching instances. No match found.");
      } else {
         return this.setNewInstanceLike(index, this.buildInstance(index));
      }
   }

   public NamedResourcesResourcesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesResourcesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesResourcesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesResourcesFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesResourcesFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesResourcesFluent that = (NamedResourcesResourcesFluent)o;
            if (!Objects.equals(this.instances, that.instances)) {
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
      return Objects.hash(new Object[]{this.instances, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.instances != null && !this.instances.isEmpty()) {
         sb.append("instances:");
         sb.append(this.instances + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class InstancesNested extends NamedResourcesInstanceFluent implements Nested {
      NamedResourcesInstanceBuilder builder;
      int index;

      InstancesNested(int index, NamedResourcesInstance item) {
         this.index = index;
         this.builder = new NamedResourcesInstanceBuilder(this, item);
      }

      public Object and() {
         return NamedResourcesResourcesFluent.this.setToInstances(this.index, this.builder.build());
      }

      public Object endInstance() {
         return this.and();
      }
   }
}
