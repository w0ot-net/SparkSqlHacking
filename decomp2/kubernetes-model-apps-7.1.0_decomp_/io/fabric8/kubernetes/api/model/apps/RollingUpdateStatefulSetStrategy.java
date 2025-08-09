package io.fabric8.kubernetes.api.model.apps;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"maxUnavailable", "partition"})
public class RollingUpdateStatefulSetStrategy implements Editable, KubernetesResource {
   @JsonProperty("maxUnavailable")
   private IntOrString maxUnavailable;
   @JsonProperty("partition")
   private Integer partition;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RollingUpdateStatefulSetStrategy() {
   }

   public RollingUpdateStatefulSetStrategy(IntOrString maxUnavailable, Integer partition) {
      this.maxUnavailable = maxUnavailable;
      this.partition = partition;
   }

   @JsonProperty("maxUnavailable")
   public IntOrString getMaxUnavailable() {
      return this.maxUnavailable;
   }

   @JsonProperty("maxUnavailable")
   public void setMaxUnavailable(IntOrString maxUnavailable) {
      this.maxUnavailable = maxUnavailable;
   }

   @JsonProperty("partition")
   public Integer getPartition() {
      return this.partition;
   }

   @JsonProperty("partition")
   public void setPartition(Integer partition) {
      this.partition = partition;
   }

   @JsonIgnore
   public RollingUpdateStatefulSetStrategyBuilder edit() {
      return new RollingUpdateStatefulSetStrategyBuilder(this);
   }

   @JsonIgnore
   public RollingUpdateStatefulSetStrategyBuilder toBuilder() {
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
      IntOrString var10000 = this.getMaxUnavailable();
      return "RollingUpdateStatefulSetStrategy(maxUnavailable=" + var10000 + ", partition=" + this.getPartition() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RollingUpdateStatefulSetStrategy)) {
         return false;
      } else {
         RollingUpdateStatefulSetStrategy other = (RollingUpdateStatefulSetStrategy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$partition = this.getPartition();
            Object other$partition = other.getPartition();
            if (this$partition == null) {
               if (other$partition != null) {
                  return false;
               }
            } else if (!this$partition.equals(other$partition)) {
               return false;
            }

            Object this$maxUnavailable = this.getMaxUnavailable();
            Object other$maxUnavailable = other.getMaxUnavailable();
            if (this$maxUnavailable == null) {
               if (other$maxUnavailable != null) {
                  return false;
               }
            } else if (!this$maxUnavailable.equals(other$maxUnavailable)) {
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
      return other instanceof RollingUpdateStatefulSetStrategy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $partition = this.getPartition();
      result = result * 59 + ($partition == null ? 43 : $partition.hashCode());
      Object $maxUnavailable = this.getMaxUnavailable();
      result = result * 59 + ($maxUnavailable == null ? 43 : $maxUnavailable.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
