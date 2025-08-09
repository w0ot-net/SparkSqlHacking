package org.glassfish.jersey.server.monitoring;

public interface TimeWindowStatistics {
   long getTimeWindow();

   double getRequestsPerSecond();

   long getMinimumDuration();

   long getMaximumDuration();

   long getAverageDuration();

   long getRequestCount();

   /** @deprecated */
   @Deprecated
   TimeWindowStatistics snapshot();
}
