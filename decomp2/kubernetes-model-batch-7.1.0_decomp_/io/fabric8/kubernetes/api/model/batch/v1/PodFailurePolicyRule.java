package io.fabric8.kubernetes.api.model.batch.v1;

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
@JsonPropertyOrder({"action", "onExitCodes", "onPodConditions"})
public class PodFailurePolicyRule implements Editable, KubernetesResource {
   @JsonProperty("action")
   private String action;
   @JsonProperty("onExitCodes")
   private PodFailurePolicyOnExitCodesRequirement onExitCodes;
   @JsonProperty("onPodConditions")
   @JsonInclude(Include.NON_EMPTY)
   private List onPodConditions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodFailurePolicyRule() {
   }

   public PodFailurePolicyRule(String action, PodFailurePolicyOnExitCodesRequirement onExitCodes, List onPodConditions) {
      this.action = action;
      this.onExitCodes = onExitCodes;
      this.onPodConditions = onPodConditions;
   }

   @JsonProperty("action")
   public String getAction() {
      return this.action;
   }

   @JsonProperty("action")
   public void setAction(String action) {
      this.action = action;
   }

   @JsonProperty("onExitCodes")
   public PodFailurePolicyOnExitCodesRequirement getOnExitCodes() {
      return this.onExitCodes;
   }

   @JsonProperty("onExitCodes")
   public void setOnExitCodes(PodFailurePolicyOnExitCodesRequirement onExitCodes) {
      this.onExitCodes = onExitCodes;
   }

   @JsonProperty("onPodConditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getOnPodConditions() {
      return this.onPodConditions;
   }

   @JsonProperty("onPodConditions")
   public void setOnPodConditions(List onPodConditions) {
      this.onPodConditions = onPodConditions;
   }

   @JsonIgnore
   public PodFailurePolicyRuleBuilder edit() {
      return new PodFailurePolicyRuleBuilder(this);
   }

   @JsonIgnore
   public PodFailurePolicyRuleBuilder toBuilder() {
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
      String var10000 = this.getAction();
      return "PodFailurePolicyRule(action=" + var10000 + ", onExitCodes=" + this.getOnExitCodes() + ", onPodConditions=" + this.getOnPodConditions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodFailurePolicyRule)) {
         return false;
      } else {
         PodFailurePolicyRule other = (PodFailurePolicyRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$action = this.getAction();
            Object other$action = other.getAction();
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$onExitCodes = this.getOnExitCodes();
            Object other$onExitCodes = other.getOnExitCodes();
            if (this$onExitCodes == null) {
               if (other$onExitCodes != null) {
                  return false;
               }
            } else if (!this$onExitCodes.equals(other$onExitCodes)) {
               return false;
            }

            Object this$onPodConditions = this.getOnPodConditions();
            Object other$onPodConditions = other.getOnPodConditions();
            if (this$onPodConditions == null) {
               if (other$onPodConditions != null) {
                  return false;
               }
            } else if (!this$onPodConditions.equals(other$onPodConditions)) {
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
      return other instanceof PodFailurePolicyRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $action = this.getAction();
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $onExitCodes = this.getOnExitCodes();
      result = result * 59 + ($onExitCodes == null ? 43 : $onExitCodes.hashCode());
      Object $onPodConditions = this.getOnPodConditions();
      result = result * 59 + ($onPodConditions == null ? 43 : $onPodConditions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
