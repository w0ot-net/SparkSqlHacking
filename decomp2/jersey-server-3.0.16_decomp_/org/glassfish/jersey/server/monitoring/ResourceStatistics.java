package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface ResourceStatistics {
   ExecutionStatistics getResourceMethodExecutionStatistics();

   ExecutionStatistics getRequestExecutionStatistics();

   Map getResourceMethodStatistics();

   /** @deprecated */
   @Deprecated
   ResourceStatistics snapshot();
}
