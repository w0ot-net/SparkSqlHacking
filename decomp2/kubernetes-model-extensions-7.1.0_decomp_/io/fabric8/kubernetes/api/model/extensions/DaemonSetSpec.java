package io.fabric8.kubernetes.api.model.extensions;

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
@JsonPropertyOrder({"minReadySeconds", "revisionHistoryLimit", "selector", "template", "templateGeneration", "updateStrategy"})
public class DaemonSetSpec implements Editable, KubernetesResource {
   @JsonProperty("minReadySeconds")
   private Integer minReadySeconds;
   @JsonProperty("revisionHistoryLimit")
   private Integer revisionHistoryLimit;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("template")
   private PodTemplateSpec template;
   @JsonProperty("templateGeneration")
   private Long templateGeneration;
   @JsonProperty("updateStrategy")
   private DaemonSetUpdateStrategy updateStrategy;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DaemonSetSpec() {
   }

   public DaemonSetSpec(Integer minReadySeconds, Integer revisionHistoryLimit, LabelSelector selector, PodTemplateSpec template, Long templateGeneration, DaemonSetUpdateStrategy updateStrategy) {
      this.minReadySeconds = minReadySeconds;
      this.revisionHistoryLimit = revisionHistoryLimit;
      this.selector = selector;
      this.template = template;
      this.templateGeneration = templateGeneration;
      this.updateStrategy = updateStrategy;
   }

   @JsonProperty("minReadySeconds")
   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   @JsonProperty("minReadySeconds")
   public void setMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
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

   @JsonProperty("template")
   public PodTemplateSpec getTemplate() {
      return this.template;
   }

   @JsonProperty("template")
   public void setTemplate(PodTemplateSpec template) {
      this.template = template;
   }

   @JsonProperty("templateGeneration")
   public Long getTemplateGeneration() {
      return this.templateGeneration;
   }

   @JsonProperty("templateGeneration")
   public void setTemplateGeneration(Long templateGeneration) {
      this.templateGeneration = templateGeneration;
   }

   @JsonProperty("updateStrategy")
   public DaemonSetUpdateStrategy getUpdateStrategy() {
      return this.updateStrategy;
   }

   @JsonProperty("updateStrategy")
   public void setUpdateStrategy(DaemonSetUpdateStrategy updateStrategy) {
      this.updateStrategy = updateStrategy;
   }

   @JsonIgnore
   public DaemonSetSpecBuilder edit() {
      return new DaemonSetSpecBuilder(this);
   }

   @JsonIgnore
   public DaemonSetSpecBuilder toBuilder() {
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
      return "DaemonSetSpec(minReadySeconds=" + var10000 + ", revisionHistoryLimit=" + this.getRevisionHistoryLimit() + ", selector=" + this.getSelector() + ", template=" + this.getTemplate() + ", templateGeneration=" + this.getTemplateGeneration() + ", updateStrategy=" + this.getUpdateStrategy() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DaemonSetSpec)) {
         return false;
      } else {
         DaemonSetSpec other = (DaemonSetSpec)o;
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

            Object this$revisionHistoryLimit = this.getRevisionHistoryLimit();
            Object other$revisionHistoryLimit = other.getRevisionHistoryLimit();
            if (this$revisionHistoryLimit == null) {
               if (other$revisionHistoryLimit != null) {
                  return false;
               }
            } else if (!this$revisionHistoryLimit.equals(other$revisionHistoryLimit)) {
               return false;
            }

            Object this$templateGeneration = this.getTemplateGeneration();
            Object other$templateGeneration = other.getTemplateGeneration();
            if (this$templateGeneration == null) {
               if (other$templateGeneration != null) {
                  return false;
               }
            } else if (!this$templateGeneration.equals(other$templateGeneration)) {
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

            Object this$template = this.getTemplate();
            Object other$template = other.getTemplate();
            if (this$template == null) {
               if (other$template != null) {
                  return false;
               }
            } else if (!this$template.equals(other$template)) {
               return false;
            }

            Object this$updateStrategy = this.getUpdateStrategy();
            Object other$updateStrategy = other.getUpdateStrategy();
            if (this$updateStrategy == null) {
               if (other$updateStrategy != null) {
                  return false;
               }
            } else if (!this$updateStrategy.equals(other$updateStrategy)) {
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
      return other instanceof DaemonSetSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $minReadySeconds = this.getMinReadySeconds();
      result = result * 59 + ($minReadySeconds == null ? 43 : $minReadySeconds.hashCode());
      Object $revisionHistoryLimit = this.getRevisionHistoryLimit();
      result = result * 59 + ($revisionHistoryLimit == null ? 43 : $revisionHistoryLimit.hashCode());
      Object $templateGeneration = this.getTemplateGeneration();
      result = result * 59 + ($templateGeneration == null ? 43 : $templateGeneration.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $updateStrategy = this.getUpdateStrategy();
      result = result * 59 + ($updateStrategy == null ? 43 : $updateStrategy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
