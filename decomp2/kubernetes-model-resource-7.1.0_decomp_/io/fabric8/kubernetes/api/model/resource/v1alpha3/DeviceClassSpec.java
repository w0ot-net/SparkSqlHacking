package io.fabric8.kubernetes.api.model.resource.v1alpha3;

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
@JsonPropertyOrder({"config", "selectors"})
public class DeviceClassSpec implements Editable, KubernetesResource {
   @JsonProperty("config")
   @JsonInclude(Include.NON_EMPTY)
   private List config = new ArrayList();
   @JsonProperty("selectors")
   @JsonInclude(Include.NON_EMPTY)
   private List selectors = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeviceClassSpec() {
   }

   public DeviceClassSpec(List config, List selectors) {
      this.config = config;
      this.selectors = selectors;
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

   @JsonProperty("selectors")
   @JsonInclude(Include.NON_EMPTY)
   public List getSelectors() {
      return this.selectors;
   }

   @JsonProperty("selectors")
   public void setSelectors(List selectors) {
      this.selectors = selectors;
   }

   @JsonIgnore
   public DeviceClassSpecBuilder edit() {
      return new DeviceClassSpecBuilder(this);
   }

   @JsonIgnore
   public DeviceClassSpecBuilder toBuilder() {
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
      return "DeviceClassSpec(config=" + var10000 + ", selectors=" + this.getSelectors() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeviceClassSpec)) {
         return false;
      } else {
         DeviceClassSpec other = (DeviceClassSpec)o;
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

            Object this$selectors = this.getSelectors();
            Object other$selectors = other.getSelectors();
            if (this$selectors == null) {
               if (other$selectors != null) {
                  return false;
               }
            } else if (!this$selectors.equals(other$selectors)) {
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
      return other instanceof DeviceClassSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $config = this.getConfig();
      result = result * 59 + ($config == null ? 43 : $config.hashCode());
      Object $selectors = this.getSelectors();
      result = result * 59 + ($selectors == null ? 43 : $selectors.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
