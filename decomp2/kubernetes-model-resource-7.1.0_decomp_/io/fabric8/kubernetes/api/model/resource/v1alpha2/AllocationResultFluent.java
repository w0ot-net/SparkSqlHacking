package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.NodeSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AllocationResultFluent extends BaseFluent {
   private NodeSelector availableOnNodes;
   private ArrayList resourceHandles = new ArrayList();
   private Boolean shareable;
   private Map additionalProperties;

   public AllocationResultFluent() {
   }

   public AllocationResultFluent(AllocationResult instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AllocationResult instance) {
      instance = instance != null ? instance : new AllocationResult();
      if (instance != null) {
         this.withAvailableOnNodes(instance.getAvailableOnNodes());
         this.withResourceHandles(instance.getResourceHandles());
         this.withShareable(instance.getShareable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeSelector getAvailableOnNodes() {
      return this.availableOnNodes;
   }

   public AllocationResultFluent withAvailableOnNodes(NodeSelector availableOnNodes) {
      this.availableOnNodes = availableOnNodes;
      return this;
   }

   public boolean hasAvailableOnNodes() {
      return this.availableOnNodes != null;
   }

   public AllocationResultFluent addToResourceHandles(int index, ResourceHandle item) {
      if (this.resourceHandles == null) {
         this.resourceHandles = new ArrayList();
      }

      ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
      if (index >= 0 && index < this.resourceHandles.size()) {
         this._visitables.get("resourceHandles").add(index, builder);
         this.resourceHandles.add(index, builder);
      } else {
         this._visitables.get("resourceHandles").add(builder);
         this.resourceHandles.add(builder);
      }

      return this;
   }

   public AllocationResultFluent setToResourceHandles(int index, ResourceHandle item) {
      if (this.resourceHandles == null) {
         this.resourceHandles = new ArrayList();
      }

      ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
      if (index >= 0 && index < this.resourceHandles.size()) {
         this._visitables.get("resourceHandles").set(index, builder);
         this.resourceHandles.set(index, builder);
      } else {
         this._visitables.get("resourceHandles").add(builder);
         this.resourceHandles.add(builder);
      }

      return this;
   }

   public AllocationResultFluent addToResourceHandles(ResourceHandle... items) {
      if (this.resourceHandles == null) {
         this.resourceHandles = new ArrayList();
      }

      for(ResourceHandle item : items) {
         ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
         this._visitables.get("resourceHandles").add(builder);
         this.resourceHandles.add(builder);
      }

      return this;
   }

   public AllocationResultFluent addAllToResourceHandles(Collection items) {
      if (this.resourceHandles == null) {
         this.resourceHandles = new ArrayList();
      }

      for(ResourceHandle item : items) {
         ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
         this._visitables.get("resourceHandles").add(builder);
         this.resourceHandles.add(builder);
      }

      return this;
   }

   public AllocationResultFluent removeFromResourceHandles(ResourceHandle... items) {
      if (this.resourceHandles == null) {
         return this;
      } else {
         for(ResourceHandle item : items) {
            ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
            this._visitables.get("resourceHandles").remove(builder);
            this.resourceHandles.remove(builder);
         }

         return this;
      }
   }

   public AllocationResultFluent removeAllFromResourceHandles(Collection items) {
      if (this.resourceHandles == null) {
         return this;
      } else {
         for(ResourceHandle item : items) {
            ResourceHandleBuilder builder = new ResourceHandleBuilder(item);
            this._visitables.get("resourceHandles").remove(builder);
            this.resourceHandles.remove(builder);
         }

         return this;
      }
   }

   public AllocationResultFluent removeMatchingFromResourceHandles(Predicate predicate) {
      if (this.resourceHandles == null) {
         return this;
      } else {
         Iterator<ResourceHandleBuilder> each = this.resourceHandles.iterator();
         List visitables = this._visitables.get("resourceHandles");

         while(each.hasNext()) {
            ResourceHandleBuilder builder = (ResourceHandleBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResourceHandles() {
      return this.resourceHandles != null ? build(this.resourceHandles) : null;
   }

   public ResourceHandle buildResourceHandle(int index) {
      return ((ResourceHandleBuilder)this.resourceHandles.get(index)).build();
   }

   public ResourceHandle buildFirstResourceHandle() {
      return ((ResourceHandleBuilder)this.resourceHandles.get(0)).build();
   }

   public ResourceHandle buildLastResourceHandle() {
      return ((ResourceHandleBuilder)this.resourceHandles.get(this.resourceHandles.size() - 1)).build();
   }

   public ResourceHandle buildMatchingResourceHandle(Predicate predicate) {
      for(ResourceHandleBuilder item : this.resourceHandles) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceHandle(Predicate predicate) {
      for(ResourceHandleBuilder item : this.resourceHandles) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AllocationResultFluent withResourceHandles(List resourceHandles) {
      if (this.resourceHandles != null) {
         this._visitables.get("resourceHandles").clear();
      }

      if (resourceHandles != null) {
         this.resourceHandles = new ArrayList();

         for(ResourceHandle item : resourceHandles) {
            this.addToResourceHandles(item);
         }
      } else {
         this.resourceHandles = null;
      }

      return this;
   }

   public AllocationResultFluent withResourceHandles(ResourceHandle... resourceHandles) {
      if (this.resourceHandles != null) {
         this.resourceHandles.clear();
         this._visitables.remove("resourceHandles");
      }

      if (resourceHandles != null) {
         for(ResourceHandle item : resourceHandles) {
            this.addToResourceHandles(item);
         }
      }

      return this;
   }

   public boolean hasResourceHandles() {
      return this.resourceHandles != null && !this.resourceHandles.isEmpty();
   }

   public ResourceHandlesNested addNewResourceHandle() {
      return new ResourceHandlesNested(-1, (ResourceHandle)null);
   }

   public ResourceHandlesNested addNewResourceHandleLike(ResourceHandle item) {
      return new ResourceHandlesNested(-1, item);
   }

   public ResourceHandlesNested setNewResourceHandleLike(int index, ResourceHandle item) {
      return new ResourceHandlesNested(index, item);
   }

   public ResourceHandlesNested editResourceHandle(int index) {
      if (this.resourceHandles.size() <= index) {
         throw new RuntimeException("Can't edit resourceHandles. Index exceeds size.");
      } else {
         return this.setNewResourceHandleLike(index, this.buildResourceHandle(index));
      }
   }

   public ResourceHandlesNested editFirstResourceHandle() {
      if (this.resourceHandles.size() == 0) {
         throw new RuntimeException("Can't edit first resourceHandles. The list is empty.");
      } else {
         return this.setNewResourceHandleLike(0, this.buildResourceHandle(0));
      }
   }

   public ResourceHandlesNested editLastResourceHandle() {
      int index = this.resourceHandles.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resourceHandles. The list is empty.");
      } else {
         return this.setNewResourceHandleLike(index, this.buildResourceHandle(index));
      }
   }

   public ResourceHandlesNested editMatchingResourceHandle(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resourceHandles.size(); ++i) {
         if (predicate.test((ResourceHandleBuilder)this.resourceHandles.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resourceHandles. No match found.");
      } else {
         return this.setNewResourceHandleLike(index, this.buildResourceHandle(index));
      }
   }

   public Boolean getShareable() {
      return this.shareable;
   }

   public AllocationResultFluent withShareable(Boolean shareable) {
      this.shareable = shareable;
      return this;
   }

   public boolean hasShareable() {
      return this.shareable != null;
   }

   public AllocationResultFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AllocationResultFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AllocationResultFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AllocationResultFluent removeFromAdditionalProperties(Map map) {
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

   public AllocationResultFluent withAdditionalProperties(Map additionalProperties) {
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
            AllocationResultFluent that = (AllocationResultFluent)o;
            if (!Objects.equals(this.availableOnNodes, that.availableOnNodes)) {
               return false;
            } else if (!Objects.equals(this.resourceHandles, that.resourceHandles)) {
               return false;
            } else if (!Objects.equals(this.shareable, that.shareable)) {
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
      return Objects.hash(new Object[]{this.availableOnNodes, this.resourceHandles, this.shareable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.availableOnNodes != null) {
         sb.append("availableOnNodes:");
         sb.append(this.availableOnNodes + ",");
      }

      if (this.resourceHandles != null && !this.resourceHandles.isEmpty()) {
         sb.append("resourceHandles:");
         sb.append(this.resourceHandles + ",");
      }

      if (this.shareable != null) {
         sb.append("shareable:");
         sb.append(this.shareable + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public AllocationResultFluent withShareable() {
      return this.withShareable(true);
   }

   public class ResourceHandlesNested extends ResourceHandleFluent implements Nested {
      ResourceHandleBuilder builder;
      int index;

      ResourceHandlesNested(int index, ResourceHandle item) {
         this.index = index;
         this.builder = new ResourceHandleBuilder(this, item);
      }

      public Object and() {
         return AllocationResultFluent.this.setToResourceHandles(this.index, this.builder.build());
      }

      public Object endResourceHandle() {
         return this.and();
      }
   }
}
