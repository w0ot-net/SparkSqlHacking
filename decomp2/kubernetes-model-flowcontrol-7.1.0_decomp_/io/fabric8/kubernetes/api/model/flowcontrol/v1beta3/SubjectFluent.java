package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SubjectFluent extends BaseFluent {
   private GroupSubjectBuilder group;
   private String kind;
   private ServiceAccountSubjectBuilder serviceAccount;
   private UserSubjectBuilder user;
   private Map additionalProperties;

   public SubjectFluent() {
   }

   public SubjectFluent(Subject instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Subject instance) {
      instance = instance != null ? instance : new Subject();
      if (instance != null) {
         this.withGroup(instance.getGroup());
         this.withKind(instance.getKind());
         this.withServiceAccount(instance.getServiceAccount());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GroupSubject buildGroup() {
      return this.group != null ? this.group.build() : null;
   }

   public SubjectFluent withGroup(GroupSubject group) {
      this._visitables.remove("group");
      if (group != null) {
         this.group = new GroupSubjectBuilder(group);
         this._visitables.get("group").add(this.group);
      } else {
         this.group = null;
         this._visitables.get("group").remove(this.group);
      }

      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public SubjectFluent withNewGroup(String name) {
      return this.withGroup(new GroupSubject(name));
   }

   public GroupNested withNewGroup() {
      return new GroupNested((GroupSubject)null);
   }

   public GroupNested withNewGroupLike(GroupSubject item) {
      return new GroupNested(item);
   }

   public GroupNested editGroup() {
      return this.withNewGroupLike((GroupSubject)Optional.ofNullable(this.buildGroup()).orElse((Object)null));
   }

   public GroupNested editOrNewGroup() {
      return this.withNewGroupLike((GroupSubject)Optional.ofNullable(this.buildGroup()).orElse((new GroupSubjectBuilder()).build()));
   }

   public GroupNested editOrNewGroupLike(GroupSubject item) {
      return this.withNewGroupLike((GroupSubject)Optional.ofNullable(this.buildGroup()).orElse(item));
   }

   public String getKind() {
      return this.kind;
   }

   public SubjectFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ServiceAccountSubject buildServiceAccount() {
      return this.serviceAccount != null ? this.serviceAccount.build() : null;
   }

   public SubjectFluent withServiceAccount(ServiceAccountSubject serviceAccount) {
      this._visitables.remove("serviceAccount");
      if (serviceAccount != null) {
         this.serviceAccount = new ServiceAccountSubjectBuilder(serviceAccount);
         this._visitables.get("serviceAccount").add(this.serviceAccount);
      } else {
         this.serviceAccount = null;
         this._visitables.get("serviceAccount").remove(this.serviceAccount);
      }

      return this;
   }

   public boolean hasServiceAccount() {
      return this.serviceAccount != null;
   }

   public SubjectFluent withNewServiceAccount(String name, String namespace) {
      return this.withServiceAccount(new ServiceAccountSubject(name, namespace));
   }

   public ServiceAccountNested withNewServiceAccount() {
      return new ServiceAccountNested((ServiceAccountSubject)null);
   }

   public ServiceAccountNested withNewServiceAccountLike(ServiceAccountSubject item) {
      return new ServiceAccountNested(item);
   }

   public ServiceAccountNested editServiceAccount() {
      return this.withNewServiceAccountLike((ServiceAccountSubject)Optional.ofNullable(this.buildServiceAccount()).orElse((Object)null));
   }

   public ServiceAccountNested editOrNewServiceAccount() {
      return this.withNewServiceAccountLike((ServiceAccountSubject)Optional.ofNullable(this.buildServiceAccount()).orElse((new ServiceAccountSubjectBuilder()).build()));
   }

   public ServiceAccountNested editOrNewServiceAccountLike(ServiceAccountSubject item) {
      return this.withNewServiceAccountLike((ServiceAccountSubject)Optional.ofNullable(this.buildServiceAccount()).orElse(item));
   }

   public UserSubject buildUser() {
      return this.user != null ? this.user.build() : null;
   }

   public SubjectFluent withUser(UserSubject user) {
      this._visitables.remove("user");
      if (user != null) {
         this.user = new UserSubjectBuilder(user);
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

   public SubjectFluent withNewUser(String name) {
      return this.withUser(new UserSubject(name));
   }

   public UserNested withNewUser() {
      return new UserNested((UserSubject)null);
   }

   public UserNested withNewUserLike(UserSubject item) {
      return new UserNested(item);
   }

   public UserNested editUser() {
      return this.withNewUserLike((UserSubject)Optional.ofNullable(this.buildUser()).orElse((Object)null));
   }

   public UserNested editOrNewUser() {
      return this.withNewUserLike((UserSubject)Optional.ofNullable(this.buildUser()).orElse((new UserSubjectBuilder()).build()));
   }

   public UserNested editOrNewUserLike(UserSubject item) {
      return this.withNewUserLike((UserSubject)Optional.ofNullable(this.buildUser()).orElse(item));
   }

   public SubjectFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SubjectFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SubjectFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SubjectFluent removeFromAdditionalProperties(Map map) {
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

   public SubjectFluent withAdditionalProperties(Map additionalProperties) {
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
            SubjectFluent that = (SubjectFluent)o;
            if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.serviceAccount, that.serviceAccount)) {
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
      return Objects.hash(new Object[]{this.group, this.kind, this.serviceAccount, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.serviceAccount != null) {
         sb.append("serviceAccount:");
         sb.append(this.serviceAccount + ",");
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

   public class GroupNested extends GroupSubjectFluent implements Nested {
      GroupSubjectBuilder builder;

      GroupNested(GroupSubject item) {
         this.builder = new GroupSubjectBuilder(this, item);
      }

      public Object and() {
         return SubjectFluent.this.withGroup(this.builder.build());
      }

      public Object endGroup() {
         return this.and();
      }
   }

   public class ServiceAccountNested extends ServiceAccountSubjectFluent implements Nested {
      ServiceAccountSubjectBuilder builder;

      ServiceAccountNested(ServiceAccountSubject item) {
         this.builder = new ServiceAccountSubjectBuilder(this, item);
      }

      public Object and() {
         return SubjectFluent.this.withServiceAccount(this.builder.build());
      }

      public Object endServiceAccount() {
         return this.and();
      }
   }

   public class UserNested extends UserSubjectFluent implements Nested {
      UserSubjectBuilder builder;

      UserNested(UserSubject item) {
         this.builder = new UserSubjectBuilder(this, item);
      }

      public Object and() {
         return SubjectFluent.this.withUser(this.builder.build());
      }

      public Object endUser() {
         return this.and();
      }
   }
}
