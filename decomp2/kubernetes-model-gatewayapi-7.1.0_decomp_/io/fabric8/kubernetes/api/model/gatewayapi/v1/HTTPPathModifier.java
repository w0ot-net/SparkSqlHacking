package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"replaceFullPath", "replacePrefixMatch", "type"})
public class HTTPPathModifier implements Editable, KubernetesResource {
   @JsonProperty("replaceFullPath")
   private String replaceFullPath;
   @JsonProperty("replacePrefixMatch")
   private String replacePrefixMatch;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPPathModifier() {
   }

   public HTTPPathModifier(String replaceFullPath, String replacePrefixMatch, String type) {
      this.replaceFullPath = replaceFullPath;
      this.replacePrefixMatch = replacePrefixMatch;
      this.type = type;
   }

   @JsonProperty("replaceFullPath")
   public String getReplaceFullPath() {
      return this.replaceFullPath;
   }

   @JsonProperty("replaceFullPath")
   public void setReplaceFullPath(String replaceFullPath) {
      this.replaceFullPath = replaceFullPath;
   }

   @JsonProperty("replacePrefixMatch")
   public String getReplacePrefixMatch() {
      return this.replacePrefixMatch;
   }

   @JsonProperty("replacePrefixMatch")
   public void setReplacePrefixMatch(String replacePrefixMatch) {
      this.replacePrefixMatch = replacePrefixMatch;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public HTTPPathModifierBuilder edit() {
      return new HTTPPathModifierBuilder(this);
   }

   @JsonIgnore
   public HTTPPathModifierBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      String var10000 = this.getReplaceFullPath();
      return "HTTPPathModifier(replaceFullPath=" + var10000 + ", replacePrefixMatch=" + this.getReplacePrefixMatch() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPPathModifier)) {
         return false;
      } else {
         HTTPPathModifier other = (HTTPPathModifier)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$replaceFullPath = this.getReplaceFullPath();
            Object other$replaceFullPath = other.getReplaceFullPath();
            if (this$replaceFullPath == null) {
               if (other$replaceFullPath != null) {
                  return false;
               }
            } else if (!this$replaceFullPath.equals(other$replaceFullPath)) {
               return false;
            }

            Object this$replacePrefixMatch = this.getReplacePrefixMatch();
            Object other$replacePrefixMatch = other.getReplacePrefixMatch();
            if (this$replacePrefixMatch == null) {
               if (other$replacePrefixMatch != null) {
                  return false;
               }
            } else if (!this$replacePrefixMatch.equals(other$replacePrefixMatch)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof HTTPPathModifier;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $replaceFullPath = this.getReplaceFullPath();
      result = result * 59 + ($replaceFullPath == null ? 43 : $replaceFullPath.hashCode());
      Object $replacePrefixMatch = this.getReplacePrefixMatch();
      result = result * 59 + ($replacePrefixMatch == null ? 43 : $replacePrefixMatch.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
