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
@JsonPropertyOrder({"hostname", "ip", "nodeName", "targetRef"})
public class EndpointAddress implements Editable, KubernetesResource {
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("ip")
   private String ip;
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("targetRef")
   private ObjectReference targetRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EndpointAddress() {
   }

   public EndpointAddress(String hostname, String ip, String nodeName, ObjectReference targetRef) {
      this.hostname = hostname;
      this.ip = ip;
      this.nodeName = nodeName;
      this.targetRef = targetRef;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("ip")
   public String getIp() {
      return this.ip;
   }

   @JsonProperty("ip")
   public void setIp(String ip) {
      this.ip = ip;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("targetRef")
   public ObjectReference getTargetRef() {
      return this.targetRef;
   }

   @JsonProperty("targetRef")
   public void setTargetRef(ObjectReference targetRef) {
      this.targetRef = targetRef;
   }

   @JsonIgnore
   public EndpointAddressBuilder edit() {
      return new EndpointAddressBuilder(this);
   }

   @JsonIgnore
   public EndpointAddressBuilder toBuilder() {
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
      String var10000 = this.getHostname();
      return "EndpointAddress(hostname=" + var10000 + ", ip=" + this.getIp() + ", nodeName=" + this.getNodeName() + ", targetRef=" + this.getTargetRef() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EndpointAddress)) {
         return false;
      } else {
         EndpointAddress other = (EndpointAddress)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
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

            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$targetRef = this.getTargetRef();
            Object other$targetRef = other.getTargetRef();
            if (this$targetRef == null) {
               if (other$targetRef != null) {
                  return false;
               }
            } else if (!this$targetRef.equals(other$targetRef)) {
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
      return other instanceof EndpointAddress;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $ip = this.getIp();
      result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $targetRef = this.getTargetRef();
      result = result * 59 + ($targetRef == null ? 43 : $targetRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
