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
@JsonPropertyOrder({"minReadySeconds", "replicas", "selector", "template"})
public class ReplicaSetSpec implements Editable, KubernetesResource {
   @JsonProperty("minReadySeconds")
   private Integer minReadySeconds;
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonProperty("template")
   private PodTemplateSpec template;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ReplicaSetSpec() {
   }

   public ReplicaSetSpec(Integer minReadySeconds, Integer replicas, LabelSelector selector, PodTemplateSpec template) {
      this.minReadySeconds = minReadySeconds;
      this.replicas = replicas;
      this.selector = selector;
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

   @JsonProperty("replicas")
   public Integer getReplicas() {
      return this.replicas;
   }

   @JsonProperty("replicas")
   public void setReplicas(Integer replicas) {
      this.replicas = replicas;
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

   @JsonIgnore
   public ReplicaSetSpecBuilder edit() {
      return new ReplicaSetSpecBuilder(this);
   }

   @JsonIgnore
   public ReplicaSetSpecBuilder toBuilder() {
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
      return "ReplicaSetSpec(minReadySeconds=" + var10000 + ", replicas=" + this.getReplicas() + ", selector=" + this.getSelector() + ", template=" + this.getTemplate() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReplicaSetSpec)) {
         return false;
      } else {
         ReplicaSetSpec other = (ReplicaSetSpec)o;
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
      return other instanceof ReplicaSetSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $minReadySeconds = this.getMinReadySeconds();
      result = result * 59 + ($minReadySeconds == null ? 43 : $minReadySeconds.hashCode());
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
