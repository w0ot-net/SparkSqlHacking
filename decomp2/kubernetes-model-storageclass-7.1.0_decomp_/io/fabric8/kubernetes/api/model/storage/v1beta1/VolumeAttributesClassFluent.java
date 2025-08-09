package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeAttributesClassFluent extends BaseFluent {
   private String apiVersion;
   private String driverName;
   private String kind;
   private ObjectMetaBuilder metadata;
   private Map parameters;
   private Map additionalProperties;

   public VolumeAttributesClassFluent() {
   }

   public VolumeAttributesClassFluent(VolumeAttributesClass instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeAttributesClass instance) {
      instance = instance != null ? instance : new VolumeAttributesClass();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDriverName(instance.getDriverName());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withParameters(instance.getParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public VolumeAttributesClassFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getDriverName() {
      return this.driverName;
   }

   public VolumeAttributesClassFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public String getKind() {
      return this.kind;
   }

   public VolumeAttributesClassFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public VolumeAttributesClassFluent withMetadata(ObjectMeta metadata) {
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

   public VolumeAttributesClassFluent addToParameters(String key, String value) {
      if (this.parameters == null && key != null && value != null) {
         this.parameters = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.parameters.put(key, value);
      }

      return this;
   }

   public VolumeAttributesClassFluent addToParameters(Map map) {
      if (this.parameters == null && map != null) {
         this.parameters = new LinkedHashMap();
      }

      if (map != null) {
         this.parameters.putAll(map);
      }

      return this;
   }

   public VolumeAttributesClassFluent removeFromParameters(String key) {
      if (this.parameters == null) {
         return this;
      } else {
         if (key != null && this.parameters != null) {
            this.parameters.remove(key);
         }

         return this;
      }
   }

   public VolumeAttributesClassFluent removeFromParameters(Map map) {
      if (this.parameters == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.parameters != null) {
                  this.parameters.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getParameters() {
      return this.parameters;
   }

   public VolumeAttributesClassFluent withParameters(Map parameters) {
      if (parameters == null) {
         this.parameters = null;
      } else {
         this.parameters = new LinkedHashMap(parameters);
      }

      return this;
   }

   public boolean hasParameters() {
      return this.parameters != null;
   }

   public VolumeAttributesClassFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeAttributesClassFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeAttributesClassFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeAttributesClassFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeAttributesClassFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeAttributesClassFluent that = (VolumeAttributesClassFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.driverName, that.driverName)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.parameters, that.parameters)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.driverName, this.kind, this.metadata, this.parameters, this.additionalProperties, super.hashCode()});
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

      if (this.parameters != null && !this.parameters.isEmpty()) {
         sb.append("parameters:");
         sb.append(this.parameters + ",");
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
         return VolumeAttributesClassFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
