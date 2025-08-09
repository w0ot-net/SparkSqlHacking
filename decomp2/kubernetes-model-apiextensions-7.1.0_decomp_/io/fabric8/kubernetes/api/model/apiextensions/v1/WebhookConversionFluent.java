package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class WebhookConversionFluent extends BaseFluent {
   private WebhookClientConfigBuilder clientConfig;
   private List conversionReviewVersions = new ArrayList();
   private Map additionalProperties;

   public WebhookConversionFluent() {
   }

   public WebhookConversionFluent(WebhookConversion instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(WebhookConversion instance) {
      instance = instance != null ? instance : new WebhookConversion();
      if (instance != null) {
         this.withClientConfig(instance.getClientConfig());
         this.withConversionReviewVersions(instance.getConversionReviewVersions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public WebhookClientConfig buildClientConfig() {
      return this.clientConfig != null ? this.clientConfig.build() : null;
   }

   public WebhookConversionFluent withClientConfig(WebhookClientConfig clientConfig) {
      this._visitables.remove("clientConfig");
      if (clientConfig != null) {
         this.clientConfig = new WebhookClientConfigBuilder(clientConfig);
         this._visitables.get("clientConfig").add(this.clientConfig);
      } else {
         this.clientConfig = null;
         this._visitables.get("clientConfig").remove(this.clientConfig);
      }

      return this;
   }

   public boolean hasClientConfig() {
      return this.clientConfig != null;
   }

   public ClientConfigNested withNewClientConfig() {
      return new ClientConfigNested((WebhookClientConfig)null);
   }

   public ClientConfigNested withNewClientConfigLike(WebhookClientConfig item) {
      return new ClientConfigNested(item);
   }

   public ClientConfigNested editClientConfig() {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse((Object)null));
   }

   public ClientConfigNested editOrNewClientConfig() {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse((new WebhookClientConfigBuilder()).build()));
   }

   public ClientConfigNested editOrNewClientConfigLike(WebhookClientConfig item) {
      return this.withNewClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildClientConfig()).orElse(item));
   }

   public WebhookConversionFluent addToConversionReviewVersions(int index, String item) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      this.conversionReviewVersions.add(index, item);
      return this;
   }

   public WebhookConversionFluent setToConversionReviewVersions(int index, String item) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      this.conversionReviewVersions.set(index, item);
      return this;
   }

   public WebhookConversionFluent addToConversionReviewVersions(String... items) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.conversionReviewVersions.add(item);
      }

      return this;
   }

   public WebhookConversionFluent addAllToConversionReviewVersions(Collection items) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.conversionReviewVersions.add(item);
      }

      return this;
   }

   public WebhookConversionFluent removeFromConversionReviewVersions(String... items) {
      if (this.conversionReviewVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.conversionReviewVersions.remove(item);
         }

         return this;
      }
   }

   public WebhookConversionFluent removeAllFromConversionReviewVersions(Collection items) {
      if (this.conversionReviewVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.conversionReviewVersions.remove(item);
         }

         return this;
      }
   }

   public List getConversionReviewVersions() {
      return this.conversionReviewVersions;
   }

   public String getConversionReviewVersion(int index) {
      return (String)this.conversionReviewVersions.get(index);
   }

   public String getFirstConversionReviewVersion() {
      return (String)this.conversionReviewVersions.get(0);
   }

   public String getLastConversionReviewVersion() {
      return (String)this.conversionReviewVersions.get(this.conversionReviewVersions.size() - 1);
   }

   public String getMatchingConversionReviewVersion(Predicate predicate) {
      for(String item : this.conversionReviewVersions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingConversionReviewVersion(Predicate predicate) {
      for(String item : this.conversionReviewVersions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public WebhookConversionFluent withConversionReviewVersions(List conversionReviewVersions) {
      if (conversionReviewVersions != null) {
         this.conversionReviewVersions = new ArrayList();

         for(String item : conversionReviewVersions) {
            this.addToConversionReviewVersions(item);
         }
      } else {
         this.conversionReviewVersions = null;
      }

      return this;
   }

   public WebhookConversionFluent withConversionReviewVersions(String... conversionReviewVersions) {
      if (this.conversionReviewVersions != null) {
         this.conversionReviewVersions.clear();
         this._visitables.remove("conversionReviewVersions");
      }

      if (conversionReviewVersions != null) {
         for(String item : conversionReviewVersions) {
            this.addToConversionReviewVersions(item);
         }
      }

      return this;
   }

   public boolean hasConversionReviewVersions() {
      return this.conversionReviewVersions != null && !this.conversionReviewVersions.isEmpty();
   }

   public WebhookConversionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public WebhookConversionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public WebhookConversionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public WebhookConversionFluent removeFromAdditionalProperties(Map map) {
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

   public WebhookConversionFluent withAdditionalProperties(Map additionalProperties) {
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
            WebhookConversionFluent that = (WebhookConversionFluent)o;
            if (!Objects.equals(this.clientConfig, that.clientConfig)) {
               return false;
            } else if (!Objects.equals(this.conversionReviewVersions, that.conversionReviewVersions)) {
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
      return Objects.hash(new Object[]{this.clientConfig, this.conversionReviewVersions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clientConfig != null) {
         sb.append("clientConfig:");
         sb.append(this.clientConfig + ",");
      }

      if (this.conversionReviewVersions != null && !this.conversionReviewVersions.isEmpty()) {
         sb.append("conversionReviewVersions:");
         sb.append(this.conversionReviewVersions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClientConfigNested extends WebhookClientConfigFluent implements Nested {
      WebhookClientConfigBuilder builder;

      ClientConfigNested(WebhookClientConfig item) {
         this.builder = new WebhookClientConfigBuilder(this, item);
      }

      public Object and() {
         return WebhookConversionFluent.this.withClientConfig(this.builder.build());
      }

      public Object endClientConfig() {
         return this.and();
      }
   }
}
