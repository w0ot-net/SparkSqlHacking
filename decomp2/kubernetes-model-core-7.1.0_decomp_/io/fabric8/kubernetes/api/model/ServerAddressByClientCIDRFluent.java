package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ServerAddressByClientCIDRFluent extends BaseFluent {
   private String clientCIDR;
   private String serverAddress;
   private Map additionalProperties;

   public ServerAddressByClientCIDRFluent() {
   }

   public ServerAddressByClientCIDRFluent(ServerAddressByClientCIDR instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServerAddressByClientCIDR instance) {
      instance = instance != null ? instance : new ServerAddressByClientCIDR();
      if (instance != null) {
         this.withClientCIDR(instance.getClientCIDR());
         this.withServerAddress(instance.getServerAddress());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getClientCIDR() {
      return this.clientCIDR;
   }

   public ServerAddressByClientCIDRFluent withClientCIDR(String clientCIDR) {
      this.clientCIDR = clientCIDR;
      return this;
   }

   public boolean hasClientCIDR() {
      return this.clientCIDR != null;
   }

   public String getServerAddress() {
      return this.serverAddress;
   }

   public ServerAddressByClientCIDRFluent withServerAddress(String serverAddress) {
      this.serverAddress = serverAddress;
      return this;
   }

   public boolean hasServerAddress() {
      return this.serverAddress != null;
   }

   public ServerAddressByClientCIDRFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServerAddressByClientCIDRFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServerAddressByClientCIDRFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServerAddressByClientCIDRFluent removeFromAdditionalProperties(Map map) {
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

   public ServerAddressByClientCIDRFluent withAdditionalProperties(Map additionalProperties) {
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
            ServerAddressByClientCIDRFluent that = (ServerAddressByClientCIDRFluent)o;
            if (!Objects.equals(this.clientCIDR, that.clientCIDR)) {
               return false;
            } else if (!Objects.equals(this.serverAddress, that.serverAddress)) {
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
      return Objects.hash(new Object[]{this.clientCIDR, this.serverAddress, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clientCIDR != null) {
         sb.append("clientCIDR:");
         sb.append(this.clientCIDR + ",");
      }

      if (this.serverAddress != null) {
         sb.append("serverAddress:");
         sb.append(this.serverAddress + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
