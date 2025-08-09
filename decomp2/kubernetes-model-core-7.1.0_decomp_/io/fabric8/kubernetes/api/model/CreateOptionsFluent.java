package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CreateOptionsFluent extends BaseFluent {
   private String apiVersion;
   private List dryRun = new ArrayList();
   private String fieldManager;
   private String fieldValidation;
   private String kind;
   private Map additionalProperties;

   public CreateOptionsFluent() {
   }

   public CreateOptionsFluent(CreateOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CreateOptions instance) {
      instance = instance != null ? instance : new CreateOptions();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withDryRun(instance.getDryRun());
         this.withFieldManager(instance.getFieldManager());
         this.withFieldValidation(instance.getFieldValidation());
         this.withKind(instance.getKind());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public CreateOptionsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public CreateOptionsFluent addToDryRun(int index, String item) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      this.dryRun.add(index, item);
      return this;
   }

   public CreateOptionsFluent setToDryRun(int index, String item) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      this.dryRun.set(index, item);
      return this;
   }

   public CreateOptionsFluent addToDryRun(String... items) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      for(String item : items) {
         this.dryRun.add(item);
      }

      return this;
   }

   public CreateOptionsFluent addAllToDryRun(Collection items) {
      if (this.dryRun == null) {
         this.dryRun = new ArrayList();
      }

      for(String item : items) {
         this.dryRun.add(item);
      }

      return this;
   }

   public CreateOptionsFluent removeFromDryRun(String... items) {
      if (this.dryRun == null) {
         return this;
      } else {
         for(String item : items) {
            this.dryRun.remove(item);
         }

         return this;
      }
   }

   public CreateOptionsFluent removeAllFromDryRun(Collection items) {
      if (this.dryRun == null) {
         return this;
      } else {
         for(String item : items) {
            this.dryRun.remove(item);
         }

         return this;
      }
   }

   public List getDryRun() {
      return this.dryRun;
   }

   public String getDryRun(int index) {
      return (String)this.dryRun.get(index);
   }

   public String getFirstDryRun() {
      return (String)this.dryRun.get(0);
   }

   public String getLastDryRun() {
      return (String)this.dryRun.get(this.dryRun.size() - 1);
   }

   public String getMatchingDryRun(Predicate predicate) {
      for(String item : this.dryRun) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingDryRun(Predicate predicate) {
      for(String item : this.dryRun) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CreateOptionsFluent withDryRun(List dryRun) {
      if (dryRun != null) {
         this.dryRun = new ArrayList();

         for(String item : dryRun) {
            this.addToDryRun(item);
         }
      } else {
         this.dryRun = null;
      }

      return this;
   }

   public CreateOptionsFluent withDryRun(String... dryRun) {
      if (this.dryRun != null) {
         this.dryRun.clear();
         this._visitables.remove("dryRun");
      }

      if (dryRun != null) {
         for(String item : dryRun) {
            this.addToDryRun(item);
         }
      }

      return this;
   }

   public boolean hasDryRun() {
      return this.dryRun != null && !this.dryRun.isEmpty();
   }

   public String getFieldManager() {
      return this.fieldManager;
   }

   public CreateOptionsFluent withFieldManager(String fieldManager) {
      this.fieldManager = fieldManager;
      return this;
   }

   public boolean hasFieldManager() {
      return this.fieldManager != null;
   }

   public String getFieldValidation() {
      return this.fieldValidation;
   }

   public CreateOptionsFluent withFieldValidation(String fieldValidation) {
      this.fieldValidation = fieldValidation;
      return this;
   }

   public boolean hasFieldValidation() {
      return this.fieldValidation != null;
   }

   public String getKind() {
      return this.kind;
   }

   public CreateOptionsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public CreateOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CreateOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CreateOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CreateOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public CreateOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            CreateOptionsFluent that = (CreateOptionsFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.dryRun, that.dryRun)) {
               return false;
            } else if (!Objects.equals(this.fieldManager, that.fieldManager)) {
               return false;
            } else if (!Objects.equals(this.fieldValidation, that.fieldValidation)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.dryRun, this.fieldManager, this.fieldValidation, this.kind, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.dryRun != null && !this.dryRun.isEmpty()) {
         sb.append("dryRun:");
         sb.append(this.dryRun + ",");
      }

      if (this.fieldManager != null) {
         sb.append("fieldManager:");
         sb.append(this.fieldManager + ",");
      }

      if (this.fieldValidation != null) {
         sb.append("fieldValidation:");
         sb.append(this.fieldValidation + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
