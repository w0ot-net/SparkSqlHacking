package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface ResponseStatistics {
   Integer getLastResponseCode();

   Map getResponseCodes();

   /** @deprecated */
   @Deprecated
   ResponseStatistics snapshot();
}
