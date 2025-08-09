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
@JsonPropertyOrder({"extensionRef", "requestHeaderModifier", "requestMirror", "requestRedirect", "responseHeaderModifier", "type", "urlRewrite"})
public class HTTPRouteFilter implements Editable, KubernetesResource {
   @JsonProperty("extensionRef")
   private io.fabric8.kubernetes.api.model.LocalObjectReference extensionRef;
   @JsonProperty("requestHeaderModifier")
   private HTTPHeaderFilter requestHeaderModifier;
   @JsonProperty("requestMirror")
   private HTTPRequestMirrorFilter requestMirror;
   @JsonProperty("requestRedirect")
   private HTTPRequestRedirectFilter requestRedirect;
   @JsonProperty("responseHeaderModifier")
   private HTTPHeaderFilter responseHeaderModifier;
   @JsonProperty("type")
   private String type;
   @JsonProperty("urlRewrite")
   private HTTPURLRewriteFilter urlRewrite;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPRouteFilter() {
   }

   public HTTPRouteFilter(io.fabric8.kubernetes.api.model.LocalObjectReference extensionRef, HTTPHeaderFilter requestHeaderModifier, HTTPRequestMirrorFilter requestMirror, HTTPRequestRedirectFilter requestRedirect, HTTPHeaderFilter responseHeaderModifier, String type, HTTPURLRewriteFilter urlRewrite) {
      this.extensionRef = extensionRef;
      this.requestHeaderModifier = requestHeaderModifier;
      this.requestMirror = requestMirror;
      this.requestRedirect = requestRedirect;
      this.responseHeaderModifier = responseHeaderModifier;
      this.type = type;
      this.urlRewrite = urlRewrite;
   }

   @JsonProperty("extensionRef")
   public io.fabric8.kubernetes.api.model.LocalObjectReference getExtensionRef() {
      return this.extensionRef;
   }

   @JsonProperty("extensionRef")
   public void setExtensionRef(io.fabric8.kubernetes.api.model.LocalObjectReference extensionRef) {
      this.extensionRef = extensionRef;
   }

   @JsonProperty("requestHeaderModifier")
   public HTTPHeaderFilter getRequestHeaderModifier() {
      return this.requestHeaderModifier;
   }

   @JsonProperty("requestHeaderModifier")
   public void setRequestHeaderModifier(HTTPHeaderFilter requestHeaderModifier) {
      this.requestHeaderModifier = requestHeaderModifier;
   }

   @JsonProperty("requestMirror")
   public HTTPRequestMirrorFilter getRequestMirror() {
      return this.requestMirror;
   }

   @JsonProperty("requestMirror")
   public void setRequestMirror(HTTPRequestMirrorFilter requestMirror) {
      this.requestMirror = requestMirror;
   }

   @JsonProperty("requestRedirect")
   public HTTPRequestRedirectFilter getRequestRedirect() {
      return this.requestRedirect;
   }

   @JsonProperty("requestRedirect")
   public void setRequestRedirect(HTTPRequestRedirectFilter requestRedirect) {
      this.requestRedirect = requestRedirect;
   }

   @JsonProperty("responseHeaderModifier")
   public HTTPHeaderFilter getResponseHeaderModifier() {
      return this.responseHeaderModifier;
   }

   @JsonProperty("responseHeaderModifier")
   public void setResponseHeaderModifier(HTTPHeaderFilter responseHeaderModifier) {
      this.responseHeaderModifier = responseHeaderModifier;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("urlRewrite")
   public HTTPURLRewriteFilter getUrlRewrite() {
      return this.urlRewrite;
   }

   @JsonProperty("urlRewrite")
   public void setUrlRewrite(HTTPURLRewriteFilter urlRewrite) {
      this.urlRewrite = urlRewrite;
   }

   @JsonIgnore
   public HTTPRouteFilterBuilder edit() {
      return new HTTPRouteFilterBuilder(this);
   }

   @JsonIgnore
   public HTTPRouteFilterBuilder toBuilder() {
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
      io.fabric8.kubernetes.api.model.LocalObjectReference var10000 = this.getExtensionRef();
      return "HTTPRouteFilter(extensionRef=" + var10000 + ", requestHeaderModifier=" + this.getRequestHeaderModifier() + ", requestMirror=" + this.getRequestMirror() + ", requestRedirect=" + this.getRequestRedirect() + ", responseHeaderModifier=" + this.getResponseHeaderModifier() + ", type=" + this.getType() + ", urlRewrite=" + this.getUrlRewrite() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPRouteFilter)) {
         return false;
      } else {
         HTTPRouteFilter other = (HTTPRouteFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$extensionRef = this.getExtensionRef();
            Object other$extensionRef = other.getExtensionRef();
            if (this$extensionRef == null) {
               if (other$extensionRef != null) {
                  return false;
               }
            } else if (!this$extensionRef.equals(other$extensionRef)) {
               return false;
            }

            Object this$requestHeaderModifier = this.getRequestHeaderModifier();
            Object other$requestHeaderModifier = other.getRequestHeaderModifier();
            if (this$requestHeaderModifier == null) {
               if (other$requestHeaderModifier != null) {
                  return false;
               }
            } else if (!this$requestHeaderModifier.equals(other$requestHeaderModifier)) {
               return false;
            }

            Object this$requestMirror = this.getRequestMirror();
            Object other$requestMirror = other.getRequestMirror();
            if (this$requestMirror == null) {
               if (other$requestMirror != null) {
                  return false;
               }
            } else if (!this$requestMirror.equals(other$requestMirror)) {
               return false;
            }

            Object this$requestRedirect = this.getRequestRedirect();
            Object other$requestRedirect = other.getRequestRedirect();
            if (this$requestRedirect == null) {
               if (other$requestRedirect != null) {
                  return false;
               }
            } else if (!this$requestRedirect.equals(other$requestRedirect)) {
               return false;
            }

            Object this$responseHeaderModifier = this.getResponseHeaderModifier();
            Object other$responseHeaderModifier = other.getResponseHeaderModifier();
            if (this$responseHeaderModifier == null) {
               if (other$responseHeaderModifier != null) {
                  return false;
               }
            } else if (!this$responseHeaderModifier.equals(other$responseHeaderModifier)) {
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

            Object this$urlRewrite = this.getUrlRewrite();
            Object other$urlRewrite = other.getUrlRewrite();
            if (this$urlRewrite == null) {
               if (other$urlRewrite != null) {
                  return false;
               }
            } else if (!this$urlRewrite.equals(other$urlRewrite)) {
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
      return other instanceof HTTPRouteFilter;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $extensionRef = this.getExtensionRef();
      result = result * 59 + ($extensionRef == null ? 43 : $extensionRef.hashCode());
      Object $requestHeaderModifier = this.getRequestHeaderModifier();
      result = result * 59 + ($requestHeaderModifier == null ? 43 : $requestHeaderModifier.hashCode());
      Object $requestMirror = this.getRequestMirror();
      result = result * 59 + ($requestMirror == null ? 43 : $requestMirror.hashCode());
      Object $requestRedirect = this.getRequestRedirect();
      result = result * 59 + ($requestRedirect == null ? 43 : $requestRedirect.hashCode());
      Object $responseHeaderModifier = this.getResponseHeaderModifier();
      result = result * 59 + ($responseHeaderModifier == null ? 43 : $responseHeaderModifier.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $urlRewrite = this.getUrlRewrite();
      result = result * 59 + ($urlRewrite == null ? 43 : $urlRewrite.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
