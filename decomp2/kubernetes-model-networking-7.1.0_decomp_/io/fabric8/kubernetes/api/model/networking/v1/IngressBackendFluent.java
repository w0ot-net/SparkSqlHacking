package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.TypedLocalObjectReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IngressBackendFluent extends BaseFluent {
   private TypedLocalObjectReference resource;
   private IngressServiceBackendBuilder service;
   private Map additionalProperties;

   public IngressBackendFluent() {
   }

   public IngressBackendFluent(IngressBackend instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressBackend instance) {
      instance = instance != null ? instance : new IngressBackend();
      if (instance != null) {
         this.withResource(instance.getResource());
         this.withService(instance.getService());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TypedLocalObjectReference getResource() {
      return this.resource;
   }

   public IngressBackendFluent withResource(TypedLocalObjectReference resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public IngressBackendFluent withNewResource(String apiGroup, String kind, String name) {
      return this.withResource(new TypedLocalObjectReference(apiGroup, kind, name));
   }

   public IngressServiceBackend buildService() {
      return this.service != null ? this.service.build() : null;
   }

   public IngressBackendFluent withService(IngressServiceBackend service) {
      this._visitables.remove("service");
      if (service != null) {
         this.service = new IngressServiceBackendBuilder(service);
         this._visitables.get("service").add(this.service);
      } else {
         this.service = null;
         this._visitables.get("service").remove(this.service);
      }

      return this;
   }

   public boolean hasService() {
      return this.service != null;
   }

   public ServiceNested withNewService() {
      return new ServiceNested((IngressServiceBackend)null);
   }

   public ServiceNested withNewServiceLike(IngressServiceBackend item) {
      return new ServiceNested(item);
   }

   public ServiceNested editService() {
      return this.withNewServiceLike((IngressServiceBackend)Optional.ofNullable(this.buildService()).orElse((Object)null));
   }

   public ServiceNested editOrNewService() {
      return this.withNewServiceLike((IngressServiceBackend)Optional.ofNullable(this.buildService()).orElse((new IngressServiceBackendBuilder()).build()));
   }

   public ServiceNested editOrNewServiceLike(IngressServiceBackend item) {
      return this.withNewServiceLike((IngressServiceBackend)Optional.ofNullable(this.buildService()).orElse(item));
   }

   public IngressBackendFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressBackendFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressBackendFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressBackendFluent removeFromAdditionalProperties(Map map) {
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

   public IngressBackendFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressBackendFluent that = (IngressBackendFluent)o;
            if (!Objects.equals(this.resource, that.resource)) {
               return false;
            } else if (!Objects.equals(this.service, that.service)) {
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
      return Objects.hash(new Object[]{this.resource, this.service, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.service != null) {
         sb.append("service:");
         sb.append(this.service + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ServiceNested extends IngressServiceBackendFluent implements Nested {
      IngressServiceBackendBuilder builder;

      ServiceNested(IngressServiceBackend item) {
         this.builder = new IngressServiceBackendBuilder(this, item);
      }

      public Object and() {
         return IngressBackendFluent.this.withService(this.builder.build());
      }

      public Object endService() {
         return this.and();
      }
   }
}
