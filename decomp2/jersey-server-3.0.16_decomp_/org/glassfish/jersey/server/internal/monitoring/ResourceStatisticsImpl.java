package org.glassfish.jersey.server.internal.monitoring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ExecutionStatistics;
import org.glassfish.jersey.server.monitoring.ResourceMethodStatistics;
import org.glassfish.jersey.server.monitoring.ResourceStatistics;

final class ResourceStatisticsImpl implements ResourceStatistics {
   private final Map resourceMethods;
   private final ExecutionStatistics resourceExecutionStatistics;
   private final ExecutionStatistics requestExecutionStatistics;

   private ResourceStatisticsImpl(Map resourceMethods, ExecutionStatistics resourceExecutionStatistics, ExecutionStatistics requestExecutionStatistics) {
      this.resourceMethods = Collections.unmodifiableMap(resourceMethods);
      this.resourceExecutionStatistics = resourceExecutionStatistics;
      this.requestExecutionStatistics = requestExecutionStatistics;
   }

   public ExecutionStatistics getResourceMethodExecutionStatistics() {
      return this.resourceExecutionStatistics;
   }

   public ExecutionStatistics getRequestExecutionStatistics() {
      return this.requestExecutionStatistics;
   }

   public Map getResourceMethodStatistics() {
      return this.resourceMethods;
   }

   public ResourceStatistics snapshot() {
      return this;
   }

   static class Builder {
      private final ConcurrentMap methodsBuilders;
      private final ResourceMethodStatisticsImpl.Factory methodFactory;
      private final AtomicReference resourceExecutionStatisticsBuilder;
      private final AtomicReference requestExecutionStatisticsBuilder;
      private volatile ResourceStatisticsImpl cached;

      Builder(Resource resource, ResourceMethodStatisticsImpl.Factory methodFactory) {
         this(methodFactory);

         for(ResourceMethod method : resource.getResourceMethods()) {
            this.getOrCreate(method);
         }

      }

      Builder(ResourceMethodStatisticsImpl.Factory methodFactory) {
         this.methodsBuilders = new ConcurrentHashMap();
         this.resourceExecutionStatisticsBuilder = new AtomicReference();
         this.requestExecutionStatisticsBuilder = new AtomicReference();
         this.methodFactory = methodFactory;
      }

      ResourceStatisticsImpl build() {
         ResourceStatisticsImpl cachedReference = this.cached;
         if (cachedReference != null) {
            return cachedReference;
         } else {
            Map<ResourceMethod, ResourceMethodStatistics> resourceMethods = new HashMap();

            for(ResourceMethodStatisticsImpl.Builder builder : this.methodsBuilders.keySet()) {
               ResourceMethodStatisticsImpl stats = builder.build();
               resourceMethods.put(stats.getResourceMethod(), stats);
            }

            ExecutionStatistics resourceStats = (ExecutionStatistics)(this.resourceExecutionStatisticsBuilder.get() == null ? ExecutionStatisticsImpl.EMPTY : ((ExecutionStatisticsImpl.Builder)this.resourceExecutionStatisticsBuilder.get()).build());
            ExecutionStatistics requestStats = (ExecutionStatistics)(this.requestExecutionStatisticsBuilder.get() == null ? ExecutionStatisticsImpl.EMPTY : ((ExecutionStatisticsImpl.Builder)this.requestExecutionStatisticsBuilder.get()).build());
            ResourceStatisticsImpl stats = new ResourceStatisticsImpl(resourceMethods, resourceStats, requestStats);
            if (MonitoringUtils.isCacheable(requestStats)) {
               this.cached = stats;
            }

            return stats;
         }
      }

      void addExecution(ResourceMethod resourceMethod, long methodStartTime, long methodDuration, long requestStartTime, long requestDuration) {
         this.cached = null;
         if (this.resourceExecutionStatisticsBuilder.get() == null) {
            this.resourceExecutionStatisticsBuilder.compareAndSet((Object)null, new ExecutionStatisticsImpl.Builder());
         }

         ((ExecutionStatisticsImpl.Builder)this.resourceExecutionStatisticsBuilder.get()).addExecution(methodStartTime, methodDuration);
         if (this.requestExecutionStatisticsBuilder.get() == null) {
            this.requestExecutionStatisticsBuilder.compareAndSet((Object)null, new ExecutionStatisticsImpl.Builder());
         }

         ((ExecutionStatisticsImpl.Builder)this.requestExecutionStatisticsBuilder.get()).addExecution(requestStartTime, requestDuration);
         this.addMethod(resourceMethod);
      }

      void addMethod(ResourceMethod resourceMethod) {
         this.cached = null;
         this.getOrCreate(resourceMethod);
      }

      private ResourceMethodStatisticsImpl.Builder getOrCreate(ResourceMethod resourceMethod) {
         ResourceMethodStatisticsImpl.Builder methodStats = this.methodFactory.getOrCreate(resourceMethod);
         this.methodsBuilders.putIfAbsent(methodStats, Boolean.TRUE);
         return methodStats;
      }
   }
}
