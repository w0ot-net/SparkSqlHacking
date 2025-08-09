package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class TokenReviewStatusFluent extends BaseFluent {
   private List audiences = new ArrayList();
   private Boolean authenticated;
   private String error;
   private UserInfoBuilder user;
   private Map additionalProperties;

   public TokenReviewStatusFluent() {
   }

   public TokenReviewStatusFluent(TokenReviewStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TokenReviewStatus instance) {
      instance = instance != null ? instance : new TokenReviewStatus();
      if (instance != null) {
         this.withAudiences(instance.getAudiences());
         this.withAuthenticated(instance.getAuthenticated());
         this.withError(instance.getError());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TokenReviewStatusFluent addToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.add(index, item);
      return this;
   }

   public TokenReviewStatusFluent setToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.set(index, item);
      return this;
   }

   public TokenReviewStatusFluent addToAudiences(String... items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenReviewStatusFluent addAllToAudiences(Collection items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenReviewStatusFluent removeFromAudiences(String... items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public TokenReviewStatusFluent removeAllFromAudiences(Collection items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public List getAudiences() {
      return this.audiences;
   }

   public String getAudience(int index) {
      return (String)this.audiences.get(index);
   }

   public String getFirstAudience() {
      return (String)this.audiences.get(0);
   }

   public String getLastAudience() {
      return (String)this.audiences.get(this.audiences.size() - 1);
   }

   public String getMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TokenReviewStatusFluent withAudiences(List audiences) {
      if (audiences != null) {
         this.audiences = new ArrayList();

         for(String item : audiences) {
            this.addToAudiences(item);
         }
      } else {
         this.audiences = null;
      }

      return this;
   }

   public TokenReviewStatusFluent withAudiences(String... audiences) {
      if (this.audiences != null) {
         this.audiences.clear();
         this._visitables.remove("audiences");
      }

      if (audiences != null) {
         for(String item : audiences) {
            this.addToAudiences(item);
         }
      }

      return this;
   }

   public boolean hasAudiences() {
      return this.audiences != null && !this.audiences.isEmpty();
   }

   public Boolean getAuthenticated() {
      return this.authenticated;
   }

   public TokenReviewStatusFluent withAuthenticated(Boolean authenticated) {
      this.authenticated = authenticated;
      return this;
   }

   public boolean hasAuthenticated() {
      return this.authenticated != null;
   }

   public String getError() {
      return this.error;
   }

   public TokenReviewStatusFluent withError(String error) {
      this.error = error;
      return this;
   }

   public boolean hasError() {
      return this.error != null;
   }

   public UserInfo buildUser() {
      return this.user != null ? this.user.build() : null;
   }

   public TokenReviewStatusFluent withUser(UserInfo user) {
      this._visitables.remove("user");
      if (user != null) {
         this.user = new UserInfoBuilder(user);
         this._visitables.get("user").add(this.user);
      } else {
         this.user = null;
         this._visitables.get("user").remove(this.user);
      }

      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public UserNested withNewUser() {
      return new UserNested((UserInfo)null);
   }

   public UserNested withNewUserLike(UserInfo item) {
      return new UserNested(item);
   }

   public UserNested editUser() {
      return this.withNewUserLike((UserInfo)Optional.ofNullable(this.buildUser()).orElse((Object)null));
   }

   public UserNested editOrNewUser() {
      return this.withNewUserLike((UserInfo)Optional.ofNullable(this.buildUser()).orElse((new UserInfoBuilder()).build()));
   }

   public UserNested editOrNewUserLike(UserInfo item) {
      return this.withNewUserLike((UserInfo)Optional.ofNullable(this.buildUser()).orElse(item));
   }

   public TokenReviewStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TokenReviewStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TokenReviewStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TokenReviewStatusFluent removeFromAdditionalProperties(Map map) {
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

   public TokenReviewStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            TokenReviewStatusFluent that = (TokenReviewStatusFluent)o;
            if (!Objects.equals(this.audiences, that.audiences)) {
               return false;
            } else if (!Objects.equals(this.authenticated, that.authenticated)) {
               return false;
            } else if (!Objects.equals(this.error, that.error)) {
               return false;
            } else if (!Objects.equals(this.user, that.user)) {
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
      return Objects.hash(new Object[]{this.audiences, this.authenticated, this.error, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.audiences != null && !this.audiences.isEmpty()) {
         sb.append("audiences:");
         sb.append(this.audiences + ",");
      }

      if (this.authenticated != null) {
         sb.append("authenticated:");
         sb.append(this.authenticated + ",");
      }

      if (this.error != null) {
         sb.append("error:");
         sb.append(this.error + ",");
      }

      if (this.user != null) {
         sb.append("user:");
         sb.append(this.user + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public TokenReviewStatusFluent withAuthenticated() {
      return this.withAuthenticated(true);
   }

   public class UserNested extends UserInfoFluent implements Nested {
      UserInfoBuilder builder;

      UserNested(UserInfo item) {
         this.builder = new UserInfoBuilder(this, item);
      }

      public Object and() {
         return TokenReviewStatusFluent.this.withUser(this.builder.build());
      }

      public Object endUser() {
         return this.and();
      }
   }
}
