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
@JsonPropertyOrder({"nodeAffinity", "podAffinity", "podAntiAffinity"})
public class Affinity implements Editable, KubernetesResource {
   @JsonProperty("nodeAffinity")
   private NodeAffinity nodeAffinity;
   @JsonProperty("podAffinity")
   private PodAffinity podAffinity;
   @JsonProperty("podAntiAffinity")
   private PodAntiAffinity podAntiAffinity;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Affinity() {
   }

   public Affinity(NodeAffinity nodeAffinity, PodAffinity podAffinity, PodAntiAffinity podAntiAffinity) {
      this.nodeAffinity = nodeAffinity;
      this.podAffinity = podAffinity;
      this.podAntiAffinity = podAntiAffinity;
   }

   @JsonProperty("nodeAffinity")
   public NodeAffinity getNodeAffinity() {
      return this.nodeAffinity;
   }

   @JsonProperty("nodeAffinity")
   public void setNodeAffinity(NodeAffinity nodeAffinity) {
      this.nodeAffinity = nodeAffinity;
   }

   @JsonProperty("podAffinity")
   public PodAffinity getPodAffinity() {
      return this.podAffinity;
   }

   @JsonProperty("podAffinity")
   public void setPodAffinity(PodAffinity podAffinity) {
      this.podAffinity = podAffinity;
   }

   @JsonProperty("podAntiAffinity")
   public PodAntiAffinity getPodAntiAffinity() {
      return this.podAntiAffinity;
   }

   @JsonProperty("podAntiAffinity")
   public void setPodAntiAffinity(PodAntiAffinity podAntiAffinity) {
      this.podAntiAffinity = podAntiAffinity;
   }

   @JsonIgnore
   public AffinityBuilder edit() {
      return new AffinityBuilder(this);
   }

   @JsonIgnore
   public AffinityBuilder toBuilder() {
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
      NodeAffinity var10000 = this.getNodeAffinity();
      return "Affinity(nodeAffinity=" + var10000 + ", podAffinity=" + this.getPodAffinity() + ", podAntiAffinity=" + this.getPodAntiAffinity() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Affinity)) {
         return false;
      } else {
         Affinity other = (Affinity)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodeAffinity = this.getNodeAffinity();
            Object other$nodeAffinity = other.getNodeAffinity();
            if (this$nodeAffinity == null) {
               if (other$nodeAffinity != null) {
                  return false;
               }
            } else if (!this$nodeAffinity.equals(other$nodeAffinity)) {
               return false;
            }

            Object this$podAffinity = this.getPodAffinity();
            Object other$podAffinity = other.getPodAffinity();
            if (this$podAffinity == null) {
               if (other$podAffinity != null) {
                  return false;
               }
            } else if (!this$podAffinity.equals(other$podAffinity)) {
               return false;
            }

            Object this$podAntiAffinity = this.getPodAntiAffinity();
            Object other$podAntiAffinity = other.getPodAntiAffinity();
            if (this$podAntiAffinity == null) {
               if (other$podAntiAffinity != null) {
                  return false;
               }
            } else if (!this$podAntiAffinity.equals(other$podAntiAffinity)) {
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
      return other instanceof Affinity;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodeAffinity = this.getNodeAffinity();
      result = result * 59 + ($nodeAffinity == null ? 43 : $nodeAffinity.hashCode());
      Object $podAffinity = this.getPodAffinity();
      result = result * 59 + ($podAffinity == null ? 43 : $podAffinity.hashCode());
      Object $podAntiAffinity = this.getPodAntiAffinity();
      result = result * 59 + ($podAntiAffinity == null ? 43 : $podAntiAffinity.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
