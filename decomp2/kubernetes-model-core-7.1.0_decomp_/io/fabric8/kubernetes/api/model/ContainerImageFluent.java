package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ContainerImageFluent extends BaseFluent {
   private List names = new ArrayList();
   private Long sizeBytes;
   private Map additionalProperties;

   public ContainerImageFluent() {
   }

   public ContainerImageFluent(ContainerImage instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerImage instance) {
      instance = instance != null ? instance : new ContainerImage();
      if (instance != null) {
         this.withNames(instance.getNames());
         this.withSizeBytes(instance.getSizeBytes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ContainerImageFluent addToNames(int index, String item) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      this.names.add(index, item);
      return this;
   }

   public ContainerImageFluent setToNames(int index, String item) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      this.names.set(index, item);
      return this;
   }

   public ContainerImageFluent addToNames(String... items) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      for(String item : items) {
         this.names.add(item);
      }

      return this;
   }

   public ContainerImageFluent addAllToNames(Collection items) {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      for(String item : items) {
         this.names.add(item);
      }

      return this;
   }

   public ContainerImageFluent removeFromNames(String... items) {
      if (this.names == null) {
         return this;
      } else {
         for(String item : items) {
            this.names.remove(item);
         }

         return this;
      }
   }

   public ContainerImageFluent removeAllFromNames(Collection items) {
      if (this.names == null) {
         return this;
      } else {
         for(String item : items) {
            this.names.remove(item);
         }

         return this;
      }
   }

   public List getNames() {
      return this.names;
   }

   public String getName(int index) {
      return (String)this.names.get(index);
   }

   public String getFirstName() {
      return (String)this.names.get(0);
   }

   public String getLastName() {
      return (String)this.names.get(this.names.size() - 1);
   }

   public String getMatchingName(Predicate predicate) {
      for(String item : this.names) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingName(Predicate predicate) {
      for(String item : this.names) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ContainerImageFluent withNames(List names) {
      if (names != null) {
         this.names = new ArrayList();

         for(String item : names) {
            this.addToNames(item);
         }
      } else {
         this.names = null;
      }

      return this;
   }

   public ContainerImageFluent withNames(String... names) {
      if (this.names != null) {
         this.names.clear();
         this._visitables.remove("names");
      }

      if (names != null) {
         for(String item : names) {
            this.addToNames(item);
         }
      }

      return this;
   }

   public boolean hasNames() {
      return this.names != null && !this.names.isEmpty();
   }

   public Long getSizeBytes() {
      return this.sizeBytes;
   }

   public ContainerImageFluent withSizeBytes(Long sizeBytes) {
      this.sizeBytes = sizeBytes;
      return this;
   }

   public boolean hasSizeBytes() {
      return this.sizeBytes != null;
   }

   public ContainerImageFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerImageFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerImageFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerImageFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerImageFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerImageFluent that = (ContainerImageFluent)o;
            if (!Objects.equals(this.names, that.names)) {
               return false;
            } else if (!Objects.equals(this.sizeBytes, that.sizeBytes)) {
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
      return Objects.hash(new Object[]{this.names, this.sizeBytes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.names != null && !this.names.isEmpty()) {
         sb.append("names:");
         sb.append(this.names + ",");
      }

      if (this.sizeBytes != null) {
         sb.append("sizeBytes:");
         sb.append(this.sizeBytes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
