package io.fabric8.kubernetes.api.model.resource.v1beta1;

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
@JsonPropertyOrder({"matchAttribute", "requests"})
public class DeviceConstraint implements Editable, KubernetesResource {
   @JsonProperty("matchAttribute")
   private String matchAttribute;
   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   private List requests = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceConstraint() {
   }

   public DeviceConstraint(String matchAttribute, List requests) {
      this.matchAttribute = matchAttribute;
      this.requests = requests;
   }

   @JsonProperty("matchAttribute")
   public String getMatchAttribute() {
      return this.matchAttribute;
   }

   @JsonProperty("matchAttribute")
   public void setMatchAttribute(String matchAttribute) {
      this.matchAttribute = matchAttribute;
   }

   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   public List getRequests() {
      return this.requests;
   }

   @JsonProperty("requests")
   public void setRequests(List requests) {
      this.requests = requests;
   }

   @JsonIgnore
   public DeviceConstraintBuilder edit() {
      return new DeviceConstraintBuilder(this);
   }

   @JsonIgnore
   public DeviceConstraintBuilder toBuilder() {
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
      String var10000 = this.getMatchAttribute();
      return "DeviceConstraint(matchAttribute=" + var10000 + ", requests=" + this.getRequests() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceConstraint)) {
         return false;
      } else {
         DeviceConstraint other = (DeviceConstraint)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$matchAttribute = this.getMatchAttribute();
            Object other$matchAttribute = other.getMatchAttribute();
            if (this$matchAttribute == null) {
               if (other$matchAttribute != null) {
                  return false;
               }
            } else if (!this$matchAttribute.equals(other$matchAttribute)) {
               return false;
            }

            Object this$requests = this.getRequests();
            Object other$requests = other.getRequests();
            if (this$requests == null) {
               if (other$requests != null) {
                  return false;
               }
            } else if (!this$requests.equals(other$requests)) {
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
      return other instanceof DeviceConstraint;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $matchAttribute = this.getMatchAttribute();
      result = result * 59 + ($matchAttribute == null ? 43 : $matchAttribute.hashCode());
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
