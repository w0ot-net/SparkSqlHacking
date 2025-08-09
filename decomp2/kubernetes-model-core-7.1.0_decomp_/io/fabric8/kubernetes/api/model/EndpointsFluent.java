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

public class EndpointsFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList subsets = new ArrayList();
   private Map additionalProperties;

   public EndpointsFluent() {
   }

   public EndpointsFluent(Endpoints instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Endpoints instance) {
      instance = instance != null ? instance : new Endpoints();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withSubsets(instance.getSubsets());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public EndpointsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public EndpointsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public EndpointsFluent withMetadata(ObjectMeta metadata) {
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

   public EndpointsFluent addToSubsets(int index, EndpointSubset item) {
      if (this.subsets == null) {
         this.subsets = new ArrayList();
      }

      EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
      if (index >= 0 && index < this.subsets.size()) {
         this._visitables.get("subsets").add(index, builder);
         this.subsets.add(index, builder);
      } else {
         this._visitables.get("subsets").add(builder);
         this.subsets.add(builder);
      }

      return this;
   }

   public EndpointsFluent setToSubsets(int index, EndpointSubset item) {
      if (this.subsets == null) {
         this.subsets = new ArrayList();
      }

      EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
      if (index >= 0 && index < this.subsets.size()) {
         this._visitables.get("subsets").set(index, builder);
         this.subsets.set(index, builder);
      } else {
         this._visitables.get("subsets").add(builder);
         this.subsets.add(builder);
      }

      return this;
   }

   public EndpointsFluent addToSubsets(EndpointSubset... items) {
      if (this.subsets == null) {
         this.subsets = new ArrayList();
      }

      for(EndpointSubset item : items) {
         EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
         this._visitables.get("subsets").add(builder);
         this.subsets.add(builder);
      }

      return this;
   }

   public EndpointsFluent addAllToSubsets(Collection items) {
      if (this.subsets == null) {
         this.subsets = new ArrayList();
      }

      for(EndpointSubset item : items) {
         EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
         this._visitables.get("subsets").add(builder);
         this.subsets.add(builder);
      }

      return this;
   }

   public EndpointsFluent removeFromSubsets(EndpointSubset... items) {
      if (this.subsets == null) {
         return this;
      } else {
         for(EndpointSubset item : items) {
            EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
            this._visitables.get("subsets").remove(builder);
            this.subsets.remove(builder);
         }

         return this;
      }
   }

   public EndpointsFluent removeAllFromSubsets(Collection items) {
      if (this.subsets == null) {
         return this;
      } else {
         for(EndpointSubset item : items) {
            EndpointSubsetBuilder builder = new EndpointSubsetBuilder(item);
            this._visitables.get("subsets").remove(builder);
            this.subsets.remove(builder);
         }

         return this;
      }
   }

   public EndpointsFluent removeMatchingFromSubsets(Predicate predicate) {
      if (this.subsets == null) {
         return this;
      } else {
         Iterator<EndpointSubsetBuilder> each = this.subsets.iterator();
         List visitables = this._visitables.get("subsets");

         while(each.hasNext()) {
            EndpointSubsetBuilder builder = (EndpointSubsetBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSubsets() {
      return this.subsets != null ? build(this.subsets) : null;
   }

   public EndpointSubset buildSubset(int index) {
      return ((EndpointSubsetBuilder)this.subsets.get(index)).build();
   }

   public EndpointSubset buildFirstSubset() {
      return ((EndpointSubsetBuilder)this.subsets.get(0)).build();
   }

   public EndpointSubset buildLastSubset() {
      return ((EndpointSubsetBuilder)this.subsets.get(this.subsets.size() - 1)).build();
   }

   public EndpointSubset buildMatchingSubset(Predicate predicate) {
      for(EndpointSubsetBuilder item : this.subsets) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSubset(Predicate predicate) {
      for(EndpointSubsetBuilder item : this.subsets) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointsFluent withSubsets(List subsets) {
      if (this.subsets != null) {
         this._visitables.get("subsets").clear();
      }

      if (subsets != null) {
         this.subsets = new ArrayList();

         for(EndpointSubset item : subsets) {
            this.addToSubsets(item);
         }
      } else {
         this.subsets = null;
      }

      return this;
   }

   public EndpointsFluent withSubsets(EndpointSubset... subsets) {
      if (this.subsets != null) {
         this.subsets.clear();
         this._visitables.remove("subsets");
      }

      if (subsets != null) {
         for(EndpointSubset item : subsets) {
            this.addToSubsets(item);
         }
      }

      return this;
   }

   public boolean hasSubsets() {
      return this.subsets != null && !this.subsets.isEmpty();
   }

   public SubsetsNested addNewSubset() {
      return new SubsetsNested(-1, (EndpointSubset)null);
   }

   public SubsetsNested addNewSubsetLike(EndpointSubset item) {
      return new SubsetsNested(-1, item);
   }

   public SubsetsNested setNewSubsetLike(int index, EndpointSubset item) {
      return new SubsetsNested(index, item);
   }

   public SubsetsNested editSubset(int index) {
      if (this.subsets.size() <= index) {
         throw new RuntimeException("Can't edit subsets. Index exceeds size.");
      } else {
         return this.setNewSubsetLike(index, this.buildSubset(index));
      }
   }

   public SubsetsNested editFirstSubset() {
      if (this.subsets.size() == 0) {
         throw new RuntimeException("Can't edit first subsets. The list is empty.");
      } else {
         return this.setNewSubsetLike(0, this.buildSubset(0));
      }
   }

   public SubsetsNested editLastSubset() {
      int index = this.subsets.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last subsets. The list is empty.");
      } else {
         return this.setNewSubsetLike(index, this.buildSubset(index));
      }
   }

   public SubsetsNested editMatchingSubset(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.subsets.size(); ++i) {
         if (predicate.test((EndpointSubsetBuilder)this.subsets.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching subsets. No match found.");
      } else {
         return this.setNewSubsetLike(index, this.buildSubset(index));
      }
   }

   public EndpointsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointsFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointsFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointsFluent that = (EndpointsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.subsets, that.subsets)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.subsets, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
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

      if (this.subsets != null && !this.subsets.isEmpty()) {
         sb.append("subsets:");
         sb.append(this.subsets + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return EndpointsFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class SubsetsNested extends EndpointSubsetFluent implements Nested {
      EndpointSubsetBuilder builder;
      int index;

      SubsetsNested(int index, EndpointSubset item) {
         this.index = index;
         this.builder = new EndpointSubsetBuilder(this, item);
      }

      public Object and() {
         return EndpointsFluent.this.setToSubsets(this.index, this.builder.build());
      }

      public Object endSubset() {
         return this.and();
      }
   }
}
