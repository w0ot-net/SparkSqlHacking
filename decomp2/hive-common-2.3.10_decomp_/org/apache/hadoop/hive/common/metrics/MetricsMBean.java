package org.apache.hadoop.hive.common.metrics;

import javax.management.DynamicMBean;
import javax.management.JMException;

public interface MetricsMBean extends DynamicMBean {
   boolean hasKey(String var1);

   void put(String var1, Object var2);

   Object get(String var1) throws JMException;

   void clear();
}
