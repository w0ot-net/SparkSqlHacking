package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CapabilitiesFluent extends BaseFluent {
   private List add = new ArrayList();
   private List drop = new ArrayList();
   private Map additionalProperties;

   public CapabilitiesFluent() {
   }

   public CapabilitiesFluent(Capabilities instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Capabilities instance) {
      instance = instance != null ? instance : new Capabilities();
      if (instance != null) {
         this.withAdd(instance.getAdd());
         this.withDrop(instance.getDrop());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CapabilitiesFluent addToAdd(int index, String item) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      this.add.add(index, item);
      return this;
   }

   public CapabilitiesFluent setToAdd(int index, String item) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      this.add.set(index, item);
      return this;
   }

   public CapabilitiesFluent addToAdd(String... items) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      for(String item : items) {
         this.add.add(item);
      }

      return this;
   }

   public CapabilitiesFluent addAllToAdd(Collection items) {
      if (this.add == null) {
         this.add = new ArrayList();
      }

      for(String item : items) {
         this.add.add(item);
      }

      return this;
   }

   public CapabilitiesFluent removeFromAdd(String... items) {
      if (this.add == null) {
         return this;
      } else {
         for(String item : items) {
            this.add.remove(item);
         }

         return this;
      }
   }

   public CapabilitiesFluent removeAllFromAdd(Collection items) {
      if (this.add == null) {
         return this;
      } else {
         for(String item : items) {
            this.add.remove(item);
         }

         return this;
      }
   }

   public List getAdd() {
      return this.add;
   }

   public String getAdd(int index) {
      return (String)this.add.get(index);
   }

   public String getFirstAdd() {
      return (String)this.add.get(0);
   }

   public String getLastAdd() {
      return (String)this.add.get(this.add.size() - 1);
   }

   public String getMatchingAdd(Predicate predicate) {
      for(String item : this.add) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAdd(Predicate predicate) {
      for(String item : this.add) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CapabilitiesFluent withAdd(List add) {
      if (add != null) {
         this.add = new ArrayList();

         for(String item : add) {
            this.addToAdd(item);
         }
      } else {
         this.add = null;
      }

      return this;
   }

   public CapabilitiesFluent withAdd(String... add) {
      if (this.add != null) {
         this.add.clear();
         this._visitables.remove("add");
      }

      if (add != null) {
         for(String item : add) {
            this.addToAdd(item);
         }
      }

      return this;
   }

   public boolean hasAdd() {
      return this.add != null && !this.add.isEmpty();
   }

   public CapabilitiesFluent addToDrop(int index, String item) {
      if (this.drop == null) {
         this.drop = new ArrayList();
      }

      this.drop.add(index, item);
      return this;
   }

   public CapabilitiesFluent setToDrop(int index, String item) {
      if (this.drop == null) {
         this.drop = new ArrayList();
      }

      this.drop.set(index, item);
      return this;
   }

   public CapabilitiesFluent addToDrop(String... items) {
      if (this.drop == null) {
         this.drop = new ArrayList();
      }

      for(String item : items) {
         this.drop.add(item);
      }

      return this;
   }

   public CapabilitiesFluent addAllToDrop(Collection items) {
      if (this.drop == null) {
         this.drop = new ArrayList();
      }

      for(String item : items) {
         this.drop.add(item);
      }

      return this;
   }

   public CapabilitiesFluent removeFromDrop(String... items) {
      if (this.drop == null) {
         return this;
      } else {
         for(String item : items) {
            this.drop.remove(item);
         }

         return this;
      }
   }

   public CapabilitiesFluent removeAllFromDrop(Collection items) {
      if (this.drop == null) {
         return this;
      } else {
         for(String item : items) {
            this.drop.remove(item);
         }

         return this;
      }
   }

   public List getDrop() {
      return this.drop;
   }

   public String getDrop(int index) {
      return (String)this.drop.get(index);
   }

   public String getFirstDrop() {
      return (String)this.drop.get(0);
   }

   public String getLastDrop() {
      return (String)this.drop.get(this.drop.size() - 1);
   }

   public String getMatchingDrop(Predicate predicate) {
      for(String item : this.drop) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingDrop(Predicate predicate) {
      for(String item : this.drop) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CapabilitiesFluent withDrop(List drop) {
      if (drop != null) {
         this.drop = new ArrayList();

         for(String item : drop) {
            this.addToDrop(item);
         }
      } else {
         this.drop = null;
      }

      return this;
   }

   public CapabilitiesFluent withDrop(String... drop) {
      if (this.drop != null) {
         this.drop.clear();
         this._visitables.remove("drop");
      }

      if (drop != null) {
         for(String item : drop) {
            this.addToDrop(item);
         }
      }

      return this;
   }

   public boolean hasDrop() {
      return this.drop != null && !this.drop.isEmpty();
   }

   public CapabilitiesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CapabilitiesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CapabilitiesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CapabilitiesFluent removeFromAdditionalProperties(Map map) {
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

   public CapabilitiesFluent withAdditionalProperties(Map additionalProperties) {
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
            CapabilitiesFluent that = (CapabilitiesFluent)o;
            if (!Objects.equals(this.add, that.add)) {
               return false;
            } else if (!Objects.equals(this.drop, that.drop)) {
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
      return Objects.hash(new Object[]{this.add, this.drop, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.add != null && !this.add.isEmpty()) {
         sb.append("add:");
         sb.append(this.add + ",");
      }

      if (this.drop != null && !this.drop.isEmpty()) {
         sb.append("drop:");
         sb.append(this.drop + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
