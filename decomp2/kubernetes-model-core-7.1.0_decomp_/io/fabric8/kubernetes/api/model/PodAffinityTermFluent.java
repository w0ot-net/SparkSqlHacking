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

public class PodAffinityTermFluent extends BaseFluent {
   private LabelSelectorBuilder labelSelector;
   private List matchLabelKeys = new ArrayList();
   private List mismatchLabelKeys = new ArrayList();
   private LabelSelectorBuilder namespaceSelector;
   private List namespaces = new ArrayList();
   private String topologyKey;
   private Map additionalProperties;

   public PodAffinityTermFluent() {
   }

   public PodAffinityTermFluent(PodAffinityTerm instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodAffinityTerm instance) {
      instance = instance != null ? instance : new PodAffinityTerm();
      if (instance != null) {
         this.withLabelSelector(instance.getLabelSelector());
         this.withMatchLabelKeys(instance.getMatchLabelKeys());
         this.withMismatchLabelKeys(instance.getMismatchLabelKeys());
         this.withNamespaceSelector(instance.getNamespaceSelector());
         this.withNamespaces(instance.getNamespaces());
         this.withTopologyKey(instance.getTopologyKey());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LabelSelector buildLabelSelector() {
      return this.labelSelector != null ? this.labelSelector.build() : null;
   }

   public PodAffinityTermFluent withLabelSelector(LabelSelector labelSelector) {
      this._visitables.remove("labelSelector");
      if (labelSelector != null) {
         this.labelSelector = new LabelSelectorBuilder(labelSelector);
         this._visitables.get("labelSelector").add(this.labelSelector);
      } else {
         this.labelSelector = null;
         this._visitables.get("labelSelector").remove(this.labelSelector);
      }

      return this;
   }

   public boolean hasLabelSelector() {
      return this.labelSelector != null;
   }

   public LabelSelectorNested withNewLabelSelector() {
      return new LabelSelectorNested((LabelSelector)null);
   }

   public LabelSelectorNested withNewLabelSelectorLike(LabelSelector item) {
      return new LabelSelectorNested(item);
   }

   public LabelSelectorNested editLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((Object)null));
   }

   public LabelSelectorNested editOrNewLabelSelector() {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public LabelSelectorNested editOrNewLabelSelectorLike(LabelSelector item) {
      return this.withNewLabelSelectorLike((LabelSelector)Optional.ofNullable(this.buildLabelSelector()).orElse(item));
   }

   public PodAffinityTermFluent addToMatchLabelKeys(int index, String item) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      this.matchLabelKeys.add(index, item);
      return this;
   }

   public PodAffinityTermFluent setToMatchLabelKeys(int index, String item) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      this.matchLabelKeys.set(index, item);
      return this;
   }

   public PodAffinityTermFluent addToMatchLabelKeys(String... items) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.matchLabelKeys.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent addAllToMatchLabelKeys(Collection items) {
      if (this.matchLabelKeys == null) {
         this.matchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.matchLabelKeys.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent removeFromMatchLabelKeys(String... items) {
      if (this.matchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.matchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public PodAffinityTermFluent removeAllFromMatchLabelKeys(Collection items) {
      if (this.matchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.matchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public List getMatchLabelKeys() {
      return this.matchLabelKeys;
   }

   public String getMatchLabelKey(int index) {
      return (String)this.matchLabelKeys.get(index);
   }

   public String getFirstMatchLabelKey() {
      return (String)this.matchLabelKeys.get(0);
   }

   public String getLastMatchLabelKey() {
      return (String)this.matchLabelKeys.get(this.matchLabelKeys.size() - 1);
   }

   public String getMatchingMatchLabelKey(Predicate predicate) {
      for(String item : this.matchLabelKeys) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMatchLabelKey(Predicate predicate) {
      for(String item : this.matchLabelKeys) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodAffinityTermFluent withMatchLabelKeys(List matchLabelKeys) {
      if (matchLabelKeys != null) {
         this.matchLabelKeys = new ArrayList();

         for(String item : matchLabelKeys) {
            this.addToMatchLabelKeys(item);
         }
      } else {
         this.matchLabelKeys = null;
      }

      return this;
   }

   public PodAffinityTermFluent withMatchLabelKeys(String... matchLabelKeys) {
      if (this.matchLabelKeys != null) {
         this.matchLabelKeys.clear();
         this._visitables.remove("matchLabelKeys");
      }

      if (matchLabelKeys != null) {
         for(String item : matchLabelKeys) {
            this.addToMatchLabelKeys(item);
         }
      }

      return this;
   }

   public boolean hasMatchLabelKeys() {
      return this.matchLabelKeys != null && !this.matchLabelKeys.isEmpty();
   }

   public PodAffinityTermFluent addToMismatchLabelKeys(int index, String item) {
      if (this.mismatchLabelKeys == null) {
         this.mismatchLabelKeys = new ArrayList();
      }

      this.mismatchLabelKeys.add(index, item);
      return this;
   }

   public PodAffinityTermFluent setToMismatchLabelKeys(int index, String item) {
      if (this.mismatchLabelKeys == null) {
         this.mismatchLabelKeys = new ArrayList();
      }

      this.mismatchLabelKeys.set(index, item);
      return this;
   }

   public PodAffinityTermFluent addToMismatchLabelKeys(String... items) {
      if (this.mismatchLabelKeys == null) {
         this.mismatchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.mismatchLabelKeys.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent addAllToMismatchLabelKeys(Collection items) {
      if (this.mismatchLabelKeys == null) {
         this.mismatchLabelKeys = new ArrayList();
      }

      for(String item : items) {
         this.mismatchLabelKeys.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent removeFromMismatchLabelKeys(String... items) {
      if (this.mismatchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.mismatchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public PodAffinityTermFluent removeAllFromMismatchLabelKeys(Collection items) {
      if (this.mismatchLabelKeys == null) {
         return this;
      } else {
         for(String item : items) {
            this.mismatchLabelKeys.remove(item);
         }

         return this;
      }
   }

   public List getMismatchLabelKeys() {
      return this.mismatchLabelKeys;
   }

   public String getMismatchLabelKey(int index) {
      return (String)this.mismatchLabelKeys.get(index);
   }

   public String getFirstMismatchLabelKey() {
      return (String)this.mismatchLabelKeys.get(0);
   }

   public String getLastMismatchLabelKey() {
      return (String)this.mismatchLabelKeys.get(this.mismatchLabelKeys.size() - 1);
   }

   public String getMatchingMismatchLabelKey(Predicate predicate) {
      for(String item : this.mismatchLabelKeys) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingMismatchLabelKey(Predicate predicate) {
      for(String item : this.mismatchLabelKeys) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodAffinityTermFluent withMismatchLabelKeys(List mismatchLabelKeys) {
      if (mismatchLabelKeys != null) {
         this.mismatchLabelKeys = new ArrayList();

         for(String item : mismatchLabelKeys) {
            this.addToMismatchLabelKeys(item);
         }
      } else {
         this.mismatchLabelKeys = null;
      }

      return this;
   }

   public PodAffinityTermFluent withMismatchLabelKeys(String... mismatchLabelKeys) {
      if (this.mismatchLabelKeys != null) {
         this.mismatchLabelKeys.clear();
         this._visitables.remove("mismatchLabelKeys");
      }

      if (mismatchLabelKeys != null) {
         for(String item : mismatchLabelKeys) {
            this.addToMismatchLabelKeys(item);
         }
      }

      return this;
   }

   public boolean hasMismatchLabelKeys() {
      return this.mismatchLabelKeys != null && !this.mismatchLabelKeys.isEmpty();
   }

   public LabelSelector buildNamespaceSelector() {
      return this.namespaceSelector != null ? this.namespaceSelector.build() : null;
   }

   public PodAffinityTermFluent withNamespaceSelector(LabelSelector namespaceSelector) {
      this._visitables.remove("namespaceSelector");
      if (namespaceSelector != null) {
         this.namespaceSelector = new LabelSelectorBuilder(namespaceSelector);
         this._visitables.get("namespaceSelector").add(this.namespaceSelector);
      } else {
         this.namespaceSelector = null;
         this._visitables.get("namespaceSelector").remove(this.namespaceSelector);
      }

      return this;
   }

   public boolean hasNamespaceSelector() {
      return this.namespaceSelector != null;
   }

   public NamespaceSelectorNested withNewNamespaceSelector() {
      return new NamespaceSelectorNested((LabelSelector)null);
   }

   public NamespaceSelectorNested withNewNamespaceSelectorLike(LabelSelector item) {
      return new NamespaceSelectorNested(item);
   }

   public NamespaceSelectorNested editNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((Object)null));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelectorLike(LabelSelector item) {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse(item));
   }

   public PodAffinityTermFluent addToNamespaces(int index, String item) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      this.namespaces.add(index, item);
      return this;
   }

   public PodAffinityTermFluent setToNamespaces(int index, String item) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      this.namespaces.set(index, item);
      return this;
   }

   public PodAffinityTermFluent addToNamespaces(String... items) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      for(String item : items) {
         this.namespaces.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent addAllToNamespaces(Collection items) {
      if (this.namespaces == null) {
         this.namespaces = new ArrayList();
      }

      for(String item : items) {
         this.namespaces.add(item);
      }

      return this;
   }

   public PodAffinityTermFluent removeFromNamespaces(String... items) {
      if (this.namespaces == null) {
         return this;
      } else {
         for(String item : items) {
            this.namespaces.remove(item);
         }

         return this;
      }
   }

   public PodAffinityTermFluent removeAllFromNamespaces(Collection items) {
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

   public PodAffinityTermFluent withNamespaces(List namespaces) {
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

   public PodAffinityTermFluent withNamespaces(String... namespaces) {
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

   public String getTopologyKey() {
      return this.topologyKey;
   }

   public PodAffinityTermFluent withTopologyKey(String topologyKey) {
      this.topologyKey = topologyKey;
      return this;
   }

   public boolean hasTopologyKey() {
      return this.topologyKey != null;
   }

   public PodAffinityTermFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodAffinityTermFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodAffinityTermFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodAffinityTermFluent removeFromAdditionalProperties(Map map) {
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

   public PodAffinityTermFluent withAdditionalProperties(Map additionalProperties) {
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
            PodAffinityTermFluent that = (PodAffinityTermFluent)o;
            if (!Objects.equals(this.labelSelector, that.labelSelector)) {
               return false;
            } else if (!Objects.equals(this.matchLabelKeys, that.matchLabelKeys)) {
               return false;
            } else if (!Objects.equals(this.mismatchLabelKeys, that.mismatchLabelKeys)) {
               return false;
            } else if (!Objects.equals(this.namespaceSelector, that.namespaceSelector)) {
               return false;
            } else if (!Objects.equals(this.namespaces, that.namespaces)) {
               return false;
            } else if (!Objects.equals(this.topologyKey, that.topologyKey)) {
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
      return Objects.hash(new Object[]{this.labelSelector, this.matchLabelKeys, this.mismatchLabelKeys, this.namespaceSelector, this.namespaces, this.topologyKey, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.labelSelector != null) {
         sb.append("labelSelector:");
         sb.append(this.labelSelector + ",");
      }

      if (this.matchLabelKeys != null && !this.matchLabelKeys.isEmpty()) {
         sb.append("matchLabelKeys:");
         sb.append(this.matchLabelKeys + ",");
      }

      if (this.mismatchLabelKeys != null && !this.mismatchLabelKeys.isEmpty()) {
         sb.append("mismatchLabelKeys:");
         sb.append(this.mismatchLabelKeys + ",");
      }

      if (this.namespaceSelector != null) {
         sb.append("namespaceSelector:");
         sb.append(this.namespaceSelector + ",");
      }

      if (this.namespaces != null && !this.namespaces.isEmpty()) {
         sb.append("namespaces:");
         sb.append(this.namespaces + ",");
      }

      if (this.topologyKey != null) {
         sb.append("topologyKey:");
         sb.append(this.topologyKey + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LabelSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      LabelSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return PodAffinityTermFluent.this.withLabelSelector(this.builder.build());
      }

      public Object endLabelSelector() {
         return this.and();
      }
   }

   public class NamespaceSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      NamespaceSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return PodAffinityTermFluent.this.withNamespaceSelector(this.builder.build());
      }

      public Object endNamespaceSelector() {
         return this.and();
      }
   }
}
