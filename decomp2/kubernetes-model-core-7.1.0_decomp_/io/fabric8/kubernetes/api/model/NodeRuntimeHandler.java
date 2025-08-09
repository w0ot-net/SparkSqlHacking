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
@JsonPropertyOrder({"features", "name"})
public class NodeRuntimeHandler implements Editable, KubernetesResource {
   @JsonProperty("features")
   private NodeRuntimeHandlerFeatures features;
   @JsonProperty("name")
   private String name;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeRuntimeHandler() {
   }

   public NodeRuntimeHandler(NodeRuntimeHandlerFeatures features, String name) {
      this.features = features;
      this.name = name;
   }

   @JsonProperty("features")
   public NodeRuntimeHandlerFeatures getFeatures() {
      return this.features;
   }

   @JsonProperty("features")
   public void setFeatures(NodeRuntimeHandlerFeatures features) {
      this.features = features;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonIgnore
   public NodeRuntimeHandlerBuilder edit() {
      return new NodeRuntimeHandlerBuilder(this);
   }

   @JsonIgnore
   public NodeRuntimeHandlerBuilder toBuilder() {
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
      NodeRuntimeHandlerFeatures var10000 = this.getFeatures();
      return "NodeRuntimeHandler(features=" + var10000 + ", name=" + this.getName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeRuntimeHandler)) {
         return false;
      } else {
         NodeRuntimeHandler other = (NodeRuntimeHandler)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$features = this.getFeatures();
            Object other$features = other.getFeatures();
            if (this$features == null) {
               if (other$features != null) {
                  return false;
               }
            } else if (!this$features.equals(other$features)) {
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
      return other instanceof NodeRuntimeHandler;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $features = this.getFeatures();
      result = result * 59 + ($features == null ? 43 : $features.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
