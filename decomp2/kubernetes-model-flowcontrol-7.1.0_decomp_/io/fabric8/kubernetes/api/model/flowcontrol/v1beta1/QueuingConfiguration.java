package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"handSize", "queueLengthLimit", "queues"})
public class QueuingConfiguration implements Editable, KubernetesResource {
   @JsonProperty("handSize")
   private Integer handSize;
   @JsonProperty("queueLengthLimit")
   private Integer queueLengthLimit;
   @JsonProperty("queues")
   private Integer queues;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public QueuingConfiguration() {
   }

   public QueuingConfiguration(Integer handSize, Integer queueLengthLimit, Integer queues) {
      this.handSize = handSize;
      this.queueLengthLimit = queueLengthLimit;
      this.queues = queues;
   }

   @JsonProperty("handSize")
   public Integer getHandSize() {
      return this.handSize;
   }

   @JsonProperty("handSize")
   public void setHandSize(Integer handSize) {
      this.handSize = handSize;
   }

   @JsonProperty("queueLengthLimit")
   public Integer getQueueLengthLimit() {
      return this.queueLengthLimit;
   }

   @JsonProperty("queueLengthLimit")
   public void setQueueLengthLimit(Integer queueLengthLimit) {
      this.queueLengthLimit = queueLengthLimit;
   }

   @JsonProperty("queues")
   public Integer getQueues() {
      return this.queues;
   }

   @JsonProperty("queues")
   public void setQueues(Integer queues) {
      this.queues = queues;
   }

   @JsonIgnore
   public QueuingConfigurationBuilder edit() {
      return new QueuingConfigurationBuilder(this);
   }

   @JsonIgnore
   public QueuingConfigurationBuilder toBuilder() {
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
      Integer var10000 = this.getHandSize();
      return "QueuingConfiguration(handSize=" + var10000 + ", queueLengthLimit=" + this.getQueueLengthLimit() + ", queues=" + this.getQueues() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof QueuingConfiguration)) {
         return false;
      } else {
         QueuingConfiguration other = (QueuingConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$handSize = this.getHandSize();
            Object other$handSize = other.getHandSize();
            if (this$handSize == null) {
               if (other$handSize != null) {
                  return false;
               }
            } else if (!this$handSize.equals(other$handSize)) {
               return false;
            }

            Object this$queueLengthLimit = this.getQueueLengthLimit();
            Object other$queueLengthLimit = other.getQueueLengthLimit();
            if (this$queueLengthLimit == null) {
               if (other$queueLengthLimit != null) {
                  return false;
               }
            } else if (!this$queueLengthLimit.equals(other$queueLengthLimit)) {
               return false;
            }

            Object this$queues = this.getQueues();
            Object other$queues = other.getQueues();
            if (this$queues == null) {
               if (other$queues != null) {
                  return false;
               }
            } else if (!this$queues.equals(other$queues)) {
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
      return other instanceof QueuingConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $handSize = this.getHandSize();
      result = result * 59 + ($handSize == null ? 43 : $handSize.hashCode());
      Object $queueLengthLimit = this.getQueueLengthLimit();
      result = result * 59 + ($queueLengthLimit == null ? 43 : $queueLengthLimit.hashCode());
      Object $queues = this.getQueues();
      result = result * 59 + ($queues == null ? 43 : $queues.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
