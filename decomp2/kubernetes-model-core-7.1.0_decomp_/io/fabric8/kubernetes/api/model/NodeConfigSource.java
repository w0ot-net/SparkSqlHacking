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
@JsonPropertyOrder({"configMap"})
public class NodeConfigSource implements Editable, KubernetesResource {
   @JsonProperty("configMap")
   private ConfigMapNodeConfigSource configMap;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeConfigSource() {
   }

   public NodeConfigSource(ConfigMapNodeConfigSource configMap) {
      this.configMap = configMap;
   }

   @JsonProperty("configMap")
   public ConfigMapNodeConfigSource getConfigMap() {
      return this.configMap;
   }

   @JsonProperty("configMap")
   public void setConfigMap(ConfigMapNodeConfigSource configMap) {
      this.configMap = configMap;
   }

   @JsonIgnore
   public NodeConfigSourceBuilder edit() {
      return new NodeConfigSourceBuilder(this);
   }

   @JsonIgnore
   public NodeConfigSourceBuilder toBuilder() {
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
      ConfigMapNodeConfigSource var10000 = this.getConfigMap();
      return "NodeConfigSource(configMap=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeConfigSource)) {
         return false;
      } else {
         NodeConfigSource other = (NodeConfigSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$configMap = this.getConfigMap();
            Object other$configMap = other.getConfigMap();
            if (this$configMap == null) {
               if (other$configMap != null) {
                  return false;
               }
            } else if (!this$configMap.equals(other$configMap)) {
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
      return other instanceof NodeConfigSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $configMap = this.getConfigMap();
      result = result * 59 + ($configMap == null ? 43 : $configMap.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
