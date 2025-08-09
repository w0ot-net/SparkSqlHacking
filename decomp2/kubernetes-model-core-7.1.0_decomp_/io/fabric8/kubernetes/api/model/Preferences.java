package io.fabric8.kubernetes.api.model;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"colors", "extensions"})
public class Preferences implements Editable, KubernetesResource {
   @JsonProperty("colors")
   private Boolean colors;
   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   private List extensions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Preferences() {
   }

   public Preferences(Boolean colors, List extensions) {
      this.colors = colors;
      this.extensions = extensions;
   }

   @JsonProperty("colors")
   public Boolean getColors() {
      return this.colors;
   }

   @JsonProperty("colors")
   public void setColors(Boolean colors) {
      this.colors = colors;
   }

   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   public List getExtensions() {
      return this.extensions;
   }

   @JsonProperty("extensions")
   public void setExtensions(List extensions) {
      this.extensions = extensions;
   }

   @JsonIgnore
   public PreferencesBuilder edit() {
      return new PreferencesBuilder(this);
   }

   @JsonIgnore
   public PreferencesBuilder toBuilder() {
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
      Boolean var10000 = this.getColors();
      return "Preferences(colors=" + var10000 + ", extensions=" + this.getExtensions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Preferences)) {
         return false;
      } else {
         Preferences other = (Preferences)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$colors = this.getColors();
            Object other$colors = other.getColors();
            if (this$colors == null) {
               if (other$colors != null) {
                  return false;
               }
            } else if (!this$colors.equals(other$colors)) {
               return false;
            }

            Object this$extensions = this.getExtensions();
            Object other$extensions = other.getExtensions();
            if (this$extensions == null) {
               if (other$extensions != null) {
                  return false;
               }
            } else if (!this$extensions.equals(other$extensions)) {
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
      return other instanceof Preferences;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $colors = this.getColors();
      result = result * 59 + ($colors == null ? 43 : $colors.hashCode());
      Object $extensions = this.getExtensions();
      result = result * 59 + ($extensions == null ? 43 : $extensions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
