package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

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

public class PolicyRulesWithSubjectsFluent extends BaseFluent {
   private ArrayList nonResourceRules = new ArrayList();
   private ArrayList resourceRules = new ArrayList();
   private ArrayList subjects = new ArrayList();
   private Map additionalProperties;

   public PolicyRulesWithSubjectsFluent() {
   }

   public PolicyRulesWithSubjectsFluent(PolicyRulesWithSubjects instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PolicyRulesWithSubjects instance) {
      instance = instance != null ? instance : new PolicyRulesWithSubjects();
      if (instance != null) {
         this.withNonResourceRules(instance.getNonResourceRules());
         this.withResourceRules(instance.getResourceRules());
         this.withSubjects(instance.getSubjects());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PolicyRulesWithSubjectsFluent addToNonResourceRules(int index, NonResourcePolicyRule item) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
      if (index >= 0 && index < this.nonResourceRules.size()) {
         this._visitables.get("nonResourceRules").add(index, builder);
         this.nonResourceRules.add(index, builder);
      } else {
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent setToNonResourceRules(int index, NonResourcePolicyRule item) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
      if (index >= 0 && index < this.nonResourceRules.size()) {
         this._visitables.get("nonResourceRules").set(index, builder);
         this.nonResourceRules.set(index, builder);
      } else {
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addToNonResourceRules(NonResourcePolicyRule... items) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      for(NonResourcePolicyRule item : items) {
         NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addAllToNonResourceRules(Collection items) {
      if (this.nonResourceRules == null) {
         this.nonResourceRules = new ArrayList();
      }

      for(NonResourcePolicyRule item : items) {
         NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
         this._visitables.get("nonResourceRules").add(builder);
         this.nonResourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent removeFromNonResourceRules(NonResourcePolicyRule... items) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         for(NonResourcePolicyRule item : items) {
            NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
            this._visitables.get("nonResourceRules").remove(builder);
            this.nonResourceRules.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeAllFromNonResourceRules(Collection items) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         for(NonResourcePolicyRule item : items) {
            NonResourcePolicyRuleBuilder builder = new NonResourcePolicyRuleBuilder(item);
            this._visitables.get("nonResourceRules").remove(builder);
            this.nonResourceRules.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeMatchingFromNonResourceRules(Predicate predicate) {
      if (this.nonResourceRules == null) {
         return this;
      } else {
         Iterator<NonResourcePolicyRuleBuilder> each = this.nonResourceRules.iterator();
         List visitables = this._visitables.get("nonResourceRules");

         while(each.hasNext()) {
            NonResourcePolicyRuleBuilder builder = (NonResourcePolicyRuleBuilder)each.next();
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

   public NonResourcePolicyRule buildNonResourceRule(int index) {
      return ((NonResourcePolicyRuleBuilder)this.nonResourceRules.get(index)).build();
   }

   public NonResourcePolicyRule buildFirstNonResourceRule() {
      return ((NonResourcePolicyRuleBuilder)this.nonResourceRules.get(0)).build();
   }

   public NonResourcePolicyRule buildLastNonResourceRule() {
      return ((NonResourcePolicyRuleBuilder)this.nonResourceRules.get(this.nonResourceRules.size() - 1)).build();
   }

   public NonResourcePolicyRule buildMatchingNonResourceRule(Predicate predicate) {
      for(NonResourcePolicyRuleBuilder item : this.nonResourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingNonResourceRule(Predicate predicate) {
      for(NonResourcePolicyRuleBuilder item : this.nonResourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PolicyRulesWithSubjectsFluent withNonResourceRules(List nonResourceRules) {
      if (this.nonResourceRules != null) {
         this._visitables.get("nonResourceRules").clear();
      }

      if (nonResourceRules != null) {
         this.nonResourceRules = new ArrayList();

         for(NonResourcePolicyRule item : nonResourceRules) {
            this.addToNonResourceRules(item);
         }
      } else {
         this.nonResourceRules = null;
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent withNonResourceRules(NonResourcePolicyRule... nonResourceRules) {
      if (this.nonResourceRules != null) {
         this.nonResourceRules.clear();
         this._visitables.remove("nonResourceRules");
      }

      if (nonResourceRules != null) {
         for(NonResourcePolicyRule item : nonResourceRules) {
            this.addToNonResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasNonResourceRules() {
      return this.nonResourceRules != null && !this.nonResourceRules.isEmpty();
   }

   public NonResourceRulesNested addNewNonResourceRule() {
      return new NonResourceRulesNested(-1, (NonResourcePolicyRule)null);
   }

   public NonResourceRulesNested addNewNonResourceRuleLike(NonResourcePolicyRule item) {
      return new NonResourceRulesNested(-1, item);
   }

   public NonResourceRulesNested setNewNonResourceRuleLike(int index, NonResourcePolicyRule item) {
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
         if (predicate.test((NonResourcePolicyRuleBuilder)this.nonResourceRules.get(i))) {
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

   public PolicyRulesWithSubjectsFluent addToResourceRules(int index, ResourcePolicyRule item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").add(index, builder);
         this.resourceRules.add(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent setToResourceRules(int index, ResourcePolicyRule item) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
      if (index >= 0 && index < this.resourceRules.size()) {
         this._visitables.get("resourceRules").set(index, builder);
         this.resourceRules.set(index, builder);
      } else {
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addToResourceRules(ResourcePolicyRule... items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(ResourcePolicyRule item : items) {
         ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addAllToResourceRules(Collection items) {
      if (this.resourceRules == null) {
         this.resourceRules = new ArrayList();
      }

      for(ResourcePolicyRule item : items) {
         ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
         this._visitables.get("resourceRules").add(builder);
         this.resourceRules.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent removeFromResourceRules(ResourcePolicyRule... items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(ResourcePolicyRule item : items) {
            ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeAllFromResourceRules(Collection items) {
      if (this.resourceRules == null) {
         return this;
      } else {
         for(ResourcePolicyRule item : items) {
            ResourcePolicyRuleBuilder builder = new ResourcePolicyRuleBuilder(item);
            this._visitables.get("resourceRules").remove(builder);
            this.resourceRules.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeMatchingFromResourceRules(Predicate predicate) {
      if (this.resourceRules == null) {
         return this;
      } else {
         Iterator<ResourcePolicyRuleBuilder> each = this.resourceRules.iterator();
         List visitables = this._visitables.get("resourceRules");

         while(each.hasNext()) {
            ResourcePolicyRuleBuilder builder = (ResourcePolicyRuleBuilder)each.next();
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

   public ResourcePolicyRule buildResourceRule(int index) {
      return ((ResourcePolicyRuleBuilder)this.resourceRules.get(index)).build();
   }

   public ResourcePolicyRule buildFirstResourceRule() {
      return ((ResourcePolicyRuleBuilder)this.resourceRules.get(0)).build();
   }

   public ResourcePolicyRule buildLastResourceRule() {
      return ((ResourcePolicyRuleBuilder)this.resourceRules.get(this.resourceRules.size() - 1)).build();
   }

   public ResourcePolicyRule buildMatchingResourceRule(Predicate predicate) {
      for(ResourcePolicyRuleBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceRule(Predicate predicate) {
      for(ResourcePolicyRuleBuilder item : this.resourceRules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PolicyRulesWithSubjectsFluent withResourceRules(List resourceRules) {
      if (this.resourceRules != null) {
         this._visitables.get("resourceRules").clear();
      }

      if (resourceRules != null) {
         this.resourceRules = new ArrayList();

         for(ResourcePolicyRule item : resourceRules) {
            this.addToResourceRules(item);
         }
      } else {
         this.resourceRules = null;
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent withResourceRules(ResourcePolicyRule... resourceRules) {
      if (this.resourceRules != null) {
         this.resourceRules.clear();
         this._visitables.remove("resourceRules");
      }

      if (resourceRules != null) {
         for(ResourcePolicyRule item : resourceRules) {
            this.addToResourceRules(item);
         }
      }

      return this;
   }

   public boolean hasResourceRules() {
      return this.resourceRules != null && !this.resourceRules.isEmpty();
   }

   public ResourceRulesNested addNewResourceRule() {
      return new ResourceRulesNested(-1, (ResourcePolicyRule)null);
   }

   public ResourceRulesNested addNewResourceRuleLike(ResourcePolicyRule item) {
      return new ResourceRulesNested(-1, item);
   }

   public ResourceRulesNested setNewResourceRuleLike(int index, ResourcePolicyRule item) {
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
         if (predicate.test((ResourcePolicyRuleBuilder)this.resourceRules.get(i))) {
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

   public PolicyRulesWithSubjectsFluent addToSubjects(int index, Subject item) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      SubjectBuilder builder = new SubjectBuilder(item);
      if (index >= 0 && index < this.subjects.size()) {
         this._visitables.get("subjects").add(index, builder);
         this.subjects.add(index, builder);
      } else {
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent setToSubjects(int index, Subject item) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      SubjectBuilder builder = new SubjectBuilder(item);
      if (index >= 0 && index < this.subjects.size()) {
         this._visitables.get("subjects").set(index, builder);
         this.subjects.set(index, builder);
      } else {
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addToSubjects(Subject... items) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      for(Subject item : items) {
         SubjectBuilder builder = new SubjectBuilder(item);
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addAllToSubjects(Collection items) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      for(Subject item : items) {
         SubjectBuilder builder = new SubjectBuilder(item);
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent removeFromSubjects(Subject... items) {
      if (this.subjects == null) {
         return this;
      } else {
         for(Subject item : items) {
            SubjectBuilder builder = new SubjectBuilder(item);
            this._visitables.get("subjects").remove(builder);
            this.subjects.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeAllFromSubjects(Collection items) {
      if (this.subjects == null) {
         return this;
      } else {
         for(Subject item : items) {
            SubjectBuilder builder = new SubjectBuilder(item);
            this._visitables.get("subjects").remove(builder);
            this.subjects.remove(builder);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeMatchingFromSubjects(Predicate predicate) {
      if (this.subjects == null) {
         return this;
      } else {
         Iterator<SubjectBuilder> each = this.subjects.iterator();
         List visitables = this._visitables.get("subjects");

         while(each.hasNext()) {
            SubjectBuilder builder = (SubjectBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSubjects() {
      return this.subjects != null ? build(this.subjects) : null;
   }

   public Subject buildSubject(int index) {
      return ((SubjectBuilder)this.subjects.get(index)).build();
   }

   public Subject buildFirstSubject() {
      return ((SubjectBuilder)this.subjects.get(0)).build();
   }

   public Subject buildLastSubject() {
      return ((SubjectBuilder)this.subjects.get(this.subjects.size() - 1)).build();
   }

   public Subject buildMatchingSubject(Predicate predicate) {
      for(SubjectBuilder item : this.subjects) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSubject(Predicate predicate) {
      for(SubjectBuilder item : this.subjects) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PolicyRulesWithSubjectsFluent withSubjects(List subjects) {
      if (this.subjects != null) {
         this._visitables.get("subjects").clear();
      }

      if (subjects != null) {
         this.subjects = new ArrayList();

         for(Subject item : subjects) {
            this.addToSubjects(item);
         }
      } else {
         this.subjects = null;
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent withSubjects(Subject... subjects) {
      if (this.subjects != null) {
         this.subjects.clear();
         this._visitables.remove("subjects");
      }

      if (subjects != null) {
         for(Subject item : subjects) {
            this.addToSubjects(item);
         }
      }

      return this;
   }

   public boolean hasSubjects() {
      return this.subjects != null && !this.subjects.isEmpty();
   }

   public SubjectsNested addNewSubject() {
      return new SubjectsNested(-1, (Subject)null);
   }

   public SubjectsNested addNewSubjectLike(Subject item) {
      return new SubjectsNested(-1, item);
   }

   public SubjectsNested setNewSubjectLike(int index, Subject item) {
      return new SubjectsNested(index, item);
   }

   public SubjectsNested editSubject(int index) {
      if (this.subjects.size() <= index) {
         throw new RuntimeException("Can't edit subjects. Index exceeds size.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public SubjectsNested editFirstSubject() {
      if (this.subjects.size() == 0) {
         throw new RuntimeException("Can't edit first subjects. The list is empty.");
      } else {
         return this.setNewSubjectLike(0, this.buildSubject(0));
      }
   }

   public SubjectsNested editLastSubject() {
      int index = this.subjects.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last subjects. The list is empty.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public SubjectsNested editMatchingSubject(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.subjects.size(); ++i) {
         if (predicate.test((SubjectBuilder)this.subjects.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching subjects. No match found.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public PolicyRulesWithSubjectsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PolicyRulesWithSubjectsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PolicyRulesWithSubjectsFluent removeFromAdditionalProperties(Map map) {
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

   public PolicyRulesWithSubjectsFluent withAdditionalProperties(Map additionalProperties) {
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
            PolicyRulesWithSubjectsFluent that = (PolicyRulesWithSubjectsFluent)o;
            if (!Objects.equals(this.nonResourceRules, that.nonResourceRules)) {
               return false;
            } else if (!Objects.equals(this.resourceRules, that.resourceRules)) {
               return false;
            } else if (!Objects.equals(this.subjects, that.subjects)) {
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
      return Objects.hash(new Object[]{this.nonResourceRules, this.resourceRules, this.subjects, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nonResourceRules != null && !this.nonResourceRules.isEmpty()) {
         sb.append("nonResourceRules:");
         sb.append(this.nonResourceRules + ",");
      }

      if (this.resourceRules != null && !this.resourceRules.isEmpty()) {
         sb.append("resourceRules:");
         sb.append(this.resourceRules + ",");
      }

      if (this.subjects != null && !this.subjects.isEmpty()) {
         sb.append("subjects:");
         sb.append(this.subjects + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NonResourceRulesNested extends NonResourcePolicyRuleFluent implements Nested {
      NonResourcePolicyRuleBuilder builder;
      int index;

      NonResourceRulesNested(int index, NonResourcePolicyRule item) {
         this.index = index;
         this.builder = new NonResourcePolicyRuleBuilder(this, item);
      }

      public Object and() {
         return PolicyRulesWithSubjectsFluent.this.setToNonResourceRules(this.index, this.builder.build());
      }

      public Object endNonResourceRule() {
         return this.and();
      }
   }

   public class ResourceRulesNested extends ResourcePolicyRuleFluent implements Nested {
      ResourcePolicyRuleBuilder builder;
      int index;

      ResourceRulesNested(int index, ResourcePolicyRule item) {
         this.index = index;
         this.builder = new ResourcePolicyRuleBuilder(this, item);
      }

      public Object and() {
         return PolicyRulesWithSubjectsFluent.this.setToResourceRules(this.index, this.builder.build());
      }

      public Object endResourceRule() {
         return this.and();
      }
   }

   public class SubjectsNested extends SubjectFluent implements Nested {
      SubjectBuilder builder;
      int index;

      SubjectsNested(int index, Subject item) {
         this.index = index;
         this.builder = new SubjectBuilder(this, item);
      }

      public Object and() {
         return PolicyRulesWithSubjectsFluent.this.setToSubjects(this.index, this.builder.build());
      }

      public Object endSubject() {
         return this.and();
      }
   }
}
