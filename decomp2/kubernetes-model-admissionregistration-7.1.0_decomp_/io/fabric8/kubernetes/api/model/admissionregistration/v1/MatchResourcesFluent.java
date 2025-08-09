package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class MatchResourcesFluent extends BaseFluent {
   private ArrayList excludeResourceRules = new ArrayList();
   private String matchPolicy;
   private LabelSelectorBuilder namespaceSelector;
   private LabelSelectorBuilder objectSelector;
   private ArrayList resourceRules = new ArrayList();
   private Map additionalProperties;

   public MatchResourcesFluent() {
   }

   public MatchResourcesFluent(MatchResources instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MatchResources instance) {
      instance = instance != null ? instance : new MatchResources();
      if (instance != null) {
         this.withExcludeResourceRules(instance.getExcludeResourceRules());
         this.withMatchPolicy(instance.getMatchPolicy());
         this.withNamespaceSelector(instance.getNamespaceSelector());
         this.withObjectSelector(instance.getObjectSelector());
         this.withResourceRules(instance.getResourceRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MatchResourcesFluent addToExcludeResourceRules(int index, NamedRuleWithOperations item) {
      if (this.excludeResourceRules == null) {
         this.excludeResourceRules = new ArrayList();
      }

      NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.excludeResourceRules.size()) {
         this._visitables.get("excludeResourceRules").add(index, builder);
         this.excludeResourceRules.add(index, builder);
      } else {
         this._visitables.get("excludeResourceRules").add(builder);
         this.excludeResourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent setToExcludeResourceRules(int index, NamedRuleWithOperations item) {
      if (this.excludeResourceRules == null) {
         this.excludeResourceRules = new ArrayList();
      }

      NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.excludeResourceRules.size()) {
         this._visitables.get("excludeResourceRules").set(index, builder);
         this.excludeResourceRules.set(index, builder);
      } else {
         this._visitables.get("excludeResourceRules").add(builder);
         this.excludeResourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent addToExcludeResourceRules(NamedRuleWithOperations... items) {
      if (this.excludeResourceRules == null) {
         this.excludeResourceRules = new ArrayList();
      }

      for(NamedRuleWithOperations item : items) {
         NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
         this._visitables.get("excludeResourceRules").add(builder);
         this.excludeResourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent addAllToExcludeResourceRules(Collection items) {
      if (this.excludeResourceRules == null) {
         this.excludeResourceRules = new ArrayList();
      }

      for(NamedRuleWithOperations item : items) {
         NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
         this._visitables.get("excludeResourceRules").add(builder);
         this.excludeResourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent removeFromExcludeResourceRules(NamedRuleWithOperations... items) {
      if (this.excludeResourceRules == null) {
         return this;
      } else {
         for(NamedRuleWithOperations item : items) {
            NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
            this._visitables.get("excludeResourceRules").remove(builder);
            this.excludeResourceRules.remove(builder);
         }

         return this;
      }
   }

   public MatchResourcesFluent removeAllFromExcludeResourceRules(Collection items) {
      if (this.excludeResourceRules == null) {
         return this;
      } else {
         for(NamedRuleWithOperations item : items) {
            NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
            this._visitables.get("excludeResourceRules").remove(builder);
            this.excludeResourceRules.remove(builder);
         }

         return this;
      }
   }

   public MatchResourcesFluent removeMatchingFromExcludeResourceRules(Predicate predicate) {
      if (this.excludeResourceRules == null) {
         return this;
      } else {
         Iterator<NamedRuleWithOperationsBuilder> each = this.excludeResourceRules.iterator();
         List visitables = this._visitables.get("excludeResourceRules");

         while(each.hasNext()) {
            NamedRuleWithOperationsBuilder builder = (NamedRuleWithOperationsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildExcludeResourceRules() {
      return this.excludeResourceRules != null ? build(this.excludeResourceRules) : null;
   }

   public NamedRuleWithOperations buildExcludeResourceRule(int index) {
      return ((NamedRuleWithOperationsBuilder)this.excludeResourceRules.get(index)).build();
   }

   public NamedRuleWithOperations buildFirstExcludeResourceRule() {
      return ((NamedRuleWithOperationsBuilder)this.excludeResourceRules.get(0)).build();
   }

   public NamedRuleWithOperations buildLastExcludeResourceRule() {
      return ((NamedRuleWithOperationsBuilder)this.excludeResourceRules.get(this.excludeResourceRules.size() - 1)).build();
   }

   public NamedRuleWithOperations buildMatchingExcludeResourceRule(Predicate predicate) {
      for(NamedRuleWithOperationsBuilder item : this.excludeResourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingExcludeResourceRule(Predicate predicate) {
      for(NamedRuleWithOperationsBuilder item : this.excludeResourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MatchResourcesFluent withExcludeResourceRules(List excludeResourceRules) {
      if (this.excludeResourceRules != null) {
         this._visitables.get("excludeResourceRules").clear();
      }

      if (excludeResourceRules != null) {
         this.excludeResourceRules = new ArrayList();

         for(NamedRuleWithOperations item : excludeResourceRules) {
            this.addToExcludeResourceRules(item);
         }
      } else {
         this.excludeResourceRules = null;
      }

      return this;
   }

   public MatchResourcesFluent withExcludeResourceRules(NamedRuleWithOperations... excludeResourceRules) {
      if (this.excludeResourceRules != null) {
         this.excludeResourceRules.clear();
         this._visitables.remove("excludeResourceRules");
      }

      if (excludeResourceRules != null) {
         for(NamedRuleWithOperations item : excludeResourceRules) {
            this.addToExcludeResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasExcludeResourceRules() {
      return this.excludeResourceRules != null && !this.excludeResourceRules.isEmpty();
   }

   public ExcludeResourceRulesNested addNewExcludeResourceRule() {
      return new ExcludeResourceRulesNested(-1, (NamedRuleWithOperations)null);
   }

   public ExcludeResourceRulesNested addNewExcludeResourceRuleLike(NamedRuleWithOperations item) {
      return new ExcludeResourceRulesNested(-1, item);
   }

   public ExcludeResourceRulesNested setNewExcludeResourceRuleLike(int index, NamedRuleWithOperations item) {
      return new ExcludeResourceRulesNested(index, item);
   }

   public ExcludeResourceRulesNested editExcludeResourceRule(int index) {
      if (this.excludeResourceRules.size() <= index) {
         throw new RuntimeException("Can't edit excludeResourceRules. Index exceeds size.");
      } else {
         return this.setNewExcludeResourceRuleLike(index, this.buildExcludeResourceRule(index));
      }
   }

   public ExcludeResourceRulesNested editFirstExcludeResourceRule() {
      if (this.excludeResourceRules.size() == 0) {
         throw new RuntimeException("Can't edit first excludeResourceRules. The list is empty.");
      } else {
         return this.setNewExcludeResourceRuleLike(0, this.buildExcludeResourceRule(0));
      }
   }

   public ExcludeResourceRulesNested editLastExcludeResourceRule() {
      int index = this.excludeResourceRules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last excludeResourceRules. The list is empty.");
      } else {
         return this.setNewExcludeResourceRuleLike(index, this.buildExcludeResourceRule(index));
      }
   }

   public ExcludeResourceRulesNested editMatchingExcludeResourceRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.excludeResourceRules.size(); ++i) {
         if (predicate.test((NamedRuleWithOperationsBuilder)this.excludeResourceRules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching excludeResourceRules. No match found.");
      } else {
         return this.setNewExcludeResourceRuleLike(index, this.buildExcludeResourceRule(index));
      }
   }

   public String getMatchPolicy() {
      return this.matchPolicy;
   }

   public MatchResourcesFluent withMatchPolicy(String matchPolicy) {
      this.matchPolicy = matchPolicy;
      return this;
   }

   public boolean hasMatchPolicy() {
      return this.matchPolicy != null;
   }

   public LabelSelector buildNamespaceSelector() {
      return this.namespaceSelector != null ? this.namespaceSelector.build() : null;
   }

   public MatchResourcesFluent withNamespaceSelector(LabelSelector namespaceSelector) {
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

   public LabelSelector buildObjectSelector() {
      return this.objectSelector != null ? this.objectSelector.build() : null;
   }

   public MatchResourcesFluent withObjectSelector(LabelSelector objectSelector) {
      this._visitables.remove("objectSelector");
      if (objectSelector != null) {
         this.objectSelector = new LabelSelectorBuilder(objectSelector);
         this._visitables.get("objectSelector").add(this.objectSelector);
      } else {
         this.objectSelector = null;
         this._visitables.get("objectSelector").remove(this.objectSelector);
      }

      return this;
   }

   public boolean hasObjectSelector() {
      return this.objectSelector != null;
   }

   public ObjectSelectorNested withNewObjectSelector() {
      return new ObjectSelectorNested((LabelSelector)null);
   }

   public ObjectSelectorNested withNewObjectSelectorLike(LabelSelector item) {
      return new ObjectSelectorNested(item);
   }

   public ObjectSelectorNested editObjectSelector() {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse((Object)null));
   }

   public ObjectSelectorNested editOrNewObjectSelector() {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public ObjectSelectorNested editOrNewObjectSelectorLike(LabelSelector item) {
      return this.withNewObjectSelectorLike((LabelSelector)Optional.ofNullable(this.buildObjectSelector()).orElse(item));
   }

   public MatchResourcesFluent addToResourceRules(int index, NamedRuleWithOperations item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").add(index, builder);
         this.resourceRules.add(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent setToResourceRules(int index, NamedRuleWithOperations item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").set(index, builder);
         this.resourceRules.set(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent addToResourceRules(NamedRuleWithOperations... items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(NamedRuleWithOperations item : items) {
         NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent addAllToResourceRules(Collection items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(NamedRuleWithOperations item : items) {
         NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public MatchResourcesFluent removeFromResourceRules(NamedRuleWithOperations... items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(NamedRuleWithOperations item : items) {
            NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public MatchResourcesFluent removeAllFromResourceRules(Collection items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(NamedRuleWithOperations item : items) {
            NamedRuleWithOperationsBuilder builder = new NamedRuleWithOperationsBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public MatchResourcesFluent removeMatchingFromResourceRules(Predicate predicate) {
      if (this.resourceRules == null) {
         return this;
      } else {
         Iterator<NamedRuleWithOperationsBuilder> each = this.resourceRules.iterator();
         List visitables = this._visitables.get("resourceRules");

         while(each.hasNext()) {
            NamedRuleWithOperationsBuilder builder = (NamedRuleWithOperationsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResourceRules() {
      return this.resourceRules != null ? build(this.resourceRules) : null;
   }

   public NamedRuleWithOperations buildResourceRule(int index) {
      return ((NamedRuleWithOperationsBuilder)this.resourceRules.get(index)).build();
   }

   public NamedRuleWithOperations buildFirstResourceRule() {
      return ((NamedRuleWithOperationsBuilder)this.resourceRules.get(0)).build();
   }

   public NamedRuleWithOperations buildLastResourceRule() {
      return ((NamedRuleWithOperationsBuilder)this.resourceRules.get(this.resourceRules.size() - 1)).build();
   }

   public NamedRuleWithOperations buildMatchingResourceRule(Predicate predicate) {
      for(NamedRuleWithOperationsBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceRule(Predicate predicate) {
      for(NamedRuleWithOperationsBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MatchResourcesFluent withResourceRules(List resourceRules) {
      if (this.resourceRules != null) {
         this._visitables.get("resourceRules").clear();
      }

      if (resourceRules != null) {
         this.resourceRules = new ArrayList();

         for(NamedRuleWithOperations item : resourceRules) {
            this.addToResourceRules(item);
         }
      } else {
         this.resourceRules = null;
      }

      return this;
   }

   public MatchResourcesFluent withResourceRules(NamedRuleWithOperations... resourceRules) {
      if (this.resourceRules != null) {
         this.resourceRules.clear();
         this._visitables.remove("resourceRules");
      }

      if (resourceRules != null) {
         for(NamedRuleWithOperations item : resourceRules) {
            this.addToResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasResourceRules() {
      return this.resourceRules != null && !this.resourceRules.isEmpty();
   }

   public ResourceRulesNested addNewResourceRule() {
      return new ResourceRulesNested(-1, (NamedRuleWithOperations)null);
   }

   public ResourceRulesNested addNewResourceRuleLike(NamedRuleWithOperations item) {
      return new ResourceRulesNested(-1, item);
   }

   public ResourceRulesNested setNewResourceRuleLike(int index, NamedRuleWithOperations item) {
      return new ResourceRulesNested(index, item);
   }

   public ResourceRulesNested editResourceRule(int index) {
      if (this.resourceRules.size() <= index) {
         throw new RuntimeException("Can't edit resourceRules. Index exceeds size.");
      } else {
         return this.setNewResourceRuleLike(index, this.buildResourceRule(index));
      }
   }

   public ResourceRulesNested editFirstResourceRule() {
      if (this.resourceRules.size() == 0) {
         throw new RuntimeException("Can't edit first resourceRules. The list is empty.");
      } else {
         return this.setNewResourceRuleLike(0, this.buildResourceRule(0));
      }
   }

   public ResourceRulesNested editLastResourceRule() {
      int index = this.resourceRules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resourceRules. The list is empty.");
      } else {
         return this.setNewResourceRuleLike(index, this.buildResourceRule(index));
      }
   }

   public ResourceRulesNested editMatchingResourceRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resourceRules.size(); ++i) {
         if (predicate.test((NamedRuleWithOperationsBuilder)this.resourceRules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resourceRules. No match found.");
      } else {
         return this.setNewResourceRuleLike(index, this.buildResourceRule(index));
      }
   }

   public MatchResourcesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MatchResourcesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MatchResourcesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MatchResourcesFluent removeFromAdditionalProperties(Map map) {
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

   public MatchResourcesFluent withAdditionalProperties(Map additionalProperties) {
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
            MatchResourcesFluent that = (MatchResourcesFluent)o;
            if (!Objects.equals(this.excludeResourceRules, that.excludeResourceRules)) {
               return false;
            } else if (!Objects.equals(this.matchPolicy, that.matchPolicy)) {
               return false;
            } else if (!Objects.equals(this.namespaceSelector, that.namespaceSelector)) {
               return false;
            } else if (!Objects.equals(this.objectSelector, that.objectSelector)) {
               return false;
            } else if (!Objects.equals(this.resourceRules, that.resourceRules)) {
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
      return Objects.hash(new Object[]{this.excludeResourceRules, this.matchPolicy, this.namespaceSelector, this.objectSelector, this.resourceRules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.excludeResourceRules != null && !this.excludeResourceRules.isEmpty()) {
         sb.append("excludeResourceRules:");
         sb.append(this.excludeResourceRules + ",");
      }

      if (this.matchPolicy != null) {
         sb.append("matchPolicy:");
         sb.append(this.matchPolicy + ",");
      }

      if (this.namespaceSelector != null) {
         sb.append("namespaceSelector:");
         sb.append(this.namespaceSelector + ",");
      }

      if (this.objectSelector != null) {
         sb.append("objectSelector:");
         sb.append(this.objectSelector + ",");
      }

      if (this.resourceRules != null && !this.resourceRules.isEmpty()) {
         sb.append("resourceRules:");
         sb.append(this.resourceRules + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ExcludeResourceRulesNested extends NamedRuleWithOperationsFluent implements Nested {
      NamedRuleWithOperationsBuilder builder;
      int index;

      ExcludeResourceRulesNested(int index, NamedRuleWithOperations item) {
         this.index = index;
         this.builder = new NamedRuleWithOperationsBuilder(this, item);
      }

      public Object and() {
         return MatchResourcesFluent.this.setToExcludeResourceRules(this.index, this.builder.build());
      }

      public Object endExcludeResourceRule() {
         return this.and();
      }
   }

   public class NamespaceSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      NamespaceSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return MatchResourcesFluent.this.withNamespaceSelector(this.builder.build());
      }

      public Object endNamespaceSelector() {
         return this.and();
      }
   }

   public class ObjectSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      ObjectSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return MatchResourcesFluent.this.withObjectSelector(this.builder.build());
      }

      public Object endObjectSelector() {
         return this.and();
      }
   }

   public class ResourceRulesNested extends NamedRuleWithOperationsFluent implements Nested {
      NamedRuleWithOperationsBuilder builder;
      int index;

      ResourceRulesNested(int index, NamedRuleWithOperations item) {
         this.index = index;
         this.builder = new NamedRuleWithOperationsBuilder(this, item);
      }

      public Object and() {
         return MatchResourcesFluent.this.setToResourceRules(this.index, this.builder.build());
      }

      public Object endResourceRule() {
         return this.and();
      }
   }
}
