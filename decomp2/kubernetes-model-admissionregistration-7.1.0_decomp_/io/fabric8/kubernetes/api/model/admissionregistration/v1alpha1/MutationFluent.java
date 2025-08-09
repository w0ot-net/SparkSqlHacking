package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MutationFluent extends BaseFluent {
   private ApplyConfigurationBuilder applyConfiguration;
   private JSONPatchBuilder jsonPatch;
   private String patchType;
   private Map additionalProperties;

   public MutationFluent() {
   }

   public MutationFluent(Mutation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Mutation instance) {
      instance = instance != null ? instance : new Mutation();
      if (instance != null) {
         this.withApplyConfiguration(instance.getApplyConfiguration());
         this.withJsonPatch(instance.getJsonPatch());
         this.withPatchType(instance.getPatchType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ApplyConfiguration buildApplyConfiguration() {
      return this.applyConfiguration != null ? this.applyConfiguration.build() : null;
   }

   public MutationFluent withApplyConfiguration(ApplyConfiguration applyConfiguration) {
      this._visitables.remove("applyConfiguration");
      if (applyConfiguration != null) {
         this.applyConfiguration = new ApplyConfigurationBuilder(applyConfiguration);
         this._visitables.get("applyConfiguration").add(this.applyConfiguration);
      } else {
         this.applyConfiguration = null;
         this._visitables.get("applyConfiguration").remove(this.applyConfiguration);
      }

      return this;
   }

   public boolean hasApplyConfiguration() {
      return this.applyConfiguration != null;
   }

   public MutationFluent withNewApplyConfiguration(String expression) {
      return this.withApplyConfiguration(new ApplyConfiguration(expression));
   }

   public ApplyConfigurationNested withNewApplyConfiguration() {
      return new ApplyConfigurationNested((ApplyConfiguration)null);
   }

   public ApplyConfigurationNested withNewApplyConfigurationLike(ApplyConfiguration item) {
      return new ApplyConfigurationNested(item);
   }

   public ApplyConfigurationNested editApplyConfiguration() {
      return this.withNewApplyConfigurationLike((ApplyConfiguration)Optional.ofNullable(this.buildApplyConfiguration()).orElse((Object)null));
   }

   public ApplyConfigurationNested editOrNewApplyConfiguration() {
      return this.withNewApplyConfigurationLike((ApplyConfiguration)Optional.ofNullable(this.buildApplyConfiguration()).orElse((new ApplyConfigurationBuilder()).build()));
   }

   public ApplyConfigurationNested editOrNewApplyConfigurationLike(ApplyConfiguration item) {
      return this.withNewApplyConfigurationLike((ApplyConfiguration)Optional.ofNullable(this.buildApplyConfiguration()).orElse(item));
   }

   public JSONPatch buildJsonPatch() {
      return this.jsonPatch != null ? this.jsonPatch.build() : null;
   }

   public MutationFluent withJsonPatch(JSONPatch jsonPatch) {
      this._visitables.remove("jsonPatch");
      if (jsonPatch != null) {
         this.jsonPatch = new JSONPatchBuilder(jsonPatch);
         this._visitables.get("jsonPatch").add(this.jsonPatch);
      } else {
         this.jsonPatch = null;
         this._visitables.get("jsonPatch").remove(this.jsonPatch);
      }

      return this;
   }

   public boolean hasJsonPatch() {
      return this.jsonPatch != null;
   }

   public MutationFluent withNewJsonPatch(String expression) {
      return this.withJsonPatch(new JSONPatch(expression));
   }

   public JsonPatchNested withNewJsonPatch() {
      return new JsonPatchNested((JSONPatch)null);
   }

   public JsonPatchNested withNewJsonPatchLike(JSONPatch item) {
      return new JsonPatchNested(item);
   }

   public JsonPatchNested editJsonPatch() {
      return this.withNewJsonPatchLike((JSONPatch)Optional.ofNullable(this.buildJsonPatch()).orElse((Object)null));
   }

   public JsonPatchNested editOrNewJsonPatch() {
      return this.withNewJsonPatchLike((JSONPatch)Optional.ofNullable(this.buildJsonPatch()).orElse((new JSONPatchBuilder()).build()));
   }

   public JsonPatchNested editOrNewJsonPatchLike(JSONPatch item) {
      return this.withNewJsonPatchLike((JSONPatch)Optional.ofNullable(this.buildJsonPatch()).orElse(item));
   }

   public String getPatchType() {
      return this.patchType;
   }

   public MutationFluent withPatchType(String patchType) {
      this.patchType = patchType;
      return this;
   }

   public boolean hasPatchType() {
      return this.patchType != null;
   }

   public MutationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MutationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MutationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MutationFluent removeFromAdditionalProperties(Map map) {
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

   public MutationFluent withAdditionalProperties(Map additionalProperties) {
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
            MutationFluent that = (MutationFluent)o;
            if (!Objects.equals(this.applyConfiguration, that.applyConfiguration)) {
               return false;
            } else if (!Objects.equals(this.jsonPatch, that.jsonPatch)) {
               return false;
            } else if (!Objects.equals(this.patchType, that.patchType)) {
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
      return Objects.hash(new Object[]{this.applyConfiguration, this.jsonPatch, this.patchType, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.applyConfiguration != null) {
         sb.append("applyConfiguration:");
         sb.append(this.applyConfiguration + ",");
      }

      if (this.jsonPatch != null) {
         sb.append("jsonPatch:");
         sb.append(this.jsonPatch + ",");
      }

      if (this.patchType != null) {
         sb.append("patchType:");
         sb.append(this.patchType + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ApplyConfigurationNested extends ApplyConfigurationFluent implements Nested {
      ApplyConfigurationBuilder builder;

      ApplyConfigurationNested(ApplyConfiguration item) {
         this.builder = new ApplyConfigurationBuilder(this, item);
      }

      public Object and() {
         return MutationFluent.this.withApplyConfiguration(this.builder.build());
      }

      public Object endApplyConfiguration() {
         return this.and();
      }
   }

   public class JsonPatchNested extends JSONPatchFluent implements Nested {
      JSONPatchBuilder builder;

      JsonPatchNested(JSONPatch item) {
         this.builder = new JSONPatchBuilder(this, item);
      }

      public Object and() {
         return MutationFluent.this.withJsonPatch(this.builder.build());
      }

      public Object endJsonPatch() {
         return this.and();
      }
   }
}
