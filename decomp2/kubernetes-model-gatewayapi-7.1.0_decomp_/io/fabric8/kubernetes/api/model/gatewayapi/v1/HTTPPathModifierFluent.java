package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HTTPPathModifierFluent extends BaseFluent {
   private String replaceFullPath;
   private String replacePrefixMatch;
   private String type;
   private Map additionalProperties;

   public HTTPPathModifierFluent() {
   }

   public HTTPPathModifierFluent(HTTPPathModifier instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPPathModifier instance) {
      instance = instance != null ? instance : new HTTPPathModifier();
      if (instance != null) {
         this.withReplaceFullPath(instance.getReplaceFullPath());
         this.withReplacePrefixMatch(instance.getReplacePrefixMatch());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getReplaceFullPath() {
      return this.replaceFullPath;
   }

   public HTTPPathModifierFluent withReplaceFullPath(String replaceFullPath) {
      this.replaceFullPath = replaceFullPath;
      return this;
   }

   public boolean hasReplaceFullPath() {
      return this.replaceFullPath != null;
   }

   public String getReplacePrefixMatch() {
      return this.replacePrefixMatch;
   }

   public HTTPPathModifierFluent withReplacePrefixMatch(String replacePrefixMatch) {
      this.replacePrefixMatch = replacePrefixMatch;
      return this;
   }

   public boolean hasReplacePrefixMatch() {
      return this.replacePrefixMatch != null;
   }

   public String getType() {
      return this.type;
   }

   public HTTPPathModifierFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public HTTPPathModifierFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPPathModifierFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPPathModifierFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPPathModifierFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPPathModifierFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPPathModifierFluent that = (HTTPPathModifierFluent)o;
            if (!Objects.equals(this.replaceFullPath, that.replaceFullPath)) {
               return false;
            } else if (!Objects.equals(this.replacePrefixMatch, that.replacePrefixMatch)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.replaceFullPath, this.replacePrefixMatch, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.replaceFullPath != null) {
         sb.append("replaceFullPath:");
         sb.append(this.replaceFullPath + ",");
      }

      if (this.replacePrefixMatch != null) {
         sb.append("replacePrefixMatch:");
         sb.append(this.replacePrefixMatch + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
