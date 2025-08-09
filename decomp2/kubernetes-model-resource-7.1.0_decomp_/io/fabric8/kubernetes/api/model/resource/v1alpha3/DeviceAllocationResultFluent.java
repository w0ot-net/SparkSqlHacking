package io.fabric8.kubernetes.api.model.resource.v1alpha3;

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

public class DeviceAllocationResultFluent extends BaseFluent {
   private ArrayList config = new ArrayList();
   private ArrayList results = new ArrayList();
   private Map additionalProperties;

   public DeviceAllocationResultFluent() {
   }

   public DeviceAllocationResultFluent(DeviceAllocationResult instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceAllocationResult instance) {
      instance = instance != null ? instance : new DeviceAllocationResult();
      if (instance != null) {
         this.withConfig(instance.getConfig());
         this.withResults(instance.getResults());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DeviceAllocationResultFluent addToConfig(int index, DeviceAllocationConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").add(index, builder);
         this.config.add(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent setToConfig(int index, DeviceAllocationConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").set(index, builder);
         this.config.set(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent addToConfig(DeviceAllocationConfiguration... items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceAllocationConfiguration item : items) {
         DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent addAllToConfig(Collection items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceAllocationConfiguration item : items) {
         DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent removeFromConfig(DeviceAllocationConfiguration... items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceAllocationConfiguration item : items) {
            DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceAllocationResultFluent removeAllFromConfig(Collection items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceAllocationConfiguration item : items) {
            DeviceAllocationConfigurationBuilder builder = new DeviceAllocationConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceAllocationResultFluent removeMatchingFromConfig(Predicate predicate) {
      if (this.config == null) {
         return this;
      } else {
         Iterator<DeviceAllocationConfigurationBuilder> each = this.config.iterator();
         List visitables = this._visitables.get("config");

         while(each.hasNext()) {
            DeviceAllocationConfigurationBuilder builder = (DeviceAllocationConfigurationBuilder)each.next();
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

   public DeviceAllocationConfiguration buildConfig(int index) {
      return ((DeviceAllocationConfigurationBuilder)this.config.get(index)).build();
   }

   public DeviceAllocationConfiguration buildFirstConfig() {
      return ((DeviceAllocationConfigurationBuilder)this.config.get(0)).build();
   }

   public DeviceAllocationConfiguration buildLastConfig() {
      return ((DeviceAllocationConfigurationBuilder)this.config.get(this.config.size() - 1)).build();
   }

   public DeviceAllocationConfiguration buildMatchingConfig(Predicate predicate) {
      for(DeviceAllocationConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingConfig(Predicate predicate) {
      for(DeviceAllocationConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceAllocationResultFluent withConfig(List config) {
      if (this.config != null) {
         this._visitables.get("config").clear();
      }

      if (config != null) {
         this.config = new ArrayList();

         for(DeviceAllocationConfiguration item : config) {
            this.addToConfig(item);
         }
      } else {
         this.config = null;
      }

      return this;
   }

   public DeviceAllocationResultFluent withConfig(DeviceAllocationConfiguration... config) {
      if (this.config != null) {
         this.config.clear();
         this._visitables.remove("config");
      }

      if (config != null) {
         for(DeviceAllocationConfiguration item : config) {
            this.addToConfig(item);
         }
      }

      return this;
   }

   public boolean hasConfig() {
      return this.config != null && !this.config.isEmpty();
   }

   public ConfigNested addNewConfig() {
      return new ConfigNested(-1, (DeviceAllocationConfiguration)null);
   }

   public ConfigNested addNewConfigLike(DeviceAllocationConfiguration item) {
      return new ConfigNested(-1, item);
   }

   public ConfigNested setNewConfigLike(int index, DeviceAllocationConfiguration item) {
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
         if (predicate.test((DeviceAllocationConfigurationBuilder)this.config.get(i))) {
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

   public DeviceAllocationResultFluent addToResults(int index, DeviceRequestAllocationResult item) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
      if (index >= 0 && index < this.results.size()) {
         this._visitables.get("results").add(index, builder);
         this.results.add(index, builder);
      } else {
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent setToResults(int index, DeviceRequestAllocationResult item) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
      if (index >= 0 && index < this.results.size()) {
         this._visitables.get("results").set(index, builder);
         this.results.set(index, builder);
      } else {
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent addToResults(DeviceRequestAllocationResult... items) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      for(DeviceRequestAllocationResult item : items) {
         DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent addAllToResults(Collection items) {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      for(DeviceRequestAllocationResult item : items) {
         DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
         this._visitables.get("results").add(builder);
         this.results.add(builder);
      }

      return this;
   }

   public DeviceAllocationResultFluent removeFromResults(DeviceRequestAllocationResult... items) {
      if (this.results == null) {
         return this;
      } else {
         for(DeviceRequestAllocationResult item : items) {
            DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
            this._visitables.get("results").remove(builder);
            this.results.remove(builder);
         }

         return this;
      }
   }

   public DeviceAllocationResultFluent removeAllFromResults(Collection items) {
      if (this.results == null) {
         return this;
      } else {
         for(DeviceRequestAllocationResult item : items) {
            DeviceRequestAllocationResultBuilder builder = new DeviceRequestAllocationResultBuilder(item);
            this._visitables.get("results").remove(builder);
            this.results.remove(builder);
         }

         return this;
      }
   }

   public DeviceAllocationResultFluent removeMatchingFromResults(Predicate predicate) {
      if (this.results == null) {
         return this;
      } else {
         Iterator<DeviceRequestAllocationResultBuilder> each = this.results.iterator();
         List visitables = this._visitables.get("results");

         while(each.hasNext()) {
            DeviceRequestAllocationResultBuilder builder = (DeviceRequestAllocationResultBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResults() {
      return this.results != null ? build(this.results) : null;
   }

   public DeviceRequestAllocationResult buildResult(int index) {
      return ((DeviceRequestAllocationResultBuilder)this.results.get(index)).build();
   }

   public DeviceRequestAllocationResult buildFirstResult() {
      return ((DeviceRequestAllocationResultBuilder)this.results.get(0)).build();
   }

   public DeviceRequestAllocationResult buildLastResult() {
      return ((DeviceRequestAllocationResultBuilder)this.results.get(this.results.size() - 1)).build();
   }

   public DeviceRequestAllocationResult buildMatchingResult(Predicate predicate) {
      for(DeviceRequestAllocationResultBuilder item : this.results) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResult(Predicate predicate) {
      for(DeviceRequestAllocationResultBuilder item : this.results) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceAllocationResultFluent withResults(List results) {
      if (this.results != null) {
         this._visitables.get("results").clear();
      }

      if (results != null) {
         this.results = new ArrayList();

         for(DeviceRequestAllocationResult item : results) {
            this.addToResults(item);
         }
      } else {
         this.results = null;
      }

      return this;
   }

   public DeviceAllocationResultFluent withResults(DeviceRequestAllocationResult... results) {
      if (this.results != null) {
         this.results.clear();
         this._visitables.remove("results");
      }

      if (results != null) {
         for(DeviceRequestAllocationResult item : results) {
            this.addToResults(item);
         }
      }

      return this;
   }

   public boolean hasResults() {
      return this.results != null && !this.results.isEmpty();
   }

   public DeviceAllocationResultFluent addNewResult(Boolean adminAccess, String device, String driver, String pool, String request) {
      return this.addToResults(new DeviceRequestAllocationResult(adminAccess, device, driver, pool, request));
   }

   public ResultsNested addNewResult() {
      return new ResultsNested(-1, (DeviceRequestAllocationResult)null);
   }

   public ResultsNested addNewResultLike(DeviceRequestAllocationResult item) {
      return new ResultsNested(-1, item);
   }

   public ResultsNested setNewResultLike(int index, DeviceRequestAllocationResult item) {
      return new ResultsNested(index, item);
   }

   public ResultsNested editResult(int index) {
      if (this.results.size() <= index) {
         throw new RuntimeException("Can't edit results. Index exceeds size.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public ResultsNested editFirstResult() {
      if (this.results.size() == 0) {
         throw new RuntimeException("Can't edit first results. The list is empty.");
      } else {
         return this.setNewResultLike(0, this.buildResult(0));
      }
   }

   public ResultsNested editLastResult() {
      int index = this.results.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last results. The list is empty.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public ResultsNested editMatchingResult(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.results.size(); ++i) {
         if (predicate.test((DeviceRequestAllocationResultBuilder)this.results.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching results. No match found.");
      } else {
         return this.setNewResultLike(index, this.buildResult(index));
      }
   }

   public DeviceAllocationResultFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceAllocationResultFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceAllocationResultFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceAllocationResultFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceAllocationResultFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceAllocationResultFluent that = (DeviceAllocationResultFluent)o;
            if (!Objects.equals(this.config, that.config)) {
               return false;
            } else if (!Objects.equals(this.results, that.results)) {
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
      return Objects.hash(new Object[]{this.config, this.results, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.config != null && !this.config.isEmpty()) {
         sb.append("config:");
         sb.append(this.config + ",");
      }

      if (this.results != null && !this.results.isEmpty()) {
         sb.append("results:");
         sb.append(this.results + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigNested extends DeviceAllocationConfigurationFluent implements Nested {
      DeviceAllocationConfigurationBuilder builder;
      int index;

      ConfigNested(int index, DeviceAllocationConfiguration item) {
         this.index = index;
         this.builder = new DeviceAllocationConfigurationBuilder(this, item);
      }

      public Object and() {
         return DeviceAllocationResultFluent.this.setToConfig(this.index, this.builder.build());
      }

      public Object endConfig() {
         return this.and();
      }
   }

   public class ResultsNested extends DeviceRequestAllocationResultFluent implements Nested {
      DeviceRequestAllocationResultBuilder builder;
      int index;

      ResultsNested(int index, DeviceRequestAllocationResult item) {
         this.index = index;
         this.builder = new DeviceRequestAllocationResultBuilder(this, item);
      }

      public Object and() {
         return DeviceAllocationResultFluent.this.setToResults(this.index, this.builder.build());
      }

      public Object endResult() {
         return this.and();
      }
   }
}
