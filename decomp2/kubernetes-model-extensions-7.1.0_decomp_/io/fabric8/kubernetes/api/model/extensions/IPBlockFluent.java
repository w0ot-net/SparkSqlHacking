package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class IPBlockFluent extends BaseFluent {
   private String cidr;
   private List except = new ArrayList();
   private Map additionalProperties;

   public IPBlockFluent() {
   }

   public IPBlockFluent(IPBlock instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IPBlock instance) {
      instance = instance != null ? instance : new IPBlock();
      if (instance != null) {
         this.withCidr(instance.getCidr());
         this.withExcept(instance.getExcept());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCidr() {
      return this.cidr;
   }

   public IPBlockFluent withCidr(String cidr) {
      this.cidr = cidr;
      return this;
   }

   public boolean hasCidr() {
      return this.cidr != null;
   }

   public IPBlockFluent addToExcept(int index, String item) {
      if (this.except == null) {
         this.except = new ArrayList();
      }

      this.except.add(index, item);
      return this;
   }

   public IPBlockFluent setToExcept(int index, String item) {
      if (this.except == null) {
         this.except = new ArrayList();
      }

      this.except.set(index, item);
      return this;
   }

   public IPBlockFluent addToExcept(String... items) {
      if (this.except == null) {
         this.except = new ArrayList();
      }

      for(String item : items) {
         this.except.add(item);
      }

      return this;
   }

   public IPBlockFluent addAllToExcept(Collection items) {
      if (this.except == null) {
         this.except = new ArrayList();
      }

      for(String item : items) {
         this.except.add(item);
      }

      return this;
   }

   public IPBlockFluent removeFromExcept(String... items) {
      if (this.except == null) {
         return this;
      } else {
         for(String item : items) {
            this.except.remove(item);
         }

         return this;
      }
   }

   public IPBlockFluent removeAllFromExcept(Collection items) {
      if (this.except == null) {
         return this;
      } else {
         for(String item : items) {
            this.except.remove(item);
         }

         return this;
      }
   }

   public List getExcept() {
      return this.except;
   }

   public String getExcept(int index) {
      return (String)this.except.get(index);
   }

   public String getFirstExcept() {
      return (String)this.except.get(0);
   }

   public String getLastExcept() {
      return (String)this.except.get(this.except.size() - 1);
   }

   public String getMatchingExcept(Predicate predicate) {
      for(String item : this.except) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingExcept(Predicate predicate) {
      for(String item : this.except) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IPBlockFluent withExcept(List except) {
      if (except != null) {
         this.except = new ArrayList();

         for(String item : except) {
            this.addToExcept(item);
         }
      } else {
         this.except = null;
      }

      return this;
   }

   public IPBlockFluent withExcept(String... except) {
      if (this.except != null) {
         this.except.clear();
         this._visitables.remove("except");
      }

      if (except != null) {
         for(String item : except) {
            this.addToExcept(item);
         }
      }

      return this;
   }

   public boolean hasExcept() {
      return this.except != null && !this.except.isEmpty();
   }

   public IPBlockFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IPBlockFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IPBlockFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IPBlockFluent removeFromAdditionalProperties(Map map) {
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

   public IPBlockFluent withAdditionalProperties(Map additionalProperties) {
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
            IPBlockFluent that = (IPBlockFluent)o;
            if (!Objects.equals(this.cidr, that.cidr)) {
               return false;
            } else if (!Objects.equals(this.except, that.except)) {
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
      return Objects.hash(new Object[]{this.cidr, this.except, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.cidr != null) {
         sb.append("cidr:");
         sb.append(this.cidr + ",");
      }

      if (this.except != null && !this.except.isEmpty()) {
         sb.append("except:");
         sb.append(this.except + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
