package io.fabric8.kubernetes.api.model.admission.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.GroupVersionKind;
import io.fabric8.kubernetes.api.model.GroupVersionResource;
import io.fabric8.kubernetes.api.model.authentication.UserInfo;
import io.fabric8.kubernetes.api.model.authentication.UserInfoBuilder;
import io.fabric8.kubernetes.api.model.authentication.UserInfoFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class AdmissionRequestFluent extends BaseFluent {
   private Boolean dryRun;
   private GroupVersionKind kind;
   private String name;
   private String namespace;
   private Object object;
   private Object oldObject;
   private String operation;
   private Object options;
   private GroupVersionKind requestKind;
   private GroupVersionResource requestResource;
   private String requestSubResource;
   private GroupVersionResource resource;
   private String subResource;
   private String uid;
   private UserInfoBuilder userInfo;
   private Map additionalProperties;

   public AdmissionRequestFluent() {
   }

   public AdmissionRequestFluent(AdmissionRequest instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AdmissionRequest instance) {
      instance = instance != null ? instance : new AdmissionRequest();
      if (instance != null) {
         this.withDryRun(instance.getDryRun());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withNamespace(instance.getNamespace());
         this.withObject(instance.getObject());
         this.withOldObject(instance.getOldObject());
         this.withOperation(instance.getOperation());
         this.withOptions(instance.getOptions());
         this.withRequestKind(instance.getRequestKind());
         this.withRequestResource(instance.getRequestResource());
         this.withRequestSubResource(instance.getRequestSubResource());
         this.withResource(instance.getResource());
         this.withSubResource(instance.getSubResource());
         this.withUid(instance.getUid());
         this.withUserInfo(instance.getUserInfo());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getDryRun() {
      return this.dryRun;
   }

   public AdmissionRequestFluent withDryRun(Boolean dryRun) {
      this.dryRun = dryRun;
      return this;
   }

   public boolean hasDryRun() {
      return this.dryRun != null;
   }

   public GroupVersionKind getKind() {
      return this.kind;
   }

   public AdmissionRequestFluent withKind(GroupVersionKind kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public AdmissionRequestFluent withNewKind(String group, String kind, String version) {
      return this.withKind(new GroupVersionKind(group, kind, version));
   }

   public String getName() {
      return this.name;
   }

   public AdmissionRequestFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public AdmissionRequestFluent withNamespace(String namespace) {
      this.namespace = namespace;
      return this;
   }

   public boolean hasNamespace() {
      return this.namespace != null;
   }

   public Object getObject() {
      return this.object;
   }

   public AdmissionRequestFluent withObject(Object object) {
      this.object = object;
      return this;
   }

   public boolean hasObject() {
      return this.object != null;
   }

   public Object getOldObject() {
      return this.oldObject;
   }

   public AdmissionRequestFluent withOldObject(Object oldObject) {
      this.oldObject = oldObject;
      return this;
   }

   public boolean hasOldObject() {
      return this.oldObject != null;
   }

   public String getOperation() {
      return this.operation;
   }

   public AdmissionRequestFluent withOperation(String operation) {
      this.operation = operation;
      return this;
   }

   public boolean hasOperation() {
      return this.operation != null;
   }

   public Object getOptions() {
      return this.options;
   }

   public AdmissionRequestFluent withOptions(Object options) {
      this.options = options;
      return this;
   }

   public boolean hasOptions() {
      return this.options != null;
   }

   public GroupVersionKind getRequestKind() {
      return this.requestKind;
   }

   public AdmissionRequestFluent withRequestKind(GroupVersionKind requestKind) {
      this.requestKind = requestKind;
      return this;
   }

   public boolean hasRequestKind() {
      return this.requestKind != null;
   }

   public AdmissionRequestFluent withNewRequestKind(String group, String kind, String version) {
      return this.withRequestKind(new GroupVersionKind(group, kind, version));
   }

   public GroupVersionResource getRequestResource() {
      return this.requestResource;
   }

   public AdmissionRequestFluent withRequestResource(GroupVersionResource requestResource) {
      this.requestResource = requestResource;
      return this;
   }

   public boolean hasRequestResource() {
      return this.requestResource != null;
   }

   public AdmissionRequestFluent withNewRequestResource(String group, String resource, String version) {
      return this.withRequestResource(new GroupVersionResource(group, resource, version));
   }

   public String getRequestSubResource() {
      return this.requestSubResource;
   }

   public AdmissionRequestFluent withRequestSubResource(String requestSubResource) {
      this.requestSubResource = requestSubResource;
      return this;
   }

   public boolean hasRequestSubResource() {
      return this.requestSubResource != null;
   }

   public GroupVersionResource getResource() {
      return this.resource;
   }

   public AdmissionRequestFluent withResource(GroupVersionResource resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public AdmissionRequestFluent withNewResource(String group, String resource, String version) {
      return this.withResource(new GroupVersionResource(group, resource, version));
   }

   public String getSubResource() {
      return this.subResource;
   }

   public AdmissionRequestFluent withSubResource(String subResource) {
      this.subResource = subResource;
      return this;
   }

   public boolean hasSubResource() {
      return this.subResource != null;
   }

   public String getUid() {
      return this.uid;
   }

   public AdmissionRequestFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public UserInfo buildUserInfo() {
      return this.userInfo != null ? this.userInfo.build() : null;
   }

   public AdmissionRequestFluent withUserInfo(UserInfo userInfo) {
      this._visitables.remove("userInfo");
      if (userInfo != null) {
         this.userInfo = new UserInfoBuilder(userInfo);
         this._visitables.get("userInfo").add(this.userInfo);
      } else {
         this.userInfo = null;
         this._visitables.get("userInfo").remove(this.userInfo);
      }

      return this;
   }

   public boolean hasUserInfo() {
      return this.userInfo != null;
   }

   public UserInfoNested withNewUserInfo() {
      return new UserInfoNested((UserInfo)null);
   }

   public UserInfoNested withNewUserInfoLike(UserInfo item) {
      return new UserInfoNested(item);
   }

   public UserInfoNested editUserInfo() {
      return this.withNewUserInfoLike((UserInfo)Optional.ofNullable(this.buildUserInfo()).orElse((Object)null));
   }

   public UserInfoNested editOrNewUserInfo() {
      return this.withNewUserInfoLike((UserInfo)Optional.ofNullable(this.buildUserInfo()).orElse((new UserInfoBuilder()).build()));
   }

   public UserInfoNested editOrNewUserInfoLike(UserInfo item) {
      return this.withNewUserInfoLike((UserInfo)Optional.ofNullable(this.buildUserInfo()).orElse(item));
   }

   public AdmissionRequestFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AdmissionRequestFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AdmissionRequestFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AdmissionRequestFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public AdmissionRequestFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            AdmissionRequestFluent that = (AdmissionRequestFluent)o;
            if (!Objects.equals(this.dryRun, that.dryRun)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.namespace, that.namespace)) {
               return false;
            } else if (!Objects.equals(this.object, that.object)) {
               return false;
            } else if (!Objects.equals(this.oldObject, that.oldObject)) {
               return false;
            } else if (!Objects.equals(this.operation, that.operation)) {
               return false;
            } else if (!Objects.equals(this.options, that.options)) {
               return false;
            } else if (!Objects.equals(this.requestKind, that.requestKind)) {
               return false;
            } else if (!Objects.equals(this.requestResource, that.requestResource)) {
               return false;
            } else if (!Objects.equals(this.requestSubResource, that.requestSubResource)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
               return false;
            } else if (!Objects.equals(this.subResource, that.subResource)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
               return false;
            } else if (!Objects.equals(this.userInfo, that.userInfo)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.dryRun, this.kind, this.name, this.namespace, this.object, this.oldObject, this.operation, this.options, this.requestKind, this.requestResource, this.requestSubResource, this.resource, this.subResource, this.uid, this.userInfo, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.dryRun != null) {
         sb.append("dryRun:");
         sb.append(this.dryRun + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.namespace != null) {
         sb.append("namespace:");
         sb.append(this.namespace + ",");
      }

      if (this.object != null) {
         sb.append("object:");
         sb.append(this.object + ",");
      }

      if (this.oldObject != null) {
         sb.append("oldObject:");
         sb.append(this.oldObject + ",");
      }

      if (this.operation != null) {
         sb.append("operation:");
         sb.append(this.operation + ",");
      }

      if (this.options != null) {
         sb.append("options:");
         sb.append(this.options + ",");
      }

      if (this.requestKind != null) {
         sb.append("requestKind:");
         sb.append(this.requestKind + ",");
      }

      if (this.requestResource != null) {
         sb.append("requestResource:");
         sb.append(this.requestResource + ",");
      }

      if (this.requestSubResource != null) {
         sb.append("requestSubResource:");
         sb.append(this.requestSubResource + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.subResource != null) {
         sb.append("subResource:");
         sb.append(this.subResource + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.userInfo != null) {
         sb.append("userInfo:");
         sb.append(this.userInfo + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public AdmissionRequestFluent withDryRun() {
      return this.withDryRun(true);
   }

   public class UserInfoNested extends UserInfoFluent implements Nested {
      UserInfoBuilder builder;

      UserInfoNested(UserInfo item) {
         this.builder = new UserInfoBuilder(this, item);
      }

      public Object and() {
         return AdmissionRequestFluent.this.withUserInfo(this.builder.build());
      }

      public Object endUserInfo() {
         return this.and();
      }
   }
}
