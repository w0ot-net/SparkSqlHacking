package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

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
@JsonPropertyOrder({"caBundle", "service", "url"})
public class WebhookClientConfig implements Editable, KubernetesResource {
   @JsonProperty("caBundle")
   private String caBundle;
   @JsonProperty("service")
   private ServiceReference service;
   @JsonProperty("url")
   private String url;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public WebhookClientConfig() {
   }

   public WebhookClientConfig(String caBundle, ServiceReference service, String url) {
      this.caBundle = caBundle;
      this.service = service;
      this.url = url;
   }

   @JsonProperty("caBundle")
   public String getCaBundle() {
      return this.caBundle;
   }

   @JsonProperty("caBundle")
   public void setCaBundle(String caBundle) {
      this.caBundle = caBundle;
   }

   @JsonProperty("service")
   public ServiceReference getService() {
      return this.service;
   }

   @JsonProperty("service")
   public void setService(ServiceReference service) {
      this.service = service;
   }

   @JsonProperty("url")
   public String getUrl() {
      return this.url;
   }

   @JsonProperty("url")
   public void setUrl(String url) {
      this.url = url;
   }

   @JsonIgnore
   public WebhookClientConfigBuilder edit() {
      return new WebhookClientConfigBuilder(this);
   }

   @JsonIgnore
   public WebhookClientConfigBuilder toBuilder() {
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
      String var10000 = this.getCaBundle();
      return "WebhookClientConfig(caBundle=" + var10000 + ", service=" + this.getService() + ", url=" + this.getUrl() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof WebhookClientConfig)) {
         return false;
      } else {
         WebhookClientConfig other = (WebhookClientConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$caBundle = this.getCaBundle();
            Object other$caBundle = other.getCaBundle();
            if (this$caBundle == null) {
               if (other$caBundle != null) {
                  return false;
               }
            } else if (!this$caBundle.equals(other$caBundle)) {
               return false;
            }

            Object this$service = this.getService();
            Object other$service = other.getService();
            if (this$service == null) {
               if (other$service != null) {
                  return false;
               }
            } else if (!this$service.equals(other$service)) {
               return false;
            }

            Object this$url = this.getUrl();
            Object other$url = other.getUrl();
            if (this$url == null) {
               if (other$url != null) {
                  return false;
               }
            } else if (!this$url.equals(other$url)) {
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
      return other instanceof WebhookClientConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $caBundle = this.getCaBundle();
      result = result * 59 + ($caBundle == null ? 43 : $caBundle.hashCode());
      Object $service = this.getService();
      result = result * 59 + ($service == null ? 43 : $service.hashCode());
      Object $url = this.getUrl();
      result = result * 59 + ($url == null ? 43 : $url.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
