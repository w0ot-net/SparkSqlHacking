package io.fabric8.kubernetes.api.model.admission.v1beta1;

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
import io.fabric8.kubernetes.api.model.GroupVersionKind;
import io.fabric8.kubernetes.api.model.GroupVersionResource;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.authentication.UserInfo;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"kind", "dryRun", "name", "namespace", "object", "oldObject", "operation", "options", "requestKind", "requestResource", "requestSubResource", "resource", "subResource", "uid", "userInfo"})
public class AdmissionRequest implements Editable, KubernetesResource {
   @JsonProperty("dryRun")
   private Boolean dryRun;
   @JsonProperty("kind")
   private GroupVersionKind kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("object")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object object;
   @JsonProperty("oldObject")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object oldObject;
   @JsonProperty("operation")
   private String operation;
   @JsonProperty("options")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object options;
   @JsonProperty("requestKind")
   private GroupVersionKind requestKind;
   @JsonProperty("requestResource")
   private GroupVersionResource requestResource;
   @JsonProperty("requestSubResource")
   private String requestSubResource;
   @JsonProperty("resource")
   private GroupVersionResource resource;
   @JsonProperty("subResource")
   private String subResource;
   @JsonProperty("uid")
   private String uid;
   @JsonProperty("userInfo")
   private UserInfo userInfo;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AdmissionRequest() {
   }

   public AdmissionRequest(Boolean dryRun, GroupVersionKind kind, String name, String namespace, Object object, Object oldObject, String operation, Object options, GroupVersionKind requestKind, GroupVersionResource requestResource, String requestSubResource, GroupVersionResource resource, String subResource, String uid, UserInfo userInfo) {
      this.dryRun = dryRun;
      this.kind = kind;
      this.name = name;
      this.namespace = namespace;
      this.object = object;
      this.oldObject = oldObject;
      this.operation = operation;
      this.options = options;
      this.requestKind = requestKind;
      this.requestResource = requestResource;
      this.requestSubResource = requestSubResource;
      this.resource = resource;
      this.subResource = subResource;
      this.uid = uid;
      this.userInfo = userInfo;
   }

   @JsonProperty("dryRun")
   public Boolean getDryRun() {
      return this.dryRun;
   }

   @JsonProperty("dryRun")
   public void setDryRun(Boolean dryRun) {
      this.dryRun = dryRun;
   }

   @JsonProperty("kind")
   public GroupVersionKind getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(GroupVersionKind kind) {
      this.kind = kind;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("object")
   public Object getObject() {
      return this.object;
   }

   @JsonProperty("object")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setObject(Object object) {
      this.object = object;
   }

   @JsonProperty("oldObject")
   public Object getOldObject() {
      return this.oldObject;
   }

   @JsonProperty("oldObject")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setOldObject(Object oldObject) {
      this.oldObject = oldObject;
   }

   @JsonProperty("operation")
   public String getOperation() {
      return this.operation;
   }

   @JsonProperty("operation")
   public void setOperation(String operation) {
      this.operation = operation;
   }

   @JsonProperty("options")
   public Object getOptions() {
      return this.options;
   }

   @JsonProperty("options")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setOptions(Object options) {
      this.options = options;
   }

   @JsonProperty("requestKind")
   public GroupVersionKind getRequestKind() {
      return this.requestKind;
   }

   @JsonProperty("requestKind")
   public void setRequestKind(GroupVersionKind requestKind) {
      this.requestKind = requestKind;
   }

   @JsonProperty("requestResource")
   public GroupVersionResource getRequestResource() {
      return this.requestResource;
   }

   @JsonProperty("requestResource")
   public void setRequestResource(GroupVersionResource requestResource) {
      this.requestResource = requestResource;
   }

   @JsonProperty("requestSubResource")
   public String getRequestSubResource() {
      return this.requestSubResource;
   }

   @JsonProperty("requestSubResource")
   public void setRequestSubResource(String requestSubResource) {
      this.requestSubResource = requestSubResource;
   }

   @JsonProperty("resource")
   public GroupVersionResource getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(GroupVersionResource resource) {
      this.resource = resource;
   }

   @JsonProperty("subResource")
   public String getSubResource() {
      return this.subResource;
   }

   @JsonProperty("subResource")
   public void setSubResource(String subResource) {
      this.subResource = subResource;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonProperty("userInfo")
   public UserInfo getUserInfo() {
      return this.userInfo;
   }

   @JsonProperty("userInfo")
   public void setUserInfo(UserInfo userInfo) {
      this.userInfo = userInfo;
   }

   @JsonIgnore
   public AdmissionRequestBuilder edit() {
      return new AdmissionRequestBuilder(this);
   }

   @JsonIgnore
   public AdmissionRequestBuilder toBuilder() {
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
      Boolean var10000 = this.getDryRun();
      return "AdmissionRequest(dryRun=" + var10000 + ", kind=" + this.getKind() + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", object=" + this.getObject() + ", oldObject=" + this.getOldObject() + ", operation=" + this.getOperation() + ", options=" + this.getOptions() + ", requestKind=" + this.getRequestKind() + ", requestResource=" + this.getRequestResource() + ", requestSubResource=" + this.getRequestSubResource() + ", resource=" + this.getResource() + ", subResource=" + this.getSubResource() + ", uid=" + this.getUid() + ", userInfo=" + this.getUserInfo() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AdmissionRequest)) {
         return false;
      } else {
         AdmissionRequest other = (AdmissionRequest)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$dryRun = this.getDryRun();
            Object other$dryRun = other.getDryRun();
            if (this$dryRun == null) {
               if (other$dryRun != null) {
                  return false;
               }
            } else if (!this$dryRun.equals(other$dryRun)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
               return false;
            }

            Object this$object = this.getObject();
            Object other$object = other.getObject();
            if (this$object == null) {
               if (other$object != null) {
                  return false;
               }
            } else if (!this$object.equals(other$object)) {
               return false;
            }

            Object this$oldObject = this.getOldObject();
            Object other$oldObject = other.getOldObject();
            if (this$oldObject == null) {
               if (other$oldObject != null) {
                  return false;
               }
            } else if (!this$oldObject.equals(other$oldObject)) {
               return false;
            }

            Object this$operation = this.getOperation();
            Object other$operation = other.getOperation();
            if (this$operation == null) {
               if (other$operation != null) {
                  return false;
               }
            } else if (!this$operation.equals(other$operation)) {
               return false;
            }

            Object this$options = this.getOptions();
            Object other$options = other.getOptions();
            if (this$options == null) {
               if (other$options != null) {
                  return false;
               }
            } else if (!this$options.equals(other$options)) {
               return false;
            }

            Object this$requestKind = this.getRequestKind();
            Object other$requestKind = other.getRequestKind();
            if (this$requestKind == null) {
               if (other$requestKind != null) {
                  return false;
               }
            } else if (!this$requestKind.equals(other$requestKind)) {
               return false;
            }

            Object this$requestResource = this.getRequestResource();
            Object other$requestResource = other.getRequestResource();
            if (this$requestResource == null) {
               if (other$requestResource != null) {
                  return false;
               }
            } else if (!this$requestResource.equals(other$requestResource)) {
               return false;
            }

            Object this$requestSubResource = this.getRequestSubResource();
            Object other$requestSubResource = other.getRequestSubResource();
            if (this$requestSubResource == null) {
               if (other$requestSubResource != null) {
                  return false;
               }
            } else if (!this$requestSubResource.equals(other$requestSubResource)) {
               return false;
            }

            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
               return false;
            }

            Object this$subResource = this.getSubResource();
            Object other$subResource = other.getSubResource();
            if (this$subResource == null) {
               if (other$subResource != null) {
                  return false;
               }
            } else if (!this$subResource.equals(other$subResource)) {
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

            Object this$userInfo = this.getUserInfo();
            Object other$userInfo = other.getUserInfo();
            if (this$userInfo == null) {
               if (other$userInfo != null) {
                  return false;
               }
            } else if (!this$userInfo.equals(other$userInfo)) {
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
      return other instanceof AdmissionRequest;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $dryRun = this.getDryRun();
      result = result * 59 + ($dryRun == null ? 43 : $dryRun.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $object = this.getObject();
      result = result * 59 + ($object == null ? 43 : $object.hashCode());
      Object $oldObject = this.getOldObject();
      result = result * 59 + ($oldObject == null ? 43 : $oldObject.hashCode());
      Object $operation = this.getOperation();
      result = result * 59 + ($operation == null ? 43 : $operation.hashCode());
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      Object $requestKind = this.getRequestKind();
      result = result * 59 + ($requestKind == null ? 43 : $requestKind.hashCode());
      Object $requestResource = this.getRequestResource();
      result = result * 59 + ($requestResource == null ? 43 : $requestResource.hashCode());
      Object $requestSubResource = this.getRequestSubResource();
      result = result * 59 + ($requestSubResource == null ? 43 : $requestSubResource.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $subResource = this.getSubResource();
      result = result * 59 + ($subResource == null ? 43 : $subResource.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $userInfo = this.getUserInfo();
      result = result * 59 + ($userInfo == null ? 43 : $userInfo.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
