package io.fabric8.kubernetes.api.model.admission.v1;

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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "request", "response"})
@Version("v1")
@Group("admission.k8s.io")
public class AdmissionReview implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "admission.k8s.io/v1";
   @JsonProperty("kind")
   private String kind = "AdmissionReview";
   @JsonProperty("request")
   private AdmissionRequest request;
   @JsonProperty("response")
   private AdmissionResponse response;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AdmissionReview() {
   }

   public AdmissionReview(String apiVersion, String kind, AdmissionRequest request, AdmissionResponse response) {
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.request = request;
      this.response = response;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("request")
   public AdmissionRequest getRequest() {
      return this.request;
   }

   @JsonProperty("request")
   public void setRequest(AdmissionRequest request) {
      this.request = request;
   }

   @JsonProperty("response")
   public AdmissionResponse getResponse() {
      return this.response;
   }

   @JsonProperty("response")
   public void setResponse(AdmissionResponse response) {
      this.response = response;
   }

   @JsonIgnore
   public AdmissionReviewBuilder edit() {
      return new AdmissionReviewBuilder(this);
   }

   @JsonIgnore
   public AdmissionReviewBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "AdmissionReview(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", request=" + this.getRequest() + ", response=" + this.getResponse() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AdmissionReview)) {
         return false;
      } else {
         AdmissionReview other = (AdmissionReview)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$request = this.getRequest();
            Object other$request = other.getRequest();
            if (this$request == null) {
               if (other$request != null) {
                  return false;
               }
            } else if (!this$request.equals(other$request)) {
               return false;
            }

            Object this$response = this.getResponse();
            Object other$response = other.getResponse();
            if (this$response == null) {
               if (other$response != null) {
                  return false;
               }
            } else if (!this$response.equals(other$response)) {
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
      return other instanceof AdmissionReview;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $request = this.getRequest();
      result = result * 59 + ($request == null ? 43 : $request.hashCode());
      Object $response = this.getResponse();
      result = result * 59 + ($response == null ? 43 : $response.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
