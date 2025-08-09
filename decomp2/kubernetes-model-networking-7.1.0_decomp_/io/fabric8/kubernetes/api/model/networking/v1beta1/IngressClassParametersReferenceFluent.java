package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class IngressClassParametersReferenceFluent extends BaseFluent {
   private String apiGroup;
   private String kind;
   private String name;
   private String namespace;
   private String scope;
   private Map additionalProperties;

   public IngressClassParametersReferenceFluent() {
   }

   public IngressClassParametersReferenceFluent(IngressClassParametersReference instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressClassParametersReference instance) {
      instance = instance != null ? instance : new IngressClassParametersReference();
      if (instance != null) {
         this.withApiGroup(instance.getApiGroup());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withScope(instance.getScope());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiGroup() {
      return this.apiGroup;
   }

   public IngressClassParametersReferenceFluent withApiGroup(String apiGroup) {
      this.apiGroup = apiGroup;
      return this;
   }

   public boolean hasApiGroup() {
      return this.apiGroup != null;
   }

   public String getKind() {
      return this.kind;
   }

   public IngressClassParametersReferenceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public IngressClassParametersReferenceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public IngressClassParametersReferenceFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public String getScope() {
      return this.scope;
   }

   public IngressClassParametersReferenceFluent withScope(String scope) {
      this.scope = scope;
      return this;
   }

   public boolean hasScope() {
      return this.scope != null;
   }

   public IngressClassParametersReferenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressClassParametersReferenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressClassParametersReferenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressClassParametersReferenceFluent removeFromAdditionalProperties(Map map) {
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

   public IngressClassParametersReferenceFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressClassParametersReferenceFluent that = (IngressClassParametersReferenceFluent)o;
            if (!Objects.equals(this.apiGroup, that.apiGroup)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.scope, that.scope)) {
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
      return Objects.hash(new Object[]{this.apiGroup, this.kind, this.name, this.namespace, this.scope, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiGroup != null) {
         sb.append("apiGroup:");
         sb.append(this.apiGroup + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.scope != null) {
         sb.append("scope:");
         sb.append(this.scope + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
