package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class PodFailurePolicyOnExitCodesRequirementFluent extends BaseFluent {
   private String containerName;
   private String operator;
   private List values = new ArrayList();
   private Map additionalProperties;

   public PodFailurePolicyOnExitCodesRequirementFluent() {
   }

   public PodFailurePolicyOnExitCodesRequirementFluent(PodFailurePolicyOnExitCodesRequirement instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodFailurePolicyOnExitCodesRequirement instance) {
      instance = instance != null ? instance : new PodFailurePolicyOnExitCodesRequirement();
      if (instance != null) {
         this.withContainerName(instance.getContainerName());
         this.withOperator(instance.getOperator());
         this.withValues(instance.getValues());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContainerName() {
      return this.containerName;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent withContainerName(String containerName) {
      this.containerName = containerName;
      return this;
   }

   public boolean hasContainerName() {
      return this.containerName != null;
   }

   public String getOperator() {
      return this.operator;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent withOperator(String operator) {
      this.operator = operator;
      return this;
   }

   public boolean hasOperator() {
      return this.operator != null;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent addToValues(int index, Integer item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(index, item);
      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent setToValues(int index, Integer item) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.set(index, item);
      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent addToValues(Integer... items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(Integer item : items) {
         this.values.add(item);
      }

      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent addAllToValues(Collection items) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      for(Integer item : items) {
         this.values.add(item);
      }

      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent removeFromValues(Integer... items) {
      if (this.values == null) {
         return this;
      } else {
         for(Integer item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public PodFailurePolicyOnExitCodesRequirementFluent removeAllFromValues(Collection items) {
      if (this.values == null) {
         return this;
      } else {
         for(Integer item : items) {
            this.values.remove(item);
         }

         return this;
      }
   }

   public List getValues() {
      return this.values;
   }

   public Integer getValue(int index) {
      return (Integer)this.values.get(index);
   }

   public Integer getFirstValue() {
      return (Integer)this.values.get(0);
   }

   public Integer getLastValue() {
      return (Integer)this.values.get(this.values.size() - 1);
   }

   public Integer getMatchingValue(Predicate predicate) {
      for(Integer item : this.values) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingValue(Predicate predicate) {
      for(Integer item : this.values) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent withValues(List values) {
      if (values != null) {
         this.values = new ArrayList();

         for(Integer item : values) {
            this.addToValues(item);
         }
      } else {
         this.values = null;
      }

      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent withValues(Integer... values) {
      if (this.values != null) {
         this.values.clear();
         this._visitables.remove("values");
      }

      if (values != null) {
         for(Integer item : values) {
            this.addToValues(item);
         }
      }

      return this;
   }

   public boolean hasValues() {
      return this.values != null && !this.values.isEmpty();
   }

   public PodFailurePolicyOnExitCodesRequirementFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodFailurePolicyOnExitCodesRequirementFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodFailurePolicyOnExitCodesRequirementFluent removeFromAdditionalProperties(Map map) {
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

   public PodFailurePolicyOnExitCodesRequirementFluent withAdditionalProperties(Map additionalProperties) {
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
            PodFailurePolicyOnExitCodesRequirementFluent that = (PodFailurePolicyOnExitCodesRequirementFluent)o;
            if (!Objects.equals(this.containerName, that.containerName)) {
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
      return Objects.hash(new Object[]{this.containerName, this.operator, this.values, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.containerName != null) {
         sb.append("containerName:");
         sb.append(this.containerName + ",");
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
