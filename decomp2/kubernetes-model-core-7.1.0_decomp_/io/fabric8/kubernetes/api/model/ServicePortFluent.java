package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ServicePortFluent extends BaseFluent {
   private String appProtocol;
   private String name;
   private Integer nodePort;
   private Integer port;
   private String protocol;
   private IntOrStringBuilder targetPort;
   private Map additionalProperties;

   public ServicePortFluent() {
   }

   public ServicePortFluent(ServicePort instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServicePort instance) {
      instance = instance != null ? instance : new ServicePort();
      if (instance != null) {
         this.withAppProtocol(instance.getAppProtocol());
         this.withName(instance.getName());
         this.withNodePort(instance.getNodePort());
         this.withPort(instance.getPort());
         this.withProtocol(instance.getProtocol());
         this.withTargetPort(instance.getTargetPort());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAppProtocol() {
      return this.appProtocol;
   }

   public ServicePortFluent withAppProtocol(String appProtocol) {
      this.appProtocol = appProtocol;
      return this;
   }

   public boolean hasAppProtocol() {
      return this.appProtocol != null;
   }

   public String getName() {
      return this.name;
   }

   public ServicePortFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getNodePort() {
      return this.nodePort;
   }

   public ServicePortFluent withNodePort(Integer nodePort) {
      this.nodePort = nodePort;
      return this;
   }

   public boolean hasNodePort() {
      return this.nodePort != null;
   }

   public Integer getPort() {
      return this.port;
   }

   public ServicePortFluent withPort(Integer port) {
      this.port = port;
      return this;
   }

   public boolean hasPort() {
      return this.port != null;
   }

   public String getProtocol() {
      return this.protocol;
   }

   public ServicePortFluent withProtocol(String protocol) {
      this.protocol = protocol;
      return this;
   }

   public boolean hasProtocol() {
      return this.protocol != null;
   }

   public IntOrString buildTargetPort() {
      return this.targetPort != null ? this.targetPort.build() : null;
   }

   public ServicePortFluent withTargetPort(IntOrString targetPort) {
      this._visitables.remove("targetPort");
      if (targetPort != null) {
         this.targetPort = new IntOrStringBuilder(targetPort);
         this._visitables.get("targetPort").add(this.targetPort);
      } else {
         this.targetPort = null;
         this._visitables.get("targetPort").remove(this.targetPort);
      }

      return this;
   }

   public boolean hasTargetPort() {
      return this.targetPort != null;
   }

   public ServicePortFluent withNewTargetPort(Object value) {
      return this.withTargetPort(new IntOrString(value));
   }

   public TargetPortNested withNewTargetPort() {
      return new TargetPortNested((IntOrString)null);
   }

   public TargetPortNested withNewTargetPortLike(IntOrString item) {
      return new TargetPortNested(item);
   }

   public TargetPortNested editTargetPort() {
      return this.withNewTargetPortLike((IntOrString)Optional.ofNullable(this.buildTargetPort()).orElse((Object)null));
   }

   public TargetPortNested editOrNewTargetPort() {
      return this.withNewTargetPortLike((IntOrString)Optional.ofNullable(this.buildTargetPort()).orElse((new IntOrStringBuilder()).build()));
   }

   public TargetPortNested editOrNewTargetPortLike(IntOrString item) {
      return this.withNewTargetPortLike((IntOrString)Optional.ofNullable(this.buildTargetPort()).orElse(item));
   }

   public ServicePortFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServicePortFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServicePortFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServicePortFluent removeFromAdditionalProperties(Map map) {
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

   public ServicePortFluent withAdditionalProperties(Map additionalProperties) {
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
            ServicePortFluent that = (ServicePortFluent)o;
            if (!Objects.equals(this.appProtocol, that.appProtocol)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.nodePort, that.nodePort)) {
               return false;
            } else if (!Objects.equals(this.port, that.port)) {
               return false;
            } else if (!Objects.equals(this.protocol, that.protocol)) {
               return false;
            } else if (!Objects.equals(this.targetPort, that.targetPort)) {
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
      return Objects.hash(new Object[]{this.appProtocol, this.name, this.nodePort, this.port, this.protocol, this.targetPort, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.appProtocol != null) {
         sb.append("appProtocol:");
         sb.append(this.appProtocol + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.nodePort != null) {
         sb.append("nodePort:");
         sb.append(this.nodePort + ",");
      }

      if (this.port != null) {
         sb.append("port:");
         sb.append(this.port + ",");
      }

      if (this.protocol != null) {
         sb.append("protocol:");
         sb.append(this.protocol + ",");
      }

      if (this.targetPort != null) {
         sb.append("targetPort:");
         sb.append(this.targetPort + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class TargetPortNested extends IntOrStringFluent implements Nested {
      IntOrStringBuilder builder;

      TargetPortNested(IntOrString item) {
         this.builder = new IntOrStringBuilder(this, item);
      }

      public Object and() {
         return ServicePortFluent.this.withTargetPort(this.builder.build());
      }

      public Object endTargetPort() {
         return this.and();
      }
   }
}
