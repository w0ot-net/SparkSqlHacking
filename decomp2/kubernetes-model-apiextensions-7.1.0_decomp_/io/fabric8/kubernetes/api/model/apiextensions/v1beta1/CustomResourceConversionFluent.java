package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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

public class CustomResourceConversionFluent extends BaseFluent {
   private List conversionReviewVersions = new ArrayList();
   private String strategy;
   private WebhookClientConfigBuilder webhookClientConfig;
   private Map additionalProperties;

   public CustomResourceConversionFluent() {
   }

   public CustomResourceConversionFluent(CustomResourceConversion instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceConversion instance) {
      instance = instance != null ? instance : new CustomResourceConversion();
      if (instance != null) {
         this.withConversionReviewVersions(instance.getConversionReviewVersions());
         this.withStrategy(instance.getStrategy());
         this.withWebhookClientConfig(instance.getWebhookClientConfig());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CustomResourceConversionFluent addToConversionReviewVersions(int index, String item) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      this.conversionReviewVersions.add(index, item);
      return this;
   }

   public CustomResourceConversionFluent setToConversionReviewVersions(int index, String item) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      this.conversionReviewVersions.set(index, item);
      return this;
   }

   public CustomResourceConversionFluent addToConversionReviewVersions(String... items) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.conversionReviewVersions.add(item);
      }

      return this;
   }

   public CustomResourceConversionFluent addAllToConversionReviewVersions(Collection items) {
      if (this.conversionReviewVersions == null) {
         this.conversionReviewVersions = new ArrayList();
      }

      for(String item : items) {
         this.conversionReviewVersions.add(item);
      }

      return this;
   }

   public CustomResourceConversionFluent removeFromConversionReviewVersions(String... items) {
      if (this.conversionReviewVersions == null) {
         return this;
      } else {
         for(String item : items) {
            this.conversionReviewVersions.remove(item);
         }

         return this;
      }
   }

   public CustomResourceConversionFluent removeAllFromConversionReviewVersions(Collection items) {
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

   public CustomResourceConversionFluent withConversionReviewVersions(List conversionReviewVersions) {
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

   public CustomResourceConversionFluent withConversionReviewVersions(String... conversionReviewVersions) {
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

   public String getStrategy() {
      return this.strategy;
   }

   public CustomResourceConversionFluent withStrategy(String strategy) {
      this.strategy = strategy;
      return this;
   }

   public boolean hasStrategy() {
      return this.strategy != null;
   }

   public WebhookClientConfig buildWebhookClientConfig() {
      return this.webhookClientConfig != null ? this.webhookClientConfig.build() : null;
   }

   public CustomResourceConversionFluent withWebhookClientConfig(WebhookClientConfig webhookClientConfig) {
      this._visitables.remove("webhookClientConfig");
      if (webhookClientConfig != null) {
         this.webhookClientConfig = new WebhookClientConfigBuilder(webhookClientConfig);
         this._visitables.get("webhookClientConfig").add(this.webhookClientConfig);
      } else {
         this.webhookClientConfig = null;
         this._visitables.get("webhookClientConfig").remove(this.webhookClientConfig);
      }

      return this;
   }

   public boolean hasWebhookClientConfig() {
      return this.webhookClientConfig != null;
   }

   public WebhookClientConfigNested withNewWebhookClientConfig() {
      return new WebhookClientConfigNested((WebhookClientConfig)null);
   }

   public WebhookClientConfigNested withNewWebhookClientConfigLike(WebhookClientConfig item) {
      return new WebhookClientConfigNested(item);
   }

   public WebhookClientConfigNested editWebhookClientConfig() {
      return this.withNewWebhookClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildWebhookClientConfig()).orElse((Object)null));
   }

   public WebhookClientConfigNested editOrNewWebhookClientConfig() {
      return this.withNewWebhookClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildWebhookClientConfig()).orElse((new WebhookClientConfigBuilder()).build()));
   }

   public WebhookClientConfigNested editOrNewWebhookClientConfigLike(WebhookClientConfig item) {
      return this.withNewWebhookClientConfigLike((WebhookClientConfig)Optional.ofNullable(this.buildWebhookClientConfig()).orElse(item));
   }

   public CustomResourceConversionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CustomResourceConversionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CustomResourceConversionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CustomResourceConversionFluent removeFromAdditionalProperties(Map map) {
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

   public CustomResourceConversionFluent withAdditionalProperties(Map additionalProperties) {
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
            CustomResourceConversionFluent that = (CustomResourceConversionFluent)o;
            if (!Objects.equals(this.conversionReviewVersions, that.conversionReviewVersions)) {
               return false;
            } else if (!Objects.equals(this.strategy, that.strategy)) {
               return false;
            } else if (!Objects.equals(this.webhookClientConfig, that.webhookClientConfig)) {
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
      return Objects.hash(new Object[]{this.conversionReviewVersions, this.strategy, this.webhookClientConfig, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conversionReviewVersions != null && !this.conversionReviewVersions.isEmpty()) {
         sb.append("conversionReviewVersions:");
         sb.append(this.conversionReviewVersions + ",");
      }

      if (this.strategy != null) {
         sb.append("strategy:");
         sb.append(this.strategy + ",");
      }

      if (this.webhookClientConfig != null) {
         sb.append("webhookClientConfig:");
         sb.append(this.webhookClientConfig + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class WebhookClientConfigNested extends WebhookClientConfigFluent implements Nested {
      WebhookClientConfigBuilder builder;

      WebhookClientConfigNested(WebhookClientConfig item) {
         this.builder = new WebhookClientConfigBuilder(this, item);
      }

      public Object and() {
         return CustomResourceConversionFluent.this.withWebhookClientConfig(this.builder.build());
      }

      public Object endWebhookClientConfig() {
         return this.and();
      }
   }
}
