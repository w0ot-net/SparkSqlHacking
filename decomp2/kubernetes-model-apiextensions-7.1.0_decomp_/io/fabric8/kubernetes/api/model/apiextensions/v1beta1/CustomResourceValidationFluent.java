package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomResourceValidationFluent extends BaseFluent {
   private JSONSchemaPropsBuilder openAPIV3Schema;
   private Map additionalProperties;

   public CustomResourceValidationFluent() {
   }

   public CustomResourceValidationFluent(CustomResourceValidation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceValidation instance) {
      instance = instance != null ? instance : new CustomResourceValidation();
      if (instance != null) {
         this.withOpenAPIV3Schema(instance.getOpenAPIV3Schema());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public JSONSchemaProps buildOpenAPIV3Schema() {
      return this.openAPIV3Schema != null ? this.openAPIV3Schema.build() : null;
   }

   public CustomResourceValidationFluent withOpenAPIV3Schema(JSONSchemaProps openAPIV3Schema) {
      this._visitables.remove("openAPIV3Schema");
      if (openAPIV3Schema != null) {
         this.openAPIV3Schema = new JSONSchemaPropsBuilder(openAPIV3Schema);
         this._visitables.get("openAPIV3Schema").add(this.openAPIV3Schema);
      } else {
         this.openAPIV3Schema = null;
         this._visitables.get("openAPIV3Schema").remove(this.openAPIV3Schema);
      }

      return this;
   }

   public boolean hasOpenAPIV3Schema() {
      return this.openAPIV3Schema != null;
   }

   public OpenAPIV3SchemaNested withNewOpenAPIV3Schema() {
      return new OpenAPIV3SchemaNested((JSONSchemaProps)null);
   }

   public OpenAPIV3SchemaNested withNewOpenAPIV3SchemaLike(JSONSchemaProps item) {
      return new OpenAPIV3SchemaNested(item);
   }

   public OpenAPIV3SchemaNested editOpenAPIV3Schema() {
      return this.withNewOpenAPIV3SchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildOpenAPIV3Schema()).orElse((Object)null));
   }

   public OpenAPIV3SchemaNested editOrNewOpenAPIV3Schema() {
      return this.withNewOpenAPIV3SchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildOpenAPIV3Schema()).orElse((new JSONSchemaPropsBuilder()).build()));
   }

   public OpenAPIV3SchemaNested editOrNewOpenAPIV3SchemaLike(JSONSchemaProps item) {
      return this.withNewOpenAPIV3SchemaLike((JSONSchemaProps)Optional.ofNullable(this.buildOpenAPIV3Schema()).orElse(item));
   }

   public CustomResourceValidationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceValidationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceValidationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceValidationFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceValidationFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceValidationFluent that = (CustomResourceValidationFluent)o;
            if (!Objects.equals(this.openAPIV3Schema, that.openAPIV3Schema)) {
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
      return Objects.hash(new Object[]{this.openAPIV3Schema, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.openAPIV3Schema != null) {
         sb.append("openAPIV3Schema:");
         sb.append(this.openAPIV3Schema + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OpenAPIV3SchemaNested extends JSONSchemaPropsFluent implements Nested {
      JSONSchemaPropsBuilder builder;

      OpenAPIV3SchemaNested(JSONSchemaProps item) {
         this.builder = new JSONSchemaPropsBuilder(this, item);
      }

      public Object and() {
         return CustomResourceValidationFluent.this.withOpenAPIV3Schema(this.builder.build());
      }

      public Object endOpenAPIV3Schema() {
         return this.and();
      }
   }
}
