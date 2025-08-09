package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface MonitoringStatistics {
   Map getUriStatistics();

   Map getResourceClassStatistics();

   ExecutionStatistics getRequestStatistics();

   ResponseStatistics getResponseStatistics();

   ExceptionMapperStatistics getExceptionMapperStatistics();

   /** @deprecated */
   @Deprecated
   MonitoringStatistics snapshot();
}
