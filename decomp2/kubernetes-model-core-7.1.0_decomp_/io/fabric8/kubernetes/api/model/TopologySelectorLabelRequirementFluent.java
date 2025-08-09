package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class TopologySelectorLabelRequirementFluent extends BaseFluent {
   private String key;
   private List values = new ArrayList();
   private Map additionalProperties;

   public TopologySelectorLabelRequirementFluent() {
   }

   public TopologySelectorLabelRequirementFluent(TopologySelectorLabelRequirement instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TopologySelectorLabelRequirement instance) {
      instance = instance != null ? instance : new TopologySelectorLabelRequirement();
      if (instance != null) {
         this.withKey(instance.getKey());
         this.withValues(instance.getValues());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKey() {
      return this.key;
   }

   public TopologySelectorLabelRequirementFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public TopologySelectorLabelRequirementFluent addToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(index, item);
      return this;
   }

   public TopologySelectorLabelRequirementFluent setToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.set(index, item);
      return this;
   }

   public TopologySelectorLabelRequirementFluent addToValues(String... items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public TopologySelectorLabelRequirementFluent addAllToValues(Collection items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public TopologySelectorLabelRequirementFluent removeFromValues(String... items) {
      if (this.values == null) {
         return this;
      } else {
         for(String item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public TopologySelectorLabelRequirementFluent removeAllFromValues(Collection items) {
      if (this.values == null) {
         return this;
      } else {
         for(String item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public List getValues() {
      return this.values;
   }

   public String getValue(int index) {
      return (String)this.values.get(index);
   }

   public String getFirstValue() {
      return (String)this.values.get(0);
   }

   public String getLastValue() {
      return (String)this.values.get(this.values.size() - 1);
   }

   public String getMatchingValue(Predicate predicate) {
      for(String item : this.values) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingValue(Predicate predicate) {
      for(String item : this.values) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TopologySelectorLabelRequirementFluent withValues(List values) {
      if (values != null) {
         this.values = new ArrayList();

         for(String item : values) {
            this.addToValues(item);
         }
      } else {
         this.values = null;
      }

      return this;
   }

   public TopologySelectorLabelRequirementFluent withValues(String... values) {
      if (this.values != null) {
         this.values.clear();
         this._visitables.remove("values");
      }

      if (values != null) {
         for(String item : values) {
            this.addToValues(item);
         }
      }

      return this;
   }

   public boolean hasValues() {
      return this.values != null && !this.values.isEmpty();
   }

   public TopologySelectorLabelRequirementFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TopologySelectorLabelRequirementFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TopologySelectorLabelRequirementFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TopologySelectorLabelRequirementFluent removeFromAdditionalProperties(Map map) {
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

   public TopologySelectorLabelRequirementFluent withAdditionalProperties(Map additionalProperties) {
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
            TopologySelectorLabelRequirementFluent that = (TopologySelectorLabelRequirementFluent)o;
            if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.values, that.values)) {
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
      return Objects.hash(new Object[]{this.key, this.values, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.values != null && !this.values.isEmpty()) {
         sb.append("values:");
         sb.append(this.values + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
