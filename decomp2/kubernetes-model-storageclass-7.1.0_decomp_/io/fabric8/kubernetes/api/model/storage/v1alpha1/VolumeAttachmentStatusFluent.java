package io.fabric8.kubernetes.api.model.storage.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VolumeAttachmentStatusFluent extends BaseFluent {
   private VolumeErrorBuilder attachError;
   private Boolean attached;
   private Map attachmentMetadata;
   private VolumeErrorBuilder detachError;
   private Map additionalProperties;

   public VolumeAttachmentStatusFluent() {
   }

   public VolumeAttachmentStatusFluent(VolumeAttachmentStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeAttachmentStatus instance) {
      instance = instance != null ? instance : new VolumeAttachmentStatus();
      if (instance != null) {
         this.withAttachError(instance.getAttachError());
         this.withAttached(instance.getAttached());
         this.withAttachmentMetadata(instance.getAttachmentMetadata());
         this.withDetachError(instance.getDetachError());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public VolumeError buildAttachError() {
      return this.attachError != null ? this.attachError.build() : null;
   }

   public VolumeAttachmentStatusFluent withAttachError(VolumeError attachError) {
      this._visitables.remove("attachError");
      if (attachError != null) {
         this.attachError = new VolumeErrorBuilder(attachError);
         this._visitables.get("attachError").add(this.attachError);
      } else {
         this.attachError = null;
         this._visitables.get("attachError").remove(this.attachError);
      }

      return this;
   }

   public boolean hasAttachError() {
      return this.attachError != null;
   }

   public VolumeAttachmentStatusFluent withNewAttachError(String message, String time) {
      return this.withAttachError(new VolumeError(message, time));
   }

   public AttachErrorNested withNewAttachError() {
      return new AttachErrorNested((VolumeError)null);
   }

   public AttachErrorNested withNewAttachErrorLike(VolumeError item) {
      return new AttachErrorNested(item);
   }

   public AttachErrorNested editAttachError() {
      return this.withNewAttachErrorLike((VolumeError)Optional.ofNullable(this.buildAttachError()).orElse((Object)null));
   }

   public AttachErrorNested editOrNewAttachError() {
      return this.withNewAttachErrorLike((VolumeError)Optional.ofNullable(this.buildAttachError()).orElse((new VolumeErrorBuilder()).build()));
   }

   public AttachErrorNested editOrNewAttachErrorLike(VolumeError item) {
      return this.withNewAttachErrorLike((VolumeError)Optional.ofNullable(this.buildAttachError()).orElse(item));
   }

   public Boolean getAttached() {
      return this.attached;
   }

   public VolumeAttachmentStatusFluent withAttached(Boolean attached) {
      this.attached = attached;
      return this;
   }

   public boolean hasAttached() {
      return this.attached != null;
   }

   public VolumeAttachmentStatusFluent addToAttachmentMetadata(String key, String value) {
      if (this.attachmentMetadata == null && key != null && value != null) {
         this.attachmentMetadata = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.attachmentMetadata.put(key, value);
      }

      return this;
   }

   public VolumeAttachmentStatusFluent addToAttachmentMetadata(Map map) {
      if (this.attachmentMetadata == null && map != null) {
         this.attachmentMetadata = new LinkedHashMap();
      }

      if (map != null) {
         this.attachmentMetadata.putAll(map);
      }

      return this;
   }

   public VolumeAttachmentStatusFluent removeFromAttachmentMetadata(String key) {
      if (this.attachmentMetadata == null) {
         return this;
      } else {
         if (key != null && this.attachmentMetadata != null) {
            this.attachmentMetadata.remove(key);
         }

         return this;
      }
   }

   public VolumeAttachmentStatusFluent removeFromAttachmentMetadata(Map map) {
      if (this.attachmentMetadata == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.attachmentMetadata != null) {
                  this.attachmentMetadata.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAttachmentMetadata() {
      return this.attachmentMetadata;
   }

   public VolumeAttachmentStatusFluent withAttachmentMetadata(Map attachmentMetadata) {
      if (attachmentMetadata == null) {
         this.attachmentMetadata = null;
      } else {
         this.attachmentMetadata = new LinkedHashMap(attachmentMetadata);
      }

      return this;
   }

   public boolean hasAttachmentMetadata() {
      return this.attachmentMetadata != null;
   }

   public VolumeError buildDetachError() {
      return this.detachError != null ? this.detachError.build() : null;
   }

   public VolumeAttachmentStatusFluent withDetachError(VolumeError detachError) {
      this._visitables.remove("detachError");
      if (detachError != null) {
         this.detachError = new VolumeErrorBuilder(detachError);
         this._visitables.get("detachError").add(this.detachError);
      } else {
         this.detachError = null;
         this._visitables.get("detachError").remove(this.detachError);
      }

      return this;
   }

   public boolean hasDetachError() {
      return this.detachError != null;
   }

   public VolumeAttachmentStatusFluent withNewDetachError(String message, String time) {
      return this.withDetachError(new VolumeError(message, time));
   }

   public DetachErrorNested withNewDetachError() {
      return new DetachErrorNested((VolumeError)null);
   }

   public DetachErrorNested withNewDetachErrorLike(VolumeError item) {
      return new DetachErrorNested(item);
   }

   public DetachErrorNested editDetachError() {
      return this.withNewDetachErrorLike((VolumeError)Optional.ofNullable(this.buildDetachError()).orElse((Object)null));
   }

   public DetachErrorNested editOrNewDetachError() {
      return this.withNewDetachErrorLike((VolumeError)Optional.ofNullable(this.buildDetachError()).orElse((new VolumeErrorBuilder()).build()));
   }

   public DetachErrorNested editOrNewDetachErrorLike(VolumeError item) {
      return this.withNewDetachErrorLike((VolumeError)Optional.ofNullable(this.buildDetachError()).orElse(item));
   }

   public VolumeAttachmentStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeAttachmentStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeAttachmentStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeAttachmentStatusFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeAttachmentStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeAttachmentStatusFluent that = (VolumeAttachmentStatusFluent)o;
            if (!Objects.equals(this.attachError, that.attachError)) {
               return false;
            } else if (!Objects.equals(this.attached, that.attached)) {
               return false;
            } else if (!Objects.equals(this.attachmentMetadata, that.attachmentMetadata)) {
               return false;
            } else if (!Objects.equals(this.detachError, that.detachError)) {
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
      return Objects.hash(new Object[]{this.attachError, this.attached, this.attachmentMetadata, this.detachError, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attachError != null) {
         sb.append("attachError:");
         sb.append(this.attachError + ",");
      }

      if (this.attached != null) {
         sb.append("attached:");
         sb.append(this.attached + ",");
      }

      if (this.attachmentMetadata != null && !this.attachmentMetadata.isEmpty()) {
         sb.append("attachmentMetadata:");
         sb.append(this.attachmentMetadata + ",");
      }

      if (this.detachError != null) {
         sb.append("detachError:");
         sb.append(this.detachError + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public VolumeAttachmentStatusFluent withAttached() {
      return this.withAttached(true);
   }

   public class AttachErrorNested extends VolumeErrorFluent implements Nested {
      VolumeErrorBuilder builder;

      AttachErrorNested(VolumeError item) {
         this.builder = new VolumeErrorBuilder(this, item);
      }

      public Object and() {
         return VolumeAttachmentStatusFluent.this.withAttachError(this.builder.build());
      }

      public Object endAttachError() {
         return this.and();
      }
   }

   public class DetachErrorNested extends VolumeErrorFluent implements Nested {
      VolumeErrorBuilder builder;

      DetachErrorNested(VolumeError item) {
         this.builder = new VolumeErrorBuilder(this, item);
      }

      public Object and() {
         return VolumeAttachmentStatusFluent.this.withDetachError(this.builder.build());
      }

      public Object endDetachError() {
         return this.and();
      }
   }
}
