package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestConfigFluent extends BaseFluent {
   private String impersonateUsername;
   private List impersonateGroups;
   private Map impersonateExtras;
   private Integer watchReconnectInterval;
   private Integer watchReconnectLimit;
   private Integer uploadRequestTimeout;
   private Integer requestRetryBackoffLimit;
   private Integer requestRetryBackoffInterval;
   private Integer requestTimeout;
   private Long scaleTimeout;
   private Integer loggingInterval;

   public RequestConfigFluent() {
   }

   public RequestConfigFluent(RequestConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RequestConfig instance) {
      instance = instance != null ? instance : new RequestConfig();
      if (instance != null) {
         this.withWatchReconnectLimit(instance.getWatchReconnectLimit());
         this.withWatchReconnectInterval(instance.getWatchReconnectInterval());
         this.withRequestTimeout(instance.getRequestTimeout());
         this.withScaleTimeout(instance.getScaleTimeout());
         this.withLoggingInterval(instance.getLoggingInterval());
         this.withRequestRetryBackoffLimit(instance.getRequestRetryBackoffLimit());
         this.withRequestRetryBackoffInterval(instance.getRequestRetryBackoffInterval());
         this.withUploadRequestTimeout(instance.getUploadRequestTimeout());
         this.withImpersonateUsername(instance.getImpersonateUsername());
         this.withImpersonateGroups(instance.getImpersonateGroups());
         this.withImpersonateExtras(instance.getImpersonateExtras());
      }

   }

   public String getImpersonateUsername() {
      return this.impersonateUsername;
   }

   public RequestConfigFluent withImpersonateUsername(String impersonateUsername) {
      this.impersonateUsername = impersonateUsername;
      return this;
   }

   public boolean hasImpersonateUsername() {
      return this.impersonateUsername != null;
   }

   public RequestConfigFluent withImpersonateGroups(String... impersonateGroups) {
      if (this.impersonateGroups != null) {
         this.impersonateGroups.clear();
         this._visitables.remove("impersonateGroups");
      }

      if (impersonateGroups != null) {
         for(String item : impersonateGroups) {
            this.addToImpersonateGroups(item);
         }
      }

      return this;
   }

   public String[] getImpersonateGroups() {
      int size = this.impersonateGroups != null ? this.impersonateGroups.size() : 0;
      String[] result = new String[size];
      if (size == 0) {
         return result;
      } else {
         int index = 0;

         for(String item : this.impersonateGroups) {
            result[index++] = item;
         }

         return result;
      }
   }

   public RequestConfigFluent addToImpersonateGroups(int index, String item) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      this.impersonateGroups.add(index, item);
      return this;
   }

   public RequestConfigFluent setToImpersonateGroups(int index, String item) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      this.impersonateGroups.set(index, item);
      return this;
   }

   public RequestConfigFluent addToImpersonateGroups(String... items) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      for(String item : items) {
         this.impersonateGroups.add(item);
      }

      return this;
   }

   public RequestConfigFluent addAllToImpersonateGroups(Collection items) {
      if (this.impersonateGroups == null) {
         this.impersonateGroups = new ArrayList();
      }

      for(String item : items) {
         this.impersonateGroups.add(item);
      }

      return this;
   }

   public RequestConfigFluent removeFromImpersonateGroups(String... items) {
      if (this.impersonateGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.impersonateGroups.remove(item);
         }

         return this;
      }
   }

   public RequestConfigFluent removeAllFromImpersonateGroups(Collection items) {
      if (this.impersonateGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.impersonateGroups.remove(item);
         }

         return this;
      }
   }

   public boolean hasImpersonateGroups() {
      return this.impersonateGroups != null && !this.impersonateGroups.isEmpty();
   }

   public RequestConfigFluent addToImpersonateExtras(String key, List value) {
      if (this.impersonateExtras == null && key != null && value != null) {
         this.impersonateExtras = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.impersonateExtras.put(key, value);
      }

      return this;
   }

   public RequestConfigFluent addToImpersonateExtras(Map map) {
      if (this.impersonateExtras == null && map != null) {
         this.impersonateExtras = new LinkedHashMap();
      }

      if (map != null) {
         this.impersonateExtras.putAll(map);
      }

      return this;
   }

   public RequestConfigFluent removeFromImpersonateExtras(String key) {
      if (this.impersonateExtras == null) {
         return this;
      } else {
         if (key != null && this.impersonateExtras != null) {
            this.impersonateExtras.remove(key);
         }

         return this;
      }
   }

   public RequestConfigFluent removeFromImpersonateExtras(Map map) {
      if (this.impersonateExtras == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.impersonateExtras != null) {
                  this.impersonateExtras.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getImpersonateExtras() {
      return this.impersonateExtras;
   }

   public RequestConfigFluent withImpersonateExtras(Map impersonateExtras) {
      if (impersonateExtras == null) {
         this.impersonateExtras = null;
      } else {
         this.impersonateExtras = new LinkedHashMap(impersonateExtras);
      }

      return this;
   }

   public boolean hasImpersonateExtras() {
      return this.impersonateExtras != null;
   }

   public Integer getWatchReconnectInterval() {
      return this.watchReconnectInterval;
   }

   public RequestConfigFluent withWatchReconnectInterval(Integer watchReconnectInterval) {
      this.watchReconnectInterval = watchReconnectInterval;
      return this;
   }

   public boolean hasWatchReconnectInterval() {
      return this.watchReconnectInterval != null;
   }

   public Integer getWatchReconnectLimit() {
      return this.watchReconnectLimit;
   }

   public RequestConfigFluent withWatchReconnectLimit(Integer watchReconnectLimit) {
      this.watchReconnectLimit = watchReconnectLimit;
      return this;
   }

   public boolean hasWatchReconnectLimit() {
      return this.watchReconnectLimit != null;
   }

   public Integer getUploadRequestTimeout() {
      return this.uploadRequestTimeout;
   }

   public RequestConfigFluent withUploadRequestTimeout(Integer uploadRequestTimeout) {
      this.uploadRequestTimeout = uploadRequestTimeout;
      return this;
   }

   public boolean hasUploadRequestTimeout() {
      return this.uploadRequestTimeout != null;
   }

   public Integer getRequestRetryBackoffLimit() {
      return this.requestRetryBackoffLimit;
   }

   public RequestConfigFluent withRequestRetryBackoffLimit(Integer requestRetryBackoffLimit) {
      this.requestRetryBackoffLimit = requestRetryBackoffLimit;
      return this;
   }

   public boolean hasRequestRetryBackoffLimit() {
      return this.requestRetryBackoffLimit != null;
   }

   public Integer getRequestRetryBackoffInterval() {
      return this.requestRetryBackoffInterval;
   }

   public RequestConfigFluent withRequestRetryBackoffInterval(Integer requestRetryBackoffInterval) {
      this.requestRetryBackoffInterval = requestRetryBackoffInterval;
      return this;
   }

   public boolean hasRequestRetryBackoffInterval() {
      return this.requestRetryBackoffInterval != null;
   }

   public Integer getRequestTimeout() {
      return this.requestTimeout;
   }

   public RequestConfigFluent withRequestTimeout(Integer requestTimeout) {
      this.requestTimeout = requestTimeout;
      return this;
   }

   public boolean hasRequestTimeout() {
      return this.requestTimeout != null;
   }

   public Long getScaleTimeout() {
      return this.scaleTimeout;
   }

   public RequestConfigFluent withScaleTimeout(Long scaleTimeout) {
      this.scaleTimeout = scaleTimeout;
      return this;
   }

   public boolean hasScaleTimeout() {
      return this.scaleTimeout != null;
   }

   public Integer getLoggingInterval() {
      return this.loggingInterval;
   }

   public RequestConfigFluent withLoggingInterval(Integer loggingInterval) {
      this.loggingInterval = loggingInterval;
      return this;
   }

   public boolean hasLoggingInterval() {
      return this.loggingInterval != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            RequestConfigFluent that = (RequestConfigFluent)o;
            if (!Objects.equals(this.impersonateUsername, that.impersonateUsername)) {
               return false;
            } else if (!Objects.equals(this.impersonateGroups, that.impersonateGroups)) {
               return false;
            } else if (!Objects.equals(this.impersonateExtras, that.impersonateExtras)) {
               return false;
            } else if (!Objects.equals(this.watchReconnectInterval, that.watchReconnectInterval)) {
               return false;
            } else if (!Objects.equals(this.watchReconnectLimit, that.watchReconnectLimit)) {
               return false;
            } else if (!Objects.equals(this.uploadRequestTimeout, that.uploadRequestTimeout)) {
               return false;
            } else if (!Objects.equals(this.requestRetryBackoffLimit, that.requestRetryBackoffLimit)) {
               return false;
            } else if (!Objects.equals(this.requestRetryBackoffInterval, that.requestRetryBackoffInterval)) {
               return false;
            } else if (!Objects.equals(this.requestTimeout, that.requestTimeout)) {
               return false;
            } else if (!Objects.equals(this.scaleTimeout, that.scaleTimeout)) {
               return false;
            } else {
               return Objects.equals(this.loggingInterval, that.loggingInterval);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.impersonateUsername, this.impersonateGroups, this.impersonateExtras, this.watchReconnectInterval, this.watchReconnectLimit, this.uploadRequestTimeout, this.requestRetryBackoffLimit, this.requestRetryBackoffInterval, this.requestTimeout, this.scaleTimeout, this.loggingInterval, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.impersonateUsername != null) {
         sb.append("impersonateUsername:");
         sb.append(this.impersonateUsername + ",");
      }

      if (this.impersonateGroups != null && !this.impersonateGroups.isEmpty()) {
         sb.append("impersonateGroups:");
         sb.append(this.impersonateGroups + ",");
      }

      if (this.impersonateExtras != null && !this.impersonateExtras.isEmpty()) {
         sb.append("impersonateExtras:");
         sb.append(this.impersonateExtras + ",");
      }

      if (this.watchReconnectInterval != null) {
         sb.append("watchReconnectInterval:");
         sb.append(this.watchReconnectInterval + ",");
      }

      if (this.watchReconnectLimit != null) {
         sb.append("watchReconnectLimit:");
         sb.append(this.watchReconnectLimit + ",");
      }

      if (this.uploadRequestTimeout != null) {
         sb.append("uploadRequestTimeout:");
         sb.append(this.uploadRequestTimeout + ",");
      }

      if (this.requestRetryBackoffLimit != null) {
         sb.append("requestRetryBackoffLimit:");
         sb.append(this.requestRetryBackoffLimit + ",");
      }

      if (this.requestRetryBackoffInterval != null) {
         sb.append("requestRetryBackoffInterval:");
         sb.append(this.requestRetryBackoffInterval + ",");
      }

      if (this.requestTimeout != null) {
         sb.append("requestTimeout:");
         sb.append(this.requestTimeout + ",");
      }

      if (this.scaleTimeout != null) {
         sb.append("scaleTimeout:");
         sb.append(this.scaleTimeout + ",");
      }

      if (this.loggingInterval != null) {
         sb.append("loggingInterval:");
         sb.append(this.loggingInterval);
      }

      sb.append("}");
      return sb.toString();
   }
}
