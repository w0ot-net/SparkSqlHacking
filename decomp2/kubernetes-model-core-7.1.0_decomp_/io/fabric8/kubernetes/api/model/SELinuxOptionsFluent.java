package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SELinuxOptionsFluent extends BaseFluent {
   private String level;
   private String role;
   private String type;
   private String user;
   private Map additionalProperties;

   public SELinuxOptionsFluent() {
   }

   public SELinuxOptionsFluent(SELinuxOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SELinuxOptions instance) {
      instance = instance != null ? instance : new SELinuxOptions();
      if (instance != null) {
         this.withLevel(instance.getLevel());
         this.withRole(instance.getRole());
         this.withType(instance.getType());
         this.withUser(instance.getUser());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLevel() {
      return this.level;
   }

   public SELinuxOptionsFluent withLevel(String level) {
      this.level = level;
      return this;
   }

   public boolean hasLevel() {
      return this.level != null;
   }

   public String getRole() {
      return this.role;
   }

   public SELinuxOptionsFluent withRole(String role) {
      this.role = role;
      return this;
   }

   public boolean hasRole() {
      return this.role != null;
   }

   public String getType() {
      return this.type;
   }

   public SELinuxOptionsFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public String getUser() {
      return this.user;
   }

   public SELinuxOptionsFluent withUser(String user) {
      this.user = user;
      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public SELinuxOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SELinuxOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SELinuxOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SELinuxOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public SELinuxOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            SELinuxOptionsFluent that = (SELinuxOptionsFluent)o;
            if (!Objects.equals(this.level, that.level)) {
               return false;
            } else if (!Objects.equals(this.role, that.role)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.level, this.role, this.type, this.user, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.level != null) {
         sb.append("level:");
         sb.append(this.level + ",");
      }

      if (this.role != null) {
         sb.append("role:");
         sb.append(this.role + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
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
}
