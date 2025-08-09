package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomResourceConversionFluent extends BaseFluent {
   private String strategy;
   private WebhookConversionBuilder webhook;
   private Map additionalProperties;

   public CustomResourceConversionFluent() {
   }

   public CustomResourceConversionFluent(CustomResourceConversion instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CustomResourceConversion instance) {
      instance = instance != null ? instance : new CustomResourceConversion();
      if (instance != null) {
         this.withStrategy(instance.getStrategy());
         this.withWebhook(instance.getWebhook());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

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

   public WebhookConversion buildWebhook() {
      return this.webhook != null ? this.webhook.build() : null;
   }

   public CustomResourceConversionFluent withWebhook(WebhookConversion webhook) {
      this._visitables.remove("webhook");
      if (webhook != null) {
         this.webhook = new WebhookConversionBuilder(webhook);
         this._visitables.get("webhook").add(this.webhook);
      } else {
         this.webhook = null;
         this._visitables.get("webhook").remove(this.webhook);
      }

      return this;
   }

   public boolean hasWebhook() {
      return this.webhook != null;
   }

   public WebhookNested withNewWebhook() {
      return new WebhookNested((WebhookConversion)null);
   }

   public WebhookNested withNewWebhookLike(WebhookConversion item) {
      return new WebhookNested(item);
   }

   public WebhookNested editWebhook() {
      return this.withNewWebhookLike((WebhookConversion)Optional.ofNullable(this.buildWebhook()).orElse((Object)null));
   }

   public WebhookNested editOrNewWebhook() {
      return this.withNewWebhookLike((WebhookConversion)Optional.ofNullable(this.buildWebhook()).orElse((new WebhookConversionBuilder()).build()));
   }

   public WebhookNested editOrNewWebhookLike(WebhookConversion item) {
      return this.withNewWebhookLike((WebhookConversion)Optional.ofNullable(this.buildWebhook()).orElse(item));
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
            if (!Objects.equals(this.strategy, that.strategy)) {
               return false;
            } else if (!Objects.equals(this.webhook, that.webhook)) {
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
      return Objects.hash(new Object[]{this.strategy, this.webhook, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.strategy != null) {
         sb.append("strategy:");
         sb.append(this.strategy + ",");
      }

      if (this.webhook != null) {
         sb.append("webhook:");
         sb.append(this.webhook + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class WebhookNested extends WebhookConversionFluent implements Nested {
      WebhookConversionBuilder builder;

      WebhookNested(WebhookConversion item) {
         this.builder = new WebhookConversionBuilder(this, item);
      }

      public Object and() {
         return CustomResourceConversionFluent.this.withWebhook(this.builder.build());
      }

      public Object endWebhook() {
         return this.and();
      }
   }
}
