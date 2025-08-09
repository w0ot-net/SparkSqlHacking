package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
@JsonPropertyOrder({"instances"})
public class NamedResourcesResources implements Editable, KubernetesResource {
   @JsonProperty("instances")
   @JsonInclude(Include.NON_EMPTY)
   private List instances = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NamedResourcesResources() {
   }

   public NamedResourcesResources(List instances) {
      this.instances = instances;
   }

   @JsonProperty("instances")
   @JsonInclude(Include.NON_EMPTY)
   public List getInstances() {
      return this.instances;
   }

   @JsonProperty("instances")
   public void setInstances(List instances) {
      this.instances = instances;
   }

   @JsonIgnore
   public NamedResourcesResourcesBuilder edit() {
      return new NamedResourcesResourcesBuilder(this);
   }

   @JsonIgnore
   public NamedResourcesResourcesBuilder toBuilder() {
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
      List var10000 = this.getInstances();
      return "NamedResourcesResources(instances=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NamedResourcesResources)) {
         return false;
      } else {
         NamedResourcesResources other = (NamedResourcesResources)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$instances = this.getInstances();
            Object other$instances = other.getInstances();
            if (this$instances == null) {
               if (other$instances != null) {
                  return false;
               }
            } else if (!this$instances.equals(other$instances)) {
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
      return other instanceof NamedResourcesResources;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $instances = this.getInstances();
      result = result * 59 + ($instances == null ? 43 : $instances.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
