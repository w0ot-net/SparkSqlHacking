package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ReplicaSetFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ReplicaSetSpecBuilder spec;
   private ReplicaSetStatusBuilder status;
   private Map additionalProperties;

   public ReplicaSetFluent() {
   }

   public ReplicaSetFluent(ReplicaSet instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ReplicaSet instance) {
      instance = instance != null ? instance : new ReplicaSet();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withSpec(instance.getSpec());
         this.withStatus(instance.getStatus());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ReplicaSetFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ReplicaSetFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ReplicaSetFluent withMetadata(ObjectMeta metadata) {
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

   public ReplicaSetSpec buildSpec() {
      return this.spec != null ? this.spec.build() : null;
   }

   public ReplicaSetFluent withSpec(ReplicaSetSpec spec) {
      this._visitables.remove("spec");
      if (spec != null) {
         this.spec = new ReplicaSetSpecBuilder(spec);
         this._visitables.get("spec").add(this.spec);
      } else {
         this.spec = null;
         this._visitables.get("spec").remove(this.spec);
      }

      return this;
   }

   public boolean hasSpec() {
      return this.spec != null;
   }

   public SpecNested withNewSpec() {
      return new SpecNested((ReplicaSetSpec)null);
   }

   public SpecNested withNewSpecLike(ReplicaSetSpec item) {
      return new SpecNested(item);
   }

   public SpecNested editSpec() {
      return this.withNewSpecLike((ReplicaSetSpec)Optional.ofNullable(this.buildSpec()).orElse((Object)null));
   }

   public SpecNested editOrNewSpec() {
      return this.withNewSpecLike((ReplicaSetSpec)Optional.ofNullable(this.buildSpec()).orElse((new ReplicaSetSpecBuilder()).build()));
   }

   public SpecNested editOrNewSpecLike(ReplicaSetSpec item) {
      return this.withNewSpecLike((ReplicaSetSpec)Optional.ofNullable(this.buildSpec()).orElse(item));
   }

   public ReplicaSetStatus buildStatus() {
      return this.status != null ? this.status.build() : null;
   }

   public ReplicaSetFluent withStatus(ReplicaSetStatus status) {
      this._visitables.remove("status");
      if (status != null) {
         this.status = new ReplicaSetStatusBuilder(status);
         this._visitables.get("status").add(this.status);
      } else {
         this.status = null;
         this._visitables.get("status").remove(this.status);
      }

      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public StatusNested withNewStatus() {
      return new StatusNested((ReplicaSetStatus)null);
   }

   public StatusNested withNewStatusLike(ReplicaSetStatus item) {
      return new StatusNested(item);
   }

   public StatusNested editStatus() {
      return this.withNewStatusLike((ReplicaSetStatus)Optional.ofNullable(this.buildStatus()).orElse((Object)null));
   }

   public StatusNested editOrNewStatus() {
      return this.withNewStatusLike((ReplicaSetStatus)Optional.ofNullable(this.buildStatus()).orElse((new ReplicaSetStatusBuilder()).build()));
   }

   public StatusNested editOrNewStatusLike(ReplicaSetStatus item) {
      return this.withNewStatusLike((ReplicaSetStatus)Optional.ofNullable(this.buildStatus()).orElse(item));
   }

   public ReplicaSetFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ReplicaSetFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ReplicaSetFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ReplicaSetFluent removeFromAdditionalProperties(Map map) {
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

   public ReplicaSetFluent withAdditionalProperties(Map additionalProperties) {
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
            ReplicaSetFluent that = (ReplicaSetFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.spec, that.spec)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.spec, this.status, this.additionalProperties, super.hashCode()});
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

      if (this.spec != null) {
         sb.append("spec:");
         sb.append(this.spec + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
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
         return ReplicaSetFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class SpecNested extends ReplicaSetSpecFluent implements Nested {
      ReplicaSetSpecBuilder builder;

      SpecNested(ReplicaSetSpec item) {
         this.builder = new ReplicaSetSpecBuilder(this, item);
      }

      public Object and() {
         return ReplicaSetFluent.this.withSpec(this.builder.build());
      }

      public Object endSpec() {
         return this.and();
      }
   }

   public class StatusNested extends ReplicaSetStatusFluent implements Nested {
      ReplicaSetStatusBuilder builder;

      StatusNested(ReplicaSetStatus item) {
         this.builder = new ReplicaSetStatusBuilder(this, item);
      }

      public Object and() {
         return ReplicaSetFluent.this.withStatus(this.builder.build());
      }

      public Object endStatus() {
         return this.and();
      }
   }
}
