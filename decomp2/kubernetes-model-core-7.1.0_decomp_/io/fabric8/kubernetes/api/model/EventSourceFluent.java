package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class EventSourceFluent extends BaseFluent {
   private String component;
   private String host;
   private Map additionalProperties;

   public EventSourceFluent() {
   }

   public EventSourceFluent(EventSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EventSource instance) {
      instance = instance != null ? instance : new EventSource();
      if (instance != null) {
         this.withComponent(instance.getComponent());
         this.withHost(instance.getHost());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getComponent() {
      return this.component;
   }

   public EventSourceFluent withComponent(String component) {
      this.component = component;
      return this;
   }

   public boolean hasComponent() {
      return this.component != null;
   }

   public String getHost() {
      return this.host;
   }

   public EventSourceFluent withHost(String host) {
      this.host = host;
      return this;
   }

   public boolean hasHost() {
      return this.host != null;
   }

   public EventSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EventSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EventSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EventSourceFluent removeFromAdditionalProperties(Map map) {
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

   public EventSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            EventSourceFluent that = (EventSourceFluent)o;
            if (!Objects.equals(this.component, that.component)) {
               return false;
            } else if (!Objects.equals(this.host, that.host)) {
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
      return Objects.hash(new Object[]{this.component, this.host, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.component != null) {
         sb.append("component:");
         sb.append(this.component + ",");
      }

      if (this.host != null) {
         sb.append("host:");
         sb.append(this.host + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
