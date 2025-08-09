package io.fabric8.kubernetes.api.model.apps;

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
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"minReadySeconds", "paused", "progressDeadlineSeconds", "replicas", "revisionHistoryLimit", "selector", "strategy", "template"})
public class DeploymentSpec implements Editable, KubernetesResource {
   @JsonProperty("minReadySeconds")
   private Integer minReadySeconds;
   @JsonProperty("paused")
   private Boolean paused;
   @JsonProperty("progressDeadlineSeconds")
   private Integer progressDeadlineSeconds;
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonProperty("revisionHistoryLimit")
   private Integer revisionHistoryLimit;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("strategy")
   private DeploymentStrategy strategy;
   @JsonProperty("template")
   private PodTemplateSpec template;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeploymentSpec() {
   }

   public DeploymentSpec(Integer minReadySeconds, Boolean paused, Integer progressDeadlineSeconds, Integer replicas, Integer revisionHistoryLimit, LabelSelector selector, DeploymentStrategy strategy, PodTemplateSpec template) {
      this.minReadySeconds = minReadySeconds;
      this.paused = paused;
      this.progressDeadlineSeconds = progressDeadlineSeconds;
      this.replicas = replicas;
      this.revisionHistoryLimit = revisionHistoryLimit;
      this.selector = selector;
      this.strategy = strategy;
      this.template = template;
   }

   @JsonProperty("minReadySeconds")
   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   @JsonProperty("minReadySeconds")
   public void setMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
   }

   @JsonProperty("paused")
   public Boolean getPaused() {
      return this.paused;
   }

   @JsonProperty("paused")
   public void setPaused(Boolean paused) {
      this.paused = paused;
   }

   @JsonProperty("progressDeadlineSeconds")
   public Integer getProgressDeadlineSeconds() {
      return this.progressDeadlineSeconds;
   }

   @JsonProperty("progressDeadlineSeconds")
   public void setProgressDeadlineSeconds(Integer progressDeadlineSeconds) {
      this.progressDeadlineSeconds = progressDeadlineSeconds;
   }

   @JsonProperty("replicas")
   public Integer getReplicas() {
      return this.replicas;
   }

   @JsonProperty("replicas")
   public void setReplicas(Integer replicas) {
      this.replicas = replicas;
   }

   @JsonProperty("revisionHistoryLimit")
   public Integer getRevisionHistoryLimit() {
      return this.revisionHistoryLimit;
   }

   @JsonProperty("revisionHistoryLimit")
   public void setRevisionHistoryLimit(Integer revisionHistoryLimit) {
      this.revisionHistoryLimit = revisionHistoryLimit;
   }

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonProperty("strategy")
   public DeploymentStrategy getStrategy() {
      return this.strategy;
   }

   @JsonProperty("strategy")
   public void setStrategy(DeploymentStrategy strategy) {
      this.strategy = strategy;
   }

   @JsonProperty("template")
   public PodTemplateSpec getTemplate() {
      return this.template;
   }

   @JsonProperty("template")
   public void setTemplate(PodTemplateSpec template) {
      this.template = template;
   }

   @JsonIgnore
   public DeploymentSpecBuilder edit() {
      return new DeploymentSpecBuilder(this);
   }

   @JsonIgnore
   public DeploymentSpecBuilder toBuilder() {
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
      Integer var10000 = this.getMinReadySeconds();
      return "DeploymentSpec(minReadySeconds=" + var10000 + ", paused=" + this.getPaused() + ", progressDeadlineSeconds=" + this.getProgressDeadlineSeconds() + ", replicas=" + this.getReplicas() + ", revisionHistoryLimit=" + this.getRevisionHistoryLimit() + ", selector=" + this.getSelector() + ", strategy=" + this.getStrategy() + ", template=" + this.getTemplate() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeploymentSpec)) {
         return false;
      } else {
         DeploymentSpec other = (DeploymentSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$minReadySeconds = this.getMinReadySeconds();
            Object other$minReadySeconds = other.getMinReadySeconds();
            if (this$minReadySeconds == null) {
               if (other$minReadySeconds != null) {
                  return false;
               }
            } else if (!this$minReadySeconds.equals(other$minReadySeconds)) {
               return false;
            }

            Object this$paused = this.getPaused();
            Object other$paused = other.getPaused();
            if (this$paused == null) {
               if (other$paused != null) {
                  return false;
               }
            } else if (!this$paused.equals(other$paused)) {
               return false;
            }

            Object this$progressDeadlineSeconds = this.getProgressDeadlineSeconds();
            Object other$progressDeadlineSeconds = other.getProgressDeadlineSeconds();
            if (this$progressDeadlineSeconds == null) {
               if (other$progressDeadlineSeconds != null) {
                  return false;
               }
            } else if (!this$progressDeadlineSeconds.equals(other$progressDeadlineSeconds)) {
               return false;
            }

            Object this$replicas = this.getReplicas();
            Object other$replicas = other.getReplicas();
            if (this$replicas == null) {
               if (other$replicas != null) {
                  return false;
               }
            } else if (!this$replicas.equals(other$replicas)) {
               return false;
            }

            Object this$revisionHistoryLimit = this.getRevisionHistoryLimit();
            Object other$revisionHistoryLimit = other.getRevisionHistoryLimit();
            if (this$revisionHistoryLimit == null) {
               if (other$revisionHistoryLimit != null) {
                  return false;
               }
            } else if (!this$revisionHistoryLimit.equals(other$revisionHistoryLimit)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$strategy = this.getStrategy();
            Object other$strategy = other.getStrategy();
            if (this$strategy == null) {
               if (other$strategy != null) {
                  return false;
               }
            } else if (!this$strategy.equals(other$strategy)) {
               return false;
            }

            Object this$template = this.getTemplate();
            Object other$template = other.getTemplate();
            if (this$template == null) {
               if (other$template != null) {
                  return false;
               }
            } else if (!this$template.equals(other$template)) {
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
      return other instanceof DeploymentSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $minReadySeconds = this.getMinReadySeconds();
      result = result * 59 + ($minReadySeconds == null ? 43 : $minReadySeconds.hashCode());
      Object $paused = this.getPaused();
      result = result * 59 + ($paused == null ? 43 : $paused.hashCode());
      Object $progressDeadlineSeconds = this.getProgressDeadlineSeconds();
      result = result * 59 + ($progressDeadlineSeconds == null ? 43 : $progressDeadlineSeconds.hashCode());
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $revisionHistoryLimit = this.getRevisionHistoryLimit();
      result = result * 59 + ($revisionHistoryLimit == null ? 43 : $revisionHistoryLimit.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $strategy = this.getStrategy();
      result = result * 59 + ($strategy == null ? 43 : $strategy.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
