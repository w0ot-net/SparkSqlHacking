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
@JsonPropertyOrder({"level", "role", "type", "user"})
public class SELinuxOptions implements Editable, KubernetesResource {
   @JsonProperty("level")
   private String level;
   @JsonProperty("role")
   private String role;
   @JsonProperty("type")
   private String type;
   @JsonProperty("user")
   private String user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SELinuxOptions() {
   }

   public SELinuxOptions(String level, String role, String type, String user) {
      this.level = level;
      this.role = role;
      this.type = type;
      this.user = user;
   }

   @JsonProperty("level")
   public String getLevel() {
      return this.level;
   }

   @JsonProperty("level")
   public void setLevel(String level) {
      this.level = level;
   }

   @JsonProperty("role")
   public String getRole() {
      return this.role;
   }

   @JsonProperty("role")
   public void setRole(String role) {
      this.role = role;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("user")
   public String getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(String user) {
      this.user = user;
   }

   @JsonIgnore
   public SELinuxOptionsBuilder edit() {
      return new SELinuxOptionsBuilder(this);
   }

   @JsonIgnore
   public SELinuxOptionsBuilder toBuilder() {
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
      String var10000 = this.getLevel();
      return "SELinuxOptions(level=" + var10000 + ", role=" + this.getRole() + ", type=" + this.getType() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SELinuxOptions)) {
         return false;
      } else {
         SELinuxOptions other = (SELinuxOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$level = this.getLevel();
            Object other$level = other.getLevel();
            if (this$level == null) {
               if (other$level != null) {
                  return false;
               }
            } else if (!this$level.equals(other$level)) {
               return false;
            }

            Object this$role = this.getRole();
            Object other$role = other.getRole();
            if (this$role == null) {
               if (other$role != null) {
                  return false;
               }
            } else if (!this$role.equals(other$role)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof SELinuxOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $level = this.getLevel();
      result = result * 59 + ($level == null ? 43 : $level.hashCode());
      Object $role = this.getRole();
      result = result * 59 + ($role == null ? 43 : $role.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
