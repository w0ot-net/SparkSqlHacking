package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"method", "service", "type"})
public class GRPCMethodMatch implements Editable, KubernetesResource {
   @JsonProperty("method")
   private String method;
   @JsonProperty("service")
   private String service;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GRPCMethodMatch() {
   }

   public GRPCMethodMatch(String method, String service, String type) {
      this.method = method;
      this.service = service;
      this.type = type;
   }

   @JsonProperty("method")
   public String getMethod() {
      return this.method;
   }

   @JsonProperty("method")
   public void setMethod(String method) {
      this.method = method;
   }

   @JsonProperty("service")
   public String getService() {
      return this.service;
   }

   @JsonProperty("service")
   public void setService(String service) {
      this.service = service;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public GRPCMethodMatchBuilder edit() {
      return new GRPCMethodMatchBuilder(this);
   }

   @JsonIgnore
   public GRPCMethodMatchBuilder toBuilder() {
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
      String var10000 = this.getMethod();
      return "GRPCMethodMatch(method=" + var10000 + ", service=" + this.getService() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GRPCMethodMatch)) {
         return false;
      } else {
         GRPCMethodMatch other = (GRPCMethodMatch)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$method = this.getMethod();
            Object other$method = other.getMethod();
            if (this$method == null) {
               if (other$method != null) {
                  return false;
               }
            } else if (!this$method.equals(other$method)) {
               return false;
            }

            Object this$service = this.getService();
            Object other$service = other.getService();
            if (this$service == null) {
               if (other$service != null) {
                  return false;
               }
            } else if (!this$service.equals(other$service)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof GRPCMethodMatch;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $method = this.getMethod();
      result = result * 59 + ($method == null ? 43 : $method.hashCode());
      Object $service = this.getService();
      result = result * 59 + ($service == null ? 43 : $service.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
