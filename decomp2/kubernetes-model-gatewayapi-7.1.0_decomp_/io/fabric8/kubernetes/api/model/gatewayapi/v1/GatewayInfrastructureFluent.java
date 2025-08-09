package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GatewayInfrastructureFluent extends BaseFluent {
   private Map annotations;
   private Map labels;
   private LocalParametersReferenceBuilder parametersRef;
   private Map additionalProperties;

   public GatewayInfrastructureFluent() {
   }

   public GatewayInfrastructureFluent(GatewayInfrastructure instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayInfrastructure instance) {
      instance = instance != null ? instance : new GatewayInfrastructure();
      if (instance != null) {
         this.withAnnotations(instance.getAnnotations());
         this.withLabels(instance.getLabels());
         this.withParametersRef(instance.getParametersRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GatewayInfrastructureFluent addToAnnotations(String key, String value) {
      if (this.annotations == null && key != null && value != null) {
         this.annotations = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.annotations.put(key, value);
      }

      return this;
   }

   public GatewayInfrastructureFluent addToAnnotations(Map map) {
      if (this.annotations == null && map != null) {
         this.annotations = new LinkedHashMap();
      }

      if (map != null) {
         this.annotations.putAll(map);
      }

      return this;
   }

   public GatewayInfrastructureFluent removeFromAnnotations(String key) {
      if (this.annotations == null) {
         return this;
      } else {
         if (key != null && this.annotations != null) {
            this.annotations.remove(key);
         }

         return this;
      }
   }

   public GatewayInfrastructureFluent removeFromAnnotations(Map map) {
      if (this.annotations == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.annotations != null) {
                  this.annotations.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAnnotations() {
      return this.annotations;
   }

   public GatewayInfrastructureFluent withAnnotations(Map annotations) {
      if (annotations == null) {
         this.annotations = null;
      } else {
         this.annotations = new LinkedHashMap(annotations);
      }

      return this;
   }

   public boolean hasAnnotations() {
      return this.annotations != null;
   }

   public GatewayInfrastructureFluent addToLabels(String key, String value) {
      if (this.labels == null && key != null && value != null) {
         this.labels = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.labels.put(key, value);
      }

      return this;
   }

   public GatewayInfrastructureFluent addToLabels(Map map) {
      if (this.labels == null && map != null) {
         this.labels = new LinkedHashMap();
      }

      if (map != null) {
         this.labels.putAll(map);
      }

      return this;
   }

   public GatewayInfrastructureFluent removeFromLabels(String key) {
      if (this.labels == null) {
         return this;
      } else {
         if (key != null && this.labels != null) {
            this.labels.remove(key);
         }

         return this;
      }
   }

   public GatewayInfrastructureFluent removeFromLabels(Map map) {
      if (this.labels == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.labels != null) {
                  this.labels.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getLabels() {
      return this.labels;
   }

   public GatewayInfrastructureFluent withLabels(Map labels) {
      if (labels == null) {
         this.labels = null;
      } else {
         this.labels = new LinkedHashMap(labels);
      }

      return this;
   }

   public boolean hasLabels() {
      return this.labels != null;
   }

   public LocalParametersReference buildParametersRef() {
      return this.parametersRef != null ? this.parametersRef.build() : null;
   }

   public GatewayInfrastructureFluent withParametersRef(LocalParametersReference parametersRef) {
      this._visitables.remove("parametersRef");
      if (parametersRef != null) {
         this.parametersRef = new LocalParametersReferenceBuilder(parametersRef);
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

   public GatewayInfrastructureFluent withNewParametersRef(String group, String kind, String name) {
      return this.withParametersRef(new LocalParametersReference(group, kind, name));
   }

   public ParametersRefNested withNewParametersRef() {
      return new ParametersRefNested((LocalParametersReference)null);
   }

   public ParametersRefNested withNewParametersRefLike(LocalParametersReference item) {
      return new ParametersRefNested(item);
   }

   public ParametersRefNested editParametersRef() {
      return this.withNewParametersRefLike((LocalParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((Object)null));
   }

   public ParametersRefNested editOrNewParametersRef() {
      return this.withNewParametersRefLike((LocalParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse((new LocalParametersReferenceBuilder()).build()));
   }

   public ParametersRefNested editOrNewParametersRefLike(LocalParametersReference item) {
      return this.withNewParametersRefLike((LocalParametersReference)Optional.ofNullable(this.buildParametersRef()).orElse(item));
   }

   public GatewayInfrastructureFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayInfrastructureFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayInfrastructureFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayInfrastructureFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayInfrastructureFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayInfrastructureFluent that = (GatewayInfrastructureFluent)o;
            if (!Objects.equals(this.annotations, that.annotations)) {
               return false;
            } else if (!Objects.equals(this.labels, that.labels)) {
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
      return Objects.hash(new Object[]{this.annotations, this.labels, this.parametersRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.annotations != null && !this.annotations.isEmpty()) {
         sb.append("annotations:");
         sb.append(this.annotations + ",");
      }

      if (this.labels != null && !this.labels.isEmpty()) {
         sb.append("labels:");
         sb.append(this.labels + ",");
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

   public class ParametersRefNested extends LocalParametersReferenceFluent implements Nested {
      LocalParametersReferenceBuilder builder;

      ParametersRefNested(LocalParametersReference item) {
         this.builder = new LocalParametersReferenceBuilder(this, item);
      }

      public Object and() {
         return GatewayInfrastructureFluent.this.withParametersRef(this.builder.build());
      }

      public Object endParametersRef() {
         return this.and();
      }
   }
}
