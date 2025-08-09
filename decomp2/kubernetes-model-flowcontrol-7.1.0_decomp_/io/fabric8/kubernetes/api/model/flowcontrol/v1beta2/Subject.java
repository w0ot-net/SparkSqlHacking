package io.fabric8.kubernetes.api.model.flowcontrol.v1beta2;

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
@JsonPropertyOrder({"kind", "group", "serviceAccount", "user"})
public class Subject implements Editable, KubernetesResource {
   @JsonProperty("group")
   private GroupSubject group;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("serviceAccount")
   private ServiceAccountSubject serviceAccount;
   @JsonProperty("user")
   private UserSubject user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Subject() {
   }

   public Subject(GroupSubject group, String kind, ServiceAccountSubject serviceAccount, UserSubject user) {
      this.group = group;
      this.kind = kind;
      this.serviceAccount = serviceAccount;
      this.user = user;
   }

   @JsonProperty("group")
   public GroupSubject getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(GroupSubject group) {
      this.group = group;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("serviceAccount")
   public ServiceAccountSubject getServiceAccount() {
      return this.serviceAccount;
   }

   @JsonProperty("serviceAccount")
   public void setServiceAccount(ServiceAccountSubject serviceAccount) {
      this.serviceAccount = serviceAccount;
   }

   @JsonProperty("user")
   public UserSubject getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(UserSubject user) {
      this.user = user;
   }

   @JsonIgnore
   public SubjectBuilder edit() {
      return new SubjectBuilder(this);
   }

   @JsonIgnore
   public SubjectBuilder toBuilder() {
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
      GroupSubject var10000 = this.getGroup();
      return "Subject(group=" + var10000 + ", kind=" + this.getKind() + ", serviceAccount=" + this.getServiceAccount() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Subject)) {
         return false;
      } else {
         Subject other = (Subject)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
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

            Object this$serviceAccount = this.getServiceAccount();
            Object other$serviceAccount = other.getServiceAccount();
            if (this$serviceAccount == null) {
               if (other$serviceAccount != null) {
                  return false;
               }
            } else if (!this$serviceAccount.equals(other$serviceAccount)) {
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
      return other instanceof Subject;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $serviceAccount = this.getServiceAccount();
      result = result * 59 + ($serviceAccount == null ? 43 : $serviceAccount.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
