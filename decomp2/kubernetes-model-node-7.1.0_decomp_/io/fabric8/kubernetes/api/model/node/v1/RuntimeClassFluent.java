package io.fabric8.kubernetes.api.model.node.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RuntimeClassFluent extends BaseFluent {
   private String apiVersion;
   private String handler;
   private String kind;
   private ObjectMetaBuilder metadata;
   private OverheadBuilder overhead;
   private SchedulingBuilder scheduling;
   private Map additionalProperties;

   public RuntimeClassFluent() {
   }

   public RuntimeClassFluent(RuntimeClass instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RuntimeClass instance) {
      instance = instance != null ? instance : new RuntimeClass();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withHandler(instance.getHandler());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withOverhead(instance.getOverhead());
         this.withScheduling(instance.getScheduling());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public RuntimeClassFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getHandler() {
      return this.handler;
   }

   public RuntimeClassFluent withHandler(String handler) {
      this.handler = handler;
      return this;
   }

   public boolean hasHandler() {
      return this.handler != null;
   }

   public String getKind() {
      return this.kind;
   }

   public RuntimeClassFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public RuntimeClassFluent withMetadata(ObjectMeta metadata) {
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

   public Overhead buildOverhead() {
      return this.overhead != null ? this.overhead.build() : null;
   }

   public RuntimeClassFluent withOverhead(Overhead overhead) {
      this._visitables.remove("overhead");
      if (overhead != null) {
         this.overhead = new OverheadBuilder(overhead);
         this._visitables.get("overhead").add(this.overhead);
      } else {
         this.overhead = null;
         this._visitables.get("overhead").remove(this.overhead);
      }

      return this;
   }

   public boolean hasOverhead() {
      return this.overhead != null;
   }

   public OverheadNested withNewOverhead() {
      return new OverheadNested((Overhead)null);
   }

   public OverheadNested withNewOverheadLike(Overhead item) {
      return new OverheadNested(item);
   }

   public OverheadNested editOverhead() {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse((Object)null));
   }

   public OverheadNested editOrNewOverhead() {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse((new OverheadBuilder()).build()));
   }

   public OverheadNested editOrNewOverheadLike(Overhead item) {
      return this.withNewOverheadLike((Overhead)Optional.ofNullable(this.buildOverhead()).orElse(item));
   }

   public Scheduling buildScheduling() {
      return this.scheduling != null ? this.scheduling.build() : null;
   }

   public RuntimeClassFluent withScheduling(Scheduling scheduling) {
      this._visitables.remove("scheduling");
      if (scheduling != null) {
         this.scheduling = new SchedulingBuilder(scheduling);
         this._visitables.get("scheduling").add(this.scheduling);
      } else {
         this.scheduling = null;
         this._visitables.get("scheduling").remove(this.scheduling);
      }

      return this;
   }

   public boolean hasScheduling() {
      return this.scheduling != null;
   }

   public SchedulingNested withNewScheduling() {
      return new SchedulingNested((Scheduling)null);
   }

   public SchedulingNested withNewSchedulingLike(Scheduling item) {
      return new SchedulingNested(item);
   }

   public SchedulingNested editScheduling() {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse((Object)null));
   }

   public SchedulingNested editOrNewScheduling() {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse((new SchedulingBuilder()).build()));
   }

   public SchedulingNested editOrNewSchedulingLike(Scheduling item) {
      return this.withNewSchedulingLike((Scheduling)Optional.ofNullable(this.buildScheduling()).orElse(item));
   }

   public RuntimeClassFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RuntimeClassFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RuntimeClassFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RuntimeClassFluent removeFromAdditionalProperties(Map map) {
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

   public RuntimeClassFluent withAdditionalProperties(Map additionalProperties) {
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
            RuntimeClassFluent that = (RuntimeClassFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.handler, that.handler)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.overhead, that.overhead)) {
               return false;
            } else if (!Objects.equals(this.scheduling, that.scheduling)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.handler, this.kind, this.metadata, this.overhead, this.scheduling, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.handler != null) {
         sb.append("handler:");
         sb.append(this.handler + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.overhead != null) {
         sb.append("overhead:");
         sb.append(this.overhead + ",");
      }

      if (this.scheduling != null) {
         sb.append("scheduling:");
         sb.append(this.scheduling + ",");
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
         return RuntimeClassFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class OverheadNested extends OverheadFluent implements Nested {
      OverheadBuilder builder;

      OverheadNested(Overhead item) {
         this.builder = new OverheadBuilder(this, item);
      }

      public Object and() {
         return RuntimeClassFluent.this.withOverhead(this.builder.build());
      }

      public Object endOverhead() {
         return this.and();
      }
   }

   public class SchedulingNested extends SchedulingFluent implements Nested {
      SchedulingBuilder builder;

      SchedulingNested(Scheduling item) {
         this.builder = new SchedulingBuilder(this, item);
      }

      public Object and() {
         return RuntimeClassFluent.this.withScheduling(this.builder.build());
      }

      public Object endScheduling() {
         return this.and();
      }
   }
}
