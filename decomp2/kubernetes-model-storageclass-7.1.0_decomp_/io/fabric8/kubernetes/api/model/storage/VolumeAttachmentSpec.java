package io.fabric8.kubernetes.api.model.storage;

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
@JsonPropertyOrder({"attacher", "nodeName", "source"})
public class VolumeAttachmentSpec implements Editable, KubernetesResource {
   @JsonProperty("attacher")
   private String attacher;
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("source")
   private VolumeAttachmentSource source;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeAttachmentSpec() {
   }

   public VolumeAttachmentSpec(String attacher, String nodeName, VolumeAttachmentSource source) {
      this.attacher = attacher;
      this.nodeName = nodeName;
      this.source = source;
   }

   @JsonProperty("attacher")
   public String getAttacher() {
      return this.attacher;
   }

   @JsonProperty("attacher")
   public void setAttacher(String attacher) {
      this.attacher = attacher;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("source")
   public VolumeAttachmentSource getSource() {
      return this.source;
   }

   @JsonProperty("source")
   public void setSource(VolumeAttachmentSource source) {
      this.source = source;
   }

   @JsonIgnore
   public VolumeAttachmentSpecBuilder edit() {
      return new VolumeAttachmentSpecBuilder(this);
   }

   @JsonIgnore
   public VolumeAttachmentSpecBuilder toBuilder() {
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
      String var10000 = this.getAttacher();
      return "VolumeAttachmentSpec(attacher=" + var10000 + ", nodeName=" + this.getNodeName() + ", source=" + this.getSource() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeAttachmentSpec)) {
         return false;
      } else {
         VolumeAttachmentSpec other = (VolumeAttachmentSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attacher = this.getAttacher();
            Object other$attacher = other.getAttacher();
            if (this$attacher == null) {
               if (other$attacher != null) {
                  return false;
               }
            } else if (!this$attacher.equals(other$attacher)) {
               return false;
            }

            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$source = this.getSource();
            Object other$source = other.getSource();
            if (this$source == null) {
               if (other$source != null) {
                  return false;
               }
            } else if (!this$source.equals(other$source)) {
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
      return other instanceof VolumeAttachmentSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attacher = this.getAttacher();
      result = result * 59 + ($attacher == null ? 43 : $attacher.hashCode());
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
