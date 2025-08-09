package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class SubjectRulesReviewStatusFluent extends BaseFluent {
   private String evaluationError;
   private Boolean incomplete;
   private ArrayList nonResourceRules = new ArrayList();
   private ArrayList resourceRules = new ArrayList();
   private Map additionalProperties;

   public SubjectRulesReviewStatusFluent() {
   }

   public SubjectRulesReviewStatusFluent(SubjectRulesReviewStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SubjectRulesReviewStatus instance) {
      instance = instance != null ? instance : new SubjectRulesReviewStatus();
      if (instance != null) {
         this.withEvaluationError(instance.getEvaluationError());
         this.withIncomplete(instance.getIncomplete());
         this.withNonResourceRules(instance.getNonResourceRules());
         this.withResourceRules(instance.getResourceRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getEvaluationError() {
      return this.evaluationError;
   }

   public SubjectRulesReviewStatusFluent withEvaluationError(String evaluationError) {
      this.evaluationError = evaluationError;
      return this;
   }

   public boolean hasEvaluationError() {
      return this.evaluationError != null;
   }

   public Boolean getIncomplete() {
      return this.incomplete;
   }

   public SubjectRulesReviewStatusFluent withIncomplete(Boolean incomplete) {
      this.incomplete = incomplete;
      return this;
   }

   public boolean hasIncomplete() {
      return this.incomplete != null;
   }

   public SubjectRulesReviewStatusFluent addToNonResourceRules(int index, NonResourceRule item) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
      if (index >= 0 && index < this.nonResourceRules.size()) {
         this._visitables.get("nonResourceRules").add(index, builder);
         this.nonResourceRules.add(index, builder);
      } else {
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent setToNonResourceRules(int index, NonResourceRule item) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
      if (index >= 0 && index < this.nonResourceRules.size()) {
         this._visitables.get("nonResourceRules").set(index, builder);
         this.nonResourceRules.set(index, builder);
      } else {
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent addToNonResourceRules(NonResourceRule... items) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      for(NonResourceRule item : items) {
         NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent addAllToNonResourceRules(Collection items) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      for(NonResourceRule item : items) {
         NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent removeFromNonResourceRules(NonResourceRule... items) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         for(NonResourceRule item : items) {
            NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
            this._visitables.get("nonResourceRules").remove(builder);
            this.nonResourceRules.remove(builder);
         }

         return this;
      }
   }

   public SubjectRulesReviewStatusFluent removeAllFromNonResourceRules(Collection items) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         for(NonResourceRule item : items) {
            NonResourceRuleBuilder builder = new NonResourceRuleBuilder(item);
            this._visitables.get("nonResourceRules").remove(builder);
            this.nonResourceRules.remove(builder);
         }

         return this;
      }
   }

   public SubjectRulesReviewStatusFluent removeMatchingFromNonResourceRules(Predicate predicate) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         Iterator<NonResourceRuleBuilder> each = this.nonResourceRules.iterator();
         List visitables = this._visitables.get("nonResourceRules");

         while(each.hasNext()) {
            NonResourceRuleBuilder builder = (NonResourceRuleBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildNonResourceRules() {
      return this.nonResourceRules != null ? build(this.nonResourceRules) : null;
   }

   public NonResourceRule buildNonResourceRule(int index) {
      return ((NonResourceRuleBuilder)this.nonResourceRules.get(index)).build();
   }

   public NonResourceRule buildFirstNonResourceRule() {
      return ((NonResourceRuleBuilder)this.nonResourceRules.get(0)).build();
   }

   public NonResourceRule buildLastNonResourceRule() {
      return ((NonResourceRuleBuilder)this.nonResourceRules.get(this.nonResourceRules.size() - 1)).build();
   }

   public NonResourceRule buildMatchingNonResourceRule(Predicate predicate) {
      for(NonResourceRuleBuilder item : this.nonResourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingNonResourceRule(Predicate predicate) {
      for(NonResourceRuleBuilder item : this.nonResourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SubjectRulesReviewStatusFluent withNonResourceRules(List nonResourceRules) {
      if (this.nonResourceRules != null) {
         this._visitables.get("nonResourceRules").clear();
      }

      if (nonResourceRules != null) {
         this.nonResourceRules = new ArrayList();

         for(NonResourceRule item : nonResourceRules) {
            this.addToNonResourceRules(item);
         }
      } else {
         this.nonResourceRules = null;
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent withNonResourceRules(NonResourceRule... nonResourceRules) {
      if (this.nonResourceRules != null) {
         this.nonResourceRules.clear();
         this._visitables.remove("nonResourceRules");
      }

      if (nonResourceRules != null) {
         for(NonResourceRule item : nonResourceRules) {
            this.addToNonResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasNonResourceRules() {
      return this.nonResourceRules != null && !this.nonResourceRules.isEmpty();
   }

   public NonResourceRulesNested addNewNonResourceRule() {
      return new NonResourceRulesNested(-1, (NonResourceRule)null);
   }

   public NonResourceRulesNested addNewNonResourceRuleLike(NonResourceRule item) {
      return new NonResourceRulesNested(-1, item);
   }

   public NonResourceRulesNested setNewNonResourceRuleLike(int index, NonResourceRule item) {
      return new NonResourceRulesNested(index, item);
   }

   public NonResourceRulesNested editNonResourceRule(int index) {
      if (this.nonResourceRules.size() <= index) {
         throw new RuntimeException("Can't edit nonResourceRules. Index exceeds size.");
      } else {
         return this.setNewNonResourceRuleLike(index, this.buildNonResourceRule(index));
      }
   }

   public NonResourceRulesNested editFirstNonResourceRule() {
      if (this.nonResourceRules.size() == 0) {
         throw new RuntimeException("Can't edit first nonResourceRules. The list is empty.");
      } else {
         return this.setNewNonResourceRuleLike(0, this.buildNonResourceRule(0));
      }
   }

   public NonResourceRulesNested editLastNonResourceRule() {
      int index = this.nonResourceRules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last nonResourceRules. The list is empty.");
      } else {
         return this.setNewNonResourceRuleLike(index, this.buildNonResourceRule(index));
      }
   }

   public NonResourceRulesNested editMatchingNonResourceRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.nonResourceRules.size(); ++i) {
         if (predicate.test((NonResourceRuleBuilder)this.nonResourceRules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching nonResourceRules. No match found.");
      } else {
         return this.setNewNonResourceRuleLike(index, this.buildNonResourceRule(index));
      }
   }

   public SubjectRulesReviewStatusFluent addToResourceRules(int index, ResourceRule item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").add(index, builder);
         this.resourceRules.add(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent setToResourceRules(int index, ResourceRule item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").set(index, builder);
         this.resourceRules.set(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent addToResourceRules(ResourceRule... items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(ResourceRule item : items) {
         ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent addAllToResourceRules(Collection items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(ResourceRule item : items) {
         ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent removeFromResourceRules(ResourceRule... items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(ResourceRule item : items) {
            ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public SubjectRulesReviewStatusFluent removeAllFromResourceRules(Collection items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(ResourceRule item : items) {
            ResourceRuleBuilder builder = new ResourceRuleBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public SubjectRulesReviewStatusFluent removeMatchingFromResourceRules(Predicate predicate) {
      if (this.resourceRules == null) {
         return this;
      } else {
         Iterator<ResourceRuleBuilder> each = this.resourceRules.iterator();
         List visitables = this._visitables.get("resourceRules");

         while(each.hasNext()) {
            ResourceRuleBuilder builder = (ResourceRuleBuilder)each.next();
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

   public ResourceRule buildResourceRule(int index) {
      return ((ResourceRuleBuilder)this.resourceRules.get(index)).build();
   }

   public ResourceRule buildFirstResourceRule() {
      return ((ResourceRuleBuilder)this.resourceRules.get(0)).build();
   }

   public ResourceRule buildLastResourceRule() {
      return ((ResourceRuleBuilder)this.resourceRules.get(this.resourceRules.size() - 1)).build();
   }

   public ResourceRule buildMatchingResourceRule(Predicate predicate) {
      for(ResourceRuleBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceRule(Predicate predicate) {
      for(ResourceRuleBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public SubjectRulesReviewStatusFluent withResourceRules(List resourceRules) {
      if (this.resourceRules != null) {
         this._visitables.get("resourceRules").clear();
      }

      if (resourceRules != null) {
         this.resourceRules = new ArrayList();

         for(ResourceRule item : resourceRules) {
            this.addToResourceRules(item);
         }
      } else {
         this.resourceRules = null;
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent withResourceRules(ResourceRule... resourceRules) {
      if (this.resourceRules != null) {
         this.resourceRules.clear();
         this._visitables.remove("resourceRules");
      }

      if (resourceRules != null) {
         for(ResourceRule item : resourceRules) {
            this.addToResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasResourceRules() {
      return this.resourceRules != null && !this.resourceRules.isEmpty();
   }

   public ResourceRulesNested addNewResourceRule() {
      return new ResourceRulesNested(-1, (ResourceRule)null);
   }

   public ResourceRulesNested addNewResourceRuleLike(ResourceRule item) {
      return new ResourceRulesNested(-1, item);
   }

   public ResourceRulesNested setNewResourceRuleLike(int index, ResourceRule item) {
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
         if (predicate.test((ResourceRuleBuilder)this.resourceRules.get(i))) {
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

   public SubjectRulesReviewStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SubjectRulesReviewStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SubjectRulesReviewStatusFluent removeFromAdditionalProperties(Map map) {
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

   public SubjectRulesReviewStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            SubjectRulesReviewStatusFluent that = (SubjectRulesReviewStatusFluent)o;
            if (!Objects.equals(this.evaluationError, that.evaluationError)) {
               return false;
            } else if (!Objects.equals(this.incomplete, that.incomplete)) {
               return false;
            } else if (!Objects.equals(this.nonResourceRules, that.nonResourceRules)) {
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
      return Objects.hash(new Object[]{this.evaluationError, this.incomplete, this.nonResourceRules, this.resourceRules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.evaluationError != null) {
         sb.append("evaluationError:");
         sb.append(this.evaluationError + ",");
      }

      if (this.incomplete != null) {
         sb.append("incomplete:");
         sb.append(this.incomplete + ",");
      }

      if (this.nonResourceRules != null && !this.nonResourceRules.isEmpty()) {
         sb.append("nonResourceRules:");
         sb.append(this.nonResourceRules + ",");
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

   public SubjectRulesReviewStatusFluent withIncomplete() {
      return this.withIncomplete(true);
   }

   public class NonResourceRulesNested extends NonResourceRuleFluent implements Nested {
      NonResourceRuleBuilder builder;
      int index;

      NonResourceRulesNested(int index, NonResourceRule item) {
         this.index = index;
         this.builder = new NonResourceRuleBuilder(this, item);
      }

      public Object and() {
         return SubjectRulesReviewStatusFluent.this.setToNonResourceRules(this.index, this.builder.build());
      }

      public Object endNonResourceRule() {
         return this.and();
      }
   }

   public class ResourceRulesNested extends ResourceRuleFluent implements Nested {
      ResourceRuleBuilder builder;
      int index;

      ResourceRulesNested(int index, ResourceRule item) {
         this.index = index;
         this.builder = new ResourceRuleBuilder(this, item);
      }

      public Object and() {
         return SubjectRulesReviewStatusFluent.this.setToResourceRules(this.index, this.builder.build());
      }

      public Object endResourceRule() {
         return this.and();
      }
   }
}
