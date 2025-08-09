package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NamedResourcesStringSliceFluent extends BaseFluent {
   private List strings = new ArrayList();
   private Map additionalProperties;

   public NamedResourcesStringSliceFluent() {
   }

   public NamedResourcesStringSliceFluent(NamedResourcesStringSlice instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesStringSlice instance) {
      instance = instance != null ? instance : new NamedResourcesStringSlice();
      if (instance != null) {
         this.withStrings(instance.getStrings());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesStringSliceFluent addToStrings(int index, String item) {
      if (this.strings == null) {
         this.strings = new ArrayList();
      }

      this.strings.add(index, item);
      return this;
   }

   public NamedResourcesStringSliceFluent setToStrings(int index, String item) {
      if (this.strings == null) {
         this.strings = new ArrayList();
      }

      this.strings.set(index, item);
      return this;
   }

   public NamedResourcesStringSliceFluent addToStrings(String... items) {
      if (this.strings == null) {
         this.strings = new ArrayList();
      }

      for(String item : items) {
         this.strings.add(item);
      }

      return this;
   }

   public NamedResourcesStringSliceFluent addAllToStrings(Collection items) {
      if (this.strings == null) {
         this.strings = new ArrayList();
      }

      for(String item : items) {
         this.strings.add(item);
      }

      return this;
   }

   public NamedResourcesStringSliceFluent removeFromStrings(String... items) {
      if (this.strings == null) {
         return this;
      } else {
         for(String item : items) {
            this.strings.remove(item);
         }

         return this;
      }
   }

   public NamedResourcesStringSliceFluent removeAllFromStrings(Collection items) {
      if (this.strings == null) {
         return this;
      } else {
         for(String item : items) {
            this.strings.remove(item);
         }

         return this;
      }
   }

   public List getStrings() {
      return this.strings;
   }

   public String getString(int index) {
      return (String)this.strings.get(index);
   }

   public String getFirstString() {
      return (String)this.strings.get(0);
   }

   public String getLastString() {
      return (String)this.strings.get(this.strings.size() - 1);
   }

   public String getMatchingString(Predicate predicate) {
      for(String item : this.strings) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingString(Predicate predicate) {
      for(String item : this.strings) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamedResourcesStringSliceFluent withStrings(List strings) {
      if (strings != null) {
         this.strings = new ArrayList();

         for(String item : strings) {
            this.addToStrings(item);
         }
      } else {
         this.strings = null;
      }

      return this;
   }

   public NamedResourcesStringSliceFluent withStrings(String... strings) {
      if (this.strings != null) {
         this.strings.clear();
         this._visitables.remove("strings");
      }

      if (strings != null) {
         for(String item : strings) {
            this.addToStrings(item);
         }
      }

      return this;
   }

   public boolean hasStrings() {
      return this.strings != null && !this.strings.isEmpty();
   }

   public NamedResourcesStringSliceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesStringSliceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesStringSliceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesStringSliceFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesStringSliceFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesStringSliceFluent that = (NamedResourcesStringSliceFluent)o;
            if (!Objects.equals(this.strings, that.strings)) {
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
      return Objects.hash(new Object[]{this.strings, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.strings != null && !this.strings.isEmpty()) {
         sb.append("strings:");
         sb.append(this.strings + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
