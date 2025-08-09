package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IngressServiceBackendFluent extends BaseFluent {
   private String name;
   private ServiceBackendPortBuilder port;
   private Map additionalProperties;

   public IngressServiceBackendFluent() {
   }

   public IngressServiceBackendFluent(IngressServiceBackend instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressServiceBackend instance) {
      instance = instance != null ? instance : new IngressServiceBackend();
      if (instance != null) {
         this.withName(instance.getName());
         this.withPort(instance.getPort());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public IngressServiceBackendFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ServiceBackendPort buildPort() {
      return this.port != null ? this.port.build() : null;
   }

   public IngressServiceBackendFluent withPort(ServiceBackendPort port) {
      this._visitables.remove("port");
      if (port != null) {
         this.port = new ServiceBackendPortBuilder(port);
         this._visitables.get("port").add(this.port);
      } else {
         this.port = null;
         this._visitables.get("port").remove(this.port);
      }

      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public IngressServiceBackendFluent withNewPort(String name, Integer number) {
      return this.withPort(new ServiceBackendPort(name, number));
   }

   public PortNested withNewPort() {
      return new PortNested((ServiceBackendPort)null);
   }

   public PortNested withNewPortLike(ServiceBackendPort item) {
      return new PortNested(item);
   }

   public PortNested editPort() {
      return this.withNewPortLike((ServiceBackendPort)Optional.ofNullable(this.buildPort()).orElse((Object)null));
   }

   public PortNested editOrNewPort() {
      return this.withNewPortLike((ServiceBackendPort)Optional.ofNullable(this.buildPort()).orElse((new ServiceBackendPortBuilder()).build()));
   }

   public PortNested editOrNewPortLike(ServiceBackendPort item) {
      return this.withNewPortLike((ServiceBackendPort)Optional.ofNullable(this.buildPort()).orElse(item));
   }

   public IngressServiceBackendFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressServiceBackendFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressServiceBackendFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressServiceBackendFluent removeFromAdditionalProperties(Map map) {
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

   public IngressServiceBackendFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressServiceBackendFluent that = (IngressServiceBackendFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
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
      return Objects.hash(new Object[]{this.name, this.port, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PortNested extends ServiceBackendPortFluent implements Nested {
      ServiceBackendPortBuilder builder;

      PortNested(ServiceBackendPort item) {
         this.builder = new ServiceBackendPortBuilder(this, item);
      }

      public Object and() {
         return IngressServiceBackendFluent.this.withPort(this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
