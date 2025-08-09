package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NodeDaemonEndpointsFluent extends BaseFluent {
   private DaemonEndpointBuilder kubeletEndpoint;
   private Map additionalProperties;

   public NodeDaemonEndpointsFluent() {
   }

   public NodeDaemonEndpointsFluent(NodeDaemonEndpoints instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeDaemonEndpoints instance) {
      instance = instance != null ? instance : new NodeDaemonEndpoints();
      if (instance != null) {
         this.withKubeletEndpoint(instance.getKubeletEndpoint());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public DaemonEndpoint buildKubeletEndpoint() {
      return this.kubeletEndpoint != null ? this.kubeletEndpoint.build() : null;
   }

   public NodeDaemonEndpointsFluent withKubeletEndpoint(DaemonEndpoint kubeletEndpoint) {
      this._visitables.remove("kubeletEndpoint");
      if (kubeletEndpoint != null) {
         this.kubeletEndpoint = new DaemonEndpointBuilder(kubeletEndpoint);
         this._visitables.get("kubeletEndpoint").add(this.kubeletEndpoint);
      } else {
         this.kubeletEndpoint = null;
         this._visitables.get("kubeletEndpoint").remove(this.kubeletEndpoint);
      }

      return this;
   }

   public boolean hasKubeletEndpoint() {
      return this.kubeletEndpoint != null;
   }

   public NodeDaemonEndpointsFluent withNewKubeletEndpoint(Integer port) {
      return this.withKubeletEndpoint(new DaemonEndpoint(port));
   }

   public KubeletEndpointNested withNewKubeletEndpoint() {
      return new KubeletEndpointNested((DaemonEndpoint)null);
   }

   public KubeletEndpointNested withNewKubeletEndpointLike(DaemonEndpoint item) {
      return new KubeletEndpointNested(item);
   }

   public KubeletEndpointNested editKubeletEndpoint() {
      return this.withNewKubeletEndpointLike((DaemonEndpoint)Optional.ofNullable(this.buildKubeletEndpoint()).orElse((Object)null));
   }

   public KubeletEndpointNested editOrNewKubeletEndpoint() {
      return this.withNewKubeletEndpointLike((DaemonEndpoint)Optional.ofNullable(this.buildKubeletEndpoint()).orElse((new DaemonEndpointBuilder()).build()));
   }

   public KubeletEndpointNested editOrNewKubeletEndpointLike(DaemonEndpoint item) {
      return this.withNewKubeletEndpointLike((DaemonEndpoint)Optional.ofNullable(this.buildKubeletEndpoint()).orElse(item));
   }

   public NodeDaemonEndpointsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeDaemonEndpointsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeDaemonEndpointsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeDaemonEndpointsFluent removeFromAdditionalProperties(Map map) {
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

   public NodeDaemonEndpointsFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeDaemonEndpointsFluent that = (NodeDaemonEndpointsFluent)o;
            if (!Objects.equals(this.kubeletEndpoint, that.kubeletEndpoint)) {
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
      return Objects.hash(new Object[]{this.kubeletEndpoint, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.kubeletEndpoint != null) {
         sb.append("kubeletEndpoint:");
         sb.append(this.kubeletEndpoint + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class KubeletEndpointNested extends DaemonEndpointFluent implements Nested {
      DaemonEndpointBuilder builder;

      KubeletEndpointNested(DaemonEndpoint item) {
         this.builder = new DaemonEndpointBuilder(this, item);
      }

      public Object and() {
         return NodeDaemonEndpointsFluent.this.withKubeletEndpoint(this.builder.build());
      }

      public Object endKubeletEndpoint() {
         return this.and();
      }
   }
}
