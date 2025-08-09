package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NamespaceSpecFluent extends BaseFluent {
   private List finalizers = new ArrayList();
   private Map additionalProperties;

   public NamespaceSpecFluent() {
   }

   public NamespaceSpecFluent(NamespaceSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamespaceSpec instance) {
      instance = instance != null ? instance : new NamespaceSpec();
      if (instance != null) {
         this.withFinalizers(instance.getFinalizers());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamespaceSpecFluent addToFinalizers(int index, String item) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      this.finalizers.add(index, item);
      return this;
   }

   public NamespaceSpecFluent setToFinalizers(int index, String item) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      this.finalizers.set(index, item);
      return this;
   }

   public NamespaceSpecFluent addToFinalizers(String... items) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      for(String item : items) {
         this.finalizers.add(item);
      }

      return this;
   }

   public NamespaceSpecFluent addAllToFinalizers(Collection items) {
      if (this.finalizers == null) {
         this.finalizers = new ArrayList();
      }

      for(String item : items) {
         this.finalizers.add(item);
      }

      return this;
   }

   public NamespaceSpecFluent removeFromFinalizers(String... items) {
      if (this.finalizers == null) {
         return this;
      } else {
         for(String item : items) {
            this.finalizers.remove(item);
         }

         return this;
      }
   }

   public NamespaceSpecFluent removeAllFromFinalizers(Collection items) {
      if (this.finalizers == null) {
         return this;
      } else {
         for(String item : items) {
            this.finalizers.remove(item);
         }

         return this;
      }
   }

   public List getFinalizers() {
      return this.finalizers;
   }

   public String getFinalizer(int index) {
      return (String)this.finalizers.get(index);
   }

   public String getFirstFinalizer() {
      return (String)this.finalizers.get(0);
   }

   public String getLastFinalizer() {
      return (String)this.finalizers.get(this.finalizers.size() - 1);
   }

   public String getMatchingFinalizer(Predicate predicate) {
      for(String item : this.finalizers) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingFinalizer(Predicate predicate) {
      for(String item : this.finalizers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamespaceSpecFluent withFinalizers(List finalizers) {
      if (finalizers != null) {
         this.finalizers = new ArrayList();

         for(String item : finalizers) {
            this.addToFinalizers(item);
         }
      } else {
         this.finalizers = null;
      }

      return this;
   }

   public NamespaceSpecFluent withFinalizers(String... finalizers) {
      if (this.finalizers != null) {
         this.finalizers.clear();
         this._visitables.remove("finalizers");
      }

      if (finalizers != null) {
         for(String item : finalizers) {
            this.addToFinalizers(item);
         }
      }

      return this;
   }

   public boolean hasFinalizers() {
      return this.finalizers != null && !this.finalizers.isEmpty();
   }

   public NamespaceSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamespaceSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamespaceSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamespaceSpecFluent removeFromAdditionalProperties(Map map) {
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

   public NamespaceSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            NamespaceSpecFluent that = (NamespaceSpecFluent)o;
            if (!Objects.equals(this.finalizers, that.finalizers)) {
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
      return Objects.hash(new Object[]{this.finalizers, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.finalizers != null && !this.finalizers.isEmpty()) {
         sb.append("finalizers:");
         sb.append(this.finalizers + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
