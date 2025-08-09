package io.fabric8.kubernetes.api.model.node.v1;

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
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"podFixed"})
public class Overhead implements Editable, KubernetesResource {
   @JsonProperty("podFixed")
   @JsonInclude(Include.NON_EMPTY)
   private Map podFixed = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Overhead() {
   }

   public Overhead(Map podFixed) {
      this.podFixed = podFixed;
   }

   @JsonProperty("podFixed")
   @JsonInclude(Include.NON_EMPTY)
   public Map getPodFixed() {
      return this.podFixed;
   }

   @JsonProperty("podFixed")
   public void setPodFixed(Map podFixed) {
      this.podFixed = podFixed;
   }

   @JsonIgnore
   public OverheadBuilder edit() {
      return new OverheadBuilder(this);
   }

   @JsonIgnore
   public OverheadBuilder toBuilder() {
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
      Map var10000 = this.getPodFixed();
      return "Overhead(podFixed=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Overhead)) {
         return false;
      } else {
         Overhead other = (Overhead)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$podFixed = this.getPodFixed();
            Object other$podFixed = other.getPodFixed();
            if (this$podFixed == null) {
               if (other$podFixed != null) {
                  return false;
               }
            } else if (!this$podFixed.equals(other$podFixed)) {
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
      return other instanceof Overhead;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $podFixed = this.getPodFixed();
      result = result * 59 + ($podFixed == null ? 43 : $podFixed.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
