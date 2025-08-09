package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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

public class ValidatingAdmissionPolicySpecFluent extends BaseFluent {
   private ArrayList auditAnnotations = new ArrayList();
   private String failurePolicy;
   private ArrayList matchConditions = new ArrayList();
   private MatchResourcesBuilder matchConstraints;
   private ParamKindBuilder paramKind;
   private ArrayList validations = new ArrayList();
   private ArrayList variables = new ArrayList();
   private Map additionalProperties;

   public ValidatingAdmissionPolicySpecFluent() {
   }

   public ValidatingAdmissionPolicySpecFluent(ValidatingAdmissionPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ValidatingAdmissionPolicySpec instance) {
      instance = instance != null ? instance : new ValidatingAdmissionPolicySpec();
      if (instance != null) {
         this.withAuditAnnotations(instance.getAuditAnnotations());
         this.withFailurePolicy(instance.getFailurePolicy());
         this.withMatchConditions(instance.getMatchConditions());
         this.withMatchConstraints(instance.getMatchConstraints());
         this.withParamKind(instance.getParamKind());
         this.withValidations(instance.getValidations());
         this.withVariables(instance.getVariables());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ValidatingAdmissionPolicySpecFluent addToAuditAnnotations(int index, AuditAnnotation item) {
      if (this.auditAnnotations == null) {
         this.auditAnnotations = new ArrayList();
      }

      AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
      if (index >= 0 && index < this.auditAnnotations.size()) {
         this._visitables.get("auditAnnotations").add(index, builder);
         this.auditAnnotations.add(index, builder);
      } else {
         this._visitables.get("auditAnnotations").add(builder);
         this.auditAnnotations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent setToAuditAnnotations(int index, AuditAnnotation item) {
      if (this.auditAnnotations == null) {
         this.auditAnnotations = new ArrayList();
      }

      AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
      if (index >= 0 && index < this.auditAnnotations.size()) {
         this._visitables.get("auditAnnotations").set(index, builder);
         this.auditAnnotations.set(index, builder);
      } else {
         this._visitables.get("auditAnnotations").add(builder);
         this.auditAnnotations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent addToAuditAnnotations(AuditAnnotation... items) {
      if (this.auditAnnotations == null) {
         this.auditAnnotations = new ArrayList();
      }

      for(AuditAnnotation item : items) {
         AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
         this._visitables.get("auditAnnotations").add(builder);
         this.auditAnnotations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent addAllToAuditAnnotations(Collection items) {
      if (this.auditAnnotations == null) {
         this.auditAnnotations = new ArrayList();
      }

      for(AuditAnnotation item : items) {
         AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
         this._visitables.get("auditAnnotations").add(builder);
         this.auditAnnotations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent removeFromAuditAnnotations(AuditAnnotation... items) {
      if (this.auditAnnotations == null) {
         return this;
      } else {
         for(AuditAnnotation item : items) {
            AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
            this._visitables.get("auditAnnotations").remove(builder);
            this.auditAnnotations.remove(builder);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicySpecFluent removeAllFromAuditAnnotations(Collection items) {
      if (this.auditAnnotations == null) {
         return this;
      } else {
         for(AuditAnnotation item : items) {
            AuditAnnotationBuilder builder = new AuditAnnotationBuilder(item);
            this._visitables.get("auditAnnotations").remove(builder);
            this.auditAnnotations.remove(builder);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicySpecFluent removeMatchingFromAuditAnnotations(Predicate predicate) {
      if (this.auditAnnotations == null) {
         return this;
      } else {
         Iterator<AuditAnnotationBuilder> each = this.auditAnnotations.iterator();
         List visitables = this._visitables.get("auditAnnotations");

         while(each.hasNext()) {
            AuditAnnotationBuilder builder = (AuditAnnotationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAuditAnnotations() {
      return this.auditAnnotations != null ? build(this.auditAnnotations) : null;
   }

   public AuditAnnotation buildAuditAnnotation(int index) {
      return ((AuditAnnotationBuilder)this.auditAnnotations.get(index)).build();
   }

   public AuditAnnotation buildFirstAuditAnnotation() {
      return ((AuditAnnotationBuilder)this.auditAnnotations.get(0)).build();
   }

   public AuditAnnotation buildLastAuditAnnotation() {
      return ((AuditAnnotationBuilder)this.auditAnnotations.get(this.auditAnnotations.size() - 1)).build();
   }

   public AuditAnnotation buildMatchingAuditAnnotation(Predicate predicate) {
      for(AuditAnnotationBuilder item : this.auditAnnotations) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAuditAnnotation(Predicate predicate) {
      for(AuditAnnotationBuilder item : this.auditAnnotations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ValidatingAdmissionPolicySpecFluent withAuditAnnotations(List auditAnnotations) {
      if (this.auditAnnotations != null) {
         this._visitables.get("auditAnnotations").clear();
      }

      if (auditAnnotations != null) {
         this.auditAnnotations = new ArrayList();

         for(AuditAnnotation item : auditAnnotations) {
            this.addToAuditAnnotations(item);
         }
      } else {
         this.auditAnnotations = null;
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent withAuditAnnotations(AuditAnnotation... auditAnnotations) {
      if (this.auditAnnotations != null) {
         this.auditAnnotations.clear();
         this._visitables.remove("auditAnnotations");
      }

      if (auditAnnotations != null) {
         for(AuditAnnotation item : auditAnnotations) {
            this.addToAuditAnnotations(item);
         }
      }

      return this;
   }

   public boolean hasAuditAnnotations() {
      return this.auditAnnotations != null && !this.auditAnnotations.isEmpty();
   }

   public ValidatingAdmissionPolicySpecFluent addNewAuditAnnotation(String key, String valueExpression) {
      return this.addToAuditAnnotations(new AuditAnnotation(key, valueExpression));
   }

   public AuditAnnotationsNested addNewAuditAnnotation() {
      return new AuditAnnotationsNested(-1, (AuditAnnotation)null);
   }

   public AuditAnnotationsNested addNewAuditAnnotationLike(AuditAnnotation item) {
      return new AuditAnnotationsNested(-1, item);
   }

   public AuditAnnotationsNested setNewAuditAnnotationLike(int index, AuditAnnotation item) {
      return new AuditAnnotationsNested(index, item);
   }

   public AuditAnnotationsNested editAuditAnnotation(int index) {
      if (this.auditAnnotations.size() <= index) {
         throw new RuntimeException("Can't edit auditAnnotations. Index exceeds size.");
      } else {
         return this.setNewAuditAnnotationLike(index, this.buildAuditAnnotation(index));
      }
   }

   public AuditAnnotationsNested editFirstAuditAnnotation() {
      if (this.auditAnnotations.size() == 0) {
         throw new RuntimeException("Can't edit first auditAnnotations. The list is empty.");
      } else {
         return this.setNewAuditAnnotationLike(0, this.buildAuditAnnotation(0));
      }
   }

   public AuditAnnotationsNested editLastAuditAnnotation() {
      int index = this.auditAnnotations.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last auditAnnotations. The list is empty.");
      } else {
         return this.setNewAuditAnnotationLike(index, this.buildAuditAnnotation(index));
      }
   }

   public AuditAnnotationsNested editMatchingAuditAnnotation(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.auditAnnotations.size(); ++i) {
         if (predicate.test((AuditAnnotationBuilder)this.auditAnnotations.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching auditAnnotations. No match found.");
      } else {
         return this.setNewAuditAnnotationLike(index, this.buildAuditAnnotation(index));
      }
   }

   public String getFailurePolicy() {
      return this.failurePolicy;
   }

   public ValidatingAdmissionPolicySpecFluent withFailurePolicy(String failurePolicy) {
      this.failurePolicy = failurePolicy;
      return this;
   }

   public boolean hasFailurePolicy() {
      return this.failurePolicy != null;
   }

   public ValidatingAdmissionPolicySpecFluent addToMatchConditions(int index, MatchCondition item) {
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

   public ValidatingAdmissionPolicySpecFluent setToMatchConditions(int index, MatchCondition item) {
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

   public ValidatingAdmissionPolicySpecFluent addToMatchConditions(MatchCondition... items) {
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

   public ValidatingAdmissionPolicySpecFluent addAllToMatchConditions(Collection items) {
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

   public ValidatingAdmissionPolicySpecFluent removeFromMatchConditions(MatchCondition... items) {
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

   public ValidatingAdmissionPolicySpecFluent removeAllFromMatchConditions(Collection items) {
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

   public ValidatingAdmissionPolicySpecFluent removeMatchingFromMatchConditions(Predicate predicate) {
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

   public ValidatingAdmissionPolicySpecFluent withMatchConditions(List matchConditions) {
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

   public ValidatingAdmissionPolicySpecFluent withMatchConditions(MatchCondition... matchConditions) {
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

   public ValidatingAdmissionPolicySpecFluent addNewMatchCondition(String expression, String name) {
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

   public ValidatingAdmissionPolicySpecFluent withMatchConstraints(MatchResources matchConstraints) {
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

   public ParamKind buildParamKind() {
      return this.paramKind != null ? this.paramKind.build() : null;
   }

   public ValidatingAdmissionPolicySpecFluent withParamKind(ParamKind paramKind) {
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

   public ValidatingAdmissionPolicySpecFluent withNewParamKind(String apiVersion, String kind) {
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

   public ValidatingAdmissionPolicySpecFluent addToValidations(int index, Validation item) {
      if (this.validations == null) {
         this.validations = new ArrayList();
      }

      ValidationBuilder builder = new ValidationBuilder(item);
      if (index >= 0 && index < this.validations.size()) {
         this._visitables.get("validations").add(index, builder);
         this.validations.add(index, builder);
      } else {
         this._visitables.get("validations").add(builder);
         this.validations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent setToValidations(int index, Validation item) {
      if (this.validations == null) {
         this.validations = new ArrayList();
      }

      ValidationBuilder builder = new ValidationBuilder(item);
      if (index >= 0 && index < this.validations.size()) {
         this._visitables.get("validations").set(index, builder);
         this.validations.set(index, builder);
      } else {
         this._visitables.get("validations").add(builder);
         this.validations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent addToValidations(Validation... items) {
      if (this.validations == null) {
         this.validations = new ArrayList();
      }

      for(Validation item : items) {
         ValidationBuilder builder = new ValidationBuilder(item);
         this._visitables.get("validations").add(builder);
         this.validations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent addAllToValidations(Collection items) {
      if (this.validations == null) {
         this.validations = new ArrayList();
      }

      for(Validation item : items) {
         ValidationBuilder builder = new ValidationBuilder(item);
         this._visitables.get("validations").add(builder);
         this.validations.add(builder);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent removeFromValidations(Validation... items) {
      if (this.validations == null) {
         return this;
      } else {
         for(Validation item : items) {
            ValidationBuilder builder = new ValidationBuilder(item);
            this._visitables.get("validations").remove(builder);
            this.validations.remove(builder);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicySpecFluent removeAllFromValidations(Collection items) {
      if (this.validations == null) {
         return this;
      } else {
         for(Validation item : items) {
            ValidationBuilder builder = new ValidationBuilder(item);
            this._visitables.get("validations").remove(builder);
            this.validations.remove(builder);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicySpecFluent removeMatchingFromValidations(Predicate predicate) {
      if (this.validations == null) {
         return this;
      } else {
         Iterator<ValidationBuilder> each = this.validations.iterator();
         List visitables = this._visitables.get("validations");

         while(each.hasNext()) {
            ValidationBuilder builder = (ValidationBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildValidations() {
      return this.validations != null ? build(this.validations) : null;
   }

   public Validation buildValidation(int index) {
      return ((ValidationBuilder)this.validations.get(index)).build();
   }

   public Validation buildFirstValidation() {
      return ((ValidationBuilder)this.validations.get(0)).build();
   }

   public Validation buildLastValidation() {
      return ((ValidationBuilder)this.validations.get(this.validations.size() - 1)).build();
   }

   public Validation buildMatchingValidation(Predicate predicate) {
      for(ValidationBuilder item : this.validations) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingValidation(Predicate predicate) {
      for(ValidationBuilder item : this.validations) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ValidatingAdmissionPolicySpecFluent withValidations(List validations) {
      if (this.validations != null) {
         this._visitables.get("validations").clear();
      }

      if (validations != null) {
         this.validations = new ArrayList();

         for(Validation item : validations) {
            this.addToValidations(item);
         }
      } else {
         this.validations = null;
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent withValidations(Validation... validations) {
      if (this.validations != null) {
         this.validations.clear();
         this._visitables.remove("validations");
      }

      if (validations != null) {
         for(Validation item : validations) {
            this.addToValidations(item);
         }
      }

      return this;
   }

   public boolean hasValidations() {
      return this.validations != null && !this.validations.isEmpty();
   }

   public ValidatingAdmissionPolicySpecFluent addNewValidation(String expression, String message, String messageExpression, String reason) {
      return this.addToValidations(new Validation(expression, message, messageExpression, reason));
   }

   public ValidationsNested addNewValidation() {
      return new ValidationsNested(-1, (Validation)null);
   }

   public ValidationsNested addNewValidationLike(Validation item) {
      return new ValidationsNested(-1, item);
   }

   public ValidationsNested setNewValidationLike(int index, Validation item) {
      return new ValidationsNested(index, item);
   }

   public ValidationsNested editValidation(int index) {
      if (this.validations.size() <= index) {
         throw new RuntimeException("Can't edit validations. Index exceeds size.");
      } else {
         return this.setNewValidationLike(index, this.buildValidation(index));
      }
   }

   public ValidationsNested editFirstValidation() {
      if (this.validations.size() == 0) {
         throw new RuntimeException("Can't edit first validations. The list is empty.");
      } else {
         return this.setNewValidationLike(0, this.buildValidation(0));
      }
   }

   public ValidationsNested editLastValidation() {
      int index = this.validations.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last validations. The list is empty.");
      } else {
         return this.setNewValidationLike(index, this.buildValidation(index));
      }
   }

   public ValidationsNested editMatchingValidation(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.validations.size(); ++i) {
         if (predicate.test((ValidationBuilder)this.validations.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching validations. No match found.");
      } else {
         return this.setNewValidationLike(index, this.buildValidation(index));
      }
   }

   public ValidatingAdmissionPolicySpecFluent addToVariables(int index, Variable item) {
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

   public ValidatingAdmissionPolicySpecFluent setToVariables(int index, Variable item) {
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

   public ValidatingAdmissionPolicySpecFluent addToVariables(Variable... items) {
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

   public ValidatingAdmissionPolicySpecFluent addAllToVariables(Collection items) {
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

   public ValidatingAdmissionPolicySpecFluent removeFromVariables(Variable... items) {
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

   public ValidatingAdmissionPolicySpecFluent removeAllFromVariables(Collection items) {
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

   public ValidatingAdmissionPolicySpecFluent removeMatchingFromVariables(Predicate predicate) {
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

   public ValidatingAdmissionPolicySpecFluent withVariables(List variables) {
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

   public ValidatingAdmissionPolicySpecFluent withVariables(Variable... variables) {
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

   public ValidatingAdmissionPolicySpecFluent addNewVariable(String expression, String name) {
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

   public ValidatingAdmissionPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidatingAdmissionPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public ValidatingAdmissionPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidatingAdmissionPolicySpecFluent that = (ValidatingAdmissionPolicySpecFluent)o;
            if (!Objects.equals(this.auditAnnotations, that.auditAnnotations)) {
               return false;
            } else if (!Objects.equals(this.failurePolicy, that.failurePolicy)) {
               return false;
            } else if (!Objects.equals(this.matchConditions, that.matchConditions)) {
               return false;
            } else if (!Objects.equals(this.matchConstraints, that.matchConstraints)) {
               return false;
            } else if (!Objects.equals(this.paramKind, that.paramKind)) {
               return false;
            } else if (!Objects.equals(this.validations, that.validations)) {
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
      return Objects.hash(new Object[]{this.auditAnnotations, this.failurePolicy, this.matchConditions, this.matchConstraints, this.paramKind, this.validations, this.variables, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.auditAnnotations != null && !this.auditAnnotations.isEmpty()) {
         sb.append("auditAnnotations:");
         sb.append(this.auditAnnotations + ",");
      }

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

      if (this.paramKind != null) {
         sb.append("paramKind:");
         sb.append(this.paramKind + ",");
      }

      if (this.validations != null && !this.validations.isEmpty()) {
         sb.append("validations:");
         sb.append(this.validations + ",");
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

   public class AuditAnnotationsNested extends AuditAnnotationFluent implements Nested {
      AuditAnnotationBuilder builder;
      int index;

      AuditAnnotationsNested(int index, AuditAnnotation item) {
         this.index = index;
         this.builder = new AuditAnnotationBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicySpecFluent.this.setToAuditAnnotations(this.index, this.builder.build());
      }

      public Object endAuditAnnotation() {
         return this.and();
      }
   }

   public class MatchConditionsNested extends MatchConditionFluent implements Nested {
      MatchConditionBuilder builder;
      int index;

      MatchConditionsNested(int index, MatchCondition item) {
         this.index = index;
         this.builder = new MatchConditionBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicySpecFluent.this.setToMatchConditions(this.index, this.builder.build());
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
         return ValidatingAdmissionPolicySpecFluent.this.withMatchConstraints(this.builder.build());
      }

      public Object endMatchConstraints() {
         return this.and();
      }
   }

   public class ParamKindNested extends ParamKindFluent implements Nested {
      ParamKindBuilder builder;

      ParamKindNested(ParamKind item) {
         this.builder = new ParamKindBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicySpecFluent.this.withParamKind(this.builder.build());
      }

      public Object endParamKind() {
         return this.and();
      }
   }

   public class ValidationsNested extends ValidationFluent implements Nested {
      ValidationBuilder builder;
      int index;

      ValidationsNested(int index, Validation item) {
         this.index = index;
         this.builder = new ValidationBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicySpecFluent.this.setToValidations(this.index, this.builder.build());
      }

      public Object endValidation() {
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
         return ValidatingAdmissionPolicySpecFluent.this.setToVariables(this.index, this.builder.build());
      }

      public Object endVariable() {
         return this.and();
      }
   }
}
