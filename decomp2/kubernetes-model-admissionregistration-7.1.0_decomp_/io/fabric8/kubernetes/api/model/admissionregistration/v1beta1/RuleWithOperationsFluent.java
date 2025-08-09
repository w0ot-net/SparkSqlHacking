package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class RuleWithOperationsFluent extends BaseFluent {
   private List apiGroups = new ArrayList();
   private List apiVersions = new ArrayList();
   private List operations = new ArrayList();
   private List resources = new ArrayList();
   private String scope;
   private Map additionalProperties;

   public RuleWithOperationsFluent() {
   }

   public RuleWithOperationsFluent(RuleWithOperations instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RuleWithOperations instance) {
      instance = instance != null ? instance : new RuleWithOperations();
      if (instance != null) {
         this.withApiGroups(instance.getApiGroups());
         this.withApiVersions(instance.getApiVersions());
         this.withOperations(instance.getOperations());
         this.withResources(instance.getResources());
         this.withScope(instance.getScope());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RuleWithOperationsFluent addToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.add(index, item);
      return this;
   }

   public RuleWithOperationsFluent setToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.set(index, item);
      return this;
   }

   public RuleWithOperationsFluent addToApiGroups(String... items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent addAllToApiGroups(Collection items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent removeFromApiGroups(String... items) {
      if (this.apiGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiGroups.remove(item);
         }

         return this;
      }
   }

   public RuleWithOperationsFluent removeAllFromApiGroups(Collection items) {
      if (this.apiGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiGroups.remove(item);
         }

         return this;
      }
   }

   public List getApiGroups() {
      return this.apiGroups;
   }

   public String getApiGroup(int index) {
      return (String)this.apiGroups.get(index);
   }

   public String getFirstApiGroup() {
      return (String)this.apiGroups.get(0);
   }

   public String getLastApiGroup() {
      return (String)this.apiGroups.get(this.apiGroups.size() - 1);
   }

   public String getMatchingApiGroup(Predicate predicate) {
      for(String item : this.apiGroups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingApiGroup(Predicate predicate) {
      for(String item : this.apiGroups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RuleWithOperationsFluent withApiGroups(List apiGroups) {
      if (apiGroups != null) {
         this.apiGroups = new ArrayList();

         for(String item : apiGroups) {
            this.addToApiGroups(item);
         }
      } else {
         this.apiGroups = null;
      }

      return this;
   }

   public RuleWithOperationsFluent withApiGroups(String... apiGroups) {
      if (this.apiGroups != null) {
         this.apiGroups.clear();
         this._visitables.remove("apiGroups");
      }

      if (apiGroups != null) {
         for(String item : apiGroups) {
            this.addToApiGroups(item);
         }
      }

      return this;
   }

   public boolean hasApiGroups() {
      return this.apiGroups != null && !this.apiGroups.isEmpty();
   }

   public RuleWithOperationsFluent addToApiVersions(int index, String item) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      this.apiVersions.add(index, item);
      return this;
   }

   public RuleWithOperationsFluent setToApiVersions(int index, String item) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      this.apiVersions.set(index, item);
      return this;
   }

   public RuleWithOperationsFluent addToApiVersions(String... items) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      for(String item : items) {
         this.apiVersions.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent addAllToApiVersions(Collection items) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      for(String item : items) {
         this.apiVersions.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent removeFromApiVersions(String... items) {
      if (this.apiVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiVersions.remove(item);
         }

         return this;
      }
   }

   public RuleWithOperationsFluent removeAllFromApiVersions(Collection items) {
      if (this.apiVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiVersions.remove(item);
         }

         return this;
      }
   }

   public List getApiVersions() {
      return this.apiVersions;
   }

   public String getApiVersion(int index) {
      return (String)this.apiVersions.get(index);
   }

   public String getFirstApiVersion() {
      return (String)this.apiVersions.get(0);
   }

   public String getLastApiVersion() {
      return (String)this.apiVersions.get(this.apiVersions.size() - 1);
   }

   public String getMatchingApiVersion(Predicate predicate) {
      for(String item : this.apiVersions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingApiVersion(Predicate predicate) {
      for(String item : this.apiVersions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RuleWithOperationsFluent withApiVersions(List apiVersions) {
      if (apiVersions != null) {
         this.apiVersions = new ArrayList();

         for(String item : apiVersions) {
            this.addToApiVersions(item);
         }
      } else {
         this.apiVersions = null;
      }

      return this;
   }

   public RuleWithOperationsFluent withApiVersions(String... apiVersions) {
      if (this.apiVersions != null) {
         this.apiVersions.clear();
         this._visitables.remove("apiVersions");
      }

      if (apiVersions != null) {
         for(String item : apiVersions) {
            this.addToApiVersions(item);
         }
      }

      return this;
   }

   public boolean hasApiVersions() {
      return this.apiVersions != null && !this.apiVersions.isEmpty();
   }

   public RuleWithOperationsFluent addToOperations(int index, String item) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      this.operations.add(index, item);
      return this;
   }

   public RuleWithOperationsFluent setToOperations(int index, String item) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      this.operations.set(index, item);
      return this;
   }

   public RuleWithOperationsFluent addToOperations(String... items) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      for(String item : items) {
         this.operations.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent addAllToOperations(Collection items) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      for(String item : items) {
         this.operations.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent removeFromOperations(String... items) {
      if (this.operations == null) {
         return this;
      } else {
         for(String item : items) {
            this.operations.remove(item);
         }

         return this;
      }
   }

   public RuleWithOperationsFluent removeAllFromOperations(Collection items) {
      if (this.operations == null) {
         return this;
      } else {
         for(String item : items) {
            this.operations.remove(item);
         }

         return this;
      }
   }

   public List getOperations() {
      return this.operations;
   }

   public String getOperation(int index) {
      return (String)this.operations.get(index);
   }

   public String getFirstOperation() {
      return (String)this.operations.get(0);
   }

   public String getLastOperation() {
      return (String)this.operations.get(this.operations.size() - 1);
   }

   public String getMatchingOperation(Predicate predicate) {
      for(String item : this.operations) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingOperation(Predicate predicate) {
      for(String item : this.operations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RuleWithOperationsFluent withOperations(List operations) {
      if (operations != null) {
         this.operations = new ArrayList();

         for(String item : operations) {
            this.addToOperations(item);
         }
      } else {
         this.operations = null;
      }

      return this;
   }

   public RuleWithOperationsFluent withOperations(String... operations) {
      if (this.operations != null) {
         this.operations.clear();
         this._visitables.remove("operations");
      }

      if (operations != null) {
         for(String item : operations) {
            this.addToOperations(item);
         }
      }

      return this;
   }

   public boolean hasOperations() {
      return this.operations != null && !this.operations.isEmpty();
   }

   public RuleWithOperationsFluent addToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.add(index, item);
      return this;
   }

   public RuleWithOperationsFluent setToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.set(index, item);
      return this;
   }

   public RuleWithOperationsFluent addToResources(String... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public RuleWithOperationsFluent removeFromResources(String... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(String item : items) {
            this.resources.remove(item);
         }

         return this;
      }
   }

   public RuleWithOperationsFluent removeAllFromResources(Collection items) {
      if (this.resources == null) {
         return this;
      } else {
         for(String item : items) {
            this.resources.remove(item);
         }

         return this;
      }
   }

   public List getResources() {
      return this.resources;
   }

   public String getResource(int index) {
      return (String)this.resources.get(index);
   }

   public String getFirstResource() {
      return (String)this.resources.get(0);
   }

   public String getLastResource() {
      return (String)this.resources.get(this.resources.size() - 1);
   }

   public String getMatchingResource(Predicate predicate) {
      for(String item : this.resources) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingResource(Predicate predicate) {
      for(String item : this.resources) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RuleWithOperationsFluent withResources(List resources) {
      if (resources != null) {
         this.resources = new ArrayList();

         for(String item : resources) {
            this.addToResources(item);
         }
      } else {
         this.resources = null;
      }

      return this;
   }

   public RuleWithOperationsFluent withResources(String... resources) {
      if (this.resources != null) {
         this.resources.clear();
         this._visitables.remove("resources");
      }

      if (resources != null) {
         for(String item : resources) {
            this.addToResources(item);
         }
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null && !this.resources.isEmpty();
   }

   public String getScope() {
      return this.scope;
   }

   public RuleWithOperationsFluent withScope(String scope) {
      this.scope = scope;
      return this;
   }

   public boolean hasScope() {
      return this.scope != null;
   }

   public RuleWithOperationsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RuleWithOperationsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RuleWithOperationsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RuleWithOperationsFluent removeFromAdditionalProperties(Map map) {
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

   public RuleWithOperationsFluent withAdditionalProperties(Map additionalProperties) {
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
            RuleWithOperationsFluent that = (RuleWithOperationsFluent)o;
            if (!Objects.equals(this.apiGroups, that.apiGroups)) {
               return false;
            } else if (!Objects.equals(this.apiVersions, that.apiVersions)) {
               return false;
            } else if (!Objects.equals(this.operations, that.operations)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.scope, that.scope)) {
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
      return Objects.hash(new Object[]{this.apiGroups, this.apiVersions, this.operations, this.resources, this.scope, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroups != null && !this.apiGroups.isEmpty()) {
         sb.append("apiGroups:");
         sb.append(this.apiGroups + ",");
      }

      if (this.apiVersions != null && !this.apiVersions.isEmpty()) {
         sb.append("apiVersions:");
         sb.append(this.apiVersions + ",");
      }

      if (this.operations != null && !this.operations.isEmpty()) {
         sb.append("operations:");
         sb.append(this.operations + ",");
      }

      if (this.resources != null && !this.resources.isEmpty()) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.scope != null) {
         sb.append("scope:");
         sb.append(this.scope + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
