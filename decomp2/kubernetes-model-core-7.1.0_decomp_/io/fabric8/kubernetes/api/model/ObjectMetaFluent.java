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
import java.util.function.Predicate;

public class ObjectMetaFluent extends BaseFluent {
   private Map annotations;
   private String creationTimestamp;
   private Long deletionGracePeriodSeconds;
   private String deletionTimestamp;
   private List finalizers = new ArrayList();
   private String generateName;
   private Long generation;
   private Map labels;
   private ArrayList managedFields = new ArrayList();
   private String name;
   private String namespace;
   private ArrayList ownerReferences = new ArrayList();
   private String resourceVersion;
   private String selfLink;
   private String uid;
   private Map additionalProperties;

   public ObjectMetaFluent() {
   }

   public ObjectMetaFluent(ObjectMeta instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ObjectMeta instance) {
      instance = instance != null ? instance : new ObjectMeta();
      if (instance != null) {
         this.withAnnotations(instance.getAnnotations());
         this.withCreationTimestamp(instance.getCreationTimestamp());
         this.withDeletionGracePeriodSeconds(instance.getDeletionGracePeriodSeconds());
         this.withDeletionTimestamp(instance.getDeletionTimestamp());
         this.withFinalizers(instance.getFinalizers());
         this.withGenerateName(instance.getGenerateName());
         this.withGeneration(instance.getGeneration());
         this.withLabels(instance.getLabels());
         this.withManagedFields(instance.getManagedFields());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withOwnerReferences(instance.getOwnerReferences());
         this.withResourceVersion(instance.getResourceVersion());
         this.withSelfLink(instance.getSelfLink());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ObjectMetaFluent addToAnnotations(String key, String value) {
      if (this.annotations == null && key != null && value != null) {
         this.annotations = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.annotations.put(key, value);
      }

      return this;
   }

   public ObjectMetaFluent addToAnnotations(Map map) {
      if (this.annotations == null && map != null) {
         this.annotations = new LinkedHashMap();
      }

      if (map != null) {
         this.annotations.putAll(map);
      }

      return this;
   }

   public ObjectMetaFluent removeFromAnnotations(String key) {
      if (this.annotations == null) {
         return this;
      } else {
         if (key != null && this.annotations != null) {
            this.annotations.remove(key);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeFromAnnotations(Map map) {
      if (this.annotations == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.annotations != null) {
                  this.annotations.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAnnotations() {
      return this.annotations;
   }

   public ObjectMetaFluent withAnnotations(Map annotations) {
      if (annotations == null) {
         this.annotations = null;
      } else {
         this.annotations = new LinkedHashMap(annotations);
      }

      return this;
   }

   public boolean hasAnnotations() {
      return this.annotations != null;
   }

   public String getCreationTimestamp() {
      return this.creationTimestamp;
   }

   public ObjectMetaFluent withCreationTimestamp(String creationTimestamp) {
      this.creationTimestamp = creationTimestamp;
      return this;
   }

   public boolean hasCreationTimestamp() {
      return this.creationTimestamp != null;
   }

   public Long getDeletionGracePeriodSeconds() {
      return this.deletionGracePeriodSeconds;
   }

   public ObjectMetaFluent withDeletionGracePeriodSeconds(Long deletionGracePeriodSeconds) {
      this.deletionGracePeriodSeconds = deletionGracePeriodSeconds;
      return this;
   }

   public boolean hasDeletionGracePeriodSeconds() {
      return this.deletionGracePeriodSeconds != null;
   }

   public String getDeletionTimestamp() {
      return this.deletionTimestamp;
   }

   public ObjectMetaFluent withDeletionTimestamp(String deletionTimestamp) {
      this.deletionTimestamp = deletionTimestamp;
      return this;
   }

   public boolean hasDeletionTimestamp() {
      return this.deletionTimestamp != null;
   }

   public ObjectMetaFluent addToFinalizers(int index, String item) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      this.finalizers.add(index, item);
      return this;
   }

   public ObjectMetaFluent setToFinalizers(int index, String item) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      this.finalizers.set(index, item);
      return this;
   }

   public ObjectMetaFluent addToFinalizers(String... items) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      for(String item : items) {
         this.finalizers.add(item);
      }

      return this;
   }

   public ObjectMetaFluent addAllToFinalizers(Collection items) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      for(String item : items) {
         this.finalizers.add(item);
      }

      return this;
   }

   public ObjectMetaFluent removeFromFinalizers(String... items) {
      if (this.finalizers == null) {
         return this;
      } else {
         for(String item : items) {
            this.finalizers.remove(item);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeAllFromFinalizers(Collection items) {
      if (this.finalizers == null) {
         return this;
      } else {
         for(String item : items) {
            this.finalizers.remove(item);
         }

         return this;
      }
   }

   public List getFinalizers() {
      return this.finalizers;
   }

   public String getFinalizer(int index) {
      return (String)this.finalizers.get(index);
   }

   public String getFirstFinalizer() {
      return (String)this.finalizers.get(0);
   }

   public String getLastFinalizer() {
      return (String)this.finalizers.get(this.finalizers.size() - 1);
   }

   public String getMatchingFinalizer(Predicate predicate) {
      for(String item : this.finalizers) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingFinalizer(Predicate predicate) {
      for(String item : this.finalizers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ObjectMetaFluent withFinalizers(List finalizers) {
      if (finalizers != null) {
         this.finalizers = new ArrayList();

         for(String item : finalizers) {
            this.addToFinalizers(item);
         }
      } else {
         this.finalizers = null;
      }

      return this;
   }

   public ObjectMetaFluent withFinalizers(String... finalizers) {
      if (this.finalizers != null) {
         this.finalizers.clear();
         this._visitables.remove("finalizers");
      }

      if (finalizers != null) {
         for(String item : finalizers) {
            this.addToFinalizers(item);
         }
      }

      return this;
   }

   public boolean hasFinalizers() {
      return this.finalizers != null && !this.finalizers.isEmpty();
   }

   public String getGenerateName() {
      return this.generateName;
   }

   public ObjectMetaFluent withGenerateName(String generateName) {
      this.generateName = generateName;
      return this;
   }

   public boolean hasGenerateName() {
      return this.generateName != null;
   }

   public Long getGeneration() {
      return this.generation;
   }

   public ObjectMetaFluent withGeneration(Long generation) {
      this.generation = generation;
      return this;
   }

   public boolean hasGeneration() {
      return this.generation != null;
   }

   public ObjectMetaFluent addToLabels(String key, String value) {
      if (this.labels == null && key != null && value != null) {
         this.labels = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.labels.put(key, value);
      }

      return this;
   }

   public ObjectMetaFluent addToLabels(Map map) {
      if (this.labels == null && map != null) {
         this.labels = new LinkedHashMap();
      }

      if (map != null) {
         this.labels.putAll(map);
      }

      return this;
   }

   public ObjectMetaFluent removeFromLabels(String key) {
      if (this.labels == null) {
         return this;
      } else {
         if (key != null && this.labels != null) {
            this.labels.remove(key);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeFromLabels(Map map) {
      if (this.labels == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.labels != null) {
                  this.labels.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLabels() {
      return this.labels;
   }

   public ObjectMetaFluent withLabels(Map labels) {
      if (labels == null) {
         this.labels = null;
      } else {
         this.labels = new LinkedHashMap(labels);
      }

      return this;
   }

   public boolean hasLabels() {
      return this.labels != null;
   }

   public ObjectMetaFluent addToManagedFields(int index, ManagedFieldsEntry item) {
      if (this.managedFields == null) {
         this.managedFields = new ArrayList();
      }

      ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
      if (index >= 0 && index < this.managedFields.size()) {
         this._visitables.get("managedFields").add(index, builder);
         this.managedFields.add(index, builder);
      } else {
         this._visitables.get("managedFields").add(builder);
         this.managedFields.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent setToManagedFields(int index, ManagedFieldsEntry item) {
      if (this.managedFields == null) {
         this.managedFields = new ArrayList();
      }

      ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
      if (index >= 0 && index < this.managedFields.size()) {
         this._visitables.get("managedFields").set(index, builder);
         this.managedFields.set(index, builder);
      } else {
         this._visitables.get("managedFields").add(builder);
         this.managedFields.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent addToManagedFields(ManagedFieldsEntry... items) {
      if (this.managedFields == null) {
         this.managedFields = new ArrayList();
      }

      for(ManagedFieldsEntry item : items) {
         ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
         this._visitables.get("managedFields").add(builder);
         this.managedFields.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent addAllToManagedFields(Collection items) {
      if (this.managedFields == null) {
         this.managedFields = new ArrayList();
      }

      for(ManagedFieldsEntry item : items) {
         ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
         this._visitables.get("managedFields").add(builder);
         this.managedFields.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent removeFromManagedFields(ManagedFieldsEntry... items) {
      if (this.managedFields == null) {
         return this;
      } else {
         for(ManagedFieldsEntry item : items) {
            ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
            this._visitables.get("managedFields").remove(builder);
            this.managedFields.remove(builder);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeAllFromManagedFields(Collection items) {
      if (this.managedFields == null) {
         return this;
      } else {
         for(ManagedFieldsEntry item : items) {
            ManagedFieldsEntryBuilder builder = new ManagedFieldsEntryBuilder(item);
            this._visitables.get("managedFields").remove(builder);
            this.managedFields.remove(builder);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeMatchingFromManagedFields(Predicate predicate) {
      if (this.managedFields == null) {
         return this;
      } else {
         Iterator<ManagedFieldsEntryBuilder> each = this.managedFields.iterator();
         List visitables = this._visitables.get("managedFields");

         while(each.hasNext()) {
            ManagedFieldsEntryBuilder builder = (ManagedFieldsEntryBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildManagedFields() {
      return this.managedFields != null ? build(this.managedFields) : null;
   }

   public ManagedFieldsEntry buildManagedField(int index) {
      return ((ManagedFieldsEntryBuilder)this.managedFields.get(index)).build();
   }

   public ManagedFieldsEntry buildFirstManagedField() {
      return ((ManagedFieldsEntryBuilder)this.managedFields.get(0)).build();
   }

   public ManagedFieldsEntry buildLastManagedField() {
      return ((ManagedFieldsEntryBuilder)this.managedFields.get(this.managedFields.size() - 1)).build();
   }

   public ManagedFieldsEntry buildMatchingManagedField(Predicate predicate) {
      for(ManagedFieldsEntryBuilder item : this.managedFields) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingManagedField(Predicate predicate) {
      for(ManagedFieldsEntryBuilder item : this.managedFields) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ObjectMetaFluent withManagedFields(List managedFields) {
      if (this.managedFields != null) {
         this._visitables.get("managedFields").clear();
      }

      if (managedFields != null) {
         this.managedFields = new ArrayList();

         for(ManagedFieldsEntry item : managedFields) {
            this.addToManagedFields(item);
         }
      } else {
         this.managedFields = null;
      }

      return this;
   }

   public ObjectMetaFluent withManagedFields(ManagedFieldsEntry... managedFields) {
      if (this.managedFields != null) {
         this.managedFields.clear();
         this._visitables.remove("managedFields");
      }

      if (managedFields != null) {
         for(ManagedFieldsEntry item : managedFields) {
            this.addToManagedFields(item);
         }
      }

      return this;
   }

   public boolean hasManagedFields() {
      return this.managedFields != null && !this.managedFields.isEmpty();
   }

   public ManagedFieldsNested addNewManagedField() {
      return new ManagedFieldsNested(-1, (ManagedFieldsEntry)null);
   }

   public ManagedFieldsNested addNewManagedFieldLike(ManagedFieldsEntry item) {
      return new ManagedFieldsNested(-1, item);
   }

   public ManagedFieldsNested setNewManagedFieldLike(int index, ManagedFieldsEntry item) {
      return new ManagedFieldsNested(index, item);
   }

   public ManagedFieldsNested editManagedField(int index) {
      if (this.managedFields.size() <= index) {
         throw new RuntimeException("Can't edit managedFields. Index exceeds size.");
      } else {
         return this.setNewManagedFieldLike(index, this.buildManagedField(index));
      }
   }

   public ManagedFieldsNested editFirstManagedField() {
      if (this.managedFields.size() == 0) {
         throw new RuntimeException("Can't edit first managedFields. The list is empty.");
      } else {
         return this.setNewManagedFieldLike(0, this.buildManagedField(0));
      }
   }

   public ManagedFieldsNested editLastManagedField() {
      int index = this.managedFields.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last managedFields. The list is empty.");
      } else {
         return this.setNewManagedFieldLike(index, this.buildManagedField(index));
      }
   }

   public ManagedFieldsNested editMatchingManagedField(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.managedFields.size(); ++i) {
         if (predicate.test((ManagedFieldsEntryBuilder)this.managedFields.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching managedFields. No match found.");
      } else {
         return this.setNewManagedFieldLike(index, this.buildManagedField(index));
      }
   }

   public String getName() {
      return this.name;
   }

   public ObjectMetaFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public ObjectMetaFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public ObjectMetaFluent addToOwnerReferences(int index, OwnerReference item) {
      if (this.ownerReferences == null) {
         this.ownerReferences = new ArrayList();
      }

      OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
      if (index >= 0 && index < this.ownerReferences.size()) {
         this._visitables.get("ownerReferences").add(index, builder);
         this.ownerReferences.add(index, builder);
      } else {
         this._visitables.get("ownerReferences").add(builder);
         this.ownerReferences.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent setToOwnerReferences(int index, OwnerReference item) {
      if (this.ownerReferences == null) {
         this.ownerReferences = new ArrayList();
      }

      OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
      if (index >= 0 && index < this.ownerReferences.size()) {
         this._visitables.get("ownerReferences").set(index, builder);
         this.ownerReferences.set(index, builder);
      } else {
         this._visitables.get("ownerReferences").add(builder);
         this.ownerReferences.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent addToOwnerReferences(OwnerReference... items) {
      if (this.ownerReferences == null) {
         this.ownerReferences = new ArrayList();
      }

      for(OwnerReference item : items) {
         OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
         this._visitables.get("ownerReferences").add(builder);
         this.ownerReferences.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent addAllToOwnerReferences(Collection items) {
      if (this.ownerReferences == null) {
         this.ownerReferences = new ArrayList();
      }

      for(OwnerReference item : items) {
         OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
         this._visitables.get("ownerReferences").add(builder);
         this.ownerReferences.add(builder);
      }

      return this;
   }

   public ObjectMetaFluent removeFromOwnerReferences(OwnerReference... items) {
      if (this.ownerReferences == null) {
         return this;
      } else {
         for(OwnerReference item : items) {
            OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
            this._visitables.get("ownerReferences").remove(builder);
            this.ownerReferences.remove(builder);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeAllFromOwnerReferences(Collection items) {
      if (this.ownerReferences == null) {
         return this;
      } else {
         for(OwnerReference item : items) {
            OwnerReferenceBuilder builder = new OwnerReferenceBuilder(item);
            this._visitables.get("ownerReferences").remove(builder);
            this.ownerReferences.remove(builder);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeMatchingFromOwnerReferences(Predicate predicate) {
      if (this.ownerReferences == null) {
         return this;
      } else {
         Iterator<OwnerReferenceBuilder> each = this.ownerReferences.iterator();
         List visitables = this._visitables.get("ownerReferences");

         while(each.hasNext()) {
            OwnerReferenceBuilder builder = (OwnerReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildOwnerReferences() {
      return this.ownerReferences != null ? build(this.ownerReferences) : null;
   }

   public OwnerReference buildOwnerReference(int index) {
      return ((OwnerReferenceBuilder)this.ownerReferences.get(index)).build();
   }

   public OwnerReference buildFirstOwnerReference() {
      return ((OwnerReferenceBuilder)this.ownerReferences.get(0)).build();
   }

   public OwnerReference buildLastOwnerReference() {
      return ((OwnerReferenceBuilder)this.ownerReferences.get(this.ownerReferences.size() - 1)).build();
   }

   public OwnerReference buildMatchingOwnerReference(Predicate predicate) {
      for(OwnerReferenceBuilder item : this.ownerReferences) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingOwnerReference(Predicate predicate) {
      for(OwnerReferenceBuilder item : this.ownerReferences) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ObjectMetaFluent withOwnerReferences(List ownerReferences) {
      if (this.ownerReferences != null) {
         this._visitables.get("ownerReferences").clear();
      }

      if (ownerReferences != null) {
         this.ownerReferences = new ArrayList();

         for(OwnerReference item : ownerReferences) {
            this.addToOwnerReferences(item);
         }
      } else {
         this.ownerReferences = null;
      }

      return this;
   }

   public ObjectMetaFluent withOwnerReferences(OwnerReference... ownerReferences) {
      if (this.ownerReferences != null) {
         this.ownerReferences.clear();
         this._visitables.remove("ownerReferences");
      }

      if (ownerReferences != null) {
         for(OwnerReference item : ownerReferences) {
            this.addToOwnerReferences(item);
         }
      }

      return this;
   }

   public boolean hasOwnerReferences() {
      return this.ownerReferences != null && !this.ownerReferences.isEmpty();
   }

   public OwnerReferencesNested addNewOwnerReference() {
      return new OwnerReferencesNested(-1, (OwnerReference)null);
   }

   public OwnerReferencesNested addNewOwnerReferenceLike(OwnerReference item) {
      return new OwnerReferencesNested(-1, item);
   }

   public OwnerReferencesNested setNewOwnerReferenceLike(int index, OwnerReference item) {
      return new OwnerReferencesNested(index, item);
   }

   public OwnerReferencesNested editOwnerReference(int index) {
      if (this.ownerReferences.size() <= index) {
         throw new RuntimeException("Can't edit ownerReferences. Index exceeds size.");
      } else {
         return this.setNewOwnerReferenceLike(index, this.buildOwnerReference(index));
      }
   }

   public OwnerReferencesNested editFirstOwnerReference() {
      if (this.ownerReferences.size() == 0) {
         throw new RuntimeException("Can't edit first ownerReferences. The list is empty.");
      } else {
         return this.setNewOwnerReferenceLike(0, this.buildOwnerReference(0));
      }
   }

   public OwnerReferencesNested editLastOwnerReference() {
      int index = this.ownerReferences.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ownerReferences. The list is empty.");
      } else {
         return this.setNewOwnerReferenceLike(index, this.buildOwnerReference(index));
      }
   }

   public OwnerReferencesNested editMatchingOwnerReference(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ownerReferences.size(); ++i) {
         if (predicate.test((OwnerReferenceBuilder)this.ownerReferences.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ownerReferences. No match found.");
      } else {
         return this.setNewOwnerReferenceLike(index, this.buildOwnerReference(index));
      }
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public ObjectMetaFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public String getSelfLink() {
      return this.selfLink;
   }

   public ObjectMetaFluent withSelfLink(String selfLink) {
      this.selfLink = selfLink;
      return this;
   }

   public boolean hasSelfLink() {
      return this.selfLink != null;
   }

   public String getUid() {
      return this.uid;
   }

   public ObjectMetaFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ObjectMetaFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ObjectMetaFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ObjectMetaFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ObjectMetaFluent removeFromAdditionalProperties(Map map) {
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

   public ObjectMetaFluent withAdditionalProperties(Map additionalProperties) {
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
            ObjectMetaFluent that = (ObjectMetaFluent)o;
            if (!Objects.equals(this.annotations, that.annotations)) {
               return false;
            } else if (!Objects.equals(this.creationTimestamp, that.creationTimestamp)) {
               return false;
            } else if (!Objects.equals(this.deletionGracePeriodSeconds, that.deletionGracePeriodSeconds)) {
               return false;
            } else if (!Objects.equals(this.deletionTimestamp, that.deletionTimestamp)) {
               return false;
            } else if (!Objects.equals(this.finalizers, that.finalizers)) {
               return false;
            } else if (!Objects.equals(this.generateName, that.generateName)) {
               return false;
            } else if (!Objects.equals(this.generation, that.generation)) {
               return false;
            } else if (!Objects.equals(this.labels, that.labels)) {
               return false;
            } else if (!Objects.equals(this.managedFields, that.managedFields)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.ownerReferences, that.ownerReferences)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
               return false;
            } else if (!Objects.equals(this.selfLink, that.selfLink)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.annotations, this.creationTimestamp, this.deletionGracePeriodSeconds, this.deletionTimestamp, this.finalizers, this.generateName, this.generation, this.labels, this.managedFields, this.name, this.namespace, this.ownerReferences, this.resourceVersion, this.selfLink, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.annotations != null && !this.annotations.isEmpty()) {
         sb.append("annotations:");
         sb.append(this.annotations + ",");
      }

      if (this.creationTimestamp != null) {
         sb.append("creationTimestamp:");
         sb.append(this.creationTimestamp + ",");
      }

      if (this.deletionGracePeriodSeconds != null) {
         sb.append("deletionGracePeriodSeconds:");
         sb.append(this.deletionGracePeriodSeconds + ",");
      }

      if (this.deletionTimestamp != null) {
         sb.append("deletionTimestamp:");
         sb.append(this.deletionTimestamp + ",");
      }

      if (this.finalizers != null && !this.finalizers.isEmpty()) {
         sb.append("finalizers:");
         sb.append(this.finalizers + ",");
      }

      if (this.generateName != null) {
         sb.append("generateName:");
         sb.append(this.generateName + ",");
      }

      if (this.generation != null) {
         sb.append("generation:");
         sb.append(this.generation + ",");
      }

      if (this.labels != null && !this.labels.isEmpty()) {
         sb.append("labels:");
         sb.append(this.labels + ",");
      }

      if (this.managedFields != null && !this.managedFields.isEmpty()) {
         sb.append("managedFields:");
         sb.append(this.managedFields + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.ownerReferences != null && !this.ownerReferences.isEmpty()) {
         sb.append("ownerReferences:");
         sb.append(this.ownerReferences + ",");
      }

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.selfLink != null) {
         sb.append("selfLink:");
         sb.append(this.selfLink + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ManagedFieldsNested extends ManagedFieldsEntryFluent implements Nested {
      ManagedFieldsEntryBuilder builder;
      int index;

      ManagedFieldsNested(int index, ManagedFieldsEntry item) {
         this.index = index;
         this.builder = new ManagedFieldsEntryBuilder(this, item);
      }

      public Object and() {
         return ObjectMetaFluent.this.setToManagedFields(this.index, this.builder.build());
      }

      public Object endManagedField() {
         return this.and();
      }
   }

   public class OwnerReferencesNested extends OwnerReferenceFluent implements Nested {
      OwnerReferenceBuilder builder;
      int index;

      OwnerReferencesNested(int index, OwnerReference item) {
         this.index = index;
         this.builder = new OwnerReferenceBuilder(this, item);
      }

      public Object and() {
         return ObjectMetaFluent.this.setToOwnerReferences(this.index, this.builder.build());
      }

      public Object endOwnerReference() {
         return this.and();
      }
   }
}
