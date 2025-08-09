package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ResourceRuleFluent extends BaseFluent {
   private List apiGroups = new ArrayList();
   private List resourceNames = new ArrayList();
   private List resources = new ArrayList();
   private List verbs = new ArrayList();
   private Map additionalProperties;

   public ResourceRuleFluent() {
   }

   public ResourceRuleFluent(ResourceRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceRule instance) {
      instance = instance != null ? instance : new ResourceRule();
      if (instance != null) {
         this.withApiGroups(instance.getApiGroups());
         this.withResourceNames(instance.getResourceNames());
         this.withResources(instance.getResources());
         this.withVerbs(instance.getVerbs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ResourceRuleFluent addToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.add(index, item);
      return this;
   }

   public ResourceRuleFluent setToApiGroups(int index, String item) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      this.apiGroups.set(index, item);
      return this;
   }

   public ResourceRuleFluent addToApiGroups(String... items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public ResourceRuleFluent addAllToApiGroups(Collection items) {
      if (this.apiGroups == null) {
         this.apiGroups = new ArrayList();
      }

      for(String item : items) {
         this.apiGroups.add(item);
      }

      return this;
   }

   public ResourceRuleFluent removeFromApiGroups(String... items) {
      if (this.apiGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.apiGroups.remove(item);
         }

         return this;
      }
   }

   public ResourceRuleFluent removeAllFromApiGroups(Collection items) {
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

   public ResourceRuleFluent withApiGroups(List apiGroups) {
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

   public ResourceRuleFluent withApiGroups(String... apiGroups) {
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

   public ResourceRuleFluent addToResourceNames(int index, String item) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      this.resourceNames.add(index, item);
      return this;
   }

   public ResourceRuleFluent setToResourceNames(int index, String item) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      this.resourceNames.set(index, item);
      return this;
   }

   public ResourceRuleFluent addToResourceNames(String... items) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      for(String item : items) {
         this.resourceNames.add(item);
      }

      return this;
   }

   public ResourceRuleFluent addAllToResourceNames(Collection items) {
      if (this.resourceNames == null) {
         this.resourceNames = new ArrayList();
      }

      for(String item : items) {
         this.resourceNames.add(item);
      }

      return this;
   }

   public ResourceRuleFluent removeFromResourceNames(String... items) {
      if (this.resourceNames == null) {
         return this;
      } else {
         for(String item : items) {
            this.resourceNames.remove(item);
         }

         return this;
      }
   }

   public ResourceRuleFluent removeAllFromResourceNames(Collection items) {
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

   public ResourceRuleFluent withResourceNames(List resourceNames) {
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

   public ResourceRuleFluent withResourceNames(String... resourceNames) {
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

   public ResourceRuleFluent addToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.add(index, item);
      return this;
   }

   public ResourceRuleFluent setToResources(int index, String item) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      this.resources.set(index, item);
      return this;
   }

   public ResourceRuleFluent addToResources(String... items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public ResourceRuleFluent addAllToResources(Collection items) {
      if (this.resources == null) {
         this.resources = new ArrayList();
      }

      for(String item : items) {
         this.resources.add(item);
      }

      return this;
   }

   public ResourceRuleFluent removeFromResources(String... items) {
      if (this.resources == null) {
         return this;
      } else {
         for(String item : items) {
            this.resources.remove(item);
         }

         return this;
      }
   }

   public ResourceRuleFluent removeAllFromResources(Collection items) {
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

   public ResourceRuleFluent withResources(List resources) {
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

   public ResourceRuleFluent withResources(String... resources) {
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

   public ResourceRuleFluent addToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.add(index, item);
      return this;
   }

   public ResourceRuleFluent setToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.set(index, item);
      return this;
   }

   public ResourceRuleFluent addToVerbs(String... items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public ResourceRuleFluent addAllToVerbs(Collection items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public ResourceRuleFluent removeFromVerbs(String... items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public ResourceRuleFluent removeAllFromVerbs(Collection items) {
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

   public ResourceRuleFluent withVerbs(List verbs) {
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

   public ResourceRuleFluent withVerbs(String... verbs) {
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

   public ResourceRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceRuleFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceRuleFluent that = (ResourceRuleFluent)o;
            if (!Objects.equals(this.apiGroups, that.apiGroups)) {
               return false;
            } else if (!Objects.equals(this.resourceNames, that.resourceNames)) {
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
      return Objects.hash(new Object[]{this.apiGroups, this.resourceNames, this.resources, this.verbs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroups != null && !this.apiGroups.isEmpty()) {
         sb.append("apiGroups:");
         sb.append(this.apiGroups + ",");
      }

      if (this.resourceNames != null && !this.resourceNames.isEmpty()) {
         sb.append("resourceNames:");
         sb.append(this.resourceNames + ",");
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
}
