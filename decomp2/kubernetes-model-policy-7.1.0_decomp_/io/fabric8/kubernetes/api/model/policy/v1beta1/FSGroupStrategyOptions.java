package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "ranges", "rule"})
public class FSGroupStrategyOptions implements Editable, KubernetesResource {
   @JsonProperty("ranges")
   @JsonInclude(Include.NON_EMPTY)
   private List ranges = new ArrayList();
   @JsonProperty("rule")
   private String rule;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FSGroupStrategyOptions() {
   }

   public FSGroupStrategyOptions(List ranges, String rule) {
      this.ranges = ranges;
      this.rule = rule;
   }

   @JsonProperty("ranges")
   public List getRanges() {
      return this.ranges;
   }

   @JsonProperty("ranges")
   public void setRanges(List ranges) {
      this.ranges = ranges;
   }

   @JsonProperty("rule")
   public String getRule() {
      return this.rule;
   }

   @JsonProperty("rule")
   public void setRule(String rule) {
      this.rule = rule;
   }

   @JsonIgnore
   public FSGroupStrategyOptionsBuilder edit() {
      return new FSGroupStrategyOptionsBuilder(this);
   }

   @JsonIgnore
   public FSGroupStrategyOptionsBuilder toBuilder() {
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

   @Generated
   public String toString() {
      List var10000 = this.getRanges();
      return "FSGroupStrategyOptions(ranges=" + var10000 + ", rule=" + this.getRule() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FSGroupStrategyOptions)) {
         return false;
      } else {
         FSGroupStrategyOptions other = (FSGroupStrategyOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ranges = this.getRanges();
            Object other$ranges = other.getRanges();
            if (this$ranges == null) {
               if (other$ranges != null) {
                  return false;
               }
            } else if (!this$ranges.equals(other$ranges)) {
               return false;
            }

            Object this$rule = this.getRule();
            Object other$rule = other.getRule();
            if (this$rule == null) {
               if (other$rule != null) {
                  return false;
               }
            } else if (!this$rule.equals(other$rule)) {
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
      return other instanceof FSGroupStrategyOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ranges = this.getRanges();
      result = result * 59 + ($ranges == null ? 43 : $ranges.hashCode());
      Object $rule = this.getRule();
      result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }
}
