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
@JsonPropertyOrder({"kubeletEndpoint"})
public class NodeDaemonEndpoints implements Editable, KubernetesResource {
   @JsonProperty("kubeletEndpoint")
   private DaemonEndpoint kubeletEndpoint;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeDaemonEndpoints() {
   }

   public NodeDaemonEndpoints(DaemonEndpoint kubeletEndpoint) {
      this.kubeletEndpoint = kubeletEndpoint;
   }

   @JsonProperty("kubeletEndpoint")
   public DaemonEndpoint getKubeletEndpoint() {
      return this.kubeletEndpoint;
   }

   @JsonProperty("kubeletEndpoint")
   public void setKubeletEndpoint(DaemonEndpoint kubeletEndpoint) {
      this.kubeletEndpoint = kubeletEndpoint;
   }

   @JsonIgnore
   public NodeDaemonEndpointsBuilder edit() {
      return new NodeDaemonEndpointsBuilder(this);
   }

   @JsonIgnore
   public NodeDaemonEndpointsBuilder toBuilder() {
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
      DaemonEndpoint var10000 = this.getKubeletEndpoint();
      return "NodeDaemonEndpoints(kubeletEndpoint=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeDaemonEndpoints)) {
         return false;
      } else {
         NodeDaemonEndpoints other = (NodeDaemonEndpoints)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$kubeletEndpoint = this.getKubeletEndpoint();
            Object other$kubeletEndpoint = other.getKubeletEndpoint();
            if (this$kubeletEndpoint == null) {
               if (other$kubeletEndpoint != null) {
                  return false;
               }
            } else if (!this$kubeletEndpoint.equals(other$kubeletEndpoint)) {
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
      return other instanceof NodeDaemonEndpoints;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $kubeletEndpoint = this.getKubeletEndpoint();
      result = result * 59 + ($kubeletEndpoint == null ? 43 : $kubeletEndpoint.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
