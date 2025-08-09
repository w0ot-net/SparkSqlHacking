package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonSerialize(
   using = MicroTimeSerDes.Serializer.class
)
@JsonDeserialize(
   using = MicroTimeSerDes.Deserializer.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"Time"})
public class MicroTime implements Editable, KubernetesResource {
   @JsonProperty("Time")
   private String time;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MicroTime() {
   }

   public MicroTime(String time) {
      this.time = time;
   }

   @JsonProperty("Time")
   public String getTime() {
      return this.time;
   }

   @JsonProperty("Time")
   public void setTime(String time) {
      this.time = time;
   }

   @JsonIgnore
   public MicroTimeBuilder edit() {
      return new MicroTimeBuilder(this);
   }

   @JsonIgnore
   public MicroTimeBuilder toBuilder() {
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
      String var10000 = this.getTime();
      return "MicroTime(time=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MicroTime)) {
         return false;
      } else {
         MicroTime other = (MicroTime)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$time = this.getTime();
            Object other$time = other.getTime();
            if (this$time == null) {
               if (other$time != null) {
                  return false;
               }
            } else if (!this$time.equals(other$time)) {
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
      return other instanceof MicroTime;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $time = this.getTime();
      result = result * 59 + ($time == null ? 43 : $time.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
