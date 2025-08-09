package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ListOptionsFluent extends BaseFluent {
   private Boolean allowWatchBookmarks;
   private String apiVersion;
   private String _continue;
   private String fieldSelector;
   private String kind;
   private String labelSelector;
   private Long limit;
   private String resourceVersion;
   private String resourceVersionMatch;
   private Boolean sendInitialEvents;
   private Long timeoutSeconds;
   private Boolean watch;
   private Map additionalProperties;

   public ListOptionsFluent() {
   }

   public ListOptionsFluent(ListOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ListOptions instance) {
      instance = instance != null ? instance : new ListOptions();
      if (instance != null) {
         this.withAllowWatchBookmarks(instance.getAllowWatchBookmarks());
         this.withApiVersion(instance.getApiVersion());
         this.withContinue(instance.getContinue());
         this.withFieldSelector(instance.getFieldSelector());
         this.withKind(instance.getKind());
         this.withLabelSelector(instance.getLabelSelector());
         this.withLimit(instance.getLimit());
         this.withResourceVersion(instance.getResourceVersion());
         this.withResourceVersionMatch(instance.getResourceVersionMatch());
         this.withSendInitialEvents(instance.getSendInitialEvents());
         this.withTimeoutSeconds(instance.getTimeoutSeconds());
         this.withWatch(instance.getWatch());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowWatchBookmarks() {
      return this.allowWatchBookmarks;
   }

   public ListOptionsFluent withAllowWatchBookmarks(Boolean allowWatchBookmarks) {
      this.allowWatchBookmarks = allowWatchBookmarks;
      return this;
   }

   public boolean hasAllowWatchBookmarks() {
      return this.allowWatchBookmarks != null;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ListOptionsFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getContinue() {
      return this._continue;
   }

   public ListOptionsFluent withContinue(String _continue) {
      this._continue = _continue;
      return this;
   }

   public boolean hasContinue() {
      return this._continue != null;
   }

   public String getFieldSelector() {
      return this.fieldSelector;
   }

   public ListOptionsFluent withFieldSelector(String fieldSelector) {
      this.fieldSelector = fieldSelector;
      return this;
   }

   public boolean hasFieldSelector() {
      return this.fieldSelector != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ListOptionsFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getLabelSelector() {
      return this.labelSelector;
   }

   public ListOptionsFluent withLabelSelector(String labelSelector) {
      this.labelSelector = labelSelector;
      return this;
   }

   public boolean hasLabelSelector() {
      return this.labelSelector != null;
   }

   public Long getLimit() {
      return this.limit;
   }

   public ListOptionsFluent withLimit(Long limit) {
      this.limit = limit;
      return this;
   }

   public boolean hasLimit() {
      return this.limit != null;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public ListOptionsFluent withResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
      return this;
   }

   public boolean hasResourceVersion() {
      return this.resourceVersion != null;
   }

   public String getResourceVersionMatch() {
      return this.resourceVersionMatch;
   }

   public ListOptionsFluent withResourceVersionMatch(String resourceVersionMatch) {
      this.resourceVersionMatch = resourceVersionMatch;
      return this;
   }

   public boolean hasResourceVersionMatch() {
      return this.resourceVersionMatch != null;
   }

   public Boolean getSendInitialEvents() {
      return this.sendInitialEvents;
   }

   public ListOptionsFluent withSendInitialEvents(Boolean sendInitialEvents) {
      this.sendInitialEvents = sendInitialEvents;
      return this;
   }

   public boolean hasSendInitialEvents() {
      return this.sendInitialEvents != null;
   }

   public Long getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   public ListOptionsFluent withTimeoutSeconds(Long timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
      return this;
   }

   public boolean hasTimeoutSeconds() {
      return this.timeoutSeconds != null;
   }

   public Boolean getWatch() {
      return this.watch;
   }

   public ListOptionsFluent withWatch(Boolean watch) {
      this.watch = watch;
      return this;
   }

   public boolean hasWatch() {
      return this.watch != null;
   }

   public ListOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ListOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ListOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ListOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public ListOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            ListOptionsFluent that = (ListOptionsFluent)o;
            if (!Objects.equals(this.allowWatchBookmarks, that.allowWatchBookmarks)) {
               return false;
            } else if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this._continue, that._continue)) {
               return false;
            } else if (!Objects.equals(this.fieldSelector, that.fieldSelector)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.labelSelector, that.labelSelector)) {
               return false;
            } else if (!Objects.equals(this.limit, that.limit)) {
               return false;
            } else if (!Objects.equals(this.resourceVersion, that.resourceVersion)) {
               return false;
            } else if (!Objects.equals(this.resourceVersionMatch, that.resourceVersionMatch)) {
               return false;
            } else if (!Objects.equals(this.sendInitialEvents, that.sendInitialEvents)) {
               return false;
            } else if (!Objects.equals(this.timeoutSeconds, that.timeoutSeconds)) {
               return false;
            } else if (!Objects.equals(this.watch, that.watch)) {
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
      return Objects.hash(new Object[]{this.allowWatchBookmarks, this.apiVersion, this._continue, this.fieldSelector, this.kind, this.labelSelector, this.limit, this.resourceVersion, this.resourceVersionMatch, this.sendInitialEvents, this.timeoutSeconds, this.watch, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowWatchBookmarks != null) {
         sb.append("allowWatchBookmarks:");
         sb.append(this.allowWatchBookmarks + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this._continue != null) {
         sb.append("_continue:");
         sb.append(this._continue + ",");
      }

      if (this.fieldSelector != null) {
         sb.append("fieldSelector:");
         sb.append(this.fieldSelector + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.labelSelector != null) {
         sb.append("labelSelector:");
         sb.append(this.labelSelector + ",");
      }

      if (this.limit != null) {
         sb.append("limit:");
         sb.append(this.limit + ",");
      }

      if (this.resourceVersion != null) {
         sb.append("resourceVersion:");
         sb.append(this.resourceVersion + ",");
      }

      if (this.resourceVersionMatch != null) {
         sb.append("resourceVersionMatch:");
         sb.append(this.resourceVersionMatch + ",");
      }

      if (this.sendInitialEvents != null) {
         sb.append("sendInitialEvents:");
         sb.append(this.sendInitialEvents + ",");
      }

      if (this.timeoutSeconds != null) {
         sb.append("timeoutSeconds:");
         sb.append(this.timeoutSeconds + ",");
      }

      if (this.watch != null) {
         sb.append("watch:");
         sb.append(this.watch + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ListOptionsFluent withAllowWatchBookmarks() {
      return this.withAllowWatchBookmarks(true);
   }

   public ListOptionsFluent withSendInitialEvents() {
      return this.withSendInitialEvents(true);
   }

   public ListOptionsFluent withWatch() {
      return this.withWatch(true);
   }
}
