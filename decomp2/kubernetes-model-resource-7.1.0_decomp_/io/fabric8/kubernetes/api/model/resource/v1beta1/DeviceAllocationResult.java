package io.fabric8.kubernetes.api.model.resource.v1beta1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"config", "results"})
public class DeviceAllocationResult implements Editable, KubernetesResource {
   @JsonProperty("config")
   @JsonInclude(Include.NON_EMPTY)
   private List config = new ArrayList();
   @JsonProperty("results")
   @JsonInclude(Include.NON_EMPTY)
   private List results = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceAllocationResult() {
   }

   public DeviceAllocationResult(List config, List results) {
      this.config = config;
      this.results = results;
   }

   @JsonProperty("config")
   @JsonInclude(Include.NON_EMPTY)
   public List getConfig() {
      return this.config;
   }

   @JsonProperty("config")
   public void setConfig(List config) {
      this.config = config;
   }

   @JsonProperty("results")
   @JsonInclude(Include.NON_EMPTY)
   public List getResults() {
      return this.results;
   }

   @JsonProperty("results")
   public void setResults(List results) {
      this.results = results;
   }

   @JsonIgnore
   public DeviceAllocationResultBuilder edit() {
      return new DeviceAllocationResultBuilder(this);
   }

   @JsonIgnore
   public DeviceAllocationResultBuilder toBuilder() {
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
      List var10000 = this.getConfig();
      return "DeviceAllocationResult(config=" + var10000 + ", results=" + this.getResults() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceAllocationResult)) {
         return false;
      } else {
         DeviceAllocationResult other = (DeviceAllocationResult)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$config = this.getConfig();
            Object other$config = other.getConfig();
            if (this$config == null) {
               if (other$config != null) {
                  return false;
               }
            } else if (!this$config.equals(other$config)) {
               return false;
            }

            Object this$results = this.getResults();
            Object other$results = other.getResults();
            if (this$results == null) {
               if (other$results != null) {
                  return false;
               }
            } else if (!this$results.equals(other$results)) {
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
      return other instanceof DeviceAllocationResult;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $config = this.getConfig();
      result = result * 59 + ($config == null ? 43 : $config.hashCode());
      Object $results = this.getResults();
      result = result * 59 + ($results == null ? 43 : $results.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
