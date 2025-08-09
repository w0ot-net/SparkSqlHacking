package io.fabric8.kubernetes.api.model.rbac;

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
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "roleRef", "subjects"})
@Version("v1")
@Group("rbac.authorization.k8s.io")
public class ClusterRoleBinding implements Editable, HasMetadata {
   @JsonProperty("apiVersion")
   private String apiVersion = "rbac.authorization.k8s.io/v1";
   @JsonProperty("kind")
   private String kind = "ClusterRoleBinding";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("roleRef")
   private RoleRef roleRef;
   @JsonProperty("subjects")
   @JsonInclude(Include.NON_EMPTY)
   private List subjects = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ClusterRoleBinding() {
   }

   public ClusterRoleBinding(String apiVersion, String kind, ObjectMeta metadata, RoleRef roleRef, List subjects) {
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.metadata = metadata;
      this.roleRef = roleRef;
      this.subjects = subjects;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("roleRef")
   public RoleRef getRoleRef() {
      return this.roleRef;
   }

   @JsonProperty("roleRef")
   public void setRoleRef(RoleRef roleRef) {
      this.roleRef = roleRef;
   }

   @JsonProperty("subjects")
   @JsonInclude(Include.NON_EMPTY)
   public List getSubjects() {
      return this.subjects;
   }

   @JsonProperty("subjects")
   public void setSubjects(List subjects) {
      this.subjects = subjects;
   }

   @JsonIgnore
   public ClusterRoleBindingBuilder edit() {
      return new ClusterRoleBindingBuilder(this);
   }

   @JsonIgnore
   public ClusterRoleBindingBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "ClusterRoleBinding(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", roleRef=" + this.getRoleRef() + ", subjects=" + this.getSubjects() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClusterRoleBinding)) {
         return false;
      } else {
         ClusterRoleBinding other = (ClusterRoleBinding)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
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

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$roleRef = this.getRoleRef();
            Object other$roleRef = other.getRoleRef();
            if (this$roleRef == null) {
               if (other$roleRef != null) {
                  return false;
               }
            } else if (!this$roleRef.equals(other$roleRef)) {
               return false;
            }

            Object this$subjects = this.getSubjects();
            Object other$subjects = other.getSubjects();
            if (this$subjects == null) {
               if (other$subjects != null) {
                  return false;
               }
            } else if (!this$subjects.equals(other$subjects)) {
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
      return other instanceof ClusterRoleBinding;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $roleRef = this.getRoleRef();
      result = result * 59 + ($roleRef == null ? 43 : $roleRef.hashCode());
      Object $subjects = this.getSubjects();
      result = result * 59 + ($subjects == null ? 43 : $subjects.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
