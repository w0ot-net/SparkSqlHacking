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
@JsonPropertyOrder({"matchExpressions", "matchFields"})
public class NodeSelectorTerm implements Editable, KubernetesResource {
   @JsonProperty("matchExpressions")
   @JsonInclude(Include.NON_EMPTY)
   private List matchExpressions = new ArrayList();
   @JsonProperty("matchFields")
   @JsonInclude(Include.NON_EMPTY)
   private List matchFields = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeSelectorTerm() {
   }

   public NodeSelectorTerm(List matchExpressions, List matchFields) {
      this.matchExpressions = matchExpressions;
      this.matchFields = matchFields;
   }

   @JsonProperty("matchExpressions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchExpressions() {
      return this.matchExpressions;
   }

   @JsonProperty("matchExpressions")
   public void setMatchExpressions(List matchExpressions) {
      this.matchExpressions = matchExpressions;
   }

   @JsonProperty("matchFields")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchFields() {
      return this.matchFields;
   }

   @JsonProperty("matchFields")
   public void setMatchFields(List matchFields) {
      this.matchFields = matchFields;
   }

   @JsonIgnore
   public NodeSelectorTermBuilder edit() {
      return new NodeSelectorTermBuilder(this);
   }

   @JsonIgnore
   public NodeSelectorTermBuilder toBuilder() {
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
      List var10000 = this.getMatchExpressions();
      return "NodeSelectorTerm(matchExpressions=" + var10000 + ", matchFields=" + this.getMatchFields() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeSelectorTerm)) {
         return false;
      } else {
         NodeSelectorTerm other = (NodeSelectorTerm)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$matchExpressions = this.getMatchExpressions();
            Object other$matchExpressions = other.getMatchExpressions();
            if (this$matchExpressions == null) {
               if (other$matchExpressions != null) {
                  return false;
               }
            } else if (!this$matchExpressions.equals(other$matchExpressions)) {
               return false;
            }

            Object this$matchFields = this.getMatchFields();
            Object other$matchFields = other.getMatchFields();
            if (this$matchFields == null) {
               if (other$matchFields != null) {
                  return false;
               }
            } else if (!this$matchFields.equals(other$matchFields)) {
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
      return other instanceof NodeSelectorTerm;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $matchExpressions = this.getMatchExpressions();
      result = result * 59 + ($matchExpressions == null ? 43 : $matchExpressions.hashCode());
      Object $matchFields = this.getMatchFields();
      result = result * 59 + ($matchFields == null ? 43 : $matchFields.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
