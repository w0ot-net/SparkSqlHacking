package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IngressClassSpecFluent extends BaseFluent {
   private String controller;
   private IngressClassParametersReferenceBuilder parameters;
   private Map additionalProperties;

   public IngressClassSpecFluent() {
   }

   public IngressClassSpecFluent(IngressClassSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressClassSpec instance) {
      instance = instance != null ? instance : new IngressClassSpec();
      if (instance != null) {
         this.withController(instance.getController());
         this.withParameters(instance.getParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getController() {
      return this.controller;
   }

   public IngressClassSpecFluent withController(String controller) {
      this.controller = controller;
      return this;
   }

   public boolean hasController() {
      return this.controller != null;
   }

   public IngressClassParametersReference buildParameters() {
      return this.parameters != null ? this.parameters.build() : null;
   }

   public IngressClassSpecFluent withParameters(IngressClassParametersReference parameters) {
      this._visitables.remove("parameters");
      if (parameters != null) {
         this.parameters = new IngressClassParametersReferenceBuilder(parameters);
         this._visitables.get("parameters").add(this.parameters);
      } else {
         this.parameters = null;
         this._visitables.get("parameters").remove(this.parameters);
      }

      return this;
   }

   public boolean hasParameters() {
      return this.parameters != null;
   }

   public IngressClassSpecFluent withNewParameters(String apiGroup, String kind, String name, String namespace, String scope) {
      return this.withParameters(new IngressClassParametersReference(apiGroup, kind, name, namespace, scope));
   }

   public ParametersNested withNewParameters() {
      return new ParametersNested((IngressClassParametersReference)null);
   }

   public ParametersNested withNewParametersLike(IngressClassParametersReference item) {
      return new ParametersNested(item);
   }

   public ParametersNested editParameters() {
      return this.withNewParametersLike((IngressClassParametersReference)Optional.ofNullable(this.buildParameters()).orElse((Object)null));
   }

   public ParametersNested editOrNewParameters() {
      return this.withNewParametersLike((IngressClassParametersReference)Optional.ofNullable(this.buildParameters()).orElse((new IngressClassParametersReferenceBuilder()).build()));
   }

   public ParametersNested editOrNewParametersLike(IngressClassParametersReference item) {
      return this.withNewParametersLike((IngressClassParametersReference)Optional.ofNullable(this.buildParameters()).orElse(item));
   }

   public IngressClassSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressClassSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressClassSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressClassSpecFluent removeFromAdditionalProperties(Map map) {
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

   public IngressClassSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressClassSpecFluent that = (IngressClassSpecFluent)o;
            if (!Objects.equals(this.controller, that.controller)) {
               return false;
            } else if (!Objects.equals(this.parameters, that.parameters)) {
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
      return Objects.hash(new Object[]{this.controller, this.parameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.controller != null) {
         sb.append("controller:");
         sb.append(this.controller + ",");
      }

      if (this.parameters != null) {
         sb.append("parameters:");
         sb.append(this.parameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParametersNested extends IngressClassParametersReferenceFluent implements Nested {
      IngressClassParametersReferenceBuilder builder;

      ParametersNested(IngressClassParametersReference item) {
         this.builder = new IngressClassParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return IngressClassSpecFluent.this.withParameters(this.builder.build());
      }

      public Object endParameters() {
         return this.and();
      }
   }
}
