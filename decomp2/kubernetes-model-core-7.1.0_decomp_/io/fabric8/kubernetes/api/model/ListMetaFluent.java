package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ListMetaFluent extends BaseFluent {
   private String _continue;
   private Long remainingItemCount;
   private String resourceVersion;
   private String selfLink;
   private Map additionalProperties;

   public ListMetaFluent() {
   }

   public ListMetaFluent(ListMeta instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ListMeta instance) {
      instance = instance != null ? instance : new ListMeta();
      if (instance != null) {
         this.withContinue(instance.getContinue());
         this.withRemainingItemCount(instance.getRemainingItemCount());
         this.withResourceVersion(instance.getResourceVersion());
         this.withSelfLink(instance.getSelfLink());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContinue() {
      return this._continue;
   }

   public ListMetaFluent withContinue(String _continue) {
      this._continue = _continue;
      return this;
   }

   public boolean hasContinue() {
      return this._continue != null;
   }

   public Long getRemainingItemCount() {
      return this.remainingItemCount;
   }

   public ListMetaFluent withRemainingItemCount(Long remainingItemCount) {
      this.remainingItemCount = remainingItemCount;
      return this;
   }

   public boolean hasRemainingItemCount() {
      return this.remainingItemCount != null;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public ListMetaFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public String getSelfLink() {
      return this.selfLink;
   }

   public ListMetaFluent withSelfLink(String selfLink) {
      this.selfLink = selfLink;
      return this;
   }

   public boolean hasSelfLink() {
      return this.selfLink != null;
   }

   public ListMetaFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ListMetaFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ListMetaFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ListMetaFluent removeFromAdditionalProperties(Map map) {
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

   public ListMetaFluent withAdditionalProperties(Map additionalProperties) {
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
            ListMetaFluent that = (ListMetaFluent)o;
            if (!Objects.equals(this._continue, that._continue)) {
               return false;
            } else if (!Objects.equals(this.remainingItemCount, that.remainingItemCount)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
               return false;
            } else if (!Objects.equals(this.selfLink, that.selfLink)) {
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
      return Objects.hash(new Object[]{this._continue, this.remainingItemCount, this.resourceVersion, this.selfLink, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this._continue != null) {
         sb.append("_continue:");
         sb.append(this._continue + ",");
      }

      if (this.remainingItemCount != null) {
         sb.append("remainingItemCount:");
         sb.append(this.remainingItemCount + ",");
      }

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.selfLink != null) {
         sb.append("selfLink:");
         sb.append(this.selfLink + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
