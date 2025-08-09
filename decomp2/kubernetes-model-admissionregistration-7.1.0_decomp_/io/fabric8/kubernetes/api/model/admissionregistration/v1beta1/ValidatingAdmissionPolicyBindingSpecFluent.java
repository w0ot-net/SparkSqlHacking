package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

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

public class ValidatingAdmissionPolicyBindingSpecFluent extends BaseFluent {
   private MatchResourcesBuilder matchResources;
   private ParamRefBuilder paramRef;
   private String policyName;
   private List validationActions = new ArrayList();
   private Map additionalProperties;

   public ValidatingAdmissionPolicyBindingSpecFluent() {
   }

   public ValidatingAdmissionPolicyBindingSpecFluent(ValidatingAdmissionPolicyBindingSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ValidatingAdmissionPolicyBindingSpec instance) {
      instance = instance != null ? instance : new ValidatingAdmissionPolicyBindingSpec();
      if (instance != null) {
         this.withMatchResources(instance.getMatchResources());
         this.withParamRef(instance.getParamRef());
         this.withPolicyName(instance.getPolicyName());
         this.withValidationActions(instance.getValidationActions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MatchResources buildMatchResources() {
      return this.matchResources != null ? this.matchResources.build() : null;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent withMatchResources(MatchResources matchResources) {
      this._visitables.remove("matchResources");
      if (matchResources != null) {
         this.matchResources = new MatchResourcesBuilder(matchResources);
         this._visitables.get("matchResources").add(this.matchResources);
      } else {
         this.matchResources = null;
         this._visitables.get("matchResources").remove(this.matchResources);
      }

      return this;
   }

   public boolean hasMatchResources() {
      return this.matchResources != null;
   }

   public MatchResourcesNested withNewMatchResources() {
      return new MatchResourcesNested((MatchResources)null);
   }

   public MatchResourcesNested withNewMatchResourcesLike(MatchResources item) {
      return new MatchResourcesNested(item);
   }

   public MatchResourcesNested editMatchResources() {
      return this.withNewMatchResourcesLike((MatchResources)Optional.ofNullable(this.buildMatchResources()).orElse((Object)null));
   }

   public MatchResourcesNested editOrNewMatchResources() {
      return this.withNewMatchResourcesLike((MatchResources)Optional.ofNullable(this.buildMatchResources()).orElse((new MatchResourcesBuilder()).build()));
   }

   public MatchResourcesNested editOrNewMatchResourcesLike(MatchResources item) {
      return this.withNewMatchResourcesLike((MatchResources)Optional.ofNullable(this.buildMatchResources()).orElse(item));
   }

   public ParamRef buildParamRef() {
      return this.paramRef != null ? this.paramRef.build() : null;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent withParamRef(ParamRef paramRef) {
      this._visitables.remove("paramRef");
      if (paramRef != null) {
         this.paramRef = new ParamRefBuilder(paramRef);
         this._visitables.get("paramRef").add(this.paramRef);
      } else {
         this.paramRef = null;
         this._visitables.get("paramRef").remove(this.paramRef);
      }

      return this;
   }

   public boolean hasParamRef() {
      return this.paramRef != null;
   }

   public ParamRefNested withNewParamRef() {
      return new ParamRefNested((ParamRef)null);
   }

   public ParamRefNested withNewParamRefLike(ParamRef item) {
      return new ParamRefNested(item);
   }

   public ParamRefNested editParamRef() {
      return this.withNewParamRefLike((ParamRef)Optional.ofNullable(this.buildParamRef()).orElse((Object)null));
   }

   public ParamRefNested editOrNewParamRef() {
      return this.withNewParamRefLike((ParamRef)Optional.ofNullable(this.buildParamRef()).orElse((new ParamRefBuilder()).build()));
   }

   public ParamRefNested editOrNewParamRefLike(ParamRef item) {
      return this.withNewParamRefLike((ParamRef)Optional.ofNullable(this.buildParamRef()).orElse(item));
   }

   public String getPolicyName() {
      return this.policyName;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent withPolicyName(String policyName) {
      this.policyName = policyName;
      return this;
   }

   public boolean hasPolicyName() {
      return this.policyName != null;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent addToValidationActions(int index, String item) {
      if (this.validationActions == null) {
         this.validationActions = new ArrayList();
      }

      this.validationActions.add(index, item);
      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent setToValidationActions(int index, String item) {
      if (this.validationActions == null) {
         this.validationActions = new ArrayList();
      }

      this.validationActions.set(index, item);
      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent addToValidationActions(String... items) {
      if (this.validationActions == null) {
         this.validationActions = new ArrayList();
      }

      for(String item : items) {
         this.validationActions.add(item);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent addAllToValidationActions(Collection items) {
      if (this.validationActions == null) {
         this.validationActions = new ArrayList();
      }

      for(String item : items) {
         this.validationActions.add(item);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent removeFromValidationActions(String... items) {
      if (this.validationActions == null) {
         return this;
      } else {
         for(String item : items) {
            this.validationActions.remove(item);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicyBindingSpecFluent removeAllFromValidationActions(Collection items) {
      if (this.validationActions == null) {
         return this;
      } else {
         for(String item : items) {
            this.validationActions.remove(item);
         }

         return this;
      }
   }

   public List getValidationActions() {
      return this.validationActions;
   }

   public String getValidationAction(int index) {
      return (String)this.validationActions.get(index);
   }

   public String getFirstValidationAction() {
      return (String)this.validationActions.get(0);
   }

   public String getLastValidationAction() {
      return (String)this.validationActions.get(this.validationActions.size() - 1);
   }

   public String getMatchingValidationAction(Predicate predicate) {
      for(String item : this.validationActions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingValidationAction(Predicate predicate) {
      for(String item : this.validationActions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent withValidationActions(List validationActions) {
      if (validationActions != null) {
         this.validationActions = new ArrayList();

         for(String item : validationActions) {
            this.addToValidationActions(item);
         }
      } else {
         this.validationActions = null;
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent withValidationActions(String... validationActions) {
      if (this.validationActions != null) {
         this.validationActions.clear();
         this._visitables.remove("validationActions");
      }

      if (validationActions != null) {
         for(String item : validationActions) {
            this.addToValidationActions(item);
         }
      }

      return this;
   }

   public boolean hasValidationActions() {
      return this.validationActions != null && !this.validationActions.isEmpty();
   }

   public ValidatingAdmissionPolicyBindingSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidatingAdmissionPolicyBindingSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidatingAdmissionPolicyBindingSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ValidatingAdmissionPolicyBindingSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidatingAdmissionPolicyBindingSpecFluent that = (ValidatingAdmissionPolicyBindingSpecFluent)o;
            if (!Objects.equals(this.matchResources, that.matchResources)) {
               return false;
            } else if (!Objects.equals(this.paramRef, that.paramRef)) {
               return false;
            } else if (!Objects.equals(this.policyName, that.policyName)) {
               return false;
            } else if (!Objects.equals(this.validationActions, that.validationActions)) {
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
      return Objects.hash(new Object[]{this.matchResources, this.paramRef, this.policyName, this.validationActions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.matchResources != null) {
         sb.append("matchResources:");
         sb.append(this.matchResources + ",");
      }

      if (this.paramRef != null) {
         sb.append("paramRef:");
         sb.append(this.paramRef + ",");
      }

      if (this.policyName != null) {
         sb.append("policyName:");
         sb.append(this.policyName + ",");
      }

      if (this.validationActions != null && !this.validationActions.isEmpty()) {
         sb.append("validationActions:");
         sb.append(this.validationActions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MatchResourcesNested extends MatchResourcesFluent implements Nested {
      MatchResourcesBuilder builder;

      MatchResourcesNested(MatchResources item) {
         this.builder = new MatchResourcesBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicyBindingSpecFluent.this.withMatchResources(this.builder.build());
      }

      public Object endMatchResources() {
         return this.and();
      }
   }

   public class ParamRefNested extends ParamRefFluent implements Nested {
      ParamRefBuilder builder;

      ParamRefNested(ParamRef item) {
         this.builder = new ParamRefBuilder(this, item);
      }

      public Object and() {
         return ValidatingAdmissionPolicyBindingSpecFluent.this.withParamRef(this.builder.build());
      }

      public Object endParamRef() {
         return this.and();
      }
   }
}
