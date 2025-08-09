package io.fabric8.kubernetes.api.model.metrics.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerMetricsFluent extends BaseFluent {
   private String name;
   private Map usage;
   private Map additionalProperties;

   public ContainerMetricsFluent() {
   }

   public ContainerMetricsFluent(ContainerMetrics instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerMetrics instance) {
      instance = instance != null ? instance : new ContainerMetrics();
      if (instance != null) {
         this.withName(instance.getName());
         this.withUsage(instance.getUsage());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public ContainerMetricsFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ContainerMetricsFluent addToUsage(String key, Quantity value) {
      if (this.usage == null && key != null && value != null) {
         this.usage = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.usage.put(key, value);
      }

      return this;
   }

   public ContainerMetricsFluent addToUsage(Map map) {
      if (this.usage == null && map != null) {
         this.usage = new LinkedHashMap();
      }

      if (map != null) {
         this.usage.putAll(map);
      }

      return this;
   }

   public ContainerMetricsFluent removeFromUsage(String key) {
      if (this.usage == null) {
         return this;
      } else {
         if (key != null && this.usage != null) {
            this.usage.remove(key);
         }

         return this;
      }
   }

   public ContainerMetricsFluent removeFromUsage(Map map) {
      if (this.usage == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.usage != null) {
                  this.usage.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getUsage() {
      return this.usage;
   }

   public ContainerMetricsFluent withUsage(Map usage) {
      if (usage == null) {
         this.usage = null;
      } else {
         this.usage = new LinkedHashMap(usage);
      }

      return this;
   }

   public boolean hasUsage() {
      return this.usage != null;
   }

   public ContainerMetricsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerMetricsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerMetricsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerMetricsFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerMetricsFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerMetricsFluent that = (ContainerMetricsFluent)o;
            if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.usage, that.usage)) {
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
      return Objects.hash(new Object[]{this.name, this.usage, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.usage != null && !this.usage.isEmpty()) {
         sb.append("usage:");
         sb.append(this.usage + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
