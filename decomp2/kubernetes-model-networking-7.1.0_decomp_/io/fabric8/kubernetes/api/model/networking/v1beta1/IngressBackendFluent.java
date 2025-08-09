package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.IntOrStringBuilder;
import io.fabric8.kubernetes.api.model.IntOrStringFluent;
import io.fabric8.kubernetes.api.model.TypedLocalObjectReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IngressBackendFluent extends BaseFluent {
   private TypedLocalObjectReference resource;
   private String serviceName;
   private IntOrStringBuilder servicePort;
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
         this.withServiceName(instance.getServiceName());
         this.withServicePort(instance.getServicePort());
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

   public String getServiceName() {
      return this.serviceName;
   }

   public IngressBackendFluent withServiceName(String serviceName) {
      this.serviceName = serviceName;
      return this;
   }

   public boolean hasServiceName() {
      return this.serviceName != null;
   }

   public IntOrString buildServicePort() {
      return this.servicePort != null ? this.servicePort.build() : null;
   }

   public IngressBackendFluent withServicePort(IntOrString servicePort) {
      this._visitables.remove("servicePort");
      if (servicePort != null) {
         this.servicePort = new IntOrStringBuilder(servicePort);
         this._visitables.get("servicePort").add(this.servicePort);
      } else {
         this.servicePort = null;
         this._visitables.get("servicePort").remove(this.servicePort);
      }

      return this;
   }

   public boolean hasServicePort() {
      return this.servicePort != null;
   }

   public IngressBackendFluent withNewServicePort(Object value) {
      return this.withServicePort(new IntOrString(value));
   }

   public ServicePortNested withNewServicePort() {
      return new ServicePortNested((IntOrString)null);
   }

   public ServicePortNested withNewServicePortLike(IntOrString item) {
      return new ServicePortNested(item);
   }

   public ServicePortNested editServicePort() {
      return this.withNewServicePortLike((IntOrString)Optional.ofNullable(this.buildServicePort()).orElse((Object)null));
   }

   public ServicePortNested editOrNewServicePort() {
      return this.withNewServicePortLike((IntOrString)Optional.ofNullable(this.buildServicePort()).orElse((new IntOrStringBuilder()).build()));
   }

   public ServicePortNested editOrNewServicePortLike(IntOrString item) {
      return this.withNewServicePortLike((IntOrString)Optional.ofNullable(this.buildServicePort()).orElse(item));
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
            } else if (!Objects.equals(this.serviceName, that.serviceName)) {
               return false;
            } else if (!Objects.equals(this.servicePort, that.servicePort)) {
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
      return Objects.hash(new Object[]{this.resource, this.serviceName, this.servicePort, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.serviceName != null) {
         sb.append("serviceName:");
         sb.append(this.serviceName + ",");
      }

      if (this.servicePort != null) {
         sb.append("servicePort:");
         sb.append(this.servicePort + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ServicePortNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      ServicePortNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return IngressBackendFluent.this.withServicePort(this.builder.build());
      }

      public Object endServicePort() {
         return this.and();
      }
   }
}
