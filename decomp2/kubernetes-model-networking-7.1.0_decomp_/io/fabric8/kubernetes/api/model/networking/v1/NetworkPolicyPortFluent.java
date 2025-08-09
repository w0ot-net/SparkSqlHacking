package io.fabric8.kubernetes.api.model.networking.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.IntOrStringBuilder;
import io.fabric8.kubernetes.api.model.IntOrStringFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NetworkPolicyPortFluent extends BaseFluent {
   private Integer endPort;
   private IntOrStringBuilder port;
   private String protocol;
   private Map additionalProperties;

   public NetworkPolicyPortFluent() {
   }

   public NetworkPolicyPortFluent(NetworkPolicyPort instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkPolicyPort instance) {
      instance = instance != null ? instance : new NetworkPolicyPort();
      if (instance != null) {
         this.withEndPort(instance.getEndPort());
         this.withPort(instance.getPort());
         this.withProtocol(instance.getProtocol());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getEndPort() {
      return this.endPort;
   }

   public NetworkPolicyPortFluent withEndPort(Integer endPort) {
      this.endPort = endPort;
      return this;
   }

   public boolean hasEndPort() {
      return this.endPort != null;
   }

   public IntOrString buildPort() {
      return this.port != null ? this.port.build() : null;
   }

   public NetworkPolicyPortFluent withPort(IntOrString port) {
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

   public NetworkPolicyPortFluent withNewPort(Object value) {
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

   public String getProtocol() {
      return this.protocol;
   }

   public NetworkPolicyPortFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public NetworkPolicyPortFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkPolicyPortFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkPolicyPortFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkPolicyPortFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkPolicyPortFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkPolicyPortFluent that = (NetworkPolicyPortFluent)o;
            if (!Objects.equals(this.endPort, that.endPort)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.protocol, that.protocol)) {
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
      return Objects.hash(new Object[]{this.endPort, this.port, this.protocol, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.endPort != null) {
         sb.append("endPort:");
         sb.append(this.endPort + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.protocol != null) {
         sb.append("protocol:");
         sb.append(this.protocol + ",");
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
         return NetworkPolicyPortFluent.this.withPort(this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
