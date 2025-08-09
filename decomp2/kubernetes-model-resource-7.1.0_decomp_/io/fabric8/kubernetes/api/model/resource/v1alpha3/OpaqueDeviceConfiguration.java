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
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"driver", "parameters"})
public class OpaqueDeviceConfiguration implements Editable, KubernetesResource {
   @JsonProperty("driver")
   private String driver;
   @JsonProperty("parameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object parameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public OpaqueDeviceConfiguration() {
   }

   public OpaqueDeviceConfiguration(String driver, Object parameters) {
      this.driver = driver;
      this.parameters = parameters;
   }

   @JsonProperty("driver")
   public String getDriver() {
      return this.driver;
   }

   @JsonProperty("driver")
   public void setDriver(String driver) {
      this.driver = driver;
   }

   @JsonProperty("parameters")
   public Object getParameters() {
      return this.parameters;
   }

   @JsonProperty("parameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setParameters(Object parameters) {
      this.parameters = parameters;
   }

   @JsonIgnore
   public OpaqueDeviceConfigurationBuilder edit() {
      return new OpaqueDeviceConfigurationBuilder(this);
   }

   @JsonIgnore
   public OpaqueDeviceConfigurationBuilder toBuilder() {
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
      String var10000 = this.getDriver();
      return "OpaqueDeviceConfiguration(driver=" + var10000 + ", parameters=" + this.getParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OpaqueDeviceConfiguration)) {
         return false;
      } else {
         OpaqueDeviceConfiguration other = (OpaqueDeviceConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$driver = this.getDriver();
            Object other$driver = other.getDriver();
            if (this$driver == null) {
               if (other$driver != null) {
                  return false;
               }
            } else if (!this$driver.equals(other$driver)) {
               return false;
            }

            Object this$parameters = this.getParameters();
            Object other$parameters = other.getParameters();
            if (this$parameters == null) {
               if (other$parameters != null) {
                  return false;
               }
            } else if (!this$parameters.equals(other$parameters)) {
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
      return other instanceof OpaqueDeviceConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $driver = this.getDriver();
      result = result * 59 + ($driver == null ? 43 : $driver.hashCode());
      Object $parameters = this.getParameters();
      result = result * 59 + ($parameters == null ? 43 : $parameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
