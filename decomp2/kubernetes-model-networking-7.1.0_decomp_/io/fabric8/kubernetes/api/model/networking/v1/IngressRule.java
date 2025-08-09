package io.fabric8.kubernetes.api.model.networking.v1;

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
@JsonPropertyOrder({"host", "http"})
public class IngressRule implements Editable, KubernetesResource {
   @JsonProperty("host")
   private String host;
   @JsonProperty("http")
   private HTTPIngressRuleValue http;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressRule() {
   }

   public IngressRule(String host, HTTPIngressRuleValue http) {
      this.host = host;
      this.http = http;
   }

   @JsonProperty("host")
   public String getHost() {
      return this.host;
   }

   @JsonProperty("host")
   public void setHost(String host) {
      this.host = host;
   }

   @JsonProperty("http")
   public HTTPIngressRuleValue getHttp() {
      return this.http;
   }

   @JsonProperty("http")
   public void setHttp(HTTPIngressRuleValue http) {
      this.http = http;
   }

   @JsonIgnore
   public IngressRuleBuilder edit() {
      return new IngressRuleBuilder(this);
   }

   @JsonIgnore
   public IngressRuleBuilder toBuilder() {
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
      String var10000 = this.getHost();
      return "IngressRule(host=" + var10000 + ", http=" + this.getHttp() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressRule)) {
         return false;
      } else {
         IngressRule other = (IngressRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$host = this.getHost();
            Object other$host = other.getHost();
            if (this$host == null) {
               if (other$host != null) {
                  return false;
               }
            } else if (!this$host.equals(other$host)) {
               return false;
            }

            Object this$http = this.getHttp();
            Object other$http = other.getHttp();
            if (this$http == null) {
               if (other$http != null) {
                  return false;
               }
            } else if (!this$http.equals(other$http)) {
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
      return other instanceof IngressRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $host = this.getHost();
      result = result * 59 + ($host == null ? 43 : $host.hashCode());
      Object $http = this.getHttp();
      result = result * 59 + ($http == null ? 43 : $http.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
