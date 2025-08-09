package io.fabric8.kubernetes.api.model.coordination.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LeaseSpecFluent extends BaseFluent {
   private ZonedDateTime acquireTime;
   private String holderIdentity;
   private Integer leaseDurationSeconds;
   private Integer leaseTransitions;
   private String preferredHolder;
   private ZonedDateTime renewTime;
   private String strategy;
   private Map additionalProperties;

   public LeaseSpecFluent() {
   }

   public LeaseSpecFluent(LeaseSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LeaseSpec instance) {
      instance = instance != null ? instance : new LeaseSpec();
      if (instance != null) {
         this.withAcquireTime(instance.getAcquireTime());
         this.withHolderIdentity(instance.getHolderIdentity());
         this.withLeaseDurationSeconds(instance.getLeaseDurationSeconds());
         this.withLeaseTransitions(instance.getLeaseTransitions());
         this.withPreferredHolder(instance.getPreferredHolder());
         this.withRenewTime(instance.getRenewTime());
         this.withStrategy(instance.getStrategy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ZonedDateTime getAcquireTime() {
      return this.acquireTime;
   }

   public LeaseSpecFluent withAcquireTime(ZonedDateTime acquireTime) {
      this.acquireTime = acquireTime;
      return this;
   }

   public boolean hasAcquireTime() {
      return this.acquireTime != null;
   }

   public String getHolderIdentity() {
      return this.holderIdentity;
   }

   public LeaseSpecFluent withHolderIdentity(String holderIdentity) {
      this.holderIdentity = holderIdentity;
      return this;
   }

   public boolean hasHolderIdentity() {
      return this.holderIdentity != null;
   }

   public Integer getLeaseDurationSeconds() {
      return this.leaseDurationSeconds;
   }

   public LeaseSpecFluent withLeaseDurationSeconds(Integer leaseDurationSeconds) {
      this.leaseDurationSeconds = leaseDurationSeconds;
      return this;
   }

   public boolean hasLeaseDurationSeconds() {
      return this.leaseDurationSeconds != null;
   }

   public Integer getLeaseTransitions() {
      return this.leaseTransitions;
   }

   public LeaseSpecFluent withLeaseTransitions(Integer leaseTransitions) {
      this.leaseTransitions = leaseTransitions;
      return this;
   }

   public boolean hasLeaseTransitions() {
      return this.leaseTransitions != null;
   }

   public String getPreferredHolder() {
      return this.preferredHolder;
   }

   public LeaseSpecFluent withPreferredHolder(String preferredHolder) {
      this.preferredHolder = preferredHolder;
      return this;
   }

   public boolean hasPreferredHolder() {
      return this.preferredHolder != null;
   }

   public ZonedDateTime getRenewTime() {
      return this.renewTime;
   }

   public LeaseSpecFluent withRenewTime(ZonedDateTime renewTime) {
      this.renewTime = renewTime;
      return this;
   }

   public boolean hasRenewTime() {
      return this.renewTime != null;
   }

   public String getStrategy() {
      return this.strategy;
   }

   public LeaseSpecFluent withStrategy(String strategy) {
      this.strategy = strategy;
      return this;
   }

   public boolean hasStrategy() {
      return this.strategy != null;
   }

   public LeaseSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LeaseSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LeaseSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LeaseSpecFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public LeaseSpecFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            LeaseSpecFluent that = (LeaseSpecFluent)o;
            if (!Objects.equals(this.acquireTime, that.acquireTime)) {
               return false;
            } else if (!Objects.equals(this.holderIdentity, that.holderIdentity)) {
               return false;
            } else if (!Objects.equals(this.leaseDurationSeconds, that.leaseDurationSeconds)) {
               return false;
            } else if (!Objects.equals(this.leaseTransitions, that.leaseTransitions)) {
               return false;
            } else if (!Objects.equals(this.preferredHolder, that.preferredHolder)) {
               return false;
            } else if (!Objects.equals(this.renewTime, that.renewTime)) {
               return false;
            } else if (!Objects.equals(this.strategy, that.strategy)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.acquireTime, this.holderIdentity, this.leaseDurationSeconds, this.leaseTransitions, this.preferredHolder, this.renewTime, this.strategy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.acquireTime != null) {
         sb.append("acquireTime:");
         sb.append(this.acquireTime + ",");
      }

      if (this.holderIdentity != null) {
         sb.append("holderIdentity:");
         sb.append(this.holderIdentity + ",");
      }

      if (this.leaseDurationSeconds != null) {
         sb.append("leaseDurationSeconds:");
         sb.append(this.leaseDurationSeconds + ",");
      }

      if (this.leaseTransitions != null) {
         sb.append("leaseTransitions:");
         sb.append(this.leaseTransitions + ",");
      }

      if (this.preferredHolder != null) {
         sb.append("preferredHolder:");
         sb.append(this.preferredHolder + ",");
      }

      if (this.renewTime != null) {
         sb.append("renewTime:");
         sb.append(this.renewTime + ",");
      }

      if (this.strategy != null) {
         sb.append("strategy:");
         sb.append(this.strategy + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
