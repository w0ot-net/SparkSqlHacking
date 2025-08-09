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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"devicePath", "name"})
public class AttachedVolume implements Editable, KubernetesResource {
   @JsonProperty("devicePath")
   private String devicePath;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AttachedVolume() {
   }

   public AttachedVolume(String devicePath, String name) {
      this.devicePath = devicePath;
      this.name = name;
   }

   @JsonProperty("devicePath")
   public String getDevicePath() {
      return this.devicePath;
   }

   @JsonProperty("devicePath")
   public void setDevicePath(String devicePath) {
      this.devicePath = devicePath;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public AttachedVolumeBuilder edit() {
      return new AttachedVolumeBuilder(this);
   }

   @JsonIgnore
   public AttachedVolumeBuilder toBuilder() {
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
      String var10000 = this.getDevicePath();
      return "AttachedVolume(devicePath=" + var10000 + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AttachedVolume)) {
         return false;
      } else {
         AttachedVolume other = (AttachedVolume)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$devicePath = this.getDevicePath();
            Object other$devicePath = other.getDevicePath();
            if (this$devicePath == null) {
               if (other$devicePath != null) {
                  return false;
               }
            } else if (!this$devicePath.equals(other$devicePath)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
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
      return other instanceof AttachedVolume;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $devicePath = this.getDevicePath();
      result = result * 59 + ($devicePath == null ? 43 : $devicePath.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
