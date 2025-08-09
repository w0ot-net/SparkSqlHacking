package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class DeviceConstraintFluent extends BaseFluent {
   private String matchAttribute;
   private List requests = new ArrayList();
   private Map additionalProperties;

   public DeviceConstraintFluent() {
   }

   public DeviceConstraintFluent(DeviceConstraint instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceConstraint instance) {
      instance = instance != null ? instance : new DeviceConstraint();
      if (instance != null) {
         this.withMatchAttribute(instance.getMatchAttribute());
         this.withRequests(instance.getRequests());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getMatchAttribute() {
      return this.matchAttribute;
   }

   public DeviceConstraintFluent withMatchAttribute(String matchAttribute) {
      this.matchAttribute = matchAttribute;
      return this;
   }

   public boolean hasMatchAttribute() {
      return this.matchAttribute != null;
   }

   public DeviceConstraintFluent addToRequests(int index, String item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      this.requests.add(index, item);
      return this;
   }

   public DeviceConstraintFluent setToRequests(int index, String item) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      this.requests.set(index, item);
      return this;
   }

   public DeviceConstraintFluent addToRequests(String... items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(String item : items) {
         this.requests.add(item);
      }

      return this;
   }

   public DeviceConstraintFluent addAllToRequests(Collection items) {
      if (this.requests == null) {
         this.requests = new ArrayList();
      }

      for(String item : items) {
         this.requests.add(item);
      }

      return this;
   }

   public DeviceConstraintFluent removeFromRequests(String... items) {
      if (this.requests == null) {
         return this;
      } else {
         for(String item : items) {
            this.requests.remove(item);
         }

         return this;
      }
   }

   public DeviceConstraintFluent removeAllFromRequests(Collection items) {
      if (this.requests == null) {
         return this;
      } else {
         for(String item : items) {
            this.requests.remove(item);
         }

         return this;
      }
   }

   public List getRequests() {
      return this.requests;
   }

   public String getRequest(int index) {
      return (String)this.requests.get(index);
   }

   public String getFirstRequest() {
      return (String)this.requests.get(0);
   }

   public String getLastRequest() {
      return (String)this.requests.get(this.requests.size() - 1);
   }

   public String getMatchingRequest(Predicate predicate) {
      for(String item : this.requests) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRequest(Predicate predicate) {
      for(String item : this.requests) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public DeviceConstraintFluent withRequests(List requests) {
      if (requests != null) {
         this.requests = new ArrayList();

         for(String item : requests) {
            this.addToRequests(item);
         }
      } else {
         this.requests = null;
      }

      return this;
   }

   public DeviceConstraintFluent withRequests(String... requests) {
      if (this.requests != null) {
         this.requests.clear();
         this._visitables.remove("requests");
      }

      if (requests != null) {
         for(String item : requests) {
            this.addToRequests(item);
         }
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null && !this.requests.isEmpty();
   }

   public DeviceConstraintFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceConstraintFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceConstraintFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceConstraintFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceConstraintFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceConstraintFluent that = (DeviceConstraintFluent)o;
            if (!Objects.equals(this.matchAttribute, that.matchAttribute)) {
               return false;
            } else if (!Objects.equals(this.requests, that.requests)) {
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
      return Objects.hash(new Object[]{this.matchAttribute, this.requests, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.matchAttribute != null) {
         sb.append("matchAttribute:");
         sb.append(this.matchAttribute + ",");
      }

      if (this.requests != null && !this.requests.isEmpty()) {
         sb.append("requests:");
         sb.append(this.requests + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
