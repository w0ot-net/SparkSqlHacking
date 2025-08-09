package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class FlowSchemaSpecFluent extends BaseFluent {
   private FlowDistinguisherMethodBuilder distinguisherMethod;
   private Integer matchingPrecedence;
   private PriorityLevelConfigurationReferenceBuilder priorityLevelConfiguration;
   private ArrayList rules = new ArrayList();
   private Map additionalProperties;

   public FlowSchemaSpecFluent() {
   }

   public FlowSchemaSpecFluent(FlowSchemaSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FlowSchemaSpec instance) {
      instance = instance != null ? instance : new FlowSchemaSpec();
      if (instance != null) {
         this.withDistinguisherMethod(instance.getDistinguisherMethod());
         this.withMatchingPrecedence(instance.getMatchingPrecedence());
         this.withPriorityLevelConfiguration(instance.getPriorityLevelConfiguration());
         this.withRules(instance.getRules());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public FlowDistinguisherMethod buildDistinguisherMethod() {
      return this.distinguisherMethod != null ? this.distinguisherMethod.build() : null;
   }

   public FlowSchemaSpecFluent withDistinguisherMethod(FlowDistinguisherMethod distinguisherMethod) {
      this._visitables.remove("distinguisherMethod");
      if (distinguisherMethod != null) {
         this.distinguisherMethod = new FlowDistinguisherMethodBuilder(distinguisherMethod);
         this._visitables.get("distinguisherMethod").add(this.distinguisherMethod);
      } else {
         this.distinguisherMethod = null;
         this._visitables.get("distinguisherMethod").remove(this.distinguisherMethod);
      }

      return this;
   }

   public boolean hasDistinguisherMethod() {
      return this.distinguisherMethod != null;
   }

   public FlowSchemaSpecFluent withNewDistinguisherMethod(String type) {
      return this.withDistinguisherMethod(new FlowDistinguisherMethod(type));
   }

   public DistinguisherMethodNested withNewDistinguisherMethod() {
      return new DistinguisherMethodNested((FlowDistinguisherMethod)null);
   }

   public DistinguisherMethodNested withNewDistinguisherMethodLike(FlowDistinguisherMethod item) {
      return new DistinguisherMethodNested(item);
   }

   public DistinguisherMethodNested editDistinguisherMethod() {
      return this.withNewDistinguisherMethodLike((FlowDistinguisherMethod)Optional.ofNullable(this.buildDistinguisherMethod()).orElse((Object)null));
   }

   public DistinguisherMethodNested editOrNewDistinguisherMethod() {
      return this.withNewDistinguisherMethodLike((FlowDistinguisherMethod)Optional.ofNullable(this.buildDistinguisherMethod()).orElse((new FlowDistinguisherMethodBuilder()).build()));
   }

   public DistinguisherMethodNested editOrNewDistinguisherMethodLike(FlowDistinguisherMethod item) {
      return this.withNewDistinguisherMethodLike((FlowDistinguisherMethod)Optional.ofNullable(this.buildDistinguisherMethod()).orElse(item));
   }

   public Integer getMatchingPrecedence() {
      return this.matchingPrecedence;
   }

   public FlowSchemaSpecFluent withMatchingPrecedence(Integer matchingPrecedence) {
      this.matchingPrecedence = matchingPrecedence;
      return this;
   }

   public boolean hasMatchingPrecedence() {
      return this.matchingPrecedence != null;
   }

   public PriorityLevelConfigurationReference buildPriorityLevelConfiguration() {
      return this.priorityLevelConfiguration != null ? this.priorityLevelConfiguration.build() : null;
   }

   public FlowSchemaSpecFluent withPriorityLevelConfiguration(PriorityLevelConfigurationReference priorityLevelConfiguration) {
      this._visitables.remove("priorityLevelConfiguration");
      if (priorityLevelConfiguration != null) {
         this.priorityLevelConfiguration = new PriorityLevelConfigurationReferenceBuilder(priorityLevelConfiguration);
         this._visitables.get("priorityLevelConfiguration").add(this.priorityLevelConfiguration);
      } else {
         this.priorityLevelConfiguration = null;
         this._visitables.get("priorityLevelConfiguration").remove(this.priorityLevelConfiguration);
      }

      return this;
   }

   public boolean hasPriorityLevelConfiguration() {
      return this.priorityLevelConfiguration != null;
   }

   public FlowSchemaSpecFluent withNewPriorityLevelConfiguration(String name) {
      return this.withPriorityLevelConfiguration(new PriorityLevelConfigurationReference(name));
   }

   public PriorityLevelConfigurationNested withNewPriorityLevelConfiguration() {
      return new PriorityLevelConfigurationNested((PriorityLevelConfigurationReference)null);
   }

   public PriorityLevelConfigurationNested withNewPriorityLevelConfigurationLike(PriorityLevelConfigurationReference item) {
      return new PriorityLevelConfigurationNested(item);
   }

   public PriorityLevelConfigurationNested editPriorityLevelConfiguration() {
      return this.withNewPriorityLevelConfigurationLike((PriorityLevelConfigurationReference)Optional.ofNullable(this.buildPriorityLevelConfiguration()).orElse((Object)null));
   }

   public PriorityLevelConfigurationNested editOrNewPriorityLevelConfiguration() {
      return this.withNewPriorityLevelConfigurationLike((PriorityLevelConfigurationReference)Optional.ofNullable(this.buildPriorityLevelConfiguration()).orElse((new PriorityLevelConfigurationReferenceBuilder()).build()));
   }

   public PriorityLevelConfigurationNested editOrNewPriorityLevelConfigurationLike(PriorityLevelConfigurationReference item) {
      return this.withNewPriorityLevelConfigurationLike((PriorityLevelConfigurationReference)Optional.ofNullable(this.buildPriorityLevelConfiguration()).orElse(item));
   }

   public FlowSchemaSpecFluent addToRules(int index, PolicyRulesWithSubjects item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").add(index, builder);
         this.rules.add(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public FlowSchemaSpecFluent setToRules(int index, PolicyRulesWithSubjects item) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
      if (index >= 0 && index < this.rules.size()) {
         this._visitables.get("rules").set(index, builder);
         this.rules.set(index, builder);
      } else {
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public FlowSchemaSpecFluent addToRules(PolicyRulesWithSubjects... items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(PolicyRulesWithSubjects item : items) {
         PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public FlowSchemaSpecFluent addAllToRules(Collection items) {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      for(PolicyRulesWithSubjects item : items) {
         PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
         this._visitables.get("rules").add(builder);
         this.rules.add(builder);
      }

      return this;
   }

   public FlowSchemaSpecFluent removeFromRules(PolicyRulesWithSubjects... items) {
      if (this.rules == null) {
         return this;
      } else {
         for(PolicyRulesWithSubjects item : items) {
            PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public FlowSchemaSpecFluent removeAllFromRules(Collection items) {
      if (this.rules == null) {
         return this;
      } else {
         for(PolicyRulesWithSubjects item : items) {
            PolicyRulesWithSubjectsBuilder builder = new PolicyRulesWithSubjectsBuilder(item);
            this._visitables.get("rules").remove(builder);
            this.rules.remove(builder);
         }

         return this;
      }
   }

   public FlowSchemaSpecFluent removeMatchingFromRules(Predicate predicate) {
      if (this.rules == null) {
         return this;
      } else {
         Iterator<PolicyRulesWithSubjectsBuilder> each = this.rules.iterator();
         List visitables = this._visitables.get("rules");

         while(each.hasNext()) {
            PolicyRulesWithSubjectsBuilder builder = (PolicyRulesWithSubjectsBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildRules() {
      return this.rules != null ? build(this.rules) : null;
   }

   public PolicyRulesWithSubjects buildRule(int index) {
      return ((PolicyRulesWithSubjectsBuilder)this.rules.get(index)).build();
   }

   public PolicyRulesWithSubjects buildFirstRule() {
      return ((PolicyRulesWithSubjectsBuilder)this.rules.get(0)).build();
   }

   public PolicyRulesWithSubjects buildLastRule() {
      return ((PolicyRulesWithSubjectsBuilder)this.rules.get(this.rules.size() - 1)).build();
   }

   public PolicyRulesWithSubjects buildMatchingRule(Predicate predicate) {
      for(PolicyRulesWithSubjectsBuilder item : this.rules) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingRule(Predicate predicate) {
      for(PolicyRulesWithSubjectsBuilder item : this.rules) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FlowSchemaSpecFluent withRules(List rules) {
      if (this.rules != null) {
         this._visitables.get("rules").clear();
      }

      if (rules != null) {
         this.rules = new ArrayList();

         for(PolicyRulesWithSubjects item : rules) {
            this.addToRules(item);
         }
      } else {
         this.rules = null;
      }

      return this;
   }

   public FlowSchemaSpecFluent withRules(PolicyRulesWithSubjects... rules) {
      if (this.rules != null) {
         this.rules.clear();
         this._visitables.remove("rules");
      }

      if (rules != null) {
         for(PolicyRulesWithSubjects item : rules) {
            this.addToRules(item);
         }
      }

      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public RulesNested addNewRule() {
      return new RulesNested(-1, (PolicyRulesWithSubjects)null);
   }

   public RulesNested addNewRuleLike(PolicyRulesWithSubjects item) {
      return new RulesNested(-1, item);
   }

   public RulesNested setNewRuleLike(int index, PolicyRulesWithSubjects item) {
      return new RulesNested(index, item);
   }

   public RulesNested editRule(int index) {
      if (this.rules.size() <= index) {
         throw new RuntimeException("Can't edit rules. Index exceeds size.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editFirstRule() {
      if (this.rules.size() == 0) {
         throw new RuntimeException("Can't edit first rules. The list is empty.");
      } else {
         return this.setNewRuleLike(0, this.buildRule(0));
      }
   }

   public RulesNested editLastRule() {
      int index = this.rules.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last rules. The list is empty.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public RulesNested editMatchingRule(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.rules.size(); ++i) {
         if (predicate.test((PolicyRulesWithSubjectsBuilder)this.rules.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching rules. No match found.");
      } else {
         return this.setNewRuleLike(index, this.buildRule(index));
      }
   }

   public FlowSchemaSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FlowSchemaSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FlowSchemaSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FlowSchemaSpecFluent removeFromAdditionalProperties(Map map) {
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

   public FlowSchemaSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            FlowSchemaSpecFluent that = (FlowSchemaSpecFluent)o;
            if (!Objects.equals(this.distinguisherMethod, that.distinguisherMethod)) {
               return false;
            } else if (!Objects.equals(this.matchingPrecedence, that.matchingPrecedence)) {
               return false;
            } else if (!Objects.equals(this.priorityLevelConfiguration, that.priorityLevelConfiguration)) {
               return false;
            } else if (!Objects.equals(this.rules, that.rules)) {
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
      return Objects.hash(new Object[]{this.distinguisherMethod, this.matchingPrecedence, this.priorityLevelConfiguration, this.rules, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.distinguisherMethod != null) {
         sb.append("distinguisherMethod:");
         sb.append(this.distinguisherMethod + ",");
      }

      if (this.matchingPrecedence != null) {
         sb.append("matchingPrecedence:");
         sb.append(this.matchingPrecedence + ",");
      }

      if (this.priorityLevelConfiguration != null) {
         sb.append("priorityLevelConfiguration:");
         sb.append(this.priorityLevelConfiguration + ",");
      }

      if (this.rules != null && !this.rules.isEmpty()) {
         sb.append("rules:");
         sb.append(this.rules + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DistinguisherMethodNested extends FlowDistinguisherMethodFluent implements Nested {
      FlowDistinguisherMethodBuilder builder;

      DistinguisherMethodNested(FlowDistinguisherMethod item) {
         this.builder = new FlowDistinguisherMethodBuilder(this, item);
      }

      public Object and() {
         return FlowSchemaSpecFluent.this.withDistinguisherMethod(this.builder.build());
      }

      public Object endDistinguisherMethod() {
         return this.and();
      }
   }

   public class PriorityLevelConfigurationNested extends PriorityLevelConfigurationReferenceFluent implements Nested {
      PriorityLevelConfigurationReferenceBuilder builder;

      PriorityLevelConfigurationNested(PriorityLevelConfigurationReference item) {
         this.builder = new PriorityLevelConfigurationReferenceBuilder(this, item);
      }

      public Object and() {
         return FlowSchemaSpecFluent.this.withPriorityLevelConfiguration(this.builder.build());
      }

      public Object endPriorityLevelConfiguration() {
         return this.and();
      }
   }

   public class RulesNested extends PolicyRulesWithSubjectsFluent implements Nested {
      PolicyRulesWithSubjectsBuilder builder;
      int index;

      RulesNested(int index, PolicyRulesWithSubjects item) {
         this.index = index;
         this.builder = new PolicyRulesWithSubjectsBuilder(this, item);
      }

      public Object and() {
         return FlowSchemaSpecFluent.this.setToRules(this.index, this.builder.build());
      }

      public Object endRule() {
         return this.and();
      }
   }
}
