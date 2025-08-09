package org.glassfish.jersey.server.internal.monitoring.jmx;

import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.server.monitoring.ExceptionMapperMXBean;
import org.glassfish.jersey.server.monitoring.ExceptionMapperStatistics;

public class ExceptionMapperMXBeanImpl implements ExceptionMapperMXBean {
   private volatile ExceptionMapperStatistics mapperStatistics;
   private volatile Map mapperExcecutions = new HashMap();

   public ExceptionMapperMXBeanImpl(ExceptionMapperStatistics mapperStatistics, MBeanExposer mBeanExposer, String parentName) {
      mBeanExposer.registerMBean(this, parentName + ",exceptions=ExceptionMapper");
      this.updateExceptionMapperStatistics(mapperStatistics);
   }

   public void updateExceptionMapperStatistics(ExceptionMapperStatistics mapperStatistics) {
      this.mapperStatistics = mapperStatistics;

      for(Map.Entry entry : mapperStatistics.getExceptionMapperExecutions().entrySet()) {
         this.mapperExcecutions.put(((Class)entry.getKey()).getName(), entry.getValue());
      }

   }

   public Map getExceptionMapperCount() {
      return this.mapperExcecutions;
   }

   public long getSuccessfulMappings() {
      return this.mapperStatistics.getSuccessfulMappings();
   }

   public long getUnsuccessfulMappings() {
      return this.mapperStatistics.getUnsuccessfulMappings();
   }

   public long getTotalMappings() {
      return this.mapperStatistics.getTotalMappings();
   }
}
