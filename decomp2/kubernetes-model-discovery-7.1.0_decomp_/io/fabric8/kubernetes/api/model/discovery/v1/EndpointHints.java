package io.fabric8.kubernetes.api.model.discovery.v1;

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
@JsonPropertyOrder({"forZones"})
public class EndpointHints implements Editable, KubernetesResource {
   @JsonProperty("forZones")
   @JsonInclude(Include.NON_EMPTY)
   private List forZones = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EndpointHints() {
   }

   public EndpointHints(List forZones) {
      this.forZones = forZones;
   }

   @JsonProperty("forZones")
   @JsonInclude(Include.NON_EMPTY)
   public List getForZones() {
      return this.forZones;
   }

   @JsonProperty("forZones")
   public void setForZones(List forZones) {
      this.forZones = forZones;
   }

   @JsonIgnore
   public EndpointHintsBuilder edit() {
      return new EndpointHintsBuilder(this);
   }

   @JsonIgnore
   public EndpointHintsBuilder toBuilder() {
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
      List var10000 = this.getForZones();
      return "EndpointHints(forZones=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EndpointHints)) {
         return false;
      } else {
         EndpointHints other = (EndpointHints)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$forZones = this.getForZones();
            Object other$forZones = other.getForZones();
            if (this$forZones == null) {
               if (other$forZones != null) {
                  return false;
               }
            } else if (!this$forZones.equals(other$forZones)) {
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
      return other instanceof EndpointHints;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $forZones = this.getForZones();
      result = result * 59 + ($forZones == null ? 43 : $forZones.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
