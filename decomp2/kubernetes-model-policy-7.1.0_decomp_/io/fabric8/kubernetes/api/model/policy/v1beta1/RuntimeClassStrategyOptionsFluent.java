package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class RuntimeClassStrategyOptionsFluent extends BaseFluent {
   private List allowedRuntimeClassNames = new ArrayList();
   private String defaultRuntimeClassName;
   private Map additionalProperties;

   public RuntimeClassStrategyOptionsFluent() {
   }

   public RuntimeClassStrategyOptionsFluent(RuntimeClassStrategyOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RuntimeClassStrategyOptions instance) {
      instance = instance != null ? instance : new RuntimeClassStrategyOptions();
      if (instance != null) {
         this.withAllowedRuntimeClassNames(instance.getAllowedRuntimeClassNames());
         this.withDefaultRuntimeClassName(instance.getDefaultRuntimeClassName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RuntimeClassStrategyOptionsFluent addToAllowedRuntimeClassNames(int index, String item) {
      if (this.allowedRuntimeClassNames == null) {
         this.allowedRuntimeClassNames = new ArrayList();
      }

      this.allowedRuntimeClassNames.add(index, item);
      return this;
   }

   public RuntimeClassStrategyOptionsFluent setToAllowedRuntimeClassNames(int index, String item) {
      if (this.allowedRuntimeClassNames == null) {
         this.allowedRuntimeClassNames = new ArrayList();
      }

      this.allowedRuntimeClassNames.set(index, item);
      return this;
   }

   public RuntimeClassStrategyOptionsFluent addToAllowedRuntimeClassNames(String... items) {
      if (this.allowedRuntimeClassNames == null) {
         this.allowedRuntimeClassNames = new ArrayList();
      }

      for(String item : items) {
         this.allowedRuntimeClassNames.add(item);
      }

      return this;
   }

   public RuntimeClassStrategyOptionsFluent addAllToAllowedRuntimeClassNames(Collection items) {
      if (this.allowedRuntimeClassNames == null) {
         this.allowedRuntimeClassNames = new ArrayList();
      }

      for(String item : items) {
         this.allowedRuntimeClassNames.add(item);
      }

      return this;
   }

   public RuntimeClassStrategyOptionsFluent removeFromAllowedRuntimeClassNames(String... items) {
      if (this.allowedRuntimeClassNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedRuntimeClassNames.remove(item);
         }

         return this;
      }
   }

   public RuntimeClassStrategyOptionsFluent removeAllFromAllowedRuntimeClassNames(Collection items) {
      if (this.allowedRuntimeClassNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedRuntimeClassNames.remove(item);
         }

         return this;
      }
   }

   public List getAllowedRuntimeClassNames() {
      return this.allowedRuntimeClassNames;
   }

   public String getAllowedRuntimeClassName(int index) {
      return (String)this.allowedRuntimeClassNames.get(index);
   }

   public String getFirstAllowedRuntimeClassName() {
      return (String)this.allowedRuntimeClassNames.get(0);
   }

   public String getLastAllowedRuntimeClassName() {
      return (String)this.allowedRuntimeClassNames.get(this.allowedRuntimeClassNames.size() - 1);
   }

   public String getMatchingAllowedRuntimeClassName(Predicate predicate) {
      for(String item : this.allowedRuntimeClassNames) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedRuntimeClassName(Predicate predicate) {
      for(String item : this.allowedRuntimeClassNames) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RuntimeClassStrategyOptionsFluent withAllowedRuntimeClassNames(List allowedRuntimeClassNames) {
      if (allowedRuntimeClassNames != null) {
         this.allowedRuntimeClassNames = new ArrayList();

         for(String item : allowedRuntimeClassNames) {
            this.addToAllowedRuntimeClassNames(item);
         }
      } else {
         this.allowedRuntimeClassNames = null;
      }

      return this;
   }

   public RuntimeClassStrategyOptionsFluent withAllowedRuntimeClassNames(String... allowedRuntimeClassNames) {
      if (this.allowedRuntimeClassNames != null) {
         this.allowedRuntimeClassNames.clear();
         this._visitables.remove("allowedRuntimeClassNames");
      }

      if (allowedRuntimeClassNames != null) {
         for(String item : allowedRuntimeClassNames) {
            this.addToAllowedRuntimeClassNames(item);
         }
      }

      return this;
   }

   public boolean hasAllowedRuntimeClassNames() {
      return this.allowedRuntimeClassNames != null && !this.allowedRuntimeClassNames.isEmpty();
   }

   public String getDefaultRuntimeClassName() {
      return this.defaultRuntimeClassName;
   }

   public RuntimeClassStrategyOptionsFluent withDefaultRuntimeClassName(String defaultRuntimeClassName) {
      this.defaultRuntimeClassName = defaultRuntimeClassName;
      return this;
   }

   public boolean hasDefaultRuntimeClassName() {
      return this.defaultRuntimeClassName != null;
   }

   public RuntimeClassStrategyOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RuntimeClassStrategyOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RuntimeClassStrategyOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RuntimeClassStrategyOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public RuntimeClassStrategyOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            RuntimeClassStrategyOptionsFluent that = (RuntimeClassStrategyOptionsFluent)o;
            if (!Objects.equals(this.allowedRuntimeClassNames, that.allowedRuntimeClassNames)) {
               return false;
            } else if (!Objects.equals(this.defaultRuntimeClassName, that.defaultRuntimeClassName)) {
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
      return Objects.hash(new Object[]{this.allowedRuntimeClassNames, this.defaultRuntimeClassName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowedRuntimeClassNames != null && !this.allowedRuntimeClassNames.isEmpty()) {
         sb.append("allowedRuntimeClassNames:");
         sb.append(this.allowedRuntimeClassNames + ",");
      }

      if (this.defaultRuntimeClassName != null) {
         sb.append("defaultRuntimeClassName:");
         sb.append(this.defaultRuntimeClassName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
