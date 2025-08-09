package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PersistentVolumeClaimSpecFluent extends BaseFluent {
   private List accessModes = new ArrayList();
   private TypedLocalObjectReferenceBuilder dataSource;
   private TypedObjectReferenceBuilder dataSourceRef;
   private VolumeResourceRequirementsBuilder resources;
   private LabelSelectorBuilder selector;
   private String storageClassName;
   private String volumeAttributesClassName;
   private String volumeMode;
   private String volumeName;
   private Map additionalProperties;

   public PersistentVolumeClaimSpecFluent() {
   }

   public PersistentVolumeClaimSpecFluent(PersistentVolumeClaimSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PersistentVolumeClaimSpec instance) {
      instance = instance != null ? instance : new PersistentVolumeClaimSpec();
      if (instance != null) {
         this.withAccessModes(instance.getAccessModes());
         this.withDataSource(instance.getDataSource());
         this.withDataSourceRef(instance.getDataSourceRef());
         this.withResources(instance.getResources());
         this.withSelector(instance.getSelector());
         this.withStorageClassName(instance.getStorageClassName());
         this.withVolumeAttributesClassName(instance.getVolumeAttributesClassName());
         this.withVolumeMode(instance.getVolumeMode());
         this.withVolumeName(instance.getVolumeName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PersistentVolumeClaimSpecFluent addToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.add(index, item);
      return this;
   }

   public PersistentVolumeClaimSpecFluent setToAccessModes(int index, String item) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      this.accessModes.set(index, item);
      return this;
   }

   public PersistentVolumeClaimSpecFluent addToAccessModes(String... items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeClaimSpecFluent addAllToAccessModes(Collection items) {
      if (this.accessModes == null) {
         this.accessModes = new ArrayList();
      }

      for(String item : items) {
         this.accessModes.add(item);
      }

      return this;
   }

   public PersistentVolumeClaimSpecFluent removeFromAccessModes(String... items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public PersistentVolumeClaimSpecFluent removeAllFromAccessModes(Collection items) {
      if (this.accessModes == null) {
         return this;
      } else {
         for(String item : items) {
            this.accessModes.remove(item);
         }

         return this;
      }
   }

   public List getAccessModes() {
      return this.accessModes;
   }

   public String getAccessMode(int index) {
      return (String)this.accessModes.get(index);
   }

   public String getFirstAccessMode() {
      return (String)this.accessModes.get(0);
   }

   public String getLastAccessMode() {
      return (String)this.accessModes.get(this.accessModes.size() - 1);
   }

   public String getMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAccessMode(Predicate predicate) {
      for(String item : this.accessModes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PersistentVolumeClaimSpecFluent withAccessModes(List accessModes) {
      if (accessModes != null) {
         this.accessModes = new ArrayList();

         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      } else {
         this.accessModes = null;
      }

      return this;
   }

   public PersistentVolumeClaimSpecFluent withAccessModes(String... accessModes) {
      if (this.accessModes != null) {
         this.accessModes.clear();
         this._visitables.remove("accessModes");
      }

      if (accessModes != null) {
         for(String item : accessModes) {
            this.addToAccessModes(item);
         }
      }

      return this;
   }

   public boolean hasAccessModes() {
      return this.accessModes != null && !this.accessModes.isEmpty();
   }

   public TypedLocalObjectReference buildDataSource() {
      return this.dataSource != null ? this.dataSource.build() : null;
   }

   public PersistentVolumeClaimSpecFluent withDataSource(TypedLocalObjectReference dataSource) {
      this._visitables.remove("dataSource");
      if (dataSource != null) {
         this.dataSource = new TypedLocalObjectReferenceBuilder(dataSource);
         this._visitables.get("dataSource").add(this.dataSource);
      } else {
         this.dataSource = null;
         this._visitables.get("dataSource").remove(this.dataSource);
      }

      return this;
   }

   public boolean hasDataSource() {
      return this.dataSource != null;
   }

   public PersistentVolumeClaimSpecFluent withNewDataSource(String apiGroup, String kind, String name) {
      return this.withDataSource(new TypedLocalObjectReference(apiGroup, kind, name));
   }

   public DataSourceNested withNewDataSource() {
      return new DataSourceNested((TypedLocalObjectReference)null);
   }

   public DataSourceNested withNewDataSourceLike(TypedLocalObjectReference item) {
      return new DataSourceNested(item);
   }

   public DataSourceNested editDataSource() {
      return this.withNewDataSourceLike((TypedLocalObjectReference)Optional.ofNullable(this.buildDataSource()).orElse((Object)null));
   }

   public DataSourceNested editOrNewDataSource() {
      return this.withNewDataSourceLike((TypedLocalObjectReference)Optional.ofNullable(this.buildDataSource()).orElse((new TypedLocalObjectReferenceBuilder()).build()));
   }

   public DataSourceNested editOrNewDataSourceLike(TypedLocalObjectReference item) {
      return this.withNewDataSourceLike((TypedLocalObjectReference)Optional.ofNullable(this.buildDataSource()).orElse(item));
   }

   public TypedObjectReference buildDataSourceRef() {
      return this.dataSourceRef != null ? this.dataSourceRef.build() : null;
   }

   public PersistentVolumeClaimSpecFluent withDataSourceRef(TypedObjectReference dataSourceRef) {
      this._visitables.remove("dataSourceRef");
      if (dataSourceRef != null) {
         this.dataSourceRef = new TypedObjectReferenceBuilder(dataSourceRef);
         this._visitables.get("dataSourceRef").add(this.dataSourceRef);
      } else {
         this.dataSourceRef = null;
         this._visitables.get("dataSourceRef").remove(this.dataSourceRef);
      }

      return this;
   }

   public boolean hasDataSourceRef() {
      return this.dataSourceRef != null;
   }

   public PersistentVolumeClaimSpecFluent withNewDataSourceRef(String apiGroup, String kind, String name, String namespace) {
      return this.withDataSourceRef(new TypedObjectReference(apiGroup, kind, name, namespace));
   }

   public DataSourceRefNested withNewDataSourceRef() {
      return new DataSourceRefNested((TypedObjectReference)null);
   }

   public DataSourceRefNested withNewDataSourceRefLike(TypedObjectReference item) {
      return new DataSourceRefNested(item);
   }

   public DataSourceRefNested editDataSourceRef() {
      return this.withNewDataSourceRefLike((TypedObjectReference)Optional.ofNullable(this.buildDataSourceRef()).orElse((Object)null));
   }

   public DataSourceRefNested editOrNewDataSourceRef() {
      return this.withNewDataSourceRefLike((TypedObjectReference)Optional.ofNullable(this.buildDataSourceRef()).orElse((new TypedObjectReferenceBuilder()).build()));
   }

   public DataSourceRefNested editOrNewDataSourceRefLike(TypedObjectReference item) {
      return this.withNewDataSourceRefLike((TypedObjectReference)Optional.ofNullable(this.buildDataSourceRef()).orElse(item));
   }

   public VolumeResourceRequirements buildResources() {
      return this.resources != null ? this.resources.build() : null;
   }

   public PersistentVolumeClaimSpecFluent withResources(VolumeResourceRequirements resources) {
      this._visitables.remove("resources");
      if (resources != null) {
         this.resources = new VolumeResourceRequirementsBuilder(resources);
         this._visitables.get("resources").add(this.resources);
      } else {
         this.resources = null;
         this._visitables.get("resources").remove(this.resources);
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null;
   }

   public ResourcesNested withNewResources() {
      return new ResourcesNested((VolumeResourceRequirements)null);
   }

   public ResourcesNested withNewResourcesLike(VolumeResourceRequirements item) {
      return new ResourcesNested(item);
   }

   public ResourcesNested editResources() {
      return this.withNewResourcesLike((VolumeResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((Object)null));
   }

   public ResourcesNested editOrNewResources() {
      return this.withNewResourcesLike((VolumeResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((new VolumeResourceRequirementsBuilder()).build()));
   }

   public ResourcesNested editOrNewResourcesLike(VolumeResourceRequirements item) {
      return this.withNewResourcesLike((VolumeResourceRequirements)Optional.ofNullable(this.buildResources()).orElse(item));
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public PersistentVolumeClaimSpecFluent withSelector(LabelSelector selector) {
      this._visitables.remove("selector");
      if (selector != null) {
         this.selector = new LabelSelectorBuilder(selector);
         this._visitables.get("selector").add(this.selector);
      } else {
         this.selector = null;
         this._visitables.get("selector").remove(this.selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public SelectorNested withNewSelector() {
      return new SelectorNested((LabelSelector)null);
   }

   public SelectorNested withNewSelectorLike(LabelSelector item) {
      return new SelectorNested(item);
   }

   public SelectorNested editSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((Object)null));
   }

   public SelectorNested editOrNewSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public SelectorNested editOrNewSelectorLike(LabelSelector item) {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse(item));
   }

   public String getStorageClassName() {
      return this.storageClassName;
   }

   public PersistentVolumeClaimSpecFluent withStorageClassName(String storageClassName) {
      this.storageClassName = storageClassName;
      return this;
   }

   public boolean hasStorageClassName() {
      return this.storageClassName != null;
   }

   public String getVolumeAttributesClassName() {
      return this.volumeAttributesClassName;
   }

   public PersistentVolumeClaimSpecFluent withVolumeAttributesClassName(String volumeAttributesClassName) {
      this.volumeAttributesClassName = volumeAttributesClassName;
      return this;
   }

   public boolean hasVolumeAttributesClassName() {
      return this.volumeAttributesClassName != null;
   }

   public String getVolumeMode() {
      return this.volumeMode;
   }

   public PersistentVolumeClaimSpecFluent withVolumeMode(String volumeMode) {
      this.volumeMode = volumeMode;
      return this;
   }

   public boolean hasVolumeMode() {
      return this.volumeMode != null;
   }

   public String getVolumeName() {
      return this.volumeName;
   }

   public PersistentVolumeClaimSpecFluent withVolumeName(String volumeName) {
      this.volumeName = volumeName;
      return this;
   }

   public boolean hasVolumeName() {
      return this.volumeName != null;
   }

   public PersistentVolumeClaimSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PersistentVolumeClaimSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PersistentVolumeClaimSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PersistentVolumeClaimSpecFluent removeFromAdditionalProperties(Map map) {
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

   public PersistentVolumeClaimSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PersistentVolumeClaimSpecFluent that = (PersistentVolumeClaimSpecFluent)o;
            if (!Objects.equals(this.accessModes, that.accessModes)) {
               return false;
            } else if (!Objects.equals(this.dataSource, that.dataSource)) {
               return false;
            } else if (!Objects.equals(this.dataSourceRef, that.dataSourceRef)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.storageClassName, that.storageClassName)) {
               return false;
            } else if (!Objects.equals(this.volumeAttributesClassName, that.volumeAttributesClassName)) {
               return false;
            } else if (!Objects.equals(this.volumeMode, that.volumeMode)) {
               return false;
            } else if (!Objects.equals(this.volumeName, that.volumeName)) {
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
      return Objects.hash(new Object[]{this.accessModes, this.dataSource, this.dataSourceRef, this.resources, this.selector, this.storageClassName, this.volumeAttributesClassName, this.volumeMode, this.volumeName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.accessModes != null && !this.accessModes.isEmpty()) {
         sb.append("accessModes:");
         sb.append(this.accessModes + ",");
      }

      if (this.dataSource != null) {
         sb.append("dataSource:");
         sb.append(this.dataSource + ",");
      }

      if (this.dataSourceRef != null) {
         sb.append("dataSourceRef:");
         sb.append(this.dataSourceRef + ",");
      }

      if (this.resources != null) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.storageClassName != null) {
         sb.append("storageClassName:");
         sb.append(this.storageClassName + ",");
      }

      if (this.volumeAttributesClassName != null) {
         sb.append("volumeAttributesClassName:");
         sb.append(this.volumeAttributesClassName + ",");
      }

      if (this.volumeMode != null) {
         sb.append("volumeMode:");
         sb.append(this.volumeMode + ",");
      }

      if (this.volumeName != null) {
         sb.append("volumeName:");
         sb.append(this.volumeName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DataSourceNested extends TypedLocalObjectReferenceFluent implements Nested {
      TypedLocalObjectReferenceBuilder builder;

      DataSourceNested(TypedLocalObjectReference item) {
         this.builder = new TypedLocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimSpecFluent.this.withDataSource(this.builder.build());
      }

      public Object endDataSource() {
         return this.and();
      }
   }

   public class DataSourceRefNested extends TypedObjectReferenceFluent implements Nested {
      TypedObjectReferenceBuilder builder;

      DataSourceRefNested(TypedObjectReference item) {
         this.builder = new TypedObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimSpecFluent.this.withDataSourceRef(this.builder.build());
      }

      public Object endDataSourceRef() {
         return this.and();
      }
   }

   public class ResourcesNested extends VolumeResourceRequirementsFluent implements Nested {
      VolumeResourceRequirementsBuilder builder;

      ResourcesNested(VolumeResourceRequirements item) {
         this.builder = new VolumeResourceRequirementsBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimSpecFluent.this.withResources(this.builder.build());
      }

      public Object endResources() {
         return this.and();
      }
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return PersistentVolumeClaimSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }
}
