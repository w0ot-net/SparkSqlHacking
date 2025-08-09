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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "clusters", "contexts", "current-context", "extensions", "preferences", "users"})
public class Config implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("clusters")
   @JsonInclude(Include.NON_EMPTY)
   private List clusters = new ArrayList();
   @JsonProperty("contexts")
   @JsonInclude(Include.NON_EMPTY)
   private List contexts = new ArrayList();
   @JsonProperty("current-context")
   private String currentContext;
   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   private List extensions = new ArrayList();
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("preferences")
   private Preferences preferences;
   @JsonProperty("users")
   @JsonInclude(Include.NON_EMPTY)
   private List users = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Config() {
   }

   public Config(String apiVersion, List clusters, List contexts, String currentContext, List extensions, String kind, Preferences preferences, List users) {
      this.apiVersion = apiVersion;
      this.clusters = clusters;
      this.contexts = contexts;
      this.currentContext = currentContext;
      this.extensions = extensions;
      this.kind = kind;
      this.preferences = preferences;
      this.users = users;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("clusters")
   @JsonInclude(Include.NON_EMPTY)
   public List getClusters() {
      return this.clusters;
   }

   @JsonProperty("clusters")
   public void setClusters(List clusters) {
      this.clusters = clusters;
   }

   @JsonProperty("contexts")
   @JsonInclude(Include.NON_EMPTY)
   public List getContexts() {
      return this.contexts;
   }

   @JsonProperty("contexts")
   public void setContexts(List contexts) {
      this.contexts = contexts;
   }

   @JsonProperty("current-context")
   public String getCurrentContext() {
      return this.currentContext;
   }

   @JsonProperty("current-context")
   public void setCurrentContext(String currentContext) {
      this.currentContext = currentContext;
   }

   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   public List getExtensions() {
      return this.extensions;
   }

   @JsonProperty("extensions")
   public void setExtensions(List extensions) {
      this.extensions = extensions;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("preferences")
   public Preferences getPreferences() {
      return this.preferences;
   }

   @JsonProperty("preferences")
   public void setPreferences(Preferences preferences) {
      this.preferences = preferences;
   }

   @JsonProperty("users")
   @JsonInclude(Include.NON_EMPTY)
   public List getUsers() {
      return this.users;
   }

   @JsonProperty("users")
   public void setUsers(List users) {
      this.users = users;
   }

   @JsonIgnore
   public ConfigBuilder edit() {
      return new ConfigBuilder(this);
   }

   @JsonIgnore
   public ConfigBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "Config(apiVersion=" + var10000 + ", clusters=" + this.getClusters() + ", contexts=" + this.getContexts() + ", currentContext=" + this.getCurrentContext() + ", extensions=" + this.getExtensions() + ", kind=" + this.getKind() + ", preferences=" + this.getPreferences() + ", users=" + this.getUsers() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Config)) {
         return false;
      } else {
         Config other = (Config)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$clusters = this.getClusters();
            Object other$clusters = other.getClusters();
            if (this$clusters == null) {
               if (other$clusters != null) {
                  return false;
               }
            } else if (!this$clusters.equals(other$clusters)) {
               return false;
            }

            Object this$contexts = this.getContexts();
            Object other$contexts = other.getContexts();
            if (this$contexts == null) {
               if (other$contexts != null) {
                  return false;
               }
            } else if (!this$contexts.equals(other$contexts)) {
               return false;
            }

            Object this$currentContext = this.getCurrentContext();
            Object other$currentContext = other.getCurrentContext();
            if (this$currentContext == null) {
               if (other$currentContext != null) {
                  return false;
               }
            } else if (!this$currentContext.equals(other$currentContext)) {
               return false;
            }

            Object this$extensions = this.getExtensions();
            Object other$extensions = other.getExtensions();
            if (this$extensions == null) {
               if (other$extensions != null) {
                  return false;
               }
            } else if (!this$extensions.equals(other$extensions)) {
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

            Object this$preferences = this.getPreferences();
            Object other$preferences = other.getPreferences();
            if (this$preferences == null) {
               if (other$preferences != null) {
                  return false;
               }
            } else if (!this$preferences.equals(other$preferences)) {
               return false;
            }

            Object this$users = this.getUsers();
            Object other$users = other.getUsers();
            if (this$users == null) {
               if (other$users != null) {
                  return false;
               }
            } else if (!this$users.equals(other$users)) {
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
      return other instanceof Config;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $clusters = this.getClusters();
      result = result * 59 + ($clusters == null ? 43 : $clusters.hashCode());
      Object $contexts = this.getContexts();
      result = result * 59 + ($contexts == null ? 43 : $contexts.hashCode());
      Object $currentContext = this.getCurrentContext();
      result = result * 59 + ($currentContext == null ? 43 : $currentContext.hashCode());
      Object $extensions = this.getExtensions();
      result = result * 59 + ($extensions == null ? 43 : $extensions.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $preferences = this.getPreferences();
      result = result * 59 + ($preferences == null ? 43 : $preferences.hashCode());
      Object $users = this.getUsers();
      result = result * 59 + ($users == null ? 43 : $users.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
