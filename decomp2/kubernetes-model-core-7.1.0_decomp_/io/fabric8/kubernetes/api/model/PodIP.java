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
@JsonPropertyOrder({"ip"})
public class PodIP implements Editable, KubernetesResource {
   @JsonProperty("ip")
   private String ip;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodIP() {
   }

   public PodIP(String ip) {
      this.ip = ip;
   }

   @JsonProperty("ip")
   public String getIp() {
      return this.ip;
   }

   @JsonProperty("ip")
   public void setIp(String ip) {
      this.ip = ip;
   }

   @JsonIgnore
   public PodIPBuilder edit() {
      return new PodIPBuilder(this);
   }

   @JsonIgnore
   public PodIPBuilder toBuilder() {
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
      String var10000 = this.getIp();
      return "PodIP(ip=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodIP)) {
         return false;
      } else {
         PodIP other = (PodIP)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ip = this.getIp();
            Object other$ip = other.getIp();
            if (this$ip == null) {
               if (other$ip != null) {
                  return false;
               }
            } else if (!this$ip.equals(other$ip)) {
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
      return other instanceof PodIP;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ip = this.getIp();
      result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
