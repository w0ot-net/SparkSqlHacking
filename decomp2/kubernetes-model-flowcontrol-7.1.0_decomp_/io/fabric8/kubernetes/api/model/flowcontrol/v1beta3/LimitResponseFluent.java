package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LimitResponseFluent extends BaseFluent {
   private QueuingConfigurationBuilder queuing;
   private String type;
   private Map additionalProperties;

   public LimitResponseFluent() {
   }

   public LimitResponseFluent(LimitResponse instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LimitResponse instance) {
      instance = instance != null ? instance : new LimitResponse();
      if (instance != null) {
         this.withQueuing(instance.getQueuing());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public QueuingConfiguration buildQueuing() {
      return this.queuing != null ? this.queuing.build() : null;
   }

   public LimitResponseFluent withQueuing(QueuingConfiguration queuing) {
      this._visitables.remove("queuing");
      if (queuing != null) {
         this.queuing = new QueuingConfigurationBuilder(queuing);
         this._visitables.get("queuing").add(this.queuing);
      } else {
         this.queuing = null;
         this._visitables.get("queuing").remove(this.queuing);
      }

      return this;
   }

   public boolean hasQueuing() {
      return this.queuing != null;
   }

   public LimitResponseFluent withNewQueuing(Integer handSize, Integer queueLengthLimit, Integer queues) {
      return this.withQueuing(new QueuingConfiguration(handSize, queueLengthLimit, queues));
   }

   public QueuingNested withNewQueuing() {
      return new QueuingNested((QueuingConfiguration)null);
   }

   public QueuingNested withNewQueuingLike(QueuingConfiguration item) {
      return new QueuingNested(item);
   }

   public QueuingNested editQueuing() {
      return this.withNewQueuingLike((QueuingConfiguration)Optional.ofNullable(this.buildQueuing()).orElse((Object)null));
   }

   public QueuingNested editOrNewQueuing() {
      return this.withNewQueuingLike((QueuingConfiguration)Optional.ofNullable(this.buildQueuing()).orElse((new QueuingConfigurationBuilder()).build()));
   }

   public QueuingNested editOrNewQueuingLike(QueuingConfiguration item) {
      return this.withNewQueuingLike((QueuingConfiguration)Optional.ofNullable(this.buildQueuing()).orElse(item));
   }

   public String getType() {
      return this.type;
   }

   public LimitResponseFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public LimitResponseFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LimitResponseFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LimitResponseFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LimitResponseFluent removeFromAdditionalProperties(Map map) {
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

   public LimitResponseFluent withAdditionalProperties(Map additionalProperties) {
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
            LimitResponseFluent that = (LimitResponseFluent)o;
            if (!Objects.equals(this.queuing, that.queuing)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.queuing, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.queuing != null) {
         sb.append("queuing:");
         sb.append(this.queuing + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class QueuingNested extends QueuingConfigurationFluent implements Nested {
      QueuingConfigurationBuilder builder;

      QueuingNested(QueuingConfiguration item) {
         this.builder = new QueuingConfigurationBuilder(this, item);
      }

      public Object and() {
         return LimitResponseFluent.this.withQueuing(this.builder.build());
      }

      public Object endQueuing() {
         return this.and();
      }
   }
}
