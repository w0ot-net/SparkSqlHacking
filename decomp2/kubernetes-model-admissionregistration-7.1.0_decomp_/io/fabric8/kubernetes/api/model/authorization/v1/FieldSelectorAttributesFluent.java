package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.FieldSelectorRequirement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class FieldSelectorAttributesFluent extends BaseFluent {
   private String rawSelector;
   private List requirements = new ArrayList();
   private Map additionalProperties;

   public FieldSelectorAttributesFluent() {
   }

   public FieldSelectorAttributesFluent(FieldSelectorAttributes instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FieldSelectorAttributes instance) {
      instance = instance != null ? instance : new FieldSelectorAttributes();
      if (instance != null) {
         this.withRawSelector(instance.getRawSelector());
         this.withRequirements(instance.getRequirements());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getRawSelector() {
      return this.rawSelector;
   }

   public FieldSelectorAttributesFluent withRawSelector(String rawSelector) {
      this.rawSelector = rawSelector;
      return this;
   }

   public boolean hasRawSelector() {
      return this.rawSelector != null;
   }

   public FieldSelectorAttributesFluent addToRequirements(int index, FieldSelectorRequirement item) {
      if (this.requirements == null) {
         this.requirements = new ArrayList();
      }

      this.requirements.add(index, item);
      return this;
   }

   public FieldSelectorAttributesFluent setToRequirements(int index, FieldSelectorRequirement item) {
      if (this.requirements == null) {
         this.requirements = new ArrayList();
      }

      this.requirements.set(index, item);
      return this;
   }

   public FieldSelectorAttributesFluent addToRequirements(FieldSelectorRequirement... items) {
      if (this.requirements == null) {
         this.requirements = new ArrayList();
      }

      for(FieldSelectorRequirement item : items) {
         this.requirements.add(item);
      }

      return this;
   }

   public FieldSelectorAttributesFluent addAllToRequirements(Collection items) {
      if (this.requirements == null) {
         this.requirements = new ArrayList();
      }

      for(FieldSelectorRequirement item : items) {
         this.requirements.add(item);
      }

      return this;
   }

   public FieldSelectorAttributesFluent removeFromRequirements(FieldSelectorRequirement... items) {
      if (this.requirements == null) {
         return this;
      } else {
         for(FieldSelectorRequirement item : items) {
            this.requirements.remove(item);
         }

         return this;
      }
   }

   public FieldSelectorAttributesFluent removeAllFromRequirements(Collection items) {
      if (this.requirements == null) {
         return this;
      } else {
         for(FieldSelectorRequirement item : items) {
            this.requirements.remove(item);
         }

         return this;
      }
   }

   public List getRequirements() {
      return this.requirements;
   }

   public FieldSelectorRequirement getRequirement(int index) {
      return (FieldSelectorRequirement)this.requirements.get(index);
   }

   public FieldSelectorRequirement getFirstRequirement() {
      return (FieldSelectorRequirement)this.requirements.get(0);
   }

   public FieldSelectorRequirement getLastRequirement() {
      return (FieldSelectorRequirement)this.requirements.get(this.requirements.size() - 1);
   }

   public FieldSelectorRequirement getMatchingRequirement(Predicate predicate) {
      for(FieldSelectorRequirement item : this.requirements) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRequirement(Predicate predicate) {
      for(FieldSelectorRequirement item : this.requirements) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FieldSelectorAttributesFluent withRequirements(List requirements) {
      if (requirements != null) {
         this.requirements = new ArrayList();

         for(FieldSelectorRequirement item : requirements) {
            this.addToRequirements(item);
         }
      } else {
         this.requirements = null;
      }

      return this;
   }

   public FieldSelectorAttributesFluent withRequirements(FieldSelectorRequirement... requirements) {
      if (this.requirements != null) {
         this.requirements.clear();
         this._visitables.remove("requirements");
      }

      if (requirements != null) {
         for(FieldSelectorRequirement item : requirements) {
            this.addToRequirements(item);
         }
      }

      return this;
   }

   public boolean hasRequirements() {
      return this.requirements != null && !this.requirements.isEmpty();
   }

   public FieldSelectorAttributesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FieldSelectorAttributesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FieldSelectorAttributesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FieldSelectorAttributesFluent removeFromAdditionalProperties(Map map) {
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

   public FieldSelectorAttributesFluent withAdditionalProperties(Map additionalProperties) {
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
            FieldSelectorAttributesFluent that = (FieldSelectorAttributesFluent)o;
            if (!Objects.equals(this.rawSelector, that.rawSelector)) {
               return false;
            } else if (!Objects.equals(this.requirements, that.requirements)) {
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
      return Objects.hash(new Object[]{this.rawSelector, this.requirements, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.rawSelector != null) {
         sb.append("rawSelector:");
         sb.append(this.rawSelector + ",");
      }

      if (this.requirements != null && !this.requirements.isEmpty()) {
         sb.append("requirements:");
         sb.append(this.requirements + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
