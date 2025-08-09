package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MutatingAdmissionPolicyBindingSpecFluent extends BaseFluent {
   private MatchResourcesBuilder matchResources;
   private ParamRefBuilder paramRef;
   private String policyName;
   private Map additionalProperties;

   public MutatingAdmissionPolicyBindingSpecFluent() {
   }

   public MutatingAdmissionPolicyBindingSpecFluent(MutatingAdmissionPolicyBindingSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MutatingAdmissionPolicyBindingSpec instance) {
      instance = instance != null ? instance : new MutatingAdmissionPolicyBindingSpec();
      if (instance != null) {
         this.withMatchResources(instance.getMatchResources());
         this.withParamRef(instance.getParamRef());
         this.withPolicyName(instance.getPolicyName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public MatchResources buildMatchResources() {
      return this.matchResources != null ? this.matchResources.build() : null;
   }

   public MutatingAdmissionPolicyBindingSpecFluent withMatchResources(MatchResources matchResources) {
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

   public MutatingAdmissionPolicyBindingSpecFluent withParamRef(ParamRef paramRef) {
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

   public MutatingAdmissionPolicyBindingSpecFluent withPolicyName(String policyName) {
      this.policyName = policyName;
      return this;
   }

   public boolean hasPolicyName() {
      return this.policyName != null;
   }

   public MutatingAdmissionPolicyBindingSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MutatingAdmissionPolicyBindingSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MutatingAdmissionPolicyBindingSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MutatingAdmissionPolicyBindingSpecFluent removeFromAdditionalProperties(Map map) {
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

   public MutatingAdmissionPolicyBindingSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            MutatingAdmissionPolicyBindingSpecFluent that = (MutatingAdmissionPolicyBindingSpecFluent)o;
            if (!Objects.equals(this.matchResources, that.matchResources)) {
               return false;
            } else if (!Objects.equals(this.paramRef, that.paramRef)) {
               return false;
            } else if (!Objects.equals(this.policyName, that.policyName)) {
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
      return Objects.hash(new Object[]{this.matchResources, this.paramRef, this.policyName, this.additionalProperties, super.hashCode()});
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
         return MutatingAdmissionPolicyBindingSpecFluent.this.withMatchResources(this.builder.build());
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
         return MutatingAdmissionPolicyBindingSpecFluent.this.withParamRef(this.builder.build());
      }

      public Object endParamRef() {
         return this.and();
      }
   }
}
