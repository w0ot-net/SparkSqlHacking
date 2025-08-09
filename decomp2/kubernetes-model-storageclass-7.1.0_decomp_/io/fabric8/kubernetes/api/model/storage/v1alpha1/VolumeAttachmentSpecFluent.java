package io.fabric8.kubernetes.api.model.storage.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeAttachmentSpecFluent extends BaseFluent {
   private String attacher;
   private String nodeName;
   private VolumeAttachmentSourceBuilder source;
   private Map additionalProperties;

   public VolumeAttachmentSpecFluent() {
   }

   public VolumeAttachmentSpecFluent(VolumeAttachmentSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeAttachmentSpec instance) {
      instance = instance != null ? instance : new VolumeAttachmentSpec();
      if (instance != null) {
         this.withAttacher(instance.getAttacher());
         this.withNodeName(instance.getNodeName());
         this.withSource(instance.getSource());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAttacher() {
      return this.attacher;
   }

   public VolumeAttachmentSpecFluent withAttacher(String attacher) {
      this.attacher = attacher;
      return this;
   }

   public boolean hasAttacher() {
      return this.attacher != null;
   }

   public String getNodeName() {
      return this.nodeName;
   }

   public VolumeAttachmentSpecFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public VolumeAttachmentSource buildSource() {
      return this.source != null ? this.source.build() : null;
   }

   public VolumeAttachmentSpecFluent withSource(VolumeAttachmentSource source) {
      this._visitables.remove("source");
      if (source != null) {
         this.source = new VolumeAttachmentSourceBuilder(source);
         this._visitables.get("source").add(this.source);
      } else {
         this.source = null;
         this._visitables.get("source").remove(this.source);
      }

      return this;
   }

   public boolean hasSource() {
      return this.source != null;
   }

   public SourceNested withNewSource() {
      return new SourceNested((VolumeAttachmentSource)null);
   }

   public SourceNested withNewSourceLike(VolumeAttachmentSource item) {
      return new SourceNested(item);
   }

   public SourceNested editSource() {
      return this.withNewSourceLike((VolumeAttachmentSource)Optional.ofNullable(this.buildSource()).orElse((Object)null));
   }

   public SourceNested editOrNewSource() {
      return this.withNewSourceLike((VolumeAttachmentSource)Optional.ofNullable(this.buildSource()).orElse((new VolumeAttachmentSourceBuilder()).build()));
   }

   public SourceNested editOrNewSourceLike(VolumeAttachmentSource item) {
      return this.withNewSourceLike((VolumeAttachmentSource)Optional.ofNullable(this.buildSource()).orElse(item));
   }

   public VolumeAttachmentSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeAttachmentSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeAttachmentSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeAttachmentSpecFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeAttachmentSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeAttachmentSpecFluent that = (VolumeAttachmentSpecFluent)o;
            if (!Objects.equals(this.attacher, that.attacher)) {
               return false;
            } else if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.source, that.source)) {
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
      return Objects.hash(new Object[]{this.attacher, this.nodeName, this.source, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attacher != null) {
         sb.append("attacher:");
         sb.append(this.attacher + ",");
      }

      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.source != null) {
         sb.append("source:");
         sb.append(this.source + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SourceNested extends VolumeAttachmentSourceFluent implements Nested {
      VolumeAttachmentSourceBuilder builder;

      SourceNested(VolumeAttachmentSource item) {
         this.builder = new VolumeAttachmentSourceBuilder(this, item);
      }

      public Object and() {
         return VolumeAttachmentSpecFluent.this.withSource(this.builder.build());
      }

      public Object endSource() {
         return this.and();
      }
   }
}
