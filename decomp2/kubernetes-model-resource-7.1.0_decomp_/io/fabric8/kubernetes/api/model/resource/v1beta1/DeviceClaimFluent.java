package io.fabric8.kubernetes.api.model.resource.v1beta1;

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

public class DeviceClaimFluent extends BaseFluent {
   private ArrayList config = new ArrayList();
   private ArrayList constraints = new ArrayList();
   private ArrayList requests = new ArrayList();
   private Map additionalProperties;

   public DeviceClaimFluent() {
   }

   public DeviceClaimFluent(DeviceClaim instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceClaim instance) {
      instance = instance != null ? instance : new DeviceClaim();
      if (instance != null) {
         this.withConfig(instance.getConfig());
         this.withConstraints(instance.getConstraints());
         this.withRequests(instance.getRequests());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DeviceClaimFluent addToConfig(int index, DeviceClaimConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").add(index, builder);
         this.config.add(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent setToConfig(int index, DeviceClaimConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").set(index, builder);
         this.config.set(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addToConfig(DeviceClaimConfiguration... items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceClaimConfiguration item : items) {
         DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addAllToConfig(Collection items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceClaimConfiguration item : items) {
         DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent removeFromConfig(DeviceClaimConfiguration... items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceClaimConfiguration item : items) {
            DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeAllFromConfig(Collection items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceClaimConfiguration item : items) {
            DeviceClaimConfigurationBuilder builder = new DeviceClaimConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeMatchingFromConfig(Predicate predicate) {
      if (this.config == null) {
         return this;
      } else {
         Iterator<DeviceClaimConfigurationBuilder> each = this.config.iterator();
         List visitables = this._visitables.get("config");

         while(each.hasNext()) {
            DeviceClaimConfigurationBuilder builder = (DeviceClaimConfigurationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConfig() {
      return this.config != null ? build(this.config) : null;
   }

   public DeviceClaimConfiguration buildConfig(int index) {
      return ((DeviceClaimConfigurationBuilder)this.config.get(index)).build();
   }

   public DeviceClaimConfiguration buildFirstConfig() {
      return ((DeviceClaimConfigurationBuilder)this.config.get(0)).build();
   }

   public DeviceClaimConfiguration buildLastConfig() {
      return ((DeviceClaimConfigurationBuilder)this.config.get(this.config.size() - 1)).build();
   }

   public DeviceClaimConfiguration buildMatchingConfig(Predicate predicate) {
      for(DeviceClaimConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingConfig(Predicate predicate) {
      for(DeviceClaimConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClaimFluent withConfig(List config) {
      if (this.config != null) {
         this._visitables.get("config").clear();
      }

      if (config != null) {
         this.config = new ArrayList();

         for(DeviceClaimConfiguration item : config) {
            this.addToConfig(item);
         }
      } else {
         this.config = null;
      }

      return this;
   }

   public DeviceClaimFluent withConfig(DeviceClaimConfiguration... config) {
      if (this.config != null) {
         this.config.clear();
         this._visitables.remove("config");
      }

      if (config != null) {
         for(DeviceClaimConfiguration item : config) {
            this.addToConfig(item);
         }
      }

      return this;
   }

   public boolean hasConfig() {
      return this.config != null && !this.config.isEmpty();
   }

   public ConfigNested addNewConfig() {
      return new ConfigNested(-1, (DeviceClaimConfiguration)null);
   }

   public ConfigNested addNewConfigLike(DeviceClaimConfiguration item) {
      return new ConfigNested(-1, item);
   }

   public ConfigNested setNewConfigLike(int index, DeviceClaimConfiguration item) {
      return new ConfigNested(index, item);
   }

   public ConfigNested editConfig(int index) {
      if (this.config.size() <= index) {
         throw new RuntimeException("Can't edit config. Index exceeds size.");
      } else {
         return this.setNewConfigLike(index, this.buildConfig(index));
      }
   }

   public ConfigNested editFirstConfig() {
      if (this.config.size() == 0) {
         throw new RuntimeException("Can't edit first config. The list is empty.");
      } else {
         return this.setNewConfigLike(0, this.buildConfig(0));
      }
   }

   public ConfigNested editLastConfig() {
      int index = this.config.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last config. The list is empty.");
      } else {
         return this.setNewConfigLike(index, this.buildConfig(index));
      }
   }

   public ConfigNested editMatchingConfig(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.config.size(); ++i) {
         if (predicate.test((DeviceClaimConfigurationBuilder)this.config.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching config. No match found.");
      } else {
         return this.setNewConfigLike(index, this.buildConfig(index));
      }
   }

   public DeviceClaimFluent addToConstraints(int index, DeviceConstraint item) {
      if (this.constraints == null) {
         this.constraints = new ArrayList();
      }

      DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
      if (index >= 0 && index < this.constraints.size()) {
         this._visitables.get("constraints").add(index, builder);
         this.constraints.add(index, builder);
      } else {
         this._visitables.get("constraints").add(builder);
         this.constraints.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent setToConstraints(int index, DeviceConstraint item) {
      if (this.constraints == null) {
         this.constraints = new ArrayList();
      }

      DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
      if (index >= 0 && index < this.constraints.size()) {
         this._visitables.get("constraints").set(index, builder);
         this.constraints.set(index, builder);
      } else {
         this._visitables.get("constraints").add(builder);
         this.constraints.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addToConstraints(DeviceConstraint... items) {
      if (this.constraints == null) {
         this.constraints = new ArrayList();
      }

      for(DeviceConstraint item : items) {
         DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
         this._visitables.get("constraints").add(builder);
         this.constraints.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addAllToConstraints(Collection items) {
      if (this.constraints == null) {
         this.constraints = new ArrayList();
      }

      for(DeviceConstraint item : items) {
         DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
         this._visitables.get("constraints").add(builder);
         this.constraints.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent removeFromConstraints(DeviceConstraint... items) {
      if (this.constraints == null) {
         return this;
      } else {
         for(DeviceConstraint item : items) {
            DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
            this._visitables.get("constraints").remove(builder);
            this.constraints.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeAllFromConstraints(Collection items) {
      if (this.constraints == null) {
         return this;
      } else {
         for(DeviceConstraint item : items) {
            DeviceConstraintBuilder builder = new DeviceConstraintBuilder(item);
            this._visitables.get("constraints").remove(builder);
            this.constraints.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeMatchingFromConstraints(Predicate predicate) {
      if (this.constraints == null) {
         return this;
      } else {
         Iterator<DeviceConstraintBuilder> each = this.constraints.iterator();
         List visitables = this._visitables.get("constraints");

         while(each.hasNext()) {
            DeviceConstraintBuilder builder = (DeviceConstraintBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConstraints() {
      return this.constraints != null ? build(this.constraints) : null;
   }

   public DeviceConstraint buildConstraint(int index) {
      return ((DeviceConstraintBuilder)this.constraints.get(index)).build();
   }

   public DeviceConstraint buildFirstConstraint() {
      return ((DeviceConstraintBuilder)this.constraints.get(0)).build();
   }

   public DeviceConstraint buildLastConstraint() {
      return ((DeviceConstraintBuilder)this.constraints.get(this.constraints.size() - 1)).build();
   }

   public DeviceConstraint buildMatchingConstraint(Predicate predicate) {
      for(DeviceConstraintBuilder item : this.constraints) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingConstraint(Predicate predicate) {
      for(DeviceConstraintBuilder item : this.constraints) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClaimFluent withConstraints(List constraints) {
      if (this.constraints != null) {
         this._visitables.get("constraints").clear();
      }

      if (constraints != null) {
         this.constraints = new ArrayList();

         for(DeviceConstraint item : constraints) {
            this.addToConstraints(item);
         }
      } else {
         this.constraints = null;
      }

      return this;
   }

   public DeviceClaimFluent withConstraints(DeviceConstraint... constraints) {
      if (this.constraints != null) {
         this.constraints.clear();
         this._visitables.remove("constraints");
      }

      if (constraints != null) {
         for(DeviceConstraint item : constraints) {
            this.addToConstraints(item);
         }
      }

      return this;
   }

   public boolean hasConstraints() {
      return this.constraints != null && !this.constraints.isEmpty();
   }

   public ConstraintsNested addNewConstraint() {
      return new ConstraintsNested(-1, (DeviceConstraint)null);
   }

   public ConstraintsNested addNewConstraintLike(DeviceConstraint item) {
      return new ConstraintsNested(-1, item);
   }

   public ConstraintsNested setNewConstraintLike(int index, DeviceConstraint item) {
      return new ConstraintsNested(index, item);
   }

   public ConstraintsNested editConstraint(int index) {
      if (this.constraints.size() <= index) {
         throw new RuntimeException("Can't edit constraints. Index exceeds size.");
      } else {
         return this.setNewConstraintLike(index, this.buildConstraint(index));
      }
   }

   public ConstraintsNested editFirstConstraint() {
      if (this.constraints.size() == 0) {
         throw new RuntimeException("Can't edit first constraints. The list is empty.");
      } else {
         return this.setNewConstraintLike(0, this.buildConstraint(0));
      }
   }

   public ConstraintsNested editLastConstraint() {
      int index = this.constraints.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last constraints. The list is empty.");
      } else {
         return this.setNewConstraintLike(index, this.buildConstraint(index));
      }
   }

   public ConstraintsNested editMatchingConstraint(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.constraints.size(); ++i) {
         if (predicate.test((DeviceConstraintBuilder)this.constraints.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching constraints. No match found.");
      } else {
         return this.setNewConstraintLike(index, this.buildConstraint(index));
      }
   }

   public DeviceClaimFluent addToRequests(int index, DeviceRequest item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
      if (index >= 0 && index < this.requests.size()) {
         this._visitables.get("requests").add(index, builder);
         this.requests.add(index, builder);
      } else {
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent setToRequests(int index, DeviceRequest item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
      if (index >= 0 && index < this.requests.size()) {
         this._visitables.get("requests").set(index, builder);
         this.requests.set(index, builder);
      } else {
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addToRequests(DeviceRequest... items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(DeviceRequest item : items) {
         DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent addAllToRequests(Collection items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(DeviceRequest item : items) {
         DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
         this._visitables.get("requests").add(builder);
         this.requests.add(builder);
      }

      return this;
   }

   public DeviceClaimFluent removeFromRequests(DeviceRequest... items) {
      if (this.requests == null) {
         return this;
      } else {
         for(DeviceRequest item : items) {
            DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
            this._visitables.get("requests").remove(builder);
            this.requests.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeAllFromRequests(Collection items) {
      if (this.requests == null) {
         return this;
      } else {
         for(DeviceRequest item : items) {
            DeviceRequestBuilder builder = new DeviceRequestBuilder(item);
            this._visitables.get("requests").remove(builder);
            this.requests.remove(builder);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeMatchingFromRequests(Predicate predicate) {
      if (this.requests == null) {
         return this;
      } else {
         Iterator<DeviceRequestBuilder> each = this.requests.iterator();
         List visitables = this._visitables.get("requests");

         while(each.hasNext()) {
            DeviceRequestBuilder builder = (DeviceRequestBuilder)each.next();
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

   public DeviceRequest buildRequest(int index) {
      return ((DeviceRequestBuilder)this.requests.get(index)).build();
   }

   public DeviceRequest buildFirstRequest() {
      return ((DeviceRequestBuilder)this.requests.get(0)).build();
   }

   public DeviceRequest buildLastRequest() {
      return ((DeviceRequestBuilder)this.requests.get(this.requests.size() - 1)).build();
   }

   public DeviceRequest buildMatchingRequest(Predicate predicate) {
      for(DeviceRequestBuilder item : this.requests) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRequest(Predicate predicate) {
      for(DeviceRequestBuilder item : this.requests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClaimFluent withRequests(List requests) {
      if (this.requests != null) {
         this._visitables.get("requests").clear();
      }

      if (requests != null) {
         this.requests = new ArrayList();

         for(DeviceRequest item : requests) {
            this.addToRequests(item);
         }
      } else {
         this.requests = null;
      }

      return this;
   }

   public DeviceClaimFluent withRequests(DeviceRequest... requests) {
      if (this.requests != null) {
         this.requests.clear();
         this._visitables.remove("requests");
      }

      if (requests != null) {
         for(DeviceRequest item : requests) {
            this.addToRequests(item);
         }
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null && !this.requests.isEmpty();
   }

   public RequestsNested addNewRequest() {
      return new RequestsNested(-1, (DeviceRequest)null);
   }

   public RequestsNested addNewRequestLike(DeviceRequest item) {
      return new RequestsNested(-1, item);
   }

   public RequestsNested setNewRequestLike(int index, DeviceRequest item) {
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
         if (predicate.test((DeviceRequestBuilder)this.requests.get(i))) {
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

   public DeviceClaimFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceClaimFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceClaimFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceClaimFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceClaimFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceClaimFluent that = (DeviceClaimFluent)o;
            if (!Objects.equals(this.config, that.config)) {
               return false;
            } else if (!Objects.equals(this.constraints, that.constraints)) {
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
      return Objects.hash(new Object[]{this.config, this.constraints, this.requests, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.config != null && !this.config.isEmpty()) {
         sb.append("config:");
         sb.append(this.config + ",");
      }

      if (this.constraints != null && !this.constraints.isEmpty()) {
         sb.append("constraints:");
         sb.append(this.constraints + ",");
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

   public class ConfigNested extends DeviceClaimConfigurationFluent implements Nested {
      DeviceClaimConfigurationBuilder builder;
      int index;

      ConfigNested(int index, DeviceClaimConfiguration item) {
         this.index = index;
         this.builder = new DeviceClaimConfigurationBuilder(this, item);
      }

      public Object and() {
         return DeviceClaimFluent.this.setToConfig(this.index, this.builder.build());
      }

      public Object endConfig() {
         return this.and();
      }
   }

   public class ConstraintsNested extends DeviceConstraintFluent implements Nested {
      DeviceConstraintBuilder builder;
      int index;

      ConstraintsNested(int index, DeviceConstraint item) {
         this.index = index;
         this.builder = new DeviceConstraintBuilder(this, item);
      }

      public Object and() {
         return DeviceClaimFluent.this.setToConstraints(this.index, this.builder.build());
      }

      public Object endConstraint() {
         return this.and();
      }
   }

   public class RequestsNested extends DeviceRequestFluent implements Nested {
      DeviceRequestBuilder builder;
      int index;

      RequestsNested(int index, DeviceRequest item) {
         this.index = index;
         this.builder = new DeviceRequestBuilder(this, item);
      }

      public Object and() {
         return DeviceClaimFluent.this.setToRequests(this.index, this.builder.build());
      }

      public Object endRequest() {
         return this.and();
      }
   }
}
