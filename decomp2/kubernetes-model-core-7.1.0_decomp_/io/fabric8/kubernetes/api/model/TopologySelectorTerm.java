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
@JsonPropertyOrder({"matchLabelExpressions"})
public class TopologySelectorTerm implements Editable, KubernetesResource {
   @JsonProperty("matchLabelExpressions")
   @JsonInclude(Include.NON_EMPTY)
   private List matchLabelExpressions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TopologySelectorTerm() {
   }

   public TopologySelectorTerm(List matchLabelExpressions) {
      this.matchLabelExpressions = matchLabelExpressions;
   }

   @JsonProperty("matchLabelExpressions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchLabelExpressions() {
      return this.matchLabelExpressions;
   }

   @JsonProperty("matchLabelExpressions")
   public void setMatchLabelExpressions(List matchLabelExpressions) {
      this.matchLabelExpressions = matchLabelExpressions;
   }

   @JsonIgnore
   public TopologySelectorTermBuilder edit() {
      return new TopologySelectorTermBuilder(this);
   }

   @JsonIgnore
   public TopologySelectorTermBuilder toBuilder() {
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
      List var10000 = this.getMatchLabelExpressions();
      return "TopologySelectorTerm(matchLabelExpressions=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TopologySelectorTerm)) {
         return false;
      } else {
         TopologySelectorTerm other = (TopologySelectorTerm)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$matchLabelExpressions = this.getMatchLabelExpressions();
            Object other$matchLabelExpressions = other.getMatchLabelExpressions();
            if (this$matchLabelExpressions == null) {
               if (other$matchLabelExpressions != null) {
                  return false;
               }
            } else if (!this$matchLabelExpressions.equals(other$matchLabelExpressions)) {
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
      return other instanceof TopologySelectorTerm;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $matchLabelExpressions = this.getMatchLabelExpressions();
      result = result * 59 + ($matchLabelExpressions == null ? 43 : $matchLabelExpressions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
