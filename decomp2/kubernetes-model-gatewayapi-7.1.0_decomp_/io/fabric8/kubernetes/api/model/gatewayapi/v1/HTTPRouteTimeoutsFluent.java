package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HTTPRouteTimeoutsFluent extends BaseFluent {
   private String backendRequest;
   private String request;
   private Map additionalProperties;

   public HTTPRouteTimeoutsFluent() {
   }

   public HTTPRouteTimeoutsFluent(HTTPRouteTimeouts instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRouteTimeouts instance) {
      instance = instance != null ? instance : new HTTPRouteTimeouts();
      if (instance != null) {
         this.withBackendRequest(instance.getBackendRequest());
         this.withRequest(instance.getRequest());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getBackendRequest() {
      return this.backendRequest;
   }

   public HTTPRouteTimeoutsFluent withBackendRequest(String backendRequest) {
      this.backendRequest = backendRequest;
      return this;
   }

   public boolean hasBackendRequest() {
      return this.backendRequest != null;
   }

   public String getRequest() {
      return this.request;
   }

   public HTTPRouteTimeoutsFluent withRequest(String request) {
      this.request = request;
      return this;
   }

   public boolean hasRequest() {
      return this.request != null;
   }

   public HTTPRouteTimeoutsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRouteTimeoutsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRouteTimeoutsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRouteTimeoutsFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRouteTimeoutsFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRouteTimeoutsFluent that = (HTTPRouteTimeoutsFluent)o;
            if (!Objects.equals(this.backendRequest, that.backendRequest)) {
               return false;
            } else if (!Objects.equals(this.request, that.request)) {
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
      return Objects.hash(new Object[]{this.backendRequest, this.request, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backendRequest != null) {
         sb.append("backendRequest:");
         sb.append(this.backendRequest + ",");
      }

      if (this.request != null) {
         sb.append("request:");
         sb.append(this.request + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
