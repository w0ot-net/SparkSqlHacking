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
@JsonPropertyOrder({"nodeSelectorTerms"})
public class NodeSelector implements Editable, KubernetesResource {
   @JsonProperty("nodeSelectorTerms")
   @JsonInclude(Include.NON_EMPTY)
   private List nodeSelectorTerms = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeSelector() {
   }

   public NodeSelector(List nodeSelectorTerms) {
      this.nodeSelectorTerms = nodeSelectorTerms;
   }

   @JsonProperty("nodeSelectorTerms")
   @JsonInclude(Include.NON_EMPTY)
   public List getNodeSelectorTerms() {
      return this.nodeSelectorTerms;
   }

   @JsonProperty("nodeSelectorTerms")
   public void setNodeSelectorTerms(List nodeSelectorTerms) {
      this.nodeSelectorTerms = nodeSelectorTerms;
   }

   @JsonIgnore
   public NodeSelectorBuilder edit() {
      return new NodeSelectorBuilder(this);
   }

   @JsonIgnore
   public NodeSelectorBuilder toBuilder() {
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
      List var10000 = this.getNodeSelectorTerms();
      return "NodeSelector(nodeSelectorTerms=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeSelector)) {
         return false;
      } else {
         NodeSelector other = (NodeSelector)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodeSelectorTerms = this.getNodeSelectorTerms();
            Object other$nodeSelectorTerms = other.getNodeSelectorTerms();
            if (this$nodeSelectorTerms == null) {
               if (other$nodeSelectorTerms != null) {
                  return false;
               }
            } else if (!this$nodeSelectorTerms.equals(other$nodeSelectorTerms)) {
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
      return other instanceof NodeSelector;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodeSelectorTerms = this.getNodeSelectorTerms();
      result = result * 59 + ($nodeSelectorTerms == null ? 43 : $nodeSelectorTerms.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
