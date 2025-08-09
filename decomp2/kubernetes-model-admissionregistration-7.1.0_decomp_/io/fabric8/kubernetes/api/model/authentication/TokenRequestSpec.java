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
@JsonPropertyOrder({"audiences", "boundObjectRef", "expirationSeconds"})
public class TokenRequestSpec implements Editable, KubernetesResource {
   @JsonProperty("audiences")
   @JsonInclude(Include.NON_EMPTY)
   private List audiences = new ArrayList();
   @JsonProperty("boundObjectRef")
   private BoundObjectReference boundObjectRef;
   @JsonProperty("expirationSeconds")
   private Long expirationSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TokenRequestSpec() {
   }

   public TokenRequestSpec(List audiences, BoundObjectReference boundObjectRef, Long expirationSeconds) {
      this.audiences = audiences;
      this.boundObjectRef = boundObjectRef;
      this.expirationSeconds = expirationSeconds;
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

   @JsonProperty("boundObjectRef")
   public BoundObjectReference getBoundObjectRef() {
      return this.boundObjectRef;
   }

   @JsonProperty("boundObjectRef")
   public void setBoundObjectRef(BoundObjectReference boundObjectRef) {
      this.boundObjectRef = boundObjectRef;
   }

   @JsonProperty("expirationSeconds")
   public Long getExpirationSeconds() {
      return this.expirationSeconds;
   }

   @JsonProperty("expirationSeconds")
   public void setExpirationSeconds(Long expirationSeconds) {
      this.expirationSeconds = expirationSeconds;
   }

   @JsonIgnore
   public TokenRequestSpecBuilder edit() {
      return new TokenRequestSpecBuilder(this);
   }

   @JsonIgnore
   public TokenRequestSpecBuilder toBuilder() {
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
      return "TokenRequestSpec(audiences=" + var10000 + ", boundObjectRef=" + this.getBoundObjectRef() + ", expirationSeconds=" + this.getExpirationSeconds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TokenRequestSpec)) {
         return false;
      } else {
         TokenRequestSpec other = (TokenRequestSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expirationSeconds = this.getExpirationSeconds();
            Object other$expirationSeconds = other.getExpirationSeconds();
            if (this$expirationSeconds == null) {
               if (other$expirationSeconds != null) {
                  return false;
               }
            } else if (!this$expirationSeconds.equals(other$expirationSeconds)) {
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

            Object this$boundObjectRef = this.getBoundObjectRef();
            Object other$boundObjectRef = other.getBoundObjectRef();
            if (this$boundObjectRef == null) {
               if (other$boundObjectRef != null) {
                  return false;
               }
            } else if (!this$boundObjectRef.equals(other$boundObjectRef)) {
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
      return other instanceof TokenRequestSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expirationSeconds = this.getExpirationSeconds();
      result = result * 59 + ($expirationSeconds == null ? 43 : $expirationSeconds.hashCode());
      Object $audiences = this.getAudiences();
      result = result * 59 + ($audiences == null ? 43 : $audiences.hashCode());
      Object $boundObjectRef = this.getBoundObjectRef();
      result = result * 59 + ($boundObjectRef == null ? 43 : $boundObjectRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
