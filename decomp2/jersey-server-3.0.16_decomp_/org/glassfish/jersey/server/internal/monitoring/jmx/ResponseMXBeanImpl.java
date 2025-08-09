package org.glassfish.jersey.server.internal.monitoring.jmx;

import java.util.Map;
import org.glassfish.jersey.server.monitoring.ResponseMXBean;
import org.glassfish.jersey.server.monitoring.ResponseStatistics;

public class ResponseMXBeanImpl implements ResponseMXBean {
   private volatile ResponseStatistics responseStatistics;

   public void updateResponseStatistics(ResponseStatistics responseStatistics) {
      this.responseStatistics = responseStatistics;
   }

   public Map getResponseCodesToCountMap() {
      return this.responseStatistics.getResponseCodes();
   }

   public Integer getLastResponseCode() {
      return this.responseStatistics.getLastResponseCode();
   }
}
