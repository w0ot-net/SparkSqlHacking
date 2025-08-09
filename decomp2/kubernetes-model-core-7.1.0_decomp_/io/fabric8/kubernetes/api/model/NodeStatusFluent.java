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
import java.util.Optional;
import java.util.function.Predicate;

public class NodeStatusFluent extends BaseFluent {
   private ArrayList addresses = new ArrayList();
   private Map allocatable;
   private Map capacity;
   private ArrayList conditions = new ArrayList();
   private NodeConfigStatusBuilder config;
   private NodeDaemonEndpointsBuilder daemonEndpoints;
   private NodeFeaturesBuilder features;
   private ArrayList images = new ArrayList();
   private NodeSystemInfoBuilder nodeInfo;
   private String phase;
   private ArrayList runtimeHandlers = new ArrayList();
   private ArrayList volumesAttached = new ArrayList();
   private List volumesInUse = new ArrayList();
   private Map additionalProperties;

   public NodeStatusFluent() {
   }

   public NodeStatusFluent(NodeStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeStatus instance) {
      instance = instance != null ? instance : new NodeStatus();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withAllocatable(instance.getAllocatable());
         this.withCapacity(instance.getCapacity());
         this.withConditions(instance.getConditions());
         this.withConfig(instance.getConfig());
         this.withDaemonEndpoints(instance.getDaemonEndpoints());
         this.withFeatures(instance.getFeatures());
         this.withImages(instance.getImages());
         this.withNodeInfo(instance.getNodeInfo());
         this.withPhase(instance.getPhase());
         this.withRuntimeHandlers(instance.getRuntimeHandlers());
         this.withVolumesAttached(instance.getVolumesAttached());
         this.withVolumesInUse(instance.getVolumesInUse());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeStatusFluent addToAddresses(int index, NodeAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      NodeAddressBuilder builder = new NodeAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").add(index, builder);
         this.addresses.add(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public NodeStatusFluent setToAddresses(int index, NodeAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      NodeAddressBuilder builder = new NodeAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").set(index, builder);
         this.addresses.set(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addToAddresses(NodeAddress... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(NodeAddress item : items) {
         NodeAddressBuilder builder = new NodeAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(NodeAddress item : items) {
         NodeAddressBuilder builder = new NodeAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public NodeStatusFluent removeFromAddresses(NodeAddress... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(NodeAddress item : items) {
            NodeAddressBuilder builder = new NodeAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(NodeAddress item : items) {
            NodeAddressBuilder builder = new NodeAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeMatchingFromAddresses(Predicate predicate) {
      if (this.addresses == null) {
         return this;
      } else {
         Iterator<NodeAddressBuilder> each = this.addresses.iterator();
         List visitables = this._visitables.get("addresses");

         while(each.hasNext()) {
            NodeAddressBuilder builder = (NodeAddressBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAddresses() {
      return this.addresses != null ? build(this.addresses) : null;
   }

   public NodeAddress buildAddress(int index) {
      return ((NodeAddressBuilder)this.addresses.get(index)).build();
   }

   public NodeAddress buildFirstAddress() {
      return ((NodeAddressBuilder)this.addresses.get(0)).build();
   }

   public NodeAddress buildLastAddress() {
      return ((NodeAddressBuilder)this.addresses.get(this.addresses.size() - 1)).build();
   }

   public NodeAddress buildMatchingAddress(Predicate predicate) {
      for(NodeAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(NodeAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withAddresses(List addresses) {
      if (this.addresses != null) {
         this._visitables.get("addresses").clear();
      }

      if (addresses != null) {
         this.addresses = new ArrayList();

         for(NodeAddress item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public NodeStatusFluent withAddresses(NodeAddress... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(NodeAddress item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public NodeStatusFluent addNewAddress(String address, String type) {
      return this.addToAddresses(new NodeAddress(address, type));
   }

   public AddressesNested addNewAddress() {
      return new AddressesNested(-1, (NodeAddress)null);
   }

   public AddressesNested addNewAddressLike(NodeAddress item) {
      return new AddressesNested(-1, item);
   }

   public AddressesNested setNewAddressLike(int index, NodeAddress item) {
      return new AddressesNested(index, item);
   }

   public AddressesNested editAddress(int index) {
      if (this.addresses.size() <= index) {
         throw new RuntimeException("Can't edit addresses. Index exceeds size.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public AddressesNested editFirstAddress() {
      if (this.addresses.size() == 0) {
         throw new RuntimeException("Can't edit first addresses. The list is empty.");
      } else {
         return this.setNewAddressLike(0, this.buildAddress(0));
      }
   }

   public AddressesNested editLastAddress() {
      int index = this.addresses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last addresses. The list is empty.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public AddressesNested editMatchingAddress(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.addresses.size(); ++i) {
         if (predicate.test((NodeAddressBuilder)this.addresses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching addresses. No match found.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public NodeStatusFluent addToAllocatable(String key, Quantity value) {
      if (this.allocatable == null && key != null && value != null) {
         this.allocatable = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.allocatable.put(key, value);
      }

      return this;
   }

   public NodeStatusFluent addToAllocatable(Map map) {
      if (this.allocatable == null && map != null) {
         this.allocatable = new LinkedHashMap();
      }

      if (map != null) {
         this.allocatable.putAll(map);
      }

      return this;
   }

   public NodeStatusFluent removeFromAllocatable(String key) {
      if (this.allocatable == null) {
         return this;
      } else {
         if (key != null && this.allocatable != null) {
            this.allocatable.remove(key);
         }

         return this;
      }
   }

   public NodeStatusFluent removeFromAllocatable(Map map) {
      if (this.allocatable == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.allocatable != null) {
                  this.allocatable.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAllocatable() {
      return this.allocatable;
   }

   public NodeStatusFluent withAllocatable(Map allocatable) {
      if (allocatable == null) {
         this.allocatable = null;
      } else {
         this.allocatable = new LinkedHashMap(allocatable);
      }

      return this;
   }

   public boolean hasAllocatable() {
      return this.allocatable != null;
   }

   public NodeStatusFluent addToCapacity(String key, Quantity value) {
      if (this.capacity == null && key != null && value != null) {
         this.capacity = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.capacity.put(key, value);
      }

      return this;
   }

   public NodeStatusFluent addToCapacity(Map map) {
      if (this.capacity == null && map != null) {
         this.capacity = new LinkedHashMap();
      }

      if (map != null) {
         this.capacity.putAll(map);
      }

      return this;
   }

   public NodeStatusFluent removeFromCapacity(String key) {
      if (this.capacity == null) {
         return this;
      } else {
         if (key != null && this.capacity != null) {
            this.capacity.remove(key);
         }

         return this;
      }
   }

   public NodeStatusFluent removeFromCapacity(Map map) {
      if (this.capacity == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.capacity != null) {
                  this.capacity.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getCapacity() {
      return this.capacity;
   }

   public NodeStatusFluent withCapacity(Map capacity) {
      if (capacity == null) {
         this.capacity = null;
      } else {
         this.capacity = new LinkedHashMap(capacity);
      }

      return this;
   }

   public boolean hasCapacity() {
      return this.capacity != null;
   }

   public NodeStatusFluent addToConditions(int index, NodeCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      NodeConditionBuilder builder = new NodeConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NodeStatusFluent setToConditions(int index, NodeCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      NodeConditionBuilder builder = new NodeConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addToConditions(NodeCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(NodeCondition item : items) {
         NodeConditionBuilder builder = new NodeConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(NodeCondition item : items) {
         NodeConditionBuilder builder = new NodeConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public NodeStatusFluent removeFromConditions(NodeCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(NodeCondition item : items) {
            NodeConditionBuilder builder = new NodeConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(NodeCondition item : items) {
            NodeConditionBuilder builder = new NodeConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<NodeConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            NodeConditionBuilder builder = (NodeConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public NodeCondition buildCondition(int index) {
      return ((NodeConditionBuilder)this.conditions.get(index)).build();
   }

   public NodeCondition buildFirstCondition() {
      return ((NodeConditionBuilder)this.conditions.get(0)).build();
   }

   public NodeCondition buildLastCondition() {
      return ((NodeConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public NodeCondition buildMatchingCondition(Predicate predicate) {
      for(NodeConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(NodeConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(NodeCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public NodeStatusFluent withConditions(NodeCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(NodeCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (NodeCondition)null);
   }

   public ConditionsNested addNewConditionLike(NodeCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, NodeCondition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((NodeConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public NodeConfigStatus buildConfig() {
      return this.config != null ? this.config.build() : null;
   }

   public NodeStatusFluent withConfig(NodeConfigStatus config) {
      this._visitables.remove("config");
      if (config != null) {
         this.config = new NodeConfigStatusBuilder(config);
         this._visitables.get("config").add(this.config);
      } else {
         this.config = null;
         this._visitables.get("config").remove(this.config);
      }

      return this;
   }

   public boolean hasConfig() {
      return this.config != null;
   }

   public ConfigNested withNewConfig() {
      return new ConfigNested((NodeConfigStatus)null);
   }

   public ConfigNested withNewConfigLike(NodeConfigStatus item) {
      return new ConfigNested(item);
   }

   public ConfigNested editConfig() {
      return this.withNewConfigLike((NodeConfigStatus)Optional.ofNullable(this.buildConfig()).orElse((Object)null));
   }

   public ConfigNested editOrNewConfig() {
      return this.withNewConfigLike((NodeConfigStatus)Optional.ofNullable(this.buildConfig()).orElse((new NodeConfigStatusBuilder()).build()));
   }

   public ConfigNested editOrNewConfigLike(NodeConfigStatus item) {
      return this.withNewConfigLike((NodeConfigStatus)Optional.ofNullable(this.buildConfig()).orElse(item));
   }

   public NodeDaemonEndpoints buildDaemonEndpoints() {
      return this.daemonEndpoints != null ? this.daemonEndpoints.build() : null;
   }

   public NodeStatusFluent withDaemonEndpoints(NodeDaemonEndpoints daemonEndpoints) {
      this._visitables.remove("daemonEndpoints");
      if (daemonEndpoints != null) {
         this.daemonEndpoints = new NodeDaemonEndpointsBuilder(daemonEndpoints);
         this._visitables.get("daemonEndpoints").add(this.daemonEndpoints);
      } else {
         this.daemonEndpoints = null;
         this._visitables.get("daemonEndpoints").remove(this.daemonEndpoints);
      }

      return this;
   }

   public boolean hasDaemonEndpoints() {
      return this.daemonEndpoints != null;
   }

   public DaemonEndpointsNested withNewDaemonEndpoints() {
      return new DaemonEndpointsNested((NodeDaemonEndpoints)null);
   }

   public DaemonEndpointsNested withNewDaemonEndpointsLike(NodeDaemonEndpoints item) {
      return new DaemonEndpointsNested(item);
   }

   public DaemonEndpointsNested editDaemonEndpoints() {
      return this.withNewDaemonEndpointsLike((NodeDaemonEndpoints)Optional.ofNullable(this.buildDaemonEndpoints()).orElse((Object)null));
   }

   public DaemonEndpointsNested editOrNewDaemonEndpoints() {
      return this.withNewDaemonEndpointsLike((NodeDaemonEndpoints)Optional.ofNullable(this.buildDaemonEndpoints()).orElse((new NodeDaemonEndpointsBuilder()).build()));
   }

   public DaemonEndpointsNested editOrNewDaemonEndpointsLike(NodeDaemonEndpoints item) {
      return this.withNewDaemonEndpointsLike((NodeDaemonEndpoints)Optional.ofNullable(this.buildDaemonEndpoints()).orElse(item));
   }

   public NodeFeatures buildFeatures() {
      return this.features != null ? this.features.build() : null;
   }

   public NodeStatusFluent withFeatures(NodeFeatures features) {
      this._visitables.remove("features");
      if (features != null) {
         this.features = new NodeFeaturesBuilder(features);
         this._visitables.get("features").add(this.features);
      } else {
         this.features = null;
         this._visitables.get("features").remove(this.features);
      }

      return this;
   }

   public boolean hasFeatures() {
      return this.features != null;
   }

   public NodeStatusFluent withNewFeatures(Boolean supplementalGroupsPolicy) {
      return this.withFeatures(new NodeFeatures(supplementalGroupsPolicy));
   }

   public FeaturesNested withNewFeatures() {
      return new FeaturesNested((NodeFeatures)null);
   }

   public FeaturesNested withNewFeaturesLike(NodeFeatures item) {
      return new FeaturesNested(item);
   }

   public FeaturesNested editFeatures() {
      return this.withNewFeaturesLike((NodeFeatures)Optional.ofNullable(this.buildFeatures()).orElse((Object)null));
   }

   public FeaturesNested editOrNewFeatures() {
      return this.withNewFeaturesLike((NodeFeatures)Optional.ofNullable(this.buildFeatures()).orElse((new NodeFeaturesBuilder()).build()));
   }

   public FeaturesNested editOrNewFeaturesLike(NodeFeatures item) {
      return this.withNewFeaturesLike((NodeFeatures)Optional.ofNullable(this.buildFeatures()).orElse(item));
   }

   public NodeStatusFluent addToImages(int index, ContainerImage item) {
      if (this.images == null) {
         this.images = new ArrayList();
      }

      ContainerImageBuilder builder = new ContainerImageBuilder(item);
      if (index >= 0 && index < this.images.size()) {
         this._visitables.get("images").add(index, builder);
         this.images.add(index, builder);
      } else {
         this._visitables.get("images").add(builder);
         this.images.add(builder);
      }

      return this;
   }

   public NodeStatusFluent setToImages(int index, ContainerImage item) {
      if (this.images == null) {
         this.images = new ArrayList();
      }

      ContainerImageBuilder builder = new ContainerImageBuilder(item);
      if (index >= 0 && index < this.images.size()) {
         this._visitables.get("images").set(index, builder);
         this.images.set(index, builder);
      } else {
         this._visitables.get("images").add(builder);
         this.images.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addToImages(ContainerImage... items) {
      if (this.images == null) {
         this.images = new ArrayList();
      }

      for(ContainerImage item : items) {
         ContainerImageBuilder builder = new ContainerImageBuilder(item);
         this._visitables.get("images").add(builder);
         this.images.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addAllToImages(Collection items) {
      if (this.images == null) {
         this.images = new ArrayList();
      }

      for(ContainerImage item : items) {
         ContainerImageBuilder builder = new ContainerImageBuilder(item);
         this._visitables.get("images").add(builder);
         this.images.add(builder);
      }

      return this;
   }

   public NodeStatusFluent removeFromImages(ContainerImage... items) {
      if (this.images == null) {
         return this;
      } else {
         for(ContainerImage item : items) {
            ContainerImageBuilder builder = new ContainerImageBuilder(item);
            this._visitables.get("images").remove(builder);
            this.images.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromImages(Collection items) {
      if (this.images == null) {
         return this;
      } else {
         for(ContainerImage item : items) {
            ContainerImageBuilder builder = new ContainerImageBuilder(item);
            this._visitables.get("images").remove(builder);
            this.images.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeMatchingFromImages(Predicate predicate) {
      if (this.images == null) {
         return this;
      } else {
         Iterator<ContainerImageBuilder> each = this.images.iterator();
         List visitables = this._visitables.get("images");

         while(each.hasNext()) {
            ContainerImageBuilder builder = (ContainerImageBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildImages() {
      return this.images != null ? build(this.images) : null;
   }

   public ContainerImage buildImage(int index) {
      return ((ContainerImageBuilder)this.images.get(index)).build();
   }

   public ContainerImage buildFirstImage() {
      return ((ContainerImageBuilder)this.images.get(0)).build();
   }

   public ContainerImage buildLastImage() {
      return ((ContainerImageBuilder)this.images.get(this.images.size() - 1)).build();
   }

   public ContainerImage buildMatchingImage(Predicate predicate) {
      for(ContainerImageBuilder item : this.images) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingImage(Predicate predicate) {
      for(ContainerImageBuilder item : this.images) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withImages(List images) {
      if (this.images != null) {
         this._visitables.get("images").clear();
      }

      if (images != null) {
         this.images = new ArrayList();

         for(ContainerImage item : images) {
            this.addToImages(item);
         }
      } else {
         this.images = null;
      }

      return this;
   }

   public NodeStatusFluent withImages(ContainerImage... images) {
      if (this.images != null) {
         this.images.clear();
         this._visitables.remove("images");
      }

      if (images != null) {
         for(ContainerImage item : images) {
            this.addToImages(item);
         }
      }

      return this;
   }

   public boolean hasImages() {
      return this.images != null && !this.images.isEmpty();
   }

   public ImagesNested addNewImage() {
      return new ImagesNested(-1, (ContainerImage)null);
   }

   public ImagesNested addNewImageLike(ContainerImage item) {
      return new ImagesNested(-1, item);
   }

   public ImagesNested setNewImageLike(int index, ContainerImage item) {
      return new ImagesNested(index, item);
   }

   public ImagesNested editImage(int index) {
      if (this.images.size() <= index) {
         throw new RuntimeException("Can't edit images. Index exceeds size.");
      } else {
         return this.setNewImageLike(index, this.buildImage(index));
      }
   }

   public ImagesNested editFirstImage() {
      if (this.images.size() == 0) {
         throw new RuntimeException("Can't edit first images. The list is empty.");
      } else {
         return this.setNewImageLike(0, this.buildImage(0));
      }
   }

   public ImagesNested editLastImage() {
      int index = this.images.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last images. The list is empty.");
      } else {
         return this.setNewImageLike(index, this.buildImage(index));
      }
   }

   public ImagesNested editMatchingImage(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.images.size(); ++i) {
         if (predicate.test((ContainerImageBuilder)this.images.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching images. No match found.");
      } else {
         return this.setNewImageLike(index, this.buildImage(index));
      }
   }

   public NodeSystemInfo buildNodeInfo() {
      return this.nodeInfo != null ? this.nodeInfo.build() : null;
   }

   public NodeStatusFluent withNodeInfo(NodeSystemInfo nodeInfo) {
      this._visitables.remove("nodeInfo");
      if (nodeInfo != null) {
         this.nodeInfo = new NodeSystemInfoBuilder(nodeInfo);
         this._visitables.get("nodeInfo").add(this.nodeInfo);
      } else {
         this.nodeInfo = null;
         this._visitables.get("nodeInfo").remove(this.nodeInfo);
      }

      return this;
   }

   public boolean hasNodeInfo() {
      return this.nodeInfo != null;
   }

   public NodeInfoNested withNewNodeInfo() {
      return new NodeInfoNested((NodeSystemInfo)null);
   }

   public NodeInfoNested withNewNodeInfoLike(NodeSystemInfo item) {
      return new NodeInfoNested(item);
   }

   public NodeInfoNested editNodeInfo() {
      return this.withNewNodeInfoLike((NodeSystemInfo)Optional.ofNullable(this.buildNodeInfo()).orElse((Object)null));
   }

   public NodeInfoNested editOrNewNodeInfo() {
      return this.withNewNodeInfoLike((NodeSystemInfo)Optional.ofNullable(this.buildNodeInfo()).orElse((new NodeSystemInfoBuilder()).build()));
   }

   public NodeInfoNested editOrNewNodeInfoLike(NodeSystemInfo item) {
      return this.withNewNodeInfoLike((NodeSystemInfo)Optional.ofNullable(this.buildNodeInfo()).orElse(item));
   }

   public String getPhase() {
      return this.phase;
   }

   public NodeStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public NodeStatusFluent addToRuntimeHandlers(int index, NodeRuntimeHandler item) {
      if (this.runtimeHandlers == null) {
         this.runtimeHandlers = new ArrayList();
      }

      NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
      if (index >= 0 && index < this.runtimeHandlers.size()) {
         this._visitables.get("runtimeHandlers").add(index, builder);
         this.runtimeHandlers.add(index, builder);
      } else {
         this._visitables.get("runtimeHandlers").add(builder);
         this.runtimeHandlers.add(builder);
      }

      return this;
   }

   public NodeStatusFluent setToRuntimeHandlers(int index, NodeRuntimeHandler item) {
      if (this.runtimeHandlers == null) {
         this.runtimeHandlers = new ArrayList();
      }

      NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
      if (index >= 0 && index < this.runtimeHandlers.size()) {
         this._visitables.get("runtimeHandlers").set(index, builder);
         this.runtimeHandlers.set(index, builder);
      } else {
         this._visitables.get("runtimeHandlers").add(builder);
         this.runtimeHandlers.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addToRuntimeHandlers(NodeRuntimeHandler... items) {
      if (this.runtimeHandlers == null) {
         this.runtimeHandlers = new ArrayList();
      }

      for(NodeRuntimeHandler item : items) {
         NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
         this._visitables.get("runtimeHandlers").add(builder);
         this.runtimeHandlers.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addAllToRuntimeHandlers(Collection items) {
      if (this.runtimeHandlers == null) {
         this.runtimeHandlers = new ArrayList();
      }

      for(NodeRuntimeHandler item : items) {
         NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
         this._visitables.get("runtimeHandlers").add(builder);
         this.runtimeHandlers.add(builder);
      }

      return this;
   }

   public NodeStatusFluent removeFromRuntimeHandlers(NodeRuntimeHandler... items) {
      if (this.runtimeHandlers == null) {
         return this;
      } else {
         for(NodeRuntimeHandler item : items) {
            NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
            this._visitables.get("runtimeHandlers").remove(builder);
            this.runtimeHandlers.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromRuntimeHandlers(Collection items) {
      if (this.runtimeHandlers == null) {
         return this;
      } else {
         for(NodeRuntimeHandler item : items) {
            NodeRuntimeHandlerBuilder builder = new NodeRuntimeHandlerBuilder(item);
            this._visitables.get("runtimeHandlers").remove(builder);
            this.runtimeHandlers.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeMatchingFromRuntimeHandlers(Predicate predicate) {
      if (this.runtimeHandlers == null) {
         return this;
      } else {
         Iterator<NodeRuntimeHandlerBuilder> each = this.runtimeHandlers.iterator();
         List visitables = this._visitables.get("runtimeHandlers");

         while(each.hasNext()) {
            NodeRuntimeHandlerBuilder builder = (NodeRuntimeHandlerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRuntimeHandlers() {
      return this.runtimeHandlers != null ? build(this.runtimeHandlers) : null;
   }

   public NodeRuntimeHandler buildRuntimeHandler(int index) {
      return ((NodeRuntimeHandlerBuilder)this.runtimeHandlers.get(index)).build();
   }

   public NodeRuntimeHandler buildFirstRuntimeHandler() {
      return ((NodeRuntimeHandlerBuilder)this.runtimeHandlers.get(0)).build();
   }

   public NodeRuntimeHandler buildLastRuntimeHandler() {
      return ((NodeRuntimeHandlerBuilder)this.runtimeHandlers.get(this.runtimeHandlers.size() - 1)).build();
   }

   public NodeRuntimeHandler buildMatchingRuntimeHandler(Predicate predicate) {
      for(NodeRuntimeHandlerBuilder item : this.runtimeHandlers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRuntimeHandler(Predicate predicate) {
      for(NodeRuntimeHandlerBuilder item : this.runtimeHandlers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withRuntimeHandlers(List runtimeHandlers) {
      if (this.runtimeHandlers != null) {
         this._visitables.get("runtimeHandlers").clear();
      }

      if (runtimeHandlers != null) {
         this.runtimeHandlers = new ArrayList();

         for(NodeRuntimeHandler item : runtimeHandlers) {
            this.addToRuntimeHandlers(item);
         }
      } else {
         this.runtimeHandlers = null;
      }

      return this;
   }

   public NodeStatusFluent withRuntimeHandlers(NodeRuntimeHandler... runtimeHandlers) {
      if (this.runtimeHandlers != null) {
         this.runtimeHandlers.clear();
         this._visitables.remove("runtimeHandlers");
      }

      if (runtimeHandlers != null) {
         for(NodeRuntimeHandler item : runtimeHandlers) {
            this.addToRuntimeHandlers(item);
         }
      }

      return this;
   }

   public boolean hasRuntimeHandlers() {
      return this.runtimeHandlers != null && !this.runtimeHandlers.isEmpty();
   }

   public RuntimeHandlersNested addNewRuntimeHandler() {
      return new RuntimeHandlersNested(-1, (NodeRuntimeHandler)null);
   }

   public RuntimeHandlersNested addNewRuntimeHandlerLike(NodeRuntimeHandler item) {
      return new RuntimeHandlersNested(-1, item);
   }

   public RuntimeHandlersNested setNewRuntimeHandlerLike(int index, NodeRuntimeHandler item) {
      return new RuntimeHandlersNested(index, item);
   }

   public RuntimeHandlersNested editRuntimeHandler(int index) {
      if (this.runtimeHandlers.size() <= index) {
         throw new RuntimeException("Can't edit runtimeHandlers. Index exceeds size.");
      } else {
         return this.setNewRuntimeHandlerLike(index, this.buildRuntimeHandler(index));
      }
   }

   public RuntimeHandlersNested editFirstRuntimeHandler() {
      if (this.runtimeHandlers.size() == 0) {
         throw new RuntimeException("Can't edit first runtimeHandlers. The list is empty.");
      } else {
         return this.setNewRuntimeHandlerLike(0, this.buildRuntimeHandler(0));
      }
   }

   public RuntimeHandlersNested editLastRuntimeHandler() {
      int index = this.runtimeHandlers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last runtimeHandlers. The list is empty.");
      } else {
         return this.setNewRuntimeHandlerLike(index, this.buildRuntimeHandler(index));
      }
   }

   public RuntimeHandlersNested editMatchingRuntimeHandler(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.runtimeHandlers.size(); ++i) {
         if (predicate.test((NodeRuntimeHandlerBuilder)this.runtimeHandlers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching runtimeHandlers. No match found.");
      } else {
         return this.setNewRuntimeHandlerLike(index, this.buildRuntimeHandler(index));
      }
   }

   public NodeStatusFluent addToVolumesAttached(int index, AttachedVolume item) {
      if (this.volumesAttached == null) {
         this.volumesAttached = new ArrayList();
      }

      AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
      if (index >= 0 && index < this.volumesAttached.size()) {
         this._visitables.get("volumesAttached").add(index, builder);
         this.volumesAttached.add(index, builder);
      } else {
         this._visitables.get("volumesAttached").add(builder);
         this.volumesAttached.add(builder);
      }

      return this;
   }

   public NodeStatusFluent setToVolumesAttached(int index, AttachedVolume item) {
      if (this.volumesAttached == null) {
         this.volumesAttached = new ArrayList();
      }

      AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
      if (index >= 0 && index < this.volumesAttached.size()) {
         this._visitables.get("volumesAttached").set(index, builder);
         this.volumesAttached.set(index, builder);
      } else {
         this._visitables.get("volumesAttached").add(builder);
         this.volumesAttached.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addToVolumesAttached(AttachedVolume... items) {
      if (this.volumesAttached == null) {
         this.volumesAttached = new ArrayList();
      }

      for(AttachedVolume item : items) {
         AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
         this._visitables.get("volumesAttached").add(builder);
         this.volumesAttached.add(builder);
      }

      return this;
   }

   public NodeStatusFluent addAllToVolumesAttached(Collection items) {
      if (this.volumesAttached == null) {
         this.volumesAttached = new ArrayList();
      }

      for(AttachedVolume item : items) {
         AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
         this._visitables.get("volumesAttached").add(builder);
         this.volumesAttached.add(builder);
      }

      return this;
   }

   public NodeStatusFluent removeFromVolumesAttached(AttachedVolume... items) {
      if (this.volumesAttached == null) {
         return this;
      } else {
         for(AttachedVolume item : items) {
            AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
            this._visitables.get("volumesAttached").remove(builder);
            this.volumesAttached.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromVolumesAttached(Collection items) {
      if (this.volumesAttached == null) {
         return this;
      } else {
         for(AttachedVolume item : items) {
            AttachedVolumeBuilder builder = new AttachedVolumeBuilder(item);
            this._visitables.get("volumesAttached").remove(builder);
            this.volumesAttached.remove(builder);
         }

         return this;
      }
   }

   public NodeStatusFluent removeMatchingFromVolumesAttached(Predicate predicate) {
      if (this.volumesAttached == null) {
         return this;
      } else {
         Iterator<AttachedVolumeBuilder> each = this.volumesAttached.iterator();
         List visitables = this._visitables.get("volumesAttached");

         while(each.hasNext()) {
            AttachedVolumeBuilder builder = (AttachedVolumeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumesAttached() {
      return this.volumesAttached != null ? build(this.volumesAttached) : null;
   }

   public AttachedVolume buildVolumesAttached(int index) {
      return ((AttachedVolumeBuilder)this.volumesAttached.get(index)).build();
   }

   public AttachedVolume buildFirstVolumesAttached() {
      return ((AttachedVolumeBuilder)this.volumesAttached.get(0)).build();
   }

   public AttachedVolume buildLastVolumesAttached() {
      return ((AttachedVolumeBuilder)this.volumesAttached.get(this.volumesAttached.size() - 1)).build();
   }

   public AttachedVolume buildMatchingVolumesAttached(Predicate predicate) {
      for(AttachedVolumeBuilder item : this.volumesAttached) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolumesAttached(Predicate predicate) {
      for(AttachedVolumeBuilder item : this.volumesAttached) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withVolumesAttached(List volumesAttached) {
      if (this.volumesAttached != null) {
         this._visitables.get("volumesAttached").clear();
      }

      if (volumesAttached != null) {
         this.volumesAttached = new ArrayList();

         for(AttachedVolume item : volumesAttached) {
            this.addToVolumesAttached(item);
         }
      } else {
         this.volumesAttached = null;
      }

      return this;
   }

   public NodeStatusFluent withVolumesAttached(AttachedVolume... volumesAttached) {
      if (this.volumesAttached != null) {
         this.volumesAttached.clear();
         this._visitables.remove("volumesAttached");
      }

      if (volumesAttached != null) {
         for(AttachedVolume item : volumesAttached) {
            this.addToVolumesAttached(item);
         }
      }

      return this;
   }

   public boolean hasVolumesAttached() {
      return this.volumesAttached != null && !this.volumesAttached.isEmpty();
   }

   public NodeStatusFluent addNewVolumesAttached(String devicePath, String name) {
      return this.addToVolumesAttached(new AttachedVolume(devicePath, name));
   }

   public VolumesAttachedNested addNewVolumesAttached() {
      return new VolumesAttachedNested(-1, (AttachedVolume)null);
   }

   public VolumesAttachedNested addNewVolumesAttachedLike(AttachedVolume item) {
      return new VolumesAttachedNested(-1, item);
   }

   public VolumesAttachedNested setNewVolumesAttachedLike(int index, AttachedVolume item) {
      return new VolumesAttachedNested(index, item);
   }

   public VolumesAttachedNested editVolumesAttached(int index) {
      if (this.volumesAttached.size() <= index) {
         throw new RuntimeException("Can't edit volumesAttached. Index exceeds size.");
      } else {
         return this.setNewVolumesAttachedLike(index, this.buildVolumesAttached(index));
      }
   }

   public VolumesAttachedNested editFirstVolumesAttached() {
      if (this.volumesAttached.size() == 0) {
         throw new RuntimeException("Can't edit first volumesAttached. The list is empty.");
      } else {
         return this.setNewVolumesAttachedLike(0, this.buildVolumesAttached(0));
      }
   }

   public VolumesAttachedNested editLastVolumesAttached() {
      int index = this.volumesAttached.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumesAttached. The list is empty.");
      } else {
         return this.setNewVolumesAttachedLike(index, this.buildVolumesAttached(index));
      }
   }

   public VolumesAttachedNested editMatchingVolumesAttached(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumesAttached.size(); ++i) {
         if (predicate.test((AttachedVolumeBuilder)this.volumesAttached.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumesAttached. No match found.");
      } else {
         return this.setNewVolumesAttachedLike(index, this.buildVolumesAttached(index));
      }
   }

   public NodeStatusFluent addToVolumesInUse(int index, String item) {
      if (this.volumesInUse == null) {
         this.volumesInUse = new ArrayList();
      }

      this.volumesInUse.add(index, item);
      return this;
   }

   public NodeStatusFluent setToVolumesInUse(int index, String item) {
      if (this.volumesInUse == null) {
         this.volumesInUse = new ArrayList();
      }

      this.volumesInUse.set(index, item);
      return this;
   }

   public NodeStatusFluent addToVolumesInUse(String... items) {
      if (this.volumesInUse == null) {
         this.volumesInUse = new ArrayList();
      }

      for(String item : items) {
         this.volumesInUse.add(item);
      }

      return this;
   }

   public NodeStatusFluent addAllToVolumesInUse(Collection items) {
      if (this.volumesInUse == null) {
         this.volumesInUse = new ArrayList();
      }

      for(String item : items) {
         this.volumesInUse.add(item);
      }

      return this;
   }

   public NodeStatusFluent removeFromVolumesInUse(String... items) {
      if (this.volumesInUse == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumesInUse.remove(item);
         }

         return this;
      }
   }

   public NodeStatusFluent removeAllFromVolumesInUse(Collection items) {
      if (this.volumesInUse == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumesInUse.remove(item);
         }

         return this;
      }
   }

   public List getVolumesInUse() {
      return this.volumesInUse;
   }

   public String getVolumesInUse(int index) {
      return (String)this.volumesInUse.get(index);
   }

   public String getFirstVolumesInUse() {
      return (String)this.volumesInUse.get(0);
   }

   public String getLastVolumesInUse() {
      return (String)this.volumesInUse.get(this.volumesInUse.size() - 1);
   }

   public String getMatchingVolumesInUse(Predicate predicate) {
      for(String item : this.volumesInUse) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVolumesInUse(Predicate predicate) {
      for(String item : this.volumesInUse) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeStatusFluent withVolumesInUse(List volumesInUse) {
      if (volumesInUse != null) {
         this.volumesInUse = new ArrayList();

         for(String item : volumesInUse) {
            this.addToVolumesInUse(item);
         }
      } else {
         this.volumesInUse = null;
      }

      return this;
   }

   public NodeStatusFluent withVolumesInUse(String... volumesInUse) {
      if (this.volumesInUse != null) {
         this.volumesInUse.clear();
         this._visitables.remove("volumesInUse");
      }

      if (volumesInUse != null) {
         for(String item : volumesInUse) {
            this.addToVolumesInUse(item);
         }
      }

      return this;
   }

   public boolean hasVolumesInUse() {
      return this.volumesInUse != null && !this.volumesInUse.isEmpty();
   }

   public NodeStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeStatusFluent removeFromAdditionalProperties(Map map) {
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

   public NodeStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeStatusFluent that = (NodeStatusFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.allocatable, that.allocatable)) {
               return false;
            } else if (!Objects.equals(this.capacity, that.capacity)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.config, that.config)) {
               return false;
            } else if (!Objects.equals(this.daemonEndpoints, that.daemonEndpoints)) {
               return false;
            } else if (!Objects.equals(this.features, that.features)) {
               return false;
            } else if (!Objects.equals(this.images, that.images)) {
               return false;
            } else if (!Objects.equals(this.nodeInfo, that.nodeInfo)) {
               return false;
            } else if (!Objects.equals(this.phase, that.phase)) {
               return false;
            } else if (!Objects.equals(this.runtimeHandlers, that.runtimeHandlers)) {
               return false;
            } else if (!Objects.equals(this.volumesAttached, that.volumesAttached)) {
               return false;
            } else if (!Objects.equals(this.volumesInUse, that.volumesInUse)) {
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
      return Objects.hash(new Object[]{this.addresses, this.allocatable, this.capacity, this.conditions, this.config, this.daemonEndpoints, this.features, this.images, this.nodeInfo, this.phase, this.runtimeHandlers, this.volumesAttached, this.volumesInUse, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.allocatable != null && !this.allocatable.isEmpty()) {
         sb.append("allocatable:");
         sb.append(this.allocatable + ",");
      }

      if (this.capacity != null && !this.capacity.isEmpty()) {
         sb.append("capacity:");
         sb.append(this.capacity + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.config != null) {
         sb.append("config:");
         sb.append(this.config + ",");
      }

      if (this.daemonEndpoints != null) {
         sb.append("daemonEndpoints:");
         sb.append(this.daemonEndpoints + ",");
      }

      if (this.features != null) {
         sb.append("features:");
         sb.append(this.features + ",");
      }

      if (this.images != null && !this.images.isEmpty()) {
         sb.append("images:");
         sb.append(this.images + ",");
      }

      if (this.nodeInfo != null) {
         sb.append("nodeInfo:");
         sb.append(this.nodeInfo + ",");
      }

      if (this.phase != null) {
         sb.append("phase:");
         sb.append(this.phase + ",");
      }

      if (this.runtimeHandlers != null && !this.runtimeHandlers.isEmpty()) {
         sb.append("runtimeHandlers:");
         sb.append(this.runtimeHandlers + ",");
      }

      if (this.volumesAttached != null && !this.volumesAttached.isEmpty()) {
         sb.append("volumesAttached:");
         sb.append(this.volumesAttached + ",");
      }

      if (this.volumesInUse != null && !this.volumesInUse.isEmpty()) {
         sb.append("volumesInUse:");
         sb.append(this.volumesInUse + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AddressesNested extends NodeAddressFluent implements Nested {
      NodeAddressBuilder builder;
      int index;

      AddressesNested(int index, NodeAddress item) {
         this.index = index;
         this.builder = new NodeAddressBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.setToAddresses(this.index, this.builder.build());
      }

      public Object endAddress() {
         return this.and();
      }
   }

   public class ConditionsNested extends NodeConditionFluent implements Nested {
      NodeConditionBuilder builder;
      int index;

      ConditionsNested(int index, NodeCondition item) {
         this.index = index;
         this.builder = new NodeConditionBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class ConfigNested extends NodeConfigStatusFluent implements Nested {
      NodeConfigStatusBuilder builder;

      ConfigNested(NodeConfigStatus item) {
         this.builder = new NodeConfigStatusBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.withConfig(this.builder.build());
      }

      public Object endConfig() {
         return this.and();
      }
   }

   public class DaemonEndpointsNested extends NodeDaemonEndpointsFluent implements Nested {
      NodeDaemonEndpointsBuilder builder;

      DaemonEndpointsNested(NodeDaemonEndpoints item) {
         this.builder = new NodeDaemonEndpointsBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.withDaemonEndpoints(this.builder.build());
      }

      public Object endDaemonEndpoints() {
         return this.and();
      }
   }

   public class FeaturesNested extends NodeFeaturesFluent implements Nested {
      NodeFeaturesBuilder builder;

      FeaturesNested(NodeFeatures item) {
         this.builder = new NodeFeaturesBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.withFeatures(this.builder.build());
      }

      public Object endFeatures() {
         return this.and();
      }
   }

   public class ImagesNested extends ContainerImageFluent implements Nested {
      ContainerImageBuilder builder;
      int index;

      ImagesNested(int index, ContainerImage item) {
         this.index = index;
         this.builder = new ContainerImageBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.setToImages(this.index, this.builder.build());
      }

      public Object endImage() {
         return this.and();
      }
   }

   public class NodeInfoNested extends NodeSystemInfoFluent implements Nested {
      NodeSystemInfoBuilder builder;

      NodeInfoNested(NodeSystemInfo item) {
         this.builder = new NodeSystemInfoBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.withNodeInfo(this.builder.build());
      }

      public Object endNodeInfo() {
         return this.and();
      }
   }

   public class RuntimeHandlersNested extends NodeRuntimeHandlerFluent implements Nested {
      NodeRuntimeHandlerBuilder builder;
      int index;

      RuntimeHandlersNested(int index, NodeRuntimeHandler item) {
         this.index = index;
         this.builder = new NodeRuntimeHandlerBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.setToRuntimeHandlers(this.index, this.builder.build());
      }

      public Object endRuntimeHandler() {
         return this.and();
      }
   }

   public class VolumesAttachedNested extends AttachedVolumeFluent implements Nested {
      AttachedVolumeBuilder builder;
      int index;

      VolumesAttachedNested(int index, AttachedVolume item) {
         this.index = index;
         this.builder = new AttachedVolumeBuilder(this, item);
      }

      public Object and() {
         return NodeStatusFluent.this.setToVolumesAttached(this.index, this.builder.build());
      }

      public Object endVolumesAttached() {
         return this.and();
      }
   }
}
