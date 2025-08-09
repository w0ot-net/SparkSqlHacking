package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
import io.fabric8.kubernetes.api.model.LabelSelector;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"admissionReviewVersions", "clientConfig", "failurePolicy", "matchConditions", "matchPolicy", "name", "namespaceSelector", "objectSelector", "rules", "sideEffects", "timeoutSeconds"})
public class ValidatingWebhook implements Editable, KubernetesResource {
   @JsonProperty("admissionReviewVersions")
   @JsonInclude(Include.NON_EMPTY)
   private List admissionReviewVersions = new ArrayList();
   @JsonProperty("clientConfig")
   private WebhookClientConfig clientConfig;
   @JsonProperty("failurePolicy")
   private String failurePolicy;
   @JsonProperty("matchConditions")
   @JsonInclude(Include.NON_EMPTY)
   private List matchConditions = new ArrayList();
   @JsonProperty("matchPolicy")
   private String matchPolicy;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespaceSelector")
   private LabelSelector namespaceSelector;
   @JsonProperty("objectSelector")
   private LabelSelector objectSelector;
   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   private List rules = new ArrayList();
   @JsonProperty("sideEffects")
   private String sideEffects;
   @JsonProperty("timeoutSeconds")
   private Integer timeoutSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ValidatingWebhook() {
   }

   public ValidatingWebhook(List admissionReviewVersions, WebhookClientConfig clientConfig, String failurePolicy, List matchConditions, String matchPolicy, String name, LabelSelector namespaceSelector, LabelSelector objectSelector, List rules, String sideEffects, Integer timeoutSeconds) {
      this.admissionReviewVersions = admissionReviewVersions;
      this.clientConfig = clientConfig;
      this.failurePolicy = failurePolicy;
      this.matchConditions = matchConditions;
      this.matchPolicy = matchPolicy;
      this.name = name;
      this.namespaceSelector = namespaceSelector;
      this.objectSelector = objectSelector;
      this.rules = rules;
      this.sideEffects = sideEffects;
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonProperty("admissionReviewVersions")
   @JsonInclude(Include.NON_EMPTY)
   public List getAdmissionReviewVersions() {
      return this.admissionReviewVersions;
   }

   @JsonProperty("admissionReviewVersions")
   public void setAdmissionReviewVersions(List admissionReviewVersions) {
      this.admissionReviewVersions = admissionReviewVersions;
   }

   @JsonProperty("clientConfig")
   public WebhookClientConfig getClientConfig() {
      return this.clientConfig;
   }

   @JsonProperty("clientConfig")
   public void setClientConfig(WebhookClientConfig clientConfig) {
      this.clientConfig = clientConfig;
   }

   @JsonProperty("failurePolicy")
   public String getFailurePolicy() {
      return this.failurePolicy;
   }

   @JsonProperty("failurePolicy")
   public void setFailurePolicy(String failurePolicy) {
      this.failurePolicy = failurePolicy;
   }

   @JsonProperty("matchConditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchConditions() {
      return this.matchConditions;
   }

   @JsonProperty("matchConditions")
   public void setMatchConditions(List matchConditions) {
      this.matchConditions = matchConditions;
   }

   @JsonProperty("matchPolicy")
   public String getMatchPolicy() {
      return this.matchPolicy;
   }

   @JsonProperty("matchPolicy")
   public void setMatchPolicy(String matchPolicy) {
      this.matchPolicy = matchPolicy;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespaceSelector")
   public LabelSelector getNamespaceSelector() {
      return this.namespaceSelector;
   }

   @JsonProperty("namespaceSelector")
   public void setNamespaceSelector(LabelSelector namespaceSelector) {
      this.namespaceSelector = namespaceSelector;
   }

   @JsonProperty("objectSelector")
   public LabelSelector getObjectSelector() {
      return this.objectSelector;
   }

   @JsonProperty("objectSelector")
   public void setObjectSelector(LabelSelector objectSelector) {
      this.objectSelector = objectSelector;
   }

   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   public List getRules() {
      return this.rules;
   }

   @JsonProperty("rules")
   public void setRules(List rules) {
      this.rules = rules;
   }

   @JsonProperty("sideEffects")
   public String getSideEffects() {
      return this.sideEffects;
   }

   @JsonProperty("sideEffects")
   public void setSideEffects(String sideEffects) {
      this.sideEffects = sideEffects;
   }

   @JsonProperty("timeoutSeconds")
   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   @JsonProperty("timeoutSeconds")
   public void setTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonIgnore
   public ValidatingWebhookBuilder edit() {
      return new ValidatingWebhookBuilder(this);
   }

   @JsonIgnore
   public ValidatingWebhookBuilder toBuilder() {
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
      List var10000 = this.getAdmissionReviewVersions();
      return "ValidatingWebhook(admissionReviewVersions=" + var10000 + ", clientConfig=" + this.getClientConfig() + ", failurePolicy=" + this.getFailurePolicy() + ", matchConditions=" + this.getMatchConditions() + ", matchPolicy=" + this.getMatchPolicy() + ", name=" + this.getName() + ", namespaceSelector=" + this.getNamespaceSelector() + ", objectSelector=" + this.getObjectSelector() + ", rules=" + this.getRules() + ", sideEffects=" + this.getSideEffects() + ", timeoutSeconds=" + this.getTimeoutSeconds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ValidatingWebhook)) {
         return false;
      } else {
         ValidatingWebhook other = (ValidatingWebhook)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$timeoutSeconds = this.getTimeoutSeconds();
            Object other$timeoutSeconds = other.getTimeoutSeconds();
            if (this$timeoutSeconds == null) {
               if (other$timeoutSeconds != null) {
                  return false;
               }
            } else if (!this$timeoutSeconds.equals(other$timeoutSeconds)) {
               return false;
            }

            Object this$admissionReviewVersions = this.getAdmissionReviewVersions();
            Object other$admissionReviewVersions = other.getAdmissionReviewVersions();
            if (this$admissionReviewVersions == null) {
               if (other$admissionReviewVersions != null) {
                  return false;
               }
            } else if (!this$admissionReviewVersions.equals(other$admissionReviewVersions)) {
               return false;
            }

            Object this$clientConfig = this.getClientConfig();
            Object other$clientConfig = other.getClientConfig();
            if (this$clientConfig == null) {
               if (other$clientConfig != null) {
                  return false;
               }
            } else if (!this$clientConfig.equals(other$clientConfig)) {
               return false;
            }

            Object this$failurePolicy = this.getFailurePolicy();
            Object other$failurePolicy = other.getFailurePolicy();
            if (this$failurePolicy == null) {
               if (other$failurePolicy != null) {
                  return false;
               }
            } else if (!this$failurePolicy.equals(other$failurePolicy)) {
               return false;
            }

            Object this$matchConditions = this.getMatchConditions();
            Object other$matchConditions = other.getMatchConditions();
            if (this$matchConditions == null) {
               if (other$matchConditions != null) {
                  return false;
               }
            } else if (!this$matchConditions.equals(other$matchConditions)) {
               return false;
            }

            Object this$matchPolicy = this.getMatchPolicy();
            Object other$matchPolicy = other.getMatchPolicy();
            if (this$matchPolicy == null) {
               if (other$matchPolicy != null) {
                  return false;
               }
            } else if (!this$matchPolicy.equals(other$matchPolicy)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespaceSelector = this.getNamespaceSelector();
            Object other$namespaceSelector = other.getNamespaceSelector();
            if (this$namespaceSelector == null) {
               if (other$namespaceSelector != null) {
                  return false;
               }
            } else if (!this$namespaceSelector.equals(other$namespaceSelector)) {
               return false;
            }

            Object this$objectSelector = this.getObjectSelector();
            Object other$objectSelector = other.getObjectSelector();
            if (this$objectSelector == null) {
               if (other$objectSelector != null) {
                  return false;
               }
            } else if (!this$objectSelector.equals(other$objectSelector)) {
               return false;
            }

            Object this$rules = this.getRules();
            Object other$rules = other.getRules();
            if (this$rules == null) {
               if (other$rules != null) {
                  return false;
               }
            } else if (!this$rules.equals(other$rules)) {
               return false;
            }

            Object this$sideEffects = this.getSideEffects();
            Object other$sideEffects = other.getSideEffects();
            if (this$sideEffects == null) {
               if (other$sideEffects != null) {
                  return false;
               }
            } else if (!this$sideEffects.equals(other$sideEffects)) {
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
      return other instanceof ValidatingWebhook;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $timeoutSeconds = this.getTimeoutSeconds();
      result = result * 59 + ($timeoutSeconds == null ? 43 : $timeoutSeconds.hashCode());
      Object $admissionReviewVersions = this.getAdmissionReviewVersions();
      result = result * 59 + ($admissionReviewVersions == null ? 43 : $admissionReviewVersions.hashCode());
      Object $clientConfig = this.getClientConfig();
      result = result * 59 + ($clientConfig == null ? 43 : $clientConfig.hashCode());
      Object $failurePolicy = this.getFailurePolicy();
      result = result * 59 + ($failurePolicy == null ? 43 : $failurePolicy.hashCode());
      Object $matchConditions = this.getMatchConditions();
      result = result * 59 + ($matchConditions == null ? 43 : $matchConditions.hashCode());
      Object $matchPolicy = this.getMatchPolicy();
      result = result * 59 + ($matchPolicy == null ? 43 : $matchPolicy.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespaceSelector = this.getNamespaceSelector();
      result = result * 59 + ($namespaceSelector == null ? 43 : $namespaceSelector.hashCode());
      Object $objectSelector = this.getObjectSelector();
      result = result * 59 + ($objectSelector == null ? 43 : $objectSelector.hashCode());
      Object $rules = this.getRules();
      result = result * 59 + ($rules == null ? 43 : $rules.hashCode());
      Object $sideEffects = this.getSideEffects();
      result = result * 59 + ($sideEffects == null ? 43 : $sideEffects.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
