package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerPortFluent extends BaseFluent {
   private Integer containerPort;
   private String hostIP;
   private Integer hostPort;
   private String name;
   private String protocol;
   private Map additionalProperties;

   public ContainerPortFluent() {
   }

   public ContainerPortFluent(ContainerPort instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerPort instance) {
      instance = instance != null ? instance : new ContainerPort();
      if (instance != null) {
         this.withContainerPort(instance.getContainerPort());
         this.withHostIP(instance.getHostIP());
         this.withHostPort(instance.getHostPort());
         this.withName(instance.getName());
         this.withProtocol(instance.getProtocol());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getContainerPort() {
      return this.containerPort;
   }

   public ContainerPortFluent withContainerPort(Integer containerPort) {
      this.containerPort = containerPort;
      return this;
   }

   public boolean hasContainerPort() {
      return this.containerPort != null;
   }

   public String getHostIP() {
      return this.hostIP;
   }

   public ContainerPortFluent withHostIP(String hostIP) {
      this.hostIP = hostIP;
      return this;
   }

   public boolean hasHostIP() {
      return this.hostIP != null;
   }

   public Integer getHostPort() {
      return this.hostPort;
   }

   public ContainerPortFluent withHostPort(Integer hostPort) {
      this.hostPort = hostPort;
      return this;
   }

   public boolean hasHostPort() {
      return this.hostPort != null;
   }

   public String getName() {
      return this.name;
   }

   public ContainerPortFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public ContainerPortFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public ContainerPortFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerPortFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerPortFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerPortFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerPortFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerPortFluent that = (ContainerPortFluent)o;
            if (!Objects.equals(this.containerPort, that.containerPort)) {
               return false;
            } else if (!Objects.equals(this.hostIP, that.hostIP)) {
               return false;
            } else if (!Objects.equals(this.hostPort, that.hostPort)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.containerPort, this.hostIP, this.hostPort, this.name, this.protocol, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.containerPort != null) {
         sb.append("containerPort:");
         sb.append(this.containerPort + ",");
      }

      if (this.hostIP != null) {
         sb.append("hostIP:");
         sb.append(this.hostIP + ",");
      }

      if (this.hostPort != null) {
         sb.append("hostPort:");
         sb.append(this.hostPort + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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
}
