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
@JsonPropertyOrder({"caBundle", "group", "groupPriorityMinimum", "insecureSkipTLSVerify", "service", "version", "versionPriority"})
public class APIServiceSpec implements Editable, KubernetesResource {
   @JsonProperty("caBundle")
   private String caBundle;
   @JsonProperty("group")
   private String group;
   @JsonProperty("groupPriorityMinimum")
   private Integer groupPriorityMinimum;
   @JsonProperty("insecureSkipTLSVerify")
   private Boolean insecureSkipTLSVerify;
   @JsonProperty("service")
   private ServiceReference service;
   @JsonProperty("version")
   private String version;
   @JsonProperty("versionPriority")
   private Integer versionPriority;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public APIServiceSpec() {
   }

   public APIServiceSpec(String caBundle, String group, Integer groupPriorityMinimum, Boolean insecureSkipTLSVerify, ServiceReference service, String version, Integer versionPriority) {
      this.caBundle = caBundle;
      this.group = group;
      this.groupPriorityMinimum = groupPriorityMinimum;
      this.insecureSkipTLSVerify = insecureSkipTLSVerify;
      this.service = service;
      this.version = version;
      this.versionPriority = versionPriority;
   }

   @JsonProperty("caBundle")
   public String getCaBundle() {
      return this.caBundle;
   }

   @JsonProperty("caBundle")
   public void setCaBundle(String caBundle) {
      this.caBundle = caBundle;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
   }

   @JsonProperty("groupPriorityMinimum")
   public Integer getGroupPriorityMinimum() {
      return this.groupPriorityMinimum;
   }

   @JsonProperty("groupPriorityMinimum")
   public void setGroupPriorityMinimum(Integer groupPriorityMinimum) {
      this.groupPriorityMinimum = groupPriorityMinimum;
   }

   @JsonProperty("insecureSkipTLSVerify")
   public Boolean getInsecureSkipTLSVerify() {
      return this.insecureSkipTLSVerify;
   }

   @JsonProperty("insecureSkipTLSVerify")
   public void setInsecureSkipTLSVerify(Boolean insecureSkipTLSVerify) {
      this.insecureSkipTLSVerify = insecureSkipTLSVerify;
   }

   @JsonProperty("service")
   public ServiceReference getService() {
      return this.service;
   }

   @JsonProperty("service")
   public void setService(ServiceReference service) {
      this.service = service;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonProperty("versionPriority")
   public Integer getVersionPriority() {
      return this.versionPriority;
   }

   @JsonProperty("versionPriority")
   public void setVersionPriority(Integer versionPriority) {
      this.versionPriority = versionPriority;
   }

   @JsonIgnore
   public APIServiceSpecBuilder edit() {
      return new APIServiceSpecBuilder(this);
   }

   @JsonIgnore
   public APIServiceSpecBuilder toBuilder() {
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
      String var10000 = this.getCaBundle();
      return "APIServiceSpec(caBundle=" + var10000 + ", group=" + this.getGroup() + ", groupPriorityMinimum=" + this.getGroupPriorityMinimum() + ", insecureSkipTLSVerify=" + this.getInsecureSkipTLSVerify() + ", service=" + this.getService() + ", version=" + this.getVersion() + ", versionPriority=" + this.getVersionPriority() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof APIServiceSpec)) {
         return false;
      } else {
         APIServiceSpec other = (APIServiceSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$groupPriorityMinimum = this.getGroupPriorityMinimum();
            Object other$groupPriorityMinimum = other.getGroupPriorityMinimum();
            if (this$groupPriorityMinimum == null) {
               if (other$groupPriorityMinimum != null) {
                  return false;
               }
            } else if (!this$groupPriorityMinimum.equals(other$groupPriorityMinimum)) {
               return false;
            }

            Object this$insecureSkipTLSVerify = this.getInsecureSkipTLSVerify();
            Object other$insecureSkipTLSVerify = other.getInsecureSkipTLSVerify();
            if (this$insecureSkipTLSVerify == null) {
               if (other$insecureSkipTLSVerify != null) {
                  return false;
               }
            } else if (!this$insecureSkipTLSVerify.equals(other$insecureSkipTLSVerify)) {
               return false;
            }

            Object this$versionPriority = this.getVersionPriority();
            Object other$versionPriority = other.getVersionPriority();
            if (this$versionPriority == null) {
               if (other$versionPriority != null) {
                  return false;
               }
            } else if (!this$versionPriority.equals(other$versionPriority)) {
               return false;
            }

            Object this$caBundle = this.getCaBundle();
            Object other$caBundle = other.getCaBundle();
            if (this$caBundle == null) {
               if (other$caBundle != null) {
                  return false;
               }
            } else if (!this$caBundle.equals(other$caBundle)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
               return false;
            }

            Object this$service = this.getService();
            Object other$service = other.getService();
            if (this$service == null) {
               if (other$service != null) {
                  return false;
               }
            } else if (!this$service.equals(other$service)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
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
      return other instanceof APIServiceSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $groupPriorityMinimum = this.getGroupPriorityMinimum();
      result = result * 59 + ($groupPriorityMinimum == null ? 43 : $groupPriorityMinimum.hashCode());
      Object $insecureSkipTLSVerify = this.getInsecureSkipTLSVerify();
      result = result * 59 + ($insecureSkipTLSVerify == null ? 43 : $insecureSkipTLSVerify.hashCode());
      Object $versionPriority = this.getVersionPriority();
      result = result * 59 + ($versionPriority == null ? 43 : $versionPriority.hashCode());
      Object $caBundle = this.getCaBundle();
      result = result * 59 + ($caBundle == null ? 43 : $caBundle.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $service = this.getService();
      result = result * 59 + ($service == null ? 43 : $service.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
