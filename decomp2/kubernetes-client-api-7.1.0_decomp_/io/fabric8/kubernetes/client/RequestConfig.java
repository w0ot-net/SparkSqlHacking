package io.fabric8.kubernetes.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestConfig {
   private String impersonateUsername;
   private String[] impersonateGroups = new String[0];
   private Map impersonateExtras = new HashMap();
   private Integer watchReconnectInterval = 1000;
   private Integer watchReconnectLimit = -1;
   private Integer uploadRequestTimeout = 120000;
   private Integer requestRetryBackoffLimit;
   private Integer requestRetryBackoffInterval;
   private Integer requestTimeout;
   private Long scaleTimeout;
   private Integer loggingInterval;

   RequestConfig() {
      this.requestRetryBackoffLimit = Config.DEFAULT_REQUEST_RETRY_BACKOFFLIMIT;
      this.requestRetryBackoffInterval = Config.DEFAULT_REQUEST_RETRY_BACKOFFINTERVAL;
      this.requestTimeout = 10000;
      this.scaleTimeout = Config.DEFAULT_SCALE_TIMEOUT;
      this.loggingInterval = 20000;
   }

   public RequestConfig(Integer watchReconnectLimit, Integer watchReconnectInterval, Integer requestTimeout, Long scaleTimeout, Integer loggingInterval, Integer requestRetryBackoffLimit, Integer requestRetryBackoffInterval, Integer uploadRequestTimeout) {
      this.requestRetryBackoffLimit = Config.DEFAULT_REQUEST_RETRY_BACKOFFLIMIT;
      this.requestRetryBackoffInterval = Config.DEFAULT_REQUEST_RETRY_BACKOFFINTERVAL;
      this.requestTimeout = 10000;
      this.scaleTimeout = Config.DEFAULT_SCALE_TIMEOUT;
      this.loggingInterval = 20000;
      this.watchReconnectLimit = watchReconnectLimit;
      this.watchReconnectInterval = watchReconnectInterval;
      this.requestTimeout = requestTimeout;
      this.scaleTimeout = scaleTimeout;
      this.loggingInterval = loggingInterval;
      this.requestRetryBackoffLimit = requestRetryBackoffLimit;
      this.requestRetryBackoffInterval = requestRetryBackoffInterval;
      this.uploadRequestTimeout = uploadRequestTimeout;
   }

   public Integer getWatchReconnectInterval() {
      return this.watchReconnectInterval;
   }

   public void setWatchReconnectInterval(Integer watchReconnectInterval) {
      this.watchReconnectInterval = watchReconnectInterval;
   }

   public Integer getWatchReconnectLimit() {
      return this.watchReconnectLimit;
   }

   public void setWatchReconnectLimit(Integer watchReconnectLimit) {
      this.watchReconnectLimit = watchReconnectLimit;
   }

   public Integer getRequestTimeout() {
      return this.requestTimeout;
   }

   public void setRequestTimeout(Integer requestTimeout) {
      this.requestTimeout = requestTimeout;
   }

   public Integer getRequestRetryBackoffLimit() {
      return this.requestRetryBackoffLimit;
   }

   public void setRequestRetryBackoffLimit(Integer requestRetryBackoffLimit) {
      this.requestRetryBackoffLimit = requestRetryBackoffLimit;
   }

   public Integer getRequestRetryBackoffInterval() {
      return this.requestRetryBackoffInterval;
   }

   public void setRequestRetryBackoffInterval(Integer requestRetryBackoffInterval) {
      this.requestRetryBackoffInterval = requestRetryBackoffInterval;
   }

   public Integer getUploadRequestTimeout() {
      return this.uploadRequestTimeout;
   }

   public void setUploadRequestTimeout(Integer uploadRequestTimeout) {
      this.uploadRequestTimeout = uploadRequestTimeout;
   }

   public Long getScaleTimeout() {
      return this.scaleTimeout;
   }

   public void setScaleTimeout(Long scaleTimeout) {
      this.scaleTimeout = scaleTimeout;
   }

   public Integer getLoggingInterval() {
      return this.loggingInterval;
   }

   public void setLoggingInterval(Integer loggingInterval) {
      this.loggingInterval = loggingInterval;
   }

   public void setImpersonateUsername(String impersonateUsername) {
      this.impersonateUsername = impersonateUsername;
   }

   public String getImpersonateUsername() {
      return this.impersonateUsername;
   }

   public void setImpersonateGroups(String... impersonateGroups) {
      this.impersonateGroups = impersonateGroups == null ? new String[0] : (String[])Arrays.copyOf(impersonateGroups, impersonateGroups.length);
   }

   public String[] getImpersonateGroups() {
      return this.impersonateGroups;
   }

   public void setImpersonateExtras(Map impersonateExtras) {
      this.impersonateExtras = new HashMap(impersonateExtras);
   }

   public Map getImpersonateExtras() {
      return Collections.unmodifiableMap(this.impersonateExtras);
   }
}
