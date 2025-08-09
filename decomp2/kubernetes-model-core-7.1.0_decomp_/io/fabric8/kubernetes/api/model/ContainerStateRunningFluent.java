package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerStateRunningFluent extends BaseFluent {
   private String startedAt;
   private Map additionalProperties;

   public ContainerStateRunningFluent() {
   }

   public ContainerStateRunningFluent(ContainerStateRunning instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerStateRunning instance) {
      instance = instance != null ? instance : new ContainerStateRunning();
      if (instance != null) {
         this.withStartedAt(instance.getStartedAt());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getStartedAt() {
      return this.startedAt;
   }

   public ContainerStateRunningFluent withStartedAt(String startedAt) {
      this.startedAt = startedAt;
      return this;
   }

   public boolean hasStartedAt() {
      return this.startedAt != null;
   }

   public ContainerStateRunningFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerStateRunningFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerStateRunningFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerStateRunningFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerStateRunningFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerStateRunningFluent that = (ContainerStateRunningFluent)o;
            if (!Objects.equals(this.startedAt, that.startedAt)) {
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
      return Objects.hash(new Object[]{this.startedAt, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.startedAt != null) {
         sb.append("startedAt:");
         sb.append(this.startedAt + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
