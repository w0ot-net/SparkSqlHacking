package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VolumeResourceRequirementsFluent extends BaseFluent {
   private Map limits;
   private Map requests;
   private Map additionalProperties;

   public VolumeResourceRequirementsFluent() {
   }

   public VolumeResourceRequirementsFluent(VolumeResourceRequirements instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VolumeResourceRequirements instance) {
      instance = instance != null ? instance : new VolumeResourceRequirements();
      if (instance != null) {
         this.withLimits(instance.getLimits());
         this.withRequests(instance.getRequests());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public VolumeResourceRequirementsFluent addToLimits(String key, Quantity value) {
      if (this.limits == null && key != null && value != null) {
         this.limits = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.limits.put(key, value);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent addToLimits(Map map) {
      if (this.limits == null && map != null) {
         this.limits = new LinkedHashMap();
      }

      if (map != null) {
         this.limits.putAll(map);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent removeFromLimits(String key) {
      if (this.limits == null) {
         return this;
      } else {
         if (key != null && this.limits != null) {
            this.limits.remove(key);
         }

         return this;
      }
   }

   public VolumeResourceRequirementsFluent removeFromLimits(Map map) {
      if (this.limits == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.limits != null) {
                  this.limits.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLimits() {
      return this.limits;
   }

   public VolumeResourceRequirementsFluent withLimits(Map limits) {
      if (limits == null) {
         this.limits = null;
      } else {
         this.limits = new LinkedHashMap(limits);
      }

      return this;
   }

   public boolean hasLimits() {
      return this.limits != null;
   }

   public VolumeResourceRequirementsFluent addToRequests(String key, Quantity value) {
      if (this.requests == null && key != null && value != null) {
         this.requests = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.requests.put(key, value);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent addToRequests(Map map) {
      if (this.requests == null && map != null) {
         this.requests = new LinkedHashMap();
      }

      if (map != null) {
         this.requests.putAll(map);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent removeFromRequests(String key) {
      if (this.requests == null) {
         return this;
      } else {
         if (key != null && this.requests != null) {
            this.requests.remove(key);
         }

         return this;
      }
   }

   public VolumeResourceRequirementsFluent removeFromRequests(Map map) {
      if (this.requests == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.requests != null) {
                  this.requests.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getRequests() {
      return this.requests;
   }

   public VolumeResourceRequirementsFluent withRequests(Map requests) {
      if (requests == null) {
         this.requests = null;
      } else {
         this.requests = new LinkedHashMap(requests);
      }

      return this;
   }

   public boolean hasRequests() {
      return this.requests != null;
   }

   public VolumeResourceRequirementsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VolumeResourceRequirementsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VolumeResourceRequirementsFluent removeFromAdditionalProperties(Map map) {
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

   public VolumeResourceRequirementsFluent withAdditionalProperties(Map additionalProperties) {
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
            VolumeResourceRequirementsFluent that = (VolumeResourceRequirementsFluent)o;
            if (!Objects.equals(this.limits, that.limits)) {
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
      return Objects.hash(new Object[]{this.limits, this.requests, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.limits != null && !this.limits.isEmpty()) {
         sb.append("limits:");
         sb.append(this.limits + ",");
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
