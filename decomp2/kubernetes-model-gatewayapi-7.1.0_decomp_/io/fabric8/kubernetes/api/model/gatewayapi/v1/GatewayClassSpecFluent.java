package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GatewayClassSpecFluent extends BaseFluent {
   private String controllerName;
   private String description;
   private ParametersReferenceBuilder parametersRef;
   private Map additionalProperties;

   public GatewayClassSpecFluent() {
   }

   public GatewayClassSpecFluent(GatewayClassSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayClassSpec instance) {
      instance = instance != null ? instance : new GatewayClassSpec();
      if (instance != null) {
         this.withControllerName(instance.getControllerName());
         this.withDescription(instance.getDescription());
         this.withParametersRef(instance.getParametersRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getControllerName() {
      return this.controllerName;
   }

   public GatewayClassSpecFluent withControllerName(String controllerName) {
      this.controllerName = controllerName;
      return this;
   }

   public boolean hasControllerName() {
      return this.controllerName != null;
   }

   public String getDescription() {
      return this.description;
   }

   public GatewayClassSpecFluent withDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean hasDescription() {
      return this.description != null;
   }

   public ParametersReference buildParametersRef() {
      return this.parametersRef != null ? this.parametersRef.build() : null;
   }

   public GatewayClassSpecFluent withParametersRef(ParametersReference parametersRef) {
      this._visitables.remove("parametersRef");
      if (parametersRef != null) {
         this.parametersRef = new ParametersReferenceBuilder(parametersRef);
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

   public GatewayClassSpecFluent withNewParametersRef(String group, String kind, String name, String namespace) {
      return this.withParametersRef(new ParametersReference(group, kind, name, namespace));
   }

   public ParametersRefNested withNewParametersRef() {
      return new ParametersRefNested((ParametersReference)null);
   }

   public ParametersRefNested withNewParametersRefLike(ParametersReference item) {
      return new ParametersRefNested(item);
   }

   public ParametersRefNested editParametersRef() {
      return this.withNewParametersRefLike((ParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((Object)null));
   }

   public ParametersRefNested editOrNewParametersRef() {
      return this.withNewParametersRefLike((ParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((new ParametersReferenceBuilder()).build()));
   }

   public ParametersRefNested editOrNewParametersRefLike(ParametersReference item) {
      return this.withNewParametersRefLike((ParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse(item));
   }

   public GatewayClassSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayClassSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayClassSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayClassSpecFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayClassSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayClassSpecFluent that = (GatewayClassSpecFluent)o;
            if (!Objects.equals(this.controllerName, that.controllerName)) {
               return false;
            } else if (!Objects.equals(this.description, that.description)) {
               return false;
            } else if (!Objects.equals(this.parametersRef, that.parametersRef)) {
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
      return Objects.hash(new Object[]{this.controllerName, this.description, this.parametersRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.controllerName != null) {
         sb.append("controllerName:");
         sb.append(this.controllerName + ",");
      }

      if (this.description != null) {
         sb.append("description:");
         sb.append(this.description + ",");
      }

      if (this.parametersRef != null) {
         sb.append("parametersRef:");
         sb.append(this.parametersRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ParametersRefNested extends ParametersReferenceFluent implements Nested {
      ParametersReferenceBuilder builder;

      ParametersRefNested(ParametersReference item) {
         this.builder = new ParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return GatewayClassSpecFluent.this.withParametersRef(this.builder.build());
      }

      public Object endParametersRef() {
         return this.and();
      }
   }
}
