package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

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
import io.fabric8.kubernetes.api.model.ObjectReference;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"configRef", "dataSecretName"})
public class Bootstrap implements Editable, KubernetesResource {
   @JsonProperty("configRef")
   private ObjectReference configRef;
   @JsonProperty("dataSecretName")
   private String dataSecretName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Bootstrap() {
   }

   public Bootstrap(ObjectReference configRef, String dataSecretName) {
      this.configRef = configRef;
      this.dataSecretName = dataSecretName;
   }

   @JsonProperty("configRef")
   public ObjectReference getConfigRef() {
      return this.configRef;
   }

   @JsonProperty("configRef")
   public void setConfigRef(ObjectReference configRef) {
      this.configRef = configRef;
   }

   @JsonProperty("dataSecretName")
   public String getDataSecretName() {
      return this.dataSecretName;
   }

   @JsonProperty("dataSecretName")
   public void setDataSecretName(String dataSecretName) {
      this.dataSecretName = dataSecretName;
   }

   @JsonIgnore
   public BootstrapBuilder edit() {
      return new BootstrapBuilder(this);
   }

   @JsonIgnore
   public BootstrapBuilder toBuilder() {
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
      ObjectReference var10000 = this.getConfigRef();
      return "Bootstrap(configRef=" + var10000 + ", dataSecretName=" + this.getDataSecretName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Bootstrap)) {
         return false;
      } else {
         Bootstrap other = (Bootstrap)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$configRef = this.getConfigRef();
            Object other$configRef = other.getConfigRef();
            if (this$configRef == null) {
               if (other$configRef != null) {
                  return false;
               }
            } else if (!this$configRef.equals(other$configRef)) {
               return false;
            }

            Object this$dataSecretName = this.getDataSecretName();
            Object other$dataSecretName = other.getDataSecretName();
            if (this$dataSecretName == null) {
               if (other$dataSecretName != null) {
                  return false;
               }
            } else if (!this$dataSecretName.equals(other$dataSecretName)) {
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
      return other instanceof Bootstrap;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $configRef = this.getConfigRef();
      result = result * 59 + ($configRef == null ? 43 : $configRef.hashCode());
      Object $dataSecretName = this.getDataSecretName();
      result = result * 59 + ($dataSecretName == null ? 43 : $dataSecretName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
