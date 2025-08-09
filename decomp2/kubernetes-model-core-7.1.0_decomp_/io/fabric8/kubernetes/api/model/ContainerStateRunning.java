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
@JsonPropertyOrder({"startedAt"})
public class ContainerStateRunning implements Editable, KubernetesResource {
   @JsonProperty("startedAt")
   private String startedAt;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerStateRunning() {
   }

   public ContainerStateRunning(String startedAt) {
      this.startedAt = startedAt;
   }

   @JsonProperty("startedAt")
   public String getStartedAt() {
      return this.startedAt;
   }

   @JsonProperty("startedAt")
   public void setStartedAt(String startedAt) {
      this.startedAt = startedAt;
   }

   @JsonIgnore
   public ContainerStateRunningBuilder edit() {
      return new ContainerStateRunningBuilder(this);
   }

   @JsonIgnore
   public ContainerStateRunningBuilder toBuilder() {
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
      String var10000 = this.getStartedAt();
      return "ContainerStateRunning(startedAt=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerStateRunning)) {
         return false;
      } else {
         ContainerStateRunning other = (ContainerStateRunning)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$startedAt = this.getStartedAt();
            Object other$startedAt = other.getStartedAt();
            if (this$startedAt == null) {
               if (other$startedAt != null) {
                  return false;
               }
            } else if (!this$startedAt.equals(other$startedAt)) {
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
      return other instanceof ContainerStateRunning;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $startedAt = this.getStartedAt();
      result = result * 59 + ($startedAt == null ? 43 : $startedAt.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
