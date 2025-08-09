package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AuditAnnotationFluent extends BaseFluent {
   private String key;
   private String valueExpression;
   private Map additionalProperties;

   public AuditAnnotationFluent() {
   }

   public AuditAnnotationFluent(AuditAnnotation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AuditAnnotation instance) {
      instance = instance != null ? instance : new AuditAnnotation();
      if (instance != null) {
         this.withKey(instance.getKey());
         this.withValueExpression(instance.getValueExpression());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKey() {
      return this.key;
   }

   public AuditAnnotationFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public String getValueExpression() {
      return this.valueExpression;
   }

   public AuditAnnotationFluent withValueExpression(String valueExpression) {
      this.valueExpression = valueExpression;
      return this;
   }

   public boolean hasValueExpression() {
      return this.valueExpression != null;
   }

   public AuditAnnotationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AuditAnnotationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AuditAnnotationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AuditAnnotationFluent removeFromAdditionalProperties(Map map) {
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

   public AuditAnnotationFluent withAdditionalProperties(Map additionalProperties) {
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
            AuditAnnotationFluent that = (AuditAnnotationFluent)o;
            if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.valueExpression, that.valueExpression)) {
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
      return Objects.hash(new Object[]{this.key, this.valueExpression, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.valueExpression != null) {
         sb.append("valueExpression:");
         sb.append(this.valueExpression + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
