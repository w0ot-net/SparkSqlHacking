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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"preferredDuringSchedulingIgnoredDuringExecution", "requiredDuringSchedulingIgnoredDuringExecution"})
public class NodeAffinity implements Editable, KubernetesResource {
   @JsonProperty("preferredDuringSchedulingIgnoredDuringExecution")
   @JsonInclude(Include.NON_EMPTY)
   private List preferredDuringSchedulingIgnoredDuringExecution = new ArrayList();
   @JsonProperty("requiredDuringSchedulingIgnoredDuringExecution")
   private NodeSelector requiredDuringSchedulingIgnoredDuringExecution;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeAffinity() {
   }

   public NodeAffinity(List preferredDuringSchedulingIgnoredDuringExecution, NodeSelector requiredDuringSchedulingIgnoredDuringExecution) {
      this.preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution;
      this.requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution;
   }

   @JsonProperty("preferredDuringSchedulingIgnoredDuringExecution")
   @JsonInclude(Include.NON_EMPTY)
   public List getPreferredDuringSchedulingIgnoredDuringExecution() {
      return this.preferredDuringSchedulingIgnoredDuringExecution;
   }

   @JsonProperty("preferredDuringSchedulingIgnoredDuringExecution")
   public void setPreferredDuringSchedulingIgnoredDuringExecution(List preferredDuringSchedulingIgnoredDuringExecution) {
      this.preferredDuringSchedulingIgnoredDuringExecution = preferredDuringSchedulingIgnoredDuringExecution;
   }

   @JsonProperty("requiredDuringSchedulingIgnoredDuringExecution")
   public NodeSelector getRequiredDuringSchedulingIgnoredDuringExecution() {
      return this.requiredDuringSchedulingIgnoredDuringExecution;
   }

   @JsonProperty("requiredDuringSchedulingIgnoredDuringExecution")
   public void setRequiredDuringSchedulingIgnoredDuringExecution(NodeSelector requiredDuringSchedulingIgnoredDuringExecution) {
      this.requiredDuringSchedulingIgnoredDuringExecution = requiredDuringSchedulingIgnoredDuringExecution;
   }

   @JsonIgnore
   public NodeAffinityBuilder edit() {
      return new NodeAffinityBuilder(this);
   }

   @JsonIgnore
   public NodeAffinityBuilder toBuilder() {
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
      List var10000 = this.getPreferredDuringSchedulingIgnoredDuringExecution();
      return "NodeAffinity(preferredDuringSchedulingIgnoredDuringExecution=" + var10000 + ", requiredDuringSchedulingIgnoredDuringExecution=" + this.getRequiredDuringSchedulingIgnoredDuringExecution() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeAffinity)) {
         return false;
      } else {
         NodeAffinity other = (NodeAffinity)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$preferredDuringSchedulingIgnoredDuringExecution = this.getPreferredDuringSchedulingIgnoredDuringExecution();
            Object other$preferredDuringSchedulingIgnoredDuringExecution = other.getPreferredDuringSchedulingIgnoredDuringExecution();
            if (this$preferredDuringSchedulingIgnoredDuringExecution == null) {
               if (other$preferredDuringSchedulingIgnoredDuringExecution != null) {
                  return false;
               }
            } else if (!this$preferredDuringSchedulingIgnoredDuringExecution.equals(other$preferredDuringSchedulingIgnoredDuringExecution)) {
               return false;
            }

            Object this$requiredDuringSchedulingIgnoredDuringExecution = this.getRequiredDuringSchedulingIgnoredDuringExecution();
            Object other$requiredDuringSchedulingIgnoredDuringExecution = other.getRequiredDuringSchedulingIgnoredDuringExecution();
            if (this$requiredDuringSchedulingIgnoredDuringExecution == null) {
               if (other$requiredDuringSchedulingIgnoredDuringExecution != null) {
                  return false;
               }
            } else if (!this$requiredDuringSchedulingIgnoredDuringExecution.equals(other$requiredDuringSchedulingIgnoredDuringExecution)) {
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
      return other instanceof NodeAffinity;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $preferredDuringSchedulingIgnoredDuringExecution = this.getPreferredDuringSchedulingIgnoredDuringExecution();
      result = result * 59 + ($preferredDuringSchedulingIgnoredDuringExecution == null ? 43 : $preferredDuringSchedulingIgnoredDuringExecution.hashCode());
      Object $requiredDuringSchedulingIgnoredDuringExecution = this.getRequiredDuringSchedulingIgnoredDuringExecution();
      result = result * 59 + ($requiredDuringSchedulingIgnoredDuringExecution == null ? 43 : $requiredDuringSchedulingIgnoredDuringExecution.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
