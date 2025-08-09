package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.NodeSelector;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceClassFluent extends BaseFluent {
   private String apiVersion;
   private String driverName;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ResourceClassParametersReferenceBuilder parametersRef;
   private Boolean structuredParameters;
   private NodeSelector suitableNodes;
   private Map additionalProperties;

   public ResourceClassFluent() {
   }

   public ResourceClassFluent(ResourceClass instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClass instance) {
      instance = instance != null ? instance : new ResourceClass();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDriverName(instance.getDriverName());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withParametersRef(instance.getParametersRef());
         this.withStructuredParameters(instance.getStructuredParameters());
         this.withSuitableNodes(instance.getSuitableNodes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ResourceClassFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getDriverName() {
      return this.driverName;
   }

   public ResourceClassFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ResourceClassFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ResourceClassFluent withMetadata(ObjectMeta metadata) {
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

   public ResourceClassParametersReference buildParametersRef() {
      return this.parametersRef != null ? this.parametersRef.build() : null;
   }

   public ResourceClassFluent withParametersRef(ResourceClassParametersReference parametersRef) {
      this._visitables.remove("parametersRef");
      if (parametersRef != null) {
         this.parametersRef = new ResourceClassParametersReferenceBuilder(parametersRef);
         this._visitables.get("parametersRef").add(this.parametersRef);
      } else {
         this.parametersRef = null;
         this._visitables.get("parametersRef").remove(this.parametersRef);
      }

      return this;
   }

   public boolean hasParametersRef() {
      return this.parametersRef != null;
   }

   public ResourceClassFluent withNewParametersRef(String apiGroup, String kind, String name, String namespace) {
      return this.withParametersRef(new ResourceClassParametersReference(apiGroup, kind, name, namespace));
   }

   public ParametersRefNested withNewParametersRef() {
      return new ParametersRefNested((ResourceClassParametersReference)null);
   }

   public ParametersRefNested withNewParametersRefLike(ResourceClassParametersReference item) {
      return new ParametersRefNested(item);
   }

   public ParametersRefNested editParametersRef() {
      return this.withNewParametersRefLike((ResourceClassParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((Object)null));
   }

   public ParametersRefNested editOrNewParametersRef() {
      return this.withNewParametersRefLike((ResourceClassParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((new ResourceClassParametersReferenceBuilder()).build()));
   }

   public ParametersRefNested editOrNewParametersRefLike(ResourceClassParametersReference item) {
      return this.withNewParametersRefLike((ResourceClassParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse(item));
   }

   public Boolean getStructuredParameters() {
      return this.structuredParameters;
   }

   public ResourceClassFluent withStructuredParameters(Boolean structuredParameters) {
      this.structuredParameters = structuredParameters;
      return this;
   }

   public boolean hasStructuredParameters() {
      return this.structuredParameters != null;
   }

   public NodeSelector getSuitableNodes() {
      return this.suitableNodes;
   }

   public ResourceClassFluent withSuitableNodes(NodeSelector suitableNodes) {
      this.suitableNodes = suitableNodes;
      return this;
   }

   public boolean hasSuitableNodes() {
      return this.suitableNodes != null;
   }

   public ResourceClassFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClassFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClassFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClassFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClassFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClassFluent that = (ResourceClassFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.driverName, that.driverName)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.parametersRef, that.parametersRef)) {
               return false;
            } else if (!Objects.equals(this.structuredParameters, that.structuredParameters)) {
               return false;
            } else if (!Objects.equals(this.suitableNodes, that.suitableNodes)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.driverName, this.kind, this.metadata, this.parametersRef, this.structuredParameters, this.suitableNodes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.driverName != null) {
         sb.append("driverName:");
         sb.append(this.driverName + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.parametersRef != null) {
         sb.append("parametersRef:");
         sb.append(this.parametersRef + ",");
      }

      if (this.structuredParameters != null) {
         sb.append("structuredParameters:");
         sb.append(this.structuredParameters + ",");
      }

      if (this.suitableNodes != null) {
         sb.append("suitableNodes:");
         sb.append(this.suitableNodes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ResourceClassFluent withStructuredParameters() {
      return this.withStructuredParameters(true);
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return ResourceClassFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class ParametersRefNested extends ResourceClassParametersReferenceFluent implements Nested {
      ResourceClassParametersReferenceBuilder builder;

      ParametersRefNested(ResourceClassParametersReference item) {
         this.builder = new ResourceClassParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return ResourceClassFluent.this.withParametersRef(this.builder.build());
      }

      public Object endParametersRef() {
         return this.and();
      }
   }
}
