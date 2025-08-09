package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

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

public class MutatingAdmissionPolicySpecFluent extends BaseFluent {
   private String failurePolicy;
   private ArrayList matchConditions = new ArrayList();
   private MatchResourcesBuilder matchConstraints;
   private ArrayList mutations = new ArrayList();
   private ParamKindBuilder paramKind;
   private String reinvocationPolicy;
   private ArrayList variables = new ArrayList();
   private Map additionalProperties;

   public MutatingAdmissionPolicySpecFluent() {
   }

   public MutatingAdmissionPolicySpecFluent(MutatingAdmissionPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MutatingAdmissionPolicySpec instance) {
      instance = instance != null ? instance : new MutatingAdmissionPolicySpec();
      if (instance != null) {
         this.withFailurePolicy(instance.getFailurePolicy());
         this.withMatchConditions(instance.getMatchConditions());
         this.withMatchConstraints(instance.getMatchConstraints());
         this.withMutations(instance.getMutations());
         this.withParamKind(instance.getParamKind());
         this.withReinvocationPolicy(instance.getReinvocationPolicy());
         this.withVariables(instance.getVariables());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFailurePolicy() {
      return this.failurePolicy;
   }

   public MutatingAdmissionPolicySpecFluent withFailurePolicy(String failurePolicy) {
      this.failurePolicy = failurePolicy;
      return this;
   }

   public boolean hasFailurePolicy() {
      return this.failurePolicy != null;
   }

   public MutatingAdmissionPolicySpecFluent addToMatchConditions(int index, MatchCondition item) {
      if (this.matchConditions == null) {
         this.matchConditions = new ArrayList();
      }

      MatchConditionBuilder builder = new MatchConditionBuilder(item);
      if (index >= 0 && index < this.matchConditions.size()) {
         this._visitables.get("matchConditions").add(index, builder);
         this.matchConditions.add(index, builder);
      } else {
         this._visitables.get("matchConditions").add(builder);
         this.matchConditions.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent setToMatchConditions(int index, MatchCondition item) {
      if (this.matchConditions == null) {
         this.matchConditions = new ArrayList();
      }

      MatchConditionBuilder builder = new MatchConditionBuilder(item);
      if (index >= 0 && index < this.matchConditions.size()) {
         this._visitables.get("matchConditions").set(index, builder);
         this.matchConditions.set(index, builder);
      } else {
         this._visitables.get("matchConditions").add(builder);
         this.matchConditions.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addToMatchConditions(MatchCondition... items) {
      if (this.matchConditions == null) {
         this.matchConditions = new ArrayList();
      }

      for(MatchCondition item : items) {
         MatchConditionBuilder builder = new MatchConditionBuilder(item);
         this._visitables.get("matchConditions").add(builder);
         this.matchConditions.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addAllToMatchConditions(Collection items) {
      if (this.matchConditions == null) {
         this.matchConditions = new ArrayList();
      }

      for(MatchCondition item : items) {
         MatchConditionBuilder builder = new MatchConditionBuilder(item);
         this._visitables.get("matchConditions").add(builder);
         this.matchConditions.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent removeFromMatchConditions(MatchCondition... items) {
      if (this.matchConditions == null) {
         return this;
      } else {
         for(MatchCondition item : items) {
            MatchConditionBuilder builder = new MatchConditionBuilder(item);
            this._visitables.get("matchConditions").remove(builder);
            this.matchConditions.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeAllFromMatchConditions(Collection items) {
      if (this.matchConditions == null) {
         return this;
      } else {
         for(MatchCondition item : items) {
            MatchConditionBuilder builder = new MatchConditionBuilder(item);
            this._visitables.get("matchConditions").remove(builder);
            this.matchConditions.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeMatchingFromMatchConditions(Predicate predicate) {
      if (this.matchConditions == null) {
         return this;
      } else {
         Iterator<MatchConditionBuilder> each = this.matchConditions.iterator();
         List visitables = this._visitables.get("matchConditions");

         while(each.hasNext()) {
            MatchConditionBuilder builder = (MatchConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMatchConditions() {
      return this.matchConditions != null ? build(this.matchConditions) : null;
   }

   public MatchCondition buildMatchCondition(int index) {
      return ((MatchConditionBuilder)this.matchConditions.get(index)).build();
   }

   public MatchCondition buildFirstMatchCondition() {
      return ((MatchConditionBuilder)this.matchConditions.get(0)).build();
   }

   public MatchCondition buildLastMatchCondition() {
      return ((MatchConditionBuilder)this.matchConditions.get(this.matchConditions.size() - 1)).build();
   }

   public MatchCondition buildMatchingMatchCondition(Predicate predicate) {
      for(MatchConditionBuilder item : this.matchConditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMatchCondition(Predicate predicate) {
      for(MatchConditionBuilder item : this.matchConditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingAdmissionPolicySpecFluent withMatchConditions(List matchConditions) {
      if (this.matchConditions != null) {
         this._visitables.get("matchConditions").clear();
      }

      if (matchConditions != null) {
         this.matchConditions = new ArrayList();

         for(MatchCondition item : matchConditions) {
            this.addToMatchConditions(item);
         }
      } else {
         this.matchConditions = null;
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent withMatchConditions(MatchCondition... matchConditions) {
      if (this.matchConditions != null) {
         this.matchConditions.clear();
         this._visitables.remove("matchConditions");
      }

      if (matchConditions != null) {
         for(MatchCondition item : matchConditions) {
            this.addToMatchConditions(item);
         }
      }

      return this;
   }

   public boolean hasMatchConditions() {
      return this.matchConditions != null && !this.matchConditions.isEmpty();
   }

   public MutatingAdmissionPolicySpecFluent addNewMatchCondition(String expression, String name) {
      return this.addToMatchConditions(new MatchCondition(expression, name));
   }

   public MatchConditionsNested addNewMatchCondition() {
      return new MatchConditionsNested(-1, (MatchCondition)null);
   }

   public MatchConditionsNested addNewMatchConditionLike(MatchCondition item) {
      return new MatchConditionsNested(-1, item);
   }

   public MatchConditionsNested setNewMatchConditionLike(int index, MatchCondition item) {
      return new MatchConditionsNested(index, item);
   }

   public MatchConditionsNested editMatchCondition(int index) {
      if (this.matchConditions.size() <= index) {
         throw new RuntimeException("Can't edit matchConditions. Index exceeds size.");
      } else {
         return this.setNewMatchConditionLike(index, this.buildMatchCondition(index));
      }
   }

   public MatchConditionsNested editFirstMatchCondition() {
      if (this.matchConditions.size() == 0) {
         throw new RuntimeException("Can't edit first matchConditions. The list is empty.");
      } else {
         return this.setNewMatchConditionLike(0, this.buildMatchCondition(0));
      }
   }

   public MatchConditionsNested editLastMatchCondition() {
      int index = this.matchConditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last matchConditions. The list is empty.");
      } else {
         return this.setNewMatchConditionLike(index, this.buildMatchCondition(index));
      }
   }

   public MatchConditionsNested editMatchingMatchCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.matchConditions.size(); ++i) {
         if (predicate.test((MatchConditionBuilder)this.matchConditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching matchConditions. No match found.");
      } else {
         return this.setNewMatchConditionLike(index, this.buildMatchCondition(index));
      }
   }

   public MatchResources buildMatchConstraints() {
      return this.matchConstraints != null ? this.matchConstraints.build() : null;
   }

   public MutatingAdmissionPolicySpecFluent withMatchConstraints(MatchResources matchConstraints) {
      this._visitables.remove("matchConstraints");
      if (matchConstraints != null) {
         this.matchConstraints = new MatchResourcesBuilder(matchConstraints);
         this._visitables.get("matchConstraints").add(this.matchConstraints);
      } else {
         this.matchConstraints = null;
         this._visitables.get("matchConstraints").remove(this.matchConstraints);
      }

      return this;
   }

   public boolean hasMatchConstraints() {
      return this.matchConstraints != null;
   }

   public MatchConstraintsNested withNewMatchConstraints() {
      return new MatchConstraintsNested((MatchResources)null);
   }

   public MatchConstraintsNested withNewMatchConstraintsLike(MatchResources item) {
      return new MatchConstraintsNested(item);
   }

   public MatchConstraintsNested editMatchConstraints() {
      return this.withNewMatchConstraintsLike((MatchResources)Optional.ofNullable(this.buildMatchConstraints()).orElse((Object)null));
   }

   public MatchConstraintsNested editOrNewMatchConstraints() {
      return this.withNewMatchConstraintsLike((MatchResources)Optional.ofNullable(this.buildMatchConstraints()).orElse((new MatchResourcesBuilder()).build()));
   }

   public MatchConstraintsNested editOrNewMatchConstraintsLike(MatchResources item) {
      return this.withNewMatchConstraintsLike((MatchResources)Optional.ofNullable(this.buildMatchConstraints()).orElse(item));
   }

   public MutatingAdmissionPolicySpecFluent addToMutations(int index, Mutation item) {
      if (this.mutations == null) {
         this.mutations = new ArrayList();
      }

      MutationBuilder builder = new MutationBuilder(item);
      if (index >= 0 && index < this.mutations.size()) {
         this._visitables.get("mutations").add(index, builder);
         this.mutations.add(index, builder);
      } else {
         this._visitables.get("mutations").add(builder);
         this.mutations.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent setToMutations(int index, Mutation item) {
      if (this.mutations == null) {
         this.mutations = new ArrayList();
      }

      MutationBuilder builder = new MutationBuilder(item);
      if (index >= 0 && index < this.mutations.size()) {
         this._visitables.get("mutations").set(index, builder);
         this.mutations.set(index, builder);
      } else {
         this._visitables.get("mutations").add(builder);
         this.mutations.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addToMutations(Mutation... items) {
      if (this.mutations == null) {
         this.mutations = new ArrayList();
      }

      for(Mutation item : items) {
         MutationBuilder builder = new MutationBuilder(item);
         this._visitables.get("mutations").add(builder);
         this.mutations.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addAllToMutations(Collection items) {
      if (this.mutations == null) {
         this.mutations = new ArrayList();
      }

      for(Mutation item : items) {
         MutationBuilder builder = new MutationBuilder(item);
         this._visitables.get("mutations").add(builder);
         this.mutations.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent removeFromMutations(Mutation... items) {
      if (this.mutations == null) {
         return this;
      } else {
         for(Mutation item : items) {
            MutationBuilder builder = new MutationBuilder(item);
            this._visitables.get("mutations").remove(builder);
            this.mutations.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeAllFromMutations(Collection items) {
      if (this.mutations == null) {
         return this;
      } else {
         for(Mutation item : items) {
            MutationBuilder builder = new MutationBuilder(item);
            this._visitables.get("mutations").remove(builder);
            this.mutations.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeMatchingFromMutations(Predicate predicate) {
      if (this.mutations == null) {
         return this;
      } else {
         Iterator<MutationBuilder> each = this.mutations.iterator();
         List visitables = this._visitables.get("mutations");

         while(each.hasNext()) {
            MutationBuilder builder = (MutationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildMutations() {
      return this.mutations != null ? build(this.mutations) : null;
   }

   public Mutation buildMutation(int index) {
      return ((MutationBuilder)this.mutations.get(index)).build();
   }

   public Mutation buildFirstMutation() {
      return ((MutationBuilder)this.mutations.get(0)).build();
   }

   public Mutation buildLastMutation() {
      return ((MutationBuilder)this.mutations.get(this.mutations.size() - 1)).build();
   }

   public Mutation buildMatchingMutation(Predicate predicate) {
      for(MutationBuilder item : this.mutations) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingMutation(Predicate predicate) {
      for(MutationBuilder item : this.mutations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingAdmissionPolicySpecFluent withMutations(List mutations) {
      if (this.mutations != null) {
         this._visitables.get("mutations").clear();
      }

      if (mutations != null) {
         this.mutations = new ArrayList();

         for(Mutation item : mutations) {
            this.addToMutations(item);
         }
      } else {
         this.mutations = null;
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent withMutations(Mutation... mutations) {
      if (this.mutations != null) {
         this.mutations.clear();
         this._visitables.remove("mutations");
      }

      if (mutations != null) {
         for(Mutation item : mutations) {
            this.addToMutations(item);
         }
      }

      return this;
   }

   public boolean hasMutations() {
      return this.mutations != null && !this.mutations.isEmpty();
   }

   public MutationsNested addNewMutation() {
      return new MutationsNested(-1, (Mutation)null);
   }

   public MutationsNested addNewMutationLike(Mutation item) {
      return new MutationsNested(-1, item);
   }

   public MutationsNested setNewMutationLike(int index, Mutation item) {
      return new MutationsNested(index, item);
   }

   public MutationsNested editMutation(int index) {
      if (this.mutations.size() <= index) {
         throw new RuntimeException("Can't edit mutations. Index exceeds size.");
      } else {
         return this.setNewMutationLike(index, this.buildMutation(index));
      }
   }

   public MutationsNested editFirstMutation() {
      if (this.mutations.size() == 0) {
         throw new RuntimeException("Can't edit first mutations. The list is empty.");
      } else {
         return this.setNewMutationLike(0, this.buildMutation(0));
      }
   }

   public MutationsNested editLastMutation() {
      int index = this.mutations.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last mutations. The list is empty.");
      } else {
         return this.setNewMutationLike(index, this.buildMutation(index));
      }
   }

   public MutationsNested editMatchingMutation(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.mutations.size(); ++i) {
         if (predicate.test((MutationBuilder)this.mutations.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching mutations. No match found.");
      } else {
         return this.setNewMutationLike(index, this.buildMutation(index));
      }
   }

   public ParamKind buildParamKind() {
      return this.paramKind != null ? this.paramKind.build() : null;
   }

   public MutatingAdmissionPolicySpecFluent withParamKind(ParamKind paramKind) {
      this._visitables.remove("paramKind");
      if (paramKind != null) {
         this.paramKind = new ParamKindBuilder(paramKind);
         this._visitables.get("paramKind").add(this.paramKind);
      } else {
         this.paramKind = null;
         this._visitables.get("paramKind").remove(this.paramKind);
      }

      return this;
   }

   public boolean hasParamKind() {
      return this.paramKind != null;
   }

   public MutatingAdmissionPolicySpecFluent withNewParamKind(String apiVersion, String kind) {
      return this.withParamKind(new ParamKind(apiVersion, kind));
   }

   public ParamKindNested withNewParamKind() {
      return new ParamKindNested((ParamKind)null);
   }

   public ParamKindNested withNewParamKindLike(ParamKind item) {
      return new ParamKindNested(item);
   }

   public ParamKindNested editParamKind() {
      return this.withNewParamKindLike((ParamKind)Optional.ofNullable(this.buildParamKind()).orElse((Object)null));
   }

   public ParamKindNested editOrNewParamKind() {
      return this.withNewParamKindLike((ParamKind)Optional.ofNullable(this.buildParamKind()).orElse((new ParamKindBuilder()).build()));
   }

   public ParamKindNested editOrNewParamKindLike(ParamKind item) {
      return this.withNewParamKindLike((ParamKind)Optional.ofNullable(this.buildParamKind()).orElse(item));
   }

   public String getReinvocationPolicy() {
      return this.reinvocationPolicy;
   }

   public MutatingAdmissionPolicySpecFluent withReinvocationPolicy(String reinvocationPolicy) {
      this.reinvocationPolicy = reinvocationPolicy;
      return this;
   }

   public boolean hasReinvocationPolicy() {
      return this.reinvocationPolicy != null;
   }

   public MutatingAdmissionPolicySpecFluent addToVariables(int index, Variable item) {
      if (this.variables == null) {
         this.variables = new ArrayList();
      }

      VariableBuilder builder = new VariableBuilder(item);
      if (index >= 0 && index < this.variables.size()) {
         this._visitables.get("variables").add(index, builder);
         this.variables.add(index, builder);
      } else {
         this._visitables.get("variables").add(builder);
         this.variables.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent setToVariables(int index, Variable item) {
      if (this.variables == null) {
         this.variables = new ArrayList();
      }

      VariableBuilder builder = new VariableBuilder(item);
      if (index >= 0 && index < this.variables.size()) {
         this._visitables.get("variables").set(index, builder);
         this.variables.set(index, builder);
      } else {
         this._visitables.get("variables").add(builder);
         this.variables.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addToVariables(Variable... items) {
      if (this.variables == null) {
         this.variables = new ArrayList();
      }

      for(Variable item : items) {
         VariableBuilder builder = new VariableBuilder(item);
         this._visitables.get("variables").add(builder);
         this.variables.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addAllToVariables(Collection items) {
      if (this.variables == null) {
         this.variables = new ArrayList();
      }

      for(Variable item : items) {
         VariableBuilder builder = new VariableBuilder(item);
         this._visitables.get("variables").add(builder);
         this.variables.add(builder);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent removeFromVariables(Variable... items) {
      if (this.variables == null) {
         return this;
      } else {
         for(Variable item : items) {
            VariableBuilder builder = new VariableBuilder(item);
            this._visitables.get("variables").remove(builder);
            this.variables.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeAllFromVariables(Collection items) {
      if (this.variables == null) {
         return this;
      } else {
         for(Variable item : items) {
            VariableBuilder builder = new VariableBuilder(item);
            this._visitables.get("variables").remove(builder);
            this.variables.remove(builder);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeMatchingFromVariables(Predicate predicate) {
      if (this.variables == null) {
         return this;
      } else {
         Iterator<VariableBuilder> each = this.variables.iterator();
         List visitables = this._visitables.get("variables");

         while(each.hasNext()) {
            VariableBuilder builder = (VariableBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVariables() {
      return this.variables != null ? build(this.variables) : null;
   }

   public Variable buildVariable(int index) {
      return ((VariableBuilder)this.variables.get(index)).build();
   }

   public Variable buildFirstVariable() {
      return ((VariableBuilder)this.variables.get(0)).build();
   }

   public Variable buildLastVariable() {
      return ((VariableBuilder)this.variables.get(this.variables.size() - 1)).build();
   }

   public Variable buildMatchingVariable(Predicate predicate) {
      for(VariableBuilder item : this.variables) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVariable(Predicate predicate) {
      for(VariableBuilder item : this.variables) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingAdmissionPolicySpecFluent withVariables(List variables) {
      if (this.variables != null) {
         this._visitables.get("variables").clear();
      }

      if (variables != null) {
         this.variables = new ArrayList();

         for(Variable item : variables) {
            this.addToVariables(item);
         }
      } else {
         this.variables = null;
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent withVariables(Variable... variables) {
      if (this.variables != null) {
         this.variables.clear();
         this._visitables.remove("variables");
      }

      if (variables != null) {
         for(Variable item : variables) {
            this.addToVariables(item);
         }
      }

      return this;
   }

   public boolean hasVariables() {
      return this.variables != null && !this.variables.isEmpty();
   }

   public MutatingAdmissionPolicySpecFluent addNewVariable(String expression, String name) {
      return this.addToVariables(new Variable(expression, name));
   }

   public VariablesNested addNewVariable() {
      return new VariablesNested(-1, (Variable)null);
   }

   public VariablesNested addNewVariableLike(Variable item) {
      return new VariablesNested(-1, item);
   }

   public VariablesNested setNewVariableLike(int index, Variable item) {
      return new VariablesNested(index, item);
   }

   public VariablesNested editVariable(int index) {
      if (this.variables.size() <= index) {
         throw new RuntimeException("Can't edit variables. Index exceeds size.");
      } else {
         return this.setNewVariableLike(index, this.buildVariable(index));
      }
   }

   public VariablesNested editFirstVariable() {
      if (this.variables.size() == 0) {
         throw new RuntimeException("Can't edit first variables. The list is empty.");
      } else {
         return this.setNewVariableLike(0, this.buildVariable(0));
      }
   }

   public VariablesNested editLastVariable() {
      int index = this.variables.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last variables. The list is empty.");
      } else {
         return this.setNewVariableLike(index, this.buildVariable(index));
      }
   }

   public VariablesNested editMatchingVariable(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.variables.size(); ++i) {
         if (predicate.test((VariableBuilder)this.variables.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching variables. No match found.");
      } else {
         return this.setNewVariableLike(index, this.buildVariable(index));
      }
   }

   public MutatingAdmissionPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MutatingAdmissionPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public MutatingAdmissionPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            MutatingAdmissionPolicySpecFluent that = (MutatingAdmissionPolicySpecFluent)o;
            if (!Objects.equals(this.failurePolicy, that.failurePolicy)) {
               return false;
            } else if (!Objects.equals(this.matchConditions, that.matchConditions)) {
               return false;
            } else if (!Objects.equals(this.matchConstraints, that.matchConstraints)) {
               return false;
            } else if (!Objects.equals(this.mutations, that.mutations)) {
               return false;
            } else if (!Objects.equals(this.paramKind, that.paramKind)) {
               return false;
            } else if (!Objects.equals(this.reinvocationPolicy, that.reinvocationPolicy)) {
               return false;
            } else if (!Objects.equals(this.variables, that.variables)) {
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
      return Objects.hash(new Object[]{this.failurePolicy, this.matchConditions, this.matchConstraints, this.mutations, this.paramKind, this.reinvocationPolicy, this.variables, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.failurePolicy != null) {
         sb.append("failurePolicy:");
         sb.append(this.failurePolicy + ",");
      }

      if (this.matchConditions != null && !this.matchConditions.isEmpty()) {
         sb.append("matchConditions:");
         sb.append(this.matchConditions + ",");
      }

      if (this.matchConstraints != null) {
         sb.append("matchConstraints:");
         sb.append(this.matchConstraints + ",");
      }

      if (this.mutations != null && !this.mutations.isEmpty()) {
         sb.append("mutations:");
         sb.append(this.mutations + ",");
      }

      if (this.paramKind != null) {
         sb.append("paramKind:");
         sb.append(this.paramKind + ",");
      }

      if (this.reinvocationPolicy != null) {
         sb.append("reinvocationPolicy:");
         sb.append(this.reinvocationPolicy + ",");
      }

      if (this.variables != null && !this.variables.isEmpty()) {
         sb.append("variables:");
         sb.append(this.variables + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MatchConditionsNested extends MatchConditionFluent implements Nested {
      MatchConditionBuilder builder;
      int index;

      MatchConditionsNested(int index, MatchCondition item) {
         this.index = index;
         this.builder = new MatchConditionBuilder(this, item);
      }

      public Object and() {
         return MutatingAdmissionPolicySpecFluent.this.setToMatchConditions(this.index, this.builder.build());
      }

      public Object endMatchCondition() {
         return this.and();
      }
   }

   public class MatchConstraintsNested extends MatchResourcesFluent implements Nested {
      MatchResourcesBuilder builder;

      MatchConstraintsNested(MatchResources item) {
         this.builder = new MatchResourcesBuilder(this, item);
      }

      public Object and() {
         return MutatingAdmissionPolicySpecFluent.this.withMatchConstraints(this.builder.build());
      }

      public Object endMatchConstraints() {
         return this.and();
      }
   }

   public class MutationsNested extends MutationFluent implements Nested {
      MutationBuilder builder;
      int index;

      MutationsNested(int index, Mutation item) {
         this.index = index;
         this.builder = new MutationBuilder(this, item);
      }

      public Object and() {
         return MutatingAdmissionPolicySpecFluent.this.setToMutations(this.index, this.builder.build());
      }

      public Object endMutation() {
         return this.and();
      }
   }

   public class ParamKindNested extends ParamKindFluent implements Nested {
      ParamKindBuilder builder;

      ParamKindNested(ParamKind item) {
         this.builder = new ParamKindBuilder(this, item);
      }

      public Object and() {
         return MutatingAdmissionPolicySpecFluent.this.withParamKind(this.builder.build());
      }

      public Object endParamKind() {
         return this.and();
      }
   }

   public class VariablesNested extends VariableFluent implements Nested {
      VariableBuilder builder;
      int index;

      VariablesNested(int index, Variable item) {
         this.index = index;
         this.builder = new VariableBuilder(this, item);
      }

      public Object and() {
         return MutatingAdmissionPolicySpecFluent.this.setToVariables(this.index, this.builder.build());
      }

      public Object endVariable() {
         return this.and();
      }
   }
}
