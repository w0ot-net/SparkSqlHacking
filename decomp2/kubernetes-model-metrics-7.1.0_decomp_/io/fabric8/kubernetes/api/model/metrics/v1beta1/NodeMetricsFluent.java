package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Duration;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NodeMetricsFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private String timestamp;
   private Map usage;
   private Duration window;
   private Map additionalProperties;

   public NodeMetricsFluent() {
   }

   public NodeMetricsFluent(NodeMetrics instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeMetrics instance) {
      instance = instance != null ? instance : new NodeMetrics();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withTimestamp(instance.getTimestamp());
         this.withUsage(instance.getUsage());
         this.withWindow(instance.getWindow());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public NodeMetricsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public NodeMetricsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public NodeMetricsFluent withMetadata(ObjectMeta metadata) {
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

   public String getTimestamp() {
      return this.timestamp;
   }

   public NodeMetricsFluent withTimestamp(String timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public boolean hasTimestamp() {
      return this.timestamp != null;
   }

   public NodeMetricsFluent addToUsage(String key, Quantity value) {
      if (this.usage == null && key != null && value != null) {
         this.usage = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.usage.put(key, value);
      }

      return this;
   }

   public NodeMetricsFluent addToUsage(Map map) {
      if (this.usage == null && map != null) {
         this.usage = new LinkedHashMap();
      }

      if (map != null) {
         this.usage.putAll(map);
      }

      return this;
   }

   public NodeMetricsFluent removeFromUsage(String key) {
      if (this.usage == null) {
         return this;
      } else {
         if (key != null && this.usage != null) {
            this.usage.remove(key);
         }

         return this;
      }
   }

   public NodeMetricsFluent removeFromUsage(Map map) {
      if (this.usage == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.usage != null) {
                  this.usage.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getUsage() {
      return this.usage;
   }

   public NodeMetricsFluent withUsage(Map usage) {
      if (usage == null) {
         this.usage = null;
      } else {
         this.usage = new LinkedHashMap(usage);
      }

      return this;
   }

   public boolean hasUsage() {
      return this.usage != null;
   }

   public Duration getWindow() {
      return this.window;
   }

   public NodeMetricsFluent withWindow(Duration window) {
      this.window = window;
      return this;
   }

   public boolean hasWindow() {
      return this.window != null;
   }

   public NodeMetricsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeMetricsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeMetricsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeMetricsFluent removeFromAdditionalProperties(Map map) {
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

   public NodeMetricsFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeMetricsFluent that = (NodeMetricsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.timestamp, that.timestamp)) {
               return false;
            } else if (!Objects.equals(this.usage, that.usage)) {
               return false;
            } else if (!Objects.equals(this.window, that.window)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.timestamp, this.usage, this.window, this.additionalProperties, super.hashCode()});
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

      if (this.timestamp != null) {
         sb.append("timestamp:");
         sb.append(this.timestamp + ",");
      }

      if (this.usage != null && !this.usage.isEmpty()) {
         sb.append("usage:");
         sb.append(this.usage + ",");
      }

      if (this.window != null) {
         sb.append("window:");
         sb.append(this.window + ",");
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
         return NodeMetricsFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
