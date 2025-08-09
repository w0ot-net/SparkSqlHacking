package io.fabric8.kubernetes.api.model.authentication;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class UserInfoFluent extends BaseFluent {
   private Map extra;
   private List groups = new ArrayList();
   private String uid;
   private String username;
   private Map additionalProperties;

   public UserInfoFluent() {
   }

   public UserInfoFluent(UserInfo instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(UserInfo instance) {
      instance = instance != null ? instance : new UserInfo();
      if (instance != null) {
         this.withExtra(instance.getExtra());
         this.withGroups(instance.getGroups());
         this.withUid(instance.getUid());
         this.withUsername(instance.getUsername());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public UserInfoFluent addToExtra(String key, List value) {
      if (this.extra == null && key != null && value != null) {
         this.extra = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.extra.put(key, value);
      }

      return this;
   }

   public UserInfoFluent addToExtra(Map map) {
      if (this.extra == null && map != null) {
         this.extra = new LinkedHashMap();
      }

      if (map != null) {
         this.extra.putAll(map);
      }

      return this;
   }

   public UserInfoFluent removeFromExtra(String key) {
      if (this.extra == null) {
         return this;
      } else {
         if (key != null && this.extra != null) {
            this.extra.remove(key);
         }

         return this;
      }
   }

   public UserInfoFluent removeFromExtra(Map map) {
      if (this.extra == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.extra != null) {
                  this.extra.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getExtra() {
      return this.extra;
   }

   public UserInfoFluent withExtra(Map extra) {
      if (extra == null) {
         this.extra = null;
      } else {
         this.extra = new LinkedHashMap(extra);
      }

      return this;
   }

   public boolean hasExtra() {
      return this.extra != null;
   }

   public UserInfoFluent addToGroups(int index, String item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      this.groups.add(index, item);
      return this;
   }

   public UserInfoFluent setToGroups(int index, String item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      this.groups.set(index, item);
      return this;
   }

   public UserInfoFluent addToGroups(String... items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(String item : items) {
         this.groups.add(item);
      }

      return this;
   }

   public UserInfoFluent addAllToGroups(Collection items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(String item : items) {
         this.groups.add(item);
      }

      return this;
   }

   public UserInfoFluent removeFromGroups(String... items) {
      if (this.groups == null) {
         return this;
      } else {
         for(String item : items) {
            this.groups.remove(item);
         }

         return this;
      }
   }

   public UserInfoFluent removeAllFromGroups(Collection items) {
      if (this.groups == null) {
         return this;
      } else {
         for(String item : items) {
            this.groups.remove(item);
         }

         return this;
      }
   }

   public List getGroups() {
      return this.groups;
   }

   public String getGroup(int index) {
      return (String)this.groups.get(index);
   }

   public String getFirstGroup() {
      return (String)this.groups.get(0);
   }

   public String getLastGroup() {
      return (String)this.groups.get(this.groups.size() - 1);
   }

   public String getMatchingGroup(Predicate predicate) {
      for(String item : this.groups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingGroup(Predicate predicate) {
      for(String item : this.groups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public UserInfoFluent withGroups(List groups) {
      if (groups != null) {
         this.groups = new ArrayList();

         for(String item : groups) {
            this.addToGroups(item);
         }
      } else {
         this.groups = null;
      }

      return this;
   }

   public UserInfoFluent withGroups(String... groups) {
      if (this.groups != null) {
         this.groups.clear();
         this._visitables.remove("groups");
      }

      if (groups != null) {
         for(String item : groups) {
            this.addToGroups(item);
         }
      }

      return this;
   }

   public boolean hasGroups() {
      return this.groups != null && !this.groups.isEmpty();
   }

   public String getUid() {
      return this.uid;
   }

   public UserInfoFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public String getUsername() {
      return this.username;
   }

   public UserInfoFluent withUsername(String username) {
      this.username = username;
      return this;
   }

   public boolean hasUsername() {
      return this.username != null;
   }

   public UserInfoFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public UserInfoFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public UserInfoFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public UserInfoFluent removeFromAdditionalProperties(Map map) {
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

   public UserInfoFluent withAdditionalProperties(Map additionalProperties) {
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
            UserInfoFluent that = (UserInfoFluent)o;
            if (!Objects.equals(this.extra, that.extra)) {
               return false;
            } else if (!Objects.equals(this.groups, that.groups)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
               return false;
            } else if (!Objects.equals(this.username, that.username)) {
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
      return Objects.hash(new Object[]{this.extra, this.groups, this.uid, this.username, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.extra != null && !this.extra.isEmpty()) {
         sb.append("extra:");
         sb.append(this.extra + ",");
      }

      if (this.groups != null && !this.groups.isEmpty()) {
         sb.append("groups:");
         sb.append(this.groups + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.username != null) {
         sb.append("username:");
         sb.append(this.username + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
