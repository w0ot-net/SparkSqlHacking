package io.fabric8.kubernetes.api.model.scheduling.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PriorityClassFluent extends BaseFluent {
   private String apiVersion;
   private String description;
   private Boolean globalDefault;
   private String kind;
   private ObjectMetaBuilder metadata;
   private String preemptionPolicy;
   private Integer value;
   private Map additionalProperties;

   public PriorityClassFluent() {
   }

   public PriorityClassFluent(PriorityClass instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PriorityClass instance) {
      instance = instance != null ? instance : new PriorityClass();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDescription(instance.getDescription());
         this.withGlobalDefault(instance.getGlobalDefault());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withPreemptionPolicy(instance.getPreemptionPolicy());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public PriorityClassFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getDescription() {
      return this.description;
   }

   public PriorityClassFluent withDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean hasDescription() {
      return this.description != null;
   }

   public Boolean getGlobalDefault() {
      return this.globalDefault;
   }

   public PriorityClassFluent withGlobalDefault(Boolean globalDefault) {
      this.globalDefault = globalDefault;
      return this;
   }

   public boolean hasGlobalDefault() {
      return this.globalDefault != null;
   }

   public String getKind() {
      return this.kind;
   }

   public PriorityClassFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public PriorityClassFluent withMetadata(ObjectMeta metadata) {
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

   public String getPreemptionPolicy() {
      return this.preemptionPolicy;
   }

   public PriorityClassFluent withPreemptionPolicy(String preemptionPolicy) {
      this.preemptionPolicy = preemptionPolicy;
      return this;
   }

   public boolean hasPreemptionPolicy() {
      return this.preemptionPolicy != null;
   }

   public Integer getValue() {
      return this.value;
   }

   public PriorityClassFluent withValue(Integer value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public PriorityClassFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PriorityClassFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PriorityClassFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PriorityClassFluent removeFromAdditionalProperties(Map map) {
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

   public PriorityClassFluent withAdditionalProperties(Map additionalProperties) {
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
            PriorityClassFluent that = (PriorityClassFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.description, that.description)) {
               return false;
            } else if (!Objects.equals(this.globalDefault, that.globalDefault)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.preemptionPolicy, that.preemptionPolicy)) {
               return false;
            } else if (!Objects.equals(this.value, that.value)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.description, this.globalDefault, this.kind, this.metadata, this.preemptionPolicy, this.value, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.description != null) {
         sb.append("description:");
         sb.append(this.description + ",");
      }

      if (this.globalDefault != null) {
         sb.append("globalDefault:");
         sb.append(this.globalDefault + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.preemptionPolicy != null) {
         sb.append("preemptionPolicy:");
         sb.append(this.preemptionPolicy + ",");
      }

      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PriorityClassFluent withGlobalDefault() {
      return this.withGlobalDefault(true);
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return PriorityClassFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
