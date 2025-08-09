package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ExternalDocumentationFluent extends BaseFluent {
   private String description;
   private String url;
   private Map additionalProperties;

   public ExternalDocumentationFluent() {
   }

   public ExternalDocumentationFluent(ExternalDocumentation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExternalDocumentation instance) {
      instance = instance != null ? instance : new ExternalDocumentation();
      if (instance != null) {
         this.withDescription(instance.getDescription());
         this.withUrl(instance.getUrl());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDescription() {
      return this.description;
   }

   public ExternalDocumentationFluent withDescription(String description) {
      this.description = description;
      return this;
   }

   public boolean hasDescription() {
      return this.description != null;
   }

   public String getUrl() {
      return this.url;
   }

   public ExternalDocumentationFluent withUrl(String url) {
      this.url = url;
      return this;
   }

   public boolean hasUrl() {
      return this.url != null;
   }

   public ExternalDocumentationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExternalDocumentationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExternalDocumentationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExternalDocumentationFluent removeFromAdditionalProperties(Map map) {
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

   public ExternalDocumentationFluent withAdditionalProperties(Map additionalProperties) {
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
            ExternalDocumentationFluent that = (ExternalDocumentationFluent)o;
            if (!Objects.equals(this.description, that.description)) {
               return false;
            } else if (!Objects.equals(this.url, that.url)) {
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
      return Objects.hash(new Object[]{this.description, this.url, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.description != null) {
         sb.append("description:");
         sb.append(this.description + ",");
      }

      if (this.url != null) {
         sb.append("url:");
         sb.append(this.url + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
