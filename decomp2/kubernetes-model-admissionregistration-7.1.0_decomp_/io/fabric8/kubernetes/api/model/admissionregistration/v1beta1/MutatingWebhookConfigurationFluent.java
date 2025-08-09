package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class MutatingWebhookConfigurationFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList webhooks = new ArrayList();
   private Map additionalProperties;

   public MutatingWebhookConfigurationFluent() {
   }

   public MutatingWebhookConfigurationFluent(MutatingWebhookConfiguration instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MutatingWebhookConfiguration instance) {
      instance = instance != null ? instance : new MutatingWebhookConfiguration();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withWebhooks(instance.getWebhooks());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public MutatingWebhookConfigurationFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public MutatingWebhookConfigurationFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public MutatingWebhookConfigurationFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public MutatingWebhookConfigurationFluent addToWebhooks(int index, MutatingWebhook item) {
      if (this.webhooks == null) {
         this.webhooks = new ArrayList();
      }

      MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
      if (index >= 0 && index < this.webhooks.size()) {
         this._visitables.get("webhooks").add(index, builder);
         this.webhooks.add(index, builder);
      } else {
         this._visitables.get("webhooks").add(builder);
         this.webhooks.add(builder);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent setToWebhooks(int index, MutatingWebhook item) {
      if (this.webhooks == null) {
         this.webhooks = new ArrayList();
      }

      MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
      if (index >= 0 && index < this.webhooks.size()) {
         this._visitables.get("webhooks").set(index, builder);
         this.webhooks.set(index, builder);
      } else {
         this._visitables.get("webhooks").add(builder);
         this.webhooks.add(builder);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent addToWebhooks(MutatingWebhook... items) {
      if (this.webhooks == null) {
         this.webhooks = new ArrayList();
      }

      for(MutatingWebhook item : items) {
         MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
         this._visitables.get("webhooks").add(builder);
         this.webhooks.add(builder);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent addAllToWebhooks(Collection items) {
      if (this.webhooks == null) {
         this.webhooks = new ArrayList();
      }

      for(MutatingWebhook item : items) {
         MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
         this._visitables.get("webhooks").add(builder);
         this.webhooks.add(builder);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent removeFromWebhooks(MutatingWebhook... items) {
      if (this.webhooks == null) {
         return this;
      } else {
         for(MutatingWebhook item : items) {
            MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
            this._visitables.get("webhooks").remove(builder);
            this.webhooks.remove(builder);
         }

         return this;
      }
   }

   public MutatingWebhookConfigurationFluent removeAllFromWebhooks(Collection items) {
      if (this.webhooks == null) {
         return this;
      } else {
         for(MutatingWebhook item : items) {
            MutatingWebhookBuilder builder = new MutatingWebhookBuilder(item);
            this._visitables.get("webhooks").remove(builder);
            this.webhooks.remove(builder);
         }

         return this;
      }
   }

   public MutatingWebhookConfigurationFluent removeMatchingFromWebhooks(Predicate predicate) {
      if (this.webhooks == null) {
         return this;
      } else {
         Iterator<MutatingWebhookBuilder> each = this.webhooks.iterator();
         List visitables = this._visitables.get("webhooks");

         while(each.hasNext()) {
            MutatingWebhookBuilder builder = (MutatingWebhookBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildWebhooks() {
      return this.webhooks != null ? build(this.webhooks) : null;
   }

   public MutatingWebhook buildWebhook(int index) {
      return ((MutatingWebhookBuilder)this.webhooks.get(index)).build();
   }

   public MutatingWebhook buildFirstWebhook() {
      return ((MutatingWebhookBuilder)this.webhooks.get(0)).build();
   }

   public MutatingWebhook buildLastWebhook() {
      return ((MutatingWebhookBuilder)this.webhooks.get(this.webhooks.size() - 1)).build();
   }

   public MutatingWebhook buildMatchingWebhook(Predicate predicate) {
      for(MutatingWebhookBuilder item : this.webhooks) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingWebhook(Predicate predicate) {
      for(MutatingWebhookBuilder item : this.webhooks) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public MutatingWebhookConfigurationFluent withWebhooks(List webhooks) {
      if (this.webhooks != null) {
         this._visitables.get("webhooks").clear();
      }

      if (webhooks != null) {
         this.webhooks = new ArrayList();

         for(MutatingWebhook item : webhooks) {
            this.addToWebhooks(item);
         }
      } else {
         this.webhooks = null;
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent withWebhooks(MutatingWebhook... webhooks) {
      if (this.webhooks != null) {
         this.webhooks.clear();
         this._visitables.remove("webhooks");
      }

      if (webhooks != null) {
         for(MutatingWebhook item : webhooks) {
            this.addToWebhooks(item);
         }
      }

      return this;
   }

   public boolean hasWebhooks() {
      return this.webhooks != null && !this.webhooks.isEmpty();
   }

   public WebhooksNested addNewWebhook() {
      return new WebhooksNested(-1, (MutatingWebhook)null);
   }

   public WebhooksNested addNewWebhookLike(MutatingWebhook item) {
      return new WebhooksNested(-1, item);
   }

   public WebhooksNested setNewWebhookLike(int index, MutatingWebhook item) {
      return new WebhooksNested(index, item);
   }

   public WebhooksNested editWebhook(int index) {
      if (this.webhooks.size() <= index) {
         throw new RuntimeException("Can't edit webhooks. Index exceeds size.");
      } else {
         return this.setNewWebhookLike(index, this.buildWebhook(index));
      }
   }

   public WebhooksNested editFirstWebhook() {
      if (this.webhooks.size() == 0) {
         throw new RuntimeException("Can't edit first webhooks. The list is empty.");
      } else {
         return this.setNewWebhookLike(0, this.buildWebhook(0));
      }
   }

   public WebhooksNested editLastWebhook() {
      int index = this.webhooks.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last webhooks. The list is empty.");
      } else {
         return this.setNewWebhookLike(index, this.buildWebhook(index));
      }
   }

   public WebhooksNested editMatchingWebhook(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.webhooks.size(); ++i) {
         if (predicate.test((MutatingWebhookBuilder)this.webhooks.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching webhooks. No match found.");
      } else {
         return this.setNewWebhookLike(index, this.buildWebhook(index));
      }
   }

   public MutatingWebhookConfigurationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MutatingWebhookConfigurationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MutatingWebhookConfigurationFluent removeFromAdditionalProperties(Map map) {
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

   public MutatingWebhookConfigurationFluent withAdditionalProperties(Map additionalProperties) {
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
            MutatingWebhookConfigurationFluent that = (MutatingWebhookConfigurationFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.webhooks, that.webhooks)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.webhooks, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.webhooks != null && !this.webhooks.isEmpty()) {
         sb.append("webhooks:");
         sb.append(this.webhooks + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookConfigurationFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class WebhooksNested extends MutatingWebhookFluent implements Nested {
      MutatingWebhookBuilder builder;
      int index;

      WebhooksNested(int index, MutatingWebhook item) {
         this.index = index;
         this.builder = new MutatingWebhookBuilder(this, item);
      }

      public Object and() {
         return MutatingWebhookConfigurationFluent.this.setToWebhooks(this.index, this.builder.build());
      }

      public Object endWebhook() {
         return this.and();
      }
   }
}
