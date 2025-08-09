package io.fabric8.kubernetes.api.model.storage.v1alpha1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"attachError", "attached", "attachmentMetadata", "detachError"})
public class VolumeAttachmentStatus implements Editable, KubernetesResource {
   @JsonProperty("attachError")
   private VolumeError attachError;
   @JsonProperty("attached")
   private Boolean attached;
   @JsonProperty("attachmentMetadata")
   @JsonInclude(Include.NON_EMPTY)
   private Map attachmentMetadata = new LinkedHashMap();
   @JsonProperty("detachError")
   private VolumeError detachError;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeAttachmentStatus() {
   }

   public VolumeAttachmentStatus(VolumeError attachError, Boolean attached, Map attachmentMetadata, VolumeError detachError) {
      this.attachError = attachError;
      this.attached = attached;
      this.attachmentMetadata = attachmentMetadata;
      this.detachError = detachError;
   }

   @JsonProperty("attachError")
   public VolumeError getAttachError() {
      return this.attachError;
   }

   @JsonProperty("attachError")
   public void setAttachError(VolumeError attachError) {
      this.attachError = attachError;
   }

   @JsonProperty("attached")
   public Boolean getAttached() {
      return this.attached;
   }

   @JsonProperty("attached")
   public void setAttached(Boolean attached) {
      this.attached = attached;
   }

   @JsonProperty("attachmentMetadata")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAttachmentMetadata() {
      return this.attachmentMetadata;
   }

   @JsonProperty("attachmentMetadata")
   public void setAttachmentMetadata(Map attachmentMetadata) {
      this.attachmentMetadata = attachmentMetadata;
   }

   @JsonProperty("detachError")
   public VolumeError getDetachError() {
      return this.detachError;
   }

   @JsonProperty("detachError")
   public void setDetachError(VolumeError detachError) {
      this.detachError = detachError;
   }

   @JsonIgnore
   public VolumeAttachmentStatusBuilder edit() {
      return new VolumeAttachmentStatusBuilder(this);
   }

   @JsonIgnore
   public VolumeAttachmentStatusBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      VolumeError var10000 = this.getAttachError();
      return "VolumeAttachmentStatus(attachError=" + var10000 + ", attached=" + this.getAttached() + ", attachmentMetadata=" + this.getAttachmentMetadata() + ", detachError=" + this.getDetachError() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeAttachmentStatus)) {
         return false;
      } else {
         VolumeAttachmentStatus other = (VolumeAttachmentStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attached = this.getAttached();
            Object other$attached = other.getAttached();
            if (this$attached == null) {
               if (other$attached != null) {
                  return false;
               }
            } else if (!this$attached.equals(other$attached)) {
               return false;
            }

            Object this$attachError = this.getAttachError();
            Object other$attachError = other.getAttachError();
            if (this$attachError == null) {
               if (other$attachError != null) {
                  return false;
               }
            } else if (!this$attachError.equals(other$attachError)) {
               return false;
            }

            Object this$attachmentMetadata = this.getAttachmentMetadata();
            Object other$attachmentMetadata = other.getAttachmentMetadata();
            if (this$attachmentMetadata == null) {
               if (other$attachmentMetadata != null) {
                  return false;
               }
            } else if (!this$attachmentMetadata.equals(other$attachmentMetadata)) {
               return false;
            }

            Object this$detachError = this.getDetachError();
            Object other$detachError = other.getDetachError();
            if (this$detachError == null) {
               if (other$detachError != null) {
                  return false;
               }
            } else if (!this$detachError.equals(other$detachError)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof VolumeAttachmentStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attached = this.getAttached();
      result = result * 59 + ($attached == null ? 43 : $attached.hashCode());
      Object $attachError = this.getAttachError();
      result = result * 59 + ($attachError == null ? 43 : $attachError.hashCode());
      Object $attachmentMetadata = this.getAttachmentMetadata();
      result = result * 59 + ($attachmentMetadata == null ? 43 : $attachmentMetadata.hashCode());
      Object $detachError = this.getDetachError();
      result = result * 59 + ($detachError == null ? 43 : $detachError.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
