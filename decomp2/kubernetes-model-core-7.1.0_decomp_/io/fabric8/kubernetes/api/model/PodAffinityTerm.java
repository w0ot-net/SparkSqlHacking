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
@JsonPropertyOrder({"labelSelector", "matchLabelKeys", "mismatchLabelKeys", "namespaceSelector", "namespaces", "topologyKey"})
public class PodAffinityTerm implements Editable, KubernetesResource {
   @JsonProperty("labelSelector")
   private LabelSelector labelSelector;
   @JsonProperty("matchLabelKeys")
   @JsonInclude(Include.NON_EMPTY)
   private List matchLabelKeys = new ArrayList();
   @JsonProperty("mismatchLabelKeys")
   @JsonInclude(Include.NON_EMPTY)
   private List mismatchLabelKeys = new ArrayList();
   @JsonProperty("namespaceSelector")
   private LabelSelector namespaceSelector;
   @JsonProperty("namespaces")
   @JsonInclude(Include.NON_EMPTY)
   private List namespaces = new ArrayList();
   @JsonProperty("topologyKey")
   private String topologyKey;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodAffinityTerm() {
   }

   public PodAffinityTerm(LabelSelector labelSelector, List matchLabelKeys, List mismatchLabelKeys, LabelSelector namespaceSelector, List namespaces, String topologyKey) {
      this.labelSelector = labelSelector;
      this.matchLabelKeys = matchLabelKeys;
      this.mismatchLabelKeys = mismatchLabelKeys;
      this.namespaceSelector = namespaceSelector;
      this.namespaces = namespaces;
      this.topologyKey = topologyKey;
   }

   @JsonProperty("labelSelector")
   public LabelSelector getLabelSelector() {
      return this.labelSelector;
   }

   @JsonProperty("labelSelector")
   public void setLabelSelector(LabelSelector labelSelector) {
      this.labelSelector = labelSelector;
   }

   @JsonProperty("matchLabelKeys")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchLabelKeys() {
      return this.matchLabelKeys;
   }

   @JsonProperty("matchLabelKeys")
   public void setMatchLabelKeys(List matchLabelKeys) {
      this.matchLabelKeys = matchLabelKeys;
   }

   @JsonProperty("mismatchLabelKeys")
   @JsonInclude(Include.NON_EMPTY)
   public List getMismatchLabelKeys() {
      return this.mismatchLabelKeys;
   }

   @JsonProperty("mismatchLabelKeys")
   public void setMismatchLabelKeys(List mismatchLabelKeys) {
      this.mismatchLabelKeys = mismatchLabelKeys;
   }

   @JsonProperty("namespaceSelector")
   public LabelSelector getNamespaceSelector() {
      return this.namespaceSelector;
   }

   @JsonProperty("namespaceSelector")
   public void setNamespaceSelector(LabelSelector namespaceSelector) {
      this.namespaceSelector = namespaceSelector;
   }

   @JsonProperty("namespaces")
   @JsonInclude(Include.NON_EMPTY)
   public List getNamespaces() {
      return this.namespaces;
   }

   @JsonProperty("namespaces")
   public void setNamespaces(List namespaces) {
      this.namespaces = namespaces;
   }

   @JsonProperty("topologyKey")
   public String getTopologyKey() {
      return this.topologyKey;
   }

   @JsonProperty("topologyKey")
   public void setTopologyKey(String topologyKey) {
      this.topologyKey = topologyKey;
   }

   @JsonIgnore
   public PodAffinityTermBuilder edit() {
      return new PodAffinityTermBuilder(this);
   }

   @JsonIgnore
   public PodAffinityTermBuilder toBuilder() {
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
      LabelSelector var10000 = this.getLabelSelector();
      return "PodAffinityTerm(labelSelector=" + var10000 + ", matchLabelKeys=" + this.getMatchLabelKeys() + ", mismatchLabelKeys=" + this.getMismatchLabelKeys() + ", namespaceSelector=" + this.getNamespaceSelector() + ", namespaces=" + this.getNamespaces() + ", topologyKey=" + this.getTopologyKey() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodAffinityTerm)) {
         return false;
      } else {
         PodAffinityTerm other = (PodAffinityTerm)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$labelSelector = this.getLabelSelector();
            Object other$labelSelector = other.getLabelSelector();
            if (this$labelSelector == null) {
               if (other$labelSelector != null) {
                  return false;
               }
            } else if (!this$labelSelector.equals(other$labelSelector)) {
               return false;
            }

            Object this$matchLabelKeys = this.getMatchLabelKeys();
            Object other$matchLabelKeys = other.getMatchLabelKeys();
            if (this$matchLabelKeys == null) {
               if (other$matchLabelKeys != null) {
                  return false;
               }
            } else if (!this$matchLabelKeys.equals(other$matchLabelKeys)) {
               return false;
            }

            Object this$mismatchLabelKeys = this.getMismatchLabelKeys();
            Object other$mismatchLabelKeys = other.getMismatchLabelKeys();
            if (this$mismatchLabelKeys == null) {
               if (other$mismatchLabelKeys != null) {
                  return false;
               }
            } else if (!this$mismatchLabelKeys.equals(other$mismatchLabelKeys)) {
               return false;
            }

            Object this$namespaceSelector = this.getNamespaceSelector();
            Object other$namespaceSelector = other.getNamespaceSelector();
            if (this$namespaceSelector == null) {
               if (other$namespaceSelector != null) {
                  return false;
               }
            } else if (!this$namespaceSelector.equals(other$namespaceSelector)) {
               return false;
            }

            Object this$namespaces = this.getNamespaces();
            Object other$namespaces = other.getNamespaces();
            if (this$namespaces == null) {
               if (other$namespaces != null) {
                  return false;
               }
            } else if (!this$namespaces.equals(other$namespaces)) {
               return false;
            }

            Object this$topologyKey = this.getTopologyKey();
            Object other$topologyKey = other.getTopologyKey();
            if (this$topologyKey == null) {
               if (other$topologyKey != null) {
                  return false;
               }
            } else if (!this$topologyKey.equals(other$topologyKey)) {
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
      return other instanceof PodAffinityTerm;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $labelSelector = this.getLabelSelector();
      result = result * 59 + ($labelSelector == null ? 43 : $labelSelector.hashCode());
      Object $matchLabelKeys = this.getMatchLabelKeys();
      result = result * 59 + ($matchLabelKeys == null ? 43 : $matchLabelKeys.hashCode());
      Object $mismatchLabelKeys = this.getMismatchLabelKeys();
      result = result * 59 + ($mismatchLabelKeys == null ? 43 : $mismatchLabelKeys.hashCode());
      Object $namespaceSelector = this.getNamespaceSelector();
      result = result * 59 + ($namespaceSelector == null ? 43 : $namespaceSelector.hashCode());
      Object $namespaces = this.getNamespaces();
      result = result * 59 + ($namespaces == null ? 43 : $namespaces.hashCode());
      Object $topologyKey = this.getTopologyKey();
      result = result * 59 + ($topologyKey == null ? 43 : $topologyKey.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
