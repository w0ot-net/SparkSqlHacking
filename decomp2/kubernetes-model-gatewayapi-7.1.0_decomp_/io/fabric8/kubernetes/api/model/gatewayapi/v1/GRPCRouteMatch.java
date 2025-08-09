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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"headers", "method"})
public class GRPCRouteMatch implements Editable, KubernetesResource {
   @JsonProperty("headers")
   @JsonInclude(Include.NON_EMPTY)
   private List headers = new ArrayList();
   @JsonProperty("method")
   private GRPCMethodMatch method;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GRPCRouteMatch() {
   }

   public GRPCRouteMatch(List headers, GRPCMethodMatch method) {
      this.headers = headers;
      this.method = method;
   }

   @JsonProperty("headers")
   @JsonInclude(Include.NON_EMPTY)
   public List getHeaders() {
      return this.headers;
   }

   @JsonProperty("headers")
   public void setHeaders(List headers) {
      this.headers = headers;
   }

   @JsonProperty("method")
   public GRPCMethodMatch getMethod() {
      return this.method;
   }

   @JsonProperty("method")
   public void setMethod(GRPCMethodMatch method) {
      this.method = method;
   }

   @JsonIgnore
   public GRPCRouteMatchBuilder edit() {
      return new GRPCRouteMatchBuilder(this);
   }

   @JsonIgnore
   public GRPCRouteMatchBuilder toBuilder() {
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
      List var10000 = this.getHeaders();
      return "GRPCRouteMatch(headers=" + var10000 + ", method=" + this.getMethod() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GRPCRouteMatch)) {
         return false;
      } else {
         GRPCRouteMatch other = (GRPCRouteMatch)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$headers = this.getHeaders();
            Object other$headers = other.getHeaders();
            if (this$headers == null) {
               if (other$headers != null) {
                  return false;
               }
            } else if (!this$headers.equals(other$headers)) {
               return false;
            }

            Object this$method = this.getMethod();
            Object other$method = other.getMethod();
            if (this$method == null) {
               if (other$method != null) {
                  return false;
               }
            } else if (!this$method.equals(other$method)) {
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
      return other instanceof GRPCRouteMatch;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $headers = this.getHeaders();
      result = result * 59 + ($headers == null ? 43 : $headers.hashCode());
      Object $method = this.getMethod();
      result = result * 59 + ($method == null ? 43 : $method.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
