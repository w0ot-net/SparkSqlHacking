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
@JsonPropertyOrder({"kind", "causes", "group", "name", "retryAfterSeconds", "uid"})
public class StatusDetails implements Editable, KubernetesResource {
   @JsonProperty("causes")
   @JsonInclude(Include.NON_EMPTY)
   private List causes = new ArrayList();
   @JsonProperty("group")
   private String group;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("retryAfterSeconds")
   private Integer retryAfterSeconds;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatusDetails() {
   }

   public StatusDetails(List causes, String group, String kind, String name, Integer retryAfterSeconds, String uid) {
      this.causes = causes;
      this.group = group;
      this.kind = kind;
      this.name = name;
      this.retryAfterSeconds = retryAfterSeconds;
      this.uid = uid;
   }

   @JsonProperty("causes")
   @JsonInclude(Include.NON_EMPTY)
   public List getCauses() {
      return this.causes;
   }

   @JsonProperty("causes")
   public void setCauses(List causes) {
      this.causes = causes;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
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

   @JsonProperty("retryAfterSeconds")
   public Integer getRetryAfterSeconds() {
      return this.retryAfterSeconds;
   }

   @JsonProperty("retryAfterSeconds")
   public void setRetryAfterSeconds(Integer retryAfterSeconds) {
      this.retryAfterSeconds = retryAfterSeconds;
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
   public StatusDetailsBuilder edit() {
      return new StatusDetailsBuilder(this);
   }

   @JsonIgnore
   public StatusDetailsBuilder toBuilder() {
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
      List var10000 = this.getCauses();
      return "StatusDetails(causes=" + var10000 + ", group=" + this.getGroup() + ", kind=" + this.getKind() + ", name=" + this.getName() + ", retryAfterSeconds=" + this.getRetryAfterSeconds() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatusDetails)) {
         return false;
      } else {
         StatusDetails other = (StatusDetails)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$retryAfterSeconds = this.getRetryAfterSeconds();
            Object other$retryAfterSeconds = other.getRetryAfterSeconds();
            if (this$retryAfterSeconds == null) {
               if (other$retryAfterSeconds != null) {
                  return false;
               }
            } else if (!this$retryAfterSeconds.equals(other$retryAfterSeconds)) {
               return false;
            }

            Object this$causes = this.getCauses();
            Object other$causes = other.getCauses();
            if (this$causes == null) {
               if (other$causes != null) {
                  return false;
               }
            } else if (!this$causes.equals(other$causes)) {
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
      return other instanceof StatusDetails;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $retryAfterSeconds = this.getRetryAfterSeconds();
      result = result * 59 + ($retryAfterSeconds == null ? 43 : $retryAfterSeconds.hashCode());
      Object $causes = this.getCauses();
      result = result * 59 + ($causes == null ? 43 : $causes.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
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
