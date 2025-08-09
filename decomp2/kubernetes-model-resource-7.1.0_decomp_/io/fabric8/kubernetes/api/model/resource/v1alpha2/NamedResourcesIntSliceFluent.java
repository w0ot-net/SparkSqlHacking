package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NamedResourcesIntSliceFluent extends BaseFluent {
   private List ints = new ArrayList();
   private Map additionalProperties;

   public NamedResourcesIntSliceFluent() {
   }

   public NamedResourcesIntSliceFluent(NamedResourcesIntSlice instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesIntSlice instance) {
      instance = instance != null ? instance : new NamedResourcesIntSlice();
      if (instance != null) {
         this.withInts(instance.getInts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesIntSliceFluent addToInts(int index, Long item) {
      if (this.ints == null) {
         this.ints = new ArrayList();
      }

      this.ints.add(index, item);
      return this;
   }

   public NamedResourcesIntSliceFluent setToInts(int index, Long item) {
      if (this.ints == null) {
         this.ints = new ArrayList();
      }

      this.ints.set(index, item);
      return this;
   }

   public NamedResourcesIntSliceFluent addToInts(Long... items) {
      if (this.ints == null) {
         this.ints = new ArrayList();
      }

      for(Long item : items) {
         this.ints.add(item);
      }

      return this;
   }

   public NamedResourcesIntSliceFluent addAllToInts(Collection items) {
      if (this.ints == null) {
         this.ints = new ArrayList();
      }

      for(Long item : items) {
         this.ints.add(item);
      }

      return this;
   }

   public NamedResourcesIntSliceFluent removeFromInts(Long... items) {
      if (this.ints == null) {
         return this;
      } else {
         for(Long item : items) {
            this.ints.remove(item);
         }

         return this;
      }
   }

   public NamedResourcesIntSliceFluent removeAllFromInts(Collection items) {
      if (this.ints == null) {
         return this;
      } else {
         for(Long item : items) {
            this.ints.remove(item);
         }

         return this;
      }
   }

   public List getInts() {
      return this.ints;
   }

   public Long getInt(int index) {
      return (Long)this.ints.get(index);
   }

   public Long getFirstInt() {
      return (Long)this.ints.get(0);
   }

   public Long getLastInt() {
      return (Long)this.ints.get(this.ints.size() - 1);
   }

   public Long getMatchingInt(Predicate predicate) {
      for(Long item : this.ints) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingInt(Predicate predicate) {
      for(Long item : this.ints) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamedResourcesIntSliceFluent withInts(List ints) {
      if (ints != null) {
         this.ints = new ArrayList();

         for(Long item : ints) {
            this.addToInts(item);
         }
      } else {
         this.ints = null;
      }

      return this;
   }

   public NamedResourcesIntSliceFluent withInts(Long... ints) {
      if (this.ints != null) {
         this.ints.clear();
         this._visitables.remove("ints");
      }

      if (ints != null) {
         for(Long item : ints) {
            this.addToInts(item);
         }
      }

      return this;
   }

   public boolean hasInts() {
      return this.ints != null && !this.ints.isEmpty();
   }

   public NamedResourcesIntSliceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesIntSliceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesIntSliceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesIntSliceFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesIntSliceFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesIntSliceFluent that = (NamedResourcesIntSliceFluent)o;
            if (!Objects.equals(this.ints, that.ints)) {
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
      return Objects.hash(new Object[]{this.ints, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ints != null && !this.ints.isEmpty()) {
         sb.append("ints:");
         sb.append(this.ints + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
