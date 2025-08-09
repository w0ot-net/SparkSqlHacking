package org.glassfish.jersey.server.internal.monitoring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.server.monitoring.ResponseStatistics;

final class ResponseStatisticsImpl implements ResponseStatistics {
   private final Map responseCodes;
   private final Integer lastResponseCode;

   private ResponseStatisticsImpl(Integer lastResponseCode, Map responseCodes) {
      this.lastResponseCode = lastResponseCode;
      this.responseCodes = Collections.unmodifiableMap(responseCodes);
   }

   public Integer getLastResponseCode() {
      return this.lastResponseCode;
   }

   public Map getResponseCodes() {
      return this.responseCodes;
   }

   public ResponseStatistics snapshot() {
      return this;
   }

   static class Builder {
      private final Map responseCodesMap = new HashMap();
      private Integer lastResponseCode = null;
      private ResponseStatisticsImpl cached = null;

      void addResponseCode(int responseCode) {
         this.cached = null;
         this.lastResponseCode = responseCode;
         Long currentValue = (Long)this.responseCodesMap.get(responseCode);
         if (currentValue == null) {
            currentValue = 0L;
         }

         this.responseCodesMap.put(responseCode, currentValue + 1L);
      }

      ResponseStatisticsImpl build() {
         if (this.cached == null) {
            this.cached = new ResponseStatisticsImpl(this.lastResponseCode, new HashMap(this.responseCodesMap));
         }

         return this.cached;
      }
   }
}
