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
@JsonPropertyOrder({"backendRequest", "request"})
public class HTTPRouteTimeouts implements Editable, KubernetesResource {
   @JsonProperty("backendRequest")
   private String backendRequest;
   @JsonProperty("request")
   private String request;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPRouteTimeouts() {
   }

   public HTTPRouteTimeouts(String backendRequest, String request) {
      this.backendRequest = backendRequest;
      this.request = request;
   }

   @JsonProperty("backendRequest")
   public String getBackendRequest() {
      return this.backendRequest;
   }

   @JsonProperty("backendRequest")
   public void setBackendRequest(String backendRequest) {
      this.backendRequest = backendRequest;
   }

   @JsonProperty("request")
   public String getRequest() {
      return this.request;
   }

   @JsonProperty("request")
   public void setRequest(String request) {
      this.request = request;
   }

   @JsonIgnore
   public HTTPRouteTimeoutsBuilder edit() {
      return new HTTPRouteTimeoutsBuilder(this);
   }

   @JsonIgnore
   public HTTPRouteTimeoutsBuilder toBuilder() {
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
      String var10000 = this.getBackendRequest();
      return "HTTPRouteTimeouts(backendRequest=" + var10000 + ", request=" + this.getRequest() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPRouteTimeouts)) {
         return false;
      } else {
         HTTPRouteTimeouts other = (HTTPRouteTimeouts)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$backendRequest = this.getBackendRequest();
            Object other$backendRequest = other.getBackendRequest();
            if (this$backendRequest == null) {
               if (other$backendRequest != null) {
                  return false;
               }
            } else if (!this$backendRequest.equals(other$backendRequest)) {
               return false;
            }

            Object this$request = this.getRequest();
            Object other$request = other.getRequest();
            if (this$request == null) {
               if (other$request != null) {
                  return false;
               }
            } else if (!this$request.equals(other$request)) {
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
      return other instanceof HTTPRouteTimeouts;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $backendRequest = this.getBackendRequest();
      result = result * 59 + ($backendRequest == null ? 43 : $backendRequest.hashCode());
      Object $request = this.getRequest();
      result = result * 59 + ($request == null ? 43 : $request.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
