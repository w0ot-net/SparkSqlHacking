package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ServiceCIDRSpecFluent extends BaseFluent {
   private List cidrs = new ArrayList();
   private Map additionalProperties;

   public ServiceCIDRSpecFluent() {
   }

   public ServiceCIDRSpecFluent(ServiceCIDRSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServiceCIDRSpec instance) {
      instance = instance != null ? instance : new ServiceCIDRSpec();
      if (instance != null) {
         this.withCidrs(instance.getCidrs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ServiceCIDRSpecFluent addToCidrs(int index, String item) {
      if (this.cidrs == null) {
         this.cidrs = new ArrayList();
      }

      this.cidrs.add(index, item);
      return this;
   }

   public ServiceCIDRSpecFluent setToCidrs(int index, String item) {
      if (this.cidrs == null) {
         this.cidrs = new ArrayList();
      }

      this.cidrs.set(index, item);
      return this;
   }

   public ServiceCIDRSpecFluent addToCidrs(String... items) {
      if (this.cidrs == null) {
         this.cidrs = new ArrayList();
      }

      for(String item : items) {
         this.cidrs.add(item);
      }

      return this;
   }

   public ServiceCIDRSpecFluent addAllToCidrs(Collection items) {
      if (this.cidrs == null) {
         this.cidrs = new ArrayList();
      }

      for(String item : items) {
         this.cidrs.add(item);
      }

      return this;
   }

   public ServiceCIDRSpecFluent removeFromCidrs(String... items) {
      if (this.cidrs == null) {
         return this;
      } else {
         for(String item : items) {
            this.cidrs.remove(item);
         }

         return this;
      }
   }

   public ServiceCIDRSpecFluent removeAllFromCidrs(Collection items) {
      if (this.cidrs == null) {
         return this;
      } else {
         for(String item : items) {
            this.cidrs.remove(item);
         }

         return this;
      }
   }

   public List getCidrs() {
      return this.cidrs;
   }

   public String getCidr(int index) {
      return (String)this.cidrs.get(index);
   }

   public String getFirstCidr() {
      return (String)this.cidrs.get(0);
   }

   public String getLastCidr() {
      return (String)this.cidrs.get(this.cidrs.size() - 1);
   }

   public String getMatchingCidr(Predicate predicate) {
      for(String item : this.cidrs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCidr(Predicate predicate) {
      for(String item : this.cidrs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceCIDRSpecFluent withCidrs(List cidrs) {
      if (cidrs != null) {
         this.cidrs = new ArrayList();

         for(String item : cidrs) {
            this.addToCidrs(item);
         }
      } else {
         this.cidrs = null;
      }

      return this;
   }

   public ServiceCIDRSpecFluent withCidrs(String... cidrs) {
      if (this.cidrs != null) {
         this.cidrs.clear();
         this._visitables.remove("cidrs");
      }

      if (cidrs != null) {
         for(String item : cidrs) {
            this.addToCidrs(item);
         }
      }

      return this;
   }

   public boolean hasCidrs() {
      return this.cidrs != null && !this.cidrs.isEmpty();
   }

   public ServiceCIDRSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServiceCIDRSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServiceCIDRSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServiceCIDRSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ServiceCIDRSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ServiceCIDRSpecFluent that = (ServiceCIDRSpecFluent)o;
            if (!Objects.equals(this.cidrs, that.cidrs)) {
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
      return Objects.hash(new Object[]{this.cidrs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.cidrs != null && !this.cidrs.isEmpty()) {
         sb.append("cidrs:");
         sb.append(this.cidrs + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
