package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class HTTPRouteRetryFluent extends BaseFluent {
   private Integer attempts;
   private String backoff;
   private List codes = new ArrayList();
   private Map additionalProperties;

   public HTTPRouteRetryFluent() {
   }

   public HTTPRouteRetryFluent(HTTPRouteRetry instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRouteRetry instance) {
      instance = instance != null ? instance : new HTTPRouteRetry();
      if (instance != null) {
         this.withAttempts(instance.getAttempts());
         this.withBackoff(instance.getBackoff());
         this.withCodes(instance.getCodes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAttempts() {
      return this.attempts;
   }

   public HTTPRouteRetryFluent withAttempts(Integer attempts) {
      this.attempts = attempts;
      return this;
   }

   public boolean hasAttempts() {
      return this.attempts != null;
   }

   public String getBackoff() {
      return this.backoff;
   }

   public HTTPRouteRetryFluent withBackoff(String backoff) {
      this.backoff = backoff;
      return this;
   }

   public boolean hasBackoff() {
      return this.backoff != null;
   }

   public HTTPRouteRetryFluent addToCodes(int index, Integer item) {
      if (this.codes == null) {
         this.codes = new ArrayList();
      }

      this.codes.add(index, item);
      return this;
   }

   public HTTPRouteRetryFluent setToCodes(int index, Integer item) {
      if (this.codes == null) {
         this.codes = new ArrayList();
      }

      this.codes.set(index, item);
      return this;
   }

   public HTTPRouteRetryFluent addToCodes(Integer... items) {
      if (this.codes == null) {
         this.codes = new ArrayList();
      }

      for(Integer item : items) {
         this.codes.add(item);
      }

      return this;
   }

   public HTTPRouteRetryFluent addAllToCodes(Collection items) {
      if (this.codes == null) {
         this.codes = new ArrayList();
      }

      for(Integer item : items) {
         this.codes.add(item);
      }

      return this;
   }

   public HTTPRouteRetryFluent removeFromCodes(Integer... items) {
      if (this.codes == null) {
         return this;
      } else {
         for(Integer item : items) {
            this.codes.remove(item);
         }

         return this;
      }
   }

   public HTTPRouteRetryFluent removeAllFromCodes(Collection items) {
      if (this.codes == null) {
         return this;
      } else {
         for(Integer item : items) {
            this.codes.remove(item);
         }

         return this;
      }
   }

   public List getCodes() {
      return this.codes;
   }

   public Integer getCode(int index) {
      return (Integer)this.codes.get(index);
   }

   public Integer getFirstCode() {
      return (Integer)this.codes.get(0);
   }

   public Integer getLastCode() {
      return (Integer)this.codes.get(this.codes.size() - 1);
   }

   public Integer getMatchingCode(Predicate predicate) {
      for(Integer item : this.codes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCode(Predicate predicate) {
      for(Integer item : this.codes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public HTTPRouteRetryFluent withCodes(List codes) {
      if (codes != null) {
         this.codes = new ArrayList();

         for(Integer item : codes) {
            this.addToCodes(item);
         }
      } else {
         this.codes = null;
      }

      return this;
   }

   public HTTPRouteRetryFluent withCodes(Integer... codes) {
      if (this.codes != null) {
         this.codes.clear();
         this._visitables.remove("codes");
      }

      if (codes != null) {
         for(Integer item : codes) {
            this.addToCodes(item);
         }
      }

      return this;
   }

   public boolean hasCodes() {
      return this.codes != null && !this.codes.isEmpty();
   }

   public HTTPRouteRetryFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRouteRetryFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRouteRetryFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRouteRetryFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRouteRetryFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRouteRetryFluent that = (HTTPRouteRetryFluent)o;
            if (!Objects.equals(this.attempts, that.attempts)) {
               return false;
            } else if (!Objects.equals(this.backoff, that.backoff)) {
               return false;
            } else if (!Objects.equals(this.codes, that.codes)) {
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
      return Objects.hash(new Object[]{this.attempts, this.backoff, this.codes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attempts != null) {
         sb.append("attempts:");
         sb.append(this.attempts + ",");
      }

      if (this.backoff != null) {
         sb.append("backoff:");
         sb.append(this.backoff + ",");
      }

      if (this.codes != null && !this.codes.isEmpty()) {
         sb.append("codes:");
         sb.append(this.codes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
