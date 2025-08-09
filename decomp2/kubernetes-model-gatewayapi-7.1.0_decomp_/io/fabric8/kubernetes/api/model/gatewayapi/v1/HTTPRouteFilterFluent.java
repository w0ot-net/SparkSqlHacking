package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HTTPRouteFilterFluent extends BaseFluent {
   private io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder extensionRef;
   private HTTPHeaderFilterBuilder requestHeaderModifier;
   private HTTPRequestMirrorFilterBuilder requestMirror;
   private HTTPRequestRedirectFilterBuilder requestRedirect;
   private HTTPHeaderFilterBuilder responseHeaderModifier;
   private String type;
   private HTTPURLRewriteFilterBuilder urlRewrite;
   private Map additionalProperties;

   public HTTPRouteFilterFluent() {
   }

   public HTTPRouteFilterFluent(HTTPRouteFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRouteFilter instance) {
      instance = instance != null ? instance : new HTTPRouteFilter();
      if (instance != null) {
         this.withExtensionRef(instance.getExtensionRef());
         this.withRequestHeaderModifier(instance.getRequestHeaderModifier());
         this.withRequestMirror(instance.getRequestMirror());
         this.withRequestRedirect(instance.getRequestRedirect());
         this.withResponseHeaderModifier(instance.getResponseHeaderModifier());
         this.withType(instance.getType());
         this.withUrlRewrite(instance.getUrlRewrite());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public io.fabric8.kubernetes.api.model.LocalObjectReference buildExtensionRef() {
      return this.extensionRef != null ? this.extensionRef.build() : null;
   }

   public HTTPRouteFilterFluent withExtensionRef(io.fabric8.kubernetes.api.model.LocalObjectReference extensionRef) {
      this._visitables.remove("extensionRef");
      if (extensionRef != null) {
         this.extensionRef = new io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder(extensionRef);
         this._visitables.get("extensionRef").add(this.extensionRef);
      } else {
         this.extensionRef = null;
         this._visitables.get("extensionRef").remove(this.extensionRef);
      }

      return this;
   }

   public boolean hasExtensionRef() {
      return this.extensionRef != null;
   }

   public HTTPRouteFilterFluent withNewExtensionRef(String name) {
      return this.withExtensionRef(new io.fabric8.kubernetes.api.model.LocalObjectReference(name));
   }

   public ExtensionRefNested withNewExtensionRef() {
      return new ExtensionRefNested((io.fabric8.kubernetes.api.model.LocalObjectReference)null);
   }

   public ExtensionRefNested withNewExtensionRefLike(io.fabric8.kubernetes.api.model.LocalObjectReference item) {
      return new ExtensionRefNested(item);
   }

   public ExtensionRefNested editExtensionRef() {
      return this.withNewExtensionRefLike((io.fabric8.kubernetes.api.model.LocalObjectReference)Optional.ofNullable(this.buildExtensionRef()).orElse((Object)null));
   }

   public ExtensionRefNested editOrNewExtensionRef() {
      return this.withNewExtensionRefLike((io.fabric8.kubernetes.api.model.LocalObjectReference)Optional.ofNullable(this.buildExtensionRef()).orElse((new io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder()).build()));
   }

   public ExtensionRefNested editOrNewExtensionRefLike(io.fabric8.kubernetes.api.model.LocalObjectReference item) {
      return this.withNewExtensionRefLike((io.fabric8.kubernetes.api.model.LocalObjectReference)Optional.ofNullable(this.buildExtensionRef()).orElse(item));
   }

   public HTTPHeaderFilter buildRequestHeaderModifier() {
      return this.requestHeaderModifier != null ? this.requestHeaderModifier.build() : null;
   }

   public HTTPRouteFilterFluent withRequestHeaderModifier(HTTPHeaderFilter requestHeaderModifier) {
      this._visitables.remove("requestHeaderModifier");
      if (requestHeaderModifier != null) {
         this.requestHeaderModifier = new HTTPHeaderFilterBuilder(requestHeaderModifier);
         this._visitables.get("requestHeaderModifier").add(this.requestHeaderModifier);
      } else {
         this.requestHeaderModifier = null;
         this._visitables.get("requestHeaderModifier").remove(this.requestHeaderModifier);
      }

      return this;
   }

   public boolean hasRequestHeaderModifier() {
      return this.requestHeaderModifier != null;
   }

   public RequestHeaderModifierNested withNewRequestHeaderModifier() {
      return new RequestHeaderModifierNested((HTTPHeaderFilter)null);
   }

   public RequestHeaderModifierNested withNewRequestHeaderModifierLike(HTTPHeaderFilter item) {
      return new RequestHeaderModifierNested(item);
   }

   public RequestHeaderModifierNested editRequestHeaderModifier() {
      return this.withNewRequestHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildRequestHeaderModifier()).orElse((Object)null));
   }

   public RequestHeaderModifierNested editOrNewRequestHeaderModifier() {
      return this.withNewRequestHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildRequestHeaderModifier()).orElse((new HTTPHeaderFilterBuilder()).build()));
   }

   public RequestHeaderModifierNested editOrNewRequestHeaderModifierLike(HTTPHeaderFilter item) {
      return this.withNewRequestHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildRequestHeaderModifier()).orElse(item));
   }

   public HTTPRequestMirrorFilter buildRequestMirror() {
      return this.requestMirror != null ? this.requestMirror.build() : null;
   }

   public HTTPRouteFilterFluent withRequestMirror(HTTPRequestMirrorFilter requestMirror) {
      this._visitables.remove("requestMirror");
      if (requestMirror != null) {
         this.requestMirror = new HTTPRequestMirrorFilterBuilder(requestMirror);
         this._visitables.get("requestMirror").add(this.requestMirror);
      } else {
         this.requestMirror = null;
         this._visitables.get("requestMirror").remove(this.requestMirror);
      }

      return this;
   }

   public boolean hasRequestMirror() {
      return this.requestMirror != null;
   }

   public RequestMirrorNested withNewRequestMirror() {
      return new RequestMirrorNested((HTTPRequestMirrorFilter)null);
   }

   public RequestMirrorNested withNewRequestMirrorLike(HTTPRequestMirrorFilter item) {
      return new RequestMirrorNested(item);
   }

   public RequestMirrorNested editRequestMirror() {
      return this.withNewRequestMirrorLike((HTTPRequestMirrorFilter)Optional.ofNullable(this.buildRequestMirror()).orElse((Object)null));
   }

   public RequestMirrorNested editOrNewRequestMirror() {
      return this.withNewRequestMirrorLike((HTTPRequestMirrorFilter)Optional.ofNullable(this.buildRequestMirror()).orElse((new HTTPRequestMirrorFilterBuilder()).build()));
   }

   public RequestMirrorNested editOrNewRequestMirrorLike(HTTPRequestMirrorFilter item) {
      return this.withNewRequestMirrorLike((HTTPRequestMirrorFilter)Optional.ofNullable(this.buildRequestMirror()).orElse(item));
   }

   public HTTPRequestRedirectFilter buildRequestRedirect() {
      return this.requestRedirect != null ? this.requestRedirect.build() : null;
   }

   public HTTPRouteFilterFluent withRequestRedirect(HTTPRequestRedirectFilter requestRedirect) {
      this._visitables.remove("requestRedirect");
      if (requestRedirect != null) {
         this.requestRedirect = new HTTPRequestRedirectFilterBuilder(requestRedirect);
         this._visitables.get("requestRedirect").add(this.requestRedirect);
      } else {
         this.requestRedirect = null;
         this._visitables.get("requestRedirect").remove(this.requestRedirect);
      }

      return this;
   }

   public boolean hasRequestRedirect() {
      return this.requestRedirect != null;
   }

   public RequestRedirectNested withNewRequestRedirect() {
      return new RequestRedirectNested((HTTPRequestRedirectFilter)null);
   }

   public RequestRedirectNested withNewRequestRedirectLike(HTTPRequestRedirectFilter item) {
      return new RequestRedirectNested(item);
   }

   public RequestRedirectNested editRequestRedirect() {
      return this.withNewRequestRedirectLike((HTTPRequestRedirectFilter)Optional.ofNullable(this.buildRequestRedirect()).orElse((Object)null));
   }

   public RequestRedirectNested editOrNewRequestRedirect() {
      return this.withNewRequestRedirectLike((HTTPRequestRedirectFilter)Optional.ofNullable(this.buildRequestRedirect()).orElse((new HTTPRequestRedirectFilterBuilder()).build()));
   }

   public RequestRedirectNested editOrNewRequestRedirectLike(HTTPRequestRedirectFilter item) {
      return this.withNewRequestRedirectLike((HTTPRequestRedirectFilter)Optional.ofNullable(this.buildRequestRedirect()).orElse(item));
   }

   public HTTPHeaderFilter buildResponseHeaderModifier() {
      return this.responseHeaderModifier != null ? this.responseHeaderModifier.build() : null;
   }

   public HTTPRouteFilterFluent withResponseHeaderModifier(HTTPHeaderFilter responseHeaderModifier) {
      this._visitables.remove("responseHeaderModifier");
      if (responseHeaderModifier != null) {
         this.responseHeaderModifier = new HTTPHeaderFilterBuilder(responseHeaderModifier);
         this._visitables.get("responseHeaderModifier").add(this.responseHeaderModifier);
      } else {
         this.responseHeaderModifier = null;
         this._visitables.get("responseHeaderModifier").remove(this.responseHeaderModifier);
      }

      return this;
   }

   public boolean hasResponseHeaderModifier() {
      return this.responseHeaderModifier != null;
   }

   public ResponseHeaderModifierNested withNewResponseHeaderModifier() {
      return new ResponseHeaderModifierNested((HTTPHeaderFilter)null);
   }

   public ResponseHeaderModifierNested withNewResponseHeaderModifierLike(HTTPHeaderFilter item) {
      return new ResponseHeaderModifierNested(item);
   }

   public ResponseHeaderModifierNested editResponseHeaderModifier() {
      return this.withNewResponseHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildResponseHeaderModifier()).orElse((Object)null));
   }

   public ResponseHeaderModifierNested editOrNewResponseHeaderModifier() {
      return this.withNewResponseHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildResponseHeaderModifier()).orElse((new HTTPHeaderFilterBuilder()).build()));
   }

   public ResponseHeaderModifierNested editOrNewResponseHeaderModifierLike(HTTPHeaderFilter item) {
      return this.withNewResponseHeaderModifierLike((HTTPHeaderFilter)Optional.ofNullable(this.buildResponseHeaderModifier()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public HTTPRouteFilterFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public HTTPURLRewriteFilter buildUrlRewrite() {
      return this.urlRewrite != null ? this.urlRewrite.build() : null;
   }

   public HTTPRouteFilterFluent withUrlRewrite(HTTPURLRewriteFilter urlRewrite) {
      this._visitables.remove("urlRewrite");
      if (urlRewrite != null) {
         this.urlRewrite = new HTTPURLRewriteFilterBuilder(urlRewrite);
         this._visitables.get("urlRewrite").add(this.urlRewrite);
      } else {
         this.urlRewrite = null;
         this._visitables.get("urlRewrite").remove(this.urlRewrite);
      }

      return this;
   }

   public boolean hasUrlRewrite() {
      return this.urlRewrite != null;
   }

   public UrlRewriteNested withNewUrlRewrite() {
      return new UrlRewriteNested((HTTPURLRewriteFilter)null);
   }

   public UrlRewriteNested withNewUrlRewriteLike(HTTPURLRewriteFilter item) {
      return new UrlRewriteNested(item);
   }

   public UrlRewriteNested editUrlRewrite() {
      return this.withNewUrlRewriteLike((HTTPURLRewriteFilter)Optional.ofNullable(this.buildUrlRewrite()).orElse((Object)null));
   }

   public UrlRewriteNested editOrNewUrlRewrite() {
      return this.withNewUrlRewriteLike((HTTPURLRewriteFilter)Optional.ofNullable(this.buildUrlRewrite()).orElse((new HTTPURLRewriteFilterBuilder()).build()));
   }

   public UrlRewriteNested editOrNewUrlRewriteLike(HTTPURLRewriteFilter item) {
      return this.withNewUrlRewriteLike((HTTPURLRewriteFilter)Optional.ofNullable(this.buildUrlRewrite()).orElse(item));
   }

   public HTTPRouteFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRouteFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRouteFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRouteFilterFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public HTTPRouteFilterFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            HTTPRouteFilterFluent that = (HTTPRouteFilterFluent)o;
            if (!Objects.equals(this.extensionRef, that.extensionRef)) {
               return false;
            } else if (!Objects.equals(this.requestHeaderModifier, that.requestHeaderModifier)) {
               return false;
            } else if (!Objects.equals(this.requestMirror, that.requestMirror)) {
               return false;
            } else if (!Objects.equals(this.requestRedirect, that.requestRedirect)) {
               return false;
            } else if (!Objects.equals(this.responseHeaderModifier, that.responseHeaderModifier)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
               return false;
            } else if (!Objects.equals(this.urlRewrite, that.urlRewrite)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.extensionRef, this.requestHeaderModifier, this.requestMirror, this.requestRedirect, this.responseHeaderModifier, this.type, this.urlRewrite, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.extensionRef != null) {
         sb.append("extensionRef:");
         sb.append(this.extensionRef + ",");
      }

      if (this.requestHeaderModifier != null) {
         sb.append("requestHeaderModifier:");
         sb.append(this.requestHeaderModifier + ",");
      }

      if (this.requestMirror != null) {
         sb.append("requestMirror:");
         sb.append(this.requestMirror + ",");
      }

      if (this.requestRedirect != null) {
         sb.append("requestRedirect:");
         sb.append(this.requestRedirect + ",");
      }

      if (this.responseHeaderModifier != null) {
         sb.append("responseHeaderModifier:");
         sb.append(this.responseHeaderModifier + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.urlRewrite != null) {
         sb.append("urlRewrite:");
         sb.append(this.urlRewrite + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ExtensionRefNested extends io.fabric8.kubernetes.api.model.LocalObjectReferenceFluent implements Nested {
      io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder builder;

      ExtensionRefNested(io.fabric8.kubernetes.api.model.LocalObjectReference item) {
         this.builder = new io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withExtensionRef(this.builder.build());
      }

      public Object endExtensionRef() {
         return this.and();
      }
   }

   public class RequestHeaderModifierNested extends HTTPHeaderFilterFluent implements Nested {
      HTTPHeaderFilterBuilder builder;

      RequestHeaderModifierNested(HTTPHeaderFilter item) {
         this.builder = new HTTPHeaderFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withRequestHeaderModifier(this.builder.build());
      }

      public Object endRequestHeaderModifier() {
         return this.and();
      }
   }

   public class RequestMirrorNested extends HTTPRequestMirrorFilterFluent implements Nested {
      HTTPRequestMirrorFilterBuilder builder;

      RequestMirrorNested(HTTPRequestMirrorFilter item) {
         this.builder = new HTTPRequestMirrorFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withRequestMirror(this.builder.build());
      }

      public Object endRequestMirror() {
         return this.and();
      }
   }

   public class RequestRedirectNested extends HTTPRequestRedirectFilterFluent implements Nested {
      HTTPRequestRedirectFilterBuilder builder;

      RequestRedirectNested(HTTPRequestRedirectFilter item) {
         this.builder = new HTTPRequestRedirectFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withRequestRedirect(this.builder.build());
      }

      public Object endRequestRedirect() {
         return this.and();
      }
   }

   public class ResponseHeaderModifierNested extends HTTPHeaderFilterFluent implements Nested {
      HTTPHeaderFilterBuilder builder;

      ResponseHeaderModifierNested(HTTPHeaderFilter item) {
         this.builder = new HTTPHeaderFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withResponseHeaderModifier(this.builder.build());
      }

      public Object endResponseHeaderModifier() {
         return this.and();
      }
   }

   public class UrlRewriteNested extends HTTPURLRewriteFilterFluent implements Nested {
      HTTPURLRewriteFilterBuilder builder;

      UrlRewriteNested(HTTPURLRewriteFilter item) {
         this.builder = new HTTPURLRewriteFilterBuilder(this, item);
      }

      public Object and() {
         return HTTPRouteFilterFluent.this.withUrlRewrite(this.builder.build());
      }

      public Object endUrlRewrite() {
         return this.and();
      }
   }
}
