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
@JsonPropertyOrder({"extra", "groups", "uid", "username"})
public class UserInfo implements Editable, KubernetesResource {
   @JsonProperty("extra")
   @JsonInclude(Include.NON_EMPTY)
   private Map extra = new LinkedHashMap();
   @JsonProperty("groups")
   @JsonInclude(Include.NON_EMPTY)
   private List groups = new ArrayList();
   @JsonProperty("uid")
   private String uid;
   @JsonProperty("username")
   private String username;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public UserInfo() {
   }

   public UserInfo(Map extra, List groups, String uid, String username) {
      this.extra = extra;
      this.groups = groups;
      this.uid = uid;
      this.username = username;
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

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
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
   public UserInfoBuilder edit() {
      return new UserInfoBuilder(this);
   }

   @JsonIgnore
   public UserInfoBuilder toBuilder() {
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
      Map var10000 = this.getExtra();
      return "UserInfo(extra=" + var10000 + ", groups=" + this.getGroups() + ", uid=" + this.getUid() + ", username=" + this.getUsername() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UserInfo)) {
         return false;
      } else {
         UserInfo other = (UserInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
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

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof UserInfo;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $extra = this.getExtra();
      result = result * 59 + ($extra == null ? 43 : $extra.hashCode());
      Object $groups = this.getGroups();
      result = result * 59 + ($groups == null ? 43 : $groups.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $username = this.getUsername();
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
