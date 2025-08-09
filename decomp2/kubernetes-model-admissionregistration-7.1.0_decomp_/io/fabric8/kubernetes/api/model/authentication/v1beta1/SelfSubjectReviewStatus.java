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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"userInfo"})
public class SelfSubjectReviewStatus implements Editable, KubernetesResource {
   @JsonProperty("userInfo")
   private io.fabric8.kubernetes.api.model.authentication.UserInfo userInfo;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SelfSubjectReviewStatus() {
   }

   public SelfSubjectReviewStatus(io.fabric8.kubernetes.api.model.authentication.UserInfo userInfo) {
      this.userInfo = userInfo;
   }

   @JsonProperty("userInfo")
   public io.fabric8.kubernetes.api.model.authentication.UserInfo getUserInfo() {
      return this.userInfo;
   }

   @JsonProperty("userInfo")
   public void setUserInfo(io.fabric8.kubernetes.api.model.authentication.UserInfo userInfo) {
      this.userInfo = userInfo;
   }

   @JsonIgnore
   public SelfSubjectReviewStatusBuilder edit() {
      return new SelfSubjectReviewStatusBuilder(this);
   }

   @JsonIgnore
   public SelfSubjectReviewStatusBuilder toBuilder() {
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
      io.fabric8.kubernetes.api.model.authentication.UserInfo var10000 = this.getUserInfo();
      return "SelfSubjectReviewStatus(userInfo=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SelfSubjectReviewStatus)) {
         return false;
      } else {
         SelfSubjectReviewStatus other = (SelfSubjectReviewStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$userInfo = this.getUserInfo();
            Object other$userInfo = other.getUserInfo();
            if (this$userInfo == null) {
               if (other$userInfo != null) {
                  return false;
               }
            } else if (!this$userInfo.equals(other$userInfo)) {
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
      return other instanceof SelfSubjectReviewStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $userInfo = this.getUserInfo();
      result = result * 59 + ($userInfo == null ? 43 : $userInfo.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
