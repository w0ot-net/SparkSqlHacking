package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NodeSelectorRequirementFluent extends BaseFluent {
   private String key;
   private String operator;
   private List values = new ArrayList();
   private Map additionalProperties;

   public NodeSelectorRequirementFluent() {
   }

   public NodeSelectorRequirementFluent(NodeSelectorRequirement instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeSelectorRequirement instance) {
      instance = instance != null ? instance : new NodeSelectorRequirement();
      if (instance != null) {
         this.withKey(instance.getKey());
         this.withOperator(instance.getOperator());
         this.withValues(instance.getValues());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKey() {
      return this.key;
   }

   public NodeSelectorRequirementFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public String getOperator() {
      return this.operator;
   }

   public NodeSelectorRequirementFluent withOperator(String operator) {
      this.operator = operator;
      return this;
   }

   public boolean hasOperator() {
      return this.operator != null;
   }

   public NodeSelectorRequirementFluent addToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(index, item);
      return this;
   }

   public NodeSelectorRequirementFluent setToValues(int index, String item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.set(index, item);
      return this;
   }

   public NodeSelectorRequirementFluent addToValues(String... items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public NodeSelectorRequirementFluent addAllToValues(Collection items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(String item : items) {
         this.values.add(item);
      }

      return this;
   }

   public NodeSelectorRequirementFluent removeFromValues(String... items) {
      if (this.values == null) {
         return this;
      } else {
         for(String item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public NodeSelectorRequirementFluent removeAllFromValues(Collection items) {
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

   public NodeSelectorRequirementFluent withValues(List values) {
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

   public NodeSelectorRequirementFluent withValues(String... values) {
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

   public NodeSelectorRequirementFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeSelectorRequirementFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeSelectorRequirementFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeSelectorRequirementFluent removeFromAdditionalProperties(Map map) {
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

   public NodeSelectorRequirementFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeSelectorRequirementFluent that = (NodeSelectorRequirementFluent)o;
            if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.operator, that.operator)) {
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
      return Objects.hash(new Object[]{this.key, this.operator, this.values, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.operator != null) {
         sb.append("operator:");
         sb.append(this.operator + ",");
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
