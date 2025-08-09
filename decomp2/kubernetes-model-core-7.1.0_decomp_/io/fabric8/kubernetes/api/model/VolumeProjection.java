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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"clusterTrustBundle", "configMap", "downwardAPI", "secret", "serviceAccountToken"})
public class VolumeProjection implements Editable, KubernetesResource {
   @JsonProperty("clusterTrustBundle")
   private ClusterTrustBundleProjection clusterTrustBundle;
   @JsonProperty("configMap")
   private ConfigMapProjection configMap;
   @JsonProperty("downwardAPI")
   private DownwardAPIProjection downwardAPI;
   @JsonProperty("secret")
   private SecretProjection secret;
   @JsonProperty("serviceAccountToken")
   private ServiceAccountTokenProjection serviceAccountToken;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VolumeProjection() {
   }

   public VolumeProjection(ClusterTrustBundleProjection clusterTrustBundle, ConfigMapProjection configMap, DownwardAPIProjection downwardAPI, SecretProjection secret, ServiceAccountTokenProjection serviceAccountToken) {
      this.clusterTrustBundle = clusterTrustBundle;
      this.configMap = configMap;
      this.downwardAPI = downwardAPI;
      this.secret = secret;
      this.serviceAccountToken = serviceAccountToken;
   }

   @JsonProperty("clusterTrustBundle")
   public ClusterTrustBundleProjection getClusterTrustBundle() {
      return this.clusterTrustBundle;
   }

   @JsonProperty("clusterTrustBundle")
   public void setClusterTrustBundle(ClusterTrustBundleProjection clusterTrustBundle) {
      this.clusterTrustBundle = clusterTrustBundle;
   }

   @JsonProperty("configMap")
   public ConfigMapProjection getConfigMap() {
      return this.configMap;
   }

   @JsonProperty("configMap")
   public void setConfigMap(ConfigMapProjection configMap) {
      this.configMap = configMap;
   }

   @JsonProperty("downwardAPI")
   public DownwardAPIProjection getDownwardAPI() {
      return this.downwardAPI;
   }

   @JsonProperty("downwardAPI")
   public void setDownwardAPI(DownwardAPIProjection downwardAPI) {
      this.downwardAPI = downwardAPI;
   }

   @JsonProperty("secret")
   public SecretProjection getSecret() {
      return this.secret;
   }

   @JsonProperty("secret")
   public void setSecret(SecretProjection secret) {
      this.secret = secret;
   }

   @JsonProperty("serviceAccountToken")
   public ServiceAccountTokenProjection getServiceAccountToken() {
      return this.serviceAccountToken;
   }

   @JsonProperty("serviceAccountToken")
   public void setServiceAccountToken(ServiceAccountTokenProjection serviceAccountToken) {
      this.serviceAccountToken = serviceAccountToken;
   }

   @JsonIgnore
   public VolumeProjectionBuilder edit() {
      return new VolumeProjectionBuilder(this);
   }

   @JsonIgnore
   public VolumeProjectionBuilder toBuilder() {
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
      ClusterTrustBundleProjection var10000 = this.getClusterTrustBundle();
      return "VolumeProjection(clusterTrustBundle=" + var10000 + ", configMap=" + this.getConfigMap() + ", downwardAPI=" + this.getDownwardAPI() + ", secret=" + this.getSecret() + ", serviceAccountToken=" + this.getServiceAccountToken() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VolumeProjection)) {
         return false;
      } else {
         VolumeProjection other = (VolumeProjection)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clusterTrustBundle = this.getClusterTrustBundle();
            Object other$clusterTrustBundle = other.getClusterTrustBundle();
            if (this$clusterTrustBundle == null) {
               if (other$clusterTrustBundle != null) {
                  return false;
               }
            } else if (!this$clusterTrustBundle.equals(other$clusterTrustBundle)) {
               return false;
            }

            Object this$configMap = this.getConfigMap();
            Object other$configMap = other.getConfigMap();
            if (this$configMap == null) {
               if (other$configMap != null) {
                  return false;
               }
            } else if (!this$configMap.equals(other$configMap)) {
               return false;
            }

            Object this$downwardAPI = this.getDownwardAPI();
            Object other$downwardAPI = other.getDownwardAPI();
            if (this$downwardAPI == null) {
               if (other$downwardAPI != null) {
                  return false;
               }
            } else if (!this$downwardAPI.equals(other$downwardAPI)) {
               return false;
            }

            Object this$secret = this.getSecret();
            Object other$secret = other.getSecret();
            if (this$secret == null) {
               if (other$secret != null) {
                  return false;
               }
            } else if (!this$secret.equals(other$secret)) {
               return false;
            }

            Object this$serviceAccountToken = this.getServiceAccountToken();
            Object other$serviceAccountToken = other.getServiceAccountToken();
            if (this$serviceAccountToken == null) {
               if (other$serviceAccountToken != null) {
                  return false;
               }
            } else if (!this$serviceAccountToken.equals(other$serviceAccountToken)) {
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
      return other instanceof VolumeProjection;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clusterTrustBundle = this.getClusterTrustBundle();
      result = result * 59 + ($clusterTrustBundle == null ? 43 : $clusterTrustBundle.hashCode());
      Object $configMap = this.getConfigMap();
      result = result * 59 + ($configMap == null ? 43 : $configMap.hashCode());
      Object $downwardAPI = this.getDownwardAPI();
      result = result * 59 + ($downwardAPI == null ? 43 : $downwardAPI.hashCode());
      Object $secret = this.getSecret();
      result = result * 59 + ($secret == null ? 43 : $secret.hashCode());
      Object $serviceAccountToken = this.getServiceAccountToken();
      result = result * 59 + ($serviceAccountToken == null ? 43 : $serviceAccountToken.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
