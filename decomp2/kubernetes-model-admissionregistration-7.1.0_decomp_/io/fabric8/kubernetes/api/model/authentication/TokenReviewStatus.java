package io.fabric8.kubernetes.api.model.authentication;

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
@JsonPropertyOrder({"audiences", "authenticated", "error", "user"})
public class TokenReviewStatus implements Editable, KubernetesResource {
   @JsonProperty("audiences")
   @JsonInclude(Include.NON_EMPTY)
   private List audiences = new ArrayList();
   @JsonProperty("authenticated")
   private Boolean authenticated;
   @JsonProperty("error")
   private String error;
   @JsonProperty("user")
   private UserInfo user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TokenReviewStatus() {
   }

   public TokenReviewStatus(List audiences, Boolean authenticated, String error, UserInfo user) {
      this.audiences = audiences;
      this.authenticated = authenticated;
      this.error = error;
      this.user = user;
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

   @JsonProperty("authenticated")
   public Boolean getAuthenticated() {
      return this.authenticated;
   }

   @JsonProperty("authenticated")
   public void setAuthenticated(Boolean authenticated) {
      this.authenticated = authenticated;
   }

   @JsonProperty("error")
   public String getError() {
      return this.error;
   }

   @JsonProperty("error")
   public void setError(String error) {
      this.error = error;
   }

   @JsonProperty("user")
   public UserInfo getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(UserInfo user) {
      this.user = user;
   }

   @JsonIgnore
   public TokenReviewStatusBuilder edit() {
      return new TokenReviewStatusBuilder(this);
   }

   @JsonIgnore
   public TokenReviewStatusBuilder toBuilder() {
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
      return "TokenReviewStatus(audiences=" + var10000 + ", authenticated=" + this.getAuthenticated() + ", error=" + this.getError() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TokenReviewStatus)) {
         return false;
      } else {
         TokenReviewStatus other = (TokenReviewStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$authenticated = this.getAuthenticated();
            Object other$authenticated = other.getAuthenticated();
            if (this$authenticated == null) {
               if (other$authenticated != null) {
                  return false;
               }
            } else if (!this$authenticated.equals(other$authenticated)) {
               return false;
            }

            Object this$audiences = this.getAudiences();
            Object other$audiences = other.getAudiences();
            if (this$audiences == null) {
               if (other$audiences != null) {
                  return false;
               }
            } else if (!this$audiences.equals(other$audiences)) {
               return false;
            }

            Object this$error = this.getError();
            Object other$error = other.getError();
            if (this$error == null) {
               if (other$error != null) {
                  return false;
               }
            } else if (!this$error.equals(other$error)) {
               return false;
            }

            Object this$user = this.getUser();
            Object other$user = other.getUser();
            if (this$user == null) {
               if (other$user != null) {
                  return false;
               }
            } else if (!this$user.equals(other$user)) {
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
      return other instanceof TokenReviewStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $authenticated = this.getAuthenticated();
      result = result * 59 + ($authenticated == null ? 43 : $authenticated.hashCode());
      Object $audiences = this.getAudiences();
      result = result * 59 + ($audiences == null ? 43 : $audiences.hashCode());
      Object $error = this.getError();
      result = result * 59 + ($error == null ? 43 : $error.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
