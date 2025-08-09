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
@JsonPropertyOrder({"as", "as-groups", "as-uid", "as-user-extra", "auth-provider", "client-certificate", "client-certificate-data", "client-key", "client-key-data", "exec", "extensions", "password", "token", "tokenFile", "username"})
public class AuthInfo implements Editable, KubernetesResource {
   @JsonProperty("as")
   private String as;
   @JsonProperty("as-groups")
   @JsonInclude(Include.NON_EMPTY)
   private List asGroups = new ArrayList();
   @JsonProperty("as-uid")
   private String asUid;
   @JsonProperty("as-user-extra")
   @JsonInclude(Include.NON_EMPTY)
   private Map asUserExtra = new LinkedHashMap();
   @JsonProperty("auth-provider")
   private AuthProviderConfig authProvider;
   @JsonProperty("client-certificate")
   private String clientCertificate;
   @JsonProperty("client-certificate-data")
   private String clientCertificateData;
   @JsonProperty("client-key")
   private String clientKey;
   @JsonProperty("client-key-data")
   private String clientKeyData;
   @JsonProperty("exec")
   private ExecConfig exec;
   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   private List extensions = new ArrayList();
   @JsonProperty("password")
   private String password;
   @JsonProperty("token")
   private String token;
   @JsonProperty("tokenFile")
   private String tokenFile;
   @JsonProperty("username")
   private String username;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AuthInfo() {
   }

   public AuthInfo(String as, List asGroups, String asUid, Map asUserExtra, AuthProviderConfig authProvider, String clientCertificate, String clientCertificateData, String clientKey, String clientKeyData, ExecConfig exec, List extensions, String password, String token, String tokenFile, String username) {
      this.as = as;
      this.asGroups = asGroups;
      this.asUid = asUid;
      this.asUserExtra = asUserExtra;
      this.authProvider = authProvider;
      this.clientCertificate = clientCertificate;
      this.clientCertificateData = clientCertificateData;
      this.clientKey = clientKey;
      this.clientKeyData = clientKeyData;
      this.exec = exec;
      this.extensions = extensions;
      this.password = password;
      this.token = token;
      this.tokenFile = tokenFile;
      this.username = username;
   }

   @JsonProperty("as")
   public String getAs() {
      return this.as;
   }

   @JsonProperty("as")
   public void setAs(String as) {
      this.as = as;
   }

   @JsonProperty("as-groups")
   @JsonInclude(Include.NON_EMPTY)
   public List getAsGroups() {
      return this.asGroups;
   }

   @JsonProperty("as-groups")
   public void setAsGroups(List asGroups) {
      this.asGroups = asGroups;
   }

   @JsonProperty("as-uid")
   public String getAsUid() {
      return this.asUid;
   }

   @JsonProperty("as-uid")
   public void setAsUid(String asUid) {
      this.asUid = asUid;
   }

   @JsonProperty("as-user-extra")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAsUserExtra() {
      return this.asUserExtra;
   }

   @JsonProperty("as-user-extra")
   public void setAsUserExtra(Map asUserExtra) {
      this.asUserExtra = asUserExtra;
   }

   @JsonProperty("auth-provider")
   public AuthProviderConfig getAuthProvider() {
      return this.authProvider;
   }

   @JsonProperty("auth-provider")
   public void setAuthProvider(AuthProviderConfig authProvider) {
      this.authProvider = authProvider;
   }

   @JsonProperty("client-certificate")
   public String getClientCertificate() {
      return this.clientCertificate;
   }

   @JsonProperty("client-certificate")
   public void setClientCertificate(String clientCertificate) {
      this.clientCertificate = clientCertificate;
   }

   @JsonProperty("client-certificate-data")
   public String getClientCertificateData() {
      return this.clientCertificateData;
   }

   @JsonProperty("client-certificate-data")
   public void setClientCertificateData(String clientCertificateData) {
      this.clientCertificateData = clientCertificateData;
   }

   @JsonProperty("client-key")
   public String getClientKey() {
      return this.clientKey;
   }

   @JsonProperty("client-key")
   public void setClientKey(String clientKey) {
      this.clientKey = clientKey;
   }

   @JsonProperty("client-key-data")
   public String getClientKeyData() {
      return this.clientKeyData;
   }

   @JsonProperty("client-key-data")
   public void setClientKeyData(String clientKeyData) {
      this.clientKeyData = clientKeyData;
   }

   @JsonProperty("exec")
   public ExecConfig getExec() {
      return this.exec;
   }

   @JsonProperty("exec")
   public void setExec(ExecConfig exec) {
      this.exec = exec;
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

   @JsonProperty("password")
   public String getPassword() {
      return this.password;
   }

   @JsonProperty("password")
   public void setPassword(String password) {
      this.password = password;
   }

   @JsonProperty("token")
   public String getToken() {
      return this.token;
   }

   @JsonProperty("token")
   public void setToken(String token) {
      this.token = token;
   }

   @JsonProperty("tokenFile")
   public String getTokenFile() {
      return this.tokenFile;
   }

   @JsonProperty("tokenFile")
   public void setTokenFile(String tokenFile) {
      this.tokenFile = tokenFile;
   }

   @JsonProperty("username")
   public String getUsername() {
      return this.username;
   }

   @JsonProperty("username")
   public void setUsername(String username) {
      this.username = username;
   }

   @JsonIgnore
   public AuthInfoBuilder edit() {
      return new AuthInfoBuilder(this);
   }

   @JsonIgnore
   public AuthInfoBuilder toBuilder() {
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
      String var10000 = this.getAs();
      return "AuthInfo(as=" + var10000 + ", asGroups=" + this.getAsGroups() + ", asUid=" + this.getAsUid() + ", asUserExtra=" + this.getAsUserExtra() + ", authProvider=" + this.getAuthProvider() + ", clientCertificate=" + this.getClientCertificate() + ", clientCertificateData=" + this.getClientCertificateData() + ", clientKey=" + this.getClientKey() + ", clientKeyData=" + this.getClientKeyData() + ", exec=" + this.getExec() + ", extensions=" + this.getExtensions() + ", password=" + this.getPassword() + ", token=" + this.getToken() + ", tokenFile=" + this.getTokenFile() + ", username=" + this.getUsername() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AuthInfo)) {
         return false;
      } else {
         AuthInfo other = (AuthInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$as = this.getAs();
            Object other$as = other.getAs();
            if (this$as == null) {
               if (other$as != null) {
                  return false;
               }
            } else if (!this$as.equals(other$as)) {
               return false;
            }

            Object this$asGroups = this.getAsGroups();
            Object other$asGroups = other.getAsGroups();
            if (this$asGroups == null) {
               if (other$asGroups != null) {
                  return false;
               }
            } else if (!this$asGroups.equals(other$asGroups)) {
               return false;
            }

            Object this$asUid = this.getAsUid();
            Object other$asUid = other.getAsUid();
            if (this$asUid == null) {
               if (other$asUid != null) {
                  return false;
               }
            } else if (!this$asUid.equals(other$asUid)) {
               return false;
            }

            Object this$asUserExtra = this.getAsUserExtra();
            Object other$asUserExtra = other.getAsUserExtra();
            if (this$asUserExtra == null) {
               if (other$asUserExtra != null) {
                  return false;
               }
            } else if (!this$asUserExtra.equals(other$asUserExtra)) {
               return false;
            }

            Object this$authProvider = this.getAuthProvider();
            Object other$authProvider = other.getAuthProvider();
            if (this$authProvider == null) {
               if (other$authProvider != null) {
                  return false;
               }
            } else if (!this$authProvider.equals(other$authProvider)) {
               return false;
            }

            Object this$clientCertificate = this.getClientCertificate();
            Object other$clientCertificate = other.getClientCertificate();
            if (this$clientCertificate == null) {
               if (other$clientCertificate != null) {
                  return false;
               }
            } else if (!this$clientCertificate.equals(other$clientCertificate)) {
               return false;
            }

            Object this$clientCertificateData = this.getClientCertificateData();
            Object other$clientCertificateData = other.getClientCertificateData();
            if (this$clientCertificateData == null) {
               if (other$clientCertificateData != null) {
                  return false;
               }
            } else if (!this$clientCertificateData.equals(other$clientCertificateData)) {
               return false;
            }

            Object this$clientKey = this.getClientKey();
            Object other$clientKey = other.getClientKey();
            if (this$clientKey == null) {
               if (other$clientKey != null) {
                  return false;
               }
            } else if (!this$clientKey.equals(other$clientKey)) {
               return false;
            }

            Object this$clientKeyData = this.getClientKeyData();
            Object other$clientKeyData = other.getClientKeyData();
            if (this$clientKeyData == null) {
               if (other$clientKeyData != null) {
                  return false;
               }
            } else if (!this$clientKeyData.equals(other$clientKeyData)) {
               return false;
            }

            Object this$exec = this.getExec();
            Object other$exec = other.getExec();
            if (this$exec == null) {
               if (other$exec != null) {
                  return false;
               }
            } else if (!this$exec.equals(other$exec)) {
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

            Object this$password = this.getPassword();
            Object other$password = other.getPassword();
            if (this$password == null) {
               if (other$password != null) {
                  return false;
               }
            } else if (!this$password.equals(other$password)) {
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

            Object this$tokenFile = this.getTokenFile();
            Object other$tokenFile = other.getTokenFile();
            if (this$tokenFile == null) {
               if (other$tokenFile != null) {
                  return false;
               }
            } else if (!this$tokenFile.equals(other$tokenFile)) {
               return false;
            }

            Object this$username = this.getUsername();
            Object other$username = other.getUsername();
            if (this$username == null) {
               if (other$username != null) {
                  return false;
               }
            } else if (!this$username.equals(other$username)) {
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
      return other instanceof AuthInfo;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $as = this.getAs();
      result = result * 59 + ($as == null ? 43 : $as.hashCode());
      Object $asGroups = this.getAsGroups();
      result = result * 59 + ($asGroups == null ? 43 : $asGroups.hashCode());
      Object $asUid = this.getAsUid();
      result = result * 59 + ($asUid == null ? 43 : $asUid.hashCode());
      Object $asUserExtra = this.getAsUserExtra();
      result = result * 59 + ($asUserExtra == null ? 43 : $asUserExtra.hashCode());
      Object $authProvider = this.getAuthProvider();
      result = result * 59 + ($authProvider == null ? 43 : $authProvider.hashCode());
      Object $clientCertificate = this.getClientCertificate();
      result = result * 59 + ($clientCertificate == null ? 43 : $clientCertificate.hashCode());
      Object $clientCertificateData = this.getClientCertificateData();
      result = result * 59 + ($clientCertificateData == null ? 43 : $clientCertificateData.hashCode());
      Object $clientKey = this.getClientKey();
      result = result * 59 + ($clientKey == null ? 43 : $clientKey.hashCode());
      Object $clientKeyData = this.getClientKeyData();
      result = result * 59 + ($clientKeyData == null ? 43 : $clientKeyData.hashCode());
      Object $exec = this.getExec();
      result = result * 59 + ($exec == null ? 43 : $exec.hashCode());
      Object $extensions = this.getExtensions();
      result = result * 59 + ($extensions == null ? 43 : $extensions.hashCode());
      Object $password = this.getPassword();
      result = result * 59 + ($password == null ? 43 : $password.hashCode());
      Object $token = this.getToken();
      result = result * 59 + ($token == null ? 43 : $token.hashCode());
      Object $tokenFile = this.getTokenFile();
      result = result * 59 + ($tokenFile == null ? 43 : $tokenFile.hashCode());
      Object $username = this.getUsername();
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
