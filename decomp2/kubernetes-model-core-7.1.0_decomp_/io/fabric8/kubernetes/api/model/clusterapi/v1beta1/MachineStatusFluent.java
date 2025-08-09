package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.NodeSystemInfo;
import io.fabric8.kubernetes.api.model.NodeSystemInfoBuilder;
import io.fabric8.kubernetes.api.model.NodeSystemInfoFluent;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class MachineStatusFluent extends BaseFluent {
   private ArrayList addresses = new ArrayList();
   private Boolean bootstrapReady;
   private String certificatesExpiryDate;
   private ArrayList conditions = new ArrayList();
   private MachineDeletionStatusBuilder deletion;
   private String failureMessage;
   private String failureReason;
   private Boolean infrastructureReady;
   private String lastUpdated;
   private NodeSystemInfoBuilder nodeInfo;
   private ObjectReferenceBuilder nodeRef;
   private Long observedGeneration;
   private String phase;
   private MachineV1Beta2StatusBuilder v1beta2;
   private Map additionalProperties;

   public MachineStatusFluent() {
   }

   public MachineStatusFluent(MachineStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MachineStatus instance) {
      instance = instance != null ? instance : new MachineStatus();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withBootstrapReady(instance.getBootstrapReady());
         this.withCertificatesExpiryDate(instance.getCertificatesExpiryDate());
         this.withConditions(instance.getConditions());
         this.withDeletion(instance.getDeletion());
         this.withFailureMessage(instance.getFailureMessage());
         this.withFailureReason(instance.getFailureReason());
         this.withInfrastructureReady(instance.getInfrastructureReady());
         this.withLastUpdated(instance.getLastUpdated());
         this.withNodeInfo(instance.getNodeInfo());
         this.withNodeRef(instance.getNodeRef());
         this.withObservedGeneration(instance.getObservedGeneration());
         this.withPhase(instance.getPhase());
         this.withV1beta2(instance.getV1beta2());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MachineStatusFluent addToAddresses(int index, MachineAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      MachineAddressBuilder builder = new MachineAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").add(index, builder);
         this.addresses.add(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public MachineStatusFluent setToAddresses(int index, MachineAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      MachineAddressBuilder builder = new MachineAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").set(index, builder);
         this.addresses.set(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public MachineStatusFluent addToAddresses(MachineAddress... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(MachineAddress item : items) {
         MachineAddressBuilder builder = new MachineAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public MachineStatusFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(MachineAddress item : items) {
         MachineAddressBuilder builder = new MachineAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public MachineStatusFluent removeFromAddresses(MachineAddress... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(MachineAddress item : items) {
            MachineAddressBuilder builder = new MachineAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public MachineStatusFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(MachineAddress item : items) {
            MachineAddressBuilder builder = new MachineAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public MachineStatusFluent removeMatchingFromAddresses(Predicate predicate) {
      if (this.addresses == null) {
         return this;
      } else {
         Iterator<MachineAddressBuilder> each = this.addresses.iterator();
         List visitables = this._visitables.get("addresses");

         while(each.hasNext()) {
            MachineAddressBuilder builder = (MachineAddressBuilder)each.next();
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

   public MachineAddress buildAddress(int index) {
      return ((MachineAddressBuilder)this.addresses.get(index)).build();
   }

   public MachineAddress buildFirstAddress() {
      return ((MachineAddressBuilder)this.addresses.get(0)).build();
   }

   public MachineAddress buildLastAddress() {
      return ((MachineAddressBuilder)this.addresses.get(this.addresses.size() - 1)).build();
   }

   public MachineAddress buildMatchingAddress(Predicate predicate) {
      for(MachineAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(MachineAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MachineStatusFluent withAddresses(List addresses) {
      if (this.addresses != null) {
         this._visitables.get("addresses").clear();
      }

      if (addresses != null) {
         this.addresses = new ArrayList();

         for(MachineAddress item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public MachineStatusFluent withAddresses(MachineAddress... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(MachineAddress item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public MachineStatusFluent addNewAddress(String address, String type) {
      return this.addToAddresses(new MachineAddress(address, type));
   }

   public AddressesNested addNewAddress() {
      return new AddressesNested(-1, (MachineAddress)null);
   }

   public AddressesNested addNewAddressLike(MachineAddress item) {
      return new AddressesNested(-1, item);
   }

   public AddressesNested setNewAddressLike(int index, MachineAddress item) {
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
         if (predicate.test((MachineAddressBuilder)this.addresses.get(i))) {
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

   public Boolean getBootstrapReady() {
      return this.bootstrapReady;
   }

   public MachineStatusFluent withBootstrapReady(Boolean bootstrapReady) {
      this.bootstrapReady = bootstrapReady;
      return this;
   }

   public boolean hasBootstrapReady() {
      return this.bootstrapReady != null;
   }

   public String getCertificatesExpiryDate() {
      return this.certificatesExpiryDate;
   }

   public MachineStatusFluent withCertificatesExpiryDate(String certificatesExpiryDate) {
      this.certificatesExpiryDate = certificatesExpiryDate;
      return this;
   }

   public boolean hasCertificatesExpiryDate() {
      return this.certificatesExpiryDate != null;
   }

   public MachineStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ConditionBuilder builder = new ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      ConditionBuilder builder = new ConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         ConditionBuilder builder = new ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         ConditionBuilder builder = new ConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public MachineStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            ConditionBuilder builder = new ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public MachineStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            ConditionBuilder builder = new ConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public MachineStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<ConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            ConditionBuilder builder = (ConditionBuilder)each.next();
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

   public Condition buildCondition(int index) {
      return ((ConditionBuilder)this.conditions.get(index)).build();
   }

   public Condition buildFirstCondition() {
      return ((ConditionBuilder)this.conditions.get(0)).build();
   }

   public Condition buildLastCondition() {
      return ((ConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public Condition buildMatchingCondition(Predicate predicate) {
      for(ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(ConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MachineStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public MachineStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (Condition)null);
   }

   public ConditionsNested addNewConditionLike(Condition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, Condition item) {
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
         if (predicate.test((ConditionBuilder)this.conditions.get(i))) {
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

   public MachineDeletionStatus buildDeletion() {
      return this.deletion != null ? this.deletion.build() : null;
   }

   public MachineStatusFluent withDeletion(MachineDeletionStatus deletion) {
      this._visitables.remove("deletion");
      if (deletion != null) {
         this.deletion = new MachineDeletionStatusBuilder(deletion);
         this._visitables.get("deletion").add(this.deletion);
      } else {
         this.deletion = null;
         this._visitables.get("deletion").remove(this.deletion);
      }

      return this;
   }

   public boolean hasDeletion() {
      return this.deletion != null;
   }

   public MachineStatusFluent withNewDeletion(String nodeDrainStartTime, String waitForNodeVolumeDetachStartTime) {
      return this.withDeletion(new MachineDeletionStatus(nodeDrainStartTime, waitForNodeVolumeDetachStartTime));
   }

   public DeletionNested withNewDeletion() {
      return new DeletionNested((MachineDeletionStatus)null);
   }

   public DeletionNested withNewDeletionLike(MachineDeletionStatus item) {
      return new DeletionNested(item);
   }

   public DeletionNested editDeletion() {
      return this.withNewDeletionLike((MachineDeletionStatus)Optional.ofNullable(this.buildDeletion()).orElse((Object)null));
   }

   public DeletionNested editOrNewDeletion() {
      return this.withNewDeletionLike((MachineDeletionStatus)Optional.ofNullable(this.buildDeletion()).orElse((new MachineDeletionStatusBuilder()).build()));
   }

   public DeletionNested editOrNewDeletionLike(MachineDeletionStatus item) {
      return this.withNewDeletionLike((MachineDeletionStatus)Optional.ofNullable(this.buildDeletion()).orElse(item));
   }

   public String getFailureMessage() {
      return this.failureMessage;
   }

   public MachineStatusFluent withFailureMessage(String failureMessage) {
      this.failureMessage = failureMessage;
      return this;
   }

   public boolean hasFailureMessage() {
      return this.failureMessage != null;
   }

   public String getFailureReason() {
      return this.failureReason;
   }

   public MachineStatusFluent withFailureReason(String failureReason) {
      this.failureReason = failureReason;
      return this;
   }

   public boolean hasFailureReason() {
      return this.failureReason != null;
   }

   public Boolean getInfrastructureReady() {
      return this.infrastructureReady;
   }

   public MachineStatusFluent withInfrastructureReady(Boolean infrastructureReady) {
      this.infrastructureReady = infrastructureReady;
      return this;
   }

   public boolean hasInfrastructureReady() {
      return this.infrastructureReady != null;
   }

   public String getLastUpdated() {
      return this.lastUpdated;
   }

   public MachineStatusFluent withLastUpdated(String lastUpdated) {
      this.lastUpdated = lastUpdated;
      return this;
   }

   public boolean hasLastUpdated() {
      return this.lastUpdated != null;
   }

   public NodeSystemInfo buildNodeInfo() {
      return this.nodeInfo != null ? this.nodeInfo.build() : null;
   }

   public MachineStatusFluent withNodeInfo(NodeSystemInfo nodeInfo) {
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

   public ObjectReference buildNodeRef() {
      return this.nodeRef != null ? this.nodeRef.build() : null;
   }

   public MachineStatusFluent withNodeRef(ObjectReference nodeRef) {
      this._visitables.remove("nodeRef");
      if (nodeRef != null) {
         this.nodeRef = new ObjectReferenceBuilder(nodeRef);
         this._visitables.get("nodeRef").add(this.nodeRef);
      } else {
         this.nodeRef = null;
         this._visitables.get("nodeRef").remove(this.nodeRef);
      }

      return this;
   }

   public boolean hasNodeRef() {
      return this.nodeRef != null;
   }

   public NodeRefNested withNewNodeRef() {
      return new NodeRefNested((ObjectReference)null);
   }

   public NodeRefNested withNewNodeRefLike(ObjectReference item) {
      return new NodeRefNested(item);
   }

   public NodeRefNested editNodeRef() {
      return this.withNewNodeRefLike((ObjectReference)Optional.ofNullable(this.buildNodeRef()).orElse((Object)null));
   }

   public NodeRefNested editOrNewNodeRef() {
      return this.withNewNodeRefLike((ObjectReference)Optional.ofNullable(this.buildNodeRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public NodeRefNested editOrNewNodeRefLike(ObjectReference item) {
      return this.withNewNodeRefLike((ObjectReference)Optional.ofNullable(this.buildNodeRef()).orElse(item));
   }

   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   public MachineStatusFluent withObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
      return this;
   }

   public boolean hasObservedGeneration() {
      return this.observedGeneration != null;
   }

   public String getPhase() {
      return this.phase;
   }

   public MachineStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public MachineV1Beta2Status buildV1beta2() {
      return this.v1beta2 != null ? this.v1beta2.build() : null;
   }

   public MachineStatusFluent withV1beta2(MachineV1Beta2Status v1beta2) {
      this._visitables.remove("v1beta2");
      if (v1beta2 != null) {
         this.v1beta2 = new MachineV1Beta2StatusBuilder(v1beta2);
         this._visitables.get("v1beta2").add(this.v1beta2);
      } else {
         this.v1beta2 = null;
         this._visitables.get("v1beta2").remove(this.v1beta2);
      }

      return this;
   }

   public boolean hasV1beta2() {
      return this.v1beta2 != null;
   }

   public V1beta2Nested withNewV1beta2() {
      return new V1beta2Nested((MachineV1Beta2Status)null);
   }

   public V1beta2Nested withNewV1beta2Like(MachineV1Beta2Status item) {
      return new V1beta2Nested(item);
   }

   public V1beta2Nested editV1beta2() {
      return this.withNewV1beta2Like((MachineV1Beta2Status)Optional.ofNullable(this.buildV1beta2()).orElse((Object)null));
   }

   public V1beta2Nested editOrNewV1beta2() {
      return this.withNewV1beta2Like((MachineV1Beta2Status)Optional.ofNullable(this.buildV1beta2()).orElse((new MachineV1Beta2StatusBuilder()).build()));
   }

   public V1beta2Nested editOrNewV1beta2Like(MachineV1Beta2Status item) {
      return this.withNewV1beta2Like((MachineV1Beta2Status)Optional.ofNullable(this.buildV1beta2()).orElse(item));
   }

   public MachineStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MachineStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MachineStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MachineStatusFluent removeFromAdditionalProperties(Map map) {
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

   public MachineStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            MachineStatusFluent that = (MachineStatusFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.bootstrapReady, that.bootstrapReady)) {
               return false;
            } else if (!Objects.equals(this.certificatesExpiryDate, that.certificatesExpiryDate)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.deletion, that.deletion)) {
               return false;
            } else if (!Objects.equals(this.failureMessage, that.failureMessage)) {
               return false;
            } else if (!Objects.equals(this.failureReason, that.failureReason)) {
               return false;
            } else if (!Objects.equals(this.infrastructureReady, that.infrastructureReady)) {
               return false;
            } else if (!Objects.equals(this.lastUpdated, that.lastUpdated)) {
               return false;
            } else if (!Objects.equals(this.nodeInfo, that.nodeInfo)) {
               return false;
            } else if (!Objects.equals(this.nodeRef, that.nodeRef)) {
               return false;
            } else if (!Objects.equals(this.observedGeneration, that.observedGeneration)) {
               return false;
            } else if (!Objects.equals(this.phase, that.phase)) {
               return false;
            } else if (!Objects.equals(this.v1beta2, that.v1beta2)) {
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
      return Objects.hash(new Object[]{this.addresses, this.bootstrapReady, this.certificatesExpiryDate, this.conditions, this.deletion, this.failureMessage, this.failureReason, this.infrastructureReady, this.lastUpdated, this.nodeInfo, this.nodeRef, this.observedGeneration, this.phase, this.v1beta2, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.bootstrapReady != null) {
         sb.append("bootstrapReady:");
         sb.append(this.bootstrapReady + ",");
      }

      if (this.certificatesExpiryDate != null) {
         sb.append("certificatesExpiryDate:");
         sb.append(this.certificatesExpiryDate + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.deletion != null) {
         sb.append("deletion:");
         sb.append(this.deletion + ",");
      }

      if (this.failureMessage != null) {
         sb.append("failureMessage:");
         sb.append(this.failureMessage + ",");
      }

      if (this.failureReason != null) {
         sb.append("failureReason:");
         sb.append(this.failureReason + ",");
      }

      if (this.infrastructureReady != null) {
         sb.append("infrastructureReady:");
         sb.append(this.infrastructureReady + ",");
      }

      if (this.lastUpdated != null) {
         sb.append("lastUpdated:");
         sb.append(this.lastUpdated + ",");
      }

      if (this.nodeInfo != null) {
         sb.append("nodeInfo:");
         sb.append(this.nodeInfo + ",");
      }

      if (this.nodeRef != null) {
         sb.append("nodeRef:");
         sb.append(this.nodeRef + ",");
      }

      if (this.observedGeneration != null) {
         sb.append("observedGeneration:");
         sb.append(this.observedGeneration + ",");
      }

      if (this.phase != null) {
         sb.append("phase:");
         sb.append(this.phase + ",");
      }

      if (this.v1beta2 != null) {
         sb.append("v1beta2:");
         sb.append(this.v1beta2 + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public MachineStatusFluent withBootstrapReady() {
      return this.withBootstrapReady(true);
   }

   public MachineStatusFluent withInfrastructureReady() {
      return this.withInfrastructureReady(true);
   }

   public class AddressesNested extends MachineAddressFluent implements Nested {
      MachineAddressBuilder builder;
      int index;

      AddressesNested(int index, MachineAddress item) {
         this.index = index;
         this.builder = new MachineAddressBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.setToAddresses(this.index, this.builder.build());
      }

      public Object endAddress() {
         return this.and();
      }
   }

   public class ConditionsNested extends ConditionFluent implements Nested {
      ConditionBuilder builder;
      int index;

      ConditionsNested(int index, Condition item) {
         this.index = index;
         this.builder = new ConditionBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class DeletionNested extends MachineDeletionStatusFluent implements Nested {
      MachineDeletionStatusBuilder builder;

      DeletionNested(MachineDeletionStatus item) {
         this.builder = new MachineDeletionStatusBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.withDeletion(this.builder.build());
      }

      public Object endDeletion() {
         return this.and();
      }
   }

   public class NodeInfoNested extends NodeSystemInfoFluent implements Nested {
      NodeSystemInfoBuilder builder;

      NodeInfoNested(NodeSystemInfo item) {
         this.builder = new NodeSystemInfoBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.withNodeInfo(this.builder.build());
      }

      public Object endNodeInfo() {
         return this.and();
      }
   }

   public class NodeRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      NodeRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.withNodeRef(this.builder.build());
      }

      public Object endNodeRef() {
         return this.and();
      }
   }

   public class V1beta2Nested extends MachineV1Beta2StatusFluent implements Nested {
      MachineV1Beta2StatusBuilder builder;

      V1beta2Nested(MachineV1Beta2Status item) {
         this.builder = new MachineV1Beta2StatusBuilder(this, item);
      }

      public Object and() {
         return MachineStatusFluent.this.withV1beta2(this.builder.build());
      }

      public Object endV1beta2() {
         return this.and();
      }
   }
}
