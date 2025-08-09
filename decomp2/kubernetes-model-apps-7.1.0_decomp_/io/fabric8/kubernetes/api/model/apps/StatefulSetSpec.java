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
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"minReadySeconds", "ordinals", "persistentVolumeClaimRetentionPolicy", "podManagementPolicy", "replicas", "revisionHistoryLimit", "selector", "serviceName", "template", "updateStrategy", "volumeClaimTemplates"})
public class StatefulSetSpec implements Editable, KubernetesResource {
   @JsonProperty("minReadySeconds")
   private Integer minReadySeconds;
   @JsonProperty("ordinals")
   private StatefulSetOrdinals ordinals;
   @JsonProperty("persistentVolumeClaimRetentionPolicy")
   private StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy;
   @JsonProperty("podManagementPolicy")
   private String podManagementPolicy;
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonProperty("revisionHistoryLimit")
   private Integer revisionHistoryLimit;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("serviceName")
   private String serviceName;
   @JsonProperty("template")
   private PodTemplateSpec template;
   @JsonProperty("updateStrategy")
   private StatefulSetUpdateStrategy updateStrategy;
   @JsonProperty("volumeClaimTemplates")
   @JsonInclude(Include.NON_EMPTY)
   private List volumeClaimTemplates = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatefulSetSpec() {
   }

   public StatefulSetSpec(Integer minReadySeconds, StatefulSetOrdinals ordinals, StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy, String podManagementPolicy, Integer replicas, Integer revisionHistoryLimit, LabelSelector selector, String serviceName, PodTemplateSpec template, StatefulSetUpdateStrategy updateStrategy, List volumeClaimTemplates) {
      this.minReadySeconds = minReadySeconds;
      this.ordinals = ordinals;
      this.persistentVolumeClaimRetentionPolicy = persistentVolumeClaimRetentionPolicy;
      this.podManagementPolicy = podManagementPolicy;
      this.replicas = replicas;
      this.revisionHistoryLimit = revisionHistoryLimit;
      this.selector = selector;
      this.serviceName = serviceName;
      this.template = template;
      this.updateStrategy = updateStrategy;
      this.volumeClaimTemplates = volumeClaimTemplates;
   }

   @JsonProperty("minReadySeconds")
   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   @JsonProperty("minReadySeconds")
   public void setMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
   }

   @JsonProperty("ordinals")
   public StatefulSetOrdinals getOrdinals() {
      return this.ordinals;
   }

   @JsonProperty("ordinals")
   public void setOrdinals(StatefulSetOrdinals ordinals) {
      this.ordinals = ordinals;
   }

   @JsonProperty("persistentVolumeClaimRetentionPolicy")
   public StatefulSetPersistentVolumeClaimRetentionPolicy getPersistentVolumeClaimRetentionPolicy() {
      return this.persistentVolumeClaimRetentionPolicy;
   }

   @JsonProperty("persistentVolumeClaimRetentionPolicy")
   public void setPersistentVolumeClaimRetentionPolicy(StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy) {
      this.persistentVolumeClaimRetentionPolicy = persistentVolumeClaimRetentionPolicy;
   }

   @JsonProperty("podManagementPolicy")
   public String getPodManagementPolicy() {
      return this.podManagementPolicy;
   }

   @JsonProperty("podManagementPolicy")
   public void setPodManagementPolicy(String podManagementPolicy) {
      this.podManagementPolicy = podManagementPolicy;
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

   @JsonProperty("serviceName")
   public String getServiceName() {
      return this.serviceName;
   }

   @JsonProperty("serviceName")
   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   @JsonProperty("template")
   public PodTemplateSpec getTemplate() {
      return this.template;
   }

   @JsonProperty("template")
   public void setTemplate(PodTemplateSpec template) {
      this.template = template;
   }

   @JsonProperty("updateStrategy")
   public StatefulSetUpdateStrategy getUpdateStrategy() {
      return this.updateStrategy;
   }

   @JsonProperty("updateStrategy")
   public void setUpdateStrategy(StatefulSetUpdateStrategy updateStrategy) {
      this.updateStrategy = updateStrategy;
   }

   @JsonProperty("volumeClaimTemplates")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumeClaimTemplates() {
      return this.volumeClaimTemplates;
   }

   @JsonProperty("volumeClaimTemplates")
   public void setVolumeClaimTemplates(List volumeClaimTemplates) {
      this.volumeClaimTemplates = volumeClaimTemplates;
   }

   @JsonIgnore
   public StatefulSetSpecBuilder edit() {
      return new StatefulSetSpecBuilder(this);
   }

   @JsonIgnore
   public StatefulSetSpecBuilder toBuilder() {
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
      return "StatefulSetSpec(minReadySeconds=" + var10000 + ", ordinals=" + this.getOrdinals() + ", persistentVolumeClaimRetentionPolicy=" + this.getPersistentVolumeClaimRetentionPolicy() + ", podManagementPolicy=" + this.getPodManagementPolicy() + ", replicas=" + this.getReplicas() + ", revisionHistoryLimit=" + this.getRevisionHistoryLimit() + ", selector=" + this.getSelector() + ", serviceName=" + this.getServiceName() + ", template=" + this.getTemplate() + ", updateStrategy=" + this.getUpdateStrategy() + ", volumeClaimTemplates=" + this.getVolumeClaimTemplates() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatefulSetSpec)) {
         return false;
      } else {
         StatefulSetSpec other = (StatefulSetSpec)o;
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

            Object this$ordinals = this.getOrdinals();
            Object other$ordinals = other.getOrdinals();
            if (this$ordinals == null) {
               if (other$ordinals != null) {
                  return false;
               }
            } else if (!this$ordinals.equals(other$ordinals)) {
               return false;
            }

            Object this$persistentVolumeClaimRetentionPolicy = this.getPersistentVolumeClaimRetentionPolicy();
            Object other$persistentVolumeClaimRetentionPolicy = other.getPersistentVolumeClaimRetentionPolicy();
            if (this$persistentVolumeClaimRetentionPolicy == null) {
               if (other$persistentVolumeClaimRetentionPolicy != null) {
                  return false;
               }
            } else if (!this$persistentVolumeClaimRetentionPolicy.equals(other$persistentVolumeClaimRetentionPolicy)) {
               return false;
            }

            Object this$podManagementPolicy = this.getPodManagementPolicy();
            Object other$podManagementPolicy = other.getPodManagementPolicy();
            if (this$podManagementPolicy == null) {
               if (other$podManagementPolicy != null) {
                  return false;
               }
            } else if (!this$podManagementPolicy.equals(other$podManagementPolicy)) {
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

            Object this$serviceName = this.getServiceName();
            Object other$serviceName = other.getServiceName();
            if (this$serviceName == null) {
               if (other$serviceName != null) {
                  return false;
               }
            } else if (!this$serviceName.equals(other$serviceName)) {
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

            Object this$volumeClaimTemplates = this.getVolumeClaimTemplates();
            Object other$volumeClaimTemplates = other.getVolumeClaimTemplates();
            if (this$volumeClaimTemplates == null) {
               if (other$volumeClaimTemplates != null) {
                  return false;
               }
            } else if (!this$volumeClaimTemplates.equals(other$volumeClaimTemplates)) {
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
      return other instanceof StatefulSetSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $minReadySeconds = this.getMinReadySeconds();
      result = result * 59 + ($minReadySeconds == null ? 43 : $minReadySeconds.hashCode());
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $revisionHistoryLimit = this.getRevisionHistoryLimit();
      result = result * 59 + ($revisionHistoryLimit == null ? 43 : $revisionHistoryLimit.hashCode());
      Object $ordinals = this.getOrdinals();
      result = result * 59 + ($ordinals == null ? 43 : $ordinals.hashCode());
      Object $persistentVolumeClaimRetentionPolicy = this.getPersistentVolumeClaimRetentionPolicy();
      result = result * 59 + ($persistentVolumeClaimRetentionPolicy == null ? 43 : $persistentVolumeClaimRetentionPolicy.hashCode());
      Object $podManagementPolicy = this.getPodManagementPolicy();
      result = result * 59 + ($podManagementPolicy == null ? 43 : $podManagementPolicy.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $serviceName = this.getServiceName();
      result = result * 59 + ($serviceName == null ? 43 : $serviceName.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $updateStrategy = this.getUpdateStrategy();
      result = result * 59 + ($updateStrategy == null ? 43 : $updateStrategy.hashCode());
      Object $volumeClaimTemplates = this.getVolumeClaimTemplates();
      result = result * 59 + ($volumeClaimTemplates == null ? 43 : $volumeClaimTemplates.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
