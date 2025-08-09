package io.fabric8.kubernetes.api.model.coordination.v1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "acquireTime", "holderIdentity", "leaseDurationSeconds", "leaseTransitions", "preferredHolder", "renewTime", "strategy"})
public class LeaseSpec implements Editable, KubernetesResource {
   @JsonProperty("acquireTime")
   @JsonFormat(
      timezone = "UTC",
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
   )
   private ZonedDateTime acquireTime;
   @JsonProperty("holderIdentity")
   private String holderIdentity;
   @JsonProperty("leaseDurationSeconds")
   private Integer leaseDurationSeconds;
   @JsonProperty("leaseTransitions")
   private Integer leaseTransitions;
   @JsonProperty("preferredHolder")
   private String preferredHolder;
   @JsonProperty("renewTime")
   @JsonFormat(
      timezone = "UTC",
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
   )
   private ZonedDateTime renewTime;
   @JsonProperty("strategy")
   private String strategy;
   @JsonIgnore
   private Map additionalProperties = new HashMap();

   public LeaseSpec() {
   }

   public LeaseSpec(ZonedDateTime acquireTime, String holderIdentity, Integer leaseDurationSeconds, Integer leaseTransitions, String preferredHolder, ZonedDateTime renewTime, String strategy) {
      this.acquireTime = acquireTime;
      this.holderIdentity = holderIdentity;
      this.leaseDurationSeconds = leaseDurationSeconds;
      this.leaseTransitions = leaseTransitions;
      this.preferredHolder = preferredHolder;
      this.renewTime = renewTime;
      this.strategy = strategy;
   }

   @JsonProperty("acquireTime")
   public ZonedDateTime getAcquireTime() {
      return this.acquireTime;
   }

   @JsonProperty("acquireTime")
   public void setAcquireTime(ZonedDateTime acquireTime) {
      this.acquireTime = acquireTime;
   }

   @JsonProperty("holderIdentity")
   public String getHolderIdentity() {
      return this.holderIdentity;
   }

   @JsonProperty("holderIdentity")
   public void setHolderIdentity(String holderIdentity) {
      this.holderIdentity = holderIdentity;
   }

   @JsonProperty("leaseDurationSeconds")
   public Integer getLeaseDurationSeconds() {
      return this.leaseDurationSeconds;
   }

   @JsonProperty("leaseDurationSeconds")
   public void setLeaseDurationSeconds(Integer leaseDurationSeconds) {
      this.leaseDurationSeconds = leaseDurationSeconds;
   }

   @JsonProperty("leaseTransitions")
   public Integer getLeaseTransitions() {
      return this.leaseTransitions;
   }

   @JsonProperty("leaseTransitions")
   public void setLeaseTransitions(Integer leaseTransitions) {
      this.leaseTransitions = leaseTransitions;
   }

   @JsonProperty("preferredHolder")
   public String getPreferredHolder() {
      return this.preferredHolder;
   }

   @JsonProperty("preferredHolder")
   public void setPreferredHolder(String preferredHolder) {
      this.preferredHolder = preferredHolder;
   }

   @JsonProperty("renewTime")
   public ZonedDateTime getRenewTime() {
      return this.renewTime;
   }

   @JsonProperty("renewTime")
   public void setRenewTime(ZonedDateTime renewTime) {
      this.renewTime = renewTime;
   }

   @JsonProperty("strategy")
   public String getStrategy() {
      return this.strategy;
   }

   @JsonProperty("strategy")
   public void setStrategy(String strategy) {
      this.strategy = strategy;
   }

   @JsonIgnore
   public LeaseSpecBuilder edit() {
      return new LeaseSpecBuilder(this);
   }

   @JsonIgnore
   public LeaseSpecBuilder toBuilder() {
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
      ZonedDateTime var10000 = this.getAcquireTime();
      return "LeaseSpec(acquireTime=" + var10000 + ", holderIdentity=" + this.getHolderIdentity() + ", leaseDurationSeconds=" + this.getLeaseDurationSeconds() + ", leaseTransitions=" + this.getLeaseTransitions() + ", preferredHolder=" + this.getPreferredHolder() + ", renewTime=" + this.getRenewTime() + ", strategy=" + this.getStrategy() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LeaseSpec)) {
         return false;
      } else {
         LeaseSpec other = (LeaseSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$leaseDurationSeconds = this.getLeaseDurationSeconds();
            Object other$leaseDurationSeconds = other.getLeaseDurationSeconds();
            if (this$leaseDurationSeconds == null) {
               if (other$leaseDurationSeconds != null) {
                  return false;
               }
            } else if (!this$leaseDurationSeconds.equals(other$leaseDurationSeconds)) {
               return false;
            }

            Object this$leaseTransitions = this.getLeaseTransitions();
            Object other$leaseTransitions = other.getLeaseTransitions();
            if (this$leaseTransitions == null) {
               if (other$leaseTransitions != null) {
                  return false;
               }
            } else if (!this$leaseTransitions.equals(other$leaseTransitions)) {
               return false;
            }

            Object this$acquireTime = this.getAcquireTime();
            Object other$acquireTime = other.getAcquireTime();
            if (this$acquireTime == null) {
               if (other$acquireTime != null) {
                  return false;
               }
            } else if (!this$acquireTime.equals(other$acquireTime)) {
               return false;
            }

            Object this$holderIdentity = this.getHolderIdentity();
            Object other$holderIdentity = other.getHolderIdentity();
            if (this$holderIdentity == null) {
               if (other$holderIdentity != null) {
                  return false;
               }
            } else if (!this$holderIdentity.equals(other$holderIdentity)) {
               return false;
            }

            Object this$preferredHolder = this.getPreferredHolder();
            Object other$preferredHolder = other.getPreferredHolder();
            if (this$preferredHolder == null) {
               if (other$preferredHolder != null) {
                  return false;
               }
            } else if (!this$preferredHolder.equals(other$preferredHolder)) {
               return false;
            }

            Object this$renewTime = this.getRenewTime();
            Object other$renewTime = other.getRenewTime();
            if (this$renewTime == null) {
               if (other$renewTime != null) {
                  return false;
               }
            } else if (!this$renewTime.equals(other$renewTime)) {
               return false;
            }

            Object this$strategy = this.getStrategy();
            Object other$strategy = other.getStrategy();
            if (this$strategy == null) {
               if (other$strategy != null) {
                  return false;
               }
            } else if (!this$strategy.equals(other$strategy)) {
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
      return other instanceof LeaseSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $leaseDurationSeconds = this.getLeaseDurationSeconds();
      result = result * 59 + ($leaseDurationSeconds == null ? 43 : $leaseDurationSeconds.hashCode());
      Object $leaseTransitions = this.getLeaseTransitions();
      result = result * 59 + ($leaseTransitions == null ? 43 : $leaseTransitions.hashCode());
      Object $acquireTime = this.getAcquireTime();
      result = result * 59 + ($acquireTime == null ? 43 : $acquireTime.hashCode());
      Object $holderIdentity = this.getHolderIdentity();
      result = result * 59 + ($holderIdentity == null ? 43 : $holderIdentity.hashCode());
      Object $preferredHolder = this.getPreferredHolder();
      result = result * 59 + ($preferredHolder == null ? 43 : $preferredHolder.hashCode());
      Object $renewTime = this.getRenewTime();
      result = result * 59 + ($renewTime == null ? 43 : $renewTime.hashCode());
      Object $strategy = this.getStrategy();
      result = result * 59 + ($strategy == null ? 43 : $strategy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
