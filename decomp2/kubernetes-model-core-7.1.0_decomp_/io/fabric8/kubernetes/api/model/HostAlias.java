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
@JsonPropertyOrder({"hostnames", "ip"})
public class HostAlias implements Editable, KubernetesResource {
   @JsonProperty("hostnames")
   @JsonInclude(Include.NON_EMPTY)
   private List hostnames = new ArrayList();
   @JsonProperty("ip")
   private String ip;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HostAlias() {
   }

   public HostAlias(List hostnames, String ip) {
      this.hostnames = hostnames;
      this.ip = ip;
   }

   @JsonProperty("hostnames")
   @JsonInclude(Include.NON_EMPTY)
   public List getHostnames() {
      return this.hostnames;
   }

   @JsonProperty("hostnames")
   public void setHostnames(List hostnames) {
      this.hostnames = hostnames;
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
   public HostAliasBuilder edit() {
      return new HostAliasBuilder(this);
   }

   @JsonIgnore
   public HostAliasBuilder toBuilder() {
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
      List var10000 = this.getHostnames();
      return "HostAlias(hostnames=" + var10000 + ", ip=" + this.getIp() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HostAlias)) {
         return false;
      } else {
         HostAlias other = (HostAlias)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostnames = this.getHostnames();
            Object other$hostnames = other.getHostnames();
            if (this$hostnames == null) {
               if (other$hostnames != null) {
                  return false;
               }
            } else if (!this$hostnames.equals(other$hostnames)) {
               return false;
            }

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
      return other instanceof HostAlias;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostnames = this.getHostnames();
      result = result * 59 + ($hostnames == null ? 43 : $hostnames.hashCode());
      Object $ip = this.getIp();
      result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
