package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ResourceClassParametersFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList filters = new ArrayList();
   private ResourceClassParametersReferenceBuilder generatedFrom;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList vendorParameters = new ArrayList();
   private Map additionalProperties;

   public ResourceClassParametersFluent() {
   }

   public ResourceClassParametersFluent(ResourceClassParameters instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClassParameters instance) {
      instance = instance != null ? instance : new ResourceClassParameters();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withFilters(instance.getFilters());
         this.withGeneratedFrom(instance.getGeneratedFrom());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withVendorParameters(instance.getVendorParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ResourceClassParametersFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public ResourceClassParametersFluent addToFilters(int index, ResourceFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").add(index, builder);
         this.filters.add(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent setToFilters(int index, ResourceFilter item) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
      if (index >= 0 && index < this.filters.size()) {
         this._visitables.get("filters").set(index, builder);
         this.filters.set(index, builder);
      } else {
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent addToFilters(ResourceFilter... items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(ResourceFilter item : items) {
         ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent addAllToFilters(Collection items) {
      if (this.filters == null) {
         this.filters = new ArrayList();
      }

      for(ResourceFilter item : items) {
         ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
         this._visitables.get("filters").add(builder);
         this.filters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent removeFromFilters(ResourceFilter... items) {
      if (this.filters == null) {
         return this;
      } else {
         for(ResourceFilter item : items) {
            ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public ResourceClassParametersFluent removeAllFromFilters(Collection items) {
      if (this.filters == null) {
         return this;
      } else {
         for(ResourceFilter item : items) {
            ResourceFilterBuilder builder = new ResourceFilterBuilder(item);
            this._visitables.get("filters").remove(builder);
            this.filters.remove(builder);
         }

         return this;
      }
   }

   public ResourceClassParametersFluent removeMatchingFromFilters(Predicate predicate) {
      if (this.filters == null) {
         return this;
      } else {
         Iterator<ResourceFilterBuilder> each = this.filters.iterator();
         List visitables = this._visitables.get("filters");

         while(each.hasNext()) {
            ResourceFilterBuilder builder = (ResourceFilterBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildFilters() {
      return this.filters != null ? build(this.filters) : null;
   }

   public ResourceFilter buildFilter(int index) {
      return ((ResourceFilterBuilder)this.filters.get(index)).build();
   }

   public ResourceFilter buildFirstFilter() {
      return ((ResourceFilterBuilder)this.filters.get(0)).build();
   }

   public ResourceFilter buildLastFilter() {
      return ((ResourceFilterBuilder)this.filters.get(this.filters.size() - 1)).build();
   }

   public ResourceFilter buildMatchingFilter(Predicate predicate) {
      for(ResourceFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFilter(Predicate predicate) {
      for(ResourceFilterBuilder item : this.filters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClassParametersFluent withFilters(List filters) {
      if (this.filters != null) {
         this._visitables.get("filters").clear();
      }

      if (filters != null) {
         this.filters = new ArrayList();

         for(ResourceFilter item : filters) {
            this.addToFilters(item);
         }
      } else {
         this.filters = null;
      }

      return this;
   }

   public ResourceClassParametersFluent withFilters(ResourceFilter... filters) {
      if (this.filters != null) {
         this.filters.clear();
         this._visitables.remove("filters");
      }

      if (filters != null) {
         for(ResourceFilter item : filters) {
            this.addToFilters(item);
         }
      }

      return this;
   }

   public boolean hasFilters() {
      return this.filters != null && !this.filters.isEmpty();
   }

   public FiltersNested addNewFilter() {
      return new FiltersNested(-1, (ResourceFilter)null);
   }

   public FiltersNested addNewFilterLike(ResourceFilter item) {
      return new FiltersNested(-1, item);
   }

   public FiltersNested setNewFilterLike(int index, ResourceFilter item) {
      return new FiltersNested(index, item);
   }

   public FiltersNested editFilter(int index) {
      if (this.filters.size() <= index) {
         throw new RuntimeException("Can't edit filters. Index exceeds size.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editFirstFilter() {
      if (this.filters.size() == 0) {
         throw new RuntimeException("Can't edit first filters. The list is empty.");
      } else {
         return this.setNewFilterLike(0, this.buildFilter(0));
      }
   }

   public FiltersNested editLastFilter() {
      int index = this.filters.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last filters. The list is empty.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public FiltersNested editMatchingFilter(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.filters.size(); ++i) {
         if (predicate.test((ResourceFilterBuilder)this.filters.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching filters. No match found.");
      } else {
         return this.setNewFilterLike(index, this.buildFilter(index));
      }
   }

   public ResourceClassParametersReference buildGeneratedFrom() {
      return this.generatedFrom != null ? this.generatedFrom.build() : null;
   }

   public ResourceClassParametersFluent withGeneratedFrom(ResourceClassParametersReference generatedFrom) {
      this._visitables.remove("generatedFrom");
      if (generatedFrom != null) {
         this.generatedFrom = new ResourceClassParametersReferenceBuilder(generatedFrom);
         this._visitables.get("generatedFrom").add(this.generatedFrom);
      } else {
         this.generatedFrom = null;
         this._visitables.get("generatedFrom").remove(this.generatedFrom);
      }

      return this;
   }

   public boolean hasGeneratedFrom() {
      return this.generatedFrom != null;
   }

   public ResourceClassParametersFluent withNewGeneratedFrom(String apiGroup, String kind, String name, String namespace) {
      return this.withGeneratedFrom(new ResourceClassParametersReference(apiGroup, kind, name, namespace));
   }

   public GeneratedFromNested withNewGeneratedFrom() {
      return new GeneratedFromNested((ResourceClassParametersReference)null);
   }

   public GeneratedFromNested withNewGeneratedFromLike(ResourceClassParametersReference item) {
      return new GeneratedFromNested(item);
   }

   public GeneratedFromNested editGeneratedFrom() {
      return this.withNewGeneratedFromLike((ResourceClassParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse((Object)null));
   }

   public GeneratedFromNested editOrNewGeneratedFrom() {
      return this.withNewGeneratedFromLike((ResourceClassParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse((new ResourceClassParametersReferenceBuilder()).build()));
   }

   public GeneratedFromNested editOrNewGeneratedFromLike(ResourceClassParametersReference item) {
      return this.withNewGeneratedFromLike((ResourceClassParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse(item));
   }

   public String getKind() {
      return this.kind;
   }

   public ResourceClassParametersFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ResourceClassParametersFluent withMetadata(ObjectMeta metadata) {
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

   public ResourceClassParametersFluent addToVendorParameters(int index, VendorParameters item) {
      if (this.vendorParameters == null) {
         this.vendorParameters = new ArrayList();
      }

      VendorParametersBuilder builder = new VendorParametersBuilder(item);
      if (index >= 0 && index < this.vendorParameters.size()) {
         this._visitables.get("vendorParameters").add(index, builder);
         this.vendorParameters.add(index, builder);
      } else {
         this._visitables.get("vendorParameters").add(builder);
         this.vendorParameters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent setToVendorParameters(int index, VendorParameters item) {
      if (this.vendorParameters == null) {
         this.vendorParameters = new ArrayList();
      }

      VendorParametersBuilder builder = new VendorParametersBuilder(item);
      if (index >= 0 && index < this.vendorParameters.size()) {
         this._visitables.get("vendorParameters").set(index, builder);
         this.vendorParameters.set(index, builder);
      } else {
         this._visitables.get("vendorParameters").add(builder);
         this.vendorParameters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent addToVendorParameters(VendorParameters... items) {
      if (this.vendorParameters == null) {
         this.vendorParameters = new ArrayList();
      }

      for(VendorParameters item : items) {
         VendorParametersBuilder builder = new VendorParametersBuilder(item);
         this._visitables.get("vendorParameters").add(builder);
         this.vendorParameters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent addAllToVendorParameters(Collection items) {
      if (this.vendorParameters == null) {
         this.vendorParameters = new ArrayList();
      }

      for(VendorParameters item : items) {
         VendorParametersBuilder builder = new VendorParametersBuilder(item);
         this._visitables.get("vendorParameters").add(builder);
         this.vendorParameters.add(builder);
      }

      return this;
   }

   public ResourceClassParametersFluent removeFromVendorParameters(VendorParameters... items) {
      if (this.vendorParameters == null) {
         return this;
      } else {
         for(VendorParameters item : items) {
            VendorParametersBuilder builder = new VendorParametersBuilder(item);
            this._visitables.get("vendorParameters").remove(builder);
            this.vendorParameters.remove(builder);
         }

         return this;
      }
   }

   public ResourceClassParametersFluent removeAllFromVendorParameters(Collection items) {
      if (this.vendorParameters == null) {
         return this;
      } else {
         for(VendorParameters item : items) {
            VendorParametersBuilder builder = new VendorParametersBuilder(item);
            this._visitables.get("vendorParameters").remove(builder);
            this.vendorParameters.remove(builder);
         }

         return this;
      }
   }

   public ResourceClassParametersFluent removeMatchingFromVendorParameters(Predicate predicate) {
      if (this.vendorParameters == null) {
         return this;
      } else {
         Iterator<VendorParametersBuilder> each = this.vendorParameters.iterator();
         List visitables = this._visitables.get("vendorParameters");

         while(each.hasNext()) {
            VendorParametersBuilder builder = (VendorParametersBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVendorParameters() {
      return this.vendorParameters != null ? build(this.vendorParameters) : null;
   }

   public VendorParameters buildVendorParameter(int index) {
      return ((VendorParametersBuilder)this.vendorParameters.get(index)).build();
   }

   public VendorParameters buildFirstVendorParameter() {
      return ((VendorParametersBuilder)this.vendorParameters.get(0)).build();
   }

   public VendorParameters buildLastVendorParameter() {
      return ((VendorParametersBuilder)this.vendorParameters.get(this.vendorParameters.size() - 1)).build();
   }

   public VendorParameters buildMatchingVendorParameter(Predicate predicate) {
      for(VendorParametersBuilder item : this.vendorParameters) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVendorParameter(Predicate predicate) {
      for(VendorParametersBuilder item : this.vendorParameters) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClassParametersFluent withVendorParameters(List vendorParameters) {
      if (this.vendorParameters != null) {
         this._visitables.get("vendorParameters").clear();
      }

      if (vendorParameters != null) {
         this.vendorParameters = new ArrayList();

         for(VendorParameters item : vendorParameters) {
            this.addToVendorParameters(item);
         }
      } else {
         this.vendorParameters = null;
      }

      return this;
   }

   public ResourceClassParametersFluent withVendorParameters(VendorParameters... vendorParameters) {
      if (this.vendorParameters != null) {
         this.vendorParameters.clear();
         this._visitables.remove("vendorParameters");
      }

      if (vendorParameters != null) {
         for(VendorParameters item : vendorParameters) {
            this.addToVendorParameters(item);
         }
      }

      return this;
   }

   public boolean hasVendorParameters() {
      return this.vendorParameters != null && !this.vendorParameters.isEmpty();
   }

   public ResourceClassParametersFluent addNewVendorParameter(String driverName, Object parameters) {
      return this.addToVendorParameters(new VendorParameters(driverName, parameters));
   }

   public VendorParametersNested addNewVendorParameter() {
      return new VendorParametersNested(-1, (VendorParameters)null);
   }

   public VendorParametersNested addNewVendorParameterLike(VendorParameters item) {
      return new VendorParametersNested(-1, item);
   }

   public VendorParametersNested setNewVendorParameterLike(int index, VendorParameters item) {
      return new VendorParametersNested(index, item);
   }

   public VendorParametersNested editVendorParameter(int index) {
      if (this.vendorParameters.size() <= index) {
         throw new RuntimeException("Can't edit vendorParameters. Index exceeds size.");
      } else {
         return this.setNewVendorParameterLike(index, this.buildVendorParameter(index));
      }
   }

   public VendorParametersNested editFirstVendorParameter() {
      if (this.vendorParameters.size() == 0) {
         throw new RuntimeException("Can't edit first vendorParameters. The list is empty.");
      } else {
         return this.setNewVendorParameterLike(0, this.buildVendorParameter(0));
      }
   }

   public VendorParametersNested editLastVendorParameter() {
      int index = this.vendorParameters.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last vendorParameters. The list is empty.");
      } else {
         return this.setNewVendorParameterLike(index, this.buildVendorParameter(index));
      }
   }

   public VendorParametersNested editMatchingVendorParameter(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.vendorParameters.size(); ++i) {
         if (predicate.test((VendorParametersBuilder)this.vendorParameters.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching vendorParameters. No match found.");
      } else {
         return this.setNewVendorParameterLike(index, this.buildVendorParameter(index));
      }
   }

   public ResourceClassParametersFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClassParametersFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClassParametersFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClassParametersFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClassParametersFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClassParametersFluent that = (ResourceClassParametersFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.filters, that.filters)) {
               return false;
            } else if (!Objects.equals(this.generatedFrom, that.generatedFrom)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.vendorParameters, that.vendorParameters)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.filters, this.generatedFrom, this.kind, this.metadata, this.vendorParameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.filters != null && !this.filters.isEmpty()) {
         sb.append("filters:");
         sb.append(this.filters + ",");
      }

      if (this.generatedFrom != null) {
         sb.append("generatedFrom:");
         sb.append(this.generatedFrom + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.vendorParameters != null && !this.vendorParameters.isEmpty()) {
         sb.append("vendorParameters:");
         sb.append(this.vendorParameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class FiltersNested extends ResourceFilterFluent implements Nested {
      ResourceFilterBuilder builder;
      int index;

      FiltersNested(int index, ResourceFilter item) {
         this.index = index;
         this.builder = new ResourceFilterBuilder(this, item);
      }

      public Object and() {
         return ResourceClassParametersFluent.this.setToFilters(this.index, this.builder.build());
      }

      public Object endFilter() {
         return this.and();
      }
   }

   public class GeneratedFromNested extends ResourceClassParametersReferenceFluent implements Nested {
      ResourceClassParametersReferenceBuilder builder;

      GeneratedFromNested(ResourceClassParametersReference item) {
         this.builder = new ResourceClassParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return ResourceClassParametersFluent.this.withGeneratedFrom(this.builder.build());
      }

      public Object endGeneratedFrom() {
         return this.and();
      }
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return ResourceClassParametersFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class VendorParametersNested extends VendorParametersFluent implements Nested {
      VendorParametersBuilder builder;
      int index;

      VendorParametersNested(int index, VendorParameters item) {
         this.index = index;
         this.builder = new VendorParametersBuilder(this, item);
      }

      public Object and() {
         return ResourceClassParametersFluent.this.setToVendorParameters(this.index, this.builder.build());
      }

      public Object endVendorParameter() {
         return this.and();
      }
   }
}
