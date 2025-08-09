package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import io.fabric8.kubernetes.api.model.TopologySelectorTerm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class StorageClassFluent extends BaseFluent {
   private Boolean allowVolumeExpansion;
   private List allowedTopologies = new ArrayList();
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private List mountOptions = new ArrayList();
   private Map parameters;
   private String provisioner;
   private String reclaimPolicy;
   private String volumeBindingMode;
   private Map additionalProperties;

   public StorageClassFluent() {
   }

   public StorageClassFluent(StorageClass instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StorageClass instance) {
      instance = instance != null ? instance : new StorageClass();
      if (instance != null) {
         this.withAllowVolumeExpansion(instance.getAllowVolumeExpansion());
         this.withAllowedTopologies(instance.getAllowedTopologies());
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withMountOptions(instance.getMountOptions());
         this.withParameters(instance.getParameters());
         this.withProvisioner(instance.getProvisioner());
         this.withReclaimPolicy(instance.getReclaimPolicy());
         this.withVolumeBindingMode(instance.getVolumeBindingMode());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowVolumeExpansion() {
      return this.allowVolumeExpansion;
   }

   public StorageClassFluent withAllowVolumeExpansion(Boolean allowVolumeExpansion) {
      this.allowVolumeExpansion = allowVolumeExpansion;
      return this;
   }

   public boolean hasAllowVolumeExpansion() {
      return this.allowVolumeExpansion != null;
   }

   public StorageClassFluent addToAllowedTopologies(int index, TopologySelectorTerm item) {
      if (this.allowedTopologies == null) {
         this.allowedTopologies = new ArrayList();
      }

      this.allowedTopologies.add(index, item);
      return this;
   }

   public StorageClassFluent setToAllowedTopologies(int index, TopologySelectorTerm item) {
      if (this.allowedTopologies == null) {
         this.allowedTopologies = new ArrayList();
      }

      this.allowedTopologies.set(index, item);
      return this;
   }

   public StorageClassFluent addToAllowedTopologies(TopologySelectorTerm... items) {
      if (this.allowedTopologies == null) {
         this.allowedTopologies = new ArrayList();
      }

      for(TopologySelectorTerm item : items) {
         this.allowedTopologies.add(item);
      }

      return this;
   }

   public StorageClassFluent addAllToAllowedTopologies(Collection items) {
      if (this.allowedTopologies == null) {
         this.allowedTopologies = new ArrayList();
      }

      for(TopologySelectorTerm item : items) {
         this.allowedTopologies.add(item);
      }

      return this;
   }

   public StorageClassFluent removeFromAllowedTopologies(TopologySelectorTerm... items) {
      if (this.allowedTopologies == null) {
         return this;
      } else {
         for(TopologySelectorTerm item : items) {
            this.allowedTopologies.remove(item);
         }

         return this;
      }
   }

   public StorageClassFluent removeAllFromAllowedTopologies(Collection items) {
      if (this.allowedTopologies == null) {
         return this;
      } else {
         for(TopologySelectorTerm item : items) {
            this.allowedTopologies.remove(item);
         }

         return this;
      }
   }

   public List getAllowedTopologies() {
      return this.allowedTopologies;
   }

   public TopologySelectorTerm getAllowedTopology(int index) {
      return (TopologySelectorTerm)this.allowedTopologies.get(index);
   }

   public TopologySelectorTerm getFirstAllowedTopology() {
      return (TopologySelectorTerm)this.allowedTopologies.get(0);
   }

   public TopologySelectorTerm getLastAllowedTopology() {
      return (TopologySelectorTerm)this.allowedTopologies.get(this.allowedTopologies.size() - 1);
   }

   public TopologySelectorTerm getMatchingAllowedTopology(Predicate predicate) {
      for(TopologySelectorTerm item : this.allowedTopologies) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedTopology(Predicate predicate) {
      for(TopologySelectorTerm item : this.allowedTopologies) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StorageClassFluent withAllowedTopologies(List allowedTopologies) {
      if (allowedTopologies != null) {
         this.allowedTopologies = new ArrayList();

         for(TopologySelectorTerm item : allowedTopologies) {
            this.addToAllowedTopologies(item);
         }
      } else {
         this.allowedTopologies = null;
      }

      return this;
   }

   public StorageClassFluent withAllowedTopologies(TopologySelectorTerm... allowedTopologies) {
      if (this.allowedTopologies != null) {
         this.allowedTopologies.clear();
         this._visitables.remove("allowedTopologies");
      }

      if (allowedTopologies != null) {
         for(TopologySelectorTerm item : allowedTopologies) {
            this.addToAllowedTopologies(item);
         }
      }

      return this;
   }

   public boolean hasAllowedTopologies() {
      return this.allowedTopologies != null && !this.allowedTopologies.isEmpty();
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public StorageClassFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public StorageClassFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public StorageClassFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public StorageClassFluent addToMountOptions(int index, String item) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      this.mountOptions.add(index, item);
      return this;
   }

   public StorageClassFluent setToMountOptions(int index, String item) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      this.mountOptions.set(index, item);
      return this;
   }

   public StorageClassFluent addToMountOptions(String... items) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      for(String item : items) {
         this.mountOptions.add(item);
      }

      return this;
   }

   public StorageClassFluent addAllToMountOptions(Collection items) {
      if (this.mountOptions == null) {
         this.mountOptions = new ArrayList();
      }

      for(String item : items) {
         this.mountOptions.add(item);
      }

      return this;
   }

   public StorageClassFluent removeFromMountOptions(String... items) {
      if (this.mountOptions == null) {
         return this;
      } else {
         for(String item : items) {
            this.mountOptions.remove(item);
         }

         return this;
      }
   }

   public StorageClassFluent removeAllFromMountOptions(Collection items) {
      if (this.mountOptions == null) {
         return this;
      } else {
         for(String item : items) {
            this.mountOptions.remove(item);
         }

         return this;
      }
   }

   public List getMountOptions() {
      return this.mountOptions;
   }

   public String getMountOption(int index) {
      return (String)this.mountOptions.get(index);
   }

   public String getFirstMountOption() {
      return (String)this.mountOptions.get(0);
   }

   public String getLastMountOption() {
      return (String)this.mountOptions.get(this.mountOptions.size() - 1);
   }

   public String getMatchingMountOption(Predicate predicate) {
      for(String item : this.mountOptions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMountOption(Predicate predicate) {
      for(String item : this.mountOptions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StorageClassFluent withMountOptions(List mountOptions) {
      if (mountOptions != null) {
         this.mountOptions = new ArrayList();

         for(String item : mountOptions) {
            this.addToMountOptions(item);
         }
      } else {
         this.mountOptions = null;
      }

      return this;
   }

   public StorageClassFluent withMountOptions(String... mountOptions) {
      if (this.mountOptions != null) {
         this.mountOptions.clear();
         this._visitables.remove("mountOptions");
      }

      if (mountOptions != null) {
         for(String item : mountOptions) {
            this.addToMountOptions(item);
         }
      }

      return this;
   }

   public boolean hasMountOptions() {
      return this.mountOptions != null && !this.mountOptions.isEmpty();
   }

   public StorageClassFluent addToParameters(String key, String value) {
      if (this.parameters == null && key != null && value != null) {
         this.parameters = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.parameters.put(key, value);
      }

      return this;
   }

   public StorageClassFluent addToParameters(Map map) {
      if (this.parameters == null && map != null) {
         this.parameters = new LinkedHashMap();
      }

      if (map != null) {
         this.parameters.putAll(map);
      }

      return this;
   }

   public StorageClassFluent removeFromParameters(String key) {
      if (this.parameters == null) {
         return this;
      } else {
         if (key != null && this.parameters != null) {
            this.parameters.remove(key);
         }

         return this;
      }
   }

   public StorageClassFluent removeFromParameters(Map map) {
      if (this.parameters == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.parameters != null) {
                  this.parameters.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getParameters() {
      return this.parameters;
   }

   public StorageClassFluent withParameters(Map parameters) {
      if (parameters == null) {
         this.parameters = null;
      } else {
         this.parameters = new LinkedHashMap(parameters);
      }

      return this;
   }

   public boolean hasParameters() {
      return this.parameters != null;
   }

   public String getProvisioner() {
      return this.provisioner;
   }

   public StorageClassFluent withProvisioner(String provisioner) {
      this.provisioner = provisioner;
      return this;
   }

   public boolean hasProvisioner() {
      return this.provisioner != null;
   }

   public String getReclaimPolicy() {
      return this.reclaimPolicy;
   }

   public StorageClassFluent withReclaimPolicy(String reclaimPolicy) {
      this.reclaimPolicy = reclaimPolicy;
      return this;
   }

   public boolean hasReclaimPolicy() {
      return this.reclaimPolicy != null;
   }

   public String getVolumeBindingMode() {
      return this.volumeBindingMode;
   }

   public StorageClassFluent withVolumeBindingMode(String volumeBindingMode) {
      this.volumeBindingMode = volumeBindingMode;
      return this;
   }

   public boolean hasVolumeBindingMode() {
      return this.volumeBindingMode != null;
   }

   public StorageClassFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StorageClassFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StorageClassFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StorageClassFluent removeFromAdditionalProperties(Map map) {
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

   public StorageClassFluent withAdditionalProperties(Map additionalProperties) {
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
            StorageClassFluent that = (StorageClassFluent)o;
            if (!Objects.equals(this.allowVolumeExpansion, that.allowVolumeExpansion)) {
               return false;
            } else if (!Objects.equals(this.allowedTopologies, that.allowedTopologies)) {
               return false;
            } else if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.mountOptions, that.mountOptions)) {
               return false;
            } else if (!Objects.equals(this.parameters, that.parameters)) {
               return false;
            } else if (!Objects.equals(this.provisioner, that.provisioner)) {
               return false;
            } else if (!Objects.equals(this.reclaimPolicy, that.reclaimPolicy)) {
               return false;
            } else if (!Objects.equals(this.volumeBindingMode, that.volumeBindingMode)) {
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
      return Objects.hash(new Object[]{this.allowVolumeExpansion, this.allowedTopologies, this.apiVersion, this.kind, this.metadata, this.mountOptions, this.parameters, this.provisioner, this.reclaimPolicy, this.volumeBindingMode, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowVolumeExpansion != null) {
         sb.append("allowVolumeExpansion:");
         sb.append(this.allowVolumeExpansion + ",");
      }

      if (this.allowedTopologies != null && !this.allowedTopologies.isEmpty()) {
         sb.append("allowedTopologies:");
         sb.append(this.allowedTopologies + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.mountOptions != null && !this.mountOptions.isEmpty()) {
         sb.append("mountOptions:");
         sb.append(this.mountOptions + ",");
      }

      if (this.parameters != null && !this.parameters.isEmpty()) {
         sb.append("parameters:");
         sb.append(this.parameters + ",");
      }

      if (this.provisioner != null) {
         sb.append("provisioner:");
         sb.append(this.provisioner + ",");
      }

      if (this.reclaimPolicy != null) {
         sb.append("reclaimPolicy:");
         sb.append(this.reclaimPolicy + ",");
      }

      if (this.volumeBindingMode != null) {
         sb.append("volumeBindingMode:");
         sb.append(this.volumeBindingMode + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public StorageClassFluent withAllowVolumeExpansion() {
      return this.withAllowVolumeExpansion(true);
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return StorageClassFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
