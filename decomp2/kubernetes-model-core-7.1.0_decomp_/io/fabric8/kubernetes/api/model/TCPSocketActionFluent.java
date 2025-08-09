package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class TCPSocketActionFluent extends BaseFluent {
   private String host;
   private IntOrStringBuilder port;
   private Map additionalProperties;

   public TCPSocketActionFluent() {
   }

   public TCPSocketActionFluent(TCPSocketAction instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TCPSocketAction instance) {
      instance = instance != null ? instance : new TCPSocketAction();
      if (instance != null) {
         this.withHost(instance.getHost());
         this.withPort(instance.getPort());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHost() {
      return this.host;
   }

   public TCPSocketActionFluent withHost(String host) {
      this.host = host;
      return this;
   }

   public boolean hasHost() {
      return this.host != null;
   }

   public IntOrString buildPort() {
      return this.port != null ? this.port.build() : null;
   }

   public TCPSocketActionFluent withPort(IntOrString port) {
      this._visitables.remove("port");
      if (port != null) {
         this.port = new IntOrStringBuilder(port);
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

   public TCPSocketActionFluent withNewPort(Object value) {
      return this.withPort(new IntOrString(value));
   }

   public PortNested withNewPort() {
      return new PortNested((IntOrString)null);
   }

   public PortNested withNewPortLike(IntOrString item) {
      return new PortNested(item);
   }

   public PortNested editPort() {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse((Object)null));
   }

   public PortNested editOrNewPort() {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse((new IntOrStringBuilder()).build()));
   }

   public PortNested editOrNewPortLike(IntOrString item) {
      return this.withNewPortLike((IntOrString)Optional.ofNullable(this.buildPort()).orElse(item));
   }

   public TCPSocketActionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TCPSocketActionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TCPSocketActionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TCPSocketActionFluent removeFromAdditionalProperties(Map map) {
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

   public TCPSocketActionFluent withAdditionalProperties(Map additionalProperties) {
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
            TCPSocketActionFluent that = (TCPSocketActionFluent)o;
            if (!Objects.equals(this.host, that.host)) {
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
      return Objects.hash(new Object[]{this.host, this.port, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.host != null) {
         sb.append("host:");
         sb.append(this.host + ",");
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

   public class PortNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      PortNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return TCPSocketActionFluent.this.withPort(this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
