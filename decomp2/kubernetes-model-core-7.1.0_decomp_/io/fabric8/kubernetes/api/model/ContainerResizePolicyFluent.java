package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerResizePolicyFluent extends BaseFluent {
   private String resourceName;
   private String restartPolicy;
   private Map additionalProperties;

   public ContainerResizePolicyFluent() {
   }

   public ContainerResizePolicyFluent(ContainerResizePolicy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerResizePolicy instance) {
      instance = instance != null ? instance : new ContainerResizePolicy();
      if (instance != null) {
         this.withResourceName(instance.getResourceName());
         this.withRestartPolicy(instance.getRestartPolicy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getResourceName() {
      return this.resourceName;
   }

   public ContainerResizePolicyFluent withResourceName(String resourceName) {
      this.resourceName = resourceName;
      return this;
   }

   public boolean hasResourceName() {
      return this.resourceName != null;
   }

   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   public ContainerResizePolicyFluent withRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
      return this;
   }

   public boolean hasRestartPolicy() {
      return this.restartPolicy != null;
   }

   public ContainerResizePolicyFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerResizePolicyFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerResizePolicyFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerResizePolicyFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerResizePolicyFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerResizePolicyFluent that = (ContainerResizePolicyFluent)o;
            if (!Objects.equals(this.resourceName, that.resourceName)) {
               return false;
            } else if (!Objects.equals(this.restartPolicy, that.restartPolicy)) {
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
      return Objects.hash(new Object[]{this.resourceName, this.restartPolicy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.resourceName != null) {
         sb.append("resourceName:");
         sb.append(this.resourceName + ",");
      }

      if (this.restartPolicy != null) {
         sb.append("restartPolicy:");
         sb.append(this.restartPolicy + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
