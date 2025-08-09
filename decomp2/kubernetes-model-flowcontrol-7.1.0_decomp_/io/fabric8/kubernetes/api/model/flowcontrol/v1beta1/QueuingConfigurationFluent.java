package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class QueuingConfigurationFluent extends BaseFluent {
   private Integer handSize;
   private Integer queueLengthLimit;
   private Integer queues;
   private Map additionalProperties;

   public QueuingConfigurationFluent() {
   }

   public QueuingConfigurationFluent(QueuingConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(QueuingConfiguration instance) {
      instance = instance != null ? instance : new QueuingConfiguration();
      if (instance != null) {
         this.withHandSize(instance.getHandSize());
         this.withQueueLengthLimit(instance.getQueueLengthLimit());
         this.withQueues(instance.getQueues());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getHandSize() {
      return this.handSize;
   }

   public QueuingConfigurationFluent withHandSize(Integer handSize) {
      this.handSize = handSize;
      return this;
   }

   public boolean hasHandSize() {
      return this.handSize != null;
   }

   public Integer getQueueLengthLimit() {
      return this.queueLengthLimit;
   }

   public QueuingConfigurationFluent withQueueLengthLimit(Integer queueLengthLimit) {
      this.queueLengthLimit = queueLengthLimit;
      return this;
   }

   public boolean hasQueueLengthLimit() {
      return this.queueLengthLimit != null;
   }

   public Integer getQueues() {
      return this.queues;
   }

   public QueuingConfigurationFluent withQueues(Integer queues) {
      this.queues = queues;
      return this;
   }

   public boolean hasQueues() {
      return this.queues != null;
   }

   public QueuingConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public QueuingConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public QueuingConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public QueuingConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public QueuingConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            QueuingConfigurationFluent that = (QueuingConfigurationFluent)o;
            if (!Objects.equals(this.handSize, that.handSize)) {
               return false;
            } else if (!Objects.equals(this.queueLengthLimit, that.queueLengthLimit)) {
               return false;
            } else if (!Objects.equals(this.queues, that.queues)) {
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
      return Objects.hash(new Object[]{this.handSize, this.queueLengthLimit, this.queues, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.handSize != null) {
         sb.append("handSize:");
         sb.append(this.handSize + ",");
      }

      if (this.queueLengthLimit != null) {
         sb.append("queueLengthLimit:");
         sb.append(this.queueLengthLimit + ",");
      }

      if (this.queues != null) {
         sb.append("queues:");
         sb.append(this.queues + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
