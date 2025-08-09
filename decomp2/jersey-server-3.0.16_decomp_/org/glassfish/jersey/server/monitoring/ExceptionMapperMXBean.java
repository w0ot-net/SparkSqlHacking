package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface ExceptionMapperMXBean {
   Map getExceptionMapperCount();

   long getSuccessfulMappings();

   long getUnsuccessfulMappings();

   long getTotalMappings();
}
