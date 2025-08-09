package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceClaimSpecFluent extends BaseFluent {
   private String allocationMode;
   private ResourceClaimParametersReferenceBuilder parametersRef;
   private String resourceClassName;
   private Map additionalProperties;

   public ResourceClaimSpecFluent() {
   }

   public ResourceClaimSpecFluent(ResourceClaimSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceClaimSpec instance) {
      instance = instance != null ? instance : new ResourceClaimSpec();
      if (instance != null) {
         this.withAllocationMode(instance.getAllocationMode());
         this.withParametersRef(instance.getParametersRef());
         this.withResourceClassName(instance.getResourceClassName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAllocationMode() {
      return this.allocationMode;
   }

   public ResourceClaimSpecFluent withAllocationMode(String allocationMode) {
      this.allocationMode = allocationMode;
      return this;
   }

   public boolean hasAllocationMode() {
      return this.allocationMode != null;
   }

   public ResourceClaimParametersReference buildParametersRef() {
      return this.parametersRef != null ? this.parametersRef.build() : null;
   }

   public ResourceClaimSpecFluent withParametersRef(ResourceClaimParametersReference parametersRef) {
      this._visitables.remove("parametersRef");
      if (parametersRef != null) {
         this.parametersRef = new ResourceClaimParametersReferenceBuilder(parametersRef);
         this._visitables.get("parametersRef").add(this.parametersRef);
      } else {
         this.parametersRef = null;
         this._visitables.get("parametersRef").remove(this.parametersRef);
      }

      return this;
   }

   public boolean hasParametersRef() {
      return this.parametersRef != null;
   }

   public ResourceClaimSpecFluent withNewParametersRef(String apiGroup, String kind, String name) {
      return this.withParametersRef(new ResourceClaimParametersReference(apiGroup, kind, name));
   }

   public ParametersRefNested withNewParametersRef() {
      return new ParametersRefNested((ResourceClaimParametersReference)null);
   }

   public ParametersRefNested withNewParametersRefLike(ResourceClaimParametersReference item) {
      return new ParametersRefNested(item);
   }

   public ParametersRefNested editParametersRef() {
      return this.withNewParametersRefLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((Object)null));
   }

   public ParametersRefNested editOrNewParametersRef() {
      return this.withNewParametersRefLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((new ResourceClaimParametersReferenceBuilder()).build()));
   }

   public ParametersRefNested editOrNewParametersRefLike(ResourceClaimParametersReference item) {
      return this.withNewParametersRefLike((ResourceClaimParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse(item));
   }

   public String getResourceClassName() {
      return this.resourceClassName;
   }

   public ResourceClaimSpecFluent withResourceClassName(String resourceClassName) {
      this.resourceClassName = resourceClassName;
      return this;
   }

   public boolean hasResourceClassName() {
      return this.resourceClassName != null;
   }

   public ResourceClaimSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceClaimSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceClaimSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceClaimSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceClaimSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceClaimSpecFluent that = (ResourceClaimSpecFluent)o;
            if (!Objects.equals(this.allocationMode, that.allocationMode)) {
               return false;
            } else if (!Objects.equals(this.parametersRef, that.parametersRef)) {
               return false;
            } else if (!Objects.equals(this.resourceClassName, that.resourceClassName)) {
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
      return Objects.hash(new Object[]{this.allocationMode, this.parametersRef, this.resourceClassName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allocationMode != null) {
         sb.append("allocationMode:");
         sb.append(this.allocationMode + ",");
      }

      if (this.parametersRef != null) {
         sb.append("parametersRef:");
         sb.append(this.parametersRef + ",");
      }

      if (this.resourceClassName != null) {
         sb.append("resourceClassName:");
         sb.append(this.resourceClassName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParametersRefNested extends ResourceClaimParametersReferenceFluent implements Nested {
      ResourceClaimParametersReferenceBuilder builder;

      ParametersRefNested(ResourceClaimParametersReference item) {
         this.builder = new ResourceClaimParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return ResourceClaimSpecFluent.this.withParametersRef(this.builder.build());
      }

      public Object endParametersRef() {
         return this.and();
      }
   }
}
