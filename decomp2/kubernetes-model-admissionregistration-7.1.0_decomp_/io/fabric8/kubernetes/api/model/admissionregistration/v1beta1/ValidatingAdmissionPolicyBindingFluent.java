package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ValidatingAdmissionPolicyBindingFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ValidatingAdmissionPolicyBindingSpecBuilder spec;
   private Map additionalProperties;

   public ValidatingAdmissionPolicyBindingFluent() {
   }

   public ValidatingAdmissionPolicyBindingFluent(ValidatingAdmissionPolicyBinding instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ValidatingAdmissionPolicyBinding instance) {
      instance = instance != null ? instance : new ValidatingAdmissionPolicyBinding();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withSpec(instance.getSpec());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ValidatingAdmissionPolicyBindingFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ValidatingAdmissionPolicyBindingFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public ValidatingAdmissionPolicyBindingFluent withMetadata(ObjectMeta metadata) {
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

   public ValidatingAdmissionPolicyBindingSpec buildSpec() {
      return this.spec != null ? this.spec.build() : null;
   }

   public ValidatingAdmissionPolicyBindingFluent withSpec(ValidatingAdmissionPolicyBindingSpec spec) {
      this._visitables.remove("spec");
      if (spec != null) {
         this.spec = new ValidatingAdmissionPolicyBindingSpecBuilder(spec);
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
      return new SpecNested((ValidatingAdmissionPolicyBindingSpec)null);
   }

   public SpecNested withNewSpecLike(ValidatingAdmissionPolicyBindingSpec item) {
      return new SpecNested(item);
   }

   public SpecNested editSpec() {
      return this.withNewSpecLike((ValidatingAdmissionPolicyBindingSpec)Optional.ofNullable(this.buildSpec()).orElse((Object)null));
   }

   public SpecNested editOrNewSpec() {
      return this.withNewSpecLike((ValidatingAdmissionPolicyBindingSpec)Optional.ofNullable(this.buildSpec()).orElse((new ValidatingAdmissionPolicyBindingSpecBuilder()).build()));
   }

   public SpecNested editOrNewSpecLike(ValidatingAdmissionPolicyBindingSpec item) {
      return this.withNewSpecLike((ValidatingAdmissionPolicyBindingSpec)Optional.ofNullable(this.buildSpec()).orElse(item));
   }

   public ValidatingAdmissionPolicyBindingFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicyBindingFluent removeFromAdditionalProperties(Map map) {
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

   public ValidatingAdmissionPolicyBindingFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidatingAdmissionPolicyBindingFluent that = (ValidatingAdmissionPolicyBindingFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.spec, that.spec)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.spec, this.additionalProperties, super.hashCode()});
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
         return ValidatingAdmissionPolicyBindingFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class SpecNested extends ValidatingAdmissionPolicyBindingSpecFluent implements Nested {
      ValidatingAdmissionPolicyBindingSpecBuilder builder;

      SpecNested(ValidatingAdmissionPolicyBindingSpec item) {
         this.builder = new ValidatingAdmissionPolicyBindingSpecBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicyBindingFluent.this.withSpec(this.builder.build());
      }

      public Object endSpec() {
         return this.and();
      }
   }
}
