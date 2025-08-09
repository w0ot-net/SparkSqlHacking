package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.LoadBalancerStatus;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class IngressStatusFluent extends BaseFluent {
   private LoadBalancerStatus loadBalancer;
   private Map additionalProperties;

   public IngressStatusFluent() {
   }

   public IngressStatusFluent(IngressStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressStatus instance) {
      instance = instance != null ? instance : new IngressStatus();
      if (instance != null) {
         this.withLoadBalancer(instance.getLoadBalancer());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LoadBalancerStatus getLoadBalancer() {
      return this.loadBalancer;
   }

   public IngressStatusFluent withLoadBalancer(LoadBalancerStatus loadBalancer) {
      this.loadBalancer = loadBalancer;
      return this;
   }

   public boolean hasLoadBalancer() {
      return this.loadBalancer != null;
   }

   public IngressStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressStatusFluent removeFromAdditionalProperties(Map map) {
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

   public IngressStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressStatusFluent that = (IngressStatusFluent)o;
            if (!Objects.equals(this.loadBalancer, that.loadBalancer)) {
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
      return Objects.hash(new Object[]{this.loadBalancer, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.loadBalancer != null) {
         sb.append("loadBalancer:");
         sb.append(this.loadBalancer + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
