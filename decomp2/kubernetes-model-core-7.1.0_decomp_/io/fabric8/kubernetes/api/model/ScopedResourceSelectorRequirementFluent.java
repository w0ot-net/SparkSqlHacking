package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ScopedResourceSelectorRequirementFluent extends BaseFluent {
   private String operator;
   private String scopeName;
   private List values = new ArrayList();
   private Map additionalProperties;

   public ScopedResourceSelectorRequirementFluent() {
   }

   public ScopedResourceSelectorRequirementFluent(ScopedResourceSelectorRequirement instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ScopedResourceSelectorRequirement instance) {
      instance = instance != null ? instance : new ScopedResourceSelectorRequirement();
      if (instance != null) {
         this.withOperator(instance.getOperator());
         this.withScopeName(instance.getScopeName());
         this.withValues(instance.getValues());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getOperator() {
      return this.operator;
   }

   public ScopedResourceSelectorRequirementFluent withOperator(String operator) {
      this.operator = operator;
      return this;
   }

   public boolean hasOperator() {
      return this.operator != null;
   }

   public String getScopeName() {
      return this.scopeName;
   }

   public ScopedResourceSelectorRequirementFluent withScopeName(String scopeName) {
      this.scopeName = scopeName;
      return this;
   }

   public boolean hasScopeName() {
      return this.scopeName != null;
   }

   public ScopedResourceSelectorRequirementFluent addToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(index, item);
      return this;
   }

   public ScopedResourceSelectorRequirementFluent setToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.set(index, item);
      return this;
   }

   public ScopedResourceSelectorRequirementFluent addToValues(String... items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public ScopedResourceSelectorRequirementFluent addAllToValues(Collection items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public ScopedResourceSelectorRequirementFluent removeFromValues(String... items) {
      if (this.values == null) {
         return this;
      } else {
         for(String item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public ScopedResourceSelectorRequirementFluent removeAllFromValues(Collection items) {
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

   public ScopedResourceSelectorRequirementFluent withValues(List values) {
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

   public ScopedResourceSelectorRequirementFluent withValues(String... values) {
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

   public ScopedResourceSelectorRequirementFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ScopedResourceSelectorRequirementFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ScopedResourceSelectorRequirementFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ScopedResourceSelectorRequirementFluent removeFromAdditionalProperties(Map map) {
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

   public ScopedResourceSelectorRequirementFluent withAdditionalProperties(Map additionalProperties) {
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
            ScopedResourceSelectorRequirementFluent that = (ScopedResourceSelectorRequirementFluent)o;
            if (!Objects.equals(this.operator, that.operator)) {
               return false;
            } else if (!Objects.equals(this.scopeName, that.scopeName)) {
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
      return Objects.hash(new Object[]{this.operator, this.scopeName, this.values, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.operator != null) {
         sb.append("operator:");
         sb.append(this.operator + ",");
      }

      if (this.scopeName != null) {
         sb.append("scopeName:");
         sb.append(this.scopeName + ",");
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
