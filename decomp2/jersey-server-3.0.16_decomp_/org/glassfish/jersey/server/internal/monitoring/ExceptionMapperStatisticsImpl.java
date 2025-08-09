package org.glassfish.jersey.server.internal.monitoring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.server.monitoring.ExceptionMapperStatistics;

final class ExceptionMapperStatisticsImpl implements ExceptionMapperStatistics {
   private final Map exceptionMapperExecutionCount;
   private final long successfulMappings;
   private final long unsuccessfulMappings;
   private final long totalMappings;

   private ExceptionMapperStatisticsImpl(Map exceptionMapperExecutionCount, long successfulMappings, long unsuccessfulMappings, long totalMappings) {
      this.exceptionMapperExecutionCount = Collections.unmodifiableMap(exceptionMapperExecutionCount);
      this.successfulMappings = successfulMappings;
      this.unsuccessfulMappings = unsuccessfulMappings;
      this.totalMappings = totalMappings;
   }

   public Map getExceptionMapperExecutions() {
      return this.exceptionMapperExecutionCount;
   }

   public long getSuccessfulMappings() {
      return this.successfulMappings;
   }

   public long getUnsuccessfulMappings() {
      return this.unsuccessfulMappings;
   }

   public long getTotalMappings() {
      return this.totalMappings;
   }

   public ExceptionMapperStatistics snapshot() {
      return this;
   }

   static class Builder {
      private Map exceptionMapperExecutionCountMap = new HashMap();
      private long successfulMappings;
      private long unsuccessfulMappings;
      private long totalMappings;
      private ExceptionMapperStatisticsImpl cached;

      void addMapping(boolean success, int count) {
         this.cached = null;
         ++this.totalMappings;
         if (success) {
            this.successfulMappings += (long)count;
         } else {
            this.unsuccessfulMappings += (long)count;
         }

      }

      void addExceptionMapperExecution(Class mapper, int count) {
         this.cached = null;
         Long cnt = (Long)this.exceptionMapperExecutionCountMap.get(mapper);
         cnt = cnt == null ? (long)count : cnt + (long)count;
         this.exceptionMapperExecutionCountMap.put(mapper, cnt);
      }

      public ExceptionMapperStatisticsImpl build() {
         if (this.cached == null) {
            this.cached = new ExceptionMapperStatisticsImpl(new HashMap(this.exceptionMapperExecutionCountMap), this.successfulMappings, this.unsuccessfulMappings, this.totalMappings);
         }

         return this.cached;
      }
   }
}
