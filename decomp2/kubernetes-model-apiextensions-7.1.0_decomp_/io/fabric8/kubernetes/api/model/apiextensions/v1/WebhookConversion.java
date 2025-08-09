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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"clientConfig", "conversionReviewVersions"})
public class WebhookConversion implements Editable, KubernetesResource {
   @JsonProperty("clientConfig")
   private WebhookClientConfig clientConfig;
   @JsonProperty("conversionReviewVersions")
   @JsonInclude(Include.NON_EMPTY)
   private List conversionReviewVersions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public WebhookConversion() {
   }

   public WebhookConversion(WebhookClientConfig clientConfig, List conversionReviewVersions) {
      this.clientConfig = clientConfig;
      this.conversionReviewVersions = conversionReviewVersions;
   }

   @JsonProperty("clientConfig")
   public WebhookClientConfig getClientConfig() {
      return this.clientConfig;
   }

   @JsonProperty("clientConfig")
   public void setClientConfig(WebhookClientConfig clientConfig) {
      this.clientConfig = clientConfig;
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

   @JsonIgnore
   public WebhookConversionBuilder edit() {
      return new WebhookConversionBuilder(this);
   }

   @JsonIgnore
   public WebhookConversionBuilder toBuilder() {
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
      WebhookClientConfig var10000 = this.getClientConfig();
      return "WebhookConversion(clientConfig=" + var10000 + ", conversionReviewVersions=" + this.getConversionReviewVersions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof WebhookConversion)) {
         return false;
      } else {
         WebhookConversion other = (WebhookConversion)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clientConfig = this.getClientConfig();
            Object other$clientConfig = other.getClientConfig();
            if (this$clientConfig == null) {
               if (other$clientConfig != null) {
                  return false;
               }
            } else if (!this$clientConfig.equals(other$clientConfig)) {
               return false;
            }

            Object this$conversionReviewVersions = this.getConversionReviewVersions();
            Object other$conversionReviewVersions = other.getConversionReviewVersions();
            if (this$conversionReviewVersions == null) {
               if (other$conversionReviewVersions != null) {
                  return false;
               }
            } else if (!this$conversionReviewVersions.equals(other$conversionReviewVersions)) {
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
      return other instanceof WebhookConversion;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clientConfig = this.getClientConfig();
      result = result * 59 + ($clientConfig == null ? 43 : $clientConfig.hashCode());
      Object $conversionReviewVersions = this.getConversionReviewVersions();
      result = result * 59 + ($conversionReviewVersions == null ? 43 : $conversionReviewVersions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
