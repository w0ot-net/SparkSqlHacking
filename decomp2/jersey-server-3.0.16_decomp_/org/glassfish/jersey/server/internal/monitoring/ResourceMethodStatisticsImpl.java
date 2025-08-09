package org.glassfish.jersey.server.internal.monitoring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ExecutionStatistics;
import org.glassfish.jersey.server.monitoring.ResourceMethodStatistics;

final class ResourceMethodStatisticsImpl implements ResourceMethodStatistics {
   private final ExecutionStatistics resourceMethodExecutionStatistics;
   private final ExecutionStatistics requestExecutionStatistics;
   private final ResourceMethod resourceMethod;

   private ResourceMethodStatisticsImpl(ResourceMethod resourceMethod, ExecutionStatistics resourceMethodExecutionStatistics, ExecutionStatistics requestExecutionStatistics) {
      this.resourceMethod = resourceMethod;
      this.resourceMethodExecutionStatistics = resourceMethodExecutionStatistics;
      this.requestExecutionStatistics = requestExecutionStatistics;
   }

   public ExecutionStatistics getRequestStatistics() {
      return this.requestExecutionStatistics;
   }

   public ExecutionStatistics getMethodStatistics() {
      return this.resourceMethodExecutionStatistics;
   }

   public ResourceMethod getResourceMethod() {
      return this.resourceMethod;
   }

   public ResourceMethodStatistics snapshot() {
      return this;
   }

   static class Factory {
      private final ConcurrentMap stringToMethodsBuilders = new ConcurrentHashMap();

      Builder getOrCreate(ResourceMethod resourceMethod) {
         String methodUniqueId = MonitoringUtils.getMethodUniqueId(resourceMethod);
         if (!this.stringToMethodsBuilders.containsKey(methodUniqueId)) {
            this.stringToMethodsBuilders.putIfAbsent(methodUniqueId, new Builder(resourceMethod));
         }

         return (Builder)this.stringToMethodsBuilders.get(methodUniqueId);
      }
   }

   static class Builder {
      private final ResourceMethod resourceMethod;
      private final AtomicReference resourceMethodExecutionStatisticsBuilder = new AtomicReference();
      private final AtomicReference requestExecutionStatisticsBuilder = new AtomicReference();
      private volatile ResourceMethodStatisticsImpl cached;

      Builder(ResourceMethod resourceMethod) {
         this.resourceMethod = resourceMethod;
      }

      ResourceMethodStatisticsImpl build() {
         ResourceMethodStatisticsImpl cachedLocalReference = this.cached;
         if (cachedLocalReference != null) {
            return cachedLocalReference;
         } else {
            ExecutionStatistics methodStats = (ExecutionStatistics)(this.resourceMethodExecutionStatisticsBuilder.get() == null ? ExecutionStatisticsImpl.EMPTY : ((ExecutionStatisticsImpl.Builder)this.resourceMethodExecutionStatisticsBuilder.get()).build());
            ExecutionStatistics requestStats = (ExecutionStatistics)(this.requestExecutionStatisticsBuilder.get() == null ? ExecutionStatisticsImpl.EMPTY : ((ExecutionStatisticsImpl.Builder)this.requestExecutionStatisticsBuilder.get()).build());
            ResourceMethodStatisticsImpl stats = new ResourceMethodStatisticsImpl(this.resourceMethod, methodStats, requestStats);
            if (MonitoringUtils.isCacheable(methodStats)) {
               this.cached = stats;
            }

            return stats;
         }
      }

      void addResourceMethodExecution(long methodStartTime, long methodDuration, long requestStartTime, long requestDuration) {
         this.cached = null;
         if (this.resourceMethodExecutionStatisticsBuilder.get() == null) {
            this.resourceMethodExecutionStatisticsBuilder.compareAndSet((Object)null, new ExecutionStatisticsImpl.Builder());
         }

         ((ExecutionStatisticsImpl.Builder)this.resourceMethodExecutionStatisticsBuilder.get()).addExecution(methodStartTime, methodDuration);
         if (this.requestExecutionStatisticsBuilder.get() == null) {
            this.requestExecutionStatisticsBuilder.compareAndSet((Object)null, new ExecutionStatisticsImpl.Builder());
         }

         ((ExecutionStatisticsImpl.Builder)this.requestExecutionStatisticsBuilder.get()).addExecution(requestStartTime, requestDuration);
      }
   }
}
