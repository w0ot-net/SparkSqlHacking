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
@JsonPropertyOrder({"count"})
public class VolumeNodeResources implements Editable, KubernetesResource {
   @JsonProperty("count")
   private Integer count;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeNodeResources() {
   }

   public VolumeNodeResources(Integer count) {
      this.count = count;
   }

   @JsonProperty("count")
   public Integer getCount() {
      return this.count;
   }

   @JsonProperty("count")
   public void setCount(Integer count) {
      this.count = count;
   }

   @JsonIgnore
   public VolumeNodeResourcesBuilder edit() {
      return new VolumeNodeResourcesBuilder(this);
   }

   @JsonIgnore
   public VolumeNodeResourcesBuilder toBuilder() {
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
      Integer var10000 = this.getCount();
      return "VolumeNodeResources(count=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeNodeResources)) {
         return false;
      } else {
         VolumeNodeResources other = (VolumeNodeResources)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$count = this.getCount();
            Object other$count = other.getCount();
            if (this$count == null) {
               if (other$count != null) {
                  return false;
               }
            } else if (!this$count.equals(other$count)) {
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
      return other instanceof VolumeNodeResources;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $count = this.getCount();
      result = result * 59 + ($count == null ? 43 : $count.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
