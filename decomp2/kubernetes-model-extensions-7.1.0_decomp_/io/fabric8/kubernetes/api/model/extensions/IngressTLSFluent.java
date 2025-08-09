package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class IngressTLSFluent extends BaseFluent {
   private List hosts = new ArrayList();
   private String secretName;
   private Map additionalProperties;

   public IngressTLSFluent() {
   }

   public IngressTLSFluent(IngressTLS instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressTLS instance) {
      instance = instance != null ? instance : new IngressTLS();
      if (instance != null) {
         this.withHosts(instance.getHosts());
         this.withSecretName(instance.getSecretName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IngressTLSFluent addToHosts(int index, String item) {
      if (this.hosts == null) {
         this.hosts = new ArrayList();
      }

      this.hosts.add(index, item);
      return this;
   }

   public IngressTLSFluent setToHosts(int index, String item) {
      if (this.hosts == null) {
         this.hosts = new ArrayList();
      }

      this.hosts.set(index, item);
      return this;
   }

   public IngressTLSFluent addToHosts(String... items) {
      if (this.hosts == null) {
         this.hosts = new ArrayList();
      }

      for(String item : items) {
         this.hosts.add(item);
      }

      return this;
   }

   public IngressTLSFluent addAllToHosts(Collection items) {
      if (this.hosts == null) {
         this.hosts = new ArrayList();
      }

      for(String item : items) {
         this.hosts.add(item);
      }

      return this;
   }

   public IngressTLSFluent removeFromHosts(String... items) {
      if (this.hosts == null) {
         return this;
      } else {
         for(String item : items) {
            this.hosts.remove(item);
         }

         return this;
      }
   }

   public IngressTLSFluent removeAllFromHosts(Collection items) {
      if (this.hosts == null) {
         return this;
      } else {
         for(String item : items) {
            this.hosts.remove(item);
         }

         return this;
      }
   }

   public List getHosts() {
      return this.hosts;
   }

   public String getHost(int index) {
      return (String)this.hosts.get(index);
   }

   public String getFirstHost() {
      return (String)this.hosts.get(0);
   }

   public String getLastHost() {
      return (String)this.hosts.get(this.hosts.size() - 1);
   }

   public String getMatchingHost(Predicate predicate) {
      for(String item : this.hosts) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingHost(Predicate predicate) {
      for(String item : this.hosts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IngressTLSFluent withHosts(List hosts) {
      if (hosts != null) {
         this.hosts = new ArrayList();

         for(String item : hosts) {
            this.addToHosts(item);
         }
      } else {
         this.hosts = null;
      }

      return this;
   }

   public IngressTLSFluent withHosts(String... hosts) {
      if (this.hosts != null) {
         this.hosts.clear();
         this._visitables.remove("hosts");
      }

      if (hosts != null) {
         for(String item : hosts) {
            this.addToHosts(item);
         }
      }

      return this;
   }

   public boolean hasHosts() {
      return this.hosts != null && !this.hosts.isEmpty();
   }

   public String getSecretName() {
      return this.secretName;
   }

   public IngressTLSFluent withSecretName(String secretName) {
      this.secretName = secretName;
      return this;
   }

   public boolean hasSecretName() {
      return this.secretName != null;
   }

   public IngressTLSFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressTLSFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressTLSFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressTLSFluent removeFromAdditionalProperties(Map map) {
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

   public IngressTLSFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressTLSFluent that = (IngressTLSFluent)o;
            if (!Objects.equals(this.hosts, that.hosts)) {
               return false;
            } else if (!Objects.equals(this.secretName, that.secretName)) {
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
      return Objects.hash(new Object[]{this.hosts, this.secretName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hosts != null && !this.hosts.isEmpty()) {
         sb.append("hosts:");
         sb.append(this.hosts + ",");
      }

      if (this.secretName != null) {
         sb.append("secretName:");
         sb.append(this.secretName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
