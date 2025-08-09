package io.fabric8.kubernetes.api.model.gatewayapi.v1beta1;

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
@JsonPropertyOrder({"from", "to"})
public class ReferenceGrantSpec implements Editable, KubernetesResource {
   @JsonProperty("from")
   @JsonInclude(Include.NON_EMPTY)
   private List from = new ArrayList();
   @JsonProperty("to")
   @JsonInclude(Include.NON_EMPTY)
   private List to = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ReferenceGrantSpec() {
   }

   public ReferenceGrantSpec(List from, List to) {
      this.from = from;
      this.to = to;
   }

   @JsonProperty("from")
   @JsonInclude(Include.NON_EMPTY)
   public List getFrom() {
      return this.from;
   }

   @JsonProperty("from")
   public void setFrom(List from) {
      this.from = from;
   }

   @JsonProperty("to")
   @JsonInclude(Include.NON_EMPTY)
   public List getTo() {
      return this.to;
   }

   @JsonProperty("to")
   public void setTo(List to) {
      this.to = to;
   }

   @JsonIgnore
   public ReferenceGrantSpecBuilder edit() {
      return new ReferenceGrantSpecBuilder(this);
   }

   @JsonIgnore
   public ReferenceGrantSpecBuilder toBuilder() {
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
      List var10000 = this.getFrom();
      return "ReferenceGrantSpec(from=" + var10000 + ", to=" + this.getTo() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReferenceGrantSpec)) {
         return false;
      } else {
         ReferenceGrantSpec other = (ReferenceGrantSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$from = this.getFrom();
            Object other$from = other.getFrom();
            if (this$from == null) {
               if (other$from != null) {
                  return false;
               }
            } else if (!this$from.equals(other$from)) {
               return false;
            }

            Object this$to = this.getTo();
            Object other$to = other.getTo();
            if (this$to == null) {
               if (other$to != null) {
                  return false;
               }
            } else if (!this$to.equals(other$to)) {
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
      return other instanceof ReferenceGrantSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $from = this.getFrom();
      result = result * 59 + ($from == null ? 43 : $from.hashCode());
      Object $to = this.getTo();
      result = result * 59 + ($to == null ? 43 : $to.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
