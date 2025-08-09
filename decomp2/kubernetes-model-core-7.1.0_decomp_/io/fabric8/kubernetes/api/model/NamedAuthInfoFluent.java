package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NamedAuthInfoFluent extends BaseFluent {
   private String name;
   private AuthInfoBuilder user;
   private Map additionalProperties;

   public NamedAuthInfoFluent() {
   }

   public NamedAuthInfoFluent(NamedAuthInfo instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedAuthInfo instance) {
      instance = instance != null ? instance : new NamedAuthInfo();
      if (instance != null) {
         this.withName(instance.getName());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getName() {
      return this.name;
   }

   public NamedAuthInfoFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public AuthInfo buildUser() {
      return this.user != null ? this.user.build() : null;
   }

   public NamedAuthInfoFluent withUser(AuthInfo user) {
      this._visitables.remove("user");
      if (user != null) {
         this.user = new AuthInfoBuilder(user);
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
      return new UserNested((AuthInfo)null);
   }

   public UserNested withNewUserLike(AuthInfo item) {
      return new UserNested(item);
   }

   public UserNested editUser() {
      return this.withNewUserLike((AuthInfo)Optional.ofNullable(this.buildUser()).orElse((Object)null));
   }

   public UserNested editOrNewUser() {
      return this.withNewUserLike((AuthInfo)Optional.ofNullable(this.buildUser()).orElse((new AuthInfoBuilder()).build()));
   }

   public UserNested editOrNewUserLike(AuthInfo item) {
      return this.withNewUserLike((AuthInfo)Optional.ofNullable(this.buildUser()).orElse(item));
   }

   public NamedAuthInfoFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedAuthInfoFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedAuthInfoFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedAuthInfoFluent removeFromAdditionalProperties(Map map) {
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

   public NamedAuthInfoFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedAuthInfoFluent that = (NamedAuthInfoFluent)o;
            if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.name, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
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

   public class UserNested extends AuthInfoFluent implements Nested {
      AuthInfoBuilder builder;

      UserNested(AuthInfo item) {
         this.builder = new AuthInfoBuilder(this, item);
      }

      public Object and() {
         return NamedAuthInfoFluent.this.withUser(this.builder.build());
      }

      public Object endUser() {
         return this.and();
      }
   }
}
