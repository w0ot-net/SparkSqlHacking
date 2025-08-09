package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.Objects;
import java.util.Optional;

public class CustomResourceFluent extends BaseFluent {
   private ObjectMetaBuilder metadata;
   private Object spec;
   private Object status;
   private String kind;
   private String apiVersion;

   public CustomResourceFluent() {
   }

   public CustomResourceFluent(CustomResource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResource instance) {
      if (instance != null) {
         this.withMetadata(instance.getMetadata());
         this.withSpec(instance.getSpec());
         this.withStatus(instance.getStatus());
         this.withKind(instance.getKind());
         this.withApiVersion(instance.getApiVersion());
      }

   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public CustomResourceFluent withMetadata(ObjectMeta metadata) {
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

   public Object getSpec() {
      return this.spec;
   }

   public CustomResourceFluent withSpec(Object spec) {
      this.spec = spec;
      return this;
   }

   public boolean hasSpec() {
      return this.spec != null;
   }

   public Object getStatus() {
      return this.status;
   }

   public CustomResourceFluent withStatus(Object status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public String getKind() {
      return this.kind;
   }

   public CustomResourceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public CustomResourceFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            CustomResourceFluent that = (CustomResourceFluent)o;
            if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.spec, that.spec)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else {
               return Objects.equals(this.apiVersion, that.apiVersion);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.metadata, this.spec, this.status, this.kind, this.apiVersion, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
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

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion);
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
         return CustomResourceFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
