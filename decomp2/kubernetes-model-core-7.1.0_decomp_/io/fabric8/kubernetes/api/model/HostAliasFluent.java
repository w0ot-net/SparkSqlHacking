package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class HostAliasFluent extends BaseFluent {
   private List hostnames = new ArrayList();
   private String ip;
   private Map additionalProperties;

   public HostAliasFluent() {
   }

   public HostAliasFluent(HostAlias instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HostAlias instance) {
      instance = instance != null ? instance : new HostAlias();
      if (instance != null) {
         this.withHostnames(instance.getHostnames());
         this.withIp(instance.getIp());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public HostAliasFluent addToHostnames(int index, String item) {
      if (this.hostnames == null) {
         this.hostnames = new ArrayList();
      }

      this.hostnames.add(index, item);
      return this;
   }

   public HostAliasFluent setToHostnames(int index, String item) {
      if (this.hostnames == null) {
         this.hostnames = new ArrayList();
      }

      this.hostnames.set(index, item);
      return this;
   }

   public HostAliasFluent addToHostnames(String... items) {
      if (this.hostnames == null) {
         this.hostnames = new ArrayList();
      }

      for(String item : items) {
         this.hostnames.add(item);
      }

      return this;
   }

   public HostAliasFluent addAllToHostnames(Collection items) {
      if (this.hostnames == null) {
         this.hostnames = new ArrayList();
      }

      for(String item : items) {
         this.hostnames.add(item);
      }

      return this;
   }

   public HostAliasFluent removeFromHostnames(String... items) {
      if (this.hostnames == null) {
         return this;
      } else {
         for(String item : items) {
            this.hostnames.remove(item);
         }

         return this;
      }
   }

   public HostAliasFluent removeAllFromHostnames(Collection items) {
      if (this.hostnames == null) {
         return this;
      } else {
         for(String item : items) {
            this.hostnames.remove(item);
         }

         return this;
      }
   }

   public List getHostnames() {
      return this.hostnames;
   }

   public String getHostname(int index) {
      return (String)this.hostnames.get(index);
   }

   public String getFirstHostname() {
      return (String)this.hostnames.get(0);
   }

   public String getLastHostname() {
      return (String)this.hostnames.get(this.hostnames.size() - 1);
   }

   public String getMatchingHostname(Predicate predicate) {
      for(String item : this.hostnames) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingHostname(Predicate predicate) {
      for(String item : this.hostnames) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HostAliasFluent withHostnames(List hostnames) {
      if (hostnames != null) {
         this.hostnames = new ArrayList();

         for(String item : hostnames) {
            this.addToHostnames(item);
         }
      } else {
         this.hostnames = null;
      }

      return this;
   }

   public HostAliasFluent withHostnames(String... hostnames) {
      if (this.hostnames != null) {
         this.hostnames.clear();
         this._visitables.remove("hostnames");
      }

      if (hostnames != null) {
         for(String item : hostnames) {
            this.addToHostnames(item);
         }
      }

      return this;
   }

   public boolean hasHostnames() {
      return this.hostnames != null && !this.hostnames.isEmpty();
   }

   public String getIp() {
      return this.ip;
   }

   public HostAliasFluent withIp(String ip) {
      this.ip = ip;
      return this;
   }

   public boolean hasIp() {
      return this.ip != null;
   }

   public HostAliasFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HostAliasFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HostAliasFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HostAliasFluent removeFromAdditionalProperties(Map map) {
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

   public HostAliasFluent withAdditionalProperties(Map additionalProperties) {
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
            HostAliasFluent that = (HostAliasFluent)o;
            if (!Objects.equals(this.hostnames, that.hostnames)) {
               return false;
            } else if (!Objects.equals(this.ip, that.ip)) {
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
      return Objects.hash(new Object[]{this.hostnames, this.ip, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hostnames != null && !this.hostnames.isEmpty()) {
         sb.append("hostnames:");
         sb.append(this.hostnames + ",");
      }

      if (this.ip != null) {
         sb.append("ip:");
         sb.append(this.ip + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
