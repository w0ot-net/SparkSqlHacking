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

public class ResourceClaimParametersFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList driverRequests = new ArrayList();
   private ResourceClaimParametersReferenceBuilder generatedFrom;
   private String kind;
   private ObjectMetaBuilder metadata;
   private Boolean shareable;
   private Map additionalProperties;

   public ResourceClaimParametersFluent() {
   }

   public ResourceClaimParametersFluent(ResourceClaimParameters instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimParameters instance) {
      instance = instance != null ? instance : new ResourceClaimParameters();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDriverRequests(instance.getDriverRequests());
         this.withGeneratedFrom(instance.getGeneratedFrom());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withShareable(instance.getShareable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ResourceClaimParametersFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public ResourceClaimParametersFluent addToDriverRequests(int index, DriverRequests item) {
      if (this.driverRequests == null) {
         this.driverRequests = new ArrayList();
      }

      DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
      if (index >= 0 && index < this.driverRequests.size()) {
         this._visitables.get("driverRequests").add(index, builder);
         this.driverRequests.add(index, builder);
      } else {
         this._visitables.get("driverRequests").add(builder);
         this.driverRequests.add(builder);
      }

      return this;
   }

   public ResourceClaimParametersFluent setToDriverRequests(int index, DriverRequests item) {
      if (this.driverRequests == null) {
         this.driverRequests = new ArrayList();
      }

      DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
      if (index >= 0 && index < this.driverRequests.size()) {
         this._visitables.get("driverRequests").set(index, builder);
         this.driverRequests.set(index, builder);
      } else {
         this._visitables.get("driverRequests").add(builder);
         this.driverRequests.add(builder);
      }

      return this;
   }

   public ResourceClaimParametersFluent addToDriverRequests(DriverRequests... items) {
      if (this.driverRequests == null) {
         this.driverRequests = new ArrayList();
      }

      for(DriverRequests item : items) {
         DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
         this._visitables.get("driverRequests").add(builder);
         this.driverRequests.add(builder);
      }

      return this;
   }

   public ResourceClaimParametersFluent addAllToDriverRequests(Collection items) {
      if (this.driverRequests == null) {
         this.driverRequests = new ArrayList();
      }

      for(DriverRequests item : items) {
         DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
         this._visitables.get("driverRequests").add(builder);
         this.driverRequests.add(builder);
      }

      return this;
   }

   public ResourceClaimParametersFluent removeFromDriverRequests(DriverRequests... items) {
      if (this.driverRequests == null) {
         return this;
      } else {
         for(DriverRequests item : items) {
            DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
            this._visitables.get("driverRequests").remove(builder);
            this.driverRequests.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimParametersFluent removeAllFromDriverRequests(Collection items) {
      if (this.driverRequests == null) {
         return this;
      } else {
         for(DriverRequests item : items) {
            DriverRequestsBuilder builder = new DriverRequestsBuilder(item);
            this._visitables.get("driverRequests").remove(builder);
            this.driverRequests.remove(builder);
         }

         return this;
      }
   }

   public ResourceClaimParametersFluent removeMatchingFromDriverRequests(Predicate predicate) {
      if (this.driverRequests == null) {
         return this;
      } else {
         Iterator<DriverRequestsBuilder> each = this.driverRequests.iterator();
         List visitables = this._visitables.get("driverRequests");

         while(each.hasNext()) {
            DriverRequestsBuilder builder = (DriverRequestsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildDriverRequests() {
      return this.driverRequests != null ? build(this.driverRequests) : null;
   }

   public DriverRequests buildDriverRequest(int index) {
      return ((DriverRequestsBuilder)this.driverRequests.get(index)).build();
   }

   public DriverRequests buildFirstDriverRequest() {
      return ((DriverRequestsBuilder)this.driverRequests.get(0)).build();
   }

   public DriverRequests buildLastDriverRequest() {
      return ((DriverRequestsBuilder)this.driverRequests.get(this.driverRequests.size() - 1)).build();
   }

   public DriverRequests buildMatchingDriverRequest(Predicate predicate) {
      for(DriverRequestsBuilder item : this.driverRequests) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingDriverRequest(Predicate predicate) {
      for(DriverRequestsBuilder item : this.driverRequests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceClaimParametersFluent withDriverRequests(List driverRequests) {
      if (this.driverRequests != null) {
         this._visitables.get("driverRequests").clear();
      }

      if (driverRequests != null) {
         this.driverRequests = new ArrayList();

         for(DriverRequests item : driverRequests) {
            this.addToDriverRequests(item);
         }
      } else {
         this.driverRequests = null;
      }

      return this;
   }

   public ResourceClaimParametersFluent withDriverRequests(DriverRequests... driverRequests) {
      if (this.driverRequests != null) {
         this.driverRequests.clear();
         this._visitables.remove("driverRequests");
      }

      if (driverRequests != null) {
         for(DriverRequests item : driverRequests) {
            this.addToDriverRequests(item);
         }
      }

      return this;
   }

   public boolean hasDriverRequests() {
      return this.driverRequests != null && !this.driverRequests.isEmpty();
   }

   public DriverRequestsNested addNewDriverRequest() {
      return new DriverRequestsNested(-1, (DriverRequests)null);
   }

   public DriverRequestsNested addNewDriverRequestLike(DriverRequests item) {
      return new DriverRequestsNested(-1, item);
   }

   public DriverRequestsNested setNewDriverRequestLike(int index, DriverRequests item) {
      return new DriverRequestsNested(index, item);
   }

   public DriverRequestsNested editDriverRequest(int index) {
      if (this.driverRequests.size() <= index) {
         throw new RuntimeException("Can't edit driverRequests. Index exceeds size.");
      } else {
         return this.setNewDriverRequestLike(index, this.buildDriverRequest(index));
      }
   }

   public DriverRequestsNested editFirstDriverRequest() {
      if (this.driverRequests.size() == 0) {
         throw new RuntimeException("Can't edit first driverRequests. The list is empty.");
      } else {
         return this.setNewDriverRequestLike(0, this.buildDriverRequest(0));
      }
   }

   public DriverRequestsNested editLastDriverRequest() {
      int index = this.driverRequests.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last driverRequests. The list is empty.");
      } else {
         return this.setNewDriverRequestLike(index, this.buildDriverRequest(index));
      }
   }

   public DriverRequestsNested editMatchingDriverRequest(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.driverRequests.size(); ++i) {
         if (predicate.test((DriverRequestsBuilder)this.driverRequests.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching driverRequests. No match found.");
      } else {
         return this.setNewDriverRequestLike(index, this.buildDriverRequest(index));
      }
   }

   public ResourceClaimParametersReference buildGeneratedFrom() {
      return this.generatedFrom != null ? this.generatedFrom.build() : null;
   }

   public ResourceClaimParametersFluent withGeneratedFrom(ResourceClaimParametersReference generatedFrom) {
      this._visitables.remove("generatedFrom");
      if (generatedFrom != null) {
         this.generatedFrom = new ResourceClaimParametersReferenceBuilder(generatedFrom);
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

   public ResourceClaimParametersFluent withNewGeneratedFrom(String apiGroup, String kind, String name) {
      return this.withGeneratedFrom(new ResourceClaimParametersReference(apiGroup, kind, name));
   }

   public GeneratedFromNested withNewGeneratedFrom() {
      return new GeneratedFromNested((ResourceClaimParametersReference)null);
   }

   public GeneratedFromNested withNewGeneratedFromLike(ResourceClaimParametersReference item) {
      return new GeneratedFromNested(item);
   }

   public GeneratedFromNested editGeneratedFrom() {
      return this.withNewGeneratedFromLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse((Object)null));
   }

   public GeneratedFromNested editOrNewGeneratedFrom() {
      return this.withNewGeneratedFromLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse((new ResourceClaimParametersReferenceBuilder()).build()));
   }

   public GeneratedFromNested editOrNewGeneratedFromLike(ResourceClaimParametersReference item) {
      return this.withNewGeneratedFromLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildGeneratedFrom()).orElse(item));
   }

   public String getKind() {
      return this.kind;
   }

   public ResourceClaimParametersFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ResourceClaimParametersFluent withMetadata(ObjectMeta metadata) {
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

   public Boolean getShareable() {
      return this.shareable;
   }

   public ResourceClaimParametersFluent withShareable(Boolean shareable) {
      this.shareable = shareable;
      return this;
   }

   public boolean hasShareable() {
      return this.shareable != null;
   }

   public ResourceClaimParametersFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimParametersFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimParametersFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimParametersFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClaimParametersFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClaimParametersFluent that = (ResourceClaimParametersFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.driverRequests, that.driverRequests)) {
               return false;
            } else if (!Objects.equals(this.generatedFrom, that.generatedFrom)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.shareable, that.shareable)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.driverRequests, this.generatedFrom, this.kind, this.metadata, this.shareable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.driverRequests != null && !this.driverRequests.isEmpty()) {
         sb.append("driverRequests:");
         sb.append(this.driverRequests + ",");
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

      if (this.shareable != null) {
         sb.append("shareable:");
         sb.append(this.shareable + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ResourceClaimParametersFluent withShareable() {
      return this.withShareable(true);
   }

   public class DriverRequestsNested extends DriverRequestsFluent implements Nested {
      DriverRequestsBuilder builder;
      int index;

      DriverRequestsNested(int index, DriverRequests item) {
         this.index = index;
         this.builder = new DriverRequestsBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimParametersFluent.this.setToDriverRequests(this.index, this.builder.build());
      }

      public Object endDriverRequest() {
         return this.and();
      }
   }

   public class GeneratedFromNested extends ResourceClaimParametersReferenceFluent implements Nested {
      ResourceClaimParametersReferenceBuilder builder;

      GeneratedFromNested(ResourceClaimParametersReference item) {
         this.builder = new ResourceClaimParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimParametersFluent.this.withGeneratedFrom(this.builder.build());
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
         return ResourceClaimParametersFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
