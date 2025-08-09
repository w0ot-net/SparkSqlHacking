package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
@JsonPropertyOrder({"conversionReviewVersions", "strategy", "webhookClientConfig"})
public class CustomResourceConversion implements Editable, KubernetesResource {
   @JsonProperty("conversionReviewVersions")
   @JsonInclude(Include.NON_EMPTY)
   private List conversionReviewVersions = new ArrayList();
   @JsonProperty("strategy")
   private String strategy;
   @JsonProperty("webhookClientConfig")
   private WebhookClientConfig webhookClientConfig;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceConversion() {
   }

   public CustomResourceConversion(List conversionReviewVersions, String strategy, WebhookClientConfig webhookClientConfig) {
      this.conversionReviewVersions = conversionReviewVersions;
      this.strategy = strategy;
      this.webhookClientConfig = webhookClientConfig;
   }

   @JsonProperty("conversionReviewVersions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConversionReviewVersions() {
      return this.conversionReviewVersions;
   }

   @JsonProperty("conversionReviewVersions")
   public void setConversionReviewVersions(List conversionReviewVersions) {
      this.conversionReviewVersions = conversionReviewVersions;
   }

   @JsonProperty("strategy")
   public String getStrategy() {
      return this.strategy;
   }

   @JsonProperty("strategy")
   public void setStrategy(String strategy) {
      this.strategy = strategy;
   }

   @JsonProperty("webhookClientConfig")
   public WebhookClientConfig getWebhookClientConfig() {
      return this.webhookClientConfig;
   }

   @JsonProperty("webhookClientConfig")
   public void setWebhookClientConfig(WebhookClientConfig webhookClientConfig) {
      this.webhookClientConfig = webhookClientConfig;
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
      List var10000 = this.getConversionReviewVersions();
      return "CustomResourceConversion(conversionReviewVersions=" + var10000 + ", strategy=" + this.getStrategy() + ", webhookClientConfig=" + this.getWebhookClientConfig() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$conversionReviewVersions = this.getConversionReviewVersions();
            Object other$conversionReviewVersions = other.getConversionReviewVersions();
            if (this$conversionReviewVersions == null) {
               if (other$conversionReviewVersions != null) {
                  return false;
               }
            } else if (!this$conversionReviewVersions.equals(other$conversionReviewVersions)) {
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

            Object this$webhookClientConfig = this.getWebhookClientConfig();
            Object other$webhookClientConfig = other.getWebhookClientConfig();
            if (this$webhookClientConfig == null) {
               if (other$webhookClientConfig != null) {
                  return false;
               }
            } else if (!this$webhookClientConfig.equals(other$webhookClientConfig)) {
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
      Object $conversionReviewVersions = this.getConversionReviewVersions();
      result = result * 59 + ($conversionReviewVersions == null ? 43 : $conversionReviewVersions.hashCode());
      Object $strategy = this.getStrategy();
      result = result * 59 + ($strategy == null ? 43 : $strategy.hashCode());
      Object $webhookClientConfig = this.getWebhookClientConfig();
      result = result * 59 + ($webhookClientConfig == null ? 43 : $webhookClientConfig.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
