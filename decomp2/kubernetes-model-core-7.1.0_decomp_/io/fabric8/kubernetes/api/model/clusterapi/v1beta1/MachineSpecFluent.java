package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Duration;
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

public class MachineSpecFluent extends BaseFluent {
   private BootstrapBuilder bootstrap;
   private String clusterName;
   private String failureDomain;
   private ObjectReferenceBuilder infrastructureRef;
   private Duration nodeDeletionTimeout;
   private Duration nodeDrainTimeout;
   private Duration nodeVolumeDetachTimeout;
   private String providerID;
   private ArrayList readinessGates = new ArrayList();
   private String version;
   private Map additionalProperties;

   public MachineSpecFluent() {
   }

   public MachineSpecFluent(MachineSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MachineSpec instance) {
      instance = instance != null ? instance : new MachineSpec();
      if (instance != null) {
         this.withBootstrap(instance.getBootstrap());
         this.withClusterName(instance.getClusterName());
         this.withFailureDomain(instance.getFailureDomain());
         this.withInfrastructureRef(instance.getInfrastructureRef());
         this.withNodeDeletionTimeout(instance.getNodeDeletionTimeout());
         this.withNodeDrainTimeout(instance.getNodeDrainTimeout());
         this.withNodeVolumeDetachTimeout(instance.getNodeVolumeDetachTimeout());
         this.withProviderID(instance.getProviderID());
         this.withReadinessGates(instance.getReadinessGates());
         this.withVersion(instance.getVersion());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Bootstrap buildBootstrap() {
      return this.bootstrap != null ? this.bootstrap.build() : null;
   }

   public MachineSpecFluent withBootstrap(Bootstrap bootstrap) {
      this._visitables.remove("bootstrap");
      if (bootstrap != null) {
         this.bootstrap = new BootstrapBuilder(bootstrap);
         this._visitables.get("bootstrap").add(this.bootstrap);
      } else {
         this.bootstrap = null;
         this._visitables.get("bootstrap").remove(this.bootstrap);
      }

      return this;
   }

   public boolean hasBootstrap() {
      return this.bootstrap != null;
   }

   public BootstrapNested withNewBootstrap() {
      return new BootstrapNested((Bootstrap)null);
   }

   public BootstrapNested withNewBootstrapLike(Bootstrap item) {
      return new BootstrapNested(item);
   }

   public BootstrapNested editBootstrap() {
      return this.withNewBootstrapLike((Bootstrap)Optional.ofNullable(this.buildBootstrap()).orElse((Object)null));
   }

   public BootstrapNested editOrNewBootstrap() {
      return this.withNewBootstrapLike((Bootstrap)Optional.ofNullable(this.buildBootstrap()).orElse((new BootstrapBuilder()).build()));
   }

   public BootstrapNested editOrNewBootstrapLike(Bootstrap item) {
      return this.withNewBootstrapLike((Bootstrap)Optional.ofNullable(this.buildBootstrap()).orElse(item));
   }

   public String getClusterName() {
      return this.clusterName;
   }

   public MachineSpecFluent withClusterName(String clusterName) {
      this.clusterName = clusterName;
      return this;
   }

   public boolean hasClusterName() {
      return this.clusterName != null;
   }

   public String getFailureDomain() {
      return this.failureDomain;
   }

   public MachineSpecFluent withFailureDomain(String failureDomain) {
      this.failureDomain = failureDomain;
      return this;
   }

   public boolean hasFailureDomain() {
      return this.failureDomain != null;
   }

   public ObjectReference buildInfrastructureRef() {
      return this.infrastructureRef != null ? this.infrastructureRef.build() : null;
   }

   public MachineSpecFluent withInfrastructureRef(ObjectReference infrastructureRef) {
      this._visitables.remove("infrastructureRef");
      if (infrastructureRef != null) {
         this.infrastructureRef = new ObjectReferenceBuilder(infrastructureRef);
         this._visitables.get("infrastructureRef").add(this.infrastructureRef);
      } else {
         this.infrastructureRef = null;
         this._visitables.get("infrastructureRef").remove(this.infrastructureRef);
      }

      return this;
   }

   public boolean hasInfrastructureRef() {
      return this.infrastructureRef != null;
   }

   public InfrastructureRefNested withNewInfrastructureRef() {
      return new InfrastructureRefNested((ObjectReference)null);
   }

   public InfrastructureRefNested withNewInfrastructureRefLike(ObjectReference item) {
      return new InfrastructureRefNested(item);
   }

   public InfrastructureRefNested editInfrastructureRef() {
      return this.withNewInfrastructureRefLike((ObjectReference)Optional.ofNullable(this.buildInfrastructureRef()).orElse((Object)null));
   }

   public InfrastructureRefNested editOrNewInfrastructureRef() {
      return this.withNewInfrastructureRefLike((ObjectReference)Optional.ofNullable(this.buildInfrastructureRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public InfrastructureRefNested editOrNewInfrastructureRefLike(ObjectReference item) {
      return this.withNewInfrastructureRefLike((ObjectReference)Optional.ofNullable(this.buildInfrastructureRef()).orElse(item));
   }

   public Duration getNodeDeletionTimeout() {
      return this.nodeDeletionTimeout;
   }

   public MachineSpecFluent withNodeDeletionTimeout(Duration nodeDeletionTimeout) {
      this.nodeDeletionTimeout = nodeDeletionTimeout;
      return this;
   }

   public boolean hasNodeDeletionTimeout() {
      return this.nodeDeletionTimeout != null;
   }

   public Duration getNodeDrainTimeout() {
      return this.nodeDrainTimeout;
   }

   public MachineSpecFluent withNodeDrainTimeout(Duration nodeDrainTimeout) {
      this.nodeDrainTimeout = nodeDrainTimeout;
      return this;
   }

   public boolean hasNodeDrainTimeout() {
      return this.nodeDrainTimeout != null;
   }

   public Duration getNodeVolumeDetachTimeout() {
      return this.nodeVolumeDetachTimeout;
   }

   public MachineSpecFluent withNodeVolumeDetachTimeout(Duration nodeVolumeDetachTimeout) {
      this.nodeVolumeDetachTimeout = nodeVolumeDetachTimeout;
      return this;
   }

   public boolean hasNodeVolumeDetachTimeout() {
      return this.nodeVolumeDetachTimeout != null;
   }

   public String getProviderID() {
      return this.providerID;
   }

   public MachineSpecFluent withProviderID(String providerID) {
      this.providerID = providerID;
      return this;
   }

   public boolean hasProviderID() {
      return this.providerID != null;
   }

   public MachineSpecFluent addToReadinessGates(int index, MachineReadinessGate item) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
      if (index >= 0 && index < this.readinessGates.size()) {
         this._visitables.get("readinessGates").add(index, builder);
         this.readinessGates.add(index, builder);
      } else {
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public MachineSpecFluent setToReadinessGates(int index, MachineReadinessGate item) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
      if (index >= 0 && index < this.readinessGates.size()) {
         this._visitables.get("readinessGates").set(index, builder);
         this.readinessGates.set(index, builder);
      } else {
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public MachineSpecFluent addToReadinessGates(MachineReadinessGate... items) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      for(MachineReadinessGate item : items) {
         MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public MachineSpecFluent addAllToReadinessGates(Collection items) {
      if (this.readinessGates == null) {
         this.readinessGates = new ArrayList();
      }

      for(MachineReadinessGate item : items) {
         MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
         this._visitables.get("readinessGates").add(builder);
         this.readinessGates.add(builder);
      }

      return this;
   }

   public MachineSpecFluent removeFromReadinessGates(MachineReadinessGate... items) {
      if (this.readinessGates == null) {
         return this;
      } else {
         for(MachineReadinessGate item : items) {
            MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
            this._visitables.get("readinessGates").remove(builder);
            this.readinessGates.remove(builder);
         }

         return this;
      }
   }

   public MachineSpecFluent removeAllFromReadinessGates(Collection items) {
      if (this.readinessGates == null) {
         return this;
      } else {
         for(MachineReadinessGate item : items) {
            MachineReadinessGateBuilder builder = new MachineReadinessGateBuilder(item);
            this._visitables.get("readinessGates").remove(builder);
            this.readinessGates.remove(builder);
         }

         return this;
      }
   }

   public MachineSpecFluent removeMatchingFromReadinessGates(Predicate predicate) {
      if (this.readinessGates == null) {
         return this;
      } else {
         Iterator<MachineReadinessGateBuilder> each = this.readinessGates.iterator();
         List visitables = this._visitables.get("readinessGates");

         while(each.hasNext()) {
            MachineReadinessGateBuilder builder = (MachineReadinessGateBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildReadinessGates() {
      return this.readinessGates != null ? build(this.readinessGates) : null;
   }

   public MachineReadinessGate buildReadinessGate(int index) {
      return ((MachineReadinessGateBuilder)this.readinessGates.get(index)).build();
   }

   public MachineReadinessGate buildFirstReadinessGate() {
      return ((MachineReadinessGateBuilder)this.readinessGates.get(0)).build();
   }

   public MachineReadinessGate buildLastReadinessGate() {
      return ((MachineReadinessGateBuilder)this.readinessGates.get(this.readinessGates.size() - 1)).build();
   }

   public MachineReadinessGate buildMatchingReadinessGate(Predicate predicate) {
      for(MachineReadinessGateBuilder item : this.readinessGates) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingReadinessGate(Predicate predicate) {
      for(MachineReadinessGateBuilder item : this.readinessGates) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MachineSpecFluent withReadinessGates(List readinessGates) {
      if (this.readinessGates != null) {
         this._visitables.get("readinessGates").clear();
      }

      if (readinessGates != null) {
         this.readinessGates = new ArrayList();

         for(MachineReadinessGate item : readinessGates) {
            this.addToReadinessGates(item);
         }
      } else {
         this.readinessGates = null;
      }

      return this;
   }

   public MachineSpecFluent withReadinessGates(MachineReadinessGate... readinessGates) {
      if (this.readinessGates != null) {
         this.readinessGates.clear();
         this._visitables.remove("readinessGates");
      }

      if (readinessGates != null) {
         for(MachineReadinessGate item : readinessGates) {
            this.addToReadinessGates(item);
         }
      }

      return this;
   }

   public boolean hasReadinessGates() {
      return this.readinessGates != null && !this.readinessGates.isEmpty();
   }

   public MachineSpecFluent addNewReadinessGate(String conditionType) {
      return this.addToReadinessGates(new MachineReadinessGate(conditionType));
   }

   public ReadinessGatesNested addNewReadinessGate() {
      return new ReadinessGatesNested(-1, (MachineReadinessGate)null);
   }

   public ReadinessGatesNested addNewReadinessGateLike(MachineReadinessGate item) {
      return new ReadinessGatesNested(-1, item);
   }

   public ReadinessGatesNested setNewReadinessGateLike(int index, MachineReadinessGate item) {
      return new ReadinessGatesNested(index, item);
   }

   public ReadinessGatesNested editReadinessGate(int index) {
      if (this.readinessGates.size() <= index) {
         throw new RuntimeException("Can't edit readinessGates. Index exceeds size.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public ReadinessGatesNested editFirstReadinessGate() {
      if (this.readinessGates.size() == 0) {
         throw new RuntimeException("Can't edit first readinessGates. The list is empty.");
      } else {
         return this.setNewReadinessGateLike(0, this.buildReadinessGate(0));
      }
   }

   public ReadinessGatesNested editLastReadinessGate() {
      int index = this.readinessGates.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last readinessGates. The list is empty.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public ReadinessGatesNested editMatchingReadinessGate(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.readinessGates.size(); ++i) {
         if (predicate.test((MachineReadinessGateBuilder)this.readinessGates.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching readinessGates. No match found.");
      } else {
         return this.setNewReadinessGateLike(index, this.buildReadinessGate(index));
      }
   }

   public String getVersion() {
      return this.version;
   }

   public MachineSpecFluent withVersion(String version) {
      this.version = version;
      return this;
   }

   public boolean hasVersion() {
      return this.version != null;
   }

   public MachineSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MachineSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MachineSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MachineSpecFluent removeFromAdditionalProperties(Map map) {
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

   public MachineSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            MachineSpecFluent that = (MachineSpecFluent)o;
            if (!Objects.equals(this.bootstrap, that.bootstrap)) {
               return false;
            } else if (!Objects.equals(this.clusterName, that.clusterName)) {
               return false;
            } else if (!Objects.equals(this.failureDomain, that.failureDomain)) {
               return false;
            } else if (!Objects.equals(this.infrastructureRef, that.infrastructureRef)) {
               return false;
            } else if (!Objects.equals(this.nodeDeletionTimeout, that.nodeDeletionTimeout)) {
               return false;
            } else if (!Objects.equals(this.nodeDrainTimeout, that.nodeDrainTimeout)) {
               return false;
            } else if (!Objects.equals(this.nodeVolumeDetachTimeout, that.nodeVolumeDetachTimeout)) {
               return false;
            } else if (!Objects.equals(this.providerID, that.providerID)) {
               return false;
            } else if (!Objects.equals(this.readinessGates, that.readinessGates)) {
               return false;
            } else if (!Objects.equals(this.version, that.version)) {
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
      return Objects.hash(new Object[]{this.bootstrap, this.clusterName, this.failureDomain, this.infrastructureRef, this.nodeDeletionTimeout, this.nodeDrainTimeout, this.nodeVolumeDetachTimeout, this.providerID, this.readinessGates, this.version, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.bootstrap != null) {
         sb.append("bootstrap:");
         sb.append(this.bootstrap + ",");
      }

      if (this.clusterName != null) {
         sb.append("clusterName:");
         sb.append(this.clusterName + ",");
      }

      if (this.failureDomain != null) {
         sb.append("failureDomain:");
         sb.append(this.failureDomain + ",");
      }

      if (this.infrastructureRef != null) {
         sb.append("infrastructureRef:");
         sb.append(this.infrastructureRef + ",");
      }

      if (this.nodeDeletionTimeout != null) {
         sb.append("nodeDeletionTimeout:");
         sb.append(this.nodeDeletionTimeout + ",");
      }

      if (this.nodeDrainTimeout != null) {
         sb.append("nodeDrainTimeout:");
         sb.append(this.nodeDrainTimeout + ",");
      }

      if (this.nodeVolumeDetachTimeout != null) {
         sb.append("nodeVolumeDetachTimeout:");
         sb.append(this.nodeVolumeDetachTimeout + ",");
      }

      if (this.providerID != null) {
         sb.append("providerID:");
         sb.append(this.providerID + ",");
      }

      if (this.readinessGates != null && !this.readinessGates.isEmpty()) {
         sb.append("readinessGates:");
         sb.append(this.readinessGates + ",");
      }

      if (this.version != null) {
         sb.append("version:");
         sb.append(this.version + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BootstrapNested extends BootstrapFluent implements Nested {
      BootstrapBuilder builder;

      BootstrapNested(Bootstrap item) {
         this.builder = new BootstrapBuilder(this, item);
      }

      public Object and() {
         return MachineSpecFluent.this.withBootstrap(this.builder.build());
      }

      public Object endBootstrap() {
         return this.and();
      }
   }

   public class InfrastructureRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      InfrastructureRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return MachineSpecFluent.this.withInfrastructureRef(this.builder.build());
      }

      public Object endInfrastructureRef() {
         return this.and();
      }
   }

   public class ReadinessGatesNested extends MachineReadinessGateFluent implements Nested {
      MachineReadinessGateBuilder builder;
      int index;

      ReadinessGatesNested(int index, MachineReadinessGate item) {
         this.index = index;
         this.builder = new MachineReadinessGateBuilder(this, item);
      }

      public Object and() {
         return MachineSpecFluent.this.setToReadinessGates(this.index, this.builder.build());
      }

      public Object endReadinessGate() {
         return this.and();
      }
   }
}
