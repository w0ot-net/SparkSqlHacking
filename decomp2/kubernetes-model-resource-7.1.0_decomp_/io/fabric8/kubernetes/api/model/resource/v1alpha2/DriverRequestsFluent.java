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

public class DriverRequestsFluent extends BaseFluent {
   private String driverName;
   private ArrayList requests = new ArrayList();
   private Object vendorParameters;
   private Map additionalProperties;

   public DriverRequestsFluent() {
   }

   public DriverRequestsFluent(DriverRequests instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DriverRequests instance) {
      instance = instance != null ? instance : new DriverRequests();
      if (instance != null) {
         this.withDriverName(instance.getDriverName());
         this.withRequests(instance.getRequests());
         this.withVendorParameters(instance.getVendorParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriverName() {
      return this.driverName;
   }

   public DriverRequestsFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public DriverRequestsFluent addToRequests(int index, ResourceRequest item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
      if (index >= 0 && index < this.requests.size()) {
         this._visitables.get("requests").add(index, builder);
         this.requests.add(index, builder);
      } else {
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DriverRequestsFluent setToRequests(int index, ResourceRequest item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
      if (index >= 0 && index < this.requests.size()) {
         this._visitables.get("requests").set(index, builder);
         this.requests.set(index, builder);
      } else {
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DriverRequestsFluent addToRequests(ResourceRequest... items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(ResourceRequest item : items) {
         ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DriverRequestsFluent addAllToRequests(Collection items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(ResourceRequest item : items) {
         ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DriverRequestsFluent removeFromRequests(ResourceRequest... items) {
      if (this.requests == null) {
         return this;
      } else {
         for(ResourceRequest item : items) {
            ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
            this._visitables.get("requests").remove(builder);
            this.requests.remove(builder);
         }

         return this;
      }
   }

   public DriverRequestsFluent removeAllFromRequests(Collection items) {
      if (this.requests == null) {
         return this;
      } else {
         for(ResourceRequest item : items) {
            ResourceRequestBuilder builder = new ResourceRequestBuilder(item);
            this._visitables.get("requests").remove(builder);
            this.requests.remove(builder);
         }

         return this;
      }
   }

   public DriverRequestsFluent removeMatchingFromRequests(Predicate predicate) {
      if (this.requests == null) {
         return this;
      } else {
         Iterator<ResourceRequestBuilder> each = this.requests.iterator();
         List visitables = this._visitables.get("requests");

         while(each.hasNext()) {
            ResourceRequestBuilder builder = (ResourceRequestBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRequests() {
      return this.requests != null ? build(this.requests) : null;
   }

   public ResourceRequest buildRequest(int index) {
      return ((ResourceRequestBuilder)this.requests.get(index)).build();
   }

   public ResourceRequest buildFirstRequest() {
      return ((ResourceRequestBuilder)this.requests.get(0)).build();
   }

   public ResourceRequest buildLastRequest() {
      return ((ResourceRequestBuilder)this.requests.get(this.requests.size() - 1)).build();
   }

   public ResourceRequest buildMatchingRequest(Predicate predicate) {
      for(ResourceRequestBuilder item : this.requests) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRequest(Predicate predicate) {
      for(ResourceRequestBuilder item : this.requests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DriverRequestsFluent withRequests(List requests) {
      if (this.requests != null) {
         this._visitables.get("requests").clear();
      }

      if (requests != null) {
         this.requests = new ArrayList();

         for(ResourceRequest item : requests) {
            this.addToRequests(item);
         }
      } else {
         this.requests = null;
      }

      return this;
   }

   public DriverRequestsFluent withRequests(ResourceRequest... requests) {
      if (this.requests != null) {
         this.requests.clear();
         this._visitables.remove("requests");
      }

      if (requests != null) {
         for(ResourceRequest item : requests) {
            this.addToRequests(item);
         }
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null && !this.requests.isEmpty();
   }

   public RequestsNested addNewRequest() {
      return new RequestsNested(-1, (ResourceRequest)null);
   }

   public RequestsNested addNewRequestLike(ResourceRequest item) {
      return new RequestsNested(-1, item);
   }

   public RequestsNested setNewRequestLike(int index, ResourceRequest item) {
      return new RequestsNested(index, item);
   }

   public RequestsNested editRequest(int index) {
      if (this.requests.size() <= index) {
         throw new RuntimeException("Can't edit requests. Index exceeds size.");
      } else {
         return this.setNewRequestLike(index, this.buildRequest(index));
      }
   }

   public RequestsNested editFirstRequest() {
      if (this.requests.size() == 0) {
         throw new RuntimeException("Can't edit first requests. The list is empty.");
      } else {
         return this.setNewRequestLike(0, this.buildRequest(0));
      }
   }

   public RequestsNested editLastRequest() {
      int index = this.requests.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last requests. The list is empty.");
      } else {
         return this.setNewRequestLike(index, this.buildRequest(index));
      }
   }

   public RequestsNested editMatchingRequest(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.requests.size(); ++i) {
         if (predicate.test((ResourceRequestBuilder)this.requests.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching requests. No match found.");
      } else {
         return this.setNewRequestLike(index, this.buildRequest(index));
      }
   }

   public Object getVendorParameters() {
      return this.vendorParameters;
   }

   public DriverRequestsFluent withVendorParameters(Object vendorParameters) {
      this.vendorParameters = vendorParameters;
      return this;
   }

   public boolean hasVendorParameters() {
      return this.vendorParameters != null;
   }

   public DriverRequestsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DriverRequestsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DriverRequestsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DriverRequestsFluent removeFromAdditionalProperties(Map map) {
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

   public DriverRequestsFluent withAdditionalProperties(Map additionalProperties) {
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
            DriverRequestsFluent that = (DriverRequestsFluent)o;
            if (!Objects.equals(this.driverName, that.driverName)) {
               return false;
            } else if (!Objects.equals(this.requests, that.requests)) {
               return false;
            } else if (!Objects.equals(this.vendorParameters, that.vendorParameters)) {
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
      return Objects.hash(new Object[]{this.driverName, this.requests, this.vendorParameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.driverName != null) {
         sb.append("driverName:");
         sb.append(this.driverName + ",");
      }

      if (this.requests != null && !this.requests.isEmpty()) {
         sb.append("requests:");
         sb.append(this.requests + ",");
      }

      if (this.vendorParameters != null) {
         sb.append("vendorParameters:");
         sb.append(this.vendorParameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RequestsNested extends ResourceRequestFluent implements Nested {
      ResourceRequestBuilder builder;
      int index;

      RequestsNested(int index, ResourceRequest item) {
         this.index = index;
         this.builder = new ResourceRequestBuilder(this, item);
      }

      public Object and() {
         return DriverRequestsFluent.this.setToRequests(this.index, this.builder.build());
      }

      public Object endRequest() {
         return this.and();
      }
   }
}
