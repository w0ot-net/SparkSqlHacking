package io.fabric8.kubernetes.api.model.apiextensions.v1;

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
@JsonPropertyOrder({"strategy", "webhook"})
public class CustomResourceConversion implements Editable, KubernetesResource {
   @JsonProperty("strategy")
   private String strategy;
   @JsonProperty("webhook")
   private WebhookConversion webhook;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceConversion() {
   }

   public CustomResourceConversion(String strategy, WebhookConversion webhook) {
      this.strategy = strategy;
      this.webhook = webhook;
   }

   @JsonProperty("strategy")
   public String getStrategy() {
      return this.strategy;
   }

   @JsonProperty("strategy")
   public void setStrategy(String strategy) {
      this.strategy = strategy;
   }

   @JsonProperty("webhook")
   public WebhookConversion getWebhook() {
      return this.webhook;
   }

   @JsonProperty("webhook")
   public void setWebhook(WebhookConversion webhook) {
      this.webhook = webhook;
   }

   @JsonIgnore
   public CustomResourceConversionBuilder edit() {
      return new CustomResourceConversionBuilder(this);
   }

   @JsonIgnore
   public CustomResourceConversionBuilder toBuilder() {
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
      String var10000 = this.getStrategy();
      return "CustomResourceConversion(strategy=" + var10000 + ", webhook=" + this.getWebhook() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceConversion)) {
         return false;
      } else {
         CustomResourceConversion other = (CustomResourceConversion)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$strategy = this.getStrategy();
            Object other$strategy = other.getStrategy();
            if (this$strategy == null) {
               if (other$strategy != null) {
                  return false;
               }
            } else if (!this$strategy.equals(other$strategy)) {
               return false;
            }

            Object this$webhook = this.getWebhook();
            Object other$webhook = other.getWebhook();
            if (this$webhook == null) {
               if (other$webhook != null) {
                  return false;
               }
            } else if (!this$webhook.equals(other$webhook)) {
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
      return other instanceof CustomResourceConversion;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $strategy = this.getStrategy();
      result = result * 59 + ($strategy == null ? 43 : $strategy.hashCode());
      Object $webhook = this.getWebhook();
      result = result * 59 + ($webhook == null ? 43 : $webhook.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
