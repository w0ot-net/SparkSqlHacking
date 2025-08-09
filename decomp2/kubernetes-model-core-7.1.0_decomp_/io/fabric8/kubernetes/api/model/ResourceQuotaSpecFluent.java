package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ResourceQuotaSpecFluent extends BaseFluent {
   private Map hard;
   private ScopeSelectorBuilder scopeSelector;
   private List scopes = new ArrayList();
   private Map additionalProperties;

   public ResourceQuotaSpecFluent() {
   }

   public ResourceQuotaSpecFluent(ResourceQuotaSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceQuotaSpec instance) {
      instance = instance != null ? instance : new ResourceQuotaSpec();
      if (instance != null) {
         this.withHard(instance.getHard());
         this.withScopeSelector(instance.getScopeSelector());
         this.withScopes(instance.getScopes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ResourceQuotaSpecFluent addToHard(String key, Quantity value) {
      if (this.hard == null && key != null && value != null) {
         this.hard = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.hard.put(key, value);
      }

      return this;
   }

   public ResourceQuotaSpecFluent addToHard(Map map) {
      if (this.hard == null && map != null) {
         this.hard = new LinkedHashMap();
      }

      if (map != null) {
         this.hard.putAll(map);
      }

      return this;
   }

   public ResourceQuotaSpecFluent removeFromHard(String key) {
      if (this.hard == null) {
         return this;
      } else {
         if (key != null && this.hard != null) {
            this.hard.remove(key);
         }

         return this;
      }
   }

   public ResourceQuotaSpecFluent removeFromHard(Map map) {
      if (this.hard == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.hard != null) {
                  this.hard.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getHard() {
      return this.hard;
   }

   public ResourceQuotaSpecFluent withHard(Map hard) {
      if (hard == null) {
         this.hard = null;
      } else {
         this.hard = new LinkedHashMap(hard);
      }

      return this;
   }

   public boolean hasHard() {
      return this.hard != null;
   }

   public ScopeSelector buildScopeSelector() {
      return this.scopeSelector != null ? this.scopeSelector.build() : null;
   }

   public ResourceQuotaSpecFluent withScopeSelector(ScopeSelector scopeSelector) {
      this._visitables.remove("scopeSelector");
      if (scopeSelector != null) {
         this.scopeSelector = new ScopeSelectorBuilder(scopeSelector);
         this._visitables.get("scopeSelector").add(this.scopeSelector);
      } else {
         this.scopeSelector = null;
         this._visitables.get("scopeSelector").remove(this.scopeSelector);
      }

      return this;
   }

   public boolean hasScopeSelector() {
      return this.scopeSelector != null;
   }

   public ScopeSelectorNested withNewScopeSelector() {
      return new ScopeSelectorNested((ScopeSelector)null);
   }

   public ScopeSelectorNested withNewScopeSelectorLike(ScopeSelector item) {
      return new ScopeSelectorNested(item);
   }

   public ScopeSelectorNested editScopeSelector() {
      return this.withNewScopeSelectorLike((ScopeSelector)Optional.ofNullable(this.buildScopeSelector()).orElse((Object)null));
   }

   public ScopeSelectorNested editOrNewScopeSelector() {
      return this.withNewScopeSelectorLike((ScopeSelector)Optional.ofNullable(this.buildScopeSelector()).orElse((new ScopeSelectorBuilder()).build()));
   }

   public ScopeSelectorNested editOrNewScopeSelectorLike(ScopeSelector item) {
      return this.withNewScopeSelectorLike((ScopeSelector)Optional.ofNullable(this.buildScopeSelector()).orElse(item));
   }

   public ResourceQuotaSpecFluent addToScopes(int index, String item) {
      if (this.scopes == null) {
         this.scopes = new ArrayList();
      }

      this.scopes.add(index, item);
      return this;
   }

   public ResourceQuotaSpecFluent setToScopes(int index, String item) {
      if (this.scopes == null) {
         this.scopes = new ArrayList();
      }

      this.scopes.set(index, item);
      return this;
   }

   public ResourceQuotaSpecFluent addToScopes(String... items) {
      if (this.scopes == null) {
         this.scopes = new ArrayList();
      }

      for(String item : items) {
         this.scopes.add(item);
      }

      return this;
   }

   public ResourceQuotaSpecFluent addAllToScopes(Collection items) {
      if (this.scopes == null) {
         this.scopes = new ArrayList();
      }

      for(String item : items) {
         this.scopes.add(item);
      }

      return this;
   }

   public ResourceQuotaSpecFluent removeFromScopes(String... items) {
      if (this.scopes == null) {
         return this;
      } else {
         for(String item : items) {
            this.scopes.remove(item);
         }

         return this;
      }
   }

   public ResourceQuotaSpecFluent removeAllFromScopes(Collection items) {
      if (this.scopes == null) {
         return this;
      } else {
         for(String item : items) {
            this.scopes.remove(item);
         }

         return this;
      }
   }

   public List getScopes() {
      return this.scopes;
   }

   public String getScope(int index) {
      return (String)this.scopes.get(index);
   }

   public String getFirstScope() {
      return (String)this.scopes.get(0);
   }

   public String getLastScope() {
      return (String)this.scopes.get(this.scopes.size() - 1);
   }

   public String getMatchingScope(Predicate predicate) {
      for(String item : this.scopes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingScope(Predicate predicate) {
      for(String item : this.scopes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ResourceQuotaSpecFluent withScopes(List scopes) {
      if (scopes != null) {
         this.scopes = new ArrayList();

         for(String item : scopes) {
            this.addToScopes(item);
         }
      } else {
         this.scopes = null;
      }

      return this;
   }

   public ResourceQuotaSpecFluent withScopes(String... scopes) {
      if (this.scopes != null) {
         this.scopes.clear();
         this._visitables.remove("scopes");
      }

      if (scopes != null) {
         for(String item : scopes) {
            this.addToScopes(item);
         }
      }

      return this;
   }

   public boolean hasScopes() {
      return this.scopes != null && !this.scopes.isEmpty();
   }

   public ResourceQuotaSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceQuotaSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceQuotaSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceQuotaSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceQuotaSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceQuotaSpecFluent that = (ResourceQuotaSpecFluent)o;
            if (!Objects.equals(this.hard, that.hard)) {
               return false;
            } else if (!Objects.equals(this.scopeSelector, that.scopeSelector)) {
               return false;
            } else if (!Objects.equals(this.scopes, that.scopes)) {
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
      return Objects.hash(new Object[]{this.hard, this.scopeSelector, this.scopes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hard != null && !this.hard.isEmpty()) {
         sb.append("hard:");
         sb.append(this.hard + ",");
      }

      if (this.scopeSelector != null) {
         sb.append("scopeSelector:");
         sb.append(this.scopeSelector + ",");
      }

      if (this.scopes != null && !this.scopes.isEmpty()) {
         sb.append("scopes:");
         sb.append(this.scopes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ScopeSelectorNested extends ScopeSelectorFluent implements Nested {
      ScopeSelectorBuilder builder;

      ScopeSelectorNested(ScopeSelector item) {
         this.builder = new ScopeSelectorBuilder(this, item);
      }

      public Object and() {
         return ResourceQuotaSpecFluent.this.withScopeSelector(this.builder.build());
      }

      public Object endScopeSelector() {
         return this.and();
      }
   }
}
