package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class QuobyteVolumeSourceFluent extends BaseFluent {
   private String group;
   private Boolean readOnly;
   private String registry;
   private String tenant;
   private String user;
   private String volume;
   private Map additionalProperties;

   public QuobyteVolumeSourceFluent() {
   }

   public QuobyteVolumeSourceFluent(QuobyteVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(QuobyteVolumeSource instance) {
      instance = instance != null ? instance : new QuobyteVolumeSource();
      if (instance != null) {
         this.withGroup(instance.getGroup());
         this.withReadOnly(instance.getReadOnly());
         this.withRegistry(instance.getRegistry());
         this.withTenant(instance.getTenant());
         this.withUser(instance.getUser());
         this.withVolume(instance.getVolume());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getGroup() {
      return this.group;
   }

   public QuobyteVolumeSourceFluent withGroup(String group) {
      this.group = group;
      return this;
   }

   public boolean hasGroup() {
      return this.group != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public QuobyteVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getRegistry() {
      return this.registry;
   }

   public QuobyteVolumeSourceFluent withRegistry(String registry) {
      this.registry = registry;
      return this;
   }

   public boolean hasRegistry() {
      return this.registry != null;
   }

   public String getTenant() {
      return this.tenant;
   }

   public QuobyteVolumeSourceFluent withTenant(String tenant) {
      this.tenant = tenant;
      return this;
   }

   public boolean hasTenant() {
      return this.tenant != null;
   }

   public String getUser() {
      return this.user;
   }

   public QuobyteVolumeSourceFluent withUser(String user) {
      this.user = user;
      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public String getVolume() {
      return this.volume;
   }

   public QuobyteVolumeSourceFluent withVolume(String volume) {
      this.volume = volume;
      return this;
   }

   public boolean hasVolume() {
      return this.volume != null;
   }

   public QuobyteVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public QuobyteVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public QuobyteVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public QuobyteVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public QuobyteVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            QuobyteVolumeSourceFluent that = (QuobyteVolumeSourceFluent)o;
            if (!Objects.equals(this.group, that.group)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.registry, that.registry)) {
               return false;
            } else if (!Objects.equals(this.tenant, that.tenant)) {
               return false;
            } else if (!Objects.equals(this.user, that.user)) {
               return false;
            } else if (!Objects.equals(this.volume, that.volume)) {
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
      return Objects.hash(new Object[]{this.group, this.readOnly, this.registry, this.tenant, this.user, this.volume, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.group != null) {
         sb.append("group:");
         sb.append(this.group + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.registry != null) {
         sb.append("registry:");
         sb.append(this.registry + ",");
      }

      if (this.tenant != null) {
         sb.append("tenant:");
         sb.append(this.tenant + ",");
      }

      if (this.user != null) {
         sb.append("user:");
         sb.append(this.user + ",");
      }

      if (this.volume != null) {
         sb.append("volume:");
         sb.append(this.volume + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public QuobyteVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
