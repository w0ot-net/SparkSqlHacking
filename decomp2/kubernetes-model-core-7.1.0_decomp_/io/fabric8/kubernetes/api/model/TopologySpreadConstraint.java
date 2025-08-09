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
@JsonPropertyOrder({"labelSelector", "matchLabelKeys", "maxSkew", "minDomains", "nodeAffinityPolicy", "nodeTaintsPolicy", "topologyKey", "whenUnsatisfiable"})
public class TopologySpreadConstraint implements Editable, KubernetesResource {
   @JsonProperty("labelSelector")
   private LabelSelector labelSelector;
   @JsonProperty("matchLabelKeys")
   @JsonInclude(Include.NON_EMPTY)
   private List matchLabelKeys = new ArrayList();
   @JsonProperty("maxSkew")
   private Integer maxSkew;
   @JsonProperty("minDomains")
   private Integer minDomains;
   @JsonProperty("nodeAffinityPolicy")
   private String nodeAffinityPolicy;
   @JsonProperty("nodeTaintsPolicy")
   private String nodeTaintsPolicy;
   @JsonProperty("topologyKey")
   private String topologyKey;
   @JsonProperty("whenUnsatisfiable")
   private String whenUnsatisfiable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TopologySpreadConstraint() {
   }

   public TopologySpreadConstraint(LabelSelector labelSelector, List matchLabelKeys, Integer maxSkew, Integer minDomains, String nodeAffinityPolicy, String nodeTaintsPolicy, String topologyKey, String whenUnsatisfiable) {
      this.labelSelector = labelSelector;
      this.matchLabelKeys = matchLabelKeys;
      this.maxSkew = maxSkew;
      this.minDomains = minDomains;
      this.nodeAffinityPolicy = nodeAffinityPolicy;
      this.nodeTaintsPolicy = nodeTaintsPolicy;
      this.topologyKey = topologyKey;
      this.whenUnsatisfiable = whenUnsatisfiable;
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

   @JsonProperty("maxSkew")
   public Integer getMaxSkew() {
      return this.maxSkew;
   }

   @JsonProperty("maxSkew")
   public void setMaxSkew(Integer maxSkew) {
      this.maxSkew = maxSkew;
   }

   @JsonProperty("minDomains")
   public Integer getMinDomains() {
      return this.minDomains;
   }

   @JsonProperty("minDomains")
   public void setMinDomains(Integer minDomains) {
      this.minDomains = minDomains;
   }

   @JsonProperty("nodeAffinityPolicy")
   public String getNodeAffinityPolicy() {
      return this.nodeAffinityPolicy;
   }

   @JsonProperty("nodeAffinityPolicy")
   public void setNodeAffinityPolicy(String nodeAffinityPolicy) {
      this.nodeAffinityPolicy = nodeAffinityPolicy;
   }

   @JsonProperty("nodeTaintsPolicy")
   public String getNodeTaintsPolicy() {
      return this.nodeTaintsPolicy;
   }

   @JsonProperty("nodeTaintsPolicy")
   public void setNodeTaintsPolicy(String nodeTaintsPolicy) {
      this.nodeTaintsPolicy = nodeTaintsPolicy;
   }

   @JsonProperty("topologyKey")
   public String getTopologyKey() {
      return this.topologyKey;
   }

   @JsonProperty("topologyKey")
   public void setTopologyKey(String topologyKey) {
      this.topologyKey = topologyKey;
   }

   @JsonProperty("whenUnsatisfiable")
   public String getWhenUnsatisfiable() {
      return this.whenUnsatisfiable;
   }

   @JsonProperty("whenUnsatisfiable")
   public void setWhenUnsatisfiable(String whenUnsatisfiable) {
      this.whenUnsatisfiable = whenUnsatisfiable;
   }

   @JsonIgnore
   public TopologySpreadConstraintBuilder edit() {
      return new TopologySpreadConstraintBuilder(this);
   }

   @JsonIgnore
   public TopologySpreadConstraintBuilder toBuilder() {
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
      return "TopologySpreadConstraint(labelSelector=" + var10000 + ", matchLabelKeys=" + this.getMatchLabelKeys() + ", maxSkew=" + this.getMaxSkew() + ", minDomains=" + this.getMinDomains() + ", nodeAffinityPolicy=" + this.getNodeAffinityPolicy() + ", nodeTaintsPolicy=" + this.getNodeTaintsPolicy() + ", topologyKey=" + this.getTopologyKey() + ", whenUnsatisfiable=" + this.getWhenUnsatisfiable() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TopologySpreadConstraint)) {
         return false;
      } else {
         TopologySpreadConstraint other = (TopologySpreadConstraint)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$maxSkew = this.getMaxSkew();
            Object other$maxSkew = other.getMaxSkew();
            if (this$maxSkew == null) {
               if (other$maxSkew != null) {
                  return false;
               }
            } else if (!this$maxSkew.equals(other$maxSkew)) {
               return false;
            }

            Object this$minDomains = this.getMinDomains();
            Object other$minDomains = other.getMinDomains();
            if (this$minDomains == null) {
               if (other$minDomains != null) {
                  return false;
               }
            } else if (!this$minDomains.equals(other$minDomains)) {
               return false;
            }

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

            Object this$nodeAffinityPolicy = this.getNodeAffinityPolicy();
            Object other$nodeAffinityPolicy = other.getNodeAffinityPolicy();
            if (this$nodeAffinityPolicy == null) {
               if (other$nodeAffinityPolicy != null) {
                  return false;
               }
            } else if (!this$nodeAffinityPolicy.equals(other$nodeAffinityPolicy)) {
               return false;
            }

            Object this$nodeTaintsPolicy = this.getNodeTaintsPolicy();
            Object other$nodeTaintsPolicy = other.getNodeTaintsPolicy();
            if (this$nodeTaintsPolicy == null) {
               if (other$nodeTaintsPolicy != null) {
                  return false;
               }
            } else if (!this$nodeTaintsPolicy.equals(other$nodeTaintsPolicy)) {
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

            Object this$whenUnsatisfiable = this.getWhenUnsatisfiable();
            Object other$whenUnsatisfiable = other.getWhenUnsatisfiable();
            if (this$whenUnsatisfiable == null) {
               if (other$whenUnsatisfiable != null) {
                  return false;
               }
            } else if (!this$whenUnsatisfiable.equals(other$whenUnsatisfiable)) {
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
      return other instanceof TopologySpreadConstraint;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxSkew = this.getMaxSkew();
      result = result * 59 + ($maxSkew == null ? 43 : $maxSkew.hashCode());
      Object $minDomains = this.getMinDomains();
      result = result * 59 + ($minDomains == null ? 43 : $minDomains.hashCode());
      Object $labelSelector = this.getLabelSelector();
      result = result * 59 + ($labelSelector == null ? 43 : $labelSelector.hashCode());
      Object $matchLabelKeys = this.getMatchLabelKeys();
      result = result * 59 + ($matchLabelKeys == null ? 43 : $matchLabelKeys.hashCode());
      Object $nodeAffinityPolicy = this.getNodeAffinityPolicy();
      result = result * 59 + ($nodeAffinityPolicy == null ? 43 : $nodeAffinityPolicy.hashCode());
      Object $nodeTaintsPolicy = this.getNodeTaintsPolicy();
      result = result * 59 + ($nodeTaintsPolicy == null ? 43 : $nodeTaintsPolicy.hashCode());
      Object $topologyKey = this.getTopologyKey();
      result = result * 59 + ($topologyKey == null ? 43 : $topologyKey.hashCode());
      Object $whenUnsatisfiable = this.getWhenUnsatisfiable();
      result = result * 59 + ($whenUnsatisfiable == null ? 43 : $whenUnsatisfiable.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
