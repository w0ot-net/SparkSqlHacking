package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CSINodeSpecFluent extends BaseFluent {
   private ArrayList drivers = new ArrayList();
   private Map additionalProperties;

   public CSINodeSpecFluent() {
   }

   public CSINodeSpecFluent(CSINodeSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CSINodeSpec instance) {
      instance = instance != null ? instance : new CSINodeSpec();
      if (instance != null) {
         this.withDrivers(instance.getDrivers());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CSINodeSpecFluent addToDrivers(int index, CSINodeDriver item) {
      if (this.drivers == null) {
         this.drivers = new ArrayList();
      }

      CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
      if (index >= 0 && index < this.drivers.size()) {
         this._visitables.get("drivers").add(index, builder);
         this.drivers.add(index, builder);
      } else {
         this._visitables.get("drivers").add(builder);
         this.drivers.add(builder);
      }

      return this;
   }

   public CSINodeSpecFluent setToDrivers(int index, CSINodeDriver item) {
      if (this.drivers == null) {
         this.drivers = new ArrayList();
      }

      CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
      if (index >= 0 && index < this.drivers.size()) {
         this._visitables.get("drivers").set(index, builder);
         this.drivers.set(index, builder);
      } else {
         this._visitables.get("drivers").add(builder);
         this.drivers.add(builder);
      }

      return this;
   }

   public CSINodeSpecFluent addToDrivers(CSINodeDriver... items) {
      if (this.drivers == null) {
         this.drivers = new ArrayList();
      }

      for(CSINodeDriver item : items) {
         CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
         this._visitables.get("drivers").add(builder);
         this.drivers.add(builder);
      }

      return this;
   }

   public CSINodeSpecFluent addAllToDrivers(Collection items) {
      if (this.drivers == null) {
         this.drivers = new ArrayList();
      }

      for(CSINodeDriver item : items) {
         CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
         this._visitables.get("drivers").add(builder);
         this.drivers.add(builder);
      }

      return this;
   }

   public CSINodeSpecFluent removeFromDrivers(CSINodeDriver... items) {
      if (this.drivers == null) {
         return this;
      } else {
         for(CSINodeDriver item : items) {
            CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
            this._visitables.get("drivers").remove(builder);
            this.drivers.remove(builder);
         }

         return this;
      }
   }

   public CSINodeSpecFluent removeAllFromDrivers(Collection items) {
      if (this.drivers == null) {
         return this;
      } else {
         for(CSINodeDriver item : items) {
            CSINodeDriverBuilder builder = new CSINodeDriverBuilder(item);
            this._visitables.get("drivers").remove(builder);
            this.drivers.remove(builder);
         }

         return this;
      }
   }

   public CSINodeSpecFluent removeMatchingFromDrivers(Predicate predicate) {
      if (this.drivers == null) {
         return this;
      } else {
         Iterator<CSINodeDriverBuilder> each = this.drivers.iterator();
         List visitables = this._visitables.get("drivers");

         while(each.hasNext()) {
            CSINodeDriverBuilder builder = (CSINodeDriverBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildDrivers() {
      return this.drivers != null ? build(this.drivers) : null;
   }

   public CSINodeDriver buildDriver(int index) {
      return ((CSINodeDriverBuilder)this.drivers.get(index)).build();
   }

   public CSINodeDriver buildFirstDriver() {
      return ((CSINodeDriverBuilder)this.drivers.get(0)).build();
   }

   public CSINodeDriver buildLastDriver() {
      return ((CSINodeDriverBuilder)this.drivers.get(this.drivers.size() - 1)).build();
   }

   public CSINodeDriver buildMatchingDriver(Predicate predicate) {
      for(CSINodeDriverBuilder item : this.drivers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingDriver(Predicate predicate) {
      for(CSINodeDriverBuilder item : this.drivers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CSINodeSpecFluent withDrivers(List drivers) {
      if (this.drivers != null) {
         this._visitables.get("drivers").clear();
      }

      if (drivers != null) {
         this.drivers = new ArrayList();

         for(CSINodeDriver item : drivers) {
            this.addToDrivers(item);
         }
      } else {
         this.drivers = null;
      }

      return this;
   }

   public CSINodeSpecFluent withDrivers(CSINodeDriver... drivers) {
      if (this.drivers != null) {
         this.drivers.clear();
         this._visitables.remove("drivers");
      }

      if (drivers != null) {
         for(CSINodeDriver item : drivers) {
            this.addToDrivers(item);
         }
      }

      return this;
   }

   public boolean hasDrivers() {
      return this.drivers != null && !this.drivers.isEmpty();
   }

   public DriversNested addNewDriver() {
      return new DriversNested(-1, (CSINodeDriver)null);
   }

   public DriversNested addNewDriverLike(CSINodeDriver item) {
      return new DriversNested(-1, item);
   }

   public DriversNested setNewDriverLike(int index, CSINodeDriver item) {
      return new DriversNested(index, item);
   }

   public DriversNested editDriver(int index) {
      if (this.drivers.size() <= index) {
         throw new RuntimeException("Can't edit drivers. Index exceeds size.");
      } else {
         return this.setNewDriverLike(index, this.buildDriver(index));
      }
   }

   public DriversNested editFirstDriver() {
      if (this.drivers.size() == 0) {
         throw new RuntimeException("Can't edit first drivers. The list is empty.");
      } else {
         return this.setNewDriverLike(0, this.buildDriver(0));
      }
   }

   public DriversNested editLastDriver() {
      int index = this.drivers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last drivers. The list is empty.");
      } else {
         return this.setNewDriverLike(index, this.buildDriver(index));
      }
   }

   public DriversNested editMatchingDriver(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.drivers.size(); ++i) {
         if (predicate.test((CSINodeDriverBuilder)this.drivers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching drivers. No match found.");
      } else {
         return this.setNewDriverLike(index, this.buildDriver(index));
      }
   }

   public CSINodeSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CSINodeSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CSINodeSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CSINodeSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CSINodeSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CSINodeSpecFluent that = (CSINodeSpecFluent)o;
            if (!Objects.equals(this.drivers, that.drivers)) {
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
      return Objects.hash(new Object[]{this.drivers, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.drivers != null && !this.drivers.isEmpty()) {
         sb.append("drivers:");
         sb.append(this.drivers + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DriversNested extends CSINodeDriverFluent implements Nested {
      CSINodeDriverBuilder builder;
      int index;

      DriversNested(int index, CSINodeDriver item) {
         this.index = index;
         this.builder = new CSINodeDriverBuilder(this, item);
      }

      public Object and() {
         return CSINodeSpecFluent.this.setToDrivers(this.index, this.builder.build());
      }

      public Object endDriver() {
         return this.and();
      }
   }
}
