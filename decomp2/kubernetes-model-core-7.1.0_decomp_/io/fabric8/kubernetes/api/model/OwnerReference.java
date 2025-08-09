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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "blockOwnerDeletion", "controller", "name", "uid"})
@Version("v1")
@Group("")
public class OwnerReference implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("blockOwnerDeletion")
   private Boolean blockOwnerDeletion;
   @JsonProperty("controller")
   private Boolean controller;
   @JsonProperty("kind")
   private String kind = "OwnerReference";
   @JsonProperty("name")
   private String name;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public OwnerReference() {
   }

   public OwnerReference(String apiVersion, Boolean blockOwnerDeletion, Boolean controller, String kind, String name, String uid) {
      this.apiVersion = apiVersion;
      this.blockOwnerDeletion = blockOwnerDeletion;
      this.controller = controller;
      this.kind = kind;
      this.name = name;
      this.uid = uid;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("blockOwnerDeletion")
   public Boolean getBlockOwnerDeletion() {
      return this.blockOwnerDeletion;
   }

   @JsonProperty("blockOwnerDeletion")
   public void setBlockOwnerDeletion(Boolean blockOwnerDeletion) {
      this.blockOwnerDeletion = blockOwnerDeletion;
   }

   @JsonProperty("controller")
   public Boolean getController() {
      return this.controller;
   }

   @JsonProperty("controller")
   public void setController(Boolean controller) {
      this.controller = controller;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public OwnerReferenceBuilder edit() {
      return new OwnerReferenceBuilder(this);
   }

   @JsonIgnore
   public OwnerReferenceBuilder toBuilder() {
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
      return "OwnerReference(apiVersion=" + var10000 + ", blockOwnerDeletion=" + this.getBlockOwnerDeletion() + ", controller=" + this.getController() + ", kind=" + this.getKind() + ", name=" + this.getName() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OwnerReference)) {
         return false;
      } else {
         OwnerReference other = (OwnerReference)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$blockOwnerDeletion = this.getBlockOwnerDeletion();
            Object other$blockOwnerDeletion = other.getBlockOwnerDeletion();
            if (this$blockOwnerDeletion == null) {
               if (other$blockOwnerDeletion != null) {
                  return false;
               }
            } else if (!this$blockOwnerDeletion.equals(other$blockOwnerDeletion)) {
               return false;
            }

            Object this$controller = this.getController();
            Object other$controller = other.getController();
            if (this$controller == null) {
               if (other$controller != null) {
                  return false;
               }
            } else if (!this$controller.equals(other$controller)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof OwnerReference;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $blockOwnerDeletion = this.getBlockOwnerDeletion();
      result = result * 59 + ($blockOwnerDeletion == null ? 43 : $blockOwnerDeletion.hashCode());
      Object $controller = this.getController();
      result = result * 59 + ($controller == null ? 43 : $controller.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
