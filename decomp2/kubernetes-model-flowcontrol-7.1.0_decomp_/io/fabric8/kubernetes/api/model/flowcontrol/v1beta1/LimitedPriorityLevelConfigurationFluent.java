package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LimitedPriorityLevelConfigurationFluent extends BaseFluent {
   private Integer assuredConcurrencyShares;
   private LimitResponseBuilder limitResponse;
   private Map additionalProperties;

   public LimitedPriorityLevelConfigurationFluent() {
   }

   public LimitedPriorityLevelConfigurationFluent(LimitedPriorityLevelConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LimitedPriorityLevelConfiguration instance) {
      instance = instance != null ? instance : new LimitedPriorityLevelConfiguration();
      if (instance != null) {
         this.withAssuredConcurrencyShares(instance.getAssuredConcurrencyShares());
         this.withLimitResponse(instance.getLimitResponse());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAssuredConcurrencyShares() {
      return this.assuredConcurrencyShares;
   }

   public LimitedPriorityLevelConfigurationFluent withAssuredConcurrencyShares(Integer assuredConcurrencyShares) {
      this.assuredConcurrencyShares = assuredConcurrencyShares;
      return this;
   }

   public boolean hasAssuredConcurrencyShares() {
      return this.assuredConcurrencyShares != null;
   }

   public LimitResponse buildLimitResponse() {
      return this.limitResponse != null ? this.limitResponse.build() : null;
   }

   public LimitedPriorityLevelConfigurationFluent withLimitResponse(LimitResponse limitResponse) {
      this._visitables.remove("limitResponse");
      if (limitResponse != null) {
         this.limitResponse = new LimitResponseBuilder(limitResponse);
         this._visitables.get("limitResponse").add(this.limitResponse);
      } else {
         this.limitResponse = null;
         this._visitables.get("limitResponse").remove(this.limitResponse);
      }

      return this;
   }

   public boolean hasLimitResponse() {
      return this.limitResponse != null;
   }

   public LimitResponseNested withNewLimitResponse() {
      return new LimitResponseNested((LimitResponse)null);
   }

   public LimitResponseNested withNewLimitResponseLike(LimitResponse item) {
      return new LimitResponseNested(item);
   }

   public LimitResponseNested editLimitResponse() {
      return this.withNewLimitResponseLike((LimitResponse)Optional.ofNullable(this.buildLimitResponse()).orElse((Object)null));
   }

   public LimitResponseNested editOrNewLimitResponse() {
      return this.withNewLimitResponseLike((LimitResponse)Optional.ofNullable(this.buildLimitResponse()).orElse((new LimitResponseBuilder()).build()));
   }

   public LimitResponseNested editOrNewLimitResponseLike(LimitResponse item) {
      return this.withNewLimitResponseLike((LimitResponse)Optional.ofNullable(this.buildLimitResponse()).orElse(item));
   }

   public LimitedPriorityLevelConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LimitedPriorityLevelConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LimitedPriorityLevelConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LimitedPriorityLevelConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public LimitedPriorityLevelConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            LimitedPriorityLevelConfigurationFluent that = (LimitedPriorityLevelConfigurationFluent)o;
            if (!Objects.equals(this.assuredConcurrencyShares, that.assuredConcurrencyShares)) {
               return false;
            } else if (!Objects.equals(this.limitResponse, that.limitResponse)) {
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
      return Objects.hash(new Object[]{this.assuredConcurrencyShares, this.limitResponse, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.assuredConcurrencyShares != null) {
         sb.append("assuredConcurrencyShares:");
         sb.append(this.assuredConcurrencyShares + ",");
      }

      if (this.limitResponse != null) {
         sb.append("limitResponse:");
         sb.append(this.limitResponse + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LimitResponseNested extends LimitResponseFluent implements Nested {
      LimitResponseBuilder builder;

      LimitResponseNested(LimitResponse item) {
         this.builder = new LimitResponseBuilder(this, item);
      }

      public Object and() {
         return LimitedPriorityLevelConfigurationFluent.this.withLimitResponse(this.builder.build());
      }

      public Object endLimitResponse() {
         return this.and();
      }
   }
}
