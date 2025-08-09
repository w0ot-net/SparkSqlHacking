package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SelfSubjectReviewStatusFluent extends BaseFluent {
   private UserInfoBuilder userInfo;
   private Map additionalProperties;

   public SelfSubjectReviewStatusFluent() {
   }

   public SelfSubjectReviewStatusFluent(SelfSubjectReviewStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SelfSubjectReviewStatus instance) {
      instance = instance != null ? instance : new SelfSubjectReviewStatus();
      if (instance != null) {
         this.withUserInfo(instance.getUserInfo());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public UserInfo buildUserInfo() {
      return this.userInfo != null ? this.userInfo.build() : null;
   }

   public SelfSubjectReviewStatusFluent withUserInfo(UserInfo userInfo) {
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

   public SelfSubjectReviewStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SelfSubjectReviewStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SelfSubjectReviewStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SelfSubjectReviewStatusFluent removeFromAdditionalProperties(Map map) {
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

   public SelfSubjectReviewStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            SelfSubjectReviewStatusFluent that = (SelfSubjectReviewStatusFluent)o;
            if (!Objects.equals(this.userInfo, that.userInfo)) {
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
      return Objects.hash(new Object[]{this.userInfo, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
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

   public class UserInfoNested extends UserInfoFluent implements Nested {
      UserInfoBuilder builder;

      UserInfoNested(UserInfo item) {
         this.builder = new UserInfoBuilder(this, item);
      }

      public Object and() {
         return SelfSubjectReviewStatusFluent.this.withUserInfo(this.builder.build());
      }

      public Object endUserInfo() {
         return this.and();
      }
   }
}
