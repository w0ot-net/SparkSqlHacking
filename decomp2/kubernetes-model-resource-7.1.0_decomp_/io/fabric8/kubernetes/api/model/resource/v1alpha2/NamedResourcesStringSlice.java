package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"strings"})
public class NamedResourcesStringSlice implements Editable, KubernetesResource {
   @JsonProperty("strings")
   @JsonInclude(Include.NON_EMPTY)
   private List strings = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamedResourcesStringSlice() {
   }

   public NamedResourcesStringSlice(List strings) {
      this.strings = strings;
   }

   @JsonProperty("strings")
   @JsonInclude(Include.NON_EMPTY)
   public List getStrings() {
      return this.strings;
   }

   @JsonProperty("strings")
   public void setStrings(List strings) {
      this.strings = strings;
   }

   @JsonIgnore
   public NamedResourcesStringSliceBuilder edit() {
      return new NamedResourcesStringSliceBuilder(this);
   }

   @JsonIgnore
   public NamedResourcesStringSliceBuilder toBuilder() {
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
      List var10000 = this.getStrings();
      return "NamedResourcesStringSlice(strings=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamedResourcesStringSlice)) {
         return false;
      } else {
         NamedResourcesStringSlice other = (NamedResourcesStringSlice)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$strings = this.getStrings();
            Object other$strings = other.getStrings();
            if (this$strings == null) {
               if (other$strings != null) {
                  return false;
               }
            } else if (!this$strings.equals(other$strings)) {
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
      return other instanceof NamedResourcesStringSlice;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $strings = this.getStrings();
      result = result * 59 + ($strings == null ? 43 : $strings.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
