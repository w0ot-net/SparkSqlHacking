package io.fabric8.kubernetes.api.model.extensions;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"revision"})
public class RollbackConfig implements Editable, KubernetesResource {
   @JsonProperty("revision")
   private Long revision;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RollbackConfig() {
   }

   public RollbackConfig(Long revision) {
      this.revision = revision;
   }

   @JsonProperty("revision")
   public Long getRevision() {
      return this.revision;
   }

   @JsonProperty("revision")
   public void setRevision(Long revision) {
      this.revision = revision;
   }

   @JsonIgnore
   public RollbackConfigBuilder edit() {
      return new RollbackConfigBuilder(this);
   }

   @JsonIgnore
   public RollbackConfigBuilder toBuilder() {
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
      Long var10000 = this.getRevision();
      return "RollbackConfig(revision=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RollbackConfig)) {
         return false;
      } else {
         RollbackConfig other = (RollbackConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$revision = this.getRevision();
            Object other$revision = other.getRevision();
            if (this$revision == null) {
               if (other$revision != null) {
                  return false;
               }
            } else if (!this$revision.equals(other$revision)) {
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
      return other instanceof RollbackConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $revision = this.getRevision();
      result = result * 59 + ($revision == null ? 43 : $revision.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
