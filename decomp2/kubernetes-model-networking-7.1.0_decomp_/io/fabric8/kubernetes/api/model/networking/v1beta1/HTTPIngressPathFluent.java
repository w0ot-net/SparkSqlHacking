package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HTTPIngressPathFluent extends BaseFluent {
   private IngressBackendBuilder backend;
   private String path;
   private String pathType;
   private Map additionalProperties;

   public HTTPIngressPathFluent() {
   }

   public HTTPIngressPathFluent(HTTPIngressPath instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPIngressPath instance) {
      instance = instance != null ? instance : new HTTPIngressPath();
      if (instance != null) {
         this.withBackend(instance.getBackend());
         this.withPath(instance.getPath());
         this.withPathType(instance.getPathType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IngressBackend buildBackend() {
      return this.backend != null ? this.backend.build() : null;
   }

   public HTTPIngressPathFluent withBackend(IngressBackend backend) {
      this._visitables.remove("backend");
      if (backend != null) {
         this.backend = new IngressBackendBuilder(backend);
         this._visitables.get("backend").add(this.backend);
      } else {
         this.backend = null;
         this._visitables.get("backend").remove(this.backend);
      }

      return this;
   }

   public boolean hasBackend() {
      return this.backend != null;
   }

   public BackendNested withNewBackend() {
      return new BackendNested((IngressBackend)null);
   }

   public BackendNested withNewBackendLike(IngressBackend item) {
      return new BackendNested(item);
   }

   public BackendNested editBackend() {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse((Object)null));
   }

   public BackendNested editOrNewBackend() {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse((new IngressBackendBuilder()).build()));
   }

   public BackendNested editOrNewBackendLike(IngressBackend item) {
      return this.withNewBackendLike((IngressBackend)Optional.ofNullable(this.buildBackend()).orElse(item));
   }

   public String getPath() {
      return this.path;
   }

   public HTTPIngressPathFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public String getPathType() {
      return this.pathType;
   }

   public HTTPIngressPathFluent withPathType(String pathType) {
      this.pathType = pathType;
      return this;
   }

   public boolean hasPathType() {
      return this.pathType != null;
   }

   public HTTPIngressPathFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPIngressPathFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPIngressPathFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPIngressPathFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPIngressPathFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPIngressPathFluent that = (HTTPIngressPathFluent)o;
            if (!Objects.equals(this.backend, that.backend)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.pathType, that.pathType)) {
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
      return Objects.hash(new Object[]{this.backend, this.path, this.pathType, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backend != null) {
         sb.append("backend:");
         sb.append(this.backend + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.pathType != null) {
         sb.append("pathType:");
         sb.append(this.pathType + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendNested extends IngressBackendFluent implements Nested {
      IngressBackendBuilder builder;

      BackendNested(IngressBackend item) {
         this.builder = new IngressBackendBuilder(this, item);
      }

      public Object and() {
         return HTTPIngressPathFluent.this.withBackend(this.builder.build());
      }

      public Object endBackend() {
         return this.and();
      }
   }
}
