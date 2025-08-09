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

public class ResourceRequirementsFluent extends BaseFluent {
   private ArrayList claims = new ArrayList();
   private Map limits;
   private Map requests;
   private Map additionalProperties;

   public ResourceRequirementsFluent() {
   }

   public ResourceRequirementsFluent(ResourceRequirements instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceRequirements instance) {
      instance = instance != null ? instance : new ResourceRequirements();
      if (instance != null) {
         this.withClaims(instance.getClaims());
         this.withLimits(instance.getLimits());
         this.withRequests(instance.getRequests());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ResourceRequirementsFluent addToClaims(int index, ResourceClaim item) {
      if (this.claims == null) {
         this.claims = new ArrayList();
      }

      ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
      if (index >= 0 && index < this.claims.size()) {
         this._visitables.get("claims").add(index, builder);
         this.claims.add(index, builder);
      } else {
         this._visitables.get("claims").add(builder);
         this.claims.add(builder);
      }

      return this;
   }

   public ResourceRequirementsFluent setToClaims(int index, ResourceClaim item) {
      if (this.claims == null) {
         this.claims = new ArrayList();
      }

      ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
      if (index >= 0 && index < this.claims.size()) {
         this._visitables.get("claims").set(index, builder);
         this.claims.set(index, builder);
      } else {
         this._visitables.get("claims").add(builder);
         this.claims.add(builder);
      }

      return this;
   }

   public ResourceRequirementsFluent addToClaims(ResourceClaim... items) {
      if (this.claims == null) {
         this.claims = new ArrayList();
      }

      for(ResourceClaim item : items) {
         ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
         this._visitables.get("claims").add(builder);
         this.claims.add(builder);
      }

      return this;
   }

   public ResourceRequirementsFluent addAllToClaims(Collection items) {
      if (this.claims == null) {
         this.claims = new ArrayList();
      }

      for(ResourceClaim item : items) {
         ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
         this._visitables.get("claims").add(builder);
         this.claims.add(builder);
      }

      return this;
   }

   public ResourceRequirementsFluent removeFromClaims(ResourceClaim... items) {
      if (this.claims == null) {
         return this;
      } else {
         for(ResourceClaim item : items) {
            ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
            this._visitables.get("claims").remove(builder);
            this.claims.remove(builder);
         }

         return this;
      }
   }

   public ResourceRequirementsFluent removeAllFromClaims(Collection items) {
      if (this.claims == null) {
         return this;
      } else {
         for(ResourceClaim item : items) {
            ResourceClaimBuilder builder = new ResourceClaimBuilder(item);
            this._visitables.get("claims").remove(builder);
            this.claims.remove(builder);
         }

         return this;
      }
   }

   public ResourceRequirementsFluent removeMatchingFromClaims(Predicate predicate) {
      if (this.claims == null) {
         return this;
      } else {
         Iterator<ResourceClaimBuilder> each = this.claims.iterator();
         List visitables = this._visitables.get("claims");

         while(each.hasNext()) {
            ResourceClaimBuilder builder = (ResourceClaimBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildClaims() {
      return this.claims != null ? build(this.claims) : null;
   }

   public ResourceClaim buildClaim(int index) {
      return ((ResourceClaimBuilder)this.claims.get(index)).build();
   }

   public ResourceClaim buildFirstClaim() {
      return ((ResourceClaimBuilder)this.claims.get(0)).build();
   }

   public ResourceClaim buildLastClaim() {
      return ((ResourceClaimBuilder)this.claims.get(this.claims.size() - 1)).build();
   }

   public ResourceClaim buildMatchingClaim(Predicate predicate) {
      for(ResourceClaimBuilder item : this.claims) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingClaim(Predicate predicate) {
      for(ResourceClaimBuilder item : this.claims) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceRequirementsFluent withClaims(List claims) {
      if (this.claims != null) {
         this._visitables.get("claims").clear();
      }

      if (claims != null) {
         this.claims = new ArrayList();

         for(ResourceClaim item : claims) {
            this.addToClaims(item);
         }
      } else {
         this.claims = null;
      }

      return this;
   }

   public ResourceRequirementsFluent withClaims(ResourceClaim... claims) {
      if (this.claims != null) {
         this.claims.clear();
         this._visitables.remove("claims");
      }

      if (claims != null) {
         for(ResourceClaim item : claims) {
            this.addToClaims(item);
         }
      }

      return this;
   }

   public boolean hasClaims() {
      return this.claims != null && !this.claims.isEmpty();
   }

   public ResourceRequirementsFluent addNewClaim(String name, String request) {
      return this.addToClaims(new ResourceClaim(name, request));
   }

   public ClaimsNested addNewClaim() {
      return new ClaimsNested(-1, (ResourceClaim)null);
   }

   public ClaimsNested addNewClaimLike(ResourceClaim item) {
      return new ClaimsNested(-1, item);
   }

   public ClaimsNested setNewClaimLike(int index, ResourceClaim item) {
      return new ClaimsNested(index, item);
   }

   public ClaimsNested editClaim(int index) {
      if (this.claims.size() <= index) {
         throw new RuntimeException("Can't edit claims. Index exceeds size.");
      } else {
         return this.setNewClaimLike(index, this.buildClaim(index));
      }
   }

   public ClaimsNested editFirstClaim() {
      if (this.claims.size() == 0) {
         throw new RuntimeException("Can't edit first claims. The list is empty.");
      } else {
         return this.setNewClaimLike(0, this.buildClaim(0));
      }
   }

   public ClaimsNested editLastClaim() {
      int index = this.claims.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last claims. The list is empty.");
      } else {
         return this.setNewClaimLike(index, this.buildClaim(index));
      }
   }

   public ClaimsNested editMatchingClaim(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.claims.size(); ++i) {
         if (predicate.test((ResourceClaimBuilder)this.claims.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching claims. No match found.");
      } else {
         return this.setNewClaimLike(index, this.buildClaim(index));
      }
   }

   public ResourceRequirementsFluent addToLimits(String key, Quantity value) {
      if (this.limits == null && key != null && value != null) {
         this.limits = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.limits.put(key, value);
      }

      return this;
   }

   public ResourceRequirementsFluent addToLimits(Map map) {
      if (this.limits == null && map != null) {
         this.limits = new LinkedHashMap();
      }

      if (map != null) {
         this.limits.putAll(map);
      }

      return this;
   }

   public ResourceRequirementsFluent removeFromLimits(String key) {
      if (this.limits == null) {
         return this;
      } else {
         if (key != null && this.limits != null) {
            this.limits.remove(key);
         }

         return this;
      }
   }

   public ResourceRequirementsFluent removeFromLimits(Map map) {
      if (this.limits == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.limits != null) {
                  this.limits.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLimits() {
      return this.limits;
   }

   public ResourceRequirementsFluent withLimits(Map limits) {
      if (limits == null) {
         this.limits = null;
      } else {
         this.limits = new LinkedHashMap(limits);
      }

      return this;
   }

   public boolean hasLimits() {
      return this.limits != null;
   }

   public ResourceRequirementsFluent addToRequests(String key, Quantity value) {
      if (this.requests == null && key != null && value != null) {
         this.requests = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.requests.put(key, value);
      }

      return this;
   }

   public ResourceRequirementsFluent addToRequests(Map map) {
      if (this.requests == null && map != null) {
         this.requests = new LinkedHashMap();
      }

      if (map != null) {
         this.requests.putAll(map);
      }

      return this;
   }

   public ResourceRequirementsFluent removeFromRequests(String key) {
      if (this.requests == null) {
         return this;
      } else {
         if (key != null && this.requests != null) {
            this.requests.remove(key);
         }

         return this;
      }
   }

   public ResourceRequirementsFluent removeFromRequests(Map map) {
      if (this.requests == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.requests != null) {
                  this.requests.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getRequests() {
      return this.requests;
   }

   public ResourceRequirementsFluent withRequests(Map requests) {
      if (requests == null) {
         this.requests = null;
      } else {
         this.requests = new LinkedHashMap(requests);
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null;
   }

   public ResourceRequirementsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceRequirementsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceRequirementsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceRequirementsFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceRequirementsFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceRequirementsFluent that = (ResourceRequirementsFluent)o;
            if (!Objects.equals(this.claims, that.claims)) {
               return false;
            } else if (!Objects.equals(this.limits, that.limits)) {
               return false;
            } else if (!Objects.equals(this.requests, that.requests)) {
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
      return Objects.hash(new Object[]{this.claims, this.limits, this.requests, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.claims != null && !this.claims.isEmpty()) {
         sb.append("claims:");
         sb.append(this.claims + ",");
      }

      if (this.limits != null && !this.limits.isEmpty()) {
         sb.append("limits:");
         sb.append(this.limits + ",");
      }

      if (this.requests != null && !this.requests.isEmpty()) {
         sb.append("requests:");
         sb.append(this.requests + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClaimsNested extends ResourceClaimFluent implements Nested {
      ResourceClaimBuilder builder;
      int index;

      ClaimsNested(int index, ResourceClaim item) {
         this.index = index;
         this.builder = new ResourceClaimBuilder(this, item);
      }

      public Object and() {
         return ResourceRequirementsFluent.this.setToClaims(this.index, this.builder.build());
      }

      public Object endClaim() {
         return this.and();
      }
   }
}
