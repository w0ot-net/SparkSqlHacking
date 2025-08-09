package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GRPCRouteFilterFluent extends BaseFluent {
   private io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder extensionRef;
   private HTTPHeaderFilterBuilder requestHeaderModifier;
   private HTTPRequestMirrorFilterBuilder requestMirror;
   private HTTPHeaderFilterBuilder responseHeaderModifier;
   private String type;
   private Map additionalProperties;

   public GRPCRouteFilterFluent() {
   }

   public GRPCRouteFilterFluent(GRPCRouteFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GRPCRouteFilter instance) {
      instance = instance != null ? instance : new GRPCRouteFilter();
      if (instance != null) {
         this.withExtensionRef(instance.getExtensionRef());
         this.withRequestHeaderModifier(instance.getRequestHeaderModifier());
         this.withRequestMirror(instance.getRequestMirror());
         this.withResponseHeaderModifier(instance.getResponseHeaderModifier());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public io.fabric8.kubernetes.api.model.LocalObjectReference buildExtensionRef() {
      return this.extensionRef != null ? this.extensionRef.build() : null;
   }

   public GRPCRouteFilterFluent withExtensionRef(io.fabric8.kubernetes.api.model.LocalObjectReference extensionRef) {
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

   public GRPCRouteFilterFluent withNewExtensionRef(String name) {
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

   public GRPCRouteFilterFluent withRequestHeaderModifier(HTTPHeaderFilter requestHeaderModifier) {
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

   public GRPCRouteFilterFluent withRequestMirror(HTTPRequestMirrorFilter requestMirror) {
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

   public HTTPHeaderFilter buildResponseHeaderModifier() {
      return this.responseHeaderModifier != null ? this.responseHeaderModifier.build() : null;
   }

   public GRPCRouteFilterFluent withResponseHeaderModifier(HTTPHeaderFilter responseHeaderModifier) {
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

   public GRPCRouteFilterFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public GRPCRouteFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GRPCRouteFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GRPCRouteFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GRPCRouteFilterFluent removeFromAdditionalProperties(Map map) {
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

   public GRPCRouteFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            GRPCRouteFilterFluent that = (GRPCRouteFilterFluent)o;
            if (!Objects.equals(this.extensionRef, that.extensionRef)) {
               return false;
            } else if (!Objects.equals(this.requestHeaderModifier, that.requestHeaderModifier)) {
               return false;
            } else if (!Objects.equals(this.requestMirror, that.requestMirror)) {
               return false;
            } else if (!Objects.equals(this.responseHeaderModifier, that.responseHeaderModifier)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.extensionRef, this.requestHeaderModifier, this.requestMirror, this.responseHeaderModifier, this.type, this.additionalProperties, super.hashCode()});
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

      if (this.responseHeaderModifier != null) {
         sb.append("responseHeaderModifier:");
         sb.append(this.responseHeaderModifier + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
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
         return GRPCRouteFilterFluent.this.withExtensionRef(this.builder.build());
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
         return GRPCRouteFilterFluent.this.withRequestHeaderModifier(this.builder.build());
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
         return GRPCRouteFilterFluent.this.withRequestMirror(this.builder.build());
      }

      public Object endRequestMirror() {
         return this.and();
      }
   }

   public class ResponseHeaderModifierNested extends HTTPHeaderFilterFluent implements Nested {
      HTTPHeaderFilterBuilder builder;

      ResponseHeaderModifierNested(HTTPHeaderFilter item) {
         this.builder = new HTTPHeaderFilterBuilder(this, item);
      }

      public Object and() {
         return GRPCRouteFilterFluent.this.withResponseHeaderModifier(this.builder.build());
      }

      public Object endResponseHeaderModifier() {
         return this.and();
      }
   }
}
