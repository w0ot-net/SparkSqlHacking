package io.fabric8.kubernetes.api.model;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"group", "readOnly", "registry", "tenant", "user", "volume"})
public class QuobyteVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("group")
   private String group;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("registry")
   private String registry;
   @JsonProperty("tenant")
   private String tenant;
   @JsonProperty("user")
   private String user;
   @JsonProperty("volume")
   private String volume;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public QuobyteVolumeSource() {
   }

   public QuobyteVolumeSource(String group, Boolean readOnly, String registry, String tenant, String user, String volume) {
      this.group = group;
      this.readOnly = readOnly;
      this.registry = registry;
      this.tenant = tenant;
      this.user = user;
      this.volume = volume;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("registry")
   public String getRegistry() {
      return this.registry;
   }

   @JsonProperty("registry")
   public void setRegistry(String registry) {
      this.registry = registry;
   }

   @JsonProperty("tenant")
   public String getTenant() {
      return this.tenant;
   }

   @JsonProperty("tenant")
   public void setTenant(String tenant) {
      this.tenant = tenant;
   }

   @JsonProperty("user")
   public String getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(String user) {
      this.user = user;
   }

   @JsonProperty("volume")
   public String getVolume() {
      return this.volume;
   }

   @JsonProperty("volume")
   public void setVolume(String volume) {
      this.volume = volume;
   }

   @JsonIgnore
   public QuobyteVolumeSourceBuilder edit() {
      return new QuobyteVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public QuobyteVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getGroup();
      return "QuobyteVolumeSource(group=" + var10000 + ", readOnly=" + this.getReadOnly() + ", registry=" + this.getRegistry() + ", tenant=" + this.getTenant() + ", user=" + this.getUser() + ", volume=" + this.getVolume() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof QuobyteVolumeSource)) {
         return false;
      } else {
         QuobyteVolumeSource other = (QuobyteVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$readOnly = this.getReadOnly();
            Object other$readOnly = other.getReadOnly();
            if (this$readOnly == null) {
               if (other$readOnly != null) {
                  return false;
               }
            } else if (!this$readOnly.equals(other$readOnly)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
               return false;
            }

            Object this$registry = this.getRegistry();
            Object other$registry = other.getRegistry();
            if (this$registry == null) {
               if (other$registry != null) {
                  return false;
               }
            } else if (!this$registry.equals(other$registry)) {
               return false;
            }

            Object this$tenant = this.getTenant();
            Object other$tenant = other.getTenant();
            if (this$tenant == null) {
               if (other$tenant != null) {
                  return false;
               }
            } else if (!this$tenant.equals(other$tenant)) {
               return false;
            }

            Object this$user = this.getUser();
            Object other$user = other.getUser();
            if (this$user == null) {
               if (other$user != null) {
                  return false;
               }
            } else if (!this$user.equals(other$user)) {
               return false;
            }

            Object this$volume = this.getVolume();
            Object other$volume = other.getVolume();
            if (this$volume == null) {
               if (other$volume != null) {
                  return false;
               }
            } else if (!this$volume.equals(other$volume)) {
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
      return other instanceof QuobyteVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $registry = this.getRegistry();
      result = result * 59 + ($registry == null ? 43 : $registry.hashCode());
      Object $tenant = this.getTenant();
      result = result * 59 + ($tenant == null ? 43 : $tenant.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $volume = this.getVolume();
      result = result * 59 + ($volume == null ? 43 : $volume.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
