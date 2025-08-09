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
@JsonPropertyOrder({"gid", "supplementalGroups", "uid"})
public class LinuxContainerUser implements Editable, KubernetesResource {
   @JsonProperty("gid")
   private Long gid;
   @JsonProperty("supplementalGroups")
   @JsonInclude(Include.NON_EMPTY)
   private List supplementalGroups = new ArrayList();
   @JsonProperty("uid")
   private Long uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LinuxContainerUser() {
   }

   public LinuxContainerUser(Long gid, List supplementalGroups, Long uid) {
      this.gid = gid;
      this.supplementalGroups = supplementalGroups;
      this.uid = uid;
   }

   @JsonProperty("gid")
   public Long getGid() {
      return this.gid;
   }

   @JsonProperty("gid")
   public void setGid(Long gid) {
      this.gid = gid;
   }

   @JsonProperty("supplementalGroups")
   @JsonInclude(Include.NON_EMPTY)
   public List getSupplementalGroups() {
      return this.supplementalGroups;
   }

   @JsonProperty("supplementalGroups")
   public void setSupplementalGroups(List supplementalGroups) {
      this.supplementalGroups = supplementalGroups;
   }

   @JsonProperty("uid")
   public Long getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(Long uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public LinuxContainerUserBuilder edit() {
      return new LinuxContainerUserBuilder(this);
   }

   @JsonIgnore
   public LinuxContainerUserBuilder toBuilder() {
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
      Long var10000 = this.getGid();
      return "LinuxContainerUser(gid=" + var10000 + ", supplementalGroups=" + this.getSupplementalGroups() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LinuxContainerUser)) {
         return false;
      } else {
         LinuxContainerUser other = (LinuxContainerUser)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$gid = this.getGid();
            Object other$gid = other.getGid();
            if (this$gid == null) {
               if (other$gid != null) {
                  return false;
               }
            } else if (!this$gid.equals(other$gid)) {
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

            Object this$supplementalGroups = this.getSupplementalGroups();
            Object other$supplementalGroups = other.getSupplementalGroups();
            if (this$supplementalGroups == null) {
               if (other$supplementalGroups != null) {
                  return false;
               }
            } else if (!this$supplementalGroups.equals(other$supplementalGroups)) {
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
      return other instanceof LinuxContainerUser;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $gid = this.getGid();
      result = result * 59 + ($gid == null ? 43 : $gid.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $supplementalGroups = this.getSupplementalGroups();
      result = result * 59 + ($supplementalGroups == null ? 43 : $supplementalGroups.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
