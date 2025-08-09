package io.fabric8.kubernetes.api.model.authentication.v1beta1;

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
@JsonPropertyOrder({"audiences", "token"})
public class TokenReviewSpec implements Editable, KubernetesResource {
   @JsonProperty("audiences")
   @JsonInclude(Include.NON_EMPTY)
   private List audiences = new ArrayList();
   @JsonProperty("token")
   private String token;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TokenReviewSpec() {
   }

   public TokenReviewSpec(List audiences, String token) {
      this.audiences = audiences;
      this.token = token;
   }

   @JsonProperty("audiences")
   @JsonInclude(Include.NON_EMPTY)
   public List getAudiences() {
      return this.audiences;
   }

   @JsonProperty("audiences")
   public void setAudiences(List audiences) {
      this.audiences = audiences;
   }

   @JsonProperty("token")
   public String getToken() {
      return this.token;
   }

   @JsonProperty("token")
   public void setToken(String token) {
      this.token = token;
   }

   @JsonIgnore
   public TokenReviewSpecBuilder edit() {
      return new TokenReviewSpecBuilder(this);
   }

   @JsonIgnore
   public TokenReviewSpecBuilder toBuilder() {
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
      List var10000 = this.getAudiences();
      return "TokenReviewSpec(audiences=" + var10000 + ", token=" + this.getToken() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TokenReviewSpec)) {
         return false;
      } else {
         TokenReviewSpec other = (TokenReviewSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$audiences = this.getAudiences();
            Object other$audiences = other.getAudiences();
            if (this$audiences == null) {
               if (other$audiences != null) {
                  return false;
               }
            } else if (!this$audiences.equals(other$audiences)) {
               return false;
            }

            Object this$token = this.getToken();
            Object other$token = other.getToken();
            if (this$token == null) {
               if (other$token != null) {
                  return false;
               }
            } else if (!this$token.equals(other$token)) {
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
      return other instanceof TokenReviewSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $audiences = this.getAudiences();
      result = result * 59 + ($audiences == null ? 43 : $audiences.hashCode());
      Object $token = this.getToken();
      result = result * 59 + ($token == null ? 43 : $token.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
