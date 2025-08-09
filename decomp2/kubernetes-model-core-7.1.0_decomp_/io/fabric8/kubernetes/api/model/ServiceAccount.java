package io.fabric8.kubernetes.api.model;

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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "automountServiceAccountToken", "imagePullSecrets", "secrets"})
@Version("v1")
@Group("")
public class ServiceAccount implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("automountServiceAccountToken")
   private Boolean automountServiceAccountToken;
   @JsonProperty("imagePullSecrets")
   @JsonInclude(Include.NON_EMPTY)
   private List imagePullSecrets = new ArrayList();
   @JsonProperty("kind")
   private String kind = "ServiceAccount";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("secrets")
   @JsonInclude(Include.NON_EMPTY)
   private List secrets = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ServiceAccount() {
   }

   public ServiceAccount(String apiVersion, Boolean automountServiceAccountToken, List imagePullSecrets, String kind, ObjectMeta metadata, List secrets) {
      this.apiVersion = apiVersion;
      this.automountServiceAccountToken = automountServiceAccountToken;
      this.imagePullSecrets = imagePullSecrets;
      this.kind = kind;
      this.metadata = metadata;
      this.secrets = secrets;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("automountServiceAccountToken")
   public Boolean getAutomountServiceAccountToken() {
      return this.automountServiceAccountToken;
   }

   @JsonProperty("automountServiceAccountToken")
   public void setAutomountServiceAccountToken(Boolean automountServiceAccountToken) {
      this.automountServiceAccountToken = automountServiceAccountToken;
   }

   @JsonProperty("imagePullSecrets")
   @JsonInclude(Include.NON_EMPTY)
   public List getImagePullSecrets() {
      return this.imagePullSecrets;
   }

   @JsonProperty("imagePullSecrets")
   public void setImagePullSecrets(List imagePullSecrets) {
      this.imagePullSecrets = imagePullSecrets;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("secrets")
   @JsonInclude(Include.NON_EMPTY)
   public List getSecrets() {
      return this.secrets;
   }

   @JsonProperty("secrets")
   public void setSecrets(List secrets) {
      this.secrets = secrets;
   }

   @JsonIgnore
   public ServiceAccountBuilder edit() {
      return new ServiceAccountBuilder(this);
   }

   @JsonIgnore
   public ServiceAccountBuilder toBuilder() {
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
      return "ServiceAccount(apiVersion=" + var10000 + ", automountServiceAccountToken=" + this.getAutomountServiceAccountToken() + ", imagePullSecrets=" + this.getImagePullSecrets() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", secrets=" + this.getSecrets() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServiceAccount)) {
         return false;
      } else {
         ServiceAccount other = (ServiceAccount)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$automountServiceAccountToken = this.getAutomountServiceAccountToken();
            Object other$automountServiceAccountToken = other.getAutomountServiceAccountToken();
            if (this$automountServiceAccountToken == null) {
               if (other$automountServiceAccountToken != null) {
                  return false;
               }
            } else if (!this$automountServiceAccountToken.equals(other$automountServiceAccountToken)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$imagePullSecrets = this.getImagePullSecrets();
            Object other$imagePullSecrets = other.getImagePullSecrets();
            if (this$imagePullSecrets == null) {
               if (other$imagePullSecrets != null) {
                  return false;
               }
            } else if (!this$imagePullSecrets.equals(other$imagePullSecrets)) {
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

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$secrets = this.getSecrets();
            Object other$secrets = other.getSecrets();
            if (this$secrets == null) {
               if (other$secrets != null) {
                  return false;
               }
            } else if (!this$secrets.equals(other$secrets)) {
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
      return other instanceof ServiceAccount;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $automountServiceAccountToken = this.getAutomountServiceAccountToken();
      result = result * 59 + ($automountServiceAccountToken == null ? 43 : $automountServiceAccountToken.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $imagePullSecrets = this.getImagePullSecrets();
      result = result * 59 + ($imagePullSecrets == null ? 43 : $imagePullSecrets.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $secrets = this.getSecrets();
      result = result * 59 + ($secrets == null ? 43 : $secrets.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
