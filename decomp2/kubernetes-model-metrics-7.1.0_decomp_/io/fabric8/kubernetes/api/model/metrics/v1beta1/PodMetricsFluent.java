package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Duration;
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

public class PodMetricsFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList containers = new ArrayList();
   private String kind;
   private ObjectMetaBuilder metadata;
   private String timestamp;
   private Duration window;
   private Map additionalProperties;

   public PodMetricsFluent() {
   }

   public PodMetricsFluent(PodMetrics instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodMetrics instance) {
      instance = instance != null ? instance : new PodMetrics();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withContainers(instance.getContainers());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withTimestamp(instance.getTimestamp());
         this.withWindow(instance.getWindow());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public PodMetricsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public PodMetricsFluent addToContainers(int index, ContainerMetrics item) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
      if (index >= 0 && index < this.containers.size()) {
         this._visitables.get("containers").add(index, builder);
         this.containers.add(index, builder);
      } else {
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodMetricsFluent setToContainers(int index, ContainerMetrics item) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
      if (index >= 0 && index < this.containers.size()) {
         this._visitables.get("containers").set(index, builder);
         this.containers.set(index, builder);
      } else {
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodMetricsFluent addToContainers(ContainerMetrics... items) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      for(ContainerMetrics item : items) {
         ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodMetricsFluent addAllToContainers(Collection items) {
      if (this.containers == null) {
         this.containers = new ArrayList();
      }

      for(ContainerMetrics item : items) {
         ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
         this._visitables.get("containers").add(builder);
         this.containers.add(builder);
      }

      return this;
   }

   public PodMetricsFluent removeFromContainers(ContainerMetrics... items) {
      if (this.containers == null) {
         return this;
      } else {
         for(ContainerMetrics item : items) {
            ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
            this._visitables.get("containers").remove(builder);
            this.containers.remove(builder);
         }

         return this;
      }
   }

   public PodMetricsFluent removeAllFromContainers(Collection items) {
      if (this.containers == null) {
         return this;
      } else {
         for(ContainerMetrics item : items) {
            ContainerMetricsBuilder builder = new ContainerMetricsBuilder(item);
            this._visitables.get("containers").remove(builder);
            this.containers.remove(builder);
         }

         return this;
      }
   }

   public PodMetricsFluent removeMatchingFromContainers(Predicate predicate) {
      if (this.containers == null) {
         return this;
      } else {
         Iterator<ContainerMetricsBuilder> each = this.containers.iterator();
         List visitables = this._visitables.get("containers");

         while(each.hasNext()) {
            ContainerMetricsBuilder builder = (ContainerMetricsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildContainers() {
      return this.containers != null ? build(this.containers) : null;
   }

   public ContainerMetrics buildContainer(int index) {
      return ((ContainerMetricsBuilder)this.containers.get(index)).build();
   }

   public ContainerMetrics buildFirstContainer() {
      return ((ContainerMetricsBuilder)this.containers.get(0)).build();
   }

   public ContainerMetrics buildLastContainer() {
      return ((ContainerMetricsBuilder)this.containers.get(this.containers.size() - 1)).build();
   }

   public ContainerMetrics buildMatchingContainer(Predicate predicate) {
      for(ContainerMetricsBuilder item : this.containers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingContainer(Predicate predicate) {
      for(ContainerMetricsBuilder item : this.containers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodMetricsFluent withContainers(List containers) {
      if (this.containers != null) {
         this._visitables.get("containers").clear();
      }

      if (containers != null) {
         this.containers = new ArrayList();

         for(ContainerMetrics item : containers) {
            this.addToContainers(item);
         }
      } else {
         this.containers = null;
      }

      return this;
   }

   public PodMetricsFluent withContainers(ContainerMetrics... containers) {
      if (this.containers != null) {
         this.containers.clear();
         this._visitables.remove("containers");
      }

      if (containers != null) {
         for(ContainerMetrics item : containers) {
            this.addToContainers(item);
         }
      }

      return this;
   }

   public boolean hasContainers() {
      return this.containers != null && !this.containers.isEmpty();
   }

   public ContainersNested addNewContainer() {
      return new ContainersNested(-1, (ContainerMetrics)null);
   }

   public ContainersNested addNewContainerLike(ContainerMetrics item) {
      return new ContainersNested(-1, item);
   }

   public ContainersNested setNewContainerLike(int index, ContainerMetrics item) {
      return new ContainersNested(index, item);
   }

   public ContainersNested editContainer(int index) {
      if (this.containers.size() <= index) {
         throw new RuntimeException("Can't edit containers. Index exceeds size.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public ContainersNested editFirstContainer() {
      if (this.containers.size() == 0) {
         throw new RuntimeException("Can't edit first containers. The list is empty.");
      } else {
         return this.setNewContainerLike(0, this.buildContainer(0));
      }
   }

   public ContainersNested editLastContainer() {
      int index = this.containers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last containers. The list is empty.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public ContainersNested editMatchingContainer(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.containers.size(); ++i) {
         if (predicate.test((ContainerMetricsBuilder)this.containers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching containers. No match found.");
      } else {
         return this.setNewContainerLike(index, this.buildContainer(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public PodMetricsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public PodMetricsFluent withMetadata(ObjectMeta metadata) {
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

   public PodMetricsFluent withTimestamp(String timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public boolean hasTimestamp() {
      return this.timestamp != null;
   }

   public Duration getWindow() {
      return this.window;
   }

   public PodMetricsFluent withWindow(Duration window) {
      this.window = window;
      return this;
   }

   public boolean hasWindow() {
      return this.window != null;
   }

   public PodMetricsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodMetricsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodMetricsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodMetricsFluent removeFromAdditionalProperties(Map map) {
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

   public PodMetricsFluent withAdditionalProperties(Map additionalProperties) {
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
            PodMetricsFluent that = (PodMetricsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.containers, that.containers)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.timestamp, that.timestamp)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.containers, this.kind, this.metadata, this.timestamp, this.window, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.containers != null && !this.containers.isEmpty()) {
         sb.append("containers:");
         sb.append(this.containers + ",");
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

   public class ContainersNested extends ContainerMetricsFluent implements Nested {
      ContainerMetricsBuilder builder;
      int index;

      ContainersNested(int index, ContainerMetrics item) {
         this.index = index;
         this.builder = new ContainerMetricsBuilder(this, item);
      }

      public Object and() {
         return PodMetricsFluent.this.setToContainers(this.index, this.builder.build());
      }

      public Object endContainer() {
         return this.and();
      }
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return PodMetricsFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
