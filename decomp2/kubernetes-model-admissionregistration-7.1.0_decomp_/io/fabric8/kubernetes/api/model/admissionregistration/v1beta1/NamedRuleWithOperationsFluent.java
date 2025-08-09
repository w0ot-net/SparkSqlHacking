package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NamedRuleWithOperationsFluent extends BaseFluent {
   private List apiGroups = new ArrayList();
   private List apiVersions = new ArrayList();
   private List operations = new ArrayList();
   private List resourceNames = new ArrayList();
   private List resources = new ArrayList();
   private String scope;
   private Map additionalProperties;

   public NamedRuleWithOperationsFluent() {
   }

   public NamedRuleWithOperationsFluent(NamedRuleWithOperations instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedRuleWithOperations instance) {
      instance = instance != null ? instance : new NamedRuleWithOperations();
      if (instance != null) {
         this.withApiGroups(instance.getApiGroups());
         this.withApiVersions(instance.getApiVersions());
         this.withOperations(instance.getOperations());
         this.withResourceNames(instance.getResourceNames());
         this.withResources(instance.getResources());
         this.withScope(instance.getScope());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedRuleWithOperationsFluent addToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.add(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent setToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.set(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent addToApiGroups(String... items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addAllToApiGroups(Collection items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromApiGroups(String... items) {
      if (this.apiGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiGroups.remove(item);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeAllFromApiGroups(Collection items) {
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

   public NamedRuleWithOperationsFluent withApiGroups(List apiGroups) {
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

   public NamedRuleWithOperationsFluent withApiGroups(String... apiGroups) {
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

   public NamedRuleWithOperationsFluent addToApiVersions(int index, String item) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      this.apiVersions.add(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent setToApiVersions(int index, String item) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      this.apiVersions.set(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent addToApiVersions(String... items) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      for(String item : items) {
         this.apiVersions.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addAllToApiVersions(Collection items) {
      if (this.apiVersions == null) {
         this.apiVersions = new ArrayList();
      }

      for(String item : items) {
         this.apiVersions.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromApiVersions(String... items) {
      if (this.apiVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiVersions.remove(item);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeAllFromApiVersions(Collection items) {
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

   public NamedRuleWithOperationsFluent withApiVersions(List apiVersions) {
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

   public NamedRuleWithOperationsFluent withApiVersions(String... apiVersions) {
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

   public NamedRuleWithOperationsFluent addToOperations(int index, String item) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      this.operations.add(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent setToOperations(int index, String item) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      this.operations.set(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent addToOperations(String... items) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      for(String item : items) {
         this.operations.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addAllToOperations(Collection items) {
      if (this.operations == null) {
         this.operations = new ArrayList();
      }

      for(String item : items) {
         this.operations.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromOperations(String... items) {
      if (this.operations == null) {
         return this;
      } else {
         for(String item : items) {
            this.operations.remove(item);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeAllFromOperations(Collection items) {
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

   public NamedRuleWithOperationsFluent withOperations(List operations) {
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

   public NamedRuleWithOperationsFluent withOperations(String... operations) {
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

   public NamedRuleWithOperationsFluent addToResourceNames(int index, String item) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      this.resourceNames.add(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent setToResourceNames(int index, String item) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      this.resourceNames.set(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent addToResourceNames(String... items) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      for(String item : items) {
         this.resourceNames.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addAllToResourceNames(Collection items) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      for(String item : items) {
         this.resourceNames.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromResourceNames(String... items) {
      if (this.resourceNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.resourceNames.remove(item);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeAllFromResourceNames(Collection items) {
      if (this.resourceNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.resourceNames.remove(item);
         }

         return this;
      }
   }

   public List getResourceNames() {
      return this.resourceNames;
   }

   public String getResourceName(int index) {
      return (String)this.resourceNames.get(index);
   }

   public String getFirstResourceName() {
      return (String)this.resourceNames.get(0);
   }

   public String getLastResourceName() {
      return (String)this.resourceNames.get(this.resourceNames.size() - 1);
   }

   public String getMatchingResourceName(Predicate predicate) {
      for(String item : this.resourceNames) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingResourceName(Predicate predicate) {
      for(String item : this.resourceNames) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamedRuleWithOperationsFluent withResourceNames(List resourceNames) {
      if (resourceNames != null) {
         this.resourceNames = new ArrayList();

         for(String item : resourceNames) {
            this.addToResourceNames(item);
         }
      } else {
         this.resourceNames = null;
      }

      return this;
   }

   public NamedRuleWithOperationsFluent withResourceNames(String... resourceNames) {
      if (this.resourceNames != null) {
         this.resourceNames.clear();
         this._visitables.remove("resourceNames");
      }

      if (resourceNames != null) {
         for(String item : resourceNames) {
            this.addToResourceNames(item);
         }
      }

      return this;
   }

   public boolean hasResourceNames() {
      return this.resourceNames != null && !this.resourceNames.isEmpty();
   }

   public NamedRuleWithOperationsFluent addToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.add(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent setToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.set(index, item);
      return this;
   }

   public NamedRuleWithOperationsFluent addToResources(String... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromResources(String... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(String item : items) {
            this.resources.remove(item);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeAllFromResources(Collection items) {
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

   public NamedRuleWithOperationsFluent withResources(List resources) {
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

   public NamedRuleWithOperationsFluent withResources(String... resources) {
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

   public NamedRuleWithOperationsFluent withScope(String scope) {
      this.scope = scope;
      return this;
   }

   public boolean hasScope() {
      return this.scope != null;
   }

   public NamedRuleWithOperationsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedRuleWithOperationsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedRuleWithOperationsFluent removeFromAdditionalProperties(Map map) {
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

   public NamedRuleWithOperationsFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedRuleWithOperationsFluent that = (NamedRuleWithOperationsFluent)o;
            if (!Objects.equals(this.apiGroups, that.apiGroups)) {
               return false;
            } else if (!Objects.equals(this.apiVersions, that.apiVersions)) {
               return false;
            } else if (!Objects.equals(this.operations, that.operations)) {
               return false;
            } else if (!Objects.equals(this.resourceNames, that.resourceNames)) {
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
      return Objects.hash(new Object[]{this.apiGroups, this.apiVersions, this.operations, this.resourceNames, this.resources, this.scope, this.additionalProperties, super.hashCode()});
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

      if (this.resourceNames != null && !this.resourceNames.isEmpty()) {
         sb.append("resourceNames:");
         sb.append(this.resourceNames + ",");
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
