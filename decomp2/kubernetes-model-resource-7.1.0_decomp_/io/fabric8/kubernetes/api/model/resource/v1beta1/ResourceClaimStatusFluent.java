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
import java.util.Optional;
import java.util.function.Predicate;

public class ResourceClaimStatusFluent extends BaseFluent {
   private AllocationResultBuilder allocation;
   private ArrayList devices = new ArrayList();
   private ArrayList reservedFor = new ArrayList();
   private Map additionalProperties;

   public ResourceClaimStatusFluent() {
   }

   public ResourceClaimStatusFluent(ResourceClaimStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimStatus instance) {
      instance = instance != null ? instance : new ResourceClaimStatus();
      if (instance != null) {
         this.withAllocation(instance.getAllocation());
         this.withDevices(instance.getDevices());
         this.withReservedFor(instance.getReservedFor());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AllocationResult buildAllocation() {
      return this.allocation != null ? this.allocation.build() : null;
   }

   public ResourceClaimStatusFluent withAllocation(AllocationResult allocation) {
      this._visitables.remove("allocation");
      if (allocation != null) {
         this.allocation = new AllocationResultBuilder(allocation);
         this._visitables.get("allocation").add(this.allocation);
      } else {
         this.allocation = null;
         this._visitables.get("allocation").remove(this.allocation);
      }

      return this;
   }

   public boolean hasAllocation() {
      return this.allocation != null;
   }

   public AllocationNested withNewAllocation() {
      return new AllocationNested((AllocationResult)null);
   }

   public AllocationNested withNewAllocationLike(AllocationResult item) {
      return new AllocationNested(item);
   }

   public AllocationNested editAllocation() {
      return this.withNewAllocationLike((AllocationResult)Optional.ofNullable(this.buildAllocation()).orElse((Object)null));
   }

   public AllocationNested editOrNewAllocation() {
      return this.withNewAllocationLike((AllocationResult)Optional.ofNullable(this.buildAllocation()).orElse((new AllocationResultBuilder()).build()));
   }

   public AllocationNested editOrNewAllocationLike(AllocationResult item) {
      return this.withNewAllocationLike((AllocationResult)Optional.ofNullable(this.buildAllocation()).orElse(item));
   }

   public ResourceClaimStatusFluent addToDevices(int index, AllocatedDeviceStatus item) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
      if (index >= 0 && index < this.devices.size()) {
         this._visitables.get("devices").add(index, builder);
         this.devices.add(index, builder);
      } else {
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent setToDevices(int index, AllocatedDeviceStatus item) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
      if (index >= 0 && index < this.devices.size()) {
         this._visitables.get("devices").set(index, builder);
         this.devices.set(index, builder);
      } else {
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent addToDevices(AllocatedDeviceStatus... items) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      for(AllocatedDeviceStatus item : items) {
         AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent addAllToDevices(Collection items) {
      if (this.devices == null) {
         this.devices = new ArrayList();
      }

      for(AllocatedDeviceStatus item : items) {
         AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
         this._visitables.get("devices").add(builder);
         this.devices.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent removeFromDevices(AllocatedDeviceStatus... items) {
      if (this.devices == null) {
         return this;
      } else {
         for(AllocatedDeviceStatus item : items) {
            AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
            this._visitables.get("devices").remove(builder);
            this.devices.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimStatusFluent removeAllFromDevices(Collection items) {
      if (this.devices == null) {
         return this;
      } else {
         for(AllocatedDeviceStatus item : items) {
            AllocatedDeviceStatusBuilder builder = new AllocatedDeviceStatusBuilder(item);
            this._visitables.get("devices").remove(builder);
            this.devices.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimStatusFluent removeMatchingFromDevices(Predicate predicate) {
      if (this.devices == null) {
         return this;
      } else {
         Iterator<AllocatedDeviceStatusBuilder> each = this.devices.iterator();
         List visitables = this._visitables.get("devices");

         while(each.hasNext()) {
            AllocatedDeviceStatusBuilder builder = (AllocatedDeviceStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildDevices() {
      return this.devices != null ? build(this.devices) : null;
   }

   public AllocatedDeviceStatus buildDevice(int index) {
      return ((AllocatedDeviceStatusBuilder)this.devices.get(index)).build();
   }

   public AllocatedDeviceStatus buildFirstDevice() {
      return ((AllocatedDeviceStatusBuilder)this.devices.get(0)).build();
   }

   public AllocatedDeviceStatus buildLastDevice() {
      return ((AllocatedDeviceStatusBuilder)this.devices.get(this.devices.size() - 1)).build();
   }

   public AllocatedDeviceStatus buildMatchingDevice(Predicate predicate) {
      for(AllocatedDeviceStatusBuilder item : this.devices) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingDevice(Predicate predicate) {
      for(AllocatedDeviceStatusBuilder item : this.devices) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClaimStatusFluent withDevices(List devices) {
      if (this.devices != null) {
         this._visitables.get("devices").clear();
      }

      if (devices != null) {
         this.devices = new ArrayList();

         for(AllocatedDeviceStatus item : devices) {
            this.addToDevices(item);
         }
      } else {
         this.devices = null;
      }

      return this;
   }

   public ResourceClaimStatusFluent withDevices(AllocatedDeviceStatus... devices) {
      if (this.devices != null) {
         this.devices.clear();
         this._visitables.remove("devices");
      }

      if (devices != null) {
         for(AllocatedDeviceStatus item : devices) {
            this.addToDevices(item);
         }
      }

      return this;
   }

   public boolean hasDevices() {
      return this.devices != null && !this.devices.isEmpty();
   }

   public DevicesNested addNewDevice() {
      return new DevicesNested(-1, (AllocatedDeviceStatus)null);
   }

   public DevicesNested addNewDeviceLike(AllocatedDeviceStatus item) {
      return new DevicesNested(-1, item);
   }

   public DevicesNested setNewDeviceLike(int index, AllocatedDeviceStatus item) {
      return new DevicesNested(index, item);
   }

   public DevicesNested editDevice(int index) {
      if (this.devices.size() <= index) {
         throw new RuntimeException("Can't edit devices. Index exceeds size.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public DevicesNested editFirstDevice() {
      if (this.devices.size() == 0) {
         throw new RuntimeException("Can't edit first devices. The list is empty.");
      } else {
         return this.setNewDeviceLike(0, this.buildDevice(0));
      }
   }

   public DevicesNested editLastDevice() {
      int index = this.devices.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last devices. The list is empty.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public DevicesNested editMatchingDevice(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.devices.size(); ++i) {
         if (predicate.test((AllocatedDeviceStatusBuilder)this.devices.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching devices. No match found.");
      } else {
         return this.setNewDeviceLike(index, this.buildDevice(index));
      }
   }

   public ResourceClaimStatusFluent addToReservedFor(int index, ResourceClaimConsumerReference item) {
      if (this.reservedFor == null) {
         this.reservedFor = new ArrayList();
      }

      ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
      if (index >= 0 && index < this.reservedFor.size()) {
         this._visitables.get("reservedFor").add(index, builder);
         this.reservedFor.add(index, builder);
      } else {
         this._visitables.get("reservedFor").add(builder);
         this.reservedFor.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent setToReservedFor(int index, ResourceClaimConsumerReference item) {
      if (this.reservedFor == null) {
         this.reservedFor = new ArrayList();
      }

      ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
      if (index >= 0 && index < this.reservedFor.size()) {
         this._visitables.get("reservedFor").set(index, builder);
         this.reservedFor.set(index, builder);
      } else {
         this._visitables.get("reservedFor").add(builder);
         this.reservedFor.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent addToReservedFor(ResourceClaimConsumerReference... items) {
      if (this.reservedFor == null) {
         this.reservedFor = new ArrayList();
      }

      for(ResourceClaimConsumerReference item : items) {
         ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
         this._visitables.get("reservedFor").add(builder);
         this.reservedFor.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent addAllToReservedFor(Collection items) {
      if (this.reservedFor == null) {
         this.reservedFor = new ArrayList();
      }

      for(ResourceClaimConsumerReference item : items) {
         ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
         this._visitables.get("reservedFor").add(builder);
         this.reservedFor.add(builder);
      }

      return this;
   }

   public ResourceClaimStatusFluent removeFromReservedFor(ResourceClaimConsumerReference... items) {
      if (this.reservedFor == null) {
         return this;
      } else {
         for(ResourceClaimConsumerReference item : items) {
            ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
            this._visitables.get("reservedFor").remove(builder);
            this.reservedFor.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimStatusFluent removeAllFromReservedFor(Collection items) {
      if (this.reservedFor == null) {
         return this;
      } else {
         for(ResourceClaimConsumerReference item : items) {
            ResourceClaimConsumerReferenceBuilder builder = new ResourceClaimConsumerReferenceBuilder(item);
            this._visitables.get("reservedFor").remove(builder);
            this.reservedFor.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimStatusFluent removeMatchingFromReservedFor(Predicate predicate) {
      if (this.reservedFor == null) {
         return this;
      } else {
         Iterator<ResourceClaimConsumerReferenceBuilder> each = this.reservedFor.iterator();
         List visitables = this._visitables.get("reservedFor");

         while(each.hasNext()) {
            ResourceClaimConsumerReferenceBuilder builder = (ResourceClaimConsumerReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildReservedFor() {
      return this.reservedFor != null ? build(this.reservedFor) : null;
   }

   public ResourceClaimConsumerReference buildReservedFor(int index) {
      return ((ResourceClaimConsumerReferenceBuilder)this.reservedFor.get(index)).build();
   }

   public ResourceClaimConsumerReference buildFirstReservedFor() {
      return ((ResourceClaimConsumerReferenceBuilder)this.reservedFor.get(0)).build();
   }

   public ResourceClaimConsumerReference buildLastReservedFor() {
      return ((ResourceClaimConsumerReferenceBuilder)this.reservedFor.get(this.reservedFor.size() - 1)).build();
   }

   public ResourceClaimConsumerReference buildMatchingReservedFor(Predicate predicate) {
      for(ResourceClaimConsumerReferenceBuilder item : this.reservedFor) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingReservedFor(Predicate predicate) {
      for(ResourceClaimConsumerReferenceBuilder item : this.reservedFor) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClaimStatusFluent withReservedFor(List reservedFor) {
      if (this.reservedFor != null) {
         this._visitables.get("reservedFor").clear();
      }

      if (reservedFor != null) {
         this.reservedFor = new ArrayList();

         for(ResourceClaimConsumerReference item : reservedFor) {
            this.addToReservedFor(item);
         }
      } else {
         this.reservedFor = null;
      }

      return this;
   }

   public ResourceClaimStatusFluent withReservedFor(ResourceClaimConsumerReference... reservedFor) {
      if (this.reservedFor != null) {
         this.reservedFor.clear();
         this._visitables.remove("reservedFor");
      }

      if (reservedFor != null) {
         for(ResourceClaimConsumerReference item : reservedFor) {
            this.addToReservedFor(item);
         }
      }

      return this;
   }

   public boolean hasReservedFor() {
      return this.reservedFor != null && !this.reservedFor.isEmpty();
   }

   public ResourceClaimStatusFluent addNewReservedFor(String apiGroup, String name, String resource, String uid) {
      return this.addToReservedFor(new ResourceClaimConsumerReference(apiGroup, name, resource, uid));
   }

   public ReservedForNested addNewReservedFor() {
      return new ReservedForNested(-1, (ResourceClaimConsumerReference)null);
   }

   public ReservedForNested addNewReservedForLike(ResourceClaimConsumerReference item) {
      return new ReservedForNested(-1, item);
   }

   public ReservedForNested setNewReservedForLike(int index, ResourceClaimConsumerReference item) {
      return new ReservedForNested(index, item);
   }

   public ReservedForNested editReservedFor(int index) {
      if (this.reservedFor.size() <= index) {
         throw new RuntimeException("Can't edit reservedFor. Index exceeds size.");
      } else {
         return this.setNewReservedForLike(index, this.buildReservedFor(index));
      }
   }

   public ReservedForNested editFirstReservedFor() {
      if (this.reservedFor.size() == 0) {
         throw new RuntimeException("Can't edit first reservedFor. The list is empty.");
      } else {
         return this.setNewReservedForLike(0, this.buildReservedFor(0));
      }
   }

   public ReservedForNested editLastReservedFor() {
      int index = this.reservedFor.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last reservedFor. The list is empty.");
      } else {
         return this.setNewReservedForLike(index, this.buildReservedFor(index));
      }
   }

   public ReservedForNested editMatchingReservedFor(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.reservedFor.size(); ++i) {
         if (predicate.test((ResourceClaimConsumerReferenceBuilder)this.reservedFor.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching reservedFor. No match found.");
      } else {
         return this.setNewReservedForLike(index, this.buildReservedFor(index));
      }
   }

   public ResourceClaimStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClaimStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClaimStatusFluent that = (ResourceClaimStatusFluent)o;
            if (!Objects.equals(this.allocation, that.allocation)) {
               return false;
            } else if (!Objects.equals(this.devices, that.devices)) {
               return false;
            } else if (!Objects.equals(this.reservedFor, that.reservedFor)) {
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
      return Objects.hash(new Object[]{this.allocation, this.devices, this.reservedFor, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allocation != null) {
         sb.append("allocation:");
         sb.append(this.allocation + ",");
      }

      if (this.devices != null && !this.devices.isEmpty()) {
         sb.append("devices:");
         sb.append(this.devices + ",");
      }

      if (this.reservedFor != null && !this.reservedFor.isEmpty()) {
         sb.append("reservedFor:");
         sb.append(this.reservedFor + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AllocationNested extends AllocationResultFluent implements Nested {
      AllocationResultBuilder builder;

      AllocationNested(AllocationResult item) {
         this.builder = new AllocationResultBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimStatusFluent.this.withAllocation(this.builder.build());
      }

      public Object endAllocation() {
         return this.and();
      }
   }

   public class DevicesNested extends AllocatedDeviceStatusFluent implements Nested {
      AllocatedDeviceStatusBuilder builder;
      int index;

      DevicesNested(int index, AllocatedDeviceStatus item) {
         this.index = index;
         this.builder = new AllocatedDeviceStatusBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimStatusFluent.this.setToDevices(this.index, this.builder.build());
      }

      public Object endDevice() {
         return this.and();
      }
   }

   public class ReservedForNested extends ResourceClaimConsumerReferenceFluent implements Nested {
      ResourceClaimConsumerReferenceBuilder builder;
      int index;

      ReservedForNested(int index, ResourceClaimConsumerReference item) {
         this.index = index;
         this.builder = new ResourceClaimConsumerReferenceBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimStatusFluent.this.setToReservedFor(this.index, this.builder.build());
      }

      public Object endReservedFor() {
         return this.and();
      }
   }
}
