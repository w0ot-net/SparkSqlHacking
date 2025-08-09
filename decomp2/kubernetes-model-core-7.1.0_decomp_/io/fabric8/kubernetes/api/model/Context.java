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
@JsonPropertyOrder({"cluster", "extensions", "namespace", "user"})
public class Context implements Editable, KubernetesResource {
   @JsonProperty("cluster")
   private String cluster;
   @JsonProperty("extensions")
   @JsonInclude(Include.NON_EMPTY)
   private List extensions = new ArrayList();
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("user")
   private String user;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Context() {
   }

   public Context(String cluster, List extensions, String namespace, String user) {
      this.cluster = cluster;
      this.extensions = extensions;
      this.namespace = namespace;
      this.user = user;
   }

   @JsonProperty("cluster")
   public String getCluster() {
      return this.cluster;
   }

   @JsonProperty("cluster")
   public void setCluster(String cluster) {
      this.cluster = cluster;
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

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("user")
   public String getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(String user) {
      this.user = user;
   }

   @JsonIgnore
   public ContextBuilder edit() {
      return new ContextBuilder(this);
   }

   @JsonIgnore
   public ContextBuilder toBuilder() {
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
      String var10000 = this.getCluster();
      return "Context(cluster=" + var10000 + ", extensions=" + this.getExtensions() + ", namespace=" + this.getNamespace() + ", user=" + this.getUser() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Context)) {
         return false;
      } else {
         Context other = (Context)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$cluster = this.getCluster();
            Object other$cluster = other.getCluster();
            if (this$cluster == null) {
               if (other$cluster != null) {
                  return false;
               }
            } else if (!this$cluster.equals(other$cluster)) {
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

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
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
      return other instanceof Context;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $cluster = this.getCluster();
      result = result * 59 + ($cluster == null ? 43 : $cluster.hashCode());
      Object $extensions = this.getExtensions();
      result = result * 59 + ($extensions == null ? 43 : $extensions.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
