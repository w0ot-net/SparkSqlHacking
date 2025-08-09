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
@JsonPropertyOrder({"timeoutSeconds"})
public class ClientIPConfig implements Editable, KubernetesResource {
   @JsonProperty("timeoutSeconds")
   private Integer timeoutSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ClientIPConfig() {
   }

   public ClientIPConfig(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonProperty("timeoutSeconds")
   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   @JsonProperty("timeoutSeconds")
   public void setTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonIgnore
   public ClientIPConfigBuilder edit() {
      return new ClientIPConfigBuilder(this);
   }

   @JsonIgnore
   public ClientIPConfigBuilder toBuilder() {
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
      Integer var10000 = this.getTimeoutSeconds();
      return "ClientIPConfig(timeoutSeconds=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientIPConfig)) {
         return false;
      } else {
         ClientIPConfig other = (ClientIPConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$timeoutSeconds = this.getTimeoutSeconds();
            Object other$timeoutSeconds = other.getTimeoutSeconds();
            if (this$timeoutSeconds == null) {
               if (other$timeoutSeconds != null) {
                  return false;
               }
            } else if (!this$timeoutSeconds.equals(other$timeoutSeconds)) {
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
      return other instanceof ClientIPConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $timeoutSeconds = this.getTimeoutSeconds();
      result = result * 59 + ($timeoutSeconds == null ? 43 : $timeoutSeconds.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
