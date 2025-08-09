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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"certificate-authority", "certificate-authority-data", "disable-compression", "extensions", "insecure-skip-tls-verify", "proxy-url", "server", "tls-server-name"})
public class Cluster implements Editable, KubernetesResource {
   @JsonProperty("certificate-authority")
   private String certificateAuthority;
   @JsonProperty("certificate-authority-data")
   private String certificateAuthorityData;
   @JsonProperty("disable-compression")
   private Boolean disableCompression;
   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   private List extensions = new ArrayList();
   @JsonProperty("insecure-skip-tls-verify")
   private Boolean insecureSkipTlsVerify;
   @JsonProperty("proxy-url")
   private String proxyUrl;
   @JsonProperty("server")
   private String server;
   @JsonProperty("tls-server-name")
   private String tlsServerName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Cluster() {
   }

   public Cluster(String certificateAuthority, String certificateAuthorityData, Boolean disableCompression, List extensions, Boolean insecureSkipTlsVerify, String proxyUrl, String server, String tlsServerName) {
      this.certificateAuthority = certificateAuthority;
      this.certificateAuthorityData = certificateAuthorityData;
      this.disableCompression = disableCompression;
      this.extensions = extensions;
      this.insecureSkipTlsVerify = insecureSkipTlsVerify;
      this.proxyUrl = proxyUrl;
      this.server = server;
      this.tlsServerName = tlsServerName;
   }

   @JsonProperty("certificate-authority")
   public String getCertificateAuthority() {
      return this.certificateAuthority;
   }

   @JsonProperty("certificate-authority")
   public void setCertificateAuthority(String certificateAuthority) {
      this.certificateAuthority = certificateAuthority;
   }

   @JsonProperty("certificate-authority-data")
   public String getCertificateAuthorityData() {
      return this.certificateAuthorityData;
   }

   @JsonProperty("certificate-authority-data")
   public void setCertificateAuthorityData(String certificateAuthorityData) {
      this.certificateAuthorityData = certificateAuthorityData;
   }

   @JsonProperty("disable-compression")
   public Boolean getDisableCompression() {
      return this.disableCompression;
   }

   @JsonProperty("disable-compression")
   public void setDisableCompression(Boolean disableCompression) {
      this.disableCompression = disableCompression;
   }

   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   public List getExtensions() {
      return this.extensions;
   }

   @JsonProperty("extensions")
   public void setExtensions(List extensions) {
      this.extensions = extensions;
   }

   @JsonProperty("insecure-skip-tls-verify")
   public Boolean getInsecureSkipTlsVerify() {
      return this.insecureSkipTlsVerify;
   }

   @JsonProperty("insecure-skip-tls-verify")
   public void setInsecureSkipTlsVerify(Boolean insecureSkipTlsVerify) {
      this.insecureSkipTlsVerify = insecureSkipTlsVerify;
   }

   @JsonProperty("proxy-url")
   public String getProxyUrl() {
      return this.proxyUrl;
   }

   @JsonProperty("proxy-url")
   public void setProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
   }

   @JsonProperty("server")
   public String getServer() {
      return this.server;
   }

   @JsonProperty("server")
   public void setServer(String server) {
      this.server = server;
   }

   @JsonProperty("tls-server-name")
   public String getTlsServerName() {
      return this.tlsServerName;
   }

   @JsonProperty("tls-server-name")
   public void setTlsServerName(String tlsServerName) {
      this.tlsServerName = tlsServerName;
   }

   @JsonIgnore
   public ClusterBuilder edit() {
      return new ClusterBuilder(this);
   }

   @JsonIgnore
   public ClusterBuilder toBuilder() {
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
      String var10000 = this.getCertificateAuthority();
      return "Cluster(certificateAuthority=" + var10000 + ", certificateAuthorityData=" + this.getCertificateAuthorityData() + ", disableCompression=" + this.getDisableCompression() + ", extensions=" + this.getExtensions() + ", insecureSkipTlsVerify=" + this.getInsecureSkipTlsVerify() + ", proxyUrl=" + this.getProxyUrl() + ", server=" + this.getServer() + ", tlsServerName=" + this.getTlsServerName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Cluster)) {
         return false;
      } else {
         Cluster other = (Cluster)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$disableCompression = this.getDisableCompression();
            Object other$disableCompression = other.getDisableCompression();
            if (this$disableCompression == null) {
               if (other$disableCompression != null) {
                  return false;
               }
            } else if (!this$disableCompression.equals(other$disableCompression)) {
               return false;
            }

            Object this$insecureSkipTlsVerify = this.getInsecureSkipTlsVerify();
            Object other$insecureSkipTlsVerify = other.getInsecureSkipTlsVerify();
            if (this$insecureSkipTlsVerify == null) {
               if (other$insecureSkipTlsVerify != null) {
                  return false;
               }
            } else if (!this$insecureSkipTlsVerify.equals(other$insecureSkipTlsVerify)) {
               return false;
            }

            Object this$certificateAuthority = this.getCertificateAuthority();
            Object other$certificateAuthority = other.getCertificateAuthority();
            if (this$certificateAuthority == null) {
               if (other$certificateAuthority != null) {
                  return false;
               }
            } else if (!this$certificateAuthority.equals(other$certificateAuthority)) {
               return false;
            }

            Object this$certificateAuthorityData = this.getCertificateAuthorityData();
            Object other$certificateAuthorityData = other.getCertificateAuthorityData();
            if (this$certificateAuthorityData == null) {
               if (other$certificateAuthorityData != null) {
                  return false;
               }
            } else if (!this$certificateAuthorityData.equals(other$certificateAuthorityData)) {
               return false;
            }

            Object this$extensions = this.getExtensions();
            Object other$extensions = other.getExtensions();
            if (this$extensions == null) {
               if (other$extensions != null) {
                  return false;
               }
            } else if (!this$extensions.equals(other$extensions)) {
               return false;
            }

            Object this$proxyUrl = this.getProxyUrl();
            Object other$proxyUrl = other.getProxyUrl();
            if (this$proxyUrl == null) {
               if (other$proxyUrl != null) {
                  return false;
               }
            } else if (!this$proxyUrl.equals(other$proxyUrl)) {
               return false;
            }

            Object this$server = this.getServer();
            Object other$server = other.getServer();
            if (this$server == null) {
               if (other$server != null) {
                  return false;
               }
            } else if (!this$server.equals(other$server)) {
               return false;
            }

            Object this$tlsServerName = this.getTlsServerName();
            Object other$tlsServerName = other.getTlsServerName();
            if (this$tlsServerName == null) {
               if (other$tlsServerName != null) {
                  return false;
               }
            } else if (!this$tlsServerName.equals(other$tlsServerName)) {
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
      return other instanceof Cluster;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $disableCompression = this.getDisableCompression();
      result = result * 59 + ($disableCompression == null ? 43 : $disableCompression.hashCode());
      Object $insecureSkipTlsVerify = this.getInsecureSkipTlsVerify();
      result = result * 59 + ($insecureSkipTlsVerify == null ? 43 : $insecureSkipTlsVerify.hashCode());
      Object $certificateAuthority = this.getCertificateAuthority();
      result = result * 59 + ($certificateAuthority == null ? 43 : $certificateAuthority.hashCode());
      Object $certificateAuthorityData = this.getCertificateAuthorityData();
      result = result * 59 + ($certificateAuthorityData == null ? 43 : $certificateAuthorityData.hashCode());
      Object $extensions = this.getExtensions();
      result = result * 59 + ($extensions == null ? 43 : $extensions.hashCode());
      Object $proxyUrl = this.getProxyUrl();
      result = result * 59 + ($proxyUrl == null ? 43 : $proxyUrl.hashCode());
      Object $server = this.getServer();
      result = result * 59 + ($server == null ? 43 : $server.hashCode());
      Object $tlsServerName = this.getTlsServerName();
      result = result * 59 + ($tlsServerName == null ? 43 : $tlsServerName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
