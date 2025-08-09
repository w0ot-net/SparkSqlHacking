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
@JsonPropertyOrder({"ints"})
public class NamedResourcesIntSlice implements Editable, KubernetesResource {
   @JsonProperty("ints")
   @JsonInclude(Include.NON_EMPTY)
   private List ints = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamedResourcesIntSlice() {
   }

   public NamedResourcesIntSlice(List ints) {
      this.ints = ints;
   }

   @JsonProperty("ints")
   @JsonInclude(Include.NON_EMPTY)
   public List getInts() {
      return this.ints;
   }

   @JsonProperty("ints")
   public void setInts(List ints) {
      this.ints = ints;
   }

   @JsonIgnore
   public NamedResourcesIntSliceBuilder edit() {
      return new NamedResourcesIntSliceBuilder(this);
   }

   @JsonIgnore
   public NamedResourcesIntSliceBuilder toBuilder() {
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
      List var10000 = this.getInts();
      return "NamedResourcesIntSlice(ints=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamedResourcesIntSlice)) {
         return false;
      } else {
         NamedResourcesIntSlice other = (NamedResourcesIntSlice)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ints = this.getInts();
            Object other$ints = other.getInts();
            if (this$ints == null) {
               if (other$ints != null) {
                  return false;
               }
            } else if (!this$ints.equals(other$ints)) {
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
      return other instanceof NamedResourcesIntSlice;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ints = this.getInts();
      result = result * 59 + ($ints == null ? 43 : $ints.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
