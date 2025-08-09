package io.fabric8.kubernetes.api.model.certificates.v1;

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
@JsonPropertyOrder({"expirationSeconds", "extra", "groups", "request", "signerName", "uid", "usages", "username"})
public class CertificateSigningRequestSpec implements Editable, KubernetesResource {
   @JsonProperty("expirationSeconds")
   private Integer expirationSeconds;
   @JsonProperty("extra")
   @JsonInclude(Include.NON_EMPTY)
   private Map extra = new LinkedHashMap();
   @JsonProperty("groups")
   @JsonInclude(Include.NON_EMPTY)
   private List groups = new ArrayList();
   @JsonProperty("request")
   private String request;
   @JsonProperty("signerName")
   private String signerName;
   @JsonProperty("uid")
   private String uid;
   @JsonProperty("usages")
   @JsonInclude(Include.NON_EMPTY)
   private List usages = new ArrayList();
   @JsonProperty("username")
   private String username;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CertificateSigningRequestSpec() {
   }

   public CertificateSigningRequestSpec(Integer expirationSeconds, Map extra, List groups, String request, String signerName, String uid, List usages, String username) {
      this.expirationSeconds = expirationSeconds;
      this.extra = extra;
      this.groups = groups;
      this.request = request;
      this.signerName = signerName;
      this.uid = uid;
      this.usages = usages;
      this.username = username;
   }

   @JsonProperty("expirationSeconds")
   public Integer getExpirationSeconds() {
      return this.expirationSeconds;
   }

   @JsonProperty("expirationSeconds")
   public void setExpirationSeconds(Integer expirationSeconds) {
      this.expirationSeconds = expirationSeconds;
   }

   @JsonProperty("extra")
   @JsonInclude(Include.NON_EMPTY)
   public Map getExtra() {
      return this.extra;
   }

   @JsonProperty("extra")
   public void setExtra(Map extra) {
      this.extra = extra;
   }

   @JsonProperty("groups")
   @JsonInclude(Include.NON_EMPTY)
   public List getGroups() {
      return this.groups;
   }

   @JsonProperty("groups")
   public void setGroups(List groups) {
      this.groups = groups;
   }

   @JsonProperty("request")
   public String getRequest() {
      return this.request;
   }

   @JsonProperty("request")
   public void setRequest(String request) {
      this.request = request;
   }

   @JsonProperty("signerName")
   public String getSignerName() {
      return this.signerName;
   }

   @JsonProperty("signerName")
   public void setSignerName(String signerName) {
      this.signerName = signerName;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonProperty("usages")
   @JsonInclude(Include.NON_EMPTY)
   public List getUsages() {
      return this.usages;
   }

   @JsonProperty("usages")
   public void setUsages(List usages) {
      this.usages = usages;
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
   public CertificateSigningRequestSpecBuilder edit() {
      return new CertificateSigningRequestSpecBuilder(this);
   }

   @JsonIgnore
   public CertificateSigningRequestSpecBuilder toBuilder() {
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
      Integer var10000 = this.getExpirationSeconds();
      return "CertificateSigningRequestSpec(expirationSeconds=" + var10000 + ", extra=" + this.getExtra() + ", groups=" + this.getGroups() + ", request=" + this.getRequest() + ", signerName=" + this.getSignerName() + ", uid=" + this.getUid() + ", usages=" + this.getUsages() + ", username=" + this.getUsername() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CertificateSigningRequestSpec)) {
         return false;
      } else {
         CertificateSigningRequestSpec other = (CertificateSigningRequestSpec)o;
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

            Object this$extra = this.getExtra();
            Object other$extra = other.getExtra();
            if (this$extra == null) {
               if (other$extra != null) {
                  return false;
               }
            } else if (!this$extra.equals(other$extra)) {
               return false;
            }

            Object this$groups = this.getGroups();
            Object other$groups = other.getGroups();
            if (this$groups == null) {
               if (other$groups != null) {
                  return false;
               }
            } else if (!this$groups.equals(other$groups)) {
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

            Object this$signerName = this.getSignerName();
            Object other$signerName = other.getSignerName();
            if (this$signerName == null) {
               if (other$signerName != null) {
                  return false;
               }
            } else if (!this$signerName.equals(other$signerName)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
               return false;
            }

            Object this$usages = this.getUsages();
            Object other$usages = other.getUsages();
            if (this$usages == null) {
               if (other$usages != null) {
                  return false;
               }
            } else if (!this$usages.equals(other$usages)) {
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
      return other instanceof CertificateSigningRequestSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expirationSeconds = this.getExpirationSeconds();
      result = result * 59 + ($expirationSeconds == null ? 43 : $expirationSeconds.hashCode());
      Object $extra = this.getExtra();
      result = result * 59 + ($extra == null ? 43 : $extra.hashCode());
      Object $groups = this.getGroups();
      result = result * 59 + ($groups == null ? 43 : $groups.hashCode());
      Object $request = this.getRequest();
      result = result * 59 + ($request == null ? 43 : $request.hashCode());
      Object $signerName = this.getSignerName();
      result = result * 59 + ($signerName == null ? 43 : $signerName.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $usages = this.getUsages();
      result = result * 59 + ($usages == null ? 43 : $usages.hashCode());
      Object $username = this.getUsername();
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
