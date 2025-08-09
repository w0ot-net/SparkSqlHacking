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

public class PodSchedulingContextStatusFluent extends BaseFluent {
   private ArrayList resourceClaims = new ArrayList();
   private Map additionalProperties;

   public PodSchedulingContextStatusFluent() {
   }

   public PodSchedulingContextStatusFluent(PodSchedulingContextStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodSchedulingContextStatus instance) {
      instance = instance != null ? instance : new PodSchedulingContextStatus();
      if (instance != null) {
         this.withResourceClaims(instance.getResourceClaims());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodSchedulingContextStatusFluent addToResourceClaims(int index, ResourceClaimSchedulingStatus item) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
      if (index >= 0 && index < this.resourceClaims.size()) {
         this._visitables.get("resourceClaims").add(index, builder);
         this.resourceClaims.add(index, builder);
      } else {
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent setToResourceClaims(int index, ResourceClaimSchedulingStatus item) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
      if (index >= 0 && index < this.resourceClaims.size()) {
         this._visitables.get("resourceClaims").set(index, builder);
         this.resourceClaims.set(index, builder);
      } else {
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent addToResourceClaims(ResourceClaimSchedulingStatus... items) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      for(ResourceClaimSchedulingStatus item : items) {
         ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent addAllToResourceClaims(Collection items) {
      if (this.resourceClaims == null) {
         this.resourceClaims = new ArrayList();
      }

      for(ResourceClaimSchedulingStatus item : items) {
         ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
         this._visitables.get("resourceClaims").add(builder);
         this.resourceClaims.add(builder);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent removeFromResourceClaims(ResourceClaimSchedulingStatus... items) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         for(ResourceClaimSchedulingStatus item : items) {
            ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
            this._visitables.get("resourceClaims").remove(builder);
            this.resourceClaims.remove(builder);
         }

         return this;
      }
   }

   public PodSchedulingContextStatusFluent removeAllFromResourceClaims(Collection items) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         for(ResourceClaimSchedulingStatus item : items) {
            ResourceClaimSchedulingStatusBuilder builder = new ResourceClaimSchedulingStatusBuilder(item);
            this._visitables.get("resourceClaims").remove(builder);
            this.resourceClaims.remove(builder);
         }

         return this;
      }
   }

   public PodSchedulingContextStatusFluent removeMatchingFromResourceClaims(Predicate predicate) {
      if (this.resourceClaims == null) {
         return this;
      } else {
         Iterator<ResourceClaimSchedulingStatusBuilder> each = this.resourceClaims.iterator();
         List visitables = this._visitables.get("resourceClaims");

         while(each.hasNext()) {
            ResourceClaimSchedulingStatusBuilder builder = (ResourceClaimSchedulingStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResourceClaims() {
      return this.resourceClaims != null ? build(this.resourceClaims) : null;
   }

   public ResourceClaimSchedulingStatus buildResourceClaim(int index) {
      return ((ResourceClaimSchedulingStatusBuilder)this.resourceClaims.get(index)).build();
   }

   public ResourceClaimSchedulingStatus buildFirstResourceClaim() {
      return ((ResourceClaimSchedulingStatusBuilder)this.resourceClaims.get(0)).build();
   }

   public ResourceClaimSchedulingStatus buildLastResourceClaim() {
      return ((ResourceClaimSchedulingStatusBuilder)this.resourceClaims.get(this.resourceClaims.size() - 1)).build();
   }

   public ResourceClaimSchedulingStatus buildMatchingResourceClaim(Predicate predicate) {
      for(ResourceClaimSchedulingStatusBuilder item : this.resourceClaims) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceClaim(Predicate predicate) {
      for(ResourceClaimSchedulingStatusBuilder item : this.resourceClaims) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSchedulingContextStatusFluent withResourceClaims(List resourceClaims) {
      if (this.resourceClaims != null) {
         this._visitables.get("resourceClaims").clear();
      }

      if (resourceClaims != null) {
         this.resourceClaims = new ArrayList();

         for(ResourceClaimSchedulingStatus item : resourceClaims) {
            this.addToResourceClaims(item);
         }
      } else {
         this.resourceClaims = null;
      }

      return this;
   }

   public PodSchedulingContextStatusFluent withResourceClaims(ResourceClaimSchedulingStatus... resourceClaims) {
      if (this.resourceClaims != null) {
         this.resourceClaims.clear();
         this._visitables.remove("resourceClaims");
      }

      if (resourceClaims != null) {
         for(ResourceClaimSchedulingStatus item : resourceClaims) {
            this.addToResourceClaims(item);
         }
      }

      return this;
   }

   public boolean hasResourceClaims() {
      return this.resourceClaims != null && !this.resourceClaims.isEmpty();
   }

   public ResourceClaimsNested addNewResourceClaim() {
      return new ResourceClaimsNested(-1, (ResourceClaimSchedulingStatus)null);
   }

   public ResourceClaimsNested addNewResourceClaimLike(ResourceClaimSchedulingStatus item) {
      return new ResourceClaimsNested(-1, item);
   }

   public ResourceClaimsNested setNewResourceClaimLike(int index, ResourceClaimSchedulingStatus item) {
      return new ResourceClaimsNested(index, item);
   }

   public ResourceClaimsNested editResourceClaim(int index) {
      if (this.resourceClaims.size() <= index) {
         throw new RuntimeException("Can't edit resourceClaims. Index exceeds size.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public ResourceClaimsNested editFirstResourceClaim() {
      if (this.resourceClaims.size() == 0) {
         throw new RuntimeException("Can't edit first resourceClaims. The list is empty.");
      } else {
         return this.setNewResourceClaimLike(0, this.buildResourceClaim(0));
      }
   }

   public ResourceClaimsNested editLastResourceClaim() {
      int index = this.resourceClaims.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resourceClaims. The list is empty.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public ResourceClaimsNested editMatchingResourceClaim(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resourceClaims.size(); ++i) {
         if (predicate.test((ResourceClaimSchedulingStatusBuilder)this.resourceClaims.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resourceClaims. No match found.");
      } else {
         return this.setNewResourceClaimLike(index, this.buildResourceClaim(index));
      }
   }

   public PodSchedulingContextStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodSchedulingContextStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodSchedulingContextStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PodSchedulingContextStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PodSchedulingContextStatusFluent that = (PodSchedulingContextStatusFluent)o;
            if (!Objects.equals(this.resourceClaims, that.resourceClaims)) {
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
      return Objects.hash(new Object[]{this.resourceClaims, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.resourceClaims != null && !this.resourceClaims.isEmpty()) {
         sb.append("resourceClaims:");
         sb.append(this.resourceClaims + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ResourceClaimsNested extends ResourceClaimSchedulingStatusFluent implements Nested {
      ResourceClaimSchedulingStatusBuilder builder;
      int index;

      ResourceClaimsNested(int index, ResourceClaimSchedulingStatus item) {
         this.index = index;
         this.builder = new ResourceClaimSchedulingStatusBuilder(this, item);
      }

      public Object and() {
         return PodSchedulingContextStatusFluent.this.setToResourceClaims(this.index, this.builder.build());
      }

      public Object endResourceClaim() {
         return this.and();
      }
   }
}
