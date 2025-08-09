package io.fabric8.kubernetes.api.model.networking.v1beta1;

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
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.TypedLocalObjectReference;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"resource", "serviceName", "servicePort"})
public class IngressBackend implements Editable, KubernetesResource {
   @JsonProperty("resource")
   private TypedLocalObjectReference resource;
   @JsonProperty("serviceName")
   private String serviceName;
   @JsonProperty("servicePort")
   private IntOrString servicePort;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressBackend() {
   }

   public IngressBackend(TypedLocalObjectReference resource, String serviceName, IntOrString servicePort) {
      this.resource = resource;
      this.serviceName = serviceName;
      this.servicePort = servicePort;
   }

   @JsonProperty("resource")
   public TypedLocalObjectReference getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(TypedLocalObjectReference resource) {
      this.resource = resource;
   }

   @JsonProperty("serviceName")
   public String getServiceName() {
      return this.serviceName;
   }

   @JsonProperty("serviceName")
   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   @JsonProperty("servicePort")
   public IntOrString getServicePort() {
      return this.servicePort;
   }

   @JsonProperty("servicePort")
   public void setServicePort(IntOrString servicePort) {
      this.servicePort = servicePort;
   }

   @JsonIgnore
   public IngressBackendBuilder edit() {
      return new IngressBackendBuilder(this);
   }

   @JsonIgnore
   public IngressBackendBuilder toBuilder() {
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
      TypedLocalObjectReference var10000 = this.getResource();
      return "IngressBackend(resource=" + var10000 + ", serviceName=" + this.getServiceName() + ", servicePort=" + this.getServicePort() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressBackend)) {
         return false;
      } else {
         IngressBackend other = (IngressBackend)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
               return false;
            }

            Object this$serviceName = this.getServiceName();
            Object other$serviceName = other.getServiceName();
            if (this$serviceName == null) {
               if (other$serviceName != null) {
                  return false;
               }
            } else if (!this$serviceName.equals(other$serviceName)) {
               return false;
            }

            Object this$servicePort = this.getServicePort();
            Object other$servicePort = other.getServicePort();
            if (this$servicePort == null) {
               if (other$servicePort != null) {
                  return false;
               }
            } else if (!this$servicePort.equals(other$servicePort)) {
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
      return other instanceof IngressBackend;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $serviceName = this.getServiceName();
      result = result * 59 + ($serviceName == null ? 43 : $serviceName.hashCode());
      Object $servicePort = this.getServicePort();
      result = result * 59 + ($servicePort == null ? 43 : $servicePort.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
