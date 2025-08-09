package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HTTPRequestRedirectFilterFluent extends BaseFluent {
   private String hostname;
   private HTTPPathModifierBuilder path;
   private Integer port;
   private String scheme;
   private Integer statusCode;
   private Map additionalProperties;

   public HTTPRequestRedirectFilterFluent() {
   }

   public HTTPRequestRedirectFilterFluent(HTTPRequestRedirectFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRequestRedirectFilter instance) {
      instance = instance != null ? instance : new HTTPRequestRedirectFilter();
      if (instance != null) {
         this.withHostname(instance.getHostname());
         this.withPath(instance.getPath());
         this.withPort(instance.getPort());
         this.withScheme(instance.getScheme());
         this.withStatusCode(instance.getStatusCode());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHostname() {
      return this.hostname;
   }

   public HTTPRequestRedirectFilterFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public HTTPPathModifier buildPath() {
      return this.path != null ? this.path.build() : null;
   }

   public HTTPRequestRedirectFilterFluent withPath(HTTPPathModifier path) {
      this._visitables.remove("path");
      if (path != null) {
         this.path = new HTTPPathModifierBuilder(path);
         this._visitables.get("path").add(this.path);
      } else {
         this.path = null;
         this._visitables.get("path").remove(this.path);
      }

      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public HTTPRequestRedirectFilterFluent withNewPath(String replaceFullPath, String replacePrefixMatch, String type) {
      return this.withPath(new HTTPPathModifier(replaceFullPath, replacePrefixMatch, type));
   }

   public PathNested withNewPath() {
      return new PathNested((HTTPPathModifier)null);
   }

   public PathNested withNewPathLike(HTTPPathModifier item) {
      return new PathNested(item);
   }

   public PathNested editPath() {
      return this.withNewPathLike((HTTPPathModifier)Optional.ofNullable(this.buildPath()).orElse((Object)null));
   }

   public PathNested editOrNewPath() {
      return this.withNewPathLike((HTTPPathModifier)Optional.ofNullable(this.buildPath()).orElse((new HTTPPathModifierBuilder()).build()));
   }

   public PathNested editOrNewPathLike(HTTPPathModifier item) {
      return this.withNewPathLike((HTTPPathModifier)Optional.ofNullable(this.buildPath()).orElse(item));
   }

   public Integer getPort() {
      return this.port;
   }

   public HTTPRequestRedirectFilterFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getScheme() {
      return this.scheme;
   }

   public HTTPRequestRedirectFilterFluent withScheme(String scheme) {
      this.scheme = scheme;
      return this;
   }

   public boolean hasScheme() {
      return this.scheme != null;
   }

   public Integer getStatusCode() {
      return this.statusCode;
   }

   public HTTPRequestRedirectFilterFluent withStatusCode(Integer statusCode) {
      this.statusCode = statusCode;
      return this;
   }

   public boolean hasStatusCode() {
      return this.statusCode != null;
   }

   public HTTPRequestRedirectFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRequestRedirectFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRequestRedirectFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRequestRedirectFilterFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRequestRedirectFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRequestRedirectFilterFluent that = (HTTPRequestRedirectFilterFluent)o;
            if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.scheme, that.scheme)) {
               return false;
            } else if (!Objects.equals(this.statusCode, that.statusCode)) {
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
      return Objects.hash(new Object[]{this.hostname, this.path, this.port, this.scheme, this.statusCode, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.scheme != null) {
         sb.append("scheme:");
         sb.append(this.scheme + ",");
      }

      if (this.statusCode != null) {
         sb.append("statusCode:");
         sb.append(this.statusCode + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PathNested extends HTTPPathModifierFluent implements Nested {
      HTTPPathModifierBuilder builder;

      PathNested(HTTPPathModifier item) {
         this.builder = new HTTPPathModifierBuilder(this, item);
      }

      public Object and() {
         return HTTPRequestRedirectFilterFluent.this.withPath(this.builder.build());
      }

      public Object endPath() {
         return this.and();
      }
   }
}
