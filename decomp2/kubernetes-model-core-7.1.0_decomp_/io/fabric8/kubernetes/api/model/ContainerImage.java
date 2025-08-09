package io.fabric8.kubernetes.api.model;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"names", "sizeBytes"})
public class ContainerImage implements Editable, KubernetesResource {
   @JsonProperty("names")
   @JsonInclude(Include.NON_EMPTY)
   private List names = new ArrayList();
   @JsonProperty("sizeBytes")
   private Long sizeBytes;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerImage() {
   }

   public ContainerImage(List names, Long sizeBytes) {
      this.names = names;
      this.sizeBytes = sizeBytes;
   }

   @JsonProperty("names")
   @JsonInclude(Include.NON_EMPTY)
   public List getNames() {
      return this.names;
   }

   @JsonProperty("names")
   public void setNames(List names) {
      this.names = names;
   }

   @JsonProperty("sizeBytes")
   public Long getSizeBytes() {
      return this.sizeBytes;
   }

   @JsonProperty("sizeBytes")
   public void setSizeBytes(Long sizeBytes) {
      this.sizeBytes = sizeBytes;
   }

   @JsonIgnore
   public ContainerImageBuilder edit() {
      return new ContainerImageBuilder(this);
   }

   @JsonIgnore
   public ContainerImageBuilder toBuilder() {
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
      List var10000 = this.getNames();
      return "ContainerImage(names=" + var10000 + ", sizeBytes=" + this.getSizeBytes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerImage)) {
         return false;
      } else {
         ContainerImage other = (ContainerImage)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$sizeBytes = this.getSizeBytes();
            Object other$sizeBytes = other.getSizeBytes();
            if (this$sizeBytes == null) {
               if (other$sizeBytes != null) {
                  return false;
               }
            } else if (!this$sizeBytes.equals(other$sizeBytes)) {
               return false;
            }

            Object this$names = this.getNames();
            Object other$names = other.getNames();
            if (this$names == null) {
               if (other$names != null) {
                  return false;
               }
            } else if (!this$names.equals(other$names)) {
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
      return other instanceof ContainerImage;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $sizeBytes = this.getSizeBytes();
      result = result * 59 + ($sizeBytes == null ? 43 : $sizeBytes.hashCode());
      Object $names = this.getNames();
      result = result * 59 + ($names == null ? 43 : $names.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
