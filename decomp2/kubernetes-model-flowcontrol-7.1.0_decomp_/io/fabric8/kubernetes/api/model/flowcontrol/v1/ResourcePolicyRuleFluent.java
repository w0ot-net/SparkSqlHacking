package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ResourcePolicyRuleFluent extends BaseFluent {
   private List apiGroups = new ArrayList();
   private Boolean clusterScope;
   private List namespaces = new ArrayList();
   private List resources = new ArrayList();
   private List verbs = new ArrayList();
   private Map additionalProperties;

   public ResourcePolicyRuleFluent() {
   }

   public ResourcePolicyRuleFluent(ResourcePolicyRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourcePolicyRule instance) {
      instance = instance != null ? instance : new ResourcePolicyRule();
      if (instance != null) {
         this.withApiGroups(instance.getApiGroups());
         this.withClusterScope(instance.getClusterScope());
         this.withNamespaces(instance.getNamespaces());
         this.withResources(instance.getResources());
         this.withVerbs(instance.getVerbs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ResourcePolicyRuleFluent addToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.add(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent setToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.set(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent addToApiGroups(String... items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent addAllToApiGroups(Collection items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent removeFromApiGroups(String... items) {
      if (this.apiGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiGroups.remove(item);
         }

         return this;
      }
   }

   public ResourcePolicyRuleFluent removeAllFromApiGroups(Collection items) {
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

   public ResourcePolicyRuleFluent withApiGroups(List apiGroups) {
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

   public ResourcePolicyRuleFluent withApiGroups(String... apiGroups) {
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

   public Boolean getClusterScope() {
      return this.clusterScope;
   }

   public ResourcePolicyRuleFluent withClusterScope(Boolean clusterScope) {
      this.clusterScope = clusterScope;
      return this;
   }

   public boolean hasClusterScope() {
      return this.clusterScope != null;
   }

   public ResourcePolicyRuleFluent addToNamespaces(int index, String item) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      this.namespaces.add(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent setToNamespaces(int index, String item) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      this.namespaces.set(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent addToNamespaces(String... items) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      for(String item : items) {
         this.namespaces.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent addAllToNamespaces(Collection items) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      for(String item : items) {
         this.namespaces.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent removeFromNamespaces(String... items) {
      if (this.namespaces == null) {
         return this;
      } else {
         for(String item : items) {
            this.namespaces.remove(item);
         }

         return this;
      }
   }

   public ResourcePolicyRuleFluent removeAllFromNamespaces(Collection items) {
      if (this.namespaces == null) {
         return this;
      } else {
         for(String item : items) {
            this.namespaces.remove(item);
         }

         return this;
      }
   }

   public List getNamespaces() {
      return this.namespaces;
   }

   public String getNamespace(int index) {
      return (String)this.namespaces.get(index);
   }

   public String getFirstNamespace() {
      return (String)this.namespaces.get(0);
   }

   public String getLastNamespace() {
      return (String)this.namespaces.get(this.namespaces.size() - 1);
   }

   public String getMatchingNamespace(Predicate predicate) {
      for(String item : this.namespaces) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingNamespace(Predicate predicate) {
      for(String item : this.namespaces) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourcePolicyRuleFluent withNamespaces(List namespaces) {
      if (namespaces != null) {
         this.namespaces = new ArrayList();

         for(String item : namespaces) {
            this.addToNamespaces(item);
         }
      } else {
         this.namespaces = null;
      }

      return this;
   }

   public ResourcePolicyRuleFluent withNamespaces(String... namespaces) {
      if (this.namespaces != null) {
         this.namespaces.clear();
         this._visitables.remove("namespaces");
      }

      if (namespaces != null) {
         for(String item : namespaces) {
            this.addToNamespaces(item);
         }
      }

      return this;
   }

   public boolean hasNamespaces() {
      return this.namespaces != null && !this.namespaces.isEmpty();
   }

   public ResourcePolicyRuleFluent addToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.add(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent setToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.set(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent addToResources(String... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent removeFromResources(String... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(String item : items) {
            this.resources.remove(item);
         }

         return this;
      }
   }

   public ResourcePolicyRuleFluent removeAllFromResources(Collection items) {
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

   public ResourcePolicyRuleFluent withResources(List resources) {
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

   public ResourcePolicyRuleFluent withResources(String... resources) {
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

   public ResourcePolicyRuleFluent addToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.add(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent setToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.set(index, item);
      return this;
   }

   public ResourcePolicyRuleFluent addToVerbs(String... items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent addAllToVerbs(Collection items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public ResourcePolicyRuleFluent removeFromVerbs(String... items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public ResourcePolicyRuleFluent removeAllFromVerbs(Collection items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public List getVerbs() {
      return this.verbs;
   }

   public String getVerb(int index) {
      return (String)this.verbs.get(index);
   }

   public String getFirstVerb() {
      return (String)this.verbs.get(0);
   }

   public String getLastVerb() {
      return (String)this.verbs.get(this.verbs.size() - 1);
   }

   public String getMatchingVerb(Predicate predicate) {
      for(String item : this.verbs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVerb(Predicate predicate) {
      for(String item : this.verbs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourcePolicyRuleFluent withVerbs(List verbs) {
      if (verbs != null) {
         this.verbs = new ArrayList();

         for(String item : verbs) {
            this.addToVerbs(item);
         }
      } else {
         this.verbs = null;
      }

      return this;
   }

   public ResourcePolicyRuleFluent withVerbs(String... verbs) {
      if (this.verbs != null) {
         this.verbs.clear();
         this._visitables.remove("verbs");
      }

      if (verbs != null) {
         for(String item : verbs) {
            this.addToVerbs(item);
         }
      }

      return this;
   }

   public boolean hasVerbs() {
      return this.verbs != null && !this.verbs.isEmpty();
   }

   public ResourcePolicyRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourcePolicyRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourcePolicyRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourcePolicyRuleFluent removeFromAdditionalProperties(Map map) {
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

   public ResourcePolicyRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourcePolicyRuleFluent that = (ResourcePolicyRuleFluent)o;
            if (!Objects.equals(this.apiGroups, that.apiGroups)) {
               return false;
            } else if (!Objects.equals(this.clusterScope, that.clusterScope)) {
               return false;
            } else if (!Objects.equals(this.namespaces, that.namespaces)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.verbs, that.verbs)) {
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
      return Objects.hash(new Object[]{this.apiGroups, this.clusterScope, this.namespaces, this.resources, this.verbs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroups != null && !this.apiGroups.isEmpty()) {
         sb.append("apiGroups:");
         sb.append(this.apiGroups + ",");
      }

      if (this.clusterScope != null) {
         sb.append("clusterScope:");
         sb.append(this.clusterScope + ",");
      }

      if (this.namespaces != null && !this.namespaces.isEmpty()) {
         sb.append("namespaces:");
         sb.append(this.namespaces + ",");
      }

      if (this.resources != null && !this.resources.isEmpty()) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.verbs != null && !this.verbs.isEmpty()) {
         sb.append("verbs:");
         sb.append(this.verbs + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ResourcePolicyRuleFluent withClusterScope() {
      return this.withClusterScope(true);
   }
}
