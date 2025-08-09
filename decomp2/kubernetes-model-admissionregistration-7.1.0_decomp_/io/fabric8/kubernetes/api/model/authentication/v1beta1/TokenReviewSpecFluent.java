package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class TokenReviewSpecFluent extends BaseFluent {
   private List audiences = new ArrayList();
   private String token;
   private Map additionalProperties;

   public TokenReviewSpecFluent() {
   }

   public TokenReviewSpecFluent(TokenReviewSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(TokenReviewSpec instance) {
      instance = instance != null ? instance : new TokenReviewSpec();
      if (instance != null) {
         this.withAudiences(instance.getAudiences());
         this.withToken(instance.getToken());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public TokenReviewSpecFluent addToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.add(index, item);
      return this;
   }

   public TokenReviewSpecFluent setToAudiences(int index, String item) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      this.audiences.set(index, item);
      return this;
   }

   public TokenReviewSpecFluent addToAudiences(String... items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenReviewSpecFluent addAllToAudiences(Collection items) {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      for(String item : items) {
         this.audiences.add(item);
      }

      return this;
   }

   public TokenReviewSpecFluent removeFromAudiences(String... items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public TokenReviewSpecFluent removeAllFromAudiences(Collection items) {
      if (this.audiences == null) {
         return this;
      } else {
         for(String item : items) {
            this.audiences.remove(item);
         }

         return this;
      }
   }

   public List getAudiences() {
      return this.audiences;
   }

   public String getAudience(int index) {
      return (String)this.audiences.get(index);
   }

   public String getFirstAudience() {
      return (String)this.audiences.get(0);
   }

   public String getLastAudience() {
      return (String)this.audiences.get(this.audiences.size() - 1);
   }

   public String getMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAudience(Predicate predicate) {
      for(String item : this.audiences) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public TokenReviewSpecFluent withAudiences(List audiences) {
      if (audiences != null) {
         this.audiences = new ArrayList();

         for(String item : audiences) {
            this.addToAudiences(item);
         }
      } else {
         this.audiences = null;
      }

      return this;
   }

   public TokenReviewSpecFluent withAudiences(String... audiences) {
      if (this.audiences != null) {
         this.audiences.clear();
         this._visitables.remove("audiences");
      }

      if (audiences != null) {
         for(String item : audiences) {
            this.addToAudiences(item);
         }
      }

      return this;
   }

   public boolean hasAudiences() {
      return this.audiences != null && !this.audiences.isEmpty();
   }

   public String getToken() {
      return this.token;
   }

   public TokenReviewSpecFluent withToken(String token) {
      this.token = token;
      return this;
   }

   public boolean hasToken() {
      return this.token != null;
   }

   public TokenReviewSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public TokenReviewSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public TokenReviewSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public TokenReviewSpecFluent removeFromAdditionalProperties(Map map) {
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

   public TokenReviewSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            TokenReviewSpecFluent that = (TokenReviewSpecFluent)o;
            if (!Objects.equals(this.audiences, that.audiences)) {
               return false;
            } else if (!Objects.equals(this.token, that.token)) {
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
      return Objects.hash(new Object[]{this.audiences, this.token, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.audiences != null && !this.audiences.isEmpty()) {
         sb.append("audiences:");
         sb.append(this.audiences + ",");
      }

      if (this.token != null) {
         sb.append("token:");
         sb.append(this.token + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
