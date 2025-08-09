package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ClusterTrustBundleProjectionFluent extends BaseFluent {
   private LabelSelectorBuilder labelSelector;
   private String name;
   private Boolean optional;
   private String path;
   private String signerName;
   private Map additionalProperties;

   public ClusterTrustBundleProjectionFluent() {
   }

   public ClusterTrustBundleProjectionFluent(ClusterTrustBundleProjection instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ClusterTrustBundleProjection instance) {
      instance = instance != null ? instance : new ClusterTrustBundleProjection();
      if (instance != null) {
         this.withLabelSelector(instance.getLabelSelector());
         this.withName(instance.getName());
         this.withOptional(instance.getOptional());
         this.withPath(instance.getPath());
         this.withSignerName(instance.getSignerName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LabelSelector buildLabelSelector() {
      return this.labelSelector != null ? this.labelSelector.build() : null;
   }

   public ClusterTrustBundleProjectionFluent withLabelSelector(LabelSelector labelSelector) {
      this._visitables.remove("labelSelector");
      if (labelSelector != null) {
         this.labelSelector = new LabelSelectorBuilder(labelSelector);
         this._visitables.get("labelSelector").add(this.labelSelector);
      } else {
         this.labelSelector = null;
         this._visitables.get("labelSelector").remove(this.labelSelector);
      }

      return this;
   }

   public boolean hasLabelSelector() {
      return this.labelSelector != null;
   }

   public LabelSelectorNested withNewLabelSelector() {
      return new LabelSelectorNested((LabelSelector)null);
   }

   public LabelSelectorNested withNewLabelSelectorLike(LabelSelector item) {
      return new LabelSelectorNested(item);
   }

   public LabelSelectorNested editLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((Object)null));
   }

   public LabelSelectorNested editOrNewLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public LabelSelectorNested editOrNewLabelSelectorLike(LabelSelector item) {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public ClusterTrustBundleProjectionFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Boolean getOptional() {
      return this.optional;
   }

   public ClusterTrustBundleProjectionFluent withOptional(Boolean optional) {
      this.optional = optional;
      return this;
   }

   public boolean hasOptional() {
      return this.optional != null;
   }

   public String getPath() {
      return this.path;
   }

   public ClusterTrustBundleProjectionFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public String getSignerName() {
      return this.signerName;
   }

   public ClusterTrustBundleProjectionFluent withSignerName(String signerName) {
      this.signerName = signerName;
      return this;
   }

   public boolean hasSignerName() {
      return this.signerName != null;
   }

   public ClusterTrustBundleProjectionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ClusterTrustBundleProjectionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ClusterTrustBundleProjectionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ClusterTrustBundleProjectionFluent removeFromAdditionalProperties(Map map) {
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

   public ClusterTrustBundleProjectionFluent withAdditionalProperties(Map additionalProperties) {
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
            ClusterTrustBundleProjectionFluent that = (ClusterTrustBundleProjectionFluent)o;
            if (!Objects.equals(this.labelSelector, that.labelSelector)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.optional, that.optional)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.signerName, that.signerName)) {
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
      return Objects.hash(new Object[]{this.labelSelector, this.name, this.optional, this.path, this.signerName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.labelSelector != null) {
         sb.append("labelSelector:");
         sb.append(this.labelSelector + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.optional != null) {
         sb.append("optional:");
         sb.append(this.optional + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.signerName != null) {
         sb.append("signerName:");
         sb.append(this.signerName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ClusterTrustBundleProjectionFluent withOptional() {
      return this.withOptional(true);
   }

   public class LabelSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      LabelSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return ClusterTrustBundleProjectionFluent.this.withLabelSelector(this.builder.build());
      }

      public Object endLabelSelector() {
         return this.and();
      }
   }
}
