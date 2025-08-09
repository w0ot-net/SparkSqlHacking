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

public class DeviceClassSpecFluent extends BaseFluent {
   private ArrayList config = new ArrayList();
   private ArrayList selectors = new ArrayList();
   private Map additionalProperties;

   public DeviceClassSpecFluent() {
   }

   public DeviceClassSpecFluent(DeviceClassSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceClassSpec instance) {
      instance = instance != null ? instance : new DeviceClassSpec();
      if (instance != null) {
         this.withConfig(instance.getConfig());
         this.withSelectors(instance.getSelectors());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DeviceClassSpecFluent addToConfig(int index, DeviceClassConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").add(index, builder);
         this.config.add(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent setToConfig(int index, DeviceClassConfiguration item) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
      if (index >= 0 && index < this.config.size()) {
         this._visitables.get("config").set(index, builder);
         this.config.set(index, builder);
      } else {
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent addToConfig(DeviceClassConfiguration... items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceClassConfiguration item : items) {
         DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent addAllToConfig(Collection items) {
      if (this.config == null) {
         this.config = new ArrayList();
      }

      for(DeviceClassConfiguration item : items) {
         DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
         this._visitables.get("config").add(builder);
         this.config.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent removeFromConfig(DeviceClassConfiguration... items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceClassConfiguration item : items) {
            DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceClassSpecFluent removeAllFromConfig(Collection items) {
      if (this.config == null) {
         return this;
      } else {
         for(DeviceClassConfiguration item : items) {
            DeviceClassConfigurationBuilder builder = new DeviceClassConfigurationBuilder(item);
            this._visitables.get("config").remove(builder);
            this.config.remove(builder);
         }

         return this;
      }
   }

   public DeviceClassSpecFluent removeMatchingFromConfig(Predicate predicate) {
      if (this.config == null) {
         return this;
      } else {
         Iterator<DeviceClassConfigurationBuilder> each = this.config.iterator();
         List visitables = this._visitables.get("config");

         while(each.hasNext()) {
            DeviceClassConfigurationBuilder builder = (DeviceClassConfigurationBuilder)each.next();
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

   public DeviceClassConfiguration buildConfig(int index) {
      return ((DeviceClassConfigurationBuilder)this.config.get(index)).build();
   }

   public DeviceClassConfiguration buildFirstConfig() {
      return ((DeviceClassConfigurationBuilder)this.config.get(0)).build();
   }

   public DeviceClassConfiguration buildLastConfig() {
      return ((DeviceClassConfigurationBuilder)this.config.get(this.config.size() - 1)).build();
   }

   public DeviceClassConfiguration buildMatchingConfig(Predicate predicate) {
      for(DeviceClassConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingConfig(Predicate predicate) {
      for(DeviceClassConfigurationBuilder item : this.config) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClassSpecFluent withConfig(List config) {
      if (this.config != null) {
         this._visitables.get("config").clear();
      }

      if (config != null) {
         this.config = new ArrayList();

         for(DeviceClassConfiguration item : config) {
            this.addToConfig(item);
         }
      } else {
         this.config = null;
      }

      return this;
   }

   public DeviceClassSpecFluent withConfig(DeviceClassConfiguration... config) {
      if (this.config != null) {
         this.config.clear();
         this._visitables.remove("config");
      }

      if (config != null) {
         for(DeviceClassConfiguration item : config) {
            this.addToConfig(item);
         }
      }

      return this;
   }

   public boolean hasConfig() {
      return this.config != null && !this.config.isEmpty();
   }

   public ConfigNested addNewConfig() {
      return new ConfigNested(-1, (DeviceClassConfiguration)null);
   }

   public ConfigNested addNewConfigLike(DeviceClassConfiguration item) {
      return new ConfigNested(-1, item);
   }

   public ConfigNested setNewConfigLike(int index, DeviceClassConfiguration item) {
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
         if (predicate.test((DeviceClassConfigurationBuilder)this.config.get(i))) {
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

   public DeviceClassSpecFluent addToSelectors(int index, DeviceSelector item) {
      if (this.selectors == null) {
         this.selectors = new ArrayList();
      }

      DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
      if (index >= 0 && index < this.selectors.size()) {
         this._visitables.get("selectors").add(index, builder);
         this.selectors.add(index, builder);
      } else {
         this._visitables.get("selectors").add(builder);
         this.selectors.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent setToSelectors(int index, DeviceSelector item) {
      if (this.selectors == null) {
         this.selectors = new ArrayList();
      }

      DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
      if (index >= 0 && index < this.selectors.size()) {
         this._visitables.get("selectors").set(index, builder);
         this.selectors.set(index, builder);
      } else {
         this._visitables.get("selectors").add(builder);
         this.selectors.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent addToSelectors(DeviceSelector... items) {
      if (this.selectors == null) {
         this.selectors = new ArrayList();
      }

      for(DeviceSelector item : items) {
         DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
         this._visitables.get("selectors").add(builder);
         this.selectors.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent addAllToSelectors(Collection items) {
      if (this.selectors == null) {
         this.selectors = new ArrayList();
      }

      for(DeviceSelector item : items) {
         DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
         this._visitables.get("selectors").add(builder);
         this.selectors.add(builder);
      }

      return this;
   }

   public DeviceClassSpecFluent removeFromSelectors(DeviceSelector... items) {
      if (this.selectors == null) {
         return this;
      } else {
         for(DeviceSelector item : items) {
            DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
            this._visitables.get("selectors").remove(builder);
            this.selectors.remove(builder);
         }

         return this;
      }
   }

   public DeviceClassSpecFluent removeAllFromSelectors(Collection items) {
      if (this.selectors == null) {
         return this;
      } else {
         for(DeviceSelector item : items) {
            DeviceSelectorBuilder builder = new DeviceSelectorBuilder(item);
            this._visitables.get("selectors").remove(builder);
            this.selectors.remove(builder);
         }

         return this;
      }
   }

   public DeviceClassSpecFluent removeMatchingFromSelectors(Predicate predicate) {
      if (this.selectors == null) {
         return this;
      } else {
         Iterator<DeviceSelectorBuilder> each = this.selectors.iterator();
         List visitables = this._visitables.get("selectors");

         while(each.hasNext()) {
            DeviceSelectorBuilder builder = (DeviceSelectorBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSelectors() {
      return this.selectors != null ? build(this.selectors) : null;
   }

   public DeviceSelector buildSelector(int index) {
      return ((DeviceSelectorBuilder)this.selectors.get(index)).build();
   }

   public DeviceSelector buildFirstSelector() {
      return ((DeviceSelectorBuilder)this.selectors.get(0)).build();
   }

   public DeviceSelector buildLastSelector() {
      return ((DeviceSelectorBuilder)this.selectors.get(this.selectors.size() - 1)).build();
   }

   public DeviceSelector buildMatchingSelector(Predicate predicate) {
      for(DeviceSelectorBuilder item : this.selectors) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSelector(Predicate predicate) {
      for(DeviceSelectorBuilder item : this.selectors) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceClassSpecFluent withSelectors(List selectors) {
      if (this.selectors != null) {
         this._visitables.get("selectors").clear();
      }

      if (selectors != null) {
         this.selectors = new ArrayList();

         for(DeviceSelector item : selectors) {
            this.addToSelectors(item);
         }
      } else {
         this.selectors = null;
      }

      return this;
   }

   public DeviceClassSpecFluent withSelectors(DeviceSelector... selectors) {
      if (this.selectors != null) {
         this.selectors.clear();
         this._visitables.remove("selectors");
      }

      if (selectors != null) {
         for(DeviceSelector item : selectors) {
            this.addToSelectors(item);
         }
      }

      return this;
   }

   public boolean hasSelectors() {
      return this.selectors != null && !this.selectors.isEmpty();
   }

   public SelectorsNested addNewSelector() {
      return new SelectorsNested(-1, (DeviceSelector)null);
   }

   public SelectorsNested addNewSelectorLike(DeviceSelector item) {
      return new SelectorsNested(-1, item);
   }

   public SelectorsNested setNewSelectorLike(int index, DeviceSelector item) {
      return new SelectorsNested(index, item);
   }

   public SelectorsNested editSelector(int index) {
      if (this.selectors.size() <= index) {
         throw new RuntimeException("Can't edit selectors. Index exceeds size.");
      } else {
         return this.setNewSelectorLike(index, this.buildSelector(index));
      }
   }

   public SelectorsNested editFirstSelector() {
      if (this.selectors.size() == 0) {
         throw new RuntimeException("Can't edit first selectors. The list is empty.");
      } else {
         return this.setNewSelectorLike(0, this.buildSelector(0));
      }
   }

   public SelectorsNested editLastSelector() {
      int index = this.selectors.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last selectors. The list is empty.");
      } else {
         return this.setNewSelectorLike(index, this.buildSelector(index));
      }
   }

   public SelectorsNested editMatchingSelector(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.selectors.size(); ++i) {
         if (predicate.test((DeviceSelectorBuilder)this.selectors.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching selectors. No match found.");
      } else {
         return this.setNewSelectorLike(index, this.buildSelector(index));
      }
   }

   public DeviceClassSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceClassSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceClassSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceClassSpecFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceClassSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceClassSpecFluent that = (DeviceClassSpecFluent)o;
            if (!Objects.equals(this.config, that.config)) {
               return false;
            } else if (!Objects.equals(this.selectors, that.selectors)) {
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
      return Objects.hash(new Object[]{this.config, this.selectors, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.config != null && !this.config.isEmpty()) {
         sb.append("config:");
         sb.append(this.config + ",");
      }

      if (this.selectors != null && !this.selectors.isEmpty()) {
         sb.append("selectors:");
         sb.append(this.selectors + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConfigNested extends DeviceClassConfigurationFluent implements Nested {
      DeviceClassConfigurationBuilder builder;
      int index;

      ConfigNested(int index, DeviceClassConfiguration item) {
         this.index = index;
         this.builder = new DeviceClassConfigurationBuilder(this, item);
      }

      public Object and() {
         return DeviceClassSpecFluent.this.setToConfig(this.index, this.builder.build());
      }

      public Object endConfig() {
         return this.and();
      }
   }

   public class SelectorsNested extends DeviceSelectorFluent implements Nested {
      DeviceSelectorBuilder builder;
      int index;

      SelectorsNested(int index, DeviceSelector item) {
         this.index = index;
         this.builder = new DeviceSelectorBuilder(this, item);
      }

      public Object and() {
         return DeviceClassSpecFluent.this.setToSelectors(this.index, this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
