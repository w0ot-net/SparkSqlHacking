package io.fabric8.kubernetes.api.model.authorization.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NonResourceAttributesFluent extends BaseFluent {
   private String path;
   private String verb;
   private Map additionalProperties;

   public NonResourceAttributesFluent() {
   }

   public NonResourceAttributesFluent(NonResourceAttributes instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NonResourceAttributes instance) {
      instance = instance != null ? instance : new NonResourceAttributes();
      if (instance != null) {
         this.withPath(instance.getPath());
         this.withVerb(instance.getVerb());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getPath() {
      return this.path;
   }

   public NonResourceAttributesFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public String getVerb() {
      return this.verb;
   }

   public NonResourceAttributesFluent withVerb(String verb) {
      this.verb = verb;
      return this;
   }

   public boolean hasVerb() {
      return this.verb != null;
   }

   public NonResourceAttributesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NonResourceAttributesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NonResourceAttributesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NonResourceAttributesFluent removeFromAdditionalProperties(Map map) {
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

   public NonResourceAttributesFluent withAdditionalProperties(Map additionalProperties) {
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
            NonResourceAttributesFluent that = (NonResourceAttributesFluent)o;
            if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.verb, that.verb)) {
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
      return Objects.hash(new Object[]{this.path, this.verb, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.verb != null) {
         sb.append("verb:");
         sb.append(this.verb + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
