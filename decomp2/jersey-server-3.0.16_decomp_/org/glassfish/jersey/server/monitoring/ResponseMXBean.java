package org.glassfish.jersey.server.monitoring;

import java.util.Map;

public interface ResponseMXBean {
   Map getResponseCodesToCountMap();

   Integer getLastResponseCode();
}
