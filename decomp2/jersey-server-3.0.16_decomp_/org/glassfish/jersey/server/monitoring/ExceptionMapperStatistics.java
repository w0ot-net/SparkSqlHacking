package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface ExceptionMapperStatistics {
   Map getExceptionMapperExecutions();

   long getSuccessfulMappings();

   long getUnsuccessfulMappings();

   long getTotalMappings();

   /** @deprecated */
   @Deprecated
   ExceptionMapperStatistics snapshot();
}
