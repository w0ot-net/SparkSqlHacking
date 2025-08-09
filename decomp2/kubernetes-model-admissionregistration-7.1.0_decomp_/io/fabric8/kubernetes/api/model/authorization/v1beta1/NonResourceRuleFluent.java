package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NonResourceRuleFluent extends BaseFluent {
   private List nonResourceURLs = new ArrayList();
   private List verbs = new ArrayList();
   private Map additionalProperties;

   public NonResourceRuleFluent() {
   }

   public NonResourceRuleFluent(NonResourceRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NonResourceRule instance) {
      instance = instance != null ? instance : new NonResourceRule();
      if (instance != null) {
         this.withNonResourceURLs(instance.getNonResourceURLs());
         this.withVerbs(instance.getVerbs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NonResourceRuleFluent addToNonResourceURLs(int index, String item) {
      if (this.nonResourceURLs == null) {
         this.nonResourceURLs = new ArrayList();
      }

      this.nonResourceURLs.add(index, item);
      return this;
   }

   public NonResourceRuleFluent setToNonResourceURLs(int index, String item) {
      if (this.nonResourceURLs == null) {
         this.nonResourceURLs = new ArrayList();
      }

      this.nonResourceURLs.set(index, item);
      return this;
   }

   public NonResourceRuleFluent addToNonResourceURLs(String... items) {
      if (this.nonResourceURLs == null) {
         this.nonResourceURLs = new ArrayList();
      }

      for(String item : items) {
         this.nonResourceURLs.add(item);
      }

      return this;
   }

   public NonResourceRuleFluent addAllToNonResourceURLs(Collection items) {
      if (this.nonResourceURLs == null) {
         this.nonResourceURLs = new ArrayList();
      }

      for(String item : items) {
         this.nonResourceURLs.add(item);
      }

      return this;
   }

   public NonResourceRuleFluent removeFromNonResourceURLs(String... items) {
      if (this.nonResourceURLs == null) {
         return this;
      } else {
         for(String item : items) {
            this.nonResourceURLs.remove(item);
         }

         return this;
      }
   }

   public NonResourceRuleFluent removeAllFromNonResourceURLs(Collection items) {
      if (this.nonResourceURLs == null) {
         return this;
      } else {
         for(String item : items) {
            this.nonResourceURLs.remove(item);
         }

         return this;
      }
   }

   public List getNonResourceURLs() {
      return this.nonResourceURLs;
   }

   public String getNonResourceURL(int index) {
      return (String)this.nonResourceURLs.get(index);
   }

   public String getFirstNonResourceURL() {
      return (String)this.nonResourceURLs.get(0);
   }

   public String getLastNonResourceURL() {
      return (String)this.nonResourceURLs.get(this.nonResourceURLs.size() - 1);
   }

   public String getMatchingNonResourceURL(Predicate predicate) {
      for(String item : this.nonResourceURLs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingNonResourceURL(Predicate predicate) {
      for(String item : this.nonResourceURLs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NonResourceRuleFluent withNonResourceURLs(List nonResourceURLs) {
      if (nonResourceURLs != null) {
         this.nonResourceURLs = new ArrayList();

         for(String item : nonResourceURLs) {
            this.addToNonResourceURLs(item);
         }
      } else {
         this.nonResourceURLs = null;
      }

      return this;
   }

   public NonResourceRuleFluent withNonResourceURLs(String... nonResourceURLs) {
      if (this.nonResourceURLs != null) {
         this.nonResourceURLs.clear();
         this._visitables.remove("nonResourceURLs");
      }

      if (nonResourceURLs != null) {
         for(String item : nonResourceURLs) {
            this.addToNonResourceURLs(item);
         }
      }

      return this;
   }

   public boolean hasNonResourceURLs() {
      return this.nonResourceURLs != null && !this.nonResourceURLs.isEmpty();
   }

   public NonResourceRuleFluent addToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.add(index, item);
      return this;
   }

   public NonResourceRuleFluent setToVerbs(int index, String item) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      this.verbs.set(index, item);
      return this;
   }

   public NonResourceRuleFluent addToVerbs(String... items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public NonResourceRuleFluent addAllToVerbs(Collection items) {
      if (this.verbs == null) {
         this.verbs = new ArrayList();
      }

      for(String item : items) {
         this.verbs.add(item);
      }

      return this;
   }

   public NonResourceRuleFluent removeFromVerbs(String... items) {
      if (this.verbs == null) {
         return this;
      } else {
         for(String item : items) {
            this.verbs.remove(item);
         }

         return this;
      }
   }

   public NonResourceRuleFluent removeAllFromVerbs(Collection items) {
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

   public NonResourceRuleFluent withVerbs(List verbs) {
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

   public NonResourceRuleFluent withVerbs(String... verbs) {
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

   public NonResourceRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NonResourceRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NonResourceRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NonResourceRuleFluent removeFromAdditionalProperties(Map map) {
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

   public NonResourceRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            NonResourceRuleFluent that = (NonResourceRuleFluent)o;
            if (!Objects.equals(this.nonResourceURLs, that.nonResourceURLs)) {
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
      return Objects.hash(new Object[]{this.nonResourceURLs, this.verbs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nonResourceURLs != null && !this.nonResourceURLs.isEmpty()) {
         sb.append("nonResourceURLs:");
         sb.append(this.nonResourceURLs + ",");
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
