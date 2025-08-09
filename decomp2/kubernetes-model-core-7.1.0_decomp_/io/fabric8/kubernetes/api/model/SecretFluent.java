package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SecretFluent extends BaseFluent {
   private String apiVersion;
   private Map data;
   private Boolean immutable;
   private String kind;
   private ObjectMetaBuilder metadata;
   private Map stringData;
   private String type;
   private Map additionalProperties;

   public SecretFluent() {
   }

   public SecretFluent(Secret instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Secret instance) {
      instance = instance != null ? instance : new Secret();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withData(instance.getData());
         this.withImmutable(instance.getImmutable());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withStringData(instance.getStringData());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public SecretFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public SecretFluent addToData(String key, String value) {
      if (this.data == null && key != null && value != null) {
         this.data = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.data.put(key, value);
      }

      return this;
   }

   public SecretFluent addToData(Map map) {
      if (this.data == null && map != null) {
         this.data = new LinkedHashMap();
      }

      if (map != null) {
         this.data.putAll(map);
      }

      return this;
   }

   public SecretFluent removeFromData(String key) {
      if (this.data == null) {
         return this;
      } else {
         if (key != null && this.data != null) {
            this.data.remove(key);
         }

         return this;
      }
   }

   public SecretFluent removeFromData(Map map) {
      if (this.data == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.data != null) {
                  this.data.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getData() {
      return this.data;
   }

   public SecretFluent withData(Map data) {
      if (data == null) {
         this.data = null;
      } else {
         this.data = new LinkedHashMap(data);
      }

      return this;
   }

   public boolean hasData() {
      return this.data != null;
   }

   public Boolean getImmutable() {
      return this.immutable;
   }

   public SecretFluent withImmutable(Boolean immutable) {
      this.immutable = immutable;
      return this;
   }

   public boolean hasImmutable() {
      return this.immutable != null;
   }

   public String getKind() {
      return this.kind;
   }

   public SecretFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public SecretFluent withMetadata(ObjectMeta metadata) {
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

   public SecretFluent addToStringData(String key, String value) {
      if (this.stringData == null && key != null && value != null) {
         this.stringData = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.stringData.put(key, value);
      }

      return this;
   }

   public SecretFluent addToStringData(Map map) {
      if (this.stringData == null && map != null) {
         this.stringData = new LinkedHashMap();
      }

      if (map != null) {
         this.stringData.putAll(map);
      }

      return this;
   }

   public SecretFluent removeFromStringData(String key) {
      if (this.stringData == null) {
         return this;
      } else {
         if (key != null && this.stringData != null) {
            this.stringData.remove(key);
         }

         return this;
      }
   }

   public SecretFluent removeFromStringData(Map map) {
      if (this.stringData == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.stringData != null) {
                  this.stringData.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getStringData() {
      return this.stringData;
   }

   public SecretFluent withStringData(Map stringData) {
      if (stringData == null) {
         this.stringData = null;
      } else {
         this.stringData = new LinkedHashMap(stringData);
      }

      return this;
   }

   public boolean hasStringData() {
      return this.stringData != null;
   }

   public String getType() {
      return this.type;
   }

   public SecretFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public SecretFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SecretFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SecretFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SecretFluent removeFromAdditionalProperties(Map map) {
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

   public SecretFluent withAdditionalProperties(Map additionalProperties) {
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
            SecretFluent that = (SecretFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.data, that.data)) {
               return false;
            } else if (!Objects.equals(this.immutable, that.immutable)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.stringData, that.stringData)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.data, this.immutable, this.kind, this.metadata, this.stringData, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.data != null && !this.data.isEmpty()) {
         sb.append("data:");
         sb.append(this.data + ",");
      }

      if (this.immutable != null) {
         sb.append("immutable:");
         sb.append(this.immutable + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.stringData != null && !this.stringData.isEmpty()) {
         sb.append("stringData:");
         sb.append(this.stringData + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public SecretFluent withImmutable() {
      return this.withImmutable(true);
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return SecretFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
