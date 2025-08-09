package io.fabric8.kubernetes.api.model.authorization.v1;

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
@JsonPropertyOrder({"extra", "groups", "nonResourceAttributes", "resourceAttributes", "uid", "user"})
public class SubjectAccessReviewSpec implements Editable, KubernetesResource {
   @JsonProperty("extra")
   @JsonInclude(Include.NON_EMPTY)
   private Map extra = new LinkedHashMap();
   @JsonProperty("groups")
   @JsonInclude(Include.NON_EMPTY)
   private List groups = new ArrayList();
   @JsonProperty("nonResourceAttributes")
   private NonResourceAttributes nonResourceAttributes;
   @JsonProperty("resourceAttributes")
   private ResourceAttributes resourceAttributes;
   @JsonProperty("uid")
   private String uid;
   @JsonProperty("user")
   private String user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SubjectAccessReviewSpec() {
   }

   public SubjectAccessReviewSpec(Map extra, List groups, NonResourceAttributes nonResourceAttributes, ResourceAttributes resourceAttributes, String uid, String user) {
      this.extra = extra;
      this.groups = groups;
      this.nonResourceAttributes = nonResourceAttributes;
      this.resourceAttributes = resourceAttributes;
      this.uid = uid;
      this.user = user;
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

   @JsonProperty("nonResourceAttributes")
   public NonResourceAttributes getNonResourceAttributes() {
      return this.nonResourceAttributes;
   }

   @JsonProperty("nonResourceAttributes")
   public void setNonResourceAttributes(NonResourceAttributes nonResourceAttributes) {
      this.nonResourceAttributes = nonResourceAttributes;
   }

   @JsonProperty("resourceAttributes")
   public ResourceAttributes getResourceAttributes() {
      return this.resourceAttributes;
   }

   @JsonProperty("resourceAttributes")
   public void setResourceAttributes(ResourceAttributes resourceAttributes) {
      this.resourceAttributes = resourceAttributes;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
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
   public SubjectAccessReviewSpecBuilder edit() {
      return new SubjectAccessReviewSpecBuilder(this);
   }

   @JsonIgnore
   public SubjectAccessReviewSpecBuilder toBuilder() {
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
      return "SubjectAccessReviewSpec(extra=" + var10000 + ", groups=" + this.getGroups() + ", nonResourceAttributes=" + this.getNonResourceAttributes() + ", resourceAttributes=" + this.getResourceAttributes() + ", uid=" + this.getUid() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubjectAccessReviewSpec)) {
         return false;
      } else {
         SubjectAccessReviewSpec other = (SubjectAccessReviewSpec)o;
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

            Object this$nonResourceAttributes = this.getNonResourceAttributes();
            Object other$nonResourceAttributes = other.getNonResourceAttributes();
            if (this$nonResourceAttributes == null) {
               if (other$nonResourceAttributes != null) {
                  return false;
               }
            } else if (!this$nonResourceAttributes.equals(other$nonResourceAttributes)) {
               return false;
            }

            Object this$resourceAttributes = this.getResourceAttributes();
            Object other$resourceAttributes = other.getResourceAttributes();
            if (this$resourceAttributes == null) {
               if (other$resourceAttributes != null) {
                  return false;
               }
            } else if (!this$resourceAttributes.equals(other$resourceAttributes)) {
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
      return other instanceof SubjectAccessReviewSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $extra = this.getExtra();
      result = result * 59 + ($extra == null ? 43 : $extra.hashCode());
      Object $groups = this.getGroups();
      result = result * 59 + ($groups == null ? 43 : $groups.hashCode());
      Object $nonResourceAttributes = this.getNonResourceAttributes();
      result = result * 59 + ($nonResourceAttributes == null ? 43 : $nonResourceAttributes.hashCode());
      Object $resourceAttributes = this.getResourceAttributes();
      result = result * 59 + ($resourceAttributes == null ? 43 : $resourceAttributes.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
