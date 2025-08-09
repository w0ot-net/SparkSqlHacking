package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HTTPURLRewriteFilterFluent extends BaseFluent {
   private String hostname;
   private HTTPPathModifierBuilder path;
   private Map additionalProperties;

   public HTTPURLRewriteFilterFluent() {
   }

   public HTTPURLRewriteFilterFluent(HTTPURLRewriteFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPURLRewriteFilter instance) {
      instance = instance != null ? instance : new HTTPURLRewriteFilter();
      if (instance != null) {
         this.withHostname(instance.getHostname());
         this.withPath(instance.getPath());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHostname() {
      return this.hostname;
   }

   public HTTPURLRewriteFilterFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public HTTPPathModifier buildPath() {
      return this.path != null ? this.path.build() : null;
   }

   public HTTPURLRewriteFilterFluent withPath(HTTPPathModifier path) {
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

   public HTTPURLRewriteFilterFluent withNewPath(String replaceFullPath, String replacePrefixMatch, String type) {
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

   public HTTPURLRewriteFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPURLRewriteFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPURLRewriteFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPURLRewriteFilterFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPURLRewriteFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPURLRewriteFilterFluent that = (HTTPURLRewriteFilterFluent)o;
            if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
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
      return Objects.hash(new Object[]{this.hostname, this.path, this.additionalProperties, super.hashCode()});
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
         return HTTPURLRewriteFilterFluent.this.withPath(this.builder.build());
      }

      public Object endPath() {
         return this.and();
      }
   }
}
