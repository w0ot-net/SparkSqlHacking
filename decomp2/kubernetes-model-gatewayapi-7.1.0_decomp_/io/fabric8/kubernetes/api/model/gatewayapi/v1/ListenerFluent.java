package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ListenerFluent extends BaseFluent {
   private AllowedRoutesBuilder allowedRoutes;
   private String hostname;
   private String name;
   private Integer port;
   private String protocol;
   private GatewayTLSConfigBuilder tls;
   private Map additionalProperties;

   public ListenerFluent() {
   }

   public ListenerFluent(Listener instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Listener instance) {
      instance = instance != null ? instance : new Listener();
      if (instance != null) {
         this.withAllowedRoutes(instance.getAllowedRoutes());
         this.withHostname(instance.getHostname());
         this.withName(instance.getName());
         this.withPort(instance.getPort());
         this.withProtocol(instance.getProtocol());
         this.withTls(instance.getTls());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AllowedRoutes buildAllowedRoutes() {
      return this.allowedRoutes != null ? this.allowedRoutes.build() : null;
   }

   public ListenerFluent withAllowedRoutes(AllowedRoutes allowedRoutes) {
      this._visitables.remove("allowedRoutes");
      if (allowedRoutes != null) {
         this.allowedRoutes = new AllowedRoutesBuilder(allowedRoutes);
         this._visitables.get("allowedRoutes").add(this.allowedRoutes);
      } else {
         this.allowedRoutes = null;
         this._visitables.get("allowedRoutes").remove(this.allowedRoutes);
      }

      return this;
   }

   public boolean hasAllowedRoutes() {
      return this.allowedRoutes != null;
   }

   public AllowedRoutesNested withNewAllowedRoutes() {
      return new AllowedRoutesNested((AllowedRoutes)null);
   }

   public AllowedRoutesNested withNewAllowedRoutesLike(AllowedRoutes item) {
      return new AllowedRoutesNested(item);
   }

   public AllowedRoutesNested editAllowedRoutes() {
      return this.withNewAllowedRoutesLike((AllowedRoutes)Optional.ofNullable(this.buildAllowedRoutes()).orElse((Object)null));
   }

   public AllowedRoutesNested editOrNewAllowedRoutes() {
      return this.withNewAllowedRoutesLike((AllowedRoutes)Optional.ofNullable(this.buildAllowedRoutes()).orElse((new AllowedRoutesBuilder()).build()));
   }

   public AllowedRoutesNested editOrNewAllowedRoutesLike(AllowedRoutes item) {
      return this.withNewAllowedRoutesLike((AllowedRoutes)Optional.ofNullable(this.buildAllowedRoutes()).orElse(item));
   }

   public String getHostname() {
      return this.hostname;
   }

   public ListenerFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public String getName() {
      return this.name;
   }

   public ListenerFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getPort() {
      return this.port;
   }

   public ListenerFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public ListenerFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public GatewayTLSConfig buildTls() {
      return this.tls != null ? this.tls.build() : null;
   }

   public ListenerFluent withTls(GatewayTLSConfig tls) {
      this._visitables.remove("tls");
      if (tls != null) {
         this.tls = new GatewayTLSConfigBuilder(tls);
         this._visitables.get("tls").add(this.tls);
      } else {
         this.tls = null;
         this._visitables.get("tls").remove(this.tls);
      }

      return this;
   }

   public boolean hasTls() {
      return this.tls != null;
   }

   public TlsNested withNewTls() {
      return new TlsNested((GatewayTLSConfig)null);
   }

   public TlsNested withNewTlsLike(GatewayTLSConfig item) {
      return new TlsNested(item);
   }

   public TlsNested editTls() {
      return this.withNewTlsLike((GatewayTLSConfig)Optional.ofNullable(this.buildTls()).orElse((Object)null));
   }

   public TlsNested editOrNewTls() {
      return this.withNewTlsLike((GatewayTLSConfig)Optional.ofNullable(this.buildTls()).orElse((new GatewayTLSConfigBuilder()).build()));
   }

   public TlsNested editOrNewTlsLike(GatewayTLSConfig item) {
      return this.withNewTlsLike((GatewayTLSConfig)Optional.ofNullable(this.buildTls()).orElse(item));
   }

   public ListenerFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ListenerFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ListenerFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ListenerFluent removeFromAdditionalProperties(Map map) {
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

   public ListenerFluent withAdditionalProperties(Map additionalProperties) {
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
            ListenerFluent that = (ListenerFluent)o;
            if (!Objects.equals(this.allowedRoutes, that.allowedRoutes)) {
               return false;
            } else if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.protocol, that.protocol)) {
               return false;
            } else if (!Objects.equals(this.tls, that.tls)) {
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
      return Objects.hash(new Object[]{this.allowedRoutes, this.hostname, this.name, this.port, this.protocol, this.tls, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowedRoutes != null) {
         sb.append("allowedRoutes:");
         sb.append(this.allowedRoutes + ",");
      }

      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.protocol != null) {
         sb.append("protocol:");
         sb.append(this.protocol + ",");
      }

      if (this.tls != null) {
         sb.append("tls:");
         sb.append(this.tls + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AllowedRoutesNested extends AllowedRoutesFluent implements Nested {
      AllowedRoutesBuilder builder;

      AllowedRoutesNested(AllowedRoutes item) {
         this.builder = new AllowedRoutesBuilder(this, item);
      }

      public Object and() {
         return ListenerFluent.this.withAllowedRoutes(this.builder.build());
      }

      public Object endAllowedRoutes() {
         return this.and();
      }
   }

   public class TlsNested extends GatewayTLSConfigFluent implements Nested {
      GatewayTLSConfigBuilder builder;

      TlsNested(GatewayTLSConfig item) {
         this.builder = new GatewayTLSConfigBuilder(this, item);
      }

      public Object and() {
         return ListenerFluent.this.withTls(this.builder.build());
      }

      public Object endTls() {
         return this.and();
      }
   }
}
