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

public class DeviceRequestFluent extends BaseFluent {
   private Boolean adminAccess;
   private String allocationMode;
   private Long count;
   private String deviceClassName;
   private String name;
   private ArrayList selectors = new ArrayList();
   private Map additionalProperties;

   public DeviceRequestFluent() {
   }

   public DeviceRequestFluent(DeviceRequest instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceRequest instance) {
      instance = instance != null ? instance : new DeviceRequest();
      if (instance != null) {
         this.withAdminAccess(instance.getAdminAccess());
         this.withAllocationMode(instance.getAllocationMode());
         this.withCount(instance.getCount());
         this.withDeviceClassName(instance.getDeviceClassName());
         this.withName(instance.getName());
         this.withSelectors(instance.getSelectors());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAdminAccess() {
      return this.adminAccess;
   }

   public DeviceRequestFluent withAdminAccess(Boolean adminAccess) {
      this.adminAccess = adminAccess;
      return this;
   }

   public boolean hasAdminAccess() {
      return this.adminAccess != null;
   }

   public String getAllocationMode() {
      return this.allocationMode;
   }

   public DeviceRequestFluent withAllocationMode(String allocationMode) {
      this.allocationMode = allocationMode;
      return this;
   }

   public boolean hasAllocationMode() {
      return this.allocationMode != null;
   }

   public Long getCount() {
      return this.count;
   }

   public DeviceRequestFluent withCount(Long count) {
      this.count = count;
      return this;
   }

   public boolean hasCount() {
      return this.count != null;
   }

   public String getDeviceClassName() {
      return this.deviceClassName;
   }

   public DeviceRequestFluent withDeviceClassName(String deviceClassName) {
      this.deviceClassName = deviceClassName;
      return this;
   }

   public boolean hasDeviceClassName() {
      return this.deviceClassName != null;
   }

   public String getName() {
      return this.name;
   }

   public DeviceRequestFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public DeviceRequestFluent addToSelectors(int index, DeviceSelector item) {
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

   public DeviceRequestFluent setToSelectors(int index, DeviceSelector item) {
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

   public DeviceRequestFluent addToSelectors(DeviceSelector... items) {
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

   public DeviceRequestFluent addAllToSelectors(Collection items) {
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

   public DeviceRequestFluent removeFromSelectors(DeviceSelector... items) {
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

   public DeviceRequestFluent removeAllFromSelectors(Collection items) {
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

   public DeviceRequestFluent removeMatchingFromSelectors(Predicate predicate) {
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

   public DeviceRequestFluent withSelectors(List selectors) {
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

   public DeviceRequestFluent withSelectors(DeviceSelector... selectors) {
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

   public DeviceRequestFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceRequestFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceRequestFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceRequestFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceRequestFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceRequestFluent that = (DeviceRequestFluent)o;
            if (!Objects.equals(this.adminAccess, that.adminAccess)) {
               return false;
            } else if (!Objects.equals(this.allocationMode, that.allocationMode)) {
               return false;
            } else if (!Objects.equals(this.count, that.count)) {
               return false;
            } else if (!Objects.equals(this.deviceClassName, that.deviceClassName)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.adminAccess, this.allocationMode, this.count, this.deviceClassName, this.name, this.selectors, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.adminAccess != null) {
         sb.append("adminAccess:");
         sb.append(this.adminAccess + ",");
      }

      if (this.allocationMode != null) {
         sb.append("allocationMode:");
         sb.append(this.allocationMode + ",");
      }

      if (this.count != null) {
         sb.append("count:");
         sb.append(this.count + ",");
      }

      if (this.deviceClassName != null) {
         sb.append("deviceClassName:");
         sb.append(this.deviceClassName + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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

   public DeviceRequestFluent withAdminAccess() {
      return this.withAdminAccess(true);
   }

   public class SelectorsNested extends DeviceSelectorFluent implements Nested {
      DeviceSelectorBuilder builder;
      int index;

      SelectorsNested(int index, DeviceSelector item) {
         this.index = index;
         this.builder = new DeviceSelectorBuilder(this, item);
      }

      public Object and() {
         return DeviceRequestFluent.this.setToSelectors(this.index, this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
